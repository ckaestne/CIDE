-- A simple game, illustrating use of the Interaction picture constructor

module DotGameLib (initState, game) where {

  -- Import the graphics library. This uses floating point, with a scale of
  --         1.0 unit  == 1 pixel

  import Graphics;                              
                                                           

  -- Types -----------------------------------------------------------------------------

  type Position = Double;
  type Velocity = Double;

  -- The state of a dot (x & y components of velocity and position)

  data Dot = Dot Velocity Velocity Position Position;

  -- The state of the blob (x & y components of position)

  data Blob = Blob Position Position;


  -- Constants --------------------------------------------------------------------------

  -- The game panel dimensions and colours

  width = 400.0;              height = 400.0;  
  panelBodyColor  = yellow;   panelHitBodyColor = white;   panelEdgeColor   = black;


  -- The sizes and colours of the various objects

  dotRadius    = 10.0;        dotBodyColor    = red;       dotEdgeColor    = black;
  blobRadius   = 30.0;        blobBodyColor   = cyan;      blobEdgeColor   = blue;
  cursorRadius = 5.0;         cursorBodyColor = white;     cursorEdgeColor = black;

  cordColor    = gray;



  -- Display of the various components -------------------------------------------------------------------

  -- Display of a single dot

  displayDot :: Dot -> Pic;

  displayDot dot =
    case dot of 
      Dot _ _ sx sy -> circle sx sy dotRadius dotBodyColor dotEdgeColor;


  -- Display of a set of dots

  displayDots :: [Dot] -> Pic;

  displayDots dots = super (map displayDot dots);


  -- Display of the blob
  -- (The blob becomes transparent whenever the user jumps it through hyperspace)

  displayBlob :: Blob -> Bool -> Pic;

  displayBlob blob pressed =
    case blob of
      Blob sx sy -> 
        let color = if pressed then trans else blobBodyColor
        in circle sx sy blobRadius color blobEdgeColor;


  -- Display of the cursor

  displayCursor :: Position -> Position -> Pic;

  displayCursor x y = circle x y cursorRadius cursorBodyColor cursorEdgeColor;


  -- Display of the cord that drags the blob

  displayCord blob x y =
    case blob of
      Blob x' y' -> line x y x' y' cordColor;


  -- Display of the game panel (it changes colour when a hit occurs)

  displayPanel hit = 
    let bgColor = if hit then panelHitBodyColor else panelBodyColor
    in rect hWidth hHeight hWidth hHeight bgColor black;

  hWidth  = width  / 2.0; 
  hHeight = height / 2.0;


  -- The dynamics of a dot --------------------------------------------------------------------------

  -- Dots fall downwards with an acceleration of g
  g = 40.0;                                       


  update :: Time -> Dot -> Dot;

  update dt dot =
    case dot of 
      Dot vx vy sx sy ->
        let {ux = bounceX vx sx;
             uy = bounceY vy sy
        } in Dot ux (uy + (dt * g)) 
                 (sx + (dt * ux)) (sy + (dt * uy));


  -- Bounce coefficient (each time a dot bounces, it speeds up)
  coeff = 1.001;

  -- The dynamics of bouncing: a dot bounces whenever it reachs a margin of the play area
  bounce :: Position -> Position -> Velocity -> Position -> Velocity;
  bounce lower upper v x =
    if ((x < lower) && (v < 0.0)) ||
       ((x > upper) && (v > 0.0))
    then -coeff * v else v;

  bounceX = bounce leftDotMargin rightDotMargin;
  bounceY = bounce topDotMargin botDotMargin;

  -- The margins of the game panel
  topDotMargin   = dotRadius;
  botDotMargin   = height - dotRadius;
  leftDotMargin  = dotRadius;
  rightDotMargin = width - dotRadius;


  -- The dynamics of the blob -----------------------------------------------------------------------

  elasticity = 0.5;

  drag :: Time -> Position -> Position -> Blob -> Bool -> Blob;

  drag dt x y blob pressed =
    if pressed then 
      Blob x y
    else
      case blob of
        Blob sx sy ->
          let {dx = x - sx;  dy = y - sy;
               vx = elasticity * dx;  vy = elasticity * dy;
               sx' = boundX (sx + (dt * vx));  
               sy' = boundY (sy + (dt * vy))
          } in Blob sx' sy';


  -- Constrain a value to an interval

  bound lower upper x = min upper (max lower x);

  boundX = bound leftBlobMargin rightBlobMargin;
  boundY = bound topBlobMargin  botBlobMargin;

  topBlobMargin   = blobRadius;
  botBlobMargin   = height - blobRadius;
  leftBlobMargin  = blobRadius;
  rightBlobMargin = width - blobRadius;




  -- Hit detection ----------------------------------------------------------------------------------

  -- Determine whether the blob has been hit by an individual dot

  hitDot :: Blob -> Dot -> Bool;

  hitDot blob dot =
    case (blob, dot) of
      (Blob x y, Dot _ _ x' y') ->
        let {dx = x - x';  dy = y - y';
             ds = (dx * dx) + (dy * dy)
        } in ds < dsBlobDot;


  -- The square of the hit distance between a dot and a blob

  dsBlobDot = let s = dotRadius + blobRadius in s * s;


  -- Determine whether the blob has been hit by any dot

  hitDots :: Blob -> [Dot] -> Bool;

  hitDots blob dots = or (map (hitDot blob) dots);


  -- The overall state of the game ----------------------------------------------------------------

  -- The overall state is the current real time (an integer, in ms since 1970), the state of
  -- the blob and the state of the dots.

  data State = State TimeMs Blob [Dot];

  -- There are 5 dots

  nDots = 5;


  -- The initial state

  initState = State 0 initBlob initDots;

  initBlob = Blob  200.0  100.0;

  initDots = [let n' = fromInt (n * 10) 
              in Dot n' 20.0 100.0 (n' + 50.0) | n <- [1 .. nDots]];


  -- The top-level transition function for the overall game -------------------------------------

  game :: Int -> Int -> Int -> Bool -> State -> (State, Pic);

  game tm xi yi pressed state =
    case state of
      State tm1 blob dots -> 
        let {dtm = tm - tm1;
             dt = if (dtm < 1000) then 0.001 * fromInt dtm else 0.0;
             x = fromInt xi;  y = fromInt yi;
             blob' = drag dt x y blob pressed;
             cursor = circle x y cursorRadius white black;
             dots' = map (update dt) dots;
             hit = hitDots blob dots;
             cord = displayCord blob x y;
             panel = displayPanel hit;
             state' = if pressed && (y < 0.0) then
                        initState
                      else
                        State tm blob' dots';
             pic = super [panel, displayDots dots, cord, displayBlob blob pressed, cursor]
        } in (state', pic)

}
