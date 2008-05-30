module DNA_lib (
  Base(A,C,G,T,X),          -- Enumerated type representing bases adenine, cytosine, guanine, thymine and 'other'.
  DNA(DNA),                 -- Representation of a molecule of DNA as a sequence of its bases
  parseDNA,                 -- Parser for DNA molecule
  displayDNA,               -- Diagrammatic representation of a molecule of DNA
  displayDNA',              -- Diagrammatic representation of a molecule of DNA with shadow effects

  deleteBase,               -- Used for direct manipulation
  insertGap,
  setBaseTo,
  showDNA,

  Amino(Ala, Arg, Asn, Asp, Cys, Gln, Glu, Gly, His, Ile, Leu, Lys, Met, Phe, Pro, Ser, Thr, Trp, Tyr, Val),
  showAmino,
  encode,
  Transcript(Transcript),
  transcribe,
  displayTranscript,
  displayAnnotDNA,

  scale                     -- For scaling (imported from Pictures)
)
 where {

import Pictures;
import DNA_pictures;        -- (Mutually recursive import)


-- Bases -------------------------------

data Base = A | C | G | T | X;

showBase :: Base -> String;
showBase b =
  case b of {
    A -> "A";
    C -> "C";
    G -> "G";
    T -> "T";
    X -> "X"
  };

instance Show Base where
  show b = showBase b;


parseBase :: Char -> Base;
parseBase ch = 
  case ch of {
    'A' -> A; 
    'C' -> C;
    'G' -> G;
    'T' -> T;
     _  -> X
  };

showBases :: [Base] -> String;
showBases bs = '[' : showBases' bs;

showBases' bases =
  case bases of {
    []   -> "]";
    [b]  -> show b ++ "]";
    b:bs -> show b ++ ", " ++ showBases' bs
  };


-- DNA --------------------------------

data DNA = DNA [Base];

showDNA :: DNA -> String;
showDNA dna =
  case dna of
    DNA bases -> "DNA " ++ showBases bases;



parseDNA :: String -> DNA;
parseDNA s = 
  let {s' = filter (/= ' ') s;         -- ignore spaces
       bases = map parseBase s'
  } in DNA bases;


displayDNA, displayDNA' :: DNA -> Pic;
displayDNA dna =
  case dna of 
    DNA bases -> let pics = map displayBasePair bases
                 in above pics;

displayDNA' = withShadow . displayDNA;
        

-- Codons --------------------------------------------

type Codon = (Base, Base, Base);


-- Amino acids ---------------------------------------

data Amino 
  = Ala | Arg | Asn | Asp | Cys | Gln | Glu | Gly | His | Ile
  | Leu | Lys | Met | Phe | Pro | Ser | Thr | Trp | Tyr | Val;


showAmino :: Amino -> String;
showAmino amino =
  case amino of {
    Ala    -> "Alanine";
    Arg    -> "Arginine";
    Asn    -> "Asparagine";
    Asp    -> "Aspartic acid";
    Cys    -> "Cysteine";
    Gln    -> "Glutamine";
    Glu    -> "Glutamic acid";
    Gly    -> "Glycine";
    His    -> "Histidine";
    Ile    -> "Isoleucine";
    Leu    -> "Leucine";
    Lys    -> "Lysine";
    Met    -> "Methionine";
    Phe    -> "Phenylalanine";
    Pro    -> "Proline";
    Ser    -> "Serine";
    Thr    -> "Threonine";
    Trp    -> "Tryptophan";
    Tyr    -> "Tyrosine";
    Val    -> "Valine"
  };


encode :: Codon -> Maybe Amino;
encode codon =
  case codon of {
    (X, _, _) -> Nothing;    -- Treat an 'X' in any position as
    (_, X, _) -> Nothing;    -- terminating the transcription
    (_, _, X) -> Nothing;    -- process.

    (T, T, T) -> Just Phe;
    (T, T, C) -> Just Phe;
    (T, T, _) -> Just Leu;
    (C, T, _) -> Just Leu;
    (A, T, G) -> Just Met;
    (A, T, _) -> Just Ile;
    (G, T, _) -> Just Val;
    
    (T, C, _) -> Just Ser;
    (C, C, _) -> Just Pro;
    (A, C, _) -> Just Thr;
    (G, C, _) -> Just Ala;

    (T, A, A) -> Nothing;
    (T, A, G) -> Nothing;
    (T, A, _) -> Just Tyr;
    (C, A, T) -> Just His;
    (C, A, C) -> Just His;
    (C, A, _) -> Just Gln;
    (A, A, T) -> Just Asn;
    (A, A, C) -> Just Asn;
    (A, A, _) -> Just Lys;
    (G, A, T) -> Just Asp;
    (G, A, C) -> Just Asp;
    (G, A, _) -> Just Glu;

    (T, G, G) -> Just Trp;
    (T, G, A) -> Nothing;
    (T, G, _) -> Just Cys;
    (C, G, _) -> Just Arg;
    (A, G, T) -> Just Ser;
    (A, G, C) -> Just Ser;
    (A, G, _) -> Just Arg;
    (G, G, _) -> Just Gly
  };


-- Transcription --------------------------------

type Junk = [Base];

type Gene = [Amino];

data Transcript = Transcript [Either Junk Gene];

transcribe :: DNA -> Transcript;
transcribe dna =
  case dna of DNA bs -> Transcript (scanBases bs);

scanBases :: [Base] -> [Either Junk Gene];
scanBases bs =
  case bs of {
    []             -> [];
    A : T : G : bs ->                                  -- "START" detected
      case scanCodons bs of
        (aminos, bs') -> Right aminos : scanBases bs';
    b : bs         -> case scanBases bs of {
                        [] -> [Left [b]];
                        Left js : es  -> Left (b:js) : es;
                        es -> Left [b] : es
                       }
  };

scanCodons :: [Base] -> ([Amino], [Base]);
scanCodons bs =
  case bs of {
    b1:b2:b3:bs -> 
      let codon = (b1, b2, b3)
      in case encode codon of {
           Nothing -> ([],bs);                         -- "STOP" detected
           Just amino -> case scanCodons bs of
                           (aminos, bs') -> ((amino: aminos), bs')
         };
    _  -> ([], [])
  };


displayTranscript :: Transcript -> Pic;
displayTranscript transcript =
  case transcript of Transcript es -> displayTrans es;

displayTrans :: [Either Junk Gene] -> Pic;
displayTrans es =
  case es of {
    []     -> NoPic;
    Left junk : es    -> displayJunk junk <-> displayTrans es;
    Right gene : es   -> displayMarker True   <-> 
                         displayGene gene <->
                         displayMarker False  <->
                         displayTrans es     
  };
    

displayJunk :: [Base] -> Pic;
displayJunk junk =
  let y = 30 * length junk
  in Rect 100 y trans blue;

displayGene :: Gene -> Pic;
displayGene aminos =
  case aminos of {
    []      -> NoPic;
    amino : aminos -> displayAmino amino <-> displayGene aminos
  };

displayAmino :: Amino -> Pic;
displayAmino amino =
  let name = showAmino amino
  in displayCodon lightYellow name;

lightYellow = (lighten . lighten) yellow;

displayMarker :: Bool -> Pic;
displayMarker flag =
  displayCodon yellow (if flag then "START" else "STOP");

displayCodon :: Color -> String -> Pic;
displayCodon bc s =
  let {h = 3 * 30;
       rect = Rect 100 h bc blue;
       text = PicText defaultFmt s
  } in rect <+> Trans 5 (h `div` 2) text;

displayAnnotDNA :: DNA -> Pic;
displayAnnotDNA dna =
  let {pic1 = displayDNA dna;
       transcription = transcribe dna;
       pic2 = displayTranscript transcription;
       gap = 20
  } in pic1 <|> Trans gap 0 pic2;



-- Direct manipulation of DNA -------------------------------------------------------

instance Edit DNA where {

  style dna = View displayAnnotDNA Picture;

  edit dna path = [("Delete base",  deleteFn),
                   ("Insert a gap", insertFn),
                   ("Set to A",     setToFn "A"),
                   ("Set to C",     setToFn "C"),
                   ("Set to G",     setToFn "G"),
                   ("Set to T",     setToFn "T"),
                   ("Unset",        setToFn "X"),
                   ("Evaluate",     evaluateFn)]
};


decode :: Path -> Int;
decode path = length path - 5;

deleteFn dna path x y lhs rhs clip =
  let {i = decode path;
       rhs' = "deleteBase " ++ show i ++ " (" ++ rhs ++ ")";
       message' = "Deleting a base"
  } in (Just rhs', Nothing, Just message');

insertFn dna path x y lhs rhs clip =
  let {i = decode path;
       rhs' = "insertGap " ++ show i ++ " (" ++ rhs ++ ")";
       message' = "Inserting a gap"
  } in (Just rhs', Nothing, Just message');

setToFn base dna path x y lhs rhs clip =
  let {i = decode path;
       rhs' = "setBaseTo " ++ show i ++ " " ++ base ++ " (" ++ rhs ++ ")";
       message' = "Setting a base"
  } in (Just rhs', Nothing, Just message');

evaluateFn dna path x y lhs rhs clip =
  let { rhs' = showDNA dna;
        message' = "Evaluating the expression"
  } in (Just rhs', Nothing, Just message');

-- Subsidiary defns

deleteBase :: Int -> DNA -> DNA;
deleteBase i dna =
  case dna of
    DNA bases -> DNA (delete i bases);

delete :: Int -> [a] -> [a];
delete i xs =
  case xs of {
    []  -> xs;               -- invalid request!
    x:xs -> if i == 0 then
              xs
            else
              x : delete (i-1) xs
  };

insertGap i dna =
  case dna of
    DNA bases -> DNA (insert i X bases);

insert :: Int -> a -> [a] -> [a];
insert i x xs =
  case xs of {
    [] -> xs;               -- invalid request!
    y:ys -> if i == 0 then
              x : xs
            else 
              y : insert (i-1) x ys
  };

setBaseTo :: Int -> Base -> DNA -> DNA;
setBaseTo i base dna =
  case dna of
    DNA bases -> DNA (setTo i base bases);

setTo :: Int -> a -> [a] -> [a];
setTo i x xs =
  case xs of {
    [] -> xs;                   -- invalid request!
    y:ys -> if i == 0 then
               x:ys
            else
               y : setTo (i-1) x ys
    }

}
