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
import de.ovgu.cide.af.RootAlternative;
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
				e.printStackTrace();
			} catch (IOException e) {
				dom = newDom(parser);
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e1) {
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
	public boolean storeAnnotations(String path, Map<String, Set<IFeature>> annotations, Map<String, Boolean> isOptional, 
									Map<String, List<String>> parentIDs) {
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
					activeAlternative = (Element) createAnnotationNode(annotation.getKey(), isOptional, featureAnnotationsElement, 
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
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (CoreException e) {
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
		String altID = alternative.altID;
		Set<IFeature> features = alternative.ownFeatures;
		
		Element featureAnnotationsElement = findFeatureAnnotationsElement(path);
		
		if (featureAnnotationsElement == null) {
			featureAnnotationsElement = dom.createElement("featureannotations");
			dom.getDocumentElement().appendChild(featureAnnotationsElement);
			featureAnnotationsElement.setAttribute("id", path);
		}
		
		// Suche nach der aktiven Alternative
		Element activeAlternative = findActiveAlternative(featureAnnotationsElement, astID);
		
		if (activeAlternative == null) {
			System.err.println("Fatal error: You cannot create an alternative for a not existing node.");
			return false;
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
			
			NodeList childNodes = activeAlternative.getParentNode().getChildNodes();
			for (int i = 0; i < childNodes.getLength(); ++i) {
				deactivateElement((Element) childNodes.item(i));
			}
			
			activeAlternative = createAlternativeNode(activeAlternative.getParentNode(), altID);
		}
		
		if ((features != null) && (features.size() > 0))
			createFeatureNodes(features, activeAlternative);	// Feature-Annotationen hinzufuegen
		
		// XML-Datei schreiben
		try {
			serializeDom();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (CoreException e) {
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
						deactivateElement(element);
				}
			}
			
			activateElement(newAlternativeNode);
		} else
			return false;
		
		try {
			serializeDom();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Gibt alle Alternativen aller AST-Knoten zurück.
	 * @param path
	 * @param featureModel
	 * @return
	 */
	public Map<String, List<Alternative>> getAlternatives(String path, IFeatureModelWithID featureModel) {		
		Element featureAnnotationsElement = findFeatureAnnotationsElement(path);
		if (featureAnnotationsElement == null)
			return null;
		
		if (alternative2node == null)
			alternative2node = new HashMap<Alternative, Element>();
		
		Map<String, List<Alternative>> result = new HashMap<String, List<Alternative>>();
		NodeList topLevelAnnotations = featureAnnotationsElement.getChildNodes();
		Alternative rootAlternative = new RootAlternative();
		
		for (int i = 0; i < topLevelAnnotations.getLength(); ++i) {
			result.putAll(getAlternatives((Element) topLevelAnnotations.item(i), rootAlternative, featureModel));
		}
		
		return result;
	}
	
	/**
	 * Gibt alle Alternativen aller AST-Knoten ab dem gegebenen Annotations-Knoten zurück.
	 * @param annotation
	 * @param parent
	 * @param featureModel
	 * @return
	 */
	private Map<String, List<Alternative>> getAlternatives(Element annotation, Alternative parent, IFeatureModelWithID featureModel) {
		String astid = annotation.getAttributes().getNamedItem("astid").getNodeValue();
		boolean isOptional = annotation.getAttributes().getNamedItem("isOptional").getNodeValue().equals("true");
		
		Map<String, List<Alternative>> result = new HashMap<String, List<Alternative>>();
		List<Alternative> altList = new LinkedList<Alternative>();
		result.put(astid, altList);
		
		NodeList alternatives = annotation.getChildNodes();
		for (int i = 0; i < alternatives.getLength(); ++i) {
			Element alternative = (Element) alternatives.item(i);
			Alternative alt = buildAlternative(alternative, astid, isOptional, featureModel);
			altList.add(alt);
			
			if (parent != null) {
				parent.addChild(alt);
				alt.setParent(parent);
			}
			
			if (parentIsActive(alternative))
				alternative2node.put(alt, alternative);
			
			NodeList subAnnotations = alternative.getChildNodes();
			for (int j = 0; j < subAnnotations.getLength(); ++j) {
				if (subAnnotations.item(j).getNodeName().equals("annotation")) {
					Element subAnnotation = (Element) subAnnotations.item(j);
					mergeId2AlternativesMap(result, getAlternatives(subAnnotation, alt, featureModel));
				}
			}
		}
		
		return result;
	}
	
	private void mergeId2AlternativesMap(Map<String, List<Alternative>> m1, Map<String, List<Alternative>> m2) {
		if ((m1 == null) || (m2 == null))
			return;
		
		for (Entry<String, List<Alternative>> entry : m2.entrySet()) {
			if (m1.containsKey(entry.getKey())) {
				m1.get(entry.getKey()).addAll(entry.getValue());
			} else {
				m1.put(entry.getKey(), entry.getValue());
			}
		}
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
	
	private Alternative buildAlternative(Element alternative, String astid, boolean isOptional, IFeatureModelWithID featureModel) {
		if (alternative == null)
			return null;
		
		List<String> parentIDs = new LinkedList<String>();
		Set<IFeature> inheritedFeatures = new HashSet<IFeature>();
		
		Node parent = alternative.getParentNode().getParentNode();
		while (!parent.getNodeName().equals("featureannotations")) {
			if (parent.getNodeName().equals("alternative")) {
				inheritedFeatures.addAll(loadFeatures(parent, featureModel));
			} else if (parent.getNodeName().equals("annotation")) {
				Node astidNode = parent.getAttributes().getNamedItem("astid");
				if (astidNode != null)
					parentIDs.add(0, astidNode.getNodeValue());
			}
			
			parent = parent.getParentNode();
		}
		
		return (new Alternative(alternative.getAttribute("altid"), astid, isOptional, parentIDs, loadFeatures(alternative, featureModel)))
					.setActive(alternative.getAttribute("isActive").equals("true")).setText(getText(alternative)).setInheritedFeatures(inheritedFeatures);
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
	 * Deaktiviert die Alternativen hinter dem gegebenen Element. Bei der Deaktivierung werden auch alle Kindknoten deaktiviert.
	 * 
	 * @param element	Zu (de)aktivierendes Element
	 * @param isActive	Indikator: Element soll aktiviert werden
	 */
	private void deactivateElement(Element element) {
		if (element == null)
			return;
		
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node childNode = childNodes.item(i);
			
			// Rekursiver Abstieg nur dann, wenn Alternative gerade aktiv ist, denn wasActive darf nur bei Kindern von Alternativen
			// auf false gesetzt werden, die gerade aktiv sind.
			if ((childNode.getNodeType() == Node.ELEMENT_NODE)
					// Wenn das Element ein alternative-Knoten ist, dann muss er aktiv sein
					&& (!element.getNodeName().equals("alternative") || element.getAttribute("isActive").equals("true"))) {
				deactivateElement((Element) childNode);
			}
		}
		
		if (element.getNodeName().equals("alternative")) {
			boolean wasActive = element.getAttribute("isActive").equals("true");
			element.setAttribute("isActive", "false");
			element.setAttribute("wasActive", wasActive ? "true" : "false");
		}
	}
	
	/**
	 * Aktiviert das gegebene Element in jedem Fall (d.h. auch wenn wasActive == false) und aktiviert die Kindknoten
	 * genau dann, wenn wasActive == true.
	 * @param element	Zu aktivierendes Element
	 */
	private void activateElement(Element element) {
		if (element == null)
			return;
		
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				reactivate((Element) childNode);
			}
		}
		
		if (element.getNodeName().equals("alternative")) {
			boolean wasActive = element.getAttribute("isActive").equals("true");
			element.setAttribute("isActive", "true");
			element.setAttribute("wasActive", wasActive ? "true" : "false");
		}
	}
	
	/**
	 * Aktiviert das gegebene Element und alle seine Kindknoten genau dann, wenn wasActive == true.
	 * @param element	Zu aktivierendes Element
	 */
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
	private Element createAnnotationNode(String astID, Map<String, Boolean> isOptional, Element grandParent, List<String> parentIDs, String altID) {
		if (grandParent == null)
			return null;
		
		boolean insertParent = false;	// Indikator: ab jetzt auch Vorgaenger-Knoten anlegen
		Element parent = grandParent;
		if (parentIDs != null) {
			for (String parentID : parentIDs) {
				grandParent = (Element) (parent.getNodeName().equals("alternative") ? parent.getParentNode() : parent);
				if (insertParent) {
					parent = (Element) createAnnotationNode(parentID, isOptional.get(parentID), findActiveAlternative(grandParent), DEFAULT_ALTID)
									   .getChildNodes().item(0);
				} else {
					parent = findActiveAlternative(parent, parentID);
					insertParent |= (parent == null);
					
					if (parent == null) {
						parent = (Element) createAnnotationNode(parentID, isOptional.get(parentID), findActiveAlternative(grandParent), DEFAULT_ALTID)
										   .getChildNodes().item(0);
					}
				}
			}
		}
		
		return createAnnotationNode(astID, isOptional.get(astID), parent, altID);
	}
	
	/**
	 * Legt einen Annotations-Knoten mit darunter liegendem Alternativ-Knoten an. Die Vorgaenger-Hierarchie wird hier nicht
	 * mit angelegt.
	 *
	 * @return	Neu angelegter Annotations-Knoten
	 */
	private Element createAnnotationNode(String astID, boolean isOptional, Node parent, String altID) {
		if (parent == null)
			return null;
		
		Element annotationElement = dom.createElement("annotation");
		annotationElement.setAttribute("astid", astID);
		annotationElement.setAttribute("isOptional", isOptional ? "true" : "false");
		
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
