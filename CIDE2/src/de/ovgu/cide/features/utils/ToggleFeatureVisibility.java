package de.ovgu.cide.features.utils;

import org.eclipse.jface.action.Action;

import de.ovgu.cide.features.IFeature;

public class ToggleFeatureVisibility extends Action {

	private IFeature feature;

	public ToggleFeatureVisibility(IFeature feature) {

		super(feature.getName());
		this.setChecked(feature.isVisible());
		this.feature = feature;
	}

	@Override
	public void run() {
		feature.setVisible(!feature.isVisible());
	}

}
