-- A set of simple parsing functions

module Parse (
  nullSeq, sat, char, eos, letter, letterLC, letterUC, digit, alphameric, 
  (<>), (>>), (~>), many, many1,
  numeral, identifier, operator,
  spaced, bracketed, sqBracketed, string,
  integer,
  intList,
  exact, unique, parser
) where {

  import Char;

  type Reads a = String -> [(a, String)];

  sat :: (Char -> Bool) Reads Char;
  sat pred cs =
    case cs of {
      []    -> [];
      c:cs  -> if pred c then [(c,cs)] else []
    };

  char :: Char -> Reads Char;
  char c = sat (== c);

  -- Test for end of string
  eos :: Reads ();
  eos s = case s of {
            ""  -> [((), "")];
            _   -> []
          };

  letter, letterLC, letterUC, digit, alphameric, opChar, space :: Reads Char;
  letter       = sat isAlpha;
  letterLC     = sat isLower;
  letterUC     = sat isUpper;
  digit        = sat isDigit;
  alphameric   = sat isAlphaNum;
  opChars      = "$%^&*-+=:~#?/><.,\\|";
  isOp c       = elem c opChars;
  opChar       = sat isOp;
  space        = sat isSpace;

  comma = spaced (char ',');

  (<>) :: Reads a -> Reads a -> Reads a;
  (p <> q) s = p s ++ q s;

  (>>) :: Reads a -> Reads b -> Reads (a,b);
  (p >> q) s = let f = \x -> [((fst x, fst y), snd y) | y <- q (snd x)]
               in concat [f x | x <- p s];

  (~>) :: Reads a -> (a -> b) -> Reads b;
  (p ~> f) s = [(f (fst x), snd x) | x <- p s];

  nullSeq :: Reads [];
  nullSeq s = [([], s)];

  many, many1 :: Reads [a];
  many p = many1 p <> nullSeq;
  many1 p = (p >> many p) ~> uncurry (:);

  numeral, identifier, operator :: Reads String;
  numeral    = many1 digit;
  identifier = (letterLC >> many alphameric) ~> uncurry (:);
  operator   = many1 opChar;

  spaced, bracketed, sqBracketed :: Reads a -> Reads a;
  spaced p    = (many space >> p) ~> snd;
  bra         = spaced (char '(');
  ket         = spaced (char ')');
  bracketed p = ((bra >> p) >> ket) ~> (snd . fst);
  sqBra = spaced (char '[');
  sqKet = spaced (char ']'); 
  sqBracketed p = ((sqBra >> p) >> sqKet) ~> (snd . fst);


  string :: String -> Reads ();
  string template str =
  case (template, str) of {
    ("", cs)      -> [((),cs)];           -- Success
    (t:ts, c:cs)  -> if c == t then
                       string ts cs
                     else
                       [];                -- Fail
    (ts, "")   -> []                      -- Fail
  };

  integer :: Reads Int;
  integer = (number' <> minusNumber');

  number' = (numeral ~> valueOf);
  minusNumber' = (char '-' >> number') ~> (negate . snd);

  valueOf = let f = \n d -> (n * 10) + ord d - ord '0'
            in foldl f 0;

  -- Lists of integers
  nullBody = nullSeq ~> const [];

  nonNullBody = let repeats = many ((comma >> spaced integer) ~> snd)
           in (spaced integer >> repeats) ~> uncurry (:);

  intList :: Reads [Int];
  intList = sqBracketed (nullBody <> nonNullBody);


  -- Parse exactly the specified object (ie, check for end-of-string)
  exact :: Reads a -> Reads a;
  exact p = (p >> (spaced eos)) ~> fst;

  -- Check that there is exactly one parse and return it (or an error)
  unique :: Reads a -> String -> a;
  unique p s = case p s of {
                 []       -> error "Parse.unique: unparsable";
                 [(x, _)] -> x;
                 _        -> error "Parse.unique: ambiguous parse"
               };

  -- Top-level parsing function
  parser :: Reads a -> String -> a;
  parser = unique . exact

}

