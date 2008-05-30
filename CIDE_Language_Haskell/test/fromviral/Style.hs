-- This module is loaded as part of the Prelude.

module Style(
    Style(Hide, Text, Data, List, Picture, This, Comment, Rotate, View),
    VStyle(VHide, VJuxta, VIndiv),
    CStyle(CHide, CText, CImage),
    FStyle(FHide, FText, FImage, FLink),
    Box(NoBox, Box),
    Link(NoLink, Link),
    defaultStyle
) where {


data Style 
  = Hide
  | Text   {mode :: Int, fmt :: Format}
  | Data    {vStyles :: [VStyle]}
  | List   {fStyle :: FStyle, offset :: Offset}	
  | Picture                                             -- For rendering the Pic datatype
  | This

  | Comment {bgColor :: Color, edgeColor :: Color, fmt :: Format, linkName :: Maybe String}

  | Rotate  {innerStyle :: Style, theta :: Int}
  | View {fn :: a -> b, style :: Style};                -- For mapping values to a different representation


data VStyle
  = VHide
  | VJuxta {cStyle :: CStyle, fStyles :: [FStyle], horiz :: Bool, annot :: Format}
  | VIndiv {box :: Box, cStyle :: CStyle, cOffset :: Offset,
             fStyles :: [FStyle], fOffsets :: [Offset],
             decor :: Pic,
             annot :: Format};

data CStyle
  = CHide
  | CText {box :: Box, fmt :: Format}
  | CImage {img :: Image};

data FStyle
  = FHide
  | FText {box :: Box, mode :: Int, fmt :: Format}
  | FImage {box :: Box}
  | FLink {box :: Box, link :: Link, sty :: Style};

data Box
  = NoBox
  | Box {width :: Int, height :: Int, bgColor :: Color, edgeColor :: Color};


type Offset = (Int, Int);

data Link
  = NoLink
  | Link {offset :: Offset, horizFirst :: Bool, linkColor :: Color, targetBox :: Box};


defaultStyle :: Style;
defaultStyle =  Text 2 defaultFmt

}

