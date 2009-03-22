package de.ovgu.cide.typing.fj.af;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;

import tmp.generated_fj.TypeDeclaration;
import tmp.generated_fj.VarDeclaration;
import cide.gparser.ParseException;
import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.af.AlternativeFeatureManager;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.fj.CFJClassTypingCheck;
import de.ovgu.cide.typing.fj.CFJTypeDeclarationWrapper;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * C OK (unter Berücksichtigung alternativer Features)
 * 
 * Grundlegende Voraussetzung: jedes CFJ-Programm ist auch ein gültiges FJ-Programm, wenn man die Annotationen weglässt
 * 
 * @author Malte Rosenthal
 */
public class CFJClassTypingCheckAF extends CFJClassTypingCheck {
	
	private AlternativeFeatureManager altFeatureManager;
	private CFJTypingManagerAF typingManager;
	
	public CFJClassTypingCheckAF(ColoredSourceFile file, CFJTypingManagerAF typingManager, TypeDeclaration typeDeclaration) {
		super(file, typingManager, typeDeclaration);
		this.altFeatureManager = file.getAltFeatureManager();
		this.typingManager = typingManager;
	}

	@Override
	public boolean evaluate(IEvaluationStrategy strategy) {
		
		// XXX MRO
		try {
		
		// (L.3): keine 2 Klassen mit gleichem Namen
		if (!checkL3()) return false;
		
		// (L.5): Klasse darf nicht >Object< heißen
		if (!checkL5()) return false;
		
		List<Alternative> altTypeDeclarations = null;
		try {
			altTypeDeclarations = altFeatureManager.getAlternatives(typeDeclaration, typingManager.rootAlternative);
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
		IFeatureModel fm = file.getFeatureModel();
		
		for (Alternative altTypeDecl : altTypeDeclarations) {
			TypeDeclaration altTypeDeclNode = altTypeDecl.getNode(this.file, typeDeclaration);
			
			// CHECK 1: alternative Klassen müssen den gleichen Namen haben
			if (!altTypeDeclNode.getIdentifier().getValue().equals(typeDeclaration.getIdentifier().getValue())) {
				return createError("Name of class in alternative >" + altTypeDecl.altID + "< differs from name of active class >" + 
								   typeDeclaration.getIdentifier().getValue() + "<.", typeDeclaration.getIdentifier());
			}
			
			// (L.4): keine 2 Methoden mit gleichem Namen
			if (!checkL4(altTypeDeclNode))
				return prependError("Duplicate method declarations in alternative >" + altTypeDecl.altID + "< of class >" + 
								   typeDeclaration.getIdentifier().getValue() + "<.");
			
			CFJTypeDeclarationWrapper supertypeDeclaration = typingManager.findTypeDeclaration(altTypeDeclNode.getExtendedType());
			
			// (L.1): Supertyp muss existieren
			if (supertypeDeclaration == null)
				return createError("Supertype does not exist in alternative >" + altTypeDecl.altID + "< of class >" + 
								   typeDeclaration.getIdentifier().getValue() + "<.", typeDeclaration.getExtendedType());
			
			if (!supertypeDeclaration.isObjectType()) {
				List<Alternative> altSupertypeDeclarations = null;
				try {
					altSupertypeDeclarations = altFeatureManager.getAlternatives(supertypeDeclaration.getTypeDeclaration(), typingManager.rootAlternative);
				} catch (CoreException e) {
					e.printStackTrace();
					return false;
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
				
				List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
				for (Alternative altSupertypeDeclaration : altSupertypeDeclarations) {
					featureSets.add(altSupertypeDeclaration.getFeatures());
				}
				
				if (strategy.mayBeMissing(fm, altTypeDecl.getFeatures(), featureSets)) {
					return createError("Supertype of alternative >" + altTypeDecl.altID + "< does not exist in some variants.", 
									   typeDeclaration.getExtendedType());
				}
			}
			
			ArrayList<VarDeclaration> ownFields = altTypeDeclNode.getVarDeclaration();
			
			if (ownFields != null) {
				for (int i = 0; i < ownFields.size(); ++i) {
					for (int j = i + 1; j < ownFields.size(); ++j) {
						// (L.7i): keine 2 Felder in Klasse gleichem Namen
						if (ownFields.get(i).getIdentifier().getValue().equals(ownFields.get(j).getIdentifier().getValue()))
							return createError("Duplicate field >" + ownFields.get(i).getIdentifier().getValue() + "< in alternative >" + 
											   altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + "<.", ownFields.get(i));
					}

					try {
						List<Alternative> altVarDeclarations = altFeatureManager.getAlternatives(ownFields.get(i), altTypeDecl);
						for (Alternative altVarDeclaration : altVarDeclarations) {
							// CHECK 2: alternative Felder müssen gleichen Namen haben
							if (!altVarDeclaration.getNode(this.file, ownFields.get(i)).getIdentifier().getValue().
									equals(ownFields.get(i).getIdentifier().getValue()))
								return createError("Alternative >" + altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + 
										"<: Name of alternative >" + altVarDeclaration.altID + "< of field >" + 
										ownFields.get(i).getIdentifier().getValue() + "< differs from the others.", ownFields.get(i));
							
							CFJTypeDeclarationWrapper typeDeclOfField = typingManager.findTypeDeclaration(CFJTypingManagerAF.getIdentifier(
										altVarDeclaration.getNode(this.file, ownFields.get(i)).getType()));
							
							// (L.2): Typ des Feldes muss existieren
							if (typeDeclOfField == null)
								return createError("Alternative >" + altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + 
										"<: Type of alternative >" + altVarDeclaration.altID + "< of field >" + 
										ownFields.get(i).getIdentifier().getValue() + "< does not exist.", ownFields.get(i));
							
							List<Alternative> altTypeDeclsOfField = altFeatureManager.getAlternatives(typeDeclOfField, typingManager.rootAlternative);
							List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
							for (Alternative altTypeDeclOfField : altTypeDeclsOfField) {
								featureSets.add(altTypeDeclOfField.getFeatures());
							}

							if (strategy.mayBeMissing(fm, altVarDeclaration.getFeatures(), featureSets))
								return createError("Alternative >" + altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + 
										"<: Type of alternative >" + altVarDeclaration.altID + "< of field >" + 
										ownFields.get(i).getIdentifier().getValue() + "< does not exist in some variants.", ownFields.get(i));

							// (L.7ii): keine 2 Felder in Klasse und allen Superklassen mit gleichem Namen
							if (typingManager.overshadows(altVarDeclaration, ownFields.get(i), altTypeDeclNode, new HashSet<IFeature>(), strategy))
								return createError("Field-overshadowing in alternative >" + altTypeDecl.altID + "< of class >" + 
										typeDeclaration.getIdentifier().getValue() + "<.", ownFields.get(i));
						}
					} catch (CoreException e) {
						e.printStackTrace();
						return false;
					} catch (ParseException e) {
						e.printStackTrace();
						return false;
					}
				}	// for each own field
			}
		}	// for each alternative type declaration
		
		CFJTypeDeclarationWrapper supertypeDeclaration = typingManager.findTypeDeclaration(typeDeclaration.getExtendedType());
		
		// (L.6): Vererbungshierchie darf keine Zyklen enthalten
		if (!inheritanceHierarchyOK(supertypeDeclaration))
			return false;

//		// Konstruktor -----------------------------------------------------------------------------------------------------
//		
//		ClassConstructor constructor = typeDeclaration.getClassConstructor();
//		
//		// (K.4): Konstruktor muss so heißen wie die Klasse
//		if (!typeDeclaration.getIdentifier().getValue().equals(CFJTypingManagerAF.getIdentifier(constructor.getType())))
//			return createError("Name of constructor is not equal to name of class.", constructor.getType());
//		
//		ArrayList<FormalParameter> constructorParameters = 
//			(constructor.getFormalParameterList() == null) ? null : constructor.getFormalParameterList().getFormalParameter();
//		
//		// (K.5): keine 2 formalen Parameter mit gleichem Namen
//		if (constructorParameters != null) {
//			HashSet<String> set = new HashSet<String>(constructorParameters.size());
//			for (FormalParameter param : constructorParameters) {
//				if (set.contains(param.getIdentifier().getValue()))
//					return createError("Duplicate constructor parameters >" + param.getIdentifier().getValue() + "<.", param);
//				set.add(param.getIdentifier().getValue());
//			}
//		}
//	
//		List<FormalParameter> ownConstructorParameters = constructorParameters;
//		List<FormalParameter> superConstructorParameters = null;
//		
//		// Es folgt ein ziemlich hässlicher Codeabschnitt, der v.a. dadurch entstanden ist, dass ich mich tollpatschig bei der
//		// Aufteilung der Konstruktor-Parameter in "Felder der Superklassen" und "eigene Felder" angestellt habe :-) Im Nachhinein
//		// betrachtet geht das bestimmt eleganter, aber ein Refactoring ist mir jetzt nicht sonderlich wichtig, weil in FJ der
//		// Konstruktor eigentlich keine Bedeutung hat, da sich sein Aufbau bereits aus den Felder der Klasse und denen der
//		// Superklassen ergibt.
//		if (constructorParameters == null) {
//			if ((fieldsOfSupertype != null) && !fieldsOfSupertype.isEmpty())
//				return createError("Wrong number of constructor-parameters.", constructor);
//		} else if ((fieldsOfSupertype != null) && !fieldsOfSupertype.isEmpty()) {
//			if (constructorParameters.size() < fieldsOfSupertype.size())
//				return createError("Wrong number of constructor-parameters.", constructor);
//			
//			int i = 0;
//			for (; i < constructorParameters.size(); ++i) {
//				if (i >= fieldsOfSupertype.size())
//					break;
//				
//				if (!CFJTypingManagerAF.typesAreEqual(constructorParameters.get(i).getType(), ((VarDeclaration) fieldsOfSupertype.get(i)).getType()))
//					return createError("First parameters of constructor are not fields of supertypes.", constructor);
//			}
//			
//			ownConstructorParameters = (i >= constructorParameters.size()) ? null : constructorParameters.subList(i, constructorParameters.size());
//			superConstructorParameters = (i < 1) ? null : constructorParameters.subList(0, i);
//		}
//		
//		ArrayList<FieldAssign> fieldAssigns = constructor.getFieldAssign();
//		
//		// Teil von (K.2)
//		this.source = constructor;
//		if (!areEqual(ownFields, fieldAssigns, ownConstructorParameters))
//			return false;
//		
//		if ((ownFields != null) && !ownFields.isEmpty()) {
//			for (int i = 0; i < ownFields.size(); ++i) {
//				// Teil von (K.2)
//				if (!strategy.equal(fm, colorManager.getColors(ownFields.get(i)), colorManager.getColors(fieldAssigns.get(i)))
//						|| !strategy.equal(fm, colorManager.getColors(fieldAssigns.get(i)), colorManager.getColors(ownConstructorParameters.get(i))))
//
//					return createError("Annotations of fields, field assigns and constructor parameters don't fit.", constructor);
//				
//				// Teil von (K.3)
//				CFJTypeDeclarationWrapper typeDecl = 
//					typingManager.findTypeDeclaration(CFJTypingManagerAF.getIdentifier(ownConstructorParameters.get(i).getType()));
//				if ((typeDecl == null) || !strategy.implies(fm, colorManager.getColors(ownConstructorParameters.get(i)), colorManager.getColors(typeDecl)))
//					return createError("Type of constructor parameter does not exist in some variants", ownConstructorParameters.get(i).getType());
//			}
//		}
//		
//		ArrayList<ASTStringNode> superCallParameters = (constructor.getIdentifierList() == null) ? null : constructor.getIdentifierList().getIdentifier();
//		
//		// Teil von (K.1)
//		this.source = constructor;
//		if (!areEqual(superConstructorParameters, superCallParameters, fieldsOfSupertype, strategy))
//			return false;
//		
//		if ((superConstructorParameters != null) && !superConstructorParameters.isEmpty()) {
//			for (int i = 0; i < superConstructorParameters.size(); ++i) {
//				Set<IFeature> f1 = colorManager.getColors(superConstructorParameters.get(i));
//				f1 = CFJTypingManagerAF.addAll(f1, colorManager.getColors(typeDeclaration));
//				Set<IFeature> f2 = colorManager.getColors(superCallParameters.get(i));
//				f2 = CFJTypingManagerAF.addAll(f2, colorManager.getColors(typeDeclaration));
//				Set<IFeature> f3 = colorManager.getColors(fieldsOfSupertype.get(i));
//				f3 = CFJTypingManagerAF.addAll(f3, colorManager.getColors(typeDeclaration));
//				
//				// Teil von (K.1)
//				if (!strategy.equal(fm, f1, f2) || !strategy.equal(fm, f2, f3))
//					return createError("Annotations of constructor parameters, super-parameters and fields of supertype don't fit.", constructor);
//				
//				// Teil von (K.3)
//				CFJTypeDeclarationWrapper typeDecl = 
//					typingManager.findTypeDeclaration(CFJTypingManagerAF.getIdentifier(superConstructorParameters.get(i).getType()));
//				if ((typeDecl == null) || !strategy.implies(fm, f1, colorManager.getColors(typeDecl)))
//					return createError("Type of constructor parameter does not exist in some variants.", superConstructorParameters.get(i).getType());
//			}
//		}
		
		// XXX MRO
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	private boolean inheritanceHierarchyOK(CFJTypeDeclarationWrapper supertypeDeclaration) {
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
	
	@Override
	public String getProblemType() {
		return "de.ovgu.cide.typing.fj.classtyping";
	}
	
//	private boolean areEqual(ArrayList<VarDeclaration> ownFields, ArrayList<FieldAssign> fieldAssigns, List<FormalParameter> ownConstructorParameters) {
//		/* Beispiel:
//		 * --------
//		 * 
//		 * Type1 ownField1;
//		 * Type2 ownField2;
//		 * ...
//		 * 
//		 * ClassName(..., Type1 ownConstructorParam1, Type2 ownConstructorParam2, ...) {
//		 * 		super(...);
//		 * 
//		 * //        Identifier	  Identifier1
//		 * //			  |			   |
//		 * //			  v			   v
//		 * 		this.ownField1 	= ownConstructorParam1;
//		 * 		this.ownField2 	= ownConstructorParam2;
//		 * 		...
//		 * }
//		 */
//		
//		if (!haveSameSize(ownFields, fieldAssigns, ownConstructorParameters))
//			return createError("Number of fields, field assignments and constructor parameters are not identical.");
//		if (ownFields == null)
//			return true;
//		
//		for (int i = 0; i < ownFields.size(); ++i) {
//			if (!ownFields.get(i).getIdentifier().getValue().equals(fieldAssigns.get(i).getIdentifier().getValue()))
//				return createError("Wrong variable of >this<.", fieldAssigns.get(i).getIdentifier());
//			if (!fieldAssigns.get(i).getIdentifier1().getValue().equals(ownConstructorParameters.get(i).getIdentifier().getValue()))
//				return createError("Wrong constructor parameter is assigned.", fieldAssigns.get(i).getIdentifier1());
//			if (!CFJTypingManagerAF.typesAreEqual(ownFields.get(i).getType(), ownConstructorParameters.get(i).getType()))
//				return createError("Type of field and type of constructor parameter are not equal.", ownConstructorParameters.get(i).getType());
//		}
//		
//		return true;
//	}
//	
//	private boolean areEqual(List<FormalParameter> superConstructorParameters, ArrayList<ASTStringNode> superCallParameters,
//							 ArrayList<IASTNode> fieldsOfSupertype, IEvaluationStrategy strategy) {
//		/* Beispiel:
//		 * --------
//		 * 
//		 * ClassName(... superConstructorParam1, ... superConstructorParam2, ...) {
//		 * 		super(superCallParam1, superCallParam2, ...);
//		 * 		...
//		 * }
//		 */
//		
//		// Die Gleichheit der Typen der superConstructorParams mit den Typen der Felder des Supertyps muss hier nicht
//		// überprüft werden, da dies weiter oben schon passiert ist (in diesem hässlichen Codeabschnitt).
//		
//		if (!haveSameSize(superConstructorParameters, superCallParameters, fieldsOfSupertype))
//			return createError("Number of constructor parameters, super-parameters and fields of supertype are not equal.");
//		if (superConstructorParameters == null)
//			return true;
//		
//		for (int i = 0; i < superConstructorParameters.size(); ++i) {
//			if (!superConstructorParameters.get(i).getIdentifier().getValue().equals(superCallParameters.get(i).getValue()))
//				return createError("Wrong super-parameter.",superCallParameters.get(i));
//		}
//		
//		return true;
//	}
//
//	@SuppressWarnings("unchecked")
//	private boolean haveSameSize(List a, List b, List c) {
//		int nrOwnFields = (a == null) ? 0 : a.size();
//		int nrFieldAssigns = (b == null) ? 0 : b.size();
//		int nrOwnConstructorParameters = (c == null) ? 0 : c.size();
//		
//		return ((nrOwnFields == nrFieldAssigns) && (nrFieldAssigns == nrOwnConstructorParameters));
//	}
}
