/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: RosterScreen.java 1597 2009-06-19 11:54:12Z luca $
*/

package lampiro.screens;

import it.yup.screens.ScreenSaver;
import it.yup.ui.UIAccordion;
import it.yup.ui.UIButton;
import it.yup.ui.UICanvas;
import it.yup.ui.UIConfig;
import it.yup.ui.UIGauge;
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
import it.yup.ui.UIVLayout;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xml.BProcessor;
import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.PacketListener;
import it.yup.xmpp.Config;
import it.yup.xmpp.Contact;
import it.yup.xmpp.DataFormListener;
import it.yup.xmpp.FTReceiver;
import it.yup.xmpp.FTSender;
import it.yup.xmpp.IQResultListener;
import it.yup.xmpp.MUC;
import it.yup.xmpp.Roster;
import it.yup.xmpp.Task;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.FTReceiver.FTREventHandler;
import it.yup.xmpp.FTReceiver.OpenListener;
import it.yup.xmpp.FTSender.FTSEventHandler;
import it.yup.xmpp.packets.DataForm;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Presence;
import it.yup.xmpp.packets.Stanza;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TimerTask;
import java.util.Vector;

import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;

import lampiro.LampiroMidlet;

//#mdebug

import it.yup.util.Logger;

//#enddebug

