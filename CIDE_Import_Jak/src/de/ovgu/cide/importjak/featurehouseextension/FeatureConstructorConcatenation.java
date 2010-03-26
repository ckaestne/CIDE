/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

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
