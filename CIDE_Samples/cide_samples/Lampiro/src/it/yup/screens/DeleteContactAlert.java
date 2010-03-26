/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DeleteContactAlert.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

public class DeleteContactAlert extends Alert implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private Command cmd_yes = new Command(rm.getString(ResourceIDs.STR_YES),
			Command.OK, 1);
	private Command cmd_no = new Command(rm.getString(ResourceIDs.STR_NO),
			Command.CANCEL, 1);
	Contact c;

	public DeleteContactAlert(Contact _c) {
		super(rm.getString(ResourceIDs.STR_DELETE_CONTACT));
		c = _c;
		setString(rm.getString(ResourceIDs.STR_DELETE_CONTACT) + ": " + c.jid
				+ "?");
		setType(AlertType.CONFIRMATION);
		setTimeout(Alert.FOREVER);
		addCommand(cmd_yes);
		addCommand(cmd_no);
		setCommandListener(this);
	}

	public void commandAction(Command cmd, Displayable d) {
		if (cmd == cmd_yes) {
			XMPPClient.getInstance().getRoster().unsubscribeContact(c);
			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		} else if (cmd == cmd_no) {
			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		}
	}

}
