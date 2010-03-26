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

package de.ovgu.cide.fm.list.editor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import de.ovgu.cide.ColoredIDEImages;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.fm.list.FeatureAdapter;
import de.ovgu.cide.fm.list.FeatureManager;
import de.ovgu.cide.fm.list.FeatureNameManager;
import de.ovgu.cide.fm.list.FixedFeature;
import de.ovgu.cide.fm.list.ListFeatureModel;
import de.ovgu.cide.utils.ColorHelper;
import de.ovgu.cide.utils.SelectFeatureSetWizard;

public class FeatureNameDialog extends Dialog {

	public class RequiredFeaturesEditor extends DialogCellEditor {

		public RequiredFeaturesEditor(Table table, int style) {
			super(table, style);
		}

		@Override
		protected Object openDialogBox(Control cellEditorWindow) {
			Set<IFeature> oldValue = new HashSet<IFeature>();
			for (FixedFeature f : (Set<FixedFeature>) this.getValue())
				oldValue.add(new FeatureAdapter(f, featureModel));
			SelectFeatureSetWizard wizard = new SelectFeatureSetWizard(
					ColorHelper.sortFeatures(featureModel.getFeatures()),
					oldValue);
			WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(),
					wizard);
			dialog.create();
			dialog.open();
			Set<IFeature> resultI = wizard.getSelectedFeatures();
			Set<FixedFeature> result = new HashSet<FixedFeature>();
			for (IFeature i : resultI)
				result.add(((FeatureAdapter) i).feature);
			return result;
		}

