module Arith
where

--
-- Expression
--

data Exp a = Const a
           | Binary BinOp (Exp a) (Exp a)
           | Unary  UnOp  (Exp a)
           | Lam String (Exp a)
           | App (Exp a) (Exp a)
           deriving Show

data BinOp = Add | Sub | Mul | Div deriving Show

data UnOp = Neg | Recip deriving Show


--
-- Evaluation result
--

data Result a err = Result a
                  | Fail err
                  deriving Show

mapResult :: (a -> Result b err) -> Result a err -> Result b err
mapResult f (Result x) = f x
mapResult f (Fail   e) = Fail e

zipResult :: (a -> b -> Result c err)
          -> Result a err -> Result b err -> Result c err
zipResult f (Result x) (Result y) = f x y
zipResult _ (Fail   e) _          = Fail e
zipResult _ _          (Fail e)   = Fail e

liftResult :: [Result a err] -> Result [a] err
liftResult [] = Result []
liftResult (Result x : rest) =
    case liftResult rest of
        Result xs -> Result (x:xs)
        Fail e    -> Fail e
liftResult (Fail e : _) = Fail e


--
-- Evaluation error
--

data EvalError = DivByZero
               | Overflow
               | TypeError
               | ApplicationError
               deriving Show

--
-- Repesentation of typed values
--

data TypedVal = TVString String
              | TVDouble Double
              | TVFun    (Result TypedVal EvalError -> Result TypedVal EvalError)

instance Show TypedVal where
   show (TVString s) = show s
   show (TVDouble d) = show d
   show (TVFun _)    = "<<function>>"

--
-- Evaluation for (dynamically) typed values
--

eval :: Exp TypedVal
     -> Result TypedVal EvalError
eval (Const x)  = Result x
eval (Binary op exp1 exp2) =
    zipResult (tvBinOp op) (eval exp1) (eval exp2)
eval (Unary op exp) = mapResult (tvUnOp op) (eval exp)
eval (Lam x exp) =
    Result $ TVFun (\_ -> eval exp)
eval (App exp1 exp2) = 
    case (eval exp1, eval exp2) of
        (Result (TVFun f), arg) -> f arg
        (Result _, _) -> Fail ApplicationError
        (Fail err, _) -> Fail err


tvBinOp :: BinOp -> TypedVal -> TypedVal -> Result TypedVal EvalError
tvBinOp Add (TVString s) (TVString t) = Result (TVString (s++t))
tvBinOp Add (TVString s) (TVDouble y) = Result (TVString (s++show y))
tvBinOp Add (TVDouble x) (TVString t) = Result (TVString (show x++t))
tvBinOp Add (TVDouble x) (TVDouble y) = Result (TVDouble (x+y))
tvBinOp Sub (TVDouble x) (TVDouble y) = Result (TVDouble (x-y))
tvBinOp Mul (TVDouble x) (TVDouble y) = Result (TVDouble (x*y))
tvBinOp Div (TVDouble x) (TVDouble 0) = Fail DivByZero
tvBinOp Div (TVDouble x) (TVDouble y) = Result (TVDouble (x/y))
tvBinOp _ _ _ = Fail TypeError

tvUnOp :: UnOp -> TypedVal -> Result TypedVal EvalError
tvUnOp Neg   (TVDouble x) = Result (TVDouble (negate x))
tvUnOp Recip (TVDouble 0) = Fail DivByZero
tvUnOp Recip (TVDouble x) = Result (TVDouble (recip x))
tvUnOp _ _ = Fail TypeError


--
-- Main evaluation function
--

evalExp :: Exp TypedVal -> Result TypedVal EvalError
evalExp exp = eval exp