package de.ovgu.cide.alternativefeatures;

/**
 * * NOTE: the entire functionality for alternative features was implemented as
 * part of a master's thesis (available in German here:
 * http://wwwiti.cs.uni-magdeburg.de/~ckaestne/thesisrosenthal.pdf) the
 * functionality has been deactivated mostly, but the code is still included.
 * 
 */
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
