package coloredide.validator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

abstract public class ColorSourceFileIteratorAction implements
		IWorkbenchWindowActionDelegate, IObjectActionDelegate {

	private final List<IProject> projects = new ArrayList<IProject>();
	protected IWorkbenchPart part;

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		IProject[] p;
		if (projects.isEmpty())
			p = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		else
			p = projects.toArray(new IProject[projects.size()]);
		WorkspaceJob op = createJob(p);
		op.setUser(true);
		op.schedule();
	}

	protected abstract WorkspaceJob createJob(IProject[] p);

	public void selectionChanged(IAction action, ISelection selection) {
		projects.clear();
		if (selection instanceof IStructuredSelection) {
			for (Object selected : ((IStructuredSelection) selection).toArray()) {
				if (selected instanceof IProject) {
					projects.add((IProject) selected);
				}
				if (selected instanceof IJavaProject) {
					projects.add(((IJavaProject) selected).getProject());
				}

			}
		}
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part=targetPart;
	}

}