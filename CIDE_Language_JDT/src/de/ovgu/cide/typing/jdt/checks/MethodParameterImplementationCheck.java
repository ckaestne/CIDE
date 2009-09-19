package de.ovgu.cide.typing.jdt.checks;

import java.util.List;

import org.eclipse.jdt.core.dom.IMethodBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.jdt.checks.util.MethodPathItem;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors of parameters between method declaration /
 * implementation and related or rather inherited (abstract) method declarations
 * in interfaces and super classes. as necessary, throws according to the strategy an
 * error that method is not implemented in some variants. 
 * 
 * @author adreilin
 * 
 */
public class MethodParameterImplementationCheck extends AbstractJDTTypingCheck {

	private final int paramIndex;
	private final List<MethodPathItem> inherMethods;
	private final String name;

	public MethodParameterImplementationCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IMethodBinding methodBinding, int paramIndex, List<MethodPathItem> inherMethods) {
		super(file, typingProvider, source);
		this.paramIndex = paramIndex;
		this.inherMethods = inherMethods;
		this.name = methodBinding.getName();
	}

	public boolean evaluate(IEvaluationStrategy strategy) {

		// checks "AND" condition for all found methods
		for (MethodPathItem tmpItem : inherMethods) {
			
			if (!tmpItem.isDeclaringClassAbstract())
				return true;

			if (strategy.equal(file.getFeatureModel(), typingProvider
					.getBindingColors().getColors(tmpItem.getInheritedParamKeys().get(paramIndex)),
									file.getColorManager().getColors(source))) 
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
		return "Declaring method " + name + "does not implement inherited abstract methods in some variants. "
				+ "Check param list.";
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.methodparameterimplementation";
	}

}
