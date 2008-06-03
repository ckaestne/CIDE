package coloredide.features;

import java.io.Serializable;
import java.util.Set;

import org.eclipse.swt.graphics.RGB;

/**
 * abstract representation of a feature with name, color and visibility.
 * features can be ordered in a natural order (depends on the feature model how
 * this is implemented, e.g., using IDs)
 * 
 * do not compare features for identity, always use the equals function
 * 
 * 
 * @author ckaestne
 * 
 */
public interface IFeature extends Comparable<IFeature> {

	/**
	 * returns a fixed ID that is used for making features persistent. only this
	 * feature-id is stored. the id must be unique for a feature inside a
	 * project and must not change when renaming the feature
	 * 
	 * @return
	 */
	public long getId();

	/**
	 * returns the name of this feature
	 * 
	 * @return not null
	 */
	public String getName();

	/**
	 * sets the name of this feature. note: not all feature models support a
	 * modification
	 * 
	 * @param name:
	 *            new name
	 * @throws UnsupportedOperationException
	 *             if not supported by the feature model
	 */
	public void setName(String name) throws UnsupportedOperationException;

	/**
	 * returns the color to represent this feature
	 * 
	 * @return not null
	 */
	public RGB getRGB();

	/**
	 * sets the color of this feature. note: not all feature models support a
	 * modification
	 * 
	 * @param new
	 *            color value
	 * @throws UnsupportedOperationException
	 *             if not supported by the feature model
	 */
	public void setRGB(RGB color) throws UnsupportedOperationException;

	/**
	 * returns whether this feature is visible. invisible features are hidden
	 * from the user interface in some parts or used to focus on a feature
	 * selection
	 * 
	 * @return
	 */
	public boolean isVisible();

	/**
	 * sets the visibility of this feature. note: not all feature models support
	 * a modification
	 * 
	 * @param isVisible
	 *            new visibility value
	 * @throws UnsupportedOperationException
	 *             if not supported by the feature model
	 */
	public void setVisibile(boolean isVisible) throws UnsupportedOperationException;

	/**
	 * a feature can require other features. that's specific to the List Feature
	 * Model until a better solution is found. return an empty set from all
	 * other feature models
	 * 
	 * @return a set of features, not null
	 */
	public Set<IFeature> getRequiredFeatures();

}
