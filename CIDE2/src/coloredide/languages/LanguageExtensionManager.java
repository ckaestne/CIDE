package coloredide.languages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

import coloredide.CIDECorePlugin;

public class LanguageExtensionManager {
	private static LanguageExtensionManager instance;

	private LanguageExtensionManager() {
	}

	public static LanguageExtensionManager getInstance() {
		if (instance == null)
			instance = new LanguageExtensionManager();
		return instance;
	}

	private List<LanguageExtensionProxy> cachedLanguageExtensions = null;

	private void loadLanguageExtensions() {
		if (cachedLanguageExtensions != null)
			return;
		cachedLanguageExtensions = new ArrayList<LanguageExtensionProxy>();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(CIDECorePlugin.ID, "language")
				.getExtensions();
		for (IExtension extension : extensions) {
			IConfigurationElement[] configurationElements = extension
					.getConfigurationElements();
			for (IConfigurationElement configurationElement : configurationElements) {
				LanguageExtensionProxy proxy = parseExtension(configurationElement);
				if (proxy != null)
					cachedLanguageExtensions.add(proxy);
			}
		}
		debugPrintExtensions();
	}

	private void debugPrintExtensions() {
		for (LanguageExtensionProxy le : LanguageExtensionManager.getInstance()
				.getLanguageExtensions())
			System.out.println(le);
	}

	private LanguageExtensionProxy parseExtension(
			IConfigurationElement configurationElement) {
		if (!configurationElement.getName().equals("language"))
			return null;
		return new LanguageExtensionProxy(configurationElement);
	}

	public List<LanguageExtensionProxy> getLanguageExtensions() {
		loadLanguageExtensions();
		return Collections.unmodifiableList(cachedLanguageExtensions);
	}
}
