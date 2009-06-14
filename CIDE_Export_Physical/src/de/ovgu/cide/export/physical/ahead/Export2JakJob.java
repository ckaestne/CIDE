package de.ovgu.cide.export.physical.ahead;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;

import de.ovgu.cide.export.BaseJavaFileExporter;
import de.ovgu.cide.export.physical.internal.BasePhysicalExportJob;
import de.ovgu.cide.export.physical.internal.DerivativeComparator;
import de.ovgu.cide.export.physical.internal.IPhysicalOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;

public class Export2JakJob extends BasePhysicalExportJob<IPhysicalOptions> {

	/**
	 * can be used during testing. all files are not exported until a file with
	 * this name is found
	 */
	public String DEBUG_SKIPTO = null;

	public Export2JakJob(String title, IProject sourceProject,
			IProject targetProject, IPhysicalOptions options)
			throws FeatureModelNotFoundException {
		super(title, sourceProject, targetProject, options);
	}

	@Override
	protected void createProject(IProgressMonitor monitor) throws CoreException {

		if (targetProject.exists())
			targetProject.delete(true, monitor);

		// create project with FeatureIDE builder, just in case
		IProjectDescription projDesc = targetProject.getWorkspace()
				.newProjectDescription(targetProject.getName());
		ICommand buildcomm = projDesc.newCommand();
		buildcomm.setBuilderName("fos.aheadjakbuilder");
		ICommand[] buildspecs = { buildcomm };
		projDesc.setBuildSpec(buildspecs);

		targetProject.create(projDesc, monitor);

		targetProject.open(monitor);
	}

	/**
	 * creates an equation file in the project with all known derivatives to
	 * specify the order
	 * 
	 * @param monitor
	 * @param refactoringOrder
	 * @throws CoreException
	 */
	private void createEquationFile(IProgressMonitor monitor,
			List<Set<IFeature>> refactoringOrder) throws CoreException {
		IFile equationFile = targetProject.getFile("project.equation");
		String equationStr = "base\n";
		for (int idx = refactoringOrder.size() - 1; idx >= 0; idx--) {
			Set<IFeature> d = refactoringOrder.get(idx);
			if (d.size() > 0) {
				if (d.size() > 1
						&& exportOptions.getDerivativesInSubdirectories())
					equationStr += "derivatives/";
				equationStr += getDerivativeName(d) + "\n";
			}
		}
		InputStream equationContent = new ByteArrayInputStream(equationStr
				.getBytes());
		createFile(equationFile, equationContent, monitor);
	}

	@Override
	protected void finishExport(IProgressMonitor monitor) throws CoreException {
		List<Set<IFeature>> refactoringOrder = new ArrayList<Set<IFeature>>(
				seenDerivatives);
		Collections.sort(refactoringOrder, new DerivativeComparator());
		createEquationFile(monitor, refactoringOrder);
		super.finishExport(monitor);
	}

	@Override
	protected BaseJavaFileExporter createExportJavaFileJob(IContainer folder,
			IFile file, ICompilationUnit compUnit, IProgressMonitor monitor) {
		// DEBUG only
		if (DEBUG_SKIPTO != null) {
			if (DEBUG_SKIPTO.equals(file.getName()))
				DEBUG_SKIPTO = null;
			else
				return new ExportJavaFileJob(folder, file, compUnit, monitor,
						this) {
					@Override
					public void execute() throws CoreException {
						System.out.println("Skipping " + file);
					}
				};
		}
		// end DEBUG
		return new ExportJavaFileJob(folder, file, compUnit, monitor, this);
	}

}
