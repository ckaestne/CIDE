-- Defines the datatype "Score" (a musical score) together with functions to
-- create, display, edit and play scores.

module MidiLib (
  Score(Score), Note(Note),
  TimeValue(Demisemiquaver, Semiquaver, Quaver, Crotchet, Minim, Semibreve, Breve),
  Intensity(Piano, MezzoForte, Forte),
  play, stop, save,
  durationOf, scoreToSequence, noteToMidiEvents,
  drawLines, drawBars, drawStave,
  drawScore, drawNote, drawTimeValue,
  addNote, deleteNote,
  cMajor
) where {

  -- Import the Midi type definitions and primitive values
  import Midi;

  -----------------------------------------------------------------------------------------------
  --   Types introduced in the  Midi.hs  library module
  -----------------------------------------------------------------------------------------------

  --  type Tick          = Int;       -- Time from the start of the score. There are 16 ticks per quarter note.
  --  type Pitch         = Int;       -- A pitch of 60 corresponds to middle C. A unit increment 
                                      -- corresponds to a semitone
  --  type Loudness      = Int;       -- Maximum loudness is 100. 
  --  type Instrument    = Int;       -- Midi instrument (0 is piano, ..., 60 is French horn, ..., 127 is gunshot)


  -----------------------------------------------------------------------------------------------
  --   Types introduced in this module
  -----------------------------------------------------------------------------------------------

  -- The "TimeValue" datatype describes the duration of a note (in American terminology, a crotchet is
  -- described as a "quarter note").
  data TimeValue = Demisemiquaver | Semiquaver | Quaver | Crotchet | Minim | Semibreve | Breve;


  -- The "Intensity" datatype describes the loudness of a note
  data Intensity = Piano | MezzoForte | Forte;

  -- A StaveIndex value describes a vertical position on the stave. The top line is 0, the space below
  -- it 1, . . .  and the bottom line is 14.  (In the key of C major, the bottom line corresponds to middle C.)
  -- type StaveIndex = Int;

  -- The "Note" datatype describes a single note
  data Note = Note Tick StaveIndex TimeValue Intensity;

  -- The "Score" datatype describes a simple musical score
  data Score = Score [Note];


  -----------------------------------------------------------------------------------------------
  --   Define show functions
  -----------------------------------------------------------------------------------------------
  
  instance Show TimeValue where
    shows timeValue s =
      case timeValue of {
        Demisemiquaver -> "Demisemiquaver" ++ s;
        Semiquaver     -> "Semiquaver" ++ s;
        Quaver         -> "Quaver" ++ s;
        Crotchet       -> "Crotchet" ++ s;
        Minim          -> "Minim" ++ s;
        Semibreve      -> "Semibreve" ++ s;
        Breve          -> "Breve" ++ s
      };



  instance Show Intensity where
    shows intensity s = 
      case intensity of {
        Piano       -> "Piano" ++ s;
        MezzoForte  -> "MezzoForte" ++ s;
        Forte       -> "Forte" ++ s
      };

  instance Show Note where
    shows note s =
      case note of
        Note tick staveIndex timeValue intensity ->
          "Note " ++ show tick ++ " " ++ show staveIndex ++ " " ++ 
                              show timeValue ++ " " ++ show intensity ++ s;

  instance Show Score where
    shows score s =
      case score of
        Score notes -> "Score " ++ show notes ++ s;
 

  -----------------------------------------------------------------------------------------------
  --   Key signatures
  -----------------------------------------------------------------------------------------------

  -- A key signature is a mapping from StaveIndex to Pitch, that is, from the vertical position on
  -- the stave to a Midi pitch value. (The mapping is non-linear since the former is expressed in a
  -- particular key whereas the latter is an index in a 12-note chromatic scale.)

  type KeySignature = (StaveIndex -> Pitch);


  -- The Midi pitches for a chromatic scale:
  --   60   C   - middle C
  --   61   C#
  --   62   D
  --   63   D#
  --   64   E
  --   65   F
  --   66   F#
  --   67   G
  --   68   G#
  --   69   A
  --   70   A#
  --   71   B
  --   72   C   - upper C

  -- The Midi pitches for the key of C major are
  cMajorPitches :: [Pitch];
  cMajorPitches = 
    [60, 62, 64, 65,67, 69, 71] ++ map (+ 12) cMajorPitches;

  -- The C major key signature for the treble clef (which maps the bottom line of the stave to middle C) is:
  cMajor :: KeySignature;
  cMajor i = cMajorPitches !! (14 - i);
  -- That is, it maps the stave index 14 (ie, the bottom line of the stave) to 60 (ie, Midi middle C).

  -----------------------------------------------------------------------------------------------
  --   Functions for playing a score
  -----------------------------------------------------------------------------------------------

  -- This is the top-level function. It creates an action value that, if executed, would result 
  -- in the playing of the score on the specified Midi instrument
  play :: Instrument -> Score -> Action;
  play instrument score =
    let {seq = scoreToSequence instrument score;
         midiSeq = mkMidiSequence seq;
         action = playMidi midiSeq
    } in action;


  -- Stop playing the last sequence that was started
  stop :: Action;
  stop = stopMidi;


  -- Converts a score to a Sequence datatype value (which represents a sequence of Midi events and
  -- can be directly converted into an actual Midi score)
  scoreToSequence :: Instrument -> Score -> Sequence;
  scoreToSequence instrument score =
    case score of
      Score notes ->
        let {midiEvents = notesToMidiEvents notes;
             track = Track instrument midiEvents
        } in Sequence [track];


  -- Map a sequence of notes to a sequence of Midi events. Since each note gives rise to a NoteOn
  -- and a NoteOff event the prelude function  concatMap is used for this task
  --    concatMap :: (a -> [b]) -> [a] -> [b]

  notesToMidiEvents :: [Note] -> [MidiEvent];
  notesToMidiEvents = concatMap noteToMidiEvents;

  -- The score is assumed to be written in the key of C major
  noteToMidiEvents :: Note -> [MidiEvent];
  noteToMidiEvents note =
    case note of 
      Note tick staveIndex timeValue intensity ->
        let {pitch = cMajor staveIndex;
             duration = durationOf timeValue;
             loud = loudnessOf intensity;
             event1 = NoteOn  pitch loud tick;
             event2 = NoteOff pitch loud (tick + duration)
        } in [event1, event2];


  durationOf :: TimeValue -> Tick;
  durationOf timeValue =
    let i = fromEnum timeValue in 2 ^ i;

  loudnessOf :: Intensity -> Loudness;
  loudnessOf intensity =
    case intensity of {
      Piano      -> 25;
      MezzoForte -> 50;
      Forte      -> 100
    };


  -----------------------------------------------------------------------------------------------
  --   Functions for saving a score to file
  -----------------------------------------------------------------------------------------------

  -- This is a top-level function. It creates an action that, if executed, saves the score
  -- to file.
  save :: String -> Instrument -> Score -> Action;
  save fName instrument score =
    let {seq = scoreToSequence instrument score;
         midiSeq = mkMidiSequence seq;
         action = writeMidiFile fName midiSeq
    } in action;


  -----------------------------------------------------------------------------------------------
  --   Functions for displaying a stave
  -----------------------------------------------------------------------------------------------

  -- Function for drawing a picture of a stave with a couple of feint lines above and one below,
  -- surrounded by a (selectable) bounding box.
  drawStave :: Pic;
  drawStave = 
    drawLines nBars 0 [False, False, True, True, True, True, True, False] <+> 
    drawBars 0 nBars <+>
    bBox nBars;

  -- Number of bars in the stave
  nBars = 24;

  -- Function to draw an invisible, selectable bounding box
  bBox :: Int -> Pic;
  bBox nBars = 
    let {w = nBars * w3;
         h = 7 * h3
    } in SelRect w h trans trans;


  -- Function to draw the vertical lines
  drawBars :: Int -> Int -> Pic;
  drawBars x n =
    line black x h4 0 h5 <+>
    if n == 0 then NoPic else drawBars (x + w3) (n-1);

  -- The horizontal lines (2 feint, 5 dark, 1 feint)
  lines nBars = drawLines nBars 0 [False, False, True, True, True, True, True, False];

  drawLines :: Int -> Int -> [Bool] -> Pic;
  drawLines nBars y darks =
    case darks of {
      [] -> NoPic;
      dark : darks -> 
        let {color = if dark then black else lightGray;
             wTotal = nBars * w3;
             ln = line color 0 y wTotal 0 
        } in ln <+> drawLines nBars (y + h3) darks
    };

  -- Some drawing functions

  (<+>) :: Pic -> Pic -> Pic;
  (<+>) =  Super;


  line:: Color -> Int -> Int -> Int -> Int -> Pic;
  line c x y w h = Trans x y (Line w h c);

  -- Constants that determine the size of the stave

  h1, h2, h3, h4 :: Int;

  -- h1 is one quarter of the distance between adjacent lines of the stave
  h1 = 4;
  h2 = h1 + h1;
  h3 = h2 + h2;	        -- Distance between adjacent lines of the stave
  h4 = h3 + h3;
  h5 = h4 + h4;

  w1, s2, w3 :: Int;

  -- w1 is one quarter of the distance between adjacent bars of the stave
  w1 = 12;
  w2 = w1 + w1;
  w3 = w2 + w2;	         -- Distance between adjacent bars of the stave

  -----------------------------------------------------------------------------------------------
  --   Functions for displaying a score
  -----------------------------------------------------------------------------------------------

  drawScore :: Score -> Pic;
  drawScore score = 
    case score of 
      Score notes -> drawStave <+> drawNotes notes;

  drawNotes :: [Note] -> Pic;
  drawNotes notes =
    let notePics = map drawNote notes
    in foldl Super NoPic notePics;


  drawNote :: Note -> Pic;
  drawNote note =
    case note of {
      Note tick pitch timeValue intensity ->
        let {x = tick * w3 + w2;
             y = pitch * h2;
             color = blue;
             notePic = drawTimeValue color timeValue
        } in Trans x y notePic
    };


  drawTimeValue :: Color -> TimeValue -> Pic;

  drawTimeValue color tv =
    let {solidBlob  = Trans (-w1) (-h2) (Ellipse w2 h3 color trans);
         hollowBlob = Trans (-w1) (-h2) (Ellipse w2 h3 trans color);
         top        = - 4 * h3;
         cLine      = line color;
         vLine      = cLine w1 0 0 top;
         hook1      = cLine w1 top w1 h3;
         hook2      = Trans 0 h3 hook1;
         hook3      = Trans 0 h3 hook2;
         crotchet   = vLine <+> solidBlob;
         quaver     = crotchet <+> hook1;
         semiquaver = quaver <+> hook2;
         leftBar    = cLine (-w1) (-h2) 0 h3;
         rightBar   = Trans w2 0 leftBar
    } in case tv of {
           Demisemiquaver -> semiquaver <+> hook3;
           Semiquaver     -> semiquaver;
           Quaver         -> quaver;
           Crotchet       -> crotchet;
           Minim          -> vLine <+> hollowBlob;
           Semibreve      -> hollowBlob;
           Breve          -> hollowBlob <+> leftBar <+> rightBar
        };


  -----------------------------------------------------------------------------------------------
  --   Functions for changing scores
  -----------------------------------------------------------------------------------------------

addNote :: Note -> Score -> Score;

addNote note score =
  case score of
    Score notes -> Score (note : notes);

deleteNote :: Tick -> Pitch -> Score -> Score;

deleteNote tick pitch score =
  case score of
    Score notes -> Score [note | note <- notes, differsFrom tick pitch note];

differsFrom :: Tick -> Pitch -> Note -> Bool;

differsFrom tick pitch note =
  case note of 
    Note tick' pitch' timeValue intensity -> 
      (tick /= tick') || (pitch /= pitch');


  -----------------------------------------------------------------------------------------------
  --   Functions for Direct Manipulation of scores
  -----------------------------------------------------------------------------------------------

  instance Edit Score where {
    style score = View drawScore Picture;
    edit score path =
      [("Insert demisemiquaver", insertNoteFn Demisemiquaver),
       ("Insert semiquaver",     insertNoteFn Semiquaver),
       ("Insert quaver",         insertNoteFn Quaver),
       ("Insert crotchet",       insertNoteFn Crotchet),
       ("Insert minim",          insertNoteFn Minim),
       ("Insert semibreve",      insertNoteFn Semibreve),
       ("Insert breve",          insertNoteFn Breve),

       ("DELETE NOTE",           deleteNoteFn),
       ("SIMPLIFY",              simplifyFn)]
  };

  -- Convert a mouse X coordinate to the corresponding tick
  decodeTick :: Int -> Tick;
  decodeTick x = x `div` w3;

  -- Convert a mouse Y coordinate to the corresponding stave index (0 .. 14)
  decodePitch :: Int -> StaveIndex;
  decodePitch y = 
    let i = (y + h1) `div` h2
    in max 0 (min 14 i);


insertNoteFn :: DurationIndex ->
                Score -> Path -> Coord -> Coord -> String -> String -> String -> (MString, MString, MString);

insertNoteFn timeValue score path x y lhs rhs clip =
  let {tick = decodeTick x;
       staveIndex = decodePitch y;
       intensity = Forte;
       note = Note tick staveIndex timeValue intensity;
       rhs' = "addNote (" ++ show note ++ ") (" ++ rhs ++ ")"
  } in (Just rhs', Nothing, Just "Note added");

deleteNoteFn :: Score -> Path -> Coord -> Coord -> String -> String -> String -> (MString, MString, MString);

deleteNoteFn score path x y lhs rhs clip =
  let {tick = show (decodeTick x);
       pitch = show (decodePitch y);
       rhs' = "deleteNote " ++ tick ++ " " ++ pitch ++ " (" ++ rhs ++ ")";
       message' = "OK"
  } in (Just rhs', Nothing, Just "Note deleted");

simplifyFn :: Score -> Path -> Coord -> Coord -> String -> String -> String -> (MString, MString, MString);

simplifyFn score path x y lhs rhs clip =
  let rhs' = show score
  in (Just rhs', Nothing, Just "Simplified")


}

  
