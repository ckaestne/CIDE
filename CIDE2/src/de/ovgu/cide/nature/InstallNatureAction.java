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

package de.ovgu.cide.nature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;

import de.ovgu.cide.utils.AbstractCIDEProjectAction;

public class InstallNatureAction extends AbstractCIDEProjectAction implements
		IObjectActionDelegate {
	public void run(IAction action) {
		for (IProject project : resources)
			try {
				System.out
						.println("Installing nature for " + project.getName());
				IProjectDescription description = project.getDescription();
				List<String> natures = new ArrayList<String>(Arrays
						.asList(description.getNatureIds()));
				if (!natures.contains(CIDEProjectNature.NATURE_ID)) {
					natures.add(CIDEProjectNature.NATURE_ID);
					description.setNatureIds(natures.toArray(new String[natures
							.size()]));
					project.setDescription(description, null);
				}

			} catch (CoreException e) {
				e.printStackTrace();
				// Something went wrong
			}
	}

}
