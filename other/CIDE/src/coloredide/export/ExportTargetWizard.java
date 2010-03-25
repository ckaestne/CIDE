package coloredide.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;

import coloredide.configuration.WizardPageCreateProject;

public class ExportTargetWizard extends Wizard {

	private IProject sourceProject;

	private ExportFactory[] factories;

	public ExportTargetWizard(IProject sourceProject, ExportFactory[] factory) {
		super();
		this.sourceProject = sourceProject;
		this.factories = factory;
	}

	private WizardPageCreateProject createProjectPage;

	private WizardPageSelectExporter selectExporterPage;

	@Override
	public void addPages() {
		selectExporterPage = new WizardPageSelectExporter("SelectExporter",
				factories);
		addPage(selectExporterPage);
		createProjectPage = new WizardPageCreateProject("CreateProjects",
				sourceProject);
		addPage(createProjectPage);

	}

	public boolean performFinish() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject targetProject = root.getProject(createProjectPage.projectName
				.getText());
		ExportFactory exportFactory = selectExporterPage.getSelectedExporter();
		BaseExportJob job = exportFactory.createJob(sourceProject,
				targetProject);
		job.setUser(true);
		job.setPriority(Job.LONG);
		// job.setRule(targetProject);
		job.schedule();

		return true;
	}
}
