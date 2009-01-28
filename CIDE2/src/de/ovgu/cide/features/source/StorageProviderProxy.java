package de.ovgu.cide.features.source;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.af.Alternative;
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
			Map<String, Set<IFeature>> annotations, Map<String, List<String>> parentIDs, IProgressMonitor monitor)
			throws CoreException {
		if (target == null)
			loadTarget();
		return target.storeAnnotations(project, annotatedResource, annotations, parentIDs, monitor);
	}
	
	public boolean activateAlternative(IProject project, Object annotatedResource, Alternative alternative, Map<String, String> id2oldText) {
		if (target == null)
			loadTarget();
		return target.activateAlternative(project, annotatedResource, alternative, id2oldText);
	}
	
	public boolean storeNewAlternative(IProject project, Object annotatedResource, Alternative alternative, Map<String, String> id2oldText) {
		if (target == null)
			loadTarget();
		return target.storeNewAlternative(project, annotatedResource, alternative, id2oldText);
	}
	
	@Override
	public Map<String, List<Alternative>> getAlternatives(IProject project, Object annotatedResource, List<String> ids) {
		if (target == null)
			loadTarget();
		return target.getAlternatives(project, annotatedResource, ids);
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
