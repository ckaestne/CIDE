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

/**
 * 
 */
package de.ovgu.cide.export.physical.internal;

import org.eclipse.jdt.core.dom.Type;

public class Formal implements Comparable<Formal> {
	private int orderNr;

	public Formal(Type type, String identifier, int orderNr) {
		this.name = identifier;
		this.type = type;
		this.orderNr = orderNr;
	}

	public Type type;

	public String name;

	public int compareTo(Formal o) {
		if (orderNr < o.orderNr)
			return -1;
		if (orderNr > o.orderNr)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return (type != null ? type.toString() : "void") + " " + name;
	}
}