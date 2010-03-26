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

package de.ovgu.cide.typing.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.FileColorChangedEvent;
import de.ovgu.cide.IColorChangeListener;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.internal.manager.EvaluationStrategyManager;
import de.ovgu.cide.typing.internal.manager.TypingExtensionManager;
import de.ovgu.cide.typing.model.DebugTyping;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import de.ovgu.cide.typing.model.ITypingCheck;
import de.ovgu.cide.typing.model.ITypingCheckListener;
import de.ovgu.cide.typing.model.ITypingProvider;
import de.ovgu.cide.typing.model.TypeCheckChangeEvent;

public class TypingManager {
	public TypingManager() {
		listener = new ListenerMix();
	}

	final ListenerMix listener;

	private final Set<ITypingCheck> knownChecks = new HashSet<ITypingCheck>();

	public void register() {
		CIDECorePlugin.getDefault().addColorChangeListener(listener);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
	}

	public void unregister() {
		if (listener == null)
			return;
		CIDECorePlugin.getDefault().removeColorChangeListener(listener);
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
	}

	class ListenerMix implements IColorChangeListener, ITypingCheckListener,
			IResourceChangeListener {

		public void astColorChanged(ASTColorChangedEvent event) {
			reevaluateFileChecks(Collections.singleton(event
					.getColoredSourceFile()));
		}

		public void colorListChanged(ColorListChangedEvent event) {
			if (event.anyChangeOf(ChangeType.REMOVE)
					|| event.anyChangeOf(ChangeType.DEPENDENCY)) {
				clearEvaluationStrategyCache(event.getProject());
				reevaluateProjectChecks(event.getProject());
			}
		}

		/**
		 * file color changed. reevaluate everything affected by this file TODO:
		 * for now only reevaluate checks in this file
		 */
		public void fileColorChanged(FileColorChangedEvent event) {
			final HashSet<ColoredSourceFile> toCheck = new HashSet<ColoredSourceFile>();
			for (IContainer folder : event.getAffectedFolders()) {
				try {
					if (folder.exists())
						folder.accept(new IResourceVisitor() {

							public boolean visit(IResource resource)
									throws CoreException {
								if (resource instanceof IFile)
									try {
										toCheck
												.add(ColoredSourceFile
														.getColoredSourceFile((IFile) resource));
									} catch (FeatureModelNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								return true;
							}
						});
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}

			reevaluateFileChecks(toCheck);
		}

		public void changedTypingChecks(TypeCheckChangeEvent event,
				IProgressMonitor monitor) {
			// called from within a job!
			knownChecks.removeAll(event.getObsoleteChecks());
			removeObsoleteErrors(event.getObsoleteChecks());
			evaluateChecks(event.getAddedChecks(), event.getProvider()
					.getProject(), monitor);
			knownChecks.addAll(event.getAddedChecks());
		}

		/**
		 * file content changed. recalculate necessary checks (but do not
		 * reevaluate existing ones)
		 */
		public void resourceChanged(IResourceChangeEvent event) {

			final List<ColoredSourceFile> toCheck = new LinkedList<ColoredSourceFile>();
			try {
				if (event != null && event.getDelta() != null)
					event.getDelta().accept(new IResourceDeltaVisitor() {

						public boolean visit(IResourceDelta delta)
								throws CoreException {
							if (delta.getKind() == IResourceDelta.CHANGED
									&& (delta.getFlags() & IResourceDelta.CONTENT) > 0)
								if (delta.getResource().getType() == IResource.FILE)
									try {
										toCheck
												.add(ColoredSourceFile
														.getColoredSourceFile((IFile) delta
																.getResource()));
									} catch (FeatureModelNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							return true;
						}
					});
			} catch (CoreException e) {

				e.printStackTrace();
			}

			recheckFiles(toCheck);
		}

	}

	/**
	 * called from within a job
	 * 
	 * @param monitor
	 */
	public void evaluateChecks(Collection<ITypingCheck> checks,
			IProject project, IProgressMonitor monitor) {
		SubProgressMonitor monitor2 = new SubProgressMonitor(monitor, 1);
		monitor2.beginTask("Evaluating type checks...", checks.size() + 10);

		// called from within a job!
		IEvaluationStrategy strategy;
		try {
			strategy = EvaluationStrategyManager.getInstance()
					.getEvaluationStrategy(project);
		} catch (FeatureModelNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		// cannot check anything without a strategy
		if (strategy == null)
			return;

		monitor2.worked(10);

		int i = 0;
		for (ITypingCheck check : checks) {
			i++;
			if (i % 25 == 0) {
				monitor2.subTask("Evaluating type check " + i + "/"
						+ checks.size());
				monitor2.worked(25);
			}

			boolean isWelltyped = check.evaluate(strategy);

			if (!isWelltyped)
				markIlltyped(check);
			else
				markWelltyped(check);

		}
		
		monitor2.done();
	}

	private void markWelltyped(ITypingCheck check) {
		// remove marker in case one exists (can happen during reevaluation)
		assert check.getFile() != null;
		if (markerIds.containsKey(check)) {
			long markerId = markerIds.remove(check);
			IMarker marker = check.getFile().getResource().getMarker(markerId);
			if (marker.exists())
				try {
					marker.delete();
				} catch (CoreException e) {
					e.printStackTrace();
				}
		}
	}

	private void markIlltyped(ITypingCheck check) {

		try {
			if (markerIds.containsKey(check)) {
				long markerId = markerIds.get(check);
				IMarker marker = check.getFile().getResource().getMarker(
						markerId);
				if (marker.exists()) {
					new TypingMarkerFactory().updateErrorMarker(marker, check);
					return;
				}
			}
			IMarker marker = new TypingMarkerFactory().createErrorMarker(check);
			markerIds.put(check, marker.getId());
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

	/**
	 * do not call from within a job
	 */
	protected void reevaluateProjectChecks(final IProject project) {
		final HashSet<ITypingCheck> checks = new HashSet<ITypingCheck>(
				knownChecks);
		WorkspaceJob op = new WorkspaceJob("Reevaluate Typing") {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				List<ITypingProvider> typingProviders = TypingExtensionManager
						.getInstance().getTypingProviders(project);
				for (ITypingProvider typingProvider : typingProviders) {
					typingProvider.prepareReevaluationAll(monitor);
				}

				// TODO currently pretty inefficient. should store association
				// of checks to projects or files more directly
				LinkedList<ITypingCheck> toCheck = new LinkedList<ITypingCheck>();
				for (ITypingCheck check : checks) {
					if (check.getFile().getResource().getProject() == project)
						toCheck.add(check);
				}
				evaluateChecks(toCheck, project, monitor);

				return Status.OK_STATUS;
			}
		};
		op.setUser(true);
		op.schedule();
	}

	protected void clearEvaluationStrategyCache(IProject project) {
		IEvaluationStrategy strategy;
		try {
			IFeatureModel featureModel = FeatureModelManager.getInstance()
					.getFeatureModel(project);
			strategy = EvaluationStrategyManager.getInstance()
					.getEvaluationStrategy(project);
			strategy.clearCache(featureModel);
		} catch (FeatureModelNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
	}

	/**
	 * do not call from within a job
	 */
	protected void reevaluateFileChecks(final Set<ColoredSourceFile> files) {
		final HashSet<ITypingCheck> checks = new HashSet<ITypingCheck>(
				knownChecks);
		WorkspaceJob op = new WorkspaceJob("Reevaluate Typing") {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {

				Map<IProject, Collection<ColoredSourceFile>> groupedFiles = groupByProject(files);
				for (Entry<IProject, Collection<ColoredSourceFile>> fileGroup : groupedFiles
						.entrySet()) {

					List<ITypingProvider> typingProviders = TypingExtensionManager
							.getInstance().getTypingProviders(
									fileGroup.getKey());
					for (ITypingProvider typingProvider : typingProviders) {
						typingProvider.prepareReevaluation(
								fileGroup.getValue(), monitor);
					}

				}
				// TODO currently pretty inefficient. should store
				// association
				// of checks to projects or files more directly
				for (ITypingCheck check : checks) {
					if (files.contains(check.getFile()))
						evaluateChecks(Collections.singleton(check), check
								.getFile().getResource().getProject(), monitor);
				}
				return Status.OK_STATUS;
			}
		};
		op.setUser(true);
		op.schedule();
	}

	private final HashMap<ITypingCheck, Long> markerIds = new HashMap<ITypingCheck, Long>();

	/**
	 * called from within a job
	 */
	public void removeObsoleteErrors(Collection<ITypingCheck> obsoleteChecks) {
		// called from within a job!
		for (ITypingCheck check : obsoleteChecks)
			markWelltyped(check);

	}

	/**
	 * do not call from within a job
	 */
	public void recheckFiles(List<ColoredSourceFile> files) {
		HashMap<IProject, List<ColoredSourceFile>> orderedFiles = new HashMap<IProject, List<ColoredSourceFile>>();
		for (ColoredSourceFile file : files) {
			List<ColoredSourceFile> projectFiles = orderedFiles.get(file
					.getResource().getProject());
			if (projectFiles == null) {
				projectFiles = new ArrayList<ColoredSourceFile>();
				orderedFiles.put(file.getResource().getProject(), projectFiles);
			}
			projectFiles.add(file);
		}

		for (Entry<IProject, List<ColoredSourceFile>> fileSet : orderedFiles
				.entrySet()) {
			WorkspaceJob op = new TypecheckFilesJob(fileSet.getKey(), fileSet
					.getValue(), this);
			op.setUser(true);
			op.schedule();
		}
	}

	/**
	 * do not call from within a job
	 */
	public void recheckProjects(IProject[] projects) {
		WorkspaceJob op = new TypecheckProjectJob(projects, this);
		op.setUser(true);
		op.schedule();
	}

	protected static Map<IProject, Collection<ColoredSourceFile>> groupByProject(
			Collection<ColoredSourceFile> files) {
		Map<IProject, Collection<ColoredSourceFile>> result = new HashMap<IProject, Collection<ColoredSourceFile>>();
		for (ColoredSourceFile file : files) {
			IProject project = file.getProject();
			Collection<ColoredSourceFile> projectFiles = result.get(project);
			if (projectFiles == null) {
				projectFiles = new HashSet<ColoredSourceFile>();
				result.put(project, projectFiles);
			}
			projectFiles.add(file);
		}
		return result;
	}

	/**
	 * do not call outside this plugin. this method is only used to access
	 * cached checks to marker resolutions
	 */
	public Set<ITypingCheck> getKnownChecks() {
		return knownChecks;
	}

}
