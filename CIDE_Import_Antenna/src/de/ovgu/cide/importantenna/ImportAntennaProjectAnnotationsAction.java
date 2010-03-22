package de.ovgu.cide.importantenna;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;

import de.ovgu.cide.utils.AbstractCIDEProjectAction;

/**
 * loads all annotations that are specified in antenna format. this is not
 * complete and may not see many elements and special cases (like negation or
 * constructed expressions)
 * 
 * @author ckaestne
 * 
 */
public class ImportAntennaProjectAnnotationsAction extends
		AbstractCIDEProjectAction {

	public void run(IAction action) {
		final IProject[] p;
		if (resources.isEmpty())
			p = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		else
			p = resources.toArray(new IProject[resources.size()]);

		WorkspaceJob op = new WorkspaceJob("Import from antenna") {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				for (IProject pp : p)

					try {
						if (pp.exists() && pp.isOpen())
							pp.getFolder("src").accept(
									new CIDEAnnotationParser(monitor));
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return Status.OK_STATUS;
			}
		};
		op.setUser(true);
		op.schedule();
	}

}