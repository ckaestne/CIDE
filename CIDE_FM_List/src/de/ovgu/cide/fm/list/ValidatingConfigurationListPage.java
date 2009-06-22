package de.ovgu.cide.fm.list;

import java.util.Set;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;

import de.ovgu.cide.configuration.NonValidatingConfigurationListPage;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

/**
 * extends the basic page with limited form of validation: checks the
 * parent-child relationship. if child is selected, parent must be selected as
 * well.
 * 
 * @author ckaestne
 * 
 */
public class ValidatingConfigurationListPage extends
		NonValidatingConfigurationListPage {

	public ValidatingConfigurationListPage(String pageName,
			IFeatureModel featureModel) {
		super(pageName, featureModel);
	}

	@Override
	protected Control createMainControl(Composite composite) {
		Control c = super.createMainControl(composite);

		table.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			public void widgetSelected(SelectionEvent e) {
				checkList();
			}
		});

		checkList();

		return c;
	}

	protected void checkList() {
		boolean isOk = true;
		String problem = "";

		for (IFeature feature : featureItems.keySet()) {
			if (isOk && featureItems.get(feature).getChecked()) {
				Set<IFeature> requiredFeatures = feature.getRequiredFeatures();
				for (IFeature requiredFeature : requiredFeatures) {
					TableItem requiredFeatureItem = featureItems
							.get(requiredFeature);
					if (requiredFeatureItem != null) {
						if (!requiredFeatureItem.getChecked()) {
							isOk = false;
							problem = "" + feature.getName() + " requires "
									+ requiredFeature.getName();
						}
					}
				}
			}
		}

		this.setPageComplete(isOk);

		this.setErrorMessage(isOk ? null : "Selection is invalid: " + problem);

	}
}
