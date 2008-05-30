/* C# Grammar for Antlr 
 * By Todd King
 * for more info see the ECMA-334 C# Grammar posted by Microsoft at 
 * http://msdn.microsoft.com/library/default.asp?url=/library/en-us/csspec/html/CSharpSpecStart.asp
 * Lexer section taken from Robin Debreuil's C# Grammar.
 */
 
header 
{
	using RecognitionException	=	antlr.RecognitionException;
	using CommonAST							= antlr.CommonAST;
	using System.Collections;
}
options 
{	
	language = "CSharp";
	namespace = "CodeCritic.Parser";
}


/**********************************************************************************************************************************
 ******************************* PARSER *******************************************************************************************
 **********************************************************************************************************************************/
class CSParser extends Parser;

options 
{
	k = 2;                           	// two token lookahead
	defaultErrorHandler = true;     	
	buildAST = true;   
 	exportVocab=CSharp;
 	ASTLabelType = "CommonAST";
 	codeGenMakeSwitchThreshold = 3;  // Some optimizations
	codeGenBitsetTestThreshold = 4;
}
tokens
{
	//Misc
	QualIdent;
	Type;
	ProjectUnit;
	SolutionUnit;
	
	//Literals
	BooleanLiteral;
	IntegerLiteral;
	HexLiteral;
	RealLiteral;
	CharLiteral;
	StringLiteral;
	VerbatimStringLiteral;
	NullLiteral;
	
	//Expression
	Expression;
	AssignExpr;
	TernaryExpr;
	BinaryExpr;
	UnaryExpr;
	CastExpr;
	ObjCreateExpr;
	ArrayCreateExpr;
	Expressions;
	ParenExpr;
	MemberAccessExpr;
	ElementAccessExpr;
	InvokeExpr;
	TypeOfExpr;
	CheckedExpr;
	UncheckedExpr;
	SizeOfExpr;
	PointerMemberAccessExpr;
	
	ArrayRank;
	Args;
	Arg;
	
	//Statements
	Statements;
	LabeledStmt;
	VariableDeclStmt		<AST=CodeCritic.Nodes.VarDeclNode>;
	ConstantDeclStmt		<AST=CodeCritic.Nodes.VarDeclNode>;
	ExprStmt;
	IfStmt;
	SwitchStmt;
	SwitchSections			<AST=CodeCritic.Nodes.ScopeNode>;
	SwitchSection;
	IterationStmt;
	ForInit;
	LoopCondition;
	ForIncrement;
	IterVar							<AST=CodeCritic.Nodes.VarDeclNode>;
	GotoStmt;
	ThrowStmt;
	ReturnStmt;
	CheckedStmt;
	UncheckedStmt;
	LockStmt;
	UsingStmt						<AST=CodeCritic.Nodes.ScopeNode>;
	TryStmt;
	TryClauses;
	CatchClause				<AST=CodeCritic.Nodes.ScopeNode>;
	FinallyClause;
	
	//Namespace
	CompileUnit				<AST=CodeCritic.Nodes.CompileUnitNode>;
	NamespaceNode			<AST=CodeCritic.Nodes.NamespaceNode>;
	UsingNode;
	Types;
	TypeModifiers;
	
	//Class
	ClassNode					<AST=CodeCritic.Nodes.InheritanceTypeNode>;
	BaseTypes;
	Members;
	Modifiers;
	ConstantNode			<AST=CodeCritic.Nodes.MemberNode>;
	FieldNode					<AST=CodeCritic.Nodes.MemberNode>;
	MethodNode				;
	DeclArgs;
	DeclArg						<AST=CodeCritic.Nodes.VarDeclNode>;
	DeclArgParams			<AST=CodeCritic.Nodes.VarDeclNode>;
	PropertyNode			<AST=CodeCritic.Nodes.MemberNode>;
	AccessorNode			<AST=CodeCritic.Nodes.ScopeNode>;
	EventNode					<AST=CodeCritic.Nodes.MemberNode>;
	IndexerNode				<AST=CodeCritic.Nodes.IndexerNode>;
	IndexerBase;
	UnaryOperatorNode	;
	BinaryOperatorNode	;
	ConversionOperatorNode	;	
	ConstructorNode		<AST=CodeCritic.Nodes.ConstructorNode>;
	ConstructorInit;
	DestructorNode		<AST=CodeCritic.Nodes.ScopeNode>;
	
	//Struct
	StructNode			<AST=CodeCritic.Nodes.InheritanceTypeNode>;
	
	//Interface
	InterfaceNode		<AST=CodeCritic.Nodes.InheritanceTypeNode>;
	IEventNode			<AST=CodeCritic.Nodes.MemberNode>;
	IIndexerNode		<AST=CodeCritic.Nodes.IndexerNode>;
	IMethodNode			;
	IPropertyNode		<AST=CodeCritic.Nodes.MemberNode>;
	IAccessorNode;
	
	//Enum
	EnumNode				<AST=CodeCritic.Nodes.TypeNode>;
	EnumMemberNode	<AST=CodeCritic.Nodes.MemberNode>;
	
	//Delegates
	DelegateNode		<AST=CodeCritic.Nodes.DelegateNode>;

	//Attributes
	GlobalAttributeSection;
	AttributeSection;
	AttributeTarget;
	Attribute;
	
}
{
		private bool errorOccured = false;
		public bool ErrorOccured
		{
			get{return errorOccured;}
		}
		//This is mostly for debugging so I can place one handy breakpoint here
		public override void reportError(RecognitionException ex)
		{
			Console.Error.WriteLine(ex + "\t[CSParser.reportError]");
			errorOccured = true;
		}
		
		protected string SetTypeName(CommonAST typenode)
		{
			if(typenode != null)
			{
				string val = typenode.getText();
				CommonAST child = (CommonAST)typenode.getFirstChild();
				while(child != null)
				{
					if(child.Type == CSharpTokenTypes.ArrayRank)
					{
						val += "["+child.getText()+"]";
					}
					else if(child.Type == CSharpTokenTypes.STAR)
					{
						val += "*";
					}
					child = (CommonAST)child.getNextSibling();
				}
				typenode.setText(val);
				return val;
			}
			return "";
		}
}

identifier
	:	<IDENTIFIER>		
	|	"module"
	|	"assembly"
	;	

