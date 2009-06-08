package de.ovgu.cide.typing.fj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;

import tmp.generated_fj.AllocationExpression;
import tmp.generated_fj.CastExpression;
import tmp.generated_fj.ClassConstructor;
import tmp.generated_fj.Expression;
import tmp.generated_fj.ExpressionList;
import tmp.generated_fj.ExtendedType;
import tmp.generated_fj.ExtendedType1;
import tmp.generated_fj.FieldInvoke;
import tmp.generated_fj.FormalParameter;
import tmp.generated_fj.FormalParameterList;
import tmp.generated_fj.InvokeTarget;
import tmp.generated_fj.InvokeTarget1;
import tmp.generated_fj.InvokeTarget2;
import tmp.generated_fj.InvokeTarget3;
import tmp.generated_fj.InvokeTarget4;
import tmp.generated_fj.MethodDeclaration;
import tmp.generated_fj.MethodInvoke;
import tmp.generated_fj.NestedExpression;
import tmp.generated_fj.PrimaryExpression;
import tmp.generated_fj.PrimaryExpression1;
import tmp.generated_fj.PrimaryExpression2;
import tmp.generated_fj.PrimaryExpression3;
import tmp.generated_fj.PrimaryExpression4;
import tmp.generated_fj.PrimaryExpression5;
import tmp.generated_fj.PrimaryExpression6;
import tmp.generated_fj.PrimaryExpression7;
import tmp.generated_fj.PrimaryExpression8;
import tmp.generated_fj.Type;
import tmp.generated_fj.Type1;
import tmp.generated_fj.Type2;
import tmp.generated_fj.TypeDeclaration;
import tmp.generated_fj.VarDeclaration;
import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gparser.ParseException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * Manager für das CFJ-Typsystem
 * 
 * Grundlegende Voraussetzung: jedes CFJ-Programm ist auch ein gültiges FJ-Programm, wenn man die Annotationen weglässt
 * 
 * @author Malte Rosenthal
 */
public class CFJTypingManager {
	
	protected ColoredSourceFile file;
	protected List<String> errorMessages;
	
	private IASTNode ast;
	
	private Map<IASTNode, List<IASTNode>> node2fields;
	private Map<String, CFJTypeDeclarationWrapper> typeID2typeDeclaration;
	private Map<IASTNode, CFJType> node2type;
	private Map<String, CFJType> typeID2type;
	private Map<String, CFJType> typeID2superType;
	
	public CFJTypingManager(ColoredSourceFile file) throws CoreException, ParseException {
		this.file = file;
		ast = file.getAST();
		
		node2fields = new HashMap<IASTNode, List<IASTNode>>();
		
		typeID2typeDeclaration = new HashMap<String, CFJTypeDeclarationWrapper>();
		typeID2typeDeclaration.put("Object", new CFJTypeDeclarationWrapper());
		
		node2type = new HashMap<IASTNode, CFJType>();
		typeID2type = new HashMap<String, CFJType>();
		typeID2superType = new HashMap<String, CFJType>();
		
		errorMessages = new LinkedList<String>();
	}
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	
	public void clearErrorMessages() {
		errorMessages.clear();
	}
	
	public static String getIdentifier(Type type) {
		if (type == null)
			return null;
		
		if (type instanceof Type1)
			return ((Type1) type).getIdentifier().getValue();
		return "Object";
	}
	
	public static String getIdentifier(ExtendedType type) {
		if (type == null)
			return null;
		
		if (type instanceof ExtendedType1)
			return ((ExtendedType1) type).getIdentifier().getValue();
		return "Object";
	}
	
	public static boolean typesAreEqual(Type typeA, Type typeB) {
		if (typeA instanceof Type1) {
			if (!(typeB instanceof Type1))
				return false;
			if (!((Type1) typeA).getIdentifier().getValue().equals(((Type1) typeB).getIdentifier().getValue()))
				return false;
		} else if ((typeA instanceof Type2) && !(typeB instanceof Type2))
			return false;
		
		return true;
	}
	
	public static class MethodSignature {
		public Type returnType;
		public FormalParameterList formalParameters;
		
		public MethodSignature(MethodDeclaration method) {
			this(method.getType(), method.getFormalParameterList());
		}
		
		public MethodSignature(Type returnType, FormalParameterList formalParameters) {
			this.returnType = returnType;
			this.formalParameters = formalParameters;
		}
		
		@Override
		public boolean equals(Object obj) {
			if ((obj == null) || !(obj instanceof MethodSignature))
				return false;
			MethodSignature that = (MethodSignature) obj;
			
			if (!typesAreEqual(this.returnType, that.returnType))
				return false;
			
			List<FormalParameter> thisFormalParameters = (this.formalParameters == null) ? null : this.formalParameters.getFormalParameter();
			List<FormalParameter> thatFormalParameters = (that.formalParameters == null) ? null : that.formalParameters.getFormalParameter();
			
			if (thisFormalParameters == null) {
				if (thatFormalParameters != null)
					return false;
			} else {
				if (thatFormalParameters == null)
					return false;
				if (thisFormalParameters.size() != thatFormalParameters.size())
					return false;
				
				for (int i = 0; i < thisFormalParameters.size(); ++i) {
					if (!typesAreEqual(thisFormalParameters.get(i).getType(), thatFormalParameters.get(i).getType()))
						return false;
				}
			}
			
			return true;
		}
	}
	
