package de.ovgu.cide.fm.guidsl;

import org.eclipse.core.resources.IProject;

import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeatureModel;
import coloredide.features.IFeatureModelProvider;

/**
 * feature model provider, initializes the guidsl feature model for each project
 * 
 * @author ckaestne
 * 
 */
public class GuidslFMProvider implements IFeatureModelProvider {

	public GuidslFMProvider() {
	}

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		return GuidslFeatureModelWrapper.getInstance(project);
	}

}
