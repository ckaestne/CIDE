-- This module defines a Tree datatype and instance for the Show and the Edit type classes.

module TreeManipLib (Tree(Tip, (:+:)), treeSize, treeSubst, treeFlip, treeFlattens) where {

  data Tree a = Tip a
              | Tree a  :+:  Tree a;


  instance (Show a) => Show (Tree a) where {
    shows tr s =
      case tr of {
        Tip x    -> "Tip " ++ shows x s;
        tr1 :+: tr2 -> "(" ++ shows tr1 (" :+: " ++ (shows tr2 (")" ++ s)))
      };
      show tr = shows tr ""
    };


  instance Edit (Tree a) where {
    menu tr = [
      "Copy",
      "Copy by value",
      "Paste",
      "Flip",
      "Evaluate",
      "Copy as list",
      "Report size of subtree"
    ];
    
    update tr menuIndex path lhs rhs clip =
      case menuIndex of {
        0 -> fCopy          tr path lhs rhs clip;
        1 -> fCopyByValue   tr path lhs rhs clip;
        2 -> fPaste         tr path lhs rhs clip;
        3 -> fFlip          tr path lhs rhs clip;
        4 -> fEval          tr path lhs rhs clip;
        5 -> fCopyAsList    tr path lhs rhs clip;
        6 -> (Nothing, Nothing, Just ("Size is: " ++ show (treeSize (treeSelect path tr))))
      }
  };


  treeSize :: Tree a -> Int;

  treeSize tr = case tr of {
                  Tip _    -> 1;
                  tr1 :+: tr2 -> treeSize tr1 + treeSize tr2
                };


  treeSelect :: [Int] -> Tree a -> Tree a;

  treeSelect path tr =
    case (path, tr) of {
      ([], tr)                  -> tr;
      ((n:path), (tr0 :+: tr1)) -> let tr = if n == 0 then tr0 else tr1
                                   in treeSelect path tr;
      _                           -> error "Tree.treeSelect: bad path"
    };


  -- Insert tr' in tr at location defined by path

  treeSubst :: [Int] -> Tree a -> Tree a -> Tree a;

  treeSubst path tr' tr =
    case (path, tr) of {
      ([], tr)                  -> tr';
      ((0:path), (tr0 :+: tr1)) -> let tr = treeSubst path tr' tr0 in tr :+: tr1;
      ((1:path), (tr0 :+: tr1)) -> let tr = treeSubst path tr' tr1 in tr0 :+: tr;
      _                         -> error "Tree.treeSubst: bad path"
    };


  treeFlip path tr =
    case (path, tr) of {
      ([], tr)                  -> treeFlip' tr;
      ((0:path), (tr0 :+: tr1)) -> let tr = treeFlip path tr0 in tr :+: tr1;
      ((1:path), (tr0 :+: tr1)) -> let tr = treeFlip path tr1 in tr0 :+: tr;
      _ -> error "Tree.treeFlip: bad path"
    }; 


  treeFlattens :: Tree a -> [a] -> [a];

  treeFlattens tr xs =
    case tr of {
      Tip x     -> x : xs;
      tr1 :+: tr2 -> treeFlattens tr1 (treeFlattens tr2 xs)
    };



  -- Functions evaluated by Menu commands ----------------------------------------------------

  fCopy tr path lhs rhs clip = 
    let { ref = if null lhs then paren rhs else lhs;
          sClip = "treeSelect " ++ show path ++ " " ++ ref
    } in (Nothing, Just sClip, Just "Copied!");


  fCopyByValue tr path lhs rhs clip =
    let { subTree = treeSelect path tr;
          sClip = show subTree
    } in (Nothing, Just sClip, Just "Copied by value!");


  fPaste tr path lhs rhs clip = 
    let { sExp = "treeSubst " ++ show path ++ paren clip ++ paren rhs;
          sWarn = "Pasted!"
    } in (Just sExp, Nothing, Just sWarn);


  fFlip tr path lhs rhs clip =
    case tr of {
      Tip _ -> (Nothing, Nothing, Just "Cannot flip this element!");
      _     ->
               let { sExp = "treeFlip " ++ show path ++ paren rhs;
                     sWarn = "Flipped!"
               } in (Just sExp, Nothing, Just sWarn)
    };


  fEval tr path lhs rhs clip =
    let { sExp = show tr;
          sWarn = "Evaluated!"
    } in (Just sExp, Nothing, Just sWarn);


  fCopyAsList tr path lhs rhs clip =
    let { sClip = show (treeFlattens tr []);
          sWarn = "Copied to clipboard as a list!"
    } in (Nothing, Just sClip, Just sWarn);


  -- Subsidiary functions -------------------------------------------------------------------

  paren s = " (" ++ s ++ ") ";


  treeFlip' tr =
    case tr of {
      Tip x      -> tr;
      tr1 :+: tr2 -> treeFlip' tr2 :+: treeFlip' tr1
    }

}
