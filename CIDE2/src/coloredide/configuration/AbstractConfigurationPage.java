package coloredide.configuration;

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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.utils.SelectFeatureSetPage;

/**
 * page that allows you to select a set of features, which is validated against
 * the feature model. only valid feature selections that can be used for the
 * configuration/derivation/generation process may be selected
 * 
 * user has to provide a feature model and receives a feature set. the concrete
 * representation (e.g., how features are shown, how errors are shown) is up to
 * a subclass that implements this page for a concrete type of feature models
 * 
 * @see SelectFeatureSetPage for a related page without validation
 * 
 * @author ckaestne
 * 
 */
public abstract class AbstractConfigurationPage extends WizardPage {

	protected final IFeatureModel featureModel;

	protected Set<IFeature> initialSelection;

	public AbstractConfigurationPage(String pageName, IFeatureModel featureModel) {
		super(pageName);
		this.featureModel = featureModel;
		this.initialSelection = null;
		this.setTitle(getTitle());
	}

	/**
	 * simple template of setting up the dialog with only a single central
	 * control. can be overridden to replace
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		FormLayout layout = new FormLayout();
		layout.marginHeight = layout.marginWidth = 5;
		composite.setLayout(layout);
		Label label = new Label(composite, SWT.NONE);
		label.setText("Select Features:");
		Control mainControl = createMainControl(composite);
		FormData formData = new FormData();
		formData.top = new FormAttachment(label, 5);
		formData.bottom = new FormAttachment(100, 0);
		formData.right = new FormAttachment(100, 0);
		formData.left = new FormAttachment(0, 0);
		mainControl.setLayoutData(formData);

		setControl(composite);
	}

	protected abstract Control createMainControl(Composite composite);

	public abstract Set<IFeature> getSelectedFeatures();

	/**
	 * necessary to distinguish from grayed features
	 * 
	 * @return
	 */
	public abstract Set<IFeature> getNotSelectedFeatures();

	public void setInitialSelection(Set<IFeature> features) {
		initialSelection = features;
	}
}
