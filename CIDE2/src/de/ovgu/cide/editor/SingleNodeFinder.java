/**
 * 
 */
package de.ovgu.cide.editor;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;

public class SingleNodeFinder extends ASTVisitor {
	private int offset;

	public SingleNodeFinder(int offset) {
		this.offset = offset;
	}

	IASTNode result = null;

	@Override
	public boolean visit(IASTNode node) {
		if (node.getStartPosition() <= offset
				&& (node.getStartPosition() + node.getLength()) > offset) {
			if (result == null || node.getLength() < result.getLength())
				result = node;
			return true;
		}
		return false;
	}
}