package de.ovgu.cide.export;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;

import de.ovgu.cide.configuration.WizardPageCreateProject;

public abstract class CIDEExportWizard extends Wizard implements IExportWizard {

	protected IProject[] sourceProject;

	public CIDEExportWizard(IProject[] sourceProject) {
		super();
		this.sourceProject = sourceProject;
	}

	public CIDEExportWizard() {
		this(null);
	}

	private WizardPageCreateProject createProjectPage;

	@Override
	public void addPages() {
		if (sourceProject == null || sourceProject.length != 1)
			addPage(new SourceProjectErrorPage(sourceProject));
		else {
			addPages2();
		}
	}

	protected void addPages2() {
		createProjectPage = new WizardPageCreateProject("CreateProjects",
				sourceProject[0]);
		addPage(createProjectPage);

	}

	public boolean performFinish() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject targetProject = root.getProject(createProjectPage.projectName
				.getText());
		BaseExportJob job = createExportJob(sourceProject[0], targetProject);
		if (job == null)
			return false;

		job.setUser(true);
		job.setPriority(Job.LONG);
		job.schedule();

		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		List<?> resources = IDE.computeSelectedResources(selection);
		List<IProject> projects = new ArrayList<IProject>();
		for (Object r : resources)
			if (r instanceof IProject)
				projects.add((IProject) r);
		sourceProject = projects.toArray(new IProject[0]);
	}

	protected abstract BaseExportJob createExportJob(IProject sourceProject,
			IProject targetProject);
}
