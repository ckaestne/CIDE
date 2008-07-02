module ErrorLogger
       (startErrorLogger, stopErrorLogger, logError, catchAndLogError,
        showIOError)
       where
{ import Config;
  import Util;
  import Time;
  import IO;
  import System.IO.Unsafe;
  import Control.Concurrent;
  import Control.Exception as Exception;
  import GHC.IOBase (IOException(..));
   
  logError :: String -> IO ();
  logError err = writeChan error_log_chan err;
   
  error_log_chan :: Chan String;
  error_log_chan = unsafePerformIO (newChan);
   
  error_log_pid :: MVar ThreadId;
  error_log_pid = unsafePerformIO (newEmptyMVar);
   
  startErrorLogger :: Config -> IO ();
  startErrorLogger conf
    = do { logError
             ("error logger started (level " ++ show (logLevel conf) ++ ") on '"
                ++ errorLogFile conf
                ++ "'");
           t <- forkIO
                  (Exception.catch (run_error_logger conf) (error_handler conf));
           putMVar error_log_pid t};
   
  stopErrorLogger :: IO ();
  stopErrorLogger
    = do { t <- takeMVar error_log_pid;
           throwTo t (ErrorCall "**stop**")};
  error_handler conf (ErrorCall "**stop**")
    = logError ("error logger stopped");
  error_handler conf exception
    = do { logError ("error logger died: " ++ show exception);
           Exception.catch (run_error_logger conf) (error_handler conf)};
  run_error_logger conf
    = do { Exception.bracket (openFile (errorLogFile conf) AppendMode)
             (\ hdl -> hClose hdl)
             (\ hdl -> doErrLogRequests hdl)};
  doErrLogRequests hdl
    = do { str <- readChan error_log_chan;
           clock_time <- getClockTime;
           let { time_str = formatTimeSensibly (toUTCTime clock_time)};
           hPutStr hdl time_str;
           hPutStrLn hdl ("  " ++ str);
           hFlush hdl;
           doErrLogRequests hdl};
   
  catchAndLogError :: String -> IO a -> (Exception -> IO a) -> IO a;
  catchAndLogError str io handler
    = Exception.catch io
        (\ e -> logError (str ++ show e) >> handler e);
   
  showIOError :: IOException -> String;
  showIOError (IOError _hdl iot loc s m_filename)
    = (showString loc . showString ": " . shows iot . showString " (" .
         showString s
         . showString ") in ["
         . shows m_filename
         . showChar ']')
        ""}
