package de.ovgu.cide.importjak.featurehouseextension;

import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

public class FeatureConstructorConcatenation {
	public final static String COMPOSITION_RULE_NAME = "ConstructorConcatenation";

	public static void compose(FSTTerminal terminalA, FSTTerminal terminalB,
			FSTTerminal terminalComp, FSTNonTerminal nonterminalParent) {
		String constructorA = terminalA.getBody();
		String constructorB = terminalB.getBody();

		if (FeatureJavaMethodOverriding.whitespaceEqual(constructorA, constructorB))
			return;

		String constructorBHeader = constructorB.substring(0, constructorB
				.indexOf("{") + 1);
		String constructorBBody = constructorB.substring(constructorB
				.indexOf("{") + 1, constructorB.lastIndexOf("}"));

		// AbstractFSTPrintVisitor
		// .startIfdef(terminalB.featureExpression)
		// + AbstractFSTPrintVisitor.endIfdef(terminalB.featureExpression);
		String constructorABody = constructorA.substring(constructorA
				.indexOf("{") + 1, constructorA.lastIndexOf("}"));
		String constructorAEnd = constructorA.substring(constructorA
				.lastIndexOf("}"), constructorA.length());

		terminalComp.setBody(constructorBHeader
				// + AbstractFSTPrintVisitor
				// .startIfdef(terminalB.featureExpression)
				+ constructorBBody
				// +
				// AbstractFSTPrintVisitor.endIfdef(terminalB.featureExpression)
				+ FeatureJavaPrintVisitor
						.startIfdef(FeatureExpressions.get(terminalA))
				+ constructorABody
				+ FeatureJavaPrintVisitor.endIfdef(FeatureExpressions.get(terminalA))
				+ constructorAEnd);
	}
}
