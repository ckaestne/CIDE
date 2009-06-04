package de.ovgu.cide.fm.guidsl;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelProvider;

/**
 * feature model provider, initializes the guidsl feature model for each project
 * (if a model.m file is present)
 * 
 * @author ckaestne
 * 
 */
public class GuidslFMProvider implements IFeatureModelProvider {

	public GuidslFMProvider() {
	}

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		return new FeatureModelProxy(project);
	}

}
