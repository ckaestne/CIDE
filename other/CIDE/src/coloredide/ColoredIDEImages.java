/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package coloredide;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class ColoredIDEImages {

	private static final IPath ICONS_PATH = new Path("$nl$/icons"); //$NON-NLS-1$

	public static final String COLLAPSE = "collapseall.gif"; //$NON-NLS-1$

	public static final String EXPAND = "expandall.gif"; //$NON-NLS-1$

	public static final String LINK_WITH_EDITOR = "synced.gif"; //$NON-NLS-1$

	public static final String SETFOCUS = "setfocus.gif"; //$NON-NLS-1$

	public static final String REFRESH = "refresh.gif"; //$NON-NLS-1$

	public static final String CLEAR = "clear.gif"; //$NON-NLS-1$

	public static final String ADD_TO_TRAY = "add.gif"; //$NON-NLS-1$

	public static final String CHECKED_IMAGE = "checked.gif";

	public static final String UNCHECKED_IMAGE = "unchecked.gif";

	public static final String COLOREDJ = "coloredj.gif"; //$NON-NLS-1$

	// ---- Helper methods to access icons on the file system
	// --------------------------------------

	public static void setImageDescriptors(IAction action, String type) {
		ImageDescriptor id = create("d", type); //$NON-NLS-1$
		if (id != null)
			action.setDisabledImageDescriptor(id);

		id = create("c", type); //$NON-NLS-1$
		if (id != null) {
			action.setHoverImageDescriptor(id);
			action.setImageDescriptor(id);
		} else {
			action.setImageDescriptor(ImageDescriptor
					.getMissingImageDescriptor());
		}
	}

	private static ImageDescriptor create(String prefix, String name) {
		IPath path = ICONS_PATH.append(prefix).append(name);
		return createImageDescriptor(ColoredIDEPlugin.getDefault().getBundle(),
				path);
	}

	/*
	 * Since 3.1.1. Load from icon paths with $NL$
	 */
	public static ImageDescriptor createImageDescriptor(Bundle bundle,
			IPath path) {
		URL url = FileLocator.find(bundle, path, null);
		if (url != null) {
			return ImageDescriptor.createFromURL(url);
		}
		return null;
	}

	public static Image getCheckImage(boolean isSelected) {
		String key = isSelected ? CHECKED_IMAGE : UNCHECKED_IMAGE;

		return getImage(key);
	}

	public static Image getImage(String key) {
		Image i = imageRegistry.get(key);
		if (i == null) {
			imageRegistry.put(key, create("", key));
			return imageRegistry.get(key);
		}
		return i;
	}

	// For the checkbox images
	private static ImageRegistry imageRegistry = new ImageRegistry();

}
