-- This module defines an instance for the list type for the Edit type class.

module ListManipLib (edit, subst, cut, reverseFrom, splice) where {

  import Char;

  instance Edit [a] where {
    edit xs path = [
      ("Cut",                    fCut),
      ("Copy",                   fCopy),
      ("Copy by value",          fCopyByValue),
      ("Copy list up to",        fCopyUpTo),
      ("Copy list from",         fCopyFrom),
      ("Paste",                  fPaste),
      ("Delete before",          fDeleteBefore),
      ("Delete after",           fDeleteAfter),
      ("Splice from clipboard",  fSplice),
      ("Reverse from",           fReverseFrom),
      ("Evaluate",               fEval),
      ("Report clipboard value", fReport)
    ]
  };


  -- Replace the i-th element of a list (0-based indexing) with the given element

  subst :: Int -> a -> [a] -> [a];

  subst i x xs = if i == 0 then
                   x : tail xs
                 else
                   head xs : subst (i-1) x (tail xs);


  -- Remove the i-th element of a list

  cut :: Int -> [a] -> [a];

  cut i xs = if i == 0 then
               tail xs
             else
               head xs : cut (i-1) (tail xs);


  -- Reverse the list from location i onwards

  reverseFrom :: Int -> [a] -> [a];

  reverseFrom i xs =
    if i == 0 then
      reverse xs
    else
      head xs : reverseFrom (i-1) (tail xs);


  -- The value of splice i xs ys  is the list formed by splicing in list xs in front of element i of list ys

  splice :: Int -> [a] -> [a] -> [a];

  splice i xs ys =
    if i == 0 then
      xs ++ ys
    else
      head ys : splice (i-1) xs (tail ys);


  -- Functions evaluated by Menu commands -------------------------------------------------------------------

  fCut xs path lhs rhs clip =
    let { i = length path - 1;
          sExp = "cut " ++ show i ++ paren rhs;
          sClip = paren rhs ++ " !! " ++ show i;
          sWarn = "Selected element cut and copied to clipboard!"
    } in (Just sExp, Just sClip, Just sWarn);


  fCopy xs path lhs rhs clip =
    let { i = length path - 1;
          sClip = lhs ++ " !! " ++ show i;
          sWarn = "Selected element copied to clipboard!"
    } in (Nothing, Just sClip, Just sWarn);


  fCopyByValue xs path lhs rhs clip =
    let { i = length path - 1;
          x = xs !! i;
          sClip = show x;
          sWarn = "Selected element copied to clipboard by value!"
    } in (Nothing, Just sClip, Just sWarn);


  fCopyUpTo xs path lhs rhs clip =
    let { n = length path;
          sClip = "take " ++ show n ++ lhs;
          sWarn = "Sublist up to selected element copied to clipboard!"
    } in (Nothing, Just sClip, Just sWarn);
       
   
  fCopyFrom xs path lhs rhs clip =
    let { n = length path;
          sClip = "drop " ++ show (n-1) ++ lhs;
          sWarn = "Sublist from selected element and beyond copied to clipboard!"
    } in (Nothing, Just sClip, Just sWarn);
 

  fPaste xs path lhs rhs clip =
    let { i = length path - 1;
          sExp = "subst " ++ show i ++ paren clip ++  paren rhs;
          sWarn = "Contents of clipboard pasted into selected position!"
    } in (Just sExp, Nothing, Just sWarn);


  fDeleteBefore xs path lhs rhs clip =
    let { n = length path;
          sExp = "drop " ++ show (n-1) ++ paren rhs;
          sWarn = "List deleted up to selected element!"
    } in (Just sExp, Nothing, Just sWarn);


  fDeleteAfter xs path lhs rhs clip =
    let { n = length path;
          sExp = "take " ++ show n ++ paren rhs;
          sWarn = "List deleted after selected element!"
    } in (Just sExp, Nothing, Just sWarn);


  fSplice xs path lhs rhs clip =
    let { i = length path - 1;
          sExp = "splice " ++ show i ++ paren clip ++ paren rhs;
          sWarn = "Clipboard contents spliced into list!"
    } in (Just sExp, Nothing, Just sWarn);


  fReverseFrom xs path lhs rhs clip =
    let { i = length path - 1;
          sExp = if i == 0
                   then "reverse" ++ paren rhs
                 else
                   "reverseFrom " ++ show i ++ paren rhs;
              sWarn = "Reverse!"
    } in (Just sExp, Nothing, Just sWarn);


  fEval xs path lhs rhs clip =
    let { sExp = show xs;
          sWarn = "Evaluate!"
    } in (Just sExp, Nothing, Just sWarn);


  fReport xs path lhs rhs clip =
    let { sWarn = "Clipboard value is:  " ++ clip
    } in (Nothing, Nothing, Just sWarn);


  -- SUBSIDIARY FUNCTIONS ---------------------------------------------------------------

  -- Insert parentheses around a string if necessary to make 
  -- it parse as an atomic expression

  paren :: String -> String;

  paren s = if isAtomic s then
              " " ++ s ++ " "
            else
              " (" ++ s ++ ") ";


  -- Test (conservative) for atomicity of an expression, defined as:
  --         SPACE*  ALPHAMERIC*  SPACE*

  isAtomic s =  
    null s  ||  
    (isSpace (head s) && isAtomic (tail s)) ||
    isAtomic1 (tail s);

  isAtomic1 s =
    null s ||
    (isAlphaNum (head s) && isAtomic1 (tail s)) ||
    isAtomic2 (tail s);

  isAtomic2 s =
    null s ||
    (isSpace (head s) && isAtomic2 (tail s))

}
