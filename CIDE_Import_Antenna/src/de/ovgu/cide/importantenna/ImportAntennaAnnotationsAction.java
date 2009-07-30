package de.ovgu.cide.importantenna;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
public class ImportAntennaAnnotationsAction implements
		IWorkbenchWindowActionDelegate, IObjectActionDelegate,
		IEditorActionDelegate {
	final List<IProject> projects = new ArrayList<IProject>();
	private ColoredTextEditor activeEditor;

	public void run(IAction action) {
		final IProject[] p;
		if (projects.isEmpty())
			p = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		else
			p = projects.toArray(new IProject[projects.size()]);

		WorkspaceJob op = new WorkspaceJob("Import from antenna") {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				for (IProject pp : p)

					try {
						if (pp.exists() && pp.isOpen())
							pp.getFolder("src").accept(new CIDEAnnotationParser(monitor));
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

	public List<IProject> getProjects() {
		return projects;
	}

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
				if (selected instanceof IProject) {
					if (!alreadyCleared) {
						activeEditor = null;
						projects.clear();
						alreadyCleared = true;
					}
					projects.add((IProject) selected);
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
				projects.clear();
				projects.add(sourceFile.getProject());
			}
		}
	}
}