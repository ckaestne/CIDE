-- This module is loaded as part of the Prelude

module Pic(
        Pic(NoPic, Line, Rect, Poly, Ellipse, PicText, PicImage, SelRect, PicAction,
                   Trans, Super, Animation, Interaction), 
        Font(Serif, SansSerif, Monospaced, Dialog, DialogInput),
        Format(NoFormat, Format),
        Color(RGB, Transparent), 
        black, blue, green, cyan, red, magenta, yellow, white, 
        lightGray, gray, darkGray,
        trans,
        invColor, lighten, darken,
        imageFile, scaleImage, imageSize, picFile,
        fontSize,
        defaultFmt
) where {

import Midi;             -- Note that this is _not_ reexported

-- Primitives
primitive primImageFile        :: String -> Image;
primitive primScaleImage       :: Int -> Int -> Image -> Image;
primitive primImageSize        :: Image -> (Int, Int);

primitive primFontSize         :: Format -> (Int, Int);


data Pic
  = NoPic
  | Line {x :: Int, y :: Int, color :: Color}
  | Rect {width :: Int, height :: Int, bgColor :: Color, edgeColor :: Color}
  | Poly {vertices :: [(Int, Int)],  bgColor :: Color, edgeColor :: Color}
  | Ellipse {width :: Int, height :: Int, bgColor :: Color, edgeColor :: Color}
  | PicText {format :: Format, text :: String}
  | PicImage {img :: Image}
  | SelRect {width :: Int, height :: Int, bgColor :: Color, edgeColor :: Color}  
  | PicAction {width :: Int, height :: Int, bgColor :: Color, edgeColor :: Color, action :: Action}  

  | Trans {x :: Int, y :: Int, pic :: Pic}
  | Super {lower :: Pic, upper :: Pic}			-- The second is superimposed on the first
  | Animation {fn :: Int -> Pic}
  | Interaction {s0 :: state, fn :: Time -> Int -> Int -> Bool -> state -> (state, Pic)};


data Font 
  = Serif | SansSerif | Monospaced | Dialog | DialogInput;

data Format
  = NoFormat
  | Format {font :: Font, ptSize :: Int, fgColor :: Color, isBold :: Bool, isItalic :: Bool};


data Color
  = RGB Int Int Int                         -- The Red, Green and Blue components, each in the range (0 .. 255)
  | Transparent;

-- Bindings for primitive values

--   for Image 

imageFile               :: String -> Image;
imageFile               =  primImageFile;

-- The expression 
--         scaleImage width height image   
-- yields a new image scaled to the given size. If either dimension is negative, then the
-- image aspect ratio is maintained.

scaleImage              :: Int -> Int -> Image -> Image;
scaleImage              =  primScaleImage;

imageSize               :: Image -> (Int, Int);
imageSize               =  primImageSize;

-- Note that this function reflects the behaviour of the particular physical device (screen, printer, ...) 
-- on which rendering takes place.
fontSize                :: Format -> (Int, Int);
fontSize                =  primFontSize;


-- Bindings for derived values

off                     = 0;
on                      = 255;

black                   = RGB off off off;
blue                    = RGB off off on ;
green                   = RGB off on  off;
cyan                    = RGB off on  on ;
red                     = RGB on  off off;
magenta                 = RGB on  off on ;
yellow                  = RGB on  on  off;
white                   = RGB on  on  on ;

lightGray               = RGB 192 192 192;
gray                    = RGB 128 128 128;
darkGray                = RGB  64  64  64;

trans                   =  Transparent;


lighten, darken         :: Color -> Color;

lighten c               =  case c of {
                             Transparent -> Transparent;
                             RGB r g b   -> RGB (more r) (more g) (more b)
                           };

darken c                =  case c of {
                             Transparent -> Transparent;
                             RGB r g b   -> RGB (less r) (less g) (less b)
                           };

picFile                 :: String -> Pic;
picFile                 =  PicImage . imageFile;

defaultFmt              :: Format;
defaultFmt              =  Format SansSerif 16 darkGray False False;

    
-- This internal constant determines the amount by which colours are lightened or darkened.
delta                   =  50;

less, more              :: Int -> Int;

less n                  = max off (n - delta);

more n                  = min on  (n + delta);


invColor                :: Color -> Color;

invColor c              =  case c of {
                             Transparent  ->  Transparent;
                             RGB r g b    ->  RGB (on - r) (on - g) (on - b)
                           }


}


