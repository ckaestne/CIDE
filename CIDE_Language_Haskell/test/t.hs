module Polymorph(main)

   where
   import Shape
   import Circle
   import Rectangle

   main =
      do
         -- handle the shapes polymorphically
         drawloop scribble

         -- handle rectangle specific instance
         draw (setWidth arectangle 30)

      where
         -- create some shape instances (using existential wrapper)
         scribble = [
            MakeExistentialShape (MakeRectangle 10 20 5 6),
            MakeExistentialShape (MakeCircle 15 25 8)]

         -- create a rectangle instance
         arectangle = (MakeRectangle 0 0 15 15)

   -- iterate through the list of shapes and draw
   drawloop [] = return True
   drawloop (x:xs) =
      do
         draw x
         draw (rMoveTo x 100 100)
         drawloop xs
