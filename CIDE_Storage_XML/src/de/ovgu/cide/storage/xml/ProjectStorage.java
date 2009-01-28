package de.ovgu.cide.storage.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModelWithID;
import de.ovgu.cide.features.IFeatureWithID;

/**
 * Speichert Annotationen, Alternativen, ... eines Eclipse-Projekts in einer XML-Datei
 * 
 * @author Malte Rosenthal
 */
public class ProjectStorage {
	private final IFile xmlIFile;
	private final File xmlFile;
	private Document dom;
	HashMap<Alternative, Element> alternative2node;
	
	/**
	 * Standard-ID fuer eine Alternative
	 */
	public static final String DEFAULT_ALTID = "First alternative";

	ProjectStorage(IProject project) {
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

	public Map<String, Set<IFeature>> readAnnotations(String path, IFeatureModelWithID featureModel) {
		Map<String, Set<IFeature>> result = new HashMap<String, Set<IFeature>>();
		Element featureAnnotationsElement = findFeatureAnnotationsElement(path);
		
		if (featureAnnotationsElement != null) {
			
			// Alle Annotations-Knoten holen
			NodeList allAnnotations = featureAnnotationsElement.getElementsByTagName("annotation");
			List<Node> activeAnnotations = new ArrayList<Node>(allAnnotations.getLength());
			List<Node> activeAlternatives = new ArrayList<Node>(allAnnotations.getLength());
			
			// Annotations- und Alternativ-Knoten rausfiltern, die nicht aktiv sind
			for (int i = 0; i < allAnnotations.getLength(); ++i) {
				Node annotation = allAnnotations.item(i);
				NodeList alternatives = annotation.getChildNodes();
				
				for (int j = 0; j < alternatives.getLength(); ++j) {
					Node alternative = alternatives.item(j);
					Node isActiveNode = (alternative.getAttributes() == null) ? null : alternative.getAttributes().getNamedItem("isActive");
					
					if ((isActiveNode != null) && isActiveNode.getNodeValue().equals("true")) {
						activeAnnotations.add(annotation);
						activeAlternatives.add(alternative);
					}
				}
			}
			
			// Annotationen aus aktiven Knoten lesen
			for (int i = 0; i < activeAnnotations.size(); ++i) {
				Node annotation = activeAnnotations.get(i);
				Node alternative = activeAlternatives.get(i);
				
				Node astidNode = annotation.getAttributes().getNamedItem("astid");
				assert astidNode != null;
				String astid = astidNode.getNodeValue();
				
				Set<IFeature> features = loadFeatures(alternative, featureModel);

				if (astid != null && features != null && !features.isEmpty())
					result.put(astid, features);
			}			
		}
		
		return result;
	}

	/**
	 * Liest alle Features, die unter dem gegebenen Alternativ-Knoten eingetragen sind
	 * 
	 * @param alternative	Alternativ-Knoten
	 * @param featureModel	Feature-Model
	 * @return	Menge von Features, die unter dem gegebenen Alternativ-Knoten eingetragen sind
	 */
	private Set<IFeature> loadFeatures(Node alternative, IFeatureModelWithID featureModel) {
		Set<IFeature> result = new HashSet<IFeature>();
		NodeList features = alternative.getChildNodes();
		
		for (int i = 0; i < features.getLength(); i++) {
			Node featureNode = features.item(i);
			
			if (featureNode.getNodeName().equals("feature")) {
				Node fidNode = featureNode.getAttributes().getNamedItem("fid");
				assert fidNode != null;
				long fid = Long.parseLong(fidNode.getNodeValue());
				
				if (fid > 0) {
					IFeature feature = featureModel.getFeature(fid);
					if (feature != null)
						result.add(feature);
				}

			}
		}
		
		return result;
	}

	/**
	 * Speichert die gegebenen Annotationen ab. Das Format ist baumartig, also werden zu jeder Entitaet auch eine Liste der IDs aller
	 * Vorgaenger-Entitaeten mitgeliefert.
	 * 
	 * Achtung: jede Liste von Vorgaenger-IDs muss top-down sortiert sein, d.h. der aelteste Vorgaenger muss ganz vorne stehen usw.
	 */
	public boolean storeAnnotations(String path, Map<String, Set<IFeature>> annotations, Map<String, List<String>> parentIDs) {
		try {
			Element featureAnnotationsElement = findFeatureAnnotationsElement(path);
			
			if (featureAnnotationsElement == null) {
				featureAnnotationsElement = dom.createElement("featureannotations");
				dom.getDocumentElement().appendChild(featureAnnotationsElement);
				featureAnnotationsElement.setAttribute("id", path);
			}
			
			for (Entry<String, Set<IFeature>> annotation : annotations.entrySet()) {
				
				// Suche nach einer aktiven Alternative
				Element activeAlternative = findActiveAlternative(featureAnnotationsElement, annotation.getKey());
				
				if (activeAlternative == null) {
					// Keine aktive Alternative gefunden: neue Annotation anlegen
					activeAlternative = (Element) createAnnotationNode(annotation.getKey(), featureAnnotationsElement, 
								parentIDs.get(annotation.getKey()), DEFAULT_ALTID).getChildNodes().item(0);
				} else {
					// Aktive Alternative gefunden: Feature-Annotationen loeschen, damit sie gleich neu gesetzt werden koennen
					List<Node> childNodes = toList(activeAlternative.getChildNodes());
					
					if (childNodes != null) {
						for (Node node : childNodes) {
							if (node.getNodeName().equals("feature"))
								activeAlternative.removeChild(node);
						}
					}
				}
				
				// Feature-Annotationen hinzufuegen
				createFeatureNodes(annotation.getValue(), activeAlternative);
			}
		} catch (RuntimeException t) {
			t.printStackTrace();
			throw t;
		}

		// XML-Datei schreiben
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
	
	/**
	 * Speichert eine neue Alternative.
	 * 
	 * Achtung: die Liste von Vorgaenger-IDs muss wie in storeAnnotation() top-down sortiert sein!
	 */
	public boolean storeNewAlternative(String path, Alternative alternative, Map<String, String> id2oldText) {
		if (alternative == null)
			return false;
		
		String astID = alternative.entityID;
		List<String> parentIDs = alternative.entityParentIDs;
		String altID = alternative.altID;
		Set<IFeature> features = alternative.features;
		
		Element featureAnnotationsElement = findFeatureAnnotationsElement(path);
		
		if (featureAnnotationsElement == null) {
			featureAnnotationsElement = dom.createElement("featureannotations");
			dom.getDocumentElement().appendChild(featureAnnotationsElement);
			featureAnnotationsElement.setAttribute("id", path);
		}
		
		// Suche nach der aktiven Alternative
		Element activeAlternative = findActiveAlternative(featureAnnotationsElement, astID);
		
		if (activeAlternative == null) {
			// Keine aktive Alternative gefunden: neue Annotation anlegen
			// Eigentlich ungewoehnliche Situation, denn eine Alternative sollte von der Oberflaeche aus eigentlich nur zu einer
			// bestehenden Entitaet angelegt werden koennen. Wir unterstuetzen es trotzdem :-)
			activeAlternative = (Element) createAnnotationNode(astID, featureAnnotationsElement, parentIDs, altID).getChildNodes().item(0);
		} else {
			// Aktive Alternative gefunden:
			// Texte auch der aktiven Kinder aktualisieren, Alternative deaktivieren und eine neue, aktive Alternative erzeugen
			
			if (id2oldText != null) {
				setTextContext(activeAlternative, id2oldText.remove(astID));
				
				for (Entry<String, String> entry : id2oldText.entrySet()) {
					Element childAlternative = findActiveAlternative(activeAlternative, entry.getKey());
					if (childAlternative != null)
						setTextContext(childAlternative, entry.getValue());
				}
			}
			
			toggleActivation((Element) activeAlternative.getParentNode(), false);
			activeAlternative = createAlternativeNode(activeAlternative.getParentNode(), altID);
		}
		
		if ((features != null) && (features.size() > 0))
			createFeatureNodes(features, activeAlternative);	// Feature-Annotationen hinzufuegen
		
		// XML-Datei schreiben
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
	
	public boolean activateAlternative(String path, Alternative alternative, Map<String, String> id2oldText) {
		if (alternative == null)
			return false;
		Element featureAnnotationsElement = findFeatureAnnotationsElement(path);
		
		if (featureAnnotationsElement != null) {
			Element activeAlternative = findActiveAlternative(featureAnnotationsElement, alternative.entityID);
			Element newAlternativeNode = alternative2node.get(alternative);
			
			if (activeAlternative != null) {
				if (id2oldText != null) {
					setTextContext(activeAlternative, id2oldText.remove(alternative.entityID));
					
					for (Entry<String, String> entry : id2oldText.entrySet()) {
						Element childAlternative = findActiveAlternative(activeAlternative, entry.getKey());
						if (childAlternative != null)
							setTextContext(childAlternative, entry.getValue());
					}
				}
				
				Node parent = activeAlternative.getParentNode();
				NodeList allAlternatives = parent.getChildNodes();
				
				for (int i = 0; i < allAlternatives.getLength(); ++i) {
					Element element = (Element) allAlternatives.item(i);
					if (!element.equals(newAlternativeNode))
						toggleActivation(element, false);
				}
			}
			
			toggleActivation(newAlternativeNode, true);
		} else
			return false;
		
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
	
	public Map<String, List<Alternative>> getAlternatives(String path, List<String> ids) {
		if ((ids == null) || (ids.size() < 1))
			return null;
		
		Element featureAnnotationsElement = findFeatureAnnotationsElement(path);
		if (featureAnnotationsElement == null)
			return null;
		
		if (alternative2node == null)
			alternative2node = new HashMap<Alternative, Element>();
		
		NodeList annotations = featureAnnotationsElement.getElementsByTagName("annotation");
		Map<String, List<Alternative>> result = new HashMap<String, List<Alternative>>();
		for (int i = 0; i < annotations.getLength(); ++i) {
			Element annotation = (Element) annotations.item(i);
			String astid = annotation.getAttributes().getNamedItem("astid").getNodeValue();
			if (ids.contains(astid)) {
				NodeList alternatives = annotation.getChildNodes();
				
				List<Alternative> altList = null;
				if (alternatives.getLength() > 0) {
					if (result.containsKey(astid)) 
						altList = result.get(astid);
					else {
						altList = new LinkedList<Alternative>();
						result.put(astid, altList);
					}
				}
				
				for (int j = 0; j < alternatives.getLength(); ++j) {
					Element alternative = (Element) alternatives.item(j);
					if (alternative.getNodeName().equals("alternative") && parentIsActive(alternative)) {
						Alternative alt = buildAlternative(alternative).setEntityID(astid);
						altList.add(alt);
						alternative2node.put(alt, alternative);
					}
				}
			}
		}
		
		return result;
	}
	
	private boolean parentIsActive(Element alternative) {
		if (alternative == null)
			return false;
		
		Node parent = alternative.getParentNode().getParentNode();
		if (parent != null) {
			if (parent.getNodeName().equals("featureannotations"))
				return true;
			if (parent.getNodeName().equals("alternative")) {
				return parent.getAttributes().getNamedItem("isActive").getNodeValue().equals("true");
			}
		}
		
		return false;
	}
	
	private Alternative buildAlternative(Element alternative) {
		if (alternative == null)
			return null;
		
		return (new Alternative(alternative.getAttribute("altid"), null, null, null))
					.setActive(alternative.getAttribute("isActive").equals("true")).setText(getText(alternative));
	}
	
	private String getText(Element alternative) {
		if (alternative == null)
			return null;
		
		NodeList childNodes = alternative.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node node = childNodes.item(i);
			if (node.getNodeName().equals("text")) {
				return node.getTextContent();
			}
		}
		
		return null;
	}

	/**
	 * (De)aktiviert die Alternative hinter dem gegebenen Element. Bei der Deaktivierung werden auch alle Kindknoten deaktiviert.
	 * Bei der Aktivierung wird nur diejenige Kind-Alternative aktiviert, für die wasActive == "true" gilt.
	 * 
	 * @param element	Zu (de)aktivierendes Element
	 * @param isActive	Indikator: Element soll aktiviert werden
	 */
	private void toggleActivation(Element element, boolean isActive) {
		if (element == null)
			return;
		
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (isActive)
					reactivate((Element) childNode);
				else
					toggleActivation((Element) childNode, isActive);
			}
		}
		
		if (element.getNodeName().equals("alternative")) {
			boolean wasActive = element.getAttribute("isActive").equals("true");
			element.setAttribute("isActive", isActive ? "true" : "false");
			element.setAttribute("wasActive", wasActive ? "true" : "false");
		}
	}
	
	private void reactivate(Element element) {
		if (element == null)
			return;
		
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE)
				reactivate((Element) childNode);
		}
		
