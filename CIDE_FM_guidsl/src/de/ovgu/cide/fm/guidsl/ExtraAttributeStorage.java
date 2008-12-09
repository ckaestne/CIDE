package de.ovgu.cide.fm.guidsl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.RGB;

import de.ovgu.cide.ChangeType;
import de.ovgu.cide.utils.ColorHelper;
import featureide.fm.core.Feature;

/**
 * stores all attributes except the feature's name. the name is used as key
 * 
 * @author ckaestne
 * 
 */
public class ExtraAttributeStorage {

	private IFile file;
	private GuidslFeatureModelWrapper featureModel;

	public ExtraAttributeStorage(IFile storageFile,
			GuidslFeatureModelWrapper featureModel) {
		this.file = storageFile;
		this.featureModel = featureModel;
		load();
	}

	protected long getNextId() {
		long max = 0;
		for (long id : featureIds.values())
			max = Math.max(max, id);
		return max + 1;
	}

	public void notifyFeatureRenamed(String oldName, String newName) {
		if (featureIds.containsKey(oldName)) {
			featureIds.put(newName, featureIds.remove(oldName));
			save();
		}
	}

	public long getFeatureId(Feature feature) {
		Long id = featureIds.get(feature.getName());
		if (id == null) {
			id = new Long(getNextId());
			featureIds.put(feature.getName(), id);
			save();
		}
		return id.longValue();
	}

	public RGB getFeatureColor(Feature feature) {
		long id = getFeatureId(feature);
		RGB color = featureColors.get(id);
		if (color == null) {
			color = ColorHelper.DEFAULT_COLORS[(int) (id % ColorHelper.DEFAULT_COLORS.length)];
		}
		return color;
	}

	public boolean isFeatureVisible(Feature feature) {
		long id = getFeatureId(feature);
		Boolean visible = featureVisibility.get(id);
		if (visible == null)
			return true;
		return visible.booleanValue();
	}

	public void setFeatureColor(Feature feature, RGB color) {
		featureColors.put(getFeatureId(feature), color);
		featureModel.fireFeatureChanged(feature,ChangeType.COLOR);
		save();
	}

	public void setFeatureVisibile(Feature feature, boolean isVisible) {
		featureVisibility.put(getFeatureId(feature), isVisible);
		featureModel.fireFeatureChanged(feature,ChangeType.VISIBILITY);
		save();
	}

	public Feature findFeatureById(Collection<Feature> features, long id) {
		for (Feature feature : features)
			if (getFeatureId(feature) == id)
				return feature;
		return null;
	}

	private Map<String, Long> featureIds;
	private Map<Long, RGB> featureColors;
	private Map<Long, Boolean> featureVisibility;

	private final static long serialVersionUID = 1L;

	private void load() {
		featureIds = new HashMap<String, Long>();
		featureColors = new HashMap<Long, RGB>();
		featureVisibility = new HashMap<Long, Boolean>();

		try {
			if (!file.exists())
				return;

			InputStream is = file.getContents(true);
			ObjectInputStream out = new ObjectInputStream(is);
			try {
				long version = out.readLong();
				if (version != serialVersionUID)
					return;

				featureIds = (Map<String, Long>) out.readObject();
				featureColors = (Map<Long, RGB>) out.readObject();
				featureVisibility = (Map<Long, Boolean>) out.readObject();
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean save() {
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(b);
			out.writeLong(serialVersionUID);
			out.writeObject(featureIds);
			out.writeObject(featureColors);
			out.writeObject(featureVisibility);
			out.close();
			ByteArrayInputStream source = new ByteArrayInputStream(b
					.toByteArray());
			if (!file.exists())
				file.create(source, true, null);
			else
				file.setContents(source, true, true, null);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
	}

}
