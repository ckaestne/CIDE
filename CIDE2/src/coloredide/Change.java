/**
 * 
 */
package coloredide;

import coloredide.features.IFeature;

public class Change {
	final public IFeature feature;
	final public ChangeType type;

	public Change(IFeature feature, ChangeType type) {
		this.feature = feature;
		this.type = type;
	}
}