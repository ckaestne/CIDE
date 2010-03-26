/**
 * 
 */
package lampiro.screens;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import it.yup.ui.UIButton;
import it.yup.ui.UICanvas;
import it.yup.ui.UICheckbox;
import it.yup.ui.UIConfig;
import it.yup.ui.UIHLayout;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UILayout;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.ui.UISeparator;
import it.yup.ui.UIUtils;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xml.BProcessor;
import it.yup.xml.Element;
import it.yup.xmpp.Config;
import it.yup.xmpp.Contact;
import it.yup.xmpp.Roster;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Presence;
import it.yup.xmpp.packets.Stanza;

/**
 * @author luca
 *
 */
public class SubscribeScreen extends UIScreen {

	public static final int ADD = 0;
	public static final int DELETE = 1;
	public static final int MODIFY = 2;

	private UIPanel subscribePanel;

	static ResourceManager rm = ResourceManager.getManager("common", "en");

	public UIButton accept = new UIButton(rm.getString(ResourceIDs.STR_ACCEPT));

	private UIButton acceptAlways = new UIButton(rm
			.getString(ResourceIDs.STR_ACCEPT_ALWAYS));

	private UIButton close = new UIButton(rm.getString(ResourceIDs.STR_CLOSE));

	private UILabel sub_text = new UILabel("");

	private String fromContact = null;

	UIHLayout acceptLayout = null;

	private SubscribeScreen(Contact fromContact) {
		this();
		this.fromContact = fromContact.jid;
		this.sub_text.setText(fromContact.getPrintableName() + " "
				+ rm.getString(ResourceIDs.STR_SUBSCRIPTION_REQUEST_FROM));
		componentSubscriptionsScreen.put(this.fromContact, this);

		// the acceptLayout is not always seen (e.g.) when only one contact is shown
		int acceptIndex = subscribePanel.getItems().indexOf(sub_text);
		UIHLayout newAcceptLayout = null;
		newAcceptLayout = UIUtils.easyCenterLayout(acceptAlways, 130);
		acceptAlways.setFont(UIConfig.small_font);
		subscribePanel.insertItemAt(newAcceptLayout, acceptIndex + 1);
	}

	/**
	 * 
	 */
	private SubscribeScreen() {
		sub_text.setWrappable(true, UICanvas.getInstance().getWidth() - 10);
		//		UIMenu menu = new UIMenu("");
		//		menu.append(cmd_yes);
		//		setMenu(menu);
		setTitle(rm.getString(ResourceIDs.STR_SUBSCRIPTION_CONFIRM));
		subscribePanel = new UIPanel();
		subscribePanel.setMaxHeight(-1);
		subscribePanel.setModal(true);
		this.append(subscribePanel);
		this.sub_text.setText(rm
				.getString(ResourceIDs.STR_SUBSCRIPTION_REQUEST));
		subscribePanel.addItem(this.sub_text);

		acceptLayout = new UIHLayout(2);
		acceptLayout.setGroup(false);
		acceptLayout.insert(accept, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);
		acceptLayout.insert(close, 1, 50, UILayout.CONSTRAINT_PERCENTUAL);

		close.setImg(UICanvas.getUIImage("/icons/contact_delete.png"));
		//acceptLayout.setSelectedItem(acceptAll);
		accept.setImg(UICanvas.getUIImage("/icons/contact_add_all.png"));
		accept.setFont(UIConfig.small_font);
		acceptAlways.setImg(UICanvas
				.getUIImage("/icons/contact_add_always.png"));
		//subscribePanel.addItem(acceptLayout);
		accept.setFocusable(true);
		UISeparator sep = new UISeparator(2);
		sep.setFg_color(0xCCCCCC);
		subscribePanel.addItem(sep);
		subscribePanel.addItem(acceptLayout);
		subscribePanel.setSelected(true);
		this.setSelectedIndex(0);
		//subscribePanel.setSelectedIndex(1);
	}

	private Hashtable subscriptions = new Hashtable(5);

