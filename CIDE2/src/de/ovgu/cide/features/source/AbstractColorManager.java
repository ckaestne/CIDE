/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.features.source;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.cide.alternativefeatures.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelWithID;

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
		
		updateColors();
	}
	
	private void save() {
		if (tempMode)
			return;
		if (!changed)
			return;
		if (batch != 0)
			return;

		try {
			if (storageProvider.storeAnnotations(project, resource, id2colors, getID2isOptional(), getID2parentIDs(), null))
				changed = false;
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	protected Map<String, List<String>> getID2parentIDs() {
		return null;
	}
	
	protected Map<String, Boolean> getID2isOptional() {
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
	
	/**
	 * Liefert alle Alternativen zu allen AST-Knoten.
	 * @param featureModel
	 * @return
	 */
	public Map<String, List<Alternative>> getAllAlternatives(IFeatureModelWithID featureModel) {
		return storageProvider.getAllAlternatives(project, resource, featureModel);
	}
	
	protected boolean activateAlternative(Alternative alternative, Map<String, String> id2oldText) {
		boolean success = storageProvider.activateAlternative(project, resource, alternative, id2oldText);
		
		// TODO MRO: Graumsam uneffizient! Wir muessen nicht alle Annotationen neu von der Platte lesen,
		//           sondern nur die der neuen Alternative (davon aber alle Kinder!).
		updateColors();
		
		return success;
	}
	
	protected boolean createAlternative(Alternative alternative, Map<String, String> id2oldText) {
		if (alternative == null)
			return false;
		
		boolean success = storageProvider.storeNewAlternative(project, resource, alternative, id2oldText);
		
		// TODO MRO: Graumsam uneffizient! Wir muessen nicht alle Annotationen neu von der Platte lesen,
		//           sondern nur die der neuen Alternative (davon aber alle Kinder!).
		updateColors();
		
		return success;
	}
	
	private void updateColors() {
		id2colors = storageProvider.readAnnotations(project, resource, featureModel);
	}

	protected boolean hasColor(String id, IFeature color) {
		return getOwnColors(id).contains(color);
	}

	private int batch = 0;

	private boolean changed = false;

	private Stack<Boolean> changeStack = null;

	private boolean tempMode;
	private boolean lastTempMode;

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
			id2colors = storageProvider.readAnnotations(project, resource, featureModel);
		this.lastTempMode = this.tempMode;
		this.tempMode = enable;
	}
	
	public void resetTemporaryMode() {
		setTemporaryMode(lastTempMode);
	}

	protected void setColors(String id, Set<IFeature> newColors) {
		if (newColors == null)
			newColors = new HashSet<IFeature>();
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
