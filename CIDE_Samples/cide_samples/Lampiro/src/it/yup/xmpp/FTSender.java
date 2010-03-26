package it.yup.xmpp;

import java.util.Vector;

import org.bouncycastle.util.encoders.Base64;

import it.yup.util.Utils;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Presence;
import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.PacketListener;

public class FTSender {

	/*
	 * Initiate a session with jingle
	 */
	public static String SESSION_INITIATE = "session-initiate";

	/*
	 * Initiate a session with jingle
	 */
	public static String SESSION_ACCEPT = "session-accept";

	/*
	 * Initiate a session with jingle
	 */
	public static String SESSION_TERMINATE = "session-terminate";

	/*
	 * Jingle related action
	 */
	public static String ACTION = "action";

	/*
	 * Jingle related DATA
	 */
	public static String DATA = "data";

	/*
	 * Jingle related jingle
	 */
	public static String JINGLE = "jingle";

	/*
	 * Jingle related jingle
	 */
	public static String DECLINE = "decline";

	/*
	 * Jingle related action
	 */
	public static String CONTENT = "content";

	/*
	 * Jingle related description
	 */
	public static String DESCRIPTION = "description";

	/*
	 * Jingle related description
	 */
	public static String DESC = "desc";

	/*
	 * Jingle related description
	 */
	public static String OFFER = "offer";

	/*
	 * Jingle related name
	 */
	public static String NAME = "name";

	/*
	 * Jingle related name
	 */
	public static String BLOCK_SIZE = "block-size";

	/*
	 * Jingle related creator
	 */
	public static String CREATOR = "creator";

	/*
	 * Jingle related initiator
	 */
	public static String INITIATOR = "initiator";

	/*
	 * Jingle related SID
	 */
	public static String SID = "sid";

	/*
	 * IBB related Seq
	 */
	public static String SEQ = "seq";

	/*
	 * Jingle related file
	 */
	public static String FILE = "file";

	/*
	 * Jingle related file
	 */
	public static String OPEN = "open";

	/*
	 * Jingle related file
	 */
	public static String CLOSE = "close";

	/*
	 * Jingle related file
	 */
	public static String TRANSPORT = "transport";

	/*
	 * Jingle related size
	 */
	public static String SIZE = "size";

	/*
	 * The name of the file to transfer
	 */
	public String fileName = "";

	/*
	 * The xmpp client
	 */
	private XMPPClient xmppClient = XMPPClient.getInstance();

	/*
	 * The data bytes of the file 
	 */
	private byte[] fileData = null;

	/*
	 * the chunk length
	 */
	private int chuck_length = 4096;

	/*
	 * The receiver of the file
	 */
	private String to = null;

	/*
	 * The description associated to the file
	 */
	private String desc = null;

	/*
	 * The Session id related to this section
	 */
	private String jingleSid = "";

	/*
	 * The Session id related to this section
	 */
	private String transportSid = "";

	/*
	 * The session must be opened and the client must have sent session acept
	 * before sending file
	 */
	private boolean sessionOpened = false;

	private FTSEventHandler eh;

	public static boolean supportFT(String fullJid) {
		Contact c = XMPPClient.getInstance().getRoster().getContactByJid(
				fullJid);
		if (c == null) return false;
		Presence p = c.getPresence(fullJid);
		if (p == null) return false;
		Element caps = c.getCapabilities(p);
		return supportFT(caps);
	}

	public static boolean supportFT(Element caps) {
		if (caps == null) return false;
		Element[] features = caps.getChildrenByName(null, "feature");
		Vector vars = new Vector(features.length);
		for (int i = 0; i < features.length; i++) {
			Element ithFeature = features[i];
			vars.addElement(ithFeature.getAttribute("var"));
		}
		if (vars.contains(XMPPClient.JINGLE) == false
				|| vars.contains(XMPPClient.JINGLE_FILE_TRANSFER) == false
				|| vars.contains(XMPPClient.JINGLE_IBB_TRANSPORT) == false ) return false;
		return true;
	}

	public interface FTSEventHandler {

		public void fileAcceptance(Contact c, String fileName, boolean accept, FTSender sender);

		public void fileError(Contact c, String fileName, Element e);

		public void fileSent(Contact c, String fileName, boolean b,FTSender sender);

		public void chunkSent(int sentBytes, int length,FTSender sender);

		public void sessionInitated(Contact contactByJid, String fileName,
				FTSender sender);
	}

