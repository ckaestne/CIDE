/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Roster.java 1588 2009-06-18 09:22:57Z luca $
*/

package it.yup.xmpp;

// #mdebug
//@
//@import it.yup.util.Logger;
//@
// #enddebug

import it.yup.xml.BProcessor;
import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.PacketListener;
import it.yup.xmpp.XMPPClient.XmppListener;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Presence;
import it.yup.xmpp.packets.Stanza;
import it.yup.util.RMSIndex;
import it.yup.util.Utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.AlertType;

public class Roster implements PacketListener {

	/*
	 *	Implements the XEP for roster push 
	 */
	class RosterX implements PacketListener {
		public RosterX() {
			EventQuery q = new EventQuery("message", null, null);
			EventQuery x = new EventQuery("x", new String[] { "xmlns" },
					new String[] { XMPPClient.NS_ROSTERX });
			q.child = x;
			BasicXmlStream.addEventListener(q, this);

			q = new EventQuery("iq", new String[] { Iq.ATT_TYPE },
					new String[] { Iq.T_SET });
			q.child = x;
			BasicXmlStream.addEventListener(q, this);
		}

		public void packetReceived(Element e) {
			//System.out.println(new String(e.toXml()));
			// check the packet sender
			// check what to do with contacts 
			// answer in case it is an Iq
			if (client.getXmppListener() != null) client.getXmppListener()
					.rosterXsubscription(e);
		}
	}

	/*
	 * The roster version
	 */
	String rosterVersion = "0";

	/** All contacts */
	public Hashtable contacts = new Hashtable();

	private XMPPClient client;

	private RosterX rosterX;

	public static String unGroupedCode = new String(
			new char[] { ((char) 0x08) });

	public static String NS_IQ_ROSTER = "jabber:iq:roster";

	public Hashtable registeredGateways = new Hashtable(5);

	Roster(XMPPClient _client) {
		loadGateways();
		client = _client;

	}

	public void streamInitialized() {
		EventQuery eq = new EventQuery(Iq.IQ, new String[] { "type" },
				new String[] { "set" });
		eq.child = new EventQuery(Iq.QUERY, new String[] { "xmlns" },
				new String[] { NS_IQ_ROSTER });
		BasicXmlStream.addEventListener(eq, this);

		this.rosterX = new RosterX();

	}

	RMSIndex rosterStore;

	/*
	 * The configuration instance
	 */
	private Config cfg = Config.getInstance();

	/**
	 * Read the contacts from the RMS
	 * 
	 */
	protected synchronized void readFromStorage() {
		try {
			// #mdebug
//@			Logger.log("Start read from storage:" + System.currentTimeMillis());
			// #enddebug
			rosterStore.open();
			// #mdebug
//@			Logger.log("Start load from rms:" + System.currentTimeMillis());
			// #enddebug
			byte[] rosterData = rosterStore.load(Utils.getBytesUtf8("roster"));
			// #mdebug
//@			Logger.log("End load from rms:" + System.currentTimeMillis());
			// #enddebug
			if (rosterData != null) {
				// #mdebug
//@				Logger.log("Start parse :" + System.currentTimeMillis());
				// #enddebug
				Element rosterEl = BProcessor.parse(rosterData);
				// #mdebug
//@				Logger.log("End parse :" + System.currentTimeMillis());
				// #enddebug
				rosterVersion = rosterEl.getAttribute("ver");
				if (rosterVersion == null) rosterVersion = "0";
				Element[] children = rosterEl.getChildrenByName(null, "group");
				for (int i = 0; i < children.length; i++) {
					Element ithChild = children[i];
					String gName = ithChild.getText();
					byte[] gData = rosterStore.load(Utils.getBytesUtf8(gName));
					if (gData != null) {
						// #mdebug
//@						System.out.println("Read group: " + gName);
						// #enddebug
						Element gEl = BProcessor.parse(gData);
						Element[] gChildren = gEl.getChildrenByName(null,
								"item");
						for (int j = 0; j < gChildren.length; j++) {
							Element item = gChildren[j];
							// #mdebug
//@							String jid = item.getAttribute("jid");
//@							System.out.println("Read: " + jid);
							// #enddebug
							this.updateRosterItem(item);
						}
					}
				}
			}
		} catch (Exception e) {
			// #mdebug
//@			Logger.log("Error in reading from storage: " + e.getMessage(),
//@					Logger.DEBUG);
//@			e.printStackTrace();
			// #enddebug
			client.showAlert(AlertType.ERROR, "Exception",
					"Error reading roster fromstorage:\n" + e, null);
		} finally {
			rosterStore.close();
		}
		// #mdebug
//@		Logger.log("Finish read from storage:" + System.currentTimeMillis());
		// #enddebug
	}

