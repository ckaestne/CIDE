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

package de.ovgu.cide.typing.internal.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;

import de.ovgu.cide.ExtensionPointManager;
import de.ovgu.cide.typing.CIDETypingPlugin;
import de.ovgu.cide.typing.model.ITypingCheckListener;
import de.ovgu.cide.typing.model.ITypingProvider;

/**
 * organizes all providers per project and collects checks
 * 
 * @author ckaestne
 * 
 */
public class TypingExtensionManager extends
		ExtensionPointManager<TypingExtensionProxy> {

	private static TypingExtensionManager instance;

	private TypingExtensionManager() {
		super(CIDETypingPlugin.PLUGIN_ID, "typingProvider");
	}

	public static TypingExtensionManager getInstance() {
		if (instance == null)
			instance = new TypingExtensionManager();
		return instance;
	}

	protected TypingExtensionProxy parseExtension(
			IConfigurationElement configurationElement) {
		if (!configurationElement.getName().equals("typingProvider"))
			return null;
		return new TypingExtensionProxy(configurationElement);
	}

	private WeakHashMap<IProject, List<ITypingProvider>> typingProviderCache = new WeakHashMap<IProject, List<ITypingProvider>>();

	public List<ITypingProvider> getTypingProviders(IProject project) {
		List<ITypingProvider> typingProviders = typingProviderCache
				.get(project);
		if (typingProviders == null) {
			typingProviders = new ArrayList<ITypingProvider>(getProviders()
					.size());
			for (TypingExtensionProxy proxy : getProviders()) {
				ITypingProvider typingProvider = proxy
						.createTypingProvider(project);
				typingProviders.add(typingProvider);
			}
			typingProviderCache.put(project, typingProviders);
		}
		return typingProviders;
	}

	/**
	 * register listeners but avoid double registration
	 * 
	 * @param providers
	 * @param listener
	 */
	public static void registerListener(List<ITypingProvider> providers,
			ITypingCheckListener listener) {
		for (ITypingProvider provider : providers) {
			provider.removeTypingCheckListener(listener);
			provider.addTypingCheckListener(listener);
		}
	}

}
