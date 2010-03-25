package coloredide.preview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;

public class PreviewFeatureSelection {

	private IProject project;

	public PreviewFeatureSelection(IProject project) {
		makeActions();
		this.project = project;
	}

	private final List<PreviewToggleShowFeatureAction> showColorActions = new ArrayList<PreviewToggleShowFeatureAction>();

	private void makeActions() {
		for (Feature feature : FeatureManager.getVisibleFeatures(project)) {
			PreviewToggleShowFeatureAction toggleShowFeatureAction = new PreviewToggleShowFeatureAction(
					feature, project);
			showColorActions.add(toggleShowFeatureAction);
		}
	}

	public List<PreviewToggleShowFeatureAction> getShowColorActions() {
		return showColorActions;
	}

	public Set<Feature> getHiddenFeatures() {
		Set<Feature> result = new HashSet<Feature>();

		for (PreviewToggleShowFeatureAction action : showColorActions) {
			if (!action.isChecked())
				result.add(action.getFeature());
		}

		return result;
	}

}
