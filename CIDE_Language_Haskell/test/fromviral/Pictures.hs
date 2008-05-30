-- This module defines a collection of functions for operating on pictures, both bit-mapped
-- and synthetic.  Some of the definitions are loosely based on those
-- in "Haskell: the Craft of Functional Programming" by Simon Thompson.

-- Note: this library represents pictures using the primitive Pic type (rather
-- than defining a concrete representation and exporting an abstract type). Thus, the types, constants
-- and functions defined in the (pervasively imported) library module "Pic" may be freely intermixed
-- with the definitions in this module.

-- This type abbreviation is used for 'coloured pictures':
--     type CPic = Color -> Color -> Pic
-- The first argument specifies the background colour, the second the edge colour.

module Pictures (
        
-- Move pictures

   move,        -- :: Int -> Int -> Pic -> Pic  move x y pic            - pic translated by (x,y)
   shift,       -- :: Pic -> Pic                shift pic               - pic repositioned at local origin


-- Superimpose pictures
	
   (<+>),       -- :: Pic -> Pic -> Pic         pic1 <+> pic2           - pic1 superimposed on pic2

   superimpose, -- :: [Pic] -> Pic              superimpose pics        - list of pics, superimposed

   (<|>),       -- :: Pic -> Pic -> Pic         pic1 <|> pic2           - pic1 alongside pic2
   (<->),       -- :: Pic -> Pic -> Pic         pic1 <-> pic2           - pic1 above pic2

   beside,      -- :: [Pic] -> Pic              beside pics             - list of pics alongside each other
   above,       -- :: [Pic] -> Pic              beside pics             - list of pics above each other

   (<*|>),      -- :: Int -> Pic                n <*|> pic              - n copies of pic alongside each other
   (<*->),      -- :: Int -> Pic                n <*-> pic              - n copies of pic above each other


-- Add positional information 

   bBox,        -- :: Pic -> Maybe (Int, Int, Int, Int)
                --                             bBox pic                 - bounding box (x1,x2,y1,y2) of pic
						
   withAxes,    -- :: Pic -> Pic               withAxes pic             - pic with axes superimposed
   withBB,      -- :: Pic -> Pic               withBB pic               - pic with bounding box superimposed


-- Transform pictures

   flipH',flipV',--:: Pic -> Pic                flipH' pic              - pic reflected about horiz/vert axis
   flipH, flipV, --:: Pic -> Pic                flipH pic               - pic reflected and repositioned

   invertColor, -- :: Pic -> Pic                invertColor pic         - pic, with colours inverted.

   scale,       -- :: Double -> Pic -> Pic      scale sf pic            - pic, scaled by scalefactor sf

   turn,        -- :: Int -> Pic -> Pic         turn delta pic          - pic, rotated by delta degrees
   turn',       -- :: Double -> Pic -> Pic      turn' theta pic         - pic, rotated by theta radians

-- Create pictures

   image,       -- :: String -> CPic            image fName bgc ec      - coloured picture from bitmap in fName      

   polygon,     -- :: Int -> Int -> CPic        polygon r n bc ec       - n-sided coloured polygon of radius r
   stelGon,     -- :: Int -> Int -> Int -> CPic 
                --                              stelGon r1 r2 n         - n-sided coloured stellated polygon,
                --                                                      - with inner/outer radii of r1 and r2

-- Some picture constants:

   pawn,        -- :: Pic                                               - Black pawn
   pawn',       -- :: Pic                                               - White pawn
   knight,      -- :: Pic                                               - Black knight
   queen'       -- :: Pic                                               - White queen                    
   
)

-- ---------------------------------------------------------------------------------------------------------------          

