module ActionLib (display, (>>), delay, button) where {

  -- "Action" is a primitive type (loosely corresponding to the type "IO ()" of standard Haskell)
  -- data Action;

  primitive primDisplayString :: String -> Action;
  primitive primNextAction :: Action -> Action -> Action;
  primitive primDelayAction :: Int -> Action -> Action;


  -- An action that, when triggered, displays a string on a pop-up window
  display          :: String -> Action;
  display          =  primDisplayString;

  -- An action that, when triggered, triggers both actions in turn

  infixl  1  >>;
  (>>)             :: Action -> Action -> Action;
  (>>)             =  primNextAction;

  -- An action that, when triggered, delays n milli-seconds and then triggers its action
  delay            :: Int -> Action -> Action;
  delay            =  primDelayAction;


  -- A function that creates a labelled PicAction picture capable of triggering its action
  button           :: String -> Action -> Pic;
  button s act     =  let {s'        = ' ' : ' ' : s;
                           n         = length s';
                           w         = n * dw;
                           w'        = w - 2 * gap;
                           h'        = h - 2 * gap;
                           picBack1  = Rect w h cyan trans;
                           picBack2  = Trans gap gap (Rect w' h' trans blue);
                           picText   = PicText fmt s';
                           picText'  = Trans 0 h1 picText;
                           picAction = PicAction w h trans blue act
                       } in Super picBack1 (Super picBack2 (Super picText' picAction));


  -- Subsidiary definitions ---------------------------------------------------------
  
  theFontSize      :: Int;
  theFontSize      =  18;

  fmt              :: Format;
  fmt              =  Format SansSerif theFontSize blue False False;

  fontWidth        =  fst (fontSize fmt);
  fontHeight       =  snd (fontSize fmt);

  dw, h, h1, gap   :: Int;
  dw               =  fontWidth `div` 3;
  h                =  2 * fontHeight;
  h1               =  (fontHeight * 4) `div` 3;
  gap              =  2

}
