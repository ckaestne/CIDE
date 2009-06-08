package de.ovgu.cide.typing.fj.af;

import tmp.generated_fj.TypeDeclaration;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.fj.CFJClassTypingCheck;

/**
 * In dieser Klasse wurden Implementierungen begonnen, die eine Klasse unter Berücksichtigung von alternativen Klassen, Methoden, formalen Parametern,
 * Rückgabetypen usw. überprüfen. Da wir schlussendlich nur alternative Terme erlauben, wurden diese Implementierungen voerst hinfällig. Trotzdem
 * sollen sie nicht verloren gehen, so dass wir sie hier nur auskommentieren.
 * 
 * @author Malte Rosenthal
 */
public class CFJClassTypingCheckAF extends CFJClassTypingCheck {
	
//	private AlternativeFeatureManager altFeatureManager;
//	private CFJTypingManagerAF typingManager;
	
	public CFJClassTypingCheckAF(ColoredSourceFile file, CFJTypingManagerAF typingManager, TypeDeclaration typeDeclaration) {
		super(file, typingManager, typeDeclaration);
//		this.altFeatureManager = file.getAltFeatureManager();
//		this.typingManager = typingManager;
	}

//	@Override
//	public boolean evaluate(IEvaluationStrategy strategy) {
//		
//		// (L.3): keine 2 Klassen mit gleichem Namen
//		if (!checkL3()) return false;
//		
//		// (L.5): Klasse darf nicht >Object< heißen
//		if (!checkL5()) return false;
//		
//		// (L.1i): Supertyp muss existieren
//		CFJTypeDeclarationWrapper supertypeDeclaration = typingManager.findTypeDeclaration(typeDeclaration.getExtendedType());
//		if (supertypeDeclaration == null)
//			return createError("Supertype of class >" + typeDeclaration.getIdentifier().getValue() + "< does not exist.", 
//							   typeDeclaration.getExtendedType());
//		
//		// (L.6): Vererbungshierchie darf keine Zyklen enthalten
//		if (!inheritanceHierarchyOK(supertypeDeclaration))
//			return false;
//		
//		List<Alternative> altTypeDeclarations = null;
//		try {
//			altTypeDeclarations = altFeatureManager.getAlternatives(typeDeclaration, typingManager.rootAlternative);
//		} catch (CoreException e) {
//			e.printStackTrace();
//			return false;
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//		IFeatureModel fm = file.getFeatureModel();
//		
//		for (Alternative altTypeDecl : altTypeDeclarations) {
//			TypeDeclaration altTypeDeclNode = altTypeDecl.getNode(this.file, typeDeclaration);
//			
//			// CHECK 1: alternative Klassen müssen den gleichen Namen und gleichen Supertypen haben
//			if (!altTypeDeclNode.getIdentifier().getValue().equals(typeDeclaration.getIdentifier().getValue())) {
//				return createError("Name of class in alternative >" + altTypeDecl.altID + "< differs from name of active class >" + 
//								   typeDeclaration.getIdentifier().getValue() + "<.", typeDeclaration.getIdentifier());
//			}
//			if (!CFJTypingManagerAF.getIdentifier(altTypeDeclNode.getExtendedType()).equals(
//					CFJTypingManagerAF.getIdentifier(typeDeclaration.getExtendedType()))) {
//				return createError("Superclass of class in alternative >" + altTypeDecl.altID + "< differs from superclass of active class >" + 
//								   typeDeclaration.getIdentifier().getValue() + "<.", typeDeclaration.getExtendedType());
//			}
//			
//			// (L.4): keine 2 Methoden mit gleichem Namen
//			if (!checkL4(altTypeDeclNode))
//				return prependError("Duplicate method declarations in alternative >" + altTypeDecl.altID + "< of class >" + 
//								   typeDeclaration.getIdentifier().getValue() + "<.");
//			
//			// (L.1ii): Supertyp muss im Kontext dieser Alternative existieren
//			if (!supertypeDeclaration.isObjectType()) {
//				List<Alternative> altSupertypeDeclarations = null;
//				try {
//					altSupertypeDeclarations = altFeatureManager.getAlternatives(supertypeDeclaration.getTypeDeclaration(), typingManager.rootAlternative);
//				} catch (CoreException e) {
//					e.printStackTrace();
//					return false;
//				} catch (ParseException e) {
//					e.printStackTrace();
//					return false;
//				}
//				
//				List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
//				for (Alternative altSupertypeDeclaration : altSupertypeDeclarations) {
//					featureSets.add(altSupertypeDeclaration.getFeatures());
//				}
//				
//				if (strategy.mayBeMissing(fm, altTypeDecl.getFeatures(), featureSets)) {
//					return createError("Supertype of alternative >" + altTypeDecl.altID + "< of class >" + typeDeclaration.getIdentifier().getValue() + 
//									   "< does not exist in some variants.", typeDeclaration.getExtendedType());
//				}
//			}
//			
//			ArrayList<VarDeclaration> ownFields = altTypeDeclNode.getVarDeclaration();
//			
//			if (ownFields != null) {
//				for (int i = 0; i < ownFields.size(); ++i) {
//					for (int j = i + 1; j < ownFields.size(); ++j) {
//						// (L.7i): keine 2 Felder in Klasse mit gleichem Namen
//						if (ownFields.get(i).getIdentifier().getValue().equals(ownFields.get(j).getIdentifier().getValue()))
//							return createError("Duplicate field >" + ownFields.get(i).getIdentifier().getValue() + "< in alternative >" + 
//											   altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + "<.", ownFields.get(i));
//					}
//
//					try {
//						List<Alternative> altVarDeclarations = altFeatureManager.getAlternatives(ownFields.get(i), altTypeDecl);
//						for (Alternative altVarDeclaration : altVarDeclarations) {
//							// CHECK 2: alternative Felder müssen gleichen Namen haben
//							if (!altVarDeclaration.getNode(this.file, ownFields.get(i)).getIdentifier().getValue().
//									equals(ownFields.get(i).getIdentifier().getValue()))
//								return createError("Alternative >" + altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + 
//										"<: Name of alternative >" + altVarDeclaration.altID + "< of field >" + 
//										ownFields.get(i).getIdentifier().getValue() + "< differs from the others.", ownFields.get(i));
//							
//							CFJTypeDeclarationWrapper typeDeclOfField = typingManager.findTypeDeclaration(CFJTypingManagerAF.getIdentifier(
//										altVarDeclaration.getNode(this.file, ownFields.get(i)).getType()));
//							
//							// (L.2): Typ des Feldes muss existieren
//							if (typeDeclOfField == null)
//								return createError("Alternative >" + altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + 
//										"<: Type of alternative >" + altVarDeclaration.altID + "< of field >" + 
//										ownFields.get(i).getIdentifier().getValue() + "< does not exist.", ownFields.get(i));
//							
//							List<Alternative> altTypeDeclsOfField = altFeatureManager.getAlternatives(typeDeclOfField, typingManager.rootAlternative);
//							List<Set<IFeature>> featureSets = new LinkedList<Set<IFeature>>();
//							for (Alternative altTypeDeclOfField : altTypeDeclsOfField) {
//								featureSets.add(altTypeDeclOfField.getFeatures());
//							}
//
//							if (strategy.mayBeMissing(fm, altVarDeclaration.getFeatures(), featureSets))
//								return createError("Alternative >" + altTypeDecl.altID + "< of class >" + altTypeDeclNode.getIdentifier().getValue() + 
//										"<: Type of alternative >" + altVarDeclaration.altID + "< of field >" + 
//										ownFields.get(i).getIdentifier().getValue() + "< does not exist in some variants.", ownFields.get(i));
//
//							// (L.7ii): keine 2 Felder in Klasse und allen Superklassen mit gleichem Namen
//							if (typingManager.overshadows(altVarDeclaration, ownFields.get(i), altTypeDeclNode, new HashSet<IFeature>(), strategy))
//								return createError("Field-overshadowing in alternative >" + altTypeDecl.altID + "< of class >" + 
//										typeDeclaration.getIdentifier().getValue() + "<.", ownFields.get(i));
//						}
//					} catch (CoreException e) {
//						e.printStackTrace();
//						return false;
//					} catch (ParseException e) {
//						e.printStackTrace();
//						return false;
//					}
//				}	// for each own field
//			}
//			
//			ArrayList<MethodDeclaration> methodDeclarations = altTypeDeclNode.getMethodDeclaration();
//			if ((methodDeclarations != null) && !methodDeclarations.isEmpty()) {
//				for (MethodDeclaration methodDeclaration : methodDeclarations) {
//					if (!((new CFJMethodTypingCheckAF(file, typingManager, methodDeclaration, altTypeDecl, altTypeDeclNode)).evaluate(strategy)))
//						return false;
//				}
//			}
//			
//		}	// for each alternative type declaration
//		
//		return true;
//	}
}
