package de.ovgu.cide.editor;

import java.util.Set;

import de.ovgu.cide.features.IFeature;

public interface IProjectionColorManager {

	public abstract Set<IFeature> getExpandedColors();

	public abstract void expandColor(IFeature color);

	public abstract void collapseColor(IFeature color);

	public abstract void expandAllColors();

	public abstract void collapseAllColors();

}