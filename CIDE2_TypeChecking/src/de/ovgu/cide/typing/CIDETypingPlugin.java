package de.ovgu.cide.typing;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import de.ovgu.cide.typing.internal.TypingManager;

/**
 * The activator class controls the plug-in life cycle
 */
public class CIDETypingPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.ovgu.cide.typing";

	// The shared instance
	private static CIDETypingPlugin plugin;

	private TypingManager typingManager;

	/**
	 * The constructor
	 */
	public CIDETypingPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		System.out.println("starting CIDE type system");
		plugin = this;
		typingManager = new TypingManager();
		typingManager.register();

		// initial type-check
		CIDETypingPlugin.getDefault().getTypingManager().recheckProjects(
				ResourcesPlugin.getWorkspace().getRoot().getProjects());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		typingManager.unregister();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static CIDETypingPlugin getDefault() {
		return plugin;
	}

	public TypingManager getTypingManager() {
		return typingManager;
	}

}
