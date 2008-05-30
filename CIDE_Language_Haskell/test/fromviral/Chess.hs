-- Defines a function for pictorially enumerating solutions to the n-queens problem.

-- See interactive module "PictureWork5" for interactive development of this function

module Chess (queens, mkDiagramsAll, diagrams) where {

import Pictures;

type Position = [Col];

type Solutions = [Position];

mkBoard :: Int -> [Color] -> Int -> Pic;

mkBoard n cs ss =
    let { row = cycle [Rect ss ss c black | c <- cs];
          board = iterate tail row
    } in (above . take n . map (beside . take n)) board;

queens :: Int -> Solutions;

queens n = queens' n n;

queens' :: Int -> Int -> Solutions;

queens' n m =
  let {cols = [0 .. (n-1)];
       q = \m -> if m == 0 then [[]]
                 else concat [[col:board | col <- cols, safe col board 1] | board <- q (m-1)]

  } in q m;


safe :: Int -> [Int] -> Int -> Bool;

safe x ys m =
  case ys of {
    []    -> True;
    y:ys'  -> (x /= y) && (x /= (y-m)) && (x /= (y+m)) && safe x ys' (m+1)
  };

placeQueens :: Int -> Position -> Pic;

placeQueens ss cols =
  let queen = mkQueen ss
  in case cols of {
       []         -> NoPic;
       col:cols'  -> let { pic1 = move 0 (ss*col) queen;
                           pic2 = placeQueens ss cols'
                     } in pic1 <+> move ss 0 pic2
     };


mkQueen :: Int -> Pic;

mkQueen ss = let sf = fromInt ss / fromInt 40
             in scale sf queen';


mkDiagram :: Int -> Color -> Color -> Position -> Pic;

mkDiagram ss c1 c2 posn = 
    let { n    = length posn;
          pic1 = placeQueens ss posn;
          pic2 = mkBoard n [c1, c2] ss
    } in pic2 <+> pic1;


mkDiagramsAll :: Int -> Color -> Color -> Int -> Pic;

mkDiagramsAll ss c1 c2 n =
    let { solutions = queens n;
          f = move ss 0 . mkDiagram ss c1 c2;
          diagrams = map f solutions
    } in beside diagrams;


-- The set of solutions to the n-queens problem

diagrams :: Int -> Pic;

diagrams = mkDiagramsAll 20 yellow green


}

