/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Stanza.java 1220 2009-02-27 09:41:06Z luca $
*/

package it.yup.xmpp.packets;

import it.yup.xml.Element;

public class Stanza extends Element {

	public static final String NS_JABBER_CLIENT = "jabber:client";
	public static final String ATT_TO   = "to";
	public static final String ATT_FROM = "from";
	public static final String ATT_TYPE = "type";
	public static final String ATT_ID   = "id";
	//public static final String ATT_JID  = "jid";

	protected Stanza(String name, String to, String type, String id) {
		super(NS_JABBER_CLIENT, name);
		if (to != null) {
			setAttribute(ATT_TO, to );
		}
		if (type != null) {
			setAttribute(ATT_TYPE, type );
		}
		if (id != null) {
			setAttribute(ATT_ID, id );
		}
	}

	public Stanza(Element e) {
		super(e);
	}
	
//	public void setType(String type) {
//		setAttribute("type", type);
//	}
//	
//	public String getType() {
//		return getAttribute("type");
//	}
//	
//	public void setId(String id) {
//		setAttribute("id", id);
//	}
//	
//	public String getId() {
//		return getAttribute("id");
//	}
//	
//	public void setFrom(String from) {
//		setAttribute("from", from);
//	}
//	
//	public String getFrom() {
//		return getAttribute("from");
//	}
//	
//	public void setTo(String to) {
//		setAttribute("to", to);
//	}
//	
//	public String getTo() {
//		return getAttribute("to");
//	}
}
