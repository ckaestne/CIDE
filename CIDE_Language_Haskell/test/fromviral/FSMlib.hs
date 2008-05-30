module FSMlib (interact) where {

-- The value of
--        interact initState fsm decode display
-- where
--      initState  :: state                          is an initial state
--      fsm        :: input -> state -> state        is a definition of a finite-state machine
--      decode     :: Int -> Int -> Maybe input      is a function that takes mouse coordinates x and y
--                                                   and yields a possible input for the fsm
--      stateToPic :: state -> Pic                   is a function that maps a state to a pictorial
--                                                   representation of it
--
-- is an interactive display of a finite-state machine starting in state initState and
-- responding to mouse clicks.

-- The function keeps track of the current state (pressed, not-pressed) of the mouse button and
-- whenever there is a button transition from not-pressed to pressed maps the mouse coordinates
-- into a Maybe "input" using the "decode" function. If this value is non-empty, then the "fsm"
-- function is used to compute a new state, and the "display" function is used to map this state
-- to a new picture. This new state and new picture are cached and are used the definition of
-- a low-level state "s0L" and finite-state machine "fsmL". These two values are supplied as
-- arguments of the "Interaction" constructor.

-- The development of this function is documented in interactive module  "FSM3".


interact :: state -> (input -> state -> state) -> (Int -> Int -> Maybe input) -> (state -> Pic) -> Pic;

interact s0 fsm decode stateToPic =
  let {s0L  = let {pic0 = stateToPic s0;
                   statePic0 = (s0, pic0);
                   mousePressed = False
              } in (statePic0, mousePressed);

       fsmL = \t x y isPressed stateL ->
                case stateL of
                  (statePic, wasPressed) -> 
                    let {click = isPressed && (not wasPressed);
                         mi = decode x y;
                         state = fst statePic;
                         statePic' = 
                           case (click, mi) of {
                             (True, Just i) -> 
                               let {state = fst statePic;
                                    state' = fsm i state;
                                    pic' = stateToPic state'
                               } in (state', pic');
                             _   ->  statePic
                           };
                         stateL' = (statePic', isPressed);
                         pic' = snd statePic'
                    } in (stateL', pic')
  } in Interaction s0L fsmL

}