	/**
	 * Save the roster to the RMS
	 * 
	 */
	protected synchronized void saveToStorage() {
		try {
			rosterStore.open();
			Element rosterEl = new Element(Roster.NS_IQ_ROSTER, "roster");
			if (rosterVersion == null) rosterVersion = "0";
			rosterEl.setAttribute("ver", this.rosterVersion);
			Hashtable groups = Group.getGroups();
			Enumeration en = groups.elements();
			while (en.hasMoreElements()) {
				Group g = (Group) en.nextElement();
				byte[] groupData = BProcessor.toBinary(g.store());
				rosterStore.store(Utils.getBytesUtf8(g.name), groupData);
				Element groupEl = new Element(Roster.NS_IQ_ROSTER, "group");
				groupEl.addText(g.name);
				rosterEl.addElement(groupEl);
			}
			rosterStore.store(Utils.getBytesUtf8("roster"), BProcessor
					.toBinary(rosterEl));
		} catch (Exception e) {
			// #mdebug
//@			Logger.log("Error in saving to storage: " + e.getMessage(),
//@					Logger.DEBUG);
			// #enddebug
			client.showAlert(AlertType.ERROR, "Exception",
					"Error saving roster to storage:\n" + e, null);
		} finally {
			rosterStore.close();
		}
	}

	public void packetReceived(Element e) {
		// #mdebug
//@		Logger.log("RosterHandler: received packet: " + new String(e.toXml()),
//@				Logger.DEBUG);
		// #enddebug

		Element query = e.getChildByName(null, Iq.QUERY);
		Element items[] = query.getChildrenByName(null, "item");
		for (int i = 0; i < items.length; i++) {
			updateRosterItem(items[i]);
		}
		String tempVer = query.getAttribute("ver");
		if (tempVer != null) this.rosterVersion = tempVer;
		saveToStorage();
	}

	/**
	 * Send a roster query
	 * 
	 * @param go_online
	 *            if true we go online when received the roster
	 */
	public void retrieveRoster(final boolean go_online, boolean purge) {
		Iq iq_roster = new Iq(null, Iq.T_GET);
		iq_roster.addElement(NS_IQ_ROSTER, Iq.QUERY);
		iq_roster.setAttribute("ver", this.rosterVersion);
		client.sendIQ(iq_roster, new IQResultListener() {
			public void handleError(Element e) {
				System.out.println(e.toXml());
			}

			// XXX I don't link this method, we should study some events for
			// doing this
			public void handleResult(Element e) {
				recreateRoster(e, true);
				saveToStorage();
				if (go_online) {
					client.setPresence(-1, null);
				}

				// Handle subscription to the agent
				Contact c = getContactByJid(Config.LAMPIRO_AGENT);
				if (c == null || !"both".equals(c.subscription)) {
					c = new Contact(Config.LAMPIRO_AGENT, "Lampiro Agent",
							null, null);
					subscribeContact(c, false);
				}
				XmppListener listener = XMPPClient.getInstance()
						.getXmppListener();
				if (listener != null) listener.rosterRetrieved();
			}
		});
	}

