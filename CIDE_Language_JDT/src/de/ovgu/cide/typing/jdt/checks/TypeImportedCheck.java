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
public class TypeImportedCheck extends AbstractJDTTypingCheck {

	private final IASTNode targetImportDeclaration;
	private final String name;

	public TypeImportedCheck(ColoredSourceFile file,
			JDTTypingProvider typingProvider, IASTNode source,
			IASTNode targetImportDeclaration, String name) {
		super(file, typingProvider, source);
		this.targetImportDeclaration = targetImportDeclaration;
		this.name = name;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), file.getColorManager().getColors(
				targetImportDeclaration));
	}

	public String getErrorMessage() {
		return "Type used for which the import declaration is not present in some variants: "
				+ name;
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.jdt.importtypereference";
	}

}
