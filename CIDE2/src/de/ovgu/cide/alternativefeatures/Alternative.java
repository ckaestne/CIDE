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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cide.gast.IASTNode;
import cide.languages.ExtendedLanguageExtension;
import cide.languages.ILanguageExtension;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.languages.LanguageExtensionProxy;

/**
 * NOTE: the entire functionality for alternative features was implemented as part of a master's thesis
 * (available in German here: http://wwwiti.cs.uni-magdeburg.de/~ckaestne/thesisrosenthal.pdf)
 * the functionality has been deactivated mostly, but the code is still included.
 * 
 * Einfache Struktur zur Repraesentation einer Alternative, z.B. eines alternativen Sourcecode-Fragments.
 * 
 * ACHTUNG: Es sind je nach Anwendungssituation nicht immer alle Felder befüllt! Ist nicht schön, aber
 * 			erspart oft aufwendige Arbeiten, die nötig wären, um solche Felder zu befüllen, obwohl sie
 * 			in dem Fall gar nicht benötigt werden. 
 * 
 * @author Malte Rosenthal
 */
public class Alternative {

	/**
	 * ID der Alternative
	 */
	public String altID;
	
	/**
	 * ID der zugrunde liegenden Entität (z.B. ID des AST-Knotens)
	 */
	public String entityID;
	
	/**
	 * Indikator: Zugrunde liegende Entität (z.B. AST-Knoten) ist optional, 
	 * d.h. es muss nicht in jeder Konfiguration eine Alternative existieren.
	 */
	public boolean isOptional;
	
	/**
	 * Liste von IDs aller Eltern-Entitäten (auch Großeltern usw.)
	 */
	public List<String> entityParentIDs;
	
	/**
	 * Menge von eigenen Features, mit der die Alternative annotiert ist
	 */
	public Set<IFeature> ownFeatures;
	
	/**
	 * Menge von Features, die die Alternative von seinen Eltern erbt
	 */
	public Set<IFeature> inheritedFeatures;
	
	/**
	 * Text der Alternative, z.B. Sourcecode
	 */
	public String text;
	
	/**
	 * Indikator: Alternative ist aktiv
	 */
	public boolean isActive;
	
	private Alternative parent;
	private List<Alternative> children;
	
	protected Alternative() { }
	
	public Alternative(String altID, String entityID, boolean isOptional, List<String> entityParentIDs, Set<IFeature> features) {
		this.altID = altID;
		this.entityID = entityID;
		this.isOptional = isOptional;
		this.entityParentIDs = entityParentIDs;
		this.ownFeatures = features;
		
		this.text = null;
		this.isActive = true;
	}
	
	public Alternative(IASTNode node, Set<IFeature> inheritedFeatures) {
		if (node != null) {
			this.altID = "Non-explicit alternative";
			this.entityID = node.getId();
			this.isOptional = node.isOptional();
			this.text = node.render();
			this.inheritedFeatures = inheritedFeatures;
		}
	}
	
	public Alternative getRoot() {
		Alternative root = this;
		while (root.parent != null)
			root = root.parent;
		
		return root;
	}
	
	public void setParent(Alternative parent) {
		this.parent = parent;
	}
	
	public Alternative getParent() {
		return parent;
	}
	
	public boolean hasAncestor(Alternative alternative) {
		if (alternative == null)
			return (this.parent == null);
		if (alternative.equals(this.parent))
			return true;
		if (this.parent != null)
			return this.parent.hasAncestor(alternative);
			
		return false;
	}
	
	public boolean parentIsActive() {
		return ((parent == null) || parent.isActive);
	}
	
	public void addChild(Alternative alt) {
		if (children == null)
			children = new LinkedList<Alternative>();
		children.add(alt);
	}
	
	public boolean hasMultipleChildren() {
		return ((children != null) && (children.size() > 1));
	}
	
	@SuppressWarnings("unchecked")
	public <NodeType extends IASTNode> NodeType getNode(ColoredSourceFile sourceFile, NodeType modelNode) {
		ILanguageExtension le = sourceFile.getLanguageExtension();
		if (le instanceof LanguageExtensionProxy)
			le = ((LanguageExtensionProxy) le).getTarget();
		ExtendedLanguageExtension languageExtension = (ExtendedLanguageExtension) le;

		return (NodeType) languageExtension.parseCodeFragment(modelNode, this.text);
	}
	
	public void update(ColoredSourceFile sourceFile, IASTNode node) {
		if ((sourceFile != null) && (node != null)) {
			this.inheritedFeatures = sourceFile.getColorManager().getInheritedColors(node);
			this.ownFeatures = sourceFile.getColorManager().getOwnColors(node);
			this.text = node.render();
		}
	}
	
	public Alternative setEntityID(String entityID) {
		this.entityID = entityID;
		return this;
	}
	
	public Alternative setActive(boolean isActive) {
		this.isActive = isActive;
		return this;
	}
	
	public Alternative setText(String text) {
		this.text = text;
		return this;
	}
	
	public Alternative setInheritedFeatures(Set<IFeature> features) {
		this.inheritedFeatures = features;
		return this;
	}
	
	/**
	 * Gibt die Menge aller Features zurück, mit der die Alternative annotiert ist (eigene Features plus geerbte Features).
	 */
	public Set<IFeature> getFeatures() {
		return addAll(ownFeatures, inheritedFeatures);
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || !(obj instanceof Alternative))
			return false;
		Alternative that = (Alternative) obj;
		
		if (this.isActive != that.isActive) return false;
		if (this.isOptional != that.isOptional) return false;
		if (!this.altID.equals(that.altID)) return false;
		if (!this.entityID.equals(that.entityID)) return false;
		if (!this.inheritedFeatures.equals(that.inheritedFeatures)) return false;
		if (!this.ownFeatures.equals(that.ownFeatures)) return false;

		return true;
	}
	
	public static Set<IFeature> addAll(Set<IFeature> to, Set<IFeature> from) {
		if (to == null)
			return from;
		if (from == null)
			return to;
		
		// Wir machen diesen Workaround, weil >to< oft ein unmodifiable set ist.
		Set<IFeature> result = new HashSet<IFeature>();
		
		for (IFeature f : to) {
			result.add(f);
		}
		for (IFeature f : from) {
			result.add(f);
		}
		
		return result;
	}
}
