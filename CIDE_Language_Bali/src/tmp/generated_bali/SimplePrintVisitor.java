package tmp.generated_bali;

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
		if (node instanceof BaliParse) {
			BaliParse n = (BaliParse)node;
			{
				OptionsNode v=n.getOptionsNode();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			{
				ParserCode v=n.getParserCode();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			{
				Statements v=n.getStatements();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof OptionsNode) {
			OptionsNode n = (OptionsNode)node;
			{
				ASTStringNode v=n.get_options();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.get_options1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ParserCode) {
			ParserCode n = (ParserCode)node;
			{
				ASTStringNode v=n.get_code();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.get_code1();
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
				ASTStringNode v=n.getFindblockend();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof Statements) {
			Statements n = (Statements)node;
			for (Statement v : n.getStatement()) {
				v.accept(this);
				hintNewLine();
				hintNewLine();
			}
			return false;
		}
		if (node instanceof Statement1) {
			Statement1 n = (Statement1)node;
			{
				BaliGrammarRule v=n.getBaliGrammarRule();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement2) {
			Statement2 n = (Statement2)node;
			{
				BaliTokenDefinition v=n.getBaliTokenDefinition();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement3) {
			Statement3 n = (Statement3)node;
			{
				JavacodeProduction v=n.getJavacodeProduction();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement4) {
			Statement4 n = (Statement4)node;
			{
				RegexTokenDefinition v=n.getRegexTokenDefinition();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement5) {
			Statement5 n = (Statement5)node;
			{
				TokenManagerDeclarations v=n.getTokenManagerDeclarations();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BaliTokenDefinition) {
			BaliTokenDefinition n = (BaliTokenDefinition)node;
			{
				ASTStringNode v=n.getString();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getBali_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof JavacodeProduction) {
			JavacodeProduction n = (JavacodeProduction)node;
			{
				ASTStringNode v=n.get_javacode();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ScanBlock v=n.getScanBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TokenManagerDeclarations) {
			TokenManagerDeclarations n = (TokenManagerDeclarations)node;
			{
				ASTStringNode v=n.get_token_mgr_decls();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				ScanBlock v=n.getScanBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ScanBlock) {
			ScanBlock n = (ScanBlock)node;
			{
				ASTStringNode v=n.getFindblockbegin();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getFindblockend();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof BaliGrammarRule) {
			BaliGrammarRule n = (BaliGrammarRule)node;
			Iterator<Production> listElements = n.getProduction().iterator();
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken("|");
				listElements.next().accept(this);
			}
			printToken(";");
			return false;
		}
		if (node instanceof Production) {
			Production n = (Production)node;
			{
				Lookahead v=n.getLookahead();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Rewrite v=n.getRewrite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Lookahead) {
			Lookahead n = (Lookahead)node;
			{
				ASTStringNode v=n.get_lookahead();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				ASTStringNode v=n.getFindcloseparen();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof SimpleListNode) {
			SimpleListNode n = (SimpleListNode)node;
			printToken("(");
			{
				Lookahead v=n.getLookahead();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Primitive v=n.getPrimitive();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken("+");
			return false;
		}
		if (node instanceof PrimitiveRewriteNode) {
			PrimitiveRewriteNode n = (PrimitiveRewriteNode)node;
			{
				Primitive v=n.getPrimitive1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				PrimitiveRewrite v=n.getPrimitiveRewrite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ComplexListNode) {
			ComplexListNode n = (ComplexListNode)node;
			printToken("(");
			{
				Lookahead v=n.getLookahead();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Primitive v=n.getPrimitive();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Primitive v=n.getPrimitive1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken("*");
			return false;
		}
		if (node instanceof PatternNode) {
			PatternNode n = (PatternNode)node;
			{
				Pattern v=n.getPattern();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ClassName v=n.getClassName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Pattern) {
			Pattern n = (Pattern)node;
			for (Primitive v : n.getPrimitive()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ClassName) {
			ClassName n = (ClassName)node;
			printToken("::");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Primitive1) {
			Primitive1 n = (Primitive1)node;
			{
				IASTNode v=n.getOptionalNode();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Primitive2) {
			Primitive2 n = (Primitive2)node;
			{
				Terminal v=n.getTerminal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof OptionalNode) {
			OptionalNode n = (OptionalNode)node;
			printToken("[");
			{
				Lookahead v=n.getLookahead();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Terminal v=n.getTerminal();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof BaliTokenNode) {
			BaliTokenNode n = (BaliTokenNode)node;
			{
				ASTStringNode v=n.getBali_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IdentifierNode) {
			IdentifierNode n = (IdentifierNode)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode) {
			StringNode n = (StringNode)node;
			{
				ASTStringNode v=n.getString();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RegexTokenDefinition) {
			RegexTokenDefinition n = (RegexTokenDefinition)node;
			{
				StateSet v=n.getStateSet();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				REKind v=n.getREKind();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				CaseFlag v=n.getCaseFlag();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			printToken("{");
			{
				REList v=n.getREList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof StateSet) {
			StateSet n = (StateSet)node;
			printToken("<");
			{
				StatesSpecifier v=n.getStatesSpecifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof StarStatesNode) {
			StarStatesNode n = (StarStatesNode)node;
			printToken("*");
			return false;
		}
		if (node instanceof ListStatesNode) {
			ListStatesNode n = (ListStatesNode)node;
			{
				StatesList v=n.getStatesList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StatesList) {
			StatesList n = (StatesList)node;
			Iterator<StateName> listElements = n.getStateName().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof StateName) {
			StateName n = (StateName)node;
			{
				ASTStringNode v=n.getBali_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TokenKindNode) {
			TokenKindNode n = (TokenKindNode)node;
			{
				ASTStringNode v=n.get_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SpecialKindNode) {
			SpecialKindNode n = (SpecialKindNode)node;
			{
				ASTStringNode v=n.get_special_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SkipKindNode) {
			SkipKindNode n = (SkipKindNode)node;
			{
				ASTStringNode v=n.get_skip();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MoreKindNode) {
			MoreKindNode n = (MoreKindNode)node;
			{
				ASTStringNode v=n.get_more();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CaseFlag) {
			CaseFlag n = (CaseFlag)node;
			printToken("[");
			{
				ASTStringNode v=n.get_ignore_case();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof REList) {
			REList n = (REList)node;
			Iterator<RegexBlock> listElements = n.getRegexBlock().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken("|");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof RegexBlock) {
			RegexBlock n = (RegexBlock)node;
			{
				Regex v=n.getRegex();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				NextState v=n.getNextState();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof NextState) {
			NextState n = (NextState)node;
			printToken(":");
			{
				ASTStringNode v=n.getBali_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringRegexNode) {
			StringRegexNode n = (StringRegexNode)node;
			{
				ASTStringNode v=n.getString();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AngleRegexNode) {
			AngleRegexNode n = (AngleRegexNode)node;
			printToken("<");
			{
				AngleRegex v=n.getAngleRegex();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BaliRegexNode) {
			BaliRegexNode n = (BaliRegexNode)node;
			{
				ASTStringNode v=n.getBali_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof ComplexRegexNode) {
			ComplexRegexNode n = (ComplexRegexNode)node;
			{
				Label v=n.getLabel();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ComplexRegex v=n.getComplexRegex();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringComplexNode) {
			StringComplexNode n = (StringComplexNode)node;
			{
				ASTStringNode v=n.getString();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof AngleComplexNode) {
			AngleComplexNode n = (AngleComplexNode)node;
			{
				ASTStringNode v=n.getFindcloseangle();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof Label) {
			Label n = (Label)node;
			{
				ASTTextNode v=n.getText17();
				if (v!=null) {
					printToken("#");
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getBali_token();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			return false;
		}
		return true;
	}
}
