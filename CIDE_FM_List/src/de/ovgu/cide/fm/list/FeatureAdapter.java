package de.ovgu.cide.fm.list;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.RGB;

import coloredide.features.IFeature;

/**
 * adapts the old feature class to the new IFeature interface
 * 
 * @author ckaestne
 * 
 */
public class FeatureAdapter implements IFeature {

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
