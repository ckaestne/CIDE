/**
 * 
 */
package lampiro.screens;

import java.util.Enumeration;

import lampiro.screens.RosterScreen.UIContact;
import lampiro.screens.RosterScreen.UIGroup;
import it.yup.ui.UIItem;
import it.yup.ui.UIMenu;
import it.yup.util.ResourceIDs;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Stanza;

/**
 * @author luca
 *
 */
public class GrpMessageComposerScreen extends MessageComposerScreen {

	/*
	 * The group to send the message to 
	 */
	private UIGroup uiGroup;

	/**
	 * @param user
	 * @param preferredResource
	 * @param default_type
	 */
	public GrpMessageComposerScreen(UIGroup group,
			int default_type) {
		super(XMPPClient.getInstance().getMyContact(), "", default_type);
		this.uiGroup = group;
		setTitle(rm.getString(ResourceIDs.STR_MESSAGE_TO) + " " + group.name);
	}
	
	public void menuAction(UIMenu menu, UIItem cmd) {
		if(cmd == cmd_send) {
			Message msg;
			Enumeration en = this.uiGroup.contacts.elements();
			String body = tf_body.getText();
			if(body == null) body = "";
			XMPPClient xmppClient = XMPPClient.getInstance();
			while (en.hasMoreElements()) {
				UIContact ithUIContact = (UIContact) en.nextElement();
				Contact ithContact = ithUIContact.c;
				
				String to= ithContact.jid;
				if(cg_type.getSelectedIndex() == 0) {
					msg = new Message(to, null);
					String subject = tf_subject.getText();
					if(subject != null && !"".equals(subject)) {
						msg.addElement(Stanza.NS_JABBER_CLIENT, Message.SUBJECT).addText(subject);
					}
				} else {
					msg = new Message(to, "chat");
				}
				
				msg.setBody(body);
	            xmppClient.sendPacket(msg);
	            ithContact.addMessageToHistory(null,msg);
			}
			RosterScreen.closeAndOpenRoster(this);
		} else  {
            super.menuAction(menu, cmd);
		}
	}


}
