package de.ovgu.cide.storage.xml;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelWithID;
import de.ovgu.cide.features.source.IStorageProvider;

public class XMLStorageProvider implements IStorageProvider {

	public XMLStorageProvider() {
	}

	public boolean isCompatible(IFeatureModel featureModel) {
		return featureModel instanceof IFeatureModelWithID;
	}

	private final WeakHashMap<IProject, ProjectStorage> storageCache = new WeakHashMap<IProject, ProjectStorage>();

	private ProjectStorage getProjectStorage(IProject project) {
		ProjectStorage cached = storageCache.get(project);
		if (cached == null) {
			cached = new ProjectStorage(project);
			storageCache.put(project, cached);
		}
		return cached;
	}

	public Map<String, Set<IFeature>> readAnnotations(IProject project,
			Object annotatedResource, IFeatureModel featureModel) {
		assert annotatedResource instanceof IResource;
		assert featureModel instanceof IFeatureModelWithID;
		String path = ((IResource) annotatedResource).getProjectRelativePath()
				.toPortableString();

		return getProjectStorage(project).readAnnotations(path,
				(IFeatureModelWithID) featureModel);
	}

	public boolean storeAnnotations(IProject project, Object annotatedResource, Map<String, Set<IFeature>> annotations, 
			Map<String, List<String>> parentIDs, IProgressMonitor monitor)
			throws CoreException {
		assert annotatedResource instanceof IResource;
		String path = ((IResource) annotatedResource).getProjectRelativePath()
				.toPortableString();

		return getProjectStorage(project).storeAnnotations(path, annotations, parentIDs);
	}
	
	public boolean storeNewAlternative(IProject project, Object annotatedResource, Alternative alternative) {
		assert annotatedResource instanceof IResource;
		String path = ((IResource) annotatedResource).getProjectRelativePath().toPortableString();
		
		return getProjectStorage(project).storeNewAlternative(path, alternative);
	}

	public boolean activateAlternative(IProject project, Object annotatedResource, String astID, String altID) {
		assert annotatedResource instanceof IResource;
		String path = ((IResource) annotatedResource).getProjectRelativePath().toPortableString();

		return getProjectStorage(project).activateAlternative(path, astID, altID);
	}
}
