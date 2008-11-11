/**
 * 
 */
package de.ovgu.cide.utils;

import org.eclipse.jface.action.Action;

import de.ovgu.cide.features.IFeature;


public abstract class FeatureAction extends Action {
	protected IFeature feature;

	public FeatureAction(IFeature feature) {
		super();
		this.feature = feature;
	}
}