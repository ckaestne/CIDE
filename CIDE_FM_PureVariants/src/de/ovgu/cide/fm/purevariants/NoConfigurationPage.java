package de.ovgu.cide.fm.purevariants;

import java.util.Collections;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.ovgu.cide.configuration.AbstractConfigurationPage;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public class NoConfigurationPage extends AbstractConfigurationPage {

	public NoConfigurationPage(String pageName, IFeatureModel featureModel) {
		super(pageName, featureModel);
		this
				.setErrorMessage("Not available. Use pure::variant transformation mechanism instead.");
	}

	@Override
	protected Control createMainControl(Composite composite) {
		return composite;
	}

	@Override
	public Set<IFeature> getNotSelectedFeatures() {
		return Collections.emptySet();
	}

	@Override
	public Set<IFeature> getSelectedFeatures() {
		return Collections.emptySet();
	}

}
