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

package de.ovgu.cide.importjak;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import de.ovgu.cide.configuration.WizardPageCreateProject;
import de.ovgu.cide.importjak.featurehouseextension.GuidslFileLoader;
import featureide.fm.core.FeatureModel;
import featureide.fm.core.io.UnsupportedModelException;
import featureide.fm.core.io.guidsl.FeatureModelReader;

public class ImportJakWizard extends Wizard implements IImportWizard {

	public class SelectSourceProjectPage extends WizardPage implements
			IWizardPage {

		private Label pathLabel;
		private Text pathEdit;
		private Button pathButton;
		private List featureList;

		protected SelectSourceProjectPage(String pageName) {
			super(pageName);

			setTitle("Select AHEAD/FeatureHouse Project");
		}

		public void createControl(Composite parent) {
			parent = new Composite(parent, SWT.NONE);
			RowLayout rl = new RowLayout(SWT.VERTICAL);
			rl.wrap = false;
			rl.fill = true;
			parent.setLayout(rl);

			Composite selectPathComposite = new Composite(parent, SWT.NONE);
			GridLayout rl2 = new GridLayout(3, false);
			selectPathComposite.setLayout(rl2);

			pathLabel = new Label(selectPathComposite, SWT.NONE);
			pathLabel.setText("Select Project:");
			pathEdit = new Text(selectPathComposite, SWT.BORDER);
			pathEdit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			pathEdit.addModifyListener(new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					selectNewPath(pathEdit.getText());
				}
			});
			pathButton = new Button(selectPathComposite, SWT.NONE);
			pathButton.setText("Browse...");
			pathButton.setEnabled(false);
			// pathButton.addSelectionListener(new SelectionListener() {
			//
			// @Override
			// public void widgetDefaultSelected(SelectionEvent e) {
			// widgetSelected(e);
			// }
			//
			// @Override
			// public void widgetSelected(SelectionEvent e) {
			// DirectoryDialog dialog = new DirectoryDialog(
			// SelectSourcePathPage.this.getShell());
			// String newPath = dialog.open();
			// if (newPath != null)
			// pathEdit.setText(newPath);
			// }
			// });

			Link description = new Link(parent, SWT.MULTI);
			description
					.setText("Expected layout:\n"
							+ "Directory with model.m file in <A HREF=\"http://www.cs.utexas.edu/users/dsb/fopdocs/guidsl.html\">GUIDSL</A> format (e.g., from <A HREF=\"http://fosd.de/fide/\">FeatureIDE</A>) describing the feature model.\n"
							+ "For each feature a subdirectory containing the Java and/or Jak code fragments.\n"
							+ "For code fragments, both, AHEAD syntax (refines, Super, ...) and FeatureIDE syntax (original), are supported.");

			new Label(parent, SWT.NONE).setText("Features:");

			featureList = new List(parent, SWT.BORDER | SWT.V_SCROLL);
			// featureList.setEnabled(false);
			featureList.setLayoutData(new RowData(SWT.DEFAULT, 200));

			setControl(parent);
			setDisabled(null);
			if (sourceProject != null)
				pathEdit.setText(sourceProject.getName());
		}

		protected void selectNewPath(String pname) {
			IProject targetProject = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(pname);

			if (!targetProject.exists()) {
				setDisabled("Project \"" + pname + "\" does not exist");
				return;
			}

			if (!targetProject.getFile("model.m").exists()) {
				setDisabled("Project \"" + pname
						+ "\" does not contain a feature model model.m");
			}

			FeatureModel featureModel = new FeatureModel();
			try {
				new FeatureModelReader(featureModel).readFromFile(targetProject
						.getFile("model.m"));
			} catch (FileNotFoundException e) {
				setDisabled("Unable to access model.m in project.");
				return;
			} catch (UnsupportedModelException e) {
				setDisabled("Feature model in model.m is unsupported: "
						+ e.getMessage());
				return;
			}

			setEnabled(targetProject, featureModel);
		}

		private void setEnabled(IProject targetProject,
				FeatureModel featureModel) {
			sourceProject = targetProject;
			targetWizard.setSourceProject(sourceProject);

			setErrorMessage(null);
			setPageComplete(true);
			featureList.removeAll();
			java.util.List<String> featureNames = GuidslFileLoader
					.getFeatureList(featureModel);
			for (String featureName : featureNames)
				if (targetProject.getFolder(featureName).exists())
					featureList.add(featureName);
			if (featureList.getItemCount() == 0)
				featureList
						.add(featureNames.size()
								+ " features found, but no source directories in target project");
		}

		private void setDisabled(String message) {
			setErrorMessage(message);
			setPageComplete(false);
			featureList.removeAll();
			featureList.add("No feature model selected.");

		}

	}

	protected IProject sourceProject = null;
	private WizardPageCreateProject targetWizard;

	public ImportJakWizard() {
		setWindowTitle("Import");
	}

	@Override
	public void addPages() {
		addPage(new SelectSourceProjectPage("Select source project"));
		targetWizard = new WizardPageCreateProject("Select target project",
				sourceProject);
		addPage(targetWizard);
		super.addPages();
	}

	public boolean performFinish() {
		assert sourceProject != null;
		final IProject targetProject = targetWizard.getTargetProject();
		final IProject sourceProject = this.sourceProject;
		assert targetProject != null;

		WorkspaceJob job = new WorkspaceJob(
				"Import AHEAD/FeatureHouse Project " + sourceProject.getName()) {
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				try {

					createProject(targetProject, monitor);

					new JakImporter().importJakProject(sourceProject,
							targetProject, monitor);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new Status(Status.ERROR, "de.ovgu.cide.import.jak",
							e.getMessage(), e);
				}
				return Status.OK_STATUS;
			}

			private void createProject(IProject targetProject,
					IProgressMonitor monitor) throws CoreException {

				if (targetProject.exists()) {
					// monitor.subTask("Removing existing project.");
					targetProject.delete(true, monitor);
				}

				// monitor.subTask("Creating target project.");
				targetProject.create(monitor);
				targetProject.open(monitor);
				// monitor.worked(1);
			}
		};
		job.setUser(true);
		job.schedule();

		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection != null
				&& selection.getFirstElement() instanceof IProject)
			sourceProject = (IProject) selection.getFirstElement();
	}

}