	/**
	 * Zwei Methoden sind gleich, wenn sie gleich heißen und die gleiche Signatur haben
	 * @param method1
	 * @param method2
	 * @return
	 */
	public static boolean methodsAreEqual(MethodDeclaration method1, MethodDeclaration method2) {
		if (method1 == null)
			return (method2 == null);
		if (method2 == null)
			return false;
		
		return (method1.getIdentifier().getValue().equals(method2.getIdentifier().getValue()) 
				&& new MethodSignature(method1).equals(new MethodSignature(method2)));
	}
	
	/**
	 * Repräsentiert einen Typ, der vom Typsystem ermittelt wird
	 */
	public class CFJType {
		public String identifier;
		public CFJTypeDeclarationWrapper astNode;
		
		public CFJType(String identifier) {
			this.identifier = identifier;
		}
		
		public CFJType(CFJTypeDeclarationWrapper astNode) {
			this.astNode = astNode;
			if (this.astNode != null)
				this.identifier = this.astNode.getIdentifier().getValue();
		}
		
		public CFJType(TypeDeclaration astNode) {
			this(new CFJTypeDeclarationWrapper(astNode));
		}
		
		public CFJTypeDeclarationWrapper getASTNode() {
			if (astNode == null) {
				astNode = findTypeDeclaration(identifier);
			}
			return astNode;
		}
		
		public CFJType getSuperType() {
			if (typeID2superType.containsKey(identifier))
				return typeID2superType.get(identifier);
			
			if (astNode == null)
				astNode = findTypeDeclaration(identifier);
			if (astNode != null) {
				CFJType superType = getType(getIdentifier(astNode.getExtendedType()));
				typeID2superType.put(identifier, superType);
				return superType;
			}
			
			return null;
		}
		
		public boolean isSubtypeOf(CFJType superType) {
			if (superType == null)
				return false;
			
			CFJType subType = this;
			do {
				if (subType.equals(superType))
					return true;
			} while ((subType = subType.getSuperType()) != null);
			
			return false;
		}
		
		public boolean isProperSubtypeOf(CFJType superType) {
			CFJType parent = this.getSuperType();
			if (parent != null)
				return parent.isSubtypeOf(superType);
			return false;
		}
		
		@Override
		public boolean equals(Object obj) {
			if ((obj == null) || (!(obj instanceof CFJType)))
				return false;
			CFJType that = (CFJType) obj;
			
			if (this.identifier == null)
				return (that.identifier == null);
			return this.identifier.equals(that.identifier);
		}
	}
	
	/**
	 * Erzeugt eine Instanz von CFJType aus dem gegebenen Namen des Typs
	 * @param identifier	Name des Typs
	 * @return
	 */
	public CFJType getType(String identifier) {
		if (typeID2type.containsKey(identifier))
			return typeID2type.get(identifier);
		
		CFJType type = new CFJType(identifier);
		typeID2type.put(identifier, type);
		return type;
	}
	
	public CFJType getType(CFJTypeDeclarationWrapper astNode) {
		if (astNode == null)
			return null;
		
		CFJType result;
		if (typeID2type.containsKey(astNode.getIdentifier().getValue())) {
			result = typeID2type.get(astNode.getIdentifier().getValue());
			result.astNode = astNode;
		} else {
			result = new CFJType(astNode);
			typeID2type.put(astNode.getIdentifier().getValue(), result);
		}
		return result;
	}
	
	public CFJType getType(TypeDeclaration astNode) {
		if (astNode == null)
			return null;
		
		CFJType result;
		if (typeID2type.containsKey(astNode.getIdentifier().getValue())) {
			result = typeID2type.get(astNode.getIdentifier().getValue());
			if (result.astNode == null)
				result.astNode = new CFJTypeDeclarationWrapper(astNode);
			else
				result.astNode.setTypeDeclaration(astNode);
		} else {
			result = new CFJType(astNode);
			typeID2type.put(astNode.getIdentifier().getValue(), result);
		}
		return result;
	}
	
	public boolean areSubtypes(ArrayList<CFJType> subTypes, ArrayList<CFJType> superTypes) {
		if (subTypes == null)
			return (superTypes == null);
		if (!haveSameSize(subTypes, superTypes))
			return false;
		
		for (int i = 0; i < subTypes.size(); ++i) {
			if (subTypes.get(i) == null) {
				if (superTypes.get(i) != null)
					return false;
			} else if (!subTypes.get(i).isSubtypeOf(superTypes.get(i)))
				return false;
		}
		return true;
	}
	
	public boolean haveSameSize(ArrayList<CFJType> subTypes, ArrayList<CFJType> superTypes) {
		if (subTypes == null)
			return (superTypes == null);
		if ((superTypes == null) || (subTypes.size() != superTypes.size()))
			return false;
		
		return true;
	}
	
