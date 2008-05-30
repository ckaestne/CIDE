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