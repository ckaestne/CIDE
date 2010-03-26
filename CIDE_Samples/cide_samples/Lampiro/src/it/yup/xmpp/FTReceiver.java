package it.yup.xmpp;

import org.bouncycastle.util.encoders.Base64;

//#mdebug
//@
//@import it.yup.util.Logger;
//@
// #enddebug

import it.yup.util.Utils;
import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.EventQueryRegistration;
import it.yup.xmlstream.PacketListener;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Stanza;

public class FTReceiver implements PacketListener {

	/*
	 * The xmpp client
	 */
	private XMPPClient xmppClient = XMPPClient.getInstance();

	/*
	 * A file transfer receiver event handler
	 */
	private FTREventHandler eh;

	public interface FTREventHandler {

		public void dataReceived(byte[] data, String fileName,String fileDesc, OpenListener ftrp);

		public void reqFT(String contactName, OpenListener ftrp);

		public void chunkReceived(int length, int fileSize,
				OpenListener openListener);
	}

	public class OpenListener implements PacketListener {
		public Element e_jingle;

		private int block_size = 4096;
		//private StringBuffer encodedData= new StringBuffer(block_size);
		private StringBuffer encodedData = new StringBuffer();
		private byte[] decodedData;

		private EventQueryRegistration dataListenerEq;

		// the file size
		public int fileSize;

		// the file size
		public String fileName;
		
		public String fileDesc="";
		
		public void answerFT( boolean accept) {
			// If accept send a correct reply and register a listener
			// to open the jingle stream
			if (accept) {
				this.acceptSession();
			} else {
				Iq reply =  new Iq(this.e_jingle.getAttribute(Iq.ATT_FROM),Iq.T_SET);
				Element jingle = reply.addElement(XMPPClient.JINGLE, FTSender.JINGLE);
				jingle.setAttribute(FTSender.ACTION, FTSender.SESSION_TERMINATE);
				jingle.addElement(null, FTSender.DECLINE);
				xmppClient.sendPacket(reply);
			}
		}

		public void packetReceived(Element e) {
			
			PacketListener dataListener = new PacketListener() {
				public void packetReceived(Element e) {
					try {
						String chunkData = e
								.getChildByName(null, FTSender.DATA).getText();
						Iq replIq = new Iq(e.getAttribute(Iq.ATT_FROM),
								Iq.T_RESULT);
						replIq.setAttribute(Iq.ATT_ID, e
								.getAttribute(Iq.ATT_ID));
						xmppClient.sendPacket(replIq);
						encodedData.append(chunkData);
						// the data is base64 encoded 
						eh.chunkReceived ((encodedData.length()*3)/4,fileSize,OpenListener.this);
					} catch (Exception ex) {
						// #mdebug
//@						ex.printStackTrace();
//@						Logger.log("In receiving an IBB packet"
//@								+ ex.getClass().getName() + "\n"
//@								+ ex.getMessage());
						// #enddebug
					}
				}
			};

			PacketListener closeListener = new PacketListener() {
				public void packetReceived(Element e) {
					try {
						BasicXmlStream.removeEventListener(dataListenerEq);
						Iq reply = Utils.easyReply(e);
						xmppClient.sendPacket(reply);
						Iq closeSession = new Iq(e.getAttribute(Iq.ATT_FROM),
								Iq.T_SET);
						Element jingleClose = closeSession.addElement(
								XMPPClient.JINGLE, FTSender.JINGLE);
						jingleClose.setAttribute(FTSender.ACTION,
								FTSender.SESSION_TERMINATE);
						jingleClose.setAttribute(FTSender.SID, e_jingle.getChildByName(null, FTSender.JINGLE)
								.getAttribute(FTSender.SID));
						jingleClose.addElement(null, "reason").addElement(null,
								"success");
						xmppClient.sendPacket(closeSession);
						String decString = encodedData.toString();
						decodedData = Base64.decode(decString);
						// #mdebug 
//@						Logger.log("File received kb: " + decodedData.length);
//@						// System.out.println(decString);
						// #enddebug
						eh.dataReceived(decodedData, fileName,fileDesc, OpenListener.this);
					} catch (Exception ex) {
						// #mdebug
//@						ex.printStackTrace();
//@						Logger.log("In closing session"
//@								+ ex.getClass().getName() + "\n"
//@								+ ex.getMessage());
						// #enddebug
					}
				}
			};
			//this.encodedData.setLength(fileSize*2);

			EventQuery eq = new EventQuery(Iq.IQ, new String[] { Iq.ATT_FROM,
					Iq.ATT_TYPE }, new String[] { e.getAttribute(Iq.ATT_FROM),
					Iq.T_SET });
			Element openElement = e.getChildByName(null, FTSender.OPEN);
			eq.child = new EventQuery(FTSender.DATA, new String[] {
					FTSender.SID, "xmlns" }, new String[] {
					openElement.getAttribute(FTSender.SID), XMPPClient.NS_IBB });
			this.dataListenerEq = BasicXmlStream.addEventListener(eq,
					dataListener);

			eq = new EventQuery(Iq.IQ,
					new String[] { Iq.ATT_FROM, Iq.ATT_TYPE }, new String[] {
							e.getAttribute(Iq.ATT_FROM), Iq.T_SET });
			eq.child = new EventQuery(FTSender.CLOSE, new String[] {
					FTSender.SID, "xmlns" }, new String[] {
					openElement.getAttribute(FTSender.SID), XMPPClient.NS_IBB });
			BasicXmlStream.addOnetimeEventListener(eq, closeListener);

			block_size = Integer.parseInt(openElement
					.getAttribute(FTSender.BLOCK_SIZE));

			Stanza reply = new Iq(e.getAttribute(Iq.ATT_FROM), Iq.T_RESULT);
			reply.setAttribute(Iq.ATT_ID, e.getAttribute(Iq.ATT_ID));
			xmppClient.sendPacket(reply);
		}
		
