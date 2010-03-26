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

package de.ovgu.cide.fm.list;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.RGB;

import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;

/**
 * one feature name manager per project. per project association of feature-id
 * and featurename and parent feature.
 * 
 * 
 * @author cKaestner
 * 
 */
public class FeatureNameManager {


	private transient IProject project;

	private Map<FixedFeature, String> featureNames;

	private Map<FixedFeature, Boolean> featureVisibility;
	private Map<FixedFeature, RGB> featureColors;

	// a depends on features B
	private Map<FixedFeature, Set<FixedFeature>> dependsOnRelation;

	private ListFeatureModel featureModel;

	FeatureNameManager(ListFeatureModel featureModel) {
		this.featureModel = featureModel;
		this.project = featureModel.getProject();
		load();
	}

	public String getFeatureName(FixedFeature f) {
		String name = find(featureNames, f);
		if (name == null) {
			name = "Feature" + f.getId();
		}
		return name;
	}

	/**
	 * this methods searches also for old Feature objects for compatibility with
	 * legacy feature model files
	 * 
	 * @param <T>
	 * @param map
	 * @param feature
	 * @return
	 */	
	private <T> T find(Map<?, T> map, FixedFeature feature) {
		for (Map.Entry<?, T> entry : map.entrySet())
			if (entry.getKey() instanceof FixedFeature)
				if (((FixedFeature) entry.getKey()).equals(feature))
					return entry.getValue();
//		for (Map.Entry<?, T> entry : map.entrySet())
//			if (entry.getKey() instanceof Feature)
//				if (((Feature) entry.getKey()).getId() == feature.getId())
//					return entry.getValue();

		return null;
	}

	public void setFeatureName(FixedFeature f, String name) {
		if (name.equals(getFeatureName(f)))
			return;
		featureNames.put(f, name);
		notify(f, ChangeType.NAME);
		try {
			save(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get the color (RGB) of a feature. may override the default color of a
	 * feature
	 * 
	 * @param f
	 *            feature
	 * @return the color (not null, default color if nothing special is set)
	 */
	public RGB getFeatureColor(FixedFeature f) {
		RGB color = find(featureColors, f);
		if (color == null) {
			color = f.getRGB();
		}
		return color;
	}

	public void setFeatureColor(FixedFeature f, RGB color) {
		if (color.equals(getFeatureColor(f)))
			return;
		// reset to default color?
		if (color.equals(f.getRGB())) {
			featureColors.remove(f);
			return;
		}
		featureColors.put(f, color);
		notify(f, ChangeType.COLOR);
		try {
			save(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void notify(FixedFeature feature, ChangeType type) {
		featureModel.fireFeatureModelChanged(new ColorListChangedEvent(this,
				project, new FeatureAdapter(feature, featureModel), type));

	}

	public boolean isFeatureVisible(FixedFeature f) {
		Boolean v = find(featureVisibility, f);
		if (v == null) {
			return false;
		}
		return v.booleanValue();
	}

	public void setFeatureVisible(FixedFeature f, boolean b) {
		if (b == isFeatureVisible(f))
			return;
		featureVisibility.put(f, new Boolean(b));
		notify(f, ChangeType.VISIBILITY);
		try {
			save(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public Set<FixedFeature> getRequiredFeatures(FixedFeature f) {
		// no legacy feature model handling! (would be too hard to get right)
		Set<FixedFeature> result = dependsOnRelation.get(f);
		if (result == null)
			return Collections.EMPTY_SET;
		return Collections.unmodifiableSet(result);
	}

	public void setRequiredFeatures(FixedFeature f,
			Set<FixedFeature> requiredFeatures) {
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
		featureColors = null;

		IFile mapFile = project.getFile(".colors");
		if (mapFile.exists()) {
			try {
				InputStream is = mapFile.getContents(true);
				ObjectInputStream out = new ObjectInputStream(is);
				try {
					featureNames = (HashMap<FixedFeature, String>) out
							.readObject();
					featureVisibility = (HashMap<FixedFeature, Boolean>) out
							.readObject();
					// @deprecated
					out.readObject();
					dependsOnRelation = (HashMap<FixedFeature, Set<FixedFeature>>) out
							.readObject();
					featureColors = (HashMap<FixedFeature, RGB>) out
							.readObject();
				} finally {
					out.close();
				}
			} catch (EOFException e) {
				//nothing
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (featureNames == null)
			featureNames = new HashMap<FixedFeature, String>();

		if (featureVisibility == null)
			featureVisibility = new HashMap<FixedFeature, Boolean>();
		if (dependsOnRelation == null)
			dependsOnRelation = new HashMap<FixedFeature, Set<FixedFeature>>();
		if (featureColors == null)
			featureColors = new HashMap<FixedFeature, RGB>();

	}

	private void save(IProgressMonitor monitor) throws CoreException {
		IFile mapFile = project.getFile(".colors");
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(b);
			out.writeObject(featureNames);
			out.writeObject(featureVisibility);
			// @deprecated
			out.writeObject(Collections.EMPTY_MAP);
			out.writeObject(dependsOnRelation);
			out.writeObject(featureColors);
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
