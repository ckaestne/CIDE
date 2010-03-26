/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: MessageComposerScreen.java 1312 2009-03-24 12:04:13Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Stanza;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class MessageComposerScreen extends Form implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");
	private Displayable next_screen = null;
	private Contact user = null;
	private ChoiceGroup cg_type = new ChoiceGroup("Type", ChoiceGroup.EXCLUSIVE);
	private TextField tf_subject = new TextField("Subject", "", 100,
			TextField.ANY);
	private TextField tf_body = new TextField("Message", "", 1000,
			TextField.ANY);
	private StringItem btn_send = new StringItem("", "send", StringItem.BUTTON);

	private Command cmd_send = new Command(rm.getString(ResourceIDs.STR_SEND),
			Command.ITEM, 1);
	private Command cmd_cancel = new Command(rm
			.getString(ResourceIDs.STR_CANCEL), Command.SCREEN, 2);

	public static int MESSAGE;
	public static int CHAT;

	public MessageComposerScreen(Contact user, Displayable next_screen,
			int default_type) {
		super(rm.getString(ResourceIDs.STR_MESSAGE_TO) + " "
				+ user.getPrintableName());
		this.next_screen = next_screen;
		this.user = user;
		cg_type.append("message", null);
		cg_type.append("chat", null);
		cg_type.setSelectedIndex(default_type, true);
		append(cg_type);
		if (default_type == MessageComposerScreen.MESSAGE) {
			append(tf_subject);
		}
		append(tf_body);

		append(btn_send);
		btn_send.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_EXPAND
				| Item.LAYOUT_NEWLINE_AFTER);
		btn_send.setDefaultCommand(cmd_send);

		// set the command listener for the login button
		btn_send.setItemCommandListener(new ItemCommandListener() {

			public void commandAction(Command cmd, Item item) {
				MessageComposerScreen.this.commandAction(cmd, null);
			}

		});

		addCommand(cmd_send);
		addCommand(cmd_cancel);
		setCommandListener(this);

		setItemStateListener(new ItemStateListener() {

			public void itemStateChanged(Item i) {
				if (cg_type == i) {
					if (cg_type.getSelectedIndex() == 0) {
						insert(1, tf_subject);
					} else {
						delete(1);
					}
				}
			}
		});
	}

	public void commandAction(Command cmd, Displayable arg) {
		if (cmd == cmd_send) {
			Message msg;
			if (cg_type.getSelectedIndex() == 0) {
				msg = new Message(user.jid, null);
				String subject = tf_subject.getString();
				if (subject != null && !"".equals(subject)) {
					msg.addElement(Stanza.NS_JABBER_CLIENT, Message.SUBJECT).addText(subject);
				}
			} else {
				msg = new Message(user.jid, "chat");
			}

			String body = tf_body.getString();
			if (body == null) body = "";
			msg.setBody(body);
			XMPPClient.getInstance().sendPacket(msg);
			user.addMessageToHistory(null,msg);
			LampiroMidlet.disp.setCurrent(next_screen);
		} else if (cmd == cmd_cancel) {
			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		}
	}

}
