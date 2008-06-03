/**
 * 
 */
package coloredide.utils;

import org.eclipse.jface.action.Action;

import coloredide.features.IFeature;


public abstract class FeatureAction extends Action {
	protected IFeature feature;

	public FeatureAction(IFeature feature) {
		super();
		this.feature = feature;
	}
}