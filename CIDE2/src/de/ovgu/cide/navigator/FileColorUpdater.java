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

package de.ovgu.cide.navigator;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.FileColorChangedEvent;
import de.ovgu.cide.IColorChangeListener;

/**
 * listens to changes to the .dircolor files and publishes them as
 * FileColorChangedEvent events (which are used to update the colors and filters
 * in the view)
 * 
 * @author ckaestne
 * 
 */
public class FileColorUpdater implements IColorChangeListener {

	public void astColorChanged(ASTColorChangedEvent event) {
		// ignore, does not affect resource view
	}

	public void fileColorChanged(FileColorChangedEvent event) {
		refresh(event.getAffectedFolders());
	}

	/**
	 * 
	 * @param items
	 *            null-> draw all
	 */
	private void refresh(final Collection<? extends IResource> items) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {

				IWorkbench wb = PlatformUI.getWorkbench();
				IWorkbenchWindow ww = wb.getActiveWorkbenchWindow();
				IWorkbenchPage ap = ww.getActivePage();
				IViewPart view = ap.findView(ProjectExplorer.VIEW_ID);
				if (view instanceof CommonNavigator) {
					CommonViewer viewer = ((CommonNavigator) view)
							.getCommonViewer();
					try {
						viewer.getControl().setRedraw(false);
						if (items == null)
							viewer.refresh(true);
						else
							for (IResource folder : items)
								viewer.refresh(folder, true);
					} finally {
						viewer.getControl().setRedraw(true);
					}
				}
			}
		});
	}

	public void colorListChanged(ColorListChangedEvent event) {
		if (event.anyChangeOf(ChangeType.COLOR))
			refresh(Collections.singleton(event.getProject()));
	}

}
