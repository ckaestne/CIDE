-- Standard styles

module Styles 
    (comment1, comment2, comment3, comment4, comment5
    ,open, open1
    ,brown, olive, lightYellow
    ,box1, box2, box3, box4, boxWidth, boxHeight
    ,fmt1, fmt2, fmt3, fmt4, fmt5
    ,ts1, ts2, ts3, ts4
    ,list, listPack, vList, bareList, bareTable, bareTable', table, table', imageList, heteroList
    ,readImages
    ,mag, enlarge, shrink
    ,rotate, twist, twist'
    ,col, invStyle
    ,enumText, enumImage
    ,listStyle, blueList, yellowList, blueListVert, yellowListVert
    ,treeStyle, blueTree, yellowTree, blueTreeVert, yellowTreeVert

    ,hint, open2, open2'
    ) where {


-- Colours

brown, olive, lightYellow :: Color;

brown                   =  RGB 100 50 0;
olive                   =  RGB 0 100 50;
lightYellow             =  lighten . lighten $ yellow;


-- Comment styles

comment1, comment2, comment3, comment4, comment5
                        :: Style;

none			:: Maybe String;
none			=  Nothing;

comment1                =  let fmt = Format SansSerif 20 green False False
                           in Comment yellow green fmt none; 
                             

comment2                =  let fmt = Format SansSerif 20 white False False
                           in Comment green black fmt none;

comment3                =  let fmt = Format SansSerif 20 red False False 
                           in Comment yellow red fmt none;

comment4                =  let fmt = Format SansSerif 14 brown False False
                           in Comment trans green fmt none;

comment5                =  let fmt = Format SansSerif 14 trans False False
                           in Comment trans trans fmt none;

open, open1             :: String -> Style;
open name               =  let fmt = Format SansSerif 12 red False False 
                           in Comment trans red fmt (Just name);

open1 name              =  let fmt = Format SansSerif 10 white False False 
                           in Comment blue blue fmt (Just name);



-- Boxes

box1, box2, box3, box4  :: Box;

box1                    =  Box 60 15 white black;
box2                    =  Box 80 20 yellow black;
box3                    =  Box 250 24 brown black;
box4                    =  Box 30 15 yellow blue;

boxWidth, boxHeight	:: Box -> Int;

boxWidth box            =  case box of Box w _ _ _ -> w;
boxHeight box           =  case box of Box _ h _ _ -> h;


-- Formats

fmt1, fmt2, fmt3, fmt4, fmt5  :: Format;

fmt1                    =  Format SansSerif 12 blue  False False;
fmt2                    =  Format SansSerif 12 red   False False;
fmt3                    =  Format SansSerif 18 green False False;
fmt4                    =  Format SansSerif 20 white False False;
fmt5                    =  Format SansSerif 10 red   False False;


-- Text styles

ts1, ts2, ts3, ts4      :: Style;

ts1                     =  Text 1 fmt1;
ts2                     =  Text 1 fmt2;
ts3                     =  Text 1 fmt3;
ts4                     =  Text 1 fmt4;


-- List styles

list, listPack, vList   :: Box -> Format -> Style;

list box fmt            =  let x = inflate (boxWidth box)
                           in List (FText box 2 fmt) (x, 0);

listPack box fmt        =  List (FText box 2 fmt);

vList box fmt           =  let y = inflate (boxHeight box)
                           in List (FText box 2 fmt) (0, y);

-- An internal scale factor that determines the spacing of list elements
inflate                 :: Int -> Int;
inflate n               =  (n * 5) `div` 4;
   

-- Lift an arbitrary style to a List style. If horiz is True the list is oriented horizontally,
-- otherwise vertically. The w and h parameters define both the spacing of the list elements
-- and also the size of the selection box (for when the user double-clicks to select an element).

bareList   		:: Bool -> Style -> Int -> Int -> Style;

bareList horiz sty w h  =  let {
                             box = Box w h trans trans;
                             fs = FLink box NoLink sty;
                             offset = if horiz then (w,0) else (0,h)
                           } in List fs offset;




-- Lift an arbitrary style to a tabular style. The w and h parameters are as defined above.
-- The bareTable' version interchanges the rows and columns.

bareTable, bareTable'   :: Style -> Int -> Int -> Style;

bareTable  sty w h      =  bareList False (bareList True  sty w h) w h;

bareTable' sty w h      =  bareList True  (bareList False sty w h) w h;



table, table'           :: Box -> Format -> Style;

table box fmt           =  let { w = inflate (boxWidth  box);
                                 h = inflate (boxHeight box);
                                 rowSty = List (FText box 2 fmt) (w, 0)
                           } in List (FLink NoBox NoLink rowSty) (0, h);

table' box fmt          =  let { w = inflate (boxWidth  box);
                                 h = inflate (boxHeight box);
                                 colSty = List (FText box 2 fmt) (0, h)
                           } in List (FLink NoBox NoLink colSty) (w, 0);


