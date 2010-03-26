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

package de.ovgu.cide.features;

import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ExtensionPointManager;
import de.ovgu.cide.preferences.PreferenceConstants;

public class FeatureModelManager extends
		ExtensionPointManager<FeatureModelProviderProxy> {

	private static FeatureModelManager instance;

	private FeatureModelManager() {
		super(CIDECorePlugin.ID, "featureModelProvider");
	}

	public static FeatureModelManager getInstance() {
		if (instance == null)
			instance = new FeatureModelManager();
		return instance;
	}

	protected FeatureModelProviderProxy parseExtension(
			IConfigurationElement configurationElement) {
		if (!configurationElement.getName().equals("featureModelProvider"))
			return null;
		return new FeatureModelProviderProxy(configurationElement);
	}

	/**
	 * only one feature model provider may be active at at time (can be
	 * configured in preferences). this returns the active one.
	 * 
	 * @return feature model provider or null if there is no active FMP
	 *         configured or the active FMP is not available
	 */
	public FeatureModelProviderProxy getActiveFeatureModelProvider() {
		String featureModelProviderId = CIDECorePlugin.getDefault()
				.getPreferenceStore().getString(
						PreferenceConstants.P_FEATUREMODELPROVIDER);
		List<FeatureModelProviderProxy> providers = getProviders();
		for (FeatureModelProviderProxy provider : providers)
			if (provider.getId().equals(featureModelProviderId))
				return provider;
		return null;
	}

	private final WeakHashMap<IProject, IFeatureModel> featureModelCache = new WeakHashMap<IProject, IFeatureModel>();

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		// assert project != null && project.exists() && project.isOpen();

		IFeatureModel featureModel = featureModelCache.get(project);
		if (featureModel == null) {
			FeatureModelProviderProxy provider = getActiveFeatureModelProvider();
			if (provider == null)
				throw new FeatureModelNotFoundException(
						"No feature model found.");
			featureModel = provider.getFeatureModel(project);
			featureModelCache.put(project, featureModel);
		}
		return featureModel;
	}

	/**
	 * same as getFeatureModel, but throws a coreException in case of an error.
	 * to be used in jobs
	 * 
	 * @param sourceProject
	 * @return
	 * @throws CoreException
	 */
	public IFeatureModel getFeatureModelCore(IProject sourceProject)
			throws CoreException {
		try {
			return FeatureModelManager.getInstance().getFeatureModel(
					sourceProject);
		} catch (FeatureModelNotFoundException e) {
			e.printStackTrace();
			throw new CoreException(new Status(IStatus.ERROR,
					CIDECorePlugin.ID, "Feature Model cannot be created", e));
		}

	}

	public List<FeatureModelProviderProxy> getFeatureModelProviders() {
		return getProviders();
	}

	public boolean isFeatureModelFile(IFile file) {
		return getActiveFeatureModelProvider().isFeatureModelFile(file);
	}
}
