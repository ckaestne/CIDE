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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.alternativefeatures.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelWithID;

public interface IStorageProvider {

	/**
	 * stores the annotation map to a disk, database, external program or
	 * whatever
	 * 
	 * @param project
	 * @param annotatedResource
	 * @param annotations
	 * @param isOptional
	 *            only needed for alternatives
	 * @param parentIDs
	 *            only needed for alternatives
	 * @param monitor
	 * @return whether storage has been successful
	 * @throws CoreException
	 */
	boolean storeAnnotations(IProject project, Object annotatedResource,
			Map<String, Set<IFeature>> annotations,
			Map<String, Boolean> isOptional,
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
	 * used only for alternatives. not used currently.
	 * 
	 * stores the given alternative
	 * 
	 * @return whether storage has been successful
	 */
	boolean storeNewAlternative(IProject project, Object annotatedResource,
			Alternative alternative, Map<String, String> id2oldText);

	/**
	 * used only for alternatives. not used currently.
	 * 
	 * Retrieves all alternatives for all AST-nodes.
	 * 
	 * @param project
	 * @param annotatedResource
	 * @return
	 */
	Map<String, List<Alternative>> getAllAlternatives(IProject project,
			Object annotatedResource, IFeatureModelWithID featureModel);

	/**
	 * returns whether this storage manager can be used with the given feature
	 * model
	 * 
	 * @param featureModel
	 * @return
	 */
	boolean isCompatible(IFeatureModel featureModel);

	/**
	 * used only for alternatives. not used currently.
	 * 
	 * Activates the given alternative
	 * 
	 * @param project
	 * @param annotatedResource
	 * @param astID
	 * @param altID
	 * @return
	 */
	boolean activateAlternative(IProject project, Object annotatedResource,
			Alternative alternative, Map<String, String> id2oldText);

	/**
	 * used only for alternatives. not used currently.
	 * 
	 * @return If the storage provider can handle alternatives
	 */
	boolean canHandleAlternatives();

	/**
	 * determines whether the given file is a part of the color storage system
	 * (e.g., .color and .dircolor files)
	 * 
	 * @param file
	 * @return
	 */
	boolean isColorStorageFile(IFile file);
}
