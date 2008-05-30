-- Defines function to display barcharts. Based on the code developed in interactive module "Pic2".


module Barchart(barChart, multiBarChart, plainBar, solidBar) where {

import List;

-- A black line from (x1,y1) to (x2,y2)

line x1 y1 x2 y2 = Trans x1 y1 (Line (x2 - x1) (y2 - y1) black);


-- A rectangle bounded by (x1,y1) and (x2,y2) with specified colours

rect color lineColor x1 y1 x2 y2 = 
    Trans x1 y1 (Rect (x2 - x1) (y2 - y1) color lineColor);


-- A function to draw a plain bar, in specified colours, with specified size

plainBar :: Color -> Color -> Int -> Int -> Int -> Pic;

plainBar color edgeColor barWidth height value =
    rect color edgeColor 0 (height - value) barWidth height;


-- A function to draw a bar with a 3-D look, in specified coulours, with specified size

solidBar :: Color -> Color -> Color -> Int -> Int -> Int -> Pic;

solidBar barColor sideBarColor lineColor barPitch height value =
  let { barWidth = barPitch `div` 2;
        barSide  = barPitch `div` 4;
        hv    = height - value;
        bws   = barWidth + barSide;
        hvb   = height - (value + barSide);
        front = rect barColor lineColor 0 hv barWidth height;
        top   = Poly [(0, hv), (barWidth, hv), (bws, hvb),(barSide, hvb)] barColor lineColor;
        side  = Poly [(barWidth, height), (bws, (height - barSide)), (bws, hvb), (barWidth, hv)] sideBarColor lineColor
  }
  in front `Super` (top `Super` side);


-- A subsidiary function that draws the contents of the bar chart. Its first argument is a
-- a function that draws a single bar, the next two arguments specify the size of the bar chart and the
-- last argument is a list of the values to be displayed. The function computes:
--   * barWidth        - the width of each bar.
--   * maxValue        - the value of the max element in the given list of values. (If the list
--                       is empty, it takes the maximum value to be 1).
--   * normValues      - the original list of values, normalised so as lie within the alloted height
--                       of the bar chart.
--   * bars            - a list of rectangles whose heights correspond to the list of normalised values.
--   * f               - a function that takes two pictures, p and q, shifts q to the right by one
--                       bar width, and yields p superimposed on the shifted version of q.
--   * foldr f NoPics bars
--                     - the overall picture, obtained by right-folding f over the list of individual
--                       bars (with the null picture, NoPic, as the zero).


content :: (Int -> Int -> Int -> Pic) -> Int -> Int -> [Int] -> Pic;

content bar width height values =
    let { barWidth = width `div` length values;
          maxValue = maximum (1 : values);
          normValues = [v * height `div` maxValue | v <- values];
          bars = [bar barWidth height v | v <- normValues];
          f = \p q -> p `Super` (Trans barWidth 0 q)
    }
    in foldr f NoPic bars;


-- A subsidiary function that draws axes and a title.

axes width height title =
  let { overhang = height `div` 5;
        xAxis = line ( -overhang) height (width + overhang) height;
        yAxis = line 0 0 0 (height + overhang);
        titleFormat = Format SansSerif 16 black False False;
        titleLine = Trans (width `div` 3) 
                          (height + overhang) 
                          (PicText titleFormat title) }
  in xAxis `Super` yAxis `Super` titleLine;


-- Finally, the top-level function for drawing a generic bar chart, obtained by
-- superimposing the axes and the content functions

barChart :: (Int -> Int -> Int -> Pic) -> Int -> Int -> String -> [Int] -> Pic;

barChart bar width height title values =
  content bar width height values `Super` axes width height title;

-- ------------------------------------------------------------------------

-- Functions for creating multi-bar charts

-- The sequence of bar colours that will be used
colors = [red, blue, yellow, magenta, cyan, black, green];


-- A function that yields a picture of a multi-bar
multiBar :: Int -> Int -> [Int] -> Pic;

multiBar barWidth height values =
   let { n = length values;
         w = barWidth `div` (n + 1);
         gap = 2;
         bs = [plainBar (fst p) black (w - gap) height (snd p) | p <- zip colors values];
         f = \p q ->  Trans w 0 q `Super` p
   }
   in foldr f NoPic bs;


-- A function that yields a picture of the bars of a multi-bar chart
multiContent :: (Int -> Int -> [Int] -> Pic) -> Int -> Int -> [[Int]] -> Pic;

multiContent bar width height values =
    let { barWidth = width `div` length values;
          bars = [bar barWidth height v | v <- values];
          f = \p q -> (Trans barWidth 0 q) `Super` p
    }
    in foldr f NoPic bars;


-- A standard list function


-- The top-level function for creating multi-barchart pictures

multiBarChart :: Int -> Int -> String -> [[Int]] -> Pic;

multiBarChart width height title listOfSequences =
      multiContent multiBar width height (transpose listOfSequences) `Super` axes width height title
}
