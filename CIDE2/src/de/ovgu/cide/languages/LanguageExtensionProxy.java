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

import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.preferences.PreferenceConstants;

public class LanguageExtensionProxy implements ILanguageExtension {

	private final IConfigurationElement configElement;

	public LanguageExtensionProxy(IConfigurationElement configurationElement) {
		this.configElement = configurationElement;
		String attribute = configElement.getAttribute("fileExtensions");
		if (attribute == null)
			fileExtensions = new String[0];
		else
			fileExtensions = attribute.split(",");
		name = configElement.getAttribute("name");
		id = configElement.getAttribute("id");
	}

	private final String[] fileExtensions;
	private final String name;
	private final String id;
	private ILanguageExtension target = null;
	
	/**
	 * Nicht schön, aber nützlich :-)
	 * @return
	 */
	public ILanguageExtension getTarget() {
		return target;
	}

	public String[] getFileExtensions() {
		return fileExtensions;
	}

	public ILanguageParser getParser(InputStream inputStream, String filePath) {
		if (target == null)
			loadTarget();
		return target.getParser(inputStream, filePath);
	}

	// public ILanguagePrintVisitor getPrettyPrinter() {
	// if (target == null)
	// loadTarget();
	// return target.getPrettyPrinter();
	// }

	private void loadTarget() {
		try {
			target = (ILanguageExtension) configElement
					.createExecutableExtension("class");
			assert id.equals(target.getId()) : "Id of language extension does not match its declaration";
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Language Extension: " + name + " (" + id + ") : "
				+ printFileExtensions(", ");
	}

	public String printFileExtensions(String separator) {
		if (fileExtensions.length == 0)
			return "-";
		String result = fileExtensions[0];
		for (int i = 1; i < fileExtensions.length; i++) {
			result += separator + fileExtensions[i];
		}
		return result;
	}

	// public ILanguageValidator getValidator() {
	// if (target == null)
	// loadTarget();
	// return target.getValidator();
	// }

	public String getName() {
		return name;
	}

	public String getId() {
		if (target != null)
			return target.getId();
		return id;
	}

	/**
	 * of the loaded language extensions only a certain set is enabled in the
	 * user preferences. this method returns whether the given extension is
	 * enabled.
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return CIDECorePlugin.getDefault().getPreferenceStore().getBoolean(
				PreferenceConstants.P_LANGPREFIX + getId());
	}
}
