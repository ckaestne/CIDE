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
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors between method declaration and inherited methods.
 * as necessary, throws an error according to the strategy that
 * overriding relationship is changed in some variants 
 * 
 * @author adreilin
 * 
 */
public class InheritedMethodCheck extends AbstractJDTTypingCheck {

	private final String name;
	private final MethodPathItem inherMethod;
	private final List<IASTNode> paramList;
	

	public InheritedMethodCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			List<IASTNode> paramList, String name, MethodPathItem inherMethod) {
		
		super(file,typingProvider,source);

		this.inherMethod = inherMethod;
		this.name = name;
		this.paramList = paramList;
		
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		
		//checks colors for method name
		if (!strategy.equal(file.getFeatureModel(),   typingProvider.getBindingColors()
				.getColors(inherMethod.getKey()), file.getColorManager()
				.getColors(source))) 
			return false;
	
		//PARAM CHECK
		for (int j = 0; j < paramList.size(); j++) {
			
			if (!strategy.equal(file.getFeatureModel(), typingProvider.getBindingColors()
					.getColors(inherMethod.getInheritedParamKeys().get(j)), file.getColorManager()
					.getColors(paramList.get(j))))
				return false;
			
		}		
		
		return true;	
	

	}

	public String getErrorMessage() {
		return "Overriding Relationship of " + name +  " is changed in some variants.";
	}
	

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodnameimplementation";
	}

	@Override
	public Severity getSeverity() {
		return Severity.WARNING;
	}

}

//BACKUP - PARAMETER;
//FOR CHECKGENERATOR

//BACKUP:
//List parameterList = node.parameters();
//
//		// build for each parameter a list of paramters keys which are
//		// overridden
//		for (int j = 0; j < parameterList.size(); j++) {
//
//			List<String> curParamList = new ArrayList<String>();
//			for (MethodPathItem curItem : inhMethods) {
//
//				if (curItem.isAbstract())
//					continue;
//
//				curParamList.add(curItem.getInheritedParamKeys().get(j));
//
//			}
//
//			checks.add(new InheritedMethodParameterCheck(file,
//					jdtTypingProvider,
//					bridge((SingleVariableDeclaration) parameterList.get(j)),
//					methodBinding.getName(), methodBinding, curParamList));
//
//		}


//package de.ovgu.cide.typing.jdt.checks;
//
//import java.util.List;
//
//import org.eclipse.jdt.core.dom.IMethodBinding;
//
//import cide.gast.IASTNode;
//import de.ovgu.cide.features.source.ColoredSourceFile;
//import de.ovgu.cide.typing.jdt.JDTTypingProvider;
//import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
//import de.ovgu.cide.typing.model.IEvaluationStrategy;
//
///**
// * checks colors of a parameters between method declaration
// * and inherited method. as necessary, throws according to the strategy an
// * warning that overriding relationship is changed 
// *  
// * @author adreilin
// * 
// */
//public class InheritedMethodParameterCheck extends AbstractJDTTypingCheck {
//
//	private final String name;
//	private final List<String> paramKeys;
//	private final IMethodBinding declMethodBinding;
//
//	public InheritedMethodParameterCheck(ColoredSourceFile file,
//			JDTTypingProvider typingProvider, IASTNode source,
//			String paramName, IMethodBinding declMethodBinding, List<String> paramKeys) {
//		
//		super(file,typingProvider,source);
//
//		this.paramKeys = paramKeys;
//		this.declMethodBinding = declMethodBinding;
//		name = paramName;
//		
//	}
//
//	public boolean evaluate(IEvaluationStrategy strategy) {
//					
//		//checks "AND" condition for all found keys
//		for (String tmpKey : paramKeys) {		
//		
//			//checks for each overridden method the implies condition
//			if (!strategy.equal(file.getFeatureModel(), typingProvider.getBindingColors()
//					.getColors(tmpKey), file.getColorManager()
//					.getColors(source))) 
//				return false;
//		}
//		
//		
//		return true;
//	}
//
//	public String getErrorMessage() {
//		return "Overriding Relationship of " +name+  " is changed in some variants due to different paramter list";
//	}
//	
//
//	public String getProblemType() {
//		return "de.ovgu.cide.typing.jdt.methodparameterimplementation";
//	}
//
//	@Override
//	public Severity getSeverity() {
//		return Severity.WARNING;
//	}
//
//}