	/**
	 * Subscribe to a contact. Adding a contact fires the transmission of two
	 * messages: an iq of type set for updating the roster, and a presence of
	 * type subscribe
	 * @param c: the contact to be subscribed
	 * @param accept: true if this is a response to a subscribe request
	 */
	public void subscribeContact(Contact c, final boolean accept) {
		contacts.put(c.jid, c);
		Iq iq_roster = new Iq(null, Iq.T_SET);
		Element query = iq_roster.addElement(NS_IQ_ROSTER, Iq.QUERY);
		Element item = query.addElement(NS_IQ_ROSTER, "item");
		item.setAttribute("jid", c.jid);
		if (c.name.length() > 0) {
			item.setAttribute("name", c.name);
		}
		for (int i = 0; i < c.getGroups().length; i++) {
			item.addElement(NS_IQ_ROSTER, "group").addText(c.getGroups()[i]);
		}
		if (c.getGroups().length == 0) this.addGatewayGroup(c, item);

		final String contactJid = c.jid;

		client.sendIQ(iq_roster, new IQResultListener() {

			public void handleError(Element e) {
				// TODO Auto-generated method stub

			}

			public void handleResult(Element e) {
				Presence psub;
				if (accept) {
					psub = new Presence(Presence.T_SUBSCRIBED, null, null, -1);
					psub.setAttribute(Stanza.ATT_TO, contactJid);
					client.sendPacket(psub);
				}
				psub = new Presence(Presence.T_SUBSCRIBE, null, null, -1);
				psub.setAttribute(Stanza.ATT_TO, contactJid);
				client.sendPacket(psub);
			}
		});
		// recreateGroups();
	}

	private void addGatewayGroup(Contact c, Element item) {
		Enumeration en = registeredGateways.keys();
		while (en.hasMoreElements()) {
			String from = (String) en.nextElement();
			String domain = Contact.domain(c.jid);
			if (domain != null && domain.equals(from)) {
				item.addElement(NS_IQ_ROSTER, "group").addText(
						((String[]) (registeredGateways.get(from)))[0]);
				break;
			}
		}
	}

	/** remove a contact */

	public void unsubscribeContact(Contact c) {
		contacts.remove(c.jid);
		if (client.getXmppListener() != null) client.getXmppListener()
				.removeContact(c);
		Iq iq_roster = new Iq(null, Iq.T_SET);
		Element query = iq_roster.addElement(NS_IQ_ROSTER, Iq.QUERY);
		Element item = query.addElement(NS_IQ_ROSTER, "item");
		item.setAttribute("jid", c.jid);
		item.setAttribute("subscription", "remove");
		client.sendPacket(iq_roster);
		// recreateGroups();
	}

	private void recreateRoster(Element iq, boolean purge) {

		// XXX -> this should be run within a synchronized

		//		// Build a lookup table with roster
		//		Hashtable oldrst = new Hashtable();
		//		Enumeration en = contacts.elements();
		//		while (en.hasMoreElements()) {
		//			Contact c = (Contact) en.nextElement();
		//			oldrst.put(c.jid, c);
		//		}

		Element query = iq.getChildByName(null, Iq.QUERY);
		if (query == null) { return; }
		String tempVer = query.getAttribute("ver");
		if (tempVer != null) this.rosterVersion = tempVer;
		Element items[] = query.getChildrenByName(null, "item");
		if (purge && tempVer == null) {
			Hashtable newContacts = new Hashtable();
			// the old contacts that have a presence but are not 
			// in the roster
			Vector oldUnRosterContacts = new Vector();
			for (int i = 0; i < items.length; i++) {
				Contact c = getContactByJid(items[i].getAttribute("jid"));
				if (c != null) newContacts.put(c.jid, c);
			}
			Enumeration en = this.contacts.keys();
			while (en.hasMoreElements()) {
				Object ithElem = en.nextElement();
				Contact contactToRemove = (Contact) this.contacts.get(ithElem);
				Presence[] ps = contactToRemove.getAllPresences();
				if (newContacts.containsKey(ithElem) == false) {
					if (ps == null || ps.length == 0) {
						if (client.getXmppListener() != null) client
								.getXmppListener().removeContact(
										contactToRemove);
					} else {
						newContacts.put(contactToRemove.jid, contactToRemove);
						oldUnRosterContacts.addElement(contactToRemove);
					}
				}
			}
			this.contacts = newContacts;
			// these old contacts must be updated
			en = oldUnRosterContacts.elements();
			while (en.hasMoreElements()) {
				if (client.getXmppListener() != null) client.getXmppListener()
						.updateContact((Contact) en.nextElement(),
								Contact.CH_STATUS);
			}
		}

		for (int i = 0; i < items.length; i++) {
			updateRosterItem(items[i]);
		}

		// add the server contact
		// XXX: is it correct to do it here ?
		// and/or is it the nicest way to do it
		XMPPClient me = XMPPClient.getInstance();
		String myDomain = Contact.domain(me.my_jid);
		Contact c = getContactByJid(myDomain);
		if (c == null) {
			Element serverEl = new Element("", "serverEl");
			serverEl.setAttributes(new String[] { Iq.ATT_TO, "jid", "name",
					"subscription" }, new String[] { me.my_jid, myDomain,
					"Jabber Server", "both" });
			updateRosterItem(serverEl);
			/// create a a fictitious presence
			Presence p = new Presence(me.my_jid, Presence.T_SUBSCRIBED,
					"online", "Jabber Server", 1);
			p.setAttribute(Presence.ATT_FROM, myDomain);
			c = getContactByJid(myDomain);
			c.updatePresence(p);
			updateRosterItem(serverEl);
		}
	}

