/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: MUC.java 1459 2009-05-11 16:51:14Z luca $
*/

package it.yup.xmpp;

import java.util.Vector;

import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Presence;
import it.yup.xmpp.packets.Stanza;

public class MUC extends Contact {

	// the topic can be null at start
	public String topic = "";

	public MUC(String jid, String name) {
		super(jid, name, "both", null);
		
		// MUCs have only one conversation
		convs.addElement(new Object[] { this.jid, new Vector() });
	}
	
	public void addMessageToHistory(String preferredResource, Message msg) {
		// for the MUC all the messages must be stored together
		// so to mantain the order 
		// the default presence is the first one
		preferredResource = this.jid;
		String type = msg.getAttribute(Message.ATT_TYPE);
		if (type==null)
			type= Message.NORMAL;
		
		compileMessage(preferredResource, msg, type);
	}

	/**
	 * Update the presence for the resource that has sent it
	 * 
	 * @param p
	 */
	public void updatePresence(Presence p) {
		if (Presence.T_UNAVAILABLE.equals(p.getAttribute(Stanza.ATT_TYPE))) {
			String offlineResource = Contact.resource(p
					.getAttribute(Stanza.ATT_FROM));
			String myResource = Contact.user(XMPPClient.getInstance()
					.getMyContact().jid);
			if (offlineResource.compareTo(myResource) == 0) {
				availability = AV_UNAVAILABLE;
				this.resources = null;
			} else {
				updateExistingPresence(p);
			}
			return;
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
			}
			availability = AV_ONLINE;
		}
	}
}
