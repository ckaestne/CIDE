package de.ovgu.cide.fm.list;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IProject;

import coloredide.ColorListChangedEvent;
import coloredide.configuration.AbstractConfigurationPage;
import coloredide.configuration.NonValidatingConfigurationListPage;
import coloredide.features.AbstractFeatureModel;
import coloredide.features.IFeature;

/**
 * simple feature model of exact 50 features in a list. no further dependencies.
 * 
 * this class must only be instantiated once per project!
 * 
 * @author ckaestne
 * 
 */
public class ListFeatureModel extends AbstractFeatureModel {

	private FeatureNameManager featureNameManager;

	private static WeakHashMap<IProject, WeakReference<ListFeatureModel>> instanceCache = new WeakHashMap<IProject, WeakReference<ListFeatureModel>>();

	public static ListFeatureModel getInstance(IProject project) {
		WeakReference<ListFeatureModel> fmRef = instanceCache.get(project);
		if (fmRef != null) {
			ListFeatureModel fm = fmRef.get();
			if (fm != null)
				return fm;
		}
		ListFeatureModel fm = new ListFeatureModel(project);
		fmRef = new WeakReference<ListFeatureModel>(fm);
		instanceCache.put(project, fmRef);
		return fm;
	}

	/**
	 * only to be instantiated from within this plugin
	 * 
	 * @param project
	 */
	private ListFeatureModel(IProject project) {
		super(project);
		this.featureNameManager = new FeatureNameManager(this);
	}

	@Override
	public void fireFeatureModelChanged(ColorListChangedEvent event) {
		super.fireFeatureModelChanged(event);
	}

	/** only to be used from within this plugin */
	public FeatureNameManager getFeatureNameManager() {
		return featureNameManager;
	}

	public Set<IFeature> getFeatures() {
		List<FixedFeature> features = FeatureManager.getFeatures();
		Set<IFeature> result = new HashSet<IFeature>(features.size());
		for (FixedFeature feature : features) {
			result.add(new FeatureAdapter(feature, this));
		}
		return Collections.unmodifiableSet(result);
	}

	public IFeature getFeature(long id) {
		for (FixedFeature f : FeatureManager.getFeatures()) {
			if (f.getId() == id)
				return new FeatureAdapter(f, this);
		}
		return null;
	}

	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		return new NonValidatingConfigurationListPage(pageName, this);
	}

	public boolean isValidSelection(Set<IFeature> selection) {
		return true;
	}

}
