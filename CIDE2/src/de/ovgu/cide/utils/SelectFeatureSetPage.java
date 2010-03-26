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

package de.ovgu.cide.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import de.ovgu.cide.features.IFeature;

/**
 * selects one or more features from a given list without checking validity
 * against a feature model
 * 
 * @author ckaestne
 * 
 */
public class SelectFeatureSetPage extends WizardPage {

	private Table table;

	private Set<IFeature> initialSelected = Collections.EMPTY_SET;
	private Set<IFeature> initialGrayed = Collections.EMPTY_SET;

	private boolean selectAll = false;

	private final List<IFeature> featureList;

	public SelectFeatureSetPage(String pageName, List<IFeature> featureList) {
		super(pageName);
		this.setTitle("Select Features");
		this.featureList = featureList;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		FormLayout layout = new FormLayout();
		layout.marginHeight = layout.marginWidth = 5;
		composite.setLayout(layout);
		Label label = new Label(composite, SWT.NONE);
		label.setText("Select Features:");
		table = new Table(composite, SWT.CHECK | SWT.BORDER);
		FormData formData = new FormData();
		formData.top = new FormAttachment(label, 5);
		formData.bottom = new FormAttachment(100, 0);
		formData.right = new FormAttachment(100, 0);
		formData.left = new FormAttachment(0, 0);
		table.setLayoutData(formData);

		for (IFeature feature : featureList) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText("Feature: " + feature.getName());
			item.setData(feature);
			boolean contains=initialSelected.contains(feature);
			item.setChecked(selectAll || contains);
			if (initialGrayed.contains(feature))
				item.setGrayed(true);
		}

		// SelectionDependencyManager selectionDepManager = new
		// SelectionDependencyManager(project,
		// table);
		// selectionDepManager.updateAll();
		// table.addListener(SWT.Selection, selectionDepManager);

		setControl(composite);
	}

	// private class SelectionDependencyManager implements Listener {
	// private final FeatureNameManager featureManager;
	//
	// private final List<IFeature> allFeatures;
	//
	// private final Table table;
	//
	// public SelectionDependencyManager(IProject project, Table table) {
	// featureManager = FeatureNameManager.getFeatureNameManager(project);
	// allFeatures = FeatureManager.getVisibleFeatures(project);
	// this.table = table;
	// }
	//
	// public void handleEvent(Event event) {
	// if (event.detail != SWT.CHECK)
	// return;
	// assert event.item instanceof TableItem;
	//
	// TableItem item = (TableItem) event.item;
	// updateItem(item);
	// }
	//
	// private void updateItem(TableItem item) {
	// assert item.getData() instanceof IFeature;
	// IFeature feature = (IFeature) item.getData();
	// boolean isChecked = item.getChecked();
	//
	// IFeature parentFeature = featureManager.getParentFeature(feature);
	// if (parentFeature != null)
	// if (isChecked) {
	// setChecked(parentFeature, true);
	// setGrayed(parentFeature, true);
	// } else
	// setGrayed(parentFeature, false);
	//
	// for (IFeature childFeature : getChildFeatures(feature))
	// if (!isChecked) {
	// setChecked(childFeature, false);
	// setGrayed(childFeature, true);
	// } else
	// setGrayed(childFeature, false);
	// }
	//
	// private void setGrayed(IFeature feature, boolean isGrayed) {
	// for (TableItem item : table.getItems()) {
	// if (item.getData() == feature) {
	// item.setGrayed(isGrayed);
	// return;
	// }
	// }
	// }
	//
	// private Set<IFeature> getChildFeatures(IFeature feature) {
	// // direct and indirect children
	// HashSet<IFeature> result = new HashSet<IFeature>();
	// result.add(feature);
	// int oldsize = 0, newsize = 1;
	// while (oldsize != newsize) {
	// oldsize = newsize;
	// for (IFeature f : allFeatures) {
	// if (result.contains(featureManager.getParentFeature(f)))
	// result.add(f);
	// }
	// newsize = result.size();
	// }
	// result.remove(feature);
	// return result;
	// }
	//
	// private void setChecked(IFeature feature, boolean checked) {
	// for (TableItem item : table.getItems()) {
	// if (item.getData() == feature) {
	// item.setChecked(checked);
	// updateItem(item);
	// return;
	// }
	// }
	// }
	//
	// public void updateAll() {
	// for (TableItem item : table.getItems()) {
	// updateItem(item);
	// }
	// }
	// }

	public Set<IFeature> getSelectedFeatures() {
		Set<IFeature> result = new HashSet<IFeature>();
		for (TableItem item : table.getItems()) {
			if (item.getChecked() && !item.getGrayed())
				result.add((IFeature) item.getData());
		}
		return result;
	}

	/**
	 * necessary to distinguish from grayed features
	 * 
	 * @return
	 */
	public Set<IFeature> getNotSelectedFeatures() {
		Set<IFeature> result = new HashSet<IFeature>();
		for (TableItem item : table.getItems()) {
			if (!item.getChecked() && !item.getGrayed())
				result.add((IFeature) item.getData());
		}
		return result;
	}

	public void setInitialSelection(Set<IFeature> selected, Set<IFeature> grayed) {
		if (selected != null)
			this.initialSelected = selected;
		else
			this.initialSelected = Collections.EMPTY_SET;
		if (grayed != null)
			this.initialGrayed = grayed;
		else
			this.initialGrayed = Collections.EMPTY_SET;
	}

	public void setInitialSelection(Set<IFeature> initialSelection) {
		setInitialSelection(initialSelection, null);
	}

	public void selectAll(boolean allSelected) {
		this.selectAll = allSelected;
	}
}
