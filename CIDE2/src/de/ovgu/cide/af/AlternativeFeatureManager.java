package de.ovgu.cide.af;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	private Map<IASTNode, List<Alternative>> node2alternatives;	// Mapped einen IASTNode auf eine Liste verfügbarer Alternativen
	
	public AlternativeFeatureManager(ColoredSourceFile coloredSourceFile) {
		this.coloredSourceFile = coloredSourceFile;
	}
	
	private void init() throws CoreException, ParseException {
		if (id2alternatives != null)
			id2alternatives.clear();
		if (node2alternatives != null)
			node2alternatives.clear();
		updateAlternativeList(coloredSourceFile.getAST(), true);
	}
	
	private void updateAlternativeList(List<IASTNode> nodes, boolean recursive) {
		if ((nodes == null) || nodes.isEmpty())
			return;
		
		final List<String> astIDs = new LinkedList<String>();
		final List<IASTNode> allASTNodes = new LinkedList<IASTNode>();
		
		for (IASTNode node : nodes) {
			if (node == null)
				continue;
			if (!recursive) {
				if (!astIDs.contains(node.getId())) {
					astIDs.add(node.getId());
					allASTNodes.add(node);
				}
			}
			else {
				node.accept(new ASTVisitor() {
					@Override
					public boolean visit(IASTNode node) {
						if (!astIDs.contains(node.getId())) {
							astIDs.add(node.getId());
							allASTNodes.add(node);
						}
						return true;
					}
				});
			}
		}
		
		if (id2alternatives == null)
			id2alternatives = new HashMap<String, List<Alternative>>();
		if (node2alternatives == null)
			node2alternatives = new HashMap<IASTNode, List<Alternative>>();
		
		mergeAlternatives(coloredSourceFile.getColorManager().getAlternatives(astIDs, (IFeatureModelWithID) coloredSourceFile.getFeatureModel()), 
							allASTNodes);
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
	
	public Map<IASTNode, List<Alternative>> getNode2Alternatives() throws CoreException, ParseException {
		init();
		return node2alternatives;
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
	
//	private Set<IFeature> getFeatures(long[] ids, IFeatureModelWithID fm) {
//		Set<IFeature> result = new HashSet<IFeature>();
//		for (IFeature feature : fm.getVisibleFeatures()) {
//			for (long id : ids) {
//				if (id == ((IFeatureWithID) feature).getId()) {
//					result.add(feature);
//					break;
//				}
//			}
//		}
//		
//		return result;
//	}
	
	private void mergeAlternatives(Map<String, List<Alternative>> newAlternatives, List<IASTNode> allASTNodes) {
		if ((id2alternatives == null) || (node2alternatives == null) || (newAlternatives == null) || (allASTNodes == null))
			return;
		
		id2alternatives.putAll(newAlternatives);
		for (IASTNode node : allASTNodes) {
			node2alternatives.put(node, id2alternatives.get(node.getId()));
		}
	}
}
