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

package de.ovgu.cide.languages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ExtensionPointManager;

public class LanguageExtensionManager extends ExtensionPointManager<LanguageExtensionProxy>{
	private static LanguageExtensionManager instance;

	private LanguageExtensionManager() {
		super(CIDECorePlugin.ID, "language");
	}

	public static LanguageExtensionManager getInstance() {
		if (instance == null)
			instance = new LanguageExtensionManager();
		return instance;
	}


	protected LanguageExtensionProxy parseExtension(
			IConfigurationElement configurationElement) {
		if (!configurationElement.getName().equals("language"))
			return null;
		return new LanguageExtensionProxy(configurationElement);
	}

	public List<LanguageExtensionProxy> getAllLanguageExtensions() {
		return getProviders();
	}

	/**
	 * returns only those language extensions which are enabled in the user
	 * preferences.
	 * 
	 * @return
	 */
	public List<LanguageExtensionProxy> getEnabledLanguageExtensions() {
		List<LanguageExtensionProxy> result = new ArrayList<LanguageExtensionProxy>();
		for (LanguageExtensionProxy lang : getProviders())
			if (lang.isEnabled())
				result.add(lang);
		return Collections.unmodifiableList(result);
	}

	/**
	 * get a language extension by an id, independent of whether it is enabled
	 * 
	 * @param languageExtensionId
	 * @return
	 */
	public LanguageExtensionProxy getLanguageExtensionById(
			String languageExtensionId) {
		for (LanguageExtensionProxy lang : getAllLanguageExtensions()) {
			if (lang.getId().equals(languageExtensionId))
				return lang;
		}
		return null;
	}
}
