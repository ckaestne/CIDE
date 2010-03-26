/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.configuration;

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

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;

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

//	private IProject getSelectedJavaProject() {
//		if (selection instanceof IStructuredSelection) {
//			Object selected = ((IStructuredSelection) selection)
//					.getFirstElement();
//			if (selected instanceof IProject)
//				return (IProject) selected;
//		}
//		return null;
//	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
