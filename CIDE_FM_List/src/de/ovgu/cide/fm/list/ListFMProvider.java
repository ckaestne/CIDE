package de.ovgu.cide.fm.list;

import org.eclipse.core.resources.IProject;

import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeatureModel;
import coloredide.features.IFeatureModelChangeListener;
import coloredide.features.IFeatureModelProvider;

public class ListFMProvider implements IFeatureModelProvider {

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		if (project == null || !project.exists())
			throw new FeatureModelNotFoundException();
		return ListFeatureModel.getInstance(project);
	}

	// public Iproje

}