public class RosterScreen extends UIScreen implements PacketListener,
		FTREventHandler, FTSEventHandler, XMPPClient.XmppListener {

	private WaitScreen dataformWaitingScreen = null;

	/*
	 * the volume for playing tones
	 */
	private int volume;

	/*
	 * a mask used to playing tones
	 */
	private boolean play_flags[];

	/*
	 * The MUC component jid
	 */
	String mucJid = null;

	/*
	 * The upload file contact
	 */
	String uploadJid = null;

	/*
	 * The upload file suffix
	 */
	String uploadSuffix = "";

	/*
	 * The base path used for construct the file upload url
	 */
	String basePath = "";

	/*
	 * The server used to explore gateways 
	 */
	private String gatewaysServer = null;

	/*
	 * If true the offline contacts are shown.
	 */
	private boolean show_offlines = false;

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private UILabel cmd_fts = new UILabel(rm
			.getString(ResourceIDs.STR_FT_STATUS));
	private UILabel cmd_send = new UILabel(rm
			.getString(ResourceIDs.STR_SEND_MESSAGE));
	private UILabel cmd_chat = new UILabel(rm.getString(ResourceIDs.STR_CHAT));
	private UILabel cmd_logout = new UILabel(rm
			.getString(ResourceIDs.STR_LOGOUT));
	private UILabel cmd_help = new UILabel(rm.getString(ResourceIDs.STR_HELP));
	// XXX info delayed
	// private Command cmd_info = new
	// Command(rm.getString(ResourceIDs.STR_EDIT_CONTACT), Command.SCREEN, 3);
	private UILabel cmd_state = new UILabel(rm
			.getString(ResourceIDs.STR_CHANGE_STATUS));
	private UILabel toggle_offline = new UILabel(rm
			.getString(ResourceIDs.STR_SHOW_OFFLINE));
	private UILabel gateways_discovery = new UILabel(rm
			.getString(ResourceIDs.STR_GATEWAY_DISCOVERY));

	private UILabel cmd_addc = new UILabel(rm
			.getString(ResourceIDs.STR_ADD_CONTACT));
	private UILabel cmd_delc = new UILabel(rm
			.getString(ResourceIDs.STR_DELETE_CONTACT));
	private UILabel cmd_album = new UILabel(rm
			.getString(ResourceIDs.STR_MM_ALBUM));
	// XXX update delayed
	// private Command cmd_reload = new
	// Command(rm.getString(ResourceIDs.STR_RELOAD_CONTACT), Command.SCREEN, 6);
	private UILabel cmd_exit = new UILabel(rm.getString(ResourceIDs.STR_EXIT));
	// #mdebug
	private UILabel cmd_debug = new UILabel(rm.getString(ResourceIDs.STR_DEBUG));
	// #enddebug
	private UILabel cmd_contact_capture_img = new UILabel(rm
			.getString(ResourceIDs.STR_SEND_IMAGE));
	private UILabel cmd_contact_capture_aud = new UILabel(rm
			.getString(ResourceIDs.STR_SEND_AUDIO));
	private UILabel cmd_capture_img = new UILabel(rm
			.getString(ResourceIDs.STR_CAPTURE_IMAGE));
	private UILabel cmd_refresh_roster = new UILabel(rm
			.getString(ResourceIDs.STR_REFRESH_ROSTER));
	private UILabel cmd_capture_aud = new UILabel(rm
			.getString(ResourceIDs.STR_CAPTURE_AUDIO));
	private UILabel cmd_about = new UILabel(rm.getString(ResourceIDs.STR_ABOUT));
	private UILabel cmd_querycmd = new UILabel(rm
			.getString(ResourceIDs.STR_QUERYCMD));
	private UILabel cmd_send_file = new UILabel(rm
			.getString(ResourceIDs.STR_SEND_FILE));
	private UILabel cmd_options = new UILabel(rm
			.getString(ResourceIDs.STR_OPTIONS_SETUP));
	private UILabel cmd_tasks = new UILabel(rm
			.getString(ResourceIDs.STR_PENDINGTASK));
	private UILabel cmd_details = new UILabel(rm
			.getString(ResourceIDs.STR_SEE_DETAILS));
	private UILabel cmd_groups = new UILabel(rm
			.getString(ResourceIDs.STR_HANDLE_GROUPS));
	private UILabel cmd_exit_muc = new UILabel(rm
			.getString(ResourceIDs.STR_EXIT_MUC));
	private UILabel cmd_close_muc = new UILabel(rm
			.getString(ResourceIDs.STR_CLOSE_MUC));
	private UILabel cmd_mucs = new UILabel(rm
			.getString(ResourceIDs.STR_GROUP_CHAT));
	UITextField muc_name_field = new UITextField("Group chat name", "", 50,
			TextField.ANY);
	private UIButton muc_button = new UIButton(rm
			.getString(ResourceIDs.STR_SUBMIT));
	private UIButton refresh_gateways = new UIButton(rm
			.getString(ResourceIDs.STR_REFRESH));
	/*
	 * the container for the refresh gateways button
	 */
	private UIHLayout refresh_container = null;
	private UIButton acceptButton = null;
	private UIButton denyButton = null;
	private UIMenu groupInviteMenu = null;
	private UIMenu gatewaysMenu = null;
	private Hashtable gateways = new Hashtable();
	private Hashtable transports = new Hashtable();

	private Image img_msg;
	private Image img_cmd;
	private Image img_task;
	private static Hashtable chatScreenList = new Hashtable();

	private String searchString = rm.getString(ResourceIDs.STR_SEARCH);

	/*
	 * the key used when filtering contacts
	 */
	private int sel_last_key = -1;

	/*
	 * the pattern used when filtering contacts
	 */

	private String sel_pattern = "";

	/*
	 * the time stamp of the last key press
	 */
	private long sel_last_ts = 0;

	/*
	 * The offset of the selected key in the research pattern
	 */
	private int sel_key_offset = 0;

	// #ifdef TIMING
	 private long paint_time = 0;
	// #endif

	/** fount used for the conversation */
	public static Font f_u;

	// drawing constants
	// /** true when some conversation has pending messages */
	// private boolean unread_messages = ;

	/** singleton */
	private static RosterScreen _instance;

	/*
	 * the contextual menu associated to a user
	 */
	private UIMenu optionsMenu = null;

	private UIMenu actionsMenu = new UIMenu("");

	private UIMenu actionsLabel = new UIMenu(rm
			.getString(ResourceIDs.STR_OPTIONS));

	private UIAccordion optionsAccordion = null;

	private UILabel optionsLabel = null;

	private Vector optionsVector = null;

	private XMPPClient xmppClient = XMPPClient.getInstance();

	// The input text field used to explore a server for gateways
	private UITextField serverGatewayInput;

	private FTReceiver ftReceiver;

	private boolean cameraOn = false;

	private boolean micOn = false;

	private static String ungrouped = rm.getString(ResourceIDs.STR_UNGROUPED);

	private Hashtable commandResources;

	private String highLightString = rm.getString(ResourceIDs.STR_HIGHLIGHTS);

	public interface WaitScreen {

		public void stopWaiting();

	}

	static class MUCStateHandler extends IQResultListener {

		private IQResultListener listener;

		public MUCStateHandler(IQResultListener listener) {
			this.listener = listener;
		}

		public void handleError(Element e) {
		}

		public void handleResult(Element e) {
			Iq nextIq = new Iq(e.getAttribute(Iq.ATT_FROM), Iq.T_SET);
			Element nextQuery = nextIq.addElement(XMPPClient.NS_MUC_OWNER,
					Iq.QUERY);

			Element query = e.getChildByName(null, Iq.QUERY);
			if (query == null) return;
			Element x = query.getChildByName(null, "x");
			if (x == null) return;
			Element[] fields = x.getChildrenByName(null, "field");

			x = nextQuery.addElement(XMPPClient.JABBER_X_DATA, "x");
			x.setAttribute(Iq.ATT_TYPE, DataForm.TYPE_SUBMIT);

			Hashtable conf = new Hashtable(20);
			conf.put("FORM_TYPE", "http://jabber.org/protocol/muc#roomconfig");
			conf.put("muc#roomconfig_roomname", "temp");
			conf.put("muc#roomconfig_roomdesc", "temp");
			//conf.put("muc#roomconfig_maxusers", "None");
			conf.put("muc#roomconfig_roomsecret", " ");
			conf.put("muc#roomconfig_enablelogging", "0");
			conf.put("muc#roomconfig_publicroom", "0");
			conf.put("muc#roomconfig_persistentroom", "0");
			conf.put("muc#roomconfig_changesubject", "1");
			conf.put("muc#roomconfig_allowinvites", "1");
			conf.put("muc#roomconfig_moderatedroom", "0");
			conf.put("muc#roomconfig_whois", "anyone");
			conf.put("muc#roomconfig_membersonly", "1");
			//conf.put("muc#roomconfig_roomadmins", Contact.userhost(XMPPClient
			//		.getInstance().my_jid));

			for (int i = 0; i < fields.length; i++) {
				Element ithField = fields[i];
				Element ithNewField = new Element(ithField.uri, ithField.name);
				String ithVar = ithField.getAttribute("var");
				ithNewField.setAttribute("var", ithVar);
				String ithVal = (String) conf.get(ithVar);
				if (ithVal != null) {
					ithNewField.addElement(ithField.uri, "value").addText(
							ithVal);
				} else {
					// the default value
					Element val = ithField.getChildByName(null, "value");
					if (val != null) {
						ithNewField.addElement(val);
					} else
						ithNewField.addElement(ithField.uri, "value").addText(
								"0");
				}

				x.addElement(ithNewField);
			}
			//System.out.println(Utils.getStringUTF8(nextIq.toXml()));

			XMPPClient.getInstance().sendIQ(nextIq, listener);
		}
	}

	private static class FileReceiveScreen extends UIScreen {

		private UILabel cmd_exit = null;

		/*
		 * The listener related to this file trasnfer
		 */
		private OpenListener ftrp;

		private UIButton buttonNo;

		private UIButton buttonYes;

		private FileReceiveScreen(Contact c, OpenListener ftrp) {
			ResourceManager rm = RosterScreen.rm;

			this.setMenu(new UIMenu(""));
			cmd_exit = new UILabel(rm.getString(ResourceIDs.STR_CLOSE)
					.toUpperCase());
			this.getMenu().append(cmd_exit);
			this.setTitle(rm.getString(ResourceIDs.STR_FT));
			this.ftrp = ftrp;

			UILabel menuLabel = new UILabel(c.getPrintableName() + " "
					+ rm.getString(ResourceIDs.STR_ASK_FT) + ": "
					+ rm.getString(ResourceIDs.STR_FILE_NAME) + " - "
					+ ftrp.fileName + " \n"
					+ rm.getString(ResourceIDs.STR_DESC) + " - "
					+ ftrp.fileDesc + " \n"
					+ rm.getString(ResourceIDs.STR_SIZE) + " - "
					+ ftrp.fileSize + ". \n"
					+ rm.getString(ResourceIDs.STR_SUBSCRIPTION_ACCEPT));
			this.append(menuLabel);

			UIHLayout yesNo = new UIHLayout(2);
			yesNo.setGroup(false);
			buttonYes = new UIButton(rm.getString(ResourceIDs.STR_YES));
			buttonYes.setAnchorPoint(Graphics.HCENTER);
			buttonNo = new UIButton(rm.getString(ResourceIDs.STR_NO));
			buttonNo.setAnchorPoint(Graphics.HCENTER);
			yesNo.insert(buttonYes, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);
			yesNo.insert(buttonNo, 1, 50, UILayout.CONSTRAINT_PERCENTUAL);
			this.append(yesNo);

			menuLabel.setWrappable(true, UICanvas.getInstance().getWidth() - 5);
			menuLabel.setFocusable(false);
			menuLabel.setSelected(false);
			yesNo.setSelectedItem(buttonYes);

		}

		public void menuAction(UIMenu menu, UIItem cmd) {
			if (cmd == cmd_exit) UICanvas.getInstance().close(this);
		}

		public void itemAction(UIItem item) {
			if (item == buttonYes) {
				this.ftrp.answerFT(true);
				FTScreen.startFtreceive(ftrp);
				updateFT();
			} else if (item == buttonNo) {
				this.ftrp.answerFT(false);
				updateFT();
			}
		}

		void updateFT() {
			FTScreen frs = FTScreen.getInstance();
			UICanvas.getInstance().close(this);
			UICanvas.getInstance().open(frs, true);
		}
	}

	static class UIGroup extends UILabel {

		private String highLightString = rm
				.getString(ResourceIDs.STR_HIGHLIGHTS);

		private UIAccordion accordion = null;

		Hashtable contacts = new Hashtable();

		private static UILabel moveLabel = new UILabel(rm
				.getString(ResourceIDs.STR_MOVE));
		private static UILabel openLabel = new UILabel(rm
				.getString(ResourceIDs.STR_OPEN));
		private static UILabel groupMessage = new UILabel(rm
				.getString(ResourceIDs.STR_SEND_GRP_MSG));

		/*
		 * The name of the group
		 */
		public String name;

		private static Hashtable uiGroups = new Hashtable();

		private static Vector groupsPosition = new Vector();

		// private boolean moving = false;

		/*
		* Used to know that any of the painted groups is moving; -1 means noone
		*/
		private static UIGroup movingGroup = null;

		private int movingColor = UIUtils.colorize(UIConfig.bg_color, -50);

		private int normalFontColor = this.getFg_color();

		private int movingFontColor = UIUtils.colorize(UIConfig.bg_color, -25);

		private static UIMenu groupMenu = null;

		static {
			toggleMenus();
			loadGroups();
		}

		private static void loadGroups() {
			groupsPosition.removeAllElements();
			byte[] gps = Config.getInstance().getData(
					Config.GROUPS_POSITION.getBytes());
			if (gps != null && gps.length > 0) {
				Element gpe = BProcessor.parse(gps);
				Element[] groups = gpe.getChildrenByName(null, "group");
				for (int i = 0; i < groups.length; i++) {
					Element ithGroup = groups[i];
					String name = ithGroup.getChildByName(null, "name")
							.getText();
					if (groupsPosition.contains(name) == false) {
						groupsPosition.addElement(name);
					}
				}
			}
		}

		public static void toggleMenus() {
			groupMenu = UIUtils.easyMenu(rm.getString(ResourceIDs.STR_GROUP),
					10, 40, UICanvas.getInstance().getWidth() - 20, moveLabel);
			groupMenu.append(openLabel);
			groupMenu.append(UIGroup.groupMessage);

			groupMenu.setSelectedItem(moveLabel);
		}

		public static UIGroup getGroup(String group, UIAccordion accordion,
				boolean allocate) {
			// In the roster the contacts without group
			// are in the "ungrouped" group with label == Roster.unGroupedCode 
			if (group.equals(Roster.unGroupedCode)) group = RosterScreen.ungrouped;
			Hashtable tempGroups = uiGroups;
			UIGroup groupLabel = (UIGroup) tempGroups.get(group);
			if (groupLabel == null && allocate == true) {
				groupLabel = new UIGroup(group, accordion);
			}
			return groupLabel;
		}

		private UIGroup(String groupName, UIAccordion accordion) {
			super(groupName);
			this.name = groupName;

			this.accordion = accordion;
			initGroupData();
			Font xFont = UIConfig.font_body;
			Font lfont = Font.getFont(xFont.getFace(), Font.STYLE_BOLD, xFont
					.getSize());
			setFont(lfont);
			Vector newGroup = new Vector();
			uiGroups.put(groupName, this);

			if (groupsPosition.contains(groupName) == false) {
				if (groupName.equals(highLightString)) {
					groupsPosition.insertElementAt(groupName, 0);
				} else {
					groupsPosition.addElement(groupName);
				}
				saveGroups();
			}

			if (groupName.equals(highLightString)) {
				accordion.insertItem(this, 0, newGroup);
			} else {
				accordion.addItem(this, newGroup);
				orderGroups();
			}
		}

		public void initGroupData() {
			this.setSubmenu(groupMenu);
		}

		private static void saveGroups() {
			Config cfg = Config.getInstance();
			Element el = new Element("groups", "groups");
			Enumeration en = groupsPosition.elements();
			while (en.hasMoreElements()) {
				String ithName = (String) en.nextElement();
				Element group = el.addElement(null, "group");
				group.addElement(null, "name").addText(ithName);
				// #mdebug 
				Logger.log("Saving group: " + ithName);
				// #enddebug
			}
			cfg.setData(Config.GROUPS_POSITION.getBytes(), BProcessor
					.toBinary(el));
			cfg.saveToStorage();
		}

		private void orderGroups() {
			UIItem[] labels = accordion.getItemLabels();
			boolean changed = false;
			for (int i = 0; i < labels.length - 1; i++) {
				int firstPosition = groupsPosition
						.indexOf(((UIGroup) labels[i]).name);
				int secondPosition = groupsPosition
						.indexOf(((UIGroup) labels[i + 1]).name);
				if (firstPosition > secondPosition) {
					accordion.swap(i, i + 1);
					changed = true;
				}
			}
			if (changed) {
				orderGroups();
			}
		}

		private void removeContact(Contact c) {
			UIItem uic = (UIItem) this.contacts.remove(c);
			if (uic != null) accordion.removePanelItem(this, uic);
			if (accordion.getPanelSize(this) == 0) {
				accordion.removeItem(this);
				uiGroups.remove(this.name);
			}
		}

		boolean reorganizeContact(Contact c, int reason) {
			boolean needRepaint = false;
			UIContact uic = (UIContact) this.contacts.get(c);
			RosterScreen rs = RosterScreen.getInstance();
			//used to know if needrePaint
			int oldAccordionSize = accordion.getPanelSize(this);
			boolean needReinsert = (uic == null ? true : checkRemoval(uic));

			if (uic != null && needReinsert) {
				needRepaint = true;
				accordion.removePanelItem(this, uic);
			}
			if (uic != null || c.isVisible() || rs.show_offlines) {
				// reinsert if it is visible
				int i = 0;
				if (c.isVisible() || rs.show_offlines
						|| chatScreenList.contains(c.jid)) {
					if (needReinsert) {
						needRepaint = true;
						int min = 0;
						int max = accordion.getPanelSize(this);
						int med = 0;
						while (min != max) {
							med = (min + max) / 2;
							UIContact ithContact = (UIContact) accordion
									.getPanelItem(this, med);
							if (c.compareTo(ithContact.c) < 0) min = med + 1;
							else
								max = med;
						}
						i = min;
						if (uic == null) {
							uic = rs.new UIContact(c);
							uic.setSubmenu(rs.actionsMenu);
							this.contacts.put(c, uic);
						}
						accordion.insertPanelItem(this, uic, i);
					}
					needRepaint |= uic.updateContactData();
				}

				if ((reason == Contact.CH_MESSAGE_NEW
						|| reason == Contact.CH_TASK_NEW || c.unread_msg() || c.pending_tasks)
						&& UIGroup.movingGroup == null) {
					// set the correct selection to the just updated task
					needRepaint = true;
					accordion.openLabel(this);
					accordion.setSelectedItem(uic);
				}
			}

			if (c.isVisible() == false && rs.show_offlines == false) {
				// if the contact is not visible remove it
				if (uic != null) accordion.removePanelItem(this, uic);
				this.contacts.remove(c);
			}
			if (reason == Contact.CH_CONTACT_REMOVED) {
				// in this case the repaint must be forced because the img has changed
				needRepaint = true;
			}

			if (rs.filtering == false
					&& RosterScreen.getInstance().getAccordion() == RosterScreen
							.getInstance().searchAccordion) needRepaint |= rs
					.filterContacts(true);
			int newAccordionSize = accordion.getPanelSize(this);
			if (newAccordionSize == 0) {
				accordion.removeItem(this);
				uiGroups.remove(this.getText());
				if (oldAccordionSize != 0) needRepaint = true;
			}
			return needRepaint;
		}

		private boolean checkRemoval(UIContact uic) {
			Vector v = accordion.getSubpanel(this);
			if (v == null) return false;
			int index = v.indexOf(uic);
			if (index > 0) {
				Contact previousContact = ((UIContact) v.elementAt(index - 1)).c;
				if (uic.c.compareTo(previousContact) > 0) return true;
			}
			if (index < v.size() - 1) {
				Contact nextContact = ((UIContact) v.elementAt(index + 1)).c;
				if (uic.c.compareTo(nextContact) < 0) return true;
			}
			return false;
		}

		public UIContact getUIContact(Contact c) {
			// TODO Auto-generated method stub
			return (UIContact) this.contacts.get(c);
		}

		public void startMoving() {
			//moving = true;
			UIGroup.movingGroup = this;
			setGradientColor(movingColor);
			setGradientSelectedColor(movingColor);
			setSelectedColor(movingColor);
			setFg_color(movingFontColor);
			accordion.closeLabel(this);
		}

		public void stopMoving() {
			//moving = false;
			UIGroup.movingGroup = null;
			int gBgColor = UIUtils.colorize(UIConfig.bg_color, -10);
			setGradientColor(UIUtils.colorize(gBgColor, -3));
			setGradientSelectedColor(UIUtils.colorize(UIConfig.header_bg, -8));
			setSelectedColor(UIConfig.header_bg);
			setFg_color(normalFontColor);
			saveGroups();
		}

		public boolean keyPressed(int key) {
			if (/*moving == false && */UIGroup.movingGroup == null) return super
					.keyPressed(key);
			if (/*moving == false &&*/UIGroup.movingGroup != null
					&& UIGroup.movingGroup != this) {
				UIItem[] labels = accordion.getItemLabels();
				int myIndex = 0;
				int movingIndex = 0;
				UICanvas.lock();
				boolean oldFreezed = this.getScreen().isFreezed();
				this.getScreen().setFreezed(true);
				for (int i = 0; i < labels.length; i++) {
					if (labels[i] == this) myIndex = i;
					else if (labels[i] == UIGroup.movingGroup) movingIndex = i;
				}
				moveGroups(this.accordion, movingIndex, myIndex);
				UIGroup.movingGroup.stopMoving();
				// little hack to "clean" selections
				((UILayout) this.getContainer()).setSelectedIndex(-1);
				this.getScreen().setFreezed(oldFreezed);
				UICanvas.unlock();
				this.askRepaint();
				return true;
			}
			int ga = UICanvas.getInstance().getGameAction(key);
			int index = 0;
			UIItem[] labels = null;
			switch (ga) {
				case Canvas.UP:
					labels = accordion.getItemLabels();
					for (int i = 0; i < labels.length; i++) {
						if (labels[i] == this) {
							index = i;
							break;
						}
					}
					if (index > 0) swapGroups(this.accordion, index, index - 1);
					break;

				case Canvas.DOWN:
					labels = accordion.getItemLabels();
					for (int i = 0; i < labels.length; i++) {
						if (labels[i] == this) {
							index = i;
							break;
						}
					}
					if (index < accordion.getItems().size() - 1) swapGroups(
							this.accordion, index, index + 1);
					break;

				case Canvas.FIRE:
					this.stopMoving();
					saveGroups();
					break;

				default:
					break;
			}

			return true;
		}

		private static void moveGroups(UIAccordion accordion, int firstIndex,
				int secondIndex) {
			UIItem[] labels = accordion.getItemLabels();
			UIGroup firstLabel = (UIGroup) labels[firstIndex];
			UIGroup secondLabel = (UIGroup) labels[secondIndex];

			int firstPosition = groupsPosition.indexOf(firstLabel.name);
			int secondPosition = groupsPosition.indexOf(secondLabel.name);
			groupsPosition.setElementAt(secondLabel.name, firstPosition);
			groupsPosition.setElementAt(firstLabel.name, secondPosition);

			accordion.move(firstIndex, secondIndex);
		}

		private static void swapGroups(UIAccordion accordion, int firstIndex,
				int secondIndex) {
			UIItem[] labels = accordion.getItemLabels();
			UIGroup firstLabel = (UIGroup) labels[firstIndex];
			UIGroup secondLabel = (UIGroup) labels[secondIndex];

			int firstPosition = groupsPosition.indexOf(firstLabel.name);
			int secondPosition = groupsPosition.indexOf(secondLabel.name);
			groupsPosition.setElementAt(secondLabel.name, firstPosition);
			groupsPosition.setElementAt(firstLabel.name, secondPosition);

			accordion.swap(firstIndex, secondIndex);
		}
	}

	// #ifdef SCREENSAVER
	 private long last_key_time;
	 private static long SCREENSAVER_DELAY = 10000;
	 private TimerTask screensaver_starter = null;//FEHLER kein TimerTask import
	// #endif

	class UIContact extends UIHLayout {

		protected Contact c;
		private UISeparator sep = new UISeparator(1);
		private UILabel statusLabel = new UILabel("");
		private UILabel contactLabel = new UILabel("");
		private UILabel statusText = new UILabel("");

		public UIContact(Contact c) {
			super(2);
			// the correct width for this img is set below !!!
			super.insert(statusLabel, 0, 0, UILayout.CONSTRAINT_PIXELS);
			super.insert(contactLabel, 1, 100, UILayout.CONSTRAINT_PERCENTUAL);
			this.c = c;
			sep.setFg_color(0x00CCCCCC);
			this.setFocusable(true);
			contactLabel.setFocusable(true);
			statusText.setFont(Font.getFont(Font.FACE_PROPORTIONAL,
					Font.STYLE_PLAIN, Font.SIZE_SMALL));
			statusText.setFg_color(0xAAAAAA);
			this.setGroup(false);
			this.screen = RosterScreen.this;
			this.updateContactData();
		}

		public boolean updateContactData() {
			boolean needRepaint = false;
			String uname = c.getPrintableName();
			Image pimg = null;

			if (c instanceof MUC == false) pimg = xmppClient.getPresenceIcon(c,
					null, c.getAvailability());
			else {
				try {
					pimg = Image.createImage("/icons/muc.png");
				} catch (IOException e) {
					pimg = xmppClient.getPresenceIcon(c, null, c
							.getAvailability());
				}
			}
			if (pimg == null) pimg = xmppClient.getPresenceIcon(null, null,
					Contact.AV_UNAVAILABLE);
			// setup the status text label
			if (contactLabel.getText().equals(uname) == false) needRepaint = true;
			this.contactLabel.setText(uname);
			String fixedStatus = "";
			if (this.c instanceof MUC == false) {
				String status = null;
				Presence[] resources = c.getAllPresences();
				if (resources != null && resources.length > 0) {
					status = resources[0].getStatus();
				}
				fixedStatus = status != null ? status : "";
			} else {
				fixedStatus = ((MUC) c).topic;
			}
			if (statusText.getText().equals(fixedStatus) == false) needRepaint = true;
			this.statusText.setText(fixedStatus);

			if (this.statusLabel.getImg() != pimg) needRepaint = true;
			this.statusLabel.setImg(pimg);
			statusLabel.setLayoutWidth(pimg.getWidth());

			Image cimg = null;
			if (c.cmdlist != null) {
				cimg = img_cmd;
			}

			if (c.pending_tasks) {
				cimg = img_task;
			} else if (c.unread_msg()) {
				cimg = img_msg;
			} else if (c.cmdlist != null) {
				cimg = img_cmd;
			}
			if (this.contactLabel.getImg() != cimg) needRepaint = true;
			contactLabel.setImg(cimg);

			this.setDirty(needRepaint);
			return needRepaint;
		}

		public int getHeight(Graphics g) {
			int superHeight = super.getHeight(g);
			this.height = superHeight + sep.getHeight(g);
			// a minimum width in case it is 0 (and hence not painted yet)
			int minWidth = RosterScreen.getInstance().getWidth() - 25;
			if (this.isSelected() && minWidth > 25) this.statusText
					.setWrappable(true, minWidth);
			else
				this.statusText.setWrappable(false, -1);
			if (this.statusText.getText().length() > 0) this.height += statusText
					.getHeight(g);
			return this.height;
		}

		protected void paint(Graphics g, int w, int h) {
			g.setColor(getBg_color() >= 0 ? getBg_color() : UIConfig.bg_color);
			int statusLabelWidth = statusLabel.getImg().getWidth();
			g.fillRect(0, 0, statusLabelWidth, h);
			super.paint(g, w, super.getHeight(g));
			if (this.statusText.getText().length() > 0) {
				g.translate(statusLabelWidth, super.getHeight(g));
				int statusTextHeight = statusText.getHeight(g);
				statusText.paint0(g, w - statusLabelWidth, statusTextHeight);
				g.translate(-statusLabelWidth, statusTextHeight);
			} else {
				g.translate(0, super.getHeight(g));
			}
			sep.paint0(g, w, sep.getHeight(g));
			//                      // Remove these elements because the pointerPressed must 
			//                      // find the UIContact 
			//                      this.getScreen().removePaintedItem(statusLabel);
			//                      this.getScreen().removePaintedItem(contactLabel);
			//                      this.getScreen().removePaintedItem(sep);
			//                      this.getScreen().removePaintedItem(statusText);
		}

		public UIItem getSelectedItem() {
			// i want to return myself and not the selected label!
			return this;
		}
	}

	// #ifdef SCREENSAVER
	 class ScreenSaverStarter extends TimerTask {
	
	 public void run() {//FEHLER isShown ist nicht implementiert
//	 if(isShown() && (System.currentTimeMillis()-
//	  last_key_time)>SCREENSAVER_DELAY) {
//	 LampiroMidlet.disp.setCurrent(new ScreenSaver(RosterScreen.this));//FEHLER: fehlender import von ScreenSaver
//	 }
	 }
	 }
	// #endif

	/*
	 * Some update operations dealing with changes performed in options screen
	 */
	public void updateScreen() {
		// update UIAccordion colors
		int gBgColor = UIUtils.colorize(UIConfig.bg_color, -10);
		rosterAccordion.setLabelColor(gBgColor);
		rosterAccordion.setLabelGradientColor(UIUtils.colorize(gBgColor, -3));
		rosterAccordion.setLabelGradientSelectedColor(UIUtils.colorize(
				UIConfig.header_bg, -8));
		rosterAccordion.setLabelSelectedColor(UIConfig.header_bg);
		searchAccordion.setLabelColor(gBgColor);
		searchAccordion.setLabelGradientColor(UIUtils.colorize(gBgColor, -3));
		searchAccordion.setLabelGradientSelectedColor(UIUtils.colorize(
				UIConfig.header_bg, -8));
		searchAccordion.setLabelSelectedColor(UIConfig.header_bg);
		UIGroup.toggleMenus();
		Enumeration en = UIGroup.uiGroups.elements();
		while (en.hasMoreElements()) {
			UIGroup ithGroup = (UIGroup) en.nextElement();
			ithGroup.initGroupData();
		}
		this.toggleMenus();

		// change the header color
		header.setBg_color(UIUtils.colorize(UIConfig.header_bg, +20));
		connData.setFg_color(UIConfig.menu_title);
		presenceLabel.setFg_color(UIConfig.menu_title);

		// updatetitlebar in chatscreen
		en = chatScreenList.elements();
		while (en.hasMoreElements()) {
			ChatScreen cs = (ChatScreen) en.nextElement();
			cs.headerImg.setBg_color(UIConfig.header_bg);
			cs.headerImg.setFg_color(UIConfig.menu_title);
			cs.headerStatus.setBg_color(UIConfig.header_bg);
			cs.headerStatus.setFg_color(UIConfig.menu_title);
		}

		// update footers and headers
		UILabel[] lbls = new UILabel[] { footerLeft, footerRight, titleLabel };
		for (int i = 0; i < lbls.length; i++) {
			UILabel label = lbls[i];
			label.setBg_color(UIConfig.header_bg);
			label.setFg_color(UIConfig.menu_title);
			label.setFont(UIConfig.font_title);
		}
	}

	private RosterScreen() {
		refresh_gateways.setAnchorPoint(Graphics.HCENTER);

		Config cfg = Config.getInstance();
		volume = Integer.parseInt(cfg.getProperty(Config.TONE_VOLUME, "50"));
		play_flags = Utils.str2flags(cfg.getProperty(
				Config.VIBRATION_AND_TONE_SETTINGS, "1"), 0, 4);

		setMenu(new UIMenu(""));
		f_u = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN,
				Font.SIZE_SMALL);

		img_msg = UICanvas.getUIImage("/icons/message.png");
		img_cmd = UICanvas.getUIImage("/icons/gear.png");
		img_task = UICanvas.getUIImage("/icons/task.png");

		/*
		 * XXX: hack, create an item and select it, the item won't relinquish
		 * focus
		 */

		this.setFreezed(true);
		header = new UIHLayout(2);
		header.setGroup(false);
		header.setFocusable(false);
		connData = new UILabel("");
		presenceLabel = new UILabel(null, "");
		presenceLabel.setAnchorPoint(Graphics.RIGHT);
		header.insert(connData, 0, 100, UILayout.CONSTRAINT_PERCENTUAL);
		header.insert(presenceLabel, 1, 20, UILayout.CONSTRAINT_PERCENTUAL);
		connData.setFg_color(UIConfig.menu_title);
		presenceLabel.setFg_color(UIConfig.menu_title);
		header.setBg_color(UIUtils.colorize(UIConfig.header_bg, +20));

		this.append(header);
		UISeparator sep = new UISeparator(2);
		sep.setFg_color(0xCCCCCC);
		this.append(sep);
		this.setSelectedIndex(2);
		rosterAccordion = new UIAccordion();
		rosterAccordion.setMaxHeight(-1);
		//rosterAccordion.closeImage = null;
		//rosterAccordion.openImage = null;
		rosterAccordion.setSepSize(1);
		rosterAccordion.setSepColor(0x00CCCCCC);

		int gBgColor = UIUtils.colorize(UIConfig.bg_color, -10);
		rosterAccordion.setLabelColor(gBgColor);
		rosterAccordion.setLabelGradientColor(UIUtils.colorize(gBgColor, -3));
		rosterAccordion.setLabelGradientSelectedColor(UIUtils.colorize(
				UIConfig.header_bg, -8));
		rosterAccordion.setLabelSelectedColor(UIConfig.header_bg);

		rosterAccordion.setModal(true);
		this.append(rosterAccordion);
		viewedAccordionIndex = this.indexOf(rosterAccordion);

		searchAccordion = new UIAccordion();
		searchAccordion.setMaxHeight(-1);
		//rosterAccordion.closeImage = null;
		//rosterAccordion.openImage = null;
		searchAccordion.setSepSize(1);
		searchAccordion.setSepColor(0x00CCCCCC);
		searchAccordion.setLabelColor(gBgColor);
		searchAccordion.setLabelGradientColor(UIUtils.colorize(gBgColor, -3));
		searchAccordion.setLabelGradientSelectedColor(UIUtils.colorize(
				UIConfig.header_bg, -8));
		searchAccordion.setLabelSelectedColor(UIConfig.header_bg);
		searchAccordion.setModal(true);
		searchGroup = UIGroup.getGroup(searchString, searchAccordion, true);

		this.setFreezed(false);
		this.setDirty(true);
		this.askRepaint();

		actionsMenu.append(actionsLabel);

		this.rosterAccordion.setSelectedIndex(0);
		// section to detect if camera is available
		try {
			String supports = System.getProperty("video.snapshot.encodings");
			if (supports != null && supports.length() > 0) {
				this.cameraOn = true;
			} else {
				this.cameraOn = false;
			}
		} catch (Exception ioe) {
			this.cameraOn = false;
		}

		try {
			String supports = System.getProperty("audio.encodings");
			if (supports != null && supports.length() > 0) {
				this.micOn = true;
			} else {
				this.micOn = false;
			}
		} catch (Exception ioe) {
			this.micOn = false;
		}

		// setup the menu
		this.toggleMenus();

		cmd_details.setFocusable(true);
		cmd_groups.setFocusable(true);
		cmd_delc.setFocusable(true);

		cmd_details.setBg_color(UIConfig.menu_color);
		cmd_groups.setBg_color(UIConfig.menu_color);
		cmd_delc.setBg_color(UIConfig.menu_color);
	}

	private void updateHeader() {
		int bytes[] = XMPPClient.getTraffic();
		String byteTrans = rm.getString(ResourceIDs.STR_TRAFFIC) + ": "
				+ (bytes[0] + bytes[1]);
		if (byteTrans.compareTo(this.connData.getText()) != 0) {
			this.connData.setText(byteTrans);
		}
		if (sel_pattern.length() > 0) {
			this.connData.setText("sel: " + sel_pattern);
		}
		Contact myContact = xmppClient.getMyContact();
		if (myContact != null) {
			Image pimg = xmppClient.getPresenceIcon(myContact, null, myContact
					.getAvailability());
			// contacts with unread messages are always at the top
			UIGroup highLightGroup = UIGroup.getGroup(highLightString,
					rosterAccordion, false);
			if (highLightGroup != null) {
				Enumeration ithGroup = rosterAccordion
						.getSubPanelElements(highLightGroup);
				if (ithGroup != null && ithGroup.hasMoreElements()) {
					UIContact firstContact = (UIContact) ithGroup.nextElement();
					if (firstContact.c.unread_msg()) {
						pimg = img_msg;
					}
				}
			}
			this.presenceLabel.setImg(pimg);
			int totalSize = 0;
			Enumeration en2 = UIGroup.uiGroups.elements();
			while (en2.hasMoreElements()) {
				UIItem ithLabel = (UIItem) en2.nextElement();
				if (ithLabel instanceof UIGroup && ithLabel != highLightGroup) {
					int size = this.getAccordion().getPanelSize(ithLabel);
					totalSize += size;
				}
			}
			String newTitle = rm.getString(ResourceIDs.STR_ROSTER_TITLE) + "("
					+ totalSize + ")";
			if (newTitle.compareTo(this.getTitle()) != 0) this
					.setTitle(newTitle);
		}
	}

	private UIHLayout header = null;
	private UILabel connData = null;
	private UILabel presenceLabel = null;
	protected UIAccordion rosterAccordion = null;
	protected UIAccordion searchAccordion = null;
	private UIGroup searchGroup = null;
	private int viewedAccordionIndex = -1;

	/**
	 * The contact that should be deleted at the user request
	 */
	private Contact delContact;

	/**
	 * The question asked whene deleting a contact
	 */
	private UILabel deleteQuestion;

	public static RosterScreen getInstance() {
		if (_instance == null) {
			_instance = new RosterScreen();
		}
		return _instance;
	}

	protected void sizeChanged(int w, int h) {
		this.width = w;
		this.height = h;
		// askRepaint();
	}

	// #ifdef SCREENSAVER
	 protected void showNotifySC() {
	 last_key_time = System.currentTimeMillis();
	 if(screensaver_starter == null) {
	 screensaver_starter = new ScreenSaverStarter();
	 Utils.tasks.scheduleAtFixedRate(screensaver_starter, SCREENSAVER_DELAY,
	  SCREENSAVER_DELAY);
	 }
	 }
	
	 protected void hideNotifySC() {
	 if(screensaver_starter != null) {
	 screensaver_starter.cancel();
	 screensaver_starter = null;
	 }
	 }
	// #endif //FEHLER (doppelte methode bei hideNotify und showNotify, SC manuell hinzugefuegt

	public void hideNotify() {
		this.setFreezed(true);
		this.sel_pattern = "";
		this.replace(viewedAccordionIndex, rosterAccordion);
		try {
			UICanvas.lock();
			filterContacts(false);
		} finally {
			UICanvas.unlock();
		}
		this.setFreezed(false);
	}

	private void toggleMenus() {
		// add or remove commands only if there is a selected user

		UIMenu menu = getMenu();

		boolean needReopen = menu.isOpenedState();
		UIItem oldSelitem = null;
		if (needReopen) {
			oldSelitem = menu.getSelectedItem();
			menu.setOpenedState(false);
			this.askRepaint();
		}
		menu.clear();
		// #mdebug
		menu.append(cmd_debug);
		// #enddebug
		UIItem sepLayout = this.getSeparator();
		menu.append(cmd_addc);
		if (this.mucJid != null) menu.append(cmd_mucs);
		menu.append(cmd_refresh_roster);
		menu.append(sepLayout);

		menu.append(cmd_album);
		menu.append(cmd_fts);
		if (this.cameraOn) menu.append(cmd_capture_img);
		if (this.micOn) menu.append(cmd_capture_aud);
		menu.append(sepLayout);

		menu.append(cmd_state);
		menu.append(gateways_discovery);
		menu.append(toggle_offline);
		menu.append(cmd_options);
		menu.append(sepLayout);

		menu.append(cmd_about);
		menu.append(cmd_help);
		menu.append(sepLayout);

		menu.append(cmd_logout);
		menu.append(cmd_exit);

		sepLayout.setFocusable(false);

		cmd_details.setBg_color(UIConfig.menu_color);
		cmd_groups.setBg_color(UIConfig.menu_color);
		cmd_delc.setBg_color(UIConfig.menu_color);

		if (needReopen) {
			menu.setSelectedItem(oldSelitem);
			this.keyPressed(UICanvas.MENU_RIGHT);
		}
	}

	public UIItem getSeparator() {
		UIHLayout sepLayout = new UIHLayout(3);
		sepLayout.setGroup(false);
		UIItem dummySep = new UISeparator(0);
		UISeparator sep = new UISeparator(1);
		sep.setFg_color(0x999999);
		sepLayout.insert(dummySep, 0, 5, UILayout.CONSTRAINT_PIXELS);
		if (UIConfig.menu_3d == true) {
			UIVLayout vlayout = new UIVLayout(2, 2);
			UISeparator sep2 = new UISeparator(1);
			sep2.setFg_color(0xFFFFFF);
			vlayout.insert(sep, 0, 1, UILayout.CONSTRAINT_PIXELS);
			vlayout.insert(sep2, 1, 1, UILayout.CONSTRAINT_PIXELS);
			vlayout.setGroup(false);
			sepLayout.insert(vlayout, 1, 100, UILayout.CONSTRAINT_PERCENTUAL);
		} else {
			sepLayout.insert(sep, 1, 100, UILayout.CONSTRAINT_PERCENTUAL);
		}
		sepLayout.insert(dummySep, 2, 5, UILayout.CONSTRAINT_PIXELS);
		return sepLayout;
	}

	/*
	*   Raised when a drag is made
	*/
	public void startDrag(UIItem draggedItem) {
		if (draggedItem instanceof UIGroup && UIGroup.movingGroup == null) {
			((UIGroup) draggedItem).startMoving();
		}
	}

	/*
	*   Raised when a drag is made
	*/
	public void endDrag() {
		if (UIGroup.movingGroup != null) {
			UIGroup.movingGroup.stopMoving();
			this.askRepaint();
		}
	}

	/**
	 * Handle key events
	 * 
	 * @param kc
	 *            the pressed key
	 */
	public boolean keyPressed(int kc) {
		// #mdebug
		Logger.log("Roster screen keypressed :" + kc);
		// #enddebug
		if (this.popupList.size() == 0
				& this.getMenu().isOpenedState() == false) {
			if (UICanvas.getInstance().hasQwerty()) {
				if ((kc >= 'A' && kc <= 'Z') || (kc >= 'a' && kc <= 'z')
						|| (kc >= '0' && kc <= '9')) {
					this.setFreezed(true);
					sel_pattern = sel_pattern + (char) kc;
					this.replace(viewedAccordionIndex, searchAccordion);
					try {
						UICanvas.lock();
						filterContacts(true);
					} finally {
						UICanvas.unlock();
					}
					this.setFreezed(false);
					if (this.rosterAccordion.getItems().size() > 0) {
						this.rosterAccordion.setSelectedIndex(0);
					}
					this.askRepaint();
					return true;
				}
			}

			switch (kc) {
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
					int key_num = kc - Canvas.KEY_NUM0;
					this.setFreezed(true);
					long t = System.currentTimeMillis();
					if ((key_num != sel_last_key) || t - sel_last_ts > 1000) {
						// new key
						sel_key_offset = 0;
						sel_last_key = key_num;
						sel_pattern = sel_pattern
								+ UIUtils.itu_keys[key_num][sel_key_offset];
						this.replace(viewedAccordionIndex, searchAccordion);
						try {
							UICanvas.lock();
							filterContacts(true);
						} finally {
							UICanvas.unlock();
						}
					} else {
						// shifted key
						sel_key_offset += 1;
						if (sel_key_offset >= UIUtils.itu_keys[key_num].length) sel_key_offset = 0;
						sel_pattern = sel_pattern.substring(0, sel_pattern
								.length() - 1)
								+ UIUtils.itu_keys[key_num][sel_key_offset];
						try {
							UICanvas.lock();
							filterContacts(false);
						} finally {
							UICanvas.unlock();
						}
						this.rosterAccordion.setDirty(true);
					}
					sel_last_ts = t;
					this.setFreezed(false);
					if (this.rosterAccordion.getItems().size() > 0) {
						this.rosterAccordion.setSelectedIndex(0);
					}
					this.askRepaint();
					return true;

			}

			int ga = UICanvas.getInstance().getGameAction(kc);
			switch (ga) {
				case Canvas.RIGHT: {
					/*
					 * Contact c = getSelectedContact(); if (c != null) {
					 * chatWithSelected(true); }
					 */
					showNextScreen(this);
					return true;
				}
				case Canvas.LEFT: {
					if (sel_pattern.length() > 0) {
						cutPattern();
						return true;
					}
					// // go to the top
					// if (this.rosterPanel.getItems().size() > 0) {
					// this.rosterPanel.setSelectedIndex(0);
					// }

					showPreviousScreen(this);
					return true;

				}

				default: {
					break;
				}
			}

			// any of the delete characters
			if (kc == UICanvas.MENU_CANCEL || kc == 8) {
				if (sel_pattern.length() > 0) {
					cutPattern();
					return true;
				}
			}
		}
		return super.keyPressed(kc);
	}

	private void cutPattern() {
		this.setFreezed(true);
		sel_pattern = sel_pattern.substring(0, sel_pattern.length() - 1);
		if (sel_pattern.length() > 0) this.replace(viewedAccordionIndex,
				searchAccordion);
		else if (sel_pattern.length() == 0) this.replace(viewedAccordionIndex,
				rosterAccordion);
		try {
			UICanvas.lock();
			filterContacts(false);
		} finally {
			UICanvas.unlock();
		}
		this.setFreezed(false);
		askRepaint();
	}

	public void itemAction(UIItem item) {
		if (item instanceof UIContact) {
			UIContact uic = (UIContact) item;
			Contact c = uic.c;
			if (this.getSelectedContact() != c) {
				this.rosterAccordion.setSelectedIndex(this.rosterAccordion
						.getItems().indexOf(uic));
			}
			if (c != null) {
				if (c.unread_msg()) {
					// at this manner the loop is made to all the resources
					// even the offline one
					Vector allConvs = c.getAllConvs();
					Enumeration en = allConvs.elements();
					while (en.hasMoreElements()) {
						Object[] coupleConv = (Object[]) en.nextElement();
						String ithRes = (String) coupleConv[0];
						Vector messages = (Vector) coupleConv[1];
						if (messages.size() > 0) {
							chatWithSelected(ithRes);
							return;
						}
					}
				}
				Presence presence = c.getPresence();
				String toJid = (presence != null ? presence
						.getAttribute(Message.ATT_FROM) : c.jid);
				chatWithSelected(toJid);
			}
		}
	}

	/**
	 * Handle a command
	 * 
	 * @param c
	 *            the selected command
	 * @param d
	 *            the object on which the command has been invoked
	 * 
	 */
	public void menuAction(UIMenu menu, UIItem c) {
		if (c == UIGroup.openLabel) {
			UIGroup selGroup = getSelectedUIGroup();
			if (selGroup != null) {
				this.getAccordion().openLabel(selGroup);
			}
		} else if (c == UIGroup.moveLabel) {
			UIGroup selGroup = getSelectedUIGroup();
			if (selGroup != null) {
				selGroup.startMoving();
			}
		} else if (c == actionsLabel) {
			this.openContactMenu();
		} else if (c == cmd_logout) {
			xmppClient.setPresence(Contact.AV_UNAVAILABLE, null);
		} else if (c == cmd_exit) {
			LampiroMidlet.exit();
		} else if (c == this.deleteQuestion) {
			xmppClient.getRoster().unsubscribeContact(this.delContact);
		} else if (c == cmd_delc) {
			this.removePopup(optionsMenu);
			Contact cont = getSelectedContact();
			deleteQuestion = new UILabel(rm
					.getString(ResourceIDs.STR_DELETE_CONTACT)
					+ ": " + cont.jid + "?");
			UIMenu deleteMenu = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_DELETE_CONTACT), 10, -1,
					UICanvas.getInstance().getWidth() - 20, deleteQuestion);
			deleteQuestion.setFocusable(true);
			deleteMenu.setSelectedIndex(1);
			deleteQuestion.setWrappable(true, deleteMenu.getWidth() - 5);
			deleteMenu.cancelMenuString = rm.getString(ResourceIDs.STR_NO);
			deleteMenu.selectMenuString = rm.getString(ResourceIDs.STR_YES);
			Graphics cg = this.getGraphics();
			int offset = (cg.getClipHeight() - deleteMenu.getHeight(cg)) / 2;
			deleteMenu.setAbsoluteY(offset);
			this.delContact = cont;
			this.addPopup(deleteMenu);
		} else if (c == cmd_help) {
			boolean oldFreezed = this.isFreezed();
			this.setFreezed(true);
			String help = rm.getString(ResourceIDs.STR_HELP_TEXT);
			help = help.replace('<', '\n');
			UITextField helpField = new UITextField("", help, help.length(),
					TextField.UNEDITABLE);
			helpField.setWrappable(true);
			helpField.setExpandable(false);
			UIMenu helpMenu = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_HELP), 1, 20, UICanvas
					.getInstance().getWidth() - 2, helpField);
			helpMenu.setSelectedIndex(1);
			helpMenu.cancelMenuString = "";
			helpMenu.selectMenuString = rm.getString(ResourceIDs.STR_CLOSE)
					.toUpperCase();
			this.addPopup(helpMenu);
			this.setFreezed(oldFreezed);
			this.askRepaint();
			helpField.expand();
		} else if (c == cmd_album) {
			AlbumScreen alb = AlbumScreen.getInstance();
			UICanvas.getInstance().open(alb, true);
		} else if (c == cmd_fts) {
			FTScreen fts = FTScreen.getInstance();
			UICanvas.getInstance().open(fts, true);
		} else if (c == cmd_addc) {
			AddContactScreen acs = new AddContactScreen();
			UICanvas.getInstance().open(acs, true);
		} else if (c == cmd_send) {
			Contact user = getSelectedContact();
			String fullJid = this.getActionJid();
			MessageComposerScreen ms = new MessageComposerScreen(user, fullJid,
					MessageComposerScreen.MESSAGE);
			UICanvas.getInstance().open(ms, true);
			this.removePopup(optionsMenu);
		} else if (c == UIGroup.groupMessage) {
			UIGroup group = getSelectedUIGroup();
			if (group == null) return;
			GrpMessageComposerScreen ms = new GrpMessageComposerScreen(group,
					MessageComposerScreen.MESSAGE);
			UICanvas.getInstance().open(ms, true);
			this.removePopup(optionsMenu);
		} else if (c == refresh_gateways) {
			this.gateways.clear();
			this.gatewaysServer = this.serverGatewayInput.getText();
			this.getIMGateways(gatewaysServer);
			// serverGateways could be null if not authenticated yet
			String localServer = Contact
					.domain(XMPPClient.getInstance().my_jid);
			if (gatewaysServer.equals(localServer) == false) {
				this.getIMGateways(localServer);
			}
			this.setFreezed(true);
			gatewaysMenu.remove(refresh_container);
			UIGauge progressGauge = new UIGauge(rm
					.getString(ResourceIDs.STR_WAIT), false, Gauge.INDEFINITE,
					Gauge.CONTINUOUS_RUNNING);
			gatewaysMenu.append(progressGauge);
			this.setFreezed(false);
			int count = 15;
			// At most 15 seconds
			while (count-- > 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			progressGauge.cancel();
			refresh_gateways.setSelected(false);
			gatewaysMenu.remove(progressGauge);
			this.removePopup(gatewaysMenu);
			this.menuAction(this.getMenu(), gateways_discovery);
			this.askRepaint();
		} else if (c == gateways_discovery) {
			this.setFreezed(true);
			gatewaysMenu = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_GATEWAYS), 10, 20, this
					.getWidth() - 20, null);
			// why ?
			//((UIItem) gatewaysMenu.getItemList().elementAt(0))
			//              .setFocusable(true);
			serverGatewayInput = new UITextField(rm
					.getString(ResourceIDs.STR_SERVER_EXPLORE), Contact
					.domain(XMPPClient.getInstance().my_jid), 255,
					TextField.ANY);
			gatewaysMenu.selectMenuString = rm.getString(
					ResourceIDs.STR_REGISTER).toUpperCase();
			this.addPopup(gatewaysMenu);
			gatewaysMenu.append(serverGatewayInput);
			Enumeration en = this.gateways.keys();
			while (en.hasMoreElements()) {
				String from = (String) en.nextElement();
				Object[] nameImg = (Object[]) this.gateways.get(from);
				String name = (String) nameImg[0];
				Image img = (Image) nameImg[1];
				UILabel ithTransport = new UILabel(img, name);
				ithTransport.setFocusable(true);
				this.transports.put(ithTransport, from);
				UIMenu gatewaysMenu = RosterScreen.this.gatewaysMenu;
				gatewaysMenu.append(ithTransport);
				gatewaysMenu.setDirty(true);
			}
			refresh_container = UIUtils.easyCenterLayout(refresh_gateways, 100);
			gatewaysMenu.append(refresh_container);
			this.setFreezed(false);
			RosterScreen.this.askRepaint();

		} else if (c == toggle_offline) {
			try {
				UICanvas.lock();
				this.show_offlines = !this.show_offlines;
				this.setDirty(true);
				this.setFreezed(true);
				Enumeration en = xmppClient.roster.contacts.elements();
				while (en.hasMoreElements()) {
					Contact ithContact = (Contact) en.nextElement();
					this.reorganizeContact(ithContact, Contact.CH_STATUS);
				}
				if (show_offlines) toggle_offline.setText(rm
						.getString(ResourceIDs.STR_HIDE_OFFLINE));
				else
					toggle_offline.setText(rm
							.getString(ResourceIDs.STR_SHOW_OFFLINE));

			} finally {
				UICanvas.unlock();
			}
			this.setFreezed(false);
			this.askRepaint();
		} else if (c == cmd_chat) {
			String fullJid = this.getActionJid();
			chatWithSelected(fullJid);
			this.removePopup(optionsMenu);
			// } else if (c == cmd_reload) {
			// Roster.getInstance().updateRoster();
		} else if (c == cmd_state) {
			StatusScreen ssc = new StatusScreen();
			UICanvas.getInstance().open(ssc, true);
			// #mdebug
		} else if (c == cmd_debug) {
			DebugScreen debugScreen = new DebugScreen();
			UICanvas.getInstance().open(debugScreen, true);
			// #enddebug
		} else if (c == cmd_contact_capture_aud) {
			String fullJid = this.getActionJid();
			this.captureMedia(fullJid, Config.audioType);
			this.removePopup(optionsMenu);
		} else if (c == cmd_contact_capture_img) {
			String fullJid = this.getActionJid();
			captureMedia(fullJid, Config.imgType);
			this.removePopup(optionsMenu);
		} else if (c == cmd_capture_aud) {
			this.captureMedia(null, Config.audioType);
		} else if (c == cmd_refresh_roster) {
			this.setFreezed(true);
			try {
				removeAllContacts();
				this.xmppClient.roster.retrieveRoster(false, true);
			} catch (Exception e) {
				//Nothing strange but when freezing / locking it is mandatory 
				// to avoid raising exceptions
				// #mdebug
				e.printStackTrace();
				// #enddebug
			}
			this.setFreezed(false);
			askRepaint();
		} else if (c == cmd_capture_img) {
			captureMedia(null, Config.imgType);
		} else if (c == cmd_querycmd) {
			this.removePopup(optionsMenu);
			Contact usr = getSelectedContact();
			String fullJid = this.getActionJid();
			usr.cmdlist = null;
			queryCmd(fullJid);
		} else if (c == cmd_send_file) {
			String fullJid = this.getActionJid();
			AlbumScreen alb = AlbumScreen.getInstance(fullJid);
			UICanvas.getInstance().open(alb, true);
			this.removePopup(optionsMenu);
		} else if (c == cmd_mucs) {
			UIMenu mucNameMenu = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_CHOOSE_NAME), 10, 20, this
					.getWidth() - 20, muc_name_field);
			mucNameMenu.append(UIUtils.easyCenterLayout(muc_button, 100));
			mucNameMenu.setDirty(true);
			mucNameMenu.setSelectedIndex(mucNameMenu.indexOf(muc_name_field));
			this.addPopup(mucNameMenu);
		} else if (c == cmd_about) {
			AboutScreen as = new AboutScreen();
			UICanvas.getInstance().open(as, true);
		} else if (c == cmd_options) {
			OptionsScreen os = new OptionsScreen();
			UICanvas.getInstance().open(os, true);
		} else if (c == cmd_tasks) {
			Contact usr = getSelectedContact();
			Task tasks[] = usr.getTasks();
			if (tasks.length == 1) {
				// #ifdef UI
				tasks[0].display();
				// #endif
			} else if (tasks.length > 1) {
				TaskListScreen taskListScreen = new TaskListScreen(tasks);
				UICanvas.getInstance().open(taskListScreen, true);
			}
		} else if (c == cmd_details) {
			Contact cont = this.getSelectedContact();
			if (cont != null) {
				ContactInfoScreen ci = new ContactInfoScreen(cont);
				UICanvas.getInstance().open(ci, true);
			}
			this.removePopup(optionsMenu);
		} else if (c == cmd_groups) {
			Contact cont = this.getSelectedContact();
			if (cont != null) {
				GroupsScreen ci = new GroupsScreen(cont);
				UICanvas.getInstance().open(ci, true);
			}
			this.removePopup(optionsMenu);
		} else if (c == muc_button) {
			createMUC(new MUCStateHandler(null));
		} else if (c == this.acceptButton) {
			UIHLayout buttons = (UIHLayout) this.groupInviteMenu.getItemList()
					.lastElement();
			UILabel groupChatLabel = (UILabel) buttons.getItem(2);
			String invitedChatJid = groupChatLabel.getText();
			Contact myContact = xmppClient.getMyContact();
			Presence pres = myContact.getPresence();
			pres.setAttribute(Stanza.ATT_TO, invitedChatJid + "/"
					+ Contact.user(myContact.getPrintableName()));
			Element el = new Element(XMPPClient.NS_MUC, DataForm.X);
			pres.addElement(el);
			xmppClient.sendPacket(pres);
			this.removePopup(this.groupInviteMenu);
		} else if (c == this.denyButton) {
			this.removePopup(this.groupInviteMenu);
		} else if (c == cmd_close_muc) {
			UIContact uiMuc = this.getSelectedUIContact();
			if (uiMuc != null) {
				MUC muc = (MUC) uiMuc.c;
				MUCScreen ms = (MUCScreen) chatScreenList.get(muc.jid);
				if (ms != null) ms.closeMe();
				Iq iq = new Iq(muc.jid, Iq.T_SET);
				Element query = iq
						.addElement(XMPPClient.NS_MUC_OWNER, Iq.QUERY);
				query.addElement(null, "destroy");
				xmppClient.sendIQ(iq, null);
			}
			this.removePopup(this.optionsMenu);
			this.askRepaint();

		} else if (c == this.cmd_exit_muc) {
			//UIAccordion ca = this.getAccordion();
			//UIGroup selGroup = (UIGroup) ca.getOpenedLabel();
			Presence pres = new Presence();
			UIContact uiMuc = this.getSelectedUIContact();
			if (uiMuc != null) {
				MUC muc = (MUC) uiMuc.c;
				MUCScreen ms = (MUCScreen) chatScreenList.get(muc.jid);
				if (ms != null) ms.closeMe();
				pres.setAttribute(Stanza.ATT_TO, muc.jid + "/"
						+ Contact.user(XMPPClient.getInstance().my_jid));
				pres.setAttribute(Stanza.ATT_TYPE, Presence.T_UNAVAILABLE);
				xmppClient.sendPacket(pres);
				//ca.removePanelItem(selGroup, uiMuc);
			}
			this.removePopup(this.optionsMenu);
			this.askRepaint();
		} else if (menu == this.gatewaysMenu) {
			String from = "";
			Enumeration en = gatewaysMenu.getItemList().elements();
			// search the containing object
			while (en.hasMoreElements()) {
				UIItem ithLabel = (UIItem) en.nextElement();
				if (ithLabel instanceof UILabel && ithLabel == c) {
					from = (String) this.transports.get(ithLabel);
					break;
				}
			}

			RegisterWaitScreen rws = new RegisterWaitScreen();
			setWaitingDF(rws);
			UICanvas.getInstance().open(rws, true);

			RegisterHandler rh = new RegisterHandler();
			Iq iq = new Iq(from, Iq.T_GET);
			iq.addElement(XMPPClient.IQ_REGISTER, Iq.QUERY);
			// from this point on all the subscription 
			// "from" and "username@from"
			// will be autoaccepted from this 
			xmppClient.autoAcceptGateways.addElement(from);
			xmppClient.sendIQ(iq, rh);
		}
	}

	public void queryCmd(String fullJid) {
		Iq iq = new Iq(fullJid, Iq.T_GET);
		Element query = iq.addElement(XMPPClient.NS_IQ_DISCO_ITEMS, Iq.QUERY);
		query.setAttribute("node", "http://jabber.org/protocol/commands");
		AdHocCommandsHandler handler = new AdHocCommandsHandler();
		xmppClient.sendIQ(iq, handler);
	}

	public void createMUC(IQResultListener listener) {
		String mucName = this.muc_name_field.getText().replace(' ', '_');
		Contact myContact = xmppClient.getMyContact();
		Presence pres = myContact.getPresence();
		pres.setAttribute(Stanza.ATT_TO, mucName + "@" + this.mucJid + "/"
				+ Contact.user(myContact.getPrintableName()));
		Element el = new Element(XMPPClient.NS_MUC, DataForm.X);
		pres.addElement(el);
		xmppClient.sendPacket(pres);

		Iq iq = new Iq(mucName + "@" + this.mucJid + "/", Iq.T_GET);
		Element query = new Element(XMPPClient.NS_MUC_OWNER, Iq.QUERY);
		iq.addElement(query);
		//		Element x = new Element(DataForm.NAMESPACE, DataForm.X);
		//		x.setAttribute("type", "submit");
		//		query.addElement(x);
		xmppClient.sendIQ(iq, listener);
		this.muc_name_field.setText("");
	}

	private String getActionJid() {
		if (optionsAccordion != null) {
			return (String) this.commandResources.get(this.optionsAccordion
					.getOpenedLabel());
		} else {
			return (String) this.commandResources.get(this.optionsMenu);
		}

	}

	private void openContactMenu() {
		// TODO Auto-generated method stub
		// setting the options menu
		UIItem selContact = getAccordion().getSelectedItem();
		if (selContact == null || selContact instanceof UIContact == false) return;

		Contact c = ((UIContact) selContact).c;

		if (c != null) {
			optionsMenu = UIUtils.easyMenu(c.getPrintableName(), 10,
					((UIContact) selContact).getSubmenu().getAbsoluteY(),
					UICanvas.getInstance().getWidth() - 20, null);
			optionsMenu.setAutoClose(false);
			optionsAccordion = null;
			Presence[] res = c.getAllPresences();
			int resLength = 1;
			if (res != null) resLength = res.length;
			this.commandResources = new Hashtable(resLength);
			if (res != null && res.length > 1 && (c instanceof MUC == false)) {
				optionsAccordion = new UIAccordion();
				optionsAccordion.setFocusable(true);
				optionsAccordion.setMaxHeight(0);
				optionsAccordion.setOneOpen(false);
				optionsAccordion.setModal(true);

				optionsAccordion.addSpareItem(cmd_details);
				optionsAccordion.addSpareItem(cmd_groups);
				optionsAccordion.addSpareItem(cmd_delc);

				for (int i = 0; i < res.length; i++) {
					optionsVector = new Vector();

					String resString = null;
					resString = Contact.resource(res[i]
							.getAttribute(Iq.ATT_FROM));
					if (resString == null) resString = res[i]
							.getAttribute(Iq.ATT_FROM);
					Image img = xmppClient.getPresenceIcon(c, res[i]
							.getAttribute(Iq.ATT_FROM), c.getAvailability());
					optionsLabel = new UILabel(img, resString);
					optionsLabel.setWrappable(true, UICanvas.getInstance()
							.getWidth() - 30);
					this.commandResources.put(optionsLabel, res[i]
							.getAttribute(Message.ATT_FROM));
					optionsVector.addElement(cmd_chat);
					optionsVector.addElement(cmd_send);
					optionsVector.addElement(cmd_send_file);
					if (this.cameraOn) optionsVector
							.addElement(cmd_contact_capture_img);
					if (this.micOn) optionsVector
							.addElement(cmd_contact_capture_aud);
					optionsVector.addElement(cmd_querycmd);
					if (c.pending_tasks) {
						optionsVector.addElement(cmd_tasks);
					}
					optionsAccordion.addItem(optionsLabel, optionsVector);
				}
				optionsMenu.append(optionsAccordion);
				//optionsAccordion.openLabel(optionsAccordion.getItemLabels()[0]);
				optionsAccordion.setSelectedIndex(0);
				optionsMenu.setSelectedItem(cmd_details);
			} else {
				String toRes = (res != null ? res[0]
						.getAttribute(Message.ATT_FROM) : c.jid);
				this.commandResources.put(optionsMenu, toRes);
				optionsMenu.append(cmd_chat);
				optionsMenu.append(cmd_send);
				optionsMenu.append(cmd_send_file);
				if (this.cameraOn) optionsMenu.append(cmd_contact_capture_img);
				if (this.micOn) optionsMenu.append(cmd_contact_capture_aud);
				optionsMenu.append(cmd_details);
				optionsMenu.append(cmd_groups);
				optionsMenu.append(cmd_delc);
				optionsMenu.append(cmd_querycmd);
				if (c.pending_tasks) {
					optionsMenu.append(cmd_tasks);
				}
				if (c instanceof MUC) {
					optionsMenu.remove(cmd_delc);
					optionsMenu.append(cmd_exit_muc);
					optionsMenu.append(cmd_close_muc);
				}
				optionsMenu.setSelectedItem(cmd_chat);
			}

			this.addPopup(optionsMenu);
		}
	}

	private void getIMGateways(String serverAddress) {
		transports.clear();
		IQResultListener dih = new IQResultListener() {
			public void handleResult(Element e) {
				Element q = e.getChildByName(XMPPClient.NS_IQ_DISCO_ITEMS,
						Iq.QUERY);
				if (q != null) {
					Element items[] = q.getChildrenByName(
							XMPPClient.NS_IQ_DISCO_ITEMS, "item");
					for (int i = 0; i < items.length; i++) {
						String ithJid = items[i].getAttribute("jid");
						IQResultListener dih = new IQResultListener() {
							public void handleError(Element e) {
							}

							public void handleResult(Element e) {
								Element q = e.getChildByName(
										XMPPClient.NS_IQ_DISCO_INFO, Iq.QUERY);
								if (q != null) {
									String type = null;
									String name = "";
									String from = e.getAttribute("from");
									Element identity = q.getChildByName(
											XMPPClient.NS_IQ_DISCO_INFO,
											"identity");
									if (identity != null) {
										type = identity.getAttribute("type");
										String category = identity
												.getAttribute("category");
										if (category.compareTo("conference") == 0
												&& type.compareTo("text") == 0) {
											mucJid = from;
											// the mucJid is changed toggle the menus
											RosterScreen.this.toggleMenus();
										}

										if (category.compareTo("store") == 0
												&& type.compareTo("file") == 0
												&& FTSender.supportFT(q)) {
											uploadJid = from;
											getBasePath(q);
										}

										name = identity.getAttribute("name");
									} else {
										name = from;
									}

									Element features[] = q.getChildrenByName(
											XMPPClient.NS_IQ_DISCO_INFO,
											"feature");
									String category = "";

									if (identity != null) category = identity
											.getAttribute("category");

									for (int i = 0; i < features.length; i++) {
										String var = features[i]
												.getAttribute("var");
										if (var
												.compareTo(XMPPClient.IQ_REGISTER) == 0) {
											Image img = null;
											if (type != null) {
												try {
													img = Image
															.createImage("/transport/"
																	+ type
																	+ ".png");
												} catch (IOException ex) {
													try {
														img = Image
																.createImage("/transport/transport.png");
													} catch (IOException e1) {
														// TODO Auto-generated
														// catch block
														e1.printStackTrace();
													}
												}
											} else {
												try {
													img = Image
															.createImage("/transport/transport.png");
												} catch (IOException e1) {
													// TODO Auto-generated catch
													// block
													e1.printStackTrace();
												}
											}

											if (category.compareTo("gateway") == 0) {
												RosterScreen.this.addGateway(
														gateways, name, from,
														img, type);
											}
										}
									}
								}
							}

							private void getBasePath(Element q) {
								Element x = q.getChildByName(
										XMPPClient.JABBER_X_DATA, "x");
								if (x == null) return;
								Element[] fields = x.getChildrenByName(null,
										"field");
								if (fields == null) return;
								for (int j = 0; j < fields.length; j++) {
									Element ithField = fields[j];
									if (ithField.getAttribute("var").compareTo(
											"url") == 0) {
										basePath = ithField.getChildByName(
												null, "value").getText();
									} else if (ithField.getAttribute("var")
											.compareTo("suffix") == 0) {
										uploadSuffix = ithField.getChildByName(
												null, "value").getText();
									}
								}
							}
						};
						Iq iq = new Iq(ithJid, Iq.T_GET);
						iq.addElement(XMPPClient.NS_IQ_DISCO_INFO, Iq.QUERY);
						xmppClient.sendIQ(iq, dih);
					}
				}
			}

			public void handleError(Element e) {
			}
		};

		Iq iq = new Iq(serverAddress, Iq.T_GET);
		iq.addElement(XMPPClient.NS_IQ_DISCO_ITEMS, Iq.QUERY);
		xmppClient.sendIQ(iq, dih);
	}

	private void addGateway(Hashtable components, String name, String from,
			Image img, String type) {
		Enumeration en = components.keys();
		while (en.hasMoreElements()) {
			String ithFrom = (String) en.nextElement();
			Object[] ithData = (Object[]) components.get(ithFrom);
			String ithType = (String) ithData[2];
			if (ithType.compareTo(type) == 0) {
				if (from.indexOf(Config.BLUENDO_SERVER) >= 0) return;
				else {
					components.remove(from);
					break;
				}
			}
		}
		components.put(from, new Object[] { name, img, type });
	}

	private UIContact getSelectedUIContact() {
		UIAccordion accordion = this.getAccordion();
		if (accordion == null) return null;
		UIItem selContact = accordion.getSelectedItem();
		if (selContact instanceof UIContact) return ((UIContact) selContact);
		else
			return null;
	}

	private Contact getSelectedContact() {
		UIContact selUIContact = this.getSelectedUIContact();
		if (selUIContact != null) return selUIContact.c;
		return null;
	}

	private UIGroup getSelectedUIGroup() {
		UIAccordion accordion = this.getAccordion();
		if (accordion == null) return null;
		UIItem selUIGroup = accordion.getSelectedItem();
		if (selUIGroup instanceof UIGroup) return (UIGroup) selUIGroup;
		else
			return null;
	}

	private UIAccordion getAccordion() {
		if (this.indexOf(rosterAccordion) > 0) return rosterAccordion;
		if (this.indexOf(searchAccordion) > 0) return searchAccordion;
		return null;
	}

	private void chatWithSelected(String preferredJid) {
		Contact user = getSelectedContact();
		if (user == null) return;
		chatWithContact(user, preferredJid);
	}

	void chatWithContact(Contact user, String preferredJid) {
		// add it to the highlight group
		UIGroup highLightGroup = UIGroup.getGroup(highLightString,
				rosterAccordion, true);
		UICanvas.lock();
		try {
			highLightGroup.reorganizeContact(user, Contact.CH_STATUS);
		} catch (Exception e) {
			// #mdebug
			e.printStackTrace();
			// #enddebug
		}
		UICanvas.unlock();

		if (preferredJid == null && user instanceof MUC == false) preferredJid = user
				.getPresence().getAttribute(Message.ATT_FROM);
		if (user instanceof MUC) preferredJid = user.jid;
		String key = (preferredJid != null ? preferredJid : user.jid);
		//		if (user.unread_msg() || force_chat) {
		ChatScreen ms = (ChatScreen) chatScreenList.get(key);
		if (ms == null) {
			if (user instanceof MUC == true) {
				ms = new MUCScreen(user);
				chatScreenList.put(key, ms);
			} else {
				ms = new ChatScreen(user, preferredJid);
				chatScreenList.put(key, ms);
			}
		} else {
			if (user instanceof MUC == false) {
				ms.preferredResource = preferredJid;
			}
		}
		UICanvas.getInstance().open(ms, true);
		//		} else {
		//			SimpleComposerScreen cs = new SimpleComposerScreen(user, null);
		//			UICanvas.display(cs);
		//		}
		//user.unread_msg = false;
	}

	public static void showNextScreen(UIScreen currentScreen) {
		Vector screenList = UICanvas.getInstance().getScreenList();
		int currentIndex = screenList.indexOf(currentScreen);
		currentIndex++;
		if (currentIndex >= screenList.size()) currentIndex = 0;
		if (screenList.size() > 1) UICanvas.getInstance().show(currentIndex);
	}

	public static void showPreviousScreen(UIScreen currentScreen) {
		Vector screenList = UICanvas.getInstance().getScreenList();
		int currentIndex = screenList.indexOf(currentScreen);
		currentIndex--;
		if (currentIndex < 0) currentIndex = screenList.size() - 1;
		if (screenList.size() > 1) UICanvas.getInstance().show(currentIndex);
	}

	public static void closeAndOpenRoster(UIScreen shownScreen) {
		UICanvas uiCanvas = UICanvas.getInstance();
		uiCanvas.close(shownScreen);
		uiCanvas.show(RosterScreen.getInstance());
	}

	private class AdHocCommandsHandler extends IQResultListener {

		public void handleError(Element e) {
			// simply ignore -> XXX we could add an alert
		}

		public void handleResult(Element e) {
			RosterScreen.this.xmppClient.handleClientCommands(e, true);
		}
	}

	private class RegisterWaitScreen extends UIScreen implements WaitScreen {

		private UILabel cmd_cancel = new UILabel(rm.getString(
				ResourceIDs.STR_CLOSE).toUpperCase());

		private UIPanel mainList = new UIPanel(true, true);

		UIGauge progress_gauge = new UIGauge(
				rm.getString(ResourceIDs.STR_WAIT), false, Gauge.INDEFINITE,
				Gauge.CONTINUOUS_RUNNING);

		public RegisterWaitScreen() {
			setMenu(new UIMenu(""));
			UIMenu menu = getMenu();
			menu.append(cmd_cancel);
			setTitle(rm.getString(ResourceIDs.STR_REGISTER));
			this.append(mainList);
			mainList.addItem(progress_gauge);
			progress_gauge.start();
		}

		public void menuAction(UIMenu menu, UIItem cmd) {
			if (cmd == cmd_cancel) {
				stopWaiting();
				RosterScreen.closeAndOpenRoster(this);
			}
		}

		public void stopWaiting() {
			progress_gauge.cancel();
			UICanvas.getInstance().close(this);
		}
	}

	private class RegisterHandler extends IQResultListener {

		/*
		 * 
		 * the received registration packet with form
		 */
		private Element e;

		/*
		 * the received dataform
		 */
		private DataForm df;

		/*
		 * the dataformscreen opened with registration data
		 */
		private UIScreen dfs;

		public void handleError(Element e) {
			// TODO Auto-generated method stub

		}

		public void handleResult(Element e) {
			closeWaitingScreen();
			Element q = e.getChildByName(XMPPClient.IQ_REGISTER, Iq.QUERY);
			if (q != null) {
				/* Parse the dataform if present */
				this.e = e;
				UIScreen screen = null;
				Element form = q.getChildByName(DataForm.NAMESPACE, DataForm.X);
				if (form != null) {
					DataForm df = new DataForm(form);
					this.df = df;
					RegisterDataFormExecutor rdf = new RegisterDataFormExecutor(
							this);
					screen = new DataFormScreen(df, rdf);
					this.dfs = screen;
				} else if (q.getChildByName(null, "username") != null) {
					screen = new GatewayRegisterScreen(e);
				}
				if (screen != null) UICanvas.getInstance().open(screen, true);
				else
					UICanvas.getInstance().open(RosterScreen.this, true);
			}
		}
	}

	public class RegisterDataFormExecutor extends IQResultListener implements
			DataFormListener {

		private RegisterHandler registerHandler;

		public RegisterDataFormExecutor(RegisterHandler registerHandler) {
			this.registerHandler = registerHandler;
		}

		public boolean execute(int cmd) {
			if (cmd == DataFormListener.CMD_SUBMIT) {
				String from = registerHandler.e.getAttribute(Stanza.ATT_FROM);
				Iq reply = new Iq(from, Iq.T_SET);
				reply.setAttribute(Stanza.ATT_FROM, registerHandler.e
						.getAttribute(Stanza.ATT_TO));
				Element query = new Element(XMPPClient.IQ_REGISTER, Iq.QUERY);
				reply.addElement(query);
				DataForm df = registerHandler.df;
				df.type = DataForm.TYPE_SUBMIT;
				query.addElement(df.getResultElement());
				xmppClient.sendIQ(reply, this);
				UICanvas.getInstance().close(registerHandler.dfs);
			} else if (cmd == DataFormListener.CMD_CANCEL) {
				UICanvas.getInstance().close(registerHandler.dfs);
			}
			return false;
		}

		public void handleError(Element e) {
			String errorText = rm.getString(ResourceIDs.STR_REG_ERROR);
			Element error = e.getChildByName(null, Iq.T_ERROR);
			if (error != null) {
				String code = error.getAttribute("code");
				if (code != null) errorText += (": " + XMPPClient
						.getErrorString(code));
			}
			XMPPClient.getInstance().showAlert(AlertType.INFO,
					rm.getString(ResourceIDs.STR_REGISTER), errorText, null);
		}

		public void handleResult(Element e) {
			String from = registerHandler.e.getAttribute(Stanza.ATT_FROM);
			Object[] nameImg = (Object[]) gateways.get(from);
			String name = (String) nameImg[0];
			String selectedText = name + ": ";
			UILabel regLabel = new UILabel(selectedText + " "
					+ rm.getString(ResourceIDs.STR_REG_GATEWAYS));
			UIMenu regMenu = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_GATEWAYS), 10, 20,
					RosterScreen.this.getWidth() - 20, regLabel);
			regLabel.setWrappable(true, regMenu.getWidth());
			RosterScreen.this.addPopup(regMenu);
		}
	}

	/**
	 * Update the (global) status of a contact and repaint the roster
	 * accordingly to the new situation
	 * 
	 * @param c
	 */
	public void updateContact(Contact c, int reason) {
		//return;
		// #ifdef TIMING
		 long t1 = System.currentTimeMillis();
		// #endif
		boolean needRepaint = false;
		try {
			UICanvas.lock();
			needRepaint = reorganizeContact(c, reason);
		} catch (Exception e) {
			// #mdebug
			e.printStackTrace();
			// #enddebug
		} finally {
			UICanvas.unlock();
			if (needRepaint) {
				rosterAccordion.setDirty(true);
				askRepaint();
				UIScreen cs = UICanvas.getInstance().getCurrentScreen();
				if (cs != this) UICanvas.getInstance().askRepaint(cs);
			}
		}

		// #ifdef TIMING
		 System.out.println("New sort time: " + (System.currentTimeMillis()
		  - t1));
		// #endif
	}

	boolean reorganizeContact(Contact c, int reason) {
		boolean needRepaint = false;

		// if a status is changed i must update the headers of 
		// all the chatscreens and not mucscreens and repaint only the painted screen
		if (reason == Contact.CH_STATUS) {
			Enumeration en = chatScreenList.keys();
			while (en.hasMoreElements()) {
				String ithJid = (String) en.nextElement();
				if (Contact.userhost(ithJid).compareTo(c.jid) == 0) {
					ChatScreen contactScreen = ((ChatScreen) chatScreenList
							.get(ithJid));
					contactScreen.updateResource();
					if (UICanvas.getInstance().getCurrentScreen() == contactScreen) needRepaint = true;
				}
			}
		}

		if (reason == Contact.CH_GROUP) {
			// if a contact has changed a group 
			// it must be removed from all the groups
			Enumeration en = UIGroup.uiGroups.keys();
			while (en.hasMoreElements()) {
				String ithGroup = (String) en.nextElement();
				if (ithGroup.equals(highLightString) == false) {
					UIGroup ithUIGroup = UIGroup.getGroup(ithGroup,
							rosterAccordion, true);
					ithUIGroup.removeContact(c);
				}
			}
			return true;
		}
		UIGroup ithGroupLabel = null;
		String[] contactGroups = c.getGroups();
		if (contactGroups.length == 0) {
			ithGroupLabel = UIGroup.getGroup(ungrouped, rosterAccordion, false);
			boolean allowReorganize = c.isVisible() || ithGroupLabel != null;
			if (allowReorganize) {
				if (ithGroupLabel == null) ithGroupLabel = UIGroup.getGroup(
						ungrouped, rosterAccordion, true);
				needRepaint |= ithGroupLabel.reorganizeContact(c, reason);
			}
		} else {
			for (int i = 0; i < contactGroups.length; i++) {
				String ithGroup = contactGroups[i];
				ithGroupLabel = UIGroup.getGroup(ithGroup, rosterAccordion,
						false);
				boolean allowReorganize = c.isVisible()
						|| ithGroupLabel != null;
				if (allowReorganize) {
					if (ithGroupLabel == null) ithGroupLabel = UIGroup
							.getGroup(ithGroup, rosterAccordion, true);
					needRepaint |= ithGroupLabel.reorganizeContact(c, reason);
				}
			}
		}

		// The higlight is a special group; it needs some extra check 
		boolean highlightCheck = (reason == Contact.CH_MESSAGE_NEW || reason == Contact.CH_TASK_NEW);

		// in the following cases i must check if the user is in the higlight group
		// and if the groups itself exists
		UIGroup highLightGroup = UIGroup.getGroup(highLightString,
				rosterAccordion, false);
		highlightCheck |= ((reason == Contact.CH_MESSAGE_READ
				|| reason == Contact.CH_TASK_REMOVED || reason == Contact.CH_STATUS)
				&& highLightGroup != null && highLightGroup.getUIContact(c) != null);

		if (highlightCheck) {
			if (highLightGroup == null) highLightGroup = UIGroup.getGroup(
					highLightString, rosterAccordion, true);
			needRepaint |= highLightGroup.reorganizeContact(c, reason);
		}
		return needRepaint;
	}

	protected boolean askRepaint() {
		boolean retVal = true;
		try {
			this.updateHeader();
			retVal = super.askRepaint();
		} catch (Exception e) {
			// #mdebug
			e.printStackTrace();
			// #enddebug
		}
		return retVal;
	}

	private boolean filtering = false;

	/*
	 * Filter the contacts by means of the letters
	 * selected by user in the rosterscreen.
	 * 
	 * @param reuse If false restart filtering from scratch
	 * @return true is something has changed
	 */
	private boolean filterContacts(boolean reuse) {
		// sometimes i must repaint here and sometimes not
		// i hence must save the freeze status
		boolean needRepaint = false;
		boolean oldFreezed = this.isFreezed();
		filtering = true;
		this.setFreezed(true);

		try {
			int oldSelectedIndex = searchAccordion.getSelectedIndex();
			// it could have been removed hence check for it again
			searchGroup = UIGroup.getGroup(searchString, searchAccordion, true);
			searchGroup.contacts.clear();
			searchAccordion.clearPanel(searchGroup);
			if (sel_pattern.length() > 0) {
				// cannot use enumeration !!!
				Vector goodContacts = new Vector();
				Enumeration en = UIGroup.uiGroups.elements();
				while (en.hasMoreElements()) {
					UIGroup ithGroup = (UIGroup) en.nextElement();
					Enumeration en2 = ithGroup.contacts.keys();
					while (en2.hasMoreElements()) {
						Contact c = (Contact) en2.nextElement();
						String contactName = c.getPrintableName().toLowerCase();
						if (contactName.indexOf(sel_pattern) == 0) {
							goodContacts.addElement(c);
						}
					}
				}
				en = goodContacts.elements();
				while (en.hasMoreElements()) {
					Contact contactToReorganize = ((Contact) en.nextElement());
					try {
						searchGroup = UIGroup.getGroup(searchString,
								searchAccordion, true);
						searchGroup.reorganizeContact(contactToReorganize,
								Contact.CH_STATUS);
					} catch (Exception e) {
						// #mdebug
						Logger.log("In filtering contacts :");
						e.printStackTrace();
						System.out.println(contactToReorganize.jid);
						// #enddebug
					}
				}
				this.searchAccordion.openLabel(searchGroup);
				needRepaint = true;
			}
			if (oldSelectedIndex != -1) searchAccordion
					.setSelectedIndex(oldSelectedIndex);
		} catch (Exception e) {
			// #mdebug
			// nothing special but since this is done 
			// within a lock / freeze it is mandatory to avoid 
			// raising exception
			Logger.log("In filtering contacts :");
			e.printStackTrace();
			// #enddebug
		}
		this.setFreezed(oldFreezed);
		filtering = false;
		return needRepaint;
	}

	public void removeContact(Contact c) {
		try {
			UICanvas.lock();

			String[] cGroups = c.getGroups();
			for (int i = 0; i < cGroups.length; i++) {
				String ithGroup = cGroups[i];
				UIGroup ithUIGroup = UIGroup.getGroup(ithGroup,
						rosterAccordion, false);
				if (ithUIGroup != null) ithUIGroup.removeContact(c);
			}
			if (cGroups.length == 0) {
				UIGroup ithUIGroup = UIGroup.getGroup(ungrouped,
						rosterAccordion, false);
				if (ithUIGroup != null) ithUIGroup.removeContact(c);
			}
			UIGroup highLightGroup = UIGroup.getGroup(highLightString,
					rosterAccordion, false);
			if (highLightGroup != null) highLightGroup.removeContact(c);

			Presence[] pres = c.getAllPresences();
			for (int l = 0; pres != null && l < pres.length; l++)
				chatScreenList.remove(pres[l]);
			chatScreenList.remove(c.jid);

			if (filtering == false) filterContacts(true);
		} catch (Exception e) {
			// #mdebug
			e.printStackTrace();
			// #enddebug    
		} finally {
			UICanvas.unlock();
		}

		// XXX we could repaint only if this contact is really displayed
		askRepaint();
	}

	public void removeAllContacts() {
		while (chatScreenList.size() > 0) {
			Object key = chatScreenList.keys().nextElement();
			ChatScreen cs = (ChatScreen) chatScreenList.get(key);
			cs.updateConversation();
			UICanvas.getInstance().close(cs);
			chatScreenList.remove(key);
		}
		this.rosterAccordion.removeAllItems();
		this.searchAccordion.removeAllItems();
		UIGroup.uiGroups.clear();
	}

	public void packetReceived(Element e) {
		String userHost = Contact.userhost(e.getAttribute(Iq.ATT_FROM));

		// a presence has arrived for a MUC
		if (e.name.compareTo("presence") == 0) {
			String type = e.getAttribute("type");
			MUC presenceMUC = (MUC) xmppClient.getRoster().getContactByJid(
					userHost);
			if (presenceMUC != null) MUCScreen.handlePresence(presenceMUC, e,
					type);
			return;
		}

		// Dispatch the topic to the correct MUC
		if (e.name.compareTo("message") == 0) {
			Element subject = e.getChildByName(null, "subject");
			Contact muc = xmppClient.roster.getContactByJid(userHost);
			if (muc != null) {
				MUCScreen mucScreen = (MUCScreen) chatScreenList.get(muc.jid);
				if (subject != null) {
					if (mucScreen == null) {
						mucScreen = new MUCScreen(muc);
						chatScreenList.put(muc.jid, mucScreen);
						UICanvas.getInstance().open(mucScreen, false);
					}
					((MUC) mucScreen.user).topic = subject.getText();
					UILabel mucName = (UILabel) mucScreen.header.getItem(0);
					mucName.setText(rm.getString(ResourceIDs.STR_TOPIC) + ": "
							+ subject.getText());
					UICanvas.getInstance().askRepaint(mucScreen);

					return;
				}
			}
		}

		String invitedMuc = e.getAttribute(Message.ATT_FROM);
		String mucName = Contact.user(invitedMuc);
		String inviterName = e.getChildByName(XMPPClient.NS_MUC_USER, "x")
				.getChildByName(null, "invite").getAttribute(Message.ATT_FROM);
		if (inviterName == null) return;
		Contact c = xmppClient.roster.getContactByJid(inviterName);
		String printableName = "";
		if (c != null) printableName = c.getPrintableName();
		else
			printableName = inviterName;
		UILabel info = new UILabel(rm
				.getString(ResourceIDs.STR_GROUP_CHAT_INVITATION)
				+ " " + printableName + "?");
		groupInviteMenu = UIUtils.easyMenu(rm
				.getString(ResourceIDs.STR_GROUP_CHAT), 10, 20,
				this.getWidth() - 20, info);
		info.setWrappable(true, groupInviteMenu.getWidth() - 5);
		info.setFocusable(false);
		UILabel groupName = new UILabel(rm
				.getString(ResourceIDs.STR_GROUP_CHAT)
				+ ": " + mucName);
		info.setSelected(false);
		groupInviteMenu.append(groupName);
		groupName.setFocusable(false);
		UIHLayout buttons = new UIHLayout(3);
		acceptButton = new UIButton(rm.getString(ResourceIDs.STR_YES));
		denyButton = new UIButton(rm.getString(ResourceIDs.STR_NO));
		buttons.insert(acceptButton, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);
		buttons.insert(denyButton, 1, 50, UILayout.CONSTRAINT_PERCENTUAL);
		acceptButton.setAnchorPoint(Graphics.HCENTER);
		denyButton.setAnchorPoint(Graphics.HCENTER);
		// hide conference name
		buttons.insert(new UILabel(invitedMuc), 2, 0,
				UILayout.CONSTRAINT_PIXELS);
		buttons.setGroup(false);
		groupInviteMenu.append(buttons);
		buttons.setSelectedItem(acceptButton);
		this.addPopup(groupInviteMenu);
	}

	/**
	 * @return the chatScreenList
	 */
	public static Hashtable getChatScreenList() {
		return chatScreenList;
	}

	public void dataReceived(byte[] data, String fileName, String fileDesc,
			OpenListener ftrp) {
		try {
			UIScreen imScreen = new ShowMMScreen(data, fileName, fileDesc);
			UICanvas.getInstance().open(imScreen, true);
			FTScreen.ftFinished(ftrp);
		} catch (Exception e) {
			String textAck = rm.getString(ResourceIDs.STR_DECODE_ERROR) + " "
					+ fileName;
			ftNotification(true, textAck, textAck);
		}
	}

	public void reqFT(String contactName, OpenListener ftrp) {
		Contact c = XMPPClient.getInstance().getRoster().getContactByJid(
				contactName);
		if (c == null) {
			ftrp.answerFT(false);
			return;
		}

		xmppClient.playSmartTone();
		FileReceiveScreen frs = new FileReceiveScreen(c, ftrp);
		UICanvas.getInstance().open(frs, true);
	}

	public void fileAcceptance(Contact c, String fileName, boolean accept,
			FTSender sender) {
		FTScreen.ftAccept(sender, accept);
		//		if (accept) {
		//			FTScreen.addFileSend(sender);
		//		}
		//		String textAck = c.getPrintableName() + " "
		//				+ rm.getString(ResourceIDs.STR_FT_ACCEPTED) + " " + fileName;
		//		String textNack = c.getPrintableName() + " "
		//				+ rm.getString(ResourceIDs.STR_FT_DECLINED) + " " + fileName;
		//		ftNotification(accept, textAck, textNack);
	}

	/*
	 * A helper function deputed to notify the user about file transfer status
	 * 
	 * @param accept
	 *            A flag to identify if it is an "ack" or a "nack"
	 *            
	 * @param textAck
	 *            The text to show if it is an "ack"
	 *            
	 * @param textAck
	 *            The text to show if it is a "nack"
	 *            
	 */
	private void ftNotification(boolean accept, String textAck, String textNack) {
		String title = rm.getString(ResourceIDs.STR_FT);
		UILabel acceptLabel = null;
		if (accept == true) acceptLabel = new UILabel(textAck);
		else
			acceptLabel = new UILabel(textNack);
		acceptLabel.setWrappable(true, this.getWidth() - 20);
		UIMenu acceptMenu = UIUtils.easyMenu(title, 10, 40,
				this.getWidth() - 20, acceptLabel);
		acceptMenu.cancelMenuString = "";
		acceptMenu.selectMenuString = rm.getString(ResourceIDs.STR_CONTINUE)
				.toUpperCase();
		UICanvas.getInstance().getCurrentScreen().addPopup(acceptMenu);
	}

	public void sessionInitated(Contact contactByJid, String fileName,
			FTSender sender) {
		FTScreen.addFileSend(sender, fileName);
	}

	public void fileSent(Contact c, String fileName, boolean success,
			FTSender sender) {
		FTScreen.ftFinished(sender);
		//		String textAck = fileName + " " + rm.getString(ResourceIDs.STR_FT_SENT)
		//				+ " " + c.getPrintableName();
		//		String textNack = fileName + " " + rm.getString(ResourceIDs.STR_FT_NOT)
		//				+ " " + rm.getString(ResourceIDs.STR_FT_SENT) + " "
		//				+ c.getPrintableName();
		//		ftNotification(success, textAck, textNack);
	}

	public void chunkSent(int sentBytes, int length, FTSender sender) {
		FTScreen.chunkTransferred(sentBytes, length, sender);

	}

	public void chunkReceived(int bytesReceived, int length,
			OpenListener openListener) {
		FTScreen.chunkTransferred(bytesReceived, length, openListener);

	}

	public void fileError(Contact c, String fileName, Element e) {
		Element error = e.getChildByName(null, "error");
		if (error != null) {
			error.getChildByName(null, "feature-not-implemented");
		}

		String textAck = rm.getString(ResourceIDs.STR_ERROR) + ". "
				+ c.getPrintableName() + " "
				+ rm.getString(ResourceIDs.STR_FT_ERROR) + " " + fileName;

		ftNotification(true, textAck, textAck);
	}

	public void authenticated() {
		gatewaysServer = Config.BLUENDO_SERVER;

		// setup here all the needed listeners
		// registration to get notified of MUC invite
		EventQuery q = new EventQuery("message", null, null);
		EventQuery x = new EventQuery("x", new String[] { "xmlns" },
				new String[] { XMPPClient.NS_MUC_USER });
		q.child = x;
		EventQuery invite = new EventQuery("invite", null, null);
		x.child = invite;
		BasicXmlStream.addEventListener(q, this);

		// registration to handle people left/join within MUCs
		q = new EventQuery("presence", null, null);
		x = new EventQuery("x", new String[] { "xmlns" },
				new String[] { XMPPClient.NS_MUC_USER });
		q.child = x;
		invite = new EventQuery("item", null, null);
		x.child = invite;
		BasicXmlStream.addEventListener(q, this);

		// listen for all incoming messages with subject
		// this is used to set the topic for MUC
		// listen here and dispatch to the correct MUC
		/// XXX: something better ?
		q = new EventQuery("message", new String[] { Iq.ATT_TYPE },
				new String[] { Message.GROUPCHAT });
		q.child = new EventQuery("subject", null, null);
		BasicXmlStream.addEventListener(q, this);

		this.ftReceiver = new FTReceiver((FTREventHandler) this);
		//= Contact.domain(XMPPClient.getInstance().my_jid);
		firstLoginIntro();
	}

	private void firstLoginIntro() {
		Config cfg = Config.getInstance();
		// check first login
		if (Config.FALSE.equals(cfg.getProperty(Config.CLIENT_INITIALIZED))) {
			cfg.setProperty(Config.CLIENT_INITIALIZED, Config.TRUE);
			cfg.saveToStorage();
			String hintText = rm.getString(ResourceIDs.STR_GATEWAY_HINT) + " "
					+ rm.getString(ResourceIDs.STR_SCARY_GMAIL);
			hintText = hintText.replace('<', '\n');
			UITextField gatewayHint = new UITextField("", hintText, hintText
					.length(), TextField.UNEDITABLE);
			gatewayHint.setWrappable(true);
			gatewayHint.setAutoUnexpand(false);
			gatewayHint.setExpandable(false);
			int canvasWidth = UICanvas.getInstance().getWidth() - 20;
			UIMenu firstLogin = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_INSTRUCTIONS), 10, 20,
					canvasWidth, gatewayHint);
			firstLogin.setSelectedIndex(1);
			firstLogin.cancelMenuString = "";
			firstLogin.selectMenuString = rm
					.getString(ResourceIDs.STR_CONTINUE).toUpperCase();
			UIHLayout gatewayLayout = new UIHLayout(5);
			Vector images = new Vector(5);
			images.addElement(UICanvas.getUIImage("/transport/msn.png"));
			images.addElement(UICanvas.getUIImage("/transport/icq.png"));
			images.addElement(UICanvas.getUIImage("/transport/aim.png"));
			images.addElement(UICanvas.getUIImage("/transport/yahoo.png"));
			images.addElement(UICanvas.getUIImage("/transport/transport.png"));
			Enumeration en = images.elements();
			int i = 0;
			while (en.hasMoreElements()) {
				UILabel ithLabel = new UILabel((Image) en.nextElement());
				ithLabel.setAnchorPoint(Graphics.HCENTER);
				gatewayLayout.insert(ithLabel, i, 25,
						UILayout.CONSTRAINT_PERCENTUAL);
				i++;
			}

			firstLogin.replace(0, gatewayLayout);
			this.addPopup(firstLogin);
			//			UICanvas.getInstance().open(this, true);
			this.askRepaint();
			gatewayHint.expand();
		}
	}

	public void rosterXsubscription(Element e) {
		Contact fromContact = xmppClient.getRoster().getContactByJid(
				e.getAttribute(Iq.ATT_FROM));
		if (fromContact == null) { return; }
		Element x = e.getChildByName(null, "x");
		Element[] items = x.getChildrenByName(null, "item");
		SubscribeScreen sScreen = SubscribeScreen
				.getComponentSubscription(fromContact);
		boolean rosterModified = false;
		for (int i = 0; i < items.length; i++) {
			Element ithItem = items[i];
			String name = ithItem.getAttribute("name");
			String jid = ithItem.getAttribute("jid");
			String group = ithItem.getChildByName(null, "group").getText();
			String groups[] = null;
			if (group != null && group.length() > 0) {
				groups = new String[] { group };
			}
			String action = ithItem.getAttribute("action");
			Contact c = xmppClient.getRoster().getContactByJid(
					Contact.userhost(jid));
			if (c != null && action.compareTo("delete") == 0) rosterModified |= sScreen
					.addSubscription(c, SubscribeScreen.DELETE);
			// if I have a previous presence check to remove it and create a new one!		
			if (action.compareTo("add") == 0) {
				if (c == null
						|| !(c.subscription.equals("both") || c.subscription
								.equals("to"))) {
					if (c != null) {
						xmppClient.getRoster().contacts.remove(c);
						this.removeContact(c);
					}
					c = new Contact(jid, name, null, groups);
					rosterModified |= sScreen.addSubscription(c,
							SubscribeScreen.ADD);
				}

			}
		}

		if (e.name.compareTo(Iq.IQ) == 0) {
			Element answer = e;
			e.setAttributes(
					new String[] { Iq.ATT_TO, Iq.ATT_FROM, Iq.ATT_TYPE },
					new String[] { e.getAttribute(Iq.ATT_FROM),
							e.getAttribute(Iq.ATT_TO), Iq.T_RESULT });
			e.removeAllElements();
			XMPPClient.getInstance().sendPacket(answer);
			//System.out.println(new String(answer.toXml()));
		}
		Config cfg = Config.getInstance();
		String agString = cfg.getProperty(Config.ACCEPTED_GATEWAYS, "");
		byte[] agb = Utils.getBytesUtf8(agString);
		Element agEl = null;
		Element parsedAgb = null;
		try {
			parsedAgb = BProcessor.parse(agb);
		} catch (Exception ex) {

		}
		agEl = (agb != null && agb.length > 0 && parsedAgb != null ? parsedAgb
				: new Element("", "agb"));
		boolean accepted = false;
		for (int i = 0; i < agEl.getChildren().length; i++) {
			if (agEl.getChildren()[i].getText().equals(fromContact.jid.trim())) {
				accepted = true;
				break;
			}
		}
		if (accepted) {
			sScreen.itemAction(sScreen.accept);
		} else if (rosterModified) {
			// if the SubscribeScreen has been modified
			// show it otherwise forget it: it had nothing to show
			UICanvas.getInstance().open(sScreen, true);
		}
	}

	public void playSmartTone() {

		boolean shown = UICanvas.getInstance().getCurrentScreen() == RosterScreen
				.getInstance();

		boolean vibrate = (shown && play_flags[1])
				|| ((!shown) && play_flags[0]);
		boolean play = (shown && play_flags[3]) || ((!shown) && play_flags[2]);

		if (vibrate) {
			// #ifdef UI
			LampiroMidlet.disp.vibrate(200);
			// #endif
// #ifndef UI
			                                             LampiroMidlet.disp.vibrate(200);
			// #endif
		}

		if (play) {
			try {
				Manager.playTone(40, 100, volume);
				Manager.playTone(50, 100, volume);
				Manager.playTone(30, 100, volume);
			} catch (MediaException e1) {

			}
		}

	}

	public void askSubscription(Contact u) {
		SubscribeScreen scs = SubscribeScreen.getUserSubscription();
		scs.addSubscription(u, SubscribeScreen.ADD);
		UICanvas.getInstance().open(scs, true);
	}

	public void handleCommand(Contact c, String chosenResource) {
		CommandListScreen cmdscr = new CommandListScreen(c, chosenResource);
		UICanvas.getInstance().open(cmdscr, true);
	}

	public void connectionLost() {
		removeAllContacts();
		this.askRepaint();
		UICanvas.getInstance().open(RegisterScreen.getInstance(), true);
		UICanvas.getInstance().close(this);
	}

	public void showAlert(AlertType type, String title, String text,
			Object next_screen) {
		if (next_screen != null) {
			UICanvas.getInstance().open((UIScreen) next_screen, true);
		}
		UICanvas.showAlert(type, title, text);
	}

	public void handleTask(Task task, boolean display) {
		Displayable cur = LampiroMidlet.disp.getCurrent();

		// Display a task only if no other task is currently displayed
		Class klass = cur.getClass();
		if (display && !DataFormScreen.class.equals(klass)
				&& !DataResultScreen.class.equals(klass)) {
			// #ifdef UI
			task.display();
			// #endif
// #ifndef UI
			                                             task.display(LampiroMidlet.disp, cur);
			// #endif
		}
	}

	public void setWaitingDF(WaitScreen dataFormScreen) {
		this.dataformWaitingScreen = dataFormScreen;
	}

	public Object handleDataForm(DataForm df, byte type, DataFormListener dfl,
			int cmds) {
		UIScreen scr = null;
		if (type == Task.CMD_INPUT) {
			scr = new DataFormScreen(df, dfl);
		} else if (type == Task.CMD_FINISHED) {
			scr = new DataResultScreen(df, dfl);
		}
		if (cmds > 0) ((DataFormScreen) scr).setActions(cmds);
		closeWaitingScreen();
		UICanvas.getInstance().open(scr, true);
		return scr;
	}

	private void closeWaitingScreen() {
		WaitScreen oldWatingScreen = dataformWaitingScreen;
		if (oldWatingScreen != null) {
			oldWatingScreen.stopWaiting();
			this.dataformWaitingScreen = null;
		}
	}

	public void commandExecuted(Object screenToClose) {
		//		if (screenToClose != null) {
		//			UICanvas.getInstance().close((UIScreen) screenToClose);
		//		}
		//		UICanvas.getInstance().show(RosterScreen.getInstance());
	}

	public void showCommand(Object screen) {
		UICanvas.getInstance().open((UIScreen) screen, true);
	}


	public boolean isMicOn() {
		return micOn;
	}

	public boolean isCameraOn() {
		return cameraOn;
	}

	public void captureMedia(final String fullJid, final int mmType) {
		this.setFreezed(true);
		Thread t = new Thread() {
			public void run() {
				MMScreen tempCanvas = new MMScreen(fullJid);
				Display.getDisplay(LampiroMidlet._lampiro).setCurrent(
						tempCanvas);
				if (mmType == Config.audioType) {
					tempCanvas.showAudio();
				} else {
					tempCanvas.showCamera();
				}
			}
		};
		t.start();
	}

	static Vector getOrderedContacts() {
		Roster roster = XMPPClient.getInstance().getRoster();
		Vector tempOrderedContacts = new Vector(roster.contacts.size());
		for (Enumeration en = roster.contacts.elements(); en.hasMoreElements();) {
			Contact c = (Contact) en.nextElement();
			if (c.isVisible() && c instanceof MUC == false) {
				Enumeration en2 = tempOrderedContacts.elements();
				int index = 0;
				while (en2.hasMoreElements()) {
					if (Utils.compareTo((Contact) en2.nextElement(), c)) index++;
				}
				tempOrderedContacts.insertElementAt(c, index);
			}
		}
		return tempOrderedContacts;
	}

	public void rosterRetrieved() {
		String localServer = Contact.domain(XMPPClient.getInstance().my_jid);
		getIMGateways(localServer);
		if (localServer.equals(Config.BLUENDO_SERVER) == false) {
			getIMGateways(Config.BLUENDO_SERVER);
		}
	}

	/*
	 *  A helper used to know if the Screen can be right/left scrolled
	 */
	public static boolean makeRoll(int kc, UIScreen screen) {
		if (screen.getPopupList().size() > 0
				|| (screen.getMenu() != null && screen.getMenu()
						.isOpenedState() == true)) { return false; }

		int ga = UICanvas.getInstance().getGameAction(kc);
		switch (ga) {
			case Canvas.RIGHT: {
				showNextScreen(screen);
				return true;
			}
			case Canvas.LEFT: {
				showPreviousScreen(screen);
				return true;
			}

			default: {
				break;
			}
		}
		return false;
	}

}
