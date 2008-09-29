package de.ovgu.cide.storage.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import coloredide.features.IFeature;
import coloredide.features.IFeatureModelWithID;
import coloredide.features.IFeatureWithID;

public class ProjectStorage {
	private final IProject project;
	private final IFile xmlIFile;
	private final File xmlFile;
	private Document dom;

	ProjectStorage(IProject project) {
		this.project = project;
		xmlIFile = project.getFile("colors.xml");
		assert xmlIFile != null;
		xmlFile = xmlIFile.getLocation().toFile();
		assert xmlFile != null;
		load();
	}

	private void load() {
		DocumentBuilder parser;
		try {
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try {
				if (xmlFile.exists())
					dom = parser.parse(xmlFile);
				else
					dom = newDom(parser);
			} catch (SAXException e) {
				dom = newDom(parser);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				dom = newDom(parser);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private Document newDom(DocumentBuilder parser) {
		Document newd = parser.newDocument();
		Element ann = newd.createElement("annotations");
		newd.appendChild(ann);
		return newd;
	}

	public Map<String, Set<IFeature>> read(String path,
			IFeatureModelWithID featureModel) {

		Map<String, Set<IFeature>> result = new HashMap<String, Set<IFeature>>();

		Element fileColorsElement = findFileColorElement(path);
		if (fileColorsElement != null) {

			NodeList keyEntries = fileColorsElement.getChildNodes();
			for (int keyidx = 0; keyidx < keyEntries.getLength(); keyidx++) {
				Node entry = keyEntries.item(keyidx);
				if (entry.getNodeName().equals("annotation")) {
					Node keyNode = entry.getAttributes().getNamedItem("astid");
					assert keyNode != null;
					String key = keyNode.getNodeValue();

					Set<IFeature> features = loadFeatures(keyNode, featureModel);

					if (key != null && features != null && !features.isEmpty())
						result.put(key, features);
				}
			}
		}
		return result;
	}

	private Set<IFeature> loadFeatures(Node keyNode,
			IFeatureModelWithID featureModel) {
		Set<IFeature> result = new HashSet<IFeature>();
		NodeList featureNodes = keyNode.getChildNodes();
		for (int featureIdx = 0; featureIdx < featureNodes.getLength(); featureIdx++) {
			Node entry = featureNodes.item(featureIdx);
			if (entry.getNodeName().equals("feature")) {
				Node idNode = entry.getAttributes().getNamedItem("fid");
				assert idNode != null;
				long id = Long.parseLong(idNode.getNodeValue());
				IFeature feature;
				if (id > 0) {
					feature = featureModel.getFeature(id);
					if (feature != null)
						result.add(feature);
				}

			}
		}
		return result;
	}

	public boolean store(String path, Map<String, Set<IFeature>> annotations) {
		try {
			Element fileColorsElement = findFileColorElement(path);
			if (fileColorsElement == null) {
				fileColorsElement = dom.createElement("featureannotations");
				dom.getDocumentElement().appendChild(fileColorsElement);
				fileColorsElement.setAttribute("id", path);
			}
			// remove existing nodes
			NodeList keyEntries = fileColorsElement.getChildNodes();
			for (int keyidx = 0; keyidx < keyEntries.getLength(); keyidx++) {
				Node entry = keyEntries.item(keyidx);
				fileColorsElement.removeChild(entry);
			}
			// add nodes based on hashmap
			for (Entry<String, Set<IFeature>> annotation : annotations
					.entrySet()) {
				Element annotationElement = dom.createElement("annotation");
				annotationElement.setAttribute("astid", annotation.getKey());

				for (IFeature feature : annotation.getValue()) {
					Element featureElement = dom.createElement("feature");
					assert feature instanceof IFeatureWithID;
					featureElement.setAttribute("fid", Long
							.toString(((IFeatureWithID) feature).getId()));

					annotationElement.appendChild(featureElement);
				}
				fileColorsElement.appendChild(annotationElement);
			}
		} catch (RuntimeException t) {
			System.out.println(t);
			throw t;
		}

		try {
			serializeDom();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private Element findFileColorElement(String path) {
		NodeList keyEntries = dom.getDocumentElement().getChildNodes();
		for (int keyidx = 0; keyidx < keyEntries.getLength(); keyidx++) {
			Node entry = keyEntries.item(keyidx);
			if (entry.getNodeName().equals("featureannotations")) {
				if (path.equals(entry.getAttributes().getNamedItem("id")
						.getNodeValue()))
					return (Element) entry;
			}
		}
		return null;
	}

	private void serializeDom() throws IOException, TransformerException,
			CoreException {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(dom);

		StreamResult result = new StreamResult(new FileWriter(xmlFile));
		transformer.transform(source, result);
		xmlIFile.refreshLocal(0, null);
	}
}
