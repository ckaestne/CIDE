module CFrac where {

-- This module defines a Style for the display of a continued fraction (whose coefficients
-- are defined as a list of numbers).

-- Limitations:  
-- At present, the size is hardwired into the function. It should be a
-- parameter. Ideally it should make use of the primitive 
--     fontSize :: Format -> (Int, Int)
-- function that yields the bounding box of the largest character in the font.
--
-- Further, the "mag" function does not work correctly on the resultant style.
--
-- Yet further: when used as a style with infinite lists, it does not terminate


cFracStyle :: Color -> Style;

cFracStyle txtColor =
  let {
    font = SansSerif;
    ptSize = 20;
    w = 50;
    h = 30;
    fmt = Format font ptSize txtColor False False;
    box = Box w h Transparent Transparent;
    link = Link (w,(h+5)) True Transparent box;
    fs0 = FText box 1 fmt;
    fs1 = FLink box link This;
    pic0 = Trans 10 20 $ PicText fmt "1";
    pic1a = Trans w (h-9) $ PicText fmt "+";
    pic1b = Trans (w+w) 21 $ PicText fmt "1";
    pic1c = Trans 90 h $ Line 120 0 darkGray;
    pic1 = pic1a `Super` pic1b `Super` pic1c;
    vs0 = VIndiv NoBox CHide (0,0) [] [] pic0 NoFormat;
    vs1 = VIndiv NoBox CHide (20,20) [fs0,fs1] [(0,0), (40,0)] pic1 NoFormat
  } in Data [vs0, vs1]

}
