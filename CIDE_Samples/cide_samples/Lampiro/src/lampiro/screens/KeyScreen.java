/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SplashScreen.java 846 2008-09-11 12:20:05Z luca $
*/

package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UIConfig;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIScreen;
import it.yup.ui.UIUtils;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Config;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class KeyScreen extends UIScreen {

	private static final byte STATE_SPLASH = 0;
	private static final byte STATE_LEFT_WAIT = 1;
	private static final byte STATE_LEFT_OK = 2;
	private static final byte STATE_RIGHT_WAIT = 3;
	private static final byte STATE_RIGHT_OK = 4;
	private static final byte STATE_WAIT_DONE = 5;

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private UILabel txt = null;
	private UILabel err = null;

	private byte status = STATE_SPLASH;
	private int lkey;
	private int rkey;

	private UIMenu confMenu;
	private int oldFg = UIConfig.menu_title;

	public KeyScreen() {
		// to simulate an empty footer!!!
		UIConfig.menu_title = UIConfig.header_bg;
		this.selectMenuString = "";
		this.cancelMenuString = "";
		txt = new UILabel("");
		err = new UILabel("");
		txt.setWrappable(true, UICanvas.getInstance().getWidth() - 10);
		Font xFont = UIConfig.font_body;
		Font lFont = Font.getFont(xFont.getFace(), Font.STYLE_BOLD, xFont
				.getSize());
		txt.setFont(lFont);
	}

	public void checkKeys() {
		status = STATE_LEFT_WAIT;
		removeAll();
		setTitle("Configuration");
		txt.setText(rm.getString(ResourceIDs.STR_KEY_SELECT_LEFT) + " "
				+ rm.getString(ResourceIDs.STR_KEY_CONFIRM));
		append(txt);
		append(err);
		askRepaint();

		UILabel confLabel = new UILabel(rm.getString(ResourceIDs.STR_KEY_PRESS));
		confMenu = UIUtils.easyMenu(
				rm.getString(ResourceIDs.STR_KEY_CONFIGURE), 2, -1, (UICanvas
						.getInstance().getWidth() * 2) / 3, confLabel, "", "");
		((UIItem) confMenu.getItemList().elementAt(0)).setFg_color(this.oldFg);
		// a hack to avoid the selection and hence a blu label!!!
		confLabel.setSelected(false);
		int screenHeight = UICanvas.getInstance().getClipHeight();
		int menuHeight = this.confMenu.getHeight(getGraphics());
		int footerHeight = this.footer.getHeight(getGraphics());
		this.confMenu
				.setAbsoluteY(screenHeight - menuHeight - footerHeight - 2);
		this.addPopup(confMenu);
	}

	private boolean isValid(int key) {
		int ga = UICanvas.getInstance().getGameAction(key);
		if (ga == UICanvas.UP || ga == UICanvas.DOWN || ga == UICanvas.LEFT
				|| ga == UICanvas.RIGHT || ga == UICanvas.FIRE) { return false; }
		return true;
	}

	public boolean keyPressed(int key) {
		switch (status) {
			case STATE_LEFT_WAIT:
				if (isValid(key)) {
					lkey = key;
					txt.setText(rm.getString(ResourceIDs.STR_KEY_CONFIRM_LEFT)
							+ " " + rm.getString(ResourceIDs.STR_KEY_CONFIRM));
					err.setText(" ");
					status = STATE_LEFT_OK;
				} else {
					err.setText(rm
							.getString(ResourceIDs.STR_KEY_ERROR_SELECTION));
				}
				this.setDirty(true);
				this.removePopup(confMenu);
				this.addPopup(confMenu);
				askRepaint();
				break;
			case STATE_LEFT_OK:
				if (lkey == key) {
					status = STATE_RIGHT_WAIT;
					txt.setText(rm.getString(ResourceIDs.STR_KEY_SELECT_RIGHT)
							+ " " + rm.getString(ResourceIDs.STR_KEY_CONFIRM));
					err.setText("");
					UILabel confLabel = new UILabel(rm
							.getString(ResourceIDs.STR_KEY_PRESS));
					confLabel.setAnchorPoint(Graphics.RIGHT);
					confLabel.setFlip(true);
					this.confMenu.remove(1);
					this.removePopup(confMenu);
					this.confMenu.setAbsoluteX(this.getWidth()
							- confMenu.getWidth());
					this.addPopup(confMenu);
					this.confMenu.append(confLabel);
					this.setDirty(true);
					this.askRepaint();

				} else {
					err.setText(rm.getString(ResourceIDs.STR_KEY_ERROR_KEY));
					txt.setText(rm.getString(ResourceIDs.STR_KEY_SELECT_LEFT)
							+ " " + rm.getString(ResourceIDs.STR_KEY_CONFIRM));
					this.removePopup(confMenu);
					this.addPopup(confMenu);
					status = STATE_LEFT_WAIT;
				}
				this.setDirty(true);
				askRepaint();
				break;
			case STATE_RIGHT_WAIT:
				if (isValid(key) && key != lkey) {
					rkey = key;
					txt.setText(rm.getString(ResourceIDs.STR_KEY_CONFIRM_RIGHT)
							+ " " + rm.getString(ResourceIDs.STR_KEY_CONFIRM));
					err.setText(" ");
					status = STATE_RIGHT_OK;
				} else {
					err.setText(rm
							.getString(ResourceIDs.STR_KEY_ERROR_SELECTION));
				}
				this.setDirty(true);
				this.removePopup(confMenu);
				this.addPopup(confMenu);
				askRepaint();
				break;
			case STATE_RIGHT_OK:
				if (rkey == key) {
					status = STATE_WAIT_DONE;
					txt.setText(rm.getString(ResourceIDs.STR_KEY_PROCEED));
					err.setText(" ");
					this.removePopup(confMenu);
				} else {
					status = STATE_RIGHT_WAIT;
					txt.setText(rm.getString(ResourceIDs.STR_KEY_SELECT_RIGHT)
							+ " " + rm.getString(ResourceIDs.STR_KEY_CONFIRM));
					err.setText(rm.getString(ResourceIDs.STR_KEY_ERROR_KEY));
					this.removePopup(confMenu);
					this.addPopup(confMenu);
				}
				this.setDirty(true);
				askRepaint();
				break;
			case STATE_WAIT_DONE:
				String keys = lkey + "," + rkey;
				Config.getInstance().setProperty(Config.CANVAS_KEYS, keys);
				Config.getInstance().saveToStorage();
				UICanvas.setMenuKeys(lkey, rkey);
				UIConfig.menu_title = this.oldFg;
				UICanvas.getInstance().open(RegisterScreen.getInstance(), true);
				UICanvas.getInstance().close(KeyScreen.this);
				break;
		}
		return false;
	}
}
