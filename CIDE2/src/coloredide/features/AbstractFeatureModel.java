package coloredide.features;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;

import coloredide.CIDECorePlugin;
import coloredide.ColorListChangedEvent;

public abstract class AbstractFeatureModel implements IFeatureModel {

	protected IProject project;
//	private final List<IFeatureModelChangeListener> listeners = new LinkedList<IFeatureModelChangeListener>();

	protected AbstractFeatureModel(IProject project) {
		assert project != null && project.exists();
		this.project = project;
	}

//	public void addListener(IFeatureModelChangeListener listener) {
//		listeners.add(listener);
//	}

	public IProject getProject() {
		return project;
	}

//	public void removeListener(IFeatureModelChangeListener listener) {
//		listeners.remove(listener);
//	}

	protected void fireFeatureModelChanged(ColorListChangedEvent event) {
		CIDECorePlugin.getDefault().notifyListeners(event);
//		for (IFeatureModelChangeListener listener : listeners)
//			listener.colorListChanged(event);
	}

	public Set<IFeature> getVisibleFeatures() {
		Set<IFeature> result = new HashSet<IFeature>();
		for (IFeature feature : getFeatures()) {
			if (feature.isVisible())
				result.add(feature);
		}
		return Collections.unmodifiableSet(result);
	}

}
