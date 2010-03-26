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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.IASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import coloredide.features.ASTColorInheritance;
import coloredide.features.Feature;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.CompilationUnitColorManager;

public class DeleteHiddenNodesVisitor extends ASTVisitor {

	private ASTRewrite rewrite;

	protected Set<Feature> hiddenColors;

	protected CompilationUnitColorManager colorManager;

	public DeleteHiddenNodesVisitor(ASTRewrite rewrite,
			CompilationUnitColorManager nodeColors, Set<Feature> hiddenColors) {
		this.rewrite = rewrite;
		this.colorManager = nodeColors;
		this.hiddenColors = hiddenColors;
	}

	@Override
	public boolean visit(InfixExpression node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	public void postVisit(IASTNode node) {
		if (shouldHide(node)) {
			// are there children that should not be deleted as well?
			List<IASTNode> replacements = new ArrayList<IASTNode>();
			for (Object prop : node.structuralPropertiesForType()) {
				if (ASTColorInheritance.notInheritedProperties.contains(prop)) {
					Object replace = rewrite.get(node,
							(StructuralPropertyDescriptor) prop);
					if (replace instanceof IASTNode)
						replacements.add((IASTNode) replace);
				}
			}
			remove(node, replacements);
		}
	}

	private boolean shouldHide(IASTNode node) {
		Set<Feature> nodeColors = colorManager.getOwnColors(node);
		for (Feature color : nodeColors)
			if (hiddenColors.contains(color))
				return true;

		return false;
	}

	/**
	 * deletes the node and replaced it with the replacement-list if not empty
	 * or null if only one single property can be replaced only the first of the
	 * list is inserted.
	 * 
	 * @param node
	 *            not null
	 * @param replacements
	 *            not null
	 */
	private void remove(IASTNode node, List<IASTNode> replacements) {
		IASTNode parent = node.getParent();
		StructuralPropertyDescriptor prop = node.getLocationInParent();
		if (prop instanceof ChildListPropertyDescriptor) {
			rewriteListChild(node, parent, (ChildListPropertyDescriptor) prop,
					replacements);
		}
		if (prop instanceof ChildPropertyDescriptor) {
			if (ASTColorInheritance.notInheritedProperties.contains(prop)) {
				rewriteIfWhileEtc(node, parent, prop, replacements);
			} else if (parent instanceof InfixExpression) {
				rewriteInfix(node, (InfixExpression) parent, prop, replacements);

			}
		}

	}

	private void rewriteInfix(IASTNode node, InfixExpression parent,
			StructuralPropertyDescriptor prop, List<IASTNode> replacements) {
		// ListRewrite extOperands = rewrite.getListRewrite(parent,
		// InfixExpression.EXTENDED_OPERANDS_PROPERTY);
		// if (extOperands.getRewrittenList().size() > 0) {
		// // if more than two operands, just shift.
		// IASTNode first = (IASTNode) extOperands.getRewrittenList().get(0);
		// extOperands.remove(first, null);
		// if (prop == InfixExpression.LEFT_OPERAND_PROPERTY) {
		// IASTNode right = (IASTNode) rewrite.get(parent,
		// InfixExpression.RIGHT_OPERAND_PROPERTY);
		// rewrite.set(parent, InfixExpression.LEFT_OPERAND_PROPERTY,
		// rewrite.createMoveTarget(right), null);
		// }
		// rewrite.set(parent, InfixExpression.RIGHT_OPERAND_PROPERTY, rewrite
		// .createMoveTarget(first), null);
		// } else {
		// // otherwise remove infixexpression
		// IASTNode value = (IASTNode) rewrite
		// .get(
		// parent,
		// (prop == InfixExpression.RIGHT_OPERAND_PROPERTY ?
		// InfixExpression.LEFT_OPERAND_PROPERTY
		// : InfixExpression.RIGHT_OPERAND_PROPERTY));
		// rewrite.set(parent.getParent(), parent.getLocationInParent(),
		// rewrite.createMoveTarget(value), null);
		// }
		IASTNode defaultValue = null;
		AST ast = rewrite.getAST();
		if (parent.getOperator() == Operator.CONDITIONAL_AND)
			defaultValue = ast.newBooleanLiteral(true);
		else if (parent.getOperator() == Operator.CONDITIONAL_OR)
			defaultValue = ast.newBooleanLiteral(false);
		else if (parent.getOperator() == Operator.PLUS
				|| parent.getOperator() == Operator.MINUS
				|| parent.getOperator() == Operator.LEFT_SHIFT
				|| parent.getOperator() == Operator.RIGHT_SHIFT_SIGNED
				|| parent.getOperator() == Operator.RIGHT_SHIFT_UNSIGNED)
			defaultValue = ast.newNumberLiteral("0");
		if (defaultValue != null)
			rewrite.set(parent, prop, defaultValue, null);
	}

	private void rewriteIfWhileEtc(IASTNode node, IASTNode parent,
			StructuralPropertyDescriptor prop, List<IASTNode> replacements) {
		Block replacement = node.getAST().newBlock();
		rewrite.set(parent, prop, replacement, null);
		if (replacements.size() > 0) {
			ListRewrite blockRewriteList = getRewriteList(replacement,
					Block.STATEMENTS_PROPERTY);
			for (IASTNode s : replacements)
				for (IASTNode n : resolveBlock(s)) {
					blockRewriteList.insertLast(move(n), null);
				}
		} else if (prop == IfStatement.ELSE_STATEMENT_PROPERTY)
			rewrite.set(parent, prop, null, null);
	}

	private void rewriteListChild(IASTNode node, IASTNode parent,
			ChildListPropertyDescriptor prop, List<IASTNode> replacements) {
		ListRewrite statementsListRewrite = getRewriteList(parent, prop);
		int position = statementsListRewrite.getRewrittenList().indexOf(node);
		statementsListRewrite.remove(node, null);
		// replacements?
		if (replacements.size() > 0) {
			boolean parentBlock = parent instanceof Block;
			for (IASTNode repl : replacements) {
				if (!parentBlock) {
					statementsListRewrite
							.insertAt(move(repl), ++position, null);
				} else {
					for (IASTNode s : resolveBlock(repl))
						statementsListRewrite.insertAt(move(s), ++position,
								null);
				}
			}
		}

	}

	private IASTNode move(IASTNode s) {
		if (s.getStartPosition() != -1)
			return rewrite.createMoveTarget(s);
		return s;
	}

	// ListRewrite statementsListRewrite = getRewriteList(node);
	// if (statementsListRewrite != null) {
	// int position = statementsListRewrite.getRewrittenList().indexOf(
	// node);
	// statementsListRewrite.remove(node, null);
	// if (replacements != null && replacements.size() > 0) {
	// if (node.getParent() instanceof Block)
	// replacements = resolveBlock(replacements);
	// for (int idx = replacements.size() - 1; idx >= 0; idx--)
	// statementsListRewrite.insertAt((IASTNode) replacements
	// .get(idx), position, null);
	// }
	// } else {
	// StructuralPropertyDescriptor prop = node.getLocationInParent();
	// if (prop == IfStatement.THEN_STATEMENT_PROPERTY
	// || prop == IfStatement.ELSE_STATEMENT_PROPERTY) {
	// Block replacement = node.getAST().newBlock();
	// rewrite.set(node.getParent(), node.getLocationInParent(),
	// replacement, null);
	// ListRewrite blockRewriteList = getRewriteList(replacement,
	// Block.STATEMENTS_PROPERTY);
	// for (IASTNode s : replacements)
	// for (IASTNode n : resolveBlock(s))
	// blockRewriteList.insertLast(n, null);
	// }

	//
	// }
	// }
	// private ListRewrite getRewriteList(IASTNode node) {
	// IASTNode parent = node.getParent();
	// StructuralPropertyDescriptor property = node.getLocationInParent();
	// if (property instanceof ChildListPropertyDescriptor)
	// return getRewriteList(parent,
	// (ChildListPropertyDescriptor) property);
	// return null;
	// }

	// private List<IASTNode> resolveBlock(List<IASTNode> replacement) {
	// if (replacement.size() == 1 && replacement.get(0) instanceof Block)
	// return ((Block) replacement.get(0)).statements();
	// return replacement;
	// }
	private List<IASTNode> resolveBlock(IASTNode replacement) {
		if (replacement instanceof Block) {
			ListRewrite rewrittenBlock = getRewriteList(replacement,
					Block.STATEMENTS_PROPERTY);
			List l = rewrittenBlock.getRewrittenList();
			if (replacement.getStartPosition() == -1)
				return new ArrayList<IASTNode>();//TODO debugging only
			return l;

		}
		return Collections.singletonList(replacement);
	}

	private final HashMap<IASTNode, HashMap<ChildListPropertyDescriptor, ListRewrite>> knownRewriteLists = new HashMap<IASTNode, HashMap<ChildListPropertyDescriptor, ListRewrite>>();

	private ListRewrite getRewriteList(IASTNode parent,
			ChildListPropertyDescriptor descriptor) {

		HashMap<ChildListPropertyDescriptor, ListRewrite> known = knownRewriteLists
				.get(parent);
		ListRewrite knownList = null;
		if (known != null) {
			knownList = known.get(descriptor);
			if (knownList != null)
				return knownList;
		}

		knownList = rewrite.getListRewrite(parent, descriptor);
		if (known == null) {
			known = new HashMap<ChildListPropertyDescriptor, ListRewrite>();
			known.put(descriptor, knownList);
		}
		knownRewriteLists.put(parent, known);

		return knownList;
	}

	public static String hideCode(ColoredJavaSourceFile source,
			Set<Feature> hiddenColors) throws CoreException {
		return hideCode(source.getCompilationUnit().getBuffer().getContents(),
				source.getAST(), source.getColorManager(), hiddenColors);
	}

	public static String hideCode(String buffer, CompilationUnit ast,
			CompilationUnitColorManager nodeColors, Set<Feature> hiddenColors)
			throws CoreException {
		Set<Feature> compUnitColors = nodeColors.getColors(ast);
		for (Feature color : compUnitColors)
			if (hiddenColors.contains(color))
				return "";

		ASTRewrite rewrite = ASTRewrite.create(ast.getAST());
		ast.accept(new DeleteHiddenNodesVisitor(rewrite, nodeColors,
				hiddenColors));
		TextEdit r = rewrite.rewriteAST();

		Document document = new Document(buffer);
		try {
			r.apply(document);
		} catch (MalformedTreeException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return document.get();
	}
}