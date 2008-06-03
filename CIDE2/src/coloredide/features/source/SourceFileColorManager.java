package coloredide.features.source;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;

import cide.gast.ASTColorInheritance;
import cide.gast.ASTNode;
import coloredide.features.IFeature;

/**
 * serializable color manager that represents the colors of one individual .java
 * file.
 * 
 * @author cKaestner
 * 
 */
public class SourceFileColorManager extends AbstractColorManager {

	private final DirectoryColorManager directoryColorManager;
	private final ColoredSourceFile source;

	public SourceFileColorManager(IFile colorFile, ColoredSourceFile source,
			DirectoryColorManager directoryColorManager) {
		super(colorFile, source.getFeatureModel());
		this.directoryColorManager = directoryColorManager;
		this.source = source;
	}

	public Set<IFeature> getOwnColors(ASTNode node) {
		return super.getOwnColors(node.getId());
	}

	public boolean addColor(ASTNode node, IFeature color) {
		return super.addColor(node.getId(), color);
	}

	public boolean removeColor(ASTNode node, IFeature color) {
		return super.removeColor(node.getId(), color);
	}

	public boolean hasColor(ASTNode node, IFeature color) {
		return super.hasColor(node.getId(), color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.source.ISourceFileColorProvider#getColors(cide.gast.ASTNode)
	 */
	public Set<IFeature> getColors(ASTNode node) {
		Set<IFeature> result = new HashSet<IFeature>();
		result.addAll(getOwnColors(node));
		result.addAll(getInheritedColors(node));
		return Collections.unmodifiableSet(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.source.ISourceFileColorProvider#getInheritedColors(cide.gast.ASTNode)
	 */
	public Set<IFeature> getInheritedColors(ASTNode node) {
		Set<IFeature> result = new HashSet<IFeature>();

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

	public void setColors(ASTNode node, Set<IFeature> newColors) {
		super.setColors(node.getId(), newColors);
	}

}
