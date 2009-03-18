package de.ovgu.cide.af;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gparser.ParseException;
import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.features.IFeatureModelWithID;
import de.ovgu.cide.features.source.ColoredSourceFile;

/**
 * Manager, der alternative Features eines ColoredSourceFile's verwaltet. Kann aus einem
 * ColoredSourceFile per getAltFeatureManager() geholt werden.
 * 
 * @author Malte Rosenthal
 */
public class AlternativeFeatureManager {
	
	private ColoredSourceFile coloredSourceFile;
	private Map<String, List<Alternative>> id2alternatives;		// Mapped eine ASTNode-ID auf eine Liste verfügbarer Alternativen
	private final Map<String, IASTNode> id2node;				// Mapped eine ASTNode-ID auf den AST-Knoten mit dieser ID
	
	public AlternativeFeatureManager(ColoredSourceFile coloredSourceFile) {
		this.coloredSourceFile = coloredSourceFile;
		id2node = new HashMap<String, IASTNode>();
	}
	
	private void init() throws CoreException, ParseException {
		if (id2alternatives != null)
			id2alternatives.clear();
		if (id2node != null)
			id2node.clear();
		
		updateAlternativeList(coloredSourceFile.getAST(), true);
	}
	
	private void updateAlternativeList(List<IASTNode> nodes, boolean recursive) {
		if ((nodes == null) || nodes.isEmpty())
			return;
		
		final List<String> astIDs = new LinkedList<String>();
		
		for (IASTNode node : nodes) {
			if (node == null)
				continue;
			if (!recursive) {
				if (!astIDs.contains(node.getId())) {
					astIDs.add(node.getId());
					id2node.put(node.getId(), node);
				}
			}
			else {
				node.accept(new ASTVisitor() {
					@Override
					public boolean visit(IASTNode node) {
						if (!astIDs.contains(node.getId())) {
							astIDs.add(node.getId());
							id2node.put(node.getId(), node);
						}
						return true;
					}
				});
			}
		}
		
		if (id2alternatives == null)
			id2alternatives = new HashMap<String, List<Alternative>>();
		
		mergeAlternatives(coloredSourceFile.getColorManager().getAlternatives(astIDs, (IFeatureModelWithID) coloredSourceFile.getFeatureModel()));
	}
	
	private void updateAlternativeList(IASTNode node, boolean recursive) {
		if (node == null)
			return;
		
		List<IASTNode> nodes = new LinkedList<IASTNode>();
		nodes.add(node);
		updateAlternativeList(nodes, recursive);
	}
	
	public List<Alternative> getAlternatives(String id) throws CoreException, ParseException {
		init();
		if (id2alternatives == null)
			return null;
		return id2alternatives.get(id);
	}
	
	public Map<String, List<Alternative>> getAlternatives() {
		return coloredSourceFile.getColorManager().getAlternatives(null, (IFeatureModelWithID) coloredSourceFile.getFeatureModel());
	}
	
	/**
	 * Gibt alle AST-Knoten zurück, zu denen es mindestens zwei Alternativen gibt
	 * @return
	 * @throws CoreException
	 * @throws ParseException
	 */
	public List<IASTNode> getAlternativeNodes() throws CoreException, ParseException {
		init();
		List<IASTNode> result = new LinkedList<IASTNode>();
		
		for (Entry<String, List<Alternative>> entry : id2alternatives.entrySet()) {
			if ((entry.getValue() != null) && (entry.getValue().size() > 1))
				result.add(id2node.get(entry.getKey()));
		}
		
		return result;
	}
	
	public void createAlternative(List<IASTNode> nodes, String altID) {
		if ((nodes == null) || nodes.isEmpty())
			return;
		
		for (IASTNode node : nodes) {
			if (node != null)
				coloredSourceFile.getColorManager().createAlternative(node, altID);
		}
		
		CIDECorePlugin.getDefault().notifyListeners(new ASTColorChangedEvent(this, nodes, coloredSourceFile));
	}
	
	/**
	 * Diese Methode wird nach dem Wechseln zu einer anderen Alternative eines AST-Knotens aufgerufen. Die neue Alternative
	 * wurde bereits ins Document geschrieben und gespeichert. Der hier übergebene AST-Knoten ist aber der alte AST-Knoten,
	 * also nicht mehr aktuell, weil beim Speichern ein neuer Parse-Lauf gestartet wird.
	 *  
	 * @param alternative
	 * @param node	Alter AST-Knoten
	 * @throws CoreException
	 * @throws ParseException
	 */
	public void activateAlternative(Alternative alternative, final IASTNode node) throws CoreException, ParseException {
		if (node == null)
			return;
		
		coloredSourceFile.getColorManager().activateAlternative(alternative, node);
		CIDECorePlugin.getDefault().notifyListeners(new ASTColorChangedEvent(this, node, coloredSourceFile));
	}
	
	private void mergeAlternatives(Map<String, List<Alternative>> newAlternatives) {
		// Diese Methode war schonmal komplexer ;-)
		if ((id2alternatives == null) || (newAlternatives == null))
			return;
		id2alternatives.putAll(newAlternatives);
	}
}
