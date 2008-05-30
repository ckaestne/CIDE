package coloredide.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ExportAction implements IObjectActionDelegate {

	private ISelection selection;

//	private IWorkbenchPart part;

	/**
	 * Constructor for Action1.
	 */
	public ExportAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
//		this.part = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Shell shell = new Shell();

		IJavaProject jproject = getSelectedJavaProject();
		if (jproject != null) {
			IProject project = getSelectedProject();
			if (project != null) {
				jproject = JavaCore.create(project);
			}
		}
		if (jproject != null) {
			ExportTargetWizard wizard = new ExportTargetWizard(jproject
					.getProject(), ExportFactoryProvider.getFactories());
			WizardDialog dialog = new WizardDialog(shell, wizard);
			dialog.create();
			dialog.open();
		} else {
			MessageDialog.openInformation(shell, "ColoredIDE",
					"No Java project selected.");
		}
	}

	private IProject getSelectedProject() {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof IProject)
				return (IProject) selected;
		}
		return null;
	}

	private IJavaProject getSelectedJavaProject() {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof IJavaProject)
				return (IJavaProject) selected;
		}
		return null;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
