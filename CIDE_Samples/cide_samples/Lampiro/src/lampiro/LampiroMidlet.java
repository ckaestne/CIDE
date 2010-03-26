/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: LampiroMidlet.java 1539 2009-05-25 21:05:01Z luca $
*/

package lampiro;

// #mdebug

import it.yup.util.Logger;
import it.yup.util.MemoryLogConsumer;
import it.yup.util.StderrConsumer;
import it.yup.util.XMPPConsumer;

// #enddebug
import javax.microedition.midlet.MIDlet;

// #ifdef UI

import it.yup.ui.UICanvas;
import it.yup.ui.UIConfig;
import it.yup.ui.UIUtils;
import javax.microedition.lcdui.Font;

// #endif
// #ifndef UI


// #endif

import it.yup.xmpp.Config;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import javax.microedition.lcdui.Display;

/**
 * Lampiro Midlet.
 * 
 * XXX: Use ResourceMgr for the phone hold on message or move the hold-on logic
 * in XMPPClient (maybe better)
 */
public class LampiroMidlet extends MIDlet {

	/** The main display */
	public static Display disp;

	/** The midlet instance */
	public static LampiroMidlet _lampiro;

	private XMPPClient xmpp = null;

	/**
	 * information saved when the app is paused (i.e. a phone call or an SMS is
	 * received or the user switches to another application).
	 */
	private int last_availability = -1;
	private String last_status;

	/**
	 * Constructor
	 */
	public LampiroMidlet() {
		xmpp = XMPPClient.getInstance();
		// #mdebug
		Logger.addConsumer(new StderrConsumer());
		Logger.addConsumer(MemoryLogConsumer.getConsumer());
		//		XMPPConsumer xmppConsumer = XMPPConsumer.getConsumer();
		//		xmppConsumer.debugJid = "blutest@jabber.bluendo.com";
		//		Logger.addConsumer(xmppConsumer);
		// #enddebug
		_lampiro = this;
		LampiroMidlet.disp = Display.getDisplay(this);
		// #ifdef UI
		UICanvas.setDisplay(Display.getDisplay(this));
		UICanvas canvas = UICanvas.getInstance();
		UICanvas.display(null);
// #ifndef GLIDER
				String colorString = Config.getInstance()
					.getProperty(Config.COLOR, "0");
		// #endif 
		int colorInt = colorString.toCharArray()[0] - '0';
		LampiroMidlet.changeColor(colorInt);
		String fontString = Config.getInstance().getProperty(Config.FONT_SIZE,
				"0");
		int fontInt = fontString.toCharArray()[0] - '0';
		LampiroMidlet.changeFont(fontInt);
		canvas.open(new lampiro.screens.SplashScreen(), true);
		// #endif
// #ifndef UI
						disp.setCurrent(new it.yup.screens.SplashScreen());
		// #endif

	}

	/**
	 * Starts the application or re-starts it after being placed in background.
	 */
	public void startApp() {

		if (last_availability >= 0) {
			xmpp.setPresence(last_availability, last_status);
			last_availability = -1;
		}
	}

	/**
	 * Closes the application.
	 * 
	 * @param unconditional
	 *            stop is forced
	 */
	protected void destroyApp(boolean unconditional) {
		xmpp.stopClient();
		Config.getInstance().saveToStorage();
		_lampiro = null;
	}

