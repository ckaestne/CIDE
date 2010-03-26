grammar completeXXLQS002 ;

emptyproduction : queryspecification  ;
queryspecification :  SELECT (SETQUANTIFIER)? selectlist tableexpression; 
selectlist :  selectsublist ( COMMA selectsublist )*   ;
selectsublist : derivedcolumn  ;
tableexpression :  fromclause  whereclause?    ;
fromclause : FROM tablereferencelist  ;
derivedcolumn :    valueexpression asclause?   ;
valueexpression : commonvalueexpression  ;
commonvalueexpression : stringvalueexpression  ;
stringvalueexpression : charactervalueexpression  ;
charactervalueexpression : characterfactor  ;
characterfactor : characterprimary  ;
characterprimary : valueexpressionprimary  ;
valueexpressionprimary : nonparenthesizedvalueexpressionprimary  ;
nonparenthesizedvalueexpressionprimary : columnreference  ;
columnreference : basicidentifierchain  ;
basicidentifierchain : identifierchain  ;
identifierchain : identifier  ;
tablereferencelist : tablereference  ;
tablereference : tableprimaryorjoinedtable  ;
tableprimaryorjoinedtable : tableprimary  ;
tableprimary : tableorqueryname  ;
tableorqueryname : tablename  ;
tablename : qualifiedidentifier  ;
qualifiedidentifier : identifier  ;

asclause :    AS? columnname   ;
columnname :    identifier   ;

whereclause :     WHERE  searchcondition   ;
searchcondition :    booleanvalueexpression    ;
booleanvalueexpression :  booleanterm (OR booleanterm)*   ;
booleanterm :   booleanfactor (AND booleanfactor)*   ;
booleanfactor :    NOT? booleantest   ;
booleantest :    booleanprimary ( IS  NOT?  truthvalue )?   ;
truthvalue :  TRUE| FALSE| UNKNOWN ;
booleanprimary :      booleanpredicand   ;
booleanpredicand :   nonparenthesizedvalueexpressionprimary   ;


SELECT :  'select'    ;
FROM :    'from'   ;
PERIOD :  '.'   ;
ASTERISK :  '*'   ;

COMMA :  ','   ;

AS :  'AS'    ;

WHERE :  'where'   ;
OR :  'OR'   ;
AND :  'AND'   ;
NOT :  'NOT'   ;
IS :  'IS'   ;
TRUE :  'TRUE'   ;
FALSE :  'FALSE'   ;
UNKNOWN :  'UNKNOWN'   ;
LEFTPARAN :  '('  ;
RIGHTPARAN :  ')'   ;

SETQUANTIFIER :  'DISTINCT' | 'ALL'   ;


identifier : ID ;
ID : ('a'..'z'|'A'..'Z')+ ; 
DIGIT : '0'..'9'+ ; 
SEMICOLON : ';' ; 
NEWLINE:'\r'? '\n' ;
WS : (' '|'\t'|'\n'|'\r')+;