//C.1.8 Literals
literal
	:	boolean_literal
	|	<INTEGER_LITERAL>
	|	<HEXADECIMAL_INTEGER_LITERAL>
	|	<REAL_LITERAL>
	|	<CHARACTER_LITERAL>
	|	<REGULAR_STRING_LITERAL>
	|	<VERBATIM_STRING_LITERAL>
	|	"null"
	;

boolean_literal
	:	"true"
	|	"false"
	;

//***********************************************************************************************************************************/
// ***** C.2.1 Basic concepts *****
//***********************************************************************************************************************************/
type_name
	:	identifier
		("." identifier)*	
	;

//***********************************************************************************************************************************/
//C.2.2 Types
//***********************************************************************************************************************************/
type
	:	non_array_type ( rank_specifiers )? ("*")?
	;

rank_specifiers
	:	(rank_specifier)+
	;	

rank_specifier
	 : "[" ( "," )* "]"
   ;
	
non_array_type
   :	predefined_type
   |	type_name
   ;

predefined_type
   :	simple_type
   |	"object"
   |	"string"
   |	"void"
   ;

simple_type 
	:	numeric_type
	|	"bool"
	;

numeric_type 
	:	integral_type
	|	floating_point_type
	|	"decimal"
	;
	
integral_type 
	:	"sbyte"
	|	"byte"
	|	"short"
	|	"ushort"
	|	"int"
	|	"uint"
	|	"long"
	|	"ulong"
	|	"char"
	;

class_type
	:	type_name
	|	"object"
	|	"string"
	;

floating_point_type 
	:	"float"
	|	"double"
	;

//***********************************************************************************************************************************/	
//C.2.4 Expressions
//***********************************************************************************************************************************/
expression
   :	conditional_expression	( assignment_operator expression )?
   ;

assignment
   : conditional_expression assignment_operator expression
   ;
	
assignment_operator 
	:	ASSIGN
	|	PLUS_ASN
	|	MINUS_ASN
	|	STAR_ASN
	|	DIV_ASN
	|	MOD_ASN
	|	BAND_ASN
	|	BOR_ASN
	|	BXOR_ASN
	|	SL_ASN
	|	SR_ASN
	|	BSR_ASN
	;

conditional_expression
   : conditional_or_expression 
   		(	"?" expression ":" conditional_expression )?
   ;

conditional_or_expression
   : conditional_and_expression 
   		( LOR conditional_or_expression  )?
   ;
	
conditional_and_expression
   : inclusive_or_expression 		( LAND conditional_and_expression	)?
   ;

inclusive_or_expression
   : exclusive_or_expression 
   		( BOR inclusive_or_expression )?
   ;

exclusive_or_expression
   : and_expression 
   		(CARET exclusive_or_expression 	)?
   ;

and_expression
   : equality_expression 
   		( AMPER and_expression 	)?
   ;

equality_expression
   : relational_expression 
   		( equality_operator equality_expression 	)?
   ;
equality_operator
	:	EQUAL | NOT_EQUAL
	;

relational_expression
   : shift_expression 
   		( ( relational_operator relational_expression ) 		
   		| ( relational_operator2 type ) 
   		)?
   ;
relational_operator
	:	LTHAN | GTHAN | LE | GE
	;
relational_operator2
	:	IS | AS
	;

shift_expression
   : additive_expression 
   		( shift_operator shift_expression  		)?
   ;
shift_operator
	:	SL | SR 
	;

additive_expression
   : multiplicative_expression 
   		( additive_operator additive_expression 	)?
   ;
additive_operator
	:	PLUS | MINUS
	;

multiplicative_expression
   : unary_expression 
   		( multiplicative_operator	multiplicative_expression )?
   ;
multiplicative_operator
	:	STAR | DIV | MOD
	;

unary_expression 
	:	unary_operator	unary_expression	
	|	LOOK_AHEAD("cast_expression()") cast_expression	
	|	primary_expression
	;
	
unary_operator	//While STAR is listed as a unary operator in the grammar, it appears to be undocumented in the unary expression MS C# deffinition section?!?!
	: PLUS | MINUS | BANG | TILDE | STAR| INC | DEC
  ;
	
cast_expression
	:	LPAREN   type   RPAREN unary_expression
	;

primary_expression
	:	primary_expression_start	( primary_expression_postfix )?
	|	creation_expression
	;
	
primary_expression_start
	:	literal
	|	identifier									
	|	parenthesized_expression		//LPAREN   expression   RPAREN
	|	predefined_type member_access
	|	this_access									//THIS
	|	base_access									//BASE ((DOT identifier) | (LBRACK   expression_list   RBRACK))
	|	typeof_expression						//TYPEOF   LPAREN	(type | VOID)	RPAREN
	|	sizeof_expression						//SIZEOF   LPAREN   unmanaged_type   RPAREN	
	|	checked_expression					//CHECKED   LPAREN   expression   RPAREN
	|	unchecked_expression				//UNCHECKED   LPAREN   expression   RPAREN
	;
	
primary_expression_postfix
	:	(	member_access 							//DOT identifier
		| invocation_expression 			//LPAREN   (argument_list)?   RPAREN
		|	element_access							//LBRACK   expression_list   RBRACK
		| post_increment_expression 	//INC
		| post_decrement_expression		//DEC
		|	pointer_member_access
		)+
	;

array_creation_postfix_expression
	:	(	member_access 							//DOT identifier
		| invocation_expression 			//LPAREN   (argument_list)?   RPAREN
		| post_increment_expression 	//INC
		| post_decrement_expression		//DEC
		|	pointer_member_access
		)+
	;

