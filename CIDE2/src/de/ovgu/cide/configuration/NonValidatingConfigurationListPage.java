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

package de.ovgu.cide.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.utils.ColorHelper;

public class NonValidatingConfigurationListPage extends
		AbstractConfigurationPage {

	public NonValidatingConfigurationListPage(String pageName,
			IFeatureModel featureModel) {
		super(pageName, featureModel);
		setPageComplete(true);
	}

	protected Table table;
	protected final HashMap<IFeature, TableItem> featureItems = new HashMap<IFeature, TableItem>();

	@Override
	protected Control createMainControl(Composite composite) {
		table = new Table(composite, SWT.CHECK | SWT.BORDER);

		for (IFeature feature : ColorHelper.sortFeatures(featureModel
				.getFeatures())) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText("Feature: " + feature.getName());
			item.setData(feature);
			item.setChecked(initialSelection != null
					&& initialSelection.contains(feature));
			featureItems.put(feature, item);
		}
		return table;
	}

	@Override
	public Set<IFeature> getSelectedFeatures() {
		Set<IFeature> result = new HashSet<IFeature>();
		for (TableItem item : table.getItems()) {
			if (item.getChecked() && !item.getGrayed())
				result.add((IFeature) item.getData());
		}
		return result;
	}

	@Override
	public Set<IFeature> getNotSelectedFeatures() {
		Set<IFeature> result = new HashSet<IFeature>();
		for (TableItem item : table.getItems()) {
			if (!item.getChecked() && !item.getGrayed())
				result.add((IFeature) item.getData());
		}
		return result;
	}

	@Override
	public String getTitle() {
		return "Select Features for Configuration";
	}
}