		if (element.getNodeName().equals("alternative") && element.getAttribute("wasActive").equals("true")) {
			boolean wasActive = element.getAttribute("isActive").equals("true");
			element.setAttribute("isActive", "true");
			element.setAttribute("wasActive", wasActive ? "true" : "false");
		}
	}
	
	private void setTextContext(Element alternative, String text) {
		if ((alternative == null) || (text == null))
			return;
		
		NodeList childNodes = alternative.getChildNodes();
		Element textElement = null;
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node node = childNodes.item(i);
			if (node.getNodeName().equals("text")) {
				textElement = (Element) node;
				break;
			}
		}
		
		if (textElement == null) {
			textElement = dom.createElement("text");
			alternative.appendChild(textElement);
		}
		
		textElement.setTextContent(text);
	}
	
	/**
	 * Erzeugt einen Annotations-Knoten unter gegebenen Vorgaenger-Knoten. Dabei wird die ganze Vorgaenger-Hierarchie
	 * aufgebaut, sofern noch nicht vorhanden. Unter dem Annotations-Knoten wird auch ein Alternativ-Knoten angelegt.
	 *
	 * @return	Neu angelegter Annotations-Knoten
	 */
	private Element createAnnotationNode(String astID, Element grandParent, List<String> parentIDs, String altID) {
		if (grandParent == null)
			return null;
		
		boolean insertParent = false;	// Indikator: ab jetzt auch Vorgaenger-Knoten anlegen
		Element parent = grandParent;
		if (parentIDs != null) {
			for (String parentID : parentIDs) {
				grandParent = parent;
				if (insertParent) {
					parent = createAnnotationNode(parentID, findActiveAlternative(grandParent), DEFAULT_ALTID);
				} else {
					parent = findActiveAlternative(parent, parentID);
					insertParent |= (parent == null);
					
					if (parent == null) {
						parent = createAnnotationNode(parentID, findActiveAlternative(grandParent), DEFAULT_ALTID);
					} else {
						parent = (Element) parent.getParentNode();
					}
				}
			}
		}
		
		return createAnnotationNode(astID, (Element) parent.getChildNodes().item(0), altID);
	}
	
	/**
	 * Legt einen Annotations-Knoten mit darunter liegendem Alternativ-Knoten an. Die Vorgaenger-Hierarchie wird hier nicht
	 * mit angelegt.
	 *
	 * @return	Neu angelegter Annotations-Knoten
	 */
	private Element createAnnotationNode(String astID, Element parent, String altID) {
		if (parent == null)
			return null;
		
		Element annotationElement = dom.createElement("annotation");
		annotationElement.setAttribute("astid", astID);
		
		createAlternativeNode(annotationElement, altID);		
		parent.appendChild(annotationElement);
		
		return annotationElement;
	}
	
	/**
	 * Legt einen Alternativ-Knoten an
	 * 
	 * @return	Neu angelegter Alternativ-Knoten
	 */
	private Element createAlternativeNode(Node parentNode, String altID) {
		if (parentNode == null)
			return null;
		
		Element activeAlternative = dom.createElement("alternative");
		activeAlternative.setAttribute("altid", altID);
		activeAlternative.setAttribute("isActive", "true");
		
		parentNode.appendChild(activeAlternative);
		
		return activeAlternative;
	}
	
	private void createFeatureNodes(Set<IFeature> features, Node parentNode) {
		if ((features == null) || (parentNode == null))
			return;
		
		List<String> featureIDs = new LinkedList<String>();
		for (IFeature feature : features) {
			assert feature instanceof IFeatureWithID;
			featureIDs.add(Long.toString(((IFeatureWithID) feature).getId()));
		}
		
		createFeatureNodes(featureIDs, parentNode);
	}
	
	private void createFeatureNodes(List<String> featureIDs, Node parentNode) {
		if ((featureIDs == null) || (parentNode == null))
			return;
		
		for (String featureID : featureIDs) {
			Element featureElement = dom.createElement("feature");
			featureElement.setAttribute("fid", featureID);
			parentNode.appendChild(featureElement);
		}
	}
	
	private Element findFeatureAnnotationsElement(String path) {
		NodeList childNodes = dom.getDocumentElement().getChildNodes();
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			
			if (childNode.getNodeName().equals("featureannotations") && path.equals(childNode.getAttributes().getNamedItem("id").getNodeValue())) {
				return (Element) childNode;
			}
		}
		
		return null;
	}
	
	/**
	 * Sucht ausgehend von dem gegebenen Element den aktiven Alternativ-Knoten zur gegebenen ID.
	 * 
	 * @return	Aktiver Alternativ-Knoten || null, falls nicht gefunden
	 */
	private Element findActiveAlternative(Element parentElement, String astID) {
		NodeList annotations = parentElement.getElementsByTagName("annotation");
		
		for (int i = 0; i < annotations.getLength(); ++i) {
			Node annotation = annotations.item(i);
			
			if (annotation.getNodeName().equals("annotation") 
					&& annotation.getAttributes().getNamedItem("astid").getNodeValue().equals(astID)) {
				Element result = findActiveAlternative(annotation);
				if (result != null)
					return result;
			}
		}
		
		return null;
	}
	
	/**
	 * Sucht innerhalb des gegebenen Annotations-Knotens den aktiven Alternativ-Knoten. Es wird nicht tiefer gesucht.
	 * 
	 * @return	Aktiver Alternativ-Knoten || annotation, falls annotation ein featureannotations-Knoten ist || null, falls nicht gefunden
	 */
	private Element findActiveAlternative(Node annotation) {
		if (annotation == null)
			return null;
		
		if (annotation.getNodeName().equals("featureannotations"))
			return (Element) annotation;
		
		NodeList alternatives = annotation.getChildNodes();
		
		for (int i = 0; i < alternatives.getLength(); ++i) {
			Node alternative = alternatives.item(i);
			
			if (alternative.getNodeName().equals("alternative") 
					&& alternative.getAttributes().getNamedItem("isActive").getNodeValue().equals("true"))
				return (Element) alternative;
		}
		
		return null;
	}
	
	private List<Node> toList(NodeList nodeList) {
		if (nodeList == null) 
			return null;
		
		LinkedList<Node> result = new LinkedList<Node>();
		for (int i = 0; i < nodeList.getLength(); ++i) {
			result.add(nodeList.item(i));
		}
		
		return result;
	}

	private void serializeDom() throws IOException, TransformerException, CoreException {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(dom);
		StreamResult result = new StreamResult(new FileWriter(xmlFile));
		
		transformer.transform(source, result);
		xmlIFile.refreshLocal(0, null);
	}
}
