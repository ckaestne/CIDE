module Util where
{ import Foreign.C.Error (getErrno, eNOENT);
  import System.Posix;
  import Control.Exception as Exception;
  import Control.Concurrent;
  import Network.Socket hiding (accept);
  import qualified Network.Socket as SocketPrim (accept);
  import System.Time;
  import Locale;
  import Char;
  import IO;
  import GHC.Base;
  import GHC.Conc;
  import GHC.IOBase;
  import qualified Debug.Trace;
  trace s e = Debug.Trace.trace s e;
  crlf = "\r\n";
  emptyLine "\r" = True;
  emptyLine _ = False;
   
  stripWS :: String -> String;
  stripWS = stripLeadingWS . reverse . stripLeadingWS . reverse;
   
  stripLeadingWS :: String -> String;
  stripLeadingWS = dropWhile isSpace;
   
  data E b a = Ok a
             | Bad b;
   
  instance Monad (E b) where
          { m >>= k
              = case m of
                    { Ok a -> k a;
                      Bad b -> Bad b};
            return a = Ok a};
   
  failE :: b -> E b a;
  failE b = Bad b;
   
  maybeE :: b -> Maybe a -> E b a;
  maybeE _ (Just a) = return a;
  maybeE b (Nothing) = failE b;
   
  commaSep :: String -> [String];
  commaSep s = go (dropWhile isSpace s)
    where { go "" = [];
            go s
              = word :
                  case rest of
                      { ',' : rest -> go rest;
                        _ -> go rest}
              where { (word, rest) = break (== ',') s}};
  hPutStrCrLf h s
    = hPutStr h s >> hPutChar h '\r' >> hPutChar h '\n';
   
  formatTimeSensibly :: CalendarTime -> String;
  formatTimeSensibly time
    = formatCalendarTime defaultTimeLocale "%a, %d %b %Y %H:%M:%S GMT"
        time;
   
  epochTimeToClockTime :: EpochTime -> ClockTime;
  epochTimeToClockTime epoch_time
    = TOD (fromIntegral $ fromEnum epoch_time) 0;
   
  timeout :: Int -> IO a -> IO a -> IO a;
  timeout secs action on_timeout
    = do { threadid <- myThreadId;
           timeout <- forkIOIgnoreExceptions
                        (do { threadDelay (secs * 1000000);
                              throwTo threadid (ErrorCall "__timeout")});
           (do { result <- action;
                 killThread timeout;
                 return result})
             `Exception.catch`
             (\ exception ->
                case exception of
                    { ErrorCall "__timeout" -> on_timeout;
                      _other
                        -> do { killThread timeout;
                                throw exception}})};

  forkIOIgnoreExceptions :: IO () -> IO ThreadId;
--  forkIOIgnoreExceptions action = IO $ \ s -> 
--    case (fork# action s) of (# s1, id #) -> (# s1, ThreadId id #)   

  accept :: Socket -> IO (Handle, SockAddr);
  accept sock
    = do { (sock', addr) <- SocketPrim.accept sock;
           handle <- socketToHandle sock' ReadWriteMode;
           return (handle, addr)};
   
  statFile :: String -> IO (Maybe FileStatus);
  statFile filename
    = do { maybe_stat <- tryJust ioErrors (getFileStatus filename);
           case maybe_stat of
               { Left e
                   -> do { errno <- getErrno;
                           if errno == eNOENT then return Nothing else ioError e};
                 Right stat -> return (Just stat)}}}
