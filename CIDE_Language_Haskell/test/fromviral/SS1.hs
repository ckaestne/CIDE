-- This is the standard stylesheet. It is optimised for TFT (rather than CRT) display devices.

module SS1 (
      comment1, comment2, comment2b, comment2m, comment3
    , open, open1, openLib
    , brown, olive, lightYellow
    , box1, box2, boxWidth, boxHeight
    , fmt1, fmt2, fmt3
    , ts1, ts2, ts3
    , list, list', bareList, bareList', bareTable, bareTable', table, table'
    , list1, list2, list1', list2', table1, table2, table1', table2'
    , mag, enlarge, shrink
    , rotate, twist, twist'
    , listStyle, blueList, yellowList, blueList', yellowList'
    , treeStyle, blueTree, yellowTree, blueTree', yellowTree'
) where {

  -- Colours

  brown, olive, lightYellow :: Color;

  brown                   =  RGB 100 50 0;
  olive                   =  RGB 0 100 50;
  lightYellow             =  lighten . lighten $ yellow;


  comment1, comment2, comment2b, comment2m, comment3 :: Style;

  -- For headings
  comment1        = let fmt = Format SansSerif 20 white False False
                    in Comment green black fmt Nothing;

  -- For ordinary text
  comment2        = let fmt = Format SansSerif 14 brown False False
                    in Comment trans green fmt Nothing;

  -- A bigger variant of comment2, with a white background
  comment2b       = let fmt = Format SansSerif 16 brown False False
                    in Comment white green fmt Nothing;

  -- A monospaced variant of comment2
  comment2m       = let fmt = Format Monospaced 14 brown False False
                    in Comment trans green fmt Nothing;

  -- A warning heading
  comment3        = let fmt = Format SansSerif 20 red False False 
                    in Comment yellow red fmt Nothing;

  -- A hyperlink
  open name       = let fmt = Format SansSerif 12 red False False 
                    in Comment trans red fmt (Just name);

  -- A stronger variant of "open"
  open1 name      = let fmt = Format SansSerif 12 red True False 
                    in Comment trans red fmt (Just name);

  -- A hyperlink to a library module
  openLib name    = let fmt = Format SansSerif 12 brown False False 
                    in Comment trans red fmt (Just name);



  box1, box2 :: Box;

  box1 = Box 60 15 white black;
  box2 = Box 30 15 yellow blue;


  boxWidth, boxHeight :: Box -> Int;

  boxWidth box  = case box of Box w _ _ _ -> w;
  boxHeight box = case box of Box _ h _ _ -> h;



  fmt1, fmt2, fmt3  :: Format;

  fmt1       =  Format SansSerif 12 blue  False False;
  fmt2       =  Format SansSerif 12 red   False False;
  fmt3       =  Format SansSerif 10 red   False False;


  -- Text styles
  -- ts1, ts2, ts3 :: Style;

  ts1 = Text 1 fmt1;
  ts2 = Text 1 fmt2;
  ts3 = Text 1 fmt3;


  -- Note the convention that (') denotes a transposed version of the vector or array

  -- Create a boxed-list style

  list, list' :: Box -> Format -> Style;

  list box fmt = let {mode = 1;
                      fStyle = FText box mode fmt;
                      x = inflate (boxWidth box)
                 } in List fStyle (x, 0);


  list' box fmt = let {mode = 1;
                       fStyle = FText box mode fmt;
                       y = inflate (boxHeight box)
                  } in List fStyle (0, y);
                     

  inflate n  = (n * 5) `div` 4;

  -- Some specialised versions

  list1, list1', list2, list2' :: Style;

  list1  = list  box1 fmt1;
  list2  = list  box2 fmt3;
  list1' = list' box1 fmt1;
  list2' = list' box2 fmt3;


  -- Lifts an arbitrary style to a List style. The w and h params define both the spacing of
  -- the list elements (either horizontally or vertically -- not both) and also the size of
  -- the selection box that defines the region in which a double-click causes the field
  -- to be selected.

  bareList, bareList' :: Int -> Int -> Style -> Style;

  bareList w h sty =
    let {offset = (w, 0);
         box = Box w h trans trans;
         fStyle = FLink box NoLink sty
    } in List fStyle offset;

  bareList' w h sty =
    let {offset = (0, h);
         box = Box w h trans trans;
         fStyle = FLink NoBox NoLink sty
    } in List fStyle offset;


  -- Create a boxed-table style

  table, table' :: Box -> Format -> Style;

  table box fmt = 
    let {rowSty = list box fmt;
         x = inflate (boxWidth box);
         y = inflate (boxHeight box)
    } in bareList' x y rowSty;

  table' box fmt =
    let {colSty = list' box fmt;
         x = inflate (boxWidth box);
         y = inflate (boxHeight box)
    } in bareList x y colSty;


  table1, table2, table1', table2' :: Style;

  table1  = table   box1 fmt1;
  table2  = table   box2 fmt3;
  table1' = table'  box1 fmt1;
  table2' = table'  box2 fmt3;


  -- Create a bare-table style

  bareTable, bareTable' :: Int -> Int -> Style -> Style;

  bareTable x y sty =
    let rowSty = bareList x y sty
    in bareList' x y rowSty;

  bareTable' x y sty =
    let colSty = bareList' x y sty
    in bareList x y colSty;


  -- A function for generating styles for displaying Haskell lists as linked-lists

  listStyle  :: Bool -> Bool -> Int -> Int -> Int -> Color -> Color -> Color -> Color -> Style;

  listStyle hOrient showCon w h ptSize txtColor bgColor edgeColor linkColor =
    let {font     = SansSerif;
         fmt      = Format font ptSize txtColor False False;
         bgColor1 = (lighten . lighten) bgColor;
         cBox     = Box w h bgColor edgeColor;              
         fBox     = Box w h bgColor1 edgeColor;
         cStyle   = if showCon then CText cBox fmt else CHide;
         mode     = 1;
         fStyle1  = FText fBox mode fmt;
         offset   = if hOrient then ((2 * w), 0) else (0, (2 * h));
         link     = Link offset hOrient linkColor cBox;
         fStyle2  = FLink fBox link This;
         vStyle1  = VJuxta cStyle [] hOrient NoFormat;
         vStyle2  = VJuxta cStyle [fStyle1, fStyle2] hOrient NoFormat
     } in Data [vStyle1, vStyle2];



  -- Some specialised versions of the above function

  blueList, yellowList, blueList', yellowList' :: Style;

  blueList                = listStyle True  True 40 15 12 white blue   black gray;
  yellowList              = listStyle True  True 60 20 15 blue  yellow gray  magenta;
  blueList'               = listStyle False True 40 15 12 white blue   black gray;
  yellowList'             = listStyle False True 60 20 15 blue  yellow gray  magenta;


  -- A function for generating styles for displaying tree values as trees

  treeStyle               :: Bool -> Bool -> Int -> Int -> Int -> Color -> Color -> Color -> Style;

  treeStyle hOrient showCon w h ptSize txtColor bgColor linkColor =
    let {w2       = w `div` 2;
         h2       = h `div` 2;
         font     = SansSerif;
         fmt      = Format font ptSize txtColor False False;
         bgColor1 = (lighten . lighten) bgColor;
         cBox     = Box w h bgColor black;              
         fBox     = Box w h bgColor1 black;
         cStyle   = if showCon then CText cBox fmt else CHide;
         mode     = 1;
         fStyle1  = FText fBox mode fmt;
         link1    = if hOrient then
                      Link ((2 * w), (-2 * h)) False linkColor cBox
                    else
                      Link ((-2 * w), (2 * h)) True linkColor cBox;
         link2    = if hOrient then
                      Link ((2 * w), (2 * h)) False linkColor cBox
                    else
                      Link ((2 * w), (2 * h)) True linkColor cBox;
                    fStyle21 = FLink fBox link1 This;
                    fStyle22 = FLink fBox link2 This;
                    bgBox    = NoBox;
                    wOffset  = if showCon then w else 0;
                    hOffset  = if showCon then h else 0;
                    vStyle1  = VJuxta cStyle [fStyle1] hOrient NoFormat;
                    vStyle2  = if hOrient then
                                 VIndiv bgBox cStyle (0,0)
                                        [fStyle21, fStyle22] [(wOffset,(-h2)), (wOffset,h2)]
                                        NoPic NoFormat
                               else
                                 VIndiv bgBox cStyle (0,0)
                                        [fStyle21, fStyle22] [((-w2),hOffset), (w2,hOffset)]
                                        NoPic NoFormat
   } in Data [vStyle1, vStyle2];


  -- Some specialised versions of the above function

  blueTree, yellowTree, blueTreeVert, yellowTreeVert :: Style;

  blueTree                =  treeStyle True  False 20 15 12 white blue   gray;
  yellowTree              =  treeStyle True  True  40 20 15 blue  yellow magenta;
  blueTree'               =  treeStyle False False 20 15 12 white blue   gray;
  yellowTree'             =  treeStyle False True  40 20 15 blue  yellow magenta;




  -- Scaling a style by a factor of (m/n)

  mag                     :: Int -> Int -> Style -> Style;

  mag m n                 =  magStyle (\k -> (k * m) `div` n);

  -- Scaling by a pre-defined factor

  enlarge, shrink         :: Style -> Style;

  enlarge                 =  mag 3 2;
  shrink                  =  mag 2 3;

  -- Rotations (in degrees, clockwise)

  rotate                  :: Int -> Style -> Style;

  rotate                  =  flip Rotate;


  twist, twist'           :: Style -> Style;

  twist                   =  flip Rotate 90;
  twist'                  =  flip Rotate (-90);

  -- Internal definitions ---------------------------------------------------------------------------------

  magStyle                :: (Int -> Int) -> Style -> Style;

  magStyle r style        =  case style of {
                               Text mode fmt          -> Text mode (magFormat r fmt);
                               Data vss               -> Data (map (magVStyle r) vss);
                               List fs (x,y)          -> List (magFStyle r fs) (r x, r y);
                               Comment bgc ec fmt lnk -> Comment bgc ec (magFormat r fmt) lnk;
                               Rotate sty theta       -> Rotate (magStyle r sty) theta;
                               View fn sty            -> View fn (magStyle r sty);

                               sty                    -> sty
                             };


  magFormat               :: (Int -> Int) -> Format -> Format;

  magFormat r fmt         =  case fmt of {
                               NoFormat                  -> NoFormat;
                               Format ft ps fgc isB isI -> Format ft (r ps) fgc isB isI
                             };


  magBox                  :: (Int -> Int) -> Box -> Box;

  magBox r box            =  case box of {
                               NoBox            -> NoBox;
                               Box w h bgc ec   -> Box (r w)  (r h) bgc ec
                             };


  magVStyle               :: (Int -> Int) -> VStyle -> VStyle;

  magVStyle r vStyle      =  case vStyle of {
                               VHide -> VHide;
                               VJuxta cStyle fStyles horiz annot -> 
                                   VJuxta (magCStyle r cStyle) (map (magFStyle r) fStyles) 
                                          horiz (magFormat r annot);
                               VIndiv box cStyle cOffset fStyles fOffsets decor annot ->
                                   VIndiv (magBox r box) (magCStyle r cStyle) (magOffset r cOffset)
                                          (map (magFStyle r) fStyles)  (map (magOffset r) fOffsets)
                                          decor (magFormat r annot)
                             };


  magCStyle               :: (Int -> Int) -> CStyle -> CStyle;

  magCStyle r cStyle      =  case cStyle of {
                               CHide -> CHide;
                               CText box fmt -> CText (magBox r box) (magFormat r fmt);
                               CImage img -> CImage img
                             };


  magFStyle               :: (Int -> Int) -> FStyle -> FStyle;

  magFStyle r fStyle      =  case fStyle of {
                               FHide -> FHide;
                               FText box mode fmt -> FText (magBox r box) mode (magFormat r fmt);
                               FImage box -> FImage (magBox r box);
                               FLink box link sty -> 
                                   FLink (magBox r box) (magLink r link) (magStyle r sty)
                             };


  magLink                 :: (Int -> Int) -> Link -> Link;

  magLink r link          =  case link of {
                               NoLink -> NoLink;
                               Link offset ho lc box -> Link (magOffset r offset) ho lc (magBox r box)
                             };


  magOffset               :: (Int -> Int) -> (Int, Int) -> (Int, Int);

  magOffset r p           =  (r (fst p), r (snd p))
}

  
