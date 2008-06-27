package de.ovgu.cide.fm.guidsl;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;

import coloredide.configuration.AbstractConfigurationPage;
import coloredide.features.IFeature;
import featureide_core.model.Feature;
import featureide_core.model.FeatureModel;
import featureide_core.model.configuration.Configuration;
import featureide_core.model.configuration.SelectableFeature;
import featureide_core.model.configuration.Selection;
import featureide_ui.editors.configuration.ConfigurationContentProvider;
import featureide_ui.editors.configuration.ConfigurationLabelProvider;

public class EquationEditorPage extends AbstractConfigurationPage {

	private TreeViewer viewer;
	private final Configuration configuration;
	private GuidslFeatureModelWrapper model;

	public EquationEditorPage(String pageName,
			GuidslFeatureModelWrapper featureModel) {
		super(pageName, featureModel);
		this.model = featureModel;
		configuration = new Configuration(featureModel.model, false);
		setPageComplete(configuration.valid());
		setTitle("Feature selection");
		this.setDescription("Select features for a variant (double-click on feature to select or deselect)");
	}

	@Override
	protected Control createMainControl(Composite composite) {
		viewer = new TreeViewer(composite);
		viewer.addDoubleClickListener(listener);
		viewer.setContentProvider(new ConfigurationContentProvider());
		try {
			Class<?> c = Class
					.forName("featureide_ui.editors.configuration.ConfigurationLabelProvider");
			System.out.println(c);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IBaseLabelProvider lp = new ConfigurationLabelProvider();
		viewer.setLabelProvider(lp);

		viewer.setInput(configuration);
		viewer.expandAll();
		return viewer.getControl();
	}

	private IDoubleClickListener listener = new IDoubleClickListener() {

		public void doubleClick(DoubleClickEvent event) {
			Object object = ((ITreeSelection) event.getSelection())
					.getFirstElement();
			if (object instanceof SelectableFeature) {
				final SelectableFeature feature = (SelectableFeature) object;
				if (feature.getAutomatic() == Selection.UNDEFINED) {
					// set to the next value
					if (feature.getManual() == Selection.UNDEFINED)
						set(feature, Selection.SELECTED);
					else if (feature.getManual() == Selection.SELECTED)
						set(feature, Selection.UNSELECTED);
					else
						// case: unselected
						set(feature, Selection.UNDEFINED);
					configChanged();
					viewer.refresh();
				}
			}
		}

		private void set(SelectableFeature feature, Selection selection) {
			configuration.setManual(feature, selection);
		}

	};

	@Override
	public Set<IFeature> getNotSelectedFeatures() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void configChanged() {
		boolean isValid = configuration.valid();
		setPageComplete(isValid);
		setErrorMessage(isValid ? null : "Invalid selection");
	}

	@Override
	public Set<IFeature> getSelectedFeatures() {
		Set<Feature> selection = configuration.getSelectedFeatures();

		Set<IFeature> result = new HashSet<IFeature>();
		for (Feature s : selection)
			result.add(new FeatureAdapter(s, model));
		return result;
	}

}
