/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: XMPPTestClient.java 1220 2009-02-27 09:41:06Z luca $
*/

package it.yup.tests;

import it.yup.transport.BaseChannel;
import it.yup.transport.SocketChannel;
import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.PacketListener;
import it.yup.xmlstream.SocketStream;
import it.yup.xmlstream.StreamEventListener;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Presence;

public class XMPPTestClient {

	BasicXmlStream stream;
	BaseChannel channel;

	class Listener implements StreamEventListener {

		public void gotStreamEvent(String event, Object source) {
			if (BasicXmlStream.STREAM_INITIALIZED.equals(event)) {
				int[] bytes = XMPPClient.getTraffic();
				TestMidlet.yup.log.setText("online, bytes: " + bytes[0] + "/"
						+ bytes[1]);
				Presence p = new Presence();
				p.setShow(Presence.SHOW_DND);
				p.setStatus("Mobile test, don't send me messages!");
				stream.send(p, -1);
			}
		}
	}

	Listener listener = new Listener();

	class Echoer implements PacketListener {

		public void packetReceived(Element e) {
			Message m = new Message(e);
			//int[] bytes = XMPPClient.getTraffic();
			//TestMidlet.yup.log.setText("echoed " + m.getBody()+ ", bytes: "+ bytes[0] + "/" + bytes[1]);
			Message reply = new Message(m.getAttribute("from"), m
					.getAttribute("type"));
			reply.setBody(m.getBody());
			stream.send(reply, -1);
		}

	}

	public void startClient() {
		stream = new SocketStream();
		channel = new SocketChannel("socket://jabber.bluendo.com:5222", stream);

		EventQuery qAuth = new EventQuery(EventQuery.ANY_EVENT, null, null);
		stream.addEventListener(qAuth, listener);

		EventQuery qMessage = new EventQuery("message", null, null);
		stream.addEventListener(qMessage, new Echoer());

		stream.initialize("test_ff@jabber.bluendo.com/pippa", "test_ff");
		channel.open();

	}
}
