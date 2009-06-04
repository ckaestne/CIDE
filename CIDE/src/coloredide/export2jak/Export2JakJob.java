package coloredide.export2jak;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

import coloredide.export.BaseExportJob;
import coloredide.export.DerivativeComparator;
import coloredide.features.Feature;

public class Export2JakJob extends BaseExportJob {

	/**
	 * can be used during testing. all files are not exported until a file with
	 * this name is found
	 */
	public String DEBUG_SKIPTO = null;

	public Export2JakJob(String title, IProject sourceProject,
			IProject targetProject) {
		super(title, sourceProject, targetProject);
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
			List<Set<Feature>> refactoringOrder) throws CoreException {
		IFile equationFile = targetProject.getFile("project.equation");
		String equationStr = "base\n";
		for (int idx = refactoringOrder.size() - 1; idx >= 0; idx--) {
			Set<Feature> d = refactoringOrder.get(idx);
			if (d.size() > 0) {
				if (d.size() > 1
						&& JakExportOptions.DERIVATIVES_IN_SUBDIRECTORIES)
					equationStr += "derivatives/";
				equationStr += getDerivativeName(d) + "\n";
			}
		}
		InputStream equationContent = new ByteArrayInputStream(equationStr
				.getBytes());
		createFile(equationFile, equationContent, monitor);
	}

	private void createModelFile(IProgressMonitor monitor,
			List<Set<Feature>> refactoringOrder) throws CoreException {
		IFile modelFile = targetProject.getFile("model.m");
		String declStr = "Model extension...\n";
		declStr += "DERIVATIVES:\n";
		for (int idx = 0; idx < refactoringOrder.size(); idx++) {
			Set<Feature> d = refactoringOrder.get(idx);
			if (d.size() > 1) {
				if (idx == 0)
					declStr += "    ";
				else
					declStr += "  | ";
				declStr += getDerivativeName(d) + "\n";
			}
		}

		declStr += "\n\n%%\n\n\n";
		for (int idx = 0; idx < refactoringOrder.size(); idx++) {
			Set<Feature> d = refactoringOrder.get(idx);
			if (d.size() > 1) {
				declStr += getDerivativeName(d) + " iff (";
				for (Iterator<Feature> featureiter = d.iterator(); featureiter
						.hasNext();) {
					declStr += featureName2LayerName(featureiter.next().getShortName(sourceProject));
					if (featureiter.hasNext())
						declStr += " and ";
				}
				declStr+=");\n";
			}
		}

		InputStream equationContent = new ByteArrayInputStream(declStr
				.getBytes());
		createFile(modelFile, equationContent, monitor);
	}

	@Override
	protected void finishExport(IProgressMonitor monitor) throws CoreException {
		List<Set<Feature>> refactoringOrder = new ArrayList<Set<Feature>>(
				seenDerivatives);
		Collections.sort(refactoringOrder, new DerivativeComparator());
		createEquationFile(monitor, refactoringOrder);
		createModelFile(monitor, refactoringOrder);
		super.finishExport(monitor);
	}

	@Override
	protected ExportJavaFileJob createExportJavaFileJob(IContainer folder,
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
