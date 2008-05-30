module List(unfoldr, tabulate, transpose, partition) where {

-- This library is not yet complete . . .


-- The dual of foldr

unfoldr 	:: (b -> Maybe (a,b)) -> b -> [a];

unfoldr f b     =  case f b of {
                     Nothing    -> [];
                     Just (a,b) -> a : unfoldr f b};


tabulate        :: Int -> [a] -> [[a]];

tabulate n xs = take n xs : tabulate n (drop n xs);


partition       :: (a -> Bool) -> [a] -> ([a],[a]);

partition p xs  =  case xs of {
                     []    -> ([],[]);
                     x:xs  -> let { ys = partition p xs;
                                    us = fst ys;
                                    vs = snd ys
                              } in if p x then
                                     ((x:us),vs)
                                   else
                                     (us,(x:vs))
                   };

-- A simplified version of the standard "transpose" function

transpose        :: [[a]] -> [[a]];

transpose xss = case xss of {
                  []:_  -> [];
                  xss -> (map head xss) : transpose (map tail xss)
                 }

}
