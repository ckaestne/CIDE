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

import featureide_ui.editors.GrammarEditor;

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
					GrammarEditor.ID);
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
