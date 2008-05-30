-- Proof objects, proof parser and theorem-prover for prop logic

module PropLogicProofLib(
  Proof(Axiom, AndIntro, AndElimL, AndElimR, ImpIntro, ImpElim,
        OrIntroL, OrIntroR, OrElim, NotIntro, NotElim, TrueIntro, FalseElim),
  form, proof, 
  infer, pp, inf
) where {

  import PropLogicLib;
  import Parse;

  -- Concrete representation of proofs for an intuitionistic propositional logic.
  -- Note that a Proof value is *not* necessarily a well-formed proof.

  data Proof = Axiom String
             | AndIntro Proof Proof
             | AndElimL Proof
             | AndElimR Proof
             | ImpIntro Identifer Form Proof
             | ImpElim Proof Proof
             | OrIntroL Proof Form
             | OrIntroR Proof Form
             | OrElim Proof (String, Proof) (String, Proof)
             | NotIntro String Form Proof
             | NotElim Proof Proof
             | TrueIntro
             | FalseElim Proof Form;

  -- Parser for IPL proofs

  form :: Reads Form;
  form =  readsForm;

  proof :: Reads Proof;
  proof = axiomProof <> bracketedProof <> 
          andIntroProof <> andElimLProof <> andElimRProof <>
          impIntroProof <> impElimProof <>
          orIntroLProof <> orIntroRProof <> orElimProof <>
          notIntroProof <> notElimProof <>
          trueIntroProof <> falseElimProof;


  axiomProof = spaced identifier ~> Axiom;

  andIntroProof =
    ((keyword "AndIntro" >> proof) >> proof) ~>
    \x -> let { proof1 = snd (fst x);
                proof2 = snd x
          } in AndIntro proof1 proof2;


  andElimLProof =
    (keyword "AndElimL" >> proof) ~>
    \x -> let prf = snd x in AndElimL prf;


  andElimRProof =
    (keyword "AndElimR" >> proof) ~>
    \x -> let prf = snd x in AndElimR prf;


  impIntroProof =
    (((((keyword "ImpIntro" >> name) >> colon) >> form) >> dot) >> proof) ~>
    \x -> let { prf = snd x;
                y = fst (fst x);
                fm = snd y;
                z = fst (fst y);
                nm = snd z
          } in ImpIntro nm fm prf;


  impElimProof = 
    (keyword "ImpElim" >> proof) >> proof ~> 
    \x -> let { prf1 = snd (fst x);
                prf2 = snd x
          } in ImpElim prf1 prf2;  


  orIntroLProof =
    (keyword "OrIntroL" >> proof) >> form ~>
    \x -> let { prf = snd (fst x);
                fm  = snd x
          } in OrIntroL prf fm;


  orIntroRProof =
    (keyword "OrIntroR" >> proof) >> form ~>
    \x -> let { prf = snd (fst x);
                fm  = snd x
          } in OrIntroR prf fm;

  
  orElimProof =
    ((((((((((keyword "OrElim" >> proof) >> (keyword "of")) >>
       (keyword "Left" )) >> name) >> (keyword "->")) >> proof) >>
       (keyword "Right")) >> name) >> (keyword "->")) >> proof) ~>
    \u -> case u of
            ((((((((((_, prf), _), _), nm1), _), prf1), _), nm2), _), prf2) ->
              OrElim prf (nm1, prf1) (nm2, prf2);

  notIntroProof =
    (((((keyword "NotIntro" >> name) >> colon) >> form) >> dot) >> proof) ~>
    \u -> case u of
            (((((_, nm), _), fm), _), prf) ->
              NotIntro nm fm prf;

  notElimProof =
    ((keyword "NotElim" >> proof) >> proof) ~>
    \u -> case u of
            ((_, prf1), prf2) -> 
              NotElim prf1 prf2;

  trueIntroProof = 
    keyword "TrueIntro" ~>
      \u -> TrueIntro;

  falseElimProof = 
    ((keyword "FalseElim" >> proof) >> form) ~>
    \u -> case u of
            ((_, prf), fm) ->
              FalseElim prf fm;


  bracketedProof = bracketed proof;

  colon = spaced (char ':');

  dot = spaced (char '.');

  name = spaced identifier;

  keyword s = spaced (string s);


  -- I N F E R E N C E -------------------------------------------------------

  infer :: Proof -> Maybe Theorem;
  infer =  infer1 [];

  infer1 :: [Hyp] -> Proof -> Either String Theorem;
  infer1 hyps prf =
    case prf of {
      Axiom nm -> case lookupHyp hyps nm of {
                    Nothing -> Left ("Bad axiom name: " ++ nm);
                    Just fm -> Right (taut nm fm)
                  };
      
      AndIntro prf1 prf2 -> case ((infer1 hyps prf1), (infer1 hyps prf2)) of {
                              ((Left s), _) -> Left s;
                              (_, (Left s)) -> Left s;
                              ((Right thm1),(Right thm2)) -> Right (andIntro thm1 thm2)
                            };

      AndElimL prf -> case (infer1 hyps prf) of {
                        Left s -> Left s;
                        Right thm -> Right (andElimL thm)
                      };

      AndElimR prf -> case (infer1 hyps prf) of {
                        Left s -> Left s;
                        Right thm -> Right (andElimR thm)
                      };

      ImpIntro nm fm prf -> let {hyps' = (nm,fm): hyps;
                                 eThm = infer1 hyps' prf
                            } in case eThm of {
                                   Left s -> Left s;
                                   Right thm -> Right (disch nm thm)
                                 };

      ImpElim prf1 prf2 -> let {eThm1 = infer1 hyps prf1;
                                eThm2 = infer1 hyps prf2
                           } in case (eThm1, eThm2) of {
                                  (Right thm1, Right thm2) ->
                                     Right (mp thm1 thm2)
                                };

      OrIntroL prf fm -> let eThm = infer1 hyps prf
                         in case eThm of {
                              Left s -> Left s;
                              Right thm -> Right (orIntroL thm fm)
                            };

      OrIntroR prf fm -> let eThm = infer1 hyps prf
                         in case eThm of {
                              Left s -> Left s;
                              Right thm -> Right (orIntroR thm fm)
                            };


      OrElim prf (nm1, prf1) (nm2, prf2) -> 
                              case infer1 hyps prf of {
                                Left s -> Left s;
                                Right thm -> 
                                    case projForm thm of {
                                      Disj fmL fmR ->
                                           let { eThm1 = infer1 ((nm1, fmL) : hyps) prf1;
                                                 eThm2 = infer1 ((nm2, fmR) : hyps) prf2
                                           } in case (eThm1, eThm2) of {
                                                  (Left s, _) -> Left s;
                                                  (_, Left s) -> Left s;
                                                  (Right thm1, Right thm2) -> Right (orElim thm thm1 thm2)
                                                };
                                      fm -> Left ("Not a disjunction: " ++ show fm)
                                    }
                              };

     
      NotIntro nm fm prf -> let {hyps' = (nm, fm): hyps;
                                 eThm = infer1 hyps' prf
                            } in case eThm of {
                                   Left s -> Left s;
                                   Right thm -> Right (notIntro nm thm)
                                 };

      
      NotElim prf1 prf2 -> let {eThm1 = infer1 hyps prf1;
                                eThm2 = infer1 hyps prf2
                           } in case (eThm1, eThm2) of {
                                  (Left s, _) -> Left s;
                                  (_, Left s) -> Left s;
                                  (Right thm1, Right thm2) -> Right (notElim thm1 thm2)
                                };


      TrueIntro -> Right trueIntro;


      FalseElim prf fm -> let eThm = infer1 hyps prf
                          in case eThm of {
                               Left s -> Left s;
                               Right thm -> Right (falseElim thm fm)
                             }
                          
    };


  lookupHyp :: [(String, Form)] -> String -> Maybe Form;
  lookupHyp hyps nm =
    case hyps of {
      []              -> Nothing;
      (nm', fm):hyps  -> if eq nm nm' then
                           Just fm
                         else
                           lookupHyp hyps nm
    };

  -- Convenient functions ---------------------------------------------------

  -- Parse a proof  (yields an error if unparsable)
  pp :: String -> Proof;
  pp = unique (exact proof);

  -- Infer a theorem  (with error if not possible)
  inf :: Proof -> Theorem;
  inf prf = case infer prf of {
              Left s -> error s;
              Right thm -> thm
            }
  
}
