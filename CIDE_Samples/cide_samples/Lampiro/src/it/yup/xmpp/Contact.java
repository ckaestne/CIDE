/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Contact.java 1597 2009-06-19 11:54:12Z luca $
*/

package it.yup.xmpp;

import it.yup.util.Utils;
import it.yup.xml.Element;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Presence;
import it.yup.xmpp.packets.Stanza;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

/**
 * The Xmpp Contact
 */
public class Contact extends IQResultListener {

	/* possible availability status */
	public static final int AV_CHAT = 0;
	public static final int AV_ONLINE = 1;
	public static final int AV_DND = 2;
	public static final int AV_AWAY = 3;
	public static final int AV_XA = 4;
	public static final int AV_UNAVAILABLE = 5;

	/* reason for status change */
	public static final int CH_MESSAGE_NEW = 0;
	public static final int CH_MESSAGE_READ = 1;
	public static final int CH_STATUS = 2;
	public static final int CH_TASK_NEW = 3;
	public static final int CH_TASK_REMOVED = 4;
	public static final int CH_CONTACT_REMOVED = 5;
	public static final int CH_GROUP = 6;

	/*
	 * The last resource associated to this user that sent a message
	 */
	/** mapping presence availability constants */
	public static String availability_mapping[] = { Presence.SHOW_CHAT, // AV_CHAT
			Presence.SHOW_ONLINE, // AV_ONLINE
			Presence.SHOW_DND, // AV_DND
			Presence.SHOW_AWAY, // AV_AWAY
			Presence.SHOW_XA, // AV_XA
			Presence.T_UNAVAILABLE // AV_UNAVAILABLE
	};

	/**
	 * Get the icon for a presence show
	 * 
	 * @param i
	 *            a {@link@Presence} AV_* constant
	 * @return
	 */

	/** the messages history */
	//private Vector conv = null;
	Vector convs = new Vector();

	/** pending commands */
	private Vector tasks = null;
	// XXX to array
	/** the command list; array of String pairs (node/name) */
	public String cmdlist[][] = null;

	private String queryCapNode = null;
	private String queryCapVer = null;

	public boolean pending_tasks = false;

	public boolean unread_msg() {
		Enumeration en = this.convs.elements();
		while (en.hasMoreElements()) {
			Object[] ithCouple = (Object[]) en.nextElement();
			if (((Vector) ithCouple[1]).size() > 0) return true;
		}
		return false;
	}

	/*
	 * A Calendar used to signal the date of message arriving 
	 */
	static Calendar cal = Calendar.getInstance();
	public String jid;
	public String name;
	private String[] groups;
	public String subscription;
	Presence[] resources = null;

	/** cached availability: cached for speeding up sorting */
	int availability = AV_UNAVAILABLE;

	/*
	 * the last (in time order) resource associated to this user
	 */
	public String lastResource = null;

	public Contact(String jid, String name, String subscription,
			String groups[]) {
		this.jid = jid;

		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}

		if (subscription == null || "".equals(subscription)) {
			this.subscription = "none";
		} else {
			this.subscription = subscription;
		}

		if (groups == null) {
			this.groups = new String[0];
		} else {
			this.groups = groups;
		}

		// add mySelf to all my groups
		// I don't add the "ungrouped" group to myself; I only add myself to the ungrouped group
		if (this.groups.length == 0) {
			((Group) Group.getGroup(Roster.unGroupedCode)).addElement(this.jid);
		} else {
			for (int i = 0; i < this.groups.length; i++) {
				((Group) Group.getGroup(this.groups[i])).addElement(this.jid);
			}
		}

