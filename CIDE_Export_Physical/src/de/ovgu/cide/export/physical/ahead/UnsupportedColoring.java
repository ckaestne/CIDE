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

package de.ovgu.cide.export.physical.ahead;

import org.eclipse.jdt.core.dom.ASTNode;

public class UnsupportedColoring {

	private ASTNode node;
	private String reason;
	private String extra;

	public UnsupportedColoring(ASTNode node, String reason) {
		this(node, reason, "");
	}

	public UnsupportedColoring(ASTNode node, String reason, String extraString) {
		this.node = node;
		this.reason = reason;
		this.extra = extraString;
	}

	@Override
	public String toString() {
		String r = "Unsupported Coloring at " + node.toString() + " - "
				+ reason;
		if (!"".equals(extra)) {
			r += " (" + extra + ")";
		}
		return r;
	}

	public int getStartPosition() {
		return node.getStartPosition();
	}

	public int getLength() {
		return node.getLength();
	}

	public String getReason() {
		return reason;
	}

}


