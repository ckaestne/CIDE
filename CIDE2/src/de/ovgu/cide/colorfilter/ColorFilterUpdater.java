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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Display;

import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.FileColorChangedEvent;
import de.ovgu.cide.IColorChangeListener;

/**
 * listens to color changes and updates the view (including the filter) at the
 * according positions
 * 
 * 
 * @author cKaestner
 * 
 */
public class ColorFilterUpdater implements IColorChangeListener {

	private StructuredViewer viewer;
	private EnableColorFilterAction filterAction;

	ColorFilterUpdater(EnableColorFilterAction action, StructuredViewer viewer) {
		this.viewer = viewer;
		this.filterAction = action;
	}

	public void astColorChanged(ASTColorChangedEvent event) {
		if (filterAction.isChecked())
			update(Collections.singleton(event.getColoredSourceFile()
					.getResource().getParent()));
	}

	public void fileColorChanged(FileColorChangedEvent event) {
		if (filterAction.isChecked()) {
			Set<IContainer> parentFolders = new HashSet<IContainer>();
			for (IContainer container : event.getAffectedFolders())
				parentFolders.add(container.getParent());
			update(parentFolders);
		}
	}

	private void update(final Collection<? extends IResource> resources) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				try {
					viewer.getControl().setRedraw(false);
					for (IResource res : resources)
						if (res != null)
							viewer.refresh(res, false);
				} finally {
					viewer.getControl().setRedraw(true);
				}
			}
		});
	}

	public void colorListChanged(ColorListChangedEvent event) {
		if (filterAction.isChecked()) {
			// colors or feature selection changed?
			if (event.anyChangeOf(ChangeType.COLOR)
					|| event.anyChangeOf(ChangeType.VISIBILITY))
				update(Collections.singleton(event.getProject()));
			// projection changed?
			if (event.getNewProjectionKind() != null)
				filterAction.updateFilter(true, event.getNewProjectionKind());
		}
	}

}
