package de.ovgu.cide.export.physical.ahead;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.IFeature;

public abstract class BaseFeatureRefactorer extends ASTVisitor {

	protected Set<IFeature> derivative;

	protected RefactoringColorManager colorManager;

	public BaseFeatureRefactorer(Set<IFeature> derivative,
			RefactoringColorManager colorManager) {
		super();
		this.derivative = derivative;
		this.colorManager = colorManager;

	}

	@Deprecated
	protected void combineReturn(List<Statement> targetBody) {
		assert targetBody.size() > 0;
		assert targetBody.get(targetBody.size() - 1) instanceof ReturnStatement;
		assert targetBody.get(targetBody.size() - 2) instanceof VariableDeclarationStatement;

		ReturnStatement returnStmt = (ReturnStatement) targetBody
				.get(targetBody.size() - 1);
		VariableDeclarationStatement resultDecl = (VariableDeclarationStatement) targetBody
				.get(targetBody.size() - 2);

		targetBody.remove(resultDecl);
		returnStmt.setExpression(((VariableDeclarationFragment) resultDecl
				.fragments().get(0)).getInitializer());
	}



	protected ReturnStatement createReturnStatement(AST ast) {
		ReturnStatement returnStatement = ast.newReturnStatement();
		Expression returnExpression = ast.newSimpleName("result");
		returnStatement.setExpression(returnExpression);
		return returnStatement;
	}



}