package de.ovgu.cide.af;

import java.util.List;
import java.util.Set;

import de.ovgu.cide.features.IFeature;

/**
 * Einfache Struktur zur Repraesentation einer Alternative, z.B. eines alternativen Sourcecode-Fragments
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
	 * Liste von IDs der Eltern-Entitäten
	 */
	public List<String> entityParentIDs;
	
	/**
	 * Menge von Features, mit der die Alternative annotiert ist
	 */
	public Set<IFeature> features;
	
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
	
	public Alternative(String altID, String entityID, boolean isOptional, List<String> entityParentIDs, Set<IFeature> features) {
		this.altID = altID;
		this.entityID = entityID;
		this.isOptional = isOptional;
		this.entityParentIDs = entityParentIDs;
		this.features = features;
		
		this.text = null;
		this.isActive = true;
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
}
