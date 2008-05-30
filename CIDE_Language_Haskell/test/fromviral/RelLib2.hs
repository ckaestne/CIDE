module RelLib2 (Set(), emptySet, mkSet, setComplement, delete, insert, inter, union,
               Rel(), emptyRel, mkRel, markRel, unmarkRel, addEdgeRel, deleteEdgeRel,
                    inverseRel, reflexClosureRel, symmClosureRel, transClosureRel, 
                    unionRel, interRel, compRel
)  where {

-- Note: the definitions of operations on Sets and Relations would benefit from
-- an extensive reworking; they were just thrown together!

-- It would also benefit from being split into two separate modules: one concerned
-- with defining the Set and Rel types and the operations on them, and the other
-- concerned with defining the Edit.edit functions for direct manipulation of
-- these entities.

  -- The cardinality of the base set (of which all sets are subsets)
  n = 8;

  intLabels = [0 .. (n-1)];

  -- A set is represented as a list of N n
  data Set = Set [Int];

  -- Constants used in displaying a set:

  -- The gap between adjacent elements
  gap = 50;

  -- The size of the squares used to depict the elements of sets
  hSide = 10;
  side = 2 * hSide;

  labelOffsetX = -5;
  labelOffsetY =  5;

  -- Colours (selected and non-selected versions) used
  labelColor0 = red;
  labelColor1 = blue;
  labelColor2 = gray;

  edgeColor1 = black;
  edgeColor2 = gray;
  
  bodyColor1 = yellow;
  bodyColor2 = trans;

  twoPi = 2.0 * pi;

  -- The radius of the circle around which elements are disposed
  radius = let circum = fromInt (n * gap) in circum / twoPi;

  type Point = (Int, Int);

  -- This function yields the location of the i-th element of the set
  locn :: Int -> Point;
  locn i = location radius i;

  -- This function yields the location a distance "extra" beyond the location of the i-th
  -- element of the set (it is used for displaying reflexive edges).
  locnExtra :: Int -> Int -> Point;
  locnExtra extra i = let r = radius + fromInt extra in location r i;

  location :: Double -> Int -> Point;
  location r i =
    let {theta = (fromInt i * twoPi / fromInt n);
         x     = round (r * cos theta);
         y     = round (r * sin theta)
    } in (x, y);


  -- "displayElem (x, y) visible ch"  displays an selectable box labelled with ch at the 
  -- point (x,y). If "visible" is false, it displays a greyed-out (but still selectable) 
  -- box. If "marked" is true, then it marks that element.

  displayElem :: (Int, Int) -> Bool -> Bool -> Char -> Pic;
  displayElem xy visible marked ch =
    let {x = fst xy; y = snd xy;
         bc = if visible then bodyColor1  else bodyColor2;
        ec = if visible then edgeColor1  else edgeColor2;
         labelColor = if marked then labelColor0 else
                      if visible then labelColor1 else labelColor2;
         box = SelRect side side bc ec;
         box' = Trans (x - hSide) (y - hSide) box;
         fmt = Format SansSerif 16 labelColor marked False;
         text = PicText fmt [ch];
         text' = Trans (x + labelOffsetX) (y + labelOffsetY) text
      } in Super box' text';

  -- Generate a label character (A, B, ...) from integer position (0, 1, ...)
  intToLetter :: Int -> Char;
  intToLetter i = chr (ord 'A' + i);

  -- A textual show function for sets
  showSet :: Set -> String;
  showSet s = 
    case s of
      Set  indices  -> let indices' = filter (< n) indices
                       in "mkSet "  ++ show indices';


  -- Define Show shows method for Set
  instance Show Set where {
    shows set s = showSet set ++ s
  };


  -- Top-level function for displaying a set
  displaySet :: (Maybe Int) -> Set -> Pic;
  displaySet maybeMarked s =
    let {pic1 = displaySetElems maybeMarked s;
         pic2 = setBackground
    } in offset (pic1 `Super` pic2);

  -- Subsidiary functions for display

  -- Display the elements of a set. If "maybe" contains an integer, then mark that element
  displaySetElems :: Maybe Int -> Set -> Pic;
  displaySetElems maybeMarked set =
    case set of 
      Set indices ->
        super [let {xt = locn i;
                    present = elem i indices;
                    marked = case maybeMarked of {
                               Nothing -> False;
                               Just j  -> (i == j)
                             }
                    } in displayElem (locn i) (elem i indices) marked (intToLetter i)
              | i <- intLabels];


  super :: [Pic] -> Pic;
  super pics =
    case pics of {
      []       -> NoPic;
      pic:pics -> Super pic (super pics)
    };

  offset :: Pic -> Pic;
  offset pic =
    let {y = round radius + 30;
         x = 2 * y
    } in Trans x y pic;

  -- Display the bounding box for the set
  setBackground :: Pic;
  setBackground =
    let {r = round (1.2 * radius) + side;
         diam = 2 * r;
         box = SelRect diam diam trans black
    } in Trans (- r) (- r) box;


  -- ------- Define Edit methods for Set -----------------------------------

  instance Edit Set where {
    style s = View (displaySet Nothing) Picture;

    change val path = 
      let {setCommands  = [("Copy set to clipboard", copyFn),
                           ("Complement set", complementFn),
                           ("Union with clipboard", unionFn),                        
                           ("Intersect with clipboard", interFn),
                           ("Evaluate set", evaluateFn)];
           elemCommands = [("Delete element", deleteFn),
                           ("Insert element", insertFn)]
      } in case decode path of {
             Nothing -> setCommands;
             Just _  -> elemCommands
           }
  };

  -- Decoding function for paths on Set. Yields either Nothing (if user clicks on background)
  -- or Just i if user clicks on element i.
  decode :: Path -> Maybe Int;
  decode path =
    if head path == 1 then 
      Nothing                    -- ie, a background click
    else
      let n = length path - 3
      in Just n;                  -- ie, the n-th element

  -- Typical paths:
  --   Background        1
  --   A                 0 0 0
  --   B                 0 1 0 0
  --   C                 0 1 1 0 0
  --   D                 0 1 1 1 0 0
  --   F                 0 1 1 1 1 0 0
  --   :                 :
  --   O                 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0

  decodeElem :: Path -> Int;
  decodeElem path =
    case decode path of {
      Nothing  -> error "RelDemo.decodeElem: should not occur";
      Just i   -> i
    };

  -- Definition of command functions

  complementFn val path x  y lhs rhs clip =
    (Just ("setComplement (" ++ rhs ++ ")"), Nothing, Just "OK");


  evaluateFn val path x y lhs rhs clip =
    (Just (showSet val), Nothing, Just "OK");


  copyFn val path x y lhs rhs clip =
    (Nothing, Just rhs, Just "OK");
  

  interFn val path x y lhs rhs clip =
    let rhs' = "(" ++ clip ++ ") `inter` (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");

  unionFn val path x y lhs rhs clip =
    let rhs' = "(" ++ clip ++ ") `union` (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");


  deleteFn val path x y lhs rhs clip =
    let i = decodeElem path
    in if memberOf i val then
         let rhs' = "delete " ++ show i ++ " (" ++ rhs ++ ")"
         in (Just rhs', Nothing, Just "OK")
       else
         (Nothing, Nothing, Just "No element to delete!");


  insertFn val path x y lhs rhs clip =
    let i = decodeElem path
    in if memberOf i val then
         (Nothing, Nothing, Just "Element already present!")
       else
         let rhs' = "insert " ++ show i ++ " (" ++ rhs ++ ")"
         in (Just rhs', Nothing, Just "OK");


  -- Operations on sets -------------------------------

  emptySet :: Set;
  emptySet =  Set [];


  mkSet :: [Int] -> Set;
  mkSet ns = Set (nub ns);


  setComplement :: Set -> Set;
  setComplement s =
    case s of 
      Set elems -> Set [i | i <- intLabels, notElem i elems];


  delete, insert :: Int -> Set -> Set;
  delete i s =
    case s of 
      Set ms -> let ms' = filter (/= i) ms
                in Set ms';

  insert i s =
    case s of
      Set ms -> Set (i : (filter (/= i) ms));


  inter, union :: Set -> Set -> Set;
  inter s1 s2 =
    case (s1, s2) of
      (Set ms1, Set ms2) -> 
        let ms = filter (\m -> elem m ms1) ms2
        in Set ms;


  union s1 s2 =
    case (s1, s2) of
      (Set ms1, Set ms2) ->
        let ms = filter (\m -> not (elem m ms1)) ms2
        in Set (ms ++ ms1);

  
  -- Eliminate duplicated elements
  nub :: [a] -> [a];
  nub xs =
    case xs of {
      []     -> [];
      x:xs   -> let xs' = filter (x /=) xs
                in x : nub xs'
    };


   -- Test for set membership				
  memberOf :: Int -> Set -> Bool;
  memberOf i s =
    case s of
      Set ms -> elem i ms;

  -- ======== RELATIONS ==================================================================================

  -- A relation is represented by a list of its edges (as ordered pairs) and
  -- an optional "marked" element

  type Edge = (Int, Int);

  data Rel = Rel [Edge] (Maybe Int);


  -- A textual show function for relations (note that it deliberately does _not_ show the marking).
  showRel :: Rel -> String;
  showRel rel =
    case rel of {
      Rel edges _ ->
        "mkRel " ++ show (nub2 edges)
    };


  -- Define Show shows method for Rel
  instance Show Rel where {
    shows rel s = showRel rel ++ s
  };


  -- Top-level function for displaying a relation
  displayRel :: Rel -> Pic;
  displayRel rel =
    case rel of 
      Rel edges maybeMarked ->
        let {pic1 = displayEdges edges;
             pic2 = displaySetElems maybeMarked baseSet;
             pic3 = setBackground
        } in offset (super [pic1, pic2, pic3]);

  -- Subsidiary functions for display

  baseSet = Set intLabels;

  -- Display an edge of a relation
    displayEdge :: (Int, Int) -> Pic;

  displayEdge edge =
    let {i = fst edge; 
         j = snd edge
    } in if (i == j) then
           let {p = locnExtra loopRadius i;
                diam = 2 * loopRadius;
                x = fst p - loopRadius; 
                y = snd p - loopRadius
           } in Trans x y (Ellipse diam diam trans edgeColor)
         else
           let {p = locn i;
                q = locn j
           } in vector (fst p) (snd p) (fst q) (snd q);

  loopRadius = (2 * side) `div` 3;

  displayEdges :: [(Int, Int)] -> Pic;
  displayEdges edges =
    super (map displayEdge edges);

  edgeColor = blue;

  line :: Int -> Int -> Int -> Int -> Pic;
  line x1 y1 x2 y2 =
    Trans x1 y1 (Line (x2 - x1) (y2 - y1) edgeColor);

  -- A vector is a line with an arrow on it. The constant k defines the size of the arrow.
  vector :: Int -> Int -> Int -> Int -> Pic;
  vector x1 y1 x2 y2 =
    let {dx = x2 - x1;
         dy = y2 - y1;
         s  = (abs dx + abs dy + abs (dx + dy) + abs (dx - dy)) `div` 2;    -- Approx length
         ux = (k * dx) `div` s;          -- u  is a short vector along the given vector
         uy = (k * dy) `div` s;
         vx = -uy `div` 2;               -- v  is a short vector perpendicular to the given vector
         vy =  ux `div` 2;
         xm = (x1 + x2) `div` 2;  ym = (y1 + y2) `div` 2;
         x4 = xm - ux + vx;  y4 = ym - uy + vy;
         x5 = xm - ux - vx;  y5 = ym - uy - vy;
         arrow1 = line xm ym x4 y4;
         arrow2 = line xm ym x5 y5;
         shaft  = line x1 y1 x2 y2
      } in super [arrow1, arrow2, shaft];

  k = 15;

  -- -------- Define Edit method for Rel -----------------------------------------

  instance Edit Rel where {
    style r = View displayRel Picture;

    change val path = 
      let {relCommands = 
             [("Copy to clipboard",        relCopyFn),
              ("Unmark",                   relUnmarkFn),
              ("Invert",                   relInverseFn),
              ("Reflexive closure",        relReflexClosureFn),
              ("Symmetric closure",        relSymmClosureFn),
              ("Transitive closure",       relTransClosureFn),
              ("Union with clipboard",     relUnionFn),
              ("Intersect with clipboard", relInterFn),
              ("Relational composition with clipboard",
                                           relCompFn),
              ("Evaluate",                 relEvaluateFn)];
           relElemCommands =  
             [("Mark element",      relMarkFn),
              ("Add edge to",       relAddEdgeFn),
              ("Delete edge to",    relDeleteEdgeFn)]
      } in case decodeRelPath path of {
             Nothing -> relCommands;
             Just _  -> relElemCommands
           }
  };

  -- Decoding function for paths on Rel. Yields either Nothing (if user clicks on background) 
  -- or Just i  if user clicks on element i.

  decodeRelPath :: Path -> Maybe Int;
  decodeRelPath path =
    let path' = tail path 
    in if head path' == 1 then
         Nothing
       else
         let n = length path' - 3
         in Just n;

  -- Typical paths:
  --   Background         1 1 0
  --   A                  1 0 0 0
  --   B                  1 0 1 0 0
  --   C                  1 0 1 1 0 0

  decodeRelElemPath :: Path -> Int;
  decodeRelElemPath path = 
    case decodeRelPath path of {
      Nothing -> error "RelDemo.decodeRelElemPath: should not occur";
      Just i  -> i
    };
  
  -- Definition of command functions for Rel

  relUnmarkFn val path x y lhs rhs clip =
    let rhs' = "unmarkRel (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");


  relInverseFn val path x y lhs rhs clip =
    let rhs' = "inverseRel (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");


  relReflexClosureFn val path x y lhs rhs clip =
    let rhs' = "reflexClosureRel (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");


  relSymmClosureFn val path x y lhs rhs clip =
    let rhs' = "symmClosureRel (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");


  relTransClosureFn val path x y lhs rhs clip =
    let rhs' = "transClosureRel (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");

  
  relEvaluateFn val path x y lhs rhs clip =
  (Just (showRel val), Nothing, Just "OK");


  relMarkFn val path x y lhs rhs clip =
    let {i = decodeRelElemPath path;
         rhs' = "markRel " ++ show i ++ " (" ++ rhs ++ ")"
    } in (Just rhs', Nothing, Just "OK");


  relAddEdgeFn val path x y lhs rhs clip =
    case val of {
      Rel _ Nothing ->
        (Nothing, Nothing, Just "No marked element present!");
      Rel edges (Just j) ->
        let {i = decodeRelElemPath path;
             rhs' = "addEdgeRel " ++ show j ++ " " ++ show i ++ " (" ++ rhs ++ ")"
        } in (Just rhs', Nothing, Just "OK")
    };

   
  relDeleteEdgeFn val path x y lhs rhs clip =
    case val of {
      Rel _ Nothing ->
        (Nothing, Nothing, Just "No marked element present!");
      Rel edges (Just j) ->
        let {i = decodeRelElemPath path;
             rhs' = "deleteEdgeRel " ++ show j ++ " " ++ show i ++ " (" ++ rhs ++ ")"
        } in (Just rhs', Nothing, Just "OK")
    };


  relCopyFn val path x y lhs rhs clip =
    (Nothing, Just rhs, Just "OK");
  

  relInterFn val path x y lhs rhs clip =
    let rhs' = "(" ++ clip ++ ") `interRel` (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");


  relUnionFn val path x y lhs rhs clip =
    let rhs' = "(" ++ clip ++ ") `unionRel` (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");


  relCompFn val path x y lhs rhs clip =
    let rhs' = "(" ++ clip ++ ") `compRel` (" ++ rhs ++ ")"
    in (Just rhs', Nothing, Just "OK");



  -- Operations on relations -------------------------------------------------------

  emptyRel :: Rel;
  emptyRel =  Rel [] Nothing;


  mkRel :: [Int] -> Rel;
  mkRel ns = Rel ns Nothing;


  markRel :: Int -> Rel -> Rel;
  markRel i rel =
    case rel of
      Rel edges _ -> Rel edges (Just i);


  ummarkRel :: Rel -> Rel;
  unmarkRel rel =
    case rel of
      Rel edges _ -> Rel edges Nothing;


  addEdgeRel :: Int -> Int -> Rel -> Rel;
  addEdgeRel i j rel =
    case rel of
      Rel edges mm -> Rel ((i,j):edges) mm;

  
  deleteEdgeRel :: Int -> Int -> Rel -> Rel;
  deleteEdgeRel i j rel =
    case rel of 
      Rel edges mm -> 
        let edges' = [edge | edge <- edges, ((i /= fst edge) || (j /= snd edge))]
        in Rel edges' mm;
  

  inverseRel :: Rel -> Rel;
  inverseRel rel =
    case rel of
      Rel edges mm -> Rel (map swap edges) mm;

  swap edge = (snd edge, fst edge);

  -- Reflexive, symmetric and transitive closure of relations

  reflexClosureRel :: Rel -> Rel;
  reflexClosureRel rel =
    case rel of
      Rel edges mm -> 
        let edges' = [(i,i) | i <- intLabels]
        in Rel (edges' ++ edges) mm;


  symmClosureRel :: Rel -> Rel;
  symmClosureRel rel =
    case rel of
      Rel edges mm -> 
        let edges' = concat [ [e, swap e] | e <- edges]
        in Rel edges' mm;


  transClosureRel :: Rel -> Rel;
  transClosureRel rel =
    case rel of
      Rel edges mm ->
        let edges' = fixPoint (transitives edges)
        in Rel edges' mm;


  -- Union, intersection and composition of relations
  unionRel, interRel, compRel :: Rel -> Rel -> Rel;

  unionRel rel1 rel2 =
    case (rel1, rel2) of
      (Rel edges1 _, Rel edges2 _) ->
        let edges' = nub2 (edges1 ++ edges2)
        in Rel edges' Nothing;



  interRel rel1 rel2 =
    case (rel1, rel2) of
      (Rel edges1 _, Rel edges2 _) ->
        let edges' = filter (\edge -> elemEdge edge edges1) edges2
        in Rel edges' Nothing;


  compRel rel1 rel2 =
    case (rel1, rel2) of
      (Rel edges1 _, Rel edges2 _) ->
         let edges  = nub2 (compEdges edges1 edges2)
         in Rel edges Nothing;


  -- Subsidiary functions --------------------------------

  -- Test for edge membership in a list of edges
  elemEdge :: Edge -> [Edge] -> Bool;
  elemEdge edge edges = or (map (edgeEq edge) edges);


  -- Eliminate duplicates in a list of edges  (should use "nubBy" !)
  nub2 :: [Edge] -> [Edge];
  nub2 xs =
    case xs of {
      []     -> [];
      x:xs   -> let xs' = filter (nEdgeEq x) xs
                in x : nub2 xs'
    };


  edgeEq, nEdgeEq :: Edge -> Edge -> Bool;
  edgeEq edge1 edge2 = (fst edge1 == fst edge2) && (snd edge1 == snd edge2);

  nEdgeEq edge1 edge2 = not (edgeEq edge1 edge2);

  -- Compute relational composition of sets of edges (NB: this does not take the nub of the result)
  compEdges :: Edges -> Edges -> Edges;
  compEdges edges1 edges2 =
    concat [ [(fst edge1, snd edge2) | edge2 <- edges2, (snd edge1 == fst edge2)]  | edge1 <- edges1];

  -- The "extend" function computes:  
  --        extend R S  --->  R union (R . S)
  extend :: Edges -> Edges -> Edges;
  extend edges1 edges2 =
    nub2 (edges1 ++ (edges1 `compEdges` edges2));

  -- The "transitives" function computes the infinite list of approximations to the transitive closure of R
  --   transitives R --->  [R, R union R.R,  R union R.R union R.R.R, . . . ]
  transitives :: Edges -> [Edges];
  transitives edges = iterate (extend edges) edges;

  -- The "fixPoint" function determines the first relation in a list such that the relation contains
  -- the same number of pairs as its successor
  -- number of elements
  fixPoint :: [Edges] -> Edges;
  fixPoint edgess =
    case edgess of
      edges1 : (edges2 : edgess) -> 
        if length edges1 == length edges2 then
           edges1
        else
           fixPoint (tail edgess)
}
