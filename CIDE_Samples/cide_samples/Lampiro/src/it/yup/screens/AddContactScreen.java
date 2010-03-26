/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: AddContactScreen.java 1310 2009-03-23 11:12:58Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xmpp.Contact;
import it.yup.xmpp.Group;
import it.yup.xmpp.XMPPClient;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;

/**
 * XXX: there is a warning on itemStateChanged; we implement here
 * ItemStateListener but the method clashes with a method with same signature
 * defined in Displayable. The Displayable method is package-protected so it is
 * not a problem, but it's a confusing warning... Displayable doesn't implement
 * ItemStateListener... nice job, Sun!
 */
public class AddContactScreen extends Form implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private TextField t_name;
	private TextField t_jid;
	private TextField t_group;
	private TextField t_error;

	private Command cmd_save = new Command(rm.getString(ResourceIDs.STR_SAVE),
			Command.OK, 1);
	private Command cmd_exit = new Command(rm.getString(ResourceIDs.STR_CLOSE),
			Command.CANCEL, 2);
	private ChoiceGroup ch_grps = new ChoiceGroup(rm
			.getString(ResourceIDs.STR_GROUP), ChoiceGroup.POPUP);

	public AddContactScreen() {
		super(rm.getString(ResourceIDs.STR_ADD_CONTACT));

		t_jid = new TextField(rm.getString(ResourceIDs.STR_ADDRESS), null, 64,
				TextField.EMAILADDR);
		t_name = new TextField(rm.getString(ResourceIDs.STR_NICKNAME), null,
				64, TextField.NON_PREDICTIVE);
		t_group = new TextField(rm.getString(ResourceIDs.STR_GROUP), null, 64,
				TextField.ANY);
		// create but don't append error
		t_error = new TextField(rm.getString(ResourceIDs.STR_ERROR), null, 64,
				TextField.UNEDITABLE);

		append(t_jid);

		append(t_name);
		append(t_group);

		// I add a list of groups only if there are groups
		Hashtable v = Group.getGroups();
		Enumeration en = v.elements();
		while (en.hasMoreElements()){
			Group g = (Group) en.nextElement();
			if (g.name.length()>0)
				ch_grps.append(g.name, null);
		}
		if (ch_grps.size() > 0) {
			append(ch_grps);
		}
		addCommand(cmd_save);
		addCommand(cmd_exit);
		setCommandListener(this);
	}

	public void commandAction(Command cmd, Displayable d) {
		if (cmd == cmd_save) {
			Contact c;
			String jid = t_jid.getString();
			// XXX also check if the contact is not already present in the
			// roster
			if (jid == null || !(Utils.is_email(jid))) {
				t_error.setString("bad jid");
				append(t_error);
				return;
			}
			String name = t_name.getString();
			String group = t_group.getString();
			String groups[] = null;
			if (group != null && group.length() > 0) {
				groups = new String[] { group };
			}
			c = new Contact(jid, name, null, groups);

			XMPPClient.getInstance().getRoster().subscribeContact(c, false);

			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		} else if (cmd == cmd_exit) {
			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		}
	}

	/**
	 * implementation of ItemStateListener. XXX I think this is not working
	 */
	public void _itemStateChanged(Item item) {
		if (item == ch_grps) {
			int index = ch_grps.getSelectedIndex();
			String gname = ch_grps.getString(index);
			t_group.setString(gname);
		}
	}

}
