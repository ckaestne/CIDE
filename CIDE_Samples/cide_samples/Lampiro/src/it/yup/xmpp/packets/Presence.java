/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Presence.java 1431 2009-05-06 15:24:26Z luca $
*/

package it.yup.xmpp.packets;

import it.yup.xml.Element;
import it.yup.xmpp.Config;

/**
 * 
 */
public class Presence extends Stanza {

	/* presence element names */
	public static final String PRESENCE = "presence";
	public static final String PRIORITY = "priority";
	public static final String RESOURCE = "resource";
	public static final String STATUS = "status";
	public static final String SHOW = "show";

	/* possible availability show */
	public static final String SHOW_CHAT = "chat";
	public static final String SHOW_DND = "dnd";
	public static final String SHOW_AWAY = "away";
	public static final String SHOW_XA = "xa";
	public static final String SHOW_ONLINE= "online";

	/* possible presence types */
	public static final String T_SUBSCRIBE = "subscribe";
	public static final String T_SUBSCRIBED = "subscribed";
	public static final String T_UNSUBSCRIBE = "unsubscribe";
	public static final String T_UNSUBSCRIBED = "unsubscribed";
	public static final String T_PROBE = "probe";
	public static final String T_UNAVAILABLE = "unavailable";
	
	public static final int PC = 0;
	public static final int PHONE = 1;
	public static final int BOT = 2;

	/*
	 * The type of the Presence: could be PC,PHONE or BOT
	 */
	public int pType = Presence.PC;

	public Presence() {
		super(PRESENCE, null, null, null);
	}

	public Presence(String to, String type, String show, String status,
			int priority) {
		super(PRESENCE, to, type, null);
		if (status != null) {
			setStatus(status);
		}
		// XXX Perhaps wrong, negative priority may have a meaning !!!
		if (priority >= 0) {
			setPriority(priority);
		}
		if (show != null) {
			setShow(show);
		}
	}

	public Presence(String type, String show, String status, int priority) {
		this(null, type, show, status, priority);
	}

	public Presence(Element e) {
		super(e);
	}

	public void setShow(String show) {
	removeChild(null, SHOW);
		this.addElementAndContent(NS_JABBER_CLIENT, SHOW, show);
	}

	public String getShow() {
		Element el = getChildByName(NS_JABBER_CLIENT, SHOW);
		if (el != null) {
			return el.getText();
		} else {
			return null;
		}
	}

	public void setPriority(int priority) {
		removeChild(null, PRIORITY);
		this.addElementAndContent(NS_JABBER_CLIENT, PRIORITY, ""+priority);
	}

	public int getPriority() {
		Element el = this.getChildByName(NS_JABBER_CLIENT, PRIORITY);
		if (el != null) {
			return Integer.parseInt(el.getText());
		} else {
			return 0;
		}
	}

	public String getResource() {
		Element el = getChildByName(NS_JABBER_CLIENT, RESOURCE);
		if (el != null) {
			return el.getText();
		} else {
			return Config.getInstance().getProperty(Config.YUP_RESOURCE, "Lampiro");
		}
	}

	public void setStatus(String status) {
	
		removeChild(null, STATUS);
		this.addElementAndContent(NS_JABBER_CLIENT, STATUS, "" + status);
	}

	public String getStatus() {
		Element el = this.getChildByName(NS_JABBER_CLIENT, STATUS);
		if (el != null) {
			return el.getText();
		} else {
			return null;
		}
	}
}
