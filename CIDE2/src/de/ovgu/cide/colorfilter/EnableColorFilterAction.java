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

package de.ovgu.cide.colorfilter;

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

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.tools.featureview.ProjectionKindManager;
import de.ovgu.cide.tools.featureview.ProjectionKindManager.ProjectionKind;

public class EnableColorFilterAction extends Action implements
		IViewActionDelegate {

	private StructuredViewer viewer = null;
	private Map<StructuredViewer, List<ViewerFilter>> previousFilters = new HashMap<StructuredViewer, List<ViewerFilter>>();
	private ViewerFilter projectionVariantFilter = new ColorFilterVariant();
	private ViewerFilter projectionFeatureFilter = new ColorFilterFeature();
	private ColorFilterUpdater updater = null;

	public void init(IViewPart view) {
		if (view instanceof ResourceNavigator) {
			viewer = ((ResourceNavigator) view).getTreeViewer();
			updater = new ColorFilterUpdater(this, viewer);
		}
		if (view instanceof CommonNavigator) {
			viewer = ((CommonNavigator) view).getCommonViewer();
			updater = new ColorFilterUpdater(this, viewer);
		}

	}

	public void run(IAction action) {
		setChecked(action.isChecked());

		updateFilter(isChecked(), ProjectionKindManager.getInstance()
				.getProjectionKind());
	}

	void updateFilter(boolean install, ProjectionKind projectionKind) {
		if (viewer == null)
			return;
		try {
			viewer.getControl().setRedraw(false);
			previousFilters.put(viewer, Arrays.asList(viewer.getFilters()));

			if (install) {
				if (projectionKind == ProjectionKind.VARIANT) {
					viewer.removeFilter(projectionFeatureFilter);
					viewer.addFilter(projectionVariantFilter);
				} else {
					viewer.removeFilter(projectionVariantFilter);
					viewer.addFilter(projectionFeatureFilter);
				}
				CIDECorePlugin.getDefault().removeColorChangeListener(updater);
				CIDECorePlugin.getDefault().addColorChangeListener(updater);
			} else {
				viewer.removeFilter(projectionVariantFilter);
				viewer.removeFilter(projectionFeatureFilter);
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
