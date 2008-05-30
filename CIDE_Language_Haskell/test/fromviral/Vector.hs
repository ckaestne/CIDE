module Vector(primListVector, primSize, primIndex, primElems, primUpdate, listVector) where {

-- This module provides 0-based 1-D arrays with fast read access.

  primitive primListVector   :: Int -> [a] -> Vector a;
  primitive primSize         :: Vector a -> Int;
  primitive primIndex        :: Vector a -> Int -> a;
  primitive primElems        :: Vector a -> [a];
  primitive primUpdate       :: Vector a -> Int -> a -> Vector a;

  listVector                 :: [a] -> Vector a;
  listVector xs              =  primListVector (length xs) xs;

  instance Show Vector where {
    shows v s = "vector " ++ shows (primSize v) ( ' ' : shows (primElems v) s);
    show  v   = shows v ""
  }

}
