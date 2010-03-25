package coloredide.validator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.ColoredIDEPlugin;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;

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
		List<IJavaProject> javaProjects = new ArrayList<IJavaProject>();
		for (IProject project : projects) {
			if (!project.exists() || !project.isOpen())
				continue;
			IJavaProject javaProject = JavaCore.create(project);
			if (javaProject != null) {
				javaProjects.add(javaProject);
			}
		}

		monitor.beginTask(jobDescription, javaProjects.size());

		for (IJavaProject project : javaProjects) {
			processProject(project, new SubProgressMonitor(monitor, 1));
		}

		finish();

		monitor.done();
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		return Status.OK_STATUS;
	}

	protected void finish() {
	}

	private void processProject(IJavaProject javaProject,
			IProgressMonitor monitor) throws CoreException {
		if (monitor.isCanceled())
			return;

		monitor.beginTask(jobPrefix + " " + javaProject.getProject().getName(),
				countJavaFiles(javaProject));
		monitor.subTask(jobPrefix + " " + javaProject.getProject().getName());
		cleanProject(javaProject, monitor);

		for (IPackageFragment pkg : javaProject.getPackageFragments()) {
			for (ICompilationUnit compUnit : pkg.getCompilationUnits()) {
				processCompilationUnit(javaProject.getProject(), compUnit,
						monitor);
			}
		}

		monitor.done();
	}

	private void processCompilationUnit(IProject project,
			ICompilationUnit compUnit, IProgressMonitor monitor)
			throws JavaModelException, CoreException {
		if (monitor.isCanceled())
			return;

		String n = "...";
		if (compUnit.getTypes().length > 0)
			n = compUnit.getTypes()[0].getFullyQualifiedName();
		monitor.subTask(jobPrefix + " " + n);
		IColoredJavaSourceFile source = ColoredJavaSourceFile
				.getColoredJavaSourceFile(compUnit);
		CompilationUnit ast = source.getAST();
		ColoredIDEPlugin.getDefault().colorCache.updateASTColors(project, ast,
				source.getColorManager());
		processSource(source);
		monitor.worked(1);
	}

	protected abstract void processSource(IColoredJavaSourceFile source)
			throws JavaModelException, CoreException;

	protected void cleanProject(IJavaProject project, IProgressMonitor monitor)
			throws CoreException {
	}

	private int countJavaFiles(IJavaProject javaProject)
			throws JavaModelException {
		int result = 0;
		for (IPackageFragment pkg : javaProject.getPackageFragments()) {
			result += pkg.getCompilationUnits().length;
		}
		return result;
	}

}