		// System.out.println("name ---->" + this.name + "(" + this.jid + ")");
	}

	public Element store() {
		Element el = new Element(Roster.NS_IQ_ROSTER, "item");
		el.setAttributes(new String[] { "jid", "name", "subscription" },
				new String[] { jid, name, subscription });
		for (int i = 0; i < this.groups.length; i++) {
			if (this.groups[i].equals(Roster.unGroupedCode) == false) {
				el.addElement(null, "group").addText(this.groups[i]);
			}
		}
		// #mdebug
//@				System.out.println("Dump: " + jid);
		// #enddebug
		return el;
	}

	public void addMessageToHistory(String preferredResource, Message msg) {
		// the default presence is the first one
		if (preferredResource == null) {
			preferredResource = (resources != null ? this.resources[0]
					.getAttribute(Message.ATT_FROM) : this.jid);
		}
		String type = msg.getAttribute(Message.ATT_TYPE);
		if (type == null) type = Message.NORMAL;

		Enumeration en = this.convs.elements();
		boolean found = false;
		while (en.hasMoreElements()) {
			Object[] convCouple = (Object[]) en.nextElement();
			if (((String) convCouple[0]).equals(preferredResource)) found = true;
		}
		if (found == false) {
			convs.addElement(new Object[] { preferredResource, new Vector() });
		}

		compileMessage(preferredResource, msg, type);
	}

	protected void compileMessage(String preferredResource, Message msg,
			String type) {
		String body = msg.getBody();
		String to = msg.getAttribute(Stanza.ATT_TO);
		String from = msg.getAttribute(Stanza.ATT_FROM);
		Element error = msg.getChildByName(null, Message.ERROR);
		if (error != null) {
			String code = error.getAttribute("code");
			if (code != null) {
				String mappedCode = XMPPClient.getErrorString (code);
				body = body + " - Error:" + mappedCode;
			}
			Element text = error.getChildByName(null, "text");
			if (text!=null){
				body = body + ". "+ text.getText();
			}
		}
		// muc can have a delay child that modifies the from 
		Element delay = msg.getChildByName(null, XMPPClient.DELAY);
		if (delay != null) {
			String delayFrom = delay.getAttribute(Message.ATT_FROM);
			if (delayFrom != null) {
				from = Contact.userhost(from) + "/" + Contact.user(delayFrom);
			}

		}
		Element subjectEl = msg.getChildByName(null, "subject");
		String subject = null;
		if (subjectEl != null) subject = subjectEl.getText();
		if (subject != null && subject.length() > 0) {
			body = subject + ": " + body;
		}
		long date;
		String arriveTime = "";

		date = System.currentTimeMillis();
		cal.setTime(new Date(date));

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		arriveTime = new String((hour < 10 ? "0" : "") + hour + ":"
				+ (minute < 10 ? "0" : "") + minute);

		if (from != null) lastResource = from;
		if (body != null && body.length() > 0) {
			getMessageHistory(preferredResource).addElement(
					new String[] { to, body, lastResource, arriveTime, type });
		}
	}

	public Vector getMessageHistory(String preferredResource) {
		Enumeration en = this.convs.elements();
		while (en.hasMoreElements()) {
			Object[] convCouple = (Object[]) en.nextElement();
			if (((String) convCouple[0]).equals(preferredResource)) return (Vector) convCouple[1];
		}
		return null;
	}

	public Vector getAllConvs() {
		return this.convs;
	}

	public int getHistoryLength(String preferredResource) {
		Vector messageHistory = getMessageHistory(preferredResource);
		if (messageHistory == null) { return 0; }
		return messageHistory.size();
	}

	public void resetMessageHistory(String preferredResource) {
		getMessageHistory(preferredResource).removeAllElements();
	}

	public String getPrintableName() {
		if (!"".equals(name)) {
			return name;
		} else {
			return jid;
		}
	}

	/**
	 * Returns the user full jid (if online) or null if offline. The presence
	 * with the highest pririty is used
	 * 
	 * @return The user full Jid.
	 */
	public String getFullJid() {
		if (resources != null) { return resources[0]
				.getAttribute(Stanza.ATT_FROM); }
		return null;
	}

	public boolean isVisible() {
		if (unread_msg()) {
			/* if the contact has messages in his chat, it will be shown */
			return true;
		}
		if (resources != null) { return true; }

		if (subscription == null) return false;
		//		if ("none".equals(subscription)) {// || "to".equals(subscription)) {
		//			// ignoring the contacts whose status hasn't to be displayed
		//			// XXX: we should let the user configure this behavior
		//			return false;
		//		}

		return false;
	}

	public void addTask(Task t) {
		if (tasks == null) {
			tasks = new Vector();
			pending_tasks = true;
		}
		if (!tasks.contains(t)) {
			tasks.addElement(t);
			pending_tasks = true;
		}
	}

	public void removeTask(Task t) {
		if (tasks == null) { return; // shouldn't happen, but...
		}
		tasks.removeElement(t);
		if (tasks.size() == 0) {
			tasks = null;
			pending_tasks = false;
		}
	}

	public Task[] getTasks() {
		Task[] tasks;
		if (this.tasks != null) {
			tasks = new Task[this.tasks.size()];
			this.tasks.copyInto(tasks);
		} else {
			tasks = new Task[0];
		}
		return tasks;
	}

	/**
	 * Get the presence of the best resource (in order of visibility and
	 * priority)
	 * 
	 * @return
	 */
	public Presence getPresence() {
		if (resources == null) return null;
		return resources[0];
	}

	/**
	 * Get all presences for each resource
	 * 
	 * @return
	 */
	public Presence[] getAllPresences() {
		// XXX it should be safer to return a copy
		return resources;
	}

	/**
	 * Update the presence for the resource that has sent it
	 * 
	 * @param p
	 */
	public void updatePresence(Presence p) {
		// look for nickname
		// many gw have wrong nickname!
		if (p.getAttribute(Iq.ATT_FROM).indexOf("@") >= 0) {
			Element x = p.getChildByName(XMPPClient.NS_VCARD_UPDATE, "x");
			if (x != null) {
				Element nickname = x.getChildByName(null, "nickname");
				if (nickname != null) {
					String nickNameText = nickname.getText();
					if (nickNameText != null && nickNameText.length() > 0) {
						this.name = nickNameText;
					}
				}
			}
		}

		if (Presence.T_UNAVAILABLE.equals(p.getAttribute(Stanza.ATT_TYPE))) {
			if (resources == null) {
				return;
			} else if (resources.length == 1) {

				// remove the resource array if the only resource is going
				// offline
				String old_jid = resources[0].getAttribute(Stanza.ATT_FROM);

				if (old_jid.equals(p.getAttribute(Stanza.ATT_FROM))) {
					resources = null;
					availability = AV_UNAVAILABLE;
				}
				return;
			} else {
				updateExistingPresence(p);
			}
		} else {
			// available presence, update the list and resort
			if (resources == null) {
				// first resource create the list
				resources = new Presence[] { p };
			} else {
				// add or update and finally sort
				String jid = p.getAttribute(Stanza.ATT_FROM);
				boolean found = false;
				// check if we can just update
				for (int i = 0; i < resources.length; i++) {
					if (jid.equals(resources[i].getAttribute(Stanza.ATT_FROM))) {
						resources[i] = p;
						found = true;
						break;
					}
				}

				if (!found) {
					// new resource found, add it
					Presence v[] = new Presence[resources.length + 1];
					v[0] = p;
					for (int i = 0; i < resources.length; i++) {
						v[i + 1] = resources[i];
					}
					resources = v;
				}

				// presence order may have changed sort the resources
				if (resources.length > 1) {
					for (int i = 0; i < resources.length - 1; i++) {
						for (int j = 1; j < resources.length; j++) {
							Presence ri = resources[i];
							Presence rj = resources[j];
							int diff = mapAvailability(ri.getShow())
									- mapAvailability(rj.getShow());
							if (diff > 0) {
								resources[i] = rj;
								resources[j] = ri;
							} else if (diff == 0) {
								// check priority
								int pdiff = ri.getPriority() - rj.getPriority();
								// higher priority comes first
								if (pdiff < 0) {
									resources[i] = rj;
									resources[j] = ri;
								}
							}
						}
					}
				}
			}
		}

		// check the capabilities
		// pass them to the roster that checks the db 
		Element capNode = p.getChildByName(null, "c");
		if (capNode != null) {
			queryCapNode = capNode.getAttribute("node");
			queryCapVer = capNode.getAttribute("ver");
			Element cap = Config.getInstance().getCapabilities(queryCapNode,
					queryCapVer);
			if (cap == null) {
				this.askCapabilities(p);
			}
		}

		// cache the new availability
		availability = mapAvailability(resources[0].getShow());
		capNode = p.getChildByName(null, "c");
		if (capNode != null) {
			String node = capNode.getAttribute("node");
			String ver = capNode.getAttribute("ver");
			Element cap = Config.getInstance().getCapabilities(node, ver);
			if (cap != null) {
				Element identity = cap.getChildByName(null, "identity");
				if (identity != null) {
					String name = identity.getAttribute("name");
					if (name != null && name.compareTo("Lampiro") == 0) {
						p.pType = Presence.PHONE;
					}
				}
			}
		}
	}

	public Element getCapabilities(Presence p) {
		if (p == null) return null;
		Element c = p.getChildByName(XMPPClient.NS_CAPS, "c");
		if (c == null) { return null; }
		String node = c.getAttribute("node");
		String ver = c.getAttribute("ver");
		return Config.getInstance().getCapabilities(node, ver);
	}

	public void askCapabilities(Presence p) {
		Element c = null;
		if (p != null) {
			c = p.getChildByName(XMPPClient.NS_CAPS, "c");
		}
		Iq iq = new Iq(p.getAttribute(Message.ATT_FROM), Iq.T_GET);
		Element query = iq.addElement(XMPPClient.NS_IQ_DISCO_INFO, Iq.QUERY);
		if (c != null) {
			query.setAttribute("node", c.getAttribute("node") + "#"
					+ c.getAttribute("ver"));
		}
		XMPPClient.getInstance().sendIQ(iq, this);
	}

	protected void updateExistingPresence(Presence p) {
		// More than one resource, remove only that resource (if
		// present)
		String from = p.getAttribute(Stanza.ATT_FROM);
		String from_cache[] = new String[resources.length];
		boolean found = false;
		for (int i = 0; i < resources.length; i++) {
			from_cache[i] = resources[i].getAttribute(Stanza.ATT_FROM);
			if (from.equals(from_cache[i])) {
				found = true;
			}
		}

		if (found) {
			Presence v[] = new Presence[resources.length - 1];
			for (int i = 0, j = 0; i < resources.length; i++) {
				if (!from.equals(from_cache[i])) {
					v[j++] = resources[i];
				}
			}
			resources = v;
		}
		// got to the end of the method, must update availability
	}

	/**
	 * Compare the contatcs for sorting them in the roster. The precedence is
	 * given by:
	 * <ol>
	 * <li> having unread messages </li>
	 * <li> having pending tasks </li>
	 * <li> the presence </li>
	 * <li> alphabetical order </li>
	 * </ol>
	 */
	public int compareTo(Contact d) {

		// check first unread messages
		if (d.unread_msg() && !unread_msg()) {
			return -1;
		} else if (unread_msg() && !d.unread_msg()) { return 1; }

		// then check for pending tasks
		if (d.pending_tasks && !this.pending_tasks) {
			return -1;
		} else if (!d.pending_tasks && this.pending_tasks) { return 1; }

		int avDiff = this.availabilityDiff(this, d);
		if (avDiff != 0) return avDiff;

		// finally use the name if all the other tests failed
		return d.getPrintableName().toLowerCase().compareTo(
				getPrintableName().toLowerCase());
	}

	private int availabilityDiff(Contact left, Contact right) {
		if ((right.availability >= 2 && left.availability <= 1)
				|| (right.availability >= 3 && left.availability <= 2)
				|| (right.availability == 5 && left.availability <= 4)) return 1;

		if ((right.availability <= 1 && left.availability >= 2)
				|| (right.availability <= 2 && left.availability >= 3)
				|| (right.availability <= 4 && left.availability == 5)) return -1;

		return 0;
	}

	/**
	 * Get the availability of the highest scrored resource
	 * 
	 * @return one of the possible AV_* constants
	 */
	public int getAvailability() {
		return availability;
	}

	/**
	 * Get the availability of a given reosurce
	 * 
	 * @return one of the possible AV_* constants
	 */
	public int getAvailability(String jid) {
		for (int i = 0; resources != null && i < resources.length; i++) {
			Presence p = this.getPresence(jid);
			if (p != null) return mapAvailability(p.getShow());
		}
		return AV_UNAVAILABLE;
	}

	public Presence getPresence(String jid) {
		if (resources != null) {
			if (jid == null) return resources[0];
			for (int i = 0; i < resources.length; i++) {
				String ijid = resources[i].getAttribute(Stanza.ATT_FROM);
				if (jid.equals(ijid)) { return resources[i]; }
			}
			return resources[0];
		}
		return null;
	}

	// XXX -> move to the Utils?
	public static String userhost(String jid) {
		int spos = jid.indexOf('/');
		if (spos > 0) {
			return jid.substring(0, spos);
		} else {
			return jid;
		}
	}

	public static String resource(String jid) {
		int spos = jid.indexOf('/');
		if (spos > 0) {
			return jid.substring(spos + 1);
		} else {
			return null;
		}
	}

	public static String user(String jid) {
		int spos = jid.indexOf('@');
		if (spos > 0) {
			return jid.substring(0, spos);
		} else {
			return null;
		}
	}

	public static String domain(String jid) {
		String uh = Contact.userhost(jid);
		int spos = uh.indexOf('@');
		if (spos > 0) {
			return uh.substring(spos + 1);
		} else {
			return null;
		}
	}

	/**
	 * Getting the constant for a given availability string
	 * 
	 * @param s
	 *            the availability string (if null mapped to "online")
	 * @return
	 */
	int mapAvailability(String s) {
		if (s == null) s = "online";
		for (int i = 0; i < availability_mapping.length; i++) {
			if (availability_mapping[i].equals(s)) { return i; }
		}
		return -1;
	}

	public void handleError(Element e) {
		// TODO Auto-generated method stub

	}

	public void handleResult(Element e) {
		Element query = e.getChildByName(XMPPClient.NS_IQ_DISCO_INFO, Iq.QUERY);
		String fullNode = query.getAttribute("node");
		if (fullNode == null) {
			if (queryCapVer != null && queryCapNode != null) fullNode = queryCapNode
					+ "#" + queryCapVer;
		}
		if (fullNode != null) {
			Vector fn = Utils.tokenize(fullNode, '#');
			String node = (String) fn.elementAt(0);
			String ver = (String) fn.elementAt(1);
			Config.getInstance().saveCapabilities(node, ver, query);
			this.updatePresence(this.resources[0]);
		}

	}

	/**
	 * @param groups the groups to set
	 */
	public boolean setGroups(String[] newGroups) {
		// first I check if the new groups are different to the old ones
		// in that case i re-check the groups structures
		boolean retVal = false;
		if (groups.length != newGroups.length) retVal = true;
		else {
			for (int i = 0; i < this.groups.length; i++) {
				String ithOldGroup = groups[i];
				boolean found = false;
				for (int j = 0; j < newGroups.length; j++) {
					String ithNewGroup = newGroups[j];
					if (ithNewGroup.equals(ithOldGroup)) {
						found = true;
						break;
					}
				}
				if (found == retVal) return true;
			}
		}

		if (retVal) {
			for (int i = 0; i < this.groups.length; i++) {
				Group.getGroup(groups[i]).removeElement(this.jid);
			}
			for (int i = 0; i < newGroups.length; i++) {
				Group.getGroup(newGroups[i]).addElement(this.jid);
			}
		}

		this.groups = newGroups;
		return retVal;
	}

	/**
	 * @return the groups
	 */
	public String[] getGroups() {
		return groups;
	}

	public boolean supportsMUC(Presence p) {
		if (p == null)
			return false;
		/*
		 * Gmail supports only the old "deprecated" format with CAPS Extension
		 * 
		 */
		Element c = p.getChildByName(XMPPClient.NS_CAPS, "c") ;
		if (c!=null){
			String ext = c.getAttribute("ext");
			if (ext != null && ext.indexOf("pmuc-v1")>=0)
				return true;
		}
		
		/*
		 * While this is the suggested one
		 */
		Element caps = this.getCapabilities(p);
		if (caps == null)
			return false;
		Element[] features= caps.getChildrenByName(null, "feature");
		for (int i = 0; i < features.length; i++) {
			Element ithFeature = features[i];
			if (ithFeature.getAttribute("var").equals(XMPPClient.NS_MUC))
				return true;
		}
		return false;
	}
}