	// Auxiliary definitions -----------------------------------------------------------------------------------------------
	
	public List<IASTNode> filter(List<IASTNode> nodes, Set<IFeature> features, IEvaluationStrategy strategy) {
		if ((nodes == null) || (strategy == null))
			return null;
		if (features == null)
			features = new HashSet<IFeature>();
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		List<IASTNode> result = new LinkedList<IASTNode>();
		
		for (IASTNode node : nodes) {
			Set<IFeature> colors = colorManager.getColors(node);
			if (colors == null)
				colors = new HashSet<IFeature>();
			if (strategy.implies(fm, features, colors))
				result.add(node);
		}
		
		return result;
	}
	
	public List<IASTNode> fields(IASTNode node) {
		if (node == null)
			return null;
		if (node2fields.containsKey(node))
			return node2fields.get(node);
		
		LinkedList<IASTNode> result = new LinkedList<IASTNode>();
		
		if ((node instanceof CFJTypeDeclarationWrapper) && (((CFJTypeDeclarationWrapper) node).getTypeDeclaration() != null))
			node = ((CFJTypeDeclarationWrapper) node).getTypeDeclaration();
		
		if (node instanceof TypeDeclaration) {
			result.addAll(((TypeDeclaration) node).getVarDeclaration());
			
			ExtendedType extendedType = ((TypeDeclaration) node).getExtendedType();
			if (extendedType instanceof ExtendedType1) {
				List<IASTNode> fieldsOfSupertype = fields(findTypeDeclaration(((ExtendedType1) extendedType).getIdentifier().getValue()));
				if (fieldsOfSupertype != null)
					// Reihenfolge der fields ist wichtig: Superklassen hängen immer vorne an
					result.addAll(0, fieldsOfSupertype);
			}
		}
		
		node2fields.put(node, result);
		return result;
	}
	
	/**
	 * Gibt alle Methoden mit gegebenem Namen zurück, die in der Vererbungshierarchie ab node aufwärts gefunden werden.
	 * @param identifier
	 * @param node
	 * @return
	 */
	public List<MethodDeclaration> mlookupAll(String identifier, CFJTypeDeclarationWrapper node) {
		if ((identifier == null) || (node == null))
			return null;
		
		List<MethodDeclaration> result = new LinkedList<MethodDeclaration>();		
		do {
			ArrayList<MethodDeclaration> allMethodDeclarations = node.getMethodDeclaration();
			if (allMethodDeclarations != null) {
				for (int i = 0; i < allMethodDeclarations.size(); ++i) {
					if (allMethodDeclarations.get(i).getIdentifier().getValue().equals(identifier))
						result.add(allMethodDeclarations.get(i));
				}
			}
			node = findTypeDeclaration(node.getExtendedType());
		} while ((node != null) && !node.isObjectType());
		
		return result;
	}
	
	/**
	 * Gibt die erste Methode mit gegebenem Namen zurück, die in der Vererbungshierarchie ab node aufwärts gefunden wird.
	 * @param identifier
	 * @param node
	 * @return
	 */
	public IASTNode mlookup(String identifier, CFJTypeDeclarationWrapper node) {
		if ((identifier == null) || (node == null))
			return null;
		
		ArrayList<MethodDeclaration> allMethodDeclarations = node.getMethodDeclaration();
		List<IASTNode> methodDeclarations = new LinkedList<IASTNode>();
		
		if (allMethodDeclarations != null) {
			for (int i = 0; i < allMethodDeclarations.size(); ++i) {
				if (allMethodDeclarations.get(i).getIdentifier().getValue().equals(identifier))
					methodDeclarations.add(allMethodDeclarations.get(i));
			}
		}

		if ((methodDeclarations == null) || methodDeclarations.isEmpty()) {
			return mlookup(identifier, findTypeDeclaration(node.getExtendedType()));
		}
		
		// methodDeclarations.size() != 1 kann eigentlich nicht passieren, weil in der Klasse überprüft wird, dass es
		// keine zwei Methoden mit gleichem Namen gibt.
		
		return methodDeclarations.get(0);
	}
	
	/**
	 * Gibt eine Liste von Signaturen zurück, die für die Überprüfung von Overriding und Methodenaufrufen relevant sind. Details sind
	 * informell sehr schwer zu erklären. Also: siehe Sourcecode ;-)
	 * @param identifier
	 * @param node
	 * @param features
	 * @param strategy
	 * @return
	 */
	protected List<MethodSignature> mtypes(String identifier, CFJTypeDeclarationWrapper node, Set<IFeature> features, IEvaluationStrategy strategy) {
		List<MethodDeclaration> methods = mlookupAll(identifier, node);
		if (methods == null)
			return null;
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		
		List<MethodSignature> result = new LinkedList<MethodSignature>();
		for (MethodDeclaration method : methods) {
			Set<IFeature> featuresWithMethod = addAll(features, colorManager.getColors(method));
			
			// Eine Methode ist nur dann relevant, wenn sie zusammen mit den gegebenen Features präsent sein kann
			if (strategy.exists(fm, featuresWithMethod)) {
				List<Set<IFeature>> mayBeMissing = new LinkedList<Set<IFeature>>();
				
				for (MethodDeclaration method2 : methods) {
					if (method2 == method)
						break;
					mayBeMissing.add(colorManager.getColors(method2));
				}

				// Die Methode ist nur dann relevant, wenn es eine Konfiguration gibt, in der keine der "Methoden unterhalb" im Kontext der
				// gegebenen Features und der Methode präsent ist
				if (mayBeMissing.isEmpty() || strategy.mayBeMissing(fm, featuresWithMethod, mayBeMissing))
					result.add(new MethodSignature(method.getType(), method.getFormalParameterList()));
			}
		}
		
		return result;
	}
	
