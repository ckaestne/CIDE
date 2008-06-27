package coloredide.features;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import coloredide.CIDECorePlugin;
import coloredide.configuration.AbstractConfigurationPage;

public class FeatureModelManager {

	private static FeatureModelManager instance;

	private FeatureModelManager() {
	}

	public static FeatureModelManager getInstance() {
		if (instance == null)
			instance = new FeatureModelManager();
		return instance;
	}

	private List<FeatureModelProviderProxy> cachedFeatureModelProviders = null;

	private void loadFeatureModelProvider() {
		if (cachedFeatureModelProviders != null)
			return;
		cachedFeatureModelProviders = new ArrayList<FeatureModelProviderProxy>();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(CIDECorePlugin.ID, "featureModelProvider")
				.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurationElements = extension
					.getConfigurationElements();
			for (IConfigurationElement configurationElement : configurationElements) {
				FeatureModelProviderProxy proxy = parseExtension(configurationElement);
				if (proxy != null)
					cachedFeatureModelProviders.add(proxy);
			}
		}
		debugPrintExtensions();
	}

	private void debugPrintExtensions() {
		for (FeatureModelProviderProxy le : FeatureModelManager.getInstance()
				.getFeatureModelProviders())
			System.out.println(le);
	}

	private FeatureModelProviderProxy parseExtension(
			IConfigurationElement configurationElement) {
		if (!configurationElement.getName().equals("featureModelProvider"))
			return null;
		return new FeatureModelProviderProxy(configurationElement);
	}

	public List<FeatureModelProviderProxy> getFeatureModelProviders() {
		loadFeatureModelProvider();
		return Collections.unmodifiableList(cachedFeatureModelProviders);
	}

	private final WeakHashMap<IProject, IFeatureModel> featureModelCache = new WeakHashMap<IProject, IFeatureModel>();

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
//		assert project != null && project.exists() && project.isOpen();

		IFeatureModel featureModel = featureModelCache.get(project);
		if (featureModel == null) {
			List<FeatureModelProviderProxy> providers = getFeatureModelProviders();
			if (providers.size() == 0)
				throw new FeatureModelNotFoundException(
						"No feature model found.");
			featureModel = providers.get(0).getFeatureModel(project);
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
}
