package coloredide.features;

/**
 * extension of the feature model required for the default storage mechanism
 * which addresses features by an id (type long)
 * 
 * @author ckaestne
 * 
 */
public interface IFeatureModelWithID extends IFeatureModel {
	/**
	 * returns a specific feature by a given ID which identifies this feature,
	 * or null if no such feature exists
	 * 
	 * @see IFeature.getId
	 * @param id
	 *            unique featureid in this project
	 * @return the feature or null if no such feature exists
	 */
	IFeature getFeature(long id);
}
