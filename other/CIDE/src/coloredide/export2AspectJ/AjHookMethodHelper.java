package coloredide.export2AspectJ;

import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import coloredide.export.Formal;
import coloredide.export.LocalVariableAnalyzer;
import coloredide.features.source.IColorManager;

public class AjHookMethodHelper {

	private int hookIdx;

	private static int hookIdxCounter = 0;

	private Statement hookCall;

	private MethodDeclaration hookDecl;

	private AspectJExecutionAdvice advice;

	private String name;

	private AST ast;

	private List<Statement> statements;

	private LocalVariableAnalyzer localVariableAnalyzer;

	private List<Formal> parameters;

	private List<Formal> returnValues;

	private CompilationUnit compUnit;

	// private List<Formal> returnAndDeclareValues;

	public AjHookMethodHelper(List<Statement> statements,
			MethodDeclaration method, CompilationUnit compUnit, IColorManager colorManager) {
		this.compUnit = compUnit;
		this.hookIdx = AjHookMethodHelper.hookIdxCounter++;
		this.name = "hook" + hookIdx;
		this.ast = compUnit.getAST();
		this.statements = statements;
		localVariableAnalyzer = new LocalVariableAnalyzer(method, statements,colorManager);
		localVariableAnalyzer.execute();
		parameters = LocalVariableAnalyzer.sortResult(localVariableAnalyzer
				.getParameters());
		returnValues = LocalVariableAnalyzer.sortResult(localVariableAnalyzer
				.getReturns());
		// returnAndDeclareValues = LocalVariableAnalyzer
		// .sortResult(localVariableAnalyzer.getReturnAndDeclare());
	}

	/**
	 * returns a statement that calls the hook method. can return additional
	 * statements if return values have to be reassigned or declared
	 */
	public Statement createHookCall() {
		if (hookCall == null) {
			MethodInvocation hookInvocation = ast.newMethodInvocation();
			hookInvocation.setName(ast.newSimpleName(name));
			hookInvocation.setExpression(ast.newThisExpression());
			for (Formal formal : parameters) {
				List<Expression> args = hookInvocation.arguments();
				args.add(ast.newSimpleName(formal.name));
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
		return hookCall;
	}

	public MethodDeclaration getHookDeclaration() {

		if (hookDecl == null) {
			hookDecl = createHookMethodSkeleton();
			// hookDecl.getBody().statements().addAll(statements);
		}
		return hookDecl;
	}

	/**
	 * skeleton used for hook method and its refinements. declares parameters,
	 * name, return types and return statement.
	 * 
	 * @return
	 */
	private MethodDeclaration createHookMethodSkeleton() {
		MethodDeclaration skeleton = ast.newMethodDeclaration();
		skeleton.setConstructor(false);
		skeleton.setName(ast.newSimpleName(name));
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
			ReturnStatement returnStmt = ast.newReturnStatement();
			returnStmt.setExpression(ast.newSimpleName(formal.name));
			block.statements().add(returnStmt);
		} else if (returnValues.size() > 0) {
			assert false : "unimplemented yet";
		}
		return skeleton;
	}

	public AspectJExecutionAdvice getAdvice() {
		if (advice == null) {
			MethodDeclaration hookDecl = createHookMethodSkeleton();
			advice = new AspectJExecutionAdvice(ast.newBlock(),
					(TypeDeclaration) compUnit.types().get(0), hookDecl
							.parameters(), hookDecl.getReturnType2(), hookDecl
							.getName());

			List statementList = advice.getAdviceBody().statements();
			int existingStatements = statementList.size();
			for (Statement stmt : statements) {
				Statement copyStmt = (Statement) ASTNode.copySubtree(stmt
						.getAST(), stmt);
				statementList.add(statementList.size() - existingStatements,
						copyStmt);
			}
		}
		return advice;
	}

	private Type copyType(Type type) {
		return (Type) ASTNode.copySubtree(ast, type);
	}

}
