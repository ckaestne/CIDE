package de.ovgu.cide.fm.list;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelProvider;

public class ListFMProvider implements IFeatureModelProvider {

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		if (project == null || !project.exists())
			throw new FeatureModelNotFoundException();
		return ListFeatureModel.getInstance(project);
	}

	// public Iproje

}
