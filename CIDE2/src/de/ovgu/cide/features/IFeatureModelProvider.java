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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

/**
 * one feature model is required for every project. the provider creates such a
 * feature model for a given project. CIDE selects the appropriate provider for
 * each project.
 * 
 * @author ckaestne
 * 
 */
public interface IFeatureModelProvider {

	/**
	 * returns the feature model for a given project. if a feature model does
	 * not exist it should usually be generated on the fly.
	 * 
	 * @param project
	 *            for which the feature model should be created. project must
	 *            exist.
	 * @return the feature model, not null
	 * @throws FeatureModelNotFoundException
	 *             if there is no feature model from this provider for the given
	 *             project (e.g., project does not have the correct nature).
	 */
	IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException;

	/**
	 * returns whether a given file belongs to the feature model representation
	 * 
	 * used to exclude such files from certain operations
	 * 
	 * @param file
	 * @return
	 */
	boolean isFeatureModelFile(IFile file);
}
