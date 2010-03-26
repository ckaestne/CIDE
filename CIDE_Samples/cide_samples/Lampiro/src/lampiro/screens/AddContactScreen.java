/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: AddContactScreen.java 1586 2009-06-17 12:26:55Z luca $
*/

package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UICombobox;
import it.yup.ui.UIConfig;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIScreen;
import it.yup.ui.UITextField;
import it.yup.ui.UIUtils;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xml.Element;
import it.yup.xmpp.Contact;
import it.yup.xmpp.IQResultListener;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Iq;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

/**
 * XXX: there is a warning on itemStateChanged; we implement here
 * ItemStateListener but the method clashes with a method with same signature
 * defined in Displayable. The Displayable method is package-protected so it is
 * not a problem, but it's a confusing warning... Displayable doesn't implement
 * ItemStateListener... nice job, Sun!
 */
public class AddContactScreen extends UIScreen {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");
	/*
	 * the found gateways
	 */
	private Vector gateways = new Vector();

	private UITextField t_name;
	private UITextField t_jid;
	private UILabel t_help;
	//private UITextField t_group;
	private UITextField t_error;
	private UICombobox t_type;

	private UILabel cmd_save = new UILabel(rm.getString(ResourceIDs.STR_SAVE)
			.toUpperCase());
	private UILabel cmd_exit = new UILabel(rm.getString(ResourceIDs.STR_CLOSE)
			.toUpperCase());

	private XMPPClient xmppClient = XMPPClient.getInstance();

	public AddContactScreen() {
		this.setFreezed(true);
		setTitle(rm.getString(ResourceIDs.STR_ADD_CONTACT));
		t_help = new UILabel(rm.getString(ResourceIDs.STR_ADDRESS));
		t_help.setWrappable(true, UICanvas.getInstance().getWidth() - 10);
		t_help.setFocusable(false);
		Font xFont = UIConfig.font_body;
		Font lFont = Font.getFont(xFont.getFace(), Font.STYLE_BOLD, xFont
				.getSize());
		t_help.setFont(lFont);

		t_jid = new UITextField("", null, 64, TextField.EMAILADDR);
		t_name = new UITextField(rm.getString(ResourceIDs.STR_NICKNAME), null,
				64, TextField.NON_PREDICTIVE);
		//t_group = new UITextField(rm.getString(ResourceIDs.STR_GROUP), null,
		//		64, TextField.ANY);
		// create but don't append error
		t_error = new UITextField(rm.getString(ResourceIDs.STR_ERROR), null,
				64, TextField.UNEDITABLE);
		t_type = new UICombobox(rm.getString(ResourceIDs.STR_CONTACT_TYPE),
				false);
		Image img = null;
		try {
			img = Image.createImage("/transport/jabber.png");
		} catch (IOException e) {
		}
		UILabel transportLabel = new UILabel(img, "Jabber");
		t_type.append(transportLabel);
		t_type.setSelectedIndex(0);

		append(t_type);
		append(t_help);
		append(t_jid);
		append(t_name);
		//append(t_group);
		

		/*
		 * XXX: useless? // I add a list of groups only if there are groups
		 * Vector v = XMPPClient.getInstance().getRoster().groups; for(int i =
		 * 1; i < v.size(); i++) { Group g = (Group) v.elementAt(i);
		 * ch_grps.append(g.name, null); } if(ch_grps.size() > 0) {
		 * append(ch_grps); }
		 */
		setMenu(UIUtils.easyMenu("", -1, -1, -1, cmd_save));
		getMenu().append(cmd_exit);
		this.setFreezed(false);
		this.askRepaint();
		getGateways();
	}

