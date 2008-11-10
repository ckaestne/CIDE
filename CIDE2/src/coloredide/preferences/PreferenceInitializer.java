package coloredide.preferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import coloredide.CIDECorePlugin;
import coloredide.features.FeatureModelManager;
import coloredide.features.FeatureModelProviderProxy;
import coloredide.languages.LanguageExtensionManager;
import coloredide.languages.LanguageExtensionProxy;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = CIDECorePlugin.getDefault()
				.getPreferenceStore();
		Set<String> supportedFileExtensions = new HashSet<String>();
		for (LanguageExtensionProxy language : LanguageExtensionManager
				.getInstance().getAllLanguageExtensions()) {

			boolean languageEnabled = true;
			for (String fileExtension : language.getFileExtensions()) {
				if (supportedFileExtensions.contains(fileExtension)) {
					languageEnabled = false;
				} else
					supportedFileExtensions.add(fileExtension);
			}
			store.setDefault(PreferenceConstants.P_LANGPREFIX
					+ language.getId(), languageEnabled);
		}

		String defaultModel = "de.ovgu.cide.fm.list";
		List<FeatureModelProviderProxy> providers = FeatureModelManager
				.getInstance().getFeatureModelProviders();
		for (FeatureModelProviderProxy provider : providers)
			if (provider.getId().equals("de.ovgu.cide.fm.guidsl"))
				defaultModel = "de.ovgu.cide.fm.guidsl";
		store.setDefault(PreferenceConstants.P_FEATUREMODELPROVIDER,
				defaultModel);
	}

}
