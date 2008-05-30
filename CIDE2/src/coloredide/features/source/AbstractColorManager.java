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
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import coloredide.features.Feature;

abstract class AbstractColorManager {

	private final IFile colorFile;

	private HashMap<String, Set<Feature>> id2colors = null;

	private IProject project;

	public AbstractColorManager(IFile colorFile, IProject project) {
		this.colorFile = colorFile;
		if (!colorFile.exists() || !loadColorFile())
			id2colors = new HashMap<String, Set<Feature>>();
		this.project = project;
	}

	private final static long serialVersionUID = 1l;

	private boolean loadColorFile() {
		try {
			if (!colorFile.exists())
				return false;

			InputStream is = colorFile.getContents(true);
			ObjectInputStream out = new ObjectInputStream(is);
			try {
				long version = out.readLong();
				if (version != serialVersionUID)
					return false;
				id2colors = (HashMap<String, Set<Feature>>) out.readObject();
				return true;
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
				out.writeObject(id2colors);
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

	protected Set<Feature> getOwnColors(String id) {
		@SuppressWarnings("unused")
		Set<Feature> colors = id2colors.get(id);
		HashSet<Feature> result = new HashSet<Feature>();
		if (colors != null) {
			result.addAll(colors);
			for (Feature f : colors) {
				addRequiredFeatures(result, f);
			}
		}
		return Collections.unmodifiableSet(result);
	}

	private void addRequiredFeatures(Set<Feature> featureList, Feature f) {
		Set<Feature> requiredFeatures = f.getRequiredFeatures(project);
		for (Feature requiredFeature : requiredFeatures) {
			if (!featureList.contains(requiredFeature)) {
				featureList.add(requiredFeature);
				addRequiredFeatures(featureList, requiredFeature);
			}
		}
	}

	protected boolean addColor(String id, Feature color) {
		Set<Feature> colors = id2colors.get(id);
		if (colors == null) {
			colors = new HashSet<Feature>();
			id2colors.put(id, colors);
		}
		boolean success = colors.add(color);
		changed |= success;
		save();
		return success;
	}

	protected boolean removeColor(String id, Feature color) {
		Set<Feature> colors = id2colors.get(id);
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

	protected boolean hasColor(String id, Feature color) {
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
		Set<Feature> colors = id2colors.get(id);
		boolean success = colors != null && colors.size() > 0;
		if (colors != null)
			id2colors.remove(id);
		changed |= success;
		save();
		return success;
	}

	public boolean hasColors() {
		for (String id : id2colors.keySet()) {
			Set<Feature> colors = id2colors.get(id);
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
				id2colors = new HashMap<String, Set<Feature>>();
		this.tempMode = enable;
	}

	protected void setColors(String id, Set<Feature> newColors) {
		Set<Feature> previousColors = id2colors.get(id);
		id2colors.put(id, new HashSet<Feature>(newColors));
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
	public Set<Feature> getAllUsedColors() {
		Set<Feature> result=new HashSet<Feature>();
		for (Set<Feature> v:id2colors.values()){
			result.addAll(v);
		}
		return result;
	}
}
