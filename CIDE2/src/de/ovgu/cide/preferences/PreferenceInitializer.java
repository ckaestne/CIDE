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

package de.ovgu.cide.preferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelProviderProxy;
import de.ovgu.cide.languages.LanguageExtensionManager;
import de.ovgu.cide.languages.LanguageExtensionProxy;

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
