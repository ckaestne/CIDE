/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: MUCScreen.java 1586 2009-06-17 12:26:55Z luca $
*/

package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UICombobox;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIScreen;
import it.yup.util.ResourceIDs;
import it.yup.xml.Element;
import it.yup.xmlstream.PacketListener;
import it.yup.xmpp.Contact;
import it.yup.xmpp.MUC;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.DataForm;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Presence;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class MUCScreen extends ChatScreen implements PacketListener {

	public static int unnamedMUCCounter = 0;

	UICombobox rosterCombo;
	private Vector mucCandidates = new Vector();

	private UILabel cmd_topic = new UILabel(rm
			.getString(ResourceIDs.STR_SET_TOPIC));

	private Vector orderedContacts;

	public MUCScreen(Contact u) {
		super(u, u.jid);
		chatLineStart = 1;
		//this.setFreezed(true);
		this.getMenu().insert(1, cmd_topic);
		this.getMenu().remove(this.cmd_capture_img);
		this.getMenu().remove(this.cmd_capture_aud);
		this.getMenu().remove(this.cmd_send_file);

		setTitle(rm.getString(ResourceIDs.STR_GROUP_CHAT) + " "
				+ user.getPrintableName());
		this.rosterCombo = new UICombobox(rm
				.getString(ResourceIDs.STR_ADD_USER), true);
		this.rosterCombo.setSubmenu(this.closeMenu);
		
		addUser.setText(rm
				.getString(ResourceIDs.STR_ADD_USER));

		this.chatPanel.insertItemAt(rosterCombo, 0);
		this.rosterCombo.setSelected(false);
		if (chatPanel.getItems().size() > 5) {
			// remember the separator
			chatPanel.setSelectedIndex(chatPanel.getItems().size() - 2);
		} else {
			chatPanel.setSelectedItem(rosterCombo);
		}
		chatPanel.setDirty(true);
		UILabel mucName = (UILabel) header.getItem(0);
		mucName.setText(rm.getString(ResourceIDs.STR_TOPIC) + ": "
				+ ((MUC) this.user).topic);
		//this.setFreezed(false);
		this.askRepaint();
	}

	private void populateRosterCombo() {
		rosterCombo.removeAll();
		mucCandidates.removeAllElements();

		this.orderedContacts = RosterScreen.getOrderedContacts();

		for (Enumeration en = orderedContacts.elements(); en.hasMoreElements();) {
			Contact c = (Contact) en.nextElement();
			String printableName = c.getPrintableName();
			Presence[] ps = c.getAllPresences();
			if (ps.length == 1 && c.supportsMUC(ps[0])) {
				this.rosterCombo.append(printableName);
				this.mucCandidates.addElement(ps[0]);
			} else if (ps.length >= 2) {
				for (int i = 0; i < ps.length; i++) {
					if (c.supportsMUC(ps[1])) {
						this.rosterCombo.append(printableName
								+ " "
								+ Contact.resource(ps[i]
										.getAttribute(Message.ATT_FROM)));
						this.mucCandidates.addElement(ps[i]);
					}
				}
			}
		}
	}

	public boolean keyPressed(int kc) {
		if (this.popupList.size() == 0
				&& this.getMenu().isOpenedState() == false) {
			int ga = UICanvas.getInstance().getGameAction(kc);

			if (ga == Canvas.FIRE) {
				if (this.chatPanel.getItems().elementAt(
						chatPanel.getSelectedIndex()) == rosterCombo) {
					populateRosterCombo();
				}
			}
		}
		return super.keyPressed(kc);
	}

	public void menuAction(UIMenu menu, UIItem c) {
		if (c == cmd_exit || c == this.closeLabel) {
			// for MUCs the conversation is downloaded again at any reconnection
			// conversations.remove(user.jid);
			closeMe();
		} else if (c == topic_button) {
			String topicName = this.topic_name_field.getText();
			XMPPClient client = XMPPClient.getInstance();
			Message msg = new Message(Contact.userhost(this.user.jid),
					Message.GROUPCHAT);
			msg.addElement(null, "subject").addText(topicName);
			client.sendPacket(msg);
			this.topic_name_field.setText("");
			return;
		} else if (c == cmd_topic) {
			askTopic();
			return;
		} else if (c == addUser) {
			this.setSelectedIndex(this.indexOf(this.rosterCombo));
			chatPanel.setSelected(false);
			chatPanel.setDirty(true);
			populateRosterCombo();
			this.rosterCombo.openMenu();
			return;
		} else if (c == cmd_clear) {
			super.menuAction(menu, c);
			this.chatPanel.insertItemAt(rosterCombo, 0);
			return;
		}

		super.menuAction(menu, c);
	}

	boolean needDisplay() {
		Vector allConvs = user.getAllConvs();
		Enumeration en = allConvs.elements();
		while (en.hasMoreElements()) {
			Object[] coupleConv = (Object[]) en.nextElement();
			Vector messages = (Vector) coupleConv[1];
			if (messages.size() > 0) { return true; }
		}
		return false;
	}

	boolean isMyPacket(Element e) {
		return Contact.userhost(e.getAttribute(Iq.ATT_FROM)).equals(
				preferredResource);
	}

	/**
	 * 
	 * @param screen_width
	 * @return true if new messages have been added
	 */
	boolean updateConversation() {
		return this.updateResConversation(this.user.jid);
	}

	void updateResource() {

	}

	static void handlePresence(MUC presenceMUC, Element e, String type) {
		String jidName = Contact.resource(e.getAttribute(Message.ATT_FROM));
		// avoid printing myself data here 
		boolean goingOffline = type != null
				&& type.compareTo("unavailable") == 0;
		if (jidName.equals(Contact.user(XMPPClient.getInstance().my_jid))
				&& goingOffline) return;
		Message msg = null;
		msg = new Message(presenceMUC.jid, Message.HEADLINE);
		msg.setAttribute(Message.ATT_FROM, e.getAttribute(Message.ATT_FROM));
		String msgText = "";
		boolean send = false;
		String endString = " "
				+ rm.getString(ResourceIDs.STR_GROUP_CHAT).toLowerCase() + ".";
		if (type == null) {
			send = true;
			msgText = jidName + " " + rm.getString(ResourceIDs.STR_JOINED_MUC)
					+ endString;
		} else if (goingOffline) {
			send = true;
			msgText = jidName + " " + rm.getString(ResourceIDs.STR_LEFT_MUC)
					+ endString;
		}
		if (send == true) {
			msg.setBody(msgText);
			presenceMUC.lastResource = null;
			presenceMUC
					.addMessageToHistory(e.getAttribute(Message.ATT_TO), msg);
			RosterScreen rs = RosterScreen.getInstance();
			rs.updateContact(presenceMUC, Contact.CH_MESSAGE_NEW);
			rs.askRepaint();
			UIScreen ms = (UIScreen) RosterScreen.getChatScreenList().get(
					presenceMUC.jid);
			if (ms != null) UICanvas.getInstance().askRepaint(ms);
		}
	}

	void sendInvite(String ithContact) {
		Message msg = new Message(user.jid, null);
		Element x = new Element(XMPPClient.NS_MUC_USER, DataForm.X);
		msg.addElement(x);
		Element invite = new Element("", "invite");
		invite.setAttribute(Message.ATT_TO, ithContact);
		x.addElement(invite);
		XMPPClient.getInstance().sendPacket(msg);
	}

	protected void getPrintableHeight(Graphics g, int h) {
		super.getPrintableHeight(g, h);
		if (rosterCombo == null) {
			// this method could be called without rosterCombo 
			// being initialized.
			this.rosterCombo = new UICombobox(rm
					.getString(ResourceIDs.STR_ADD_USER), true);
		}
		this.printableHeight -= this.rosterCombo.getHeight(g);
	}

	ConversationEntry wrapMessage(String text[]) {

		// #ifdef TIMING
		long t1 = System.currentTimeMillis();
		// #endif

		byte type = (text[2] != null && Contact.resource(text[2]) != null && Contact
				.resource(text[2]).equals(Contact.user(text[0]))) ? ConversationEntry.ENTRY_TO
				: ConversationEntry.ENTRY_FROM;

		// #ifdef TIMING
		 System.out.println("wrap conv: " + (System.currentTimeMillis() -
		 t1));
		// #endif

		String labelText = "";
		labelText += text[1];
		ConversationEntry convEntry = new ConversationEntry(labelText, type);
		if (type == ConversationEntry.ENTRY_FROM && text[2] != null) convEntry.from = text[2];
		if (text[3] != null) convEntry.arriveTime = text[3];
		convEntry.messageType = text[4];
		return convEntry;
	}

	public void itemAction(UIItem c) {
		if (c == this.rosterCombo) {
			int[] selIndeces = this.rosterCombo.getSelectedIndeces();
			for (int i = 0; i < selIndeces.length; i++) {
				int ithInt = selIndeces[i];
				Presence p = (Presence) this.mucCandidates.elementAt(ithInt);
				this.sendInvite(p.getAttribute(Message.ATT_FROM));
			}
			this.setSelectedIndex(this.indexOf(this.chatPanel));
			boolean flags[] = new boolean[this.mucCandidates.size()];
			for (int i = 0; i < flags.length; i++)
				flags[i] = false;
			this.rosterCombo.setSelectedFlags(flags);
			this.chatPanel
					.setSelectedIndex(this.chatPanel.getItems().size() - 2);

			rosterCombo.removeAll();
			mucCandidates.removeAllElements();
			if (orderedContacts != null) orderedContacts.removeAllElements();

			this.askRepaint();
			return;
		}
		super.itemAction(c);
	}

	String getLabelHeader(ConversationEntry entry) {
		String retString = "";
		int fromLength = entry.from.length();
		int arriveTimeLength = entry.arriveTime.length();
		if (arriveTimeLength > 0 || fromLength > 0) {
			retString = "[";
			if (fromLength > 0) retString += Contact.resource(entry.from);
			if (fromLength > 0 && arriveTimeLength > 0) retString += " ";
			if (arriveTimeLength > 0) retString += entry.arriveTime;
			retString += "] ";
		}
		return retString;
	}

}
