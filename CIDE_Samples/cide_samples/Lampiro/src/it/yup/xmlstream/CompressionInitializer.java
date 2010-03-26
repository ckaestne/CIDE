/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: CompressionInitializer.java 1578 2009-06-16 11:07:59Z luca $
*/

// #ifdef COMPRESSION
package it.yup.xmlstream;

import it.yup.xml.Element;

public class CompressionInitializer extends Initializer implements PacketListener {
	
	BasicXmlStream stream;
	
	protected CompressionInitializer() {
		super("http://jabber.org/features/compress", true);
	}

	public void start(BasicXmlStream stream) {
		
		this.stream = stream;
		
		Element methods = (Element) stream.features.get(namespace);
		boolean found = false;
		Element [] children = methods.getChildren();
		for(int i=0; i<children.length; i++) {
			Element method = children[i];
			if("method".equals(method.name) && "zlib".equals(method.getText())) {
				found = true;
				break;
			}
		}
		
		if(found) {
			Element compress = new Element("http://jabber.org/protocol/compress", "compress");
			compress.addElement("http://jabber.org/protocol/compress", "method").addText("zlib");
			EventQuery pq = new EventQuery(EventQuery.ANY_PACKET, null, null);
			BasicXmlStream.addOnetimeEventListener(pq, this);
			stream.send(compress, -1);
		} else {
			stream.nextInitializer();
		}
	}

	public void packetReceived(Element e) {
		if("compressed".equals(e.name)) {
			((SocketStream) stream).startCompression();
			stream.restart();
			stream.dispatchEvent(BasicXmlStream.COMPRESSION_INITIALIZED, null);
		} else {
			stream.nextInitializer();
		}
	}

}
// #endif
