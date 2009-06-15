package de.ovgu.cide.export.physical.aspectj;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;

public class AspectJPrettyPrinter extends CopiedNaiveASTFlattener {

	public boolean visit(AspectJCompilationUnit node) {
		// if (node.getJavadoc() != null) {
		// node.getJavadoc().accept(this);
		// }
		// if (node.getAST().apiLevel() == AST.JLS2_INTERNAL) {
		// printModifiers(node.getModifiers());
		// }
		// if (node.getAST().apiLevel() >= AST.JLS3) {
		// printModifiers(node.modifiers());
		// }

		for (Object importNode : node.imports) {
			this.buffer.append(importNode + "\n");
		}

		this.buffer.append("public aspect ");
		// this.buffer.append(node.isInterface() ? "interface " : "class
		// ");//$NON-NLS-2$//$NON-NLS-1$
		this.buffer.append(node.getAspectName());
		// if (node.getAST().apiLevel() >= AST.JLS3) {
		// if (!node.typeParameters().isEmpty()) {
		// this.buffer.append("<");//$NON-NLS-1$
		// for (Iterator it = node.typeParameters().iterator(); it.hasNext(); )
		// {
		// TypeParameter t = (TypeParameter) it.next();
		// t.accept(this);
		// if (it.hasNext()) {
		// this.buffer.append(",");//$NON-NLS-1$
		// }
		// }
		// this.buffer.append(">");//$NON-NLS-1$
		// }
		// }
		this.buffer.append(" ");//$NON-NLS-1$
		// if (node.getAST().apiLevel() == AST.JLS2_INTERNAL) {
		// if (node.internalGetSuperclass() != null) {
		// this.buffer.append("extends ");//$NON-NLS-1$
		// node.internalGetSuperclass().accept(this);
		// this.buffer.append(" ");//$NON-NLS-1$
		// }
		// if (!node.internalSuperInterfaces().isEmpty()) {
		// this.buffer.append(node.isInterface() ? "extends " : "implements
		// ");//$NON-NLS-2$//$NON-NLS-1$
		// for (Iterator it = node.internalSuperInterfaces().iterator();
		// it.hasNext(); ) {
		// Name n = (Name) it.next();
		// n.accept(this);
		// if (it.hasNext()) {
		// this.buffer.append(", ");//$NON-NLS-1$
		// }
		// }
		// this.buffer.append(" ");//$NON-NLS-1$
		// }
		// }
		// if (node.getAST().apiLevel() >= AST.JLS3) {
		// if (node.getSuperclassType() != null) {
		// this.buffer.append("extends ");//$NON-NLS-1$
		// node.getSuperclassType().accept(this);
		// this.buffer.append(" ");//$NON-NLS-1$
		// }
		// if (!node.superInterfaceTypes().isEmpty()) {
		// this.buffer.append(node.isInterface() ? "extends " : "implements
		// ");//$NON-NLS-2$//$NON-NLS-1$
		// for (Iterator it = node.superInterfaceTypes().iterator();
		// it.hasNext(); ) {
		// Type t = (Type) it.next();
		// t.accept(this);
		// if (it.hasNext()) {
		// this.buffer.append(", ");//$NON-NLS-1$
		// }
		// }
		// this.buffer.append(" ");//$NON-NLS-1$
		// }
		// }
		this.buffer.append("{\n");//$NON-NLS-1$
		this.indent++;
		this.buffer.append("\n");
		printIndent();
		buffer.append("// INTRODUCTIONS \n\n");
		// buffer.append("failure: printing introductions");
		Set<TypeDeclaration> classesToExtend = node.introductions().keySet();
		for (Iterator<TypeDeclaration> iter = classesToExtend.iterator(); iter
				.hasNext();) {
			// for (Iterator<HashMap<TypeDeclaration, ArrayList<ASTNode>>> it =
			// node.introductions().iterator(); it.hasNext();) {
			for (Iterator<ASTNode> iter2 = node.introductions()
					.get(iter.next()).iterator(); iter2.hasNext();) {
				iter2.next().accept(this);
			}

		}
		this.buffer.append("\n");
		printIndent();
		buffer.append("// EXECUTION ADVICE \n\n");
		node.optimizeAdvice();
		for (Iterator<AspectJExecutionAdvice> it = node.getAdviceList()
				.iterator(); it.hasNext();) {
			visit(it.next());
		}

		buffer.append("// Weaving Precedence \n\n");
		buffer.append(node.getPrecedenceDeclaration());

		this.indent--;
		printIndent();
		this.buffer.append("}\n");//$NON-NLS-1$
		return false;
	}

	public void visit(AspectJExecutionAdvice advice) {
		List<Pointcut> pc = advice.getPointcut();
		Pointcut pc1 = pc.get(0);
		Type returnType = pc1.getReturnType();
		if (returnType != null) {
			returnType.accept(this); // otherwise its a constructor
			buffer.append(" around(");
		} else {
			buffer.append(" after(");
		}
		for (Iterator it = pc.get(0).getParameters().iterator(); it.hasNext();) {
			SingleVariableDeclaration v = (SingleVariableDeclaration) it.next();
			v.accept(this);
			this.buffer.append(",");//$NON-NLS-1$
		}
		pc.get(0).getThisPointer().getName().accept(this); // this-type
		this.buffer.append(" ");
		this.buffer.append("this_");
		this.buffer.append("):");

		buffer.append("(");
		// print all pointcuts
		for (Iterator iter = pc.iterator(); iter.hasNext();) {
			buffer.append("(");

			Pointcut pointcut = (Pointcut) iter.next();
			buffer.append("execution(");//$NON-NLS-1$

			if (returnType != null) {// handle a method
				pointcut.getReturnType().accept(this);
				this.buffer.append(" ");
			}
			pointcut.getThisPointer().getName().accept(this);
			this.buffer.append(".");
			if (returnType != null)
				pointcut.getMethodName().accept(this);
			else
				buffer.append("new");
			this.buffer.append("(");
			for (Iterator<SingleVariableDeclaration> var = pointcut
					.getParameters().iterator(); var.hasNext();) {
				var.next().getType().accept(this);
				if (var.hasNext()) {
					buffer.append(",");
				}
			}
			this.buffer.append("))");

			for (SingleVariableDeclaration var : advice.getPointcut().get(0)
					.getParameters()) {
				buffer.append(" && args(");
				var.getName().accept(this);
				buffer.append(")");

			}
			buffer.append(")");
			if (iter.hasNext())
				buffer.append(" || ");
		}
		buffer.append(") && this(");
		this.buffer.append("this_");
		buffer.append(")");
		buffer.append(advice.getAdviceBody().toString().replaceAll("this\\.",
				"this__dot_"));

	}

	public void endVisit(AspectJCompilationUnit node) {
	}

}
