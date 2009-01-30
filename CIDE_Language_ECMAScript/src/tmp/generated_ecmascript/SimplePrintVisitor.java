package tmp.generated_ecmascript;

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
		if (node instanceof Program) {
			Program n = (Program)node;
			{
				SourceElements v=n.getSourceElements();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getEof();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression1) {
			PrimaryExpression1 n = (PrimaryExpression1)node;
			printToken("this");
			return false;
		}
		if (node instanceof PrimaryExpression2) {
			PrimaryExpression2 n = (PrimaryExpression2)node;
			{
				ObjectLiteral v=n.getObjectLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression3) {
			PrimaryExpression3 n = (PrimaryExpression3)node;
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
		if (node instanceof PrimaryExpression4) {
			PrimaryExpression4 n = (PrimaryExpression4)node;
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression5) {
			PrimaryExpression5 n = (PrimaryExpression5)node;
			{
				ArrayLiteral v=n.getArrayLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression6) {
			PrimaryExpression6 n = (PrimaryExpression6)node;
			{
				Literal v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal1) {
			Literal1 n = (Literal1)node;
			{
				ASTStringNode v=n.getDecimal_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal2) {
			Literal2 n = (Literal2)node;
			{
				ASTStringNode v=n.getHex_integer_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal3) {
			Literal3 n = (Literal3)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal4) {
			Literal4 n = (Literal4)node;
			{
				ASTStringNode v=n.getBoolean_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal5) {
			Literal5 n = (Literal5)node;
			{
				ASTStringNode v=n.getNull_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal6) {
			Literal6 n = (Literal6)node;
			{
				ASTStringNode v=n.getRegular_expression_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Identifier) {
			Identifier n = (Identifier)node;
			{
				ASTStringNode v=n.getIdentifier_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ArrayLiteral1) {
			ArrayLiteral1 n = (ArrayLiteral1)node;
			printToken("[");
			{
				Elision v=n.getElision();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof ArrayLiteral2) {
			ArrayLiteral2 n = (ArrayLiteral2)node;
			printToken("[");
			{
				ElementList v=n.getElementList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Elision v=n.getElision1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof ArrayLiteral3) {
			ArrayLiteral3 n = (ArrayLiteral3)node;
			printToken("[");
			{
				ElementList v=n.getElementList1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof ElementList) {
			ElementList n = (ElementList)node;
			{
				Elision v=n.getElision();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ElementListEnd v : n.getElementListEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ElementListEnd) {
			ElementListEnd n = (ElementListEnd)node;
			{
				Elision v=n.getElision();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Elision) {
			Elision n = (Elision)node;
			for (ASTTextNode v : n.getText283()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ObjectLiteral) {
			ObjectLiteral n = (ObjectLiteral)node;
			printToken("{");
			{
				PropertyNameAndValueList v=n.getPropertyNameAndValueList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof PropertyNameAndValueList) {
			PropertyNameAndValueList n = (PropertyNameAndValueList)node;
			{
				PropertyNameAndValue v=n.getPropertyNameAndValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (PropertyNameAndValueListEnd v : n.getPropertyNameAndValueListEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof PropertyNameAndValueListEnd1) {
			PropertyNameAndValueListEnd1 n = (PropertyNameAndValueListEnd1)node;
			printToken(",");
			{
				PropertyNameAndValue v=n.getPropertyNameAndValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PropertyNameAndValueListEnd2) {
			PropertyNameAndValueListEnd2 n = (PropertyNameAndValueListEnd2)node;
			printToken(",");
			return false;
		}
		if (node instanceof PropertyNameAndValue) {
			PropertyNameAndValue n = (PropertyNameAndValue)node;
			{
				PropertyName v=n.getPropertyName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PropertyName1) {
			PropertyName1 n = (PropertyName1)node;
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PropertyName2) {
			PropertyName2 n = (PropertyName2)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PropertyName3) {
			PropertyName3 n = (PropertyName3)node;
			{
				ASTStringNode v=n.getDecimal_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberExpression1) {
			MemberExpression1 n = (MemberExpression1)node;
			{
				MemberExpressionPre v=n.getMemberExpressionPre();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (MemberExpressionPart v : n.getMemberExpressionPart()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof MemberExpression2) {
			MemberExpression2 n = (MemberExpression2)node;
			{
				AllocationExpression v=n.getAllocationExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberExpressionPre1) {
			MemberExpressionPre1 n = (MemberExpressionPre1)node;
			{
				FunctionExpression v=n.getFunctionExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberExpressionPre2) {
			MemberExpressionPre2 n = (MemberExpressionPre2)node;
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberExpressionForIn) {
			MemberExpressionForIn n = (MemberExpressionForIn)node;
			{
				MemberExpressionPre v=n.getMemberExpressionPre();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (MemberExpressionPart v : n.getMemberExpressionPart()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof AllocationExpression) {
			AllocationExpression n = (AllocationExpression)node;
			printToken("new");
			{
				MemberExpression v=n.getMemberExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AllocationExpressionEnd v : n.getAllocationExpressionEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof AllocationExpressionEnd) {
			AllocationExpressionEnd n = (AllocationExpressionEnd)node;
			{
				Arguments v=n.getArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (MemberExpressionPart v : n.getMemberExpressionPart()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof MemberExpressionPart1) {
			MemberExpressionPart1 n = (MemberExpressionPart1)node;
			printToken("[");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof MemberExpressionPart2) {
			MemberExpressionPart2 n = (MemberExpressionPart2)node;
			printToken(".");
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CallExpression) {
			CallExpression n = (CallExpression)node;
			{
				MemberExpression v=n.getMemberExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Arguments v=n.getArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CallExpressionPart v : n.getCallExpressionPart()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof CallExpressionForIn) {
			CallExpressionForIn n = (CallExpressionForIn)node;
			{
				MemberExpressionForIn v=n.getMemberExpressionForIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Arguments v=n.getArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (CallExpressionPart v : n.getCallExpressionPart()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof CallExpressionPart1) {
			CallExpressionPart1 n = (CallExpressionPart1)node;
			{
				Arguments v=n.getArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CallExpressionPart2) {
			CallExpressionPart2 n = (CallExpressionPart2)node;
			printToken("[");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof CallExpressionPart3) {
			CallExpressionPart3 n = (CallExpressionPart3)node;
			printToken(".");
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Arguments) {
			Arguments n = (Arguments)node;
			printToken("(");
			{
				ArgumentList v=n.getArgumentList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof ArgumentList) {
			ArgumentList n = (ArgumentList)node;
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AssignmentExpression v : n.getAssignmentExpression1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof LeftHandSideExpression1) {
			LeftHandSideExpression1 n = (LeftHandSideExpression1)node;
			{
				CallExpression v=n.getCallExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LeftHandSideExpression2) {
			LeftHandSideExpression2 n = (LeftHandSideExpression2)node;
			{
				MemberExpression v=n.getMemberExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LeftHandSideExpressionForIn1) {
			LeftHandSideExpressionForIn1 n = (LeftHandSideExpressionForIn1)node;
			{
				CallExpressionForIn v=n.getCallExpressionForIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LeftHandSideExpressionForIn2) {
			LeftHandSideExpressionForIn2 n = (LeftHandSideExpressionForIn2)node;
			{
				MemberExpressionForIn v=n.getMemberExpressionForIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PostfixExpression) {
			PostfixExpression n = (PostfixExpression)node;
			{
				LeftHandSideExpression v=n.getLeftHandSideExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				PostfixOperator v=n.getPostfixOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PostfixOperator1) {
			PostfixOperator1 n = (PostfixOperator1)node;
			printToken("++");
			return false;
		}
		if (node instanceof PostfixOperator2) {
			PostfixOperator2 n = (PostfixOperator2)node;
			printToken("--");
			return false;
		}
		if (node instanceof UnaryExpression1) {
			UnaryExpression1 n = (UnaryExpression1)node;
			{
				PostfixExpression v=n.getPostfixExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpression2) {
			UnaryExpression2 n = (UnaryExpression2)node;
			for (UnaryExpressionOp v : n.getUnaryExpressionOp()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof UnaryExpressionOp) {
			UnaryExpressionOp n = (UnaryExpressionOp)node;
			{
				UnaryOperator v=n.getUnaryOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryOperator1) {
			UnaryOperator1 n = (UnaryOperator1)node;
			printToken("delete");
			return false;
		}
		if (node instanceof UnaryOperator2) {
			UnaryOperator2 n = (UnaryOperator2)node;
			printToken("void");
			return false;
		}
		if (node instanceof UnaryOperator3) {
			UnaryOperator3 n = (UnaryOperator3)node;
			printToken("typeof");
			return false;
		}
		if (node instanceof UnaryOperator4) {
			UnaryOperator4 n = (UnaryOperator4)node;
			printToken("++");
			return false;
		}
		if (node instanceof UnaryOperator5) {
			UnaryOperator5 n = (UnaryOperator5)node;
			printToken("--");
			return false;
		}
		if (node instanceof UnaryOperator6) {
			UnaryOperator6 n = (UnaryOperator6)node;
			printToken("+");
			return false;
		}
		if (node instanceof UnaryOperator7) {
			UnaryOperator7 n = (UnaryOperator7)node;
			printToken("-");
			return false;
		}
		if (node instanceof UnaryOperator8) {
			UnaryOperator8 n = (UnaryOperator8)node;
			printToken("~");
			return false;
		}
		if (node instanceof UnaryOperator9) {
			UnaryOperator9 n = (UnaryOperator9)node;
			printToken("!");
			return false;
		}
		if (node instanceof MultiplicativeExpression) {
			MultiplicativeExpression n = (MultiplicativeExpression)node;
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (MultiplicativeExpressionEnd v : n.getMultiplicativeExpressionEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof MultiplicativeExpressionEnd) {
			MultiplicativeExpressionEnd n = (MultiplicativeExpressionEnd)node;
			{
				MultiplicativeOperator v=n.getMultiplicativeOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MultiplicativeOperator1) {
			MultiplicativeOperator1 n = (MultiplicativeOperator1)node;
			printToken("*");
			return false;
		}
		if (node instanceof MultiplicativeOperator2) {
			MultiplicativeOperator2 n = (MultiplicativeOperator2)node;
			{
				ASTStringNode v=n.getSlash();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MultiplicativeOperator3) {
			MultiplicativeOperator3 n = (MultiplicativeOperator3)node;
			printToken("%");
			return false;
		}
		if (node instanceof AdditiveExpression) {
			AdditiveExpression n = (AdditiveExpression)node;
			{
				MultiplicativeExpression v=n.getMultiplicativeExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AdditiveExpressionEnd v : n.getAdditiveExpressionEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof AdditiveExpressionEnd) {
			AdditiveExpressionEnd n = (AdditiveExpressionEnd)node;
			{
				AdditiveOperator v=n.getAdditiveOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				MultiplicativeExpression v=n.getMultiplicativeExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AdditiveOperator1) {
			AdditiveOperator1 n = (AdditiveOperator1)node;
			printToken("+");
			return false;
		}
		if (node instanceof AdditiveOperator2) {
			AdditiveOperator2 n = (AdditiveOperator2)node;
			printToken("-");
			return false;
		}
		if (node instanceof ShiftExpression) {
			ShiftExpression n = (ShiftExpression)node;
			{
				AdditiveExpression v=n.getAdditiveExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ShiftExpressionEnd v : n.getShiftExpressionEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ShiftExpressionEnd) {
			ShiftExpressionEnd n = (ShiftExpressionEnd)node;
			{
				ShiftOperator v=n.getShiftOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AdditiveExpression v=n.getAdditiveExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ShiftOperator1) {
			ShiftOperator1 n = (ShiftOperator1)node;
			printToken("<<");
			return false;
		}
		if (node instanceof ShiftOperator2) {
			ShiftOperator2 n = (ShiftOperator2)node;
			printToken(">>");
			return false;
		}
		if (node instanceof ShiftOperator3) {
			ShiftOperator3 n = (ShiftOperator3)node;
			printToken(">>>");
			return false;
		}
		if (node instanceof RelationalExpression) {
			RelationalExpression n = (RelationalExpression)node;
			{
				ShiftExpression v=n.getShiftExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (RelationalExpressionEnd v : n.getRelationalExpressionEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof RelationalExpressionEnd) {
			RelationalExpressionEnd n = (RelationalExpressionEnd)node;
			{
				RelationalOperator v=n.getRelationalOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ShiftExpression v=n.getShiftExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RelationalOperator1) {
			RelationalOperator1 n = (RelationalOperator1)node;
			printToken("<");
			return false;
		}
		if (node instanceof RelationalOperator2) {
			RelationalOperator2 n = (RelationalOperator2)node;
			printToken(">");
			return false;
		}
		if (node instanceof RelationalOperator3) {
			RelationalOperator3 n = (RelationalOperator3)node;
			printToken("<=");
			return false;
		}
		if (node instanceof RelationalOperator4) {
			RelationalOperator4 n = (RelationalOperator4)node;
			printToken(">=");
			return false;
		}
		if (node instanceof RelationalOperator5) {
			RelationalOperator5 n = (RelationalOperator5)node;
			printToken("instanceof");
			return false;
		}
		if (node instanceof RelationalOperator6) {
			RelationalOperator6 n = (RelationalOperator6)node;
			printToken("in");
			return false;
		}
		if (node instanceof RelationalExpressionNoIn) {
			RelationalExpressionNoIn n = (RelationalExpressionNoIn)node;
			{
				ShiftExpression v=n.getShiftExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (RelationalExpressionNoInEnd v : n.getRelationalExpressionNoInEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof RelationalExpressionNoInEnd) {
			RelationalExpressionNoInEnd n = (RelationalExpressionNoInEnd)node;
			{
				RelationalNoInOperator v=n.getRelationalNoInOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ShiftExpression v=n.getShiftExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RelationalNoInOperator1) {
			RelationalNoInOperator1 n = (RelationalNoInOperator1)node;
			printToken("<");
			return false;
		}
		if (node instanceof RelationalNoInOperator2) {
			RelationalNoInOperator2 n = (RelationalNoInOperator2)node;
			printToken(">");
			return false;
		}
		if (node instanceof RelationalNoInOperator3) {
			RelationalNoInOperator3 n = (RelationalNoInOperator3)node;
			printToken("<=");
			return false;
		}
		if (node instanceof RelationalNoInOperator4) {
			RelationalNoInOperator4 n = (RelationalNoInOperator4)node;
			printToken(">=");
			return false;
		}
		if (node instanceof RelationalNoInOperator5) {
			RelationalNoInOperator5 n = (RelationalNoInOperator5)node;
			printToken("instanceof");
			return false;
		}
		if (node instanceof EqualityExpression) {
			EqualityExpression n = (EqualityExpression)node;
			{
				RelationalExpression v=n.getRelationalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (EqualityExpressionEnd v : n.getEqualityExpressionEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EqualityExpressionEnd) {
			EqualityExpressionEnd n = (EqualityExpressionEnd)node;
			{
				EqualityOperator v=n.getEqualityOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				RelationalExpression v=n.getRelationalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EqualityExpressionNoIn) {
			EqualityExpressionNoIn n = (EqualityExpressionNoIn)node;
			{
				RelationalExpressionNoIn v=n.getRelationalExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (EqualityExpressionNoInEnd v : n.getEqualityExpressionNoInEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EqualityExpressionNoInEnd) {
			EqualityExpressionNoInEnd n = (EqualityExpressionNoInEnd)node;
			{
				EqualityOperator v=n.getEqualityOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				RelationalExpressionNoIn v=n.getRelationalExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EqualityOperator1) {
			EqualityOperator1 n = (EqualityOperator1)node;
			printToken("==");
			return false;
		}
		if (node instanceof EqualityOperator2) {
			EqualityOperator2 n = (EqualityOperator2)node;
			printToken("!=");
			return false;
		}
		if (node instanceof EqualityOperator3) {
			EqualityOperator3 n = (EqualityOperator3)node;
			printToken("===");
			return false;
		}
		if (node instanceof EqualityOperator4) {
			EqualityOperator4 n = (EqualityOperator4)node;
			printToken("!==");
			return false;
		}
		if (node instanceof BitwiseANDExpression) {
			BitwiseANDExpression n = (BitwiseANDExpression)node;
			{
				EqualityExpression v=n.getEqualityExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (EqualityExpression v : n.getEqualityExpression1()) {
				printToken("&");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof BitwiseANDExpressionNoIn) {
			BitwiseANDExpressionNoIn n = (BitwiseANDExpressionNoIn)node;
			{
				EqualityExpressionNoIn v=n.getEqualityExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (EqualityExpressionNoIn v : n.getEqualityExpressionNoIn1()) {
				printToken("&");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof BitwiseXORExpression) {
			BitwiseXORExpression n = (BitwiseXORExpression)node;
			{
				BitwiseANDExpression v=n.getBitwiseANDExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BitwiseANDExpression v : n.getBitwiseANDExpression1()) {
				printToken("^");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof BitwiseXORExpressionNoIn) {
			BitwiseXORExpressionNoIn n = (BitwiseXORExpressionNoIn)node;
			{
				BitwiseANDExpressionNoIn v=n.getBitwiseANDExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BitwiseANDExpressionNoIn v : n.getBitwiseANDExpressionNoIn1()) {
				printToken("^");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof BitwiseORExpression) {
			BitwiseORExpression n = (BitwiseORExpression)node;
			{
				BitwiseXORExpression v=n.getBitwiseXORExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BitwiseXORExpression v : n.getBitwiseXORExpression1()) {
				printToken("|");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof BitwiseORExpressionNoIn) {
			BitwiseORExpressionNoIn n = (BitwiseORExpressionNoIn)node;
			{
				BitwiseXORExpressionNoIn v=n.getBitwiseXORExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BitwiseXORExpressionNoIn v : n.getBitwiseXORExpressionNoIn1()) {
				printToken("|");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof LogicalANDExpression) {
			LogicalANDExpression n = (LogicalANDExpression)node;
			{
				BitwiseORExpression v=n.getBitwiseORExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BitwiseORExpression v : n.getBitwiseORExpression1()) {
				printToken("&&");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof LogicalANDExpressionNoIn) {
			LogicalANDExpressionNoIn n = (LogicalANDExpressionNoIn)node;
			{
				BitwiseORExpressionNoIn v=n.getBitwiseORExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BitwiseORExpressionNoIn v : n.getBitwiseORExpressionNoIn1()) {
				printToken("&&");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof LogicalORExpression) {
			LogicalORExpression n = (LogicalORExpression)node;
			{
				LogicalANDExpression v=n.getLogicalANDExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (LogicalANDExpression v : n.getLogicalANDExpression1()) {
				printToken("||");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof LogicalORExpressionNoIn) {
			LogicalORExpressionNoIn n = (LogicalORExpressionNoIn)node;
			{
				LogicalANDExpressionNoIn v=n.getLogicalANDExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (LogicalANDExpressionNoIn v : n.getLogicalANDExpressionNoIn1()) {
				printToken("||");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ConditionalExpression) {
			ConditionalExpression n = (ConditionalExpression)node;
			{
				LogicalORExpression v=n.getLogicalORExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ConditionalExpressionEnd v=n.getConditionalExpressionEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConditionalExpressionEnd) {
			ConditionalExpressionEnd n = (ConditionalExpressionEnd)node;
			printToken("?");
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				AssignmentExpression v=n.getAssignmentExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConditionalExpressionNoIn) {
			ConditionalExpressionNoIn n = (ConditionalExpressionNoIn)node;
			{
				LogicalORExpressionNoIn v=n.getLogicalORExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ConditionalExpressionNoInEnd v=n.getConditionalExpressionNoInEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConditionalExpressionNoInEnd) {
			ConditionalExpressionNoInEnd n = (ConditionalExpressionNoInEnd)node;
			printToken("?");
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				AssignmentExpressionNoIn v=n.getAssignmentExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentExpression1) {
			AssignmentExpression1 n = (AssignmentExpression1)node;
			{
				LeftHandSideExpression v=n.getLeftHandSideExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AssignmentOperator v=n.getAssignmentOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentExpression2) {
			AssignmentExpression2 n = (AssignmentExpression2)node;
			{
				ConditionalExpression v=n.getConditionalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentExpressionNoIn1) {
			AssignmentExpressionNoIn1 n = (AssignmentExpressionNoIn1)node;
			{
				LeftHandSideExpression v=n.getLeftHandSideExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AssignmentOperator v=n.getAssignmentOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AssignmentExpressionNoIn v=n.getAssignmentExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentExpressionNoIn2) {
			AssignmentExpressionNoIn2 n = (AssignmentExpressionNoIn2)node;
			{
				ConditionalExpressionNoIn v=n.getConditionalExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator1) {
			AssignmentOperator1 n = (AssignmentOperator1)node;
			printToken("=");
			return false;
		}
		if (node instanceof AssignmentOperator2) {
			AssignmentOperator2 n = (AssignmentOperator2)node;
			printToken("*=");
			return false;
		}
		if (node instanceof AssignmentOperator3) {
			AssignmentOperator3 n = (AssignmentOperator3)node;
			{
				ASTStringNode v=n.getSlashassign();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator4) {
			AssignmentOperator4 n = (AssignmentOperator4)node;
			printToken("%=");
			return false;
		}
		if (node instanceof AssignmentOperator5) {
			AssignmentOperator5 n = (AssignmentOperator5)node;
			printToken("+=");
			return false;
		}
		if (node instanceof AssignmentOperator6) {
			AssignmentOperator6 n = (AssignmentOperator6)node;
			printToken("-=");
			return false;
		}
		if (node instanceof AssignmentOperator7) {
			AssignmentOperator7 n = (AssignmentOperator7)node;
			printToken("<<=");
			return false;
		}
		if (node instanceof AssignmentOperator8) {
			AssignmentOperator8 n = (AssignmentOperator8)node;
			printToken(">>=");
			return false;
		}
		if (node instanceof AssignmentOperator9) {
			AssignmentOperator9 n = (AssignmentOperator9)node;
			printToken(">>>=");
			return false;
		}
		if (node instanceof AssignmentOperator10) {
			AssignmentOperator10 n = (AssignmentOperator10)node;
			printToken("&=");
			return false;
		}
		if (node instanceof AssignmentOperator11) {
			AssignmentOperator11 n = (AssignmentOperator11)node;
			printToken("^=");
			return false;
		}
		if (node instanceof AssignmentOperator12) {
			AssignmentOperator12 n = (AssignmentOperator12)node;
			printToken("|=");
			return false;
		}
		if (node instanceof Expression) {
			Expression n = (Expression)node;
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AssignmentExpression v : n.getAssignmentExpression1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ExpressionNoIn) {
			ExpressionNoIn n = (ExpressionNoIn)node;
			{
				AssignmentExpressionNoIn v=n.getAssignmentExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AssignmentExpressionNoIn v : n.getAssignmentExpressionNoIn1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Statement1) {
			Statement1 n = (Statement1)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement2) {
			Statement2 n = (Statement2)node;
			{
				JScriptVarStatement v=n.getJScriptVarStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement3) {
			Statement3 n = (Statement3)node;
			{
				VariableStatement v=n.getVariableStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement4) {
			Statement4 n = (Statement4)node;
			{
				EmptyStatement v=n.getEmptyStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement5) {
			Statement5 n = (Statement5)node;
			{
				LabelledStatement v=n.getLabelledStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement6) {
			Statement6 n = (Statement6)node;
			{
				ExpressionStatement v=n.getExpressionStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement7) {
			Statement7 n = (Statement7)node;
			{
				IfStatement v=n.getIfStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement8) {
			Statement8 n = (Statement8)node;
			{
				IterationStatement v=n.getIterationStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement9) {
			Statement9 n = (Statement9)node;
			{
				ContinueStatement v=n.getContinueStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement10) {
			Statement10 n = (Statement10)node;
			{
				BreakStatement v=n.getBreakStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement11) {
			Statement11 n = (Statement11)node;
			{
				ImportStatement v=n.getImportStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement12) {
			Statement12 n = (Statement12)node;
			{
				ReturnStatement v=n.getReturnStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement13) {
			Statement13 n = (Statement13)node;
			{
				WithStatement v=n.getWithStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement14) {
			Statement14 n = (Statement14)node;
			{
				SwitchStatement v=n.getSwitchStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement15) {
			Statement15 n = (Statement15)node;
			{
				ThrowStatement v=n.getThrowStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement16) {
			Statement16 n = (Statement16)node;
			{
				TryStatement v=n.getTryStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Block) {
			Block n = (Block)node;
			printToken("{");
			{
				StatementList v=n.getStatementList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof StatementList) {
			StatementList n = (StatementList)node;
			for (Statement v : n.getStatement()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof VariableStatement) {
			VariableStatement n = (VariableStatement)node;
			printToken("var");
			{
				VariableDeclarationList v=n.getVariableDeclarationList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText329();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VariableDeclarationList) {
			VariableDeclarationList n = (VariableDeclarationList)node;
			{
				VariableDeclaration v=n.getVariableDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (VariableDeclaration v : n.getVariableDeclaration1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof VariableDeclarationListNoIn) {
			VariableDeclarationListNoIn n = (VariableDeclarationListNoIn)node;
			{
				VariableDeclarationNoIn v=n.getVariableDeclarationNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (VariableDeclarationNoIn v : n.getVariableDeclarationNoIn1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof VariableDeclaration) {
			VariableDeclaration n = (VariableDeclaration)node;
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Initialiser v=n.getInitialiser();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VariableDeclarationNoIn) {
			VariableDeclarationNoIn n = (VariableDeclarationNoIn)node;
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				InitialiserNoIn v=n.getInitialiserNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Initialiser) {
			Initialiser n = (Initialiser)node;
			printToken("=");
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof InitialiserNoIn) {
			InitialiserNoIn n = (InitialiserNoIn)node;
			printToken("=");
			{
				AssignmentExpressionNoIn v=n.getAssignmentExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EmptyStatement) {
			EmptyStatement n = (EmptyStatement)node;
			printToken(";");
			return false;
		}
		if (node instanceof ExpressionStatement) {
			ExpressionStatement n = (ExpressionStatement)node;
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText331();
				if (v!=null) {
					printToken(";");
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
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Statement v=n.getStatement1();
				if (v!=null) {
					printToken("else");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IterationStatement1) {
			IterationStatement1 n = (IterationStatement1)node;
			printToken("do");
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("while");
			printToken("(");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				ASTTextNode v=n.getText332();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IterationStatement2) {
			IterationStatement2 n = (IterationStatement2)node;
			printToken("while");
			printToken("(");
			{
				Expression v=n.getExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				Statement v=n.getStatement1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IterationStatement3) {
			IterationStatement3 n = (IterationStatement3)node;
			printToken("for");
			printToken("(");
			{
				ExpressionNoIn v=n.getExpressionNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				Expression v=n.getExpression2();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				Expression v=n.getExpression3();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				Statement v=n.getStatement2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IterationStatement4) {
			IterationStatement4 n = (IterationStatement4)node;
			printToken("for");
			printToken("(");
			printToken("var");
			{
				VariableDeclarationList v=n.getVariableDeclarationList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				Expression v=n.getExpression4();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				Expression v=n.getExpression5();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				Statement v=n.getStatement3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IterationStatement5) {
			IterationStatement5 n = (IterationStatement5)node;
			printToken("for");
			printToken("(");
			printToken("var");
			{
				VariableDeclarationNoIn v=n.getVariableDeclarationNoIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("in");
			{
				Expression v=n.getExpression6();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				Statement v=n.getStatement4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IterationStatement6) {
			IterationStatement6 n = (IterationStatement6)node;
			printToken("for");
			printToken("(");
			{
				LeftHandSideExpressionForIn v=n.getLeftHandSideExpressionForIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("in");
			{
				Expression v=n.getExpression7();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				Statement v=n.getStatement5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ContinueStatement) {
			ContinueStatement n = (ContinueStatement)node;
			printToken("continue");
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText333();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BreakStatement) {
			BreakStatement n = (BreakStatement)node;
			printToken("break");
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText334();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ReturnStatement) {
			ReturnStatement n = (ReturnStatement)node;
			printToken("return");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText335();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof WithStatement) {
			WithStatement n = (WithStatement)node;
			printToken("with");
			printToken("(");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SwitchStatement) {
			SwitchStatement n = (SwitchStatement)node;
			printToken("switch");
			printToken("(");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				CaseBlock v=n.getCaseBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CaseBlock) {
			CaseBlock n = (CaseBlock)node;
			printToken("{");
			{
				CaseClauses v=n.getCaseClauses();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				CaseBlockEnd v=n.getCaseBlockEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CaseBlockEnd1) {
			CaseBlockEnd1 n = (CaseBlockEnd1)node;
			printToken("}");
			return false;
		}
		if (node instanceof CaseBlockEnd2) {
			CaseBlockEnd2 n = (CaseBlockEnd2)node;
			{
				DefaultClause v=n.getDefaultClause();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				CaseClauses v=n.getCaseClauses();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof CaseClauses) {
			CaseClauses n = (CaseClauses)node;
			for (CaseClause v : n.getCaseClause()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof CaseClause) {
			CaseClause n = (CaseClause)node;
			printToken("case");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				StatementList v=n.getStatementList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DefaultClause) {
			DefaultClause n = (DefaultClause)node;
			printToken("default");
			printToken(":");
			{
				StatementList v=n.getStatementList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LabelledStatement) {
			LabelledStatement n = (LabelledStatement)node;
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ThrowStatement) {
			ThrowStatement n = (ThrowStatement)node;
			printToken("throw");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText337();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TryStatement) {
			TryStatement n = (TryStatement)node;
			printToken("try");
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				TryStatementEnd v=n.getTryStatementEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TryStatementEnd1) {
			TryStatementEnd1 n = (TryStatementEnd1)node;
			{
				Finally v=n.getFinally_KW();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TryStatementEnd2) {
			TryStatementEnd2 n = (TryStatementEnd2)node;
			{
				Catch v=n.getCatch_KW();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Finally v=n.getFinally_KW1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Catch) {
			Catch n = (Catch)node;
			printToken("catch");
			printToken("(");
			{
				Identifier v=n.getIdentifier();
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
			return false;
		}
		if (node instanceof Finally) {
			Finally n = (Finally)node;
			printToken("finally");
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof FunctionDeclaration) {
			FunctionDeclaration n = (FunctionDeclaration)node;
			printToken("function");
			{
				Identifier v=n.getIdentifier();
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
			{
				FunctionBody v=n.getFunctionBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof FunctionExpression) {
			FunctionExpression n = (FunctionExpression)node;
			printToken("function");
			{
				Identifier v=n.getIdentifier();
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
			{
				FunctionBody v=n.getFunctionBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof FormalParameterList) {
			FormalParameterList n = (FormalParameterList)node;
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Identifier v : n.getIdentifier1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof FunctionBody) {
			FunctionBody n = (FunctionBody)node;
			printToken("{");
			{
				SourceElements v=n.getSourceElements();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof SourceElements) {
			SourceElements n = (SourceElements)node;
			for (SourceElement v : n.getSourceElement()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof SourceElement1) {
			SourceElement1 n = (SourceElement1)node;
			{
				FunctionDeclaration v=n.getFunctionDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SourceElement2) {
			SourceElement2 n = (SourceElement2)node;
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ImportStatement) {
			ImportStatement n = (ImportStatement)node;
			printToken("import");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText338();
				if (v!=null) {
					printToken(".");
					printToken("*");
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof Name) {
			Name n = (Name)node;
			{
				ASTStringNode v=n.getIdentifier_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTStringNode v : n.getIdentifier_name1()) {
				printToken(".");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof JScriptVarStatement) {
			JScriptVarStatement n = (JScriptVarStatement)node;
			printToken("var");
			{
				JScriptVarDeclarationList v=n.getJScriptVarDeclarationList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText339();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof JScriptVarDeclarationList) {
			JScriptVarDeclarationList n = (JScriptVarDeclarationList)node;
			{
				JScriptVarDeclaration v=n.getJScriptVarDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (JScriptVarDeclaration v : n.getJScriptVarDeclaration1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof JScriptVarDeclaration) {
			JScriptVarDeclaration n = (JScriptVarDeclaration)node;
			{
				Identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				ASTStringNode v=n.getIdentifier_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Initialiser v=n.getInitialiser();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