		private void acceptSession() {
			
			Element e = this.e_jingle;
			Element session_accept = new Iq(e.getAttribute(Iq.ATT_FROM),
					Iq.T_SET);
			Element jingle = this.e_jingle
					.getChildByName(null, FTSender.JINGLE);
			jingle.setAttribute(FTSender.ACTION, FTSender.SESSION_ACCEPT);
			session_accept.addElement(jingle);
			xmppClient.sendPacket(session_accept);
		}
	};

	public FTReceiver(FTREventHandler eh) {
		this.eh = eh;
		EventQuery eq = new EventQuery(Iq.IQ, new String[] { Iq.ATT_TYPE },
				new String[] { Iq.T_SET });
		eq.child = new EventQuery(FTSender.JINGLE, new String[] { "xmlns",
				FTSender.ACTION }, new String[] { XMPPClient.JINGLE,
				FTSender.SESSION_INITIATE });
		BasicXmlStream.addEventListener(eq, this);
	}

	public void packetReceived(Element e) {
		// file transfer receive protocol
		OpenListener ftrp = new OpenListener();
		ftrp.e_jingle = e;
		Element fileNode = e.getChildByName(null, FTSender.JINGLE)
				.getChildByName(null, FTSender.CONTENT).getChildByName(null,
						FTSender.DESCRIPTION).getChildByName(null,
						FTSender.OFFER).getChildByName(null, FTSender.FILE);
		ftrp.fileSize = Integer.parseInt(fileNode.getAttribute(FTSender.SIZE));
		ftrp.fileName = fileNode.getAttribute(FTSender.NAME);
		Element desc = fileNode.getChildByName(null, FTSender.DESC);
		if (desc!=null)
			ftrp.fileDesc=desc.getText();
		Stanza reply = Utils.easyReply(e);
		xmppClient.sendPacket(reply);
		
		EventQuery eq = new EventQuery(Iq.IQ, new String[] { Iq.ATT_FROM,
				Iq.ATT_TYPE }, new String[] { e.getAttribute(Iq.ATT_FROM),
				Iq.T_SET });
		Element jingle = e.getChildByName(null, FTSender.JINGLE);
		Element content = jingle.getChildByName(null, FTSender.CONTENT);
		Element transport = content.getChildByName(null, FTSender.TRANSPORT);
		eq.child = new EventQuery(FTSender.OPEN, new String[] { FTSender.SID },
				new String[] { transport.getAttribute(FTSender.SID) });
		BasicXmlStream.addOnetimeEventListener(eq, ftrp);
		
		eh.reqFT(e.getAttribute(Iq.ATT_FROM), ftrp);
	}
}
