package de.ovgu.cide.features.source;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

abstract class AbstractColorManager {

	private final IStorageProvider storageProvider;

	private Map<String, Set<IFeature>> id2colors = null;

	private final IProject project;

	private final Object resource;

	protected final IFeatureModel featureModel;

	public AbstractColorManager(IStorageProvider storageProvider,
			IProject project, Object annotatedResource,
			IFeatureModel featureModel) {
		this.storageProvider = storageProvider;
		this.project = project;
		this.resource = annotatedResource;
		this.featureModel = featureModel;
		id2colors = storageProvider.readAnnotations(project, annotatedResource,
				featureModel);
	}
	
	private void save() {
		if (tempMode)
			return;
		if (!changed)
			return;
		if (batch != 0)
			return;

		try {
			if (storageProvider.storeAnnotations(project, resource, id2colors, getID2parentIDs(), null))
				changed = false;
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	protected Map<String, List<String>> getID2parentIDs() {
		return null;
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

			// Achtung: Auch wenn  colors  hier leer ist, darf der Eintrag nicht aus  id2colors  geloescht werden,
			//          weil beim Abspeichern der Annotationen sonst vergessen werden kann, dass die Faerbung
			//          aufgehoben wurde.
			
		}
		changed |= success;
		save();
		return success;
	}
	
	protected boolean activateAlternative(String id, String altID) {
		boolean success = storageProvider.activateAlternative(project, resource, id, altID);
		changed |= success;
		return success;
	}
	
	protected boolean createAlternative(Alternative alternative) {
		boolean success = storageProvider.storeNewAlternative(project, resource, alternative);
		changed |= success;
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
			id2colors.put(id, new HashSet<IFeature>());
		
			// Achtung: Der Eintrag darf nicht aus  id2colors  geloescht werden, weil beim Abspeichern der Annotationen 
			//          sonst vergessen werden kann, dass die Faerbung aufgehoben wurde.
			
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
			id2colors = storageProvider.readAnnotations(project, resource,
					featureModel);
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
