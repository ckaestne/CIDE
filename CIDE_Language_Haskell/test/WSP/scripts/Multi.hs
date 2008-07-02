module Multi where
{ import Prelude hiding (head, span, div, map);
  import WASH.CGI.CGI;
  import Random;
  import qualified WASH.CGI.Persistent2 as P;
   
  mainCGI :: CGI ();
 
   
  firstExercise :: (Int, Int) -> CGI ();
  firstExercise (mpy, rpt) = runExercises 1 [] []
    where { runExercises nr successes failures
              = if nr > rpt then finalReport else
                  do { factor <- io (randomRIO (0, 12));
                       standardQuery ("Question " ++ show nr ++ " of " ++ show rpt) $
                         do { text (show factor ++ " * " ++ show mpy ++ " = ");
                              activate (checkAnswer factor) inputField empty}}
              where { checkAnswer factor answer
                        = let { correct = answer == factor * mpy;
                                message = if correct then "correct! " else "wrong! ";
                                continue (F0)
                                  = if correct then
                                      runExercises (nr + 1) (factor : successes) failures else
                                      runExercises (nr + 1) successes (factor : failures)}
                            in
                            standardQuery ("Answer " ++ show nr ++ " of " ++ show rpt) $
                              do { p (text
                                        (show factor ++ " * " ++ show mpy ++ " = " ++
                                           show (factor * mpy)));
                                   text ("Your answer " ++ show answer ++ " was " ++ message);
                                   submit F0 continue (attr "value" "CONTINUE")};
                      finalReport
                        = let { lenSucc = length successes;
                                pItem (m, l, r)
                                  = li
                                      (text
                                         ("Multiplier " ++ show m ++ " : " ++ show l ++
                                            " correct out of "
                                            ++ show r))}
                            in
                            do { initialHandle <- P.init ("multi-baer") [];
                                 currentHandle <- P.add initialHandle (mpy, lenSucc, rpt);
                                 hiScores <- P.get currentHandle
}}}}
