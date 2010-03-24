package de.ovgu.cide.typing.fj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tmp.generated_fj.ClassConstructor;
import tmp.generated_fj.FieldAssign;
import tmp.generated_fj.FormalParameter;
import tmp.generated_fj.Goal;
import tmp.generated_fj.MethodDeclaration;
import tmp.generated_fj.TypeDeclaration;
import tmp.generated_fj.VarDeclaration;
import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * C OK
 * 
 * Grundlegende Voraussetzung: jedes CFJ-Programm ist auch ein gültiges FJ-Programm, wenn man die Annotationen weglässt
 * 
 * @author Malte Rosenthal
 */
public class CFJClassTypingCheck extends CFJTypingCheck {
	
	protected TypeDeclaration typeDeclaration;
	
	public CFJClassTypingCheck(ColoredSourceFile file, CFJTypingManager typingManager, TypeDeclaration typeDeclaration) {
		super(file, typingManager, typeDeclaration);
		this.typeDeclaration = typeDeclaration;
	}

public boolean evaluate(IEvaluationStrategy strategy) {
		
		// (L.2) wird hier nicht direkt geprüft, da es mittelbar über die Konstruktor-Checks mitgeprüft wird
		
		// (L.3): keine 2 Klassen mit gleichem Namen
		if (!checkL3()) return false;
		
		// (L.4): keine 2 Methoden mit gleichem Namen
		if (!checkL4(typeDeclaration)) return false;
		
		// (L.5): Klasse darf nicht >Object< heißen
		if (!checkL5()) return false;
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		
		CFJTypeDeclarationWrapper supertypeDeclaration = typingManager.findTypeDeclaration(typeDeclaration.getExtendedType());
		
		// (L.1): Supertyp muss existieren
		if (supertypeDeclaration == null)
			return createError("Supertype does not exist.", typeDeclaration.getExtendedType());
		if (!supertypeDeclaration.isObjectType() 
				&& !strategy.implies(fm, colorManager.getColors(typeDeclaration), colorManager.getColors(supertypeDeclaration.getTypeDeclaration())))
			return createError("Supertype does not exist in some variants.", typeDeclaration.getExtendedType());
		
		// (L.6): Vererbungshierchie darf keine Zyklen enthalten
		if (!inheritanceHierarchyOK(supertypeDeclaration))
			return false;
		
		ArrayList<VarDeclaration> ownFields = typeDeclaration.getVarDeclaration();
		ArrayList<IASTNode> fieldsOfSupertype = new ArrayList<IASTNode>(typingManager.fields(supertypeDeclaration));	
		
		// (L.7): keine 2 Felder in Klasse und allen Superklassen mit gleichem Namen
		if (ownFields != null) {
			for (int i = 0; i < ownFields.size(); ++i) {
				for (int j = i + 1; j < ownFields.size(); ++j) {
					if (ownFields.get(i).getIdentifier().getValue().equals(ownFields.get(j).getIdentifier().getValue()))
						return createError("Duplicate field >" + ownFields.get(i).getIdentifier().getValue() + "<.", ownFields.get(i));
				}

				for (int j = 0; j < fieldsOfSupertype.size(); ++j) {
					if (ownFields.get(i).getIdentifier().getValue().equals(((VarDeclaration) fieldsOfSupertype.get(j)).getIdentifier().getValue()))
						return createError("Duplicate field >" + ownFields.get(i).getIdentifier().getValue() + "< in supertype.", ownFields.get(i));
				}
			}
		}

		// Konstruktor -----------------------------------------------------------------------------------------------------
		
		ClassConstructor constructor = typeDeclaration.getClassConstructor();
		
		// (K.4): Konstruktor muss so heißen wie die Klasse
		if (!typeDeclaration.getIdentifier().getValue().equals(CFJTypingManager.getIdentifier(constructor.getType())))
			return createError("Name of constructor is not equal to name of class.", constructor.getType());
		
		ArrayList<FormalParameter> constructorParameters = 
			(constructor.getFormalParameterList() == null) ? null : constructor.getFormalParameterList().getFormalParameter();
		
		// (K.5): keine 2 formalen Parameter mit gleichem Namen
		if (constructorParameters != null) {
			HashSet<String> set = new HashSet<String>(constructorParameters.size());
			for (FormalParameter param : constructorParameters) {
				if (set.contains(param.getIdentifier().getValue()))
					return createError("Duplicate constructor parameters >" + param.getIdentifier().getValue() + "<.", param);
				set.add(param.getIdentifier().getValue());
			}
		}
	
		List<FormalParameter> ownConstructorParameters = constructorParameters;
		List<FormalParameter> superConstructorParameters = null;
		
		// Es folgt ein ziemlich hässlicher Codeabschnitt, der v.a. dadurch entstanden ist, dass ich mich tollpatschig bei der
		// Aufteilung der Konstruktor-Parameter in "Felder der Superklassen" und "eigene Felder" angestellt habe :-) Im Nachhinein
		// betrachtet geht das bestimmt eleganter, aber ein Refactoring ist mir jetzt nicht sonderlich wichtig, weil in FJ der
		// Konstruktor eigentlich keine Bedeutung hat, da sich sein Aufbau bereits aus den Felder der Klasse und denen der
		// Superklassen ergibt.
		if (constructorParameters == null) {
			if ((fieldsOfSupertype != null) && !fieldsOfSupertype.isEmpty())
				return createError("Wrong number of constructor-parameters.", constructor);
		} else if ((fieldsOfSupertype != null) && !fieldsOfSupertype.isEmpty()) {
			if (constructorParameters.size() < fieldsOfSupertype.size())
				return createError("Wrong number of constructor-parameters.", constructor);
			
			int i = 0;
			for (; i < constructorParameters.size(); ++i) {
				if (i >= fieldsOfSupertype.size())
					break;
				
				if (!CFJTypingManager.typesAreEqual(constructorParameters.get(i).getType(), ((VarDeclaration) fieldsOfSupertype.get(i)).getType()))
					return createError("First parameters of constructor are not fields of supertypes.", constructor);
			}
			
			ownConstructorParameters = (i >= constructorParameters.size()) ? null : constructorParameters.subList(i, constructorParameters.size());
			superConstructorParameters = (i < 1) ? null : constructorParameters.subList(0, i);
		}
		
		ArrayList<FieldAssign> fieldAssigns = constructor.getFieldAssign();
		
		// Teil von (K.2)
		this.source = constructor;
		if (!areEqual(ownFields, fieldAssigns, ownConstructorParameters))
			return false;
		
		if ((ownFields != null) && !ownFields.isEmpty()) {
			for (int i = 0; i < ownFields.size(); ++i) {
				// Teil von (K.2)
				if (!strategy.equal(fm, colorManager.getColors(ownFields.get(i)), colorManager.getColors(fieldAssigns.get(i)))
						|| !strategy.equal(fm, colorManager.getColors(fieldAssigns.get(i)), colorManager.getColors(ownConstructorParameters.get(i))))

					return createError("Annotations of fields, field assigns and constructor parameters don't fit.", constructor);
				
				// Teil von (K.3)
				CFJTypeDeclarationWrapper typeDecl = 
					typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(ownConstructorParameters.get(i).getType()));
				if ((typeDecl == null) || !strategy.implies(fm, colorManager.getColors(ownConstructorParameters.get(i)), colorManager.getColors(typeDecl)))
					return createError("Type of constructor parameter does not exist in some variants", ownConstructorParameters.get(i).getType());
			}
		}
		
