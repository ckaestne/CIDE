package de.ovgu.cide.editor;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;

import cide.gast.ASTVisitor;
import cide.gast.ASTWrappers;
import cide.gast.IASTNode;

/**
 * searches to AST for nodes that lie in a specified selection range. returns
 * only the toplevel nodes as far as possible
 * 
 * this is used to determine which nodes should be colored for a user selection
 * 
 * 
 * @author ckaestne
 * 
 */
public class SelectionFinder extends ASTVisitor {

	private final List<IASTNode> selectedNodes;

	private final List<IASTNode> knownNodes = new LinkedList<IASTNode>();

	private final int offset;

	private final int length;

	private final boolean optionalOnly;

	/**
	 * 
	 * @param selectedNodes
	 *            empty list which holds the result after the visitor is run
	 * @param selection
	 * @param optionalOnly
	 */
	public SelectionFinder(List<IASTNode> selectedNodes,
			ITextSelection selection, boolean optionalOnly) {
		this(selectedNodes, selection.getOffset(), selection.getLength(),
				optionalOnly);
	}

	public SelectionFinder(List<IASTNode> selectedNodes, IRegion region,
			boolean optionalOnly) {
		this(selectedNodes, region.getOffset(), region.getLength(),
				optionalOnly);
	}

	public SelectionFinder(List<IASTNode> selectedNodes, int offset,
			int length, boolean optionalOnly) {
		this.selectedNodes = selectedNodes;
		this.offset = offset;
		this.length = length;
		this.optionalOnly = optionalOnly;
	}

	@Override
	public boolean visit(IASTNode node) {
		boolean r = super.visit(node);
		if (node.getStartPosition() >= offset
				&& (node.getStartPosition() + node.getLength()) <= (offset + length)) {
			if (!optionalOnly || node.isOptional()) {
				boolean parentAlreadySelected = selectedNodes.contains(node
						.getParent());
				boolean hasKnownParent = knownNodes.contains(node.getParent());
				boolean inheritsColors = ASTWrappers.inheritsColors(node
						.getParent(), node);

				if (!hasKnownParent
						|| (!inheritsColors && parentAlreadySelected)) {
					selectedNodes.add(node);
				}
			}
			knownNodes.add(node);
		}

		return r;
	}

	public List<IASTNode> getAllSelectedNodes() {
		return knownNodes;
	}
}
