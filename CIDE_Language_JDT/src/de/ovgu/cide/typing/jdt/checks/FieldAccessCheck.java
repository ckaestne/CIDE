package de.ovgu.cide.typing.jdt.checks;

import org.eclipse.jdt.core.dom.IVariableBinding;

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
public class FieldAccessCheck extends AbstractJDTTypingCheck {

	private final IVariableBinding targetField;

	public FieldAccessCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IVariableBinding target) {
		super(file, typingProvider, source);
		this.targetField = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), typingProvider.getBindingColors()
				.getColors(targetField));
	}

	public String getErrorMessage() {
		return "Access to field which is not present in some variants: "
				+ targetField.getName();
	}

	
	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.fieldaccess";
	}

	

}