		ArrayList<ASTStringNode> superCallParameters = (constructor.getIdentifierList() == null) ? null : constructor.getIdentifierList().getIdentifier();
		
		// Teil von (K.1)
		this.source = constructor;
		if (!areEqual(superConstructorParameters, superCallParameters, fieldsOfSupertype, strategy))
			return false;
		
		if ((superConstructorParameters != null) && !superConstructorParameters.isEmpty()) {
			for (int i = 0; i < superConstructorParameters.size(); ++i) {
				Set<IFeature> f1 = colorManager.getColors(superConstructorParameters.get(i));
				f1 = CFJTypingManager.addAll(f1, colorManager.getColors(typeDeclaration));
				Set<IFeature> f2 = colorManager.getColors(superCallParameters.get(i));
				f2 = CFJTypingManager.addAll(f2, colorManager.getColors(typeDeclaration));
				Set<IFeature> f3 = colorManager.getColors(fieldsOfSupertype.get(i));
				f3 = CFJTypingManager.addAll(f3, colorManager.getColors(typeDeclaration));
				
				// Teil von (K.1)
				if (!strategy.equal(fm, f1, f2) || !strategy.equal(fm, f2, f3))
					return createError("Annotations of constructor parameters, super-parameters and fields of supertype don't fit.", constructor);
				
				// Teil von (K.3)
				CFJTypeDeclarationWrapper typeDecl = 
					typingManager.findTypeDeclaration(CFJTypingManager.getIdentifier(superConstructorParameters.get(i).getType()));
				if ((typeDecl == null) || !strategy.implies(fm, f1, colorManager.getColors(typeDecl)))
					return createError("Type of constructor parameter does not exist in some variants.", superConstructorParameters.get(i).getType());
			}
		}
		
