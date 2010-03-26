/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: ContactInfoScreen.java 1356 2009-04-08 10:07:06Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Presence;
import it.yup.xmpp.packets.Stanza;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class ContactInfoScreen extends Form implements CommandListener {

	public ContactInfoScreen(Contact c) {
		super("Contact Info");
		Command cmd_close = new Command("close", 1, Command.OK);
		TextField tf_jid = new TextField("JID", c.jid, 50, TextField.UNEDITABLE);
		tf_jid.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_LEFT
				| Item.LAYOUT_NEWLINE_AFTER);
		append(tf_jid);

		if (c.name != null && c.name.length() > 0) {
			TextField tf_nick = new TextField("Nick", c.name, 50,
					TextField.UNEDITABLE);
			tf_nick.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_LEFT
					| Item.LAYOUT_NEWLINE_AFTER);
			append(tf_nick);
		}

		TextField tf_sub = new TextField("Subscription", c.subscription, 50,
				TextField.UNEDITABLE);
		tf_sub.setLayout(Item.LAYOUT_DEFAULT | Item.LAYOUT_LEFT
				| Item.LAYOUT_NEWLINE_AFTER);
		append(tf_sub);

		StringItem si_rres = new StringItem("Resources", "");
		si_rres.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_LEFT
				| Item.LAYOUT_NEWLINE_AFTER);
		append(si_rres);

		Presence[] resources = c.getAllPresences();
		for (int i = 0; i < resources.length; i++) {
			String jid = resources[i].getAttribute(Stanza.ATT_FROM);
			ImageItem ii_img = new ImageItem(null, XMPPClient.getInstance()
					.getPresenceIcon(c,null,c.getAvailability(jid)), Item.LAYOUT_LEFT,
					null);
			ii_img.setLayout(Item.LAYOUT_LEFT | Item.LAYOUT_NEWLINE_BEFORE);
			append(ii_img);
			StringItem si_fulljid = new StringItem("", jid);
			si_fulljid.setLayout(Item.LAYOUT_LEFT | Item.LAYOUT_NEWLINE_AFTER);
			append(si_fulljid);
			String status = resources[i].getStatus();
			if (status != null && status.length() > 0) {
				StringItem si_status = new StringItem("Status", status);
				si_status.setLayout(Item.LAYOUT_LEFT
						| Item.LAYOUT_NEWLINE_AFTER);
				append(si_status);
			}
		}

		StringItem si_close = new StringItem("close", null, StringItem.BUTTON);
		si_close.setDefaultCommand(cmd_close);
		si_close.setItemCommandListener(new ItemCommandListener() {
			public void commandAction(Command arg0, Item arg1) {
				LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
			}

		});
		si_close.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_EXPAND);
		append(si_close);

		addCommand(cmd_close);
		setCommandListener(this);
	}

	public void commandAction(Command cmd, Displayable disp) {
		LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
	}

}
