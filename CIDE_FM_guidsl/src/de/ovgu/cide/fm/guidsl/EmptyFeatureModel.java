package de.ovgu.cide.fm.guidsl;

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import coloredide.configuration.AbstractConfigurationPage;
import coloredide.features.AbstractFeatureModel;
import coloredide.features.IFeature;

public class EmptyFeatureModel extends AbstractFeatureModel {

	protected EmptyFeatureModel(IProject project) {
		super(project);
	}

	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		return new AbstractConfigurationPage(pageName, this) {

			@Override
			protected Control createMainControl(Composite composite) {
				this.setErrorMessage("No feature model available");
				this.setPageComplete(false);
				return composite;
			}

			@Override
			public Set<IFeature> getNotSelectedFeatures() {
				return Collections.EMPTY_SET;
			}

			@Override
			public Set<IFeature> getSelectedFeatures() {
				return Collections.EMPTY_SET;
			}

		};
	}

	public IFeature getFeature(long id) {
		return null;
	}

	public Set<IFeature> getFeatures() {
		return Collections.EMPTY_SET;
	}

	public boolean isValidSelection(Set<IFeature> selection) {
		return false;
	}

	public IFeature createNewFeature() {
		return null;
	}

}
