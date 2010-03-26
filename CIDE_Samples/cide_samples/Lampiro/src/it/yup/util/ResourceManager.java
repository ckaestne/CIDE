/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: ResourceManager.java 1539 2009-05-25 21:05:01Z luca $
*/

package it.yup.util;

import it.yup.xmpp.XMPPClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.AlertType;

public class ResourceManager {

	private static ResourceManager manager = null;

	private Hashtable resources = new Hashtable();

	private ResourceManager(String name, String locale) {
// #ifndef GLIDER
					InputStream is = this.getClass().getResourceAsStream("/" + name + "." + locale);
			// #endif
			try {
				int b;
				StringBuffer buf = new StringBuffer();
				while ((b = is.read()) != -1) {
					char c = (char) b;
					if (c == '\n') {
						Vector tokens = Utils.tokenize(buf.toString(), '\t');
						resources.put(tokens.elementAt(0), tokens.elementAt(1));
						buf.delete(0, buf.length());
					} else {
						buf.append(c);
					}
				}
				is.close();
			} catch (IOException e) {
				// XXX we should launch an exception and trap it outside, without using the XMPPClient
				XMPPClient.getInstance().showAlert(AlertType.INFO,
						"Resource Manager Error",
						"Can't read resources:\n" + e.getMessage(), null);

			}
	}

	public static ResourceManager getManager(String name, String locale) {
		if (ResourceManager.manager == null) {
			manager = new ResourceManager(name, locale);
		}
		return manager;
	}

	public String getString(int id) {
		return (String) resources.get("" + id);
	}
}
