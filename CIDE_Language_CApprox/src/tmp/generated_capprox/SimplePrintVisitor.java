package tmp.generated_capprox;

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
		if (node instanceof TranslationUnit) {
			TranslationUnit n = (TranslationUnit)node;
			{
				Sequence_CodeUnit_TopLevel v=n.getSequence_CodeUnit_TopLevel();
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
		if (node instanceof Sequence_CodeUnit_TopLevel) {
			Sequence_CodeUnit_TopLevel n = (Sequence_CodeUnit_TopLevel)node;
			for (CodeUnit_TopLevel v : n.getCodeUnit_TopLevel()) {
				v.accept(this);
				hintNewLine();
			}
			return false;
		}
		if (node instanceof Include) {
			Include n = (Include)node;
			{
				PPIncludeStatement v=n.getPPIncludeStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Define) {
			Define n = (Define)node;
			{
				PPDefineStatement v=n.getPPDefineStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IfDefTL) {
			IfDefTL n = (IfDefTL)node;
			{
				PPIfDef_TopLevel v=n.getPPIfDef_TopLevel();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Preprocessor) {
			Preprocessor n = (Preprocessor)node;
			printToken("#");
			{
				PPOtherIgnore v=n.getPPOtherIgnore();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getFindlineend();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Func) {
			Func n = (Func)node;
			{
				Function v=n.getFunction();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeDef_) {
			TypeDef_ n = (TypeDef_)node;
			{
				TypeDef v=n.getTypeDef();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExternDec) {
			ExternDec n = (ExternDec)node;
			{
				ExternDecl v=n.getExternDecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StmtTL) {
			StmtTL n = (StmtTL)node;
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IfDefBL) {
			IfDefBL n = (IfDefBL)node;
			{
				PPIfDef_BlockLevel v=n.getPPIfDef_BlockLevel();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IncludeBL) {
			IncludeBL n = (IncludeBL)node;
			{
				PPIncludeStatement v=n.getPPIncludeStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DefineBL) {
			DefineBL n = (DefineBL)node;
			{
				PPDefineStatement v=n.getPPDefineStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PreprocessorBL) {
			PreprocessorBL n = (PreprocessorBL)node;
			printToken("#");
			{
				PPOtherIgnore v=n.getPPOtherIgnore();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getFindlineend();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof If) {
			If n = (If)node;
			{
				IfStatement v=n.getIfStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof For) {
			For n = (For)node;
			{
				ForStatement v=n.getForStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof While) {
			While n = (While)node;
			{
				WhileStatement v=n.getWhileStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Do) {
			Do n = (Do)node;
			{
				DoStatement v=n.getDoStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Switch) {
			Switch n = (Switch)node;
			{
				SwitchStatement v=n.getSwitchStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CodeUnit_InBlock10) {
			CodeUnit_InBlock10 n = (CodeUnit_InBlock10)node;
			{
				GotoLabel v=n.getGotoLabel();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Blck) {
			Blck n = (Blck)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Stmt) {
			Stmt n = (Stmt)node;
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement) {
			Statement n = (Statement)node;
			for (AnyStmtToken v : n.getAnyStmtToken()) {
				v.accept(this);
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof IfStatement) {
			IfStatement n = (IfStatement)node;
			printToken("if");
			printToken("(");
			{
				ASTStringNode v=n.getFindendcb();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				BlockOrSingleStatement v=n.getBlockOrSingleStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ElseBlock v=n.getElseBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ElseBlock) {
			ElseBlock n = (ElseBlock)node;
			printToken("else");
			return false;
		}
		if (node instanceof ForStatement) {
			ForStatement n = (ForStatement)node;
			printToken("for");
			printToken("(");
			{
				ASTStringNode v=n.getFindendcb();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintIncIndent();
			hintNewLine();
			{
				BlockOrSingleStatement v=n.getBlockOrSingleStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			return false;
		}
		if (node instanceof WhileStatement) {
			WhileStatement n = (WhileStatement)node;
			printToken("while");
			printToken("(");
			{
				ASTStringNode v=n.getFindendcb();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintIncIndent();
			hintNewLine();
			{
				BlockOrSingleStatement v=n.getBlockOrSingleStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			return false;
		}
		if (node instanceof DoStatement) {
			DoStatement n = (DoStatement)node;
			printToken("do");
			hintIncIndent();
			hintNewLine();
			{
				BlockOrSingleStatement v=n.getBlockOrSingleStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			hintNewLine();
			printToken("while");
			printToken("(");
			{
				ASTStringNode v=n.getFindendcb();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof SwitchStatement) {
			SwitchStatement n = (SwitchStatement)node;
			printToken("switch");
			printToken("(");
			{
				ASTStringNode v=n.getFindendcb();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (SwCase v : n.getSwCase()) {
				v.accept(this);
			}
			hintDecIndent();
			printToken("}");
			return false;
		}
		if (node instanceof SwCase1) {
			SwCase1 n = (SwCase1)node;
			printToken("default");
			printToken(":");
			{
				Sequence_CodeUnit_InBlock v=n.getSequence_CodeUnit_InBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof SwCase2) {
			SwCase2 n = (SwCase2)node;
			printToken("case");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					printToken("(");
					v.accept(this);
					printToken(")");
				}
			}
			{
				SwCaseLabel v=n.getSwCaseLabel();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (MoreSwCaseLabel v : n.getMoreSwCaseLabel()) {
				v.accept(this);
			}
			printToken(":");
			{
				Sequence_CodeUnit_InBlock v=n.getSequence_CodeUnit_InBlock1();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof SwCaseLabel1) {
			SwCaseLabel1 n = (SwCaseLabel1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SwCaseLabel2) {
			SwCaseLabel2 n = (SwCaseLabel2)node;
			{
				ASTStringNode v=n.getOther();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MoreSwCaseLabel) {
			MoreSwCaseLabel n = (MoreSwCaseLabel)node;
			printToken("|");
			{
				SwCaseLabel v=n.getSwCaseLabel();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExternDecl) {
			ExternDecl n = (ExternDecl)node;
			printToken("extern");
			{
				ASTStringNode v=n.getLiteral();
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
			return false;
		}
		if (node instanceof PPIncludeStatement) {
			PPIncludeStatement n = (PPIncludeStatement)node;
			printToken("#");
			printToken("include");
			{
				ASTStringNode v=n.getFindlineend();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof PPDefineStatement1) {
			PPDefineStatement1 n = (PPDefineStatement1)node;
			printToken("#");
			printToken("define");
			{
				ASTStringNode v=n.getFindlineend();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof PPDefineStatement2) {
			PPDefineStatement2 n = (PPDefineStatement2)node;
			printToken("#");
			printToken("undef");
			{
				ASTStringNode v=n.getFindlineend1();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof PPIfDef_TopLevel) {
			PPIfDef_TopLevel n = (PPIfDef_TopLevel)node;
			{
				IfDefLine v=n.getIfDefLine();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintIncIndent();
			{
				Sequence_CodeUnit_TopLevel v=n.getSequence_CodeUnit_TopLevel();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (IfElseIf_TopLevel v : n.getIfElseIf_TopLevel()) {
				v.accept(this);
			}
			{
				Sequence_CodeUnit_TopLevel v=n.getSequence_CodeUnit_TopLevel1();
				if (v!=null) {
					hintDecIndent();
					printToken("#");
					printToken("else");
					hintIncIndent();
					hintNewLine();
					v.accept(this);
				}
			}
			hintDecIndent();
			printToken("#");
			printToken("endif");
			hintNewLine();
			return false;
		}
		if (node instanceof PPIfDef_BlockLevel) {
			PPIfDef_BlockLevel n = (PPIfDef_BlockLevel)node;
			{
				IfDefLine v=n.getIfDefLine();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintIncIndent();
			{
				Sequence_CodeUnit_InBlock v=n.getSequence_CodeUnit_InBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (IfElseIf_BlockLevel v : n.getIfElseIf_BlockLevel()) {
				v.accept(this);
			}
			{
				Sequence_CodeUnit_InBlock v=n.getSequence_CodeUnit_InBlock1();
				if (v!=null) {
					hintDecIndent();
					printToken("#");
					printToken("else");
					hintIncIndent();
					hintNewLine();
					v.accept(this);
					hintNewLine();
				}
			}
			hintDecIndent();
			printToken("#");
			printToken("endif");
			hintNewLine();
			return false;
		}
		if (node instanceof PPOtherIgnore1) {
			PPOtherIgnore1 n = (PPOtherIgnore1)node;
			printToken("line");
			return false;
		}
		if (node instanceof PPOtherIgnore2) {
			PPOtherIgnore2 n = (PPOtherIgnore2)node;
			printToken("pragma");
			return false;
		}
		if (node instanceof PPOtherIgnore3) {
			PPOtherIgnore3 n = (PPOtherIgnore3)node;
			printToken("error");
			return false;
		}
		if (node instanceof IfDefLine1) {
			IfDefLine1 n = (IfDefLine1)node;
			printToken("#");
			printToken("ifdef");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof IfDefLine2) {
			IfDefLine2 n = (IfDefLine2)node;
			printToken("#");
			printToken("ifndef");
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof IfDefLine3) {
			IfDefLine3 n = (IfDefLine3)node;
			printToken("#");
			printToken("if");
			{
				ASTStringNode v=n.getFindlineend();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof IfElseIf1) {
			IfElseIf1 n = (IfElseIf1)node;
			printToken("#");
			printToken("elif");
			return false;
		}
		if (node instanceof IfElseIf2) {
			IfElseIf2 n = (IfElseIf2)node;
			printToken("#");
			printToken("elsif");
			return false;
		}
		if (node instanceof IfElseIf_BlockLevel) {
			IfElseIf_BlockLevel n = (IfElseIf_BlockLevel)node;
			hintDecIndent();
			{
				IfElseIf v=n.getIfElseIf();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getFindlineend();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintIncIndent();
			hintNewLine();
			{
				Sequence_CodeUnit_InBlock v=n.getSequence_CodeUnit_InBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IfElseIf_TopLevel) {
			IfElseIf_TopLevel n = (IfElseIf_TopLevel)node;
			{
				IfElseIf v=n.getIfElseIf();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getFindlineend();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			{
				Sequence_CodeUnit_TopLevel v=n.getSequence_CodeUnit_TopLevel();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Function) {
			Function n = (Function)node;
			{
				FunctionHeader v=n.getFunctionHeader();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				FunctionParameterList v=n.getFunctionParameterList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			hintIncIndent();
			hintNewLine();
			{
				BlockOrSemi v=n.getBlockOrSemi();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			hintNewLine();
			return false;
		}
		if (node instanceof FunctionHeader) {
			FunctionHeader n = (FunctionHeader)node;
			for (Modifier v : n.getModifier()) {
				v.accept(this);
			}
			{
				FunctionReturnType v=n.getFunctionReturnType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText25();
				if (v!=null) {
					printToken("*");
					v.accept(this);
				}
			}
			{
				FunctionExoticStuff v=n.getFunctionExoticStuff();
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
			return false;
		}
		if (node instanceof FunctionReturnType) {
			FunctionReturnType n = (FunctionReturnType)node;
			{
				ASTTextNode v=n.getText26();
				if (v!=null) {
					printToken("const");
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText27();
				if (v!=null) {
					printToken("struct");
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText28();
				if (v!=null) {
					printToken("unsigned");
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
		if (node instanceof Modifier1) {
			Modifier1 n = (Modifier1)node;
			printToken("static");
			return false;
		}
		if (node instanceof Modifier2) {
			Modifier2 n = (Modifier2)node;
			printToken("inline");
			return false;
		}
		if (node instanceof Modifier3) {
			Modifier3 n = (Modifier3)node;
			printToken("__inline__");
			return false;
		}
		if (node instanceof Modifier4) {
			Modifier4 n = (Modifier4)node;
			printToken("__inline");
			return false;
		}
		if (node instanceof Modifier5) {
			Modifier5 n = (Modifier5)node;
			printToken("extern");
			return false;
		}
		if (node instanceof Modifier6) {
			Modifier6 n = (Modifier6)node;
			printToken("__TIPOFUNC__");
			return false;
		}
		if (node instanceof FunctionExoticStuff) {
			FunctionExoticStuff n = (FunctionExoticStuff)node;
			printToken("__regbank");
			printToken("(");
			{
				ASTStringNode v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof FunctionParameterList) {
			FunctionParameterList n = (FunctionParameterList)node;
			Iterator<FunctionParameter> listElements = n.getFunctionParameter().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof FunctionParameter) {
			FunctionParameter n = (FunctionParameter)node;
			for (VarDeclToken v : n.getVarDeclToken()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Block) {
			Block n = (Block)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			{
				Sequence_CodeUnit_InBlock v=n.getSequence_CodeUnit_InBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			return false;
		}
		if (node instanceof GotoLabel) {
			GotoLabel n = (GotoLabel)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			return false;
		}
		if (node instanceof Sequence_CodeUnit_InBlock) {
			Sequence_CodeUnit_InBlock n = (Sequence_CodeUnit_InBlock)node;
			for (CodeUnit_InBlock v : n.getCodeUnit_InBlock()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof BlockOrSemi1) {
			BlockOrSemi1 n = (BlockOrSemi1)node;
			printToken(";");
			return false;
		}
		if (node instanceof BlockOrSemi2) {
			BlockOrSemi2 n = (BlockOrSemi2)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BlockOrSemi3) {
			BlockOrSemi3 n = (BlockOrSemi3)node;
			for (VarDecl v : n.getVarDecl()) {
				v.accept(this);
			}
			{
				Block v=n.getBlock1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BlockOrSingleStatement1) {
			BlockOrSingleStatement1 n = (BlockOrSingleStatement1)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BlockOrSingleStatement2) {
			BlockOrSingleStatement2 n = (BlockOrSingleStatement2)node;
			{
				CodeUnit_InBlock v=n.getCodeUnit_InBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeDef1) {
			TypeDef1 n = (TypeDef1)node;
			printToken("typedef");
			printToken("enum");
			for (AnyTypeDefToken v : n.getAnyTypeDefToken()) {
				v.accept(this);
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof TypeDef2) {
			TypeDef2 n = (TypeDef2)node;
			printToken("typedef");
			for (AnyStmtToken v : n.getAnyStmtToken()) {
				v.accept(this);
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof BlockAssignment) {
			BlockAssignment n = (BlockAssignment)node;
			printToken("=");
			{
				Cast v=n.getCast();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			{
				ASTStringNode v=n.getFindendccb();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EnumBlock) {
			EnumBlock n = (EnumBlock)node;
			printToken("enum");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			{
				ASTStringNode v=n.getFindendccb();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Cast) {
			Cast n = (Cast)node;
			printToken("(");
			{
				FunctionReturnType v=n.getFunctionReturnType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof AnyTypeDefToken1) {
			AnyTypeDefToken1 n = (AnyTypeDefToken1)node;
			printToken("{");
			return false;
		}
		if (node instanceof AnyTypeDefToken2) {
			AnyTypeDefToken2 n = (AnyTypeDefToken2)node;
			printToken("}");
			return false;
		}
		if (node instanceof AnyTypeDefToken3) {
			AnyTypeDefToken3 n = (AnyTypeDefToken3)node;
			{
				AnyStmtToken v=n.getAnyStmtToken();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken1) {
			AnyStmtToken1 n = (AnyStmtToken1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken2) {
			AnyStmtToken2 n = (AnyStmtToken2)node;
			{
				ASTStringNode v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken3) {
			AnyStmtToken3 n = (AnyStmtToken3)node;
			{
				ASTStringNode v=n.getOther();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken4) {
			AnyStmtToken4 n = (AnyStmtToken4)node;
			printToken(",");
			return false;
		}
		if (node instanceof AnyStmtToken5) {
			AnyStmtToken5 n = (AnyStmtToken5)node;
			printToken("|");
			return false;
		}
		if (node instanceof AnyStmtToken6) {
			AnyStmtToken6 n = (AnyStmtToken6)node;
			printToken("<");
			return false;
		}
		if (node instanceof AnyStmtToken7) {
			AnyStmtToken7 n = (AnyStmtToken7)node;
			printToken(">");
			return false;
		}
		if (node instanceof AnyStmtToken8) {
			AnyStmtToken8 n = (AnyStmtToken8)node;
			printToken("(");
			return false;
		}
		if (node instanceof AnyStmtToken9) {
			AnyStmtToken9 n = (AnyStmtToken9)node;
			printToken(")");
			return false;
		}
		if (node instanceof AnyStmtToken10) {
			AnyStmtToken10 n = (AnyStmtToken10)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken11) {
			AnyStmtToken11 n = (AnyStmtToken11)node;
			printToken("if");
			return false;
		}
		if (node instanceof AnyStmtToken12) {
			AnyStmtToken12 n = (AnyStmtToken12)node;
			printToken("else");
			return false;
		}
		if (node instanceof AnyStmtToken13) {
			AnyStmtToken13 n = (AnyStmtToken13)node;
			printToken("for");
			return false;
		}
		if (node instanceof AnyStmtToken14) {
			AnyStmtToken14 n = (AnyStmtToken14)node;
			printToken("while");
			return false;
		}
		if (node instanceof AnyStmtToken15) {
			AnyStmtToken15 n = (AnyStmtToken15)node;
			{
				EnumBlock v=n.getEnumBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken16) {
			AnyStmtToken16 n = (AnyStmtToken16)node;
			printToken("enum");
			return false;
		}
		if (node instanceof AnyStmtToken17) {
			AnyStmtToken17 n = (AnyStmtToken17)node;
			printToken("*");
			return false;
		}
		if (node instanceof AnyStmtToken18) {
			AnyStmtToken18 n = (AnyStmtToken18)node;
			{
				BlockAssignment v=n.getBlockAssignment();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken19) {
			AnyStmtToken19 n = (AnyStmtToken19)node;
			printToken("=");
			return false;
		}
		if (node instanceof AnyStmtToken20) {
			AnyStmtToken20 n = (AnyStmtToken20)node;
			printToken(":");
			return false;
		}
		if (node instanceof AnyStmtToken21) {
			AnyStmtToken21 n = (AnyStmtToken21)node;
			{
				Modifier v=n.getModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken22) {
			AnyStmtToken22 n = (AnyStmtToken22)node;
			printToken("ifdef");
			return false;
		}
		if (node instanceof AnyStmtToken23) {
			AnyStmtToken23 n = (AnyStmtToken23)node;
			printToken("ifndef");
			return false;
		}
		if (node instanceof AnyStmtToken24) {
			AnyStmtToken24 n = (AnyStmtToken24)node;
			printToken("define");
			return false;
		}
		if (node instanceof AnyStmtToken25) {
			AnyStmtToken25 n = (AnyStmtToken25)node;
			printToken("include");
			return false;
		}
		if (node instanceof AnyStmtToken26) {
			AnyStmtToken26 n = (AnyStmtToken26)node;
			printToken("elif");
			return false;
		}
		if (node instanceof AnyStmtToken27) {
			AnyStmtToken27 n = (AnyStmtToken27)node;
			printToken("elsif");
			return false;
		}
		if (node instanceof AnyStmtToken28) {
			AnyStmtToken28 n = (AnyStmtToken28)node;
			{
				PPOtherIgnore v=n.getPPOtherIgnore();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyStmtToken29) {
			AnyStmtToken29 n = (AnyStmtToken29)node;
			printToken("const");
			return false;
		}
		if (node instanceof AnyStmtToken30) {
			AnyStmtToken30 n = (AnyStmtToken30)node;
			printToken("struct");
			return false;
		}
		if (node instanceof AnyStmtToken31) {
			AnyStmtToken31 n = (AnyStmtToken31)node;
			printToken("unsigned");
			return false;
		}
		if (node instanceof VarDecl) {
			VarDecl n = (VarDecl)node;
			for (VarDeclTokenOrComma v : n.getVarDeclTokenOrComma()) {
				v.accept(this);
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof VarDeclTokenOrComma1) {
			VarDeclTokenOrComma1 n = (VarDeclTokenOrComma1)node;
			{
				VarDeclToken v=n.getVarDeclToken();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VarDeclTokenOrComma2) {
			VarDeclTokenOrComma2 n = (VarDeclTokenOrComma2)node;
			printToken(",");
			return false;
		}
		if (node instanceof VarDeclToken1) {
			VarDeclToken1 n = (VarDeclToken1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VarDeclToken2) {
			VarDeclToken2 n = (VarDeclToken2)node;
			{
				ASTStringNode v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VarDeclToken3) {
			VarDeclToken3 n = (VarDeclToken3)node;
			printToken("*");
			return false;
		}
		if (node instanceof VarDeclToken4) {
			VarDeclToken4 n = (VarDeclToken4)node;
			{
				ASTStringNode v=n.getOther();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VarDeclToken5) {
			VarDeclToken5 n = (VarDeclToken5)node;
			printToken("|");
			return false;
		}
		if (node instanceof VarDeclToken6) {
			VarDeclToken6 n = (VarDeclToken6)node;
			printToken("const");
			return false;
		}
		if (node instanceof VarDeclToken7) {
			VarDeclToken7 n = (VarDeclToken7)node;
			printToken("struct");
			return false;
		}
		if (node instanceof VarDeclToken8) {
			VarDeclToken8 n = (VarDeclToken8)node;
			printToken("unsigned");
			return false;
		}
		if (node instanceof VarDeclToken9) {
			VarDeclToken9 n = (VarDeclToken9)node;
			printToken("enum");
			return false;
		}
		if (node instanceof VarDeclToken10) {
			VarDeclToken10 n = (VarDeclToken10)node;
			{
				PPOtherIgnore v=n.getPPOtherIgnore();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VarDeclToken11) {
			VarDeclToken11 n = (VarDeclToken11)node;
			{
				Modifier v=n.getModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VarDeclToken12) {
			VarDeclToken12 n = (VarDeclToken12)node;
			printToken("(");
			{
				ASTStringNode v=n.getFindendcb();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal) {
			Literal n = (Literal)node;
			{
				ASTStringNode v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
