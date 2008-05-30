module Ratio (Ratio, (%), pair, numerator, denominator, (+%), (*%), decimal) where {

  infixl 7 %;

  data Ratio = Int :% Int;                    -- This (infixed) constructor is not exported

  reduce x y = if y == 0 then
                 error "Ratio.%: zero denominator" 
               else
                 let d = gcd x y in
                 (x `quot` d) :% (y `quot` d);

  x % y            = reduce (x * signum y) (abs y);

  pair r           = case r of { 
                       x :% y    -> (x, y)
                     };

  numerator        = fst . pair;

  denominator      = snd . pair;


  r +% r'          = case (r, r') of {
                       ((x :% y), (x' :% y')) ->
                           reduce ((x * y') + (x' * y)) (y * y')
                     };


  r *% r'          = case (r, r') of {
                       ((x :% y), (x' :% y')) -> 
                           reduce (x * x') (y * y')
                     };





  -- Express the ratio p as a decimal expansion. 
  -- Requires p to be a *proper* fraction (ie,  numerator < denominator)

  radix            = 10;

  toDigit n        = chr (n + ord '0');


  decimal          :: Ratio -> [Char];

  decimal p        =  case p of {
                        n :% m  ->  if n < m then
                                      "0." ++ decim (n * radix) m	
                                    else
                                      error "Ratio.decimal: improper fraction"
                      };


  decim            :: Int -> Int -> [Char];

  decim n m        =  if n == 0 then
                        ""
                      else
                        toDigit (n `quot` m) : decim ((n `rem` m) * radix) m;


  instance Show Ratio where {
    shows r s = case r of { 
                   x :% y    ->  '(' : shows x (" % " ++ (shows y (')' :s)))
                };
    show r    = shows r ""
  }


--  instance Display Ratio where {
--    display r = let { pic1 = Trans 4  0 (display (numerator r));
--                      pic  = Trans 0 18 (Line 20 0 red);
--                      pic2 = Trans 4 21 (display (denominator r))
--                } in Super pic1 (Super pic pic2);
--    displays r pic = Super (display r) pic
--  }

}