	/**
	 * Gibt die Signatur der ersten Methode mit gegebenem Namen zurück, die in der Vererbungshierarchie ab node gefunden wird.
	 * @param identifier
	 * @param node
	 * @return
	 */
	public MethodSignature mtype(String identifier, CFJTypeDeclarationWrapper node) {
		MethodDeclaration method = (MethodDeclaration) mlookup(identifier, node);
		if (method == null)
			return null;
		
		return new MethodSignature(method.getType(), method.getFormalParameterList());
	}
	
	/**
	 * Eine Methode ist ein gültiges Overriding, falls
	 * 1. sie ein gültiges Overriding im Sinne von FJ ist UND
	 * 2. die Farben der Parameter mit jeder Methode gleichen Namens in den Superklassen zusammenpassen (s.u.), mit der sie gleichzeitig
	 *    präsent sein kann.
	 *       
	 * @param method
	 * @param strategy
	 * @return
	 */
	public boolean override(MethodDeclaration method, IEvaluationStrategy strategy) {
		if (method == null)
			return false;
		
		TypeDeclaration typeDeclaration = findSurroundingTypeDeclaration(method);
		if (typeDeclaration == null)
			return false;
		
		CFJTypeDeclarationWrapper superTypeDeclaration = findTypeDeclaration(typeDeclaration.getExtendedType());
		if (superTypeDeclaration == null)
			return false;

		String identifier = method.getIdentifier().getValue();
		MethodSignature signature = new MethodSignature(method);
		
		// Überprüfung im Sinne von FJ
		MethodSignature existentSignature = mtype(identifier, superTypeDeclaration);
		if (existentSignature == null)
			return true;
		if (!signature.equals(existentSignature))
			return false;
		
		if (signature.formalParameters == null)
			return true;
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		Set<IFeature> features = colorManager.getColors(method);
		
		List<MethodSignature> existentSignatures = mtypes(identifier, superTypeDeclaration, features, strategy);
		if ((existentSignatures == null) || existentSignatures.isEmpty())
			return true;
		
		for (MethodSignature sig : existentSignatures) {
			// Im Kontext der Farben beider Methoden müssen die Parameter beider Methoden in genau den gleichen
			// Varianten präsent sein.
			for (int i = 0; i < signature.formalParameters.getFormalParameter().size(); ++i) {
				Set<IFeature> source = colorManager.getColors(signature.formalParameters.getFormalParameter().get(i));
				source = addAll(addAll(source, features), getFeaturesOfParent(sig.returnType));
				Set<IFeature> target = colorManager.getColors(sig.formalParameters.getFormalParameter().get(i));
				target = addAll(target, features);

				if (!strategy.equal(fm, source, target)) {
					errorMessages.add(0, "Annotations of parameters don't fit with method in class >" + 
										 findSurroundingTypeDeclaration(sig.returnType).getIdentifier().getValue() + "<.");
					return false;
				}
			}
		}
		
		return true;
	}
	
	// Typing rules -------------------------------------------------------------------------------------------------------------
	
	/**
	 * T-VAR: lokale Variablen
	 * @param identifier
	 * @param method
	 * @return
	 */
	private CFJType typeOf(ASTStringNode identifier, MethodDeclaration method, IEvaluationStrategy strategy) {
		if ((identifier == null) || (method == null))
			return null;
		
		FormalParameterList formalParameterList = method.getFormalParameterList();
		ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
		
		if (formalParameters != null) {
			SourceFileColorManager colorManager = file.getColorManager();
			
			// (T.1) -> (V.1): Variable muss existieren
			List<IASTNode> presentParameters = filter(cast(formalParameters), colorManager.getColors(identifier), strategy);
			for (IASTNode node : presentParameters) {
				if (((FormalParameter) node).getIdentifier().getValue().equals(identifier.getValue()))
					return getType(getIdentifier(((FormalParameter) node).getType()));
			}
		}
		
		errorMessages.add(0, "Variable >" + identifier + "< does not exist in some variants.");
		return null;
	}
	
