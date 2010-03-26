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

package coloredide.configuration;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class WizardPageCreateProject extends WizardPage {

	public WizardPageCreateProject(String pageName, IProject p) {
		super(pageName);
		this.setTitle("Define Target Project");
		this.setPageComplete(false);
		sourceProject=p;
	}

	IProject sourceProject;

	public Text projectName;

	private void checkProjectName() {
		if (projectName.getText().equals(sourceProject.getName())) {
			WizardPageCreateProject.this.setMessage(
					"Cannot overwrite the source project.", DialogPage.ERROR);
			WizardPageCreateProject.this.setPageComplete(false);
		} else if (projectName.getText().equals("")) {
			WizardPageCreateProject.this.setMessage(
					"Project name cannot be empty.", DialogPage.ERROR);
			WizardPageCreateProject.this.setPageComplete(false);
		} else {
			IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName.getText());
			if (project.exists())
				WizardPageCreateProject.this.setMessage("Project "
						+ projectName.getText()
						+ " already exists. Will be overwritten.",
						DialogPage.WARNING);
			else
				WizardPageCreateProject.this.setMessage("");
			WizardPageCreateProject.this.setPageComplete(true);
		}
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Project name:");

		projectName = new Text(composite, SWT.BORDER);
		projectName.setText("");
		projectName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				checkProjectName();
			}
		});

		Button button = new Button(composite, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {

			}
		});
		button.setEnabled(false);

		FormLayout layout = new FormLayout();
		layout.marginHeight = layout.marginWidth = 5;
		composite.setLayout(layout);

		FormData data = new FormData();
		data.top = new FormAttachment(label, 5);
		data.left = new FormAttachment(0, 0);
		data.width=200;
		projectName.setLayoutData(data);

		data = new FormData();
		data.right = new FormAttachment(100, 0);
		data.left = new FormAttachment(projectName,5);
		data.top = new FormAttachment(label, 5);
		data.width = 40;
		button.setLayoutData(data);

		this.setControl(composite);
	}

}
