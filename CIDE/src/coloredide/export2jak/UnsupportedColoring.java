package coloredide.export2jak;

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.export.Formal;

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


