package coloredide.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.FeatureNameManager;

public class WizardPageSelectFeatures extends WizardPage {

	private Table table;

	private IProject project;

	private Set<Feature> initialSelection = Collections.EMPTY_SET;

	private boolean selectAll = false;

	public WizardPageSelectFeatures(String pageName, IProject p) {
		super(pageName);
		this.setTitle("Select Features for Configuration");
		this.project = p;
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

		for (Feature feature : FeatureManager.getVisibleFeatures(project)) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(feature.getName(project));
			item.setData(feature);
			item.setChecked(selectAll || initialSelection.contains(feature));
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
	// private final List<Feature> allFeatures;
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
	// assert item.getData() instanceof Feature;
	// Feature feature = (Feature) item.getData();
	// boolean isChecked = item.getChecked();
	//
	// Feature parentFeature = featureManager.getParentFeature(feature);
	// if (parentFeature != null)
	// if (isChecked) {
	// setChecked(parentFeature, true);
	// setGrayed(parentFeature, true);
	// } else
	// setGrayed(parentFeature, false);
	//
	// for (Feature childFeature : getChildFeatures(feature))
	// if (!isChecked) {
	// setChecked(childFeature, false);
	// setGrayed(childFeature, true);
	// } else
	// setGrayed(childFeature, false);
	// }
	//
	// private void setGrayed(Feature feature, boolean isGrayed) {
	// for (TableItem item : table.getItems()) {
	// if (item.getData() == feature) {
	// item.setGrayed(isGrayed);
	// return;
	// }
	// }
	// }
	//
	// private Set<Feature> getChildFeatures(Feature feature) {
	// // direct and indirect children
	// HashSet<Feature> result = new HashSet<Feature>();
	// result.add(feature);
	// int oldsize = 0, newsize = 1;
	// while (oldsize != newsize) {
	// oldsize = newsize;
	// for (Feature f : allFeatures) {
	// if (result.contains(featureManager.getParentFeature(f)))
	// result.add(f);
	// }
	// newsize = result.size();
	// }
	// result.remove(feature);
	// return result;
	// }
	//
	// private void setChecked(Feature feature, boolean checked) {
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

	public Set<Feature> getSelectedFeatures() {
		Set<Feature> result = new HashSet<Feature>();
		for (TableItem item : table.getItems()) {
			if (item.getChecked())
				result.add((Feature) item.getData());
		}
		return result;
	}

	public void setInitialSelection(Set<Feature> initialSelection) {
		if (initialSelection != null)
			this.initialSelection = initialSelection;
		else
			this.initialSelection = Collections.EMPTY_SET;
	}

	public void selectAll(boolean allSelected) {
		this.selectAll = allSelected;
	}
}
