-- A very simple implementation of Set operations (sim. to Data.Set)

module Set (
  elem', nub,
  union, intersect
) where {
  instance Equal Char    where eq c d = (c == d);
  instance Equal Int     where eq n m = (n == m);
  instance Equal Double  where eq e f = (e == f);
  
  instance Equal Bool    where eq p q = if p then q else not q;
  instance (Equal a) => Equal (Maybe a) where
    eq x y = case x of {
               Nothing -> case y of {Nothing -> True; _  -> False};
               Just u  -> case y of {Just v -> eq u v; _ -> False}
             };
  instance (Equal a) => Equal [a] where
    eq xs ys = case xs of {
                 []      -> case ys of {[] -> True; _ -> False};
                 x:xs    -> case ys of {y:ys -> eq x y && eq xs ys; _ -> False}
               };
  instance (Equal a, Equal b) => Equal (a,b) where 
    eq p q = eq (fst p) (fst q) && eq (snd p) (snd q);
  instance (Equal a, Equal b, Equal c) => Equal (a,b,c) where
    eq p q = case p of 
               (a,b,c) -> case q of 
                            (a',b',c') -> (eq a a') && (eq b b') && (eq c c');
  instance (Equal a, Equal b, Equal c, Equal d) => Equal (a,b,c,c) where
    eq p q = case p of 
               (a,b,c,d) -> case q of 
                              (a',b',c',d') -> (eq a a') && (eq b b') && (eq c c') && (eq d d');



  -- A local definition of "elem", using the above "eq" predicate
  elem' :: a -> [a] -> Bool;
  elem' x xs = case xs of {
                 []    -> False;
                 y:ys  -> (x `eq` y) || elem' x ys
               };


  -- Definition of the standard fn, "nub"
  nub :: [a] -> [a];
  nub xs = nub2 xs [];
  
  nub2 xs rems =
    case xs of {
      []     -> [];
      y:ys   -> if elem' y rems then
                  nub2 ys rems
                else 
                  y : nub2 ys (y : rems)
    };

  union, intersect :: [a] -> [a] -> [a];
  union xs ys =
    let {zs = nub2 ys xs} in xs ++ zs;

  intersect xs ys =
    case xs of {
      []     ->  [];
      x:xs   ->  if elem' x ys then
                   x : intersect xs ys
                 else
                   intersect xs ys
  }


}
