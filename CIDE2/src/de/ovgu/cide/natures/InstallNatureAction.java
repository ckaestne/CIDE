package de.ovgu.cide.natures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.ovgu.cide.nature.CIDEProjectNature;

public class InstallNatureAction implements IObjectActionDelegate {
	private final List<IProject> resources = new ArrayList<IProject>();

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		for (IProject project : resources)
			try {
				System.out
						.println("Installing nature for " + project.getName());
				IProjectDescription description = project.getDescription();
				// String[] natures = description.getNatureIds();
				// String[] newNatures = new String[natures.length + 1];
				// System.arraycopy(natures, 0, newNatures, 0, natures.length);
				// newNatures[natures.length] = CIDEProjectNature.NATURE_ID;
				// description.setNatureIds(newNatures);
				// project.setDescription(description, null);
				List<String> natures = new ArrayList<String>(Arrays
						.asList(description.getNatureIds()));
				if (!natures.contains(CIDEProjectNature.NATURE_ID)) {
					natures.add(CIDEProjectNature.NATURE_ID);
					description.setNatureIds(natures.toArray(new String[natures
							.size()]));
					project.setDescription(description, null);
				}

			} catch (CoreException e) {
				e.printStackTrace();
				// Something went wrong
			}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		resources.clear();
		if (selection instanceof IStructuredSelection) {
			for (Object selected : ((IStructuredSelection) selection).toArray()) {
				if (selected instanceof IProject) {
					resources.add((IProject) selected);
				}

			}
		}
	}

}
