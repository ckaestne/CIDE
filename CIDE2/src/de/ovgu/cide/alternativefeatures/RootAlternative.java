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
