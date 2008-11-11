package de.ovgu.cide.language.jdt.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import de.ovgu.cide.configuration.jdt.JDTColorManagerBridge;
import de.ovgu.cide.editor.CodeSegment;
import de.ovgu.cide.features.IFeature;


public class CodeSegmentCalculatorJava {
	protected static final Set<IFeature> NOCOLORS = new HashSet<IFeature>();

	public static List<CodeSegment> getCodeSegments(ASTNode ast,
			final JDTColorManagerBridge colorManager) {
		final Stack<CodeSegment> stack = new Stack<CodeSegment>();
		final List<CodeSegment> list = new ArrayList<CodeSegment>();

		stack.push(new CodeSegment(ast.getStartPosition(), ast
				.getStartPosition()
				+ ast.getLength(), NOCOLORS,false));

		ASTVisitor visitor = new ASTVisitor() {
			private ASTNode last;

			public void preVisit(ASTNode node) {
				ASTNode next = node;
				node = last;
				last = next;

				if (node == null)
					return;

				while (stack.peek().endPosition() < node.getStartPosition())
					list.add(stack.pop());

				Set<IFeature> colors = colorManager.getColors(node);
				if (stack.peek().getColors().equals(colors)) {
					// colors did not change, ignore
//					stack.peek().containingNodes.add(node);
				} else {
					CodeSegment old = stack.pop();
					// finished previous segment
					list.add(new CodeSegment(old.getOffset(), node
							.getStartPosition(), old.getColors(),false/*,
							old.containingNodes*/));
					stack.push(new CodeSegment(node.getStartPosition()
							+ node.getLength(), old.endPosition(), old.getColors(),false/*,
							old.containingNodes*/));
					stack.push(new CodeSegment(node.getStartPosition(), node
							.getStartPosition()
							+ node.getLength(), colors,false/*, old.containingNodes*/));
//					stack.peek().containingNodes.add(node);
				}

				super.preVisit(node);
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