	private void getGateways() {
		Hashtable registeredGateways = XMPPClient.getInstance().getRoster().registeredGateways;
		Enumeration en = registeredGateways.keys();
		while (en.hasMoreElements()) {
			String ithFrom = (String) en.nextElement();
			String[] data = (String[]) registeredGateways.get(ithFrom);
			String ithType = data[0];
			String ithName = data[1];
			Image img = null;
			Contact rosterContact = XMPPClient.getInstance().getRoster()
					.getContactByJid(ithFrom);
			if (rosterContact == null || rosterContact.isVisible() == false) continue;

			if (ithType != null) {
				try {
					img = Image.createImage("/transport/" + ithType + ".png");
				} catch (IOException ex) {
					try {
						img = Image.createImage("/transport/transport.png");
					} catch (IOException e1) {
					}
				}
			} else {
				try {
					img = Image.createImage("/transport/transport.png");
				} catch (IOException e1) {
				}
			}
			UILabel transportLabel = new UILabel(img, ithName);
			t_type.append(transportLabel);
			this.gateways.addElement(ithFrom);
		}
	}

	public void itemAction(UIItem cmd) {
		if (cmd == t_type) {
			if (t_type.getSelectedIndex() > 0) {
				IQResultListener gjh = new IQResultListener() {
					public void handleError(Element e) {
					}

					public void handleResult(Element e) {
						Element query = e.getChildByName(
								XMPPClient.JABBER_IQ_GATEWAY, Iq.QUERY);
						Element desc = query.getChildByName(
								XMPPClient.JABBER_IQ_GATEWAY, "desc");
						t_help.setText(desc.getText());
						AddContactScreen.this.askRepaint();
					}
				};
				String to = (String) this.gateways.elementAt(this.t_type
						.getSelectedIndex() - 1);
				Iq iq = new Iq(to, Iq.T_GET);
				iq.addElement(XMPPClient.JABBER_IQ_GATEWAY, Iq.QUERY);
				xmppClient.sendIQ(iq, gjh);
			} else {
				t_help.setText(rm.getString(ResourceIDs.STR_ADDRESS));
				this.askRepaint();
			}
		}
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_save) {
			if (this.t_type.getSelectedIndex() == 0) {
				String jid = t_jid.getText();
				String name = t_name.getText();
				String group = "";//t_group.getText();
				registerContact(jid, name, group);
			} else {
				IQResultListener gjh = new IQResultListener() {
					public void handleError(Element e) {
					}

					public void handleResult(Element e) {
						Element query = e.getChildByName(
								XMPPClient.JABBER_IQ_GATEWAY, Iq.QUERY);
						// some perverted gateway answers the wrong way !!!
						Element q = query.getChildByName(
								XMPPClient.JABBER_IQ_GATEWAY, "prompt");
						if (q == null) {
							q = query.getChildByName(
									XMPPClient.JABBER_IQ_GATEWAY, "jid");
						}
						String jid = q.getText();
						String name = t_name.getText();
						String group = "";//t_group.getText();
						AddContactScreen.this.registerContact(jid, name, group);

					}
				};
				String to = (String) this.gateways.elementAt(this.t_type
						.getSelectedIndex() - 1);
				Iq iq = new Iq(to, Iq.T_SET);
				Element query = iq.addElement(XMPPClient.JABBER_IQ_GATEWAY,
						Iq.QUERY);
				Element prompt = query.addElement(XMPPClient.JABBER_IQ_GATEWAY,
						Iq.PROMPT);
				prompt.addText(this.t_jid.getText());
				xmppClient.sendIQ(iq, gjh);
			}
			UICanvas.getInstance().show(RosterScreen.getInstance());
			UICanvas.getInstance().close(this);
		} else if (cmd == cmd_exit) {
			RosterScreen.closeAndOpenRoster(this);
		}
	}

	/**
	 * 
	 */
	private void registerContact(String jid, String name, String group) {
		Contact c;
		// XXX also check if the contact is not already present in the
		// roster
		if (jid == null || !(Utils.is_jid(jid))) {
			t_error.setText("bad jid");
			append(t_error);
			return;
		}

		String groups[] = null;
		if (group != null && group.length() > 0) {
			groups = new String[] { group };
		}
		c = new Contact(jid, name, null, groups);

		xmppClient.getRoster().subscribeContact(c, false);
	}
}