	/*
	 * add a subscription request
	 * XXX this should be synchronized I think
	 */
	public boolean addSubscription(Contact c, int action) {
		synchronized (this.subscriptions) {
			// first check if the contact is already online
			Enumeration en = this.subscriptions.keys();
			while (en.hasMoreElements()) {
				UILabel selLabel = (UILabel) en.nextElement();
				Object[] objects = (Object[]) this.subscriptions.get(selLabel);
				Contact ithC = (Contact) objects[0];
				if (Contact.userhost(ithC.jid).compareTo(c.jid) == 0) return false;
			}
			// then insert it
			String upAction = "";
			if (action == SubscribeScreen.ADD) {
				upAction = rm.getString(ResourceIDs.STR_ADD_CONTACT);
			}
			if (action == SubscribeScreen.DELETE) {
				upAction = rm.getString(ResourceIDs.STR_DELETE_CONTACT);
			}
			UICheckbox ithSubscription = new UICheckbox(upAction + " "
					+ c.getPrintableName() + "?");
			ithSubscription.setChecked(true);
			ithSubscription.setWrappable(true, UICanvas.getInstance()
					.getWidth() - 20);
			subscriptions.put(ithSubscription, new Object[] { c,
					new Integer(action) });
			this.subscribePanel.insertItemAt(ithSubscription,
					this.subscribePanel.getItems().size() - 2);
		}
		return true;
	}

	public void itemAction(UIItem cmd) {
		if (cmd == this.close) {
			// so that the user preferred resource is reset
			SubscribeScreen.releaseScreen(this);
			UICanvas.getInstance().close(this);
		} else if (cmd == accept) {
			// XXX this could have serious synch problems
			synchronized (this.subscriptions) {
				Enumeration en = this.subscriptions.keys();
				Roster roster = XMPPClient.getInstance().getRoster();
				while (en.hasMoreElements()) {
					UICheckbox selLabel = (UICheckbox) en.nextElement();
					Object objects[] = (Object[]) this.subscriptions
							.get(selLabel);
					Contact c = (Contact) objects[0];
					int action = ((Integer) objects[1]).intValue();

					if (selLabel.isChecked()) {
						if (action == SubscribeScreen.ADD) roster
								.subscribeContact(c, true);
						else if (action == SubscribeScreen.DELETE) roster
								.unsubscribeContact(c);
						this.subscriptions.remove(selLabel);
					} else {
						// negate presence subscription
						Presence pmsg = new Presence();
						pmsg.setAttribute(Stanza.ATT_TO, c.jid);
						pmsg.setAttribute(Stanza.ATT_TYPE,
								Presence.T_UNSUBSCRIBED);
						XMPPClient.getInstance().sendPacket(pmsg);

						this.subscribePanel.removeItem((UIItem) selLabel
								.getContainer());
						this.subscriptions.remove(selLabel);
					}
				}
			}
			this.itemAction(this.close);
		} else if (cmd == acceptAlways) {
			this.itemAction(accept);
			Config cfg = Config.getInstance();
			String acceptedGateways = cfg.getProperty(Config.ACCEPTED_GATEWAYS,
					"");
			byte[] agb = Utils.getBytesUtf8(acceptedGateways);
			Element parsedAgb = null;
			Element agEl = null;
			if (agb != null && agb.length > 0) {
				try {
					parsedAgb = BProcessor.parse(agb);
				} catch (Exception ex) {
				}
			}
			if (parsedAgb == null) parsedAgb = new Element("", "agb");

			agEl = parsedAgb;

			// check if the gw is already autoaccepted
			boolean found = false;
			for (int i = 0; i < agEl.getChildren().length; i++) {
				if (agEl.getChildren()[i].getText().equals(fromContact)) {
					found = true;
					break;
				}
			}
			if (found == false) agEl.addElement(null, "agw").addText(
					this.fromContact);
			agb = BProcessor.toBinary(agEl);
			cfg.setProperty(Config.ACCEPTED_GATEWAYS, Utils.getStringUTF8(agb));
			cfg.saveToStorage();
		}
	}

	private static SubscribeScreen userSubscriptionScreen = null;
	private static Hashtable componentSubscriptionsScreen = new Hashtable();

	public synchronized static void releaseScreen(SubscribeScreen ss) {
		if (ss == SubscribeScreen.userSubscriptionScreen) {
			userSubscriptionScreen = null;
		} else {
			componentSubscriptionsScreen.remove(ss.fromContact);
		}
	}

	/*
	 * Returns a user subscription screen useful for "normal" contacts (not for gateway ones)
	 */
	public synchronized static SubscribeScreen getUserSubscription() {
		if (userSubscriptionScreen == null) userSubscriptionScreen = new SubscribeScreen();
		return userSubscriptionScreen;
	}

	public synchronized static SubscribeScreen getComponentSubscription(
			Contact componentJid) {
		SubscribeScreen subscribeScreen = (SubscribeScreen) componentSubscriptionsScreen
				.get(componentJid.jid);
		if (subscribeScreen == null) subscribeScreen = new SubscribeScreen(
				componentJid);
		return subscribeScreen;
	}

}
