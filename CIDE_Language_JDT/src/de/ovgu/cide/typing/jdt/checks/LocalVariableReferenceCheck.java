package de.ovgu.cide.typing.jdt.checks;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.jdt.JDTTypingProvider;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * checks colors between a local type reference in a file and the import
 * declaration
 * 
 * @author ckaestne
 * 
 */
public class LocalVariableReferenceCheck extends AbstractJDTTypingCheck {

	private final IASTNode targetVariableDeclaration;
	private final String name;

	public LocalVariableReferenceCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IASTNode targetImportDeclaration, String variableName) {
		super(file, typingProvider, source);
		this.targetVariableDeclaration = targetImportDeclaration;
		this.name = variableName;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), file.getColorManager().getColors(
				targetVariableDeclaration));
	}

	public String getErrorMessage() {
		return "Variable used which is not present in some variants: "
				+ name;
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.localvariablereference";
	}

}
