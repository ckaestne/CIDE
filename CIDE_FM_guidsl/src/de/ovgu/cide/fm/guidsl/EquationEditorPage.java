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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.ovgu.cide.configuration.AbstractConfigurationPage;
import de.ovgu.cide.features.IFeature;
import featureide.fm.core.Feature;
import featureide.fm.core.configuration.Configuration;
import featureide.fm.core.configuration.SelectableFeature;
import featureide.fm.core.configuration.Selection;
import featureide.fm.ui.editors.configuration.ConfigurationContentProvider;
import featureide.fm.ui.editors.configuration.ConfigurationLabelProvider;

public class EquationEditorPage extends AbstractConfigurationPage {

	private TreeViewer viewer;
	private final Configuration configuration;
	private GuidslFeatureModelWrapper model;

	public EquationEditorPage(String pageName,
			GuidslFeatureModelWrapper featureModel) {
		super(pageName, featureModel);
		this.model = featureModel;
		configuration = new Configuration(featureModel.model, true);
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
					.forName("featureide.fm.ui.editors.configuration.ConfigurationLabelProvider");
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
