/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SplashScreen.java 1539 2009-05-25 21:05:01Z luca $
*/

package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UIConfig;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UILayout;
import it.yup.ui.UIMenu;
import it.yup.ui.UIScreen;
import it.yup.ui.UITextField;
import it.yup.ui.UIUtils;
import it.yup.ui.UIVLayout;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xmpp.Config;

import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class SplashScreen extends UIScreen {

	// In this screen it is hard to use FIRE and Menu keys
	// since the software may be uniitialized so
	// lots of shortcuts and controls are handled manually like this
	class UISplashTextField extends UITextField {
		public UISplashTextField(String label, String text, int maxSize,
				int constraints) {
			super(label, text, maxSize, constraints);
		}

		public boolean keyPressed(int key) {
			boolean retVal = super.keyPressed(key);
			int ga = UICanvas.getInstance().getGameAction(key);
			if (ga == Canvas.FIRE) { return false; }
			return retVal;
		}
	};

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private UIMenu helpMenu;

	private UISplashTextField helpField = null;

	//private UIButton close;

	public SplashScreen() {
		try {
			/*
			 * Load the configuration in UIConfig
			 */
			UIConfig.cancelMenuString = rm.getString(ResourceIDs.STR_CANCEL)
					.toUpperCase();
			UIConfig.selectMenuString = rm.getString(ResourceIDs.STR_SELECT)
					.toUpperCase();
			UIConfig.menuString = rm.getString(ResourceIDs.STR_MENU)
					.toUpperCase();
			UIConfig.optionsString = rm.getString(ResourceIDs.STR_OPTIONS)
					.toUpperCase();

			UIVLayout uvl = new UIVLayout(4, -1);

			UILabel dummyLabel = new UILabel("");
			uvl.insert(dummyLabel, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);

			// #ifndef GLIDER
			setTitle("Lampiro");
			Image logo = Image.createImage("/icons/lampiro_icon.png");
			UILabel ul = new UILabel("Loading Lampiro...");
			// #endif
			UILabel up = new UILabel(logo);
			up.setAnchorPoint(Graphics.HCENTER | Graphics.VCENTER);
			uvl.insert(up, 1, logo.getHeight()+10, UILayout.CONSTRAINT_PIXELS);

			ul.setAnchorPoint(Graphics.HCENTER | Graphics.VCENTER);
			uvl.insert(ul, 2, UIConfig.font_body.getHeight(),
					UILayout.CONSTRAINT_PIXELS);

			uvl.insert(dummyLabel, 3, 50, UILayout.CONSTRAINT_PERCENTUAL);

			append(uvl);

		} catch (Exception ex) {
		}

		Utils.tasks.schedule(new TimerTask() {
			public void run() {
				checkKeys();
			}
		}, 3000);
	}

	public boolean keyPressed(int kc) {
		if (helpMenu == null) return super.keyPressed(kc);

		int ga = UICanvas.getInstance().getGameAction(kc);
		switch (ga) {
			case Canvas.UP:
			case Canvas.DOWN:
			case Canvas.LEFT:
			case Canvas.RIGHT:
				return super.keyPressed(kc);
			case Canvas.FIRE:
				this.removePopup(this.helpMenu);
				this.helpMenu = null;
				UICanvas.getInstance().open(RegisterScreen.getInstance(), true);
				UICanvas.getInstance().close(SplashScreen.this);
				return true;
		}
		return super.keyPressed(kc);
	}

	private void checkKeys() {
		int q;
		String keys = Config.getInstance().getProperty(Config.CANVAS_KEYS);
		if (keys != null && (q = keys.indexOf(',')) != -1) {
			int l = Integer.parseInt(keys.substring(0, q));
			int r = Integer.parseInt(keys.substring(q + 1));
			UICanvas.setMenuKeys(l, r);
			UICanvas.getInstance().open(RegisterScreen.getInstance(), true);
			UICanvas.getInstance().close(SplashScreen.this);
		} else {
			// save actual configuration
			//SplashScreen.this.close = new UIButton(rm.getString(ResourceIDs.STR_CLOSE));
			keys = UICanvas.MENU_LEFT + "," + UICanvas.MENU_RIGHT;
			Config.getInstance().setProperty(Config.CANVAS_KEYS, keys);
			Config.getInstance().saveToStorage();

			String help = rm.getString(ResourceIDs.STR_KEY_HELP);
			help = help.replace('<', '\n');

			helpField = new UISplashTextField("", help, help.length(),
					TextField.UNEDITABLE);
			helpField.setWrappable(true);
			helpField.setAutoUnexpand(false);
			helpField.setExpandable(false);

			helpMenu = UIUtils.easyMenu(rm.getString(ResourceIDs.STR_HELP), 1,
					20, UICanvas.getInstance().getWidth() - 2, helpField);
			helpMenu.selectMenuString = "";
			((UIItem) helpMenu.getItemList().elementAt(0)).setFocusable(true);
			helpMenu.setSelectedIndex(1);
			helpMenu.cancelMenuString = "";
			//UIHLayout uhl = UIHLayout.easyCenterLayout(close, 80);
			//helpMenu.append(uhl);
			this.addPopup(helpMenu);
			this.askRepaint();
			helpField.expand();
		}
	}

	public void menuAction(UIMenu menu, UIItem c) {
		if (menu == this.helpMenu) {
			UICanvas.getInstance().open(RegisterScreen.getInstance(), true);
			UICanvas.getInstance().close(SplashScreen.this);
		}
	}
}
