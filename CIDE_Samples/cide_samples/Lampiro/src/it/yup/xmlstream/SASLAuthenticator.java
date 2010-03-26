/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SASLAuthenticator.java 1578 2009-06-16 11:07:59Z luca $
*/

package it.yup.xmlstream;

import it.yup.util.GoogleToken;
import it.yup.util.Utils;
import it.yup.xml.Element;
import it.yup.xmpp.Contact;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;

import org.bouncycastle.util.encoders.Base64;

// XXX Note if we used a state machine with just one listener we could avoid declaring
// on the fly stubs for calling the correct method. Each stub takes about 900 bytes, which is
// a considerable waste of space
/**
 * Class carrying on all the authentications steps 
 *
 */
public class SASLAuthenticator extends Initializer {

	private static String MECHANISM_PLAIN = "PLAIN";
	private static String MECHANISM_DIGEST_MD5 = "DIGEST-MD5";
	private static String MECHANISM_X_GOOGLE_TOKEN = "X-GOOGLE-TOKEN";

	private String supportedMechanisms[] = new String[] { MECHANISM_DIGEST_MD5,
			MECHANISM_PLAIN, MECHANISM_X_GOOGLE_TOKEN };

	protected SASLAuthenticator() {
		// mandatory 
		super("urn:ietf:params:xml:ns:xmpp-sasl", false);
	}

