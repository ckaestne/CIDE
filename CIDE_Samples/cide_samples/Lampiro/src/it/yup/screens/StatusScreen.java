/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: StatusScreen.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Config;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Presence;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 * Screen for selecting the status and its description message
 * TODO:
 * <ul>
 * 	<li> add icons </li>
 *  <li> set priority </li>
 * </ul>
 * 
 */
public class StatusScreen extends Form implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	// the possible status 
	private ChoiceGroup ch_status;
	private TextField tf_status;
	private Command cmd_status = new Command(rm
			.getString(ResourceIDs.STR_SET_STATUS), Command.OK, 1);
	private Command cmd_exit = new Command(rm.getString(ResourceIDs.STR_BACK),
			Command.CANCEL, 2);

	/** private constructor */
	public StatusScreen() {
		super(rm.getString(ResourceIDs.STR_CHOOSE_STATUS));
		XMPPClient client = XMPPClient.getInstance();
		ch_status = new ChoiceGroup(
				rm.getString(ResourceIDs.STR_CHOOSE_STATUS), Choice.EXCLUSIVE);
		Presence p = client.getMyContact().getPresence();

		String mapping[] = Contact.availability_mapping;
		for (int i = 0; i < mapping.length; i++) {
			ch_status.append(mapping[i], null);
			if (mapping[i].equals(p.getShow())) {
				ch_status.setSelectedIndex(i, true);
			}
		}

		append(ch_status);
		tf_status = new TextField(rm.getString(ResourceIDs.STR_STATUS_MESSAGE),
				p.getStatus(), 128, TextField.ANY);
		append(tf_status);
		addCommand(cmd_exit);
		addCommand(cmd_status);
		setCommandListener(this);
	}

	public void commandAction(Command cmd, Displayable d) {
		if (cmd == cmd_exit) {
			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		} else if (cmd == cmd_status) {
			XMPPClient client = XMPPClient.getInstance();
			String msg = tf_status.getString();
			int show_idx = ch_status.getSelectedIndex();
			int availability = show_idx;

			if (msg == null || "".equals(msg)) {
				msg = "Connected using Lampiro: http://lampiro.bluendo.com";
			}

			Config cfg = Config.getInstance();
			String show = Contact.availability_mapping[availability];
			if (!Presence.T_UNAVAILABLE.equals(show)) {
				cfg.setProperty(Config.LAST_PRESENCE_SHOW, show);
			}
			cfg.setProperty(Config.LAST_STATUS_MESSAGE, msg);
			cfg.saveToStorage();

			client.setPresence(availability, msg);

			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		}
	}

}
