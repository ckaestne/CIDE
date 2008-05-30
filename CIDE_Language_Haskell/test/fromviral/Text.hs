module Text (  isLower, isUpper, isDigit, isAlpha, isAlphaNum, toUpper, toLower, 
               toDigit, fromDigit, showInt,
               sterlingChar, notChar, plusMinusChar, quarterChar, halfChar, threeQuartersChar,
               timesChar, emptySetChar, integralChar, circleDotChar,
               toGreekChar, toGreek
            ) where {

-- THIS MODULE HAS BEEN SUPERCEDED BY MODULE "Char", WHICH IS VERY MUCH MORE STANDARD ***

orda                    = ord 'a';
ordz                    = ord 'z';
ordA                    = ord 'A';
ordZ                    = ord 'Z';
ord0                    = ord '0';
ord9                    = ord '9';

isLower c               = let cn = ord c in (cn >= orda) && (cn <= ordz);
isUpper c               = let cn = ord c in (cn >= ordA) && (cn <= ordZ);
isDigit c               = let cn = ord c in (cn >= ord0) && (cn <= ord9);

isAlpha c               = isLower c || isUpper c;
isAlphaNum c            = isAlpha c || isDigit c;

offset                  = ordA - orda;
toUpper c               = if isLower c then chr (ord c + offset) else c;
toLower c               = if isUpper c then chr (ord c - offset) else c;

toDigit                 :: Int -> Char;
toDigit n               =  chr (n + ord0);

fromDigit               :: Char -> Int;
fromDigit d             =  ord d - ord0;


-- Number to decimal numeral conversion

radix                   = 10;

showInt                 :: Int -> String;
showInt n               =  let f = \n digits -> 
                                      if n == 0 then 
                                        digits 
                                      else
                                        f (n `div` radix) 
                                          (toDigit (n `mod` radix) : digits)
                           in case compare n 0 of {
                                GT -> f n [];
                                EQ -> "0";
                                LT -> '-' : f (negate n) []
                              };


-- Some useful Unicode symbols
sterlingChar            =  '\163';
notChar                 =  '\172';
plusMinusChar           =  '\177';
quarterChar             =  '\188';
halfChar                =  '\189';
threeQuartersChar       =  '\190';
timesChar               =  '\215';
emptySetChar            =  '\216';
integralChar            =  '\643';
circleDotChar           =  '\664';

-- Greek stuff
greekDict               :: [(Char, Char)];

greekDict               =  [ ('a', '\945'), ('b', '\946'), ('c', '\947'), ('d', '\948'), ('e', '\949'),
	                     ('h', '\952'), ('i', '\943'), ('l', '\955'), ('n', '\957'), ('t', '\964'), ('v', '\966'),
                             ('K', '\922'), ('H', '\920'), ('V', '\934') 
                           ];							-- NB: Incomplete !


toGreekChar             :: Char -> Char;
toGreekChar c           =  if isAlpha c then
                             case lookup c greekDict of {
                               Nothing -> '?';
                               Just d  -> d}
                           else
                             c;

toGreek                 :: String -> String;
toGreek                 =  map toGreekChar
}
