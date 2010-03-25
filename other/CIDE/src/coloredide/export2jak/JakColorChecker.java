package coloredide.export2jak;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import coloredide.export.Formal;
import coloredide.export.LocalVariableAnalyzer;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.utils.GenericVisitor;

public class JakColorChecker extends GenericVisitor {

	public static class UnsupportedColoredReturn extends UnsupportedColoring {
		public UnsupportedColoredReturn(ReturnStatement node) {
			super(node, "Return must not be colored");
		}
	}

	public static class UnsupportedColoredContinue extends UnsupportedColoring {
		public UnsupportedColoredContinue(ContinueStatement node) {
			super(node, "Continue must not be colored");
		}
	}

	public static class UnsupportedColoredBreak extends UnsupportedColoring {
		public UnsupportedColoredBreak(BreakStatement node) {
			super(node, "Break must not be colored");
		}
	}

	public static class UnsupportedColoredSuperConstructorInvocation extends
			UnsupportedColoring {
		public UnsupportedColoredSuperConstructorInvocation(
				SuperConstructorInvocation node) {
			super(node, "Super constructor invocation must not be colored");
		}
	}

	public static class UnsupportedColoringMultipleWriteAccess extends
			UnsupportedColoring {

		public UnsupportedColoringMultipleWriteAccess(ASTNode node,
				Set<Formal> returns) {
			super(node, "Multiple Write Access to local variables", returns
					.toString());
		}

	}

	private List<UnsupportedColoring> unsupportedColorings = new ArrayList<UnsupportedColoring>();
	private final IColorManager colorManager;

	public JakColorChecker(IColorManager colorManager) {
		this.colorManager = colorManager;
	}

	public boolean foundUnsupportedColoring() {
		return unsupportedColorings.size() > 0;
	}

	public List<UnsupportedColoring> getUnsupportedColorings() {
		return unsupportedColorings;
	}

	//
	// @Override
	// public boolean visit(ReturnStatement node) {
	// Set<Feature> nodeColors = getColors(node);
	// if (!nodeColors.isEmpty()) {
	// MethodDeclaration method = RefactoringUtils
	// .getMethodDeclaration(node);
	// if (!nodeColors.equals(colorManager.getColors(method)))
	// unsupportedColorings.add(new UnsupportedColoredReturn(node));
	// }
	// return super.visit(node);
	// }

