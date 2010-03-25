/**
 * 
 */
package coloredide.editor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;

import coloredide.features.ASTColorInheritance;

class SelectionFinder extends ASTVisitor {

	private final Set<ASTNode> selectedNodes;

	private final Set<ASTNode> knownNodes = new HashSet<ASTNode>();

	private final int offset;

	private final int length;

	public SelectionFinder(Set<ASTNode> selectedNodes, ITextSelection selection) {
		this.selectedNodes = selectedNodes;
		this.offset = selection.getOffset();
		this.length = selection.getLength();
	}

	public SelectionFinder(Set<ASTNode> selectedNodes, IRegion region) {
		this.selectedNodes = selectedNodes;
		this.offset = region.getOffset();
		this.length = region.getLength();
	}

	@Override
	public void preVisit(ASTNode node) {
		super.preVisit(node);
		if (node.getStartPosition() >= offset
				&& (node.getStartPosition() + node.getLength()) <= (offset + length)) {

			boolean hasColoredParent = selectedNodes.contains(node.getParent());
			boolean hasKnownParent = knownNodes.contains(node.getParent());
			boolean noInherit = !ASTColorInheritance.inheritsColors(node
					.getParent(), node);
			if (!hasKnownParent || (noInherit && hasColoredParent)) {
				selectedNodes.add(node);
			}
			knownNodes.add(node);

		}
	}
}