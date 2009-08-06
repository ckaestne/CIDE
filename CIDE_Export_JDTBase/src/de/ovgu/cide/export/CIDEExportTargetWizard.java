package de.ovgu.cide.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.ui.IExportWizard;

import de.ovgu.cide.configuration.WizardPageCreateProject;
import de.ovgu.cide.export.useroptions.IUserOptionProvider;
import de.ovgu.cide.export.useroptions.UserOptionsPage;

/**
 * generalized export wizard with options and a target project
 * 
 * @author ckaestne
 * 
 * @param <T>
 */
public abstract class CIDEExportTargetWizard<T extends IUserOptionProvider>
		extends CIDEExport2Wizard implements IExportWizard {

	protected T options;

	public CIDEExportTargetWizard(IProject[] sourceProject, T options) {
		super(sourceProject);
		this.options = options;
	}

	public CIDEExportTargetWizard(T options) {
		this(null, options);
	}

	WizardPageCreateProject createProjectPage;

	@Override
	public void addPages() {
		if (sourceProject == null || sourceProject.length != 1)
			addPage(new SourceProjectErrorPage(sourceProject));
		else {
			addPages2();
		}
	}

	protected void addPages2() {
		if (options != null && options.getUserOptions() != null
				&& options.getUserOptions().size() > 0)
			addPage(new UserOptionsPage(options.getUserOptions()));
		createProjectPage = new WizardPageCreateProject("CreateProjects",
				sourceProject[0]);
		addPage(createProjectPage);
		createProjectPage
				.setMessage(
						"All exports and imports in CIDE are experimental and may not work for all annotated elements. In some exports only annotations on types, members, and (top-level) statements are supported.",
						DialogPage.WARNING);

	}

	@Override
	protected IProject getTargetProject() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		return root.getProject(createProjectPage.projectName.getText());
	}
}
