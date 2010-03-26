package it.yup.xml;

// #debug
//@import it.yup.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

public class BSerializer {

	// serialize the BElement and all its children
	public static byte[] toXml(Element el) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		KXmlSerializer serializer = new KXmlSerializer();
		try {
			serializer.setOutput(baos, "UTF-8");
			write(el, serializer);
			serializer.endDocument();
			serializer.flush();
		} catch (IOException e) {
			// #debug 
//@			        	Logger.log("[BElement:toXml] IOException" + e.getMessage());
		}
		return baos.toByteArray();
	}

	private static void write(Element el, XmlSerializer serializer) {
		try {
			serializer.setPrefix("", el.uri);
			serializer.startTag(el.uri, el.name);
			for (int i = 0; i < el.nattributes; i++) {
				serializer.attribute(el.attributes[i][0], el.attributes[i][1],
						el.attributes[i][2]);
			}

			for (int i = 0; i < el.nchildren; i++) {
				Object c = el.children[i];
				if (c.getClass() == String.class) {
					serializer.text((String) c);
				} else {
					write((Element) c, serializer);
				}
			}
			serializer.endTag(el.uri, el.name);
			serializer.flush();
		} catch (IllegalArgumentException e) {
			// #debug
//@			        	Logger.log("[BElement::write] IllegalArgumentException:" + e.getMessage());
		} catch (IllegalStateException e) {
			//        	 #debug
//@			        	Logger.log("[BElement::write] IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			//        	 #debug
//@			        	Logger.log("[BElement::write] IOException: " + e.getMessage());    		
		}
	}
}
