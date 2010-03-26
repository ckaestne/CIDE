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

package de.ovgu.cide.fm.guidsl;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import featureide.fm.ui.editors.FeatureModelEditor;

public class EditFeatureModelAction implements IObjectActionDelegate {

	private ISelection selection;
	private IWorkbenchPart part;

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Shell shell = new Shell();

		assert part != null;
		IProject project = getSelectedProject();
		if (project == null) {
			MessageDialog
					.openInformation(shell, "CIDE", "No project selected.");
			return;
		}

		try {
			IFile modelFile = project.getFile("model.m");
			if (!modelFile.exists()) {
				modelFile.create(new ByteArrayInputStream(
						"Project : [Feature1] [Feature2] :: _Project ;".getBytes()), true,
						null);
			}

			IDE.openEditor(part.getSite().getPage(), modelFile,
					FeatureModelEditor.ID);
		} catch (PartInitException e) {
			MessageDialog.openInformation(shell, "CIDE",
					"Error opening model.m file.");
		} catch (CoreException e) {
			MessageDialog.openInformation(shell, "CIDE",
					"Error opening model.m file.");
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