	private CFJType typeOf(ASTStringNode identifier, ClassConstructor constructor, IEvaluationStrategy strategy) {
		if ((identifier == null) || (constructor == null))
			return null;
		
		FormalParameterList formalParameterList = constructor.getFormalParameterList();
		ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
		
		if (formalParameters != null) {
			SourceFileColorManager colorManager = file.getColorManager();
			
			// (T.1) -> (V.1): Variable muss existieren
			List<IASTNode> presentParameters = filter(cast(formalParameters), colorManager.getColors(identifier), strategy);
			for (IASTNode node : presentParameters) {
				if (((FormalParameter) node).getIdentifier().getValue().equals(identifier.getValue()))
					return getType(getIdentifier(((FormalParameter) node).getType()));
			}
		}
		
		errorMessages.add(0, "Variable >" + identifier + "< does not exist in some variants.");
		return null;
	}
	
	/**
	 * T-FIELD: Zugriff auf ein Feld (instance.field oder this.field)
	 * @param fieldInvoke
	 * @param strategy
	 * @return
	 */
	private CFJType typeOf(FieldInvoke fieldInvoke, IEvaluationStrategy strategy) {
		if (fieldInvoke == null)
			return null;
		if (node2type.containsKey(fieldInvoke))
			return node2type.get(fieldInvoke);
		
		// (F.2): Invoke target muss well-typed sein
		CFJType typeOfInvokeTarget = typeOf(fieldInvoke.getInvokeTarget(), strategy);
		if (typeOfInvokeTarget == null) {
			errorMessages.add(0, "Invoke target is not well-typed.");
			return null;
		}
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		
		List<IASTNode> fieldsOfInvokeTarget = filter(fields(typeOfInvokeTarget.getASTNode()), colorManager.getColors(fieldInvoke), strategy);
		// (T.2) -> (F.1i): Feld muss existieren
		if ((fieldsOfInvokeTarget == null) || (fieldsOfInvokeTarget.isEmpty())) {
			errorMessages.add(0, "Field is not present in invoke target in some variants.");
			return null;
		}
		
		// (L.2): Typ des Feldes muss existieren (wird eigentlich bereits in der Klasse überprüft, aber da wir
		//        die Reihenfolge der Checks nicht genau wissen, prüfen wir das hier auch nochmal)
		for (IASTNode node : fieldsOfInvokeTarget) {
			VarDeclaration varDecl = (VarDeclaration) node;
			if (fieldInvoke.getIdentifier().getValue().equals(varDecl.getIdentifier().getValue())) {
				CFJTypeDeclarationWrapper typeDeclaration = findTypeDeclaration(getIdentifier(varDecl.getType()));
				
				if (typeDeclaration == null) {
					errorMessages.add(0, "Type >" + getIdentifier(varDecl.getType()) + "< does not exist.");
					return null;
				}
				if (strategy.implies(fm, colorManager.getColors(varDecl), colorManager.getColors(typeDeclaration))) {
					CFJType type = getType(getIdentifier(varDecl.getType()));
					node2type.put(fieldInvoke, type);
					return type;
				}
				
				errorMessages.add(0, "Type >" + getIdentifier(varDecl.getType()) + "< does not exist in some variants.");
				return null;
			}
		}
		
		// (T.2) -> (F.1ii): Feld muss existieren
		errorMessages.add(0, "Field is not present in invoke target in some variants.");
		return null;
	}
	
