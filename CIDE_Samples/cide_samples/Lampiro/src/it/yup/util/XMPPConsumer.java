/**
 * 
 */
package it.yup.util;

import java.util.Vector;

import it.yup.xml.Element;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Message;

/**
 * @author luca
 *
 */
public class XMPPConsumer extends Thread implements LogConsumer {

	public String debugJid = "helpMePlease@jabber.bluendo.com";

	private static XMPPConsumer consumer = null;

	private Vector messages = new Vector();
	private Vector outGoingMessages = new Vector();

	private boolean active = false;

	public static XMPPConsumer getConsumer() {
		if (consumer == null) {
			consumer = new XMPPConsumer();
		}
		return consumer;
	};

	/**
	 * 
	 */
	public XMPPConsumer() {
		active = true;
		start();
	}

	public void run() {

		while (active) {
			XMPPClient xmppClient = XMPPClient.getInstance();

			synchronized (messages) {
				try {
					messages.wait();
					while (messages.size() > 0) {
						String message = (String) messages.elementAt(0);
						// avoid sending XMPP traffic for infinite recursion
						// and useless date lake presencehandler
						if (message.startsWith("[SEND]")
								|| message.startsWith("[RECV]")
								|| message.startsWith("Sender: waiting")
								|| message.startsWith("PresenceHandler"))
						{
							;
						} else {
							Message msg = null;
							msg = new Message(debugJid, "chat");
							msg.setBody(message);
							outGoingMessages.addElement(msg);
						}
						messages.removeElementAt(0);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			while (outGoingMessages.size() > 0) {
				if (xmppClient != null && xmppClient.my_jid != null) {
					try {
						xmppClient.sendPacket((Element) outGoingMessages
								.elementAt(0));
						outGoingMessages.removeElementAt(0);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				} else
					break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see it.yup.util.LogConsumer#gotMessage(java.lang.String, int)
	 * 
	 */
	public void gotMessage(String message, int level) {
		synchronized (messages) {
			if (active) {
				messages.addElement(message);
				messages.notify();
			}
		}
	}

	/* (non-Javadoc)
	 * @see it.yup.util.LogConsumer#setExiting()
	 */
	public void setExiting() {
		this.active = false;

	}

}
