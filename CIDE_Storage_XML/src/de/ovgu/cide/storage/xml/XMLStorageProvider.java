package de.ovgu.cide.storage.xml;

import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.features.IFeatureModelWithID;
import coloredide.features.source.IStorageProvider;

public class XMLStorageProvider implements IStorageProvider {

	public XMLStorageProvider() {
	}

	@Override
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

	@Override
	public Map<String, Set<IFeature>> readAnnotations(IProject project,
			Object annotatedResource, IFeatureModel featureModel) {
		assert annotatedResource instanceof IResource;
		assert featureModel instanceof IFeatureModelWithID;
		String path = ((IResource) annotatedResource).getProjectRelativePath()
				.toPortableString();

		return getProjectStorage(project).read(path,
				(IFeatureModelWithID) featureModel);
	}

	@Override
	public boolean storeAnnotations(IProject project, Object annotatedResource,
			Map<String, Set<IFeature>> annotations, IProgressMonitor monitor)
			throws CoreException {
		assert annotatedResource instanceof IResource;
		String path = ((IResource) annotatedResource).getProjectRelativePath()
				.toPortableString();

		return getProjectStorage(project).store(path, annotations);
	}

}
