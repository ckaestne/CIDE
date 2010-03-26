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
