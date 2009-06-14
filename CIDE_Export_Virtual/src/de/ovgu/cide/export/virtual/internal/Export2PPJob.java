package de.ovgu.cide.export.virtual.internal;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.features.FeatureModelNotFoundException;

public class Export2PPJob extends BaseExportJob {

	private IPPExportOptions options;

	public Export2PPJob(String title, IProject sourceProject,
			IProject targetProject, IPPExportOptions options)
			throws FeatureModelNotFoundException {
		super(title, sourceProject, targetProject);
		this.options = options;
	}

	@Override
	protected void createProject(IProgressMonitor monitor) throws CoreException {

		if (targetProject.exists())
			targetProject.delete(true, monitor);

		targetProject.create(/* projDesc, */monitor);

		targetProject.open(monitor);
	}

	@Override
	protected ExportPPJavaFileJob createExportJavaFileJob(IContainer folder,
			IFile file, ICompilationUnit compUnit, IProgressMonitor monitor) {
		return new ExportPPJavaFileJob(folder, file, compUnit, monitor, this,
				options);
	}

	protected void createFeatureDirectories(IProgressMonitor monitor)
			throws CoreException {
		baseFolder = targetProject.getFolder("java");
		createFolder(baseFolder, monitor);
	}
}
