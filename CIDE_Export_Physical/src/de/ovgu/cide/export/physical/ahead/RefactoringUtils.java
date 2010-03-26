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

package de.ovgu.cide.export.physical.ahead;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeLiteral;

import de.ovgu.cide.configuration.jdt.ASTColorInheritance;
import de.ovgu.cide.export.physical.internal.GenericVisitor;
import de.ovgu.cide.export.physical.internal.LocalVariableAnalyzer;
import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.IFeature;

public class RefactoringUtils {

	public static boolean areAllStatementsColored(MethodDeclaration method,
			RefactoringColorManager colorManager, Set<IFeature> derivative) {
		List<Statement> statements = method.getBody().statements();
		for (int idx = 0; idx < statements.size(); idx++) {
			if (!derivative.equals(colorManager.getColors(statements.get(idx))))
				return false;
		}
		return true;
	}

	public static List<Statement> findBeforeStatements(
			MethodDeclaration method, RefactoringColorManager colorManager,
			Set<IFeature> derivative) {
		List<Statement> statements = method.getBody().statements();
		List<Statement> result = new ArrayList<Statement>();

		int forwardIdx = 0;
		while (forwardIdx < statements.size()
				&& derivative.equals(colorManager.getColors(statements
						.get(forwardIdx)))) {
			result.add(statements.get(forwardIdx));
			forwardIdx++;
		}
		return result;
	}

	public static List<Statement> findAfterStatements(MethodDeclaration method,
			RefactoringColorManager colorManager, Set<IFeature> derivative) {
		List<Statement> statements = method.getBody().statements();
		List<Statement> result = new ArrayList<Statement>();

		boolean ignoreLast = false;
		if (statements.size() > 0) {
			Statement lastStmt = statements.get(statements.size() - 1);
			if (isSimpleReturnStmt(lastStmt))
				ignoreLast = true;
		}

		int forwardIdx = 0;
		while (forwardIdx < statements.size()
				&& derivative.equals(colorManager.getColors(statements
						.get(forwardIdx)))) {
			forwardIdx++;
		}

		int backwardIdx = statements.size() - (ignoreLast ? 1 : 0) - 1;
		while (backwardIdx >= 0
				&& backwardIdx > forwardIdx
				&& derivative.equals(colorManager.getColors(statements
						.get(backwardIdx)))) {
			result.add(0, statements.get(backwardIdx));
			backwardIdx--;
		}
		return result;
	}

	public static boolean canRefactorStatementsBeforeAfter(
			MethodDeclaration method, List<Statement> refactorFirst,
			List<Statement> refactorLast, RefactoringColorManager colorManager,
			Set<IFeature> derivative) {
		if (!(method.getParent() instanceof TypeDeclaration))
			return false;
		if (!(method.getParent().getParent() instanceof CompilationUnit))
			if (!MethodObjectHelper
					.isMethodObjectClass((TypeDeclaration) method.getParent()))
				return false;

		if (refactorFirst.size() == 0 && refactorLast.size() == 0)
			return false;
		// check that there are no variables defined (otherwise just do not
		// refactor this, but wait for hook methods)
		if (refactorLast.size() > 0) {
			LocalVariableAnalyzer lva = new LocalVariableAnalyzer(method,
					refactorLast, refactorFirst, colorManager);
			lva.setIgnoreMethodParameters(true);
			lva.execute();
			if (lva.getParameters().size() > 0)
				return false;
		}
		// cannot refactor first statements in constructor, only the end
		if (method.isConstructor())
			if (refactorFirst.size() > 0
					&& !refactorFirst.equals(method.getBody().statements()))
				return false;
		// check that these statements do not contain exceptions to the subtree
		// rule
		if (containsSubtreeRuleException(refactorFirst, colorManager,
				derivative)
				|| containsSubtreeRuleException(refactorLast, colorManager,
						derivative))
			return false;

		return true;
	}

	/**
	 * determines simple expressions used in return statements. the idea is to
	 * ignore return statements when possible. however ignoring is not possible
	 * if the return statement contains an expression that uses a method
	 * invocation or something similar.
	 */
	public static boolean isSimpleReturnStmt(Statement lastStmt) {
		if (lastStmt instanceof ReturnStatement) {
			Expression returnExpr = ((ReturnStatement) lastStmt)
					.getExpression();
			return isSimpleExpression(returnExpr);
		}
		return false;
	}

	/**
	 * @see isSimpleReturnStmt
	 */
	private static boolean isSimpleExpression(Expression expr) {
		if (expr == null)
			return true;
		if (expr instanceof Name)
			return true;
		if (expr instanceof ArrayAccess)
			return true;
		if (expr instanceof BooleanLiteral)
			return true;
		if (expr instanceof CharacterLiteral)
			return true;
		if (expr instanceof InstanceofExpression)
			return isSimpleExpression(((InstanceofExpression) expr)
					.getLeftOperand());
		if (expr instanceof NullLiteral)
			return true;
		if (expr instanceof NumberLiteral)
			return true;
		if (expr instanceof StringLiteral)
			return true;
		if (expr instanceof TypeLiteral)
			return true;
		return false;
	}

	public static boolean isVoid(Type t) {
		if (t == null)
			return true;
		if (t.isPrimitiveType())
			if (((PrimitiveType) t).getPrimitiveTypeCode() == PrimitiveType.VOID)
				return true;
		return false;
	}

