/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: MUCComposer.java 1597 2009-06-19 11:54:12Z luca $
*/

package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.xmpp.Contact;
import it.yup.xmpp.MUC;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Message;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;

public class MUCComposer extends SimpleComposerScreen {

	MUCComposer(MUC muc) {
		super(muc, null);
		preferredResource = muc.jid;
	}

	public void commandAction(Command cmd, Displayable d) {
		if (cmd == cmd_send) {
			
			this.removeCommand(cmd_send);
			
			String msgText = getString();
			Message msg = null;
			msg = new Message(Contact.userhost(preferredResource), Message.GROUPCHAT);
			msg.setBody(msgText);
			XMPPClient.getInstance().sendPacket(msg);
			UICanvas.display(null);
			UICanvas.getInstance().askRepaint(
					UICanvas.getInstance().getCurrentScreen());
		} else {
			super.commandAction(cmd, d);
		}
	}
}