creation_expression
	:	NEW non_array_type ( LPAREN (argument_list)? RPAREN	( primary_expression_postfix )?
		| LBRACK	expression_list	RBRACK	(	rank_specifiers )? ( array_initializer )?	( array_creation_postfix_expression )?  
		|	rank_specifiers	array_initializer	( array_creation_postfix_expression )?  			
	;

parenthesized_expression
	:	LPAREN   expression   RPAREN 
	;

member_access //assumes a predefined type or a primaryexpression came before this rule
	:	DOT	identifier
	;

invocation_expression //assumes a predefined type or a primaryexpression came before this rule
	:	LPAREN	( argument_list )?	RPAREN
	;
	
argument_list 
	:	argument (COMMA argument)*
	;

argument 
	:	(REF | OUT)?	expression
	;

element_access
	:   LBRACK   argument_list   RBRACK
	;
	
expression_list
	:	expression (COMMA expression)*
	;

this_access 
	:	THIS
	;

base_access 
	:	BASE (member_access | element_access)
	;

post_increment_expression 
	:	INC
	;

post_decrement_expression 
	:	DEC
	;

typeof_expression 
	:	TYPEOF LPAREN	type	RPAREN
	;
	
checked_expression 
	:	CHECKED   LPAREN   expression   RPAREN
	;

unchecked_expression 
	:	UNCHECKED   LPAREN   expression   RPAREN
	;

//***********************************************************************************************************************************/
//C.2.5 Statements
//***********************************************************************************************************************************/
statement
	:	identifier	COLON	statement	//labeled_statement
	|	LOOK_AHEAD("type() identifier()") local_variable_declaration	SEMI	
	|	local_constant_declaration	SEMI
	|	embedded_statement					
	;

local_variable_declaration 
	:	type   local_variable_declarators
	;
	
local_variable_declarators
	:	local_variable_declarator (COMMA local_variable_declarators)?	
	;

local_variable_declarator
	:	identifier	(local_variable_assignment)?
	;
	
local_variable_assignment
	:	ASSIGN	local_variable_initializer
	;

local_variable_initializer 
	:	array_initializer 
	|	expression
	;

local_constant_declaration 
	:	CONST   type   local_constant_declarators
	;
	
local_constant_declarators
	:	local_constant_declarator (COMMA local_constant_declarators)?	
	;

local_constant_declarator
	:	identifier	ASSIGN	local_variable_initializer
	;

embedded_statement 
	:	block									//LBRACE   (statement_list)?   RBRACE 
	|	SEMI									//empty_statement
	|	expression_statement	//statement_expression	COMMA
	|	selection_statement		//IF   LPAREN || SWITCH   LPAREN
	|	iteration_statement		//WHILE || DO || FOR || FOREACH
	|	jump_statement				//BREAK || CONTINUE || GOTO || RETURN || THROW
	|	try_statement					//TRY   block   catch_clauses   finally_clause
	|	checked_statement			//CHECKED   block
	|	unchecked_statement		//UNCHECKED   block
	|	lock_statement				//LOCK   LPAREN   expression   RPAREN   embedded_statement
	|	using_statement				//USING   LPAREN    resource_acquisition   RPAREN    embedded_statement
	|	unsafe_statement			//UNSAFE block
	|	fixed_statement
	;
	
block
	:	LBRACE  (statement)*   RBRACE
	;

statement_list
	:	(statement)+
	;
	
expression_statement 
	:	expression	SEMI
	; 
	
selection_statement 
	:	if_statement
	|	switch_statement 
	;
	
if_statement 
	:	IF	LPAREN	expression	RPAREN	embedded_statement   (ELSE embedded_statement)? 	
	;
	
switch_statement 
	:	SWITCH	LPAREN	expression	RPAREN	switch_block 
	;

switch_block 
	:	LBRACE   (switch_section)+   RBRACE
	;
switch_section 
	:	(switch_label)+   statement_list
	;

switch_label 
	:	CASE	expression	COLON
	|	DEFAULT	COLON
	;

iteration_statement 
	:	while_statement		//WHILE LPAREN
	|	do_statement			//DO embedded_statement
	|	for_statement			//FOR	LPAREN
	|	foreach_statement //FOREACH   LPAREN 
	;

while_statement 
	:	WHILE	LPAREN	expression	RPAREN	embedded_statement 
	
	;

do_statement 
	:	DO	embedded_statement	WHILE	LPAREN	expression	RPAREN
	;

for_statement
	:	FOR LPAREN	(for_initializer)?	SEMI	(expression)?	SEMI	(expression_list)?	RPAREN	embedded_statement 
	
	;
	
for_initializer 
	:	LOOK_AHEAD("type() identifier()") local_variable_declaration
	|	expression_list 
	;
	
foreach_statement 
	:	FOREACH LPAREN type  identifier   IN   expression	RPAREN   embedded_statement 
	;
	
jump_statement 
	:	BREAK	SEMI
	|	CONTINUE	SEMI
	|	goto_statement
	|	return_statement
	|	throw_statement 
	;

goto_statement
	:	GOTO	(	
			identifier   SEMI	
		|	CASE   expression   SEMI
		|	DEFAULT   SEMI		
	);

return_statement
	:	RETURN   (expression)?   SEMI		
	;

throw_statement 
	:	THROW   (expression)?   SEMI	
	;

try_statement 
	:	TRY	block	try_statement_clauses
	;
	
try_statement_clauses
	:	catch_clauses (finally_clause)? 
	|	finally_clause	
	;

catch_clauses 
	:	( catch_clause )+
	;

catch_clause 
	:	CATCH
								(	( LPAREN	type	( identifier )?	RPAREN	block )
									
								|	block	
								)
	;
/*	
catch_clause 
	:	cat:CATCH^<AST=CodeCritic.Nodes.InnerScopeNode>
								(	( LPAREN!	class_type	( identifier )?	RPAREN!	block )
									{#cat.setType(CatchClause); #cat.setText("specific");}
								|	block	{#cat.setType(CatchClause); #cat.setText("generic");}
								)
	;
*/	
finally_clause 
	:	FINALLY   block
	;
	
checked_statement 
	:	CHECKED   block 
	;

unchecked_statement 
	:	UNCHECKED   block
	;

lock_statement 
	:	LOCK   LPAREN   expression   RPAREN   embedded_statement 
	;

using_statement 
	:	USING
	   LPAREN    resource_acquisition   RPAREN    embedded_statement 	
	;

resource_acquisition 
	:	LOOK_AHEAD("type() identifier() (COMMA|SEMI|ASSIGN)") local_variable_declaration
	|	expression 
	;

//***********************************************************************************************************************************/
//C.2.6 Namespaces
//***********************************************************************************************************************************/
compilation_unit 
	:	(using_directive)*   (attributes_either)?   (namespace_member_declaration_no_attr (namespace_member_declaration)* )?
		
	;

namespace_declaration 
	:	NAMESPACE type_name   namespace_body    
	;

namespace_body 
	:	LBRACE   (using_directive)*   (namespace_member_declaration)*   RBRACE 	(SEMI)?
	;

using_directive
	:	USING  type_name (	ASSIGN type_name SEMI
				|	SEMI
 				)
	;
	
namespace_member_declaration_no_attr
	:	namespace_declaration
	|	(type_modifiers)? type_declaration
	;
	
namespace_member_declaration 
	:	namespace_declaration
	|	(attributes)? (type_modifiers)? type_declaration
	;

type_declaration 	//Type declaration that assumes parent rule has already looked for attributes and modifiers
	:	class_declaration
	|	struct_declaration
	|	interface_declaration
	|	enum_declaration
	|	delegate_declaration
	;

type_modifiers
	:	(type_modifier)+
	;

type_modifier
	:	NEW
	|	PUBLIC
	|	PROTECTED
	|	INTERNAL
	|	PRIVATE
	|	ABSTRACT
	|	SEALED 
	|	UNSAFE
	;
	
//***********************************************************************************************************************************/
//C.2.7 Classes section
//***********************************************************************************************************************************/
class_declaration
	:	CLASS identifier   (class_base)?   class_body	(SEMI)? 
	;

class_base 
	:	COLON class_type (COMMA type_name)*	
	;

interface_type_list 
	:	type_name (COMMA type_name)*
	;

class_body 
	:	LBRACE   (class_member_declaration)*   RBRACE 
	;

class_member_declaration 
	:	(attributes)?	(member_modifiers)?
		(	constant_declaration		//CONST type   constant_declarators   SEMI
		|	event_declaration		//EVENT   type   variable_declarators   SEMI
		|	destructor_declaration		//TILDE   identifier   LPAREN   RPAREN    body
		|	conversion_operator_declaration		
		|	type_declaration	
		|	type	(	constructor_declaration
								|	indexer_declaration_no_interface
								|	operator_declaration
								|	LOOK_AHEAD("indexer_base() LBRACK") indexer_declaration_interface
								|	type_name	( method_declaration
																	|	property_declaration
																	|	field_declaration
																	)
								)
		)
		;

member_modifiers
	:	(member_modifier)+	
	;

member_modifier 
	:	NEW
	|	PUBLIC
	|	PROTECTED
	|	INTERNAL
	|	PRIVATE
	|	STATIC
	|	VIRTUAL
	|	SEALED
	|	OVERRIDE
	|	ABSTRACT
	|	EXTERN 
	| READONLY
	|	UNSAFE
	;

constant_declaration
	:	CONST	type	constant_declarators	SEMI
	;
	
constant_declarators
	:	constant_declarator	(COMMA	constant_declarator)*
	;
	
constant_declarator 
	:	identifier	ASSIGN	expression 
		
	;

field_declaration		//okay I know this is ugly, but as long as it all works out I have saved us an arbitrary look ahead operation
	:	field_declaration_start	(COMMA	field_declarators)?	SEMI			
	;
	
field_declaration_start
	:	(ASSIGN	variable_initializer)?
	;

field_declarators
	:	field_declarator (COMMA   field_declarator)*  
	;

field_declarator
	:	identifier  (ASSIGN   variable_initializer)?
	;

variable_initializer 
	:	(	array_initializer
		|	expression
		|	stackalloc_initializer
		)
	;

method_declaration
	:	LPAREN	(formal_parameter_list)?	RPAREN	body 
	;

body 
	:	( block | SEMI )
	;

formal_parameter_list
	:	( attributes )?	( ( fixed_parameter	( COMMA	( attributes )?	( fixed_parameter | parameter_array ) )* )
														|	parameter_array )
	;

fixed_parameter
	:	(parameter_modifier)?	type	identifier 
	;

parameter_modifier 
	:	REF
	|	OUT 
	;

parameter_array
	//:	PARAMS	non_array_type	rank_specifiers	identifier 
		:	PARAMS	type	identifier 
	;

property_declaration
	:	LBRACE	accessor_declarations	RBRACE 
	;

accessor_declarations
	:	accessor_declaration (accessor_declaration)?
	;

accessor_declaration
	:	(attributes)?	IDENTIFIER	body 
	;

event_declaration
	:	EVENT	type	(		(event_variable_declarator (COMMA	event_variable_declarator )*	SEMI )
										|	(type_name	LBRACE	event_accessor_declarations	RBRACE
											)	
										)
	;

event_variable_declarator
	:	identifier  (ASSIGN   variable_initializer)?
	;

event_accessor_declarations 
	:	accessor_declaration accessor_declaration
	;

indexer_declaration_no_interface
	:	THIS	LBRACK<AST=CodeCritic.Nodes.IndexerNode>	formal_parameter_list	RBRACK	LBRACE	accessor_declarations	RBRACE 
	;
	
indexer_declaration_interface
	:	indexer_base	LBRACK<AST=CodeCritic.Nodes.IndexerNode>	formal_parameter_list	RBRACK	LBRACE	accessor_declarations	RBRACE
	;
	
indexer_base
	:	identifier (DOT (identifier|THIS))*
	;
	
operator_declaration
	:	OPERATOR	(	(overloadable_unary_operator	LPAREN	type	identifier	RPAREN)=>	unary_operator_declaration
							|	binary_operator_declaration )
	;

unary_operator_declaration
	:	overloadable_unary_operator	LPAREN	type	identifier	RPAREN	body
	;

overloadable_unary_operator
	:	PLUS
	|	MINUS
	|	BANG
	|	TILDE
	|	INC
	|	DEC
	|	TRUE
	| FALSE
	;
	
binary_operator_declaration
	:	overloadable_binary_operator	LPAREN	type	identifier	COMMA	type	identifier	RPAREN	body
	;
	
overloadable_binary_operator
	:	PLUS
	|	MINUS
	|	STAR
	|	DIV
	|	MOD
	|	AMPER
	|	BOR
	|	CARET
	|	SL
	|	SR
	|	EQUAL
	|	NOT_EQUAL
	|	GTHAN
	|	LTHAN
	|	GE
	|	LE
	;

conversion_operator_declaration
	:	conversion_operator	OPERATOR	type	LPAREN	type	identifier	RPAREN	body
		
	;

conversion_operator
	:	IMPLICIT
	|	EXPLICIT
	;

constructor_declaration
	:	LPAREN	(formal_parameter_list)?	RPAREN	(constructor_initializer)?	body 
	;

constructor_initializer 
	:	COLON	(BASE | THIS)	LPAREN	(argument_list)?	RPAREN
	;

destructor_declaration
	:	TILDE	identifier	LPAREN	RPAREN	body 
	;

//***********************************************************************************************************************************/
//C.2.8 Structs
//***********************************************************************************************************************************/
struct_declaration
	:	STRUCT   identifier   (base_interfaces)?   class_body   (SEMI)? 
	;

base_interfaces 
	:	COLON   interface_type_list 
	;
	
//***********************************************************************************************************************************/
//C.2.9 Arrays
//***********************************************************************************************************************************/
array_initializer
	:	LBRACE rest_of_array_initializer
	;
rest_of_array_initializer
	:	RBRACE
	|	variable_initializer	(	RBRACE | ( COMMA	rest_of_array_initializer))
	;

//***********************************************************************************************************************************/
//C.2.10 Interfaces
//***********************************************************************************************************************************/
interface_declaration
	:	INTERFACE	identifier	(base_interfaces)?	interface_body   (SEMI)?
			
	;
	
interface_body 
	:	LBRACE   (interface_member_declaration)*   RBRACE 
	;

interface_member_declaration 
	:	(attributes)?	(interface_member_modifiers)?
		(	interface_event_declaration			//EVENT   type   identifier   SEMI 
		|	type	(	interface_indexer_declaration 	//type   THIS   LBRACK   formal_parameter_list   RBRACK   LBRACE   interface_accessors   RBRACE
							|	identifier	(	interface_method_declaration	//type   identifier   LPAREN   (formal_parameter_list)?   RPAREN   SEMI 
															|	interface_property_declaration	//type   identifier   LBRACE   interface_accessors   RBRACE
															)
							)
		)
	;

interface_member_modifiers
	:	(NEW | UNSAFE)+	
	;

interface_event_declaration
	:	EVENT   type   identifier   SEMI 
	;
	
interface_indexer_declaration
	:	THIS	LBRACK	formal_parameter_list	RBRACK	LBRACE	interface_accessors	RBRACE 
	;
	
interface_method_declaration
	:	LPAREN	(formal_parameter_list)?	RPAREN	SEMI 
	;

interface_property_declaration
	:	LBRACE	interface_accessors	RBRACE 
	;

interface_accessors 
	:	interface_accessor (interface_accessor)?
	;

interface_accessor
	:	(attributes)?	IDENTIFIER	SEMI 
	;

//***********************************************************************************************************************************/
//C.2.11 Enums
//***********************************************************************************************************************************/
enum_declaration
	:	ENUM   identifier   (enum_base)?   enum_body   (SEMI)? 
	;
	
enum_base 
	:	COLON   integral_type 
	;

enum_body
	:	LBRACE	rest_of_enum_body	
	;

rest_of_enum_body
	:	RBRACE
	|	enum_member_declaration	(	RBRACE | ( COMMA	rest_of_enum_body))
	;

enum_member_declaration 
	:	(attributes)?	identifier	(enum_member_assignment)?
	;

enum_member_assignment
	:	ASSIGN	expression	
	;

//***********************************************************************************************************************************/	
//C.2.12 Delegates
//***********************************************************************************************************************************/
delegate_declaration
	:	DELEGATE   type   identifier   LPAREN   (formal_parameter_list)?   RPAREN   SEMI 
	;

//***********************************************************************************************************************************/	
//C.2.13 Attributes
//***********************************************************************************************************************************/	
attributes_either
	:	(attribute_section_start)+
	;

attribute_section_start
	:	LBRACK	(	LOOKAHEAD("(ASSEMBLY|MODULE) COLON") global_attribute_target_specifier	attribute_section
								|	(attribute_target_specifier)?	attribute_section
								)
							
	;

global_attribute_target_specifier
	:	global_attribute_target	COLON	
	;

global_attribute_target
	:	ASSEMBLY
	|	MODULE
	;

attributes
	:	(local_attribute)+	
	;
	
local_attribute	
	:	LBRACK	(attribute_target_specifier)?	attribute_section
	;

attribute_section
	:	attribute	(RBRACK	|	COMMA	(RBRACK	|	attribute_section))
	;

attribute_target_specifier
	:	attribute_target   COLON
	;

attribute_target
	:	EVENT
	|	RETURN
	|	IDENTIFIER
	;

attribute
	:	type_name	(attribute_arguments)?
	;

attribute_arguments
	:	LPAREN (expression_list)? RPAREN
	;

//***********************************************************************************************************************************/	
//C.3 Grammar extensions for unsafe code
//***********************************************************************************************************************************/	
sizeof_expression 
	:	SIZEOF LPAREN  type   RPAREN	
	;
	
unsafe_statement
	:	UNSAFE	block
	;
	
pointer_member_access
	:	ARROW	IDENTIFIER	
	;

/*
addressof_expression
	:	AMPER	unary_expression
	;
*/

fixed_statement
	:	FIXED	LPAREN	type	fixed_pointer_declarators	RPAREN	embedded_statement
	;

fixed_pointer_declarators
	:	fixed_pointer_declarator	( COMMA	fixed_pointer_declarator )*
	;

fixed_pointer_declarator
	:	identifier	ASSIGN	fixed_pointer_initializer
	;
 
fixed_pointer_initializer
 	:	(AMPER)?	expression
 	;

stackalloc_initializer
	:	STACKALLOC	non_array_type	LBRACK	expression	RBRACK
	;

// =======================================================
// == LEXER ==============================================
// =======================================================
	
class CSLexer extends Lexer;

options 
{
	k=4;                       // four characters of lookahead
	charVocabulary='\u0003'..'\uFFFF';
	exportVocab=CSharp;
	testLiterals=false;
	codeGenBitsetTestThreshold=20;
}

	
// ***** A.1.7 Keywords *****
tokens
{
	ABSTRACT	=	"abstract";			LONG		=	"long";
	AS			=	"as";				NAMESPACE	=	"namespace";
	BASE		=	"base";				NEW			=	"new";
	BOOL		=	"bool";				NULL		=	"null";
	BREAK		=	"break";			OBJECT		=	"object";
	BYTE		=	"byte";				OPERATOR	=	"operator";
	CASE		=	"case";				OUT			=	"out";
	CATCH		=	"catch";			OVERRIDE	=	"override";
	CHAR		=	"char";				PARAMS		=	"params";
	CHECKED		=	"checked";			PRIVATE		=	"private";
	CLASS		=	"class";			PROTECTED	=	"protected";
	CONST		=	"const";			PUBLIC		=	"public";
	CONTINUE	=	"continue";			READONLY	=	"readonly";
	DECIMAL		=	"decimal";			REF			=	"ref";
	DEFAULT		=	"default";			RETURN		=	"return";
	DELEGATE	=	"delegate";			SBYTE		=	"sbyte";
	DO			=	"do";				SEALED		=	"sealed";
	DOUBLE		=	"double";			SHORT		=	"short";
	ELSE		=	"else";				SIZEOF		=	"sizeof";
	ENUM		=	"enum";				STACKALLOC	=	"stackalloc";
	EVENT		=	"event";			STATIC		=	"static";
	EXPLICIT	=	"explicit";			STRING		=	"string";
	EXTERN		=	"extern";			STRUCT		=	"struct";
	FALSE		=	"false";			SWITCH		=	"switch";
	FINALLY		=	"finally";			THIS		=	"this";
	FIXED		=	"fixed";			THROW		=	"throw";
	FLOAT		=	"float";			TRUE		=	"true";
	FOR			=	"for";				TRY			=	"try";
	FOREACH		=	"foreach";			TYPEOF		=	"typeof";
	GOTO		=	"goto";				UINT		=	"uint";
	IF			=	"if";				ULONG		=	"ulong";
	IMPLICIT	=	"implicit";			UNCHECKED	=	"unchecked";
	IN			=	"in";				UNSAFE		=	"unsafe";
	INT			=	"int";				USHORT		=	"ushort";
	INTERFACE	=	"interface";		USING		=	"using";
	INTERNAL	=	"internal";			VIRTUAL		=	"virtual";
	IS			=	"is";				VOID		=	"void";
	LOCK		=	"lock";				WHILE		=	"while";
	VOLATILE = "volatile";	MODULE = "module";
	ASSEMBLY = "assembly";
}


// ***** Lexical Grammar *****
/*
Input
	:	(Input_section)*
	;
Input_section
	:	(Input_element)*   New_line
	;
Input_element
	:	Whitespace
	|	Comment
	|	Token
	;
*/
// ***** A.1.1 LINE TERMINATORS *****
protected
NEW_LINE
	:	(	// carriage return character followed by possible line feed character	
			{ LA(2)=='\u000A' }? '\u000D' '\u000A'			
		|	'\u000D'			// line feed character							
		|	'\u000A'			// line feed character							
		|	'\u2028'			// line separator character
		|	'\u2029'			// paragraph separator character 
		)
		{newline();}
	;
	
protected
NEW_LINE_CHARACTER
	:	('\u000D' | '\u000A' | '\u2028' | '\u2029')
	;
	
protected
NOT_NEW_LINE
	:	~( '\u000D' | '\u000A' | '\u2028' | '\u2029')
	;
	
	
// ***** A.1.2 WHITESPACE *****
WHITESPACE
	:	(	' '
		|	'\u0009' // horizontal tab character
		|	'\u000B' // vertical tab character
		|	'\u000C' // form feed character 
		|	NEW_LINE 
		)+
		{ _ttype = Token.SKIP; }
	;	
	
	
// ***** A.1.3 COMMENTS *****
SINGLE_LINE_COMMENT
	:	"//" 
		(NOT_NEW_LINE)* 
		(NEW_LINE)
		{_ttype = Token.SKIP;}
	;
	
DELIMITED_COMMENT
	:	"/*"  
		(	{ LA(2)!='/' }? '*'
		|	NEW_LINE		
		|	~('*'|'\u000D'|'\u000A'|'\u2028'|'\u2029')
		)*
		"*/"
		{ _ttype = Token.SKIP; }
	;	

/*	
// ***** A.1.4 TOKENS *****
TOKEN
	:	identifier
	|	KEYWORD
	|	INTEGER_LITERAL
	|	REAL_LITERAL
	|	CHARACTER_LITERAL
	|	STRING_LITERAL
	|	OPERATOR_OR_PUNCTUATOR
	;
*/	
	
// ***** A.1.5 UNICODE character escape sequences *****
UNICODE_ESCAPE_SEQUENCE
	:	('\\' 'u'   HEX_DIGIT   HEX_DIGIT   HEX_DIGIT  HEX_DIGIT)
	|	('\\' 'U'   HEX_DIGIT   HEX_DIGIT   HEX_DIGIT  HEX_DIGIT  
					HEX_DIGIT   HEX_DIGIT   HEX_DIGIT  HEX_DIGIT)
	;

// ***** A.1.6 identifierS *****

IDENTIFIER
options { testLiterals=true; }
	:	IDENTIFIER_START_CHARACTER (IDENTIFIER_PART_CHARACTER)*
//	|	'@' (identifier_PART_CHARACTER)*
	;
	
protected
IDENTIFIER_START_CHARACTER
	:	('a'..'z'|'A'..'Z'|'_'|'$'|'@') //TODO: IDENTIFIER literals can have starting @
	;
	
protected
IDENTIFIER_PART_CHARACTER
	:	('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'$') 
	;
	
// ***** A.1.8 LITERALS *****

/* // move to parser - TODO: look into this...

BOOLEAN_LITERAL
	:	TRUE
	|	FALSE
	;
NULL_LITERAL
	:	NULL
	;
*/

NUMERIC_LITERAL

	// real
	:	('.' DECIMAL_DIGIT) =>
		 '.' (DECIMAL_DIGIT)+ (EXPONENT_PART)? (REAL_TYPE_SUFFIX)?
		{$setType(REAL_LITERAL);}
			
	|	((DECIMAL_DIGIT)+ '.' DECIMAL_DIGIT) =>
		 (DECIMAL_DIGIT)+ '.' (DECIMAL_DIGIT)+ (EXPONENT_PART)? (REAL_TYPE_SUFFIX)?
		{$setType(REAL_LITERAL);}
		
	|	((DECIMAL_DIGIT)+ (EXPONENT_PART)) =>
		 (DECIMAL_DIGIT)+ (EXPONENT_PART) (REAL_TYPE_SUFFIX)?
		{$setType(REAL_LITERAL);}
		
	|	((DECIMAL_DIGIT)+ (REAL_TYPE_SUFFIX)) =>
		 (DECIMAL_DIGIT)+ (REAL_TYPE_SUFFIX)		
		{$setType(REAL_LITERAL);}
		 
	// integer
	|	 (DECIMAL_DIGIT)+ (INTEGER_TYPE_SUFFIX)?	
		{$setType(INTEGER_LITERAL);}
	
	// just a dot
	| '.'{$setType(DOT);}
	;

	
HEXADECIMAL_INTEGER_LITERAL
	:	"0x"   (HEX_DIGIT)+   (INTEGER_TYPE_SUFFIX)?
	|	"0X"   (HEX_DIGIT)+   (INTEGER_TYPE_SUFFIX)?
	;

CHARACTER_LITERAL
	:	"'"!   CHARACTER   "'"!
	;	

/*
STRING_LITERAL
	:	REGULAR_STRING_LITERAL
	|	VERBATIM_STRING_LITERAL
	;
*/

REGULAR_STRING_LITERAL
	:	'\"'!   
		(	rs:REGULAR_STRING_LITERAL_CHARACTER
		)*   
		'\"'!
	;
	
VERBATIM_STRING_LITERAL
{string s="";}
	:	 '@' '"'  	
		(	"\"\""			{s+=("\"");}
		|	'\\'				{s+=("\\\\");}
		|	{ LA(2)=='\u000A' }? '\u000D' '\u000A'	{s+=("\u000D\u000A"); newline();}
		| '\u000D'		{s+=('\u000D'); newline();}
		| '\u000A'		{s+=('\u000A'); newline();}
		| '\u2028'		{s+=('\u2028'); newline();}
		| '\u2029'		{s+=('\u2029'); newline();}
		|	ch:~('\"'|'\\'|'\u000D'|'\u000A'|'\u2028'|'\u2029')	{s+=(ch);}
		)* 
		'"'
		{$setText(s);}
	;

// ===== literal (protected) helpers ============

// nums
protected
DECIMAL_DIGIT
	: 	'0'	|	'1'	|	'2'	|	'3'	|	'4'	|	'5'	|	'6'	|	'7'	|	'8'	|	'9'
	;
protected	
INTEGER_TYPE_SUFFIX
	:
	(	options {generateAmbigWarnings=false;}
		:	"UL"	| "LU" 	| "ul"	| "lu"
		|	"UL"	| "LU" 	| "uL"	| "lU"
		|	"U"		| "L"	| "u"	| "l"
	)
	;
		
protected
HEX_DIGIT
	:	'0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | 
		'A' | 'B' | 'C' | 'D' | 'E' | 'F'  |
		'a' | 'b' | 'c' | 'd' | 'e' | 'f'
	;	
	
protected	
EXPONENT_PART
	:	"e"  (SIGN)*  (DECIMAL_DIGIT)+
	|	"E"  (SIGN)*  (DECIMAL_DIGIT)+
	;	
	
protected
SIGN
	:	'+' | '-'
	;
	
protected
REAL_TYPE_SUFFIX
	: 'F' | 'f' | 'D' | 'd' | 'M' | 'm'
	;

// chars
protected
CHARACTER
	:	SINGLE_CHARACTER
	|	SIMPLE_ESCAPE_SEQUENCE
	|	HEXADECIMAL_ESCAPE_SEQUENCE
	|	UNICODE_ESCAPE_SEQUENCE
	;

protected
SINGLE_CHARACTER
	:	 ~( '\'' | '\\' | '\u000D' | '\u000A' | '\u2028' | '\u2029')
	;
	
protected
SIMPLE_ESCAPE_SEQUENCE
	:	"\\'" | "\\\"" | "\\\\" | "\\0" | "\\a"  
	|	"\\b" | "\\f"  | "\\n"  | "\\r" | "\\t" | "\\v"
	;
	
protected	
HEXADECIMAL_ESCAPE_SEQUENCE	
	:	('\\' 'x' HEX_DIGIT)
		( options {warnWhenFollowAmbig = false;}: 
		HEX_DIGIT 
			( options {warnWhenFollowAmbig = false;}:
			HEX_DIGIT 
				( options {warnWhenFollowAmbig = false;}:
				HEX_DIGIT
				)?
			)?
		)? // oh antlr syntax
	;

// strings

	
protected	
REGULAR_STRING_LITERAL_CHARACTER
	:	SINGLE_REGULAR_STRING_LITERAL_CHARACTER
	|	SIMPLE_ESCAPE_SEQUENCE
	|	HEXADECIMAL_ESCAPE_SEQUENCE
	|	UNICODE_ESCAPE_SEQUENCE
	;
	
protected	
SINGLE_REGULAR_STRING_LITERAL_CHARACTER
	:	 ~( '\"' | '\\' | '\u000D' | '\u000A' | '\u2028' | '\u2029')
	;

/*
protected 
VERBATIM_STRING_LITERAL
	:	'@'!	'\"'!
		( VERBATIM_QUOTE_ESCAPE_SEQUENCE | SINGLE_VERBATIM_STRING_LITERAL_CHARACTER )*
		'\"'!
	;

VERBATIM_QUOTE_ESCAPE_SEQUENCE
	:	'\"'	'\"'
	;

SINGLE_VERBATIM_STRING_LITERAL_CHARACTER
	:	~( '\"' )
	;
*/

	
	
// ***** A.1.9 Operators and punctuators *****
/*
Operator_or_punctuator
	:	'{'	|	'}'	|	'['	|	']'	|	'('	|	')'	|			','	|	':'	|	';'
	|	'+'	|	'-'	|	'*'	|	'/'	|	'%'	|	'&'	|	'|'	|	'^'	|	'!'	|	'~'
	|	'='	|	'<'	|	'>'	|	'?'	|	"++"|	"--"|	"&&"|	"||"|	"<<"|	">>"
	|	"=="|	"!="|	"<="|	">="|	"+="|	"-="|	"*="|	"/="|	"%="|	"&="
	|	"|="|	"^="| "<<="	| ">>=" |	"->"
	;
*/
LBRACE		:	'{'		;	RBRACE		:	'}'		;
LBRACK		:	'['		;	RBRACK		:	']'		;
LPAREN		:	'('		;	RPAREN		:	')'		;

PLUS		:	'+'		;		PLUS_ASN	:	"+="	;	
MINUS		:	'-'		;		MINUS_ASN	:	"-="	;	
STAR		:	'*'		;		STAR_ASN	:	"*="	;
DIV			:	'/'		;		DIV_ASN		:	"/="	;
MOD			:	'%'		;		MOD_ASN		:	"%="	;
INC			:	"++"	;		DEC			:	"--"	;

SL			:	"<<"	;		SL_ASN		:	"<<="	;
SR			:	">>"	;		SR_ASN		:	">>="	;
BSR			:	">>>"	;		BSR_ASN		:	">>>="	;

AMPER		:	'&'		;		BAND_ASN	:	"&="	;	
BOR			:	'|'		;		BOR_ASN		:	"|="	;	
CARET		:	'^'		;		BXOR_ASN	:	"^="	;
TILDE		:	'~'		;

ASSIGN	:	'='		;		EQUAL			:	"=="	;
LTHAN		:	'<'		;		LE				:	"<="	;
GTHAN		:	">"		;		GE				:	">="	;
BANG		:	'!'		;		NOT_EQUAL	:	"!="	;
LOR			:	"||"	;		LAND			:	"&&"	;

COMMA		:	','		;		COLON		:	':'		;	
SEMI		:	';'		;		HASH		:	"#"     ;
QUOTE		:	"\""  ;		QMARK		:	'?'		;

ARROW		:	"->"	;


// ***** A.1.10 Pre_processing directives *****
PP_DIRECTIVE									//screw pre processors, ignore them all
	:	HASH	(PP_MESSAGE_CHAR)+	(NEW_LINE)?
		{ _ttype = Token.SKIP; }
	;
	
protected
PP_MESSAGE_CHAR
	:	('a'..'z'|'A'..'Z'|' '|'	'|'0'..'9'|'`'|'~'|'!'|'@'|'#'|'$'|'%'|'^'|'&'|'*'|'('|')'|'-'|'_'|'='|'+'|'['|']'|'{'|'}'|'|'|':'|';'|'\''|'\"'|'?'|'/'|'\\'|','|'<'|'.'|'>')
	;
	
/*
PP_DIRECTIVE									//screw pre processors, ignore them all
	:	HASH	PP_MESSAGE
		{ _ttype = Token.SKIP; }
	;
protected
PP_MESSAGE
	:	(NOT_NEW_LINE)+ //(NEW_LINE)?
	;
*/
	
/*
PP_DIRECTIVE
	:	 HASH (PP_WHITESPACE)?
		(	dc:PP_DECLARATION	{Console.Write("===>decl: "+dc.getText());}
		| 	rg:PP_REGION		{Console.Write("===>regn: "+rg.getText());}
//		|	PP_CONDITIONAL
//		|	PP_LINE
//		|	PP_DIAGNOSTIC
		)
	{ _ttype = Token.SKIP; }
	;

protected	
PP_NEW_LINE
	:	(PP_WHITESPACE)?	(SINGLE_LINE_COMMENT | NEW_LINE_CHARACTER)
	;

protected	
PP_DECLARATION
	:	(PPT_DEFINE | PPT_UNDEF)	(PP_WHITESPACE)?	CONDITIONAL_SYMBOL PP_NEW_LINE
	;
protected
PPT_DEFINE
	:	"define"
	;
protected
PPT_UNDEF
	:	"undef"
	;
protected
CONDITIONAL_SYMBOL
	:	IDENTIFIER_START_CHARACTER (IDENTIFIER_PART_CHARACTER)*
	;

protected
PP_CONDITIONAL
	:	PP_IF_SECTION	(PP_ELIF_SECTIONS)?	(PP_ELSE_SECTION)?	PP_ENDIF
	;
protected
PP_CONDITIONAL
	:	PP_CONDITIONAL_OP	(PP_WHITESPACE)?	PP_EXPRESSION	PP_NEW_LINE
	;
PP_CONDITIONAL_OP
	:	"if"
	|	"elif"
	;


protected	
PP_REGION
	:	(PPT_REGION | PPT_END_REGION)	PP_MESSAGE	
	;
protected
PPT_REGION
	:	"region"
	;
protected
PPT_END_REGION
	:	"endregion"
	;
protected
PP_MESSAGE
	:	(NOT_NEW_LINE)* NEW_LINE
	;



/*
// skip conditional compilation for now 
protected	
PP_CONDITIONAL
	:	PP_IF_SECTION PP_ENDIF
	;
protected	
PP_IF_SECTION
	:	PPT_IF PP_EXPRESSION PP_NEW_LINE// (CONDITIONAL_SECTION)?
	;
protected	
PP_ENDIF
	:	HASH PPT_ENDIF PP_NEW_LINE
	;
	
protected	
PP_SIGN  
	:	(LOR | LAND | EQUAL | NOT_EQUAL | LNOT)
	;

protected	
PP_EXPRESSION  
	:	PP_PRIMARY_EXPRESSION
		(PP_SIGN PP_PRIMARY_EXPRESSION)?
	;

protected	
PP_OR_EXPRESSION
	:	PP_AND_EXPRESSION (LOR PP_AND_EXPRESSION)?
	;
protected	
PP_AND_EXPRESSION
	:	PP_EQUALITY_EXPRESSION ((PP_WHITESPACE)? LAND  (PP_WHITESPACE)? PP_EQUALITY_EXPRESSION)?
	;
protected	
PP_EQUALITY_EXPRESSION
	:	PP_UNARY_EXPRESSION ((EQUAL | NOT_EQUAL)  PP_UNARY_EXPRESSION)? 
	;
protected	
PP_UNARY_EXPRESSION
	:	PP_PRIMARY_EXPRESSION (LNOT  PP_PRIMARY_EXPRESSION)?
	;

protected	
PP_PRIMARY_EXPRESSION
	:	(PPT_TRUE)=>PPT_TRUE 		{Console.WriteLine("  ===>true ");}
	|	(PPT_FALSE)=>PPT_FALSE 		{Console.WriteLine("  ===>false ");}
	|	LPAREN 
		ex:PP_EXPRESSION 
		RPAREN 						{Console.WriteLine("  ===>expr "+ex.getText());}
	|	((PP_WHITESPACE)? 
		cs:CONDITIONAL_SYMBOL 
		(PP_WHITESPACE)?)  			{Console.WriteLine("  ===>symbol "+cs.getText());}
	;

	
protected
PPT_IF
	:	"if"
	;
protected
PPT_ENDIF
	:	"endif"
	;
protected
PPT_TRUE
	:	"true"
	;
protected
PPT_FALSE
	:	"false"
	;


protected	
PP_ELIF_SECTIONS:
	:	PP_ELIF_SECTION
	|	PP_ELIF_SECTIONS PP_ELIF_SECTION
	;

PP_ELIF_SECTION:
	:	(WHITESPACE)? # (WHITESPACE)? ELIF WHITESPACE PP_EXPRESSION PP_NEW_LINE (CONDITIONAL_SECTION)?
	;
PP_ELSE_SECTION:
	:	(WHITESPACE)? # (WHITESPACE)? ELSE PP_NEW_LINE (CONDITIONAL_SECTION)?
	;
*/
