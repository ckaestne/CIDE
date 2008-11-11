package de.ovgu.cide.features.source;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public class StorageProviderProxy implements IStorageProvider {

	public boolean isCompatible(IFeatureModel featureModel) {
		if (target == null)
			loadTarget();
		return target.isCompatible(featureModel);
	}

	public Map<String, Set<IFeature>> readAnnotations(IProject project,
			Object annotatedResource, IFeatureModel featureModel) {
		if (target == null)
			loadTarget();
		return target.readAnnotations(project, annotatedResource, featureModel);
	}

	public boolean storeAnnotations(IProject project, Object annotatedResource,
			Map<String, Set<IFeature>> annotations, IProgressMonitor monitor)
			throws CoreException {
		if (target == null)
			loadTarget();
		return target.storeAnnotations(project, annotatedResource, annotations,
				monitor);
	}

	private IConfigurationElement configElement;

	protected StorageProviderProxy() {
	}

	public StorageProviderProxy(IConfigurationElement configurationElement) {
		this.configElement = configurationElement;
		name = configElement.getAttribute("name");
		id = configElement.getAttribute("id");
	}

	protected String name;
	protected String id;
	protected IStorageProvider target = null;

	protected void loadTarget() {
		try {
			target = (IStorageProvider) configElement
					.createExecutableExtension("provider");
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Storage Provider: " + name + " (" + id + ")";
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

}
