module Array (Array, listArray, (!), bounds, indices, elems, assocs, 
              accumArray, (//), accum, ixmap) where {

  -- RESTRICTION:  implemented only for 1-D, integer indexing.

  listArray                  :: (a,a) -> [b] -> Array a b;
  (!)                        :: Array a b -> a -> b;
  bounds                     :: Array a b -> (a,a);
  indices                    :: Array a b -> [a];
  elems                      :: Array a b -> [b];
  assocs                     :: Array a b -> [(a,b)];
  accumArray                 :: (b -> c -> b) -> b -> (a,a) -> [(a,c)] -> Array a b;
  (//)                       :: Array a b -> [(a,b)] -> Array a b;
  ixmap                      :: (a,a) -> (a -> b) -> Array b c -> Array a c;

-- IMPLEMENTATION -----------------------------------------------------------------------------

  -- It makes use of the primitive type Vector a   which provides 0-based 1-D arrays with fast read access.

  primitive primListVector   :: Int -> [a] -> Vector a;
  primitive primSize         :: Vector a -> Int;
  primitive primIndex        :: Vector a -> Int -> a;
  primitive primElems        :: Vector a -> [a];
  primitive primUpdate       :: Vector a -> Int -> a -> Vector a;


  -- Representation: represent an array by its lower bound and a (0-based) vector of its elements
  data Array a b = MkArray a (Vector b);



  listArray bounds vs        =  let { lowerBound = fst bounds;
                                      upperBound = snd bounds;
                                      size = 1 + upperBound - lowerBound
                                } in MkArray lowerBound (primListVector size vs);

  a ! i                      =  case a of {
                                  MkArray lowerBound vec -> 
                                    if (i < lowerBound) || (i >= (lowerBound + primSize vec)) then
                                      error "Array.!: out of bounds array access"
                                    else
                                       primIndex vec (i - lowerBound);
                                  _ -> error "Array.!: badly typed"
                                };

  bounds a                   =  case a of {
                                  MkArray lowerBound vec ->
                                    (lowerBound, lowerBound + primSize vec - 1);
                                  _                      -> error "Array.bounds: badly typed"
                                };

  indices a                  =  let bnds = bounds a in [fst bnds .. snd bnds];

  elems a                    =  case a of {
                                  MkArray _ vec -> primElems vec;
                                  _             -> error "Array.elems: badly typed"
                                };

  assocs a                   =  case a of {
                                  MkArray lowerBound vec -> zip 
                                                              [lowerBound .. (lowerBound + primSize vec -1)]
                                                              (primElems vec);
                                  _                -> error "Array.assocs: badly typed"
                                };

  a // ixs                   =  error "Array.//: not yet implemented";
  accumArray                 =  error "Array.accumArray: not yet implemented";
  ixmap                      =  error "Array.ixmap: not yet implemented";


  instance Show Array where {
    shows a s = let {bnds = shows (bounds a);
                     xs   = shows (assocs a)
                } in "array " ++ bnds (' ' : xs s);
    show a    = shows a ""
  }

}
