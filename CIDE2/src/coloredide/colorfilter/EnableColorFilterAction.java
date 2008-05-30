package coloredide.colorfilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.views.navigator.ResourceNavigator;

import coloredide.CIDECorePlugin;

public class EnableColorFilterAction extends Action implements
		IViewActionDelegate {

	private StructuredViewer viewer = null;
	private Map<StructuredViewer, List<ViewerFilter>> previousFilters = new HashMap<StructuredViewer, List<ViewerFilter>>();
	private ViewerFilter interestFilter = new ColorFilter2();
	private ColorFilterUpdater updater=null;

	public void init(IViewPart view) {
		if (view instanceof ResourceNavigator) {
			viewer = ((ResourceNavigator) view).getTreeViewer();
			updater=new ColorFilterUpdater(this,viewer);
		}
		if (view instanceof CommonNavigator) {
			viewer = ((CommonNavigator) view).getCommonViewer();
			updater=new ColorFilterUpdater(this,viewer);
		}

	}

	public void run(IAction action) {
		setChecked(action.isChecked());

		updateFilter(isChecked());
	}

	private void updateFilter(boolean install) {
		if (viewer == null)
			return;
		try {
			viewer.getControl().setRedraw(false);
			previousFilters.put(viewer, Arrays.asList(viewer.getFilters()));

			// if (viewPart != null && manageFilters) {
			// Set<ViewerFilter> toAdd = new HashSet<ViewerFilter>();
			// // Set<Class<?>> excludedFilters = getPreservedFilterClasses();
			// // for (ViewerFilter filter : previousFilters.get(viewer)) {
			// // if (excludedFilters.contains(filter.getClass())) {
			// // toAdd.add(filter);
			// // }
			// // }
			//
			// toAdd.add(interestFilter);
			// viewer.setFilters(toAdd.toArray(new ViewerFilter[toAdd.size()]));
			// } else {
			if (install){
				viewer.addFilter(interestFilter);
				CIDECorePlugin.getDefault().addColorChangeListener(updater);
			}
			else{
				viewer.removeFilter(interestFilter);
				CIDECorePlugin.getDefault().removeColorChangeListener(updater);
			}
			// }
		} finally {
			viewer.getControl().setRedraw(true);
		}
		// if (viewer instanceof TreeViewer) {
		// ((TreeViewer) viewer).expandAll();
		// }

	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
