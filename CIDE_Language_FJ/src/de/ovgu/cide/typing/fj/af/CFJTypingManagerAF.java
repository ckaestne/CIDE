package de.ovgu.cide.typing.fj.af;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;

import tmp.generated_fj.AllocationExpression;
import tmp.generated_fj.CastExpression;
import tmp.generated_fj.Expression;
import tmp.generated_fj.ExpressionList;
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
import tmp.generated_fj.TypeDeclaration;
import tmp.generated_fj.VarDeclaration;
import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gparser.ParseException;
import de.ovgu.cide.alternativefeatures.Alternative;
import de.ovgu.cide.alternativefeatures.AlternativeFeatureManager;
import de.ovgu.cide.alternativefeatures.RootAlternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.typing.fj.CFJTypeDeclarationWrapper;
import de.ovgu.cide.typing.fj.CFJTypingManager;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * Manager für das CFJ-Typsystem unter Berücksichtigung alternativer Features
 * 
 * Grundlegende Voraussetzung: jedes CFJ-Programm ist auch ein gültiges FJ-Programm, wenn man die Annotationen weglässt
 * 
 * @author Malte Rosenthal
 */
public class CFJTypingManagerAF extends CFJTypingManager {
	
	public RootAlternative rootAlternative;
	private AlternativeFeatureManager altFeatureManager;
	
	public CFJTypingManagerAF(ColoredSourceFile file) throws CoreException, ParseException {
		super(file);
		this.altFeatureManager = file.getAltFeatureManager();
		rootAlternative = new RootAlternative();
	}
	
	// Auxiliary definitions -----------------------------------------------------------------------------------------------
	
	// Haben wir benötigt, als versucht wurde, auch alternative Klassen, Methoden usw. im Typsystem zu berücksichtigen.
//	public boolean overshadows(Alternative altVarDeclaration, VarDeclaration modelNode, TypeDeclaration typeDeclaration, Set<IFeature> context, 
//							   IEvaluationStrategy strategy) throws CoreException, ParseException {
//		
//		if ((altVarDeclaration == null) || (strategy == null))
//			return false;
//		
//		Set<IFeature> features = addAll(context, altVarDeclaration.getFeatures());
//		IFeatureModel fm = file.getFeatureModel();
//		String identifier = altVarDeclaration.getNode(this.file, modelNode).getIdentifier().getValue();
//
//		CFJTypeDeclarationWrapper supertypeDeclaration = findTypeDeclaration(typeDeclaration.getExtendedType());
//		if ((supertypeDeclaration != null) && !supertypeDeclaration.isObjectType()) {
//			List<Alternative> altSupertypeDeclarations = altFeatureManager.getAlternatives(supertypeDeclaration.getTypeDeclaration(), rootAlternative);
//
//			for (Alternative altSupertypeDeclaration : altSupertypeDeclarations) {
//				TypeDeclaration altSupertypeDeclNode = altSupertypeDeclaration.getNode(this.file, supertypeDeclaration.getTypeDeclaration());
//				
//				ArrayList<VarDeclaration> varDeclarations = altSupertypeDeclNode.getVarDeclaration();
//
//				for (VarDeclaration varDeclaration : varDeclarations) {
//					List<Alternative> altVarDeclarations = altFeatureManager.getAlternatives(varDeclaration, altSupertypeDeclaration);
//
//					for (Alternative alt : altVarDeclarations) {
//						if (alt.getNode(this.file, varDeclaration).getIdentifier().getValue().equals(identifier) 
//								&& strategy.exists(fm, addAll(features, alt.getFeatures()))) {
//							errorMessages.add(0, "Alternative >" + altVarDeclaration.altID + "< of variable >" + identifier + "< overshadows " +
//									"alternative >" + alt.altID + "< in alternative >" + altSupertypeDeclaration.altID + "< of class >" + 
//									altSupertypeDeclNode.getIdentifier().getValue() + "<.");
//							return true;
//						}
//					}
//				}
//				
//				Set<IFeature> f = addAll(context, altSupertypeDeclaration.getFeatures());
//				if (strategy.exists(fm, f) && overshadows(altVarDeclaration, modelNode, altSupertypeDeclNode, f, strategy))
//					return true;
//			}
//		}
//		
//		return false;
//	}
	
