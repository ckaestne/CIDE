package coloredide.export;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class WizardPageSelectExporter extends WizardPage {

	private ExportFactory[] factories;

	private ExportFactory selectedExportFactory;

	public WizardPageSelectExporter(String pageName, ExportFactory[] factories) {
		super(pageName);
		this.setTitle("Select Exporter");
		this.setPageComplete(false);
		this.factories = factories;
		assert factories.length > 0;
		this.setPageComplete(false);
	}

	Tree exporters;

	//
	// private void checkProjectName() {
	// if (projectName.getText().equals(sourceProject.getName())) {
	// WizardPageSelectExporter.this.setMessage(
	// "Cannot overwrite the source project.", DialogPage.ERROR);
	// WizardPageSelectExporter.this.setPageComplete(false);
	// } else if (projectName.getText().equals("")) {
	// WizardPageSelectExporter.this.setMessage(
	// "Project name cannot be empty.", DialogPage.ERROR);
	// WizardPageSelectExporter.this.setPageComplete(false);
	// } else {
	// IProject project = ResourcesPlugin.getWorkspace().getRoot()
	// .getProject(projectName.getText());
	// if (project.exists())
	// WizardPageSelectExporter.this.setMessage("Project "
	// + projectName.getText()
	// + " already exists. Will be overwritten.",
	// DialogPage.WARNING);
	// else
	// WizardPageSelectExporter.this.setMessage("");
	// WizardPageSelectExporter.this.setPageComplete(true);
	// }
	// }

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		Label label = new Label(composite, SWT.NONE);
		label.setText("Project name:");

		exporters = new Tree(composite, SWT.BORDER);
		
		for (ExportFactory f: factories){
			TreeItem item = new TreeItem(exporters, SWT.DEFAULT);
			item.setData(f);
			item.setText(f.getName());
		}
		
		
		exporters.addSelectionListener(new SelectionListener(){


			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = exporters.getSelection();
				if (items.length==1)
					if (items[0].getData() instanceof ExportFactory) {
						selectedExportFactory=(ExportFactory) items[0].getData();
						setPageComplete(true);
					}
			}} );
		
	

		FormLayout layout = new FormLayout();
		layout.marginHeight = layout.marginWidth = 5;
		composite.setLayout(layout);

		FormData data = new FormData();
		data.top = new FormAttachment(label, 5);
		data.left = new FormAttachment(0, 0);
		data.width = 200;
		exporters.setLayoutData(data);
		//
		// data = new FormData();
		// data.right = new FormAttachment(100, 0);
		// data.left = new FormAttachment(projectName,5);
		// data.top = new FormAttachment(label, 5);
		// data.width = 40;
		// button.setLayoutData(data);

		this.setControl(composite);
	}

	public ExportFactory getSelectedExporter() {
		return selectedExportFactory;
	}

}
