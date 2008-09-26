package coloredide.features.source;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import coloredide.features.Feature;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;

/**
 * general color manager. serializes to a file in the following format
 * HashMap<String,<Set<Long>>>
 * 
 * 
 * 
 * @author ckaestne
 * 
 */
@SuppressWarnings("deprecation")
abstract class AbstractColorManager {

	private final IFile colorFile;

	private HashMap<String, Set<IFeature>> id2colors = null;

	protected final IFeatureModel featureModel;

	public AbstractColorManager(IFile colorFile, IFeatureModel featureModel) {
		this.featureModel = featureModel;
		this.colorFile = colorFile;
		if (!colorFile.exists() || !loadColorFile())
			id2colors = new HashMap<String, Set<IFeature>>();
	}

	private final static long serialVersionUID = 2l;

	private static final long LEGACY_SERIALIZED_VERSION = 1l;

	private boolean loadColorFile() {
		try {
			if (!colorFile.exists())
				return false;

			InputStream is = colorFile.getContents(true);
			ObjectInputStream out = new ObjectInputStream(is);
			try {
				long version = out.readLong();
				if (version == LEGACY_SERIALIZED_VERSION)
					id2colors = loadLegacySerialization(out);
				else if (version != serialVersionUID)
					return false;
				else
					id2colors = loadFeatureMap(out);
				return true;
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * the features themselfs are not serialized, only their IDs. this method
	 * does the serialization
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private HashMap<String, Set<IFeature>> loadFeatureMap(ObjectInputStream out)
			throws IOException, ClassNotFoundException {
		HashMap<String, Set<Long>> storedMap = (HashMap<String, Set<Long>>) out
				.readObject();

		HashMap<String, Set<IFeature>> result = new HashMap<String, Set<IFeature>>();
		for (Map.Entry<String, Set<Long>> entry : storedMap.entrySet()) {
			Set<Long> colorIds = entry.getValue();
			if (!colorIds.isEmpty()) {
				Set<IFeature> features = new HashSet<IFeature>();
				for (long id : colorIds) {
					IFeature feature = featureModel.getFeature(id);
					if (feature != null)
						features.add(feature);
				}
				if (!features.isEmpty())
					result.put(entry.getKey(), features);
			}
		}
		return result;
	}

	/**
	 * old serialization, where a feature class was directly serialized. needed
	 * to be able to load old files
	 * 
	 * @param out
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private HashMap<String, Set<IFeature>> loadLegacySerialization(
			ObjectInputStream out) throws IOException, ClassNotFoundException {

		HashMap<String, Set<Feature>> storedMap = (HashMap<String, Set<Feature>>) out
				.readObject();

		HashMap<String, Set<IFeature>> result = new HashMap<String, Set<IFeature>>();
		for (Map.Entry<String, Set<Feature>> entry : storedMap.entrySet()) {
			Set<Feature> colorIds = entry.getValue();
			if (!colorIds.isEmpty()) {
				Set<IFeature> features = new HashSet<IFeature>();
				for (Feature id : colorIds) {
					IFeature feature = featureModel.getFeature(id.getId());
					if (feature != null)
						features.add(feature);
				}
				if (!features.isEmpty())
					result.put(entry.getKey(), features);
			}
		}
		return result;
	}

	private void save() {
		if (tempMode)
			return;
		if (!changed)
			return;
		if (batch != 0)
			return;

		try {
			saveColorFile(null);
			changed = false;
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public boolean saveColorFile(IProgressMonitor monitor) throws CoreException {
		try {
			if (!id2colors.isEmpty()) {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(b);
				out.writeLong(serialVersionUID);
				out.writeObject(getIdMap());
				out.close();
				ByteArrayInputStream source = new ByteArrayInputStream(b
						.toByteArray());
				if (!colorFile.exists())
					colorFile.create(source, true, monitor);
				else
					colorFile.setContents(source, true, true, monitor);
				// System.out.println("saving color file " + colorFile);
			} else {
				if (colorFile.exists())
					colorFile.delete(true, monitor);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * transforms the id2colors map into a form that is serializable with
	 * standard API objects (Long instead of IFeature)
	 * 
	 * @return
	 */
	private Map<String, Set<Long>> getIdMap() {
		HashMap<String, Set<Long>> result = new HashMap<String, Set<Long>>();
		for (Map.Entry<String, Set<IFeature>> entry : id2colors.entrySet()) {
			Set<IFeature> features = entry.getValue();
			if (!features.isEmpty()) {
				Set<Long> ids = new HashSet<Long>();
				for (IFeature feature : features) {
					ids.add(feature.getId());
				}
				if (!ids.isEmpty())
					result.put(entry.getKey(), ids);
			}
		}
		return result;
	}

	protected Set<IFeature> getOwnColors(String id) {
		Set<IFeature> colors = id2colors.get(id);
		HashSet<IFeature> result = new HashSet<IFeature>();
		if (colors != null) {
			result.addAll(colors);
			for (IFeature f : colors) {
				addRequiredFeatures(result, f);
			}
		}
		return Collections.unmodifiableSet(result);
	}

	private void addRequiredFeatures(Set<IFeature> featureList, IFeature f) {
		Set<IFeature> requiredFeatures = f.getRequiredFeatures();
		for (IFeature requiredFeature : requiredFeatures) {
			if (!featureList.contains(requiredFeature)) {
				featureList.add(requiredFeature);
				addRequiredFeatures(featureList, requiredFeature);
			}
		}
	}

	protected boolean addColor(String id, IFeature color) {
		Set<IFeature> colors = id2colors.get(id);
		if (colors == null) {
			colors = new HashSet<IFeature>();
			id2colors.put(id, colors);
		}
		boolean success = colors.add(color);
		changed |= success;
		save();
		return success;
	}

	protected boolean removeColor(String id, IFeature color) {
		Set<IFeature> colors = id2colors.get(id);
		boolean success = false;
		if (colors != null) {
			success |= colors.remove(color);
			if (colors.isEmpty())
				id2colors.remove(id);
		}
		changed |= success;
		save();
		return success;
	}

	protected boolean hasColor(String id, IFeature color) {
		return getOwnColors(id).contains(color);
	}

	private int batch = 0;

	private boolean changed = false;

	private Stack<Boolean> changeStack = null;

	private boolean tempMode;

	public void beginBatch() {
		if (batch > 0) {
			if (changeStack == null)
				changeStack = new Stack<Boolean>();
			changeStack.push(new Boolean(changed));
		}
		batch++;
		changed = false;
	}

	public void endBatch() {
		batch--;

		if (batch > 0) {
			changed = changeStack.pop().booleanValue() | changed;
		} else {
			save();
		}
	}

	protected boolean clearColor(String id) {
		Set<IFeature> colors = id2colors.get(id);
		boolean success = colors != null && colors.size() > 0;
		if (colors != null)
			id2colors.remove(id);
		changed |= success;
		save();
		return success;
	}

	public boolean hasColors() {
		for (String id : id2colors.keySet()) {
			Set<IFeature> colors = id2colors.get(id);
			if (colors != null && !colors.isEmpty())
				return true;
		}
		return false;
	}

	/**
	 * in temporary mode changes will not be saved and as soon as temporary mode
	 * is disabled all changes will be discarded
	 * 
	 * @param enable
	 */
	public void setTemporaryMode(boolean enable) {
		if (tempMode == true && enable == false)
			if (!loadColorFile())
				id2colors = new HashMap<String, Set<IFeature>>();
		this.tempMode = enable;
	}

	protected void setColors(String id, Set<IFeature> newColors) {
		Set<IFeature> previousColors = id2colors.get(id);
		id2colors.put(id, new HashSet<IFeature>(newColors));
		boolean success = previousColors == null
				|| !previousColors.equals(newColors);
		changed |= success;
		save();
	}

	/**
	 * returns all features that are ever used in any entry of this
	 * color-manager (file or directory).
	 * 
	 * this function is only added temporary for experimental purposes. note
	 * that features might be stored for elements that have been deleted. do not
	 * rely on this function.
	 * 
	 * 
	 * @return
	 */
	public Set<IFeature> getAllUsedColors() {
		Set<IFeature> result = new HashSet<IFeature>();
		for (Set<IFeature> v : id2colors.values()) {
			result.addAll(v);
		}
		return result;
	}

	/**
	 * to be used only for special access. usually use the higher level access
	 * functions in concrete instances of the color manager.
	 */
	public Set<IFeature> getOwnColorsS(String id) {
		return getOwnColors(id);
	}
}
