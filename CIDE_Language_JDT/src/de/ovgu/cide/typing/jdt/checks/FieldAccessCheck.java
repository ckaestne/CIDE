package de.ovgu.cide.typing.jdt.checks;

import org.eclipse.jdt.core.dom.IVariableBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingCheck;

public class FieldAccessCheck implements ITypingCheck {

	private final IASTNode source;
	private final IVariableBinding targetField;
	private final JDTTypingProvider typingProvider;
	private final ColoredSourceFile file;

	public FieldAccessCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IVariableBinding target) {
		this.file = file;
		this.source = source;
		this.targetField = target;
		this.typingProvider = typingProvider;
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

	public ColoredSourceFile getFile() {
		return file;
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.fieldaccess";
	}

	public Severity getSeverity() {
		return Severity.ERROR;
	}

	public IASTNode getSource() {
		return source;
	}

}