	/**
	 * Update roster item
	 * 
	 * @param item
	 */
	private void updateRosterItem(Element item) {
		// XXX handle the case in which the subscription is "remove"
		// XXX: A lot of the group logic should be redone
		//	for example I don't like all the translations between group <--> String and so on.. ugly
		String jid = item.getAttribute("jid");
		boolean changedGroups = false;

		Element group_elements[] = item.getChildrenByName(null, "group");
		String groups[] = new String[group_elements.length];
		for (int j = 0; j < groups.length; j++) {
			groups[j] = group_elements[j].getText();
		}

		// "ungrouped" contact if no group assign the ungrouped
		Contact c = getContactByJid(jid);
		if (c == null) {
			c = new Contact(jid, item.getAttribute("name"), item
					.getAttribute("subscription"), groups);
		} else {
			// contact found, just update
			c.subscription = item.getAttribute("subscription");
			String name = item.getAttribute("name");
			if (name != null) {
				c.name = name;
			}
			changedGroups = c.setGroups(groups);
		}

		if (changedGroups) {
			if (client.getXmppListener() != null) client.getXmppListener()
					.updateContact(c, Contact.CH_GROUP);
		}

		// XXX not sure that is completely correct...
		String subscription = item.getAttribute("subscription");
		if (subscription != null && subscription.compareTo("remove") == 0) {
			// if the user has removed me from roster
			// there is nothing to do remove contacts and nothing all
			contacts.remove(c.jid);
			if (client.getXmppListener() != null) client.getXmppListener()
					.removeContact(c);
			return;
		}

		contacts.put(c.jid, c);
		// check if this contact is one of my registered gateways
		updateGateways(c);
		if (client.getXmppListener() != null) client.getXmppListener()
				.updateContact(c, Contact.CH_STATUS);
	}

	/*
	 * Load the registered gateways from recordStore
	 */
	private void loadGateways() {
		byte[] gwBytes = cfg.getData(Config.REGISTERED_GATEWAYS.getBytes());
		// to check it is a valid xml
		if (gwBytes == null || gwBytes.length == 0) return;

		Element decodedPacket = null;
		try {
			decodedPacket = BProcessor.parse(gwBytes);
		} catch (Exception e) {
			// #mdebug
//@			e.printStackTrace();
//@			Logger.log("In loading gateways" + e.getClass().getName() + "\n"
//@					+ e.getMessage());
			//#enddebug
			return;
		}

		Element[] children = decodedPacket.getChildren();
		try {
			for (int i = 0; i < children.length; i++) {
				Element ithElem = children[i];
				String ithFrom = ithElem.getChildByName(null, "from").getText();
				String ithType = ithElem.getChildByName(null, "type").getText();
				String ithName = ithElem.getChildByName(null, "name").getText();
				this.registeredGateways.put(ithFrom, new String[] { ithType,
						ithName });
			}
		} catch (Exception e) {
			// corrupted configuration reset it
			cfg.setData(Config.REGISTERED_GATEWAYS.getBytes(), new byte[] {});
		}
	}

