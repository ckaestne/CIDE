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

package de.ovgu.cide.fm.guidsl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.ovgu.cide.Change;
import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.configuration.AbstractConfigurationPage;
import de.ovgu.cide.configuration.NonValidatingConfigurationListPage;
import de.ovgu.cide.features.AbstractFeatureModel;
import de.ovgu.cide.features.IFeature;
import featureide.fm.core.Feature;
import featureide.fm.core.FeatureModel;
import featureide.fm.core.GrammarFile;
import featureide.fm.core.configuration.Configuration;
import featureide.fm.core.configuration.Selection;
import featureide.fm.core.io.ModelWarning;
import featureide.fm.core.io.UnsupportedModelException;
import featureide.fm.core.io.guidsl.FeatureModelReader;

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
	private final PropertyChangeListener changeListener = new PropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(FeatureModel.FEATURE_NAME_CHANGED)) {
				extraAttributeStorage.notifyFeatureRenamed(evt.getOldValue()
						.toString(), evt.getNewValue().toString());
			}
		}
	};

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
		grammarFile.deleteAllModelMarkers();
		try {
			FeatureModelReader modelReader = new FeatureModelReader(model);
			modelReader.readFromFile(grammarFile.getResource());
			for (ModelWarning warning : modelReader.getWarnings())
				grammarFile.createModelMarker(warning.message,
						IMarker.SEVERITY_WARNING, warning.line);
			model.addListener(changeListener);
		} catch (IOException e) {
			grammarFile.createModelMarker(e.getMessage(),
					IMarker.SEVERITY_ERROR, 0);
		} catch (UnsupportedModelException e) {
			grammarFile.createModelMarker(e.getMessage(),
					IMarker.SEVERITY_ERROR, e.lineNumber);
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
			if (!feature.isAbstract())
				result.add(new FeatureAdapter(feature, this));
		}

		return result;
	}

	public void fireFeatureChanged(Feature feature, ChangeType type) {
		fireFeatureModelChanged(new ColorListChangedEvent(this, project,
				new Change(new FeatureAdapter(feature, this), type)));
	}

	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		try {
			// Class.forName("");
			return new EquationEditorPage(pageName, this);
		} catch (NoClassDefFoundError e) {
			return new NonValidatingConfigurationListPage(pageName, this);
		}

	}

	public boolean isValidSelection(Set<IFeature> selection) {
		Configuration conf = new Configuration(model, false);
		for (IFeature feature : selection)
			conf.setManual(feature.getName(), Selection.SELECTED);
		return conf.valid();
	}

	public IFeature createNewFeature() {
		throw new UnsupportedOperationException();
	}

	/**
	 * to be used only internally in this plugin.
	 * 
	 * @return
	 */
	FeatureModel getInternalModel() {
		return model;
	}
}
