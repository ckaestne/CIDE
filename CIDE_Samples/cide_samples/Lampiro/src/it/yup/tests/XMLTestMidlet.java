/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: TestMidlet.java 1215 2009-02-26 09:40:59Z luca $
*/

package it.yup.tests;

import it.yup.util.Utils;
import it.yup.xml.BProcessor;
import it.yup.xml.Element;
import it.yup.xml.KXmlParser;
import it.yup.xml.KXmlProcessor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;

// #mdebug
//@import it.yup.util.Logger;
//@import it.yup.util.StderrConsumer;
// #enddebug

import org.xmlpull.v1.XmlPullParserException;

/**
 * YUP Main midlet
 */
public class XMLTestMidlet extends MIDlet {

	/** The main display */
	public static Display disp;

	/** The midlet instance */
	public static XMLTestMidlet yup;

	private Form form = new Form("Test midlet");
	public StringItem log = new StringItem("Bytes", "offline");

	public XMLTestMidlet() {

		// #debug		
//@				Logger.addConsumer(new StderrConsumer());

		disp = Display.getDisplay(this);
		form.append(log);
		yup = this;

	}

	public void startApp() {
		disp.setCurrent(form);
		//testPerformances();

		testEnDecode();
	}

	private void testEnDecode() {
//		String inputPacket = "<doc xmlns=\"jabber:client\">\n";
//		inputPacket += "<stream:stream xmlns:stream=\"http://etherx.jabber.org/streams\" version=\"1.0\" xmlns=\"jabber:client\" to=\"jabber.bluendo.com\" xml:lang=\"en\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\" />\n";
//		inputPacket += "</doc>";

		//String inputPacket = "";
		//inputPacket += "<stream:stream xmlns:stream=\"http://etherx.jabber.org/streams\" version=\"1.0\" xmlns=\"jabber:client\" to=\"jabber.bluendo.com\" xml:lang=\"en\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\" />\n";

		String inputPacket = "<cap xmlns='http://jabber.org/protocol/disco#info'><feature var='http://jabber.org/protocol/bytestreams'></feature><feature var='http://jabber.org/protocol/si'></feature><feature var='http://jabber.org/protocol/si/profile/file-transfer'></feature><feature var='http://jabber.org/protocol/disco#info'></feature><feature var='http://jabber.org/protocol/commands'></feature><feature var='http://jabber.org/protocol/rosterx'></feature><feature var='http://jabber.org/protocol/muc'></feature><feature var='jabber:x:data'></feature></cap>";
		
		Element packet = null;
		KXmlParser parser = new KXmlParser();
		try {
			parser.setFeature(KXmlParser.FEATURE_PROCESS_NAMESPACES, true);
			parser.setInput(new InputStreamReader(new ByteArrayInputStream(
					Utils.getBytesUtf8(inputPacket))));
			packet = KXmlProcessor.parseDocument(parser);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] encodedPacket = BProcessor.toBinary(packet);
		Element decodedPacket = BProcessor.parse(encodedPacket);
		String outputPacket = Utils.getStringUTF8(decodedPacket.toXml());

	}

	private void testPerformances() {
		try {
			// If no exception is thrown, then the URI is valid, but the file may or may not exist.
			this.log.setText("start:" + System.currentTimeMillis());
			InputStream is = this.getClass().getResourceAsStream("/stream.xml");
			KXmlParser parser = new KXmlParser();
			Element decodedPacket = null;
			try {
				parser.setInput(new InputStreamReader(is));

				int level = 0;
				Element el = null;
				while (true) {
					try {
						int token = parser.nextToken();
						//				logger.log("Got token: " + token + " level: " + level);
						if (token == KXmlParser.START_TAG) {
							level += 1;
							if (level == 1) {
								Element documentStart = KXmlProcessor
										.pullDocumentStart(parser);
							} else if (level == 2) {
								//						logger.log("pulling stanza");
								el = KXmlProcessor.pullElement(parser);
								level -= 1;
							}
						} else if (token == KXmlParser.END_DOCUMENT) {
							break;
						}
					} catch (Exception e) {

					}
				}

				parser.setFeature(KXmlParser.FEATURE_PROCESS_NAMESPACES, true);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.log.setText(log.getText() + "\\tdecoded:"
					+ System.currentTimeMillis());
			//decodedPacket.toXml();
			//this.log.setText(log.getText() + "\\tencoded:"
			//		+ System.currentTimeMillis());

		} catch (Exception ioe) {
		}
	}

	protected void destroyApp(boolean param) {

	}

	protected void pauseApp() {
	}

	public void exit() {
		destroyApp(false);
		notifyDestroyed();
	}

	//	public void abort(String string, Exception e) {
	//		// XXX: notify the user?
	//	}
}