	/**
	 * T-INVK: Aufruf einer Methode (instance.method() oder this.method())
	 * @param methodInvoke
	 * @param strategy
	 * @return
	 */
	private CFJType typeOf(MethodInvoke methodInvoke, IEvaluationStrategy strategy) {
		if (methodInvoke == null)
			return null;
		if (node2type.containsKey(methodInvoke))
			return node2type.get(methodInvoke);
		
		// (I.3): Invoke target muss well-typed sein
		CFJType typeOfInvokeTarget = typeOf(methodInvoke.getInvokeTarget(), strategy);
		if (typeOfInvokeTarget == null) {
			errorMessages.add(0, "Invoke target is not well-typed.");
			return null;
		}
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		Set<IFeature> features = colorManager.getColors(methodInvoke);
		
		List<MethodSignature> mtypes = 
			mtypes(methodInvoke.getIdentifier().getValue(), typeOfInvokeTarget.getASTNode(), colorManager.getColors(methodInvoke), strategy);
		
		// (T.3i) -> (I.1): Methode muss existieren
		if ((mtypes == null) || mtypes.isEmpty()) {
			errorMessages.add(0, "Method is not present.");
			return null;
		}
		
		List<Set<IFeature>> featuresOfMethods = new LinkedList<Set<IFeature>>();
		for (MethodSignature mtype : mtypes) {
			featuresOfMethods.add(getFeaturesOfParent(mtype.returnType));
		}
		
		if (strategy.mayBeMissing(fm, features, featuresOfMethods)) {
			errorMessages.add(0, "Method is not present in some variants.");
			return null;
		}
		
		// (I.4): Expressions müssen well-typed sein
		ArrayList<CFJType> typesOfExpressions = typesOf(methodInvoke.getExpressionList(), strategy);
		if ((methodInvoke.getExpressionList() != null) && (typesOfExpressions == null)) {
			errorMessages.add(0, "At least one of the expressions are not well-typed.");
			return null;
		}
		
		// Für jede Methode, die aufgerufen werden könnte, müssen Checks durchgeführt werden
		for (MethodSignature mtype : mtypes) {
			ArrayList<CFJType> typesOfParameters = typesOf(mtype.formalParameters);

			// (I.5): richtige Anzahl von Aufrufparametern
			if (!haveSameSize(typesOfExpressions, typesOfParameters)) {
				errorMessages.add(0, "Wrong number of parameters.");
				return null;
			}

			// (I.6): Expressions müssen Subtypen der Typen der formalen Parameter sein
			if (!areSubtypes(typesOfExpressions, typesOfParameters)) {
				errorMessages.add(0, "Expressions are not subtypes of method-parameters.");
				return null;
			}

			if (methodInvoke.getExpressionList() != null) {
				for (int i = 0; i < methodInvoke.getExpressionList().getExpression().size(); ++i) {
					Set<IFeature> featuresOfMethod = getFeaturesOfParent(mtype.returnType);
					if (featuresOfMethod == null)
						featuresOfMethod = new HashSet<IFeature>();
					
					// Im Kontext der Farben von Methodenaufruf und Methode müssen die Expressions und die Parameter
					// in genau den gleichen Varianten präsent sein.
					Set<IFeature> source = colorManager.getColors(methodInvoke.getExpressionList().getExpression().get(i));
					source = addAll(addAll(source, features), featuresOfMethod);
					Set<IFeature> target = (mtype.formalParameters == null) ?  new HashSet<IFeature>()
							: colorManager.getColors(mtype.formalParameters.getFormalParameter().get(i));
					target = addAll(addAll(target, features), featuresOfMethod);

					// (T.3ii) -> (I.2): Annotationen müssen passen
					if (!strategy.equal(fm, source, target)) {
						errorMessages.add(0, "Annotations of expressions and method-parameters of method in class >" + 
											 findSurroundingTypeDeclaration(mtype.returnType).getIdentifier().getValue() + "< don't match.");
						return null;
					}
				}
			}
		}

		CFJType type = getType(getIdentifier(mtypes.get(0).returnType));
		node2type.put(methodInvoke, type);
		return type;
	}
	
	/**
	 * T-NEW: Erzeugen einer Instanz einer Klasse (new C())
	 * @param allocationExpression
	 * @param strategy
	 * @return
	 */
	private CFJType typeOf(AllocationExpression allocationExpression, IEvaluationStrategy strategy) {
		if (allocationExpression == null)
			return null;
		if (node2type.containsKey(allocationExpression))
			return node2type.get(allocationExpression);
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		Set<IFeature> features = colorManager.getColors(allocationExpression);
		
		CFJTypeDeclarationWrapper typeDeclaration = findTypeDeclaration(allocationExpression.getIdentifier().getValue());
		
		// (T.4i) -> (N.1): Typ muss existieren
		if (typeDeclaration == null) {
			errorMessages.add(0, "Type >" + allocationExpression.getIdentifier().getValue() + "< does not exist.");
			return null;
		}
		if (!strategy.implies(fm, features, colorManager.getColors(typeDeclaration))) {
			errorMessages.add(0, "Type does not exist in some variants.");
			return null;
		}
		
		List<IASTNode> fieldsOfTypeDeclaration = fields(typeDeclaration);
		if (fieldsOfTypeDeclaration == null)
			// Dürfte eigentlich nicht passieren, denn gibt es keine Felder, wird eine leere Liste zurückgegeben.
			return null;
		
		// (N.3): Expressions müssen well-typed sein
		ArrayList<CFJType> typesOfExpressions = typesOf(allocationExpression.getExpressionList(), strategy);
		if ((allocationExpression.getExpressionList() != null) && (typesOfExpressions == null)) {
			errorMessages.add(0, "At least one of the expressions are not well-typed.");
			return null;
		}
		
		ArrayList<CFJType> typesOfFields = null;
		if (fieldsOfTypeDeclaration.size() > 0) {
			typesOfFields = new ArrayList<CFJType>(fieldsOfTypeDeclaration.size());

			for (int i = 0; i < fieldsOfTypeDeclaration.size(); ++i) {
				typesOfFields.add(getType(getIdentifier(((VarDeclaration) fieldsOfTypeDeclaration.get(i)).getType())));
			}
		}

		// (N.4): richtige Anzahl von Parametern
		if (!haveSameSize(typesOfExpressions, typesOfFields)) {
			errorMessages.add(0, "Wrong number of parameters.");
			return null;
		}
		
		// (N.5): Expressions müssen Subtypen der Typen der formalen Parameter sein
		if (!areSubtypes(typesOfExpressions, typesOfFields)) {
			errorMessages.add(0, "Expressions are not subtypes of parameters.");
			return null;
		}

		if (allocationExpression.getExpressionList() != null) {
			for (int i = 0; i < allocationExpression.getExpressionList().getExpression().size(); ++i) {
				Set<IFeature> source = colorManager.getColors(allocationExpression.getExpressionList().getExpression().get(i));
				source = addAll(source, features);
				Set<IFeature> target = colorManager.getColors(fieldsOfTypeDeclaration.get(i));
				target = addAll(target, features);

				// (T.4ii) -> (N.2): Annotationen müssen passen
				if (!strategy.equal(fm, source, target)) {
					errorMessages.add(0, "Annotations of expressions and parameters don't match.");
					return null;
				}
			}
		}
		
		CFJType type = getType(typeDeclaration);
		node2type.put(allocationExpression, type);
		return type;
	}
	
