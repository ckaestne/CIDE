package de.ovgu.cide.typing.jdt.checks;

import java.util.List;

import org.eclipse.jdt.core.dom.IMethodBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors of a parameters between method declaration
 * and inherited method. as necessary, throws according to the strategy an
 * warning that overriding relationship is changed 
 *  
 * @author adreilin
 * 
 */
public class InheritedMethodParameterCheck extends AbstractJDTTypingCheck {

	private final String name;
	private final List<String> paramKeys;
	private final IMethodBinding declMethodBinding;

	public InheritedMethodParameterCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			String paramName, IMethodBinding declMethodBinding, List<String> paramKeys) {
		
		super(file,typingProvider,source);

		this.paramKeys = paramKeys;
		this.declMethodBinding = declMethodBinding;
		name = paramName;
		
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		
		//checks precondition if param color is equal to method(name) 
//		if (!strategy.implies(file.getFeatureModel(),  typingProvider.getBindingColors()
//				.getColors(declMethodBinding),  file.getColorManager()
//				.getColors(source))) {
//		
				
		//checks "AND" condition for all found keys
		for (String tmpKey : paramKeys) {		
		
			//checks for each overridden method the implies condition
			if (!strategy.equal(file.getFeatureModel(), typingProvider.getBindingColors()
					.getColors(tmpKey), file.getColorManager()
					.getColors(source))) 
				return false;
		}
		
		
		return true;
	}

	public String getErrorMessage() {
		return "Overriding Relationship of " +name+  " is changed in some variants due to different paramter list";
	}
	

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodparameterimplementation";
	}

	@Override
	public Severity getSeverity() {
		return Severity.WARNING;
	}

}