	private CFJType commonSupertype(List<CFJType> types) {
		if ((types == null) || types.isEmpty())
			return null;
		if (types.size() == 1)
			return types.get(0);
		
		CFJType result = types.get(0);
		List<CFJType> otherTypes = types.subList(1, types.size());
		boolean commonSupertypeFound = false;
		
		while (!result.identifier.equals("Object")) {
			commonSupertypeFound = true;
			for (CFJType type : otherTypes) {
				if (!type.isSubtypeOf(result)) {
					commonSupertypeFound = false;
					break;
				}
			}
			
			if (commonSupertypeFound)
				return result;
			result = result.getSuperType();
		}
		
		return result;
	}
		
	// Typing rules -------------------------------------------------------------------------------------------------------------
	
	/**
	 * T-VAR: lokale Variablen
	 */
	public CFJType typeOf(ASTStringNode identifier, Alternative parent, MethodDeclaration method, Set<IFeature> context, IEvaluationStrategy strategy) {
		if (identifier == null)
			return null;
		
		Alternative identifierAlternative = null;
		try {
			identifierAlternative = altFeatureManager.getTheAlternative(identifier, parent);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		FormalParameterList formalParameterList = method.getFormalParameterList();
		ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
		
		if (formalParameters != null) {
			// (T.1) -> (V.1): Variable muss existieren
			List<IASTNode> presentParameters = filter(cast(formalParameters), addAll(context, identifierAlternative.getFeatures()), strategy);
			for (IASTNode param : presentParameters) {
				if (((FormalParameter) param).getIdentifier().getValue().equals(identifier.getValue()))
					return getType(getIdentifier(((FormalParameter) param).getType()));
			}
		}
		
		errorMessages.add(0, "Variable >" + identifier + "< does not exist in some variants.");
		return null;
	}
	
	// Versuch, auch alternative Klassen und alternative formale Parameter zu berücksichtigen
//	/**
//	 * T-VAR: lokale Variablen
//	 * @param identifier
//	 * @param method
//	 * @return
//	 */
//	public CFJType typeOf(ASTStringNode identifier, Alternative methodAlternative, MethodDeclaration method, Set<IFeature> context, 
//						  IEvaluationStrategy strategy) {
//		if ((identifier == null) || (method == null))
//			return null;
//		
//		Alternative identifierAlternative = null;
//		try {
//			// Es gibt nur eine Alternative zum identifier
//			identifierAlternative = altFeatureManager.getAlternatives(identifier, methodAlternative).get(0);
//		} catch (CoreException e) {
//			e.printStackTrace();
//			return null;
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//		FormalParameterList formalParameterList = method.getFormalParameterList();
//		ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
//		
//		if (formalParameters != null) {
//			IFeatureModel fm = file.getFeatureModel();
//			Set<IFeature> features = addAll(context, identifierAlternative.getFeatures());
//			List<CFJType> result = new LinkedList<CFJType>();
//			
//			// (T.1) -> (V.1): Variable muss existieren
//			for (FormalParameter formalParameter : formalParameters) {
//				if (!formalParameter.getIdentifier().getValue().equals(identifier.getValue()))
//					continue;
//				
//				List<Alternative> altParams = null;
//				try {
//					altParams = altFeatureManager.getAlternatives(formalParameter, methodAlternative);
//				} catch (CoreException e) {
//					e.printStackTrace();
//					return null;
//				} catch (ParseException e) {
//					e.printStackTrace();
//					return null;
//				}
//				
//				for (Alternative altParam : altParams) {
//					if (strategy.exists(fm, addAll(features, altParam.getFeatures()))) {
//						result.add(getType(getIdentifier(altParam.getNode(file, formalParameter).getType())));
//					}
//				}
//			}
//			
//			if (!result.isEmpty())
//				return commonSupertype(result);
//		}
//		
//		errorMessages.add(0, "Variable >" + identifier + "< does not exist in some variants.");
//		return null;
//	}
	
	// Konstruktoren überprüfen wir hier nicht mehr
//	private CFJType typeOf(ASTStringNode identifier, ClassConstructor constructor, IEvaluationStrategy strategy) {
//		if ((identifier == null) || (constructor == null))
//			return null;
//		
//		FormalParameterList formalParameterList = constructor.getFormalParameterList();
//		ArrayList<FormalParameter> formalParameters = (formalParameterList == null) ? null : formalParameterList.getFormalParameter();
//		
//		if (formalParameters != null) {
//			SourceFileColorManager colorManager = file.getColorManager();
//			
//			// (T.1) -> (V.1): Variable muss existieren
//			List<IASTNode> presentParameters = filter(cast(formalParameters), colorManager.getColors(identifier), strategy);
//			for (IASTNode node : presentParameters) {
//				if (((FormalParameter) node).getIdentifier().getValue().equals(identifier.getValue()))
//					return getType(getIdentifier(((FormalParameter) node).getType()));
//			}
//		}
//		
//		errorMessages.add(0, "Variable >" + identifier + "< does not exist in some variants.");
//		return null;
//	}
	
	/**
	 * T-FIELD: Zugriff auf ein Feld (instance.field oder this.field)
	 * @param fieldInvoke
	 * @param strategy
	 * @return
	 */
	public CFJType typeOf(FieldInvoke fieldInvoke, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if (fieldInvoke == null)
			return null;
		
		List<Alternative> fieldInvokeAlternatives = null;
		try {
			fieldInvokeAlternatives = altFeatureManager.getAlternatives(fieldInvoke, parent);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		List<CFJType> result = new LinkedList<CFJType>();
		
		for (Alternative fieldInvokeAlternative : fieldInvokeAlternatives) {
			FieldInvoke fieldInvokeAltNode = fieldInvokeAlternative.getNode(file, fieldInvoke);
			Set<IFeature> contextWithAlternative = addAll(context, fieldInvokeAlternative.getFeatures());
			
			// (F.2): Invoke target muss well-typed sein
			CFJType typeOfInvokeTarget = typeOf(fieldInvokeAltNode.getInvokeTarget(), fieldInvokeAlternative, methodDeclaration, contextWithAlternative, 
												strategy);
			if (typeOfInvokeTarget == null) {
				errorMessages.add(0, "Alternative >" + fieldInvokeAlternative.altID + "< of field invoke term: Invoke target is not well-typed.");
				return null;
			}
			
			List<IASTNode> fieldsOfInvokeTarget = filter(fields(typeOfInvokeTarget.getASTNode()), contextWithAlternative, strategy);
			// (T.2) -> (F.1i): Feld muss existieren
			if ((fieldsOfInvokeTarget == null) || (fieldsOfInvokeTarget.isEmpty())) {
				errorMessages.add(0, "Alternative >" + fieldInvokeAlternative.altID + "< of field invoke term: Field is not present in invoke target " +
									 "in some variants.");
				return null;
			}
			
			boolean fieldFound = false;
			for (IASTNode node : fieldsOfInvokeTarget) {
				VarDeclaration varDecl = (VarDeclaration) node;
				if (fieldInvokeAltNode.getIdentifier().getValue().equals(varDecl.getIdentifier().getValue())) {
					CFJTypeDeclarationWrapper typeDeclaration = findTypeDeclaration(getIdentifier(varDecl.getType()));
					
					if (typeDeclaration == null) {
						errorMessages.add(0, "Type >" + getIdentifier(varDecl.getType()) + "< does not exist.");
						return null;
					}
					
					result.add(getType(getIdentifier(varDecl.getType())));
					fieldFound = true;
					break;
				}
			}
			
			if (!fieldFound) {
				errorMessages.add(0, "Alternative >" + fieldInvokeAlternative.altID + "< of field invoke term: Field is not present in invoke target " +
				 					 "in some variants.");
				return null;
			}
		}

		return commonSupertype(result);
	}
	
	// Versuch, auch alternative Klassen und alternative Felder zu berücksichtigen
//	/**
//	 * T-FIELD: Zugriff auf ein Feld (instance.field oder this.field)
//	 * @param fieldInvoke
//	 * @param strategy
//	 * @return
//	 */
//	public CFJType typeOf(FieldInvoke fieldInvoke, TypeDeclaration typeDeclaration, Alternative methodAlternative, 
//						  MethodDeclaration methodDeclaration, Set<IFeature> context, IEvaluationStrategy strategy) {
//		if (fieldInvoke == null)
//			return null;
////		if (node2type.containsKey(fieldInvoke))
////			;//return node2type.get(fieldInvoke);
//		
//		IFeatureModel fm = file.getFeatureModel();
//		List<CFJType> result = new LinkedList<CFJType>();
//		
//		List<Alternative> fieldInvokeAlternatives = null;
//		try {
//			fieldInvokeAlternatives = altFeatureManager.getAlternatives(fieldInvoke, methodAlternative);
//		} catch (CoreException e) {
//			e.printStackTrace();
//			return null;
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//		for (Alternative fieldInvokeAlternative : fieldInvokeAlternatives) {
//			FieldInvoke fieldInvokeAlternativeNode = fieldInvokeAlternative.getNode(file, fieldInvoke);
//			Set<IFeature> featuresWithInvokeAlternative = addAll(context, fieldInvokeAlternative.getFeatures());
//			
//			// (F.2): Invoke target muss well-typed sein
//			CFJType typeOfInvokeTarget = typeOf(fieldInvokeAlternativeNode.getInvokeTarget(), typeDeclaration, methodAlternative, 
//												methodDeclaration, featuresWithInvokeAlternative, strategy);
//			if (typeOfInvokeTarget == null) {
//				errorMessages.add(0, "Alternative >" + fieldInvokeAlternative.altID + "< of field invoke term: invoke target is not well-typed.");
//				return null;
//			}
//			if (typeOfInvokeTarget.identifier.equals("Object")) {
//				errorMessages.add(0, "Alternative >" + fieldInvokeAlternative.altID + "< of field invoke term: invoke target is >Object< that does not " +
//									 "contain any fields.");
//				return null;
//			}
//			
//			TypeDeclaration invokeTargetTypeDecl = typeOfInvokeTarget.getASTNode().getTypeDeclaration();
//			List<Alternative> invokeTargetTypeDeclAlternatives = null;
//			try {
//				invokeTargetTypeDeclAlternatives = altFeatureManager.getAlternatives(invokeTargetTypeDecl, rootAlternative);
//			} catch (CoreException e) {
//				e.printStackTrace();
//				return null;
//			} catch (ParseException e) {
//				e.printStackTrace();
//				return null;
//			}
//			
//			List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
//			
//			for (Alternative invokeTargetTypeDeclAlternative : invokeTargetTypeDeclAlternatives) {
//				if (strategy.exists(fm, addAll(featuresWithInvokeAlternative, invokeTargetTypeDeclAlternative.getFeatures()))) {
//					ArrayList<VarDeclaration> varDeclarations = invokeTargetTypeDeclAlternative.getNode(file, invokeTargetTypeDecl).getVarDeclaration();
//					
//					// (T.2) -> (F.1i): Feld muss existieren
//					if ((varDeclarations == null) || (varDeclarations.isEmpty())) {
//						errorMessages.add(0, "Alternative >" + fieldInvokeAlternative.altID + "< of field invoke term: field does not exist in " + 
//											 "alternative >" + invokeTargetTypeDeclAlternative.altID + "< of class >" + 
//											 invokeTargetTypeDecl.getIdentifier().getValue() + "<.");
//						return null;
//					}
//					
//					for (VarDeclaration varDeclaration : varDeclarations) {
//						if (varDeclaration.getIdentifier().getValue().equals(fieldInvokeAlternativeNode.getIdentifier().getValue())) {
//							List<Alternative> varDeclarationAlternatives = null;
//							try {
//								varDeclarationAlternatives = altFeatureManager.getAlternatives(varDeclaration, invokeTargetTypeDeclAlternative);
//							} catch (CoreException e) {
//								e.printStackTrace();
//								return null;
//							} catch (ParseException e) {
//								e.printStackTrace();
//								return null;
//							}
//							
//							for (Alternative varDeclarationAlternative : varDeclarationAlternatives) {
//								if (strategy.exists(fm, addAll(featuresWithInvokeAlternative, varDeclarationAlternative.getFeatures()))) {
//									featureSets.add(varDeclarationAlternative.getFeatures());
//									result.add(getType(getIdentifier(varDeclarationAlternative.getNode(file, varDeclaration).getType())));
//								}
//							}
//						}
//					}
//				}
//			}	// for each alternative of the invoke target type declaration
//			
//			if (!strategy.mayBeMissing(fm, featuresWithInvokeAlternative, featureSets)) {
//				errorMessages.add(0, "Alternative >" + fieldInvokeAlternative.altID + "< of field invoke term: field does not exist in some variants " + 
//			 			 			 "of class >" + invokeTargetTypeDecl.getIdentifier().getValue() + "<.");
//				return null;
//			}
//			
//		}	// for each alternative of the invoke target term
//
//		return commonSupertype(result);
//	}
	
	/**
	 * T-INVK: Aufruf einer Methode (instance.method() oder this.method())
	 * @param methodInvoke
	 * @param strategy
	 * @return
	 */
	public CFJType typeOf(MethodInvoke methodInvoke, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if (methodInvoke == null)
			return null;
		
		List<Alternative> methodInvokeAlternatives = null;
		try {
			methodInvokeAlternatives = altFeatureManager.getAlternatives(methodInvoke, parent);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		List<CFJType> result = new LinkedList<CFJType>();
		
		for (Alternative methodInvokeAlternative : methodInvokeAlternatives) {
			MethodInvoke methodInvokeAltNode = methodInvokeAlternative.getNode(this.file, methodInvoke);
			Set<IFeature> contextWithAlt = addAll(context, methodInvokeAlternative.getFeatures());
			
			// (I.3): Invoke target muss well-typed sein
			CFJType typeOfInvokeTarget = 
				typeOf(methodInvokeAltNode.getInvokeTarget(), methodInvokeAlternative, methodDeclaration, contextWithAlt, strategy);
			if (typeOfInvokeTarget == null) {
				errorMessages.add(0, "Alternative >" + methodInvokeAlternative.altID + "< of method invoke term: Invoke target is not well-typed.");
				return null;
			}
			
			IFeatureModel fm = file.getFeatureModel();
			SourceFileColorManager colorManager = file.getColorManager();
			
			List<MethodSignature> mtypes = 
				mtypes(methodInvokeAltNode.getIdentifier().getValue(), typeOfInvokeTarget.getASTNode(), contextWithAlt, strategy);
			
			// (T.3i) -> (I.1): Methode muss existieren
			if ((mtypes == null) || mtypes.isEmpty()) {
				errorMessages.add(0, "Alternative >" + methodInvokeAlternative.altID + "< of method invoke term: Method is not present.");
				return null;
			}
			
			List<Set<IFeature>> featuresOfMethods = new LinkedList<Set<IFeature>>();
			for (MethodSignature mtype : mtypes) {
				featuresOfMethods.add(getFeaturesOfParent(mtype.returnType));
			}
			
			if (strategy.mayBeMissing(fm, contextWithAlt, featuresOfMethods)) {
				errorMessages.add(0, "Alternative >" + methodInvokeAlternative.altID + "< of method invoke term: Method is not present in some variants.");
				return null;
			}
			
			// (I.4): Expressions müssen well-typed sein
			ArrayList<CFJType> typesOfExpressions = typesOf(methodInvokeAltNode.getExpressionList(), methodInvokeAlternative, methodDeclaration, 
															contextWithAlt, strategy);
			if ((methodInvokeAltNode.getExpressionList() != null) && (typesOfExpressions == null)) {
				errorMessages.add(0, "Alternative >" + methodInvokeAlternative.altID + "< of method invoke term: At least one of the expressions are " +
									 "not well-typed.");
				return null;
			}
			
			// Für jede Methode, die aufgerufen werden könnte, müssen Checks durchgeführt werden
			for (MethodSignature mtype : mtypes) {
				ArrayList<CFJType> typesOfParameters = typesOf(mtype.formalParameters);

				// (I.5): richtige Anzahl von Aufrufparametern
				if (!haveSameSize(typesOfExpressions, typesOfParameters)) {
					errorMessages.add(0, "Alternative >" + methodInvokeAlternative.altID + "< of method invoke term: Wrong number of parameters.");
					return null;
				}

				// (I.6): Expressions müssen Subtypen der Typen der formalen Parameter sein
				if (!areSubtypes(typesOfExpressions, typesOfParameters)) {
					errorMessages.add(0, "Alternative >" + methodInvokeAlternative.altID + "< of method invoke term: Expressions are not subtypes " +
										 "of method-parameters.");
					return null;
				}

				if (methodInvokeAltNode.getExpressionList() != null) {
					for (int i = 0; i < methodInvokeAltNode.getExpressionList().getExpression().size(); ++i) {
						Set<IFeature> featuresOfMethod = getFeaturesOfParent(mtype.returnType);
						if (featuresOfMethod == null)
							featuresOfMethod = new HashSet<IFeature>();
						
						List<Alternative> expressionAlternatives = null;
						try {
							expressionAlternatives = 
								altFeatureManager.getAlternatives(methodInvokeAltNode.getExpressionList().getExpression().get(i), methodInvokeAlternative);
						} catch (CoreException e) {
							e.printStackTrace();
							return null;
						} catch (ParseException e) {
							e.printStackTrace();
							return null;
						}
						
						Set<IFeature> target = (mtype.formalParameters == null) ?  new HashSet<IFeature>()
								: colorManager.getColors(mtype.formalParameters.getFormalParameter().get(i));
						target = addAll(addAll(target, contextWithAlt), featuresOfMethod);
						
						for (Alternative expressionAlternative : expressionAlternatives) {
							// Im Kontext der Farben von Methodenaufruf und Methode müssen die Expressions und die Parameter
							// in genau den gleichen Varianten präsent sein.
							Set<IFeature> source = expressionAlternative.getFeatures();
							source = addAll(addAll(source, contextWithAlt), featuresOfMethod);

							// (T.3ii) -> (I.2): Annotationen müssen passen
							if (!strategy.equal(fm, source, target)) {
								errorMessages.add(0, "Alternative >" + methodInvokeAlternative.altID + "< of method invoke term, alternative >" +
													 expressionAlternative.altID + "< of parameter #" + (i+1) + ": Annotations of expression " +
													 "and method-parameter of method in class >" + 
													 findSurroundingTypeDeclaration(mtype.returnType).getIdentifier().getValue() + "< don't match.");
								return null;
							}
						}
					}
				}
			}

			result.add(getType(getIdentifier(mtypes.get(0).returnType)));
		}
		
		return commonSupertype(result);
	}
	
	/**
	 * T-NEW: Erzeugen einer Instanz einer Klasse (new C())
	 * @param allocationExpression
	 * @param strategy
	 * @return
	 */
	public CFJType typeOf(AllocationExpression allocationExpression, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if (allocationExpression == null)
			return null;
		
		List<Alternative> allocationExpressionAlternatives = null;
		try {
			allocationExpressionAlternatives = altFeatureManager.getAlternatives(allocationExpression, parent);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		IFeatureModel fm = file.getFeatureModel();
		SourceFileColorManager colorManager = file.getColorManager();
		
		List<CFJType> result = new LinkedList<CFJType>();
		
		for (Alternative allocationExpressionAlternative : allocationExpressionAlternatives) {
			AllocationExpression allocationExpressionAltNode = allocationExpressionAlternative.getNode(this.file, allocationExpression);
			Set<IFeature> contextWithAlt = addAll(context, allocationExpressionAlternative.getFeatures());
			
			CFJTypeDeclarationWrapper typeDeclaration = findTypeDeclaration(allocationExpressionAltNode.getIdentifier().getValue());

			// (T.4i) -> (N.1): Typ muss existieren
			if (typeDeclaration == null) {
				errorMessages.add(0, "Alternative >" + allocationExpressionAlternative.altID + "< of allocation expression: Type >" + 
									 allocationExpressionAltNode.getIdentifier().getValue() + "< does not exist.");
				return null;
			}
			if (!strategy.implies(fm, contextWithAlt, colorManager.getColors(typeDeclaration))) {
				errorMessages.add(0, "Type does not exist in some variants.");
				return null;
			}

			List<IASTNode> fieldsOfTypeDeclaration = fields(typeDeclaration);
			if (fieldsOfTypeDeclaration == null)
				// Dürfte eigentlich nicht passieren, denn gibt es keine Felder, wird eine leere Liste zurückgegeben.
				return null;

			// (N.3): Expressions müssen well-typed sein
			ArrayList<CFJType> typesOfExpressions = typesOf(allocationExpressionAltNode.getExpressionList(), allocationExpressionAlternative, 
															methodDeclaration, contextWithAlt, strategy);
			if ((allocationExpressionAltNode.getExpressionList() != null) && (typesOfExpressions == null)) {
				errorMessages.add(0, "Alternative >" + allocationExpressionAlternative.altID + "< of allocation expression: At least one of the " +
									 "expressions are not well-typed.");
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
				errorMessages.add(0, "Alternative >" + allocationExpressionAlternative.altID + "< of allocation expression: Wrong number of parameters.");
				return null;
			}
			
			// (N.5): Expressions müssen Subtypen der Typen der formalen Parameter sein
			if (!areSubtypes(typesOfExpressions, typesOfFields)) {
				errorMessages.add(0, "Alternative >" + allocationExpressionAlternative.altID + "< of allocation expression: Expressions are not " +
									 "subtypes of parameters.");
				return null;
			}

			if (allocationExpressionAltNode.getExpressionList() != null) {
				for (int i = 0; i < allocationExpressionAltNode.getExpressionList().getExpression().size(); ++i) {
					List<Alternative> expressionAlternatives = null;
					try {
						expressionAlternatives = 
							altFeatureManager.getAlternatives(allocationExpressionAltNode.getExpressionList().getExpression().get(i), 
															  allocationExpressionAlternative);
					} catch (CoreException e) {
						e.printStackTrace();
						return null;
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
					
					Set<IFeature> target = colorManager.getColors(fieldsOfTypeDeclaration.get(i));
					target = addAll(target, contextWithAlt);
					
					for (Alternative expressionAlternative : expressionAlternatives) {
						Set<IFeature> source = expressionAlternative.getFeatures();
						source = addAll(source, contextWithAlt);

						// (T.4ii) -> (N.2): Annotationen müssen passen
						if (!strategy.equal(fm, source, target)) {
							errorMessages.add(0, "Alternative >" + allocationExpressionAlternative.altID + "< of allocation expression, alternative >" +
												 expressionAlternative.altID + "< of parameter #" + (i+1) + ": Annotations of expression and parameter" +
												 " don't match.");
							return null;
						}
					}
				}
			}
			
			result.add(getType(typeDeclaration));
		}

		return commonSupertype(result);
	}
	
	/**
	 * T-UCAST, T-DCAST: Up- und Down-Casts ((C) t)
	 * @param castExpression
	 * @param strategy
	 * @return
	 */
	public CFJType typeOf(CastExpression castExpression, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if (castExpression == null)
			return null;
		
		List<Alternative> castExpressionAlternatives = null;
		try {
			castExpressionAlternatives = altFeatureManager.getAlternatives(castExpression, parent);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		List<CFJType> result = new LinkedList<CFJType>();
		
		for (Alternative castExpressionAlternative : castExpressionAlternatives) {
			CastExpression castExpressionAltNode = castExpressionAlternative.getNode(this.file, castExpression);
			Set<IFeature> contextWithAlt = addAll(context, castExpressionAlternative.getFeatures());
			
			// (C.2): Expression muss well-typed sein
			CFJType typeOfPrimExpression = 
				typeOf(castExpressionAltNode.getPrimaryExpression(), castExpressionAlternative, methodDeclaration, contextWithAlt, strategy);
			if (typeOfPrimExpression == null) {
				errorMessages.add(0, "Alternative >" + castExpressionAlternative.altID + "< of cast expression: Primary expression is not well-typed.");
				return null;
			}
			
			IFeatureModel fm = file.getFeatureModel();
			SourceFileColorManager colorManager = file.getColorManager();
			
			CFJTypeDeclarationWrapper typeDeclaration = findTypeDeclaration(getIdentifier(castExpressionAltNode.getType()));
			
			// (T.5) -> (C.1): Typ muss existieren
			if (typeDeclaration == null) {
				errorMessages.add(0, "Alternative >" + castExpressionAlternative.altID + "< of cast expression: Type does not exist.");
				return null;
			}
			if (!strategy.implies(fm, contextWithAlt, colorManager.getColors(typeDeclaration))) {
				errorMessages.add(0, "Alternative >" + castExpressionAlternative.altID + "< of cast expression: Type does not exist in some variants.");
				return null;
			}
			
			CFJType typeOfTypeDeclaration = getType(typeDeclaration);		
			if (!(typeOfPrimExpression.isSubtypeOf(typeOfTypeDeclaration) || (typeOfTypeDeclaration.isProperSubtypeOf(typeOfPrimExpression)))) {
				// (C.3): Cast muss legal sein
				errorMessages.add(0, "Alternative >" + castExpressionAlternative.altID + "< of cast expression: Illegal cast.");
				return null;
			}
			
			result.add(typeOfTypeDeclaration);
		}
		
		return commonSupertype(result);
	}
	
	// Rekursiver Abstieg im AST -------------------------------------------------------------------------------------------

	public CFJType typeOf(InvokeTarget invokeTarget, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if (invokeTarget == null)
			return null;
		
		// AllocationExpression
		if (invokeTarget instanceof InvokeTarget1) {
			return typeOf(((InvokeTarget1) invokeTarget).getAllocationExpression(), parent, methodDeclaration, context, strategy);
		}
		// NestedExpression
		if (invokeTarget instanceof InvokeTarget2) {
			return typeOf(((InvokeTarget2) invokeTarget).getNestedExpression().getExpression(), parent, methodDeclaration, context, strategy);
		}
		// <IDENTIFIER>
		if (invokeTarget instanceof InvokeTarget3) {
			return typeOf(((InvokeTarget3) invokeTarget).getIdentifier(), parent, methodDeclaration, context, strategy);
		}
		// "this"
		if (invokeTarget instanceof InvokeTarget4) {
			// Wir suchen die Klasse von der methodDeclaration aus und nicht vom invokeTarget, da das invokeTarget möglicherweise als
			// einzelnes Codefragment geparsed wurde (kann in Alternativen vorliegen) und somit die Informationen über Vaterknoten usw.
			// möglicherweise nicht vorhanden sind.
			TypeDeclaration surroundingTypeDeclaration = findSurroundingTypeDeclaration(methodDeclaration);
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
		MethodDeclaration methodDeclaration = (MethodDeclaration) expression.getParent();
		
		try {
			Alternative methodAlternative = altFeatureManager.getTheAlternative(methodDeclaration, rootAlternative);
			return typeOf(expression, methodAlternative, methodDeclaration, methodAlternative.getFeatures(), strategy);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public CFJType typeOf(Expression expression, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if ((expression == null) || (parent == null) || (methodDeclaration == null) || (strategy == null))
			return null;
		if (context == null)
			context = new HashSet<IFeature>();
		return typeOf(expression.getPrimaryExpression(), parent, methodDeclaration, context, strategy);
	}

	public CFJType typeOf(PrimaryExpression primaryExpression, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if (primaryExpression == null)
			return null;
		
		// <INTEGER_LITERAL>
		if (primaryExpression instanceof PrimaryExpression1) {
			return getType("Object");
		}
		// MethodInvoke
		if (primaryExpression instanceof PrimaryExpression2) {
			return typeOf(((PrimaryExpression2) primaryExpression).getMethodInvoke(), parent, methodDeclaration, context, strategy);
		}
		// FieldInvoke
		if (primaryExpression instanceof PrimaryExpression3) {
			return typeOf(((PrimaryExpression3) primaryExpression).getFieldInvoke(), parent, methodDeclaration, context, strategy);
		}
		// <IDENTIFIER>
		if (primaryExpression instanceof PrimaryExpression4) {
			return typeOf(((PrimaryExpression4) primaryExpression).getIdentifier(), parent, methodDeclaration, context, strategy);
		}
		// AllocationExpression
		if (primaryExpression instanceof PrimaryExpression5) {
			return typeOf(((PrimaryExpression5) primaryExpression).getAllocationExpression(), parent, methodDeclaration, context, strategy);
		}
		// CastExpression
		if (primaryExpression instanceof PrimaryExpression6) {
			return typeOf(((PrimaryExpression6) primaryExpression).getCastExpression(), parent, methodDeclaration, context, strategy);
		}
		// NestedExpression
		if (primaryExpression instanceof PrimaryExpression7) {
			return typeOf(((PrimaryExpression7) primaryExpression).getNestedExpression(), parent, methodDeclaration, context, strategy);
		}
		// "this"
		if (primaryExpression instanceof PrimaryExpression8) {
			// Wir suchen die Klasse von der methodDeclaration aus und nicht vom primaryExpression, da die primaryExpression möglicherweise als
			// einzelnes Codefragment geparsed wurde (kann in Alternativen vorliegen) und somit die Informationen über Vaterknoten usw.
			// möglicherweise nicht vorhanden sind.
			TypeDeclaration surroundingTypeDeclaration = findSurroundingTypeDeclaration(methodDeclaration);
			if (surroundingTypeDeclaration == null) {
				errorMessages.add(0, "Did not find surrounding type for >this<.");
				return null;
			}
			return getType(surroundingTypeDeclaration);
		}
		
		// Tritt nur bei unvollständiger Fallunterscheidung auf
		return null;
	}

	public CFJType typeOf(NestedExpression nestedExpression, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
						  IEvaluationStrategy strategy) {
		if (nestedExpression == null)
			return null;
		return typeOf(nestedExpression.getExpression(), parent, methodDeclaration, context, strategy);
	}
	
	// Weitere Helferlein --------------------------------------------------------------------------------------------------

	public ArrayList<CFJType> typesOf(ExpressionList expressionList, Alternative parent, MethodDeclaration methodDeclaration, Set<IFeature> context,
									  IEvaluationStrategy strategy) {
		if (expressionList == null)
			return null;
		
		ArrayList<Expression> expressions = expressionList.getExpression();
		if (expressions == null)
			return null;
		
		ArrayList<CFJType> types = new ArrayList<CFJType>(expressions.size());
		for (int i = 0; i < expressions.size(); ++i) {
			CFJType type = typeOf(expressions.get(i), parent, methodDeclaration, context, strategy);
			if (type == null)
				return null;
			types.add(type);
		}
		return types;
	}
}
