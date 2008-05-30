//package de.ovgu.cide.language.bali.validation;
//
//import cide.greferences.IResolver;
//import cide.greferences.IValidationRule;
//import cide.languages.ILanguageValidator;
//
//public class BaliValidator implements ILanguageValidator {
//
//	private IResolver resolver = null;
//	private IValidationRule referenceProductionRule;
//
//	public IResolver getResolver() {
//		if (resolver == null)
//			resolver = new BaliResolver();
//		return resolver;
//	}
//
//	public IValidationRule[] getValidationRules() {
//		if (referenceProductionRule == null)
//			referenceProductionRule = new BaliRefProductionRule();
//		return new IValidationRule[] { referenceProductionRule };
//	}
//
//}
