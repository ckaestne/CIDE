/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SubscriptionConfirmAlert.java 1310 2009-03-23 11:12:58Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Presence;
import it.yup.xmpp.packets.Stanza;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public class SubscriptionConfirmAlert extends Alert implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private Command cmd_yes = new Command(rm.getString(ResourceIDs.STR_YES),
			Command.OK, 1);
	private Command cmd_no = new Command(rm.getString(ResourceIDs.STR_NO),
			Command.CANCEL, 1);
	private Contact contact;
	private Displayable next;

	public SubscriptionConfirmAlert(Contact contact, Displayable next) {
		super(rm.getString(ResourceIDs.STR_SUBSCRIPTION_CONFIRM));
		this.contact = contact;
		this.next = next;
		setString(rm.getString(ResourceIDs.STR_SUBSCRIPTION_REQUEST_FROM) + " "
				+ contact.jid + ". "
				+ rm.getString(ResourceIDs.STR_SUBSCRIPTION_ACCEPT));
		setType(AlertType.CONFIRMATION);
		setTimeout(Alert.FOREVER);
		addCommand(cmd_yes);
		addCommand(cmd_no);
		setCommandListener(this);
	}

	public void commandAction(Command cmd, Displayable d) {
		Display disp = Display.getDisplay(LampiroMidlet._lampiro);
		XMPPClient client = XMPPClient.getInstance();
		Presence pmsg = new Presence();
		pmsg.setAttribute(Stanza.ATT_TO, contact.jid);
		if (cmd == cmd_yes) {
			Contact c = client.roster.getContactByJid(contact.jid);
			if (c == null) {
				c = new Contact(contact.jid, contact.name, "from",
						contact.getGroups());
				// xmpp.addContact(c);
			}
			pmsg.setAttribute(Stanza.ATT_TYPE, Presence.T_SUBSCRIBED);
			client.sendPacket(pmsg);
			pmsg.setAttribute(Stanza.ATT_TYPE, Presence.T_SUBSCRIBE);
			client.sendPacket(pmsg);
		} else if (cmd == cmd_no) {
			pmsg.setAttribute(Stanza.ATT_TYPE, Presence.T_UNSUBSCRIBED);
			client.sendPacket(pmsg);
		}

		disp.setCurrent(next);
	}

}
