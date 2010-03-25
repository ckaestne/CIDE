/**
 * 
 */
package coloredide.utils;

import org.eclipse.jface.action.Action;

import coloredide.features.Feature;


public class FeatureAction extends Action {
	protected Feature feature;

	public FeatureAction(Feature feature) {
		super();
		this.feature = feature;
	}
}