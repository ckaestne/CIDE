<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


  
  
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


  
  
  
  
  <title>The FeatureBNF Grammar Specification Language (formerly gCIDE)</title>
  <link href="ar.css" rel="stylesheet" type="text/css" />


  
  
  <meta content="Christian Kaestner" name="author" />
</head>


<body>


	
	
	
	
<h1>The FeatureBNF Grammar Specification Language (formerly gCIDE)</h1>




<h2>Basics</h2>



<p>To create a language plug-in for <a href="http://wwwiti.cs.uni-magdeburg.de/iti_db/research/cide">CIDE</a>, the FeatureBNF grammar for that language is processed by <em>astgen</em>. 
This tool generates classes for an AST and a JavaCC grammar, that has to be subsequentially processed
by <a href="https://javacc.dev.java.net">JavaCC</a> to create a parser. Finally the generated classes are adapted for the 
<em>de.ovgu.cide.core.language</em> extension point using the <em>ILanguageExtension</em> interface.</p>



<p>Due to this process (first processing by <em>astgen</em>, then by JavaCC) the grammar consists of two parts.
The first part contains instructions for JavaCC, especially declarations for the lexer, that are not
required by <em>astgen</em>. These instructions are directly in the JavaCC format and are not preprocessed by
<em>astgen</em>. Please refer to the JavaCC documentation for details.</p>



<p>The second part consists of annotated productions that are processed by <em>astgen</em>. Both parts are separated
by the keyword GRAMMARSTART. The part after GRAMMARSTART consists only of productions in the following 
form:</p>



<div class="code">ProductionName : Tokens :: ChoiceName | Tokens :: ChoiceName | ... ;</div>



<p>A production can consist of multiple choices, each of which can consist of a sequence of tokens, and can  
(but must not) be named (names must not collide with production names). Tokens
can be non-terminals that reference other productions, tokens from the lexer written in pointy brackets,
constant texts written in double quotes or special annotations we explain below. For example</p>



<div class="code">ConstructorDeclaration: [ TypeParameters ]
&lt;IDENTIFIER&gt; FormalParameters [ "throws" NameList ] "{" [
ExplicitConstructorInvocation ] ( BlockStatement )* "}" ;</div>



<p>Possible modifieres are (X)* for multiple items, (X)+ for one or more items, [X] or (X)? for optional items.
X can be a sequence of tokens, that however can only contain a single non-terminal or or lexer-token 
(if more are required, these must be refactored into an own production, if only constants are required
add the pseudo lexer token &lt;NONE&gt;).


</p>

<h2>Annotations for Lists</h2>




<p>In case list items are parsed by a production, but cannot be parsed only with the (X)* modifier,
e.g., in 

</p>

<div class="code">Production: &lt;IDENTIFIER&gt;  ":"  Choice  ( "|" Choice )* ";" ;</div>



<p>add a &amp;LI annotation before every list element. These will then be combined in a single list in 
the created AST.

</p>

<div class="code">Production: &lt;IDENTIFIER&gt;  ":"  &amp;LI Choice  ( "|" &amp;LI Choice )* ";" ;</div>



<h2>Optional Elements</h2>




<p>If elements that are mandatory by the grammar should be colored anyway, they can be annotated
as optional in the grammar using the OPTIONAL keyword. The optional keyword wraps a token and
provides the default value that is inserted when the item is removed.

</p>

<div class="code">FieldDeclaration: OPTIONAL(Type,"int") VariableDeclarator ";" ;</div>



<h2>Wrappers</h2>




<p>Wrappers require two annotations, one where they are used and one to determine which element is wrapped.
Consider the following example:

</p>

<div class="code">Statement: Block | StatementExpression | IfStatement{Statement} | ...;<br />


	IfStatement: "if" "(" Expression ")" Statement! ;</div>



<p>The IfStatement may warp around the "then" statement. To define this, the IfStatement is annotated with {X}
that contains the type of the wrapped element. Note, this annotation is defined where IfStatement is referenced
in Statement, it is not defined in the IfStatement production itself. Inside the IfStatement production
the wrapped element is identified with a ! as annotation after the element.

</p>

<h2>Lookahead</h2>



<p>Lookahead works exactly as in JavaCC, however the notation is slightly different. First, the 
keyword is LL instead of LOOKAHEAD, second if a semantic lookahead is to be used it must be
written in double quotes. Example which shows both use cases:

