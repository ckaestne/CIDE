package coloredide.features;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * one feature name manager per project. per project association of feature-id
 * and featurename and parent feature.
 * 
 * 
 * @author cKaestner
 * 
 */
public class FeatureNameManager {
	private static final Map<IProject, FeatureNameManager> cache = new WeakHashMap<IProject, FeatureNameManager>();

	public static FeatureNameManager getFeatureNameManager(IProject project) {
		FeatureNameManager r = cache.get(project);
		if (r == null) {
			r = new FeatureNameManager(project);
			cache.put(project, r);
		}
		return r;
	}

	private transient IProject project;

	private Map<Feature, String> featureNames;

	private Map<Feature, Boolean> featureVisibility;

	// a depends on features B
	private Map<Feature, Set<Feature>> dependsOnRelation;

	public FeatureNameManager(IProject project) {
		this.project = project;
		load();
	}

	public String getFeatureName(Feature f) {
		String name = featureNames.get(f);
		if (name == null) {
			name = "Feature" + f.id;
		}
		return name;
	}

	public void setFeatureName(Feature f, String name) {
		if (name.equals(getFeatureName(f)))
			return;
		featureNames.put(f, name);
		try {
			save(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public boolean isFeatureVisible(Feature f) {
		Boolean v = featureVisibility.get(f);
		if (v == null) {
			return false;
		}
		return v.booleanValue();
	}

	public void setFeatureVisible(Feature f, boolean b) {
		if (b == isFeatureVisible(f))
			return;
		featureVisibility.put(f, new Boolean(b));
		try {
			save(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public Set<Feature> getRequiredFeatures(Feature f) {
		Set<Feature> result = dependsOnRelation.get(f);
		if (result == null)
			return Collections.EMPTY_SET;
		return Collections.unmodifiableSet(result);
	}

	public void setRequiredFeatures(Feature f, Set<Feature> requiredFeatures) {
		if (requiredFeatures == null)
			dependsOnRelation.remove(f);
		else
			dependsOnRelation.put(f, requiredFeatures);

		try {
			save(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void load() {
		featureNames = null;
		featureVisibility = null;
		dependsOnRelation = null;

		IFile mapFile = project.getFile(".colors");
		if (mapFile.exists()) {
			try {
				InputStream is = mapFile.getContents(true);
				ObjectInputStream out = new ObjectInputStream(is);
				try {
					featureNames = (HashMap<Feature, String>) out.readObject();
					featureVisibility = (HashMap<Feature, Boolean>) out
							.readObject();
					//depricated
					out.readObject();
					dependsOnRelation = (HashMap<Feature, Set<Feature>>) out
							.readObject();
				} finally {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (featureNames == null)
			featureNames = new HashMap<Feature, String>();

		if (featureVisibility == null)
			featureVisibility = new HashMap<Feature, Boolean>();
		if (dependsOnRelation == null)
			dependsOnRelation = new HashMap<Feature, Set<Feature>>();

	}

	private void save(IProgressMonitor monitor) throws CoreException {
		IFile mapFile = project.getFile(".colors");
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(b);
			out.writeObject(featureNames);
			out.writeObject(featureVisibility);
			//deprecated
			out.writeObject(Collections.EMPTY_MAP);
			out.writeObject(dependsOnRelation);
			out.close();
			ByteArrayInputStream source = new ByteArrayInputStream(b
					.toByteArray());
			if (!mapFile.exists())
				mapFile.create(source, true, monitor);
			else
				mapFile.setContents(source, true, true, monitor);
			// System.out.println("saving colornames file " + mapFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
