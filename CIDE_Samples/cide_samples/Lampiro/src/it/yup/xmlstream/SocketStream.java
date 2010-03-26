/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SocketStream.java 1577 2009-06-15 14:38:27Z luca $
*/

package it.yup.xmlstream;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import it.yup.transport.BaseChannel;
import it.yup.transport.SocketChannel;

// #mdebug

import it.yup.util.Logger;

// #enddebug

import it.yup.util.Utils;
import it.yup.xml.Element;
import it.yup.xml.KXmlParser;
import it.yup.xml.KXmlProcessor;
import it.yup.xmpp.Contact;

public class SocketStream extends BasicXmlStream implements Runnable {

	private KXmlParser parser = null;

	private SocketChannel channel = null;

	private int level;

	public void initialize(String jid, String password) {

		this.jid = jid;
		this.password = password;

		// Create an instance of the XML parser lasting as long as the stream
		parser = new KXmlParser();
		try {
			parser.setFeature(KXmlParser.FEATURE_PROCESS_NAMESPACES, true);
		} catch (XmlPullParserException e1) {
			// Can't happen
		}
	}

	protected void restart() {
		try {
			// parser.require(KXmlParser.START_DOCUMENT, null, null);
			// #debug			
			Logger.log("setting parser input");
			level = 0;
			parser.setInput(this.channel.getReader());
			parser.defineEntityReplacementText("quot", "\"");
			StringBuffer streamStart = new StringBuffer();
			streamStart.append("<?xml version=\"1.0\"?>\n");
			streamStart
					.append("<stream:stream xmlns:stream=\"http://etherx.jabber.org/streams\" version=\"1.0\" xmlns=\"jabber:client\" xml:lang=\"en\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"");
			streamStart.append((" to=\"" + Contact.domain(jid) + "\">"));
			// #debug			
			Logger.log("Sending stream start");

			// channel.sendContent(streamStart.toString().getBytes("utf-8"));
			channel.sendContent(Utils.getBytesUtf8(streamStart.toString()));

		} catch (XmlPullParserException e) {
			dispatchEvent(BasicXmlStream.STREAM_ERROR, null);
			// #mdebug
			Logger.log("[SocketStream::restart] XmlPullParserException: "
					+ e.getMessage());
			// #enddebug
		}
	}

	protected void tryToSend() {
		// simply pass the packets to the channel
		synchronized (this.sendQueue) {
			for (int i = 0; i < this.sendQueue.size(); i++)
				this.channel
						.sendContent(((Element) this.sendQueue.elementAt(i))
								.toXml());
			this.sendQueue.removeAllElements();
		}
	}

	public void connectionEstablished(BaseChannel connection) {
		// #debug		
		Logger.log("Connection established");
		this.channel = (SocketChannel) connection;
		dispatchEvent(BasicXmlStream.STREAM_CONNECTED, null);
		// #debug		
		Logger.log("restarting stream");
		restart();
		// #debug		
		Logger.log("starting reader");
		new Thread(this).start();
	}

	public void connectionFailed(BaseChannel connection) {
		dispatchEvent(BasicXmlStream.CONNECTION_FAILED, null);
	}

	public void connectionLost(BaseChannel connection) {
		synchronized (this.sendQueue) {
			// XXX: not the final answer: we must take into 
			// consideration that when switching through
			// multiple transports this is not feasible!!!
			this.sendQueue.removeAllElements();
		}
		dispatchEvent(BasicXmlStream.STREAM_TERMINATED, null);
		dispatchEvent(BasicXmlStream.CONNECTION_LOST, null);
	}

	public void run() {
		try {
			level = 0;
			parser.require(KXmlParser.START_DOCUMENT, null, null);
			while (true) {
				int token = parser.nextToken();
				//				logger.log("Got token: " + token + " level: " + level);
				if (token == KXmlParser.START_TAG) {
					level += 1;
					if (level == 1) {
						Element documentStart = KXmlProcessor
								.pullDocumentStart(parser);
						this.SID = documentStart.getAttribute("id");
					} else if (level == 2) {
						//						logger.log("pulling stanza");
						Element stanza = KXmlProcessor.pullElement(parser);
						level -= 1;
						// #debug						
						Logger.log("[RECV] " + new String(stanza.toXml()));

						promotePacket(stanza);
						if ("features".equals(stanza.name)) {
							processFeatures(stanza.getChildren());
						}
					}
				}
			}
		} catch (XmlPullParserException e) {
			// #debug			
			Logger.log(e.getMessage());
			this.channel.close();
			connectionLost(this.channel);
		} catch (IOException e) {
			// #mdebug			
			Logger.log(e.getMessage());
			e.printStackTrace();
			// #enddebug
			connectionLost(this.channel);
		} catch (Exception e) {
			// catch this to avoid a problem that cannot be catched outside
			try {
				this.channel.close();
				connectionLost(this.channel);
			} catch (Exception innerE) {
			}
			// #mdebug			
			Logger.log("Parser " + e.getClass().getName() + ":"
					+ e.getMessage());
			// #enddebug
		}

	}

	//	 #ifdef TLS
					protected void startTLS() throws IOException {
						channel.startTLS();
					}
					
	//	 #endif

	// #ifdef COMPRESSION
	protected void startCompression() {
		channel.startCompression();
	}
	// #endif

}
