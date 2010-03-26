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
package coloredide.configuration;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.features.Feature;

public class CodeSegmentBak {
	public CodeSegmentBak(int start, int end, Set<Feature> col, Set<ASTNode> nodes) {
		startPosition = start;
		length = end - start;
		colors = col;
		// containingNodes.addAll(nodes);
	}

	public CodeSegmentBak(int start, int end, Set<Feature> col) {
		startPosition = start;
		length = end - start;
		colors = col;
	}

	int startPosition;

	int length;

	int endPosition() {
		return startPosition + length;
	}

	// final Set<ASTNode> containingNodes=new HashSet<ASTNode>();

	Set<Feature> colors;

	public String toString() {
		String result = "<" + startPosition + "-" + endPosition() + ":"
				+ colors;
		// for (ASTNode c:containingNodes){
		// result+=c.getClass().getSimpleName()+",";
		// }
		result += ">";
		return result;
	}

	boolean isEmpty() {
		return length == 0;
	}

	CodeSegmentBak copy() {
		CodeSegmentBak s = new CodeSegmentBak(startPosition, endPosition(), colors);
		// s.containingNodes.addAll(containingNodes);
		return s;
	}

	void setEndPosition(int end) {
		length = end - startPosition;
	}

	void moveStartPosition(int newStartpos) {
		// sets the start position without changing the end

		length += startPosition - newStartpos;
		startPosition = newStartpos;
	}
}