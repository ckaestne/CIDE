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

package de.ovgu.cide.fm.list;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.graphics.RGB;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureWithID;

/**
 * adapts the old feature class to the new IFeature interface
 * 
 * @author ckaestne
 * 
 */
public class FeatureAdapter implements IFeatureWithID {

	public FixedFeature feature;
	private ListFeatureModel featureModel;

	public long getId() {
		return feature.getId();
	}

	public String getName() {
		return featureModel.getFeatureNameManager().getFeatureName(feature);
	}

	public RGB getRGB() {
		return featureModel.getFeatureNameManager().getFeatureColor(feature);
	}

	public boolean isVisible() {
		return featureModel.getFeatureNameManager().isFeatureVisible(feature);
	}

	public Set<IFeature> getRequiredFeatures() {
		Set<FixedFeature> requiredFeatures = featureModel
				.getFeatureNameManager().getRequiredFeatures(feature);
		if (requiredFeatures.isEmpty())
			return Collections.emptySet();
		Set<IFeature> result = new HashSet<IFeature>();
		for (FixedFeature f : requiredFeatures)
			result.add(new FeatureAdapter(f, featureModel));
		return result;
	}

	public int compareTo(IFeature o) {
		if (o instanceof FeatureAdapter)
			return feature.compareTo(((FeatureAdapter) o).feature);
		return 0;
	}

	@Override
	public int hashCode() {
		return feature.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FixedFeature)
			return feature.equals(((FixedFeature) obj));
		if (obj instanceof FeatureAdapter)
			return feature.equals(((FeatureAdapter) obj).feature);
		return super.equals(obj);
	}

	public FeatureAdapter(FixedFeature feature, ListFeatureModel featureModel) {
		this.feature = feature;
		this.featureModel = featureModel;
	}

	public void setName(String name) throws UnsupportedOperationException {
		featureModel.getFeatureNameManager().setFeatureName(feature, name);
	}

	public void setRGB(RGB color) throws UnsupportedOperationException {
		featureModel.getFeatureNameManager().setFeatureColor(feature, color);
	}

	public void setVisible(boolean isVisible)
			throws UnsupportedOperationException {
		featureModel.getFeatureNameManager().setFeatureVisible(feature,
				isVisible);
	}

	public boolean canSetName() {
		return true;
	}

	public boolean canSetRGB() {
		return true;
	}

	public boolean canSetVisible() {
		return true;
	}

}
