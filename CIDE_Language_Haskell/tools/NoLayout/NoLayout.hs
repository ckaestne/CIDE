module NoLayout(main) where

import Language.Haskell.Parser
import Language.Haskell.Pretty
import Language.Haskell.Syntax
import IO
import System

unLayout :: String -> String
unLayout hs =
    case parseModule hs of
        ParseOk p -> prettyPrintWithMode mode p
        ParseFailed srcloc errmsg ->
           error ("Parser failed in line " ++ show (srcLine srcloc) ++
                  ", column " ++ show (srcColumn srcloc) ++ ":\n" ++
                  errmsg
                 )
    where
    ParseOk p = parseModule hs
    mode = defaultMode { layout=PPSemiColon }

main :: IO ()
main = do args <- getArgs
          case args of
             [f] -> do hs <- readFile f
                       putStrLn $ unLayout hs
             [f,g] -> do hs <- readFile f
                         writeFile g $ (unLayout hs ++ "\n")
             _   -> do hPutStrLn stderr "invalid arguments"
                       return ()