	/**
	 * T-UCAST, T-DCAST: Up- und Down-Casts ((C) t)
	 * @param castExpression
	 * @param strategy
	 * @return
	 */
	private CFJType typeOf(CastExpression castExpression, IEvaluationStrategy strategy) {
		if (castExpression == null)
			return null;
		if (node2type.containsKey(castExpression))
			return node2type.get(castExpression);
		
		// (C.2): Expression muss well-typed sein
		CFJType typeOfPrimExpression = typeOf(castExpression.getPrimaryExpression(), strategy);
		if (typeOfPrimExpression == null) {
			errorMessages.add(0, "Primary expression is not well-typed.");
			return null;
		}
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		
		CFJTypeDeclarationWrapper typeDeclaration = findTypeDeclaration(getIdentifier(castExpression.getType()));
		
		// (T.5) -> (C.1): Typ muss existieren
		if (typeDeclaration == null) {
			errorMessages.add(0, "Type does not exist.");
			return null;
		}
		if (!strategy.implies(fm, colorManager.getColors(castExpression), colorManager.getColors(typeDeclaration))) {
			errorMessages.add(0, "Type does not exist in some variants.");
			return null;
		}
		
		CFJType typeOfTypeDeclaration = getType(typeDeclaration);		
		if (typeOfPrimExpression.isSubtypeOf(typeOfTypeDeclaration) || (typeOfTypeDeclaration.isProperSubtypeOf(typeOfPrimExpression))) {
			node2type.put(castExpression, typeOfTypeDeclaration);
			return typeOfTypeDeclaration;
		}
		
		// (C.3): Cast muss legal sein
		errorMessages.add(0, "Illegal cast.");
		return null;
	}
	
	// Rekursiver Abstieg im AST -------------------------------------------------------------------------------------------

	private CFJType typeOf(InvokeTarget invokeTarget, IEvaluationStrategy strategy) {
		if (invokeTarget == null)
			return null;
		
		// AllocationExpression
		if (invokeTarget instanceof InvokeTarget1) {
			return typeOf(((InvokeTarget1) invokeTarget).getAllocationExpression(), strategy);
		}
		// NestedExpression
		if (invokeTarget instanceof InvokeTarget2) {
			return typeOf(((InvokeTarget2) invokeTarget).getNestedExpression().getExpression(), strategy);
		}
		// <IDENTIFIER>
		if (invokeTarget instanceof InvokeTarget3) {
			return typeOf(((InvokeTarget3) invokeTarget).getIdentifier(), findSurroundingMethodDeclaration(invokeTarget), strategy);
		}
		// "this"
		if (invokeTarget instanceof InvokeTarget4) {
			TypeDeclaration surroundingTypeDeclaration = findSurroundingTypeDeclaration(invokeTarget);
			if (surroundingTypeDeclaration == null) {
				errorMessages.add(0, "Did not find surrounding type for >this<.");
				return null;
			}
			return getType(surroundingTypeDeclaration);
		}
		
		// Tritt nur bei unvollständiger Fallunterscheidung auf
		return null;
	}

	public CFJType typeOf(Expression expression, IEvaluationStrategy strategy) {
		if (expression == null)
			return null;
		return typeOf(expression.getPrimaryExpression(), strategy);
	}

	private CFJType typeOf(PrimaryExpression primaryExpression, IEvaluationStrategy strategy) {
		if (primaryExpression == null)
			return null;
		
		// <INTEGER_LITERAL>
		if (primaryExpression instanceof PrimaryExpression1) {
			return getType("Object");
		}
		// MethodInvoke
		if (primaryExpression instanceof PrimaryExpression2) {
			return typeOf(((PrimaryExpression2) primaryExpression).getMethodInvoke(), strategy);
		}
		// FieldInvoke
		if (primaryExpression instanceof PrimaryExpression3) {
			return typeOf(((PrimaryExpression3) primaryExpression).getFieldInvoke(), strategy);
		}
		// <IDENTIFIER>
		if (primaryExpression instanceof PrimaryExpression4) {
			MethodDeclaration methodDeclaration = findSurroundingMethodDeclaration(primaryExpression);
			if (methodDeclaration != null)
				return typeOf(((PrimaryExpression4) primaryExpression).getIdentifier(), methodDeclaration, strategy);
			return typeOf(((PrimaryExpression4) primaryExpression).getIdentifier(), findSurroundingClassConstructor(primaryExpression), strategy);
		}
		// AllocationExpression
		if (primaryExpression instanceof PrimaryExpression5) {
			return typeOf(((PrimaryExpression5) primaryExpression).getAllocationExpression(), strategy);
		}
		// CastExpression
		if (primaryExpression instanceof PrimaryExpression6) {
			return typeOf(((PrimaryExpression6) primaryExpression).getCastExpression(), strategy);
		}
		// NestedExpression
		if (primaryExpression instanceof PrimaryExpression7) {
			return typeOf(((PrimaryExpression7) primaryExpression).getNestedExpression(), strategy);
		}
		// "this"
		if (primaryExpression instanceof PrimaryExpression8) {
			TypeDeclaration surroundingTypeDeclaration = findSurroundingTypeDeclaration(primaryExpression);
			if (surroundingTypeDeclaration == null) {
				errorMessages.add(0, "Did not find surrounding type for >this<.");
				return null;
			}
			return getType(surroundingTypeDeclaration);
		}
		
		// Tritt nur bei unvollständiger Fallunterscheidung auf
		return null;
	}

