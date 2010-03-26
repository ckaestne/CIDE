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

package de.ovgu.cide.typing.jdt.checks;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.IMethodBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors of exception in throws clause between method declaration /
 * implementation and related or rather inherited (abstract) method declarations
 * in interfaces and super classes. as necessary, throws according to the strategy an
 * error that method is not implemented in some variants. 
 *  
 * @author adreilin
 * 
 */
public class MethodImplementationExceptionCheck extends AbstractJDTTypingCheck {

	private final String excKey;
	private final List<MethodPathItem> inherMethods;
	private final String name;
	private final IMethodBinding methodBinding;

	public MethodImplementationExceptionCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IMethodBinding methodBinding, String excKey,
			List<MethodPathItem> inherMethods) {
		super(file, typingProvider, source);
		this.excKey = excKey;
		this.inherMethods = inherMethods;
		this.name = methodBinding.getName();
		this.methodBinding = methodBinding;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {

		// checks "AND" condition for all found methods
		for (MethodPathItem tmpItem : inherMethods) {

			if (!tmpItem.isDeclaringClassAbstract())
				return true;

			boolean excImplies = false;
			for (String tmpKey : tmpItem.getInheritedExceptionKeys(
					methodBinding).get(excKey)) {
				// checks for each overridden method the implies condition
				if (strategy.implies(file.getFeatureModel(), file
						.getColorManager().getColors(source), typingProvider
						.getBindingColors().getColors(tmpKey))) {
					excImplies = true;
					break;
				}
			}

			if (excImplies)
				continue;

			// we have found one overriden method for which "target -> source"
			// is false

			// checks if current item is abstract
			if (tmpItem.isAbstract())
				// check failed
				return false;
			else
				// another method implementation exists
				return true;

		}

		return true;

	}

	public String getErrorMessage() {
		return "Implementing method "
				+ name
				+ " does not implement inherited abstract methods in some variants. "
				+ "Check throws clause.";
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodimplementationexception";
	}

}
