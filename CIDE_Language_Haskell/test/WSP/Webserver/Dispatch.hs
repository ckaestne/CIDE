module Dispatch where
{ import Control.Concurrent.MVar;
  import Maybe;
  import Monad;
  import Deployment;
  import State;
  import WASH.CGI.CGI;
  import WASH.CGI.RawCGI;
   
  runDispatch ::
                a -> String -> IO (Maybe (String, CGIOptions, CGIProgram));
  runDispatch conf path
    = do { st <- readMVar currentState;
           let { deployments = deploymentState st;
                 dobjs = objs deployments};
           return $ dispatchLoop path dobjs};
   
  dispatchLoop ::
                 String ->
                   [DeployedObject] -> Maybe (String, CGIOptions, CGIProgram);
  dispatchLoop path [] = Nothing;
  dispatchLoop path (dobj : dobjs)
    = do { if objectActive dobj then return () else Nothing;
           pathInfo <- splitPath (objectPath dobj) path;
           return (pathInfo, objectOptions dobj, objectVal dobj)}
        `mplus` dispatchLoop path dobjs;
   
  splitPath :: String -> String -> Maybe String;
  splitPath "" testPath
    = if legalSuffix testPath then Just testPath else Nothing;
  splitPath _ "" = Nothing;
  splitPath (p : ps) (t : ts)
    = if p == t then splitPath ps ts else Nothing;
   
  legalSuffix :: String -> Bool;
  legalSuffix "" = True;
  legalSuffix (x : xs) = legalSuffixChar x;
   
  legalSuffixChar :: Char -> Bool;
  legalSuffixChar x = x `elem` "/?"}
