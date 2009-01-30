package tmp.generated_phaskell;

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
		if (node instanceof module) {
			module n = (module)node;
			printToken("module");
			{
				modid v=n.getModid();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				exports v=n.getExports();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			printToken("where");
			{
				body v=n.getBody();
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
		if (node instanceof qconid) {
			qconid n = (qconid)node;
			{
				ASTStringNode v=n.getConstructor_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof exports) {
			exports n = (exports)node;
			printToken("(");
			{
				exportsList v=n.getExportsList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText364();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof exportsList) {
			exportsList n = (exportsList)node;
			Iterator<export> listElements = n.getExport().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof export1) {
			export1 n = (export1)node;
			{
				qvar v=n.getQvar();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof export2) {
			export2 n = (export2)node;
			{
				qtyconorcls v=n.getQtyconorcls();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				details v=n.getDetails();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof export3) {
			export3 n = (export3)node;
			printToken("module");
			{
				modid v=n.getModid();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof details1) {
			details1 n = (details1)node;
			printToken("(");
			{
				ASTStringNode v=n.getVarsym();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof details2) {
			details2 n = (details2)node;
			printToken("(");
			{
				cnamelist v=n.getCnamelist();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof cnamelist) {
			cnamelist n = (cnamelist)node;
			Iterator<cname> listElements = n.getCname().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof qtyconorcls) {
			qtyconorcls n = (qtyconorcls)node;
			{
				qconid v=n.getQconid();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof cname1) {
			cname1 n = (cname1)node;
			{
				ASTStringNode v=n.getVariable_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof cname2) {
			cname2 n = (cname2)node;
			{
				ASTStringNode v=n.getConstructor_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof qvar1) {
			qvar1 n = (qvar1)node;
			{
				qvarid v=n.getQvarid();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof qvar2) {
			qvar2 n = (qvar2)node;
			printToken("(");
			{
				qvarsym v=n.getQvarsym();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof qvarid) {
			qvarid n = (qvarid)node;
			{
				ASTStringNode v=n.getVariable_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof qvarsym) {
			qvarsym n = (qvarsym)node;
			{
				ASTStringNode v=n.getVarsym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof modid) {
			modid n = (modid)node;
			{
				qconid v=n.getQconid();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof conid) {
			conid n = (conid)node;
			{
				ASTStringNode v=n.getConstructor_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof body1) {
			body1 n = (body1)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			{
				impdecls v=n.getImpdecls();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				topdecls v=n.getTopdecls();
				if (v!=null) {
					printToken(";");
					hintNewLine();
					hintNewLine();
					v.accept(this);
				}
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			return false;
		}
		if (node instanceof body2) {
			body2 n = (body2)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			{
				topdecls v=n.getTopdecls1();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			return false;
		}
		if (node instanceof impdecls) {
			impdecls n = (impdecls)node;
			Iterator<impdecl> listElements = n.getImpdecl().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(";");
				hintNewLine();
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof impdecl1) {
			impdecl1 n = (impdecl1)node;
			printToken("import");
			{
				ASTTextNode v=n.getText366();
				if (v!=null) {
					printToken("qualified");
					v.accept(this);
				}
			}
			{
				modid v=n.getModid();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				modid v=n.getModid1();
				if (v!=null) {
					printToken("as");
					v.accept(this);
				}
			}
			{
				impspec v=n.getImpspec();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof impdecl2) {
			impdecl2 n = (impdecl2)node;
			return false;
		}
		if (node instanceof impspec) {
			impspec n = (impspec)node;
			{
				ASTTextNode v=n.getText368();
				if (v!=null) {
					printToken("hiding");
					v.accept(this);
				}
			}
			printToken("(");
			{
				imports v=n.getImports();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof imports) {
			imports n = (imports)node;
			{
				imp v=n.getImp();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (imp v : n.getImp1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof imp1) {
			imp1 n = (imp1)node;
			{
				var v=n.getVar();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof imp2) {
			imp2 n = (imp2)node;
			{
				tyconorcls v=n.getTyconorcls();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				list v=n.getList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof tyconorcls) {
			tyconorcls n = (tyconorcls)node;
			{
				conid v=n.getConid();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof topdecls) {
			topdecls n = (topdecls)node;
			Iterator<topdecl> listElements = n.getTopdecl().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(";");
				hintNewLine();
				hintNewLine();
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof typedecl) {
			typedecl n = (typedecl)node;
			printToken("type");
			{
				simpletype v=n.getSimpletype();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				declrhs v=n.getDeclrhs();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof datadecl) {
			datadecl n = (datadecl)node;
			printToken("data");
			{
				optContext v=n.getOptContext();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				simpletype v=n.getSimpletype1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				constrs v=n.getConstrs();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				deriving v=n.getDeriving();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof newtypedecl) {
			newtypedecl n = (newtypedecl)node;
			printToken("newtype");
			{
				optContext v=n.getOptContext1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				simpletype v=n.getSimpletype2();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				declrhs v=n.getDeclrhs1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof classdecl) {
			classdecl n = (classdecl)node;
			printToken("class");
			{
				optContext v=n.getOptContext2();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				conid v=n.getConid();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				tyvar v=n.getTyvar();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				cdecls v=n.getCdecls();
				if (v!=null) {
					printToken("where");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof instancedecl) {
			instancedecl n = (instancedecl)node;
			printToken("instance");
			{
				optContext v=n.getOptContext3();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				qconid v=n.getQconid();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				inst v=n.getInst();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				block v=n.getBlock();
				if (v!=null) {
					printToken("where");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof defaultdecl) {
			defaultdecl n = (defaultdecl)node;
			printToken("default");
			{
				list v=n.getList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof declaration) {
			declaration n = (declaration)node;
			{
				decl v=n.getDecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof typeSignature) {
			typeSignature n = (typeSignature)node;
			{
				signdecl v=n.getSigndecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixityDeclaration) {
			fixityDeclaration n = (fixityDeclaration)node;
			{
				fixdecl v=n.getFixdecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof valueDeclaration) {
			valueDeclaration n = (valueDeclaration)node;
			{
				valdef v=n.getValdef();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof nonStandardDeclaration) {
			nonStandardDeclaration n = (nonStandardDeclaration)node;
			{
				nonstddecl v=n.getNonstddecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof decl5) {
			decl5 n = (decl5)node;
			return false;
		}
		if (node instanceof declrhs) {
			declrhs n = (declrhs)node;
			printToken("=");
			{
				ASTStringNode v=n.getFindnonstddeclrest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof optContext) {
			optContext n = (optContext)node;
			{
				context v=n.getContext();
				if (v!=null) {
					v.accept(this);
					printToken("=>");
				}
			}
			return false;
		}
		if (node instanceof deriving) {
			deriving n = (deriving)node;
			printToken("deriving");
			{
				ASTStringNode v=n.getFindnonstddeclrest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof constrs) {
			constrs n = (constrs)node;
			Iterator<constr> listElements = n.getConstr().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken("|");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof constr) {
			constr n = (constr)node;
			{
				conP v=n.getConP();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getFindconrest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof inst1) {
			inst1 n = (inst1)node;
			{
				gtycon v=n.getGtycon();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof inst2) {
			inst2 n = (inst2)node;
			printToken("(");
			{
				gtycon v=n.getGtycon1();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (tyvar v : n.getTyvar()) {
				v.accept(this);
			}
			printToken(")");
			return false;
		}
		if (node instanceof inst3) {
			inst3 n = (inst3)node;
			printToken("[");
			{
				conid v=n.getConid();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof gtycon) {
			gtycon n = (gtycon)node;
			{
				qtyconorcls v=n.getQtyconorcls();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof cdecls) {
			cdecls n = (cdecls)node;
			printToken("{");
			{
				cdeclsI v=n.getCdeclsI();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof cdeclsI) {
			cdeclsI n = (cdeclsI)node;
			Iterator<cdecl> listElements = n.getCdecl().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(";");
				hintNewLine();
				hintNewLine();
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof cdecl1) {
			cdecl1 n = (cdecl1)node;
			{
				signdecl v=n.getSigndecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof cdecl2) {
			cdecl2 n = (cdecl2)node;
			{
				nonstddecl v=n.getNonstddecl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof context) {
			context n = (context)node;
			{
				ASTStringNode v=n.getFinduntilsemiorcontextarrow();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof simpletype) {
			simpletype n = (simpletype)node;
			{
				ASTStringNode v=n.getConstructor_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getFinduntilequals();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof nonstddecl) {
			nonstddecl n = (nonstddecl)node;
			{
				ASTStringNode v=n.getFindnonstddeclrest();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof valdef) {
			valdef n = (valdef)node;
			{
				funlhs v=n.getFunlhs();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				declrhs v=n.getDeclrhs();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixdecl) {
			fixdecl n = (fixdecl)node;
			{
				fixity v=n.getFixity();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getInteger();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ops v=n.getOps();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixity1) {
			fixity1 n = (fixity1)node;
			printToken("infixl");
			return false;
		}
		if (node instanceof fixity2) {
			fixity2 n = (fixity2)node;
			printToken("infixr");
			return false;
		}
		if (node instanceof fixity3) {
			fixity3 n = (fixity3)node;
			printToken("infix");
			return false;
		}
		if (node instanceof ops) {
			ops n = (ops)node;
			Iterator<op> listElements = n.getOp().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof signdecl) {
			signdecl n = (signdecl)node;
			{
				vars v=n.getVars();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("::");
			{
				ASTStringNode v=n.getFinduntilsemiorccb();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof vars) {
			vars n = (vars)node;
			Iterator<var> listElements = n.getVar().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof var1) {
			var1 n = (var1)node;
			{
				ASTStringNode v=n.getVariable_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof var2) {
			var2 n = (var2)node;
			printToken("(");
			{
				ASTStringNode v=n.getVarsym();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof conP1) {
			conP1 n = (conP1)node;
			{
				ASTStringNode v=n.getConstructor_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof conP2) {
			conP2 n = (conP2)node;
			printToken("(");
			{
				ASTStringNode v=n.getConsym();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof tyvar) {
			tyvar n = (tyvar)node;
			{
				ASTStringNode v=n.getVariable_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof funlhs) {
			funlhs n = (funlhs)node;
			{
				funlhsL v=n.getFunlhsL();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				funlhsR v=n.getFunlhsR();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof funlhsL1) {
			funlhsL1 n = (funlhsL1)node;
			{
				ASTStringNode v=n.getVariable_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				varop v=n.getVarop();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof funlhsL2) {
			funlhsL2 n = (funlhsL2)node;
			{
				var v=n.getVar();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof funlhsR1) {
			funlhsR1 n = (funlhsR1)node;
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof funlhsR2) {
			funlhsR2 n = (funlhsR2)node;
			{
				ASTStringNode v=n.getFinduntilsemiorequals();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof varop1) {
			varop1 n = (varop1)node;
			{
				ASTStringNode v=n.getVarsym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof varop2) {
			varop2 n = (varop2)node;
			printToken("`");
			{
				ASTStringNode v=n.getVariable_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("`");
			return false;
		}
		if (node instanceof conop1) {
			conop1 n = (conop1)node;
			{
				ASTStringNode v=n.getConsym();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof conop2) {
			conop2 n = (conop2)node;
			printToken("`");
			{
				ASTStringNode v=n.getConstructor_id();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("`");
			return false;
		}
		if (node instanceof op1) {
			op1 n = (op1)node;
			{
				varop v=n.getVarop();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof op2) {
			op2 n = (op2)node;
			{
				conop v=n.getConop();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof block) {
			block n = (block)node;
			printToken("{");
			{
				ASTStringNode v=n.getFindblockcontent();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof list) {
			list n = (list)node;
			printToken("(");
			{
				ASTStringNode v=n.getFindlistcontent();
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
