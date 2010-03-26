/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: RegisterScreen.java 1564 2009-06-09 14:17:08Z luca $
*/

package it.yup.screens;

import java.io.IOException;
import java.io.InputStream;
import lampiro.LampiroMidlet;
import it.yup.util.Logger;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.EventQueryRegistration;
import it.yup.xmlstream.StreamEventListener;
import it.yup.xmpp.Config;
import it.yup.xmpp.XMPPClient;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class RegisterScreen extends Form implements CommandListener,
		StreamEventListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private static RegisterScreen _instance;

	private TextField tf_jid = new TextField(rm
			.getString(ResourceIDs.STR_JABBER_ID), null, 32,
			TextField.EMAILADDR | TextField.NON_PREDICTIVE);

	private TextField tf_pwd = new TextField(rm
			.getString(ResourceIDs.STR_PASSWORD), null, 32, TextField.ANY
			| TextField.PASSWORD);

	private TextField tf_email = new TextField(rm
			.getString(ResourceIDs.STR_EMAIL_ADDRESS), null, 32,
			TextField.EMAILADDR);

	private TextField tf_server = new TextField(rm
			.getString(ResourceIDs.STR_SERVER_NAME), null, 32, TextField.ANY
			| TextField.NON_PREDICTIVE);

	private ChoiceGroup grp_new_account = new ChoiceGroup("",
			ChoiceGroup.MULTIPLE);

	private ChoiceGroup grp_advanced = new ChoiceGroup("", ChoiceGroup.MULTIPLE);

	private ChoiceGroup grp_server = new ChoiceGroup("Connecting server",
			ChoiceGroup.EXCLUSIVE);

	/** Progress bar during login */
	private Gauge progress_gauge = new Gauge(
			rm.getString(ResourceIDs.STR_WAIT), false, Gauge.INDEFINITE,
			Gauge.CONTINUOUS_RUNNING);

	private StringItem btn_login = new StringItem(null, rm
			.getString(ResourceIDs.STR_LOGIN), Item.BUTTON);

	private Command cmd_exit = new Command(rm.getString(ResourceIDs.STR_EXIT),
			Command.EXIT, 1);

	private Command cmd_login = new Command("Login", Command.SCREEN, 1);

	/** true if we must register a new account */
	private boolean register = false;

	private EventQueryRegistration reg;

	private Config cfg = Config.getInstance();

	/** local copy of the jid server (for detecting changes) */
	private String jid_server = "";

	private RegisterScreen() {
		super(rm.getString(ResourceIDs.STR_TITLE));

		// Add options to the connecting group
		grp_new_account.append(rm.getString(ResourceIDs.STR_NEW_USER), null);
		grp_advanced.append(rm.getString(ResourceIDs.STR_ADVANCED_OPTIONS),
				null);
		grp_server.append("automatic", null);
		grp_server.append("manual", null);

		btn_login.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_EXPAND
				| Item.LAYOUT_NEWLINE_AFTER);
		btn_login.setDefaultCommand(new Command("Login", Command.ITEM, 1));

		// set the command listener for the login button
		btn_login.setItemCommandListener(new ItemCommandListener() {
			public void commandAction(Command _1, Item _2) {
				// just forward the command to the login
				login();
			}
		});

		setItemStateListener(new ItemStateListener() {
			public void itemStateChanged(Item item) {
				_itemStateChanged(item);
			}
		});

		// set the values from config
		if (cfg.getProperty(Config.USER) != null) {
			String tempUser = cfg.getProperty(Config.USER, "") + "@"
					+ cfg.getProperty(Config.SERVER, "");
			if (tempUser.compareTo("@") == 0) {
				tempUser = "@" + Config.BLUENDO_SERVER;
				this.grp_new_account.setSelectedFlags(new boolean[] { true });
			} else
				grp_server.setSelectedIndex(1, true);
			tf_jid.setString(tempUser);
			jid_server = get_server(tf_jid.getString());
			tf_pwd.setString(cfg.getProperty(Config.PASSWORD, ""));
			tf_server.setString(cfg.getProperty(Config.CONNECTING_SERVER, ""));
			// append(btn_login);
		}

		addCommand(cmd_exit);
		setCommandListener(this);
		// #debug        