-- A style for displaying a list of Images. The w and h parameters are as defined above.

imageList               :: Int -> Int -> Style;

imageList w h           =  error "Styles.Not yet defined";


-- A heterogeneous style for lists. The function takes a list of styles and renders each
-- element of the list to which the style is applied with the corresponding style.
-- The arguments are:
--     cell	- The box used for the cells of the backbone list
--     fmt      - The format used for the constructors of the backbone list
--     dx       - The horizontal link-length for the backbone list
--     dy       - The distance below the backbone list that the element are displayed
--     styles   - A list of styles (which, if not long enough, are recycled)  the elements 
--              - of which are used as styles for the elements of the subject list.

heteroList cell fmt dx dy styles =
  let f = \ss ->
    case ss of {
      []          -> Hide;		-- Is this a sensible definition (it was Default)?   ??
      sty : stys  -> let {
                       headLink  = Link (0, dy) False gray NoBox;
                       tailLink  = Link (dx, 0) True  gray cell;
                       headStyle = FLink cell headLink sty;
                       tailStyle = FLink cell tailLink (f stys);
                       nilStyle  = VJuxta cStyle [] True NoFormat;
                       cStyle    = CText cell fmt;
                       consStyle = VJuxta cStyle [headStyle, tailStyle] True NoFormat
                     } in Data [nilStyle, consStyle]
  }
  in f (take 50 (cycle styles));


-- Scaling a style by a factor of (m/n)

mag                     :: Int -> Int -> Style -> Style;

mag m n                 =  magStyle (\k -> (k * m) `div` n);


enlarge, shrink         :: Style -> Style;
enlarge                 =  mag 3 2;
shrink                  =  mag 2 3;

-- Rotations (in degrees, clockwise)

rotate                  :: Int -> Style -> Style;

rotate                  =  flip Rotate;


twist, twist'           :: Style -> Style;

twist                   =  flip Rotate 90;
twist'                  =  flip Rotate (-90);

-- Mapping the colours in a style

col                     :: (Color -> Color) -> Style -> Style;

col                     =  colStyle;

invStyle                :: Style -> Style;

invStyle                =  colStyle invColor;


-- Images

-- Read a list of images, from a directory defined (wrt the "images" directory) by "path", with
-- file names "names" and all with the suffix "suffix".

readImages              :: String -> [String] -> String -> [Image];

readImages path names suffix
                        =  [imageFile (path ++ name ++ suffix) | name <- names];



-- Data structures

-- Form a style that displays the constructors of an enumerated datatype as text boxes

enumText                    :: Box -> Format -> Style;

enumText box fmt            =  let { cStyle = CText box fmt;
                                     vStyle = VJuxta cStyle [] True NoFormat
                               } in Data [vStyle];


-- Form a style that displays the constructors of an enumerated datatype as images

enumImage               :: [Image] -> Style;

enumImage images        =  let vStyles = [VJuxta (CImage img) [] True NoFormat | img <- images]
                           in Data vStyles;


-- A function for generating styles for displaying Haskell lists as linked-lists

listStyle               :: Bool -> Bool -> Int -> Int -> Int -> Color -> Color -> Color -> Color -> Style;

listStyle hOrient showCon w h ptSize txtColor bgColor edgeColor linkColor 
                        =  let {
                             font     = SansSerif;
                             fmt      = Format font ptSize txtColor False False;
                             bgColor1 = (lighten . lighten) bgColor;
                             cBox     = Box w h bgColor edgeColor;              
                             fBox     = Box w h bgColor1 edgeColor;
                             cStyle   = if showCon then CText cBox fmt else CHide;
                             fStyle1  = FText fBox 2 fmt;
                             offset   = if hOrient then ((2 * w), 0) else (0, (2 * h));
                             link     = Link offset hOrient linkColor cBox;
                             fStyle2  = FLink fBox link This;
                             vStyle1  = VJuxta cStyle [] hOrient NoFormat;
                             vStyle2  = VJuxta cStyle [fStyle1, fStyle2] hOrient NoFormat
                           } in Data [vStyle1, vStyle2];


blueList, yellowList, blueListVert, yellowListVert :: Style;

blueList                = listStyle True  True 40 15 12 white blue   black gray;
yellowList              = listStyle True  True 60 20 15 blue  yellow gray  magenta;
blueListVert            = listStyle False True 40 15 12 white blue   black gray;
yellowListVert          = listStyle False True 60 20 15 blue  yellow gray  magenta;


-- A function for generating styles for displaying tree values as trees