		protected void updateContents(Object value) {
			if (getDefaultLabel() == null) {
				return;
			}

			String text = getFeatureSetString(project, (Set<FixedFeature>) this
					.getValue());
			getDefaultLabel().setText(text);
		}
	}

	public class FeaturesColorEditor extends DialogCellEditor {

		public FeaturesColorEditor(Table table, int style) {
			super(table, style);
		}

		@Override
		protected Object openDialogBox(Control cellEditorWindow) {
			RGB oldColor = (RGB) this.getValue();

			ColorDialog colorDialog = new ColorDialog(cellEditorWindow
					.getShell());
			colorDialog.setRGB(oldColor);
			colorDialog.setText("Select Color");
			return colorDialog.open();
		}

		protected void updateContents(Object value) {
			if (getDefaultLabel() == null) {
				return;
			}

			String text = ColorHelper.rgb2str((RGB) this.getValue());
			getDefaultLabel().setText(text);
		}
	}

	protected static String[] columnNames = new String[] { "id", "name",
			"check", "depends-on", "color" };

	public class FeatureCellModifier implements ICellModifier {

		public boolean canModify(Object element, String property) {

			return property != columnNames[0];
		}

		public Object getValue(Object element, String property) {
			FixedFeature f = (FixedFeature) element;
			if (columnNames[1] == property)
				return featureNames.getFeatureName(f);
			if (columnNames[2] == property)
				return new Boolean(featureNames.isFeatureVisible(f));
			if (columnNames[3] == property)
				return featureNames.getRequiredFeatures(f);
			if (columnNames[4] == property)
				return featureNames.getFeatureColor(f);
			// Feature parent = featureNames.getParentFeature(f);
			// if (parent == null)
			// return new Integer(0);
			// return new
			// Integer(FeatureManager.getFeatures().indexOf(parent));

			return null;
		}

		public void modify(Object element, String property, Object value) {
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}
			FixedFeature f = (FixedFeature) element;
			if (columnNames[1] == property)
				featureNames.setFeatureName(f, (String) value);
			if (columnNames[2] == property)
				featureNames.setFeatureVisible(f, (Boolean) value);
			if (columnNames[3] == property) {
				featureNames.setRequiredFeatures(f, (Set<FixedFeature>) value);
				// int idx = ((Integer) value).intValue() - 1;
				// if (idx == -1)
				// featureNames.setParentFeature(f, null);
				// else
				// featureNames.setParentFeature(f, FeatureManager
				// .getFeatures().get(idx));
			}
			if (columnNames[4] == property)
				featureNames.setFeatureColor(f, (RGB) value);
			tablev.refresh(element);
		}

	}

	public class FeatureLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		// Names of images used to represent checkboxes

		public Image getColumnImage(Object element, int columnIndex) {
			FixedFeature f = (FixedFeature) element;
			return (columnIndex == 2) ? ColoredIDEImages
					.getCheckImage(featureNames.isFeatureVisible(f)) : null;
		}

		public String getColumnText(Object element, int columnIndex) {
			FixedFeature f = (FixedFeature) element;
			if (columnIndex == 0)
				return "" + f.getId();
			if (columnIndex == 1)
				return featureNames.getFeatureName(f);
			if (columnIndex == 3) {
				Set<FixedFeature> requiredFeatures = featureNames
						.getRequiredFeatures(f);
				return getFeatureSetString(project, requiredFeatures);
			}
			if (columnIndex == 4)
				return ColorHelper.rgb2str(featureNames.getFeatureColor(f));
			return "";
		}

	}

	public class FeatureContentProvider implements IStructuredContentProvider {

		public void dispose() {
			// TODO Auto-generated method stub

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub

		}

		public Object[] getElements(Object inputElement) {
			return FeatureManager.getFeatures().toArray();
		}

	}

	private TableViewer tablev;

	private IProject project;

	private FeatureNameManager featureNames;

	private ListFeatureModel featureModel;

	protected FeatureNameDialog(Shell parentShell, IProject project) {
		super(parentShell);
		this.project = project;
		this.featureModel = ListFeatureModel.getInstance(project);
		this.featureNames = featureModel.getFeatureNameManager();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = (Composite) super.createDialogArea(parent);

		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
		Table table = new Table(comp, style);
		GridData gd;
		table.setLayoutData(gd = new GridData(GridData.FILL_BOTH));
		gd.heightHint = 400;
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn columnID = new TableColumn(table, SWT.LEFT);
		columnID.setText("ID");
		columnID.setWidth(50);
		TableColumn columnName = new TableColumn(table, SWT.LEFT);
		columnName.setText("Name");
		columnName.setWidth(300);
		TableColumn columnVisible = new TableColumn(table, SWT.LEFT);
		columnVisible.setText("Visible");
		columnVisible.setWidth(50);
		TableColumn columnParent = new TableColumn(table, SWT.LEFT);
		columnParent.setText("Parent");
		columnParent.setWidth(100);
		TableColumn columnColor = new TableColumn(table, SWT.LEFT);
		columnColor.setText("Color");
		columnColor.setWidth(60);

		tablev = new TableViewer(table);
		tablev.setColumnProperties(columnNames);

		// Create the cell editors
		CellEditor[] editors = new CellEditor[columnNames.length];

		// Column 1 : Completed (Checkbox)
		editors[2] = new CheckboxCellEditor(tablev.getTable());

		// Column 2 : Description (Free text)
		TextCellEditor textEditor = new TextCellEditor(tablev.getTable());
		((Text) textEditor.getControl()).setTextLimit(60);
		editors[1] = textEditor;

		editors[0] = textEditor;

		editors[3] = new RequiredFeaturesEditor(tablev.getTable(),
				SWT.READ_ONLY);
		editors[4] = new FeaturesColorEditor(tablev.getTable(), SWT.READ_ONLY);

		tablev.setCellEditors(editors);
		// tablev.setCellModifier(new ExampleCellModifier(this));

		tablev.setContentProvider(new FeatureContentProvider());
		tablev.setLabelProvider(new FeatureLabelProvider());
		tablev.setCellModifier(new FeatureCellModifier());
		tablev.setInput(FeatureManager.class);

		return comp;
	}

	// private String[] getFeatureIDStrings() {
	// List<Feature> f = FeatureManager.getFeatures();
	// String[] result = new String[f.size() + 1];
	// result[0] = "none";
	// for (int idx = 0; idx < f.size(); idx++) {
	// result[idx + 1] = f.get(idx).id + " ("
	// + f.get(idx).getShortName(project) + ")";
	// }
	// return result;
	// }

	protected void createButtonsForButtonBar(Composite parent) {
		// create OK and Cancel buttons by default
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}

	protected String getFeatureSetString(IProject project,
			Set<FixedFeature> features) {
		if (features == null || features.isEmpty())
			return "- none -";
		String result = "";
		for (FixedFeature f : features) {
			if (result.length() > 0)
				result += ", ";
			result += featureNames.getFeatureName(f);
		}
		return result;
	}
}