</p>

<div class="code">TypeDeclaration: LL(2)  Modifiers  ClassOrInterfaceDeclaration 	| LL("Modifiers()")  Modifiers  EnumDeclaration;</div>



<h2>Pretty printer annotations</h2>



<p>The pretty printer is automatically created from the grammar. By default it just
returns a sequence of tokens separated by a single whitespace following some heuristics.
These heuristics can changed or turned off by overwriting the generated SimplePrintVisitor.
If whitespaces should be preserved completely, they must be parsed as special tokens by the lexer 
(see JavaCC manual).

</p>

<p>Linebreaks and indentation can be specified using grammar annotations. @+ specifies that all following
lines should be indented, @- reverses this. @! specifies that a line break should be added at this position.
With @~ it is even possible to specify that a single whitespace should be added.
The annotations can be combined e.g., @+!

</p>

<p>Example:
</p>

<div class="code">PackageDeclaration: "package" Name ";" @!;<br />


	ClassOrInterfaceBody: "{" @+! ( ClassOrInterfaceBodyDeclaration @! @! )*  @-! "}" ;</div>




<h2>JavaCC Specials</h2>



<p>If Java code is to be inserted in the created JavaCC grammar, it can be done with a special JAVA 
annotation, that contains the Java code in double quotes.

</p>

<div class="code">StorageClassSpecifier: &lt;AUTO&gt; |  &lt;REGISTER&gt; |  &lt;TYPEDEF&gt; JAVA("typedefParsingStack.push(Boolean.TRUE);") ;</div>



<p>If the lexer is extended with JAVACODE in JavaCC, it can be used with the JAVATOKEN annotation, e.g.,

</p>

<div class="code">Grammar : JAVATOKEN(findIntroductionBlock) (Production)*  &lt;EOF&gt;;</div>




<h2>Example</h2>



<pre class="code">PARSER_BEGIN(JavaParser)<br />package tmp.generated_simple;<br /><br />import java.io.*;<br />import java.util.*;<br />import cide.gast.*;<br />import cide.gparser.*;<br /><br />public class JavaParser {}<br />PARSER_END(JavaParser)<br /><br />SKIP : {<br />  " "<br />| "\t"<br />| "\n"<br />| "\r"<br />| "\f"<br />}<br /><br />TOKEN :<br />{<br />  &lt; ABSTRACT: "abstract" &gt;<br />| &lt; ASSERT: "assert" &gt;<br />| &lt; BOOLEAN: "boolean" &gt;<br />| &lt; BREAK: "break" &gt;<br />}<br /><br />TOKEN :<br />{<br />  &lt; INTEGER_LITERAL:<br />        &lt;DECIMAL_LITERAL&gt; (["l","L"])?<br />      | &lt;HEX_LITERAL&gt; (["l","L"])?<br />      | &lt;OCTAL_LITERAL&gt; (["l","L"])?<br />  &gt;<br />}<br /><br />// ... more lexer instructions here<br /><br />GRAMMARSTART<br /><br />CompilationUnit: (TypeDeclaration)* &lt;EOF&gt;;<br /><br />TypeDeclaration: "class" Name [ "extends" Name ] ClassBody @! @!;<br /><br />Name: &lt;IDENTIFIER&gt;;<br /><br />ClassBody: "{" @+! (Member)* @-! "}";<br /><br />Member: Method | Field;<br />Method: "void" Name "(" ")" Block;<br />Field: Name ";" @!;<br /><br />Block: "{" @+! (Statement)* @-! "}" @!;<br /><br />Statement: <br />	LL(2) MethodInvocation ";" @! | <br />	IfStatement{Block} | <br />	Assignment ";" @! | <br />	Block;<br /><br />MethodInvocation: Name "(" ")";<br />Assignment: Name "=" Expression;<br />IfStatement: "if" "(" Expression ")" Block! [ "else" Block ];<br /><br />Expression: LL(2) BinaryExpression | UnaryExpression;<br />UnaryExpression: &lt;INTEGER_LITERAL&gt;;<br />BinaryExpression: OPTIONAL(UnaryExpression, "0") "+" OPTIONAL(Expression, "0");<br /></pre>


</body>
</html>
