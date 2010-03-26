/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.features;

import java.util.Set;

import org.eclipse.swt.graphics.RGB;

/**
 * abstract representation of a feature with name, color and visibility.
 * features can be ordered in a natural order (depends on the feature model how
 * this is implemented, e.g., using IDs)
 * 
 * do not compare features for identity, always use the equals function
 * 
 * features themselves as they are provided by the FeatureModel do not have a
 * certain format of IDs. most feature models and the default storage mechanism
 * require IFeatureWithID that provide a unique ID for each feature.
 * 
 * 
 * @author ckaestne
 * 
 */
public interface IFeature extends Comparable<IFeature> {

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
	 * @param name
	 *            : new name
	 * @throws UnsupportedOperationException
	 *             if not supported by the feature model
	 */
	public void setName(String name) throws UnsupportedOperationException;

	/**
	 * returns whether modifying the name of the feature is possible (depends on
	 * the feature model implementation)
	 */
	public boolean canSetName();

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
	 * @param new color value
	 * @throws UnsupportedOperationException
	 *             if not supported by the feature model
	 */
	public void setRGB(RGB color) throws UnsupportedOperationException;

	/**
	 * returns whether modifying the color of the feature is possible (depends
	 * on the feature model implementation)
	 */
	public boolean canSetRGB();

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
	public void setVisible(boolean isVisible)
			throws UnsupportedOperationException;

	/**
	 * returns whether modifying the visibility of the feature is possible
	 * (depends on the feature model implementation)
	 */
	public boolean canSetVisible();

	/**
	 * a feature can require other features. that's specific to the List Feature
	 * Model until a better solution is found. return an empty set from all
	 * other feature models
	 * 
	 * @return a set of features, not null
	 */
	public Set<IFeature> getRequiredFeatures();

}
