package coloredide.tools.interactionanalyzer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.ui.IViewPart;

import coloredide.validator.ColorSourceFileIteratorAction;

public class CollectInteractionsAction extends ColorSourceFileIteratorAction {

	protected WorkspaceJob createJob(IProject[] p) {
		IViewPart view;
		try {
			view = part.getSite().getPage().showView(
					"coloredide.InteractionsView");
			if (view instanceof InteractionsView)
				return new CollectInteractionsJob(p[0],
						((InteractionsView) view).tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
