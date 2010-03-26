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

package de.ovgu.cide.importantenna;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import de.ovgu.cide.editor.ColoredTextEditor;
import de.ovgu.cide.features.source.ColoredSourceFile;

/**
 * loads all annotations that are specified in antenna format. this is not
 * complete and may not see many elements and special cases (like negation or
 * constructed expressions)
 * 
 * @author ckaestne
 * 
 */
public class ImportAntennaFileAnnotationsAction implements
		IWorkbenchWindowActionDelegate, IObjectActionDelegate,
		IEditorActionDelegate {
	final List<IFile> files = new ArrayList<IFile>();
	private ColoredTextEditor activeEditor;

	public void run(IAction action) {
		final IFile[] p;
		if (files.isEmpty()) {
			System.out.println("no files selected");
			return;
		} else
			p = files.toArray(new IFile[files.size()]);

		WorkspaceJob op = new WorkspaceJob("Import from antenna") {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				for (IFile pp : p)

					try {
						if (pp.exists())
							pp.accept(new CIDEAnnotationParser(monitor));
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

	//
	// public List<IProject> getProjects() {
	// return files;
	// }

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			boolean alreadyCleared = false;
			for (Object selected : ((IStructuredSelection) selection).toArray()) {
				if (selected instanceof IFile) {
					if (!alreadyCleared) {
						activeEditor = null;
						files.clear();
						alreadyCleared = true;
					}
					files.add((IFile) selected);
				}
				if (selected instanceof ICompilationUnit) {
					if (!alreadyCleared) {
						activeEditor = null;
						files.clear();
						alreadyCleared = true;
					}
					files.add((IFile) ((ICompilationUnit) selected)
							.getResource());
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
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		if (targetEditor instanceof ColoredTextEditor) {
			ColoredSourceFile sourceFile = ((ColoredTextEditor) targetEditor)
					.getSourceFile();
			if (sourceFile != null) {
				activeEditor = (ColoredTextEditor) targetEditor;
				files.clear();
				files.add(sourceFile.getResource());
			}
		}
	}
}