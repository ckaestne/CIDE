/**
 * 
 */
package de.ovgu.cide;

import de.ovgu.cide.features.IFeature;

public class Change {
	final public IFeature feature;
	final public ChangeType type;

	public Change(IFeature feature, ChangeType type) {
		this.feature = feature;
		this.type = type;
	}
}