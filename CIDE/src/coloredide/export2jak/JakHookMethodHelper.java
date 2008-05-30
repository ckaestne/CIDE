package coloredide.export2jak;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclaration;

import coloredide.export.Formal;
import coloredide.export.LocalVariableAnalyzer;
import coloredide.export.LocalVariableHelper;
import coloredide.features.source.IColorManager;

public class JakHookMethodHelper {

	private int hookIdx;

	private static int hookIdxCounter = 0;

	private Statement hookCall;

	private MethodDeclaration hookDecl;

	private MethodDeclaration refinement;

	private String name;

	private AST ast;

	private List<Statement> statements;

	private LocalVariableAnalyzer localVariableAnalyzer;

	private List<Formal> parameters;

	private List<Formal> returnValues;

	private Statement subtreeRuleException;

	public static int debug_getHookIdx() {
		return hookIdxCounter;
	}

	// private List<Formal> returnAndDeclareValues;

	/**
	 * assert !statements.isEmpty();
	 */
	JakHookMethodHelper(List<Statement> statements,
			Statement subtreeRuleException, IColorManager colorManager) {
		this(statements, RefactoringUtils.getMethodDeclaration(statements
				.get(0)), subtreeRuleException, colorManager);
	}

	private Block exceptionPlaceholder;

	private boolean hasSubtreeRuleException;

	private ASTNode subtreeRuleExceptionParent;

	private boolean subtreeRuleExceptionIsBlock;

	private List thrownExceptions;

	private boolean isStatic;

	/**
	 * 
	 * @param statements
	 * @param method
	 * @param derivative
	 * @param subtreeRuleException
	 *            can be specified if a child of one of the statements should be
	 *            preserved inside the hook declaration's body
	 */
	public JakHookMethodHelper(List<Statement> statements,
			MethodDeclaration method, Statement subtreeRuleException,
			IColorManager colorManager) {
		this.hookIdx = JakHookMethodHelper.hookIdxCounter++;
		this.name = "hook" + hookIdx;
		this.ast = method.getAST();
		this.statements = statements;
		this.thrownExceptions = calcThrownExceptions(method, statements);
		this.isStatic = isStatic(method);

		localVariableAnalyzer = new LocalVariableAnalyzer(method, statements,
				colorManager);
		localVariableAnalyzer.execute();
		parameters = LocalVariableAnalyzer.sortResult(localVariableAnalyzer
				.getParameters());
		returnValues = LocalVariableAnalyzer.sortResult(localVariableAnalyzer
				.getReturns());

		this.hasSubtreeRuleException = subtreeRuleException != null;
		if (hasSubtreeRuleException) {
			subtreeRuleExceptionParent = subtreeRuleException.getParent();
			this.subtreeRuleException = subtreeRuleException;
			subtreeRuleExceptionIsBlock = subtreeRuleException instanceof Block;
			exceptionPlaceholder = ast.newBlock();
			replaceSubtreeRuleExceptionByPlaceholder();
			assert subtreeRuleException.getParent() == null;
		}
	}

	private boolean isStatic(MethodDeclaration method) {
		for (IExtendedModifier modifier : (List<IExtendedModifier>) method
				.modifiers()) {
			if (modifier.isModifier()) {
				if (((Modifier) modifier).isStatic())
					return true;
			}
		}
		return false;
	}

	private List calcThrownExceptions(MethodDeclaration method,
			List<Statement> statements2) {
		ArrayList<Name> result = new ArrayList<Name>();
		result.addAll(method.thrownExceptions());

		/*
		 * search ast upwards for try blocks that catch additional exceptions.
		 * include those
		 * 
		 * do not include the own Return* exceptions for emulating return
		 * statements in hooks
		 */
		ASTNode node = statements2.get(0);
		while (node != null && node != method) {
			if (node.getLocationInParent() == TryStatement.BODY_PROPERTY) {
				TryStatement tryStmt = (TryStatement) node.getParent();
				for (CatchClause cc : (List<CatchClause>) tryStmt
						.catchClauses()) {
					Type exceptionType = cc.getException().getType();
					if (!containsException(result, exceptionType.toString()))
						if (!exceptionType.toString().startsWith("Return"))
							result.add(method.getAST().newSimpleName(
									exceptionType.toString()));
				}
			}

			node = node.getParent();
		}

		return result;
	}

	private boolean containsException(ArrayList<Name> exceptionList,
			String exceptionName) {
		for (Name name : exceptionList) {
			if (name.toString().equals(exceptionName))
				return true;
		}
		return false;
	}

	private void replaceSubtreeRuleExceptionByPlaceholder() {
		ASTNode parent = subtreeRuleException.getParent();
		StructuralPropertyDescriptor prop = subtreeRuleException
				.getLocationInParent();
		if (prop.isSimpleProperty() || prop.isChildProperty()) {
			parent.setStructuralProperty(prop, exceptionPlaceholder);
		} else if (prop.isChildListProperty()) {
			assert false;
		}
	}

	/**
	 * returns a statement that calls the hook method. can return additional
	 * statements if return values have to be reassigned or declared
	 */
	public Statement getHookCall() {
		if (hookCall == null) {
			createHookCall();
		}
		return hookCall;
	}

