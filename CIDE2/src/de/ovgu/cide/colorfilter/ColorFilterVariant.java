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

package de.ovgu.cide.colorfilter;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.DirectoryColorManager;

/**
 * shows files and folders that are included in a variant for the given feature
 * selection. specifically those files and directories are hidden that are
 * entirely annotated, but not included in the feature selection.
 * 
 * does not look at the file content, because most files will probably contain
 * some "white" code
 * 
 * color files and model.m are always hidden in this view
 * 
 * TODO: improvement to look at file content and see whether any code remains in
 * a variant
 * 
 * 
 * @author cKaestner
 * 
 */
public class ColorFilterVariant extends ViewerFilter {

	@Override
	public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
		if (parent instanceof TreePath)
			parent = ((TreePath) parent).getLastSegment();

		if (!(parent instanceof IResource) || parent instanceof IWorkspaceRoot)
			return elements;
		else
			return super.filter(viewer, parent, elements);
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IFolder) {
			return isContainerVisible((IFolder) element);
		}
		if (element instanceof IFile) {
			return isFileVisible((IFile) element);
		}

		// show all others
		return true;
	}

	private boolean isFileVisible(IFile file) {
		if (file == null || file.getName() == null
				|| file.getFileExtension() == null
				|| file.getFileExtension().equals("color")
				|| file.getFileExtension().equals("colors")
				|| file.getName().equals("model.m"))
			return false;

		IFeatureModel featureModel;
		try {
			featureModel = FeatureModelManager.getInstance().getFeatureModel(
					file.getProject());
		} catch (FeatureModelNotFoundException e) {
			// projects without feature model are always visible
			return true;
		}
		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManagerS(file.getParent(), featureModel);
		Collection<IFeature> fileColors = dirColorManager.getColors(file);
		if (fileColors.isEmpty())
			return true;

		Collection<IFeature> expectedColors = featureModel.getVisibleFeatures();
		if (expectedColors.containsAll(fileColors))
			return true;

		return false;
	}

	private boolean isContainerVisible(IFolder folder) {
		IFeatureModel featureModel;
		try {
			featureModel = FeatureModelManager.getInstance().getFeatureModel(
					folder.getProject());
		} catch (FeatureModelNotFoundException e) {
			// projects without feature model are always visible
			return true;
		}
		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManager(folder, featureModel);
		Set<IFeature> folderColors = dirColorManager.getFolderColors();
		if (folderColors.isEmpty())
			return true;

		Collection<IFeature> expectedColors = featureModel.getVisibleFeatures();
		if (expectedColors.containsAll(folderColors))
			return true;

		return false;
	}
}
