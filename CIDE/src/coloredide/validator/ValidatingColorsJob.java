/**
 * 
 */
package coloredide.validator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;

import coloredide.ColoredIDEPlugin;
import coloredide.features.source.IColoredJavaSourceFile;

class ValidatingColorsJob extends ColoredSourceFileIteratorJob {

	public ValidatingColorsJob(IProject[] projects) {
		super(projects,"Validating Colors", "Validating");
	}

	public ValidatingColorsJob(IProject project) {
		this(new IProject[] { project });
	}

	protected void processSource(IColoredJavaSourceFile source)
			throws JavaModelException, CoreException {
		ColoredIDEPlugin.getDefault().getValidator().runValidation(source);
	}

	@Override
	protected void cleanProject(IJavaProject project, IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return;

		project.getResource().deleteMarkers(ColorProblem.TYPEID, true,
				IResource.DEPTH_INFINITE);
		monitor.worked(5);
	}
}