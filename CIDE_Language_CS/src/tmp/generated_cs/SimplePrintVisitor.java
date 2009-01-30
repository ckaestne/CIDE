package tmp.generated_cs;

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
		if (node instanceof compilation_unit) {
			compilation_unit n = (compilation_unit)node;
			for (using_directive v : n.getUsing_directive()) {
				v.accept(this);
			}
			{
				attributes_either v=n.getAttributes_either();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				compilation_unitEnd v=n.getCompilation_unitEnd();
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
		if (node instanceof identifier1) {
			identifier1 n = (identifier1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof identifier2) {
			identifier2 n = (identifier2)node;
			printToken("module");
			return false;
		}
		if (node instanceof identifier3) {
			identifier3 n = (identifier3)node;
			printToken("assembly");
			return false;
		}
		if (node instanceof literal1) {
			literal1 n = (literal1)node;
			{
				boolean_literal v=n.getBoolean_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof literal2) {
			literal2 n = (literal2)node;
			{
				ASTStringNode v=n.getNumeric_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof literal3) {
			literal3 n = (literal3)node;
			{
				ASTStringNode v=n.getHexadecimal_integer_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof literal4) {
			literal4 n = (literal4)node;
			{
				ASTStringNode v=n.getCharacter_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof literal5) {
			literal5 n = (literal5)node;
			{
				ASTStringNode v=n.getRegular_string_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof literal6) {
			literal6 n = (literal6)node;
			{
				ASTStringNode v=n.getVerbatim_string_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof literal7) {
			literal7 n = (literal7)node;
			printToken("null");
			return false;
		}
		if (node instanceof boolean_literal1) {
			boolean_literal1 n = (boolean_literal1)node;
			printToken("true");
			return false;
		}
		if (node instanceof boolean_literal2) {
			boolean_literal2 n = (boolean_literal2)node;
			printToken("false");
			return false;
		}
		if (node instanceof type_name) {
			type_name n = (type_name)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (identifier v : n.getIdentifier1()) {
				printToken(".");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof type) {
			type n = (type)node;
			{
				non_array_type v=n.getNon_array_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				rank_specifiers v=n.getRank_specifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText146();
				if (v!=null) {
					printToken("*");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof rank_specifiers) {
			rank_specifiers n = (rank_specifiers)node;
			for (rank_specifier v : n.getRank_specifier()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof rank_specifier) {
			rank_specifier n = (rank_specifier)node;
			printToken("[");
			for (ASTTextNode v : n.getText147()) {
				printToken(",");
				v.accept(this);
			}
			printToken("]");
			return false;
		}
		if (node instanceof non_array_type1) {
			non_array_type1 n = (non_array_type1)node;
			{
				predefined_type v=n.getPredefined_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof non_array_type2) {
			non_array_type2 n = (non_array_type2)node;
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof predefined_type1) {
			predefined_type1 n = (predefined_type1)node;
			{
				simple_type v=n.getSimple_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof predefined_type2) {
			predefined_type2 n = (predefined_type2)node;
			printToken("object");
			return false;
		}
		if (node instanceof predefined_type3) {
			predefined_type3 n = (predefined_type3)node;
			printToken("string");
			return false;
		}
		if (node instanceof predefined_type4) {
			predefined_type4 n = (predefined_type4)node;
			printToken("void");
			return false;
		}
		if (node instanceof simple_type1) {
			simple_type1 n = (simple_type1)node;
			{
				numeric_type v=n.getNumeric_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof simple_type2) {
			simple_type2 n = (simple_type2)node;
			printToken("bool");
			return false;
		}
		if (node instanceof numeric_type1) {
			numeric_type1 n = (numeric_type1)node;
			{
				integral_type v=n.getIntegral_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof numeric_type2) {
			numeric_type2 n = (numeric_type2)node;
			{
				floating_point_type v=n.getFloating_point_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof numeric_type3) {
			numeric_type3 n = (numeric_type3)node;
			printToken("decimal");
			return false;
		}
		if (node instanceof integral_type1) {
			integral_type1 n = (integral_type1)node;
			printToken("sbyte");
			return false;
		}
		if (node instanceof integral_type2) {
			integral_type2 n = (integral_type2)node;
			printToken("byte");
			return false;
		}
		if (node instanceof integral_type3) {
			integral_type3 n = (integral_type3)node;
			printToken("short");
			return false;
		}
		if (node instanceof integral_type4) {
			integral_type4 n = (integral_type4)node;
			printToken("ushort");
			return false;
		}
		if (node instanceof integral_type5) {
			integral_type5 n = (integral_type5)node;
			printToken("int");
			return false;
		}
		if (node instanceof integral_type6) {
			integral_type6 n = (integral_type6)node;
			printToken("uint");
			return false;
		}
		if (node instanceof integral_type7) {
			integral_type7 n = (integral_type7)node;
			printToken("long");
			return false;
		}
		if (node instanceof integral_type8) {
			integral_type8 n = (integral_type8)node;
			printToken("ulong");
			return false;
		}
		if (node instanceof integral_type9) {
			integral_type9 n = (integral_type9)node;
			printToken("char");
			return false;
		}
		if (node instanceof class_type1) {
			class_type1 n = (class_type1)node;
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_type2) {
			class_type2 n = (class_type2)node;
			printToken("object");
			return false;
		}
		if (node instanceof class_type3) {
			class_type3 n = (class_type3)node;
			printToken("string");
			return false;
		}
		if (node instanceof floating_point_type1) {
			floating_point_type1 n = (floating_point_type1)node;
			printToken("float");
			return false;
		}
		if (node instanceof floating_point_type2) {
			floating_point_type2 n = (floating_point_type2)node;
			printToken("double");
			return false;
		}
		if (node instanceof expression) {
			expression n = (expression)node;
			{
				conditional_expression v=n.getConditional_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				expressionInternal v=n.getExpressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof expressionInternal) {
			expressionInternal n = (expressionInternal)node;
			{
				assignment_operator v=n.getAssignment_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof assignment) {
			assignment n = (assignment)node;
			{
				conditional_expression v=n.getConditional_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				assignment_operator v=n.getAssignment_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof assignment_operator1) {
			assignment_operator1 n = (assignment_operator1)node;
			printToken("=");
			return false;
		}
		if (node instanceof assignment_operator2) {
			assignment_operator2 n = (assignment_operator2)node;
			printToken("+=");
			return false;
		}
		if (node instanceof assignment_operator3) {
			assignment_operator3 n = (assignment_operator3)node;
			printToken("-=");
			return false;
		}
		if (node instanceof assignment_operator4) {
			assignment_operator4 n = (assignment_operator4)node;
			printToken("*=");
			return false;
		}
		if (node instanceof assignment_operator5) {
			assignment_operator5 n = (assignment_operator5)node;
			printToken("/=");
			return false;
		}
		if (node instanceof assignment_operator6) {
			assignment_operator6 n = (assignment_operator6)node;
			printToken("%=");
			return false;
		}
		if (node instanceof assignment_operator7) {
			assignment_operator7 n = (assignment_operator7)node;
			printToken("&=");
			return false;
		}
		if (node instanceof assignment_operator8) {
			assignment_operator8 n = (assignment_operator8)node;
			printToken("|=");
			return false;
		}
		if (node instanceof assignment_operator9) {
			assignment_operator9 n = (assignment_operator9)node;
			printToken("^=");
			return false;
		}
		if (node instanceof assignment_operator10) {
			assignment_operator10 n = (assignment_operator10)node;
			printToken("<<=");
			return false;
		}
		if (node instanceof assignment_operator11) {
			assignment_operator11 n = (assignment_operator11)node;
			printToken(">>=");
			return false;
		}
		if (node instanceof assignment_operator12) {
			assignment_operator12 n = (assignment_operator12)node;
			printToken(">>>=");
			return false;
		}
		if (node instanceof conditional_expression) {
			conditional_expression n = (conditional_expression)node;
			{
				conditional_or_expression v=n.getConditional_or_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				conditional_expressionInternal v=n.getConditional_expressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof conditional_expressionInternal) {
			conditional_expressionInternal n = (conditional_expressionInternal)node;
			printToken("?");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				conditional_expression v=n.getConditional_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof conditional_or_expression) {
			conditional_or_expression n = (conditional_or_expression)node;
			{
				conditional_and_expression v=n.getConditional_and_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				conditional_or_expression v=n.getConditional_or_expression();
				if (v!=null) {
					printToken("||");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof conditional_and_expression) {
			conditional_and_expression n = (conditional_and_expression)node;
			{
				inclusive_or_expression v=n.getInclusive_or_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				conditional_and_expression v=n.getConditional_and_expression();
				if (v!=null) {
					printToken("&&");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof inclusive_or_expression) {
			inclusive_or_expression n = (inclusive_or_expression)node;
			{
				exclusive_or_expression v=n.getExclusive_or_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				inclusive_or_expression v=n.getInclusive_or_expression();
				if (v!=null) {
					printToken("|");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof exclusive_or_expression) {
			exclusive_or_expression n = (exclusive_or_expression)node;
			{
				and_expression v=n.getAnd_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				exclusive_or_expression v=n.getExclusive_or_expression();
				if (v!=null) {
					printToken("^");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof and_expression) {
			and_expression n = (and_expression)node;
			{
				equality_expression v=n.getEquality_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				and_expression v=n.getAnd_expression();
				if (v!=null) {
					printToken("&");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof equality_expression) {
			equality_expression n = (equality_expression)node;
			{
				relational_expression v=n.getRelational_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				equality_expressionInternal v=n.getEquality_expressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof equality_expressionInternal) {
			equality_expressionInternal n = (equality_expressionInternal)node;
			{
				equality_operator v=n.getEquality_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				equality_expression v=n.getEquality_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof equality_operator1) {
			equality_operator1 n = (equality_operator1)node;
			printToken("==");
			return false;
		}
		if (node instanceof equality_operator2) {
			equality_operator2 n = (equality_operator2)node;
			printToken("!=");
			return false;
		}
		if (node instanceof relational_expression) {
			relational_expression n = (relational_expression)node;
			{
				shift_expression v=n.getShift_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				relational_expressionInternal v=n.getRelational_expressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof relational_expressionInternal1) {
			relational_expressionInternal1 n = (relational_expressionInternal1)node;
			{
				relational_operator v=n.getRelational_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				relational_expression v=n.getRelational_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof relational_expressionInternal2) {
			relational_expressionInternal2 n = (relational_expressionInternal2)node;
			{
				relational_operator2I v=n.getRelational_operator2I();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof relational_operator1) {
			relational_operator1 n = (relational_operator1)node;
			printToken("<");
			return false;
		}
		if (node instanceof relational_operator2) {
			relational_operator2 n = (relational_operator2)node;
			printToken(">");
			return false;
		}
		if (node instanceof relational_operator3) {
			relational_operator3 n = (relational_operator3)node;
			printToken("<=");
			return false;
		}
		if (node instanceof relational_operator4) {
			relational_operator4 n = (relational_operator4)node;
			printToken(">=");
			return false;
		}
		if (node instanceof relational_operator2I1) {
			relational_operator2I1 n = (relational_operator2I1)node;
			printToken("is");
			return false;
		}
		if (node instanceof relational_operator2I2) {
			relational_operator2I2 n = (relational_operator2I2)node;
			printToken("as");
			return false;
		}
		if (node instanceof shift_expression) {
			shift_expression n = (shift_expression)node;
			{
				additive_expression v=n.getAdditive_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				shift_expressionInternal v=n.getShift_expressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof shift_expressionInternal) {
			shift_expressionInternal n = (shift_expressionInternal)node;
			{
				shift_operator v=n.getShift_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				shift_expression v=n.getShift_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof shift_operator1) {
			shift_operator1 n = (shift_operator1)node;
			printToken("<<");
			return false;
		}
		if (node instanceof shift_operator2) {
			shift_operator2 n = (shift_operator2)node;
			printToken(">>");
			return false;
		}
		if (node instanceof additive_expression) {
			additive_expression n = (additive_expression)node;
			{
				multiplicative_expression v=n.getMultiplicative_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				additive_expressionInternal v=n.getAdditive_expressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof additive_expressionInternal) {
			additive_expressionInternal n = (additive_expressionInternal)node;
			{
				additive_operator v=n.getAdditive_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				additive_expression v=n.getAdditive_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof additive_operator1) {
			additive_operator1 n = (additive_operator1)node;
			printToken("+");
			return false;
		}
		if (node instanceof additive_operator2) {
			additive_operator2 n = (additive_operator2)node;
			printToken("-");
			return false;
		}
		if (node instanceof multiplicative_expression) {
			multiplicative_expression n = (multiplicative_expression)node;
			{
				unary_expression v=n.getUnary_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				multiplicative_expressionInternal v=n.getMultiplicative_expressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof multiplicative_expressionInternal) {
			multiplicative_expressionInternal n = (multiplicative_expressionInternal)node;
			{
				multiplicative_operator v=n.getMultiplicative_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				multiplicative_expression v=n.getMultiplicative_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof multiplicative_operator1) {
			multiplicative_operator1 n = (multiplicative_operator1)node;
			printToken("*");
			return false;
		}
		if (node instanceof multiplicative_operator2) {
			multiplicative_operator2 n = (multiplicative_operator2)node;
			printToken("/");
			return false;
		}
		if (node instanceof multiplicative_operator3) {
			multiplicative_operator3 n = (multiplicative_operator3)node;
			printToken("%");
			return false;
		}
		if (node instanceof unary_expression1) {
			unary_expression1 n = (unary_expression1)node;
			{
				unary_operator v=n.getUnary_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				unary_expression v=n.getUnary_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof unary_expression2) {
			unary_expression2 n = (unary_expression2)node;
			{
				cast_expression v=n.getCast_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof unary_expression3) {
			unary_expression3 n = (unary_expression3)node;
			{
				primary_expression v=n.getPrimary_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof unary_operator1) {
			unary_operator1 n = (unary_operator1)node;
			printToken("+");
			return false;
		}
		if (node instanceof unary_operator2) {
			unary_operator2 n = (unary_operator2)node;
			printToken("-");
			return false;
		}
		if (node instanceof unary_operator3) {
			unary_operator3 n = (unary_operator3)node;
			printToken("!");
			return false;
		}
		if (node instanceof unary_operator4) {
			unary_operator4 n = (unary_operator4)node;
			printToken("~");
			return false;
		}
		if (node instanceof unary_operator5) {
			unary_operator5 n = (unary_operator5)node;
			printToken("*");
			return false;
		}
		if (node instanceof unary_operator6) {
			unary_operator6 n = (unary_operator6)node;
			printToken("++");
			return false;
		}
		if (node instanceof unary_operator7) {
			unary_operator7 n = (unary_operator7)node;
			printToken("--");
			return false;
		}
		if (node instanceof cast_expression) {
			cast_expression n = (cast_expression)node;
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				unary_expression v=n.getUnary_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression1) {
			primary_expression1 n = (primary_expression1)node;
			{
				primary_expression_start v=n.getPrimary_expression_start();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				primary_expression_postfix v=n.getPrimary_expression_postfix();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression2) {
			primary_expression2 n = (primary_expression2)node;
			{
				creation_expression v=n.getCreation_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start1) {
			primary_expression_start1 n = (primary_expression_start1)node;
			{
				literal v=n.getLiteral();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start2) {
			primary_expression_start2 n = (primary_expression_start2)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start3) {
			primary_expression_start3 n = (primary_expression_start3)node;
			{
				parenthesized_expression v=n.getParenthesized_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start4) {
			primary_expression_start4 n = (primary_expression_start4)node;
			{
				predefined_type v=n.getPredefined_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				member_access v=n.getMember_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start5) {
			primary_expression_start5 n = (primary_expression_start5)node;
			{
				this_access v=n.getThis_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start6) {
			primary_expression_start6 n = (primary_expression_start6)node;
			{
				base_access v=n.getBase_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start7) {
			primary_expression_start7 n = (primary_expression_start7)node;
			{
				typeof_expression v=n.getTypeof_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start8) {
			primary_expression_start8 n = (primary_expression_start8)node;
			{
				sizeof_expression v=n.getSizeof_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start9) {
			primary_expression_start9 n = (primary_expression_start9)node;
			{
				checked_expression v=n.getChecked_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_start10) {
			primary_expression_start10 n = (primary_expression_start10)node;
			{
				unchecked_expression v=n.getUnchecked_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_postfix) {
			primary_expression_postfix n = (primary_expression_postfix)node;
			for (primary_expression_postfixInternal v : n.getPrimary_expression_postfixInternal()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof primary_expression_postfixInternal1) {
			primary_expression_postfixInternal1 n = (primary_expression_postfixInternal1)node;
			{
				member_access v=n.getMember_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_postfixInternal2) {
			primary_expression_postfixInternal2 n = (primary_expression_postfixInternal2)node;
			{
				invocation_expression v=n.getInvocation_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_postfixInternal3) {
			primary_expression_postfixInternal3 n = (primary_expression_postfixInternal3)node;
			{
				element_access v=n.getElement_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_postfixInternal4) {
			primary_expression_postfixInternal4 n = (primary_expression_postfixInternal4)node;
			{
				post_increment_expression v=n.getPost_increment_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_postfixInternal5) {
			primary_expression_postfixInternal5 n = (primary_expression_postfixInternal5)node;
			{
				post_decrement_expression v=n.getPost_decrement_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof primary_expression_postfixInternal6) {
			primary_expression_postfixInternal6 n = (primary_expression_postfixInternal6)node;
			{
				pointer_member_access v=n.getPointer_member_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof array_creation_postfix_expression) {
			array_creation_postfix_expression n = (array_creation_postfix_expression)node;
			for (array_creation_postfix_expressionInternal v : n.getArray_creation_postfix_expressionInternal()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof array_creation_postfix_expressionInternal1) {
			array_creation_postfix_expressionInternal1 n = (array_creation_postfix_expressionInternal1)node;
			{
				member_access v=n.getMember_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof array_creation_postfix_expressionInternal2) {
			array_creation_postfix_expressionInternal2 n = (array_creation_postfix_expressionInternal2)node;
			{
				invocation_expression v=n.getInvocation_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof array_creation_postfix_expressionInternal3) {
			array_creation_postfix_expressionInternal3 n = (array_creation_postfix_expressionInternal3)node;
			{
				post_increment_expression v=n.getPost_increment_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof array_creation_postfix_expressionInternal4) {
			array_creation_postfix_expressionInternal4 n = (array_creation_postfix_expressionInternal4)node;
			{
				post_decrement_expression v=n.getPost_decrement_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof array_creation_postfix_expressionInternal5) {
			array_creation_postfix_expressionInternal5 n = (array_creation_postfix_expressionInternal5)node;
			{
				pointer_member_access v=n.getPointer_member_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof creation_expression) {
			creation_expression n = (creation_expression)node;
			printToken("new");
			{
				non_array_type v=n.getNon_array_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				creation_expressionPostFix v=n.getCreation_expressionPostFix();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof creation_expressionPostFix1) {
			creation_expressionPostFix1 n = (creation_expressionPostFix1)node;
			printToken("(");
			{
				argument_list v=n.getArgument_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				primary_expression_postfix v=n.getPrimary_expression_postfix();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof creation_expressionPostFix2) {
			creation_expressionPostFix2 n = (creation_expressionPostFix2)node;
			printToken("[");
			{
				expression_list v=n.getExpression_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			{
				rank_specifiers v=n.getRank_specifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				array_initializer v=n.getArray_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				array_creation_postfix_expression v=n.getArray_creation_postfix_expression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof creation_expressionPostFix3) {
			creation_expressionPostFix3 n = (creation_expressionPostFix3)node;
			{
				rank_specifiers v=n.getRank_specifiers1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				array_initializer v=n.getArray_initializer1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				array_creation_postfix_expression v=n.getArray_creation_postfix_expression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof parenthesized_expression) {
			parenthesized_expression n = (parenthesized_expression)node;
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof member_access) {
			member_access n = (member_access)node;
			printToken(".");
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof invocation_expression) {
			invocation_expression n = (invocation_expression)node;
			printToken("(");
			{
				argument_list v=n.getArgument_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof argument_list) {
			argument_list n = (argument_list)node;
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
		if (node instanceof argument) {
			argument n = (argument)node;
			{
				argumentPrefix v=n.getArgumentPrefix();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof argumentPrefix1) {
			argumentPrefix1 n = (argumentPrefix1)node;
			printToken("ref");
			return false;
		}
		if (node instanceof argumentPrefix2) {
			argumentPrefix2 n = (argumentPrefix2)node;
			printToken("out");
			return false;
		}
		if (node instanceof element_access) {
			element_access n = (element_access)node;
			printToken("[");
			{
				argument_list v=n.getArgument_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof expression_list) {
			expression_list n = (expression_list)node;
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (expression_listList v : n.getExpression_listList()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof expression_listList) {
			expression_listList n = (expression_listList)node;
			printToken(",");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof this_access) {
			this_access n = (this_access)node;
			printToken("this");
			return false;
		}
		if (node instanceof base_access1) {
			base_access1 n = (base_access1)node;
			printToken("base");
			{
				member_access v=n.getMember_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof base_access2) {
			base_access2 n = (base_access2)node;
			printToken("base");
			{
				element_access v=n.getElement_access();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof post_increment_expression) {
			post_increment_expression n = (post_increment_expression)node;
			printToken("++");
			return false;
		}
		if (node instanceof post_decrement_expression) {
			post_decrement_expression n = (post_decrement_expression)node;
			printToken("--");
			return false;
		}
		if (node instanceof typeof_expression) {
			typeof_expression n = (typeof_expression)node;
			printToken("typeof");
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof checked_expression) {
			checked_expression n = (checked_expression)node;
			printToken("checked");
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof unchecked_expression) {
			unchecked_expression n = (unchecked_expression)node;
			printToken("unchecked");
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof statement1) {
			statement1 n = (statement1)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof statement2) {
			statement2 n = (statement2)node;
			{
				local_variable_declaration v=n.getLocal_variable_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof statement3) {
			statement3 n = (statement3)node;
			{
				local_constant_declaration v=n.getLocal_constant_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof statement4) {
			statement4 n = (statement4)node;
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_variable_declaration) {
			local_variable_declaration n = (local_variable_declaration)node;
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				local_variable_declarators v=n.getLocal_variable_declarators();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_variable_declarators) {
			local_variable_declarators n = (local_variable_declarators)node;
			{
				local_variable_declarator v=n.getLocal_variable_declarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				local_variable_declarators v=n.getLocal_variable_declarators();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_variable_declarator) {
			local_variable_declarator n = (local_variable_declarator)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				local_variable_assignment v=n.getLocal_variable_assignment();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_variable_assignment) {
			local_variable_assignment n = (local_variable_assignment)node;
			printToken("=");
			{
				local_variable_initializer v=n.getLocal_variable_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_variable_initializer1) {
			local_variable_initializer1 n = (local_variable_initializer1)node;
			{
				array_initializer v=n.getArray_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_variable_initializer2) {
			local_variable_initializer2 n = (local_variable_initializer2)node;
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_constant_declaration) {
			local_constant_declaration n = (local_constant_declaration)node;
			printToken("const");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				local_constant_declarators v=n.getLocal_constant_declarators();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_constant_declarators) {
			local_constant_declarators n = (local_constant_declarators)node;
			{
				local_constant_declarator v=n.getLocal_constant_declarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				local_constant_declarators v=n.getLocal_constant_declarators();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof local_constant_declarator) {
			local_constant_declarator n = (local_constant_declarator)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				local_variable_initializer v=n.getLocal_variable_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement1) {
			embedded_statement1 n = (embedded_statement1)node;
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement2) {
			embedded_statement2 n = (embedded_statement2)node;
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof embedded_statement3) {
			embedded_statement3 n = (embedded_statement3)node;
			{
				selection_statement v=n.getSelection_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement4) {
			embedded_statement4 n = (embedded_statement4)node;
			{
				iteration_statement v=n.getIteration_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement5) {
			embedded_statement5 n = (embedded_statement5)node;
			{
				jump_statement v=n.getJump_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement6) {
			embedded_statement6 n = (embedded_statement6)node;
			{
				try_statement v=n.getTry_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement7) {
			embedded_statement7 n = (embedded_statement7)node;
			{
				checked_statement v=n.getChecked_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement8) {
			embedded_statement8 n = (embedded_statement8)node;
			{
				unchecked_statement v=n.getUnchecked_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement9) {
			embedded_statement9 n = (embedded_statement9)node;
			{
				lock_statement v=n.getLock_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement10) {
			embedded_statement10 n = (embedded_statement10)node;
			{
				using_statement v=n.getUsing_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement11) {
			embedded_statement11 n = (embedded_statement11)node;
			{
				unsafe_statement v=n.getUnsafe_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement12) {
			embedded_statement12 n = (embedded_statement12)node;
			{
				fixed_statement v=n.getFixed_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof embedded_statement13) {
			embedded_statement13 n = (embedded_statement13)node;
			{
				expression_statement v=n.getExpression_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof block) {
			block n = (block)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (statement v : n.getStatement()) {
				v.accept(this);
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			hintNewLine();
			return false;
		}
		if (node instanceof statement_list) {
			statement_list n = (statement_list)node;
			for (statement v : n.getStatement()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof expression_statement) {
			expression_statement n = (expression_statement)node;
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof selection_statement1) {
			selection_statement1 n = (selection_statement1)node;
			{
				if_statement v=n.getIf_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof selection_statement2) {
			selection_statement2 n = (selection_statement2)node;
			{
				switch_statement v=n.getSwitch_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof if_statement) {
			if_statement n = (if_statement)node;
			printToken("if");
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			hintIncIndent();
			hintNewLine();
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			hintDecIndent();
			hintNewLine();
			{
				embedded_statement v=n.getEmbedded_statement1();
				if (v!=null) {
					printToken("else");
					hintIncIndent();
					hintNewLine();
					v.accept(this);
					hintDecIndent();
					hintNewLine();
				}
			}
			return false;
		}
		if (node instanceof switch_statement) {
			switch_statement n = (switch_statement)node;
			printToken("switch");
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			hintNewLine();
			{
				switch_block v=n.getSwitch_block();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof switch_block) {
			switch_block n = (switch_block)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (switch_section v : n.getSwitch_section()) {
				v.accept(this);
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			hintNewLine();
			return false;
		}
		if (node instanceof switch_section) {
			switch_section n = (switch_section)node;
			for (switch_label v : n.getSwitch_label()) {
				v.accept(this);
			}
			{
				statement_list v=n.getStatement_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof switch_label1) {
			switch_label1 n = (switch_label1)node;
			printToken("case");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			return false;
		}
		if (node instanceof switch_label2) {
			switch_label2 n = (switch_label2)node;
			printToken("default");
			printToken(":");
			return false;
		}
		if (node instanceof iteration_statement1) {
			iteration_statement1 n = (iteration_statement1)node;
			{
				while_statement v=n.getWhile_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof iteration_statement2) {
			iteration_statement2 n = (iteration_statement2)node;
			{
				do_statement v=n.getDo_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof iteration_statement3) {
			iteration_statement3 n = (iteration_statement3)node;
			{
				for_statement v=n.getFor_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof iteration_statement4) {
			iteration_statement4 n = (iteration_statement4)node;
			{
				foreach_statement v=n.getForeach_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof while_statement) {
			while_statement n = (while_statement)node;
			printToken("while");
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof do_statement) {
			do_statement n = (do_statement)node;
			printToken("do");
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("while");
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof for_statement) {
			for_statement n = (for_statement)node;
			printToken("for");
			printToken("(");
			{
				for_initializer v=n.getFor_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			{
				expression_list v=n.getExpression_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof for_initializer1) {
			for_initializer1 n = (for_initializer1)node;
			{
				local_variable_declaration v=n.getLocal_variable_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof for_initializer2) {
			for_initializer2 n = (for_initializer2)node;
			{
				expression_list v=n.getExpression_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof foreach_statement) {
			foreach_statement n = (foreach_statement)node;
			printToken("foreach");
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("in");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof jump_statement1) {
			jump_statement1 n = (jump_statement1)node;
			printToken("break");
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof jump_statement2) {
			jump_statement2 n = (jump_statement2)node;
			printToken("continue");
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof jump_statement3) {
			jump_statement3 n = (jump_statement3)node;
			{
				goto_statement v=n.getGoto_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof jump_statement4) {
			jump_statement4 n = (jump_statement4)node;
			{
				return_statement v=n.getReturn_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof jump_statement5) {
			jump_statement5 n = (jump_statement5)node;
			{
				throw_statement v=n.getThrow_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof goto_statement) {
			goto_statement n = (goto_statement)node;
			printToken("goto");
			{
				goto_statementEnd v=n.getGoto_statementEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof goto_statementEnd1) {
			goto_statementEnd1 n = (goto_statementEnd1)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof goto_statementEnd2) {
			goto_statementEnd2 n = (goto_statementEnd2)node;
			printToken("case");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof goto_statementEnd3) {
			goto_statementEnd3 n = (goto_statementEnd3)node;
			printToken("default");
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof return_statement) {
			return_statement n = (return_statement)node;
			printToken("return");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof throw_statement) {
			throw_statement n = (throw_statement)node;
			printToken("throw");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof try_statement) {
			try_statement n = (try_statement)node;
			printToken("try");
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				try_statement_clauses v=n.getTry_statement_clauses();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof try_statement_clauses1) {
			try_statement_clauses1 n = (try_statement_clauses1)node;
			{
				catch_clauses v=n.getCatch_clauses();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				finally_clause v=n.getFinally_clause();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof try_statement_clauses2) {
			try_statement_clauses2 n = (try_statement_clauses2)node;
			{
				finally_clause v=n.getFinally_clause1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof catch_clauses) {
			catch_clauses n = (catch_clauses)node;
			for (catch_clause v : n.getCatch_clause()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof catch_clause) {
			catch_clause n = (catch_clause)node;
			printToken("catch");
			{
				catch_clauseEnd v=n.getCatch_clauseEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof catch_clauseEnd1) {
			catch_clauseEnd1 n = (catch_clauseEnd1)node;
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof catch_clauseEnd2) {
			catch_clauseEnd2 n = (catch_clauseEnd2)node;
			{
				block v=n.getBlock1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof finally_clause) {
			finally_clause n = (finally_clause)node;
			printToken("finally");
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof checked_statement) {
			checked_statement n = (checked_statement)node;
			printToken("checked");
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof unchecked_statement) {
			unchecked_statement n = (unchecked_statement)node;
			printToken("unchecked");
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof lock_statement) {
			lock_statement n = (lock_statement)node;
			printToken("lock");
			printToken("(");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof using_statement) {
			using_statement n = (using_statement)node;
			printToken("using");
			printToken("(");
			{
				resource_acquisition v=n.getResource_acquisition();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof resource_acquisition1) {
			resource_acquisition1 n = (resource_acquisition1)node;
			{
				local_variable_declaration v=n.getLocal_variable_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof resource_acquisition2) {
			resource_acquisition2 n = (resource_acquisition2)node;
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof compilation_unitEnd) {
			compilation_unitEnd n = (compilation_unitEnd)node;
			{
				namespace_member_declaration_no_attr v=n.getNamespace_member_declaration_no_attr();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (namespace_member_declaration v : n.getNamespace_member_declaration()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof namespace_declaration) {
			namespace_declaration n = (namespace_declaration)node;
			printToken("namespace");
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				namespace_body v=n.getNamespace_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof namespace_body) {
			namespace_body n = (namespace_body)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (using_directive v : n.getUsing_directive()) {
				v.accept(this);
			}
			for (namespace_member_declaration v : n.getNamespace_member_declaration()) {
				v.accept(this);
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			{
				ASTTextNode v=n.getText210();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			hintNewLine();
			return false;
		}
		if (node instanceof using_directive) {
			using_directive n = (using_directive)node;
			printToken("using");
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				using_directiveEnd v=n.getUsing_directiveEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof using_directiveEnd1) {
			using_directiveEnd1 n = (using_directiveEnd1)node;
			printToken("=");
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof using_directiveEnd2) {
			using_directiveEnd2 n = (using_directiveEnd2)node;
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof namespace_member_declaration_no_attr1) {
			namespace_member_declaration_no_attr1 n = (namespace_member_declaration_no_attr1)node;
			{
				namespace_declaration v=n.getNamespace_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof namespace_member_declaration_no_attr2) {
			namespace_member_declaration_no_attr2 n = (namespace_member_declaration_no_attr2)node;
			{
				type_modifiers v=n.getType_modifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				type_declaration v=n.getType_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof namespace_member_declaration1) {
			namespace_member_declaration1 n = (namespace_member_declaration1)node;
			{
				namespace_declaration v=n.getNamespace_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof namespace_member_declaration2) {
			namespace_member_declaration2 n = (namespace_member_declaration2)node;
			{
				attributes v=n.getAttributes();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				type_modifiers v=n.getType_modifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				type_declaration v=n.getType_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_declaration1) {
			type_declaration1 n = (type_declaration1)node;
			{
				class_declaration v=n.getClass_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_declaration2) {
			type_declaration2 n = (type_declaration2)node;
			{
				struct_declaration v=n.getStruct_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_declaration3) {
			type_declaration3 n = (type_declaration3)node;
			{
				interface_declaration v=n.getInterface_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_declaration4) {
			type_declaration4 n = (type_declaration4)node;
			{
				enum_declaration v=n.getEnum_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_declaration5) {
			type_declaration5 n = (type_declaration5)node;
			{
				delegate_declaration v=n.getDelegate_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_modifiers) {
			type_modifiers n = (type_modifiers)node;
			for (type_modifier v : n.getType_modifier()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof type_modifier1) {
			type_modifier1 n = (type_modifier1)node;
			printToken("new");
			return false;
		}
		if (node instanceof type_modifier2) {
			type_modifier2 n = (type_modifier2)node;
			printToken("public");
			return false;
		}
		if (node instanceof type_modifier3) {
			type_modifier3 n = (type_modifier3)node;
			printToken("protected");
			return false;
		}
		if (node instanceof type_modifier4) {
			type_modifier4 n = (type_modifier4)node;
			printToken("internal");
			return false;
		}
		if (node instanceof type_modifier5) {
			type_modifier5 n = (type_modifier5)node;
			printToken("private");
			return false;
		}
		if (node instanceof type_modifier6) {
			type_modifier6 n = (type_modifier6)node;
			printToken("abstract");
			return false;
		}
		if (node instanceof type_modifier7) {
			type_modifier7 n = (type_modifier7)node;
			printToken("sealed");
			return false;
		}
		if (node instanceof type_modifier8) {
			type_modifier8 n = (type_modifier8)node;
			printToken("unsafe");
			return false;
		}
		if (node instanceof class_declaration) {
			class_declaration n = (class_declaration)node;
			printToken("class");
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				class_base v=n.getClass_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				class_body v=n.getClass_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText220();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_base) {
			class_base n = (class_base)node;
			printToken(":");
			{
				class_type v=n.getClass_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (type_name v : n.getType_name()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof interface_type_list) {
			interface_type_list n = (interface_type_list)node;
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (type_name v : n.getType_name1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof class_body) {
			class_body n = (class_body)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (class_member_declaration v : n.getClass_member_declaration()) {
				v.accept(this);
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			hintNewLine();
			return false;
		}
		if (node instanceof class_member_declaration) {
			class_member_declaration n = (class_member_declaration)node;
			{
				attributes v=n.getAttributes();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				member_modifiers v=n.getMember_modifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				class_member_declarationEnd v=n.getClass_member_declarationEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_member_declarationEnd1) {
			class_member_declarationEnd1 n = (class_member_declarationEnd1)node;
			{
				constant_declaration v=n.getConstant_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_member_declarationEnd2) {
			class_member_declarationEnd2 n = (class_member_declarationEnd2)node;
			{
				event_declaration v=n.getEvent_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_member_declarationEnd3) {
			class_member_declarationEnd3 n = (class_member_declarationEnd3)node;
			{
				destructor_declaration v=n.getDestructor_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_member_declarationEnd4) {
			class_member_declarationEnd4 n = (class_member_declarationEnd4)node;
			{
				conversion_operator_declaration v=n.getConversion_operator_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_member_declarationEnd5) {
			class_member_declarationEnd5 n = (class_member_declarationEnd5)node;
			{
				type_declaration v=n.getType_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof class_member_declarationEnd6) {
			class_member_declarationEnd6 n = (class_member_declarationEnd6)node;
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				typeEnd v=n.getTypeEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof typeEnd1) {
			typeEnd1 n = (typeEnd1)node;
			{
				constructor_declaration v=n.getConstructor_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof typeEnd2) {
			typeEnd2 n = (typeEnd2)node;
			{
				indexer_declaration_no_interface v=n.getIndexer_declaration_no_interface();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof typeEnd3) {
			typeEnd3 n = (typeEnd3)node;
			{
				operator_declaration v=n.getOperator_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof typeEnd4) {
			typeEnd4 n = (typeEnd4)node;
			{
				indexer_declaration_interface v=n.getIndexer_declaration_interface();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof typeEnd5) {
			typeEnd5 n = (typeEnd5)node;
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				type_nameEnd v=n.getType_nameEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_nameEnd1) {
			type_nameEnd1 n = (type_nameEnd1)node;
			{
				method_declaration v=n.getMethod_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_nameEnd2) {
			type_nameEnd2 n = (type_nameEnd2)node;
			{
				property_declaration v=n.getProperty_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof type_nameEnd3) {
			type_nameEnd3 n = (type_nameEnd3)node;
			{
				field_declaration v=n.getField_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof member_modifiers) {
			member_modifiers n = (member_modifiers)node;
			for (member_modifier v : n.getMember_modifier()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof member_modifier1) {
			member_modifier1 n = (member_modifier1)node;
			printToken("new");
			return false;
		}
		if (node instanceof member_modifier2) {
			member_modifier2 n = (member_modifier2)node;
			printToken("public");
			return false;
		}
		if (node instanceof member_modifier3) {
			member_modifier3 n = (member_modifier3)node;
			printToken("protected");
			return false;
		}
		if (node instanceof member_modifier4) {
			member_modifier4 n = (member_modifier4)node;
			printToken("internal");
			return false;
		}
		if (node instanceof member_modifier5) {
			member_modifier5 n = (member_modifier5)node;
			printToken("private");
			return false;
		}
		if (node instanceof member_modifier6) {
			member_modifier6 n = (member_modifier6)node;
			printToken("static");
			return false;
		}
		if (node instanceof member_modifier7) {
			member_modifier7 n = (member_modifier7)node;
			printToken("virtual");
			return false;
		}
		if (node instanceof member_modifier8) {
			member_modifier8 n = (member_modifier8)node;
			printToken("sealed");
			return false;
		}
		if (node instanceof member_modifier9) {
			member_modifier9 n = (member_modifier9)node;
			printToken("override");
			return false;
		}
		if (node instanceof member_modifier10) {
			member_modifier10 n = (member_modifier10)node;
			printToken("abstract");
			return false;
		}
		if (node instanceof member_modifier11) {
			member_modifier11 n = (member_modifier11)node;
			printToken("extern");
			return false;
		}
		if (node instanceof member_modifier12) {
			member_modifier12 n = (member_modifier12)node;
			printToken("readonly");
			return false;
		}
		if (node instanceof member_modifier13) {
			member_modifier13 n = (member_modifier13)node;
			printToken("unsafe");
			return false;
		}
		if (node instanceof constant_declaration) {
			constant_declaration n = (constant_declaration)node;
			printToken("const");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				constant_declarators v=n.getConstant_declarators();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof constant_declarators) {
			constant_declarators n = (constant_declarators)node;
			{
				constant_declarator v=n.getConstant_declarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (constant_declarator v : n.getConstant_declarator1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof constant_declarator) {
			constant_declarator n = (constant_declarator)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof field_declaration) {
			field_declaration n = (field_declaration)node;
			{
				field_declaration_start v=n.getField_declaration_start();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				field_declarators v=n.getField_declarators();
				if (v!=null) {
					printToken(",");
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof field_declaration_start) {
			field_declaration_start n = (field_declaration_start)node;
			{
				variable_initializer v=n.getVariable_initializer();
				if (v!=null) {
					printToken("=");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof field_declarators) {
			field_declarators n = (field_declarators)node;
			{
				field_declarator v=n.getField_declarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (field_declarator v : n.getField_declarator1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof field_declarator) {
			field_declarator n = (field_declarator)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				variable_initializer v=n.getVariable_initializer();
				if (v!=null) {
					printToken("=");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof variable_initializer1) {
			variable_initializer1 n = (variable_initializer1)node;
			{
				array_initializer v=n.getArray_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof variable_initializer2) {
			variable_initializer2 n = (variable_initializer2)node;
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof variable_initializer3) {
			variable_initializer3 n = (variable_initializer3)node;
			{
				stackalloc_initializer v=n.getStackalloc_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof method_declaration) {
			method_declaration n = (method_declaration)node;
			printToken("(");
			{
				formal_parameter_list v=n.getFormal_parameter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				body v=n.getBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof body1) {
			body1 n = (body1)node;
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof body2) {
			body2 n = (body2)node;
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof formal_parameter_list) {
			formal_parameter_list n = (formal_parameter_list)node;
			{
				attributes v=n.getAttributes();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				formal_parameter_listEnd v=n.getFormal_parameter_listEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof formal_parameter_listEnd1) {
			formal_parameter_listEnd1 n = (formal_parameter_listEnd1)node;
			{
				fixed_parameter v=n.getFixed_parameter();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (fixed_parameterEnd v : n.getFixed_parameterEnd()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof formal_parameter_listEnd2) {
			formal_parameter_listEnd2 n = (formal_parameter_listEnd2)node;
			{
				parameter_array v=n.getParameter_array();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof formal_parameter_listEndInt1) {
			formal_parameter_listEndInt1 n = (formal_parameter_listEndInt1)node;
			{
				fixed_parameter v=n.getFixed_parameter();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof formal_parameter_listEndInt2) {
			formal_parameter_listEndInt2 n = (formal_parameter_listEndInt2)node;
			{
				parameter_array v=n.getParameter_array();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixed_parameterEnd) {
			fixed_parameterEnd n = (fixed_parameterEnd)node;
			printToken(",");
			{
				attributes v=n.getAttributes();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				formal_parameter_listEndInt v=n.getFormal_parameter_listEndInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixed_parameter) {
			fixed_parameter n = (fixed_parameter)node;
			{
				parameter_modifier v=n.getParameter_modifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof parameter_modifier1) {
			parameter_modifier1 n = (parameter_modifier1)node;
			printToken("ref");
			return false;
		}
		if (node instanceof parameter_modifier2) {
			parameter_modifier2 n = (parameter_modifier2)node;
			printToken("out");
			return false;
		}
		if (node instanceof parameter_array) {
			parameter_array n = (parameter_array)node;
			printToken("params");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof property_declaration) {
			property_declaration n = (property_declaration)node;
			printToken("{");
			{
				accessor_declarations v=n.getAccessor_declarations();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof accessor_declarations) {
			accessor_declarations n = (accessor_declarations)node;
			{
				accessor_declaration v=n.getAccessor_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				accessor_declaration v=n.getAccessor_declaration1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof accessor_declaration) {
			accessor_declaration n = (accessor_declaration)node;
			{
				attributes v=n.getAttributes();
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
				body v=n.getBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof event_declaration) {
			event_declaration n = (event_declaration)node;
			printToken("event");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				event_declarationInt v=n.getEvent_declarationInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof event_declarationInt1) {
			event_declarationInt1 n = (event_declarationInt1)node;
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			{
				event_accessor_declarations v=n.getEvent_accessor_declarations();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof event_declarationInt2) {
			event_declarationInt2 n = (event_declarationInt2)node;
			{
				event_variable_declarator v=n.getEvent_variable_declarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (event_variable_declarator v : n.getEvent_variable_declarator1()) {
				printToken(",");
				v.accept(this);
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof event_variable_declarator) {
			event_variable_declarator n = (event_variable_declarator)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				variable_initializer v=n.getVariable_initializer();
				if (v!=null) {
					printToken("=");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof event_accessor_declarations) {
			event_accessor_declarations n = (event_accessor_declarations)node;
			{
				accessor_declaration v=n.getAccessor_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				accessor_declaration v=n.getAccessor_declaration1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof indexer_declaration_no_interface) {
			indexer_declaration_no_interface n = (indexer_declaration_no_interface)node;
			printToken("this");
			printToken("[");
			{
				formal_parameter_list v=n.getFormal_parameter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			printToken("{");
			{
				accessor_declarations v=n.getAccessor_declarations();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof indexer_declaration_interface) {
			indexer_declaration_interface n = (indexer_declaration_interface)node;
			{
				indexer_base v=n.getIndexer_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("[");
			{
				formal_parameter_list v=n.getFormal_parameter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			printToken("{");
			{
				accessor_declarations v=n.getAccessor_declarations();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof indexer_base) {
			indexer_base n = (indexer_base)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (indexer_baseInt v : n.getIndexer_baseInt()) {
				printToken(".");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof indexer_baseInt1) {
			indexer_baseInt1 n = (indexer_baseInt1)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof indexer_baseInt2) {
			indexer_baseInt2 n = (indexer_baseInt2)node;
			printToken("this");
			return false;
		}
		if (node instanceof operator_declaration1) {
			operator_declaration1 n = (operator_declaration1)node;
			printToken("operator");
			{
				unary_operator_declaration v=n.getUnary_operator_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof operator_declaration2) {
			operator_declaration2 n = (operator_declaration2)node;
			printToken("operator");
			{
				binary_operator_declaration v=n.getBinary_operator_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof unary_operator_declaration) {
			unary_operator_declaration n = (unary_operator_declaration)node;
			{
				overloadable_unary_operator v=n.getOverloadable_unary_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				body v=n.getBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof overloadable_unary_operator1) {
			overloadable_unary_operator1 n = (overloadable_unary_operator1)node;
			printToken("+");
			return false;
		}
		if (node instanceof overloadable_unary_operator2) {
			overloadable_unary_operator2 n = (overloadable_unary_operator2)node;
			printToken("-");
			return false;
		}
		if (node instanceof overloadable_unary_operator3) {
			overloadable_unary_operator3 n = (overloadable_unary_operator3)node;
			printToken("!");
			return false;
		}
		if (node instanceof overloadable_unary_operator4) {
			overloadable_unary_operator4 n = (overloadable_unary_operator4)node;
			printToken("~");
			return false;
		}
		if (node instanceof overloadable_unary_operator5) {
			overloadable_unary_operator5 n = (overloadable_unary_operator5)node;
			printToken("++");
			return false;
		}
		if (node instanceof overloadable_unary_operator6) {
			overloadable_unary_operator6 n = (overloadable_unary_operator6)node;
			printToken("--");
			return false;
		}
		if (node instanceof overloadable_unary_operator7) {
			overloadable_unary_operator7 n = (overloadable_unary_operator7)node;
			printToken("true");
			return false;
		}
		if (node instanceof overloadable_unary_operator8) {
			overloadable_unary_operator8 n = (overloadable_unary_operator8)node;
			printToken("false");
			return false;
		}
		if (node instanceof binary_operator_declaration) {
			binary_operator_declaration n = (binary_operator_declaration)node;
			{
				overloadable_binary_operator v=n.getOverloadable_binary_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(",");
			{
				type v=n.getType1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				body v=n.getBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof overloadable_binary_operator1) {
			overloadable_binary_operator1 n = (overloadable_binary_operator1)node;
			printToken("+");
			return false;
		}
		if (node instanceof overloadable_binary_operator2) {
			overloadable_binary_operator2 n = (overloadable_binary_operator2)node;
			printToken("-");
			return false;
		}
		if (node instanceof overloadable_binary_operator3) {
			overloadable_binary_operator3 n = (overloadable_binary_operator3)node;
			printToken("*");
			return false;
		}
		if (node instanceof overloadable_binary_operator4) {
			overloadable_binary_operator4 n = (overloadable_binary_operator4)node;
			printToken("/");
			return false;
		}
		if (node instanceof overloadable_binary_operator5) {
			overloadable_binary_operator5 n = (overloadable_binary_operator5)node;
			printToken("%");
			return false;
		}
		if (node instanceof overloadable_binary_operator6) {
			overloadable_binary_operator6 n = (overloadable_binary_operator6)node;
			printToken("&");
			return false;
		}
		if (node instanceof overloadable_binary_operator7) {
			overloadable_binary_operator7 n = (overloadable_binary_operator7)node;
			printToken("|");
			return false;
		}
		if (node instanceof overloadable_binary_operator8) {
			overloadable_binary_operator8 n = (overloadable_binary_operator8)node;
			printToken("^");
			return false;
		}
		if (node instanceof overloadable_binary_operator9) {
			overloadable_binary_operator9 n = (overloadable_binary_operator9)node;
			printToken("<<");
			return false;
		}
		if (node instanceof overloadable_binary_operator10) {
			overloadable_binary_operator10 n = (overloadable_binary_operator10)node;
			printToken(">>");
			return false;
		}
		if (node instanceof overloadable_binary_operator11) {
			overloadable_binary_operator11 n = (overloadable_binary_operator11)node;
			printToken("==");
			return false;
		}
		if (node instanceof overloadable_binary_operator12) {
			overloadable_binary_operator12 n = (overloadable_binary_operator12)node;
			printToken("!=");
			return false;
		}
		if (node instanceof overloadable_binary_operator13) {
			overloadable_binary_operator13 n = (overloadable_binary_operator13)node;
			printToken(">");
			return false;
		}
		if (node instanceof overloadable_binary_operator14) {
			overloadable_binary_operator14 n = (overloadable_binary_operator14)node;
			printToken("<");
			return false;
		}
		if (node instanceof overloadable_binary_operator15) {
			overloadable_binary_operator15 n = (overloadable_binary_operator15)node;
			printToken(">=");
			return false;
		}
		if (node instanceof overloadable_binary_operator16) {
			overloadable_binary_operator16 n = (overloadable_binary_operator16)node;
			printToken("<=");
			return false;
		}
		if (node instanceof conversion_operator_declaration) {
			conversion_operator_declaration n = (conversion_operator_declaration)node;
			{
				conversion_operator v=n.getConversion_operator();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("operator");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				type v=n.getType1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				body v=n.getBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof conversion_operator1) {
			conversion_operator1 n = (conversion_operator1)node;
			printToken("implicit");
			return false;
		}
		if (node instanceof conversion_operator2) {
			conversion_operator2 n = (conversion_operator2)node;
			printToken("explicit");
			return false;
		}
		if (node instanceof constructor_declaration) {
			constructor_declaration n = (constructor_declaration)node;
			printToken("(");
			{
				formal_parameter_list v=n.getFormal_parameter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				constructor_initializer v=n.getConstructor_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				body v=n.getBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof constructor_initializer) {
			constructor_initializer n = (constructor_initializer)node;
			printToken(":");
			{
				constructor_initializerInt v=n.getConstructor_initializerInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				argument_list v=n.getArgument_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof constructor_initializerInt1) {
			constructor_initializerInt1 n = (constructor_initializerInt1)node;
			printToken("base");
			return false;
		}
		if (node instanceof constructor_initializerInt2) {
			constructor_initializerInt2 n = (constructor_initializerInt2)node;
			printToken("this");
			return false;
		}
		if (node instanceof destructor_declaration) {
			destructor_declaration n = (destructor_declaration)node;
			printToken("~");
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			printToken(")");
			{
				body v=n.getBody();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof struct_declaration) {
			struct_declaration n = (struct_declaration)node;
			printToken("struct");
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				base_interfaces v=n.getBase_interfaces();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				class_body v=n.getClass_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText266();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof base_interfaces) {
			base_interfaces n = (base_interfaces)node;
			printToken(":");
			{
				interface_type_list v=n.getInterface_type_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof array_initializer) {
			array_initializer n = (array_initializer)node;
			printToken("{");
			{
				rest_of_array_initializer v=n.getRest_of_array_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof rest_of_array_initializer1) {
			rest_of_array_initializer1 n = (rest_of_array_initializer1)node;
			printToken("}");
			return false;
		}
		if (node instanceof rest_of_array_initializer2) {
			rest_of_array_initializer2 n = (rest_of_array_initializer2)node;
			{
				variable_initializer v=n.getVariable_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				rest_of_array_initializerEnd v=n.getRest_of_array_initializerEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof rest_of_array_initializerEnd1) {
			rest_of_array_initializerEnd1 n = (rest_of_array_initializerEnd1)node;
			printToken("}");
			return false;
		}
		if (node instanceof rest_of_array_initializerEnd2) {
			rest_of_array_initializerEnd2 n = (rest_of_array_initializerEnd2)node;
			printToken(",");
			{
				rest_of_array_initializer v=n.getRest_of_array_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_declaration) {
			interface_declaration n = (interface_declaration)node;
			printToken("interface");
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				base_interfaces v=n.getBase_interfaces();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				interface_body v=n.getInterface_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText269();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_body) {
			interface_body n = (interface_body)node;
			printToken("{");
			hintIncIndent();
			hintNewLine();
			for (interface_member_declaration v : n.getInterface_member_declaration()) {
				v.accept(this);
			}
			hintDecIndent();
			hintNewLine();
			printToken("}");
			hintNewLine();
			return false;
		}
		if (node instanceof interface_member_declaration) {
			interface_member_declaration n = (interface_member_declaration)node;
			{
				attributes v=n.getAttributes();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (interface_member_modifier v : n.getInterface_member_modifier()) {
				v.accept(this);
			}
			{
				interface_member_declarationEnd v=n.getInterface_member_declarationEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_member_declarationEnd1) {
			interface_member_declarationEnd1 n = (interface_member_declarationEnd1)node;
			{
				interface_event_declaration v=n.getInterface_event_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_member_declarationEnd2) {
			interface_member_declarationEnd2 n = (interface_member_declarationEnd2)node;
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				interface_member_declarationEndType v=n.getInterface_member_declarationEndType();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_member_declarationEndType1) {
			interface_member_declarationEndType1 n = (interface_member_declarationEndType1)node;
			{
				interface_indexer_declaration v=n.getInterface_indexer_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_member_declarationEndType2) {
			interface_member_declarationEndType2 n = (interface_member_declarationEndType2)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				interface_member_declarationEndTypeIdentifier v=n.getInterface_member_declarationEndTypeIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_member_declarationEndTypeIdentifier1) {
			interface_member_declarationEndTypeIdentifier1 n = (interface_member_declarationEndTypeIdentifier1)node;
			{
				interface_method_declaration v=n.getInterface_method_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_member_declarationEndTypeIdentifier2) {
			interface_member_declarationEndTypeIdentifier2 n = (interface_member_declarationEndTypeIdentifier2)node;
			{
				interface_property_declaration v=n.getInterface_property_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_member_modifier1) {
			interface_member_modifier1 n = (interface_member_modifier1)node;
			printToken("new");
			return false;
		}
		if (node instanceof interface_member_modifier2) {
			interface_member_modifier2 n = (interface_member_modifier2)node;
			printToken("unsafe");
			return false;
		}
		if (node instanceof interface_event_declaration) {
			interface_event_declaration n = (interface_event_declaration)node;
			printToken("event");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof interface_indexer_declaration) {
			interface_indexer_declaration n = (interface_indexer_declaration)node;
			printToken("this");
			printToken("[");
			{
				formal_parameter_list v=n.getFormal_parameter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			printToken("{");
			{
				interface_accessors v=n.getInterface_accessors();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof interface_method_declaration) {
			interface_method_declaration n = (interface_method_declaration)node;
			printToken("(");
			{
				formal_parameter_list v=n.getFormal_parameter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof interface_property_declaration) {
			interface_property_declaration n = (interface_property_declaration)node;
			printToken("{");
			{
				interface_accessors v=n.getInterface_accessors();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof interface_accessors) {
			interface_accessors n = (interface_accessors)node;
			{
				interface_accessor v=n.getInterface_accessor();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				interface_accessor v=n.getInterface_accessor1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof interface_accessor) {
			interface_accessor n = (interface_accessor)node;
			{
				attributes v=n.getAttributes();
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
			hintNewLine();
			return false;
		}
		if (node instanceof enum_declaration) {
			enum_declaration n = (enum_declaration)node;
			printToken("enum");
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				enum_base v=n.getEnum_base();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				enum_body v=n.getEnum_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText272();
				if (v!=null) {
					printToken(";");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof enum_base) {
			enum_base n = (enum_base)node;
			printToken(":");
			{
				integral_type v=n.getIntegral_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof enum_body) {
			enum_body n = (enum_body)node;
			printToken("{");
			{
				rest_of_enum_body v=n.getRest_of_enum_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof rest_of_enum_body1) {
			rest_of_enum_body1 n = (rest_of_enum_body1)node;
			printToken("}");
			return false;
		}
		if (node instanceof rest_of_enum_body2) {
			rest_of_enum_body2 n = (rest_of_enum_body2)node;
			{
				enum_member_declaration v=n.getEnum_member_declaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				rest_of_enum_bodyEnd v=n.getRest_of_enum_bodyEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof rest_of_enum_bodyEnd1) {
			rest_of_enum_bodyEnd1 n = (rest_of_enum_bodyEnd1)node;
			printToken("}");
			return false;
		}
		if (node instanceof rest_of_enum_bodyEnd2) {
			rest_of_enum_bodyEnd2 n = (rest_of_enum_bodyEnd2)node;
			printToken(",");
			{
				rest_of_enum_body v=n.getRest_of_enum_body();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof enum_member_declaration) {
			enum_member_declaration n = (enum_member_declaration)node;
			{
				attributes v=n.getAttributes();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				enum_member_assignment v=n.getEnum_member_assignment();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof enum_member_assignment) {
			enum_member_assignment n = (enum_member_assignment)node;
			printToken("=");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof delegate_declaration) {
			delegate_declaration n = (delegate_declaration)node;
			printToken("delegate");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				formal_parameter_list v=n.getFormal_parameter_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken(";");
			hintNewLine();
			return false;
		}
		if (node instanceof attributes_either) {
			attributes_either n = (attributes_either)node;
			for (attribute_section_start v : n.getAttribute_section_start()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof attribute_section_start1) {
			attribute_section_start1 n = (attribute_section_start1)node;
			printToken("[");
			{
				global_attribute_target_specifier v=n.getGlobal_attribute_target_specifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				attribute_section v=n.getAttribute_section();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof attribute_section_start2) {
			attribute_section_start2 n = (attribute_section_start2)node;
			printToken("[");
			{
				attribute_target_specifier v=n.getAttribute_target_specifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				attribute_section v=n.getAttribute_section1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof global_attribute_target_specifier) {
			global_attribute_target_specifier n = (global_attribute_target_specifier)node;
			{
				global_attribute_target v=n.getGlobal_attribute_target();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			return false;
		}
		if (node instanceof global_attribute_target1) {
			global_attribute_target1 n = (global_attribute_target1)node;
			printToken("assembly");
			return false;
		}
		if (node instanceof global_attribute_target2) {
			global_attribute_target2 n = (global_attribute_target2)node;
			printToken("module");
			return false;
		}
		if (node instanceof attributes) {
			attributes n = (attributes)node;
			for (local_attribute v : n.getLocal_attribute()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof local_attribute) {
			local_attribute n = (local_attribute)node;
			printToken("[");
			{
				attribute_target_specifier v=n.getAttribute_target_specifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				attribute_section v=n.getAttribute_section();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof attribute_section) {
			attribute_section n = (attribute_section)node;
			{
				attribute v=n.getAttribute();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				attribute_sectionEnd v=n.getAttribute_sectionEnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof attribute_sectionEnd1) {
			attribute_sectionEnd1 n = (attribute_sectionEnd1)node;
			printToken("]");
			return false;
		}
		if (node instanceof attribute_sectionEnd2) {
			attribute_sectionEnd2 n = (attribute_sectionEnd2)node;
			printToken(",");
			printToken("]");
			return false;
		}
		if (node instanceof attribute_sectionEnd3) {
			attribute_sectionEnd3 n = (attribute_sectionEnd3)node;
			printToken(",");
			{
				attribute_section v=n.getAttribute_section();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof attribute_target_specifier) {
			attribute_target_specifier n = (attribute_target_specifier)node;
			{
				attribute_target v=n.getAttribute_target();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			return false;
		}
		if (node instanceof attribute_target1) {
			attribute_target1 n = (attribute_target1)node;
			printToken("event");
			return false;
		}
		if (node instanceof attribute_target2) {
			attribute_target2 n = (attribute_target2)node;
			printToken("return");
			return false;
		}
		if (node instanceof attribute_target3) {
			attribute_target3 n = (attribute_target3)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof attribute) {
			attribute n = (attribute)node;
			{
				type_name v=n.getType_name();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				attribute_arguments v=n.getAttribute_arguments();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof attribute_arguments) {
			attribute_arguments n = (attribute_arguments)node;
			printToken("(");
			{
				expression_list v=n.getExpression_list();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof sizeof_expression) {
			sizeof_expression n = (sizeof_expression)node;
			printToken("sizeof");
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof unsafe_statement) {
			unsafe_statement n = (unsafe_statement)node;
			printToken("unsafe");
			{
				block v=n.getBlock();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof pointer_member_access) {
			pointer_member_access n = (pointer_member_access)node;
			printToken("->");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixed_statement) {
			fixed_statement n = (fixed_statement)node;
			printToken("fixed");
			printToken("(");
			{
				type v=n.getType();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				fixed_pointer_declarators v=n.getFixed_pointer_declarators();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				embedded_statement v=n.getEmbedded_statement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixed_pointer_declarators) {
			fixed_pointer_declarators n = (fixed_pointer_declarators)node;
			{
				fixed_pointer_declarator v=n.getFixed_pointer_declarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (fixed_pointer_declarator v : n.getFixed_pointer_declarator1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof fixed_pointer_declarator) {
			fixed_pointer_declarator n = (fixed_pointer_declarator)node;
			{
				identifier v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("=");
			{
				fixed_pointer_initializer v=n.getFixed_pointer_initializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof fixed_pointer_initializer) {
			fixed_pointer_initializer n = (fixed_pointer_initializer)node;
			{
				ASTTextNode v=n.getText281();
				if (v!=null) {
					printToken("&");
					v.accept(this);
				}
			}
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof stackalloc_initializer) {
			stackalloc_initializer n = (stackalloc_initializer)node;
			printToken("stackalloc");
			{
				non_array_type v=n.getNon_array_type();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("[");
			{
				expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		return true;
	}
}
