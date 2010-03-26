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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public class DirectoryColorManager extends AbstractColorManager {

	private IContainer directory;

	private static final String THIS_DIR = ".";

	protected DirectoryColorManager(IStorageProvider storageProvider,
			IContainer directory, IFeatureModel featureModel) {
		super(storageProvider, directory.getProject(), directory, featureModel);
		assert directory != null;

		this.directory = directory;
		FolderCacheManager.registerDirectoryColorManager(this);
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

			cachedCJSF = new DirectoryColorManager(StorageProviderManager
					.getInstance().getStorageProvider(directory.getProject(),
							featureModel), directory, featureModel);
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
		boolean success = super.addColor(THIS_DIR, color);
		if (success)
			FolderCacheManager.invalidateAllCaches();
		return success;
	}

	public boolean addColor(IFile node, IFeature color) {
		return super.addColor(node.getName(), color);
	}

	public boolean removeFolderColor(IFeature color) {
		boolean success = super.removeColor(THIS_DIR, color);
		if (success)
			FolderCacheManager.invalidateAllCaches();
		return success;
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

	/**
	 * folder color cache is a quick hack to prevent looking up the folder
	 * colors over and over again
	 * 
	 * the static inner class cache manager deletes all caches ones any folder
	 * color is changed. quick and dirty solution with static methods.
	 */
	private Set<IFeature> folderColorCache = null;

	private static class FolderCacheManager {
		private static List<WeakReference<DirectoryColorManager>> registeredManagers = new ArrayList<WeakReference<DirectoryColorManager>>();

		static void registerDirectoryColorManager(DirectoryColorManager d) {
			registeredManagers.add(new WeakReference<DirectoryColorManager>(d));
		}

		static void invalidateAllCaches() {
			Iterator<WeakReference<DirectoryColorManager>> managerIterator = registeredManagers
					.iterator();
			while (managerIterator.hasNext()) {
				WeakReference<DirectoryColorManager> managerRef = managerIterator
						.next();
				DirectoryColorManager manager = managerRef.get();
				if (manager == null)
					managerIterator.remove();
				else
					manager.invalidateFolderColorCache();
			}
		}
	}

	public void invalidateFolderColorCache() {
		folderColorCache = null;
	}

	public Set<IFeature> getFolderColors() {
		if (folderColorCache == null) {
			Set<IFeature> result = new HashSet<IFeature>();
			result.addAll(getOwnFolderColors());
			result.addAll(getInheritedFolderColors());
			folderColorCache = Collections.unmodifiableSet(result);
		}
		return folderColorCache;
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
		boolean success = super.clearColor(THIS_DIR);
		if (success)
			FolderCacheManager.invalidateAllCaches();
		return success;
	}

	public boolean clearColor(IFile node) {
		return super.clearColor(node.getName());
	}

	public void setFolderColors(Set<IFeature> newColors) {
		super.setColors(THIS_DIR, newColors);
		FolderCacheManager.invalidateAllCaches();
	}

	public void setColors(IFile node, Set<IFeature> newColors) {
		super.setColors(node.getName(), newColors);
	}

}