package de.ovgu.cide.af;

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
