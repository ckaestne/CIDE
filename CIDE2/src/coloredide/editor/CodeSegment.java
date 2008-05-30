/**
 * 
 */
package coloredide.editor;

import java.util.Set;

import org.eclipse.jface.text.Position;

import cide.gast.ASTNode;
import coloredide.features.Feature;

public class CodeSegment extends Position {
	boolean isHidden;

//	public CodeSegment(int start, int end, Set<Feature> col, Set<ASTNode> nodes, boolean isHidden) {
//		super(start, end - start);
//		colors = col;
//		this.isHidden=isHidden;
//	}

	public CodeSegment(int start, int end, Set<Feature> col, boolean isHidden) {
		super(start, end - start);
		colors = col;
		this.isHidden=isHidden;
	}

	int endPosition() {
		return getOffset() + getLength();
	}

	// final Set<ASTNode> containingNodes=new HashSet<ASTNode>();

	private Set<Feature> colors;

	public String toString() {
		String result = "<" + getOffset() + "-" + endPosition() + ":" + colors;
		result += ">";
		return result;
	}

	boolean isEmpty() {
		return length == 0;
	}

	CodeSegment copy() {
		return new CodeSegment(getOffset(), endPosition(), colors, isHidden);
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