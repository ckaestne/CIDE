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

package de.ovgu.cide.features.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.features.IFeatureModel;

public class StorageProviderManager {

	/**
	 * returns the storage provider for a given project. returns the first
	 * compatible one without further (user-specific) selection so far
	 * 
	 * @param project
	 * @return
	 */
	public IStorageProvider getStorageProvider(IProject project,
			IFeatureModel featureModel) {
		for (IStorageProvider provider : getStorageProviders()) {
			if (provider.isCompatible(featureModel))
				return provider;
		}
		return null;
	}

	private static StorageProviderManager instance;

	private StorageProviderManager() {
	}

	public static StorageProviderManager getInstance() {
		if (instance == null)
			instance = new StorageProviderManager();
		return instance;
	}

	private List<StorageProviderProxy> storageProviders = null;

	private void loadStorageProviders() {
		if (storageProviders != null)
			return;
		storageProviders = new ArrayList<StorageProviderProxy>();

		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(CIDECorePlugin.ID, "colorStorageProvider")
				.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurationElements = extension
					.getConfigurationElements();
			for (IConfigurationElement configurationElement : configurationElements)
				if (configurationElement.getName().equals("storageProvider"))
					storageProviders.add(new StorageProviderProxy(
							configurationElement));
		}

		assert !storageProviders.isEmpty();
//		storageProviders
//				.add(new DefaultStorageProvider().new DefaultStorageProviderProxy());
	}

	/**
	 * list sorted by priority. highest priority first
	 * 
	 * @return
	 */
	public List<StorageProviderProxy> getStorageProviders() {
		loadStorageProviders();

		return Collections.unmodifiableList(storageProviders);
	}

}
