package de.ovgu.cide.af;


public class RootAlternative extends Alternative {

	public RootAlternative() {
		this.altID = "Root alternative";
		this.entityID = null;
		this.entityParentIDs = null;
		this.inheritedFeatures = null;
		this.isActive = true;
		this.isOptional = false;
		this.ownFeatures = null;
		this.text = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((obj != null) && (obj instanceof RootAlternative));
	}
}