	/*
	 * save the registered gateways to recordStore
	 */
	private void saveGateways() {
		Element el = new Element("", "gws");
		Enumeration en = this.registeredGateways.keys();
		while (en.hasMoreElements()) {
			String ithFrom = (String) en.nextElement();
			String[] data = (String[]) this.registeredGateways.get(ithFrom);
			String ithType = data[0];
			String ithName = data[1];
			Element gw = el.addElement(null, "gw");
			gw.addElement(null, "from").addText(ithFrom);
			gw.addElement(null, "type").addText(ithType);
			gw.addElement(null, "name").addText(ithName);
		}
		cfg.setData(Config.REGISTERED_GATEWAYS.getBytes(), BProcessor
				.toBinary(el));

	}

	/*
	 * Check if contact is a gateway and in case 
	 * start the procedure to add it to the registered gateways
	 * 
	 * @param c
	 * 		The contact to check for
	 */
	private void updateGateways(Contact c) {
		if (c.jid.indexOf('@') >= 0
				|| registeredGateways.containsKey(Contact.userhost(c.jid))
		/*|| c.isVisible() == false*/) return;

		IQResultListener gw = new IQResultListener() {
			public void handleError(Element e) {
			}

			public void handleResult(Element e) {
				Element q = e.getChildByName(XMPPClient.NS_IQ_DISCO_INFO,
						Iq.QUERY);
				if (q != null) {
					String type = null;
					String name = "";
					String from = e.getAttribute("from");
					Element identity = q.getChildByName(
							XMPPClient.NS_IQ_DISCO_INFO, "identity");
					if (identity != null) {
						type = identity.getAttribute("type");
						String category = identity.getAttribute("category");
						name = identity.getAttribute("name");
						if (category.compareTo("gateway") == 0) {
							Roster.this.registeredGateways.put(from,
									new String[] { type, name });
							saveGateways();
						}
					}
				}
			}
		};

		Iq iq = new Iq(c.jid, Iq.T_GET);
		iq.addElement(XMPPClient.NS_IQ_DISCO_INFO, Iq.QUERY);
		XMPPClient.getInstance().sendIQ(iq, gw);
	}

	public Contact getContactByJid(String jid) {
		return (Contact) contacts.get(Contact.userhost(jid));
	}

	public void purge() {
		this.contacts.clear();
	}

	// XXX temporary removed
	// private void recreateGroups() {
	//	    
	// // unclassified users are group 0, remove all other groups
	// groups.removeAllElements();
	// Group ng = new Group("No Group");
	// groups.addElement(ng);
	//	    
	// Group gi;
	// Enumeration en = contacts.elements();
	// while(en.hasMoreElements()) {
	// Contact c = (Contact) en.nextElement();
	//
	// // the contact is not in any group
	// if(c.groups.length == 0) {
	// ng.addContact(c);
	// } else {
	//
	// // add a contact in all the pertaining groups
	// for(int p = 0; p < c.groups.length; p++) {
	// gi = findGroup(c.groups[p]);
	// gi.addContact(c);
	// }
	// }
	// }
	// }

	// private Group findGroup(String gname) {
	// Group g = null;
	// for(int i = 1; i < groups.size(); i++) {
	// g = (Group)groups.elementAt(i);
	// if(g.name.equals(gname)) {
	// return g;
	// }
	// }
	//	    
	// /* arrivando qui, non ho trovato il gruppo */
	// g = new Group(gname);
	// groups.addElement(g);
	// return g;
	// }
}
