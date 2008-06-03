package coloredide.configuration;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import coloredide.features.FeatureModelManager;
import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeatureModel;

public class CreateConfigurationAction implements IObjectActionDelegate {

	private ISelection selection;

	// private IWorkbenchPart part;

	/**
	 * Constructor for Action1.
	 */
	public CreateConfigurationAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// this.part = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Shell shell = new Shell();

		IProject project = getSelectedProject();
		if (project != null) {
			try {
				IFeatureModel fm = FeatureModelManager.getInstance()
						.getFeatureModel(project);
				WizardCreateConfiguration wizard = new WizardCreateConfiguration(
						project, fm);
				WizardDialog dialog = new WizardDialog(shell, wizard);
				dialog.create();
				dialog.open();
			} catch (FeatureModelNotFoundException e) {
				MessageDialog.openInformation(shell, "CIDE",
						"Could not instanciate feature model.");
			}
		} else {
			MessageDialog
					.openInformation(shell, "CIDE", "No project selected.");
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

	private IProject getSelectedJavaProject() {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof IProject)
				return (IProject) selected;
		}
		return null;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
