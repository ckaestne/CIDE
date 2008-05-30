package coloredide.export2jak.validator;

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

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class CheckColorsForJakExportAction implements IWorkbenchWindowActionDelegate,
		IObjectActionDelegate {
	private final List<IProject> projects = new ArrayList<IProject>();

	/**
	 * The constructor.
	 */
	public CheckColorsForJakExportAction() {
	}

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
		WorkspaceJob op = new JakColorCheckerJob(p);
		op.setUser(true);
		op.schedule();
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
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

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}
}