where {

axisLength                      =  50;
axisOverhang                    =  5;

axisColor                       =  black;

-- A pair of axes (useful for indicating the origin of a picture)
axes :: Pic;
axes = let { a = - axisOverhang;
             b = axisLength + axisOverhang;
             xAxis = Trans a 0 $ Line b 0 axisColor;
             yAxis = Trans 0 a $ Line 0 b axisColor
       } in xAxis `Super` yAxis;


-- Superimpose one picture (the first arg) on another (the second arg)
(<+>) :: Pic -> Pic -> Pic;
(<+>) =  Super;


-- Superimpose a list of pictures (the head of the list on top of its tail)
superimpose :: [Pic] -> Pic;
superimpose =  foldr Super NoPic;


-- Superimpose a pair of axes on a picture
withAxes :: Pic -> Pic;
withAxes  = (axes <+>);


-- Compute the bounding box of a picture: (xLeft, xRight, yTop, yBottom), in absolute coords
bBox :: Pic -> Maybe (Int, Int, Int, Int);

bBox pic = case pic of {
               NoPic           -> Nothing;
               Line x y _      -> Just (min 0 x, max 0 x, min 0 y, max 0 y);
               Rect w h _ _    -> Just (0, w, 0, h);
               Poly vs _ _     -> let {xs = map fst vs; ys = map snd vs} in
                                      Just (minimum xs, maximum xs, minimum ys, maximum ys);
               Ellipse w h _ _ -> Just (0, w, 0, h);
               PicText fmt s   -> Nothing;                              -- Cannot easily compute this!
               PicImage img    -> let wh = imageSize img in Just (0, fst wh, 0, snd wh);    
               SelRect w h _ _ -> Just (0, w, 0, h);                                    

               p `Super` q     -> case (bBox p, bBox q) of {
                                    (Nothing, Nothing)            -> Nothing;
                                    (Nothing, Just (x1,x2,y1,y2)) -> Just (x1,x2,y1,y2);
                                    (Just (x1,x2,y1,y2), Nothing) -> Just (x1,x2,y1,y2);
                                    (Just (x1,x2,y1,y2), Just (x1',x2',y1',y2')) -> 
                                       Just (min x1 x1', max x2 x2', min y1 y1', max y2 y2')
                                  };

               Trans x y p     -> case bBox p of {
                                    Nothing            -> Nothing;
                                    Just (x1,x2,y1,y2) -> Just (x1+x, x2+x, y1+y, y2+y)
                                  };

               Animation fn    -> bBox (fn 0);			-- ie, compute bounding box at time zero.
               Interaction _ _ -> error "Pictures.bBox: Interaction not yet implemented"
            };


-- Superimpose the bounding box on a picture
withBB :: Pic -> Pic;
withBB p = case bBox p of {
             Nothing            -> p;
             Just (x1,x2,y1,y2) -> let box = Trans x1 y1 $ Rect (x2-x1) (y2-y1) trans axisColor
                                   in box `Super` p
           };


-- Move a picture by a specified amount

move :: Int -> Int -> Pic -> Pic;
move =  Trans;


-- Shift a picture so that the top LH corner of its bounding box is at the origin
shift :: Pic -> Pic;
shift pic = case bBox pic of {
              Nothing            -> pic;
              Just (x1,x2,y1,y2) -> Trans (- x1) (- y1) pic
            };


-- Place one picture beside (<|>) another picture or on top of (<->) another picture.

(<|>), (<->) :: Pic -> Pic -> Pic;

p <-> q = case bBox p of {
            Nothing            -> q;
            Just (x1,x2,y1,y2) -> Super p (Trans 0 y2 q)
          };

p <|> q = case bBox p of {
            Nothing            -> q;
            Just (x1,x2,y1,y2) -> Super p (Trans x2 0 q)
          };


-- Place a list of pictures beside/above each other
beside, above :: [Pic] -> Pic;

beside =  foldr (<|>) NoPic;
above  =  foldr (<->) NoPic;


-- Place n instances of a picture beside (<|*>) each other or above (<-*>) each other

(<*|>), (<*->) :: Int -> Pic -> Pic;

n <*|> pic = beside (replicate n pic);
n <*-> pic = above  (replicate n pic);


-- Place infinitely many instances of a picture beside (repeatH) each other
-- or above (repeatV) each other

repeatH, repeatV :: Pic -> Pic;

repeatH = beside . repeat;
repeatV = above  . repeat;


-- Reflect a picture in the horizontal axis (flipH') or the vertical axis (flipV')

flipH', flipV' :: Pic -> Pic;

flipH' pic = 
  case pic of {
    NoPic             -> NoPic;
    Line x y c        -> Line x (-y) c;
    Rect w h c1 c2    -> Trans 0 (-h) (Rect w h c1 c2);
    Ellipse w h c1 c2 -> Trans 0 (-h) (Ellipse w h c1 c2);
    Poly xys c1 c2    -> let xys' = [(fst xy, (- snd xy)) | xy <- xys] in Poly xys' c1 c2;
    PicImage img      -> error "Pictures.flipH': images cannot be reflected";
    SelRect w h c1 c2 -> Trans 0 (-h) (SelRect w h c1 c2);

    Super p q         -> Super (flipH' p) (flipH' q);
    Trans x y p       -> Trans x (-y) (flipH' p);

    Animation fn      -> Animation (\t -> flipH' (fn t));
    Interaction _ _   -> error "Pictures.flipH': Interaction not yet implemented"
  };

flipV' pic = 
  case pic of {
    NoPic             -> NoPic;
    Line x y c        -> Line (-x) y c;
    Rect w h c1 c2    -> Trans (-w) 0 (Rect w h c1 c2);
    Ellipse w h c1 c2 -> Trans (-w) 0 (Ellipse w h c1 c2);
    Poly xys c1 c2    -> let xys' = [(- fst xy, snd xy) | xy <- xys] in Poly xys' c1 c2;
    PicImage img      -> error "Pictures.flipV': images cannot be reflected";
    SelRect w h c1 c2 -> Trans (-w) 0 (SelRect w h c1 c2);

    Super p q         -> Super (flipV' p) (flipV' q);
    Trans x y p       -> Trans (-x) y (flipV' p);

    Animation fn      -> Animation (\t -> flipV' (fn t));
    Interaction _ _   -> error "Pictures.flipH': Interaction not yet implemented"
  };


-- Reflect a picture in the horiz/vertical axis, then shift it to the local origin
flipH, flipV :: Pic -> Pic;

flipH = shift . flipH';
flipV = shift . flipV';

-- Invert the colours in a picture
invertColor :: Pic -> Pic;

invertColor pic =
  case pic of {
    NoPic              -> NoPic;
    Line x y c         -> Line x y (invColor c);
    Rect w h bgc ec    -> Rect w h (invColor bgc) (invColor ec);
    Ellipse w h bgc ec -> Ellipse w h (invColor bgc) (invColor ec);
    Poly xys c1 c2     -> Poly xys (invColor c1) (invColor c2);
    PicImage img       -> error "Pictures.invertColor: the colours of images cannot be inverted";
    SelRect w h bgc ec -> SelRect w h (invColor bgc) (invColor ec);

    Super p q          -> Super (invertColor p) (invertColor q);
    Trans x y p        -> Trans x y (invertColor p);

    Animation fn       -> Animation (\t -> invertColor (fn t));
    Interaction _ _    -> error "Pictures.invertColor: Interaction not yet implemented"
  };


-- Scale all the dimensions in a picture
scale :: Double -> Pic -> Pic;

scale d pic =
  let f = \n -> round (d * fromInt n) 
  in case pic of {
       NoPic              -> NoPic;
       Line x y c         -> Line (f x) (f y) c;
       Rect w h bgc ec    -> Rect (f w) (f h) bgc ec;
       Ellipse w h bgc ec -> Ellipse (f w) (f h) bgc ec;
       PicText fmt s      -> let fmt' = case fmt of Format fnt ps c b i -> Format fnt (f ps) c b i
                             in PicText fmt' s;
       Poly xys bgc ec    -> Poly [(f (fst xy), f (snd xy)) | xy <- xys] bgc ec;
       PicImage img       -> let { wh = imageSize img; w = fst wh; h = snd wh;
                                   img' = scaleImage (f w) (f h) img			
                                 } in PicImage img';
       SelRect w h bgc ec -> SelRect (f w) (f h) bgc ec;

       Super pic1 pic2    -> Super (scale d pic1) (scale d pic2);
       Trans x y pic      -> Trans (f x) (f y) (scale d pic);

       Animation fn       -> Animation (\t -> scale d (fn t));
       Interaction _ _    -> error "Pictures.scale: Interaction not yet implemented"
     };


-- Rotate a picture clockwise through angle of theta radians
turn' :: Double -> Pic -> Pic;

turn' theta pic =
  let { s = sin theta;
        c = cos theta;
        f  = \x y -> round ((c * fromInt x) + (s * fromInt y));
        f' = \x y -> round ((c * fromInt y) - (s * fromInt x))}
  in case pic of {
       NoPic              -> NoPic;
       Line x y c         -> Line (f x y) (f' x y) c;
       Rect w h bgc ec    -> Poly [(0, 0),
                                   (f w 0, f' w 0),
                                   (f w h, f' w h),
                                   (f 0 h, f' 0 h)] bgc ec;
       Ellipse w h bgc ec -> if w /= h then
                                error "Pictures.turn: cannot rotate a non-circular ellipse"
                             else
                                let { radius = fromInt w / 2.0;
                                      phi = (pi/4.0) - theta;
                                      s2  = sqrt 2.0;
                                      x   = round (radius * ((s2 * cos phi) - 1.0));
                                      y   = round (radius * (1.0 - (s2 * sin phi)))}
                                in Trans x (- y) (Ellipse w w bgc ec);
       Poly xys bgc ec    -> let xys' = [let {x = fst xy; y = snd xy} in (f x y, f' x y) | xy <- xys]
                             in Poly xys' bgc ec;
       PicImage img       -> error "Pictures.turn: cannot rotate an image";
       SelRect w h bgc ec -> error "Pictures.turn: Selectable Rectangles cannot be rotated";

       Trans x y pic      -> let { x'   = f x y; y' = f' x y; 
                                   pic' = turn' theta pic
                             } in Trans x' y' pic';
       Super p q          -> Super (turn' theta p) (turn' theta q);

       Animation fn       -> Animation (\t -> turn' theta (fn t));
       Interaction _ _    -> error "Pictures.turn: Interaction not implemented"
     };


-- Rotate a picture clockwise through angle of theta degrees
turn :: Double -> Pic -> Pic;

turn theta = turn' (fromInt theta * pi / 180.0);

-- Create a picture from a bit-mapped image residing in file of specified filename

image :: String -> Pic;
image fName = let im = imageFile fName in PicImage im;

-- Regular polygon. The arguments are:
--       number of vertices
--       outer radius
--       background colour
--       edge colour

polygon :: Int -> Int -> CPic;

polygon n radius bgc ec =
  let { k  = 2.0 * pi / fromInt n;
        r  = fromInt radius;
        f  = \i -> let th = k * fromInt i 
                   in (round (r * cos th), round (r * sin th));
        vs = map f [0 .. (n-1)]
  } in Poly vs bgc ec;


-- Stellated regular polygon. The arguments are:
--       number of (outer) vertices
--       inner radius
--       outer radius
--       background colour
--       edge colour

stelGon :: Int -> Int -> Int -> CPic;

stelGon n radius1 radius2 bgc ec =
  let { m = 2 * n;
        k = 2.0 * pi / fromInt m;
        r1 = fromInt radius1; r2 = fromInt radius2;
        f = \i -> let { r = if even i then r1 else r2;
                        th = k * fromInt i
                  } in (round (r * cos th), round (r * sin th));
        vs = map f [0 .. (m-1)]
   } in Poly vs bgc ec;

-- ----------------------------------------------------------------------

-- Define some (rather simplistic) chessmen
--    pawn, pawn'        - black and white pawns
--    knight             - black knight
--    queen'             - white queen

-- Make a symmetric, black polygon from a list of the vertices of its RHS
mkSymPoly :: [(Int,Int)] -> Pic;

mkSymPoly vs = let poly = Poly vs black black 
            in Trans 20 0 (poly <+> flipV' poly);

-- Make a complex polygon by superimposing symmetric black polygons defined by a list of lists of vertices
mkPolys vss = let polys = map mkSymPoly vss in superimpose polys;

pawn, pawn', knight, queen' :: Pic;

pawn = 
   let { vsTop = [(0,16),(4,16),(4,25),(0,25)];
         vsBase = [(0,27),(8,27),(8,36),(0,36)]
   } in mkPolys [vsTop, vsBase];


pawn' = 
   let { vsTop = [(0,16),(4,16),(4,25),(0,25),(0,22),(2,22),(2,18),(0,18)];
         vsBase = [(0,27),(8,27),(8,36),(0,36),(0,33),(5,33),(5,29),(0,29)]
   } in mkPolys [vsTop, vsBase];


knight = 
  let { body = let vs =  [(5,16),(18,7),(16,5),(19,4),(33,18),(33,36),(11,36),(15,24),(9,25),(6,21)]
               in Poly vs black red;
        eye  = let vs = [(16,14),(18,12),(20,14),(18, 16)]
               in Poly vs white gray;
        mane = let vs = [(23,18),(24,16),(28,19),(28,27),(26,27),(26,21)]
               in Poly vs lightGray gray
  } in superimpose [eye, mane, body];


queen' = 
  let { vsTop  = [(16,10),(5,13),(0,5),(0,10),(4,16),(10,15),(7,21),(0,21),(0,24),(8,24)];
        vsBase = [(0,27),(7,27),(7,36),(0,36),(0,33),(5,33),(5,29),(0,29)]
  } in mkPolys [vsTop, vsBase]

}
