package tmp.generated_c;

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
				ExternalDeclaration v=n.getExternalDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ExternalDeclaration v : n.getExternalDeclaration1()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ExternalDeclaration1) {
			ExternalDeclaration1 n = (ExternalDeclaration1)node;
			{
				FunctionDefinition v=n.getFunctionDefinition();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExternalDeclaration2) {
			ExternalDeclaration2 n = (ExternalDeclaration2)node;
			{
				Declaration v=n.getDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof FunctionDefinition) {
			FunctionDefinition n = (FunctionDefinition)node;
			{
				DeclarationSpecifiers v=n.getDeclarationSpecifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Declarator v=n.getDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				DeclarationList v=n.getDeclarationList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				CompoundStatement v=n.getCompoundStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Declaration) {
			Declaration n = (Declaration)node;
			{
				DeclarationSpecifiers v=n.getDeclarationSpecifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				InitDeclaratorList v=n.getInitDeclaratorList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof DeclarationList) {
			DeclarationList n = (DeclarationList)node;
			{
				Declaration v=n.getDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Declaration v : n.getDeclaration1()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof DeclarationSpecifiers1) {
			DeclarationSpecifiers1 n = (DeclarationSpecifiers1)node;
			{
				StorageClassSpecifier v=n.getStorageClassSpecifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				DeclarationSpecifiers v=n.getDeclarationSpecifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DeclarationSpecifiers2) {
			DeclarationSpecifiers2 n = (DeclarationSpecifiers2)node;
			{
				TypeSpecifier v=n.getTypeSpecifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				DeclarationSpecifiers v=n.getDeclarationSpecifiers1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DeclarationSpecifiers3) {
			DeclarationSpecifiers3 n = (DeclarationSpecifiers3)node;
			{
				TypeQualifier v=n.getTypeQualifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				DeclarationSpecifiers v=n.getDeclarationSpecifiers2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StorageClassSpecifier1) {
			StorageClassSpecifier1 n = (StorageClassSpecifier1)node;
			{
				ASTStringNode v=n.getAuto();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StorageClassSpecifier2) {
			StorageClassSpecifier2 n = (StorageClassSpecifier2)node;
			{
				ASTStringNode v=n.getRegister();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StorageClassSpecifier3) {
			StorageClassSpecifier3 n = (StorageClassSpecifier3)node;
			{
				ASTStringNode v=n.getStatic_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StorageClassSpecifier4) {
			StorageClassSpecifier4 n = (StorageClassSpecifier4)node;
			{
				ASTStringNode v=n.getExtern();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StorageClassSpecifier5) {
			StorageClassSpecifier5 n = (StorageClassSpecifier5)node;
			{
				ASTStringNode v=n.getTypedef();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier1) {
			TypeSpecifier1 n = (TypeSpecifier1)node;
			{
				ASTStringNode v=n.getVoid_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier2) {
			TypeSpecifier2 n = (TypeSpecifier2)node;
			{
				ASTStringNode v=n.getChar_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier3) {
			TypeSpecifier3 n = (TypeSpecifier3)node;
			{
				ASTStringNode v=n.getShort_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier4) {
			TypeSpecifier4 n = (TypeSpecifier4)node;
			{
				ASTStringNode v=n.getInt_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier5) {
			TypeSpecifier5 n = (TypeSpecifier5)node;
			{
				ASTStringNode v=n.getLong_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier6) {
			TypeSpecifier6 n = (TypeSpecifier6)node;
			{
				ASTStringNode v=n.getFloat_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier7) {
			TypeSpecifier7 n = (TypeSpecifier7)node;
			{
				ASTStringNode v=n.getDouble_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier8) {
			TypeSpecifier8 n = (TypeSpecifier8)node;
			{
				ASTStringNode v=n.getSigned();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier9) {
			TypeSpecifier9 n = (TypeSpecifier9)node;
			{
				ASTStringNode v=n.getUnsigned();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier10) {
			TypeSpecifier10 n = (TypeSpecifier10)node;
			{
				StructOrUnionSpecifier v=n.getStructOrUnionSpecifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier11) {
			TypeSpecifier11 n = (TypeSpecifier11)node;
			{
				EnumSpecifier v=n.getEnumSpecifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeSpecifier12) {
			TypeSpecifier12 n = (TypeSpecifier12)node;
			{
				TypedefName v=n.getTypedefName();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeQualifier1) {
			TypeQualifier1 n = (TypeQualifier1)node;
			{
				ASTStringNode v=n.getConst_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeQualifier2) {
			TypeQualifier2 n = (TypeQualifier2)node;
			{
				ASTStringNode v=n.getVolatile_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructOrUnionSpecifier) {
			StructOrUnionSpecifier n = (StructOrUnionSpecifier)node;
			{
				StructOrUnion v=n.getStructOrUnion();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				StructOrUnionSpecifierInner v=n.getStructOrUnionSpecifierInner();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructOrUnionSpecifierInner1) {
			StructOrUnionSpecifierInner1 n = (StructOrUnionSpecifierInner1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			{
				StructDeclarationList v=n.getStructDeclarationList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof StructOrUnionSpecifierInner2) {
			StructOrUnionSpecifierInner2 n = (StructOrUnionSpecifierInner2)node;
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructOrUnion1) {
			StructOrUnion1 n = (StructOrUnion1)node;
			{
				ASTStringNode v=n.getStruct();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructOrUnion2) {
			StructOrUnion2 n = (StructOrUnion2)node;
			{
				ASTStringNode v=n.getUnion();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructDeclarationList) {
			StructDeclarationList n = (StructDeclarationList)node;
			{
				StructDeclaration v=n.getStructDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (StructDeclaration v : n.getStructDeclaration1()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof InitDeclaratorList) {
			InitDeclaratorList n = (InitDeclaratorList)node;
			{
				InitDeclarator v=n.getInitDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (InitDeclarator v : n.getInitDeclarator1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof InitDeclarator) {
			InitDeclarator n = (InitDeclarator)node;
			{
				Declarator v=n.getDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Initializer v=n.getInitializer();
				if (v!=null) {
					printToken("=");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructDeclaration) {
			StructDeclaration n = (StructDeclaration)node;
			{
				SpecifierQualifierList v=n.getSpecifierQualifierList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				StructDeclaratorList v=n.getStructDeclaratorList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof SpecifierQualifierList1) {
			SpecifierQualifierList1 n = (SpecifierQualifierList1)node;
			{
				TypeSpecifier v=n.getTypeSpecifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SpecifierQualifierList v=n.getSpecifierQualifierList();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof SpecifierQualifierList2) {
			SpecifierQualifierList2 n = (SpecifierQualifierList2)node;
			{
				TypeQualifier v=n.getTypeQualifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				SpecifierQualifierList v=n.getSpecifierQualifierList1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructDeclaratorList) {
			StructDeclaratorList n = (StructDeclaratorList)node;
			{
				StructDeclarator v=n.getStructDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (StructDeclarator v : n.getStructDeclarator1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof StructDeclarator1) {
			StructDeclarator1 n = (StructDeclarator1)node;
			{
				Declarator v=n.getDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof StructDeclarator2) {
			StructDeclarator2 n = (StructDeclarator2)node;
			{
				Declarator v=n.getDeclarator1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				ConstantExpression v=n.getConstantExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EnumSpecifier) {
			EnumSpecifier n = (EnumSpecifier)node;
			{
				ASTStringNode v=n.getEnum_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				EnumSpecifierInternal v=n.getEnumSpecifierInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EnumSpecifierInternal1) {
			EnumSpecifierInternal1 n = (EnumSpecifierInternal1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("{");
			{
				EnumeratorList v=n.getEnumeratorList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof EnumSpecifierInternal2) {
			EnumSpecifierInternal2 n = (EnumSpecifierInternal2)node;
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EnumeratorList) {
			EnumeratorList n = (EnumeratorList)node;
			{
				Enumerator v=n.getEnumerator();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Enumerator v : n.getEnumerator1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Enumerator) {
			Enumerator n = (Enumerator)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ConstantExpression v=n.getConstantExpression();
				if (v!=null) {
					printToken("=");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Declarator) {
			Declarator n = (Declarator)node;
			{
				Pointer v=n.getPointer();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				DirectDeclarator v=n.getDirectDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DirectDeclarator) {
			DirectDeclarator n = (DirectDeclarator)node;
			{
				DirectDeclaratorP1 v=n.getDirectDeclaratorP1();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (DirectDeclaratorP2 v : n.getDirectDeclaratorP2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof DirectDeclaratorP11) {
			DirectDeclaratorP11 n = (DirectDeclaratorP11)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DirectDeclaratorP12) {
			DirectDeclaratorP12 n = (DirectDeclaratorP12)node;
			printToken("(");
			{
				Declarator v=n.getDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof DirectDeclaratorP21) {
			DirectDeclaratorP21 n = (DirectDeclaratorP21)node;
			printToken("[");
			{
				ConstantExpression v=n.getConstantExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof DirectDeclaratorP22) {
			DirectDeclaratorP22 n = (DirectDeclaratorP22)node;
			printToken("(");
			{
				ParameterTypeList v=n.getParameterTypeList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof DirectDeclaratorP23) {
			DirectDeclaratorP23 n = (DirectDeclaratorP23)node;
			printToken("(");
			{
				IdentifierList v=n.getIdentifierList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof Pointer) {
			Pointer n = (Pointer)node;
			printToken("*");
			{
				TypeQualifierList v=n.getTypeQualifierList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Pointer v=n.getPointer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof TypeQualifierList) {
			TypeQualifierList n = (TypeQualifierList)node;
			{
				TypeQualifier v=n.getTypeQualifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (TypeQualifier v : n.getTypeQualifier1()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ParameterTypeList) {
			ParameterTypeList n = (ParameterTypeList)node;
			{
				ParameterList v=n.getParameterList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTTextNode v=n.getText18();
				if (v!=null) {
					printToken(",");
					printToken(".");
					printToken(".");
					printToken(".");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ParameterList) {
			ParameterList n = (ParameterList)node;
			{
				ParameterDeclaration v=n.getParameterDeclaration();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ParameterDeclaration v : n.getParameterDeclaration1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof ParameterDeclaration) {
			ParameterDeclaration n = (ParameterDeclaration)node;
			{
				DeclarationSpecifiers v=n.getDeclarationSpecifiers();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ParameterDeclarationInternal v=n.getParameterDeclarationInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ParameterDeclarationInternal1) {
			ParameterDeclarationInternal1 n = (ParameterDeclarationInternal1)node;
			{
				Declarator v=n.getDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ParameterDeclarationInternal2) {
			ParameterDeclarationInternal2 n = (ParameterDeclarationInternal2)node;
			{
				AbstractDeclarator v=n.getAbstractDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof IdentifierList) {
			IdentifierList n = (IdentifierList)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (ASTStringNode v : n.getIdentifier1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof Initializer1) {
			Initializer1 n = (Initializer1)node;
			{
				AssignmentExpression v=n.getAssignmentExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Initializer2) {
			Initializer2 n = (Initializer2)node;
			printToken("{");
			{
				InitializerList v=n.getInitializerList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			return false;
		}
		if (node instanceof InitializerList) {
			InitializerList n = (InitializerList)node;
			{
				Initializer v=n.getInitializer();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Initializer v : n.getInitializer1()) {
				printToken(",");
				v.accept(this);
			}
			return false;
		}
		if (node instanceof TypeName) {
			TypeName n = (TypeName)node;
			{
				SpecifierQualifierList v=n.getSpecifierQualifierList();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				AbstractDeclarator v=n.getAbstractDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AbstractDeclarator1) {
			AbstractDeclarator1 n = (AbstractDeclarator1)node;
			{
				Pointer v=n.getPointer();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AbstractDeclarator2) {
			AbstractDeclarator2 n = (AbstractDeclarator2)node;
			{
				Pointer v=n.getPointer1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				DirectAbstractDeclarator v=n.getDirectAbstractDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof DirectAbstractDeclarator) {
			DirectAbstractDeclarator n = (DirectAbstractDeclarator)node;
			{
				DirectAbstractDeclaratorP1 v=n.getDirectAbstractDeclaratorP1();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (DirectAbstractDeclaratorP2 v : n.getDirectAbstractDeclaratorP2()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof DirectAbstractDeclaratorP11) {
			DirectAbstractDeclaratorP11 n = (DirectAbstractDeclaratorP11)node;
			printToken("(");
			{
				AbstractDeclarator v=n.getAbstractDeclarator();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof DirectAbstractDeclaratorP12) {
			DirectAbstractDeclaratorP12 n = (DirectAbstractDeclaratorP12)node;
			printToken("[");
			{
				ConstantExpression v=n.getConstantExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof DirectAbstractDeclaratorP13) {
			DirectAbstractDeclaratorP13 n = (DirectAbstractDeclaratorP13)node;
			printToken("(");
			{
				ParameterTypeList v=n.getParameterTypeList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof DirectAbstractDeclaratorP21) {
			DirectAbstractDeclaratorP21 n = (DirectAbstractDeclaratorP21)node;
			printToken("[");
			{
				ConstantExpression v=n.getConstantExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("]");
			return false;
		}
		if (node instanceof DirectAbstractDeclaratorP22) {
			DirectAbstractDeclaratorP22 n = (DirectAbstractDeclaratorP22)node;
			printToken("(");
			{
				ParameterTypeList v=n.getParameterTypeList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof TypedefName) {
			TypedefName n = (TypedefName)node;
			{
				ASTStringNode v=n.getIdentifier();
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
				ExpressionStatement v=n.getExpressionStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement3) {
			Statement3 n = (Statement3)node;
			{
				CompoundStatement v=n.getCompoundStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement4) {
			Statement4 n = (Statement4)node;
			{
				SelectionStatement v=n.getSelectionStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement5) {
			Statement5 n = (Statement5)node;
			{
				IterationStatement v=n.getIterationStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Statement6) {
			Statement6 n = (Statement6)node;
			{
				JumpStatement v=n.getJumpStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LabeledStatement1) {
			LabeledStatement1 n = (LabeledStatement1)node;
			{
				ASTStringNode v=n.getIdentifier();
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
		if (node instanceof LabeledStatement2) {
			LabeledStatement2 n = (LabeledStatement2)node;
			{
				ASTStringNode v=n.getCase_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ConstantExpression v=n.getConstantExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				Statement v=n.getStatement1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LabeledStatement3) {
			LabeledStatement3 n = (LabeledStatement3)node;
			{
				ASTStringNode v=n.getDflt();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				Statement v=n.getStatement2();
				if (v!=null) {
					v.accept(this);
				}
			}
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
			printToken(";");
			return false;
		}
		if (node instanceof CompoundStatement) {
			CompoundStatement n = (CompoundStatement)node;
			printToken("{");
			{
				DeclarationList v=n.getDeclarationList();
				if (v!=null) {
					v.accept(this);
				}
			}
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
			{
				Statement v=n.getStatement();
				if (v!=null) {
					v.accept(this);
				}
			}
			for (Statement v : n.getStatement1()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof SelectionStatement1) {
			SelectionStatement1 n = (SelectionStatement1)node;
			{
				ASTStringNode v=n.getIf_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
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
		if (node instanceof SelectionStatement2) {
			SelectionStatement2 n = (SelectionStatement2)node;
			{
				ASTStringNode v=n.getSwitch_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				Expression v=n.getExpression1();
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
		if (node instanceof IterationStatement1) {
			IterationStatement1 n = (IterationStatement1)node;
			{
				ASTStringNode v=n.getWhile_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
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
		if (node instanceof IterationStatement2) {
			IterationStatement2 n = (IterationStatement2)node;
			{
				ASTStringNode v=n.getDo_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				Statement v=n.getStatement1();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ASTStringNode v=n.getWhile_kw1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				Expression v=n.getExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken(";");
			return false;
		}
		if (node instanceof IterationStatement3) {
			IterationStatement3 n = (IterationStatement3)node;
			{
				ASTStringNode v=n.getFor_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
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
			printToken(";");
			{
				Expression v=n.getExpression4();
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
		if (node instanceof JumpStatement1) {
			JumpStatement1 n = (JumpStatement1)node;
			{
				ASTStringNode v=n.getGoto_kw();
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
		if (node instanceof JumpStatement2) {
			JumpStatement2 n = (JumpStatement2)node;
			{
				ASTStringNode v=n.getContinue_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof JumpStatement3) {
			JumpStatement3 n = (JumpStatement3)node;
			{
				ASTStringNode v=n.getBreak_kw();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(";");
			return false;
		}
		if (node instanceof JumpStatement4) {
			JumpStatement4 n = (JumpStatement4)node;
			{
				ASTStringNode v=n.getReturn_kw();
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
			printToken(";");
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
		if (node instanceof AssignmentExpression1) {
			AssignmentExpression1 n = (AssignmentExpression1)node;
			{
				UnaryExpression v=n.getUnaryExpression();
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
		if (node instanceof AssignmentOperator1) {
			AssignmentOperator1 n = (AssignmentOperator1)node;
			{
				ASTStringNode v=n.getEq();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator2) {
			AssignmentOperator2 n = (AssignmentOperator2)node;
			{
				ASTStringNode v=n.getAssstar();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator3) {
			AssignmentOperator3 n = (AssignmentOperator3)node;
			{
				ASTStringNode v=n.getAssslash();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator4) {
			AssignmentOperator4 n = (AssignmentOperator4)node;
			{
				ASTStringNode v=n.getAsspercent();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator5) {
			AssignmentOperator5 n = (AssignmentOperator5)node;
			{
				ASTStringNode v=n.getAssplus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator6) {
			AssignmentOperator6 n = (AssignmentOperator6)node;
			{
				ASTStringNode v=n.getAssminus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator7) {
			AssignmentOperator7 n = (AssignmentOperator7)node;
			{
				ASTStringNode v=n.getAsssl();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator8) {
			AssignmentOperator8 n = (AssignmentOperator8)node;
			{
				ASTStringNode v=n.getAsssr();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator9) {
			AssignmentOperator9 n = (AssignmentOperator9)node;
			{
				ASTStringNode v=n.getAssamp();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator10) {
			AssignmentOperator10 n = (AssignmentOperator10)node;
			{
				ASTStringNode v=n.getAsstil();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AssignmentOperator11) {
			AssignmentOperator11 n = (AssignmentOperator11)node;
			{
				ASTStringNode v=n.getAssor();
				if (v!=null) {
					v.accept(this);
				}
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
				ConditionalExpressionInternal v=n.getConditionalExpressionInternal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConditionalExpressionInternal) {
			ConditionalExpressionInternal n = (ConditionalExpressionInternal)node;
			printToken("?");
			{
				Expression v=n.getExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(":");
			{
				ConditionalExpression v=n.getConditionalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ConstantExpression) {
			ConstantExpression n = (ConstantExpression)node;
			{
				ConditionalExpression v=n.getConditionalExpression();
				if (v!=null) {
					v.accept(this);
				}
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
			{
				LogicalORExpression v=n.getLogicalORExpression();
				if (v!=null) {
					printToken("||");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof LogicalANDExpression) {
			LogicalANDExpression n = (LogicalANDExpression)node;
			{
				InclusiveORExpression v=n.getInclusiveORExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				LogicalANDExpression v=n.getLogicalANDExpression();
				if (v!=null) {
					printToken("&&");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof InclusiveORExpression) {
			InclusiveORExpression n = (InclusiveORExpression)node;
			{
				ExclusiveORExpression v=n.getExclusiveORExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				InclusiveORExpression v=n.getInclusiveORExpression();
				if (v!=null) {
					printToken("|");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ExclusiveORExpression) {
			ExclusiveORExpression n = (ExclusiveORExpression)node;
			{
				ANDExpression v=n.getANDExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ExclusiveORExpression v=n.getExclusiveORExpression();
				if (v!=null) {
					printToken("^");
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ANDExpression) {
			ANDExpression n = (ANDExpression)node;
			{
				EqualityExpression v=n.getEqualityExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				ANDExpression v=n.getANDExpression();
				if (v!=null) {
					printToken("&");
					v.accept(this);
				}
			}
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
			{
				EqualityExpressionInt v=n.getEqualityExpressionInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EqualityExpressionInt1) {
			EqualityExpressionInt1 n = (EqualityExpressionInt1)node;
			{
				ASTStringNode v=n.getEqeq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				EqualityExpression v=n.getEqualityExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof EqualityExpressionInt2) {
			EqualityExpressionInt2 n = (EqualityExpressionInt2)node;
			{
				ASTStringNode v=n.getNoteq();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				EqualityExpression v=n.getEqualityExpression1();
				if (v!=null) {
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
			{
				RelationalExpressionInt v=n.getRelationalExpressionInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RelationalExpressionInt1) {
			RelationalExpressionInt1 n = (RelationalExpressionInt1)node;
			printToken("<");
			{
				RelationalExpression v=n.getRelationalExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RelationalExpressionInt2) {
			RelationalExpressionInt2 n = (RelationalExpressionInt2)node;
			printToken(">");
			{
				RelationalExpression v=n.getRelationalExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RelationalExpressionInt3) {
			RelationalExpressionInt3 n = (RelationalExpressionInt3)node;
			printToken("<=");
			{
				RelationalExpression v=n.getRelationalExpression2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof RelationalExpressionInt4) {
			RelationalExpressionInt4 n = (RelationalExpressionInt4)node;
			printToken(">=");
			{
				RelationalExpression v=n.getRelationalExpression3();
				if (v!=null) {
					v.accept(this);
				}
			}
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
			{
				ShiftExpressionInt v=n.getShiftExpressionInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ShiftExpressionInt1) {
			ShiftExpressionInt1 n = (ShiftExpressionInt1)node;
			printToken("<<");
			{
				ShiftExpression v=n.getShiftExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof ShiftExpressionInt2) {
			ShiftExpressionInt2 n = (ShiftExpressionInt2)node;
			printToken(">>");
			{
				ShiftExpression v=n.getShiftExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
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
			{
				AdditiveExpressionInt v=n.getAdditiveExpressionInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AdditiveExpressionInt1) {
			AdditiveExpressionInt1 n = (AdditiveExpressionInt1)node;
			printToken("+");
			{
				AdditiveExpression v=n.getAdditiveExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof AdditiveExpressionInt2) {
			AdditiveExpressionInt2 n = (AdditiveExpressionInt2)node;
			printToken("-");
			{
				AdditiveExpression v=n.getAdditiveExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MultiplicativeExpression) {
			MultiplicativeExpression n = (MultiplicativeExpression)node;
			{
				CastExpression v=n.getCastExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				MultiplicativeExpressionInt v=n.getMultiplicativeExpressionInt();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MultiplicativeExpressionInt1) {
			MultiplicativeExpressionInt1 n = (MultiplicativeExpressionInt1)node;
			printToken("*");
			{
				MultiplicativeExpression v=n.getMultiplicativeExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MultiplicativeExpressionInt2) {
			MultiplicativeExpressionInt2 n = (MultiplicativeExpressionInt2)node;
			printToken("/");
			{
				MultiplicativeExpression v=n.getMultiplicativeExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof MultiplicativeExpressionInt3) {
			MultiplicativeExpressionInt3 n = (MultiplicativeExpressionInt3)node;
			printToken("%");
			{
				MultiplicativeExpression v=n.getMultiplicativeExpression2();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CastExpression1) {
			CastExpression1 n = (CastExpression1)node;
			printToken("(");
			{
				TypeName v=n.getTypeName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			{
				CastExpression v=n.getCastExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof CastExpression2) {
			CastExpression2 n = (CastExpression2)node;
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
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
			printToken("++");
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpression3) {
			UnaryExpression3 n = (UnaryExpression3)node;
			printToken("--");
			{
				UnaryExpression v=n.getUnaryExpression1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpression4) {
			UnaryExpression4 n = (UnaryExpression4)node;
			{
				UnaryOperator v=n.getUnaryOperator();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				CastExpression v=n.getCastExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpression5) {
			UnaryExpression5 n = (UnaryExpression5)node;
			{
				ASTStringNode v=n.getSizeof();
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				UnaryExpressionSizeOf v=n.getUnaryExpressionSizeOf();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpressionSizeOf1) {
			UnaryExpressionSizeOf1 n = (UnaryExpressionSizeOf1)node;
			{
				UnaryExpression v=n.getUnaryExpression();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryExpressionSizeOf2) {
			UnaryExpressionSizeOf2 n = (UnaryExpressionSizeOf2)node;
			printToken("(");
			{
				TypeName v=n.getTypeName();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof UnaryOperator1) {
			UnaryOperator1 n = (UnaryOperator1)node;
			{
				ASTStringNode v=n.getAnd();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryOperator2) {
			UnaryOperator2 n = (UnaryOperator2)node;
			{
				ASTStringNode v=n.getStar();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryOperator3) {
			UnaryOperator3 n = (UnaryOperator3)node;
			{
				ASTStringNode v=n.getPlus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryOperator4) {
			UnaryOperator4 n = (UnaryOperator4)node;
			{
				ASTStringNode v=n.getMinus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryOperator5) {
			UnaryOperator5 n = (UnaryOperator5)node;
			{
				ASTStringNode v=n.getTilde();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof UnaryOperator6) {
			UnaryOperator6 n = (UnaryOperator6)node;
			{
				ASTStringNode v=n.getNot();
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
			for (PostfixExpressionInternal v : n.getPostfixExpressionInternal()) {
				v.accept(this);
			}
			return false;
		}
		if (node instanceof PostfixExpressionInternal1) {
			PostfixExpressionInternal1 n = (PostfixExpressionInternal1)node;
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
		if (node instanceof PostfixExpressionInternal2) {
			PostfixExpressionInternal2 n = (PostfixExpressionInternal2)node;
			printToken("(");
			{
				ArgumentExpressionList v=n.getArgumentExpressionList();
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			return false;
		}
		if (node instanceof PostfixExpressionInternal3) {
			PostfixExpressionInternal3 n = (PostfixExpressionInternal3)node;
			printToken(".");
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PostfixExpressionInternal4) {
			PostfixExpressionInternal4 n = (PostfixExpressionInternal4)node;
			printToken("->");
			{
				ASTStringNode v=n.getIdentifier1();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PostfixExpressionInternal5) {
			PostfixExpressionInternal5 n = (PostfixExpressionInternal5)node;
			{
				ASTStringNode v=n.getPlusplus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PostfixExpressionInternal6) {
			PostfixExpressionInternal6 n = (PostfixExpressionInternal6)node;
			{
				ASTStringNode v=n.getMinusminus();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression1) {
			PrimaryExpression1 n = (PrimaryExpression1)node;
			{
				ASTStringNode v=n.getIdentifier();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof PrimaryExpression2) {
			PrimaryExpression2 n = (PrimaryExpression2)node;
			{
				Constant v=n.getConstant();
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
		if (node instanceof ArgumentExpressionList) {
			ArgumentExpressionList n = (ArgumentExpressionList)node;
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
		if (node instanceof Constant1) {
			Constant1 n = (Constant1)node;
			{
				ASTStringNode v=n.getInteger_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Constant2) {
			Constant2 n = (Constant2)node;
			{
				ASTStringNode v=n.getFloating_point_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Constant3) {
			Constant3 n = (Constant3)node;
			{
				ASTStringNode v=n.getCharacter_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		if (node instanceof Constant4) {
			Constant4 n = (Constant4)node;
			{
				ASTStringNode v=n.getString_literal();
				if (v!=null) {
					v.accept(this);
				}
			}
			return false;
		}
		return true;
	}
}