	/*
	 * The constructor that initiate a Jingle file transfer
	 */
	public FTSender(String fileName, byte[] fileData, String to, String desc, FTSEventHandler eh) {
		this.fileName = fileName;
		this.fileData = fileData;
		this.to = to;
		this.desc = desc;
		this.eh = eh;
	}

	public void sessionInitiate() {
		Iq sessionInitiateIq = new Iq(this.to, Iq.T_SET);
		Element jingle = sessionInitiateIq.addElement(XMPPClient.JINGLE,
				FTSender.JINGLE);
		jingle.setAttribute(ACTION, SESSION_INITIATE);
		jingle.setAttribute(INITIATOR, xmppClient.my_jid);
		String encodedSid = Utils.hexDigest(System.currentTimeMillis() + "",
				"sha1");
		jingleSid = new String(encodedSid);
		jingle.setAttribute(SID, jingleSid);
		Element content = jingle.addElement(null, CONTENT);
		content.setAttribute(CREATOR, INITIATOR);
		content.setAttribute(NAME, "a-file-transfer" + jingleSid);
		Element description = content.addElement(
				XMPPClient.JINGLE_FILE_TRANSFER, DESCRIPTION);
		Element offer = description.addElement(null, OFFER);
		Element file = offer.addElement(XMPPClient.FILE_TRANSFER, FILE);
		file.setAttribute(NAME, this.fileName);
		if (fileData != null) file
				.setAttribute(SIZE, this.fileData.length + "");
		if (desc != null && desc.length()>0)
			file.addElement(null, DESC).addText(this.desc);
		Element transport = content.addElement(XMPPClient.JINGLE_IBB_TRANSPORT,
				TRANSPORT);
		transport.setAttribute(FTSender.BLOCK_SIZE, this.chuck_length + "");
		encodedSid = Utils.hexDigest((System.currentTimeMillis() + 1) + "",
				"sha1");
		transportSid = new String(encodedSid);
		transport.setAttribute(FTSender.SID, transportSid);

		IQResultListener sessionInitiateListener = new IQResultListener() {
			public void handleError(Element e) {
				eh.fileError(XMPPClient.getInstance().getRoster()
						.getContactByJid(e.getAttribute(Iq.ATT_FROM)),
						fileName, e);
			}

			public void handleResult(Element e) {
				Element jingle = e.getChildByName(XMPPClient.JINGLE,
						FTSender.JINGLE);
				if (jingle != null) {
					Element decline = jingle.getChildByName(null,
							FTSender.DECLINE);
					if (decline != null) {
						eh.fileAcceptance(XMPPClient.getInstance().getRoster()
								.getContactByJid(e.getAttribute(Iq.ATT_FROM)),
								fileName, false,FTSender.this);
						return;
					}
				}
				FTSender.this.initiateInteraction();
			}
		};

		PacketListener terminateListener = new PacketListener() {
			public void packetReceived(Element e) {
				Element jingle = e.getChildByName(XMPPClient.JINGLE,
						FTSender.JINGLE);
				if (jingle != null) {
					Element decline = jingle.getChildByName(null,
							FTSender.DECLINE);
					if (decline != null) {
						eh.fileAcceptance(XMPPClient.getInstance().getRoster()
								.getContactByJid(e.getAttribute(Iq.ATT_FROM)),
								fileName, false,FTSender.this);
						return;
					}
				}
			}
		};
		//this.encodedData.setLength(fileSize*2);

		EventQuery eq = new EventQuery(Iq.IQ, new String[] { Iq.ATT_FROM,
				Iq.ATT_TYPE }, new String[] { this.to, Iq.T_SET });
		EventQuery eqChild = eq.child = new EventQuery(FTSender.JINGLE,
				new String[] { FTSender.ACTION },
				new String[] { FTSender.SESSION_TERMINATE });
		eqChild.child = new EventQuery(FTSender.DECLINE, null, null);
		BasicXmlStream.addEventListener(eq, terminateListener);

		xmppClient.sendIQ(sessionInitiateIq, sessionInitiateListener);
		
		eh.sessionInitated(XMPPClient.getInstance().getRoster()
				.getContactByJid(this.to), fileName, this);
	}

