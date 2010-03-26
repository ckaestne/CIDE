/**
 * 
 */
package lampiro.screens;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.TextField;

import it.yup.ui.UICanvas;
import it.yup.ui.UICheckbox;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.ui.UISeparator;
import it.yup.ui.UITextField;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xml.Element;
import it.yup.xmpp.Contact;
import it.yup.xmpp.Roster;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Iq;

/**
 * @author luca
 *
 */
public class GroupsScreen extends UIScreen {

	private ResourceManager rm = ResourceManager.getManager("common", "en");

	private UILabel cmd_submit = new UILabel(rm.getString(
			ResourceIDs.STR_SUBMIT).toUpperCase());
	private UILabel cmd_cancel = new UILabel(rm.getString(
			ResourceIDs.STR_CANCEL).toUpperCase());

	private UITextField group_name;

	private Vector groupCBS = new Vector();

	/*
	 * The contact whose groups are to be changed 
	 */
	private Contact contact;

	/**
	 * @param cont 
	 * 
	 */
	public GroupsScreen(Contact cont) {
		super();

		this.contact = cont;

		this.setTitle(rm.getString(ResourceIDs.STR_HANDLE_GROUPS));

		setMenu(new UIMenu(""));
		this.getMenu().append(cmd_submit);
		this.getMenu().append(cmd_cancel);

		UIPanel contactPanel = new UIPanel();
		contactPanel.setMaxHeight(-1);
		contactPanel.setModal(true);
		this.append(contactPanel);

		group_name = new UITextField(rm.getString(ResourceIDs.STR_NEW_GROUP),
				"", 255, TextField.ANY);
		contactPanel.addItem(group_name);
		
		UISeparator sep = new UISeparator(2);
		sep.setFg_color(0xAAAAAA);
		contactPanel.addItem(sep);
		
		UILabel existingGroups = new UILabel(rm.getString(ResourceIDs.STR_EXISTING_GROUPS));
		contactPanel.addItem(existingGroups);
		Font xFont = UICanvas.getInstance().getCurrentScreen().getGraphics().getFont();
		Font lFont = Font.getFont(xFont.getFace(), Font.STYLE_BOLD, xFont
				.getSize());
		existingGroups.setFont(lFont);
		
		// we should collect all the groups
		// even the groups of the offline contacts
		Roster roster = XMPPClient.getInstance().getRoster();
		Vector groups = new Vector();
		Enumeration en = roster.contacts.elements();
		while (en.hasMoreElements()) {
			Contact ithContact = (Contact) en.nextElement();
			String[] contactGroups = ithContact.getGroups();
			for (int i = 0; i < contactGroups.length; i++) {
				String ithGroup = contactGroups[i];
				if (groups.contains(ithGroup) == false) groups
						.addElement(ithGroup);
			}
		}
		en = groups.elements();
		while (en.hasMoreElements()) {
			String ithGroup = (String) en.nextElement();
			UICheckbox ithCheckbox = new UICheckbox(ithGroup);
			contactPanel.addItem(ithCheckbox);
			this.groupCBS.addElement(ithCheckbox);
			for (int i = 0; i < contact.getGroups().length; i++) {
				if (contact.getGroups()[i].equals(ithGroup)) {
					ithCheckbox.setChecked(true);
				}
			}
		}

		contactPanel.setSelectedItem(this.group_name);
		this.setSelectedItem(contactPanel);
	}

	public void itemAction(UIItem item) {

	}

	public void menuAction(UIMenu menu, UIItem c) {
		if (c == cmd_submit) {
			Vector newGroups = new Vector();
			String groupName = this.group_name.getText();
			if (groupName.length() > 0) newGroups.addElement(groupName);

			Iq iq_roster = new Iq(null, Iq.T_SET);
			Element query = iq_roster.addElement(Roster.NS_IQ_ROSTER, Iq.QUERY);
			Element item = query.addElement(Roster.NS_IQ_ROSTER, "item");
			item.setAttribute("jid", this.contact.jid);
			if (contact.name.length() > 0) {
				item.setAttribute("name", contact.name);
			}
			XMPPClient.getInstance().sendIQ(iq_roster, null);

			iq_roster = new Iq(null, Iq.T_SET);
			iq_roster.addElement(query);
			Enumeration en = this.groupCBS.elements();
			while (en.hasMoreElements()) {
				UICheckbox ithGroup = (UICheckbox) en.nextElement();
				if (ithGroup.isChecked()) newGroups.addElement(ithGroup
						.getText());
			}
			en = newGroups.elements();
			while (en.hasMoreElements()){
				item.addElement(Roster.NS_IQ_ROSTER, "group").addText((String) en.nextElement());
			}
			XMPPClient.getInstance().sendIQ(iq_roster, null);
			RosterScreen.closeAndOpenRoster(this);
		} else if (c == cmd_cancel) {
			RosterScreen.closeAndOpenRoster(this);
		}
	}
}
