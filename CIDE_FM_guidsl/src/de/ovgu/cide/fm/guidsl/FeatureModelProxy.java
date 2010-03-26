/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

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

	public IFeature createNewFeature() throws UnsupportedOperationException {
		return getTarget().createNewFeature();
	}

	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		return getTarget().getConfigurationPage(pageName);
	}

	public Set<IFeature> getFeatures() {
		return getTarget().getFeatures();
	}

	public IProject getProject() {
		return getTarget().getProject();
	}

	public Set<IFeature> getVisibleFeatures() {
		return getTarget().getVisibleFeatures();
	}

	public boolean isValidSelection(Set<IFeature> selection) {
		return getTarget().isValidSelection(selection);
	}

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
