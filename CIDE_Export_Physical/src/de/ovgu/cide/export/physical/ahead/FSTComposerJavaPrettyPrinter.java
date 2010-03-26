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

import java.util.Iterator;
import java.util.Stack;

import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeParameter;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.physical.ahead.ast.IJakASTVisitor;
import de.ovgu.cide.export.physical.ahead.ast.JakClassRefinement;
import de.ovgu.cide.export.physical.ahead.ast.JakCompilationUnit;
import de.ovgu.cide.export.physical.internal.IPhysicalOptions;

/**
 * this is a printer that process kind of standard java (which cannot be
 * compiled directly though, because inheritance is not fixed). this is meant as
 * input for the FSTComposer's Java frontend
 * 
 * 
 * @author cKaestner
 * 
 */
public class FSTComposerJavaPrettyPrinter extends CopiedNaiveASTFlattener
		implements IJakASTVisitor {
	private final IPhysicalOptions options;

	public FSTComposerJavaPrettyPrinter(IPhysicalOptions options) {
		this.options = options;
	}

	protected boolean isInRefinement() {
		return currentRefinement.size() > 0;
	}

	private final Stack<JakClassRefinement> currentRefinement = new Stack<JakClassRefinement>();

	private final Stack<MethodDeclaration> currentMethod = new Stack<MethodDeclaration>();

	public boolean visit(JakCompilationUnit node) {
		// if (node.getLayer().length() > 0) {
		// this.buffer.append("layer ");
		// this.buffer.append(node.getLayer());
		// this.buffer.append(";\n\n");
		// }

		// if (node.getPackage() != null) {
		// node.getPackage().accept(this);
		// }
		// for (Iterator it = node.imports().iterator(); it.hasNext();) {
		// ImportDeclaration d = (ImportDeclaration) it.next();
		// d.accept(this);
		// }
		// if (node.getRefinement() != null)
		// node.getRefinement().accept(this);
		// return false;
		return true;
	}

	public boolean visit(JakClassRefinement node) {
		currentRefinement.push(node);

		// if (node.getJavadoc() != null) {
		// node.getJavadoc().accept(this);
		// }
		printModifiers(node.modifiers());
		this.buffer.append(node.isInterface() ? "interface " : "class ");//$NON-NLS-2$//$NON-NLS-1$
		node.getName().accept(this);
		if (!node.typeParameters().isEmpty()) {
			this.buffer.append("<");//$NON-NLS-1$
			for (Iterator<?> it = node.typeParameters().iterator(); it
					.hasNext();) {
				TypeParameter t = (TypeParameter) it.next();
				t.accept(this);
				if (it.hasNext()) {
					this.buffer.append(",");//$NON-NLS-1$
				}
			}
			this.buffer.append(">");//$NON-NLS-1$
		}
		this.buffer.append(" ");//$NON-NLS-1$
		if (node.getSuperclassType() != null) {
			this.buffer.append("extends ");//$NON-NLS-1$
			node.getSuperclassType().accept(this);
			this.buffer.append(" ");//$NON-NLS-1$
		}
		if (!node.superInterfaceTypes().isEmpty()) {
			this.buffer.append("implements ");//$NON-NLS-2$//$NON-NLS-1$
			for (Iterator<?> it = node.superInterfaceTypes().iterator(); it
					.hasNext();) {
				Type t = (Type) it.next();
				t.accept(this);
				if (it.hasNext()) {
					this.buffer.append(", ");//$NON-NLS-1$
				}
			}
			this.buffer.append(" ");//$NON-NLS-1$
		}
		this.buffer.append("{\n");//$NON-NLS-1$
		this.indent++;
		BodyDeclaration prev = null;
		for (Iterator<?> it = node.bodyDeclarations().iterator(); it.hasNext();) {
			BodyDeclaration d = (BodyDeclaration) it.next();
			if (prev instanceof EnumConstantDeclaration) {
				// enum constant declarations do not include punctuation
				if (d instanceof EnumConstantDeclaration) {
					// enum constant declarations are separated by commas
					this.buffer.append(", ");//$NON-NLS-1$
				} else {
					// semicolon separates last enum constant declaration from
					// first class body declarations
					this.buffer.append("; ");//$NON-NLS-1$
				}
			}
			d.accept(this);
		}
		if (options.isDebugPrintInnerClassRefinements()
				|| !options.getMethodObjectsInStaticToplevelClass()) {
			for (JakClassRefinement ref : node.getInnerClassRefinements()) {
				ref.accept(this);
			}
		}
		this.indent--;
		printIndent();
		this.buffer.append("}\n");//$NON-NLS-1$
		return false;
	}

	public void endVisit(JakCompilationUnit node) {
	}

	public void endVisit(JakClassRefinement node) {
		currentRefinement.pop();
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		currentMethod.pop();
		super.endVisit(node);
	}

	/*
	 * @see ASTVisitor#visit(SuperConstructorInvocation)
	 */
	public boolean visit(SuperConstructorInvocation node) {
		if (!SuperCallHelper.isSuperLayerCall(node))
			return super.visit(node);
		/**
		 * no explicit super constructor invocation in Jak.
		 */
		//
		// printIndent();
		// if (node.getExpression() != null) {
		// node.getExpression().accept(this);
		// this.buffer.append(".");//$NON-NLS-1$
		// }
		// if (node.getAST().apiLevel() >= AST.JLS3) {
		// if (!node.typeArguments().isEmpty()) {
		// this.buffer.append("<");//$NON-NLS-1$
		// for (Iterator it = node.typeArguments().iterator(); it
		// .hasNext();) {
		// Type t = (Type) it.next();
		// t.accept(this);
		// if (it.hasNext()) {
		// this.buffer.append(",");//$NON-NLS-1$
		// }
		// }
		// this.buffer.append(">");//$NON-NLS-1$
		// }
		// }
		// this.buffer.append("Super(");//$NON-NLS-1$
		// String types = SuperTypeHelper.getCachedTypes(node);
		// this.buffer.append(types);
		//
		// this.buffer.append(")(");//$NON-NLS-1$
		//
		// for (Iterator it = node.arguments().iterator(); it.hasNext();) {
		// Expression e = (Expression) it.next();
		// e.accept(this);
		// if (it.hasNext()) {
		// this.buffer.append(",");//$NON-NLS-1$
		// }
		// }
		// this.buffer.append(");\n");//$NON-NLS-1$
		return false;
	}

	/*
	 * @see ASTVisitor#visit(SuperMethodInvocation)
	 */
	public boolean visit(SuperMethodInvocation node) {
		if (!SuperCallHelper.isSuperLayerCall(node))
			return super.visit(node);

		// if (node.getQualifier() != null) {
		// node.getQualifier().accept(this);
		// this.buffer.append(".");//$NON-NLS-1$
		// }
		this.buffer.append("original");//$NON-NLS-1$
		this.buffer.append("(");//$NON-NLS-1$
		for (Iterator<?> it = node.arguments().iterator(); it.hasNext();) {
			Expression e = (Expression) it.next();
			e.accept(this);
			if (it.hasNext()) {
				this.buffer.append(",");//$NON-NLS-1$
			}
		}
		this.buffer.append(")");//$NON-NLS-1$
		return false;
	}

	protected void hook_beforeVisitMethodDeclaration(MethodDeclaration node) {
		// if (node.isConstructor())
		// return;
		// if (currentRefinement.size() > 0) {
		// if (currentRefinement.peek().refinements().contains(node))
		// buffer.append("overrides ");
		// if (currentRefinement.peek().introductions().contains(node))
		// buffer.append("new ");
		//
		// }
	}

	/*
	 * @see ASTVisitor#visit(MethodDeclaration)
	 */
	public boolean visit(MethodDeclaration node) {
		currentMethod.push(node);
		if (!node.isConstructor() || currentRefinement.size() == 0
				|| !currentRefinement.peek().refinements().contains(node))
			return super.visit(node);

		// only special treatment for constructor refinements!
		if (node.getJavadoc() != null) {
			node.getJavadoc().accept(this);
		}
		printIndent();
		// buffer.append("refines ");
		node.getName().accept(this);
		this.buffer.append("(");//$NON-NLS-1$
		for (Iterator<?> it = node.parameters().iterator(); it.hasNext();) {
			SingleVariableDeclaration v = (SingleVariableDeclaration) it.next();
			v.accept(this);
			if (it.hasNext()) {
				this.buffer.append(",");//$NON-NLS-1$
			}
		}
		this.buffer.append(")");//$NON-NLS-1$
		for (int i = 0; i < node.getExtraDimensions(); i++) {
			this.buffer.append("[]"); //$NON-NLS-1$
		}
		if (!node.thrownExceptions().isEmpty()) {
			this.buffer.append(" throws ");//$NON-NLS-1$
			for (Iterator<?> it = node.thrownExceptions().iterator(); it
					.hasNext();) {
				Name n = (Name) it.next();
				n.accept(this);
				if (it.hasNext()) {
					this.buffer.append(", ");//$NON-NLS-1$
				}
			}
			this.buffer.append(" ");//$NON-NLS-1$
		}
		if (node.getBody() == null) {
			this.buffer.append(";\n");//$NON-NLS-1$
		} else {
			node.getBody().accept(this);
		}
		return false;
	}

}
