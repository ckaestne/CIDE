module Deployment where
{ import System.Plugins as P;
  import WASH.CGI.RawCGI as RawCGI (CGIProgram, CGIOptions);
   
  data DeploymentState = DeploymentState{desc :: [Descriptor],
                                         objs :: [DeployedObject]};
  initialDeploymentState = DeploymentState{desc = [], objs = []};
   
  data Descriptor = Descriptor{applicationName :: String,
                               applicationDescription :: String, applicationPath :: String,
                               deploymentDirectory :: FilePath, extra_resources :: [String],
                               mainModule :: String, mainSymbol :: String, sources :: [String],
                               libraries :: [String], extra_libraries :: [String],
                               packages :: [String], compilerFlags :: [String],
                               linkerFlags :: [String]}
                  deriving (Read, Show);
   
  data DeployedObject = DeployedObject{objectName :: String,
                                       objectPath :: String, objectMod :: String,
                                       objectVal :: CGIProgram, objectActive :: Bool,
                                       objectSystem :: Bool, objectOptions :: CGIOptions}}