	/**
	 * Pauses the application placing it in background (i.e. due to a phone call
	 * or an SMS or the user switches to another application). The app saves the
	 * current Presence and sets it to a status indicating the user is not
	 * available.
	 */
	protected void pauseApp() {
		if (xmpp.getMyContact() != null) {
			last_availability = xmpp.getMyContact().getAvailability();
			last_status = xmpp.getMyContact().getPresence().getStatus();
			xmpp.setPresence(Contact.AV_DND,
					"Phone hold on, please don't send messages");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void exit() {
		if (_lampiro == null) { return; }
		LampiroMidlet m = _lampiro;
		m.destroyApp(false);
		m.notifyDestroyed();
	}

	// #ifdef UI

	static public void changeFont(int fontIndex) {
		switch (fontIndex) {
			case 0:
				UIConfig.font_body = Font.getFont(Font.FACE_PROPORTIONAL,
						Font.STYLE_PLAIN, Font.SIZE_SMALL);
				break;
			case 1:
				UIConfig.font_body = Font.getFont(Font.FACE_PROPORTIONAL,
						Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
				break;
			case 2:
				UIConfig.font_body = Font.getFont(Font.FACE_PROPORTIONAL,
						Font.STYLE_PLAIN, Font.SIZE_LARGE);
				break;

			default:
				break;
		}
	}

	static public void changeColor(int colorIndex) {
		switch (colorIndex) {
			case 0:
				UIConfig.tbb_color = 0xb0c2c8;
				UIConfig.input_color = 0xFFFFFF;
				UIConfig.header_bg = 0x567cfe;
				UIConfig.tbs_color = UIConfig.header_bg;
				UIConfig.header_fg = 0xDDE7EC;
				UIConfig.menu_title = 0xDDE7EC;
				UIConfig.bg_color = 0xddddff;
				UIConfig.menu_border = 0x223377;
				UIConfig.menu_color = 0xacc2d8;
				UIConfig.menu_3d = true;
				UIConfig.button_color = UIConfig.tbb_color;
				UIConfig.button_selected_color = UIConfig.header_bg;
				UIConfig.bb_color = UIConfig.menu_border;
				UIConfig.bbs_color = UIConfig.menu_border;
				UIConfig.bdb_color = UIUtils.colorize(UIConfig.menu_color, -50);
				UIConfig.blb_color = UIUtils.colorize(UIConfig.menu_color, 50);
				UIConfig.bdbs_color = UIConfig.bdb_color;
				UIConfig.blbs_color = UIConfig.blb_color;
				break;
			case 1:
				UIConfig.tbb_color = 0xb0c2c8;
				UIConfig.input_color = 0xFFFFFF;
				UIConfig.header_bg = 0x24982f;
				UIConfig.tbs_color = UIUtils.colorize(UIConfig.header_bg, 30);
				UIConfig.header_fg = 0xDDE7EC;
				UIConfig.menu_title = 0xDDE7EC;
				UIConfig.bg_color = 0xddffdd;
				UIConfig.menu_border = 0x227733;
				UIConfig.menu_color = 0xacd8c2;
				UIConfig.menu_3d = true;
				UIConfig.button_color = UIConfig.tbb_color;
				UIConfig.button_selected_color = UIConfig.tbs_color;
				UIConfig.bb_color = UIConfig.menu_border;
				UIConfig.bbs_color = UIConfig.menu_border;
				UIConfig.bdb_color = UIUtils.colorize(UIConfig.menu_color, -50);
				UIConfig.blb_color = UIUtils.colorize(UIConfig.menu_color, 50);
				UIConfig.bdbs_color = UIConfig.bdb_color;
				UIConfig.blbs_color = UIConfig.blb_color;
				break;
			case 2:
				UIConfig.tbb_color = 0xb0c2c8;
				UIConfig.input_color = 0xFFFFFF;
				UIConfig.header_bg = 0xdb0724;
				UIConfig.tbs_color = UIUtils.colorize(UIConfig.header_bg, 20);
				UIConfig.header_fg = 0xDDE7EC;
				UIConfig.menu_title = 0xDDE7EC;
				UIConfig.bg_color = 0xffdddd;
				UIConfig.menu_border = 0x773322;
				UIConfig.menu_color = 0xd8c2ac;
				UIConfig.menu_3d = false;
				UIConfig.button_color = UIConfig.tbb_color;
				UIConfig.button_selected_color = UIConfig.tbs_color;
				UIConfig.bb_color = UIConfig.menu_border;
				UIConfig.bbs_color = UIConfig.menu_border;
				UIConfig.bdb_color = UIConfig.bb_color;
				UIConfig.blb_color = UIConfig.bb_color;
				UIConfig.bdbs_color = UIConfig.bdb_color;
				UIConfig.blbs_color = UIConfig.blb_color;

				break;
			case 3:
				UIConfig.tbb_color = 0xb0c2c8;
				UIConfig.input_color = 0xFFFFFF;
				UIConfig.header_bg = 0x111111;
				UIConfig.tbs_color = 0xff8000;
				UIConfig.header_fg = 0xDDE7EC;
				UIConfig.menu_title = 0xfb7c00;
				UIConfig.bg_color = 0xf8ebcf;
				UIConfig.menu_border = 0xfe611b;
				UIConfig.menu_color = 0xffc22a;
				UIConfig.menu_3d = false;
				UIConfig.button_color = 0xffa658;
				UIConfig.button_selected_color = 0xff8000;
				UIConfig.bb_color = UIConfig.button_selected_color;
				UIConfig.bbs_color = UIConfig.button_color;
				UIConfig.bdb_color = UIConfig.button_selected_color;
				UIConfig.blb_color = UIConfig.button_selected_color;
				UIConfig.bdbs_color = UIConfig.button_color;
				UIConfig.blbs_color = UIConfig.button_color;
				break;

			default:
				break;
		}
	}

	// #endif 
}
