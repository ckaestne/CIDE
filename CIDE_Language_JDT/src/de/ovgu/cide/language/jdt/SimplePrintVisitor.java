package de.ovgu.cide.language.jdt;

import java.io.PrintStream;

import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cide.gast.ASTStringNode;
import cide.gast.ASTTextNode;
import cide.gast.AbstractPrintVisitor;
import cide.gast.IASTNode;
import cide.gast.Property;

/**
 * derived from NaiveASTFlattener
 * 
 * @author ckaestne
 * 
 */
public class SimplePrintVisitor extends AbstractPrintVisitor {

	public SimplePrintVisitor(PrintStream out) {
		super(out);
	}

	public SimplePrintVisitor() {
		super();
	}

	public boolean visit(IASTNode node) {
		if (node instanceof ASTStringNode) {
			printToken(((ASTStringNode) node).getValue());
			return false;
		}
		if (node instanceof ASTTextNode){
			printToken(((ASTTextNode) node).getValue());
			return false;
		}
		if (node instanceof UnifiedASTNode) {
			UnifiedASTNode unode = (UnifiedASTNode) node;

			if (unode.getEclipseASTNodeClass().equals(
					"AnnotationTypeDeclaration")) {

				accept(node, AnnotationTypeDeclaration.JAVADOC_PROPERTY.getId());

			}

			if (unode.getEclipseASTNodeClass().equals(
					CompilationUnit.class.getSimpleName())) {
				accept(unode, CompilationUnit.PACKAGE_PROPERTY.getId());
				accept(unode, CompilationUnit.IMPORTS_PROPERTY.getId());
				accept(unode, CompilationUnit.TYPES_PROPERTY.getId());
			}

			if (unode.getEclipseASTNodeClass().equals(
					TypeDeclaration.class.getSimpleName())) {

				accept(node, TypeDeclaration.JAVADOC_PROPERTY.getId());
				accept(node, TypeDeclaration.MODIFIERS2_PROPERTY.getId());
				accept(node, TypeDeclaration.INTERFACE_PROPERTY.getId());
				accept(node, TypeDeclaration.NAME_PROPERTY.getId());
				accept(node, TypeDeclaration.TYPE_PARAMETERS_PROPERTY.getId(),
						"<", ",", ">", true);
				//				this.buffer.append(" ");//$NON-NLS-1$

				accept(node, TypeDeclaration.SUPERCLASS_TYPE_PROPERTY.getId(),
						"extends", "", "", false);
				accept(node, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY
						.getId(), "implements", ",", "", false);
				printToken("{");
				hintNewLine();
				hintIncIndent();

				accept(node, TypeDeclaration.BODY_DECLARATIONS_PROPERTY.getId());

				hintDecIndent();
				printToken("}");
				hintNewLine();
			}
			
			printToken(unode.getEclipseASTNodeClass());
		}
		return false;
	}

	private void accept(IASTNode node, String childType) {
		Property prop = node.getProperty(childType);
		for (IASTNode child : prop.getChildren())
			child.accept(this);
	}

	private void accept(IASTNode node, String childType, String before,
			String separator, String after, boolean printBeforeAfterForEmptyList) {

		Property prop = node.getProperty(childType);
		IASTNode[] children = prop.getChildren();

		if (printBeforeAfterForEmptyList || children.length > 0)
			printToken(before);

		boolean first = true;
		for (IASTNode child : prop.getChildren()) {
			if (!first)
				printToken(separator);
			else
				first = false;
			child.accept(this);
		}

		if (printBeforeAfterForEmptyList || children.length > 0)
			printToken(after);
	}
}
