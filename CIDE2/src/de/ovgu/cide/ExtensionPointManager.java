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

package de.ovgu.cide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

public abstract class ExtensionPointManager<T> {

	private final String namespace;
	private final String extensionPointName;

	protected ExtensionPointManager(String namespace, String extensionPointName) {
		this.namespace = namespace;
		this.extensionPointName = extensionPointName;
	}

	private List<T> cachedProviders = null;

	private void loadProviders() {
		if (cachedProviders != null)
			return;
		cachedProviders = new ArrayList<T>();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(namespace, extensionPointName)
				.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurationElements = extension
					.getConfigurationElements();
			for (IConfigurationElement configurationElement : configurationElements) {
				T proxy = parseExtension(configurationElement);
				if (proxy != null)
					cachedProviders.add(proxy);
			}
		}
		debugPrintExtensions();
	}

	private void debugPrintExtensions() {
		for (T le : getProviders())
			System.out.println(le);
	}

	protected abstract T parseExtension(
			IConfigurationElement configurationElement);

	protected List<T> getProviders() {
		loadProviders();
		return Collections.unmodifiableList(cachedProviders);
	}

}
