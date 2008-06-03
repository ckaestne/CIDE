package de.ovgu.cide.fm.list;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.RGB;

public class FixedFeature implements Comparable<FixedFeature>, Serializable {

	private static final long serialVersionUID = 1L;

	private RGB rgb;

	private long id;

	public FixedFeature(long id, RGB rgb) {
		this.rgb = rgb;
		this.id = id;
	}

	RGB getRGB() {
		return rgb;
	}

	public long getId() {
		return id;
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
		for (FixedFeature feature : FeatureManager.getFeatures()) {
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
	public int compareTo(FixedFeature o) {
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
		if (obj instanceof FixedFeature)
			return id == ((FixedFeature) obj).id;
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