	/**
	 * Start the login process. The result is asynchronous, and in order to get it 
	 * register a listener for the STREAM_CONNECTED event.
	 */
	public void start(BasicXmlStream xmlStream) {

		this.stream = xmlStream;
		// Config cfg = xmlStream.config;
		// look for the best auth mechanism and start the auth (the first, the better)
		Element mechanisms = (Element) stream.features.get(namespace);

		Element auth = new Element(namespace, "auth");
		for (int i = 0; i < supportedMechanisms.length; i++) {
			Element[] children = mechanisms.getChildren();
			for (int j = 0; j < children.length; j++) {
				Element mechanism = children[j];
				if (supportedMechanisms[i].equals(mechanism.getText())) {
					if (supportedMechanisms[i].equals(MECHANISM_PLAIN)) {
						auth.setAttribute("mechanism", MECHANISM_PLAIN);
						try {
							ByteArrayOutputStream bos = new ByteArrayOutputStream();
							String tmp = Contact.userhost(stream.jid);
							bos.write(Utils.getBytesUtf8(tmp));
							bos.write(0);
							tmp = Contact.user(stream.jid);
							bos.write(Utils.getBytesUtf8(tmp));
							bos.write(0);
							tmp = stream.password;
							bos.write(Utils.getBytesUtf8(tmp));
							/* base64 **SHOULD** be ASCII and doesn't need UTF-8 */
							auth.addText(new String(Base64.encode(bos
									.toByteArray())));
						} catch (UnsupportedEncodingException e) {
							// YUPMidlet.yup.reportException("UnsupportedEncoding on SASLAutenticator", e, null);
						} catch (IOException e) {
							// YUPMidlet.yup.reportException("IO error on SASLAutenticator", e, null);
						}

						// listen for the result 
						EventQuery pq = new EventQuery(EventQuery.ANY_PACKET,
								null, null);

						BasicXmlStream.addOnetimeEventListener(pq,
								new PacketListener() {
									public void packetReceived(Element e) {
										if ("success".equals(e.name)) {
											stream.restart();
											stream
													.dispatchEvent(
															BasicXmlStream.STREAM_AUTHENTICATED,
															null);
										} else {
											stream
													.dispatchEvent(
															BasicXmlStream.STREAM_ERROR,
															"Cannot authenticate");
										}
									}
								});
						stream.send(auth, -1);
						// started auth with this method, don't try the others
						return;
					} else if (supportedMechanisms[i]
							.equals(MECHANISM_DIGEST_MD5)) {
						auth.setAttribute("mechanism", MECHANISM_DIGEST_MD5);
						EventQuery pq = new EventQuery(EventQuery.ANY_PACKET,
								null, null);
						BasicXmlStream.addOnetimeEventListener(pq,
								new PacketListener() {
									public void packetReceived(Element e) {
										if ("challenge".equals(e.name)) {
											gotChallenge(e);
											stream
													.dispatchEvent(
															BasicXmlStream.STREAM_AUTHENTICATED,
															null);
										} else {
											stream
													.dispatchEvent(
															BasicXmlStream.STREAM_ERROR,
															"Cannot authenticate");
										}
									}

								});
						stream.send(auth, -1);
						return;
					} else if (supportedMechanisms[i]
							.equals(MECHANISM_X_GOOGLE_TOKEN)) {
						auth
								.setAttribute("mechanism",
										MECHANISM_X_GOOGLE_TOKEN);
						String user = Contact.user(stream.jid);
						String token = GoogleToken.getToken(user,
								stream.password);

						try {
							String jid = Contact.userhost(stream.jid);
							byte jidbytes[] = Utils.getBytesUtf8(jid);
							byte tokenbytes[] = Utils.getBytesUtf8(token);
							byte buf[] = new byte[2 + jidbytes.length
									+ tokenbytes.length];
							buf[0] = 0;
							System.arraycopy(jidbytes, 0, buf, 1,
									jidbytes.length);
							buf[jidbytes.length + 1] = 0;
							System.arraycopy(tokenbytes, 0, buf,
									jidbytes.length + 2, tokenbytes.length);
							// #mdebug
//@																					System.out.println(new String(Base64.encode(buf)));
							// #enddebug
							auth.addText(new String(Base64.encode(buf)));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// listen for the result 
						EventQuery pq = new EventQuery(EventQuery.ANY_PACKET,
								null, null);

						BasicXmlStream.addOnetimeEventListener(pq,
								new PacketListener() {
									public void packetReceived(Element e) {
										if ("success".equals(e.name)) {
											stream.restart();
											stream
													.dispatchEvent(
															BasicXmlStream.STREAM_AUTHENTICATED,
															null);
											return;
										} else {
											stream
													.dispatchEvent(
															BasicXmlStream.STREAM_ERROR,
															"Cannot authenticate");
										}
									}
								});

						stream.send(auth, -1);
						return;
					}
				}
			}
		}
		// XXX here we should use a different event
		stream.dispatchEvent(BasicXmlStream.STREAM_ERROR,
				"Cannot find suitable mechanism for authentication");
	}

	/**
	 * Proceed with the challenge reveived from the server (digest md5 auth)
	 * @param packet
	 */
	private void gotChallenge(Element packet) {
		try {
			// decode and parse the challenge
			String challengeMessage = new String(Base64
					.decode(packet.getText()));
			Hashtable challengeDirectives = parse(challengeMessage);

			String response_content;

			if (challengeDirectives.containsKey("rspauth")) {
				response_content = "";
			} else {

				// generate the response
				Hashtable responseDirectives = new Hashtable();
				String nonce = (String) challengeDirectives.get("nonce");
				responseDirectives.put("nonce", nonce);
				String nc = "00000001"; // response sequence number XXX handle subsequents
				responseDirectives.put("nc", nc);

				// XXX very unsecure, but good for now
				String cnonce = Utils.hexDigest(
						"" + System.currentTimeMillis(), "md5");

				responseDirectives.put("cnonce", cnonce);
				String qop = "auth";
				responseDirectives.put("qop", qop);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				bos.write(Utils.digest(Contact.user(stream.jid) + ":"
						+ Contact.domain(stream.jid) + ":" + stream.password,
						"md5"));
				bos.write(Utils.getBytesUtf8(":" + nonce + ":" + cnonce));

				byte A1[] = bos.toByteArray();
				String digest_uri = "xmpp/" + Contact.domain(stream.jid); // XXX don't know if this is correct
				String A2 = ("AUTHENTICATE:" + digest_uri);

				String KD = Utils.bytesToHex(Utils.digest(A1, "md5"))
						+ ":"
						+ (nonce + ":" + nc + ":" + cnonce + ":" + "auth" + ":" + Utils
								.hexDigest(A2, "md5"));
				String response = Utils.hexDigest(KD, "md5");
				responseDirectives.put("response", response);
				responseDirectives.put("charset", "utf-8");
				responseDirectives.put("digest-uri", digest_uri);
				responseDirectives.put("username", Contact.user(stream.jid));
				responseDirectives.put("realm", Contact.domain(stream.jid));

				// prepare the response putting together all the directives
				response_content = unparse(responseDirectives);
			}

			Element responseElement = new Element(namespace, "response");
			// responseElement.content = new String(Base64.encode(content.getBytes("utf-8")));
			// BASE64 **SHOULD** be UTF-8
			responseElement.addText(new String(Base64.encode(Utils
					.getBytesUtf8(response_content))));
			EventQuery pq = new EventQuery(EventQuery.ANY_PACKET, null, null);

			BasicXmlStream.addOnetimeEventListener(pq, new PacketListener() {
				public void packetReceived(Element e) {
					if ("success".equals(e.name)) {
						stream.restart();
					} else if ("challenge".equals(e.name)) {
						gotChallenge(e);
					} else if ("failure".equals(e.name)) {
						Element child = e
								.getChildByName(null, "not-authorized");
						if (child != null) stream.dispatchEvent(
								BasicXmlStream.NOT_AUTHORIZED,
								"Cannot authenticate");
						else
							stream.dispatchEvent(
									BasicXmlStream.REGISTRATION_FAILED,
									"Cannot registrate");
					} else {
						stream.dispatchEvent(BasicXmlStream.STREAM_ERROR,
								"Cannot authenticate");
					}
				}

			});
			stream.send(responseElement, -1);
		} catch (UnsupportedEncodingException e) {
			// YUPMidlet.yup.reportException("UnsupportedEncoding on gotChallenge in SASLAutenticator", e, null);
		} catch (IOException e1) {
			stream.dispatchEvent(BasicXmlStream.STREAM_ERROR,
					"Not enough memory for completing the authentication");
		}
	}

	private Hashtable parse(String message) {
		Hashtable directives = new Hashtable();
		boolean cont = true;
		int cur = 0;
		while (cont) {
			int middle = message.indexOf("=", cur);
			String name = message.substring(cur, middle);
			middle += 1;
			if (message.charAt(middle) == '"') {
				middle += 1;
				int end = message.indexOf('"', middle);
				directives.put(name, message.substring(middle, end));
				cur = message.indexOf(",", end) + 1;
				if (cur == 0) cont = false;
			} else {
				int end = message.indexOf(',', middle);
				if (end == -1) {
					directives.put(name, message.substring(middle).trim());
					cont = false;
				} else {
					directives.put(name, message.substring(middle, end).trim());
				}
				cur = end + 1;
			}
		}
		return directives;
	}

	private String unparse(Hashtable directives) {
		Enumeration keys = directives.keys();
		Hashtable quote = new Hashtable();
		StringBuffer out = new StringBuffer();
		quote.put("username", "");
		quote.put("realm", "");
		quote.put("cnonce", "");
		quote.put("nonce", "");
		quote.put("digest-uri", "");
		quote.put("authzid", "");
		quote.put("cipher", "");
		String join = "";
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) directives.get(key);
			if (quote.containsKey(key)) {
				out.append(join + key + "=" + "\"" + value + "\"");
			} else {
				out.append(join + key + "=" + value);
			}
			join = ",";
		}
		return out.toString();
	}

}
