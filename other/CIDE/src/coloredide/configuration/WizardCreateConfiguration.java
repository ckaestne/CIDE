package coloredide.configuration;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;

import coloredide.utils.WizardPageSelectFeatures;

public class WizardCreateConfiguration extends Wizard {

	private IProject sourceProject;

	public WizardCreateConfiguration(IProject sourceProject) {
		super();
		this.sourceProject = sourceProject;
	}

	private WizardPageSelectFeatures selectFeaturesPage;

	private WizardPageCreateProject createProjectPage;

	@Override
	public void addPages() {
		selectFeaturesPage = new WizardPageSelectFeatures("SelectFeatures",sourceProject);
		addPage(selectFeaturesPage);
		createProjectPage = new WizardPageCreateProject("CreateProjects",sourceProject);
		addPage(createProjectPage);

	}

	public boolean performFinish() {
		
		CreateConfigurationJob job = new CreateConfigurationJob(sourceProject,
				selectFeaturesPage.getSelectedFeatures(),
				createProjectPage.projectName.getText());
		job.setUser(true);
		job.setPriority(Job.LONG);
		job.schedule();



		return true;
	}
}
