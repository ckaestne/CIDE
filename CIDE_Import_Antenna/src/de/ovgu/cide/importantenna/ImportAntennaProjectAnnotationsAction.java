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

package de.ovgu.cide.importantenna;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;

import de.ovgu.cide.utils.AbstractCIDEProjectAction;

/**
 * loads all annotations that are specified in antenna format. this is not
 * complete and may not see many elements and special cases (like negation or
 * constructed expressions)
 * 
 * @author ckaestne
 * 
 */
public class ImportAntennaProjectAnnotationsAction extends
		AbstractCIDEProjectAction {

	public void run(IAction action) {
		final IProject[] p;
		if (resources.isEmpty())
			p = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		else
			p = resources.toArray(new IProject[resources.size()]);

		WorkspaceJob op = new WorkspaceJob("Import from antenna") {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				for (IProject pp : p)

					try {
						if (pp.exists() && pp.isOpen())
							pp.getFolder("src").accept(
									new CIDEAnnotationParser(monitor));
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return Status.OK_STATUS;
			}
		};
		op.setUser(true);
		op.schedule();
	}

}