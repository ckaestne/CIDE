/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: StatusScreen.java 1561 2009-06-08 13:52:35Z luca $
*/

package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIRadioButtons;
import it.yup.ui.UIScreen;
import it.yup.ui.UITextField;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Config;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Presence;

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
public class StatusScreen extends UIScreen {
	
	private UIPanel statusPanel = new UIPanel(true,false);

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	// the possible status 
	private UIRadioButtons ch_status;
	private UITextField tf_status;
	private UITextField priority;
	private UILabel cmd_status = new UILabel(rm.getString(
			ResourceIDs.STR_SET_STATUS).toUpperCase());
	private UILabel cmd_exit = new UILabel(rm.getString(ResourceIDs.STR_CANCEL)
			.toUpperCase());

	private UIMenu closeMenu = null;

	/** private constructor */
	public StatusScreen() {
		this.setFreezed(true);
		setTitle(rm.getString(ResourceIDs.STR_CHOOSE_STATUS));
		XMPPClient client = XMPPClient.getInstance();
		Contact myContact = client.getMyContact();
		String mapping[] = Contact.availability_mapping;
		ch_status = new UIRadioButtons(mapping);
		String show = "";
		String messageStatus = "";
		String priorityVal = "";
		closeMenu = new UIMenu("");
		closeMenu.append(cmd_exit);

		if (myContact != null && myContact.getPresence() != null) {
			Presence p = myContact.getPresence();
			show = p.getShow();
			messageStatus = p.getStatus();
			priorityVal = String.valueOf(p.getPriority());
		} else {
			Config cfg = Config.getInstance();
			show = cfg.getProperty(Config.LAST_PRESENCE_SHOW, "");
			messageStatus = cfg.getProperty(Config.LAST_STATUS_MESSAGE, "");
			priorityVal = cfg.getProperty(Config.LAST_PRIORITY, "0");
			if (priorityVal.length() == 0) priorityVal = "0";
		}
		if (show == null) ch_status.setSelectedIndex(1);
		else {
			for (int i = 0; i < mapping.length; i++) {
				if (mapping[i].equals(show)) {
					ch_status.setSelectedIndex(i);
					break;
				}
			}
		}

		setMenu(new UIMenu(""));
		UIMenu menu = getMenu();
		ch_status.setSubmenu(closeMenu);
		this.statusPanel.addItem(ch_status);
		for (int i = 0; i < mapping.length; i++) {
			ch_status.getItem(i).setSubmenu(closeMenu);
		}
		tf_status = new UITextField(rm
				.getString(ResourceIDs.STR_STATUS_MESSAGE), messageStatus, 128,
				TextField.ANY);
		tf_status.setSubmenu(closeMenu);
		this.statusPanel.addItem(tf_status);
		priority = new UITextField(rm.getString(ResourceIDs.STR_PRIORITY),
				priorityVal, 10, TextField.NUMERIC);
		priority.setSubmenu(closeMenu);
		this.statusPanel.addItem(priority);

		//menu.append(cmd_exit);
		menu.append(cmd_status);
		this.setSelectedIndex(0);

		this.setFreezed(false);
		this.append(statusPanel);
		
		this.askRepaint();
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_exit) {
			UICanvas.getInstance().close(this);
			UICanvas.getInstance().show(RosterScreen.getInstance());
		} else if (cmd == cmd_status) {
			XMPPClient client = XMPPClient.getInstance();
			String msg = tf_status.getText();
			int availability = ch_status.getSelectedIndex();

			if (msg == null || "".equals(msg)) {
				msg = "Connected using Lampiro: http://lampiro.bluendo.com";
			}

			Config cfg = Config.getInstance();
			String show = Contact.availability_mapping[availability];
			if (!Presence.T_UNAVAILABLE.equals(show)) {
				cfg.setProperty(Config.LAST_PRESENCE_SHOW, show);
			}
			String priorityString = this.priority.getText();
			cfg.setProperty(Config.LAST_PRIORITY, priorityString);
			cfg.setProperty(Config.LAST_STATUS_MESSAGE, msg);
			cfg.saveToStorage();

			Contact myContact = client.getMyContact();
			if (myContact != null) {
				client.setPresence(availability, msg, Integer
						.parseInt(priorityString));
			}

			RosterScreen.closeAndOpenRoster(this);
		}
	}
}