	public static CompilationUnit getCompilationUnit(ASTNode node) {
		while (node.getParent() != null && !(node instanceof CompilationUnit))
			node = node.getParent();
		if (node instanceof CompilationUnit)
			return (CompilationUnit) node;
		return null;
	}

	public static MethodDeclaration getMethodDeclaration(ASTNode node) {
		while (node.getParent() != null && !(node instanceof MethodDeclaration))
			node = node.getParent();
		if (node instanceof MethodDeclaration)
			return (MethodDeclaration) node;
		return null;
	}

	private static class UncoloredChildrenFinder extends GenericVisitor {
		public boolean hasUncoloredChildren = false;
		public Stack<ASTNode> uncoloredChild = new Stack<ASTNode>();
		private final ASTNode parent;
		private final RefactoringColorManager colorManager;
		private final Set<IFeature> derivative;

		public UncoloredChildrenFinder(ASTNode node,
				RefactoringColorManager colorManager, Set<IFeature> derivative) {
			this.parent = node;
			this.colorManager = colorManager;
			this.derivative = derivative;
		}

		@Override
		protected boolean visitNode(ASTNode node) {
			if (node == parent)
				return true;

			if (!ASTColorInheritance.inheritsColors(parent, node))
				if (!colorManager.getColors(node).containsAll(derivative)) {
					hasUncoloredChildren = true;
					uncoloredChild.push(node);
				}

			return false;
		}
	}

	public static boolean canRefactorAllStatements(MethodDeclaration method,
			RefactoringColorManager colorManager, Set<IFeature> derivative) {
		if (!areAllStatementsColored(method, colorManager, derivative))
			return false;

		// check exceptions to the subtree rule. there may be at most one!
		List<Statement> statements = method.getBody().statements();
		boolean foundException = false;
		for (int idx = 0; idx < statements.size(); idx++) {
			if (isSubtreeRuleException(statements.get(idx), colorManager,
					derivative))
				if (!foundException)
					foundException = true;
				else
					return false;
		}
		if (foundException)
			if (!RefactoringUtils.isVoid(method.getReturnType2()))
				return false;

		return true;
	}

	/**
	 * checks whether any direct children are not colored as well. note, the
	 * node must be colored!
	 * 
	 * @param node
	 *            colored node to check
	 * @param colorManager
	 * @param derivative
	 * @return
	 */
	public static boolean isSubtreeRuleException(ASTNode node,
			RefactoringColorManager colorManager, Set<IFeature> derivative) {
		assert colorManager.getColors(node).equals(derivative);
		UncoloredChildrenFinder v = new UncoloredChildrenFinder(node,
				colorManager, derivative);
		node.accept(v);
		return v.hasUncoloredChildren;
	}

	public static boolean hasMultipleSubtreeRuleException(ASTNode node,
			RefactoringColorManager colorManager, Set<IFeature> derivative) {
		assert colorManager.getColors(node).equals(derivative);
		UncoloredChildrenFinder v = new UncoloredChildrenFinder(node,
				colorManager, derivative);
		node.accept(v);
		return v.uncoloredChild.size() > 1;
	}

	public static boolean containsSubtreeRuleException(List<Statement> nodes,
			RefactoringColorManager colorManager, Set<IFeature> derivative) {
		for (Statement node : nodes)
			if (isSubtreeRuleException(node, colorManager, derivative))
				return true;
		return false;
	}

	public static ASTNode getSubtreeRuleExceptionNode(ASTNode node,
			RefactoringColorManager colorManager, Set<IFeature> derivative) {
		assert colorManager.getColors(node).equals(derivative);
		UncoloredChildrenFinder v = new UncoloredChildrenFinder(node,
				colorManager, derivative);
		node.accept(v);
		return v.uncoloredChild.size() == 0 ? null : v.uncoloredChild.peek();
	}

	public static Statement findSubtreeRuleException(
			List<Statement> statements, RefactoringColorManager colorManager,
			Set<IFeature> derivative) {
		for (Statement statement : statements) {
			ASTNode e = RefactoringUtils.getSubtreeRuleExceptionNode(statement,
					colorManager, derivative);
			if (e != null) {
				assert e instanceof Statement;
				return (Statement) e;
			}
		}
		return null;
	}

	public static void addStatementOrBlockContent(Statement statementOrBlock,
			List<Statement> targetList) {
		if (statementOrBlock instanceof Block) {
			List<Statement> list = new ArrayList<Statement>(
					((Block) statementOrBlock).statements());
			((Block) statementOrBlock).statements().clear();
			targetList.addAll(list);
		} else {
			targetList.add(statementOrBlock);
		}

	}

	public static Statement copyStatement(Statement stmt) {
		return (Statement) ASTNode.copySubtree(stmt.getAST(), stmt);
	}

	public static void replaceASTNode(ASTNode target, ASTNode replacement) {
		ASTNode parent = target.getParent();
		if (target.getLocationInParent().isChildListProperty()) {
			List<ASTNode> list = (List<ASTNode>) parent
					.getStructuralProperty(target.getLocationInParent());
			int p = list.indexOf(target);
			list.set(p, replacement);
		} else
			parent.setStructuralProperty(target.getLocationInParent(),
					replacement);
	}

	public static TypeDeclaration getContainingType(ASTNode node) {
		while (node.getParent() != null && !(node instanceof TypeDeclaration))
			node = node.getParent();
		if (node instanceof TypeDeclaration)
			return (TypeDeclaration) node;
		return null;
	}
}
