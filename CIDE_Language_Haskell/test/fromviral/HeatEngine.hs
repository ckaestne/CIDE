-- This module defines a single function that yields a picture (:: Pic) of
-- a heat engine at a phase theta.

module HeatEngine where {

  import Graphics;

  engine :: Double -> Pic;

  engine theta =
    let { crankLength = 40.0;  conRodLength = 100.0;   pistonRodLength = 150.0; width = 5.0;
          cylinderRadius = 30.0; cylinderThickness = 10.0; pistonWidth = 20.0; pistonGap = 15.0;
          x1 = crankLength * cos theta;   y1 = crankLength * sin theta;
          x2 = x1 + sqrt ((conRodLength ^ 2) - (y1 ^ 2));
          x3 = x2 + pistonRodLength;
          x4 = conRodLength + pistonRodLength;
          link = \x1 y1 x2 y2 -> bar x1 y1 x2 y2 width gray black red white;
          crank = link 0.0 0.0 x1 y1;
          conRod = link x1 y1 x2 0.0;
          pistonRod = link x2 0.0 x3 0.0;
          piston = rect x3 0.0  pistonWidth cylinderRadius gray black;
          u1 = crankLength + pistonWidth + pistonGap;
          innerCylinder = rect x4 0.0 u1 cylinderRadius yellow yellow;
          outerCylinder = rect x4 0.0 (u1 + cylinderThickness) (cylinderRadius + cylinderThickness) black black;
          x5 = (x3 + x4 + u1) / 2.0;
          greenComp = round (100.0 * (1.0 - (x1 / crankLength)));
          gasColor = RGB 255 greenComp 50;
          gas = rect x5 0.0 ((x4 + u1) - x5) cylinderRadius gasColor gasColor
  } in super [outerCylinder, innerCylinder, pistonRod, conRod, crank, gas, piston]


}
