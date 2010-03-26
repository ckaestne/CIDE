/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: PacketListener.java 1220 2009-02-27 09:41:06Z luca $
*/

package it.yup.xmlstream;

import it.yup.xml.Element;


/**
 * Listening to XMPP packets
 *
 */
public interface PacketListener 
{
	/**
	 * Called when an XMPP element is received
	 * @param e
	 */
    public void packetReceived (Element e);
}
