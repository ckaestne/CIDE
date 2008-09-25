package coloredide.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import coloredide.features.IFeature;
import coloredide.features.source.SourceFileColorManager;

public class CodeSegmentCalculator {
	protected static final Set<IFeature> NOCOLORS = new HashSet<IFeature>();

	public static List<CodeSegment> getCodeSegments(IASTNode ast,
			final SourceFileColorManager colorManager) {
		final Stack<CodeSegment> stack = new Stack<CodeSegment>();
		final List<CodeSegment> list = new ArrayList<CodeSegment>();

		stack.push(new CodeSegment(ast.getStartPosition(), ast
				.getStartPosition()
				+ ast.getLength(), NOCOLORS, false));

		IASTVisitor visitor = new ASTVisitor() {
			private IASTNode last;

			public boolean visit(IASTNode node) {
				IASTNode next = node;
				node = last;
				last = next;

				if (node == null)
					return true;

				while (stack.peek().endPosition() <= node.getStartPosition())
					list.add(stack.pop());

				Set<IFeature> colors = colorManager.getColors(node);
				if (stack.peek().getColors().equals(colors)) {
					// colors did not change, ignore
					// stack.peek().containingNodes.add(node);
				} else {
					CodeSegment old = stack.pop();
					// finished previous segment
					assert old.getOffset() <= node.getStartPosition();
					list.add(new CodeSegment(old.getOffset(), node
							.getStartPosition(), old.getColors(), false));
					assert node.getStartPosition() + node.getLength() <= old
							.endPosition();
					stack.push(new CodeSegment(node.getStartPosition()
							+ node.getLength(), old.endPosition(), old.getColors(),
							false));
					assert node.getStartPosition() <= node.getStartPosition()
							+ node.getLength();
					stack.push(new CodeSegment(node.getStartPosition(), node
							.getStartPosition()
							+ node.getLength(), colors, false));
					// stack.peek().containingNodes.add(node);
				}

				return super.visit(node);
			}
		};
		ast.accept(visitor);

		while (!stack.isEmpty())
			list.add(stack.pop());

		removeEmptySegments(list);

		return list;
	}

	private static void removeEmptySegments(List<CodeSegment> list) {
		Iterator<CodeSegment> i = list.iterator();
		while (i.hasNext()) {
			CodeSegment seg = i.next();
			if (seg.isEmpty()) {
				i.remove();
			}
		}
	}
}
