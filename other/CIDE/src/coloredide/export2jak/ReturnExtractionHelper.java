package coloredide.export2jak;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;

import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.utils.StrUtils;

/**
 * replaces colored return statements with an exception and catches this
 * exception to return the value
 * 
 * @author cKaestner
 * 
 */
public class ReturnExtractionHelper extends ASTVisitor {

	private IColorManager colorManager;

	ReturnExtractionHelper(IColorManager colorManager) {
		this.colorManager = colorManager;
	}

	private boolean foundColoredReturn = false;

	public boolean visit(ReturnStatement node) {
		Set<Feature> returnColors = colorManager.getColors(node);
		MethodDeclaration method = RefactoringUtils.getMethodDeclaration(node);
		Set<Feature> methodColors = colorManager.getColors(method);

		boolean replaceReturn = false;
		if (!methodColors.equals(returnColors))
			replaceReturn = true;
		// check also if any of the parents are colored (the subtree rule
		// exception does not help here much, in case the return is extracted to
		// a hook method it must be replaced too
		ASTNode parent = node.getParent();
		while (parent != null && parent != method && !replaceReturn) {
			Set<Feature> parentColors = colorManager.getColors(parent);
			if (!parentColors.equals(returnColors))
				replaceReturn = true;

			parent = parent.getParent();
		}

		if (replaceReturn) {
			foundColoredReturn = true;
			replaceReturnByException(node);
		}

		return false;
	}

	private void replaceReturnByException(ReturnStatement returnStatement) {
		AST ast = returnStatement.getAST();
		Set<Feature> returnColors = colorManager.getOwnColors(returnStatement);

		Expression expr = returnStatement.getExpression();
		returnStatement.setExpression(null);

		ThrowStatement throwStatement = ast.newThrowStatement();
		ClassInstanceCreation newInstance = ast.newClassInstanceCreation();
		newInstance.setType(createExceptionType(RefactoringUtils
				.getMethodDeclaration(returnStatement)));
		if (expr != null)
			newInstance.arguments().add(expr);

		throwStatement.setExpression(newInstance);

		colorManager.setColors(throwStatement, returnColors);

		RefactoringUtils.replaceASTNode(returnStatement, throwStatement);
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		if (!foundColoredReturn)
			return;

		surroundBodyWithTryCatch(node);

		foundColoredReturn = false;
	}

	/**
	 * surround method body with try catch block.
	 * 
	 * if the last statement in the body is not a return statement (can happen
	 * e.g., when the last statement is a hook method), then also a dummy return
	 * statement is introduced that should never occur though.
	 * 
	 * @param method
	 */
	private void surroundBodyWithTryCatch(MethodDeclaration method) {
		AST ast = method.getAST();
		Block oldBody = method.getBody();
		boolean lastStatementIsReturn = oldBody.statements().get(
				oldBody.statements().size() - 1) instanceof ReturnStatement;

		Block newBody = ast.newBlock();
		method.setBody(newBody);

		TryStatement tryStatement = ast.newTryStatement();
		newBody.statements().add(tryStatement);
		tryStatement.setBody(oldBody);

		if (!lastStatementIsReturn
				&& !RefactoringUtils.isVoid(method.getReturnType2())) {
			addDummyReturnStatementHack(method, oldBody);
		}

		CatchClause catchClause = ast.newCatchClause();
		SingleVariableDeclaration exceptionDeclaration = ast
				.newSingleVariableDeclaration();
		exceptionDeclaration.setName(ast.newSimpleName("r"));
		exceptionDeclaration.setType(createExceptionType(method));

		catchClause.setException(exceptionDeclaration);
		catchClause.setBody(ast.newBlock());
		ReturnStatement returnStatement = ast.newReturnStatement();
		FieldAccess fieldAccess = ast.newFieldAccess();
		fieldAccess.setName(ast.newSimpleName("value"));
		fieldAccess.setExpression(ast.newSimpleName("r"));
		if (RefactoringUtils.isVoid(method.getReturnType2())) {
		} else if (method.getReturnType2().isPrimitiveType()) {
			returnStatement.setExpression(fieldAccess);
		} else {
			CastExpression cast = ast.newCastExpression();
			cast.setExpression(fieldAccess);
			cast.setType((Type) ASTNode.copySubtree(ast, method
					.getReturnType2()));
			returnStatement.setExpression(cast);
		}
		catchClause.getBody().statements().add(returnStatement);

		tryStatement.catchClauses().add(catchClause);
	}

	/**
	 * create a method call in the following form "throw ReturnHack.returnInt;"
	 * and add it to the body as the last statement
	 * 
	 * 
	 * @param method
	 * @param oldBody
	 */
	private void addDummyReturnStatementHack(MethodDeclaration method,
			Block body) {
		AST ast = method.getAST();
		ThrowStatement throwStmt = ast.newThrowStatement();
		FieldAccess fa = ast.newFieldAccess();
		fa.setExpression(ast.newSimpleName("ReturnHack"));
		fa.setName(ast.newSimpleName("return" + getReturnTypeStr(method)));
		throwStmt.setExpression(fa);

		body.statements().add(throwStmt);
	}

	private Type createExceptionType(MethodDeclaration method) {
		AST ast = method.getAST();
		String returnExceptionStr = "Return" + getReturnTypeStr(method);
		return ast.newSimpleType(ast.newSimpleName(returnExceptionStr));
	}

	private String getReturnTypeStr(MethodDeclaration method) {
		Type returnType = method.getReturnType2();
		if (returnType.isPrimitiveType()) {
			return StrUtils.firstUp(((PrimitiveType) returnType)
					.getPrimitiveTypeCode().toString());
		} else {
			return "Object";
		}
	}

}
