package coloredide.features;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.RGB;

public class Feature implements Serializable, Comparable<Feature> {

	private static final long serialVersionUID = 1L;

	private RGB rgb;

	public long id;

	public Feature(long id, RGB rgb) {
		this.rgb = rgb;
		this.id = id;
	}

	public RGB getRGB() {
		return rgb;
	}

	public String getName(IProject project) {
		return  "Feature: "+getShortName(project);
	}
	public String getShortName(IProject project) {
		return  FeatureNameManager.getFeatureNameManager(project).getFeatureName(this);
	}
	public boolean isVisible(IProject project){
		return FeatureNameManager.getFeatureNameManager(project).isFeatureVisible(this);
	}
	public Set<Feature> getRequiredFeatures(IProject project) {
		return  FeatureNameManager.getFeatureNameManager(project).getRequiredFeatures(this);
	}

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
		for (Feature feature : FeatureManager.getFeatures()) {
			if (feature.id == id)
				return feature;
		}
		throw new InvalidObjectException("Feature with ID " + id
				+ " is not known.");
	}



	public int compareTo(Feature o) {
		if (this.id<o.id) return -1;
		if (this.id>o.id) return 1;
		return 0;
	}

}
