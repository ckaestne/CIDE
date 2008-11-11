package de.ovgu.cide.features.source;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cide.gast.ASTWrappers;
import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;

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

	public SourceFileColorManager(IStorageProvider storageProvider,
			ColoredSourceFile source,
			DirectoryColorManager directoryColorManager) {
		super(storageProvider, source.getResource().getProject(), source
				.getResource(), source.getFeatureModel());
		this.directoryColorManager = directoryColorManager;
		this.source = source;
	}

	public Set<IFeature> getOwnColors(IASTNode node) {
		return super.getOwnColors(node.getId());
	}

	public boolean addColor(IASTNode node, IFeature color) {
		return super.addColor(node.getId(), color);
	}

	public boolean removeColor(IASTNode node, IFeature color) {
		return super.removeColor(node.getId(), color);
	}

	public boolean hasColor(IASTNode node, IFeature color) {
		return super.hasColor(node.getId(), color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * coloredide.features.source.ISourceFileColorProvider#getColors(cide.gast
	 * .IASTNode)
	 */
	public Set<IFeature> getColors(IASTNode node) {
		Set<IFeature> result = new HashSet<IFeature>();
		result.addAll(getOwnColors(node));
		result.addAll(getInheritedColors(node));
		return Collections.unmodifiableSet(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * coloredide.features.source.ISourceFileColorProvider#getInheritedColors
	 * (cide.gast.IASTNode)
	 */
	public Set<IFeature> getInheritedColors(IASTNode node) {
		Set<IFeature> result = new HashSet<IFeature>();

		IASTNode parent = node.getParent();
		if (parent != null) {
			if (parent.isOptional())
				if (ASTWrappers.inheritsColors(parent, node))
					result.addAll(getOwnColors(parent));
			result.addAll(getInheritedColors(parent));
		}
		result.addAll(directoryColorManager.getColors(source.getResource()));

		return Collections.unmodifiableSet(result);
	}

	public boolean clearColor(IASTNode node) {
		return super.clearColor(node.getId());
	}

	public void setColors(IASTNode node, Set<IFeature> newColors) {
		super.setColors(node.getId(), newColors);
	}

	public DirectoryColorManager getDirectoryColorManager() {
		return directoryColorManager;
	}
}
