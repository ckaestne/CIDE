package tmp.generated_javacc;

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
		if (node instanceof javacc_input) {
			javacc_input n = (javacc_input)node;
			{
				javacc_options v=n.getJavacc_options();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("PARSER_BEGIN");
			printToken("(");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				CompilationUnit v=n.getCompilationUnit();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("PARSER_END");
			printToken("(");
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			for (production v : n.getProduction()) {
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
		if (node instanceof javacc_options) {
			javacc_options n = (javacc_options)node;
			printToken("options");
			printToken("{");
			for (option_binding v : n.getOption_binding()) {
				v.accept(this);
			}
			printToken("}");
			return false;
		}
		if (node instanceof option_binding) {
			option_binding n = (option_binding)node;
			{
				OptionName v=n.getOptionName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				OptionValue v=n.getOptionValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof OptionName1) {
			OptionName1 n = (OptionName1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof OptionName2) {
			OptionName2 n = (OptionName2)node;
			printToken("LOOKAHEAD");
			return false;
		}
		if (node instanceof OptionName3) {
			OptionName3 n = (OptionName3)node;
			printToken("IGNORE_CASE");
			return false;
		}
		if (node instanceof OptionName4) {
			OptionName4 n = (OptionName4)node;
			printToken("static");
			return false;
		}
		if (node instanceof OptionValue1) {
			OptionValue1 n = (OptionValue1)node;
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof OptionValue2) {
			OptionValue2 n = (OptionValue2)node;
			{
				BooleanLiteral v=n.getBooleanLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof OptionValue3) {
			OptionValue3 n = (OptionValue3)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof production1) {
			production1 n = (production1)node;
			{
				javacode_production v=n.getJavacode_production();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			hintNewLine();
			return false;
		}
		if (node instanceof production2) {
			production2 n = (production2)node;
			{
				regular_expr_production v=n.getRegular_expr_production();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			hintNewLine();
			return false;
		}
		if (node instanceof production3) {
			production3 n = (production3)node;
			{
				token_manager_decls v=n.getToken_manager_decls();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			hintNewLine();
			return false;
		}
		if (node instanceof production4) {
			production4 n = (production4)node;
			{
				bnf_production v=n.getBnf_production();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			hintNewLine();
			return false;
		}
		if (node instanceof javacode_production) {
			javacode_production n = (javacode_production)node;
			printToken("JAVACODE");
			{
				AccessModifier v=n.getAccessModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ResultType v=n.getResultType();
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
			{
				FormalParameters v=n.getFormalParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ThrowsClause v=n.getThrowsClause();
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
		if (node instanceof ThrowsClause) {
			ThrowsClause n = (ThrowsClause)node;
			Iterator<Name> listElements = n.getName().iterator();
			printToken("throws");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof bnf_production) {
			bnf_production n = (bnf_production)node;
			{
				AccessModifier v=n.getAccessModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ResultType v=n.getResultType();
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
			{
				FormalParameters v=n.getFormalParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ThrowsClause v=n.getThrowsClause();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			hintIncIndent();
			{
				expansion_choices v=n.getExpansion_choices();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			printToken("}");
			return false;
		}
		if (node instanceof AccessModifier1) {
			AccessModifier1 n = (AccessModifier1)node;
			printToken("public");
			return false;
		}
		if (node instanceof AccessModifier2) {
			AccessModifier2 n = (AccessModifier2)node;
			printToken("protected");
			return false;
		}
		if (node instanceof AccessModifier3) {
			AccessModifier3 n = (AccessModifier3)node;
			printToken("private");
			return false;
		}
		if (node instanceof regular_expr_production) {
			regular_expr_production n = (regular_expr_production)node;
			Iterator<regexpr_spec> listElements = n.getRegexpr_spec().iterator();
			{
				regular_expr_productionPrefix v=n.getRegular_expr_productionPrefix();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				regexpr_kind v=n.getRegexpr_kind();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText464();
				if (v!=null) {
					printToken("[");
					printToken("IGNORE_CASE");
					printToken("]");
					v.accept(this);
				}
			}
			printToken(":");
			printToken("{");
			hintIncIndent();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				hintNewLine();
				printToken("|");
				listElements.next().accept(this);
			}
			hintDecIndent();
			printToken("}");
			return false;
		}
		if (node instanceof regular_expr_productionPrefix1) {
			regular_expr_productionPrefix1 n = (regular_expr_productionPrefix1)node;
			printToken("<");
			printToken("*");
			printToken(">");
			return false;
		}
		if (node instanceof regular_expr_productionPrefix2) {
			regular_expr_productionPrefix2 n = (regular_expr_productionPrefix2)node;
			Iterator<ASTStringNode> listElements = n.getIdentifier().iterator();
			printToken("<");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof token_manager_decls) {
			token_manager_decls n = (token_manager_decls)node;
			printToken("TOKEN_MGR_DECLS");
			printToken(":");
			{
				ClassOrInterfaceBody v=n.getClassOrInterfaceBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof regexpr_kind1) {
			regexpr_kind1 n = (regexpr_kind1)node;
			printToken("TOKEN");
			return false;
		}
		if (node instanceof regexpr_kind2) {
			regexpr_kind2 n = (regexpr_kind2)node;
			printToken("SPECIAL_TOKEN");
			return false;
		}
		if (node instanceof regexpr_kind3) {
			regexpr_kind3 n = (regexpr_kind3)node;
			printToken("SKIP");
			return false;
		}
		if (node instanceof regexpr_kind4) {
			regexpr_kind4 n = (regexpr_kind4)node;
			printToken("MORE");
			return false;
		}
		if (node instanceof regexpr_spec) {
			regexpr_spec n = (regexpr_spec)node;
			{
				regular_expression v=n.getRegular_expression();
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
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					printToken(":");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expansion_choices) {
			expansion_choices n = (expansion_choices)node;
			Iterator<expansion> listElements = n.getExpansion().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				hintNewLine();
				printToken("|");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof expansion) {
			expansion n = (expansion)node;
			{
				local_lookahead v=n.getLocal_lookahead();
				if (v!=null) {
					printToken("LOOKAHEAD");
					printToken("(");
					v.accept(this);
					printToken(")");
				}
			}
			for (expansion_unit v : n.getExpansion_unit()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof local_lookahead) {
			local_lookahead n = (local_lookahead)node;
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText470();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			{
				expansion_choices v=n.getExpansion_choices();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText471();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			{
				Expression v=n.getExpression();
				if (v!=null) {
					printToken("{");
					v.accept(this);
					printToken("}");
				}
			}
			return false;
		}
		if (node instanceof expansion_unit1) {
			expansion_unit1 n = (expansion_unit1)node;
			printToken("LOOKAHEAD");
			printToken("(");
			{
				local_lookahead v=n.getLocal_lookahead();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof expansion_unit2) {
			expansion_unit2 n = (expansion_unit2)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expansion_unit3) {
			expansion_unit3 n = (expansion_unit3)node;
			printToken("[");
			{
				expansion_choices v=n.getExpansion_choices();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof expansion_unit4) {
			expansion_unit4 n = (expansion_unit4)node;
			printToken("try");
			printToken("{");
			{
				expansion_choices v=n.getExpansion_choices1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			for (CatchPart v : n.getCatchPart()) {
				v.accept(this);
			}
			{
				Block v=n.getBlock1();
				if (v!=null) {
					printToken("finally");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expansion_unit5) {
			expansion_unit5 n = (expansion_unit5)node;
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
					printToken("=");
				}
			}
			{
				expansion_unitMain v=n.getExpansion_unitMain();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expansion_unit6) {
			expansion_unit6 n = (expansion_unit6)node;
			printToken("(");
			{
				expansion_choices v=n.getExpansion_choices2();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				ExpModifier v=n.getExpModifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CatchPart) {
			CatchPart n = (CatchPart)node;
			printToken("catch");
			printToken("(");
			{
				Name v=n.getName();
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
			printToken(")");
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expansion_unitMain1) {
			expansion_unitMain1 n = (expansion_unitMain1)node;
			{
				ASTStringNode v=n.getIdentifier();
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
			return false;
		}
		if (node instanceof expansion_unitMain2) {
			expansion_unitMain2 n = (expansion_unitMain2)node;
			{
				regular_expression v=n.getRegular_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					printToken(".");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExpModifier1) {
			ExpModifier1 n = (ExpModifier1)node;
			printToken("+");
			return false;
		}
		if (node instanceof ExpModifier2) {
			ExpModifier2 n = (ExpModifier2)node;
			printToken("*");
			return false;
		}
		if (node instanceof ExpModifier3) {
			ExpModifier3 n = (ExpModifier3)node;
			printToken("?");
			return false;
		}
		if (node instanceof regular_expression1) {
			regular_expression1 n = (regular_expression1)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof regular_expression2) {
			regular_expression2 n = (regular_expression2)node;
			printToken("<");
			{
				RegId v=n.getRegId();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				complex_regular_expression_choices v=n.getComplex_regular_expression_choices();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof regular_expression3) {
			regular_expression3 n = (regular_expression3)node;
			printToken("<");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof EOFExpr) {
			EOFExpr n = (EOFExpr)node;
			printToken("<");
			printToken("EOF");
			printToken(">");
			return false;
		}
		if (node instanceof RegId) {
			RegId n = (RegId)node;
			{
				ASTTextNode v=n.getText476();
				if (v!=null) {
					printToken("#");
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			return false;
		}
		if (node instanceof complex_regular_expression_choices) {
			complex_regular_expression_choices n = (complex_regular_expression_choices)node;
			Iterator<complex_regular_expression> listElements = n.getComplex_regular_expression().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken("|");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof complex_regular_expression) {
			complex_regular_expression n = (complex_regular_expression)node;
			for (complex_regular_expression_unit v : n.getComplex_regular_expression_unit()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof complex_regular_expression_unit1) {
			complex_regular_expression_unit1 n = (complex_regular_expression_unit1)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof complex_regular_expression_unit2) {
			complex_regular_expression_unit2 n = (complex_regular_expression_unit2)node;
			printToken("<");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(">");
			return false;
		}
		if (node instanceof complex_regular_expression_unit3) {
			complex_regular_expression_unit3 n = (complex_regular_expression_unit3)node;
			{
				character_list v=n.getCharacter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof complex_regular_expression_unit4) {
			complex_regular_expression_unit4 n = (complex_regular_expression_unit4)node;
			printToken("(");
			{
				complex_regular_expression_choices v=n.getComplex_regular_expression_choices();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				CREUPostfix v=n.getCREUPostfix();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CREUPostfix1) {
			CREUPostfix1 n = (CREUPostfix1)node;
			printToken("+");
			return false;
		}
		if (node instanceof CREUPostfix2) {
			CREUPostfix2 n = (CREUPostfix2)node;
			printToken("*");
			return false;
		}
		if (node instanceof CREUPostfix3) {
			CREUPostfix3 n = (CREUPostfix3)node;
			printToken("?");
			return false;
		}
		if (node instanceof CREUPostfix4) {
			CREUPostfix4 n = (CREUPostfix4)node;
			printToken("{");
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				CREUPostfixPP v=n.getCREUPostfixPP();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof CREUPostfixPP) {
			CREUPostfixPP n = (CREUPostfixPP)node;
			printToken(",");
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof character_list) {
			character_list n = (character_list)node;
			{
				ASTTextNode v=n.getText480();
				if (v!=null) {
					printToken("~");
					v.accept(this);
				}
			}
			printToken("[");
			{
				character_descriptorList v=n.getCharacter_descriptorList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof character_descriptorList) {
			character_descriptorList n = (character_descriptorList)node;
			Iterator<character_descriptor> listElements = n.getCharacter_descriptor().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof character_descriptor) {
			character_descriptor n = (character_descriptor)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getString_literal1();
				if (v!=null) {
					printToken("-");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CompilationUnit) {
			CompilationUnit n = (CompilationUnit)node;
			{
				PackageDeclaration v=n.getPackageDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ImportDeclaration v : n.getImportDeclaration()) {
				v.accept(this);
			}
			for (TypeDeclaration v : n.getTypeDeclaration()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof PackageDeclaration) {
			PackageDeclaration n = (PackageDeclaration)node;
			printToken("package");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof ImportDeclaration) {
			ImportDeclaration n = (ImportDeclaration)node;
			printToken("import");
			{
				ASTTextNode v=n.getText481();
				if (v!=null) {
					printToken("static");
					v.accept(this);
				}
			}
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText482();
				if (v!=null) {
					v.accept(this);
					printToken(".");
					printToken("*");
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof Modifiers) {
			Modifiers n = (Modifiers)node;
			for (Modifier v : n.getModifier()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Modifier1) {
			Modifier1 n = (Modifier1)node;
			printToken("public");
			return false;
		}
		if (node instanceof Modifier2) {
			Modifier2 n = (Modifier2)node;
			printToken("static");
			return false;
		}
		if (node instanceof Modifier3) {
			Modifier3 n = (Modifier3)node;
			printToken("protected");
			return false;
		}
		if (node instanceof Modifier4) {
			Modifier4 n = (Modifier4)node;
			printToken("private");
			return false;
		}
		if (node instanceof Modifier5) {
			Modifier5 n = (Modifier5)node;
			printToken("final");
			return false;
		}
		if (node instanceof Modifier6) {
			Modifier6 n = (Modifier6)node;
			printToken("abstract");
			return false;
		}
		if (node instanceof Modifier7) {
			Modifier7 n = (Modifier7)node;
			printToken("synchronized");
			return false;
		}
		if (node instanceof Modifier8) {
			Modifier8 n = (Modifier8)node;
			printToken("native");
			return false;
		}
		if (node instanceof Modifier9) {
			Modifier9 n = (Modifier9)node;
			printToken("transient");
			return false;
		}
		if (node instanceof Modifier10) {
			Modifier10 n = (Modifier10)node;
			printToken("volatile");
			return false;
		}
		if (node instanceof Modifier11) {
			Modifier11 n = (Modifier11)node;
			printToken("strictfp");
			return false;
		}
		if (node instanceof Modifier12) {
			Modifier12 n = (Modifier12)node;
			{
				Annotation v=n.getAnnotation();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeDeclaration1) {
			TypeDeclaration1 n = (TypeDeclaration1)node;
			printToken(";");
			return false;
		}
		if (node instanceof TypeDeclaration2) {
			TypeDeclaration2 n = (TypeDeclaration2)node;
			{
				Modifiers v=n.getModifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ClassOrInterfaceDeclaration v=n.getClassOrInterfaceDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeDeclaration3) {
			TypeDeclaration3 n = (TypeDeclaration3)node;
			{
				Modifiers v=n.getModifiers1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				EnumDeclaration v=n.getEnumDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeDeclaration4) {
			TypeDeclaration4 n = (TypeDeclaration4)node;
			{
				Modifiers v=n.getModifiers2();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AnnotationTypeDeclaration v=n.getAnnotationTypeDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration n = (ClassOrInterfaceDeclaration)node;
			{
				ClassOrInterface v=n.getClassOrInterface();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				TypeParameters v=n.getTypeParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ExtendsList v=n.getExtendsList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ImplementsList v=n.getImplementsList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ClassOrInterfaceBody v=n.getClassOrInterfaceBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterface1) {
			ClassOrInterface1 n = (ClassOrInterface1)node;
			printToken("class");
			return false;
		}
		if (node instanceof ClassOrInterface2) {
			ClassOrInterface2 n = (ClassOrInterface2)node;
			printToken("interface");
			return false;
		}
		if (node instanceof ExtendsList) {
			ExtendsList n = (ExtendsList)node;
			Iterator<ClassOrInterfaceType> listElements = n.getClassOrInterfaceType().iterator();
			printToken("extends");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof ImplementsList) {
			ImplementsList n = (ImplementsList)node;
			Iterator<ClassOrInterfaceType> listElements = n.getClassOrInterfaceType().iterator();
			printToken("implements");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof EnumDeclaration) {
			EnumDeclaration n = (EnumDeclaration)node;
			printToken("enum");
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ImplementsList v=n.getImplementsList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				EnumBody v=n.getEnumBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EnumBody) {
			EnumBody n = (EnumBody)node;
			Iterator<EnumConstant> listElements = n.getEnumConstant().iterator();
			printToken("{");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			{
				EnumBodyInternal v=n.getEnumBodyInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof EnumBodyInternal) {
			EnumBodyInternal n = (EnumBodyInternal)node;
			printToken(";");
			for (ClassOrInterfaceBodyDeclaration v : n.getClassOrInterfaceBodyDeclaration()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EnumConstant) {
			EnumConstant n = (EnumConstant)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
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
			{
				ClassOrInterfaceBody v=n.getClassOrInterfaceBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeParameters) {
			TypeParameters n = (TypeParameters)node;
			Iterator<TypeParameter> listElements = n.getTypeParameter().iterator();
			printToken("<");
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof TypeParameter) {
			TypeParameter n = (TypeParameter)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				TypeBound v=n.getTypeBound();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeBound) {
			TypeBound n = (TypeBound)node;
			printToken("extends");
			{
				ClassOrInterfaceType v=n.getClassOrInterfaceType();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ClassOrInterfaceType v : n.getClassOrInterfaceType1()) {
				printToken("&");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceBody) {
			ClassOrInterfaceBody n = (ClassOrInterfaceBody)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (ClassOrInterfaceBodyDeclaration v : n.getClassOrInterfaceBodyDeclaration()) {
				v.accept(this);
				hintNewLine();
				hintNewLine();
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			return false;
		}
		if (node instanceof ClassOrInterfaceBodyDeclaration1) {
			ClassOrInterfaceBodyDeclaration1 n = (ClassOrInterfaceBodyDeclaration1)node;
			{
				Initializer v=n.getInitializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceBodyDeclaration2) {
			ClassOrInterfaceBodyDeclaration2 n = (ClassOrInterfaceBodyDeclaration2)node;
			{
				Modifiers v=n.getModifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ClassOrInterfaceDeclaration v=n.getClassOrInterfaceDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceBodyDeclaration3) {
			ClassOrInterfaceBodyDeclaration3 n = (ClassOrInterfaceBodyDeclaration3)node;
			{
				Modifiers v=n.getModifiers1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				EnumDeclaration v=n.getEnumDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceBodyDeclaration4) {
			ClassOrInterfaceBodyDeclaration4 n = (ClassOrInterfaceBodyDeclaration4)node;
			{
				Modifiers v=n.getModifiers2();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ConstructorDeclaration v=n.getConstructorDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceBodyDeclaration5) {
			ClassOrInterfaceBodyDeclaration5 n = (ClassOrInterfaceBodyDeclaration5)node;
			{
				Modifiers v=n.getModifiers3();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				FieldDeclaration v=n.getFieldDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceBodyDeclaration6) {
			ClassOrInterfaceBodyDeclaration6 n = (ClassOrInterfaceBodyDeclaration6)node;
			{
				Modifiers v=n.getModifiers4();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				MethodDeclaration v=n.getMethodDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceBodyDeclaration7) {
			ClassOrInterfaceBodyDeclaration7 n = (ClassOrInterfaceBodyDeclaration7)node;
			printToken(";");
			return false;
		}
		if (node instanceof FieldDeclaration) {
			FieldDeclaration n = (FieldDeclaration)node;
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				VariableDeclarator v=n.getVariableDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (VariableDeclarator v : n.getVariableDeclarator1()) {
				printToken(",");
				v.accept(this);
			}
			printToken(";");
			return false;
		}
		if (node instanceof VariableDeclarator) {
			VariableDeclarator n = (VariableDeclarator)node;
			{
				VariableDeclaratorId v=n.getVariableDeclaratorId();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				VariableInitializer v=n.getVariableInitializer();
				if (v!=null) {
					printToken("=");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VariableDeclaratorId) {
			VariableDeclaratorId n = (VariableDeclaratorId)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTTextNode v : n.getText498()) {
				printToken("[");
				printToken("]");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof VariableInitializer1) {
			VariableInitializer1 n = (VariableInitializer1)node;
			{
				ArrayInitializer v=n.getArrayInitializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof VariableInitializer2) {
			VariableInitializer2 n = (VariableInitializer2)node;
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ArrayInitializer) {
			ArrayInitializer n = (ArrayInitializer)node;
			printToken("{");
			{
				ArrayInitializerInternal v=n.getArrayInitializerInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText499();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof ArrayInitializerInternal) {
			ArrayInitializerInternal n = (ArrayInitializerInternal)node;
			{
				VariableInitializer v=n.getVariableInitializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (VariableInitializer v : n.getVariableInitializer1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof MethodDeclaration) {
			MethodDeclaration n = (MethodDeclaration)node;
			{
				TypeParameters v=n.getTypeParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ResultType v=n.getResultType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				MethodDeclarator v=n.getMethodDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				NameList v=n.getNameList();
				if (v!=null) {
					printToken("throws");
					v.accept(this);
				}
			}
			{
				MethodDeclarationBody v=n.getMethodDeclarationBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MethodDeclarationBody1) {
			MethodDeclarationBody1 n = (MethodDeclarationBody1)node;
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MethodDeclarationBody2) {
			MethodDeclarationBody2 n = (MethodDeclarationBody2)node;
			printToken(";");
			return false;
		}
		if (node instanceof MethodDeclarator) {
			MethodDeclarator n = (MethodDeclarator)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				FormalParameters v=n.getFormalParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTTextNode v : n.getText501()) {
				printToken("[");
				printToken("]");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof FormalParameters) {
			FormalParameters n = (FormalParameters)node;
			printToken("(");
			{
				FormalParametersInternal v=n.getFormalParametersInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof FormalParametersInternal) {
			FormalParametersInternal n = (FormalParametersInternal)node;
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
				ASTTextNode v=n.getText502();
				if (v!=null) {
					printToken("final");
					v.accept(this);
				}
			}
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText503();
				if (v!=null) {
					printToken("...");
					v.accept(this);
				}
			}
			{
				VariableDeclaratorId v=n.getVariableDeclaratorId();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConstructorDeclaration) {
			ConstructorDeclaration n = (ConstructorDeclaration)node;
			{
				TypeParameters v=n.getTypeParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				FormalParameters v=n.getFormalParameters();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				NameList v=n.getNameList();
				if (v!=null) {
					printToken("throws");
					v.accept(this);
				}
			}
			printToken("{");
			{
				ExplicitConstructorInvocation v=n.getExplicitConstructorInvocation();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BlockStatement v : n.getBlockStatement()) {
				v.accept(this);
			}
			printToken("}");
			return false;
		}
		if (node instanceof ExplicitConstructorInvocation1) {
			ExplicitConstructorInvocation1 n = (ExplicitConstructorInvocation1)node;
			printToken("this");
			{
				Arguments v=n.getArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof ExplicitConstructorInvocation2) {
			ExplicitConstructorInvocation2 n = (ExplicitConstructorInvocation2)node;
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
					printToken(".");
				}
			}
			printToken("super");
			{
				Arguments v=n.getArguments1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof Initializer) {
			Initializer n = (Initializer)node;
			{
				ASTTextNode v=n.getText504();
				if (v!=null) {
					printToken("static");
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
		if (node instanceof Type1) {
			Type1 n = (Type1)node;
			{
				ReferenceTypeP v=n.getReferenceTypeP();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Type2) {
			Type2 n = (Type2)node;
			{
				PrimitiveType v=n.getPrimitiveType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ReferenceTypeP1) {
			ReferenceTypeP1 n = (ReferenceTypeP1)node;
			{
				PrimitiveType v=n.getPrimitiveType();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTTextNode v : n.getText505()) {
				printToken("[");
				printToken("]");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ReferenceTypeP2) {
			ReferenceTypeP2 n = (ReferenceTypeP2)node;
			{
				ClassOrInterfaceType v=n.getClassOrInterfaceType();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTTextNode v : n.getText506()) {
				printToken("[");
				printToken("]");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceType) {
			ClassOrInterfaceType n = (ClassOrInterfaceType)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				TypeArguments v=n.getTypeArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ClassOrInterfaceTypeIntern v : n.getClassOrInterfaceTypeIntern()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ClassOrInterfaceTypeIntern) {
			ClassOrInterfaceTypeIntern n = (ClassOrInterfaceTypeIntern)node;
			printToken(".");
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				TypeArguments v=n.getTypeArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeArguments) {
			TypeArguments n = (TypeArguments)node;
			printToken("<");
			{
				TypeArgument v=n.getTypeArgument();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (TypeArgument v : n.getTypeArgument1()) {
				printToken(",");
				v.accept(this);
			}
			printToken(">");
			return false;
		}
		if (node instanceof TypeArgument1) {
			TypeArgument1 n = (TypeArgument1)node;
			{
				ReferenceTypeP v=n.getReferenceTypeP();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeArgument2) {
			TypeArgument2 n = (TypeArgument2)node;
			printToken("?");
			{
				WildcardBounds v=n.getWildcardBounds();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof WildcardBounds1) {
			WildcardBounds1 n = (WildcardBounds1)node;
			printToken("extends");
			{
				ReferenceTypeP v=n.getReferenceTypeP();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof WildcardBounds2) {
			WildcardBounds2 n = (WildcardBounds2)node;
			printToken("super");
			{
				ReferenceTypeP v=n.getReferenceTypeP1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimitiveType1) {
			PrimitiveType1 n = (PrimitiveType1)node;
			printToken("boolean");
			return false;
		}
		if (node instanceof PrimitiveType2) {
			PrimitiveType2 n = (PrimitiveType2)node;
			printToken("char");
			return false;
		}
		if (node instanceof PrimitiveType3) {
			PrimitiveType3 n = (PrimitiveType3)node;
			printToken("byte");
			return false;
		}
		if (node instanceof PrimitiveType4) {
			PrimitiveType4 n = (PrimitiveType4)node;
			printToken("short");
			return false;
		}
		if (node instanceof PrimitiveType5) {
			PrimitiveType5 n = (PrimitiveType5)node;
			printToken("int");
			return false;
		}
		if (node instanceof PrimitiveType6) {
			PrimitiveType6 n = (PrimitiveType6)node;
			printToken("long");
			return false;
		}
		if (node instanceof PrimitiveType7) {
			PrimitiveType7 n = (PrimitiveType7)node;
			printToken("float");
			return false;
		}
		if (node instanceof PrimitiveType8) {
			PrimitiveType8 n = (PrimitiveType8)node;
			printToken("double");
			return false;
		}
		if (node instanceof ResultType1) {
			ResultType1 n = (ResultType1)node;
			printToken("void");
			return false;
		}
		if (node instanceof ResultType2) {
			ResultType2 n = (ResultType2)node;
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Name) {
			Name n = (Name)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (JavaIdentifier v : n.getJavaIdentifier1()) {
				printToken(".");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof NameList) {
			NameList n = (NameList)node;
			Iterator<Name> listElements = n.getName().iterator();
			if (listElements.hasNext()) {
				listElements.next().accept(this);
			}
			while (listElements.hasNext()) {
				printToken(",");
				listElements.next().accept(this);
			}
			return false;
		}
		if (node instanceof Expression) {
			Expression n = (Expression)node;
			{
				ConditionalExpression v=n.getConditionalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AssignExp v=n.getAssignExp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignExp) {
			AssignExp n = (AssignExp)node;
			{
				AssignmentOperator v=n.getAssignmentOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Expression v=n.getExpression();
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
			printToken("/=");
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
		if (node instanceof ConditionalExpression1) {
			ConditionalExpression1 n = (ConditionalExpression1)node;
			{
				IASTNode v=n.getConditionalExpressionFull();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConditionalExpression2) {
			ConditionalExpression2 n = (ConditionalExpression2)node;
			{
				ConditionalOrExpression v=n.getConditionalOrExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConditionalExpressionFull) {
			ConditionalExpressionFull n = (ConditionalExpressionFull)node;
			{
				ConditionalOrExpression v=n.getConditionalOrExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("?");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				Expression v=n.getExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConditionalOrExpression) {
			ConditionalOrExpression n = (ConditionalOrExpression)node;
			{
				ConditionalAndExpression v=n.getConditionalAndExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ConditionalAndExpression v : n.getConditionalAndExpression1()) {
				printToken("||");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ConditionalAndExpression) {
			ConditionalAndExpression n = (ConditionalAndExpression)node;
			{
				InclusiveOrExpression v=n.getInclusiveOrExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (InclusiveOrExpression v : n.getInclusiveOrExpression1()) {
				printToken("&&");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof InclusiveOrExpression) {
			InclusiveOrExpression n = (InclusiveOrExpression)node;
			{
				ExclusiveOrExpression v=n.getExclusiveOrExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ExclusiveOrExpression v : n.getExclusiveOrExpression1()) {
				printToken("|");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ExclusiveOrExpression) {
			ExclusiveOrExpression n = (ExclusiveOrExpression)node;
			{
				AndExpression v=n.getAndExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (AndExpression v : n.getAndExpression1()) {
				printToken("^");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof AndExpression) {
			AndExpression n = (AndExpression)node;
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
		if (node instanceof EqualityExpression) {
			EqualityExpression n = (EqualityExpression)node;
			{
				InstanceOfExpression v=n.getInstanceOfExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (EqualityExpressionIntern v : n.getEqualityExpressionIntern()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EqualityExpressionIntern) {
			EqualityExpressionIntern n = (EqualityExpressionIntern)node;
			{
				EqualityOp v=n.getEqualityOp();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				InstanceOfExpression v=n.getInstanceOfExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EqualityOp1) {
			EqualityOp1 n = (EqualityOp1)node;
			printToken("==");
			return false;
		}
		if (node instanceof EqualityOp2) {
			EqualityOp2 n = (EqualityOp2)node;
			printToken("!=");
			return false;
		}
		if (node instanceof InstanceOfExpression) {
			InstanceOfExpression n = (InstanceOfExpression)node;
			{
				RelationalExpression v=n.getRelationalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Type v=n.getType();
				if (v!=null) {
					printToken("instanceof");
					v.accept(this);
				}
			}
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
			for (RelationalExpressionIntern v : n.getRelationalExpressionIntern()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof RelationalExpressionIntern) {
			RelationalExpressionIntern n = (RelationalExpressionIntern)node;
			{
				RelationalOp v=n.getRelationalOp();
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
		if (node instanceof RelationalOp1) {
			RelationalOp1 n = (RelationalOp1)node;
			printToken("<");
			return false;
		}
		if (node instanceof RelationalOp2) {
			RelationalOp2 n = (RelationalOp2)node;
			printToken(">");
			return false;
		}
		if (node instanceof RelationalOp3) {
			RelationalOp3 n = (RelationalOp3)node;
			printToken("<=");
			return false;
		}
		if (node instanceof RelationalOp4) {
			RelationalOp4 n = (RelationalOp4)node;
			printToken(">=");
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
			for (ShiftExpressionRight v : n.getShiftExpressionRight()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ShiftExpressionRight) {
			ShiftExpressionRight n = (ShiftExpressionRight)node;
			{
				ShiftOp v=n.getShiftOp();
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
		if (node instanceof ShiftOp1) {
			ShiftOp1 n = (ShiftOp1)node;
			printToken("<<");
			return false;
		}
		if (node instanceof ShiftOp2) {
			ShiftOp2 n = (ShiftOp2)node;
			printToken(">");
			printToken(">");
			printToken(">");
			return false;
		}
		if (node instanceof ShiftOp3) {
			ShiftOp3 n = (ShiftOp3)node;
			printToken(">");
			printToken(">");
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
			for (AdditiveExpressionIntern v : n.getAdditiveExpressionIntern()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof AdditiveExpressionIntern) {
			AdditiveExpressionIntern n = (AdditiveExpressionIntern)node;
			{
				AdditiveOp v=n.getAdditiveOp();
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
		if (node instanceof AdditiveOp1) {
			AdditiveOp1 n = (AdditiveOp1)node;
			printToken("+");
			return false;
		}
		if (node instanceof AdditiveOp2) {
			AdditiveOp2 n = (AdditiveOp2)node;
			printToken("-");
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
			for (MultiplicativeExpressionIntern v : n.getMultiplicativeExpressionIntern()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof MultiplicativeExpressionIntern) {
			MultiplicativeExpressionIntern n = (MultiplicativeExpressionIntern)node;
			{
				MultiplicativeOp v=n.getMultiplicativeOp();
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
		if (node instanceof MultiplicativeOp1) {
			MultiplicativeOp1 n = (MultiplicativeOp1)node;
			printToken("*");
			return false;
		}
		if (node instanceof MultiplicativeOp2) {
			MultiplicativeOp2 n = (MultiplicativeOp2)node;
			printToken("/");
			return false;
		}
		if (node instanceof MultiplicativeOp3) {
			MultiplicativeOp3 n = (MultiplicativeOp3)node;
			printToken("%");
			return false;
		}
		if (node instanceof UnaryExpression1) {
			UnaryExpression1 n = (UnaryExpression1)node;
			{
				AdditiveOp v=n.getAdditiveOp();
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
		if (node instanceof UnaryExpression2) {
			UnaryExpression2 n = (UnaryExpression2)node;
			{
				PreIncrementExpression v=n.getPreIncrementExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpression3) {
			UnaryExpression3 n = (UnaryExpression3)node;
			{
				PreDecrementExpression v=n.getPreDecrementExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpression4) {
			UnaryExpression4 n = (UnaryExpression4)node;
			{
				UnaryExpressionNotPlusMinus v=n.getUnaryExpressionNotPlusMinus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PreIncrementExpression) {
			PreIncrementExpression n = (PreIncrementExpression)node;
			printToken("++");
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PreDecrementExpression) {
			PreDecrementExpression n = (PreDecrementExpression)node;
			printToken("--");
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpressionNotPlusMinus1) {
			UnaryExpressionNotPlusMinus1 n = (UnaryExpressionNotPlusMinus1)node;
			{
				UnaryOp v=n.getUnaryOp();
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
		if (node instanceof UnaryExpressionNotPlusMinus2) {
			UnaryExpressionNotPlusMinus2 n = (UnaryExpressionNotPlusMinus2)node;
			{
				CastExpression v=n.getCastExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpressionNotPlusMinus3) {
			UnaryExpressionNotPlusMinus3 n = (UnaryExpressionNotPlusMinus3)node;
			{
				PostfixExpression v=n.getPostfixExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryOp1) {
			UnaryOp1 n = (UnaryOp1)node;
			printToken("~");
			return false;
		}
		if (node instanceof UnaryOp2) {
			UnaryOp2 n = (UnaryOp2)node;
			printToken("!");
			return false;
		}
		if (node instanceof CastLookahead1) {
			CastLookahead1 n = (CastLookahead1)node;
			printToken("(");
			{
				PrimitiveType v=n.getPrimitiveType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CastLookahead2) {
			CastLookahead2 n = (CastLookahead2)node;
			printToken("(");
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("[");
			printToken("]");
			return false;
		}
		if (node instanceof CastLookahead3) {
			CastLookahead3 n = (CastLookahead3)node;
			printToken("(");
			{
				Type v=n.getType1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				CastLAOp v=n.getCastLAOp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CastLAOp1) {
			CastLAOp1 n = (CastLAOp1)node;
			printToken("~");
			return false;
		}
		if (node instanceof CastLAOp2) {
			CastLAOp2 n = (CastLAOp2)node;
			printToken("!");
			return false;
		}
		if (node instanceof CastLAOp3) {
			CastLAOp3 n = (CastLAOp3)node;
			printToken("(");
			return false;
		}
		if (node instanceof CastLAOp4) {
			CastLAOp4 n = (CastLAOp4)node;
			printToken("this");
			return false;
		}
		if (node instanceof CastLAOp5) {
			CastLAOp5 n = (CastLAOp5)node;
			printToken("super");
			return false;
		}
		if (node instanceof CastLAOp6) {
			CastLAOp6 n = (CastLAOp6)node;
			printToken("new");
			return false;
		}
		if (node instanceof CastLAOp7) {
			CastLAOp7 n = (CastLAOp7)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CastLAOp8) {
			CastLAOp8 n = (CastLAOp8)node;
			{
				Literal v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PostfixExpression) {
			PostfixExpression n = (PostfixExpression)node;
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				PostfixOp v=n.getPostfixOp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PostfixOp1) {
			PostfixOp1 n = (PostfixOp1)node;
			printToken("++");
			return false;
		}
		if (node instanceof PostfixOp2) {
			PostfixOp2 n = (PostfixOp2)node;
			printToken("--");
			return false;
		}
		if (node instanceof CastExpression1) {
			CastExpression1 n = (CastExpression1)node;
			printToken("(");
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CastExpression2) {
			CastExpression2 n = (CastExpression2)node;
			printToken("(");
			{
				Type v=n.getType1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				UnaryExpressionNotPlusMinus v=n.getUnaryExpressionNotPlusMinus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression) {
			PrimaryExpression n = (PrimaryExpression)node;
			{
				PrimaryPrefix v=n.getPrimaryPrefix();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (PrimarySuffix v : n.getPrimarySuffix()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof MemberSelector) {
			MemberSelector n = (MemberSelector)node;
			printToken(".");
			{
				TypeArguments v=n.getTypeArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryPrefix1) {
			PrimaryPrefix1 n = (PrimaryPrefix1)node;
			{
				Literal v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryPrefix2) {
			PrimaryPrefix2 n = (PrimaryPrefix2)node;
			printToken("this");
			return false;
		}
		if (node instanceof PrimaryPrefix3) {
			PrimaryPrefix3 n = (PrimaryPrefix3)node;
			printToken("super");
			printToken(".");
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryPrefix4) {
			PrimaryPrefix4 n = (PrimaryPrefix4)node;
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
		if (node instanceof PrimaryPrefix5) {
			PrimaryPrefix5 n = (PrimaryPrefix5)node;
			{
				AllocationExpression v=n.getAllocationExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryPrefix6) {
			PrimaryPrefix6 n = (PrimaryPrefix6)node;
			{
				ResultType v=n.getResultType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(".");
			printToken("class");
			return false;
		}
		if (node instanceof PrimaryPrefix7) {
			PrimaryPrefix7 n = (PrimaryPrefix7)node;
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimarySuffix1) {
			PrimarySuffix1 n = (PrimarySuffix1)node;
			printToken(".");
			printToken("this");
			return false;
		}
		if (node instanceof PrimarySuffix2) {
			PrimarySuffix2 n = (PrimarySuffix2)node;
			printToken(".");
			{
				AllocationExpression v=n.getAllocationExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimarySuffix3) {
			PrimarySuffix3 n = (PrimarySuffix3)node;
			{
				MemberSelector v=n.getMemberSelector();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimarySuffix4) {
			PrimarySuffix4 n = (PrimarySuffix4)node;
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
		if (node instanceof PrimarySuffix5) {
			PrimarySuffix5 n = (PrimarySuffix5)node;
			printToken(".");
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimarySuffix6) {
			PrimarySuffix6 n = (PrimarySuffix6)node;
			{
				Arguments v=n.getArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal1) {
			Literal1 n = (Literal1)node;
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal2) {
			Literal2 n = (Literal2)node;
			{
				ASTStringNode v=n.getFloating_point_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal3) {
			Literal3 n = (Literal3)node;
			{
				ASTStringNode v=n.getCharacter_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal4) {
			Literal4 n = (Literal4)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal5) {
			Literal5 n = (Literal5)node;
			{
				BooleanLiteral v=n.getBooleanLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Literal6) {
			Literal6 n = (Literal6)node;
			{
				NullLiteral v=n.getNullLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BooleanLiteral1) {
			BooleanLiteral1 n = (BooleanLiteral1)node;
			printToken("true");
			return false;
		}
		if (node instanceof BooleanLiteral2) {
			BooleanLiteral2 n = (BooleanLiteral2)node;
			printToken("false");
			return false;
		}
		if (node instanceof NullLiteral) {
			NullLiteral n = (NullLiteral)node;
			printToken("null");
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
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Expression v : n.getExpression1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof AllocationExpression1) {
			AllocationExpression1 n = (AllocationExpression1)node;
			printToken("new");
			{
				PrimitiveType v=n.getPrimitiveType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ArrayDimsAndInits v=n.getArrayDimsAndInits();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AllocationExpression2) {
			AllocationExpression2 n = (AllocationExpression2)node;
			printToken("new");
			{
				ClassOrInterfaceType v=n.getClassOrInterfaceType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				TypeArguments v=n.getTypeArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AllocationExpressionInit v=n.getAllocationExpressionInit();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AllocationExpressionInit1) {
			AllocationExpressionInit1 n = (AllocationExpressionInit1)node;
			{
				ArrayDimsAndInits v=n.getArrayDimsAndInits();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AllocationExpressionInit2) {
			AllocationExpressionInit2 n = (AllocationExpressionInit2)node;
			{
				Arguments v=n.getArguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ClassOrInterfaceBody v=n.getClassOrInterfaceBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ArrayDimsAndInits1) {
			ArrayDimsAndInits1 n = (ArrayDimsAndInits1)node;
			printToken("[");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			for (Expression v : n.getExpression1()) {
				printToken("[");
				v.accept(this);
				printToken("]");
			}
			for (ASTTextNode v : n.getText557()) {
				printToken("[");
				printToken("]");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ArrayDimsAndInits2) {
			ArrayDimsAndInits2 n = (ArrayDimsAndInits2)node;
			for (ASTTextNode v : n.getText558()) {
				printToken("[");
				printToken("]");
				v.accept(this);
			}
			{
				ArrayInitializer v=n.getArrayInitializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement1) {
			Statement1 n = (Statement1)node;
			{
				LabeledStatement v=n.getLabeledStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement2) {
			Statement2 n = (Statement2)node;
			{
				AssertStatement v=n.getAssertStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement3) {
			Statement3 n = (Statement3)node;
			{
				Block v=n.getBlock();
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
				StatementExpression v=n.getStatementExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof Statement6) {
			Statement6 n = (Statement6)node;
			{
				SwitchStatement v=n.getSwitchStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement7) {
			Statement7 n = (Statement7)node;
			{
				IASTNode v=n.getIfStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement8) {
			Statement8 n = (Statement8)node;
			{
				IASTNode v=n.getWhileStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement9) {
			Statement9 n = (Statement9)node;
			{
				IASTNode v=n.getDoStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement10) {
			Statement10 n = (Statement10)node;
			{
				IASTNode v=n.getForStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement11) {
			Statement11 n = (Statement11)node;
			{
				BreakStatement v=n.getBreakStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement12) {
			Statement12 n = (Statement12)node;
			{
				ContinueStatement v=n.getContinueStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement13) {
			Statement13 n = (Statement13)node;
			{
				ReturnStatement v=n.getReturnStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement14) {
			Statement14 n = (Statement14)node;
			{
				ThrowStatement v=n.getThrowStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement15) {
			Statement15 n = (Statement15)node;
			{
				IASTNode v=n.getSynchronizedStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof Statement16) {
			Statement16 n = (Statement16)node;
			{
				IASTNode v=n.getTryStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof AssertStatement) {
			AssertStatement n = (AssertStatement)node;
			printToken("assert");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Expression v=n.getExpression1();
				if (v!=null) {
					printToken(":");
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof LabeledStatement) {
			LabeledStatement n = (LabeledStatement)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
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
		if (node instanceof Block) {
			Block n = (Block)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (BlockStatement v : n.getBlockStatement()) {
				v.accept(this);
			}
			hintDecIndent();
			printToken("}");
			hintNewLine();
			return false;
		}
		if (node instanceof BlockStatement1) {
			BlockStatement1 n = (BlockStatement1)node;
			{
				LocalVariableDeclaration v=n.getLocalVariableDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof BlockStatement2) {
			BlockStatement2 n = (BlockStatement2)node;
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BlockStatement3) {
			BlockStatement3 n = (BlockStatement3)node;
			{
				ClassOrInterfaceDeclaration v=n.getClassOrInterfaceDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LocalVariableDeclaration) {
			LocalVariableDeclaration n = (LocalVariableDeclaration)node;
			{
				ASTTextNode v=n.getText559();
				if (v!=null) {
					printToken("final");
					v.accept(this);
				}
			}
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				VariableDeclarator v=n.getVariableDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (VariableDeclarator v : n.getVariableDeclarator1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof EmptyStatement) {
			EmptyStatement n = (EmptyStatement)node;
			printToken(";");
			return false;
		}
		if (node instanceof StatementExpression1) {
			StatementExpression1 n = (StatementExpression1)node;
			{
				PreIncrementExpression v=n.getPreIncrementExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StatementExpression2) {
			StatementExpression2 n = (StatementExpression2)node;
			{
				PreDecrementExpression v=n.getPreDecrementExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StatementExpression3) {
			StatementExpression3 n = (StatementExpression3)node;
			{
				PrimaryExpression v=n.getPrimaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				StatementExpressionAssignment v=n.getStatementExpressionAssignment();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StatementExpressionAssignment1) {
			StatementExpressionAssignment1 n = (StatementExpressionAssignment1)node;
			printToken("++");
			return false;
		}
		if (node instanceof StatementExpressionAssignment2) {
			StatementExpressionAssignment2 n = (StatementExpressionAssignment2)node;
			printToken("--");
			return false;
		}
		if (node instanceof StatementExpressionAssignment3) {
			StatementExpressionAssignment3 n = (StatementExpressionAssignment3)node;
			{
				AssignmentOperator v=n.getAssignmentOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Expression v=n.getExpression();
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
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (SwitchStatementLabel v : n.getSwitchStatementLabel()) {
				v.accept(this);
			}
			hintDecIndent();
			printToken("}");
			hintNewLine();
			return false;
		}
		if (node instanceof SwitchStatementLabel) {
			SwitchStatementLabel n = (SwitchStatementLabel)node;
			{
				SwitchLabel v=n.getSwitchLabel();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (BlockStatement v : n.getBlockStatement()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof SwitchLabel1) {
			SwitchLabel1 n = (SwitchLabel1)node;
			printToken("case");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			return false;
		}
		if (node instanceof SwitchLabel2) {
			SwitchLabel2 n = (SwitchLabel2)node;
			printToken("default");
			printToken(":");
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
			hintNewLine();
			hintIncIndent();
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			{
				Statement v=n.getStatement1();
				if (v!=null) {
					printToken("else");
					hintNewLine();
					hintIncIndent();
					v.accept(this);
					hintDecIndent();
				}
			}
			return false;
		}
		if (node instanceof WhileStatement) {
			WhileStatement n = (WhileStatement)node;
			printToken("while");
			printToken("(");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			hintNewLine();
			hintIncIndent();
			{
				Statement v=n.getStatement();
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
			hintNewLine();
			hintIncIndent();
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			printToken("while");
			printToken("(");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken(";");
			return false;
		}
		if (node instanceof ForStatement) {
			ForStatement n = (ForStatement)node;
			printToken("for");
			printToken("(");
			{
				ForStatementInternal v=n.getForStatementInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			hintNewLine();
			hintIncIndent();
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			return false;
		}
		if (node instanceof ForStatementInternal1) {
			ForStatementInternal1 n = (ForStatementInternal1)node;
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ForStatementInternal2) {
			ForStatementInternal2 n = (ForStatementInternal2)node;
			{
				ForInit v=n.getForInit();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				Expression v=n.getExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				ForUpdate v=n.getForUpdate();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ForInit1) {
			ForInit1 n = (ForInit1)node;
			{
				LocalVariableDeclaration v=n.getLocalVariableDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ForInit2) {
			ForInit2 n = (ForInit2)node;
			{
				StatementExpressionList v=n.getStatementExpressionList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StatementExpressionList) {
			StatementExpressionList n = (StatementExpressionList)node;
			{
				StatementExpression v=n.getStatementExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (StatementExpression v : n.getStatementExpression1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ForUpdate) {
			ForUpdate n = (ForUpdate)node;
			{
				StatementExpressionList v=n.getStatementExpressionList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof BreakStatement) {
			BreakStatement n = (BreakStatement)node;
			printToken("break");
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof ContinueStatement) {
			ContinueStatement n = (ContinueStatement)node;
			printToken("continue");
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
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
			printToken(";");
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
			printToken(";");
			return false;
		}
		if (node instanceof SynchronizedStatement) {
			SynchronizedStatement n = (SynchronizedStatement)node;
			printToken("synchronized");
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
			for (CatchBlock v : n.getCatchBlock()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof FinallyBlock) {
			FinallyBlock n = (FinallyBlock)node;
			printToken("finally");
			{
				Block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CatchBlock) {
			CatchBlock n = (CatchBlock)node;
			printToken("catch");
			printToken("(");
			{
				FormalParameter v=n.getFormalParameter();
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
		if (node instanceof Annotation1) {
			Annotation1 n = (Annotation1)node;
			{
				NormalAnnotation v=n.getNormalAnnotation();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Annotation2) {
			Annotation2 n = (Annotation2)node;
			{
				SingleMemberAnnotation v=n.getSingleMemberAnnotation();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Annotation3) {
			Annotation3 n = (Annotation3)node;
			{
				MarkerAnnotation v=n.getMarkerAnnotation();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof NormalAnnotation) {
			NormalAnnotation n = (NormalAnnotation)node;
			printToken("@");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				MemberValuePairs v=n.getMemberValuePairs();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			hintNewLine();
			return false;
		}
		if (node instanceof MarkerAnnotation) {
			MarkerAnnotation n = (MarkerAnnotation)node;
			printToken("@");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof SingleMemberAnnotation) {
			SingleMemberAnnotation n = (SingleMemberAnnotation)node;
			printToken("@");
			{
				Name v=n.getName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				MemberValue v=n.getMemberValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			hintNewLine();
			return false;
		}
		if (node instanceof MemberValuePairs) {
			MemberValuePairs n = (MemberValuePairs)node;
			{
				MemberValuePair v=n.getMemberValuePair();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (MemberValuePair v : n.getMemberValuePair1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof MemberValuePair) {
			MemberValuePair n = (MemberValuePair)node;
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				MemberValue v=n.getMemberValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberValue1) {
			MemberValue1 n = (MemberValue1)node;
			{
				Annotation v=n.getAnnotation();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberValue2) {
			MemberValue2 n = (MemberValue2)node;
			{
				MemberValueArrayInitializer v=n.getMemberValueArrayInitializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberValue3) {
			MemberValue3 n = (MemberValue3)node;
			{
				ConditionalExpression v=n.getConditionalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MemberValueArrayInitializer) {
			MemberValueArrayInitializer n = (MemberValueArrayInitializer)node;
			printToken("{");
			{
				MemberValue v=n.getMemberValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (MemberValue v : n.getMemberValue1()) {
				printToken(",");
				v.accept(this);
			}
			{
				ASTTextNode v=n.getText564();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof AnnotationTypeDeclaration) {
			AnnotationTypeDeclaration n = (AnnotationTypeDeclaration)node;
			printToken("@");
			printToken("interface");
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AnnotationTypeBody v=n.getAnnotationTypeBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnnotationTypeBody) {
			AnnotationTypeBody n = (AnnotationTypeBody)node;
			printToken("{");
			for (AnnotationTypeMemberDeclaration v : n.getAnnotationTypeMemberDeclaration()) {
				v.accept(this);
			}
			printToken("}");
			return false;
		}
		if (node instanceof AnnotationTypeMemberDeclaration1) {
			AnnotationTypeMemberDeclaration1 n = (AnnotationTypeMemberDeclaration1)node;
			{
				Modifiers v=n.getModifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				JavaIdentifier v=n.getJavaIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			printToken(")");
			{
				DefaultValue v=n.getDefaultValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof AnnotationTypeMemberDeclaration2) {
			AnnotationTypeMemberDeclaration2 n = (AnnotationTypeMemberDeclaration2)node;
			{
				Modifiers v=n.getModifiers1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ClassOrInterfaceDeclaration v=n.getClassOrInterfaceDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnnotationTypeMemberDeclaration3) {
			AnnotationTypeMemberDeclaration3 n = (AnnotationTypeMemberDeclaration3)node;
			{
				Modifiers v=n.getModifiers2();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				EnumDeclaration v=n.getEnumDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnnotationTypeMemberDeclaration4) {
			AnnotationTypeMemberDeclaration4 n = (AnnotationTypeMemberDeclaration4)node;
			{
				Modifiers v=n.getModifiers3();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AnnotationTypeDeclaration v=n.getAnnotationTypeDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnnotationTypeMemberDeclaration5) {
			AnnotationTypeMemberDeclaration5 n = (AnnotationTypeMemberDeclaration5)node;
			{
				Modifiers v=n.getModifiers4();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				FieldDeclaration v=n.getFieldDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AnnotationTypeMemberDeclaration6) {
			AnnotationTypeMemberDeclaration6 n = (AnnotationTypeMemberDeclaration6)node;
			printToken(";");
			return false;
		}
		if (node instanceof DefaultValue) {
			DefaultValue n = (DefaultValue)node;
			printToken("default");
			{
				MemberValue v=n.getMemberValue();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof JavaIdentifier1) {
			JavaIdentifier1 n = (JavaIdentifier1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof JavaIdentifier2) {
			JavaIdentifier2 n = (JavaIdentifier2)node;
			printToken("options");
			return false;
		}
		if (node instanceof JavaIdentifier3) {
			JavaIdentifier3 n = (JavaIdentifier3)node;
			printToken("LOOKAHEAD");
			return false;
		}
		if (node instanceof JavaIdentifier4) {
			JavaIdentifier4 n = (JavaIdentifier4)node;
			printToken("IGNORE_CASE");
			return false;
		}
		if (node instanceof JavaIdentifier5) {
			JavaIdentifier5 n = (JavaIdentifier5)node;
			printToken("PARSER_BEGIN");
			return false;
		}
		if (node instanceof JavaIdentifier6) {
			JavaIdentifier6 n = (JavaIdentifier6)node;
			printToken("PARSER_END");
			return false;
		}
		if (node instanceof JavaIdentifier7) {
			JavaIdentifier7 n = (JavaIdentifier7)node;
			printToken("JAVACODE");
			return false;
		}
		if (node instanceof JavaIdentifier8) {
			JavaIdentifier8 n = (JavaIdentifier8)node;
			printToken("TOKEN");
			return false;
		}
		if (node instanceof JavaIdentifier9) {
			JavaIdentifier9 n = (JavaIdentifier9)node;
			printToken("SPECIAL_TOKEN");
			return false;
		}
		if (node instanceof JavaIdentifier10) {
			JavaIdentifier10 n = (JavaIdentifier10)node;
			printToken("MORE");
			return false;
		}
		if (node instanceof JavaIdentifier11) {
			JavaIdentifier11 n = (JavaIdentifier11)node;
			printToken("SKIP");
			return false;
		}
		if (node instanceof JavaIdentifier12) {
			JavaIdentifier12 n = (JavaIdentifier12)node;
			printToken("TOKEN_MGR_DECLS");
			return false;
		}
		if (node instanceof JavaIdentifier13) {
			JavaIdentifier13 n = (JavaIdentifier13)node;
			printToken("EOF");
			return false;
		}
		return true;
	}
}
