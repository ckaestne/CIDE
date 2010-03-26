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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.DirectoryColorManager;

/**
 * shows only files and folders that contain at least one of the visible
 * features.
 * 
 * TODO: this is a straightforward implementation, but probably rather slow.
 * caching might be needed.
 * 
 * @author cKaestner
 * 
 */
public class ColorFilterFeature extends ViewerFilter {

	

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
		Collection<IFeature> expectedColors = featureModel.getVisibleFeatures();
		if (overlap(fileColors, expectedColors))
			return true;

		ColoredSourceFile source = ColoredSourceFile.getColoredSourceFile(file,
				featureModel);
		if (!source.isColored())
			return false;

		Set<IFeature> astColors = source.getColorManager().getAllUsedColors();

		if (overlap(astColors, expectedColors))
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
		Collection<IFeature> expectedColors = featureModel.getVisibleFeatures();
		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManager(folder, featureModel);

		if (overlap(dirColorManager.getFolderColors(), expectedColors))
			return true;

		try {
			for (IResource member : folder.members()) {
				if (member instanceof IFolder)
					if (isContainerVisible((IFolder) member))
						return true;
				if (member instanceof IFile)
					if (isFileVisible((IFile) member))
						return true;
			}
			return false;
		} catch (CoreException e) {
			return false;
		}

	}

	/**
	 * returns whether both sets overlap in one or more entries
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean overlap(Collection<IFeature> a, Collection<IFeature> b) {
		for (IFeature f : a)
			if (b.contains(f))
				return true;
		return false;
	}
}
