/**
 * 
 */
package de.ovgu.cide.validator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.features.source.ColoredSourceFile;

class ValidatingColorsJob extends ColoredSourceFileIteratorJob {

	public ValidatingColorsJob(IProject[] projects) {
		super(projects,"Validating Colors", "Validating");
	}

	public ValidatingColorsJob(IProject project) {
		this(new IProject[] { project });
	}

	protected void processSource(ColoredSourceFile source)
			throws CoreException { 
//		CIDECorePlugin.getDefault().getValidator().runValidation(source);
	}

	@Override
	protected void cleanProject(IProject project, IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return;

		project.deleteMarkers(ColorProblem.TYPEID, true,
				IResource.DEPTH_INFINITE);
		monitor.worked(5);
	}
}