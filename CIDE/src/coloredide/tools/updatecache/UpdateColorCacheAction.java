package coloredide.tools.updatecache;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;

import coloredide.validator.ColorSourceFileIteratorAction;

public class UpdateColorCacheAction extends ColorSourceFileIteratorAction {

	protected WorkspaceJob createJob(IProject[] p) {
				return new UpdateColorCacheJob(p);
	}

}