		return true;
	}
	
	/**
	 * (L.3): keine 2 Klassen mit gleichem Namen
	 */
	protected boolean checkL3() {
		ArrayList<TypeDeclaration> typeDeclarations = ((Goal) typeDeclaration.getParent()).getTypeDeclaration();
		if (typeDeclarations != null) {
			boolean typeDeclarationAlreadyFound = false;
			
			for (int i = 0; i < typeDeclarations.size(); ++i) {
				if (typeDeclarations.get(i).getIdentifier().getValue().equals(typeDeclaration.getIdentifier().getValue())) {
					if (typeDeclarationAlreadyFound)
						return createError("Duplicate type declaration >" + typeDeclarations.get(i).getIdentifier().getValue() + "<.", 
							typeDeclaration);
					else
						typeDeclarationAlreadyFound = true;
				}
			}
		}
		return true;
	}
	
	/**
	 * (L.4): keine 2 Methoden mit gleichem Namen
	 */
	protected boolean checkL4(TypeDeclaration typeDeclaration) {
		ArrayList<MethodDeclaration> methodDeclarations = typeDeclaration.getMethodDeclaration();
		if (methodDeclarations != null) {
			for (int i = 0; i < methodDeclarations.size(); ++i) {
				for (int j = i + 1; j < methodDeclarations.size(); ++j) {
					if (methodDeclarations.get(i).getIdentifier().getValue().equals(methodDeclarations.get(j).getIdentifier().getValue()))
						return createError("Duplicate method declaration >" + methodDeclarations.get(i).getIdentifier().getValue() + "<.", 
											methodDeclarations.get(j));
				}
			}
		}
		return true;
	}
	
	/**
	 * (L.5): Klasse darf nicht >Object< heißen
	 */
	protected boolean checkL5() {
		if (typeDeclaration.getIdentifier().getValue().equals("Object"))
			return createError("Illegal classname >Object<", typeDeclaration.getIdentifier());
		return true;
	}

	protected boolean inheritanceHierarchyOK(CFJTypeDeclarationWrapper supertypeDeclaration) {
		if (supertypeDeclaration == null)
			return false;
		List<String> knownClassNames = new LinkedList<String>();
		
		String className = typeDeclaration.getIdentifier().getValue();
		knownClassNames.add(className);
		
		while (!supertypeDeclaration.isObjectType()) {
			String nameOfSupertype = supertypeDeclaration.getIdentifier().getValue();
			
			if (knownClassNames.contains(nameOfSupertype))
				return createError("Cyclic class hierarchie through >" + nameOfSupertype + "<", typeDeclaration);
			knownClassNames.add(nameOfSupertype);
			
			supertypeDeclaration = typingManager.findTypeDeclaration(supertypeDeclaration.getTypeDeclaration().getExtendedType());
			if (supertypeDeclaration == null)
				return createError("Supertype of class >" + nameOfSupertype + "< does not exist.", typeDeclaration);
		}
		
		return true;
	}
	
	private boolean areEqual(ArrayList<VarDeclaration> ownFields, ArrayList<FieldAssign> fieldAssigns, List<FormalParameter> ownConstructorParameters) {
		/* Beispiel:
		 * --------
		 * 
		 * Type1 ownField1;
		 * Type2 ownField2;
		 * ...
		 * 
		 * ClassName(..., Type1 ownConstructorParam1, Type2 ownConstructorParam2, ...) {
		 * 		super(...);
		 * 
		 * //        Identifier	  Identifier1
		 * //			  |			   |
		 * //			  v			   v
		 * 		this.ownField1 	= ownConstructorParam1;
		 * 		this.ownField2 	= ownConstructorParam2;
		 * 		...
		 * }
		 */
		
		if (!haveSameSize(ownFields, fieldAssigns, ownConstructorParameters))
			return createError("Number of fields, field assignments and constructor parameters are not identical.");
		if (ownFields == null)
			return true;
		
		for (int i = 0; i < ownFields.size(); ++i) {
			if (!ownFields.get(i).getIdentifier().getValue().equals(fieldAssigns.get(i).getIdentifier().getValue()))
				return createError("Wrong variable of >this<.", fieldAssigns.get(i).getIdentifier());
			if (!fieldAssigns.get(i).getIdentifier1().getValue().equals(ownConstructorParameters.get(i).getIdentifier().getValue()))
				return createError("Wrong constructor parameter is assigned.", fieldAssigns.get(i).getIdentifier1());
			if (!CFJTypingManager.typesAreEqual(ownFields.get(i).getType(), ownConstructorParameters.get(i).getType()))
				return createError("Type of field and type of constructor parameter are not equal.", ownConstructorParameters.get(i).getType());
		}
		
		return true;
	}
	
	private boolean areEqual(List<FormalParameter> superConstructorParameters, ArrayList<ASTStringNode> superCallParameters,
							 ArrayList<IASTNode> fieldsOfSupertype, IEvaluationStrategy strategy) {
		/* Beispiel:
		 * --------
		 * 
		 * ClassName(... superConstructorParam1, ... superConstructorParam2, ...) {
		 * 		super(superCallParam1, superCallParam2, ...);
		 * 		...
		 * }
		 */
		
		// Die Gleichheit der Typen der superConstructorParams mit den Typen der Felder des Supertyps muss hier nicht
		// überprüft werden, da dies weiter oben schon passiert ist (in diesem hässlichen Codeabschnitt).
		
		if (!haveSameSize(superConstructorParameters, superCallParameters, fieldsOfSupertype))
			return createError("Number of constructor parameters, super-parameters and fields of supertype are not equal.");
		if (superConstructorParameters == null)
			return true;
		
		for (int i = 0; i < superConstructorParameters.size(); ++i) {
			if (!superConstructorParameters.get(i).getIdentifier().getValue().equals(superCallParameters.get(i).getValue()))
				return createError("Wrong super-parameter.",superCallParameters.get(i));
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	private boolean haveSameSize(List a, List b, List c) {
		int nrOwnFields = (a == null) ? 0 : a.size();
		int nrFieldAssigns = (b == null) ? 0 : b.size();
		int nrOwnConstructorParameters = (c == null) ? 0 : c.size();
		
		return ((nrOwnFields == nrFieldAssigns) && (nrFieldAssigns == nrOwnConstructorParameters));
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.fj.classtyping";
	}
}
