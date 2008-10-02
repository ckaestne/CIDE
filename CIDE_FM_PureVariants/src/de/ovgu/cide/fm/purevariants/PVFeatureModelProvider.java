package de.ovgu.cide.fm.purevariants;

import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeatureModel;
import coloredide.features.IFeatureModelProvider;

import com.ps.consul.eclipse.ui.mapping.Mapping;
import com.ps.consul.eclipse.ui.mapping.Util;

public class PVFeatureModelProvider implements IFeatureModelProvider {

	public PVFeatureModelProvider() {
		// TODO Auto-generated constructor stub
	}

	private final WeakHashMap<IProject, PVFeatureModel> cache = new WeakHashMap<IProject, PVFeatureModel>();

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		PVFeatureModel fm = cache.get(project);
		if (fm != null)
			return fm;

		String targetName = project.getName();
		if (targetName.indexOf("_Variability") >= 0)
			throw new FeatureModelNotFoundException(
					"No feature model for a pure::variants model project.");

		String pvProjectName = project.getName() + "_Variability";
		IProject pvProject = project.getWorkspace().getRoot().getProject(
				pvProjectName);
		if (!pvProject.exists())
			try {
				Util.makeStandardMappingProject(pvProjectName);
			} catch (Exception e) {
				e.printStackTrace();
				throw new FeatureModelNotFoundException(
						"Pure::variants model not present and cannot be created.",
						e);
			}

		assert pvProject.exists();

		IFile ccfm = pvProject.getFile("Mapping.ccfm");
		if (!ccfm.exists())
			throw new FeatureModelNotFoundException(
					"Mapping.ccfm file not found in " + pvProjectName);
		IFile vdm = pvProject.getFile("Config/Config.vdm");
		if (!vdm.exists())
			throw new FeatureModelNotFoundException(
					"Config.vdm file not found in " + pvProjectName);

		Mapping mapping = null;
		try {
			mapping = new Mapping(ccfm, vdm);
		} catch (CoreException e) {
			throw new FeatureModelNotFoundException("Cannot create PV Mapping");
		}

		fm = new PVFeatureModel(mapping, project);
		cache.put(project, fm);
		return fm;
	}

}
