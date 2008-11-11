package de.ovgu.cide.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Event;

import de.ovgu.cide.features.IFeature;
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

	NewFeatureAction(ToggleTextColorContext context, IFeatureModel featureModel) {
		super(context, null);
		this.featureModel = featureModel;
		this.setText("New Feature/Rule...");
	}

	@Override
	public void run() {
		this.feature = featureModel.createNewFeature();
		if (feature != null)
			super.run();
	}

}
