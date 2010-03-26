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
package de.ovgu.cide.editor;

import java.util.Set;

import org.eclipse.jface.text.Position;

import de.ovgu.cide.features.IFeature;

public class CodeSegment extends Position {
	boolean isHidden;

//	public CodeSegment(int start, int end, Set<Feature> col, Set<ASTNode> nodes, boolean isHidden) {
//		super(start, end - start);
//		colors = col;
//		this.isHidden=isHidden;
//	}

	public CodeSegment(int start, int end, Set<IFeature> col, boolean isHidden) {
		super(start, end - start);
		colors = col;
		this.isHidden=isHidden;
	}

	public int endPosition() {
		return getOffset() + getLength();
	}

	// final Set<ASTNode> containingNodes=new HashSet<ASTNode>();

	private Set<IFeature> colors;

	public String toString() {
		String result = "<" + getOffset() + "-" + endPosition() + ":" + colors;
		result += ">";
		return result;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public CodeSegment copy() {
		return new CodeSegment(getOffset(), endPosition(), colors, isHidden);
	}

	public void setEndPosition(int end) {
		setLength(end - getOffset());
	}

	public void moveStartPosition(int newStartpos) {
		// sets the start position without changing the end
		setLength(getLength() + getOffset() - newStartpos);
		setOffset(newStartpos);
	}

	public void update(int off, int len) {
		setOffset(off);
		setLength(len);
	}

	public Set<IFeature> getColors() {
		return colors;
	}
}