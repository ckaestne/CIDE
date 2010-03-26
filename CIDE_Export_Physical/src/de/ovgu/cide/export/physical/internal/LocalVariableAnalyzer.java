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

package de.ovgu.cide.export.physical.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * this class analyzes a method (start with MethodDeclaration) in regard to some
 * statements that should be refactored.
 * 
 * it analyzes which variables are declared, accessed and assigned where. this
 * analysis is required to find out which parameters and return values are
 * needed for hook methods.
 * 
 * 
 * @author cKaestner
 * 
 */
public class LocalVariableAnalyzer {

	private MethodDeclaration method;

	// private CompilationUnit compUnit;

	private List<Statement> statements;

	private List<Statement> ignoreStatements;

	private boolean executed = false;


	public LocalVariableAnalyzer(MethodDeclaration method,
			List<Statement> statements, Object colorManager) {
		assert method != null;
		assert statements != null && statements.size() > 0 : "no statements to analyze";
		this.method = method;
		// this.compUnit = compUnit;
		this.statements = statements;
		this.ignoreStatements = null;
	}

	public LocalVariableAnalyzer(MethodDeclaration method,
			List<Statement> statements, List<Statement> ignoreStatements,
			Object colorManager) {
		this(method, statements, colorManager);
		this.ignoreStatements = ignoreStatements;
	}

	public void execute() {
		method.accept(new LocalVariableDeclarationFinder());
		method.accept(new LocalVariableAccessFinder());
		method.accept(new LocalVariableAssignmentFinder());
		executed = true;
	}

	/**
	 * returns all formals that must be passed to the hook method as parameters
	 * 
	 * finds all variables that are accessed inside the statements and declared
	 * before
	 */
	public Set<Formal> getParameters() {
		assert executed;
		Collection<Formal> accessedInside = variableAccessInStatements;
		Set<Formal> result = new HashSet<Formal>();
		for (Iterator<Formal> i = accessedInside.iterator(); i.hasNext();) {
			Formal accessedName = i.next();
			VariableDeclaration variableDecl = LocalVariableHelper
					.getDeclForFormal(accessedName);
			if (variableDecl != null)
				if (!declaredInsideStatements(variableDecl))
					result.add(accessedName);

		}
		return result;
	}

	/**
	 * checks whether the variable declaration is inside the statements
	 * 
	 * @param variableDecl
	 * @return
	 */
	private boolean declaredInsideStatements(VariableDeclaration variableDecl) {
		ASTNode node = variableDecl;
		while (node != null) {
			if (ignoreStatements != null && ignoreStatements.contains(node))
				return false;
			if (statements != null && statements.contains(node))
				return true;
			node = node.getParent();
		}
		return false;
	}

	// public boolean containsColorVariableDeclarationsAsParameters(
	// Set<Feature> accessColor) {
	// assert executed;
	//
	// Collection<Formal> accessedInside = variableAccessInStatements.values();
	// for (String variableDeclarationKey : variableDeclarationsBefore
	// .keySet()) {
	// Formal variableDeclaredBefore = variableDeclarationsBefore
	// .get(variableDeclarationKey);
	//
	// if (accessedInside.contains(variableDeclaredBefore)) {
	// Set<Feature> colors = variableDeclarationsColors
	// .get(variableDeclarationKey);
	// if (colors != null && !colors.isEmpty())
	// if (!accessColor.containsAll(colors)
	// || accessColor.equals(colors))
	// return true;
	// }
	// }
	// return false;
	// }

	/**
	 * returns all formals that must be returned.
	 * 
	 * finds all variables that have been assigned and are accessed afterwards
	 */
	public Set<Formal> getReturns() {
		assert executed;
		Set<Formal> result = new HashSet<Formal>();
		Set<Formal> potentialReturns = new HashSet<Formal>();
		potentialReturns.addAll(variableAssignmentInStatements);
		potentialReturns.addAll(variableDeclarationsInStatements);
		Collection<Formal> accessedAfter = variableAccessAfter;
		for (Formal access : accessedAfter) {
			if (potentialReturns.contains(access))
				result.add(access);
		}
		return result;
	}

	private final Set<Formal> variableAccessBefore = new HashSet<Formal>();

	private final Set<Formal> variableAccessInStatements = new HashSet<Formal>();

	private final Set<Formal> variableAccessAfter = new HashSet<Formal>();

	private final Set<Formal> variableAssignmentInStatements = new HashSet<Formal>();
	private final Set<Formal> variableDeclarationsInStatements = new HashSet<Formal>();

	private boolean isIgnoreMethodParameters = false;

