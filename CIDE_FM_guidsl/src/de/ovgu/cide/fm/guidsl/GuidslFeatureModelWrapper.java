package de.ovgu.cide.fm.guidsl;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import coloredide.Change;
import coloredide.ChangeType;
import coloredide.ColorListChangedEvent;
import coloredide.configuration.AbstractConfigurationPage;
import coloredide.configuration.NonValidatingConfigurationListPage;
import coloredide.features.AbstractFeatureModel;
import coloredide.features.IFeature;
import featureide_core.model.Feature;
import featureide_core.model.FeatureModel;
import featureide_core.model.GrammarFile;
import featureide_core.model.configuration.Configuration;
import featureide_core.model.configuration.Selection;
import featureide_core.model.io.ModelWarning;
import featureide_core.model.io.UnsupportedModelException;
import featureide_core.model.io.guidsl.FeatureModelReader;

/**
 * this class wraps a feature model from the FeatureIDE implementation.
 * 
 * it additionally stores visibility, colors, and ids separately.
 * 
 * 
 * @author ckaestne
 * 
 */
public class GuidslFeatureModelWrapper extends AbstractFeatureModel {

	private class ModelResourceChangeListener implements
			IResourceChangeListener {

		public void resourceChanged(IResourceChangeEvent event) {
			checkModelChange(event.getDelta().findMember(
					grammarFile.getResource().getFullPath()));
		}

		private void checkModelChange(IResourceDelta delta) {
			if (delta == null
					|| (delta.getFlags() & IResourceDelta.CONTENT) == 0)
				return;
			System.out.println("Model "
					+ grammarFile.getResource().getFullPath() + " changed");
			Job job = new Job("Load Model") {
				protected IStatus run(IProgressMonitor monitor) {
					List<Change> changes = new ArrayList<Change>();
					for (IFeature f : getFeatures())
						changes.add(new Change(f, ChangeType.REMOVE));

					loadModel();

					for (IFeature f : getFeatures())
						changes.add(new Change(f, ChangeType.ADD));
					fireFeatureModelChanged(new ColorListChangedEvent(this,
							project, changes));
					return Status.OK_STATUS;
				}
			};
			job.setPriority(Job.SHORT);
			job.schedule();
		}

	}

	private GrammarFile grammarFile;
	FeatureModel model;
	public ExtraAttributeStorage extraAttributeStorage;

	private static WeakHashMap<IProject, WeakReference<GuidslFeatureModelWrapper>> instanceCache = new WeakHashMap<IProject, WeakReference<GuidslFeatureModelWrapper>>();

	public static GuidslFeatureModelWrapper getInstance(IProject project) {
		WeakReference<GuidslFeatureModelWrapper> fmRef = instanceCache
				.get(project);
		if (fmRef != null) {
			GuidslFeatureModelWrapper fm = fmRef.get();
			if (fm != null)
				return fm;
		}
		GuidslFeatureModelWrapper fm = new GuidslFeatureModelWrapper(project);
		fmRef = new WeakReference<GuidslFeatureModelWrapper>(fm);
		instanceCache.put(project, fmRef);
		return fm;
	}

	private GuidslFeatureModelWrapper(IProject project) {
		super(project);
		this.grammarFile = new GrammarFile(project.getFile("model.m"));
		this.model = new FeatureModel();
		this.extraAttributeStorage = new ExtraAttributeStorage(project
				.getFile("model.colors"), this);

		initResourceListener();
		loadModel();
	}

	private void initResourceListener() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				new ModelResourceChangeListener(),
				IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * Loads the model again from the file. Attend that all local changes in the
	 * model get lost.
	 * 
	 * Before loading all error markers will be deleted and afterwards new ones
	 * might be created if some errors occur.
	 */
	private void loadModel() {
		try {
			grammarFile.deleteAllModelMarkers();
			try {
				FeatureModelReader modelReader = new FeatureModelReader(model);
				modelReader.loadFromFile(grammarFile.getResource());
				for (ModelWarning warning : modelReader.getWarnings())
					grammarFile.createModelMarker(warning.message,
							IMarker.SEVERITY_WARNING, warning.line);
			} catch (IOException e) {
				grammarFile.createModelMarker(e.getMessage(),
						IMarker.SEVERITY_ERROR, 0);
			} catch (UnsupportedModelException e) {
				grammarFile.createModelMarker(e.getMessage(),
						IMarker.SEVERITY_ERROR, e.lineNumber);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public IFeature getFeature(long id) {
		Feature feature = extraAttributeStorage.findFeatureById(model
				.getFeatures(), id);
		if (feature != null)
			return new FeatureAdapter(feature, this);
		return null;
	}

	public Set<IFeature> getFeatures() {
		Collection<Feature> features = model.getFeatures();

		Set<IFeature> result = new HashSet<IFeature>(features.size());
		for (Feature feature : features) {
			if (!feature.isCompound())
				result.add(new FeatureAdapter(feature, this));
		}

		return result;
	}

	public void fireFeatureChanged(Feature feature, ChangeType type) {
		fireFeatureModelChanged(new ColorListChangedEvent(this, project,
				new Change(new FeatureAdapter(feature,
						this), type)));
	}

	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		try {
//			Class.forName("");
			return new EquationEditorPage(pageName,this); 
		} catch (NoClassDefFoundError e) {
			return new NonValidatingConfigurationListPage(pageName, this);
		}

	}

	public boolean isValidSelection(Set<IFeature> selection) {
		Configuration conf = new Configuration(model,false);
		for (IFeature feature:selection)
			conf.setManual(feature.getName(), Selection.SELECTED);
		return conf.valid();
	}

}
