package de.ovgu.cide.typing.bali.checks;

import org.eclipse.core.resources.IFile;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingCheck;

public class ProductionReferenceCheck implements ITypingCheck {

	private final IASTNode source;
	private final IASTNode target;
	private final ColoredSourceFile file;

	public ProductionReferenceCheck(ColoredSourceFile file, IASTNode source,
			IASTNode target) {
		this.file = file;
		this.source = source;
		this.target = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(),file.getColorManager().getColors(source), file.getColorManager().getColors(target));
	}

	public String getErrorMessage() {
		return "Reference to production which is not present in some variants";
	}

	public ColoredSourceFile getFile() {
		return file;
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.bali.productionreference";
	}

	public Severity getSeverity() {
		return Severity.ERROR;
	}

	public IASTNode getSource() {
		return source;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ProductionReferenceCheck))
			return super.equals(obj);
		ProductionReferenceCheck other = (ProductionReferenceCheck) obj;
		return (file.equals(other.file) & target.equals(other.target) & source
				.equals(other.source));
	}

	@Override
	public int hashCode() {
		return source.hashCode() + target.hashCode() + file.hashCode();
	}

	@Override
	public String toString() {
		return "[" + file + " - " + source.getId() + "]";

	}
}
