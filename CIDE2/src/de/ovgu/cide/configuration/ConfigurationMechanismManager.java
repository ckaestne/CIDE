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

package de.ovgu.cide.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.configuration.defaultconf.DefaultConfigurationMechanism;
import de.ovgu.cide.features.source.ColoredSourceFile;

public class ConfigurationMechanismManager {

	private static ConfigurationMechanismManager instance;

	private ConfigurationMechanismManager() {
	}

	public static ConfigurationMechanismManager getInstance() {
		if (instance == null)
			instance = new ConfigurationMechanismManager();
		return instance;
	}

	private List<IConfigurationMechanism> configurationMechanisms = null;

	private void loadFeatureModelProvider() {
		if (configurationMechanisms != null)
			return;
		configurationMechanisms = new ArrayList<IConfigurationMechanism>();

		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(CIDECorePlugin.ID, "configurationMechanism")
				.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurationElements = extension
					.getConfigurationElements();
			for (IConfigurationElement configurationElement : configurationElements)
				if (configurationElement.getName().equals(
						"configurationMechanism"))
					try {
						IConfigurationMechanism mechanism = (IConfigurationMechanism) configurationElement
								.createExecutableExtension("mechanism");
						configurationMechanisms.add(mechanism);
					} catch (CoreException e) {
						e.printStackTrace();
					}

		}
		// sort
		Collections.sort(configurationMechanisms,
				new Comparator<IConfigurationMechanism>() {
					public int compare(IConfigurationMechanism o1,
							IConfigurationMechanism o2) {
						if (o1.getPriority() > o2.getPriority())
							return -1;
						return 1;
					}
				});

	}

	/**
	 * list sorted by priority. highest priority first
	 * 
	 * @return
	 */
	public List<IConfigurationMechanism> getConfigurationMechanisms() {
		loadFeatureModelProvider();

		return Collections.unmodifiableList(configurationMechanisms);
	}

	/**
	 * returns the suitable mechanism for a file. must not return null (returns
	 * the default mechanism if no custom mechanism is provided)
	 * 
	 * @param file
	 * @return
	 */
	public IConfigurationMechanism getConfigurationMechanism(
			ColoredSourceFile file) {
		List<IConfigurationMechanism> mechanisms = getConfigurationMechanisms();
		for (IConfigurationMechanism mechanism : mechanisms)
			if (mechanism.canConfigureFile(file))
				return mechanism;
		return new DefaultConfigurationMechanism();
	}

}
