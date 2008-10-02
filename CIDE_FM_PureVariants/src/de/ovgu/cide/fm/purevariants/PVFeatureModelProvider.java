package de.ovgu.cide.fm.purevariants;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;

import com.ps.consul.eclipse.core.model.pv.PVModel;
import com.ps.consul.eclipse.ui.mapping.Mapping;
import com.ps.consul.eclipse.ui.mapping.Util;

import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeatureModel;
import coloredide.features.IFeatureModelProvider;

public class PVFeatureModelProvider implements IFeatureModelProvider {

	public PVFeatureModelProvider() {
		// TODO Auto-generated constructor stub
	}

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
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

		Mapping mapping =null;
		try {
			 mapping = new Mapping(ccfm, vdm);
		} catch (CoreException e) {
			throw new FeatureModelNotFoundException("Cannot create PV Mapping");
		}

		
		return new PVFeatureModel(mapping,project);
	}

}