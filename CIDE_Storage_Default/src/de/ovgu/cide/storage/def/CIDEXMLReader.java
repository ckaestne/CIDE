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

package de.ovgu.cide.storage.def;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModelWithID;

/**
 * reads a simple XML format (not validated before reading)
 * 
 * <annotations> <key name="ASTID" features="1,2,3" /> <key name="ASTID"
 * features="1,2,3" /> </annotations>
 * 
 * featureids are separated by commas
 * 
 * @author ckaestne
 * 
 */
public class CIDEXMLReader {

	/**
	 * using sax parser (for performance) to get all values
	 * 
	 * @param r
	 * @param featureModel
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	static HashMap<String, Set<IFeature>> loadFeatureMap(Reader r,
			final IFeatureModelWithID featureModel) throws IOException,
			ClassNotFoundException {
		final HashMap<String, Set<IFeature>> result = new HashMap<String, Set<IFeature>>();

		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			SAXParser parser = parserFactory.newSAXParser();
			XMLReader xmlReader = parser.getXMLReader();
			// xmlReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
			// false);
			// xmlReader.setFeature("http://xml.org/sax/features/validation",
			// false);
			DefaultHandler handler = new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (qName.equals("key")) {
						String key = attributes.getValue("name");
						String featureIdsStr = attributes.getValue("features");
						if (key != null && featureIdsStr != null) {
							String[] featureIds = featureIdsStr.split(",");
							HashSet<IFeature> features = new HashSet<IFeature>();
							for (String featureId : featureIds) {
								long id = -1;
								try {
									id = Long.parseLong(featureId);
								} catch (NumberFormatException e) {
								}
								if (id >= 0) {
									IFeature feature = featureModel
											.getFeature(id);
									if (feature != null)
										features.add(feature);
									else
										System.out.println("Unknown feature (xml): "
												+ id);
								}
							}
							result.put(key, features);
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
			xmlReader.parse(new InputSource(r));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return result;
	}

}
