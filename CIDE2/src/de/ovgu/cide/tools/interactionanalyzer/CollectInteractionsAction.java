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
				return new CollectStatisticsAndInteractionsJob(p[0],
						((InteractionsView) view).tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