	private void initiateInteraction() {
		EventQuery eq = new EventQuery(Iq.IQ, new String[] { Iq.ATT_TYPE },
				new String[] { Iq.T_SET });
		eq.child = new EventQuery(FTSender.JINGLE, new String[] { "xmlns",
				FTSender.ACTION, FTSender.SID }, new String[] {
				XMPPClient.JINGLE, FTSender.SESSION_ACCEPT,
				FTSender.this.jingleSid });
		PacketListener ibbSender = new PacketListener() {

			public void packetReceived(Element e) {
				eh.fileAcceptance(XMPPClient.getInstance().getRoster()
						.getContactByJid(e.getAttribute(Iq.ATT_FROM)),
						fileName, true,FTSender.this);
				Element recIq = e;
				Iq reply = Utils.easyReply(recIq);
				xmppClient.sendPacket(reply);
				if (sessionOpened) FTSender.this.sendFile();
				// this iq must have arrived and the packet received below
				sessionOpened = true;
			}
		};
		BasicXmlStream.addOnetimeEventListener(eq, ibbSender);

		Iq initiateInteraction = new Iq(this.to, Iq.T_SET);
		Element open = initiateInteraction.addElement(XMPPClient.NS_IBB, OPEN);
		open.setAttribute(SID, transportSid);
		open.setAttribute(BLOCK_SIZE, chuck_length + "");
		IQResultListener initiateInteractionListener = new IQResultListener() {
			public void handleError(Element e) {
				eh.fileAcceptance(XMPPClient.getInstance().getRoster()
						.getContactByJid(e.getAttribute(Iq.ATT_FROM)),
						fileName, false,FTSender.this);
			}

			public void handleResult(Element e) {
				if (sessionOpened) FTSender.this.sendFile();
				// this result must have arrived and the packet received below
				sessionOpened = true;
			}
		};

		xmppClient.sendIQ(initiateInteraction, initiateInteractionListener);
	}

	private int fileOffset = 0;
	private String encodedData = "";
	private IQResultListener chunkListener;
	private int seq = 0;

	private void sendFile() {
		this.fileOffset = 0;
		this.encodedData = new String(Base64.encode(this.fileData));

		chunkListener = new IQResultListener() {
			public void handleError(Element e) {
			}

			public void handleResult(Element e) {
				if (fileOffset < encodedData.length()) FTSender.this
						.sendChunk();
				else
					FTSender.this.sendFooter();
			}
		};
		sendChunk();
	}

	private void sendChunk() {
		Iq chunkIq = new Iq(this.to, Iq.T_SET);
		Element data = chunkIq.addElement(XMPPClient.NS_IBB, DATA);
		data.setAttribute(SID, transportSid);
		data.setAttribute(SEQ, seq + "");
		seq++;
		if (seq == 65536) seq = 0;
		int endIndex = Math
				.min(fileOffset + chuck_length, encodedData.length());
		String substr = this.encodedData.substring(fileOffset, endIndex);
		fileOffset += chuck_length;
		data.addText(substr);
		xmppClient.sendIQ(chunkIq, chunkListener);
		eh.chunkSent(fileOffset + chuck_length,encodedData.length(),FTSender.this);
	}

	private void sendFooter() {
		Iq closeInteraction = new Iq(this.to, Iq.T_SET);
		Element close = closeInteraction.addElement(XMPPClient.NS_IBB, CLOSE);
		close.setAttribute(SID, transportSid);

		IQResultListener closeListener = new IQResultListener() {
			public void handleError(Element e) {
				eh.fileSent(XMPPClient.getInstance().getRoster()
						.getContactByJid(e.getAttribute(Iq.ATT_FROM)),
						fileName, true,FTSender.this);
			}

			public void handleResult(Element e) {
				sessionOpened = false;
				eh.fileSent(XMPPClient.getInstance().getRoster()
						.getContactByJid(e.getAttribute(Iq.ATT_FROM)),
						fileName, true,FTSender.this);
			}
		};

		// first setup the listener to SESSION_TERMINATE
		PacketListener terminateListener = new PacketListener() {
			public void packetReceived(Element e) {
				Iq reply = Utils.easyReply(e);
				xmppClient.sendPacket(reply);
			}
		};

		EventQuery eq = new EventQuery(Iq.IQ, new String[] { Iq.ATT_FROM,
				Iq.ATT_TYPE }, new String[] { this.to, Iq.T_SET });
		eq.child = new EventQuery(FTSender.JINGLE, new String[] {
				FTSender.ACTION, "xmlns" }, new String[] {
				FTSender.SESSION_TERMINATE, XMPPClient.JINGLE });
		BasicXmlStream.addOnetimeEventListener(eq, terminateListener);

		xmppClient.sendIQ(closeInteraction, closeListener);
	}
}
