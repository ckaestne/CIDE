package de.ovgu.cide.typing.jdt.checks;

import org.eclipse.jdt.core.dom.IMethodBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors between a field and references to it
 * 
 * @author ckaestne
 * 
 */
public class MethodInvocationCheck extends AbstractJDTTypingCheck {

	private final IMethodBinding targetMethod;

	public MethodInvocationCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IMethodBinding target) {
		super(file, typingProvider, source);
		this.targetMethod = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), typingProvider.getBindingColors()
				.getColors(targetMethod));
	}

	public String getErrorMessage() {
		return "Invoking method which is not present in some variants: "
				+ targetMethod.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodinvocation";
	}

}
