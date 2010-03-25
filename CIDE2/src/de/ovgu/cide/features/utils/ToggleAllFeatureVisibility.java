package de.ovgu.cide.features.utils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public class ToggleAllFeatureVisibility extends Action {

	private final IFeatureModel featureModel;
	protected final boolean visible;

	public ToggleAllFeatureVisibility(IFeatureModel featureModel, boolean select) {

		super(select ? "Select all" : "Deselect all",IAction.AS_PUSH_BUTTON);
		this.featureModel = featureModel;
		this.visible = select;
	}

	@Override
	public void run() {
		for (IFeature feature : featureModel.getFeatures()) {
			feature.setVisible(visible);
		}

	}
}