	@Override
	public boolean visit(ContinueStatement node) {
		Set<Feature> nodeColors = getColors(node);
		if (!nodeColors.isEmpty()) {
			MethodDeclaration method = RefactoringUtils
					.getMethodDeclaration(node);
			if (!nodeColors.equals(colorManager.getColors(method)))
				unsupportedColorings.add(new UnsupportedColoredContinue(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(BreakStatement node) {
		Set<Feature> nodeColors = getColors(node);
		if (!nodeColors.isEmpty()) {
			MethodDeclaration method = RefactoringUtils
					.getMethodDeclaration(node);
			if (!nodeColors.equals(colorManager.getColors(method)))
				unsupportedColorings.add(new UnsupportedColoredBreak(node));
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		Set<Feature> nodeColors = getColors(node);
		if (!nodeColors.isEmpty()) {
			unsupportedColorings
					.add(new UnsupportedColoredSuperConstructorInvocation(node));
		}
		return super.visit(node);
	}

	@Override
	protected boolean visitNode(ASTNode node) {
		// tmpDeleteTransactions(node);
		if (!colorManager.getOwnColors(node).isEmpty()) {
			Set<Feature> nodeColors = getColors(node);
			ASTNode parent = node.getParent();
			if (parent != null
					&& !nodeColors.equals(colorManager.getColors(parent))) {
				UnsupportedColoring er = isSupportedColoring(node);
				if (er != null)
					unsupportedColorings.add(er);

			}
		}
		return super.visitNode(node);
	}

	//
	// private void tmpDeleteTransactions(ASTNode node) {
	// Feature toDelete = FeatureManager.getFeatures().get(38);
	// if (colorManager.getOwnColors(node).contains(toDelete))
	// colorManager.removeColor(node, toDelete);
	// }

	private UnsupportedColoring isSupportedColoring(ASTNode node) {
		if (node instanceof CompilationUnit)
			return null;
		if (isImportStatement(node))
			return null;
		if (isMovableType(node))
			return null;
		if (isExtendsOrImplements(node))
			return null;
		if (isRefinableMethodOrField(node))
			return null;
		if (isSubtreeRuleException(node))
			return new UnsupportedColoring(node,
					"Multiple exceptions from subtree rule in one node");
		if (isAllStatements(node))
			return null;
		if (isAroundAdvice(node))
			return null;
		UnsupportedColoring r = isStatementExtractableWithHook(node);
		if (r != dummy)
			return r;
		if (isStatementOutsideBlock(node))
			return new UnsupportedColoring(node,
					"Colored statement must be placed in block");

		return new UnsupportedColoring(node,
				"Export not supported (too fine granularity?)");
	}

	private boolean isExtendsOrImplements(ASTNode node) {
		if (isMovableType(node.getParent())) {
			if (node.getLocationInParent() == TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY) {
				return true;
			}
			if (node.getLocationInParent() == TypeDeclaration.SUPERCLASS_TYPE_PROPERTY) {
				return true;
			}
		}
		return false;
	}

	private boolean isImportStatement(ASTNode node) {
		return node instanceof ImportDeclaration;
	}

	private boolean isAllStatements(ASTNode node) {
		if (isToplevelStatementInMethod(node)) {
			if (RefactoringUtils.canRefactorAllStatements(RefactoringUtils
					.getMethodDeclaration(node), colorManager, getColors(node)))
				return true;
		}
		return false;
	}

	private ASTNode cachedNode = null;
	private Set<Feature> cachedColors = null;

	private Set<Feature> getColors(ASTNode node) {
		if (cachedNode == node)
			return cachedColors;
		return cachedColors = colorManager.getColors(cachedNode = node);
	}

	private boolean isSubtreeRuleException(ASTNode node) {
		return RefactoringUtils.hasMultipleSubtreeRuleException(node,
				colorManager, getColors(node));
	}

	// private boolean isStatementInCompletelyColoredBody(ASTNode node) {
	// if (isToplevelStatementInMethod(node)) {
	// MethodDeclaration method = RefactoringUtils
	// .getMethodDeclaration(node);
	// if (RefactoringUtils.areAllStatementsColored(method, colorManager,
	// derivative))
	// if (RefactoringUtils.canRefactorAllStatements(method,
	// colorManager, derivative))
	// return true;
	// }
	// return false;
	// }

	private final AroundAdviceAnalyzer aroundAdviceAnalyzer = new AroundAdviceAnalyzer();

	private class AroundAdviceAnalyzer {
		private Set<MethodDeclaration> analyzedMethods = new HashSet<MethodDeclaration>();
		private Set<Statement> coveredStatements = new HashSet<Statement>();

		void analyzeMethod(MethodDeclaration method, Set<Feature> derivative) {
			if (!analyzedMethods.add(method))
				return;

			List<Statement> before = RefactoringUtils.findBeforeStatements(
					method, colorManager, derivative);
			List<Statement> after = RefactoringUtils.findAfterStatements(
					method, colorManager, derivative);
			if (RefactoringUtils.canRefactorStatementsBeforeAfter(method,
					before, after, colorManager, derivative)) {
				coveredStatements.addAll(before);
				coveredStatements.addAll(after);
			}

		}

		boolean isStatementAccessibleByAroundAdvice(ASTNode node) {
			analyzeMethod(RefactoringUtils.getMethodDeclaration(node),
					getColors(node));
			return coveredStatements.contains(node);
		}

	}

	private boolean isAroundAdvice(ASTNode node) {
		if (isToplevelStatementInMethod(node)) {
			if (aroundAdviceAnalyzer.isStatementAccessibleByAroundAdvice(node))
				return true;
		}
		return false;
	}

	private boolean isToplevelStatementInMethod(ASTNode node) {
		return node instanceof Statement && node.getParent() instanceof Block
				&& node.getParent().getParent() instanceof MethodDeclaration;
	}

	/**
	 * whole compilation units and toplevel types
	 */
	private boolean isMovableType(ASTNode node) {
		if (node instanceof TypeDeclaration) {
			if (node.getParent() instanceof CompilationUnit)
				return true;
		}
		return false;
	}

	/**
	 * methods in toplevel types
	 */
	private boolean isRefinableMethodOrField(ASTNode node) {
		if (node instanceof MethodDeclaration
				|| node instanceof FieldDeclaration) {
			if (node.getParent() instanceof TypeDeclaration
					&& node.getParent().getParent() instanceof CompilationUnit)
				// methods in top-level types ok
				return true;
		}
		return false;
	}

	private boolean isStatementOutsideBlock(ASTNode node) {
		if (node instanceof Statement)
			return !(node.getParent() instanceof Block);
		return false;
	}

	/**
	 * statements unless access to local variables restricts it
	 */
	private UnsupportedColoring isStatementExtractableWithHook(ASTNode node) {
		if (node instanceof Statement) {
			if (node.getParent() instanceof Block
					&& isRefinableMethodOrField(RefactoringUtils
							.getMethodDeclaration(node))) {
				List<Statement> statementsToExtract = findStatementsBelongingToHook(node);

				assert !statementsToExtract.isEmpty();
				LocalVariableAnalyzer localVariableAnalyzer = new LocalVariableAnalyzer(
						RefactoringUtils.getMethodDeclaration(node),
						statementsToExtract, colorManager);
				localVariableAnalyzer.execute();

				// support only a single return value for now
				if (localVariableAnalyzer.getReturns().size() > 1)
					return new UnsupportedColoringMultipleWriteAccess(node,
							localVariableAnalyzer.getReturns());

				// // local variables accessed must be uncolored (or have less
				// // colors than the statement currently analyzed)
				// if (localVariableAnalyzer
				// .containsColorVariableDeclarationsAsParameters(colorManager
				// .getColors(node)))
				// return new UnsupportedColoring(node, "Access to colored
				// variables");

				// all other cases are possible
				return null;
			}
		}
		return dummy;
	}

	UnsupportedColoring dummy = new UnsupportedColoring(null, "");

	/**
	 * searches for a sequence of statements that belong together and are
	 * colored accordingly around the given node
	 * 
	 * @param node
	 * @return
	 */
	private List<Statement> findStatementsBelongingToHook(ASTNode node) {
		assert node.getParent() instanceof Block;
		Block block = (Block) node.getParent();
		List<Statement> blockStatements = block.statements();

		boolean foundTargetNode = false;
		List<Statement> statementsToExtract = new ArrayList<Statement>();
		for (int stmtIdx = 0; stmtIdx < blockStatements.size(); stmtIdx++) {
			Statement statement = blockStatements.get(stmtIdx);
			boolean shouldExtract = colorManager.getColors(statement).equals(
					getColors(node));
			if (statement == node)
				foundTargetNode = true;
			if (shouldExtract)
				statementsToExtract.add(statement);
			else {
				// if uncolored statement was found before the target
				// node, reset this is another hook method, otherwise
				// quit, we found our hook sequence
				if (foundTargetNode)
					stmtIdx = blockStatements.size();
				else
					statementsToExtract.clear();
			}
		}
		return statementsToExtract;
	}
}
