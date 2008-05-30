-- This module deals _correctly_ with displays of characters, lists and strings (including
-- dealing correctly with escaped characters, and with such constructions nested to
-- arbitrary depth). But it does not deal with many other standard types (such as Bool
-- or Maybe) and it is not efficiently coded).

module PreludeText (
  showChar,
  showString,
  showParen
) where {
  
  import Numeric;

  showChar :: Char -> ShowS;
  showChar = (:);

  showString :: String -> ShowS;
  showString = (++);

  showParen :: Bool -> ShowS -> ShowS;
  showParen b p = if b then showChar '(' . p . showChar ')'  else p;


  instance Show () where {
    shows x s = "()" ++ s
  };

  instance Show Char where {
    shows c s = '\'' : (c : ('\'' : s))
  };


  instance Show Int where {
    shows n s = if n < 0 then 
                   '-' : Numeric.showInt (-n) s               -- NB: No account taken of surrounding precedences!
                else
                   Numeric.showInt n s
  };


  instance Show Double where {
    shows d s = Numeric.showDouble d ++ s
  };

  instance Show Image where {
    shows img s = let p = imageSize img in "<image> " ++ show p ++ s
  };


  instance Show (a -> b) where {
    shows f s = "<function>" ++ s
  };


  instance (Show a, Show b) => Show (a,b) where {
    shows p s = '(' : (shows (fst p) (',' : shows (snd p) (')' : s)))
  };


  instance (Show a) => Show [a] where {
    shows xs s = case xs of {
                   []   -> "[]";
                   y:ys -> if isChar y then
                             '\"' : showsString xs ('\"' : s)
                           else
                             '['  :  (show y ++ showsList   ys (']'  : s))
                 }
  };


  showsString :: String -> String -> String;
  showsString cs s = case cs of {
                         []   -> s;
                         d:ds -> escape d (showsString ds s)
                     };


  escape :: Char -> String -> String;
  escape d cs = case d of {
                  '\n' -> '\\' : ('n'  : cs);
                  '\"' -> '\\' : ('\"' : cs);
                  '\'' -> '\\' : ('\'' : cs);
                  '\\' -> '\\' : ('\\' : cs);
                  c    -> c  : cs
                };

  showsList :: [a] -> String -> String;
  showsList xs s = case xs of {
                     []   -> s;
                     y:ys -> ',' : (' ' : (shows y (showsList ys s)))
                   };

  -- This local type class is used in the List instance of Show (above) for determining
  -- whether to display a list as a string or as a sequence delimited with [ , , , ...]
  -- Its single method, "isChar" returns True for a Char, or False for any other type.

  class Type a where {
    isChar :: a -> Bool;
    
    isChar x = False
  };

  instance Type Char where {
    isChar c = True
  }

}


