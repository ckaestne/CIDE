package de.ovgu.cide.editor;

import org.eclipse.jface.dialogs.MessageDialog;

import de.ovgu.cide.features.IFeatureModel;

/**
 * this action calls the feature model to create a new feature. if supported by
 * the feature model, the code is directly annotated with the new feature
 * 
 * @author cKaestner
 * 
 */
public class NewFeatureAction extends ToggleTextColorAction {

	private IFeatureModel featureModel;

	NewFeatureAction(SelectionActionsContext context, IFeatureModel featureModel) {
		super(context, null);
		this.featureModel = featureModel;

		this.setText("New Feature/Rule...");
		this.setChecked(false);
	}

	@Override
	public void run() {
		try {
			this.feature = featureModel.createNewFeature();
			if (feature != null)
				super.run();
		} catch (UnsupportedOperationException e) {
			MessageDialog.openError(null, "New feature",
					"The current feature model does not support this action. "
							+ "Edit the feature model manually instead.");
		}
	}

}