	private CFJType typeOf(NestedExpression nestedExpression, IEvaluationStrategy strategy) {
		if (nestedExpression == null)
			return null;
		return typeOf(nestedExpression.getExpression(), strategy);
	}
	
	// Weitere Helferlein --------------------------------------------------------------------------------------------------
	
	public ArrayList<CFJType> typesOf(FormalParameterList formalParameterList) {
		if (formalParameterList == null)
			return null;
		
		ArrayList<FormalParameter> formalParameters = formalParameterList.getFormalParameter();
		if (formalParameters == null)
			return null;
		
		ArrayList<CFJType> types = new ArrayList<CFJType>(formalParameters.size());
		for (int i = 0; i < formalParameters.size(); ++i) {
			types.add(getType(getIdentifier(formalParameters.get(i).getType())));
		}
		return types;
	}

	private ArrayList<CFJType> typesOf(ExpressionList expressionList, IEvaluationStrategy strategy) {
		if (expressionList == null)
			return null;
		
		ArrayList<Expression> expressions = expressionList.getExpression();
		if (expressions == null)
			return null;
		
		ArrayList<CFJType> types = new ArrayList<CFJType>(expressions.size());
		for (int i = 0; i < expressions.size(); ++i) {
			CFJType type = typeOf(expressions.get(i), strategy);
			if (type == null)
				return null;
			types.add(type);
		}
		return types;
	}
	
	private MethodDeclaration findSurroundingMethodDeclaration(IASTNode node) {
		while (node != null) {
			if (node instanceof MethodDeclaration)
				return (MethodDeclaration) node;
			node = node.getParent();
		}
		return null;
	}
	
	private ClassConstructor findSurroundingClassConstructor(IASTNode node) {
		while (node != null) {
			if (node instanceof ClassConstructor)
				return (ClassConstructor) node;
			node = node.getParent();
		}
		return null;
	}
	
	public TypeDeclaration findSurroundingTypeDeclaration(IASTNode node) {
		while (node != null) {
			if (node instanceof TypeDeclaration) {
				return (TypeDeclaration) node;
			}
			node = node.getParent();
		}
		return null;
	}

	public CFJTypeDeclarationWrapper findTypeDeclaration(ExtendedType extendedType) {
		if (extendedType == null)
			return null;
		if (extendedType instanceof ExtendedType1)
			return findTypeDeclaration(((ExtendedType1) extendedType).getIdentifier().getValue());
		
		return findTypeDeclaration("Object");
	}

	public CFJTypeDeclarationWrapper findTypeDeclaration(String identifier) {
		if (identifier == null)
			return null;
		if (typeID2typeDeclaration.containsKey(identifier))
			return typeID2typeDeclaration.get(identifier);
		
		FindTypeDeclarationVisitor astVisitor = new FindTypeDeclarationVisitor(identifier);
		ast.accept(astVisitor);
		
		if (astVisitor.getTypeDeclaration() != null) {
			CFJTypeDeclarationWrapper result = new CFJTypeDeclarationWrapper(astVisitor.getTypeDeclaration());
			typeID2typeDeclaration.put(identifier, result);
			return result;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<IASTNode> cast(List list) {
		if (list == null)
			return null;
		
		List<IASTNode> result = new LinkedList<IASTNode>();
		for (Object o : list) {
			if (o == null)
				continue;
			if (!(o instanceof IASTNode))
				throw new ClassCastException("Could not cast from " + o.getClass().getSimpleName() + " to IASTNode.");
			result.add((IASTNode) o);
		}
		
		return result;
	}
	
	public static Set<IFeature> addAll(Set<IFeature> to, Set<IFeature> from) {
		if (to == null)
			return from;
		if (from == null)
			return to;
		
		// Wir machen diesen Workaround, weil >to< oft ein unmodifiable set ist.
		Set<IFeature> result = new HashSet<IFeature>();
		
		for (IFeature f : to) {
			result.add(f);
		}
		for (IFeature f : from) {
			result.add(f);
		}
		
		return result;
	}
	
	protected Set<IFeature> getFeaturesOfParent(IASTNode node) {
		if ((node == null) || (node.getParent() == null))
			return null;
		
		SourceFileColorManager colorManager = file.getColorManager();
		return colorManager.getColors(node.getParent());
	}
}
