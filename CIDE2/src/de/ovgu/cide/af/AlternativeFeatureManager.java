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
import de.ovgu.cide.features.source.ColoredSourceFile;

/**
 * Manager, der alternative Features eines ColoredSourceFile's verwaltet. Kann aus einem
 * ColoredSourceFile per getAltFeatureManager() geholt werden.
 * 
 * @author Malte Rosenthal
 */
public class AlternativeFeatureManager {
	
	private ColoredSourceFile coloredSourceFile;
	private Map<String, List<Alternative>> id2alternatives;		// Mapped eine ASTNode-ID auf eine Liste verf�gbarer Alternativen
	
	public AlternativeFeatureManager(ColoredSourceFile coloredSourceFile) {
		this.coloredSourceFile = coloredSourceFile;
		try {
			updateAlternativeList(coloredSourceFile.getAST(), true);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateAlternativeList(List<IASTNode> nodes, boolean recursive) {
		if ((nodes == null) || nodes.isEmpty())
			return;
		
		final List<String> astIDs = new LinkedList<String>();
		
		for (IASTNode node : nodes) {
			if (node == null)
				continue;
			if (!recursive) {
				if (!astIDs.contains(node.getId()))
					astIDs.add(node.getId());
			}
			else {
				node.accept(new ASTVisitor() {
					@Override
					public boolean visit(IASTNode node) {
						if (!astIDs.contains(node.getId()))
							astIDs.add(node.getId());
						return true;
					}
				});
			}
		}
		
		if (id2alternatives == null)
			id2alternatives = new HashMap<String, List<Alternative>>();
		mergeAlternatives(coloredSourceFile.getColorManager().getAlternatives(astIDs));
	}
	
	private void updateAlternativeList(IASTNode node, boolean recursive) {
		if (node == null)
			return;
		
		List<IASTNode> nodes = new LinkedList<IASTNode>();
		nodes.add(node);
		updateAlternativeList(nodes, recursive);
	}
	
	public List<Alternative> getAlternatives(String id) {
		if (id2alternatives == null)
			return null;
		return id2alternatives.get(id);
	}

	public void createAlternative(List<IASTNode> nodes, String altID) {
		if ((nodes == null) || nodes.isEmpty())
			return;
		
		for (IASTNode node : nodes) {
			if (node != null)
				coloredSourceFile.getColorManager().createAlternative(node, altID);
		}
		
		CIDECorePlugin.getDefault().notifyListeners(new ASTColorChangedEvent(this, nodes, coloredSourceFile));
		updateAlternativeList(nodes, true);
	}
	
	public void activateAlternative(Alternative alternative, IASTNode node) {
		coloredSourceFile.getColorManager().activateAlternative(alternative, node);
		CIDECorePlugin.getDefault().notifyListeners(new ASTColorChangedEvent(this, node, coloredSourceFile));
		updateAlternativeList(node, true);
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
	
	private void mergeAlternatives(Map<String, List<Alternative>> newAlternatives) {
		if ((id2alternatives == null) || (newAlternatives == null))
			return;
		id2alternatives.putAll(newAlternatives);
	}
}
