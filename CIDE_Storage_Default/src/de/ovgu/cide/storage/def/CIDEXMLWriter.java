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
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureWithID;

public class CIDEXMLWriter {

	public InputStream serializeAnnotations(
			Map<String, Set<IFeature>> annotations) {

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
					"annotations.dtd");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			hd.setResult(streamResult);
			hd.startDocument();
			// annotations tag.
			hd.startElement("", "", "annotations", new AttributesImpl());
			// key tags.
			for (Entry<String, Set<Long>> annotation : getIdMap(annotations)
					.entrySet()) {
				if (annotation.getValue().isEmpty())
					continue;
				AttributesImpl attributes = new AttributesImpl();
				attributes.addAttribute("", "", "name", "CDATA", annotation
						.getKey());
				String featureIds = "";
				for (Long feature : annotation.getValue()) {
					featureIds += "," + feature.toString();
				}
				attributes.addAttribute("", "", "features", "CDATA", featureIds
						.substring(1));
				hd.startElement("", "", "key", attributes);
				hd.endElement("", "", "key");
			}
			hd.endElement("", "", "annotations");
			hd.endDocument();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	/**
	 * transforms the id2colors map into a form that is serializable with
	 * standard API objects (Long instead of IFeature)
	 * 
	 * @param annotations
	 * 
	 * @return
	 */
	private Map<String, Set<Long>> getIdMap(
			Map<String, Set<IFeature>> annotations) {
		HashMap<String, Set<Long>> result = new HashMap<String, Set<Long>>();
		for (Map.Entry<String, Set<IFeature>> entry : annotations.entrySet()) {
			Set<IFeature> features = entry.getValue();
			if (!features.isEmpty()) {
				Set<Long> ids = new HashSet<Long>();
				for (IFeature feature : features) {
					assert feature instanceof IFeatureWithID;
					ids.add(((IFeatureWithID) feature).getId());
				}
				if (!ids.isEmpty())
					result.put(entry.getKey(), ids);
			}
		}
		return result;
	}
}
