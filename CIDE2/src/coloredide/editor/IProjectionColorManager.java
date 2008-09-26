package coloredide.editor;

import java.util.Set;

import coloredide.features.IFeature;

public interface IProjectionColorManager {

	public abstract Set<IFeature> getExpandedColors();

	public abstract void expandColor(IFeature color);

	public abstract void collapseColor(IFeature color);

	public abstract void expandAllColors();

	public abstract void collapseAllColors();

}