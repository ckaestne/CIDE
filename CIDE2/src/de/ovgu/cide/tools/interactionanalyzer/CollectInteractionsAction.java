package de.ovgu.cide.tools.interactionanalyzer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.ui.IViewPart;

import de.ovgu.cide.features.source.ColoredSourceFileIteratorAction;

public class CollectInteractionsAction extends ColoredSourceFileIteratorAction {

	protected WorkspaceJob createJob(IProject[] p) {
		IViewPart view;
		try {
			view = part.getSite().getPage().showView(
					InteractionsView.VIEW_ID);
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
