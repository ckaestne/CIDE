module Global where
{ import System.IO.Unsafe;
  import Network.BSD;
  import Control.Concurrent;
   
  local_hostent :: MVar HostEntry;
  local_hostent = unsafePerformIO newEmptyMVar}
