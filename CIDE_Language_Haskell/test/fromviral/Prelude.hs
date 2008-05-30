module Prelude(
    Bool(False, True), 
    Maybe(Nothing, Just),
    Either(Left, Right),
    Ordering(LT, EQ, GT),

--      The following built-in types are defined in the Prelude, but
--      are denoted by built-in syntax, and cannot legally
--      appear in an export list.
--  List type:  List((:), [])
--  Unit type:  ()(())

    (&&), (||), not, otherwise,
    negate, (+), (-), (*), quot, rem, quotRem, div, mod, (/), recip, (^), (<), (<=), (==), (/=), (>=), (>), 
    fromInt, truncate, round, floor, ceiling,
    signum, abs, compare, max, min,
    ord, chr, subtract, even, odd, gcd, lcm, succ, pred,
    toEnum, fromEnum,
    pi, sqrt, exp, log, sin, cos, asin, acos, atan, atan2,

    maybe, either,

    id, const, (.), flip, ($), 
    fst, snd, curry, uncurry, until,
    error, undefined,

    map, (++), filter, concat, concatMap, head, tail, last, init, null,
    length, (!!), foldl, foldl1, scanl, scanl1, foldr, foldr1, scanr, scanr1,
    iterate, repeat, replicate, cycle, take, drop, 
    splitAt, takeWhile, dropWhile, span, break, 
    lines, words, unlines, unwords, 
    reverse, 
    and, or, any, all, elem, notElem, lookup,
    sum, product, maximum, minimum, 
    zip, zip3, zip4, zipWith, unzip,
    from, fromThen, fromTo, fromThenTo,

    textFile,

--  Type classes 
    Equal(eq, neq),
    Show(shows, show),  
    Read(reads),              
    Edit(style, edit, adjust),     

--  The following identifiers are used in constructing pictures:

    Pic(NoPic, Line, Rect, Poly, Ellipse, PicText, PicImage, SelRect, PicAction, Trans, Super, Animation, Interaction), 
    Font(Serif, SansSerif, Monospaced, Dialog, DialogInput),
    Format(NoFormat, Format),
    Color(RGB, Transparent), 

    imageFile, scaleImage, imageSize, picFile,
    fontSize, defaultFmt,

    black, blue, green, cyan, red, magenta, yellow, white, 
    lightGray, gray, darkGray,
    trans,
    lighten, darken, invColor,

-- The following identifiers are used in constructing styles:
    Style(Hide, Text, Data, List, Picture, This, Comment, Rotate, View),
    VStyle(VHide, VJuxta, VIndiv),
    CStyle(CHide, CText, CImage),
    FStyle(FHide, FText, FImage, FLink),
    Box(NoBox, Box),
    Link(NoLink, Link),
    defaultStyle

)  where {

import Pic;

import Style;

import PreludeText;   

class Equal a where {                        -- A non-standard variant!
  eq, neq  :: a -> a -> Bool;
    
  neq x y = not (x `eq` y)
};
                                  
class Show a where {                            
  shows :: a -> String -> String;
  show  :: a -> String;

  show s = shows s ""
};

-- The first argument of "reads" is required by the dynamic class mechanism
class Read a where 
  reads :: a -> String -> [(a, String)];

type Path     = [Int];

type EditFn a = a -> Path -> Int -> Int -> String -> String -> String ->
                      (Maybe String, Maybe String, Maybe String);

type AdjustFn a = a -> Path -> Int -> Int -> String -> String ->          
                      (Maybe String, Maybe String);

class Edit a where {  
  style   :: a -> Style;   
  edit    :: a -> Path -> [(String, ChangeFn a)];                         
  adjust  :: adjustFn a;

  style x = defaultStyle                                -- Used if no appropriate instance of Edit.style is defined
};


-- Arithmetic primitives

primitive primNegate        :: a -> a;                  -- For types in {Int, Double}
primitive primAbs           :: a -> a;
primitive primSignum        :: a -> a;
primitive primPlus          :: a -> a -> a;      
primitive primMinus         :: a -> a -> a;
primitive primMul           :: a -> a -> a;

primitive primDivideDouble  :: Double -> Double -> Double;

primitive primQuotInt       :: Int -> Int -> Int;
primitive primRemInt        :: Int -> Int -> Int;

primitive primLT            :: a -> a -> Bool;         -- For types in {Int, Double}  [Should also incl Char]
primitive primLTE           :: a -> a -> Bool;
primitive primGTE           :: a -> a -> Bool;
primitive primGT            :: a -> a -> Bool;

primitive primEq	    :: a -> a -> Bool;	       -- For types in {Int, Char, Double}

primitive primPred          :: a -> a;                 -- For types in (Int, Char}
primitive primSucc          :: a -> a;

primitive primIntToDouble   :: Int -> Double;
primitive primTruncate      :: Double -> Int;
primitive primRound         :: Double -> Int;
primitive primFloor         :: Double -> Int;
primitive primCeiling       :: Double -> Int;

primitive primFromEnum      :: a -> Int;
primitive primToEnum        :: a -> Int -> a;          -- The first arg is a witness for the desired type

-- Floating-point primitives

primitive primPiDouble      :: Double;
primitive primSqrtDouble    :: Double -> Double;
primitive primExpDouble     :: Double -> Double;
primitive primLogDouble     :: Double -> Double;
primitive primSinDouble     :: Double -> Double;
primitive primCosDouble     :: Double -> Double;

primitive primASinDouble    :: Double -> Double;
primitive primACosDouble    :: Double -> Double;
primitive primATanDouble    :: Double -> Double;
primitive primATan2Double   :: Double -> Double -> Double;

-- Misc primitives

primitive primError         :: String -> a;

-- -------------------------------------------------------

infixr 9 .;
infixr 8 ^, ^^, **;
infixl 7 *, /, `quot`, `rem`, `div`, `mod`;
infixl 6 +, -;
-- infixr 5  :
infixr 5 ++;
infix  4 ==, /=, <, <=, >, >=, `elem`, `notElem`;
infixr 3 &&;
infixr 2 ||;
infixr 0 $;


-- Equality on primitive types (Char, Int, Double)
(==)                    :: a -> a -> Bool;
(==)                    =  primEq;


-- Numeric functions
subtract                :: Int -> Int -> Int;
subtract                =  flip (-);

even, odd               :: Int -> Bool;
even n                  =  (n `rem` 2) == 0;
odd                     =  not . even;

x ^ n                   =  if n == 0 then 1 
                           else if n == 1 then x 
                           else let {m = n `quot` 2; a = x ^ m; b = a * a} 
                                in if even n then b else b * x;

-- gcd, lcm   

gcd m n                 =  if n == 0 then m else gcd n (m `rem` n);

lcm m n                 =  (m `quot` (gcd m n)) * n;

-- Enumerations

fromEnum                :: a -> Int;                -- For Char, Int and datatypes
fromEnum                =  primFromEnum;

toEnum                  :: a -> Int -> a;           -- For Char, Int and datatypes
toEnum                  =  primToEnum;              -- The first arg is a witness for the desired type

-- Trivial type
-- data () = ()

-- Function type

-- identity function
id                      :: a -> a;
id x                    =  x;

-- constant function
const                   :: a -> b -> a;
const x y               =  x;       
			
-- function composition
(.)                     :: (b -> c) -> (a -> b) -> (a -> c);
(f . g) x               =  f (g x);

-- flip: reverses its args
flip                    :: (a -> b -> c) -> (b -> a -> c);
flip f x y              =  f y x;

-- right-associative infix application operator
($)                     :: (a -> b) -> (a -> b);
f $ x                   =  f x;

-- Boolean type (wired-in to the interpreter)
-- data Bool = False | True;

-- Boolean functions
(&&), (||)              :: Bool -> Bool -> Bool;
x && y                  =  if x then y else False;
x || y                  =  if x then True else y;

-- not                  :: Bool -> Bool;
not x                   =  if x then False else True;

otherwise               =  True;

-- Character type

-- data Char            =  . . . 'a' | 'b' . . . 	-- Unicode values

ord                     :: Char -> Int;
ord                     =  primFromEnum;

chr                     :: Int -> Char;
chr                     =  primToEnum 'a';


-- type String          =  [Char];

-- Maybe type  (wired-in to the interpreter)

-- data Maybe a         =  Nothing 
--                      |  Just a;

maybe                   :: b -> (a -> b) -> Maybe a -> b;
			
maybe n f a             =  case a of {
                             Nothing -> n;
                             Just x  -> f x
                           };

-- Either type
		
data Either a b         =  Left a 
                        |  Right b;

either                  :: (a -> c) -> (b -> c) -> Either a b -> c;

either f g xy           =  case xy of {
                             Left x   -> f x;
                             Right y  -> g y
                           };
 				
-- Ordering type
			
data Ordering           = LT | EQ | GT;


-- Standard numeric types
			
negate                  =  primNegate;
(+)                     =  primPlus;
(-)                     =  primMinus;
(*)                     =  primMul;

quot                    =  primQuotInt;
rem                     =  primRemInt;
quotRem n m             =  (quot n m, rem n m);

div                     =  quot;             -- Faut de mieux
mod                     =  rem;              -- ditto

(/)                     =  primDivideDouble;
recip                   =  primDivideDouble 1.0;

fromInt                 =  primIntToDouble;
truncate                =  primTruncate;
round                   =  primRound;
floor                   =  primFloor;
ceiling                 =  primCeiling;

(<)                     =  primLT;
(<=)                    =  primLTE;
(>=)                    =  primGTE;
(>)                     =  primGT;

x /= y                  =  not (x == y);

compare n m             =  if n == m then EQ else if n < m then LT else GT;

max n m                 =  if n <= m then m else n;
min n m                 =  if n <= m then n else m;

pred                    =  primPred;
succ                    =  primSucc;

signum                  =  primSignum;
abs                     =  primAbs;

pi                      =  primPiDouble;
sqrt                    =  primSqrtDouble;
exp                     =  primExpDouble;
log                     =  primLogDouble;
sin                     =  primSinDouble;
cos                     =  primCosDouble;

asin                    =  primASinDouble;
acos                    =  primACosDouble;
atan                    =  primATanDouble;
atan2                   =  primATan2Double;


-- Lists

-- data List a          =  [] | a : List a

-- Tuples

fst                     :: (a,b) -> a;
fst p                   =  case p of { (x,y) -> x };

snd                     :: (a,b) -> b;
snd p                   =  case p of { (x,y) -> y };

curry                   :: ((a,b) -> c) -> a -> b -> c;
curry f x y             =  f (x, y)

uncurry                 :: (a -> b -> c) -> ((a,b) -> c);
uncurry f p             =  f (fst p) (snd p);


-- Misc functions

until                   :: (a -> Bool) -> (a -> a) -> (a -> a);
until p f x             =  if p x then x else until p f (f x);

error                   :: String -> a;
error                   =  primError;

undefined               :: a;
undefined               =  error "Prelude.undefined";


-- Standard list functions

infixl 9 !!;
infixr 5 ++;
infix  4 `elem`, `notElem`;

map                     :: (a -> b) -> [a] -> [b];

map f xs                =  case xs of {
                             []    -> [];
                             y:ys  -> f y : map f ys
                           };

(++)                    :: [a] -> [a] -> [a];

xs ++ ys                =  case xs of {
                             []      -> ys;
                             z : zs  -> z : (zs ++ ys)
                           };

filter                  :: (a -> Bool) -> [a] -> [a];

filter p xs             =  case xs of {
                             []      -> [];
                             y : ys  -> let zs = filter p ys in 
                                        if p y then y : zs else zs
                           };

concat                  :: [[a]] -> [a];

concat xss              =  foldr (++) [] xss;

concatMap               :: (a -> [b]) -> [a] -> [b];

concatMap f             =  concat . map f;


head xs                 =  case xs of { 
                             []     -> error "Prelude.head: empty list";
                             y : _  -> y
                           };

tail xs                 =  case xs of { 
                             []      -> error "Prelude.tail: empty list";
                             _ : ys  -> ys
                           };

last xs                 =  case xs of {
                             []       -> error "Prelude.last: empty list";
                             x : []   -> x;
                             _ : xs   -> last xs
                           };

init xs                 =  case xs of {
                             []       -> error "Prelude.init: empty list";
                             _ : []   -> [];
                             x : xs   -> x : init xs
                           };

null xs                 =  case xs of { [] -> True; _ -> False};

length xs               =  case xs of { 
                             []      -> 0; 
                             _ : ys  -> succ (length ys)
                           };

-- List index (subscript) operator, 0-origin

xs !! n                 =  if n < 0 then
                             error "Prelude.!!: negative index"
                           else if null xs then
                             error "Prelude.!!: index too large"
                           else if n == 0  then
                             head xs
                           else (tail xs) !! (pred n);   

foldl                   :: (a -> b -> a) -> a -> [b] -> a;

foldl f z xs            =  case xs of {
                             []    ->  z;
                             y:ys  ->  foldl f (f z y) ys 
			   };

foldl1                  :: (a -> a -> a) -> [a] -> a;

foldl1 f xs             =  case xs of {
                             []    ->  error "Prelude.foldl1: empty list";
                             y:ys  ->  foldr f y ys
                           };

scanl                   :: (a -> b -> a) -> a -> [b] -> [a];

scanl f q xs            =  q : (case xs of {
                                  []     ->  [];
                                  y:ys   ->  scanl f (f q y) ys
                                });

scanl1                  :: (a -> a -> a) -> [a] -> [a];

scanl1 f xs             =  case xs of {
                             []    -> [];
                             y:ys  -> scanl f y ys
                           };
                              
-- foldr, foldr1, scanr, scanr1 are the right-to-left duals of the above functions

foldr                   :: (a -> b -> b) -> b -> [a] -> b;

foldr f z xs            =  case xs of {
                             []    -> z;
                             y:ys  -> f y (foldr f z ys)
                           };

foldr1                  :: (a -> a -> a) -> [a] -> a;

foldr1 f xs             =  case xs of {
                             []     ->  error "Prelude.foldr1: empty list";
                             y:[]   ->  y;
                             y:ys   ->  f y (foldr1 f ys)
                           };

scanr                   :: (a -> b -> b) -> b -> [a] -> [b];

scanr f q0 xs           =  case xs of {
                             []     ->  [q0];
                             y:ys   ->  let { qs = scanr f q0 ys;
                                              q  = head qs} 
                                        in f y q : qs
                           };

scanr1                  :: (a -> a -> a) -> [a] -> [a];

scanr1 f xs             =  case xs of {
                             []     ->  [];
                             y:[]   ->  [y];
                             y:ys   ->  let { qs = scanr1 f ys;
                                              q  = head qs}
                                        in f y q : qs
                           };

iterate                 :: (a -> a) -> a -> [a];

iterate f x             =  x : iterate f (f x);

repeat                  :: a -> [a];

repeat x                =  let xs = x:xs in xs;

replicate               :: Int -> a -> [a];

replicate n x           =  take n (repeat x);

cycle                   :: [a] -> [a];

cycle xs                =  case xs of {
                             []    ->  error "Prelude.cycle: empty list";
                             y:ys  ->  let xs' = xs ++ xs' in xs'
                           };

take                    :: Int -> [a] -> [a];

take n xs               =  if n <= 0 then
                             []
                           else 
                             case xs of {
                               []    ->  [];
                               y:ys  ->  y : take (pred n) ys
                             };

drop                    :: Int -> [a] -> [a];

drop n xs               =  if n <= 0 then
                             xs
                           else
                             case xs of {
                               []    ->  [];
                               y:ys  ->  drop (pred n) ys
                             };

splitAt                 :: Int -> [a] -> ([a],[a]);

splitAt n xs            =  (take n xs, drop n xs);

takeWhile               :: (a -> Bool) -> [a] -> [a];

takeWhile p xs          =  case xs of {
                             []    ->  [];
                             y:ys  ->  if p y then
                                         y : takeWhile p ys
                                       else
                                         []
                           };

dropWhile               :: (a -> Bool) -> [a] -> [a];

dropWhile p xs          =  case xs of {
                             []    ->  [];
                             y:ys  ->  if p y then
                                         dropWhile p ys
                                       else
                                         xs
                           };

span, break             :: (a -> Bool) -> [a] -> ([a],[a]);

span                    =  error "Prelude.span: Not yet defined";  

break                   =  error "Prelude.break: Not yet defined"; 


lines                   =  error "Prelude.lines: Not yet defined";
words                   =  error "Prelude.words: Not yet defined";
unlines                 =  error "Prelude.unlines: Not yet defined";
unwords                 =  error "Prelude.unwords: Not yet defined";

reverse                 :: [a] -> [a];


reverse                 =  foldl (flip (:)) [];

and, or                 :: [Bool] -> [Bool];

and                     =  foldr (&&) True;

or                      =  foldr (||) False;

any, all                :: (a -> Bool) -> [a] -> Bool;

any p                   =  or . map p;

all p                   =  and . map p;

elem, notElem           :: a -> [a] -> Bool;

elem x                  =  let f = (==) x in any f;

notElem x               =  let f = (/=) x in all f;

lookup                  :: a -> [(a,b)] -> Maybe b;

lookup key assocs       =  case assocs of {
                             (x,y) : assocs' -> if key == x then Just y else lookup key assocs';
                             []              -> Nothing
                           };

sum, product            :: [Int] -> Int;

sum                     =  foldl (+) 0;

product                 =  foldl (*) 1;

maximum, minimum        :: [Int] -> Int;

maximum ns              =  case ns of {
                             []    ->  error "Prelude.maximum: empty list";
                             _     ->  foldl1 max ns
                           };

minimum ns              =  case ns of {
                             []    ->  error "Prelude.minimum: empty list";
                             _     ->  foldl1 min ns
                           };

zip                     :: [a] -> [b] -> [(a,b)];

zip xs ys               =  case xs of {
                             []    ->  [];
                             u:us  ->  case ys of {
                                         []    ->  [];
                                         v:vs  ->  (u,v) : zip us vs
                                       }
                           };

zip3                    :: [a] -> [b] -> [c] -> [(a,b,c)];

zip3 xs ys zs           =  case (xs, ys, zs) of {
                             ((x:xs),(y:ys),(z:zs)) -> (x,y,z) : zip3 xs ys zs;
                             _                      -> []
                           };

zip4                    :: [a] -> [b] -> [c] -> [d] -> [(a,b,c,d)];

zip4 ws xs ys zs        =  case (ws, xs, ys, zs) of {
                             ((w:ws),(x:xs),(y:ys),(z:zs)) -> (w,x,y,z) : zip4 ws xs ys zs;
                             _                      -> []
                           };

zipWith                 :: (a -> b -> c) -> [a] -> [b] -> [c];

zipWith f xs ys         =  case xs of {
                             u:us -> case ys of {
                                       v:vs -> f u v : zipWith f us vs;
                                       _    -> []
                                     };
                             _    -> []
                           };

unzip                   :: [(a,b)] -> ([a],[b]);

-- NB: This definition works only for *finite* lists (it would need strictness annotations on the patterns
--     in order to work with infinite ones).
unzip xys               =  case xys of {
                             []        ->  ([], []);
                             (u,v):uvs -> case unzip uvs of {
                                            (us,vs) -> ((u:us),(v:vs))
                                          }
                           };

-- Input (eager) of a text file. (The filename is interpreted relative to the "files" sub-directory)
primitive primTextFile :: String -> String;
textFile = primTextFile;

-- -----------------------------------------------------------------------------------------------------

-- These functions are used in the implementation of arithmetic progression list constructs (such
-- as [1,3 ..]). They are exported.

from n = n : from (n + 1);

fromThen n m = n : fromThen m (m + (m - n));

fromTo n m = if n > m then [] else n : fromTo (succ n) m;

fromThenTo n m k = if n > k then [] else n : fromThenTo m (m + (m - n)) k

}



