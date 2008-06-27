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

import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;

public class DirectoryColorManager extends AbstractColorManager {

	private IContainer directory;

	private static final String THIS_DIR = ".";
	public static final String DIRCOLOR_FILENAME = ".dircolors";

	protected DirectoryColorManager(IContainer directory,
			IFeatureModel featureModel) {
		super(getColorFile(directory), featureModel);
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
			IContainer directory, IFeatureModel featureModel) {
		if (directory instanceof IWorkspaceRoot)
			return null;
		return getColoredDirectoryManagerForContainer(directory, featureModel);
	}

	public static DirectoryColorManager getColoredDirectoryManager(
			IFolder directory, IFeatureModel featureModel) {
		return getColoredDirectoryManagerForContainer(directory, featureModel);
	}

	public static DirectoryColorManager getColoredDirectoryManager(
			IProject directory, IFeatureModel featureModel) {
		return getColoredDirectoryManagerForContainer(directory, featureModel);
	}

	private static DirectoryColorManager getColoredDirectoryManagerForContainer(
			IContainer directory, IFeatureModel featureModel) {
		DirectoryColorManager cachedCJSF = null;
		WeakReference<DirectoryColorManager> r = dirCache.get(directory);
		if (r != null)
			cachedCJSF = r.get();
		if (cachedCJSF == null) {
			cachedCJSF = new DirectoryColorManager(directory, featureModel);
			r = new WeakReference<DirectoryColorManager>(cachedCJSF);
			dirCache.put(directory, r);
		}
		return cachedCJSF;
	}

	public Set<IFeature> getOwnFolderColors() {
		return super.getOwnColors(THIS_DIR);
	}

	public Set<IFeature> getOwnColors(IFile node) {
		return super.getOwnColors(node.getName());
	}

	public boolean addFolderColor(IFeature color) {
		return super.addColor(THIS_DIR, color);
	}

	public boolean addColor(IFile node, IFeature color) {
		return super.addColor(node.getName(), color);
	}

	public boolean removeFolderColor(IFeature color) {
		return super.removeColor(THIS_DIR, color);
	}

	public boolean removeColor(IFile node, IFeature color) {
		return super.removeColor(node.getName(), color);
	}

	public boolean hasFolderColor(IFeature color) {
		return super.hasColor(THIS_DIR, color);
	}

	public boolean hasColor(IFile node, IFeature color) {
		return super.hasColor(node.getName(), color);
	}

	private Set<IFeature> folderColorHash = null;

	public Set<IFeature> getFolderColors() {
		if (folderColorHash == null) {
			Set<IFeature> result = new HashSet<IFeature>();
			result.addAll(getOwnFolderColors());
			result.addAll(getInheritedFolderColors());
			folderColorHash = Collections.unmodifiableSet(result);
		}
		return folderColorHash;
	}

	public Set<IFeature> getColors(IFile node) {
		Set<IFeature> result = new HashSet<IFeature>();
		result.addAll(getOwnColors(node));
		result.addAll(getFolderColors());
		return Collections.unmodifiableSet(result);
	}

	public Set<IFeature> getInheritedFolderColors() {
		Set<IFeature> result = new HashSet<IFeature>();

		IContainer parentDir = directory.getParent();
		if (parentDir instanceof IFolder) {
			result.addAll(getColoredDirectoryManager((IFolder) parentDir,
					featureModel).getFolderColors());
		}
		if (parentDir instanceof IProject) {
			result.addAll(getColoredDirectoryManager((IProject) parentDir,
					featureModel).getFolderColors());
		}

		return Collections.unmodifiableSet(result);
	}

	public boolean clearFolderColor() {
		return super.clearColor(THIS_DIR);
	}

	public boolean clearColor(IFile node) {
		return super.clearColor(node.getName());
	}

	public void setFolderColors(Set<IFeature> newColors) {
		super.setColors(THIS_DIR, newColors);
	}

	public void setColors(IFile node, Set<IFeature> newColors) {
		super.setColors(node.getName(), newColors);
	}

}