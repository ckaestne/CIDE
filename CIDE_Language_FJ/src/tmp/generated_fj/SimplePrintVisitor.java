package tmp.generated_fj;

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
		if (node instanceof Goal) {
			Goal n = (Goal)node;
			for (TypeDeclaration v : n.getTypeDeclaration()) {
				v.accept(this);
				hintNewLine();
				hintNewLine();
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
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("extends");
			{
				ExtendedType v=n.getExtendedType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (VarDeclaration v : n.getVarDeclaration()) {
				v.accept(this);
				hintNewLine();
			}
			{
				ClassConstructor v=n.getClassConstructor();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			for (MethodDeclaration v : n.getMethodDeclaration()) {
				hintNewLine();
				v.accept(this);
				hintNewLine();
			}
			hintDecIndent();
			printToken("}");
			return false;
		}
		if (node instanceof ExtendedType1) {
			ExtendedType1 n = (ExtendedType1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExtendedType2) {
			ExtendedType2 n = (ExtendedType2)node;
			printToken("Object");
			return false;
		}
		if (node instanceof VarDeclaration) {
			VarDeclaration n = (VarDeclaration)node;
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof ClassConstructor) {
			ClassConstructor n = (ClassConstructor)node;
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				FormalParameterList v=n.getFormalParameterList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken("{");
			hintIncIndent();
			hintNewLine();
			printToken("super");
			printToken("(");
			{
				IdentifierList v=n.getIdentifierList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken(";");
			for (FieldAssign v : n.getFieldAssign()) {
				hintNewLine();
				v.accept(this);
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			return false;
		}
		if (node instanceof FieldAssign) {
			FieldAssign n = (FieldAssign)node;
			printToken("this");
			printToken(".");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof MethodDeclaration) {
			MethodDeclaration n = (MethodDeclaration)node;
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				FormalParameterList v=n.getFormalParameterList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken("{");
			hintIncIndent();
			hintNewLine();
			printToken("return");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintDecIndent();
			hintNewLine();
			printToken("}");
			return false;
		}
		if (node instanceof IdentifierList) {
			IdentifierList n = (IdentifierList)node;
			Iterator<ASTStringNode> listElements = n.getIdentifier().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof FormalParameterList) {
			FormalParameterList n = (FormalParameterList)node;
			Iterator<FormalParameter> listElements = n.getFormalParameter().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof FormalParameter) {
			FormalParameter n = (FormalParameter)node;
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Type1) {
			Type1 n = (Type1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Type2) {
			Type2 n = (Type2)node;
			printToken("Object");
			return false;
		}
		if (node instanceof Expression) {
			Expression n = (Expression)node;
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression1) {
			PrimaryExpression1 n = (PrimaryExpression1)node;
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression2) {
			PrimaryExpression2 n = (PrimaryExpression2)node;
			{
				MethodInvoke v=n.getMethodInvoke();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression3) {
			PrimaryExpression3 n = (PrimaryExpression3)node;
			{
				FieldInvoke v=n.getFieldInvoke();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression4) {
			PrimaryExpression4 n = (PrimaryExpression4)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression5) {
			PrimaryExpression5 n = (PrimaryExpression5)node;
			{
				AllocationExpression v=n.getAllocationExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression6) {
			PrimaryExpression6 n = (PrimaryExpression6)node;
			{
				CastExpression v=n.getCastExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression7) {
			PrimaryExpression7 n = (PrimaryExpression7)node;
			{
				NestedExpression v=n.getNestedExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression8) {
			PrimaryExpression8 n = (PrimaryExpression8)node;
			printToken("this");
			return false;
		}
		if (node instanceof MethodInvoke) {
			MethodInvoke n = (MethodInvoke)node;
			{
				InvokeTarget v=n.getInvokeTarget();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(".");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				ExpressionList v=n.getExpressionList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof FieldInvoke) {
			FieldInvoke n = (FieldInvoke)node;
			{
				InvokeTarget v=n.getInvokeTarget();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(".");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof InvokeTarget1) {
			InvokeTarget1 n = (InvokeTarget1)node;
			{
				AllocationExpression v=n.getAllocationExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof InvokeTarget2) {
			InvokeTarget2 n = (InvokeTarget2)node;
			{
				NestedExpression v=n.getNestedExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof InvokeTarget3) {
			InvokeTarget3 n = (InvokeTarget3)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof InvokeTarget4) {
			InvokeTarget4 n = (InvokeTarget4)node;
			printToken("this");
			return false;
		}
		if (node instanceof AllocationExpression) {
			AllocationExpression n = (AllocationExpression)node;
			printToken("new");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				ExpressionList v=n.getExpressionList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof CastExpression) {
			CastExpression n = (CastExpression)node;
			printToken("(");
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof NestedExpression) {
			NestedExpression n = (NestedExpression)node;
			printToken("(");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof ExpressionList) {
			ExpressionList n = (ExpressionList)node;
			Iterator<Expression> listElements = n.getExpression().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		return true;
	}
}
