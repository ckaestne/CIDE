//package de.ovgu.cide.language.gcide;
//
//import java.util.HashMap;
//import java.util.Set;
//
//import tmp.generated_gcide.ReferenceManager;
//
//import cide.features.IASTColorProvider;
//import cide.greferences.ASimpleSubsetValidationRule;
//import cide.greferences.IReference;
//import cide.greferences.IReferenceType;
//import cide.greferences.IValidationErrorCallback;
//import cide.greferences.IValidationRule;
//
//public class ValidateNonTerminalsRule extends ASimpleSubsetValidationRule {
//
//	protected ValidateNonTerminalsRule() {
//		super(ReferenceManager.production, true);
//	}
//
//	public String getErrorMessage() {
//		return "Referenced production must have a subset of colors from reference";
//	}
//
//	public String getName() {
//		return "gCIDE Production Reference Rule";
//	}
//}
