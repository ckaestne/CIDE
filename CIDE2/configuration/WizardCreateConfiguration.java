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
