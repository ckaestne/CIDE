module Char (
  isSpace, isUpper, isLower, isAlpha, isDigit, isAlphaNum,
  digitToInt, intToDigit,
  toUpper, toLower,
  ord, chr
) where {

-- This module is based loosely on the Standard Haskell module of the same name.

orda                    = ord 'a';
ordz                    = ord 'z';
ordA                    = ord 'A';
ordZ                    = ord 'Z';
ord0                    = ord '0';
ord9                    = ord '9';
spaceChars              = " \n\r";
offset                  = ordA - orda;


isSpace c               = c `elem` spaceChars;

-- isLower c               = let cn = ord c in (cn >= orda) && (cn <= ordz);
-- isUpper c               = let cn = ord c in (cn >= ordA) && (cn <= ordZ);
-- isDigit c               = let cn = ord c in (cn >= ord0) && (cn <= ord9);

isLower c               = (c >= 'a') && (c <= 'z');
isUpper c               = (c >= 'A') && (c <= 'Z');
isDigit c               = (c >= '0') && (c <= '9');

isAlpha c               = isLower c || isUpper c;
isAlphaNum c            = isAlpha c || isDigit c;

toUpper c               = if isLower c then chr (ord c + offset) else c;
toLower c               = if isUpper c then chr (ord c - offset) else c;

digitToInt              :: Char -> Int;
digitToInt c            =  if isDigit c then ord c - ord0 else error "Char.digitToInt: not a digit";

intToDigit              :: Int -> Char;
intToDigit i            =  if ((i >= 0) && (i <= 9)) then chr (i + ord0) else error "Char.intToDigit: not a digit"

}
