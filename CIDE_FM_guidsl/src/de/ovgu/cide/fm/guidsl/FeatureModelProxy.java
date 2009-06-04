package de.ovgu.cide.fm.guidsl;

import java.util.Collections;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.ovgu.cide.configuration.AbstractConfigurationPage;
import de.ovgu.cide.features.AbstractFeatureModel;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModelWithID;
import featureide.fm.core.FeatureModel;

/**
 * this class delegates all calls to the GuidslFeatureModelWrapper or in case
 * there is no feature model to wrap to the empty feature model
 * 
 * this class is necessary to be able to switch the internal representation of
 * the feature model without changing the externally provided object. especially
 * to switch from the empty feature model to a real one without observable
 * difference.
 * 
 * use only inside this plug-in
 * 
 * @author ckaestne
 * 
 */
public class FeatureModelProxy implements IFeatureModelWithID {

	private final IProject project;

	FeatureModelProxy(IProject project) {
		this.project = project;
	};

	// feature model or null if empty feature
	// model should be used
	private IFeatureModelWithID targetFeatureModel = null;

	/**
	 * returns the target or the empty feature model if null
	 * 
	 * @return
	 */
	private IFeatureModelWithID getTarget() {
		if (targetFeatureModel != null)
			return targetFeatureModel;

		if (project.exists() && project.getFile("model.m").exists())
			return targetFeatureModel = GuidslFeatureModelWrapper
					.getInstance(project);

		return new EmptyFeatureModel(project);
	}

	static class EmptyFeatureModel extends AbstractFeatureModel {

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
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public IFeature createNewFeature() throws UnsupportedOperationException {
		return getTarget().createNewFeature();
	}

	@Override
	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		return getTarget().getConfigurationPage(pageName);
	}

	@Override
	public Set<IFeature> getFeatures() {
		return getTarget().getFeatures();
	}

	@Override
	public IProject getProject() {
		return getTarget().getProject();
	}

	@Override
	public Set<IFeature> getVisibleFeatures() {
		return getTarget().getVisibleFeatures();
	}

	@Override
	public boolean isValidSelection(Set<IFeature> selection) {
		return getTarget().isValidSelection(selection);
	}

	@Override
	public IFeature getFeature(long id) {
		return getTarget().getFeature(id);
	}

	/**
	 * access function used only internally (inside this plug-in!) for
	 * type-checking purposes
	 * 
	 * @return
	 */
	public FeatureModel getInternalModel() {
		if (targetFeatureModel == null)
			return null;
		assert targetFeatureModel instanceof GuidslFeatureModelWrapper;
		return ((GuidslFeatureModelWrapper) targetFeatureModel)
				.getInternalModel();
	}

}
