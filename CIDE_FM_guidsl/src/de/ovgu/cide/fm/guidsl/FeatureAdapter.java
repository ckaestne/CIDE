package de.ovgu.cide.fm.guidsl;

import java.util.Collections;
import java.util.Set;

import org.eclipse.swt.graphics.RGB;

import coloredide.features.IFeature;
import featureide_core.model.Feature;

public class FeatureAdapter implements IFeature {

	private Feature feature;
	private GuidslFeatureModelWrapper model;

	public FeatureAdapter(Feature feature, GuidslFeatureModelWrapper model) {
		this.feature = feature;
		this.model = model;
	}

	public long getId() {
		return model.extraAttributeStorage.getFeatureId(feature);
	}

	public String getName() {
		return feature.getName();
	}

	public RGB getRGB() {
		return model.extraAttributeStorage.getFeatureColor(feature);
	}

	public Set<IFeature> getRequiredFeatures() {
		return Collections.EMPTY_SET;
	}

	public boolean isVisible() {
		return model.extraAttributeStorage.isFeatureVisible(feature);
	}

	public void setName(String name) throws UnsupportedOperationException {
		// feature.setName(name);
		throw new UnsupportedOperationException();
	}

	public void setRGB(RGB color) throws UnsupportedOperationException {
		model.extraAttributeStorage.setFeatureColor(feature, color);
	}

	public void setVisible(boolean isVisible)
			throws UnsupportedOperationException {
		model.extraAttributeStorage.setFeatureVisibile(feature, isVisible);
	}

	public int compareTo(IFeature o) {
		return getName().compareTo(o.getName());
	}

	public boolean canSetName() {
		return false;
	}

	public boolean canSetRGB() {
		return true;
	}

	public boolean canSetVisible() {
		return true;
	}

}
