package de.ovgu.cide.configuration.purevariants;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import coloredide.configuration.CreateConfigurationJob;
import coloredide.features.IFeature;

import com.ps.consul.eclipse.core.model.IPVElement;
import com.ps.consul.eclipse.core.model.IPVModel;
import com.ps.consul.eclipse.core.model.IPVVariantModel;
import com.ps.consul.eclipse.core.model.ModelConstants;
import com.ps.consul.eclipse.core.transform.ClientTransformStatus;
import com.ps.consul.eclipse.core.transform.IClientTransformModule;
import com.ps.consul.eclipse.ui.mapping.Rule;

import de.ovgu.cide.fm.purevariants.RuleAdapter;

public class CIDETransformation implements IClientTransformModule {

	@Override
	public ClientTransformStatus done() {
		System.out.println("done");
		return ClientTransformStatus.STATUS_OK;
	}

	@Override
	public ClientTransformStatus init(IPVVariantModel arg0, IPVModel[] models,
			Map<String, String> defaultArgs, Map<String, String> params) {

		String targetProject = params.get("CIDE_Target_Project");
		if (targetProject == null || targetProject.equals(""))
			return new ClientTransformStatus(ClientTransformStatus.ERROR,
					"CIDE: Target project not specified.");


		String inputProject = params.get("CIDE_Input_Project");
		if (inputProject == null || inputProject.equals("")) {
			String pvProjectPath = defaultArgs.get("PROJECT");
			String pvProjectName = new Path(pvProjectPath).lastSegment();
			int p = pvProjectName.indexOf("_Variability");
			if (p > 0)
				inputProject = pvProjectName.substring(0, p);
		}
		IProject cideProject = null;
		if (inputProject != null)
			cideProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
					inputProject);
		if (cideProject == null || !cideProject.exists())
			return new ClientTransformStatus(ClientTransformStatus.ERROR,
					"Painted input project not found");

		Set<IFeature> features = new HashSet<IFeature>();
		for (IPVModel model : models) {
			if (model.getType().equals(ModelConstants.CCM_TYPE)) {
				for (IPVElement elem : model.getElementList()) {
					if (Rule.isRule(elem)) {
						features.add(new RuleAdapter(new Rule(elem), null));
					}
				}
			}
		}

		System.out.println("transform " + cideProject + " with " + features);
		final CreateConfigurationJob job = new CreateConfigurationJob(
				cideProject, features, targetProject);
		// job.setUser(false);
		// job.setPriority(Job.LONG);
		// job.setName("CIDE transformation for pure::variants");
		// job.schedule();

		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {

				@Override
				public void run(IProgressMonitor monitor) throws CoreException {
					job.runInWorkspace(monitor);
				}

			}, null);
		} catch (CoreException e) {
			e.printStackTrace();
			return new ClientTransformStatus(ClientTransformStatus.ERROR, e
					.getMessage());
		}

		return ClientTransformStatus.STATUS_OK;
	}

	@Override
	public ClientTransformStatus work() {
		System.out.println("work");
		return ClientTransformStatus.STATUS_OK;
	}

}
