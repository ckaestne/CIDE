package de.ovgu.cide.features.source;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public interface IStorageProvider {

	/**
	 * stores the annotation map to a disk, database, external program or
	 * whatever
	 * 
	 * @return whether storage has been successful
	 */
	boolean storeAnnotations(IProject project, Object annotatedResource, Map<String, Set<IFeature>> annotations, 
			Map<String, List<String>> parentIDs, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * retrieves annotations for a given resource
	 * 
	 * @param annotatedResource
	 *            object identifying the resource (usually IFile or IFolder)
	 * @return the annotations in a map, or an empty map if no annotations are
	 *         available
	 */
	Map<String, Set<IFeature>> readAnnotations(IProject project,
			Object annotatedResource, IFeatureModel featureModel);
	
	/**
	 * stores the given alternative
	 * 
	 * @return whether storage has been successful
	 */
	boolean storeNewAlternative(IProject project, Object annotatedResource, Alternative alternative, Map<String, String> id2oldText);
	
	Map<String, List<Alternative>> getAlternatives(IProject project, Object annotatedResource, List<String> ids);

	/**
	 * returns whether this storage manager can be used with the given feature
	 * model
	 * 
	 * @param featureModel
	 * @return
	 */
	boolean isCompatible(IFeatureModel featureModel);
	
	/**
	 * TODO MRO
	 * @param project
	 * @param annotatedResource
	 * @param astID
	 * @param altID
	 * @return
	 */
	boolean activateAlternative(IProject project, Object annotatedResource, Alternative alternative, Map<String, String> id2oldText);
}
