package de.ovgu.cide.typing.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;

import de.ovgu.cide.typing.CIDETypingPlugin;
import de.ovgu.cide.utils.AbstractCIDEProjectAction;

public class ForceValidationAction extends AbstractCIDEProjectAction {

	public void run(IAction action) {
		IProject[] p;
		if (resources.isEmpty())
			p = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		else
			p = resources.toArray(new IProject[resources.size()]);

		// if (activeEditor != null)
		// activeEditor.doSave(null);

		CIDETypingPlugin.getDefault().getTypingManager().recheckProjects(p);
	}

}