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

package de.ovgu.cide.alternativefeatures;

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
 *  * NOTE: the entire functionality for alternative features was implemented as part of a master's thesis
 * (available in German here: http://wwwiti.cs.uni-magdeburg.de/~ckaestne/thesisrosenthal.pdf)
 * the functionality has been deactivated mostly, but the code is still included.
 * 
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
		if (coloredSourceFile.isColored())
			id2alternatives = coloredSourceFile.getColorManager().getAllAlternatives((IFeatureModelWithID) coloredSourceFile.getFeatureModel());
		
		id2node.clear();
		coloredSourceFile.getAST().accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				id2node.put(node.getId(), node);
				return true;
			}
		});
	}
		
	/**
	 * Gibt alle Alternativen aller AST-Knoten zurück.
	 */
	public Map<String, List<Alternative>> getAllAlternatives() {
		return coloredSourceFile.getColorManager().getAllAlternatives((IFeatureModelWithID) coloredSourceFile.getFeatureModel());
	}
	
	/**
	 * Gibt eine Liste von Alternativen zum AST-Knoten mit gegebener ID zurück, dessen Vater-Alternative aktiv ist.
	 * 
	 * Dabei werden Änderungen an der aktiven Alternative, die noch nicht in die XML-Datei zurückgespeichert wurden, nicht berücksichtigt.
	 * Steht die aktive Alternative also noch gar nicht in der XML-Datei, so wird sie hier auch nicht zurückgegeben.
	 * @param id
	 * @return
	 * @throws CoreException
	 * @throws ParseException
	 */
	public List<Alternative> getAlternativesWithActiveParent(String id) throws CoreException, ParseException {
		init();
		if (id2alternatives == null)
			return null;
		
		List<Alternative> result = new LinkedList<Alternative>();
		List<Alternative> allAlternatives = id2alternatives.get(id);
		
		if (allAlternatives != null) {
			for (Alternative alternative : allAlternatives) {
				if (alternative.parentIsActive())
					result.add(alternative);
			}
		}
		
		return result;
	}
	
	/**
	 * Gibt alle AST-Knoten zurück, zu denen es mindestens zwei Alternativen gibt, dessen Vater-Alternative aktiv ist.
	 * @return
	 * @throws CoreException
	 * @throws ParseException
	 */
	public List<IASTNode> getAlternativeNodesWithActiveParent() throws CoreException, ParseException {
		init();
		if (id2alternatives == null)
			return null;
		
		List<IASTNode> result = new LinkedList<IASTNode>();
		
		for (Entry<String, List<Alternative>> entry : id2alternatives.entrySet()) {
			Map<Alternative, List<Alternative>> alternativesGroupedByParent = groupByParent(entry.getValue());
			if (alternativesGroupedByParent != null) {
				for (Entry<Alternative, List<Alternative>> group : alternativesGroupedByParent.entrySet()) {
					if (group.getKey().isActive && (group.getValue() != null) && (group.getValue().size() > 1)) {
						result.add(id2node.get(entry.getKey()));
						break;
					}
				}
			}
		}
		
		return result;
	}
	
	public Map<Alternative, List<Alternative>> groupByParent(List<Alternative> alternatives) {
		if (alternatives == null)
			return null;
		
		Map<Alternative, List<Alternative>> result = new HashMap<Alternative, List<Alternative>>();
		for (Alternative alternative : alternatives) {
			if (result.containsKey(alternative.getParent())) {
				result.get(alternative.getParent()).add(alternative);
			} else {
				List<Alternative> list = new LinkedList<Alternative>();
				list.add(alternative);
				result.put(alternative.getParent(), list);
			}
		}
		
		return result;
	}
	
	/**
	 * Gibt eine Liste von Alternativen zum gegebenen AST-Knoten innerhalb der gegebenen Eltern-Alternative zurück.
	 * Dabei werden Änderungen an der aktiven Alternative, die noch nicht in die XML-Datei zurückgespeichert wurden, mitberücksichtigt.
	 * 
	 * @param node
	 * @param parent
	 * @return
	 * @throws CoreException
	 * @throws ParseException
	 */
	public List<Alternative> getAlternatives(IASTNode node, Alternative parent) throws CoreException, ParseException {
		if (node == null)
			return null;

		init();
		List<Alternative> result = new LinkedList<Alternative>();
		if (id2alternatives == null) {
			result.add(new Alternative(node, parent.getFeatures()));
			return result;
		}
		
		List<Alternative> alternatives = id2alternatives.get(node.getId());
		Alternative activeAlternative = null;
		
		if (alternatives != null) {
			for (Alternative alternative : alternatives) {
				if (alternative.hasAncestor(parent)) {
					if (!alternative.isActive) {
						result.add(alternative);
					} else
						activeAlternative = alternative;
				}
			}
		}

		if (activeAlternative != null) {
			activeAlternative.update(coloredSourceFile, node);
			result.add(activeAlternative);
		} else {
			result.add(new Alternative(node, parent.getFeatures()));
		}

		return result;
	}
	
	/**
	 * Weiß man, dass ein AST-Knoten in nur einer Alternative vorliegen kann (z.B. weil man keine Alternativen zu dem Knoten
	 * angeben kann), so kann diese Alternative mit dieser Methode abgerufen werden.
	 * 
	 * @param node
	 * @param parent
	 * @return
	 * @throws CoreException
	 * @throws ParseException
	 */
	public Alternative getTheAlternative(IASTNode node, Alternative parent) throws CoreException, ParseException {
		List<Alternative> alternatives = getAlternatives(node, parent);
		if ((alternatives != null) && (alternatives.size() == 1))
			return alternatives.get(0);
		return null;
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
}