//@		addCommand(new Command("debug", Command.SCREEN, 1));
	}

	public static RegisterScreen getInstance() {
		if (_instance == null) {
			_instance = new RegisterScreen();
		}
		_instance.placeItems();
		return _instance;
	}

	private void placeItems() {
		deleteAll();

		append(grp_new_account);
		append(tf_jid);
		append(tf_pwd);
		if (grp_new_account.isSelected(0)) {
			append(tf_email);
		}
		append(grp_advanced);
		checkLogin();

		if (grp_advanced.isSelected(0)) {
			append(grp_server);
			if (grp_server.getSelectedIndex() == 1) {
				append(tf_server);
			}
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmd_exit) {
			LampiroMidlet.exit();
			// #mdebug        	
//@		} else if ("debug".equals(c.getLabel())) {
//@			DebugScreen debugScreen = new DebugScreen();
//@			debugScreen.setReturnScreen(this);
//@			LampiroMidlet.disp.setCurrent(debugScreen);
			// #enddebug        	
		} else if (c == cmd_login) {
			login();
		}
	}

	private void login() {

		deleteAll();
		append("Logging in...");
		append(progress_gauge);

		new Thread() {
			public void run() {

				String jid = tf_jid.getString();
				int at_idx = jid.indexOf("@");
				String user = jid.substring(0, at_idx);
				String server = jid.substring(at_idx + 1);

				String cfg_user = cfg.getProperty(Config.USER);
				String cfg_server = cfg.getProperty(Config.SERVER);

				if ((cfg_user == null || !cfg_user.equals(user))
						|| (cfg_server == null || !cfg_server.equals(server))) {
					cfg.setProperty(Config.CLIENT_INITIALIZED, Config.FALSE);
				}
				cfg.setProperty(Config.USER, user);
				cfg.setProperty(Config.SERVER, server);
				cfg.setProperty(Config.PASSWORD, tf_pwd.getString());
				cfg.setProperty(Config.EMAIL, tf_email.getString());

				if (grp_server.getSelectedIndex() == 0) {
					cfg.setProperty(Config.CONNECTING_SERVER, srvQuery());
				} else {
					cfg.setProperty(Config.CONNECTING_SERVER, tf_server
							.getString());
				}

				cfg.saveToStorage();

				try {
					// Get the XMPP client
					XMPPClient xmpp = XMPPClient.getInstance();
					boolean newCredentials = Config.FALSE.equals(cfg
							.getProperty(Config.CLIENT_INITIALIZED));
					xmpp.createStream(register, newCredentials);

					EventQuery qAuth = new EventQuery(EventQuery.ANY_EVENT,
							null, null);
					reg = BasicXmlStream.addEventListener(qAuth,
							RegisterScreen.this);
					xmpp.openStream();

				} catch (Exception e) {
					Logger.log(e.getMessage());
				} catch (Error e) {
					Logger.log("Error:" + e.getClass().getName() + ":"
							+ e.getMessage());
				}
			}
		}.start();
	}

	public void gotStreamEvent(String event, Object source) {
		XMPPClient client = XMPPClient.getInstance();
		if (BasicXmlStream.STREAM_ERROR.equals(event)
				|| BasicXmlStream.CONNECTION_FAILED.equals(event)
				|| BasicXmlStream.CONNECTION_LOST.equals(event)) {

			reg.remove();
			client.closeStream();

			String description = null;
			if (BasicXmlStream.CONNECTION_FAILED.equals(event)) {
				description = "connection failed";
			} else if (BasicXmlStream.CONNECTION_LOST.equals(event)) {
				description = "connection lost";
			} else {
				description = (String) source;
			}

			client.showAlert(AlertType.ERROR, register ? rm
					.getString(ResourceIDs.STR_REGFAIL_TITLE) : rm
					.getString(ResourceIDs.STR_LOGFAIL_TITLE), (register ? (rm
					.getString(ResourceIDs.STR_REGFAIL_DESC) + " ") : (rm
					.getString(ResourceIDs.STR_LOGFAIL_DESC) + " "))
					+ description, this);
			placeItems();

		} else if (BasicXmlStream.STREAM_INITIALIZED.equals(event)) {
			reg.remove();
			client.stream_authenticated();
		}
	}

	private int indexOf(Item item) {
		for (int i = 0; i < size(); i++) {
			if (item.equals(get(i))) return i;
		}
		return -1;
	}

	private void _itemStateChanged(Item item) {

		if (item.equals(grp_new_account)) {
			register = grp_new_account.isSelected(0);
			if (register) insert(indexOf(tf_pwd) + 1, tf_email);
			else
				delete(indexOf(tf_email));
		} else if (item.equals(grp_server)) {

			if (grp_server.getSelectedIndex() == 1) {
				insert(indexOf(grp_server) + 1, tf_server);
				String jid = tf_jid.getString();
				tf_server.setString(get_server(jid) + ":5222");
			} else {
				int idx = indexOf(tf_server);
				if (idx != -1) {
					delete(idx);
				}
			}
			return;
		} else if (item.equals(grp_advanced)) {
			if (grp_advanced.isSelected(0)) {
				append(grp_server);
				if (grp_server.isSelected(1)) {
					append(tf_server);
				}
			} else {
				delete(indexOf(grp_server));
				int idx = indexOf(tf_server);
				if (idx >= 0) delete(idx);
			}
			return;
		} else if (item.equals(tf_jid)) {
			if (grp_server.getSelectedIndex() == 1) {
				String jid_server = get_server(tf_jid.getString());
				if (!this.jid_server.equals(jid_server)) {
					this.jid_server = jid_server;
					grp_server.setSelectedIndex(0, true);
					int idx = indexOf(tf_server);
					if (idx >= 0) delete(idx);
				}
			}
		}

		// check if we must enable / disable
		checkLogin();
	}

	/**
	 * check if we must enable the login
	 */
	private void checkLogin() {

		TextField items[] = new TextField[] { null, null, null, null };
		boolean checkmail[] = new boolean[] { true, false, true, false };

		items[0] = tf_jid;
		items[1] = tf_pwd;

		if (register) {
			items[2] = tf_email;
		}
		if (grp_server.getSelectedIndex() == 1) {
			items[3] = tf_server;
		}

		boolean complete = true;
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) continue;
			String s = items[i].getString();
			if (s.length() == 0) {
				complete = false;
				break;
			} else if (checkmail[i] && !Utils.is_email(s)) {
				complete = false;
				break;
			}
			;
		}

		int idx = indexOf(btn_login);
		if (complete && idx == -1) {
			insert(indexOf(grp_advanced), btn_login);
			addCommand(cmd_login);
		} else if (!complete && idx != -1) {
			delete(idx);
			removeCommand(cmd_login);
		}

	}

	private String srvQuery() {

		String jid = RegisterScreen.this.tf_jid.getString();
		String host = jid.substring(jid.indexOf("@") + 1);
		try {
			String domain = host;
			HttpConnection conn = (HttpConnection) Connector
					.open(Config.SRV_QUERY_PATH + domain);
			InputStream is = conn.openInputStream();
			int b = -1;
			StringBuffer buffer = new StringBuffer();
			while ((b = is.read()) != -1) {
				buffer.append((char) b);
			}
			String result = buffer.toString();
			if ("_:-1".equals(result)) {
				result = host + ":5222";
			}
			return result;
		} catch (IOException e) {
			return host + ":5222";
		}

	}

	private String get_server(String jid) {
		int server_idx = jid.indexOf("@");
		if (server_idx >= 0) {
			return jid.substring(server_idx + 1);
		} else {
			return "";
		}
	}
}
