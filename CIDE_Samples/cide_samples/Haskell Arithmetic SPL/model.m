Arith_ : Arith+ :: _Arith ;

Arith : PF_NoVars
	| Lazy_Vars
	| ITE
	| Lambdas
	| Lazy_Lambdas
	| Strict_Lambdas
	| Dynamic_Lambdas
	| Strict_Vars
	| Vars
	| EvalError
	| BinOps
	| UnOps
	| Bools
	| Windows
	| Linux
	| Static_Lambdas ;

