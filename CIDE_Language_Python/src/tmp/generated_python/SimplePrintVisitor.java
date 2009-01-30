package tmp.generated_python;

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
		if (node instanceof file_input) {
			file_input n = (file_input)node;
			for (newlineOrStmt v : n.getNewlineOrStmt()) {
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
		if (node instanceof newlineOrStmt1) {
			newlineOrStmt1 n = (newlineOrStmt1)node;
			{
				ASTStringNode v=n.getNewline();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof newlineOrStmt2) {
			newlineOrStmt2 n = (newlineOrStmt2)node;
			{
				stmt v=n.getStmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof funcdef) {
			funcdef n = (funcdef)node;
			printToken("def");
			{
				AnyName v=n.getAnyName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				parameters v=n.getParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof parameters) {
			parameters n = (parameters)node;
			printToken("(");
			{
				varargslist v=n.getVarargslist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof varargslist1) {
			varargslist1 n = (varargslist1)node;
			{
				defaultarg v=n.getDefaultarg();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (defaultarg v : n.getDefaultarg1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ExtraArgList v=n.getExtraArgList();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			{
				ExtraKeywordList v=n.getExtraKeywordList();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText578();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof varargslist2) {
			varargslist2 n = (varargslist2)node;
			{
				ExtraArgList v=n.getExtraArgList1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ExtraKeywordList v=n.getExtraKeywordList1();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof varargslist3) {
			varargslist3 n = (varargslist3)node;
			{
				ExtraKeywordList v=n.getExtraKeywordList2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExtraArgList) {
			ExtraArgList n = (ExtraArgList)node;
			printToken("*");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExtraKeywordList) {
			ExtraKeywordList n = (ExtraKeywordList)node;
			{
				power v=n.getPower();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof defaultarg) {
			defaultarg n = (defaultarg)node;
			{
				fpdef v=n.getFpdef();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				test v=n.getTest();
				if (v!=null) {
					printToken("=");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fpdef1) {
			fpdef1 n = (fpdef1)node;
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fpdef2) {
			fpdef2 n = (fpdef2)node;
			printToken("(");
			{
				fplist v=n.getFplist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof fplist) {
			fplist n = (fplist)node;
			{
				fpdef v=n.getFpdef();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (fpdef v : n.getFpdef1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText579();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof stmt1) {
			stmt1 n = (stmt1)node;
			{
				simple_stmt v=n.getSimple_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof stmt2) {
			stmt2 n = (stmt2)node;
			{
				compound_stmt v=n.getCompound_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof simple_stmt) {
			simple_stmt n = (simple_stmt)node;
			{
				small_stmt v=n.getSmall_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (small_stmt v : n.getSmall_stmt1()) {
				printToken(";");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText580();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getNewline();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt1) {
			small_stmt1 n = (small_stmt1)node;
			{
				expr_stmt v=n.getExpr_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt2) {
			small_stmt2 n = (small_stmt2)node;
			{
				print_stmt v=n.getPrint_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt3) {
			small_stmt3 n = (small_stmt3)node;
			{
				del_stmt v=n.getDel_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt4) {
			small_stmt4 n = (small_stmt4)node;
			{
				pass_stmt v=n.getPass_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt5) {
			small_stmt5 n = (small_stmt5)node;
			{
				flow_stmt v=n.getFlow_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt6) {
			small_stmt6 n = (small_stmt6)node;
			{
				import_stmt v=n.getImport_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt7) {
			small_stmt7 n = (small_stmt7)node;
			{
				global_stmt v=n.getGlobal_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt8) {
			small_stmt8 n = (small_stmt8)node;
			{
				exec_stmt v=n.getExec_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof small_stmt9) {
			small_stmt9 n = (small_stmt9)node;
			{
				assert_stmt v=n.getAssert_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmt) {
			expr_stmt n = (expr_stmt)node;
			{
				SmartTestList v=n.getSmartTestList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				expr_stmtEnd v=n.getExpr_stmtEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd1) {
			expr_stmtEnd1 n = (expr_stmtEnd1)node;
			{
				ASTStringNode v=n.getPluseq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd2) {
			expr_stmtEnd2 n = (expr_stmtEnd2)node;
			{
				ASTStringNode v=n.getMinuseq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd3) {
			expr_stmtEnd3 n = (expr_stmtEnd3)node;
			{
				ASTStringNode v=n.getMultiplyeq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd4) {
			expr_stmtEnd4 n = (expr_stmtEnd4)node;
			{
				ASTStringNode v=n.getDivideeq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd5) {
			expr_stmtEnd5 n = (expr_stmtEnd5)node;
			{
				ASTStringNode v=n.getFloordivideeq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList4();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd6) {
			expr_stmtEnd6 n = (expr_stmtEnd6)node;
			{
				ASTStringNode v=n.getModuloeq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList5();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd7) {
			expr_stmtEnd7 n = (expr_stmtEnd7)node;
			{
				ASTStringNode v=n.getAndeq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList6();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd8) {
			expr_stmtEnd8 n = (expr_stmtEnd8)node;
			{
				ASTStringNode v=n.getOreq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList7();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd9) {
			expr_stmtEnd9 n = (expr_stmtEnd9)node;
			{
				ASTStringNode v=n.getXoreq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList8();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd10) {
			expr_stmtEnd10 n = (expr_stmtEnd10)node;
			{
				ASTStringNode v=n.getLshifteq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList9();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd11) {
			expr_stmtEnd11 n = (expr_stmtEnd11)node;
			{
				ASTStringNode v=n.getRshifteq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList10();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd12) {
			expr_stmtEnd12 n = (expr_stmtEnd12)node;
			{
				ASTStringNode v=n.getPowereq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList11();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr_stmtEnd13) {
			expr_stmtEnd13 n = (expr_stmtEnd13)node;
			for (SmartTestList v : n.getSmartTestList12()) {
				printToken("=");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof print_stmt1) {
			print_stmt1 n = (print_stmt1)node;
			{
				ASTStringNode v=n.getPrint();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getRshift();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				print_stmtEndP v=n.getPrint_stmtEndP();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof print_stmt2) {
			print_stmt2 n = (print_stmt2)node;
			{
				ASTStringNode v=n.getPrint1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				print_stmtEndA v=n.getPrint_stmtEndA();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof print_stmt3) {
			print_stmt3 n = (print_stmt3)node;
			{
				ASTStringNode v=n.getPrint2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof print_stmtEndP) {
			print_stmtEndP n = (print_stmtEndP)node;
			for (test v : n.getTest()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText581();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof print_stmtEndA) {
			print_stmtEndA n = (print_stmtEndA)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (test v : n.getTest1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText582();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof del_stmt) {
			del_stmt n = (del_stmt)node;
			{
				ASTStringNode v=n.getDel();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				exprlist v=n.getExprlist();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof pass_stmt) {
			pass_stmt n = (pass_stmt)node;
			{
				ASTStringNode v=n.getPass();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof flow_stmt1) {
			flow_stmt1 n = (flow_stmt1)node;
			{
				ASTStringNode v=n.getBreak_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof flow_stmt2) {
			flow_stmt2 n = (flow_stmt2)node;
			{
				ASTStringNode v=n.getContinue_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof flow_stmt3) {
			flow_stmt3 n = (flow_stmt3)node;
			{
				return_stmt v=n.getReturn_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof flow_stmt4) {
			flow_stmt4 n = (flow_stmt4)node;
			{
				yield_stmt v=n.getYield_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof flow_stmt5) {
			flow_stmt5 n = (flow_stmt5)node;
			{
				raise_stmt v=n.getRaise_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof return_stmt) {
			return_stmt n = (return_stmt)node;
			{
				ASTStringNode v=n.getReturn_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof yield_stmt) {
			yield_stmt n = (yield_stmt)node;
			{
				ASTStringNode v=n.getYield();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SmartTestList v=n.getSmartTestList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof raise_stmt) {
			raise_stmt n = (raise_stmt)node;
			{
				ASTStringNode v=n.getRaise();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				raise_stmt_end v=n.getRaise_stmt_end();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof raise_stmt_end) {
			raise_stmt_end n = (raise_stmt_end)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				testcommatest v=n.getTestcommatest();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof testcommatest) {
			testcommatest n = (testcommatest)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				test v=n.getTest1();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof import_stmt1) {
			import_stmt1 n = (import_stmt1)node;
			{
				ASTStringNode v=n.getImport_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Import v=n.getImport_KW();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof import_stmt2) {
			import_stmt2 n = (import_stmt2)node;
			{
				ASTStringNode v=n.getFrom();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ImportFrom v=n.getImportFrom();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Import) {
			Import n = (Import)node;
			{
				dotted_as_name v=n.getDotted_as_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (dotted_as_name v : n.getDotted_as_name1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ImportFrom) {
			ImportFrom n = (ImportFrom)node;
			{
				dotted_name v=n.getDotted_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getImport_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ImportFromEnd v=n.getImportFromEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ImportFromEnd1) {
			ImportFromEnd1 n = (ImportFromEnd1)node;
			printToken("*");
			return false;
		}
		if (node instanceof ImportFromEnd2) {
			ImportFromEnd2 n = (ImportFromEnd2)node;
			{
				import_as_name v=n.getImport_as_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (import_as_name v : n.getImport_as_name1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof dotted_as_name) {
			dotted_as_name n = (dotted_as_name)node;
			{
				dotted_name v=n.getDotted_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Name v=n.getName();
				if (v!=null) {
					printToken("as");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof dotted_name) {
			dotted_name n = (dotted_name)node;
			{
				AnyName v=n.getAnyName();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AnyName v : n.getAnyName1()) {
				printToken(".");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof import_as_name) {
			import_as_name n = (import_as_name)node;
			{
				AnyName v=n.getAnyName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Name v=n.getName();
				if (v!=null) {
					printToken("as");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof global_stmt) {
			global_stmt n = (global_stmt)node;
			{
				ASTStringNode v=n.getGlobal();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Name v : n.getName1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof exec_stmt) {
			exec_stmt n = (exec_stmt)node;
			printToken("exec");
			{
				expr v=n.getExpr();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				exec_stmt_end v=n.getExec_stmt_end();
				if (v!=null) {
					printToken("in");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof exec_stmt_end) {
			exec_stmt_end n = (exec_stmt_end)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				test v=n.getTest1();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof assert_stmt) {
			assert_stmt n = (assert_stmt)node;
			printToken("assert");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				test v=n.getTest1();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof compound_stmt1) {
			compound_stmt1 n = (compound_stmt1)node;
			{
				if_stmt v=n.getIf_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof compound_stmt2) {
			compound_stmt2 n = (compound_stmt2)node;
			{
				while_stmt v=n.getWhile_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof compound_stmt3) {
			compound_stmt3 n = (compound_stmt3)node;
			{
				for_stmt v=n.getFor_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof compound_stmt4) {
			compound_stmt4 n = (compound_stmt4)node;
			{
				try_stmt v=n.getTry_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof compound_stmt5) {
			compound_stmt5 n = (compound_stmt5)node;
			{
				funcdef v=n.getFuncdef();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof compound_stmt6) {
			compound_stmt6 n = (compound_stmt6)node;
			{
				classdef v=n.getClassdef();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof if_stmt) {
			if_stmt n = (if_stmt)node;
			printToken("if");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (elif_stmt v : n.getElif_stmt()) {
				v.accept(this);
			}
			{
				suite v=n.getSuite1();
				if (v!=null) {
					printToken("else");
					printToken(":");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof elif_stmt) {
			elif_stmt n = (elif_stmt)node;
			printToken("elif");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof while_stmt) {
			while_stmt n = (while_stmt)node;
			printToken("while");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				suite v=n.getSuite1();
				if (v!=null) {
					printToken("else");
					printToken(":");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof for_stmt) {
			for_stmt n = (for_stmt)node;
			printToken("for");
			{
				exprlist v=n.getExprlist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("in");
			{
				SmartTestList v=n.getSmartTestList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				suite v=n.getSuite1();
				if (v!=null) {
					printToken("else");
					printToken(":");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof try_stmt) {
			try_stmt n = (try_stmt)node;
			printToken("try");
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				tryEnd v=n.getTryEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof tryEnd1) {
			tryEnd1 n = (tryEnd1)node;
			for (except_clause v : n.getExcept_clause()) {
				v.accept(this);
			}
			{
				suite v=n.getSuite();
				if (v!=null) {
					printToken("else");
					printToken(":");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof tryEnd2) {
			tryEnd2 n = (tryEnd2)node;
			printToken("finally");
			printToken(":");
			{
				suite v=n.getSuite1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof except_clause) {
			except_clause n = (except_clause)node;
			printToken("except");
			{
				testcommatest v=n.getTestcommatest();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof suite1) {
			suite1 n = (suite1)node;
			{
				simple_stmt v=n.getSimple_stmt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof suite2) {
			suite2 n = (suite2)node;
			hintIncIndent();
			{
				ASTStringNode v=n.getNewline();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getIndent();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (stmt v : n.getStmt()) {
				v.accept(this);
			}
			{
				ASTStringNode v=n.getDedent();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			return false;
		}
		if (node instanceof test1) {
			test1 n = (test1)node;
			{
				lambdef v=n.getLambdef();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof test2) {
			test2 n = (test2)node;
			{
				and_test v=n.getAnd_test();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (and_test v : n.getAnd_test1()) {
				printToken("or");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof and_test) {
			and_test n = (and_test)node;
			{
				not_test v=n.getNot_test();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (not_test v : n.getNot_test1()) {
				printToken("and");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof not_test1) {
			not_test1 n = (not_test1)node;
			printToken("not");
			{
				not_test v=n.getNot_test();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof not_test2) {
			not_test2 n = (not_test2)node;
			{
				comparison v=n.getComparison();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comparison) {
			comparison n = (comparison)node;
			{
				expr v=n.getExpr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (compEnd v : n.getCompEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof compEnd) {
			compEnd n = (compEnd)node;
			{
				comp_op v=n.getComp_op();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				expr v=n.getExpr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op1) {
			comp_op1 n = (comp_op1)node;
			{
				ASTStringNode v=n.getLess();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op2) {
			comp_op2 n = (comp_op2)node;
			{
				ASTStringNode v=n.getGreater();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op3) {
			comp_op3 n = (comp_op3)node;
			{
				ASTStringNode v=n.getEqequal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op4) {
			comp_op4 n = (comp_op4)node;
			{
				ASTStringNode v=n.getEqgreater();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op5) {
			comp_op5 n = (comp_op5)node;
			{
				ASTStringNode v=n.getEqless();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op6) {
			comp_op6 n = (comp_op6)node;
			{
				ASTStringNode v=n.getLessgreater();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op7) {
			comp_op7 n = (comp_op7)node;
			{
				ASTStringNode v=n.getNotequal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op8) {
			comp_op8 n = (comp_op8)node;
			{
				ASTStringNode v=n.getIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op9) {
			comp_op9 n = (comp_op9)node;
			printToken("not");
			{
				ASTStringNode v=n.getIn1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof comp_op10) {
			comp_op10 n = (comp_op10)node;
			{
				ASTStringNode v=n.getIs();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("not");
			return false;
		}
		if (node instanceof comp_op11) {
			comp_op11 n = (comp_op11)node;
			{
				ASTStringNode v=n.getIs1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expr) {
			expr n = (expr)node;
			{
				xor_expr v=n.getXor_expr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (xor_expr v : n.getXor_expr1()) {
				printToken("|");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof xor_expr) {
			xor_expr n = (xor_expr)node;
			{
				and_expr v=n.getAnd_expr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (and_expr v : n.getAnd_expr1()) {
				printToken("^");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof and_expr) {
			and_expr n = (and_expr)node;
			{
				shift_expr v=n.getShift_expr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (shift_expr v : n.getShift_expr1()) {
				printToken("&");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof shift_expr) {
			shift_expr n = (shift_expr)node;
			{
				arith_expr v=n.getArith_expr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (shift_exprEnd v : n.getShift_exprEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof shift_exprEnd1) {
			shift_exprEnd1 n = (shift_exprEnd1)node;
			printToken("<<");
			{
				arith_expr v=n.getArith_expr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof shift_exprEnd2) {
			shift_exprEnd2 n = (shift_exprEnd2)node;
			printToken(">>");
			{
				arith_expr v=n.getArith_expr1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof arith_expr) {
			arith_expr n = (arith_expr)node;
			{
				term v=n.getTerm();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (arith_exprEnd v : n.getArith_exprEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof arith_exprEnd1) {
			arith_exprEnd1 n = (arith_exprEnd1)node;
			{
				ASTStringNode v=n.getPlus();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				term v=n.getTerm();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof arith_exprEnd2) {
			arith_exprEnd2 n = (arith_exprEnd2)node;
			{
				ASTStringNode v=n.getMinus();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				term v=n.getTerm1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof term) {
			term n = (term)node;
			{
				factor v=n.getFactor();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (termEnd v : n.getTermEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof termEnd1) {
			termEnd1 n = (termEnd1)node;
			printToken("*");
			{
				factor v=n.getFactor();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof termEnd2) {
			termEnd2 n = (termEnd2)node;
			{
				ASTStringNode v=n.getDivide();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				factor v=n.getFactor1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof termEnd3) {
			termEnd3 n = (termEnd3)node;
			{
				ASTStringNode v=n.getFloordivide();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				factor v=n.getFactor2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof termEnd4) {
			termEnd4 n = (termEnd4)node;
			{
				ASTStringNode v=n.getModulo();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				factor v=n.getFactor3();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof factor1) {
			factor1 n = (factor1)node;
			{
				ASTStringNode v=n.getPlus();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				factor v=n.getFactor();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof factor2) {
			factor2 n = (factor2)node;
			{
				ASTStringNode v=n.getMinus();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				factor v=n.getFactor1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof factor3) {
			factor3 n = (factor3)node;
			{
				ASTStringNode v=n.getNot();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				factor v=n.getFactor2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof factor4) {
			factor4 n = (factor4)node;
			{
				powerfactor v=n.getPowerfactor();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof powerfactor) {
			powerfactor n = (powerfactor)node;
			{
				atomtrailer v=n.getAtomtrailer();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (factor v : n.getFactor()) {
				printToken("**");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof atomtrailer) {
			atomtrailer n = (atomtrailer)node;
			{
				atom v=n.getAtom();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (atomtrailerEnd v : n.getAtomtrailerEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof atomtrailerEnd1) {
			atomtrailerEnd1 n = (atomtrailerEnd1)node;
			printToken("(");
			printToken(")");
			return false;
		}
		if (node instanceof atomtrailerEnd2) {
			atomtrailerEnd2 n = (atomtrailerEnd2)node;
			printToken("(");
			{
				arglist v=n.getArglist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof atomtrailerEnd3) {
			atomtrailerEnd3 n = (atomtrailerEnd3)node;
			printToken("[");
			{
				subscriptlist v=n.getSubscriptlist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof atomtrailerEnd4) {
			atomtrailerEnd4 n = (atomtrailerEnd4)node;
			{
				ASTStringNode v=n.getDot();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AnyName v=n.getAnyName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof atom1) {
			atom1 n = (atom1)node;
			printToken("(");
			printToken(")");
			return false;
		}
		if (node instanceof atom2) {
			atom2 n = (atom2)node;
			printToken("(");
			{
				SmartTestList v=n.getSmartTestList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof atom3) {
			atom3 n = (atom3)node;
			{
				ASTStringNode v=n.getLbracket();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				listmaker v=n.getListmaker();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof atom4) {
			atom4 n = (atom4)node;
			printToken("{");
			{
				dictmaker v=n.getDictmaker();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof atom5) {
			atom5 n = (atom5)node;
			printToken("`");
			{
				SmartTestList v=n.getSmartTestList1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("`");
			return false;
		}
		if (node instanceof atom6) {
			atom6 n = (atom6)node;
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof atom7) {
			atom7 n = (atom7)node;
			{
				Number v=n.getNumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof atom8) {
			atom8 n = (atom8)node;
			{
				StringNode v=n.getStringNode();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (StringNode v : n.getStringNode1()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof lambdef) {
			lambdef n = (lambdef)node;
			{
				ASTStringNode v=n.getLambda();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				varargslist v=n.getVarargslist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof subscriptlist) {
			subscriptlist n = (subscriptlist)node;
			{
				subscript v=n.getSubscript();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (subscript v : n.getSubscript1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText586();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof subscript1) {
			subscript1 n = (subscript1)node;
			{
				ASTStringNode v=n.getDot();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getDot1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getDot2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof subscript2) {
			subscript2 n = (subscript2)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				slice v=n.getSlice();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof subscript3) {
			subscript3 n = (subscript3)node;
			{
				slice v=n.getSlice1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof slice) {
			slice n = (slice)node;
			{
				ct v=n.getCt();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ct v=n.getCt1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ct) {
			ct n = (ct)node;
			printToken(":");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof exprlist) {
			exprlist n = (exprlist)node;
			{
				expr v=n.getExpr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (expr v : n.getExpr1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText587();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SmartTestList) {
			SmartTestList n = (SmartTestList)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (test v : n.getTest1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText588();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof testlist) {
			testlist n = (testlist)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (test v : n.getTest1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText589();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof dictmaker) {
			dictmaker n = (dictmaker)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				test v=n.getTest1();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (dictInt v : n.getDictInt()) {
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText590();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof dictInt) {
			dictInt n = (dictInt)node;
			printToken(",");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				test v=n.getTest1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof listmaker) {
			listmaker n = (listmaker)node;
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				listmakerEnd v=n.getListmakerEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof listmakerEnd1) {
			listmakerEnd1 n = (listmakerEnd1)node;
			for (list_for v : n.getList_for()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof listmakerEnd2) {
			listmakerEnd2 n = (listmakerEnd2)node;
			for (test v : n.getTest()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText591();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof list_for) {
			list_for n = (list_for)node;
			printToken("for");
			{
				exprlist v=n.getExprlist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("in");
			{
				SmartTestList v=n.getSmartTestList();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (list_if v : n.getList_if()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof list_if) {
			list_if n = (list_if)node;
			printToken("if");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof classdef) {
			classdef n = (classdef)node;
			printToken("class");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				testlist v=n.getTestlist();
				if (v!=null) {
					printToken("(");
					v.accept(this);
					printToken(")");
				}
			}
			printToken(":");
			{
				suite v=n.getSuite();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof arglist1) {
			arglist1 n = (arglist1)node;
			{
				normalargs v=n.getNormalargs();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				arglist1End v=n.getArglist1End();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof arglist2) {
			arglist2 n = (arglist2)node;
			{
				arglist1EndEnd v=n.getArglist1EndEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof arglist1End) {
			arglist1End n = (arglist1End)node;
			printToken(",");
			{
				arglist1EndEnd v=n.getArglist1EndEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof arglist1EndEnd1) {
			arglist1EndEnd1 n = (arglist1EndEnd1)node;
			{
				ExtraArgValueList v=n.getExtraArgValueList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ExtraKeywordValueList v=n.getExtraKeywordValueList();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof arglist1EndEnd2) {
			arglist1EndEnd2 n = (arglist1EndEnd2)node;
			{
				ExtraKeywordValueList v=n.getExtraKeywordValueList1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof normalargs) {
			normalargs n = (normalargs)node;
			{
				argument v=n.getArgument();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (argument v : n.getArgument1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ExtraArgValueList) {
			ExtraArgValueList n = (ExtraArgValueList)node;
			printToken("*");
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExtraKeywordValueList) {
			ExtraKeywordValueList n = (ExtraKeywordValueList)node;
			{
				power v=n.getPower();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof power1) {
			power1 n = (power1)node;
			printToken("**");
			return false;
		}
		if (node instanceof power2) {
			power2 n = (power2)node;
			printToken("*");
			printToken("*");
			return false;
		}
		if (node instanceof argument) {
			argument n = (argument)node;
			{
				AnyName v=n.getAnyName();
				if (v!=null) {
					v.accept(this);
					printToken("=");
				}
			}
			{
				test v=n.getTest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Number1) {
			Number1 n = (Number1)node;
			{
				ASTStringNode v=n.getHexnumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Number2) {
			Number2 n = (Number2)node;
			{
				ASTStringNode v=n.getOctnumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Number3) {
			Number3 n = (Number3)node;
			{
				ASTStringNode v=n.getDecnumber();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Number4) {
			Number4 n = (Number4)node;
			{
				ASTStringNode v=n.getFloat_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Number5) {
			Number5 n = (Number5)node;
			{
				ASTStringNode v=n.getComplex();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Complex) {
			Complex n = (Complex)node;
			{
				ASTStringNode v=n.getFloat_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Name1) {
			Name1 n = (Name1)node;
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Name2) {
			Name2 n = (Name2)node;
			{
				ASTStringNode v=n.getAs();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode1) {
			StringNode1 n = (StringNode1)node;
			{
				ASTStringNode v=n.getSingle_string();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode2) {
			StringNode2 n = (StringNode2)node;
			{
				ASTStringNode v=n.getSingle_string2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode3) {
			StringNode3 n = (StringNode3)node;
			{
				ASTStringNode v=n.getTriple_string();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode4) {
			StringNode4 n = (StringNode4)node;
			{
				ASTStringNode v=n.getTriple_string2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode5) {
			StringNode5 n = (StringNode5)node;
			{
				ASTStringNode v=n.getSingle_ustring();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode6) {
			StringNode6 n = (StringNode6)node;
			{
				ASTStringNode v=n.getSingle_ustring2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode7) {
			StringNode7 n = (StringNode7)node;
			{
				ASTStringNode v=n.getTriple_ustring();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StringNode8) {
			StringNode8 n = (StringNode8)node;
			{
				ASTStringNode v=n.getTriple_ustring2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName1) {
			AnyName1 n = (AnyName1)node;
			{
				ASTStringNode v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName2) {
			AnyName2 n = (AnyName2)node;
			printToken("or");
			return false;
		}
		if (node instanceof AnyName3) {
			AnyName3 n = (AnyName3)node;
			printToken("and");
			return false;
		}
		if (node instanceof AnyName4) {
			AnyName4 n = (AnyName4)node;
			printToken("not");
			return false;
		}
		if (node instanceof AnyName5) {
			AnyName5 n = (AnyName5)node;
			{
				ASTStringNode v=n.getIs();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName6) {
			AnyName6 n = (AnyName6)node;
			{
				ASTStringNode v=n.getIn();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName7) {
			AnyName7 n = (AnyName7)node;
			{
				ASTStringNode v=n.getLambda();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName8) {
			AnyName8 n = (AnyName8)node;
			{
				ASTStringNode v=n.getIf_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName9) {
			AnyName9 n = (AnyName9)node;
			{
				ASTStringNode v=n.getElse_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName10) {
			AnyName10 n = (AnyName10)node;
			{
				ASTStringNode v=n.getElif();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName11) {
			AnyName11 n = (AnyName11)node;
			{
				ASTStringNode v=n.getWhile_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName12) {
			AnyName12 n = (AnyName12)node;
			{
				ASTStringNode v=n.getFor_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName13) {
			AnyName13 n = (AnyName13)node;
			{
				ASTStringNode v=n.getTry_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName14) {
			AnyName14 n = (AnyName14)node;
			{
				ASTStringNode v=n.getExcept();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName15) {
			AnyName15 n = (AnyName15)node;
			{
				ASTStringNode v=n.getDef();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName16) {
			AnyName16 n = (AnyName16)node;
			{
				ASTStringNode v=n.getClass_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName17) {
			AnyName17 n = (AnyName17)node;
			{
				ASTStringNode v=n.getFinally_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName18) {
			AnyName18 n = (AnyName18)node;
			{
				ASTStringNode v=n.getPrint();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName19) {
			AnyName19 n = (AnyName19)node;
			{
				ASTStringNode v=n.getPass();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName20) {
			AnyName20 n = (AnyName20)node;
			{
				ASTStringNode v=n.getBreak_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName21) {
			AnyName21 n = (AnyName21)node;
			{
				ASTStringNode v=n.getContinue_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName22) {
			AnyName22 n = (AnyName22)node;
			{
				ASTStringNode v=n.getReturn_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName23) {
			AnyName23 n = (AnyName23)node;
			{
				ASTStringNode v=n.getYield();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName24) {
			AnyName24 n = (AnyName24)node;
			{
				ASTStringNode v=n.getImport_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName25) {
			AnyName25 n = (AnyName25)node;
			{
				ASTStringNode v=n.getFrom();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName26) {
			AnyName26 n = (AnyName26)node;
			{
				ASTStringNode v=n.getDel();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName27) {
			AnyName27 n = (AnyName27)node;
			{
				ASTStringNode v=n.getRaise();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName28) {
			AnyName28 n = (AnyName28)node;
			{
				ASTStringNode v=n.getGlobal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName29) {
			AnyName29 n = (AnyName29)node;
			{
				ASTStringNode v=n.getExec();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName30) {
			AnyName30 n = (AnyName30)node;
			{
				ASTStringNode v=n.getAssert_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnyName31) {
			AnyName31 n = (AnyName31)node;
			{
				ASTStringNode v=n.getAs();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
