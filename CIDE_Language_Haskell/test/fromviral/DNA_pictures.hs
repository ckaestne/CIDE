module DNA_pictures where {

-- Defn of DNA picture functions

import Pictures;
import DNA_lib;           -- (Mutually recursive import)

backbone, backbone' :: Pic;
backbone =
  let {phosphate = Rect 6 6 white black;
       sugar     = Poly [(15,7), (24,15), (20,25), (10,25), (6,15)] white black;
       upperLink = line 3 6 6 15 black;
       sideLink  = line 24 15 29 15 black;
       lowerLink = line 10 25 3 30 black
  } in super [sugar, phosphate, upperLink, sideLink, lowerLink];

backbone' = flipH (flipV backbone);

smallBaseWidth = 14;
bigBaseWidth   = 27;
bondWidth      =  8;
baseHeight     = 16;
totalWidth     = bigBaseWidth + bondWidth + smallBaseWidth;

base :: Color -> Color -> Bool -> String -> Pic;
base lc bc isBig s =
  let {w      = if isBig then bigBaseWidth else smallBaseWidth;
       offset = if isBig then 9 else 3;
       rect   = Rect w baseHeight bc black;
       fmt    = Format "SansSerif" 12 lc True False;
       text   = PicText fmt s
  } in rect <+> Trans offset 13 text;


adenine, cytosine, guanine, thymine :: Pic;
adenine  = base black yellow True "A";
cytosine = base white green False "C";
guanine  = base white blue True "G";
thymine  = base white red False "T";


bonds :: Bool -> Pic;
bonds isTriple =
  let {bond = Rect bondWidth 1 red red;
       ys   = if isTriple then [3, 7, 11] else [5, 9]
  } in super [Trans 0 y bond| y <- ys];

bonds2, bonds3 :: Pic;
bonds2 = bonds False;
bonds3 = bonds True;


cg, gc, ta, at, other :: Pic;
cg = super [Trans smallBaseWidth 0 bonds3, cytosine, Trans (smallBaseWidth + bondWidth) 0 guanine];
gc = super [Trans bigBaseWidth   0 bonds3, guanine,  Trans (bigBaseWidth   + bondWidth) 0 cytosine];
ta = super [Trans smallBaseWidth 0 bonds2, thymine,  Trans (smallBaseWidth + bondWidth) 0 adenine];
at = super [Trans bigBaseWidth   0 bonds2, adenine,  Trans (bigBaseWidth   + bondWidth) 0 thymine]; 
other = Rect totalWidth baseHeight white cyan;

selRect :: Pic;
selRect = SelRect totalWidth baseHeight trans trans;		-- Invisible unless selected

attach :: Pic -> Pic;
attach basePair = 
  super [backbone, Trans 29 8 (basePair <+> selRect), Trans (totalWidth + 29) 0 backbone'];

cg', gc', ta', at' :: Pic;
cg' = attach cg;
gc' = attach gc;
ta' = attach ta;
at' = attach at;
other' = attach other;


displayBasePair :: Base -> Pic;
displayBasePair base =
  case base of {
    A     -> at';
    C     -> cg';
    G     -> gc';
    T     -> ta';
    X     -> other'
  };





withShadow :: Pic -> Pic;
withShadow = shadowed 2 3 lightGray;

-- ----------------------------------------------------------

-- Some auxiliary picture functions

line :: Int -> Int -> Int -> Int -> Pic;
line x1 y1 x2 y2 c
  = let {x' = x2 - x1;
         y' = y2 - y1
    } in Trans x1 y1 (Line x' y' c);

super :: [Pic] -> Pic;
super pics =
  case pics of {
    []      -> NoPic;
    pic:pics -> pic <+> super pics
  };

monochrome :: Color -> Pic -> Pic;
monochrome c pic =
  case pic of {
    NoPic            -> NoPic;
    Line x y _       -> Line x y c;
    Rect w h _ _     -> Rect w h c c;
    Ellipse w h _ _  -> Ellipse w h c c;
    PicText fmt s    -> PicText (monochromeFmt c fmt) s;
    SelRect w h _ _  -> NoPic;                                          -- This kind of pic is not shadowed
    Poly vs _ _      -> Poly vs c c;
    Super pic pic'   -> Super (monochrome c pic) (monochrome c pic');
    Trans x y pic    -> Trans x y (monochrome c pic);
    Animation f      -> Animation (\t -> monochrome c (f t));
    Interaction s0 f -> Interaction s0
                                    (\t x y b s ->
                                       case f t x y b s of 
                                         (s, pic) -> (s, monochrome c pic))
  };

monochromeFmt :: Color -> Format -> Format;
monochromeFmt c fmt =
  case fmt of
    Format font ps _ b i -> Format font ps c b i;

shadowed :: Int -> Int -> Color -> Pic -> Pic;
shadowed dx dy c pic =
  let pic' = Trans dx dy (monochrome c pic)
  in pic' <+> pic

}