	private void createHookCall() {
		MethodInvocation hookInvocation = ast.newMethodInvocation();
		hookInvocation.setName(ast.newSimpleName(name));
		if (!isStatic)
			hookInvocation.setExpression(ast.newThisExpression());
		for (Formal formal : parameters) {
			List<Expression> args = hookInvocation.arguments();
			SimpleName param = ast.newSimpleName(formal.name);
			args.add(param);
			//update local variable helper
			VariableDeclaration decl = LocalVariableHelper.getDeclForFormal(formal);
			LocalVariableHelper.addLocalVariableAccess(param, decl);
		}
		Expression expr = hookInvocation;
		if (returnValues.size() == 1) {
			Formal formal = returnValues.get(0);
			Assignment assignment = ast.newAssignment();
			// if (returnAndDeclareValues.size() == 1)
			// assert false : "unimplemented yet";
			assignment.setLeftHandSide(ast.newSimpleName(formal.name));
			assignment.setRightHandSide(expr);
			expr = assignment;
		} else if (returnValues.size() > 0) {
			assert false : "unimplemented yet";
		}
		hookCall = ast.newExpressionStatement(expr);
	}

	public MethodDeclaration getHookDeclaration() {

		if (hookDecl == null) {
			createHookDeclaration();
		}
		return hookDecl;
	}

	/**
	 * creates the declaration of the hook method.
	 * 
	 * it has an empty body, except it was created for a code fragment that
	 * contains an exception to the subtree rule
	 */
	private void createHookDeclaration() {
		hookDecl = createHookMethodSkeleton();

		if (subtreeRuleException != null) {
			RefactoringUtils.addStatementOrBlockContent(subtreeRuleException,
					hookDecl.getBody().statements());
		}

		appendReturnStatement(hookDecl.getBody());

	}

	private void appendReturnStatement(Block block) {
		if (returnValues.size() == 1) {
			Formal formal = returnValues.get(0);
			ReturnStatement returnStmt = ast.newReturnStatement();
			SimpleName v = ast.newSimpleName(formal.name);
			returnStmt.setExpression(v);
			LocalVariableHelper.addLocalVariableAccess(v, formal);
			block.statements().add(returnStmt);
		} else if (returnValues.size() > 0) {
			assert false : "unimplemented yet";
		}
	}

	/**
	 * skeleton used for hook method and its refinements. declares parameters,
	 * name, return types, but not the body
	 * 
	 * @return
	 */
	private MethodDeclaration createHookMethodSkeleton() {
		MethodDeclaration skeleton = ast.newMethodDeclaration();
		skeleton.setConstructor(false);
		skeleton.setName(ast.newSimpleName(name));
		skeleton.modifiers().add(
				ast.newModifier(Modifier.ModifierKeyword.PROTECTED_KEYWORD));
		if (isStatic)
			skeleton.modifiers().add(
					ast.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));
		skeleton.thrownExceptions().addAll(
				ASTNode.copySubtrees(ast, thrownExceptions));
		Block block = ast.newBlock();
		skeleton.setBody(block);
		for (Formal formal : parameters) {
			List<SingleVariableDeclaration> args = skeleton.parameters();
			SingleVariableDeclaration paramDecl = ast
					.newSingleVariableDeclaration();
			paramDecl.setName(ast.newSimpleName(formal.name));
			paramDecl.setType(copyType(formal.type));
			args.add(paramDecl);
		}

		if (returnValues.size() == 1) {
			Formal formal = returnValues.get(0);
			skeleton.setReturnType2(copyType(formal.type));
		} else if (returnValues.size() > 0) {
			assert false : "unimplemented yet";
		}
		return skeleton;
	}

	public MethodDeclaration getRefinement() {
		if (refinement == null) {
			createRefinement();
		}
		return refinement;
	}

	private void createRefinement() {
		refinement = createHookMethodSkeleton();
		List<Statement> statementList = refinement.getBody().statements();

		for (Statement stmt : statements) {
			Statement copyStmt = copyStatement(stmt);
			if (hasSubtreeRuleException && subtreeRuleExceptionParent == stmt) {
				assert returnValues.size() <= 1;
				Formal formal = null;
				if (returnValues.size() == 1)
					formal = returnValues.get(0);
				Statement replacement = JakFeatureRefactorer.createSuperCall(
						refinement, ast, false, formal);
				/*
				 * we place the supercall at the old position where currently
				 * the placeholder is located
				 */
				if (subtreeRuleExceptionIsBlock) {
					Block b = ast.newBlock();
					b.statements().add(replacement);
					replacement = b;
				}

				StructuralPropertyDescriptor prop = exceptionPlaceholder
						.getLocationInParent();
				if (prop.isSimpleProperty() || prop.isChildProperty()) {
					copyStmt.setStructuralProperty(prop, replacement);
				} else if (prop.isChildListProperty()) {
					assert false;
				}

			}

			statementList.add(copyStmt);
		}
		if (subtreeRuleException == null)
			statementList.add(JakFeatureRefactorer.createSuperCall(refinement,
					ast, true, null));
		else
			appendReturnStatement(refinement.getBody());

	}

	private Statement copyStatement(Statement stmt) {
		return (Statement) ASTNode.copySubtree(stmt.getAST(), stmt);
	}

	private Type copyType(Type type) {
		return (Type) ASTNode.copySubtree(ast, type);
	}

}
