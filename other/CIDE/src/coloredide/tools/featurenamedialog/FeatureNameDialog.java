package coloredide.tools.featurenamedialog;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.wizards.datatransfer.SelectFilesOperation;

import coloredide.ColoredIDEImages;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.FeatureNameManager;
import coloredide.utils.*;

public class FeatureNameDialog extends Dialog {

	public class RequiredFeaturesEditor extends DialogCellEditor {

		public RequiredFeaturesEditor(Table table, int style) {
			super(table, style);
		}

		@Override
		protected Object openDialogBox(Control cellEditorWindow) {
			Set<Feature> oldValue = (Set<Feature>) this.getValue();
			SelectFeatureWizard wizard = new SelectFeatureWizard(project,
					oldValue);
			WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(),
					wizard);
			dialog.create();
			dialog.open();
			return wizard.getSelectedFeatures();
		}

		protected void updateContents(Object value) {
			if (getDefaultLabel() == null) {
				return;
			}

			String text = getFeatureSetString(project, (Set<Feature>) this
					.getValue());
			getDefaultLabel().setText(text);
		}
	}

	protected static String[] columnNames = new String[] { "id", "name",
			"check", "depends-on" };

	public class FeatureCellModifier implements ICellModifier {

		public boolean canModify(Object element, String property) {

			return property != columnNames[0];
		}

		public Object getValue(Object element, String property) {
			Feature f = (Feature) element;
			if (columnNames[1] == property)
				return featureNames.getFeatureName(f);
			if (columnNames[2] == property)
				return new Boolean(featureNames.isFeatureVisible(f));
			if (columnNames[3] == property) {
				return featureNames.getRequiredFeatures(f);
				// Feature parent = featureNames.getParentFeature(f);
				// if (parent == null)
				// return new Integer(0);
				// return new
				// Integer(FeatureManager.getFeatures().indexOf(parent));
			}
			return null;
		}

		public void modify(Object element, String property, Object value) {
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}
			Feature f = (Feature) element;
			if (columnNames[1] == property)
				featureNames.setFeatureName(f, (String) value);
			if (columnNames[2] == property)
				featureNames.setFeatureVisible(f, (Boolean) value);
			if (columnNames[3] == property) {
				featureNames.setRequiredFeatures(f, (Set<Feature>) value);
				// int idx = ((Integer) value).intValue() - 1;
				// if (idx == -1)
				// featureNames.setParentFeature(f, null);
				// else
				// featureNames.setParentFeature(f, FeatureManager
				// .getFeatures().get(idx));
			}
			tablev.refresh(element);
		}

	}

	public class FeatureLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		// Names of images used to represent checkboxes

		public Image getColumnImage(Object element, int columnIndex) {
			Feature f = (Feature) element;
			return (columnIndex == 2) ? ColoredIDEImages
					.getCheckImage(featureNames.isFeatureVisible(f)) : null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Feature f = (Feature) element;
			if (columnIndex == 0)
				return "" + f.id;
			if (columnIndex == 1)
				return featureNames.getFeatureName(f);
			if (columnIndex == 3) {
				Set<Feature> requiredFeatures = featureNames
						.getRequiredFeatures(f);
				return getFeatureSetString(project, requiredFeatures);
			}
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

	protected FeatureNameDialog(Shell parentShell, IProject project) {
		super(parentShell);
		this.project = project;
		this.featureNames = FeatureNameManager.getFeatureNameManager(project);
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

	protected static String getFeatureSetString(IProject project,
			Set<Feature> features) {
		if (features == null || features.isEmpty())
			return "- none -";
		String result = "";
		for (Feature f : features) {
			if (result.length() > 0)
				result += ", ";
			result += f.getShortName(project);
		}
		return result;
	}
}
