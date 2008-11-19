package de.ovgu.cide.typing.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.source.ColoredSourceFile;

public abstract class AbstractFileBasedTypingProvider extends
		AbstractTypingProvider {

	protected AbstractFileBasedTypingProvider(IProject project) {
		super(project);
	}

	public Set<ITypingCheck> getChecks() {
		return merge(checks.values());
	}

	protected <K> Set<K> merge(Collection<Set<K>> setList) {
		Set<K> result = new HashSet<K>();
		for (Set<K> item : setList)
			result.addAll(item);
		return result;
	}

	final protected Map<IFile, Set<ITypingCheck>> checks = new HashMap<IFile, Set<ITypingCheck>>();

	public void updateAll() {
		final LinkedList<ColoredSourceFile> files = new LinkedList<ColoredSourceFile>();
		try {
			getProject().accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (resource.getType() == IResource.FILE) {

						try {
							IFile file = (IFile) resource;
							ColoredSourceFile coloredSourceFile = ColoredSourceFile
									.getColoredSourceFile(file);
							if (matchFileForUpdate(coloredSourceFile))
								files.add(coloredSourceFile);
						} catch (FeatureModelNotFoundException e) {
						}

						return false;
					}
					return true;
				}

			});
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// detect files that no longer exist but still have checks
		Set<ITypingCheck> obsoleteChecks = new HashSet<ITypingCheck>();
		Set<IFile> oldFiles = checks.keySet();
		for (IFile oldFile : oldFiles)
			if (!files.contains(oldFile))
				obsoleteChecks.addAll(checks.remove(oldFile));
		if (obsoleteChecks.size()>0)
			fireTypingCheckChanged(Collections.EMPTY_SET, obsoleteChecks);

		updateFile(files);
	}

	/**
	 * used in default implementation of updateAll. used to sort out which files
	 * to update. asked for every single file in the workspace
	 * 
	 * @param resource
	 * @return
	 */
	protected boolean matchFileForUpdate(ColoredSourceFile resource) {
		return true;
	}

}
