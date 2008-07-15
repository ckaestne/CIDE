/**
 * 
 */
package coloredide.configuration;

import java.util.Set;

import org.eclipse.jdt.core.dom.IASTNode;
import org.eclipse.jface.text.Position;

import coloredide.features.Feature;

public class CodeSegment extends Position {
	public CodeSegment(int start, int end, Set<Feature> col, Set<IASTNode> nodes) {
		super(start, end - start);
		colors = col;
	}

	public CodeSegment(int start, int end, Set<Feature> col) {
		super(start, end - start);
		colors = col;
	}

	int endPosition() {
		return getOffset() + getLength();
	}

	// final Set<IASTNode> containingNodes=new HashSet<IASTNode>();

	Set<Feature> colors;

	public String toString() {
		String result = "<" + getOffset() + "-" + endPosition() + ":" + colors;
		result += ">";
		return result;
	}

	boolean isEmpty() {
		return length == 0;
	}

	CodeSegment copy() {
		return new CodeSegment(getOffset(), endPosition(), colors);
	}

	void setEndPosition(int end) {
		setLength(end - getOffset());
	}

	void moveStartPosition(int newStartpos) {
		// sets the start position without changing the end
		setLength(getLength() + getOffset() - newStartpos);
		setOffset(newStartpos);
	}

	public void update(int off, int len) {
		setOffset(off);
		setLength(len);
	}

	public Set<Feature> getColors() {
		return colors;
	}
}