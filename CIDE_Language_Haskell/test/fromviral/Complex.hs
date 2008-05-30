module Complex where {

  infix 6 :+ ;

  data Complex = Double :+ Double;

  realPart, imagPart :: Complex -> Double;
  realPart z = case z of {
                 x :+ y -> x
               };

  imagPart z = case z of {
                 x :+ y -> y
               };

  conjugate :: Complex -> Complex;
  conjugate z = case z of {
                  x :+ y -> x :+ (-y)
                };

  mkPolar :: Double -> Double -> Complex;
  mkPolar r theta = (r * cos theta) :+ (r * sin theta);

  cis :: Double -> Complex;
  cis theta = cos theta :+ sin theta;

  polar :: Complex -> (Double, Double);
  polar z = (magnitude z, phase z);

  magnitude :: Complex -> Double;
  magnitude z = case z of {
                  x :+ y -> sqrt ((x ^ 2) + (y ^ 2))
                };

  phase :: Complex -> Double;
  phase z = case z of {
              x :+ y -> atan2 y x
            };


  instance Show Complex where {
    shows z s = case z of { 
                   x :+ y    ->  shows x (" :+ " ++ shows y s)
                };
    show z    = shows z ""
  }


}
