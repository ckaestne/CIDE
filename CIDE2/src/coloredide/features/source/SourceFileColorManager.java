package coloredide.features.source;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;

import cide.features.IASTColorProvider;
import cide.features.IFeature;
import cide.gast.ASTNode;
import coloredide.features.ASTColorInheritance;
import coloredide.features.Feature;

/**
 * serializable color manager that represents the colors of one individual .java
 * file.
 * 
 * @author cKaestner
 * 
 */
public class SourceFileColorManager extends AbstractColorManager implements
		IASTColorProvider {

	private final DirectoryColorManager directoryColorManager;
	private final ColoredSourceFile source;

	public SourceFileColorManager(IFile colorFile, ColoredSourceFile source,
			DirectoryColorManager directoryColorManager) {
		super(colorFile, source.getProject());
		this.directoryColorManager = directoryColorManager;
		this.source = source;
	}

	public Set<IFeature> getOwnColorsI(ASTNode node) {
		return new HashSet<IFeature>(getOwnColors(node));
	}

	public Set<Feature> getOwnColors(ASTNode node) {
		return super.getOwnColors(node.getId());
	}

	public boolean addColor(ASTNode node, Feature color) {
		return super.addColor(node.getId(), color);
	}

	public boolean removeColor(ASTNode node, Feature color) {
		return super.removeColor(node.getId(), color);
	}

	public boolean hasColor(ASTNode node, Feature color) {
		return super.hasColor(node.getId(), color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.source.ISourceFileColorProvider#getColors(cide.gast.ASTNode)
	 */
	public Set<Feature> getColors(ASTNode node) {
		Set<Feature> result = new HashSet<Feature>();
		result.addAll(getOwnColors(node));
		result.addAll(getInheritedColors(node));
		return Collections.unmodifiableSet(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.source.ISourceFileColorProvider#getInheritedColors(cide.gast.ASTNode)
	 */
	public Set<Feature> getInheritedColors(ASTNode node) {
		Set<Feature> result = new HashSet<Feature>();

		ASTNode parent = node.getParent();
		if (parent != null) {
			if (parent.isOptional())
				if (ASTColorInheritance.inheritsColors(parent, node))
					result.addAll(getOwnColors(parent));
			result.addAll(getInheritedColors(parent));
		}
		result.addAll(directoryColorManager.getColors(source.getResource()));

		return Collections.unmodifiableSet(result);
	}

	public boolean clearColor(ASTNode node) {
		return super.clearColor(node.getId());
	}

	public void setColors(ASTNode node, Set<Feature> newColors) {
		super.setColors(node.getId(), newColors);
	}

	public Set<IFeature> getColorsI(ASTNode node) {
		return new HashSet<IFeature>(getColors(node));
	}

	public Set<IFeature> getInheritedColorsI(ASTNode node) {
		return new HashSet<IFeature>(getInheritedColors(node));
	}
}
