module Graphics (move, super, animate, animateCached, line, rect, circle, 
                 argand, bar, gearWheel, waveform) where {

  -- A collection of relatively high-level functions for graphics and animation.

  import Numeric;
  import Complex;
  import Vector;


  move     :: Double -> Double -> Pic -> Pic;

  super     :: [Pic] -> Pic;

  animate   :: Double -> (Double -> Pic) -> Pic;

  animateCached 
            :: Int -> Double -> (Double -> Pic) -> Pic;

  line      :: Double -> Double -> Double -> Double -> Color -> Pic;

  rect      :: Double -> Double -> Double -> Double -> Color -> Color -> Pic;  
 
  circle    :: Double -> Double -> Double -> Color -> Color -> Pic;  

  argand    :: Color -> Complex -> Pic;

  bar       :: Double -> Double -> Double -> Double -> Double -> Color -> Color -> Color -> Color -> Pic;

  gearWheel :: Double -> Double -> Double -> Color -> Color -> Double -> Pic;

  waveform  :: (Double -> Double) -> Double -> Pic;

  -- In all cases except for the "waveform" function, the positive direction of the Y axis is
  -- defined to be downwards.


  -- move x y pic
  --       Translate the location of a picture by (x,y)

  -- super pics
  --       Superimpose a list of pictures (with the tail of the list on the top)

  -- animate freq picFn
  --       Animate picFn (a function that maps a phase expressed in radians to a picture)
  --       at a frequency of  freq  Hz.

  -- animateCached nPhases freq picFn
  --       Similar to animate (see above) and intended only for use with instances of picFn
  --       that are periodic (with period 2 pi) and whose values are expensive to compute
  --       (relative to the cost of rendering them). It uses a Vector to cache nPhases
  --       samples (equally spaced, over the interval 0.0 to 2 pi) of picFn.

  -- line x1 y1 x2 y2 c    
  --       Draw a line from (x1,y1) to (x2,y2) in colour c.

  -- rect x y hw hh bc ec
  --       Draw a rectangle centered on (x,y), of half-width hw and half-height hh, in colours bc and ec.

  -- circle x y r bc ec
  --       Draw a circle centered on (x,y), of radius r, in colours bc and ec.

  -- argand c z
  --       Draw an Argand diagram, in colour c, of the complex number z.

  -- bar x1 y1 x2 y2 hw bc ec cc wc
  --       Draw a bar from (x1,y1) to (x2,y2) of half-width hw
  --       and colours:  bc   - body colour
  --                     ec   - edge colour
  --                     cc   - circle colour
  --                     wc   - web colour         

  -- gearWheel toothDepth toothWidth bc ec radius x y phase
  --       Draw a gear wheel about the point (x,y) with specified parameters. The "phase" parameter
  --       is such that an increment of 2 pi  corresponds to rotating the wheel by one tooth pitch.

  -- waveform fn phase
  --       Draws a plot of the function fn, over the domain (0, 2 pi), where the range of the
  --       function is assumed to be +/- 1.   For example, given the function "sin", it will draw 
  --       exactly one cycle of the waveform, with amplitude 100 pixels.
  --       Unlike the other functions in this module, the positive direction for the Y axis is
  --       taken to be Upwards.



  -- Implementation ----------------------------------------------------------

  move x y = Trans (round x) (round y);


  super = foldl Super NoPic;


  animate freq fn = let omega = 2.0 * pi * freq / 1000.0
                    in Animation (fn . (* omega) . fromInt);


  animateCached nPhases freq fn =
    let { phases = [0 .. (nPhases-1)];
          k = 2.0 * pi / fromInt nPhases;
          pics = [fn (k * fromInt i) | i <- phases];
          picVec = listVector pics;
          g = \i -> let j = round (fromInt (i * nPhases) * freq / 1000.0) `mod` nPhases
                    in picVec `primIndex` j
    } in Animation g;

  
  line u v u' v' col =
    Trans (round u) (round v) (Line (round (u' - u)) (round (v' - v)) col);


  rect x y hw hh bc ec =
    let { rectPic = Rect (round (hw + hw)) (round (hh + hh)) bc ec;
          x' = round (x - hw);  y' = round (y - hh)
    } in Trans x' y' rectPic;


  circle x y r bc ec =
    let { diam = round (r + r);
          circlePic = Ellipse diam diam bc ec;
          x' = round (x - r);  y' = round (y - r)
    } in Trans x' y' circlePic;


  argand col z = 
    line 0.0 0.0 (realPart z) (imagPart z) col;


  bar x1 y1 x2 y2 hw bc ec cc wc =
    let { dx = x2 - x1;  dy = y2 - y1;
          ds = sqrt ((dx*dx) + (dy*dy));
          a = hw / ds;
          ux = a * dx;  uy = a * dy;
          p0x = round (x1 + uy);   p0y = round (y1 - ux);
          p1x = round (x2 + uy);   p1y = round (y2 - ux);
          p2x = round (x2 - uy);   p2y = round (y2 + ux);
          p3x = round (x1 - uy);   p3y = round (y1 + ux);
          vertices = [(p0x,p0y), (p1x,p1y), (p2x,p2y), (p3x,p3y)];
          poly = Poly vertices bc ec;
          q1x = round (x1 - hw);   q1y = round (y1 - hw);
          q2x = round (x2 - hw);   q2y = round (y2 - hw);
          diam = 2 * round hw;  circ = Ellipse diam diam cc ec;
          circ1 = Trans q1x q1y circ;
          circ2 = Trans q2x q2y circ;
          web = line x1 y1 x2 y2 wc
    } in super [poly, circ2, circ1, web];


  gearWheel toothDepth toothWidth  bc ec radius x y phase =
    let { td2 = toothDepth / 2.0;
          radius1= radius - td2;  radius2 = radius + td2;
          twoPi = 2.0 * pi;
          circum = twoPi * radius;
          nTeeth = let n = round (circum / toothWidth) 
                   in (n `div` 2) * 2 + 1;
          toothIndices = [0 .. (nTeeth-1)];
          toothAngle = 2.0 * pi / fromInt nTeeth;
          offset = phase / fromInt nTeeth;
          f = \r theta -> let { x' = x + (r * cos theta); y' = y + (r * sin theta)
                          } in (round x', round y');
          f1 = f radius1;  f2 = f radius2;
          d = toothAngle / 8.0;
          g = \i -> let a = (toothAngle * fromInt i) + offset
                      in [f1 (a + d), f2 (a + (3.0 * d)), f2 (a + (5.0 * d)), f1 (a + (7.0 * d))];
          vertices = concat (map g toothIndices)
       } in Poly vertices bc ec;

  -- Constants for the following "waveform" function

  dx   = 8;                          -- width of each slice of the plot
  n    = 50;                         -- number of points
  xmax = n * dx;                     -- the rightmost point
  ns   = [0 .. n];                   -- set of points that are plotted
  v0   = (0,0);                      -- the left end of the axis
  v1   = (xmax, 0);                  -- the right end of the axis
  a    = 100.0;                      -- the range scaling factor
  k    = 2.0 * pi / fromInt n;       -- the domain scaling factor

  waveform fn phase =
    let {vertices = [ let {x = i*dx; u = k * fromInt i; v = - fn (u + phase); y = round (a * v)} in (x,y) | i <- ns];
         vertices' = v1 : (v0 : vertices)
    } in Poly vertices' cyan blue
       
}