	private abstract class MethodAnalyzerASTVisitor extends ASTVisitor {
		protected long insideTargetStatements = 0;

		protected boolean encounteredTargetStatements = false;

		protected long isIgnore = 0;

		public void preVisit(ASTNode node) {
			if (ignoreStatements != null && ignoreStatements.contains(node)) {
				isIgnore++;
				return;
			}

			if (statements.contains(node)) {
				insideTargetStatements++;
				encounteredTargetStatements = true;
			}
			super.preVisit(node);
		}

		public void postVisit(ASTNode node) {
			if (ignoreStatements != null && ignoreStatements.contains(node)) {
				isIgnore--;
				return;
			}

			if (statements.contains(node))
				insideTargetStatements--;
			super.preVisit(node);
		}

		@Override
		public boolean visit(QualifiedName node) {
			if (ignoreStatements != null && ignoreStatements.contains(node))
				return false;

			return visitName(node);
		}

		public boolean visit(SimpleName node) {
			if (ignoreStatements != null && ignoreStatements.contains(node))
				return false;

			return visitName(node);
		}

		protected boolean visitName(Name node) {
			return true;
		}
	}

//	private static int formalCounter = 0;

	private class LocalVariableDeclarationFinder extends
			MethodAnalyzerASTVisitor {

		public boolean visit(SingleVariableDeclaration node) {
			if (isIgnore > 0)
				return false;

			// special check for parameters
			if (isIgnoreMethodParameters
					&& node.getParent() instanceof MethodDeclaration)
				return false;

			foundDeclaration(node);

			return super.visit(node);
		}

		public boolean visit(VariableDeclarationFragment node) {
			if (isIgnore > 0)
				return false;

			foundDeclaration(node);

			return super.visit(node);
		}

		private void foundDeclaration(VariableDeclaration decl) {
			assert decl != null;

			if (insideTargetStatements > 0) {
				Formal formal = LocalVariableHelper.getFormal(decl);
				assert (formal != null);
				variableDeclarationsInStatements.add(formal);
			}
		}

	}

	private class LocalVariableAccessFinder extends MethodAnalyzerASTVisitor {
		protected boolean visitName(Name node) {
			if (isIgnore > 0)
				return false;

			VariableDeclaration declaration = LocalVariableHelper
					.findVariableDeclaration(node);

			if (declaration == null)
				return true;

			VariableDeclaration decl = LocalVariableHelper
					.findVariableDeclaration(node);
			if (decl == null)
				return true;
			Formal formal = LocalVariableHelper.getFormal(decl);
			if (formal == null)
				return true;

			if (!encounteredTargetStatements)
				variableAccessBefore.add(formal);
			else if (insideTargetStatements > 0)
				variableAccessInStatements.add(formal);
			else
				variableAccessAfter.add(formal);

			return false;
		}
	}

	private class LocalVariableAssignmentFinder extends
			MethodAnalyzerASTVisitor {

		@Override
		public boolean visit(Assignment node) {
			if (isIgnore > 0)
				return false;
			if (insideTargetStatements <= 0)
				return true;

			Expression expr = node.getLeftHandSide();

			if (assignedExpression(expr))
				return true;

			return super.visit(node);
		}

		private boolean assignedExpression(Expression expr) {
			if (expr instanceof Name) {
				VariableDeclaration decl = LocalVariableHelper
						.findVariableDeclaration((Name) expr);
				if (decl == null)
					return true;

				if (encounteredTargetStatements && insideTargetStatements > 0) {
					Formal formal = LocalVariableHelper.getFormal(decl);
					if (formal != null)
						variableAssignmentInStatements.add(formal);
				}
			}
			return false;
		}

		@Override
		public boolean visit(PostfixExpression node) {
			if (isIgnore > 0)
				return false;

			if (assignedExpression(node.getOperand()))
				return true;
			return super.visit(node);
		}

		@Override
		public boolean visit(PrefixExpression node) {
			if (isIgnore > 0)
				return false;

			if (assignedExpression(node.getOperand()))
				return true;
			return super.visit(node);
		}
	}

	public static List<Formal> sortResult(Set<Formal> set) {
		List<Formal> result = new ArrayList<Formal>(set);
		Collections.sort(result);
		return result;
	}

	/**
	 * when analyzing methods for around-refactorings variables declared in
	 * parameters are not counted as declared-before because they can be
	 * accessed anyway
	 * 
	 * @param b
	 */
	public void setIgnoreMethodParameters(boolean b) {
		isIgnoreMethodParameters = b;
	}
}
