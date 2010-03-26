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

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors of an exception in throws clause (between method declaration
 * and inherited method ). as necessary, throws according to the strategy an
 * error that overriding relationship is broken 
 *  
 * @author adreilin
 * 
 */
public class InheritedMethodExceptionCheck extends AbstractJDTTypingCheck {

	private final String excName;
	private final List<String> exceptionKeys;

	public InheritedMethodExceptionCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			String excName, List<String> excepKeys) {
		
		super(file,typingProvider,source);

		this.exceptionKeys = excepKeys;
		this.excName = excName;
		
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		
		//checks "OR" condition for all found keys	
		for (String tmpKey : exceptionKeys) {			
			
			//checks for each overridden method the implies condition
			if (strategy.implies(file.getFeatureModel(),file.getColorManager()
					.getColors(source), typingProvider.getBindingColors()
					.getColors(tmpKey))) 
				return true;
			
			
		}
		
		return false;
	}

	public String getErrorMessage() {
		return "Exception "+ excName +"  is not compatible with throws clause of overridden method in some variants";
	}
	

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodexceptionimplementation";
	}


}
