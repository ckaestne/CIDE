Statement
	: While
	| If
	| Require
	;

ElseClause
	: "else" ThenClause :: ElseNode
	;

If
	: "if" Test ThenClause [ElseClause] :: IfNode
	;

Require
	: REQUIRE IDENTIFIER :: RequireNode
	; 

Test
	: "(" IDENTIFIER ")" :: TestNode
	;

ThenClause
	: "{" Statement "}" :: ThenNode
	; 

While
	: "while" Test ThenClause :: WhileNode
	;