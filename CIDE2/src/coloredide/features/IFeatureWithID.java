package coloredide.features;

public interface IFeatureWithID extends IFeature {
	/**
	 * returns a fixed ID that is used for making features persistent. only this
	 * feature-id is stored. the id must be unique for a feature inside a
	 * project and must not change when renaming the feature
	 * 
	 * most feature model plugins and the default storage mechanism use Long as
	 * ID type
	 * 
	 * @return
	 */
	public long getId();
}
