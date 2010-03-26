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

package de.ovgu.cide.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.DirectoryColorManager;

/**
 * sets colors for a set of files or directories
 * 
 * @author ckaestne
 * 
 */
public class SetCompUnitColorJob extends WorkspaceJob {

	private Collection<IResource> resources;

	private Set<IFeature> features;

	private Set<IFeature> removedfeatures;

	private List<DirectoryColorManager> batched = new ArrayList<DirectoryColorManager>();

	public SetCompUnitColorJob(Collection<IResource> resources,
			Set<IFeature> features, Set<IFeature> removedfeatures) {
		super("Set Resource Colors");
		this.resources = resources;
		this.features = features;
		this.removedfeatures = removedfeatures;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {

		monitor.beginTask("coloring", resources.size());

		for (IResource resource : resources) {
			if (resource instanceof IFile) {
				IFile file = (IFile) resource;
				IFeatureModel fm=FeatureModelManager.getInstance().getFeatureModelCore(file.getProject());
				DirectoryColorManager colorManager = DirectoryColorManager
						.getColoredDirectoryManagerS(resource.getParent(),fm);
				colorManager.beginBatch();
				batched.add(colorManager);

				setFileColors(file, colorManager);
			}

			if (resource instanceof IFolder || resource instanceof IProject) {
				IContainer container = (IContainer) resource;
				IFeatureModel fm=FeatureModelManager.getInstance().getFeatureModelCore(resource.getProject());
				DirectoryColorManager colorManager = DirectoryColorManager
						.getColoredDirectoryManagerS(container,fm);
				colorManager.beginBatch();
				batched.add(colorManager);

				setDirColors(colorManager);
			}

			monitor.worked(1);
		}

		for (DirectoryColorManager colorManager : batched) {
			colorManager.endBatch();
		}

		monitor.done();

		return Status.OK_STATUS;
	}

	private void setDirColors(DirectoryColorManager colorManager) {
		for (IFeature color : features)
			colorManager.addFolderColor(color);
		for (IFeature color : removedfeatures)
			colorManager.removeFolderColor(color);
	}

	private void setFileColors(IFile file, DirectoryColorManager colorManager) {
		for (IFeature color : features)
			colorManager.addColor(file, color);
		for (IFeature color : removedfeatures)
			colorManager.removeColor(file, color);
	}

}
