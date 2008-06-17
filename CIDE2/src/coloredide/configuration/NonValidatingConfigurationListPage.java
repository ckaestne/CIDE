package coloredide.configuration;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.utils.ColorHelper;

public class NonValidatingConfigurationListPage extends
		AbstractConfigurationPage {

	public NonValidatingConfigurationListPage(String pageName,
			IFeatureModel featureModel) {
		super(pageName, featureModel);
		setPageComplete(true);
	}

	private Table table;

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
