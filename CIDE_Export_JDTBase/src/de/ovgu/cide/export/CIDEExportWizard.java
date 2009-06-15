package de.ovgu.cide.export;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;

import de.ovgu.cide.configuration.WizardPageCreateProject;
import de.ovgu.cide.export.useroptions.IUserOptionProvider;
import de.ovgu.cide.export.useroptions.UserOptionsPage;

public abstract class CIDEExportWizard<T extends IUserOptionProvider> extends
		Wizard implements IExportWizard {

	protected IProject[] sourceProject;
	protected T options;

	public CIDEExportWizard(IProject[] sourceProject, T options) {
		super();
		this.sourceProject = sourceProject;
		this.options = options;
	}

	public CIDEExportWizard(T options) {
		this(null, options);
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
		if (options != null && options.getUserOptions().size() > 0)
			addPage(new UserOptionsPage(options.getUserOptions()));
		createProjectPage = new WizardPageCreateProject("CreateProjects",
				sourceProject[0]);
		addPage(createProjectPage);
		createProjectPage
				.setMessage(
						"All exports and imports in CIDE are experimental and may not work for all annotated elements. In some exports only annotations on types, members, and (top-level) statements are supported.",
						DialogPage.WARNING);

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
