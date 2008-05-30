package coloredide.colorfilter;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.DirectoryColorManager;

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
public class ColorFilter2 extends ViewerFilter {

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
		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManagerS(file.getParent());
		Collection<Feature> fileColors = dirColorManager.getColors(file);
		Collection<Feature> expectedColors = FeatureManager
				.getVisibleFeatures(file.getProject());
		if (overlap(fileColors, expectedColors))
			return true;

		ColoredSourceFile source = ColoredSourceFile.getColoredSourceFile(file);
		if (!source.isColored())
			return false;

		Set<Feature> astColors = source.getColorManager().getAllUsedColors();

		if (overlap(astColors, expectedColors))
			return true;

		return false;
	}

	private boolean isContainerVisible(IFolder folder) {
		Collection<Feature> expectedColors = FeatureManager
				.getVisibleFeatures(folder.getProject());
		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManager(folder);

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
	private boolean overlap(Collection<Feature> a, Collection<Feature> b) {
		for (Feature f : a)
			if (b.contains(f))
				return true;
		return false;
	}
}
