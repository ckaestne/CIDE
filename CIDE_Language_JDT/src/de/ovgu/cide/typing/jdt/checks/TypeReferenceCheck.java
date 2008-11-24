package de.ovgu.cide.typing.jdt.checks;

import org.eclipse.jdt.core.dom.ITypeBinding;

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
public class TypeReferenceCheck extends AbstractJDTTypingCheck {

	private final ITypeBinding target;

	public TypeReferenceCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			ITypeBinding target) {
		super(file, typingProvider, source);
		this.target = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), typingProvider.getBindingColors()
				.getColors(target));
	}

	public String getErrorMessage() {
		return "Referencing type which is not present in some variants: "
				+ target.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.typereference";
	}

}
