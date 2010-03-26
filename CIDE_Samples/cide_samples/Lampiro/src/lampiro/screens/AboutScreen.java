/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: AboutScreen.java 1552 2009-05-28 14:46:49Z luca $
*/

package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIScreen;
import it.yup.ui.UIUtils;
import it.yup.xmpp.Config;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class AboutScreen extends UIScreen {

	Image logo;

	private static String[] lines = {
			"Mobile Messaging",
			"",
// #ifndef GLIDER
					"(c) 2007-2009 Bluendo srl",
					"http://www.bluendo.com",
			// #endif
			"",
			Config.getInstance().getProperty(Config.VERSION),
			"Available/Total memory:",
			Runtime.getRuntime().freeMemory() / 1000 + "/"
					+ Runtime.getRuntime().totalMemory() / 1000 + " Kb" };

	private static UILabel cmd_ok = new UILabel("OK");

	public AboutScreen() {
		setTitle("ABOUT");
		try {
// #ifndef GLIDER
						logo = Image.createImage("/icons/lampiro_icon.png");
			// #endif
		} catch (Exception ex) {
		}
		UILabel uimg = new UILabel(logo);
		uimg.setAnchorPoint(Graphics.HCENTER | Graphics.VCENTER);
		append(uimg);
		for (int i = 0; i < lines.length; i++) {
			UILabel ul = new UILabel(lines[i]);
			ul.setAnchorPoint(Graphics.HCENTER | Graphics.VCENTER);
			append(ul);
		}
		setMenu(UIUtils.easyMenu("", -1, -1, -1, cmd_ok));
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_ok) {
			UICanvas.getInstance().show(RosterScreen.getInstance());
			UICanvas.getInstance().close(this);
		}
	}

}
