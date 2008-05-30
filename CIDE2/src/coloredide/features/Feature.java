package coloredide.features;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.RGB;

import cide.features.IFeature;

public class Feature implements IFeature {

	private static final long serialVersionUID = 1L;

	private RGB rgb;

	private long id;

	public Feature(long id, RGB rgb) {
		this.rgb = rgb;
		this.id = id;
	}

	RGB getRGB() {
		return rgb;
	}

	public RGB getRGB(IProject project) {
		return FeatureNameManager.getFeatureNameManager(project)
				.getFeatureColor(this);
	}

	public long getId() {
		return id;
	}

	public String getName(IProject project) {
		return "Feature: " + getShortName(project);
	}

	public String getShortName(IProject project) {
		return FeatureNameManager.getFeatureNameManager(project)
				.getFeatureName(this);
	}

	public boolean isVisible(IProject project) {
		return FeatureNameManager.getFeatureNameManager(project)
				.isFeatureVisible(this);
	}

	public Set<Feature> getRequiredFeatures(IProject project) {
		return FeatureNameManager.getFeatureNameManager(project)
				.getRequiredFeatures(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#toString()
	 */
	public String toString() {
		return "Feature_" + id;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeLong(id);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		id = in.readLong();
	}

	private Object readResolve() throws ObjectStreamException {
		for (IFeature feature : FeatureManager.getFeatures()) {
			if (feature.getId() == id)
				return feature;
		}
		throw new InvalidObjectException("Feature with ID " + id
				+ " is not known.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#compareTo(coloredide.features.Feature)
	 */
	public int compareTo(IFeature o) {
		if (this.id < o.getId())
			return -1;
		if (this.id > o.getId())
			return 1;
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Feature)
			return id == ((Feature) obj).id;
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.features.IFeature#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) id;
	}
}
