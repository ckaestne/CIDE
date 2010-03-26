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

package de.ovgu.cide.features.source;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.FileColorChangedEvent;

/**
 * listens to changes to the .dircolor files and publishes them as
 * FileColorChangedEvent events (which are used to update the colors and filters
 * in the view)
 * 
 * hack that works only with the default color provider. TODO find a better
 * solution
 * 
 * @author ckaestne
 * 
 */
public class FileColorChangeListener implements IResourceChangeListener {

	public void resourceChanged(IResourceChangeEvent event) {
		try {
			IResourceDelta delta = event.getDelta();
			if (delta != null) {
				ChangedColorFinder f = new ChangedColorFinder();
				delta.accept(f);
				if (f.folders.size() > 0)
					CIDECorePlugin.getDefault().notifyListeners(
							new FileColorChangedEvent(this, f.folders));
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private class ChangedColorFinder implements IResourceDeltaVisitor {

		List<IContainer> folders = new ArrayList<IContainer>();

		public boolean visit(IResourceDelta delta) throws CoreException {
			if (((delta.getKind() & (IResourceDelta.ADDED | IResourceDelta.REMOVED)) > 0)
					|| ((delta.getFlags() & (IResourceDelta.CONTENT)) > 0))
				if (delta.getResource().getName().equals(
						".dircolors")) {
					IContainer updatedFolder = delta.getResource().getParent();
					if (updatedFolder != null)
						folders.add(updatedFolder);
				}
			return true;
		}

	}

}
