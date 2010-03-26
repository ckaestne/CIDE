/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: MessageComposerScreen.java 1564 2009-06-09 14:17:08Z luca $
*/

package lampiro.screens;

import it.yup.ui.UIButton;
import it.yup.ui.UICombobox;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIScreen;
import it.yup.ui.UITextField;
import it.yup.ui.UIUtils;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Stanza;

import javax.microedition.lcdui.TextField;

public class MessageComposerScreen extends UIScreen {

	protected static ResourceManager rm = ResourceManager.getManager("common", "en");
	private Contact user = null;
    private int mtype;
	protected UICombobox cg_type = new UICombobox("Type", false);
	protected UITextField tf_subject = new UITextField("Subject", "", 100, TextField.ANY);
	protected UITextField tf_body = new UITextField("Message", "", 1000, TextField.ANY);
	private UIButton btn_send = new UIButton("Send"); 
	
	protected UILabel cmd_send = new UILabel(rm.getString(ResourceIDs.STR_SEND).toUpperCase());
	private UILabel cmd_cancel = new UILabel(rm.getString(ResourceIDs.STR_CANCEL).toUpperCase());
	
	public static final int MESSAGE = 0;
	public static final int CHAT = 1;
	
	private String preferredResource = null;
	
    /**
     * Builds a composer screen associated with the given Contact and for the 
     * given message type (message/chat).
     */
	public MessageComposerScreen(Contact user, String preferredResource, int default_type ) {
		setTitle(rm.getString(ResourceIDs.STR_MESSAGE_TO) + " " + user.getPrintableName());
		this.user = user;
		this.preferredResource = preferredResource;
		cg_type.append("message");
		cg_type.append("chat");
		cg_type.setSelectedIndex(default_type);
		append(cg_type);
		if(default_type == MessageComposerScreen.MESSAGE) {
			append(tf_subject);
		}
		append(tf_body);
		tf_body.setWrappable(true);
		tf_body.setMaxHeight(60);
		mtype = default_type;
        
		append(UIUtils.easyCenterLayout(btn_send,75));

		setMenu(new UIMenu(""));
        UIMenu menu = getMenu();
		menu.append(cmd_send);
        menu.append(cmd_cancel);
	}

    /*
     * (non-Javadoc)
     * @see it.yup.ui.UIScreen#menuAction(it.yup.ui.UIMenu, it.yup.ui.UIItem)
     */
	public void menuAction(UIMenu menu, UIItem cmd) {
		if(cmd == cmd_send) {
			Message msg;
			String to= preferredResource != null ? preferredResource : user.jid;
			if(cg_type.getSelectedIndex() == 0) {
				msg = new Message(to, null);
				String subject = tf_subject.getText();
				if(subject != null && !"".equals(subject)) {
					msg.addElement(Stanza.NS_JABBER_CLIENT, Message.SUBJECT).addText(subject);
				}
			} else {
				msg = new Message(to, "chat");
			}
			
			String body = tf_body.getText();
			if(body == null) body = "";
			msg.setBody(body);
            XMPPClient.getInstance().sendPacket(msg);
            user.addMessageToHistory(preferredResource,msg);
            RosterScreen.closeAndOpenRoster(this);
		} else if(cmd == cmd_cancel) {
			RosterScreen.closeAndOpenRoster(this);
		}
	}

    /*
     * (non-Javadoc)
     * @see it.yup.ui.UIScreen#itemAction(it.yup.ui.UIItem)
     */
    public void itemAction(UIItem item) {
        if(item == btn_send) {
            menuAction(null, cmd_send);
        } else if(item == cg_type) {
            if(mtype == 1 && cg_type.getSelectedIndex() == 0) {
                insert(1, tf_subject);
            } else if(mtype == 0 && cg_type.getSelectedIndex() == 1) {
                remove(1);
            }
            mtype = cg_type.getSelectedIndex();
        }
    }
    
}
