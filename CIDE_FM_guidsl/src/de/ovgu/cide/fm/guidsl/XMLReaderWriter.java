/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.fm.guidsl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.graphics.RGB;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class XMLReaderWriter {

	/**
	 * using sax parser (for performance) to get all values
	 * 
	 * mostly copied from Storage_Default: CIDEXMLReader
	 * 
	 * @param r
	 * @param featureModel
	 * @return
	 * @throws IOException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public static void readFile(InputStream is,
			final Map<String, Long> featureIds,
			final Map<Long, RGB> featureColors,
			final Map<Long, Boolean> featureVisibility) throws IOException {

		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			SAXParser parser = parserFactory.newSAXParser();
			XMLReader xmlReader = parser.getXMLReader();
			DefaultHandler handler = new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (qName.equals("featureattr")) {
						String name = attributes.getValue("name");
						long id = -1;
						try {
							id = Long.parseLong(attributes.getValue("id"));
						} catch (NumberFormatException e) {
						}
						long color = -1;
						try {
							color = Long
									.parseLong(attributes.getValue("color"));
						} catch (NumberFormatException e) {
						}
						boolean selected = !"false".equals(attributes
								.getValue("selected"));

						if (name != null && id > 0) {
							featureIds.put(name, id);

							if (color >= 0)
								featureColors.put(id, getRGB(color));
							featureVisibility.put(id, selected);
						}

					}
				}

				@Override
				public InputSource resolveEntity(String publicId,
						String systemId) throws IOException, SAXException {
					return new InputSource(
							new ByteArrayInputStream(new byte[0]));
				}
			};
			xmlReader.setContentHandler(handler);
			xmlReader.setDTDHandler(handler);
			xmlReader.setErrorHandler(handler);
			xmlReader.setEntityResolver(handler);
			xmlReader.parse(new InputSource(is));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static InputStream writeFile(final Map<String, Long> featureIds,
			final Map<Long, RGB> featureColors,
			final Map<Long, Boolean> featureVisibility) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			StreamResult streamResult = new StreamResult(out);
			SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
					.newInstance();
			// SAX2.0 ContentHandler.
			TransformerHandler hd = tf.newTransformerHandler();
			Transformer serializer = hd.getTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
					"featuremodelattributes.dtd");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			hd.setResult(streamResult);
			hd.startDocument();
			// annotations tag.
			hd.startElement("", "", "featuremodelattributes",
					new AttributesImpl());
			// key tags.
			for (Entry<String, Long> color : featureIds.entrySet()) {
				Long id = color.getValue();

				AttributesImpl attributes = new AttributesImpl();
				attributes
						.addAttribute("", "", "name", "CDATA", color.getKey());
				attributes.addAttribute("", "", "id", "CDATA", id.toString());
				if (featureColors.containsKey(id))
					attributes.addAttribute("", "", "color", "CDATA", ""
							+ getLong(featureColors.get(id)));
				if (featureVisibility.containsKey(id))
					attributes.addAttribute("", "", "selected", "CDATA",
							featureVisibility.get(id) ? "true" : "false");

				hd.startElement("", "", "featureattr", attributes);
				hd.endElement("", "", "featureattr");
			}
			hd.endElement("", "", "featuremodelattributes");
			hd.endDocument();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	protected static RGB getRGB(long color) {
		return new RGB((byte)color & 255,(byte) (color >> 8) & 255, (byte)(color >> 16) & 255);
	}

	protected static long getLong(RGB color) {
		return (color.blue << 16) | (color.green << 8) | color.red;
	}
}
