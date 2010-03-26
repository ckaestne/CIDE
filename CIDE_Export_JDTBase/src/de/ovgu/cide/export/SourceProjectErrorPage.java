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

package de.ovgu.cide.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * todo: should be replaced by a select-project dialog
 * 
 * @author ckaestne
 * 
 */
public class SourceProjectErrorPage extends WizardPage {

	private IProject[] source;

	public SourceProjectErrorPage(IProject[] sourceProject) {
		super("Error");
		this.source = sourceProject;
	}

	public void createControl(Composite parent) {

		setTitle("Cannot export");
		if (source == null || source.length == 0)
			setErrorMessage("Select a project to export");
		else if (source.length > 1)
			setErrorMessage("Select only a single project to export");
		else
			assert false;
		setPageComplete(false);
		setControl(parent);
	}
}
