/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: BasicXmlStream.java 1578 2009-06-16 11:07:59Z luca $
*/

package it.yup.xmlstream;

import it.yup.transport.TransportListener;

//#mdebug

import it.yup.util.Logger;

//#enddebug

import it.yup.xml.Element;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;
import it.yup.xmpp.packets.Iq;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public abstract class BasicXmlStream implements TransportListener,
		StreamEventListener {

	/*
	 * The registration for mySelf is used for unmatched stanza registration;
	 */
	private static EventQueryRegistration unmatchedStanzaReg = null;

	/** packets waiting for being sent */
	protected Vector sendQueue = new Vector(10);

	/** Storing XPath like queries and relative packet listeners */
	public static Vector eventListeners = new Vector(10);

	/* EVENT CONSTANTS */
	public static String STREAM_CONNECTED = "_01";
	public static String STREAM_INITIALIZED = "_02";
	// public static String STREAM_DISCONNECTED = "_03";
	public static String STREAM_ERROR = "_04";
	// public static String STREAM_RESOURCE_BOUND = "_05";
	// public static String STREAM_SESSION_OPENED = "_06";
	public static String STREAM_ACCOUNT_REGISTERED = "_07";
	public static String CONNECTION_LOST = "_08";
	public static String STREAM_TERMINATED = "_09";
	// public static String AUTHENTICACTION_FAILED = "_09";
	public static String REGISTRATION_FAILED = "_10";
	public static String CONNECTION_FAILED = "_11";
	public static String NOT_AUTHORIZED = "_12";
	//public static String STREAM_REGISTRATION_ERROR = "_08";
	public static String TLS_INITIALIZED = "_13";
	public static String STREAM_AUTHENTICATED = "_14";
	public static String COMPRESSION_INITIALIZED = "_15";
	public static String UNMATCHED_STANZA = "_16";

	/** Session ID for this stream */
	protected String SID = null;

	/* configuration properties */
	public static final String USERNAME = "1";
	public static final String PASSWORD = "2";

	/* User related data */
	/** Session jid */
	public String jid = null;
	/** Password used for authentication */
	protected String password = null;

	/**
	 * Stream features, mapping namespace to relevant dom Element 
	 */
	protected Hashtable features = new Hashtable();

	/** Initializers */
	protected Vector initializers = new Vector();

	/** Iterate through initializers in subsequent {@link BasicXmlStream#nextInitializer()}
	 * calls */
	protected Enumeration initializerInterator = null;

	/**
	 * Class used for associating packet listeners and queries
	 */
	protected static class ListenerRegistration {
		public EventQuery query;
		public Object listener;
		public boolean oneTime;

		public ListenerRegistration(EventQuery query, Object listener,
				boolean oneTime) {
			this.query = query;
			this.listener = listener;
			this.oneTime = oneTime;
		}
	}

	protected BasicXmlStream() {
		// prepare the default initializers
		// #ifdef TLS
						if (XMPPClient.getInstance().addTLS)
						initializers.addElement(new TLSInitializer());
		// #endif
		// #ifdef COMPRESSION    
		if (XMPPClient.getInstance().addCompression) initializers
				.addElement(new CompressionInitializer());
		// #endif    	
		initializers.addElement(new SASLAuthenticator());
		initializers.addElement(new ResourceBinding());
		initializers.addElement(new SessionOpener());

		eventListeners.removeAllElements();
		EventQuery eq = new EventQuery(BasicXmlStream.UNMATCHED_STANZA, null,
				null);
		unmatchedStanzaReg = addEventListener(eq, this);
	}

	/** Initialize the stream 
	 * @param jid 
	 * 		jid with or without resource; in the first case the resource is taken as a request 
	 * 		(the server may override it)
	 * @param domain
	 * @param password
	 * */
	public abstract void initialize(String jid, String password);

	/**
	 * Send a XMPP packet. It's possible to set a maximum wait time in order to send a packet
	 * also when cheap connections aren't available.
	 * 
	 * @param packetToSend
	 * 			the XMPP packet to send
	 * @param maxWait 
	 * 			maximum time a packet can wait before sending it (-1 for sending it 
	 * 			only when a cheap connection is available). This paramenter is only for
	 * 			compatibility with future extensions
	 */
	public void send(Element packetToSend, int maxWait) {
		// prepare the packet to send
		packetToSend.queueTime = new Date().getTime();
		packetToSend.maxWait = maxWait;

		synchronized (sendQueue) {
			this.sendQueue.addElement(packetToSend);
		}
		tryToSend();
	}

	/** Restart a stream (used during initialization)*/
	protected abstract void restart();

	protected Vector getPacketsToSend(boolean onlyUrgent) {
		Vector packetsToSend = new Vector();

		synchronized (sendQueue) {
			if (onlyUrgent) {
				// try to send the most urgent
				Enumeration en = sendQueue.elements();

				// the packets due in the next second
				long aBitLater = (new Date()).getTime() + 1000;
				while (en.hasMoreElements()) {
					Element ithPacket = ((Element) en.nextElement());
					if (ithPacket.maxWait > 0
							&& (ithPacket.queueTime + 1000 * ithPacket.maxWait) > aBitLater) {
						packetsToSend.addElement(ithPacket);
						// this is the first place to look for an error
						sendQueue.removeElement(ithPacket);
					}
				}
			} else {
				Enumeration en = sendQueue.elements();

				while (en.hasMoreElements()) {
					packetsToSend.addElement(en.nextElement());
				}
				sendQueue.removeAllElements();
			}
		}

		return packetsToSend;
	}

	/** 
	 * Method starting the send process, only if necessary
	 * */
	protected abstract void tryToSend();

	/**
	 * Add an event listener, it may be either a {@link PacketListener} or a {@link StreamEventListener}
	 * @param query
	 * @param listener either a {@link PacketListener} or a {@link StreamEventListener}
	 * @return the registration object that may be used for unregistering the listener
	 */
	public static EventQueryRegistration addEventListener(EventQuery query,
			Object listener) {
		ListenerRegistration ld = new ListenerRegistration(query, listener,
				false);
		synchronized (eventListeners) {
			eventListeners.addElement(ld);
		}

		return new EventQueryRegistration(ld, eventListeners);
	}

	/**
	 * Remove an event listener passing the {@link EventQueryRegistration} received from 
	 * {@link BasicXmlStream#addEventListener(EventQuery, Object)} or
	 * {@link BasicXmlStream#addOnetimeEventListener(EventQuery, Object)} 
	 * @param registration
	 */
	public static void removeEventListener(EventQueryRegistration registration) {
		registration.remove();
	}

	/**
	 * Add an event listener that can be fired only one, it may be either a 
	 * {@link PacketListener} or a {@link StreamEventListener}
	 * @param query
	 * @param listener either a {@link PacketListener} or a {@link StreamEventListener}
	 * @return the registration object that may be used for unregistering the listener
	 */
	public static EventQueryRegistration addOnetimeEventListener(
			EventQuery query, Object listener) {
		ListenerRegistration ld = new ListenerRegistration(query, listener,
				true);
		synchronized (eventListeners) {
			eventListeners.addElement(ld);
		}
		return new EventQueryRegistration(ld, eventListeners);
	}

	/**
	 * Call the packet listeners registered for this packet
	 * @param stanza
	 */
	protected void promotePacket(Element stanza) {
		boolean matched = false;
		try {
			// #ifdef TIMING    		
			    		long t1 = System.currentTimeMillis();
			// #endif

			// 			XXX transform into a preprocessor macro    		
			//    		Uncomment for logging the number of listeners
			//System.out.println("---->" + eventListeners.size());

			Enumeration enPacketListener = null;
			synchronized (eventListeners) {
				enPacketListener = eventListeners.elements();
			}
			while (enPacketListener.hasMoreElements()) {
				ListenerRegistration listenerData = (ListenerRegistration) enPacketListener
						.nextElement();

				//Uncomment for dumping registered listeners
				//				    			EventQuery q = listenerData.query;
				//				    			String tab = ">>";
				//				    			while(q!=null) {
				//				    				System.out.println(tab + q.event);
				//				    				if(q.tagAttrNames != null) {
				//				    					for(int i=0; i<q.tagAttrNames.length; i++) {
				//				    						System.out.println(tab +">" + q.tagAttrNames[i] +": " + q.tagAttrValues[i]);
				//				    					}
				//				    				}
				//				    				q = q.child;
				//				
				//				    				tab += ">>";
				//				
				//				    			}

				if (areMatching(stanza, listenerData.query)) {
					// #ifdef TIMING    				
					    				long t2 = System.currentTimeMillis();
					// #endif
					matched = true;
					((PacketListener) listenerData.listener)
							.packetReceived(stanza);
					if (listenerData.oneTime == true) {
						synchronized (eventListeners) {
							eventListeners.removeElement(listenerData);
						}
					}
					// #ifdef TIMING
					    				EventQuery q = listenerData.query; 				
					    				System.out.println("L " + q.event + ":" + (System.currentTimeMillis() - t2));
					// #endif    				
				}
			}
			// #ifdef TIMING
			    		System.out.println("Promote: " + (System.currentTimeMillis() - t1));
			// #endif

		} catch (RuntimeException e) {
			// XXX don't knwow if here we must do something like closing the stream
			// #mdebug
			e.printStackTrace();
			Logger.log(new String(stanza.toXml()));
			Logger.log("[BasicXmlStream::promotePacket] RuntimeException: "
					+ e.getClass().getName() + "\n" + e.getMessage());
			// #enddebug
		}
		if (matched == false) {
			try {
				dispatchEvent(BasicXmlStream.UNMATCHED_STANZA, stanza);
			} catch (Exception e) {
				// #mdebug
				e.printStackTrace();
				Logger.log(new String(stanza.toXml()));
				Logger.log("[BasicXmlStream::promotePacket] RuntimeException: "
						+ e.getClass().getName() + "\n" + e.getMessage());
				// #enddebug
			}
		}
	}

	/**
	 * Verify if a packet matches a query 
	 * @param receivedPacket
	 * @param query
	 * @return
	 */
	protected boolean areMatching(Element receivedPacket, EventQuery query) {

		/* better stating first a condition that fails immediatly the check 
		 * (just readability issue) */
		if (!query.event.equals(receivedPacket.name)
				&& !query.event.equals(EventQuery.ANY_PACKET)) { return false; }

		// then check all the attributes if the query has any 
		if (query.tagAttrNames != null) {
			for (int l = 0; l < query.tagAttrNames.length; l++) {
				String lthName = query.tagAttrNames[l];
				String lthValue = query.tagAttrValues[l];
				if ("xmlns".equals(lthName)
						&& lthValue.equals(receivedPacket.uri)) {
					continue;
				} else {
					String val = receivedPacket.getAttribute(lthName);
					if (val == null || !val.equals(lthValue)) { return false; }
				}
			}
		}

		/* a packet with no child doesn't match a query with a child sub-query */
		Element[] children = receivedPacket.getChildren();
		if (query.child != null && children != null && children.length == 0) { return false; }

		// all attributes verified, check the children
		if (query.child != null) {
			for (int i = 0; i < children.length; i++) {
				Element ithChild = children[i];
				if (areMatching(ithChild, query.child)) { return true; }
			}
			return false;
		}

		return true;
	}

	/**
	 * Dispatch an XmlStream event
	 * */
	protected void dispatchEvent(String event, Object source) {
		//		Vector listeners = BasicXmlStream.eventListeners;
		//		Enumeration en = eventListeners.elements();
		//		while (en.hasMoreElements()) {
		//			ListenerRegistration listenerData = (ListenerRegistration) en
		//					.nextElement();
		//			if (listenerData.query.event.equals(EventQuery.ANY_EVENT)
		//					|| event.equals(listenerData.query.event)) {
		//				((StreamEventListener) listenerData.listener).gotStreamEvent(
		//						event, source);
		//				if (listenerData.oneTime) {
		//					synchronized (eventListeners) {
		//						eventListeners.removeElement(listenerData);
		//					}
		//				}
		//			}
		//		}

		ListenerRegistration[] regs = null;
		synchronized (eventListeners) {
			regs = new ListenerRegistration[eventListeners.size()];
			eventListeners.copyInto(regs);
		}
		for (int i = 0; i < regs.length; i++) {
			ListenerRegistration listenerData = regs[i];
			if (listenerData.query.event.equals(EventQuery.ANY_EVENT)
					|| event.equals(listenerData.query.event)) {
				((StreamEventListener) listenerData.listener).gotStreamEvent(
						event, source);
				if (listenerData.oneTime) {
					synchronized (eventListeners) {
						eventListeners.removeElement(listenerData);
					}
				}
			}
		}

	}

	public void gotStreamEvent(String event, Object source) {
		if (event.equals(BasicXmlStream.UNMATCHED_STANZA)
				&& source instanceof Element) {
			Element sSource = (Element) source;
			if (sSource.name.equals(Iq.IQ)) {
				String type = sSource.getAttribute(Iq.ATT_TYPE);
				if (type.equals(Iq.T_GET) || type.equals(Iq.T_SET)) {
					Element replyIq = new Element(sSource);
					replyIq.setAttribute(Iq.ATT_TO, replyIq
							.getAttribute(Iq.ATT_FROM));
					replyIq.delAttribute(Iq.ATT_FROM);
					replyIq.setAttribute(Iq.ATT_TYPE, Iq.T_ERROR);
					Element error = replyIq.addElement(null, Iq.T_ERROR);
					error.setAttribute(Iq.ATT_TYPE, "cancel");
					error.addElement("urn:ietf:params:xml:ns:xmpp-stanzas",
							"feature-not-implemented");
					XMPPClient.getInstance().sendPacket(replyIq);
				}
			}
		}
	}

	/**
	 * Start the feature chain
	 * @param features
	 */
	protected void processFeatures(Element features[]) {
		this.features.clear();
		this.initializerInterator = null;
		for (int i = 0; i < features.length; i++) {
			this.features.put(features[i].uri, features[i]);
		}
		// received a set of features trigger the stream initialization
		nextInitializer();
	}

	/**
	 * Call the next stream initialiazer.
	 * Dispatch {@link XmlStream#STREAM_INITIALIZATION_FINISHED} when all the 
	 * initializers have been processed
	 */
	public void nextInitializer() {
		if (initializerInterator == null) {
			initializerInterator = initializers.elements();
		}
		while (initializerInterator.hasMoreElements()) {
			Initializer initializer = (Initializer) initializerInterator
					.nextElement();
			if (initializer.matchFeatures(features)) {
				initializer.start(this);
				return;
			}
		}
		initializerInterator = null;
		dispatchEvent(BasicXmlStream.STREAM_INITIALIZED, null);
	}

	public void addInitializer(Initializer initializer, int position) {
		this.initializers.insertElementAt(initializer, position);
	}

	public void removeInitializer(Initializer initializer) {
		this.initializers.removeElement(initializer);
	}

	/**
	 *	Initializer that binds a resource
	 */
	private class ResourceBinding extends Initializer implements PacketListener {

		public ResourceBinding() {
			super("urn:ietf:params:xml:ns:xmpp-bind", false);
		}

		public void start(BasicXmlStream xmlStream) {
			this.stream = xmlStream;
			Iq iq = new Iq(null, "set");
			Element bind = new Element(namespace, "bind");
			String s = Contact.resource(xmlStream.jid);
			if (s != null) {
				bind.addElementAndContent(namespace, "resource", s);
			}
			iq.addElement(bind);
			EventQuery q = new EventQuery("iq", new String[] { "id" },
					new String[] { iq.getAttribute("id") });
			BasicXmlStream.addOnetimeEventListener(q, this);
			stream.send(iq, -1);
		}

		public void packetReceived(Element e) {
			if (Iq.T_RESULT.equals(e.getAttribute("type"))) {
				Element bind = e.getChildByName(null, "bind");
				Element jid = null;
				if (bind != null
						&& (jid = bind.getChildByName(null, "jid")) != null
						&& jid.getText() != null) {
					stream.jid = jid.getText();
				}
				stream.nextInitializer();
			} else {
				stream.dispatchEvent(BasicXmlStream.STREAM_ERROR,
						"cannot bind resource");
			}
		}

	}

	/** 
	 * Initialiazer that opens a session
	 * */
	private class SessionOpener extends Initializer implements PacketListener {

		public SessionOpener() {
			super("urn:ietf:params:xml:ns:xmpp-session", true);
		}

		public void start(BasicXmlStream xmlStream) {
			this.stream = xmlStream;
			Iq iq = new Iq(null, "set");
			Element session = new Element(namespace, "session");
			iq.addElement(session);
			EventQuery q = new EventQuery("iq", new String[] { "id" },
					new String[] { iq.getAttribute("id") });
			BasicXmlStream.addOnetimeEventListener(q, this);
			stream.send(iq, -1);
		}

		public void packetReceived(Element e) {
			if ("result".equals(e.getAttribute("type"))) {
				stream.nextInitializer();
			} else {
				stream.dispatchEvent(BasicXmlStream.STREAM_ERROR,
						"cannot start session");
			}
		}
	}
}
