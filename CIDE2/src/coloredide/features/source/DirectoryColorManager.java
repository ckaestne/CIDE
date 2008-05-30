package coloredide.features.source;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Path;

import coloredide.features.Feature;

public class DirectoryColorManager extends AbstractColorManager {

	private IContainer directory;

	private static final String THIS_DIR = ".";
	public static final String DIRCOLOR_FILENAME = ".dircolors";

	protected DirectoryColorManager(IContainer directory) {
		super(getColorFile(directory), directory.getProject());
		this.directory = directory;
	}

	protected static IFile getColorFile(IContainer directory) {
		return directory.getFile(new Path(DIRCOLOR_FILENAME));
	}

	private static final WeakHashMap<IContainer, WeakReference<DirectoryColorManager>> dirCache = new WeakHashMap<IContainer, WeakReference<DirectoryColorManager>>();

	/**
	 * if possible use one of the other methods as they do not return a null
	 * value
	 * 
	 * @param directory
	 * @return
	 */
	public static DirectoryColorManager getColoredDirectoryManagerS(
			IContainer directory) {
		if (directory instanceof IWorkspaceRoot)
			return null;
		return getColoredDirectoryManagerForContainer(directory);
	}

	public static DirectoryColorManager getColoredDirectoryManager(
			IFolder directory) {
		return getColoredDirectoryManagerForContainer(directory);
	}

	public static DirectoryColorManager getColoredDirectoryManager(
			IProject directory) {
		return getColoredDirectoryManagerForContainer(directory);
	}

	private static DirectoryColorManager getColoredDirectoryManagerForContainer(
			IContainer directory) {
		DirectoryColorManager cachedCJSF = null;
		WeakReference<DirectoryColorManager> r = dirCache.get(directory);
		if (r != null)
			cachedCJSF = r.get();
		if (cachedCJSF == null) {
			cachedCJSF = new DirectoryColorManager(directory);
			r = new WeakReference<DirectoryColorManager>(cachedCJSF);
			dirCache.put(directory, r);
		}
		return cachedCJSF;
	}

	public Set<Feature> getOwnFolderColors() {
		return super.getOwnColors(THIS_DIR);
	}

	public Set<Feature> getOwnColors(IFile node) {
		return super.getOwnColors(node.getName());
	}

	public boolean addFolderColor(Feature color) {
		return super.addColor(THIS_DIR, color);
	}

	public boolean addColor(IFile node, Feature color) {
		return super.addColor(node.getName(), color);
	}

	public boolean removeFolderColor(Feature color) {
		return super.removeColor(THIS_DIR, color);
	}

	public boolean removeColor(IFile node, Feature color) {
		return super.removeColor(node.getName(), color);
	}

	public boolean hasFolderColor(Feature color) {
		return super.hasColor(THIS_DIR, color);
	}

	public boolean hasColor(IFile node, Feature color) {
		return super.hasColor(node.getName(), color);
	}

	public Set<Feature> getFolderColors() {
		Set<Feature> result = new HashSet<Feature>();
		result.addAll(getOwnFolderColors());
		result.addAll(getInheritedFolderColors());
		return Collections.unmodifiableSet(result);
	}

	public Set<Feature> getColors(IFile node) {
		Set<Feature> result = new HashSet<Feature>();
		result.addAll(getOwnColors(node));
		result.addAll(getFolderColors());
		return Collections.unmodifiableSet(result);
	}

	public Set<Feature> getInheritedFolderColors() {
		Set<Feature> result = new HashSet<Feature>();

		IContainer parentDir = directory.getParent();
		if (parentDir instanceof IFolder) {
			result.addAll(getColoredDirectoryManager((IFolder) parentDir)
					.getFolderColors());
		}
		if (parentDir instanceof IProject) {
			result.addAll(getColoredDirectoryManager((IProject) parentDir)
					.getFolderColors());
		}

		return Collections.unmodifiableSet(result);
	}

	public boolean clearFolderColor() {
		return super.clearColor(THIS_DIR);
	}

	public boolean clearColor(IFile node) {
		return super.clearColor(node.getName());
	}

	public void setFolderColors(Set<Feature> newColors) {
		super.setColors(THIS_DIR, newColors);
	}

	public void setColors(IFile node, Set<Feature> newColors) {
		super.setColors(node.getName(), newColors);
	}

}