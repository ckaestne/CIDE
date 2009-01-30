package tmp.generated_antlr;

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
			printToken("grammar");
			{
				RuleId v=n.getRuleId();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			hintNewLine();
			for (Rule v : n.getRule()) {
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
		if (node instanceof Modifier1) {
			Modifier1 n = (Modifier1)node;
			printToken("protected");
			return false;
		}
		if (node instanceof Modifier2) {
			Modifier2 n = (Modifier2)node;
			printToken("public");
			return false;
		}
		if (node instanceof Modifier3) {
			Modifier3 n = (Modifier3)node;
			printToken("private");
			return false;
		}
		if (node instanceof Modifier4) {
			Modifier4 n = (Modifier4)node;
			printToken("fragment");
			return false;
		}
		if (node instanceof Rule) {
			Rule n = (Rule)node;
			{
				Modifier v=n.getModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				RuleId v=n.getRuleId();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText5();
				if (v!=null) {
					printToken("!");
					v.accept(this);
				}
			}
			printToken(":");
			{
				altList v=n.getAltList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			hintNewLine();
			return false;
		}
		if (node instanceof block) {
			block n = (block)node;
			printToken("(");
			{
				altList v=n.getAltList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof altList) {
			altList n = (altList)node;
			Iterator<altRewrite> listElements = n.getAltRewrite().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				hintIncIndent();
				hintNewLine();
				printToken("|");
				listElements.next().accept(this);
				hintDecIndent();
			}
			return false;
		}
		if (node instanceof altRewrite) {
			altRewrite n = (altRewrite)node;
			for (elementNoOptionSpec v : n.getElementNoOptionSpec()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof elementNoOptionSpec1) {
			elementNoOptionSpec1 n = (elementNoOptionSpec1)node;
			{
				atom v=n.getAtom();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ebnfSuffix v=n.getEbnfSuffix();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof elementNoOptionSpec2) {
			elementNoOptionSpec2 n = (elementNoOptionSpec2)node;
			{
				ebnf v=n.getEbnf();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof atom1) {
			atom1 n = (atom1)node;
			{
				terminal v=n.getTerminal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof atom2) {
			atom2 n = (atom2)node;
			{
				ASTStringNode v=n.getRule_ref();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ebnf) {
			ebnf n = (ebnf)node;
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				blockModifier v=n.getBlockModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof blockModifier1) {
			blockModifier1 n = (blockModifier1)node;
			printToken("?");
			return false;
		}
		if (node instanceof blockModifier2) {
			blockModifier2 n = (blockModifier2)node;
			printToken("*");
			return false;
		}
		if (node instanceof blockModifier3) {
			blockModifier3 n = (blockModifier3)node;
			printToken("+");
			return false;
		}
		if (node instanceof blockModifier4) {
			blockModifier4 n = (blockModifier4)node;
			printToken("^");
			return false;
		}
		if (node instanceof blockModifier5) {
			blockModifier5 n = (blockModifier5)node;
			printToken("!");
			return false;
		}
		if (node instanceof terminal1) {
			terminal1 n = (terminal1)node;
			{
				ASTStringNode v=n.getChar_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof terminal2) {
			terminal2 n = (terminal2)node;
			{
				ASTStringNode v=n.getToken_ref();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof terminal3) {
			terminal3 n = (terminal3)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof terminal4) {
			terminal4 n = (terminal4)node;
			printToken(".");
			return false;
		}
		if (node instanceof terminal5) {
			terminal5 n = (terminal5)node;
			printToken("!");
			return false;
		}
		if (node instanceof notTerminal1) {
			notTerminal1 n = (notTerminal1)node;
			{
				ASTStringNode v=n.getChar_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof notTerminal2) {
			notTerminal2 n = (notTerminal2)node;
			{
				ASTStringNode v=n.getToken_ref();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof notTerminal3) {
			notTerminal3 n = (notTerminal3)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ebnfSuffix1) {
			ebnfSuffix1 n = (ebnfSuffix1)node;
			printToken("?");
			return false;
		}
		if (node instanceof ebnfSuffix2) {
			ebnfSuffix2 n = (ebnfSuffix2)node;
			printToken("*");
			return false;
		}
		if (node instanceof ebnfSuffix3) {
			ebnfSuffix3 n = (ebnfSuffix3)node;
			printToken("+");
			return false;
		}
		if (node instanceof RuleId1) {
			RuleId1 n = (RuleId1)node;
			{
				ASTStringNode v=n.getToken_ref();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RuleId2) {
			RuleId2 n = (RuleId2)node;
			{
				ASTStringNode v=n.getRule_ref();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
