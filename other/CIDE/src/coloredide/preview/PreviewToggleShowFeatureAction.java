/**
 * 
 */
package coloredide.preview;

import org.eclipse.core.resources.IProject;

import coloredide.features.Feature;
import coloredide.utils.FeatureAction;

public class PreviewToggleShowFeatureAction extends FeatureAction {

//	private ColoredIDEPlugin activator;

	public PreviewToggleShowFeatureAction(Feature feature, IProject project) {
		super(feature);
		this.setText(feature.getName(project));
		this.setChecked(true);
	}

	public void run() {
		super.run();
		PreviewManager.updatedShowFeature(feature);
	}

	public Feature getFeature() {
		return feature;
	}
}