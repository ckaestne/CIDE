package coloredide.features;

import org.eclipse.core.resources.IProject;

/**
 * one feature model is required for every project. the provider creates such a
 * feature model for a given project. CIDE selects the appropriate provider for
 * each project.
 * 
 * @author ckaestne
 * 
 */
public interface IFeatureModelProvider {

	/**
	 * returns the feature model for a given project. if a feature model does
	 * not exist it should usually be generated on the fly.
	 * 
	 * @param project
	 *            for which the feature model should be created. project must
	 *            exist.
	 * @return the feature model, not null
	 * @throws FeatureModelNotFoundException
	 *             if there is no feature model from this provider for the given
	 *             project (e.g., project does not have the correct nature).
	 */
	IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException;

}
