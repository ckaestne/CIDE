-- Supplementary stylesheet for exercises

module SSExercises (open2, open2', hint) where {

import SS;

open2                   =  enlarge . open;

open' name              =  let fmt = Format SansSerif 12 red False False
                           in Comment lightGray red fmt (Just name);

open2'                  =  enlarge . open';

hint                    :: Bool -> Style;
hint flag               =  let fmt = Format SansSerif 14 (if flag then red else trans) False False
                           in Comment trans green fmt Nothing

}



