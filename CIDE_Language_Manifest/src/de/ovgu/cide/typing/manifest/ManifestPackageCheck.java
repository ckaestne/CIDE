package de.ovgu.cide.typing.manifest;

import org.eclipse.core.resources.IFolder;

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.DirectoryColorManager;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingCheck;

public class ManifestPackageCheck implements ITypingCheck {
	private ColoredSourceFile file;
	private IASTNode source;
	private IFolder target;

	public ManifestPackageCheck(ColoredSourceFile file, IASTNode source,
			IFolder target) {
		this.file = file;
		this.source = source;
		this.target = target;
	}

	public boolean evaluate(IEvaluationStrategy strategy) {
		return strategy.implies(file.getFeatureModel(), file.getColorManager()
				.getColors(source), DirectoryColorManager
				.getColoredDirectoryManager(target, file.getFeatureModel())
				.getFolderColors());
	}

	public String getErrorMessage() {
		return "Reference to package which is not present in some variants";
	}

	public ColoredSourceFile getFile() {
		return file;
	}

	public String getProblemType() {
		return "de.ovgu.cide.typing.manifest.packagereferencerror";
	}

	public Severity getSeverity() {
		return Severity.ERROR;
	}

	public IASTNode getSource() {
		return source;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ManifestPackageCheck))
			return super.equals(obj);
		ManifestPackageCheck other = (ManifestPackageCheck) obj;
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
