package tmp.generated_simple;

import java.util.*;
import cide.gast.*;

import java.io.PrintStream;

import cide.languages.*;

/** package visibility. use only via ASTNode.render() **/
public class SimplePrintVisitor extends AbstractPrintVisitor {
	public SimplePrintVisitor(PrintStream out) {
		super(out);
	}
	public SimplePrintVisitor() {
		super();
	}
	public boolean visit(IASTNode node) {
		if (node instanceof ASTStringNode){
			printToken(((ASTStringNode)node).getValue());
			return false;
		}
		if (node instanceof ASTTextNode){
			return false;
		}
		if (node instanceof CompilationUnit) {
			CompilationUnit n = (CompilationUnit)node;
			for (TypeDeclaration v : n.getTypeDeclaration()) {
				v.accept(this);
			}
			{
				ASTStringNode v=n.getEof();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeDeclaration) {
			TypeDeclaration n = (TypeDeclaration)node;
			printToken("class");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Name v=n.getName1();
				if (v!=null) {
					printToken("extends");
					v.accept(this);
				}
			}
			{
				ClassBody v=n.getClassBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Name) {
			Name n = (Name)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassBody) {
			ClassBody n = (ClassBody)node;
			printToken("{");
			hintIncIndent();
			for (Member v : n.getMember()) {
				v.accept(this);
			}
			hintDecIndent();
			printToken("}");
			return false;
		}
		if (node instanceof Member1) {
			Member1 n = (Member1)node;
			{
				Method v=n.getMethod();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Member2) {
			Member2 n = (Member2)node;
			{
				Field v=n.getField();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Method) {
			Method n = (Method)node;
			printToken("void");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			printToken(")");
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Field) {
			Field n = (Field)node;
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof Block) {
			Block n = (Block)node;
			printToken("{");
			hintIncIndent();
			for (Statement v : n.getStatement()) {
				v.accept(this);
			}
			hintDecIndent();
			printToken("}");
			return false;
		}
		if (node instanceof Statement1) {
			Statement1 n = (Statement1)node;
			{
				MethodInvocation v=n.getMethodInvocation();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof Statement2) {
			Statement2 n = (Statement2)node;
			{
				IASTNode v=n.getIfStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement3) {
			Statement3 n = (Statement3)node;
			{
				Assignment v=n.getAssignment();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof Statement4) {
			Statement4 n = (Statement4)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MethodInvocation) {
			MethodInvocation n = (MethodInvocation)node;
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			printToken(")");
			return false;
		}
		if (node instanceof Assignment) {
			Assignment n = (Assignment)node;
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IfStatement) {
			IfStatement n = (IfStatement)node;
			printToken("if");
			printToken("(");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Block v=n.getBlock1();
				if (v!=null) {
					printToken("else");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Expression1) {
			Expression1 n = (Expression1)node;
			{
				BinaryExpression v=n.getBinaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Expression2) {
			Expression2 n = (Expression2)node;
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpression) {
			UnaryExpression n = (UnaryExpression)node;
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BinaryExpression) {
			BinaryExpression n = (BinaryExpression)node;
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("+");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
