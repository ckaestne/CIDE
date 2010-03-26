TOKEN : {
    <COMPARISON : "==" | "!=">
    | <IDENTIFIER : <LETTER> (<LETTER> | <DIGIT>)*>
    | <INTEGER : <DIGIT> <DIGIT>*>
    | <#LETTER: ["a"-"z", "A"-"Z"]>
    | <#DIGIT: ["0"-"9"]>
}

Program : Statement (";" Statement)* ;

Statement : Assignment | Control ;

Assignment : IDENTIFIER "=" Expression :: AssignmentNode ;

Expression : Operand [Addition] :: ExpressionNode ;

Operand : IDENTIFIER :: IdentifierOperand
	| INTEGER :: IntegerOperand ;

Addition : "+" Expression :: AdditionNode ;

Control : If | While ;

If : "if" Test ThenClause [ElseClause] :: IfNode ;

Test : "(" Expression COMPARISON Expression ")" :: TestNode ;

ThenClause : "then" Assignment :: ThenNode ;

ElseClause : "else" Assignment :: ElseNode ;

While : "while" Test "do" Assignment :: WhileNode ;
