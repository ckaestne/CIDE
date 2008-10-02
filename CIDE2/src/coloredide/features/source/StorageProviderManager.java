package coloredide.features.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

import coloredide.CIDECorePlugin;
import coloredide.features.IFeatureModel;

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

		return new DefaultStorageProvider();
	}

	private static StorageProviderManager instance;

	private StorageProviderManager() {
	}

	public static StorageProviderManager getInstance() {
		if (instance == null)
			instance = new StorageProviderManager();
		return instance;
	}

	private List<IStorageProvider> storageProviders = null;

	private void loadStorageProviders() {
		if (storageProviders != null)
			return;
		storageProviders = new ArrayList<IStorageProvider>();

		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(CIDECorePlugin.ID, "colorStorageProvider")
				.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurationElements = extension
					.getConfigurationElements();
			for (IConfigurationElement configurationElement : configurationElements)
				if (configurationElement.getName().equals("storageProvider"))
					try {
						IStorageProvider mechanism = (IStorageProvider) configurationElement
								.createExecutableExtension("provider");
						storageProviders.add(mechanism);
					} catch (CoreException e) {
						e.printStackTrace();
					}

		}
	}

	/**
	 * list sorted by priority. highest priority first
	 * 
	 * @return
	 */
	public List<IStorageProvider> getStorageProviders() {
		loadStorageProviders();

		return Collections.unmodifiableList(storageProviders);
	}

}
