package de.ovgu.cide.typing.jdt.checks;

import java.util.Collections;
import java.util.Set;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;

import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors between an import statement and the type it imports
 * 
 * @author ckaestne
 * 
 */
public class ImportTargetCheck extends AbstractJDTTypingCheck {

	private final IBinding targetBinding;

	public ImportTargetCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source, IBinding binding) {
		super(file, typingProvider, source);
		this.targetBinding = binding;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		Set<IFeature> importColors = file.getColorManager().getColors(source);

		Set<IFeature> targetColors = Collections.EMPTY_SET;
		if (targetBinding instanceof ITypeBinding) {
			targetColors = typingProvider.getBindingColors().getColors(
					(ITypeBinding) targetBinding);
		}
		if (targetBinding instanceof IMethodBinding) {
			targetColors = typingProvider.getBindingColors().getColors(
					(IMethodBinding) targetBinding);
		}
		if (targetBinding instanceof IVariableBinding) {
			targetColors = typingProvider.getBindingColors().getColors(
					(IVariableBinding) targetBinding);
		}
		return strategy.implies(file.getFeatureModel(), importColors,
				targetColors);
	}

	public String getErrorMessage() {
		return "Import of type which is not present in some variants: "
				+ targetBinding.getName();
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.importtarget";
	}

}
