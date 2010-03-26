/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: ChatScreen.java 1586 2009-06-17 12:26:55Z luca $
*/

package lampiro.screens;

import it.yup.ui.UIButton;
import it.yup.ui.UICanvas;
import it.yup.ui.UICombobox;
import it.yup.ui.UIConfig;
import it.yup.ui.UIEmoLabel;
import it.yup.ui.UIHLayout;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UILayout;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.ui.UISeparator;
import it.yup.ui.UITextField;
import it.yup.ui.UIUtils;

// #mdebug
//@
//@import it.yup.util.Logger;
//@
// #enddebug

import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.EventQueryRegistration;
import it.yup.xmlstream.PacketListener;
import it.yup.xmpp.Config;
import it.yup.xmpp.Contact;
import it.yup.xmpp.IQResultListener;
import it.yup.xmpp.MUC;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Presence;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

import lampiro.LampiroMidlet;

public class ChatScreen extends UIScreen implements PacketListener,
		CommandListener {

	class ForwardScreen extends UIScreen {

		private UIPanel contactPanel = new UIPanel(true, true);

		private UILabel cmd_exit = new UILabel(rm.getString(
				ResourceIDs.STR_CLOSE).toUpperCase());

		Vector orderedContacts = null;

		private String messageToForward = "";

		private String fromContact = "";

		public ForwardScreen(String text, String fromContact) {
			this.append(contactPanel);
			this.messageToForward = text;
			this.fromContact = fromContact;
			this.setTitle(ChatScreen.this.getTitle());
			this.orderedContacts = RosterScreen.getOrderedContacts();
			UILabel instrLabel = new UILabel(rm
					.getString(ResourceIDs.STR_FORWARD)
					+ " \""
					+ this.messageToForward
					+ "\" "
					+ rm.getString(ResourceIDs.STR_TO).toLowerCase() + ":");
			instrLabel.setWrappable(true,
					UICanvas.getInstance().getWidth() - 40);
			instrLabel.setAnchorPoint(Graphics.HCENTER);
			Font xFont = UIConfig.font_body;
			Font lfont = Font.getFont(xFont.getFace(), Font.STYLE_BOLD, xFont
					.getSize());
			instrLabel.setFont(lfont);
			UISeparator sep = new UISeparator(2, UIUtils.colorize(
					UIConfig.bg_color, -10));
			this.contactPanel.addItem(instrLabel);
			this.contactPanel.addItem(sep);
			instrLabel.setFocusable(false);

			setMenu(new UIMenu(""));
			UIMenu menu = getMenu();
			menu.append(cmd_exit);

			for (Enumeration en = orderedContacts.elements(); en
					.hasMoreElements();) {
				Contact c = (Contact) en.nextElement();
				String printableName = c.getPrintableName();
				Presence[] ps = c.getAllPresences();
				if (ps.length == 1) {
					UILabel ithContactLabel = new UILabel(printableName);
					ithContactLabel.setFocusable(true);
					ithContactLabel.setWrappable(true, UICanvas.getInstance()
							.getWidth() - 40);
					this.contactPanel.addItem(ithContactLabel);
					ithContactLabel.setStatus(c);
				} else if (ps.length >= 2) {
					for (int i = 0; i < ps.length; i++) {
						String printablePresName = printableName
								+ " "
								+ Contact.resource(ps[i]
										.getAttribute(Message.ATT_FROM));
						UILabel ithContactLabel = new UILabel(printablePresName);
						ithContactLabel.setWrappable(true, UICanvas
								.getInstance().getWidth() - 30);
						ithContactLabel.setFocusable(true);
						this.contactPanel.addItem(ithContactLabel);
						ithContactLabel.setStatus(ps[i]);
					}
				}
			}
			// select the first contact
			if (this.contactPanel.getItems().size() >= 3) contactPanel
					.setSelectedIndex(2);
			this.setSelectedItem(contactPanel);
		}

		public void itemAction(UIItem item) {
			if (item instanceof UILabel) {
				Object statusTo = item.getStatus();
				if (statusTo != null) {
					Message msg = null;
					String forwardResource = null;
					String to = (statusTo instanceof Contact ? ((Contact) statusTo).jid
							: ((Presence) statusTo)
									.getAttribute(Message.ATT_FROM));
					Contact forwardContact = XMPPClient.getInstance()
							.getRoster().getContactByJid(to);
					if (statusTo instanceof Contact) {
						to = ((Contact) statusTo).jid;
						Presence[] resources = forwardContact.getAllPresences();
						forwardResource = (resources != null
								&& resources.length > 0 ? resources[0]
								.getAttribute(Message.ATT_FROM) : to);
					} else if (statusTo instanceof Presence) {
						to = ((Presence) statusTo)
								.getAttribute(Message.ATT_FROM);
						forwardResource = to;
					}
					msg = new Message(to, Message.CHAT);
					msg.setBody(rm.getString(ResourceIDs.STR_FORWARDED_TEXT)
							+ fromContact + ": \"" + this.messageToForward
							+ "\"");
					XMPPClient.getInstance().sendPacket(msg);

					user.addMessageToHistory(preferredResource, msg);
					if (forwardContact != null) {
						// i added to the message history and the remove it
						// so that it is mantained now but the "icon" is not updated
						forwardContact
								.addMessageToHistory(forwardResource, msg);
						forwardContact.resetMessageHistory(forwardResource);
					}

				}
				this.closeMe();
			}
		}

		public void menuAction(UIMenu menu, UIItem cmd) {
			if (cmd == cmd_exit) {
				closeMe();
			}
		}

		private void closeMe() {
			UICanvas.getInstance().close(this);
			UICanvas.getInstance().open(ChatScreen.this, true);
		}

	}

	class MUCUpdateListener extends IQResultListener {

		public void handleError(Element e) {

		}

		public void handleResult(Element e) {
			String mucJid = e.getAttribute(Message.ATT_FROM);
			XMPPClient xmppClient = XMPPClient.getInstance();
			Contact mucContact = xmppClient.getRoster().getContactByJid(mucJid);
			if (mucContact == null) return;
			MUC muc = (MUC) mucContact;
			UICanvas.getInstance().close(ChatScreen.this);
			RosterScreen.getInstance().chatWithContact(muc, null);
			MUCScreen mucScreen = (MUCScreen) RosterScreen.getChatScreenList()
					.get(mucJid);
			// the jid of the already present contact
			String cJid = ChatScreen.this.preferredResource;
			if (cJid == null) cJid = ChatScreen.this.user.jid;
			mucScreen.sendInvite(cJid);
			mucScreen.setSelectedItem(mucScreen.chatPanel);
			mucScreen.chatPanel.setSelectedItem(mucScreen.rosterCombo);
			mucScreen.keyPressed(UICanvas.getInstance().getKeyCode(
					UICanvas.FIRE));
			// meanwhile send the whole history!! :D

			Enumeration en = ChatScreen.this.current_conversation.elements();
			String myJid = xmppClient.my_jid;
			while (en.hasMoreElements()) {
				ConversationEntry entry = (ConversationEntry) en.nextElement();
				if (entry.messageType.equals(Message.CHAT)) {
					Message m = new Message(mucJid, Message.GROUPCHAT);
					m.setBody(entry.text);
					Element delay = m.addElement(XMPPClient.NS_DELAY,
							XMPPClient.DELAY);
					String completeFrom = (entry.type == ConversationEntry.ENTRY_TO ? myJid
							: cJid);
					delay.setAttribute(Message.ATT_FROM, completeFrom);
					xmppClient.sendPacket(m);
				}
			}
		}

	}

	class UICutLabel extends UIEmoLabel {

		private String completeText = "";

		public UICutLabel(String newText, String completeText) {
			super(newText);
			this.completeText = completeText;
			// TODO Auto-generated constructor stub
		}

		protected void paint(Graphics g, int w, int h) {
			super.paint(g, w, h);
			g.setColor(0x555555);
			Font currentFont = this.getFont();
			if (currentFont == null) currentFont = UIConfig.font_body;
			String moreString = " " + rm.getString(ResourceIDs.STR_MORE) + " ";
			int moreWidth = currentFont.stringWidth(moreString);
			g.fillRect(w - moreWidth - 2, h - currentFont.getHeight() - 2,
					moreWidth + 1, currentFont.getHeight() + 1);
			g.setColor(0xFFFFFF);
			g
					.drawString(moreString, w - moreWidth - 1, h
							- currentFont.getHeight() - 1, Graphics.TOP
							| Graphics.LEFT);
		}

	}

	static ResourceManager rm = ResourceManager.getManager("common", "en");

	UITextField topic_name_field = new UITextField(rm.getString(
			ResourceIDs.STR_ROOM_NAME), "", 50,
			TextField.ANY);

	UIButton topic_button = new UIButton(rm.getString(ResourceIDs.STR_SUBMIT));

	/*
	 * Used to avoid to repaint for the same status twice
	 */
	private String oldStatus = "";

	/*
	 * The area in which the screen can paint (hence excluding
	 * headers footers and title screen)
	 */
	protected int printableHeight = -1;

	/*
	 * The total area screen. Since it may vary during execution 
	 * its value is saved here
	 */
	protected int screenHeight = -1;
	/*
	 * The header used to show stats and advice;
	 */
	protected UIHLayout header;

	/*
	 * The user associated to this UIscreen
	 * 
	 */
	protected Contact user;
	UILabel cmd_exit;
	private UILabel cmd_write;
	protected UILabel cmd_clear;

	protected UILabel addUser = new UILabel(rm
			.getString(ResourceIDs.STR_GROUP_CHAT));

	protected UILabel cmd_capture_img = new UILabel(rm
			.getString(ResourceIDs.STR_SEND_IMAGE));

	protected UILabel cmd_capture_aud = new UILabel(rm
			.getString(ResourceIDs.STR_SEND_AUDIO));

	protected UILabel cmd_send_file = new UILabel(rm
			.getString(ResourceIDs.STR_SEND_FILE));

	protected UILabel cmd_forward_message = new UILabel(rm
			.getString(ResourceIDs.STR_FORWARD_MESSAGE));

	/*
	 * The menu used by UIlabels in chatscreen to close the screen
	 */
	UIMenu closeMenu;
	/*
	 * The UIlabel used by UIlabels in chatscreen to close the screen
	 */
	UILabel closeLabel;

	// #mdebug
//@	private UILabel cmd_debug = new UILabel("Debug");
	// #enddebug

	private Hashtable cmd_urls = new Hashtable();

	// XXX add a global handler for icons
	protected static Image img_msg;

	// the panel containing headers and labels
	protected UIPanel chatPanel;

	// private Image buffer;

	// /** number of currently displayed entries */
	// private int displayed_entries;

	// private boolean _entries_scrolled = false;

	static int scroll_color = 0x444444;

	private EventQueryRegistration reg = null;

	/** wrapped conversation cache */
	static Hashtable conversations = new Hashtable();
	private Vector current_conversation = null;
	private int hs = 2 * Short.parseShort(Config.getInstance().getProperty(
			Config.HISTORY_SIZE, "30"));
	UIMenu zoomSubmenu = new UIMenu("");
	UILabel zoomLabel = new UILabel("EXPAND");

	protected String preferredResource;

	UILabel headerImg = null;

	UILabel headerStatus = null;

	// the start index of the chat lines in the chat panel:
	// 0 for chats and 2 for MUCs (the rosterCombo is present)
	int chatLineStart = 0;

	static {
		try {
			img_msg = Image.createImage("/icons/message.png");
		} catch (IOException e) {
			img_msg = Image.createImage(16, 16);
		}
	}

	void askTopic() {
		UIMenu topicNameMenu = UIUtils.easyMenu(rm
				.getString(ResourceIDs.STR_CHOOSE_NAME), 10, 20, this
				.getWidth() - 20, topic_name_field);
		topic_name_field.setText(XMPPClient.getInstance().getMyContact().jid
				.replace('@', '_')
				+ MUCScreen.unnamedMUCCounter);
		MUCScreen.unnamedMUCCounter++;
		topicNameMenu.append(UIUtils.easyCenterLayout(topic_button, 100));
		topicNameMenu.setDirty(true);
		topicNameMenu.setSelectedIndex(topicNameMenu.indexOf(topic_name_field));
		this.addPopup(topicNameMenu);
	}

	public ChatScreen(Contact u, String preferredResource) {
		super();

		// lots of insertion and deletion ...
		//this.setFreezed(true);

		// prepare closeMenu
		closeMenu = new UIMenu("");
		closeLabel = new UILabel(rm.getString(ResourceIDs.STR_CLOSE)
				.toUpperCase());
		closeMenu.append(closeLabel);

		user = u;
		this.preferredResource = preferredResource;
		// buffer = null;

		cmd_exit = new UILabel(rm.getString(ResourceIDs.STR_CLOSE));
		cmd_write = new UILabel(rm.getString(ResourceIDs.STR_WRITE));
		cmd_clear = new UILabel(rm.getString(ResourceIDs.STR_CLEAR_HIST));

		setMenu(new UIMenu(""));
		UIMenu menu = getMenu();
		// #debug
//@		menu.append(cmd_debug);
		menu.append(cmd_write);
		menu.append(cmd_forward_message);
		RosterScreen rs = RosterScreen.getInstance();
		if (rs.isCameraOn()) menu.append(this.cmd_capture_img);
		if (rs.isMicOn()) menu.append(this.cmd_capture_aud);
		menu.append(this.cmd_send_file);
		menu.append(cmd_clear);
		if (rs.mucJid != null
				&& user.supportsMUC(user.getPresence(preferredResource))) menu
				.append(addUser);
		menu.append(cmd_exit);

		setTitle(rm.getString(ResourceIDs.STR_CHAT_WITH) + " "
				+ user.getPrintableName());

		current_conversation = (Vector) conversations.get(preferredResource);
		if (current_conversation == null) {
			current_conversation = new Vector();
			conversations.put(preferredResource, current_conversation);
		}
		/*
		 * XXX: hack, create an item and select it, the item won't relinquish
		 * focus
		 */

		XMPPClient client = XMPPClient.getInstance();
		chatPanel = new UIPanel();
		chatPanel.setMaxHeight(-1);
		chatPanel.setFocusable(true);
		// the panel has a contestual menu that let close the the screen
		// as well as the uilabels used to print the chat lines
		chatPanel.setSubmenu(this.closeMenu);
		chatPanel.setModal(true);
		Image img = client.getPresenceIcon(user, preferredResource, user
				.getAvailability(preferredResource));
		header = new UIHLayout(2);
		header.setGroup(false);
		String status = getPrintableStatus();
		headerStatus = new UILabel(status);
		header.insert(headerStatus, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);
		headerImg = new UILabel(img);
		headerImg.setBg_color(UIConfig.header_bg);
		headerImg.setFg_color(UIConfig.menu_title);
		headerStatus.setBg_color(UIConfig.header_bg);
		headerStatus.setFg_color(UIConfig.menu_title);
		header.insert(headerImg, 1, img.getWidth() + 2,
				UILayout.CONSTRAINT_PIXELS);
		header.setFocusable(false);
		this.append(header);
		UISeparator sep = new UISeparator(2);
		sep.setFg_color(0xCCCCCC);
		this.append(sep);
		this.append(chatPanel);
		this.setSelectedIndex(2);

		// to compute the printableHeight the currently visualizd screen must be 
		// used and not this one since it may be invisible
		getPrintableHeight(UICanvas.getInstance().getCurrentScreen()
				.getGraphics(), this.height);

		for (int j = 0; j < current_conversation.size(); j++) {
			ConversationEntry entry = (ConversationEntry) current_conversation
					.elementAt(j);
			this.updateLabel(entry);
		}
		if (chatPanel.getItems().size() > 0) {
			// remember the separator
			chatPanel.setSelectedIndex(chatPanel.getItems().size() - 2);
			chatPanel.setDirty(true);
			this.askRepaint();
		}

		// prepare zoomSubMenu
		zoomSubmenu.append(this.zoomLabel);
		zoomLabel.setAnchorPoint(Graphics.HCENTER);
		zoomSubmenu.setAbsoluteX(10);
		zoomSubmenu.setAbsoluteY(10);
		zoomSubmenu.setWidth(this.getWidth() - 30);

		// listen for all incoming messages with bodies
		EventQuery q = new EventQuery("message", null, null);
		q.child = new EventQuery("body", null, null);
		if (reg == null) {
			reg = BasicXmlStream.addEventListener(q, this);
		}

		// so to reset the status in the roster
		// it must be locked because it interferes 
		// with the items positioning in the roster screen!!!
		// hence all these operations must be done alltogether
		try {
			UICanvas.lock();
			updateConversation();
			RosterScreen roster = RosterScreen.getInstance();
			roster.reorganizeContact(user, Contact.CH_MESSAGE_READ);
		} finally {
			UICanvas.unlock();
		}

		this.askRepaint();

	}

	private String getPrintableStatus() {
		String status = "";
		Presence[] allPresences = user.getAllPresences();
		// could even be null if the user is offline now
		if (allPresences != null) {
			Presence p = user.getPresence(preferredResource);
			if (p != null) status = p.getStatus();
		}
		if (status == null || status.length() == 0) {
			if (user.name.length() > 0) status = user.getPrintableName();
			else {
				Presence p = user.getPresence(preferredResource);
				if (p != null) status = p.getAttribute(Iq.ATT_FROM);
				else
					status = user.getPrintableName();
			}
		}
		return status;
	}

	void updateResource() {
		if (preferredResource == null) return;
		Image img = XMPPClient.getInstance().getPresenceIcon(user,
				preferredResource, user.getAvailability(preferredResource));
		this.headerImg.setImg(img);
		this.headerStatus.setText(getPrintableStatus());

		String status = "";
		if (user.getAvailability(preferredResource) == Contact.AV_UNAVAILABLE) {
			status = rm.getString(ResourceIDs.STR_OFFLINE);
		} else {
			String showStatus = user.getPresence(preferredResource).getStatus();
			if (showStatus == null) showStatus = "";
			status = (rm.getString(ResourceIDs.STR_ONLINE) + ": " + showStatus);
		}

		String msgText = user.getPrintableName() + " "
				+ rm.getString(ResourceIDs.STR_IS) + " " + status;

		if (msgText.equals(oldStatus)) return;
		oldStatus = msgText;
		Message msg = null;
		msg = new Message(preferredResource, Message.HEADLINE);
		msg.setAttribute(Message.ATT_FROM, preferredResource);
		msg.setBody(msgText);
		user.addMessageToHistory(preferredResource, msg);
	}

	protected void paint(Graphics g, int w, int h) {
		if (this.updateConversation()) this.chatPanel.setDirty(true);
		RosterScreen.getInstance().reorganizeContact(user,
				Contact.CH_MESSAGE_READ);
		super.paint(g, w, h);
	}

	protected void getPrintableHeight(Graphics g, int h) {
		int maxHeight = h - 10;
		maxHeight -= this.header.getHeight(g);
		maxHeight -= this.headerLayout.getHeight(g);
		maxHeight -= this.footer.getHeight(g);
		this.printableHeight = maxHeight;
		this.screenHeight = h;
	}

	/**
	 * 
	 * @param screen_width
	 * @return true if new messages have been added
	 */
	boolean updateConversation() {
		return this.updateResConversation(null);
	}

	boolean updateResConversation(String res) {
		if (res == null) res = this.preferredResource;

		// if the user is offline i get all the conversations
		//		Presence[] ps = user.getAllPresences();
		Vector messages = null;
		//		if (ps == null || ps.length ==0)
		messages = user.getMessageHistory(res);
		//		else 
		//			messages = user.getAllMessageHistory();
		if (messages == null || messages.size() == 0) { return false; }
		for (int i = 0; i < messages.size(); i++) {
			String msg[] = (String[]) messages.elementAt(i);
			checkUrls(msg[1]);
			ConversationEntry entry = wrapMessage(msg);
			updateLabel(entry);
			current_conversation.addElement(entry);
		}
		//		if (ps == null || ps.length ==0)
		user.resetMessageHistory(res);
		//		else 
		//			user.resetAllMessageHistory();

		// must be done here after repaint to be sure all the panel
		// has been updated
		this.chatPanel.setSelectedIndex(this.chatPanel.getItems().size() - 2);
		return true;
	}

	/**
	 * @param entry
	 */
	private void updateLabel(ConversationEntry entry) {
		String s = (String) entry.text;
		s = getLabelHeader(entry) + s;
		UIEmoLabel uel = new UIEmoLabel(s);
		uel.setWrappable(true, this.width - 10);
		uel.setFocusable(true);

		int newBgColor = -1;
		if (entry.type == ConversationEntry.ENTRY_TO) {
			uel.setAnchorPoint(Graphics.RIGHT);
		} else {
			uel.setAnchorPoint(Graphics.LEFT);
			newBgColor = UIUtils.colorize(UIConfig.bg_color, -10);
			uel.setBg_color(newBgColor);
		}
		uel.setStatus(entry);

		// little modifications depending on message type
		if (entry.messageType.equals(Message.ERROR)) uel.setFg_color(0xCC0000);
		else if (entry.messageType.equals(Message.HEADLINE)) uel
				.setFg_color(0x00CC00);

		this.chatPanel.addItem(uel);
		uel.setSubmenu(this.closeMenu);
		this.checkSize(uel);
		UISeparator sep = new UISeparator(1);
		sep.setFg_color(0xCCCCCC);
		this.chatPanel.addItem(sep);

		// empty oldMessages
		while (this.chatPanel.getItems().size() > hs) {
			this.chatPanel.removeItemAt(this.chatLineStart);
			this.chatPanel.removeItemAt(this.chatLineStart);
		}
	}

	/*
	 * Checks if the current label must be cut in order to fit the screen
	 * 
	 * @param uel the current label that is inserted now
	 */
	private void checkSize(UIEmoLabel uel) {
		//UIScreen currentScreen = UICanvas.getInstance().getCurrentScreen();
		//if (currentScreen == null) return;

		// first try to use the the Chatscreen
		// otherwise the currentScreen just in case
		// i am not shown (composing or other screen has focus)
		UIScreen currentScreen = this;
		Graphics tempGraphics = currentScreen.getGraphics();
		if (tempGraphics == null) {
			currentScreen = UICanvas.getInstance().getCurrentScreen();
			if (currentScreen == null) return;
			tempGraphics = currentScreen.getGraphics();
		}

		Font oldFont = tempGraphics.getFont();
		tempGraphics.setFont(UIConfig.font_body);
		int labelHeight = uel.getHeight(tempGraphics);
		//int panelHeight = this.chatPanel.getHeight(tempGraphics);
		UIMenu itemSubMenu = uel.getSubmenu();
		if (labelHeight > this.printableHeight
				&& (itemSubMenu == null || itemSubMenu != zoomSubmenu)) {
			String oldText = uel.getText();
			Vector oldTextLines = uel.getTextLines();
			int lineHeight = labelHeight / oldTextLines.size();
			oldTextLines.setSize(printableHeight / lineHeight - 1);
			String newText = "";
			Enumeration en = oldTextLines.elements();
			while (en.hasMoreElements()) {
				newText += (en.nextElement() + "\n");
			}
			newText = newText.substring(0, newText.length() - 1);
			UICutLabel uicl = new UICutLabel(newText, oldText);
			uicl.setWrappable(true, this.width - 10);
			uicl.setSubmenu(zoomSubmenu);
			uicl.setTextLines(null);
			int index = this.chatPanel.getItems().indexOf(uel);
			this.chatPanel.insertItemAt(uicl, index);
			this.chatPanel.removeItem(uel);
		}
		uel.setTextLines(null);
		tempGraphics.setFont(oldFont);
	}

	String getLabelHeader(ConversationEntry entry) {
		String retString = "";
		if (entry.arriveTime.length() > 0) {
			retString = "[" + entry.arriveTime + "] ";
		}
		return retString;
	}

	public void showNotify() {
		// reset the status img
		Image img = XMPPClient.getInstance().getPresenceIcon(user,
				preferredResource, user.getAvailability());
		if (img != ((UILabel) this.header.getItem(1)).getImg()) {
			((UILabel) this.header.getItem(1)).setImg(img);
			this.askRepaint();
		}
	}

	private void checkUrls(String text) {
		// parse the urls and add to the command menu
		Enumeration en = Utils.find_urls(text).elements();
		while (en.hasMoreElements()) {
			String url = (String) en.nextElement();
			if (!cmd_urls.containsKey(url)) {
				UILabel cmd = new UILabel(url);
				cmd_urls.put(url, cmd);
				UIMenu menu = getMenu();
				menu.append(cmd);
			}
		}
	}

	/**
	 * Wrap a message so that it fits the windows
	 * 
	 * @param
	 * @param screen_width
	 * 
	 * @return
	 */
	ConversationEntry wrapMessage(String text[]) {

		// #ifdef TIMING
		 long t1 = System.currentTimeMillis();
		// #endif

		String myJid = Contact.userhost(XMPPClient.getInstance().my_jid);
		byte type = Contact.userhost(text[0]).equals(myJid) ? ConversationEntry.ENTRY_FROM
				: ConversationEntry.ENTRY_TO;

		// #ifdef TIMING
		 System.out.println("wrap conv: " + (System.currentTimeMillis() -
		 t1));
		// #endif

		ConversationEntry convEntry = new ConversationEntry(text[1], type);
		if (text[3] != null) convEntry.arriveTime = text[3];
		convEntry.messageType = text[4];
		return convEntry;
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_exit || cmd == this.closeLabel) {
			closeMe();
		} else if (cmd == cmd_capture_aud) {
			RosterScreen.getInstance().captureMedia(preferredResource,
					Config.audioType);
		} else if (cmd == cmd_capture_img) {
			RosterScreen.getInstance().captureMedia(preferredResource,
					Config.imgType);
		} else if (cmd == cmd_send_file) {
			AlbumScreen alb = AlbumScreen.getInstance(preferredResource);
			UICanvas.getInstance().open(alb, true);
		} else if (cmd == cmd_write) {
			SimpleComposerScreen cs = null;
			if (user instanceof MUC == true) cs = new MUCComposer((MUC) user);
			else
				cs = new SimpleComposerScreen(user, this.preferredResource);
			UICanvas.display(cs);
		} else if (cmd == cmd_clear) {
			current_conversation.removeAllElements();
			Enumeration en = cmd_urls.elements();
			UIMenu mn = getMenu();
			while (en.hasMoreElements()) {
				mn.remove((UILabel) en.nextElement());
			}
			this.setFreezed(true);
			this.chatPanel.removeAllItems();
			this.setFreezed(false);
			cmd_urls.clear();
			this.setDirty(true);
			// #mdebug
//@		} else if (cmd == cmd_debug) {
//@			Logger.log(
//@
//@			"h:" + UICanvas.getInstance().getHeight() + "w:"
//@					+ UICanvas.getInstance().getWidth() + "ch:");
//@			Logger.log(this.getGraphics().getClipHeight() + "cw:"
//@					+ this.getGraphics().getClipWidth() + "ph:"
//@					+ this.chatPanel.getHeight(getGraphics()));
//@			//
//@			DebugScreen debugScreen = new DebugScreen();
//@			UICanvas.getInstance().open(debugScreen, true);
			// #enddebug
		} else if (cmd == this.addUser) {
			askTopic();
		} else if (cmd == this.cmd_forward_message) {
			String text = null;
			UIItem selItem = (UIItem) chatPanel.getItems().elementAt(
					chatPanel.getSelectedIndex());
			if (selItem instanceof UILabel == false) return;
			text = ((UILabel) selItem).getText();
			Object status = selItem.getStatus();
			if (status instanceof ConversationEntry == false) return;
			ConversationEntry entry = (ConversationEntry) status;
			String fromContact = "";
			if (entry.type == ConversationEntry.ENTRY_TO) fromContact = XMPPClient
					.getInstance().my_jid;
			else
				fromContact = (preferredResource != null ? preferredResource
						: user.jid);
			ForwardScreen fs = new ForwardScreen(text, fromContact);
			UICanvas.getInstance().open(fs, true);
		} else if (cmd == topic_button) {
			RosterScreen rs = RosterScreen.getInstance();
			rs.muc_name_field.setText(this.topic_name_field.getText());
			this.topic_name_field.setText("");
			rs.createMUC(new RosterScreen.MUCStateHandler(
					new MUCUpdateListener()));
		} else if (cmd == this.zoomLabel) {
			UICutLabel selLabel = (UICutLabel) this.chatPanel.getSelectedItem();
			final String selText = selLabel.completeText;

			UITextField expField = new UITextField("", selText, selText
					.length(), TextField.UNEDITABLE);
			expField.setWrappable(true);
			expField.setExpandable(false);

			int maxHeight = UICanvas.getInstance().getClipHeight() - 10;
			Graphics g = this.getGraphics();
			maxHeight -= this.headerLayout.getHeight(g);
			maxHeight -= this.footer.getHeight(g);

			expField.setMaxHeight(maxHeight);
			UIMenu expandMenu = new UIMenu("");
			expandMenu.append(closeLabel);
			UIScreen zoomScreen = new UIScreen() {
				public void menuAction(UIMenu menu, UIItem cmd) {
					if (cmd == closeLabel) {
						// so that the user preferred resource is reset
						UICanvas.getInstance().close(this);
						UICanvas.getInstance().show(ChatScreen.this);
					}
				}
			};
			zoomScreen.setMenu(expandMenu);
			zoomScreen.setTitle(rm.getString(ResourceIDs.STR_EXPANDED));
			zoomScreen.append(expField);
			zoomScreen.setSelectedItem(expField);
			UICanvas.getInstance().open(zoomScreen, true);
			expField.expand();

		} else {
			if (this.cmd_urls.contains(cmd)) {
				String url = ((UILabel) cmd).getText();

				try {
					LampiroMidlet._lampiro.platformRequest(url);
				} catch (ConnectionNotFoundException e) {
					UICanvas.showAlert(AlertType.ERROR, "URL Error",
							"Can't open URL:" + e.getMessage());
				}
			}
		}
	}

	public void closeMe() {
		// so that the user preferred resource is reset
		user.lastResource = null;
		Hashtable chatScreenList = RosterScreen.getChatScreenList();
		// better to search me and nothing else
		Enumeration en = chatScreenList.keys();
		while (en.hasMoreElements()) {
			Object ithKey = en.nextElement();
			Object object = chatScreenList.get(ithKey);
			if (object == this) chatScreenList.remove(ithKey);
		}
		//chatScreenList.remove(this.user.jid);

		// reset the status in the roster
		RosterScreen roster = RosterScreen.getInstance();
		roster.updateContact(user, Contact.CH_MESSAGE_READ);
		if (reg != null) {
			reg.remove();
			reg = null;
		}
		// to reset the header from outside this screen to ;
		roster.setDirty(true);
		UICanvas.getInstance().close(this);
	}

	boolean isPrintable(int key) {
		int keyNum = -1;
		switch (key) {
			case Canvas.KEY_NUM0:
			case Canvas.KEY_NUM1:
			case Canvas.KEY_NUM2:
			case Canvas.KEY_NUM3:
			case Canvas.KEY_NUM4:
			case Canvas.KEY_NUM5:
			case Canvas.KEY_NUM6:
			case Canvas.KEY_NUM7:
			case Canvas.KEY_NUM8:
			case Canvas.KEY_NUM9:
				keyNum = key;
		}
		if (keyNum == -1
				&& UICanvas.getInstance().getGameAction(key) != Canvas.FIRE) { return false; }
		return true;
	}

	/**
	 * Handle key events
	 * 
	 * @param kc
	 *            the pressed key
	 */
	public boolean keyPressed(int kc) {
		if (this.popupList.size() == 0
				&& this.getMenu().isOpenedState() == false) {
			int ga = UICanvas.getInstance().getGameAction(kc);

			boolean ip = isPrintable(kc);

			if (ip || ga == Canvas.FIRE) {
				if (this.chatPanel.getSelectedIndex() < 0
						|| this.chatPanel.getItems().elementAt(
								this.chatPanel.getSelectedIndex()) instanceof UICombobox == false) {
					SimpleComposerScreen cs = null;
					if (user instanceof MUC == true) cs = new MUCComposer(
							(MUC) user);
					else
						cs = new SimpleComposerScreen(user,
								this.preferredResource);
					UICanvas.display(cs);
					return true;
				}
			}

			switch (ga) {
				case Canvas.RIGHT: {
					RosterScreen roster = RosterScreen.getInstance();
					roster.updateContact(user, Contact.CH_MESSAGE_READ);
					RosterScreen.showNextScreen(this);
					return true;
				}
				case Canvas.LEFT: {
					RosterScreen roster = RosterScreen.getInstance();
					roster.updateContact(user, Contact.CH_MESSAGE_READ);
					RosterScreen.showPreviousScreen(this);
					return true;
				}
			}
		}
		return super.keyPressed(kc);
	}

	public void packetReceived(Element e) {
		// avoid useless repaint when computing conversation
		this.setFreezed(true);
		// check if it is a msg for myself so the img_msg is not shown
		// and avoid fast bot problem
		// String fullJid = user.getFullJid();
		// fullJid could be null for offline contact
		// so let's use in that case the userhost and nothing more
		// if (fullJid == null) fullJid = user.jid;
		// String userHost = Contact.userhost(fullJid);
		boolean myPacket = isMyPacket(e);
		boolean updated = false;
		if (myPacket && needDisplay()
				&& this == UICanvas.getInstance().getCurrentScreen()) {
			try {
				UICanvas.lock();
				updated = updateConversation();
				RosterScreen.getInstance().reorganizeContact(user,
						Contact.CH_MESSAGE_READ);
			} finally {
				UICanvas.unlock();
			}

			if (updated == false) {
				this.setFreezed(false);
				return;
			}
		} else if (myPacket == false) {
			((UILabel) this.header.getItem(1)).setImg(img_msg);
			updated = true;
			/*((UILabel) this.header.getItem(1)).setDirty(true);
			this.askRepaint();*/
		}
		this.setFreezed(false);
		if (updated) askRepaint();

	}

	boolean needDisplay() {
		return user.getHistoryLength(this.preferredResource) > 0;
	}

	boolean isMyPacket(Element e) {
		return e.getAttribute(Iq.ATT_FROM).equals(preferredResource);
	}

	/**
	 * Entry for a conversation
	 */
	static class ConversationEntry {
		public String messageType;
		/** message from */
		public static final byte ENTRY_FROM = 0;
		/** message to */
		public static final byte ENTRY_TO = 1;

		/** previous message wrap XXX ? */
		public static final byte ENTRY_ERROR = 2;

		/** message type in / on */
		public byte type;

		/** the message itself */
		public String text;

		/** first line of the entry that is displayed */
		public int entry_offset = 0;
		public String from = "";
		public String arriveTime = "";

		public ConversationEntry(String text, byte type) {
			this.type = type;
			this.text = text;
		}
	}

	public void commandAction(Command cmd, Displayable disp) {
		UICanvas.display(null);
		this.dirty = true;
		this.askRepaint();

	}
}
