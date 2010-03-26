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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.IFeatureModel;

public abstract class ColoredSourceFileIteratorJob extends WorkspaceJob {

	protected final IProject[] projects;

	private String jobPrefix;

	private String jobDescription;

	protected boolean colorCacheUpdate = true;

	public ColoredSourceFileIteratorJob(IProject[] projects,
			String jobDescription, String jobPrefix) {
		super(jobDescription);
		this.jobDescription = jobDescription;
		this.jobPrefix = jobPrefix;
		this.projects = projects;
	}

	public ColoredSourceFileIteratorJob(IProject project,
			String jobDescription, String jobPrefix) {
		this(new IProject[] { project }, jobDescription, jobPrefix);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		List<IProject> projects = new ArrayList<IProject>();
		for (IProject project : this.projects) {
			if (project.exists() && project.isOpen() && CIDECorePlugin.isCIDEProject(project))
				projects.add(project);
		}

		monitor.beginTask(jobDescription, projects.size());

		
		for (IProject project : projects) {
			IFeatureModel featureModel = FeatureModelManager.getInstance().getFeatureModelCore(project);
			processProject(project, featureModel,new SubProgressMonitor(monitor, 1));
		}

		finish();

		monitor.done();
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		return Status.OK_STATUS;
	}

	protected void finish() {
	}

	private void processProject(IProject project, IFeatureModel featureModel,
			IProgressMonitor monitor) throws CoreException {
		if (monitor.isCanceled())
			return;

		monitor.beginTask(jobPrefix + " " + project.getProject().getName(),
				countColoredFiles(project));
		monitor.subTask(jobPrefix + " " + project.getProject().getName());
		cleanProject(project, monitor);

		processContainer(project, featureModel, monitor);

		monitor.done();
	}

	protected void processContainer(IContainer container,
			IFeatureModel featureModel, IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return;

		for (IResource resource : container.members()) {
			if (resource instanceof IContainer)
				processContainer((IContainer) resource, featureModel, monitor);
			if (resource instanceof IFile)
				processSourceFile((IFile) resource, featureModel, monitor);
		}
	}

	private void processSourceFile(IFile file, IFeatureModel featureModel,
			IProgressMonitor monitor) throws CoreException {
		if (monitor.isCanceled())
			return;
		if ("color".equals(file.getFileExtension()))
			return;

		ColoredSourceFile source = ColoredSourceFile.getColoredSourceFile(file,
				featureModel);
		if (!source.isColored())
			return;

		monitor.subTask(jobPrefix + " " + file.getName());

		// ISourceFile ast = source.getAST();
		// ColoredIDEPlugin.getDefault().colorCache.updateASTColors(project,
		// ast,
		// source.getColorManager());
		processSource(source);
		monitor.worked(1);
	}

	protected abstract void processSource(ColoredSourceFile source)
			throws CoreException;

	protected void cleanProject(IProject project, IProgressMonitor monitor)
			throws CoreException {
	}

	private int countColoredFiles(IContainer container) throws CoreException {
		int result = 0;
		for (IResource resource : container.members()) {
			if (resource instanceof IContainer)
				countColoredFiles((IContainer) resource);
			if (resource instanceof IFile) {
				IFile file = (IFile) resource;
				if ("color".equals(file.getFileExtension()))
					continue;

				if (ColoredSourceFile.isFileColored(file))
					result++;
			}

		}
		return result;
	}

}