treeStyle               :: Bool -> Bool -> Int -> Int -> Int -> Color -> Color -> Color -> Style;

treeStyle hOrient showCon w h ptSize txtColor bgColor linkColor
                        =  let {
                             w2       = w `div` 2;
                             h2       = h `div` 2;
                             font     = SansSerif;
                             fmt      = Format font ptSize txtColor False False;
                             bgColor1 = (lighten . lighten) bgColor;
                             cBox     = Box w h bgColor black;              
                             fBox     = Box w h bgColor1 black;
                             cStyle   = if showCon then CText cBox fmt else CHide;
                             fStyle1  = FText fBox 2 fmt;
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


blueTree, yellowTree, blueTreeVert, yellowTreeVert :: Style;

blueTree                =  treeStyle True  False 20 15 12 white blue   gray;
yellowTree              =  treeStyle True  True  40 20 15 blue  yellow magenta;
blueTreeVert            =  treeStyle False False 20 15 12 white blue   gray;
yellowTreeVert          =  treeStyle False True  40 20 15 blue  yellow magenta;


-- Supplementary defs

open2                   =  enlarge . open;

open' name              =  let fmt = Format SansSerif 12 red False False
                           in Comment lightGray red fmt (Just name);

open2'                  =  enlarge . open';

hint                    :: Bool -> Style;
hint flag               =  let fmt = Format SansSerif 14 (if flag then red else trans) False False
                           in Comment trans green fmt none;



-- Internal definitions ---------------------------------------------------------------------------------

magStyle                :: (Int -> Int) -> Style -> Style;

magStyle r style        =  case style of {
                             Text mode fmt          -> Text mode (magFormat r fmt);
                             Data vss               -> Data (map (magVStyle r) vss);
                             List fs (x,y)          -> List (magFStyle r fs) (r x, r y);
                             Comment bgc ec fmt lnk -> Comment bgc ec (magFormat r fmt) lnk;
                             Rotate sty theta       -> Rotate (magStyle r sty) theta;

                             sty                    -> sty
                           };


magFormat               :: (Int -> Int) -> Format -> Format;

magFormat r fmt         =  case fmt of {
                             NoFormat                   -> NoFormat;
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

magOffset r p           =  (r (fst p), r (snd p));

-- --------------------------------------------------------------------------

colStyle                :: (Color -> Color) -> Style -> Style;

colStyle f style        =  case style of {
                             Text mode fmt          -> Text mode (colFormat f fmt);
                             Data vs                -> Data (map (colVStyle f) vs);
                             List fs offset         -> List (colFStyle f fs) offset;
                             Comment bgc ec fmt lnk -> Comment (f bgc) (f ec) (colFormat f fmt) lnk;
                             Clip is box            -> Clip (colStyle f is) (colBox f box);
                             Rotate sty theta       -> Rotate (colStyle f sty) theta;

                             style                  -> style
                           };


colFormat               :: (Color -> Color) -> Format -> Format;

colFormat f fmt         =  case fmt of {
                             NoFormat                     -> NoFormat;
                             Format font ptSize fgc ib ii -> Format font ptSize (f fgc) ib ii
                           };


colBox                  :: (Color -> Color) -> Box -> Box;

colBox f box            =  case box of {
                             NoBox           ->  NoBox;
                             Box w h bgc ec  ->  Box w h (f bgc) (f ec)
                           };

colVStyle               :: (Color -> Color) -> VStyle -> VStyle;

colVStyle f vs          =  case vs of {
                             VHide                               -> VHide;
                             VJuxta cs fss h ann                 -> 
                                 VJuxta (colCStyle f cs) (map (colFStyle f) fss) h (colFormat f ann);
                             VIndiv box cs cOff fss fos decor an ->
                                 VIndiv (colBox f box) (colCStyle f cs) cOff 
                                        (map (colFStyle f) fss) fos 
                                        decor (colFormat f an)
                           };


colCStyle               :: (Color -> Color) -> CStyle -> CStyle;

colCStyle f cs          =  case cs of {
                             CText box fmt -> CText (colBox f box) (colFormat f fmt);
                             csty          -> csty
                           };


colFStyle               :: (Color -> Color) -> FStyle -> FStyle;

colFStyle f fs          =  case fs of {
                             FHide -> FHide;
                             FText box mode fmt -> FText (colBox f box) mode (colFormat f fmt);
                             FImage box         -> FImage (colBox f box);
                             FLink box link sty -> FLink (colBox f box) (colLink f link) (colStyle f sty)
                           };


colLink                :: (Color -> Color) -> Link -> Link;

colLink f link          =  case link of {
                             NoLink             -> NoLink;
                             Link off hf lc box -> Link off hf (f lc) (colBox f box)
                           }

}
