package coloredide.export2pp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;

import coloredide.export.BaseExportJob;
import coloredide.export.DerivativeComparator;
import coloredide.features.Feature;

public class Export2PPJob extends BaseExportJob {

	public Export2PPJob(String title, IProject sourceProject,
			IProject targetProject) {
		super(title, sourceProject, targetProject);
	}

	@Override
	protected void createProject(IProgressMonitor monitor) throws CoreException {

		if (targetProject.exists())
			targetProject.delete(true, monitor);

		// TODO: create project with Java nature
		// IProjectDescription projDesc = targetProject.getWorkspace()
		// .newProjectDescription(targetProject.getName());
		// ICommand buildcomm = projDesc.newCommand();
		// buildcomm.setBuilderName("fos.aheadjakbuilder");
		// ICommand[] buildspecs = { buildcomm };
		// projDesc.setBuildSpec(buildspecs);

		targetProject.create(/* projDesc, */monitor);

		targetProject.open(monitor);
	}

	@Override
	protected ExportPPJavaFileJob createExportJavaFileJob(IContainer folder,
			IFile file, ICompilationUnit compUnit, IProgressMonitor monitor) {
		return new ExportPPJavaFileJob(folder, file, compUnit, monitor, this);
	}

	protected void createFeatureDirectories(IProgressMonitor monitor)
			throws CoreException {
		baseFolder = targetProject.getFolder("java");
		createFolder(baseFolder, monitor);
	}
}
