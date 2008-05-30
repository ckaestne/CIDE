-- This module provides an implementation of the abstraction "a row 
-- of yellow/cyan coloured squares".

module ColorRowLib (Row(), mkRow, setYellow, setCyan, rowToPic) where {

  -- The row of coloured squares is represented by a list of Bool, with False
  -- representing a yellow square and True representing a cyan one.
  data Row = Row [Bool];


  mkRow :: Int -> Row;
  mkRow n = Row (replicate n False);


  rowToBools :: Row -> [Bool];
  rowToBools row =
    case row of
      Row bs -> bs;


  -- Functions to set the colour of a square to yellow or to cyan

  setYellow, setCyan :: Int -> Row -> Row;
  setYellow = setRowElem False;
  setCyan   = setRowElem True;


  setRowElem :: Bool -> Int -> Row -> Row;
  setRowElem b i row =
    let bs = rowToBools row
    in Row (setListElem b i bs);


  setListElem :: Bool -> Int -> [Bool] -> [Bool];
  setListElem b i bs =
    if i == 0 then 
      b : tail bs
    else
      head bs : setListElem b (i-1) (tail bs);


  -- Defn of the function that yields a picture of the row of coloured squares

  -- The size of the squares
  s1 = 20;  s3 = 3 * s1;


  boolToPic :: Bool -> Pic;
  boolToPic b =
    let {color = if b then cyan else yellow;
         sq1 = Rect s3 s3 trans gray;
         sq2 = SelRect s1 s1 color black
    } in sq1 `Super` Trans s1 s1 sq2;


  boolsToPic :: [Bool] -> Pic;
  boolsToPic = foldr fn NoPic;


  fn :: Bool -> Pic -> Pic;
  fn b pic = Super (boolToPic b) 
                   (Trans s3 0 pic);


  rowToPic :: Row -> Pic;
  rowToPic = boolsToPic . rowToBools
}
