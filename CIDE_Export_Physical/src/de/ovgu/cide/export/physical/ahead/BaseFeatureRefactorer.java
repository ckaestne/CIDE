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