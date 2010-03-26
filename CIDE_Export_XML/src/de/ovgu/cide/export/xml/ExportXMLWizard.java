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

package de.ovgu.cide.export.xml;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IExportWizard;

import de.ovgu.cide.export.CIDEExport2Wizard;

public class ExportXMLWizard extends CIDEExport2Wizard implements IExportWizard {

	public ExportXMLWizard() {
		super(null);
	}

	@Override
	public void addPages() {
		super.addPages();
		addPage(new WizardPage("Note") {

			public void createControl(Composite parent) {
				Label label = new Label(parent, SWT.NONE);
				label
						.setText("Creates XML files containing all annotations inside the source project.");
				setControl(label);
			}
		});
	}

	@Override
	protected Job createExportJob(IProject sourceProject, IProject targetProject) {
		return new CreateAnnotationXMLFileJob(sourceProject);
	}

	@Override
	protected IProject getTargetProject() {
		return null;
	}
}
