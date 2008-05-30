module Numeric (showInt, showOct, showHex, showDouble, show, showTime) where {

-- A partial implementation of the Haskell "Numeric" library module.
-- With the addition of the functions show and time;

showInt, showOct, showHex  :: Int -> (String -> String);

showInt = showIntAtBase 10 intToDigit;                             -- NB: Non-negative integers only!
showOct = showIntAtBase 8  intToDigit;
showHex = showIntAtBase 16 intToDigit;


show :: Int -> String;

show n = 
  if n < 0 then
     '-' : (showInt (-n) "")
  else
     showInt n "" ;


primitive primShowDouble :: Double -> String;                       -- An efficient, built-in conversion
 
showDouble :: Double -> String;
showDouble = primShowDouble;


-- The value of
--       showTime gmtOffset ms 
-- where:
--        ms  is the time in milliseconds since the start of 1970
--        gmtOffset is the offset, in hours, relative to GMT
-- is a string giving the number of days, hours, minutes and seconds since the start of 1970

showTime :: Int -> Int -> String;

showTime gmtOffset ms' = 
  let { ms    = ms' `mod` 1000;
        sec'  = ms' `div` 1000;

        sec   = sec' `mod` 60;
        min'  = sec' `div` 60;

        min   = min' `mod` 60;
        hr'   = (min' `div` 60) + gmtOffset;

        hr    = hr' `mod` 24;
        day'  = hr' `div` 24
  } in  showInt day' (" days,  " ++ showInt hr (" hrs,  " ++ showInt min ("mins,  " ++ showInt sec " secs")));

            
-- ------------------------------------------------------------------------

showIntAtBase :: Int -> (Int -> Char) -> Int -> (String -> String);

showIntAtBase base intToDig n rest = 
  if n < 0 then 
    error "Numeric.showIntAtBase: can't show negative numbers" 
  else
    let { d = rem n base; q = quot n base;
          rest' = intToDig d : rest
    } in if q == 0 then 
           rest' 
         else 
           showIntAtBase base intToDigit q rest';
 


intToDigit :: Int -> Char;

intToDigit i =
  if (i >= 0) && (i <= 9) then chr (ord '0' + i)
  else if (i >= 10) && (i <= 15) then chr (ord 'a' + i - 10)
  else error "Numeric.intToDigit:  not a digit"


}
