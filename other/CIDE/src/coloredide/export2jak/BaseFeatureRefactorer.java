package coloredide.export2jak;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayAccess;
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
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import coloredide.features.Feature;
import coloredide.features.source.IColorManager;

public abstract class BaseFeatureRefactorer extends ASTVisitor {

	protected Set<Feature> derivative;

	protected IColorManager colorManager;

	public BaseFeatureRefactorer(Set<Feature> derivative,
			IColorManager colorManager) {
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