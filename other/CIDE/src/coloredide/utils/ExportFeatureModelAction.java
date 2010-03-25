package coloredide.utils;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import coloredide.features.GuidslExport;

public class ExportFeatureModelAction implements IObjectActionDelegate{

	private IProject project;

	protected IProject getSelectedProject(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof IProject)
				return (IProject) selected;
			if (selected instanceof IJavaProject)
				return ((IJavaProject) selected).getProject();
		}
		return null;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.project = getSelectedProject(selection);
	}
	
	public void run(IAction action) {
		try {
			new GuidslExport().exportToGuidsl(project);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		
	}
	
	

}
