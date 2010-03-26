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

import java.util.List;

import org.eclipse.jdt.core.dom.IMethodBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors between method declaration / implementation and related or rather
 * inherited (abstract) method declarations in interfaces and super classes.
 * as necessary, throws according to the strategy an error that method is not 
 * implemented in some variants.
 *  
 * @author adreilin
 * 
 */
public class MethodImplementationNameCheck extends
		AbstractJDTTypingCheck {

	private final List<MethodPathItem> inherMethods;
	private final String name;

	public MethodImplementationNameCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source, 
			IMethodBinding sourceBinding, List<MethodPathItem> inherMethods) {
		super(file, typingProvider, source);
		this.inherMethods = inherMethods;
		this.name = sourceBinding.getName();
	}

	public boolean evaluate(IEvaluationStrategy strategy) {

		// checks "AND" condition for all found methods
		for (MethodPathItem tmpItem : inherMethods) {
			
			if (!tmpItem.isDeclaringClassAbstract())
				return true;
			
			// check relationship between each declaring and implemented method
			if (strategy.implies(file.getFeatureModel(), typingProvider
					.getBindingColors().getColors(tmpItem.getKey()), file
					.getColorManager().getColors(source)))
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
		return "Declaring method " +name+ " does not implement inherited abstract methods in some variants";
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodimplementationname";
	}

}
