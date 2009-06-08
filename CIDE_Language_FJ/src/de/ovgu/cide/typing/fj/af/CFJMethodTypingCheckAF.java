package de.ovgu.cide.typing.fj.af;

import tmp.generated_fj.MethodDeclaration;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.fj.CFJMethodTypingCheck;

/**
 * In dieser Klasse wurden Implementierungen begonnen, die eine Methode unter Berücksichtigung von alternativen Klassen, Methoden, formalen Parametern,
 * Rückgabetypen usw. überprüfen. Da wir schlussendlich nur alternative Terme erlauben, wurden diese Implementierungen voerst hinfällig. Trotzdem
 * sollen sie nicht verloren gehen, so dass wir sie hier nur auskommentieren. 
 * 
 * @author Malte Rosenthal
 */
public class CFJMethodTypingCheckAF extends CFJMethodTypingCheck {
	
	public CFJMethodTypingCheckAF(ColoredSourceFile file, CFJTypingManagerAF typingManager, MethodDeclaration methodDeclaration) {
		super(file, typingManager, methodDeclaration);
	}
	
//	private AlternativeFeatureManager altFeatureManager;
//	private CFJTypingManagerAF typingManager;
//	private Alternative parentAlternative;
//	private TypeDeclaration typeDeclaration;
	
//	public CFJMethodTypingCheckAF(ColoredSourceFile file, CFJTypingManagerAF typingManager, MethodDeclaration methodDeclaration, 
//								  Alternative parentAlternative, TypeDeclaration typeDeclaration) {
//		super(file, typingManager, methodDeclaration);
//		this.altFeatureManager = file.getAltFeatureManager();
//		this.typingManager = typingManager;
//		this.parentAlternative = parentAlternative;
//		this.typeDeclaration = typeDeclaration;
//	}

//	@Override
//	public boolean evaluate(IEvaluationStrategy strategy) {
//		IFeatureModel fm = file.getFeatureModel();
//		SourceFileColorManager colorManager = file.getColorManager();
//		
//		List<Alternative> altMethodDeclarations = null;
//		try {
//			altMethodDeclarations = altFeatureManager.getAlternatives(methodDeclaration, parentAlternative);
//		} catch (CoreException e) {
//			e.printStackTrace();
//			return false;
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//		for (Alternative altMethDecl : altMethodDeclarations) {
//			MethodDeclaration altMethDeclNode = altMethDecl.getNode(file, methodDeclaration);
//			
//			// CHECK 3: alternative Methoden müssen den gleichen Namen haben
//			if (!altMethDeclNode.getIdentifier().getValue().equals(methodDeclaration.getIdentifier().getValue())) {
//				return createError("Name of method in alternative >" + altMethDecl.altID + "< differs from name of active method >" + 
//								   methodDeclaration.getIdentifier().getValue() + "<.", methodDeclaration.getIdentifier());
//			}
//		
//			FormalParameterList formalParameterList = altMethDeclNode.getFormalParameterList();
//			ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
//
//			if (formalParameters != null) {
//				HashSet<String> set = new HashSet<String>(formalParameters.size());
//
//				for (FormalParameter param : formalParameters) {
//					// (M.6): keine 2 formalen Parameter mit gleichem Namen
//					if (set.contains(param.getIdentifier().getValue()))
//						return createError("Duplicate method parameter >" + param.getIdentifier().getValue() + "<.", param);
//					set.add(param.getIdentifier().getValue());
//
//					List<Alternative> altParams = null;
//					try {
//						altParams = altFeatureManager.getAlternatives(param, altMethDecl);
//					} catch (CoreException e) {
//						e.printStackTrace();
//						return false;
//					} catch (ParseException e) {
//						e.printStackTrace();
//						return false;
//					}
//					
//					for (Alternative altParam : altParams) {
//						FormalParameter altParamNode = altParam.getNode(file, param);
//						
//						// CHECK 4: alternative formale Parameter müssen den gleichen Namen haben
//						if (!altParamNode.getIdentifier().getValue().equals(param.getIdentifier().getValue())) {
//							return createError("Alternative >" + altMethDecl.altID + "< of method >" + methodDeclaration.getIdentifier().getValue() + "<: "
//									+ "Name of formal parameter in alternative >" + altParam.altID + "< differs from name of active parameter >" 
//									+ param.getIdentifier().getValue() + "<.", param.getIdentifier());
//						}
//						
//						// (M.3): Typ eines jeden formalen Parameters muss existieren
//						CFJTypeDeclarationWrapper typeDecl = typingManager.findTypeDeclaration(CFJTypingManagerAF.getIdentifier(altParamNode.getType()));
//						if (typeDecl == null)
//							return createError("Alternative >" + altMethDecl.altID + "< of method >" + altMethDeclNode.getIdentifier().getValue() + 
//									"<: Type of alternative >" + altParam.altID + "< of parameter >" + 
//									param.getIdentifier().getValue() + "< does not exist.", param);
//						
//						List<Alternative> altTypeDecls = null;
//						try {
//							altTypeDecls = altFeatureManager.getAlternatives(typeDecl, typingManager.rootAlternative);
//						} catch (CoreException e) {
//							e.printStackTrace();
//							return false;
//						} catch (ParseException e) {
//							e.printStackTrace();
//							return false;
//						}
//						
//						List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
//						for (Alternative altTypeDecl : altTypeDecls) {
//							featureSets.add(altTypeDecl.getFeatures());
//						}
//
//						if (strategy.mayBeMissing(fm, altParam.getFeatures(), featureSets))
//							return createError("Alternative >" + altMethDecl.altID + "< of method >" + altMethDeclNode.getIdentifier().getValue() + 
//									"<: Type of alternative >" + altParam.altID + "< of parameter >" + 
//									param.getIdentifier().getValue() + "< does not exist in some variants.", param);
//					}
//				}
//			}	// Ende der Behandlung formaler Parameter
//			
//			Type returnType = altMethDeclNode.getType();
//			List<Alternative> altReturnTypes = null;
//			try {
//				altReturnTypes = altFeatureManager.getAlternatives(returnType, altMethDecl);
//			} catch (CoreException e) {
//				e.printStackTrace();
//				return false;
//			} catch (ParseException e) {
//				e.printStackTrace();
//				return false;
//			}
//			
//			for (Alternative altReturnType : altReturnTypes) {
//				Type altReturnTypeNode = altReturnType.getNode(file, returnType);
//				
//				// (M.1): Rückgabetyp muss existieren
//				CFJTypeDeclarationWrapper typeDecl = typingManager.findTypeDeclaration(CFJTypingManagerAF.getIdentifier(altReturnTypeNode));
//				if (typeDecl == null)
//					return createError("Alternative >" + altMethDecl.altID + "< of method >" + altMethDeclNode.getIdentifier().getValue() + 
//							"<: Return-type >" + CFJTypingManagerAF.getIdentifier(altReturnTypeNode) + "< (alternative >" + altReturnType.altID + 
//							"<) does not exist.", returnType);
//				
//				List<Alternative> altTypeDecls = null;
//				try {
//					altTypeDecls = altFeatureManager.getAlternatives(typeDecl, typingManager.rootAlternative);
//				} catch (CoreException e) {
//					e.printStackTrace();
//					return false;
//				} catch (ParseException e) {
//					e.printStackTrace();
//					return false;
//				}
//				
//				List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
//				for (Alternative altTypeDecl : altTypeDecls) {
//					featureSets.add(altTypeDecl.getFeatures());
//				}
//
//				if (strategy.mayBeMissing(fm, altReturnType.getFeatures(), featureSets))
//					return createError("Alternative >" + altMethDecl.altID + "< of method >" + altMethDeclNode.getIdentifier().getValue() + 
//							"<: Return-type >" + typeDecl.getIdentifier().getValue() + "< (alternative >" + altReturnType.altID + 
//							"<) does not exist in some variants.", returnType);
//				
//				CFJType returnCFJType = typingManager.getType(typeDecl);
//
//				// (M.4): Expression muss well-typed sein
//				CFJType typeOfExpression = 
//					typingManager.typeOf(altMethDeclNode.getExpression(), typeDeclaration, altMethDecl, altMethDeclNode, altReturnType.getFeatures(), 
//										 strategy);
//				if (typeOfExpression == null) {
//					return createError("Alternative >" + altMethDecl.altID + "< of method >" + altMethDeclNode.getIdentifier().getValue() + 
//									   "<: At least one alternative of the expression is not well-typed.", methodDeclaration.getExpression());
//				}
//
//				// (M.5): Typ der Expression muss Subtyp des Rückgabetyps sein
//				if (!typeOfExpression.isSubtypeOf(returnCFJType)) {
//					return createError("Alternative >" + altMethDecl.altID + "< of method >" + altMethDeclNode.getIdentifier().getValue() + 
//							"<: The type of at least one alternative of the expression is no subtype of return-type >" + 
//							typeDecl.getIdentifier().getValue() + "<.", methodDeclaration.getExpression());
//				}
//			}	// for each alternative return type
//
//			// (M.2): Gültiges Overriding
//			if (!typingManager.override(methodDeclaration, strategy)) {
//				return createError("No valid overriding.", methodDeclaration);
//			}
//		}	// for each alternative method declaration
//				
//		return true;
//	}
//	
//	protected boolean createError(String message, IASTNode source) {
//		super.createError(message, source);
//		errorMessage = "Alternative >" + parentAlternative.altID + "< of class " + typeDeclaration.getIdentifier().getValue() + "<: " + errorMessage;
//		return false;
//	}
//
//	@Override
//	public String getProblemType() {
//		return "de.ovgu.cide.typing.fj.methodtyping";
//	}
}
