-- A simple game

-- The game is played on a board consisting of a row of places. An empty
-- place is indicated by a 0, a place with a piece by a positive integer. The
-- allowable moves for a piece of value n are exactly n steps in either direction.

-- Operations on the ADT: lift a piece from one place; drop it in another; compute the score.

-- The definitions for these operations are split up into two groups: the first check the
-- validity of the arguments, the second implement the operation.


module RowGameLib(Position(), posStyle, initPos, checkLift, checkDrop, liftIt, dropIt, score) where {

  import Styles;            -- Needed for the style "list bt5" used in this module
  import Char;              -- Needed for the "intToDigit" function used in computing the score

  data Position = Position {state :: Maybe Int, view :: [Int]};

  initPos = Position Nothing [3, 0, 0, 2, 1, 0, 0, 3, 1, 0];

 
  -- These two functions check and report on the validity of the pre-conditions for an operation
  checkLift :: Int -> Position -> Either String String;
  checkDrop :: Int -> Position -> Either String String;

  checkLift i pos =
    case pos of {
      Position Nothing xs ->
        if (i < 0) || (i >= length xs) then
          Left "Index is out of bounds!"
        else if (xs !! i) == 0 then
          Left "Cannot pick up a piece of value 0!"
        else
          Right "OK";

      Position (Just _) xs -> 
        Left "A piece has already been picked up!"
    };

 
  checkDrop i pos =
    case pos of {
      Position Nothing xs ->
        Left "No piece has been picked up!";

      Position (Just (j,n)) xs ->
        if (i < 0) || (i >= length xs) then
          Left "Index is out of bounds!"
        else if (xs !! i) /= 0 then
          Left "Cannot drop a piece in a non-empty place!"
        else if (i /= (j-n)) && (i /= j) && (i /= (j+n)) then
          Left "Not allowed to drop it here!"
        else 
          Right "OK"
    };
      
  
  -- These two functions actually carry out the operations (assumed to be valid)

  liftIt :: Int -> Position -> Position;
  dropIt :: Int -> Position -> Position;

  liftIt i pos =
    case pos of {
      Position Nothing xs ->
        let { n = xs !! i;
              state' = Just (i,n);
              xs' = subst i 0 xs
        } in Position state' xs';

      Position (Just _) xs -> 
        error "Game.liftIt"
    };


  dropIt i pos = 
    case pos of {
      Position Nothing xs ->
        error "Game.dropIt";

      Position (Just (j,n)) xs -> 
        let { state' = Nothing;
              xs' = subst i n xs
        } in Position state' xs'
    };


  -- The score of the game is computed by treating the position as a decimal numeral
  
  score :: Position -> String;

  score pos =
    case pos of {
      Position Nothing xs -> let digits = map intToDigit (dropWhile (== 0) xs)
                             in "The score is: " ++ digits;
      Position (Just _) _ -> "Cannot compute the score when you have a piece not on the board!"
  };


  -- SUBSIDIARY FUNCTIONS -------------------------------------------------------------

  -- Replace the i-th element of a list (0-based indexing) with the given element

  subst :: Int -> a -> [a] -> [a];

  subst i x xs = if i == 0 then
                   x : tail xs
                 else
                   head xs : subst (i-1) x (tail xs);


  -- Define an instance of Show for Position (useful for debugging)

  instance Show Position where {
    shows pos s = 
      case pos of {
        Position Nothing  xs -> "Position Nothing " ++ shows xs s;
        Position (Just (i,n)) xs -> "Position (Just (" ++ shows i ("," ++ shows n (")) " ++ shows xs s))
      };

    show pos = shows pos ""
  };


  -- Definition of a style for Position

  posStyle = Data [vStyle];

  vStyle = VJuxta cStyle [fStyle1, fStyle2] True NoFormat;

  cStyle = CText cBox cFmt;
  cBox = Box 480 25 lightGray green;
  cFmt = Format SansSerif 16 red True False;

  fStyle1 = FHide;
  
  fStyle2 = FLink NoBox fLink (list box4 fmt5);
  fLink = Link (-380, 5) True trans cBox

}

