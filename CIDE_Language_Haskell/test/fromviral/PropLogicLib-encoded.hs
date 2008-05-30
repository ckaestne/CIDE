-- Propositional logic
-- THIS VERSION ENCODES NAMES AS INTEGERS, THUS ALLOWING MORE RAPID COMPARISON.

module PropLogicLib (
  Form(F, T, Var, Neg, Conj, Disj, Imp, Equiv),     
  mkVar, encode, decode,
  showForm, showName, readsForm, pf,

  Theorem(),
  projHyps, projForm,
  taut, disch, mp,
  andIntro, andElimL, andElimR,
  orIntroL, orIntroR, orElim,
  notIntro, notElim,
  trueIntro, falseElim
) 

where {
  import List;
  import Char;
  import Parse;
  import Set;

  data Form = F
            | T
            | Var Int                                        -- Note that identifiers are coded as integers
            | Neg   Form
            | Conj  Form Form
            | Disj  Form Form
            | Imp   Form Form
            | Equiv Form Form;

  -- This function restricts variable names to proper identifiers
  mkVar :: String -> Var;
  mkVar s = if isIdentifier s then 
              Var (encode s)
            else
              error ("PropLogic.mkVar: bad identifer: " ++ s);

  isIdentifier s = isAlpha (head s) &&
                   and (map isAlphaNum (tail s));

  base = 100;                                                -- Used for encoding/decoding strings

  encode :: String -> Int;
  encode = let f = \n ch -> (n * base) + encodeChar ch
           in foldl f 0;

  encodeChar :: Char -> Int;            
  encodeChar ch = let n = ord ch - ord '0'                   -- Crude, but adequate for ordinary ASCII alphamerics
                  in if (n >= 0) && (n < base) then 
                       n
                     else
                       error ("PropLogic.encodeChar: char out of range: " ++ [ch]);

  decode :: Int -> String;
  decode n = decode' n  "";

  decode' n rest =
    let {d = rem  n base;
         q = quot n base;
         rest' = decodeChar d : rest
    } in if q == 0 then rest' else decode' q rest';

  decodeChar :: Int -> Char;
  decodeChar n = chr (n + ord '0');


  instance Equal Form where
    eq fm1 fm2 =
      case (fm1, fm2) of {
        (F,               F)                -> True;
        (T,               T)                -> True;
        (Var s1,          Var s2)           -> (s1 == s2);
        (Neg fm1a,        Neg fm2a)         -> (eq fm1a fm2a);
        (Conj fm1a fm1b,  Conj fm2a fm2b)   -> eq fm1a fm2a && eq fm1b fm2b;
        (Disj fm1a fm1b,  Disj fm2a fm2b)   -> eq fm1a fm2a && eq fm1b fm2b;
        (Imp fm1a fm1b,   Imp fm2a fm2b)    -> eq fm1a fm2a && eq fm1b fm2b;
        (Equiv fm1a fm1b, Equiv fm2a fm2b)  -> eq fm1a fm2a && eq fm1b fm2b;
        _                                   -> False
      };


  instance Show Form where {
    shows fm s = showForm fm ++ s;
    show  fm   = showForm fm
  };


  -- The first arg (fm) is present purely in order so that the dynamic class
  -- mechanism can determine which instance to use. It is not used in computing the result.
  instance Read Form where 
    reads fm = readsForm;

  -- A convenient "parse form" function
  pf :: String -> Form;
  pf = unique (exact readsForm);



  -- Unparsing functions
  showForm :: Form -> String;
  showForm fm =
    case fm of {
      F              -> "F";
      T              -> "T";
      Var s          -> decode s;
      Neg fm         -> "~ " ++ showParenForm fm;
      Conj  fm1 fm2  -> opForm fm1 " & " fm2;
      Disj  fm1 fm2  -> opForm fm1 " | " fm2;
      Imp   fm1 fm2  -> opForm fm1 " => " fm2;
      Equiv fm1 fm2  -> opForm fm1 " == " fm2
    };

  opForm fm1 op fm2 = showParenForm fm1 ++ op ++ showParenForm fm2;


  -- A formula is deemed to be atomic (for unparsing) if it is a truth value, a Var or a Neg of an atomic formula
  isAtomic fm =
    case fm of {
      F        -> True;
      T        -> True;
      Var _    -> True;
      Neg fm   -> isAtomic fm;
      _        -> False
    };

  -- Apply paretheses unless atomic
  showParenForm fm =
    if isAtomic fm then 
      showForm fm 
    else
      "(" ++ showForm fm ++ ")";


  -- Parsing functions

  readsForm :: Reads Form;
  readsForm = readsAtomicForm <> readsBinOpForm;

  readsAtomicForm = readsTruthValue <> readsVar <> readsNegForm <> readsBracketedForm;

  readsTruthValue = spaced ((char 'F' ~> const F) <> (char 'T' ~> const T));

  readsVar = spaced identifier ~> mkVar;

  readsNegForm = (spaced (char '~') >> readsAtomicForm) ~> (Neg. snd);

  readsBracketedForm = bracketed readsForm;

  readsBinOpForm = (readsAtomicForm >> spaced operator >> readsAtomicForm) ~> mkBinForm;

  mkBinForm x = 
    case x of {
      ((f1, "&" ), f2) -> Conj  f1 f2;
      ((f1, "|" ), f2) -> Disj  f1 f2;
      ((f1, "=>"), f2) -> Imp   f1 f2;
      ((f1, "=="), f2) -> Equiv f1 f2;
      ((f1, op  ), f2) -> error "PropLogic.mkBinForm:  unknown operator: " ++ op
    };


-- --- Theorems ---------------------------------------------------------------------------------------------

  type Name = Int;                                         -- The names of hypotheses are (like Var names) encoded 

  type Hyp = (Name, Form);

  type Hyps = [Hyp];

  data Theorem = Thm Hyps Form;

  projHyps :: Thm -> Hyps;
  projHyps thm = case thm of Thm hyps _ -> hyps;

  projForm :: Thm -> Form;
  projForm thm = case thm of Thm _ fm -> fm;

  instance Show Theorem where {
    shows thm s = showThm thm ++ s;
    show  thm   = showThm thm
  };

  showThm :: Theorem -> String;
  showThm thm = 
    case thm of 
      Thm hyps conc -> showNames (map fst hyps) ++ " |- " ++ showForm conc;

  showNames :: [Name] -> String;
  showNames names = 
    if null names then
      ""
    else
      '{' : showName (head names) ++ concat [ ", " ++ (showName name) | name <- tail names] ++ "} ";

  showName :: Name -> String;
  showName =  decode;


  -- Natural deduction inference rules ----------------------------------------------------------------------

  taut :: Name -> Form -> Thm;
  taut name fm = Thm [(name,fm)] fm;

  disch :: Name -> Theorem -> Theorem;
  disch name thm =
    case thm of
      Thm hyps conc ->
        let { p = \hyp -> fst hyp `eq` name;
              hypsPair = partition p hyps;
              hyps1 = fst hypsPair; 
              hyps2 = snd hypsPair
        } in case hyps1 of {
               [(nm, fm)] -> Thm hyps2 (Imp fm conc);
               _          -> error "PropLogic.impIntro: hypothesis not present"
             };
       

  mp :: Theorem -> Theorem -> Theorem;
  mp thm1 thm2 =
    case (thm1, thm2) of {
      (Thm hyps1 (Imp fm1a fm1b), Thm hyps2 fm2) ->
         let { clashes = isClash hyps1 hyps2;
               hyps = hyps1 ++ hyps2
         } in if null clashes then
                if fm1a `eq` fm2 then
                  Thm hyps fm1b
                else
                  error "PropLogic.mp: formulae do not match"
              else
                  error ("PropLogic.mp: " ++ showNames clashes);
      _  -> error "PropLogic.mp: not an implication"    
    };

  andIntro :: Theorem -> Theorem -> Theorem;
  andIntro thm1 thm2 =
    case (thm1, thm2) of
      ((Thm hyps1 conc1), (Thm hyps2 conc2)) ->
        Thm (merge hyps1 hyps2) (Conj conc1 conc2);

  andElimL, andElimR :: Theorem -> Theorem;
  andElimL thm =
    case thm of {
      Thm hyps (Conj fmL fmR) -> Thm hyps fmL;
      _                       -> error "PropLogic.andElimL: not a conjunction"
    };

  andElimR thm =
    case thm of {
      Thm hyps (Conj fmL fmR) -> Thm hyps fmR;
      _                       -> error "PropLogic.andElimR: not a conjunction"
    };

  orIntroL, orIntroR :: Theorem -> Form -> Form;
  orIntroL thm fmR =
    case thm of 
      Thm hyps fmL -> Thm hyps (Disj fmL fmR);

  orIntroR thm fmL =
    case thm of 
      Thm hyps fmR -> Thm hyps (Disj fmL fmR);

  orElim :: Theorem -> Theorem -> Theorem -> Theorem;
  orElim thm thm1 thm2 =
    case (thm, (thm1, thm2)) of 
      (Thm hyps fm, (Thm hyps1 fm1, Thm hyps2 fm2)) ->
        case fm of {
          Disj fmL fmR -> 
              let { hyps1' = removeForm fmL hyps1;
                    hyps2' = removeForm fmR hyps2;
                    hyps' = merge hyps (merge hyps1' hyps2')
              } in if eq fm1 fm2 then
                      Thm hyps' fm1
                   else
                      error ("PropLogic.orElim: theorems do not match: " ++ show fm1 ++ ", and " ++ show fm2);
          _ -> error ("PropLogic.orElim: not a disjunction: " ++ show fm)
        };

  notIntro :: Name -> Theorem -> Theorem;
  notIntro nm thm =
    case thm of 
      Thm hyps fm -> 
        let { p = \hyp -> fst hyp `eq` nm;
              hypsPair = partition p hyps;
              hyps1 = fst hypsPair;
              hyps2 = snd hypsPair
        } in case hyps1 of {
              [(nm,fm)] -> Thm hyps2 (Neg fm);
              _         -> error ("PropLogic.notIntro: hyp not present: " ++ (showName nm))
             };

  notElim :: Theorem -> Theorem -> Theorem;
  notElim thm1 thm2 =
    case (thm1, thm2) of {
      ((Thm hyps1 fm1),(Thm hyps2 (Neg fm2))) -> 
        if (fm1 `eq` fm2) then
          Thm (merge hyps1 hyps2) F
        else
          error "PropLogic.notElim: non-matching formulae";
      _  -> error "PropLogic.notElim: not a negation"
    };

  trueIntro :: Theorem;
  trueIntro =  Thm [] T;

  falseElim :: Theorem -> Form -> Theorem;
  falseElim thm fm =
    case thm of {
      Thm hyps F -> Thm hyps fm;
      _          -> error "PropLogic.falseElim: non-false theorem"
    };

           
              

  -- Attempt to merge two list of hypotheses. Throws an error if the same name occurs
  -- in each list but associated with a different formula.
  merge :: Hyps -> Hyps -> Hyps;
  merge hyps1 hyps2 = 
    let clashes = isClash hyps1 hyps2 
    in if null clashes then
         nub (hyps1 ++ hyps2)
       else
         error ("PropLogic.merge: " ++ showNames clashes);


  -- ---------------------------------------

  -- This function compares two sets of hypotheses; it returns a list of the names of any
  -- clashes. Thus, the return of an empty list denotes success.

  isClash :: [Hyp] -> [Hyp] -> [Name];

  isClash hyps1 hyps2 =
    case hyps2 of {
      [] -> [];
      hyp:hyps -> isClash' hyps1 hyp ++ isClash hyps1 hyps
    };

  isClash' :: [Hyp] -> Hyp -> [Name];

  isClash' hyps hyp =
    case hyps of {
      [] -> [];
      hyp1:hyps1 -> isClash'' hyp hyp1 ++ isClash' hyps1 hyp
    };

  isClash'' :: Hyp -> Hyp -> [Name];

  isClash'' hyp1 hyp2 =
    case (hyp1, hyp2) of {
      ((name1, fm1), (name2, fm2)) -> 
        if (name1 /= name2) || (fm1 `eq` fm2) then
          []
        else
          [name1];
    _  -> hyp1
  };


  -- This function removes a formula from a list of hypotheses. It fails if the formula is not present
  removeForm :: Form -> [Hyp] -> [Hyp];
  removeForm fm hyps =
    case hyps of {
      [] -> error ("PropLogic.removeHyp: formula not found: " ++ show fm);
      hyp: hyps -> if eq fm (snd hyp) then
                          hyps
                       else 
                          hyp : removeForm fm hyps
    }
}
