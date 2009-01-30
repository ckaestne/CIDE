package tmp.generated_gcide;

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
		if (node instanceof Grammar) {
			Grammar n = (Grammar)node;
			{
				ASTStringNode v=n.getFindintroductionblock();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			for (Production v : n.getProduction()) {
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
		if (node instanceof Production) {
			Production n = (Production)node;
			Iterator<Choice> listElements = n.getChoice().iterator();
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			hintIncIndent();
			hintNewLine();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				hintNewLine();
				printToken("|");
				listElements.next().accept(this);
			}
			hintDecIndent();
			hintNewLine();
			printToken(";");
			hintNewLine();
			hintNewLine();
			return false;
		}
		if (node instanceof Choice) {
			Choice n = (Choice)node;
			for (Text v : n.getText()) {
				v.accept(this);
			}
			for (MultAndText v : n.getMultAndText()) {
				v.accept(this);
			}
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					printToken("::");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MultAndText) {
			MultAndText n = (MultAndText)node;
			{
				Mult v=n.getMult();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Text v : n.getText()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Mult1) {
			Mult1 n = (Mult1)node;
			{
				OneOrMore v=n.getOneOrMore();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Mult2) {
			Mult2 n = (Mult2)node;
			{
				ZeroOrMore v=n.getZeroOrMore();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Mult3) {
			Mult3 n = (Mult3)node;
			{
				Wrappee v=n.getWrappee();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Mult4) {
			Mult4 n = (Mult4)node;
			{
				OneOrZero v=n.getOneOrZero();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Mult5) {
			Mult5 n = (Mult5)node;
			{
				Unit v=n.getUnit();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Mult6) {
			Mult6 n = (Mult6)node;
			{
				OptionalWithDefault v=n.getOptionalWithDefault();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof OneOrMore) {
			OneOrMore n = (OneOrMore)node;
			printToken("(");
			for (Text v : n.getText()) {
				v.accept(this);
			}
			{
				Unit v=n.getUnit();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Text v : n.getText1()) {
				v.accept(this);
			}
			printToken(")");
			printToken("+");
			return false;
		}
		if (node instanceof ZeroOrMore) {
			ZeroOrMore n = (ZeroOrMore)node;
			printToken("(");
			for (Text v : n.getText()) {
				v.accept(this);
			}
			{
				Unit v=n.getUnit();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Text v : n.getText1()) {
				v.accept(this);
			}
			printToken(")");
			printToken("*");
			return false;
		}
		if (node instanceof OneOrZero) {
			OneOrZero n = (OneOrZero)node;
			printToken("[");
			for (Text v : n.getText()) {
				v.accept(this);
			}
			{
				Unit v=n.getUnit();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Text v : n.getText1()) {
				v.accept(this);
			}
			printToken("]");
			return false;
		}
		if (node instanceof OptionalWithDefault) {
			OptionalWithDefault n = (OptionalWithDefault)node;
			printToken("OPTIONAL");
			printToken("(");
			{
				Unit v=n.getUnit();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(",");
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof Wrappee) {
			Wrappee n = (Wrappee)node;
			{
				Unit v=n.getUnit();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("!");
			return false;
		}
		if (node instanceof Unit1) {
			Unit1 n = (Unit1)node;
			{
				NonTerminal v=n.getNonTerminal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Unit2) {
			Unit2 n = (Unit2)node;
			{
				Ident v=n.getIdent();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof NonTerminal) {
			NonTerminal n = (NonTerminal)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					printToken("{");
					v.accept(this);
					printToken("}");
				}
			}
			return false;
		}
		if (node instanceof Ident1) {
			Ident1 n = (Ident1)node;
			printToken("JAVATOKEN");
			printToken("(");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof Ident2) {
			Ident2 n = (Ident2)node;
			printToken("<");
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof Text1) {
			Text1 n = (Text1)node;
			{
				Lookahead v=n.getLookahead();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Text2) {
			Text2 n = (Text2)node;
			{
				Java v=n.getJava();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Text3) {
			Text3 n = (Text3)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Text4) {
			Text4 n = (Text4)node;
			{
				LayoutHint v=n.getLayoutHint();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LayoutHint) {
			LayoutHint n = (LayoutHint)node;
			printToken("@");
			{
				ASTTextNode v=n.getText348();
				if (v!=null) {
					printToken("+");
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText349();
				if (v!=null) {
					printToken("-");
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText350();
				if (v!=null) {
					printToken("!");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Lookahead) {
			Lookahead n = (Lookahead)node;
			printToken("LOOK_AHEAD");
			printToken("(");
			{
				LookaheadBody v=n.getLookaheadBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof LookaheadBody1) {
			LookaheadBody1 n = (LookaheadBody1)node;
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LookaheadBody2) {
			LookaheadBody2 n = (LookaheadBody2)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Java) {
			Java n = (Java)node;
			printToken("JAVA");
			printToken("(");
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		return true;
	}
}
