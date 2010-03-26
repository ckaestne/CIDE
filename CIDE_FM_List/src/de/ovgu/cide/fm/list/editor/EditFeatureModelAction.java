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

package de.ovgu.cide.fm.list.editor;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class EditFeatureModelAction implements IObjectActionDelegate {

	private ISelection selection;

	// private IWorkbenchPart part;

	/**
	 * Constructor for Action1.
	 */
	public EditFeatureModelAction() {
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

		IProject jproject = getSelectedProject();
		if (jproject != null) {
			FeatureNameDialog dialog = new FeatureNameDialog(shell, jproject
					.getProject());
			dialog.create();
			dialog.open();
		} else {
			MessageDialog.openInformation(shell, "ColoredIDE",
					"No Java project selected.");
		}
	}

	protected IProject getSelectedProject() {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof IProject)
				return (IProject) selected;
		}
		return null;
	}

	//
	// private IProject getSelectedJavaProject() {
	// if (selection instanceof IStructuredSelection) {
	// Object selected = ((IStructuredSelection) selection)
	// .getFirstElement();
	// if (selected instanceof IJavaProject)
	// return (IJavaProject) selected;
	// }
	// return null;
	// }

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
