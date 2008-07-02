module AccessLogger
       (startAccessLogger, stopAccessLogger, logAccess) where
{ import ErrorLogger;
  import Request;
  import Response;
  import Config;
  import Global;
  import Util;
  import IO;
  import Char (toLower);
  import System.IO.Unsafe;
  import System.Time;
  import Network.Socket;
  import Network.BSD;
  import Control.Exception as Exception;
  import Control.Concurrent;
   
  data LogRequest = LogReq{log_ipaddr :: HostAddress,
                           log_logname :: String, log_request :: Request,
                           log_response :: Response, log_time :: ClockTime,
                           log_delay :: TimeDiff, log_user :: String,
                           log_server :: HostEntry};
   
  logAccess ::
              Request -> Response -> HostAddress -> TimeDiff -> IO ();
  logAccess req resp haddr delay
    = do { time <- getClockTime;
           server <- readMVar local_hostent;
           writeChan access_log_chan
             LogReq{log_ipaddr = haddr, log_logname = "<nobody>",
                    log_request = req, log_response = resp, log_time = time,
                    log_delay = delay, log_user = "<nouser>", log_server = server}}
    where {  
            access_log_chan :: Chan LogRequest};
  access_log_chan = unsafePerformIO (newChan);
   
  access_log_pid :: MVar ThreadId;
  access_log_pid = unsafePerformIO (newEmptyMVar);
   
  startAccessLogger :: Config -> IO ();
  startAccessLogger conf
    = do { logError
             ("access logger started on '" ++ accessLogFile conf ++ "'");
           t <- forkIO
                  (Exception.catch (run_access_logger conf) (error_handler conf));
           putMVar access_log_pid t};
   
  stopAccessLogger :: IO ();
  stopAccessLogger
    = do { t <- takeMVar access_log_pid;
           throwTo t (ErrorCall "**stop**")};
  error_handler conf (ErrorCall "**stop**")
    = logError ("access logger stopped");
  error_handler conf exception
    = do { logError ("access logger died: " ++ show exception);
           Exception.catch (run_access_logger conf) (error_handler conf)};
  run_access_logger conf
    = Exception.bracket (openFile (accessLogFile conf) AppendMode)
        (\ hdl -> hClose hdl)
        (\ hdl -> doLogRequests conf hdl);
  doLogRequests conf hdl
    = do { req <- readChan access_log_chan;
           ip_addr <- inet_ntoa (log_ipaddr req);
           host <- if hostnameLookups conf then
                     do { catchJust ioErrors
                            (do { ent <- getHostByAddr AF_INET (log_ipaddr req);
                                  return (Just ent)})
                            (\ _ -> return Nothing)}
                     else return Nothing;
           let { line = mkLogLine req ip_addr host (accessLogFormat conf)};
           hPutStrLn hdl line;
           hFlush hdl;
           doLogRequests conf hdl};
   
  mkLogLine ::
              LogRequest -> String -> Maybe HostEntry -> String -> String;
  mkLogLine _info _ip_addr _host "" = "";
  mkLogLine info ip_addr host ('%' : '{' : rest)
    = expand info ip_addr host (Just str) c ++
        mkLogLine info ip_addr host rest1
    where { (str, '}' : c : rest1) = span (/= '}') rest};
  mkLogLine info ip_addr host ('%' : c : rest)
    = expand info ip_addr host Nothing c ++
        mkLogLine info ip_addr host rest;
  mkLogLine info ip_addr host (c : rest)
    = c : mkLogLine info ip_addr host rest;
  expand info ip_addr host arg c
    = case c of
          { 'b' -> show (contentLength resp_body);
            'f' -> getFileName resp_body;
            'h'
              -> case host of
                     { Just ent -> hostName ent;
                       Nothing -> ip_addr};
            'a' -> ip_addr;
            'l' -> log_logname info;
            'r' -> show (log_request info);
            's' -> show resp_code;
            't' -> formatTimeSensibly (toUTCTime (log_time info));
            'T' -> timeDiffToString (log_delay info);
            'v' -> hostName (log_server info);
            'u' -> log_user info;
            'i' -> getReqHeader arg (reqHeaders (log_request info));
            _ -> ['%', c]}
    where { Response{respCode = resp_code, respHeaders = resp_headers,
                     respCoding = resp_coding, respBody = resp_body,
                     respSendBody = resp_send_body}
              = log_response info};
  getReqHeader (Nothing) _hdrs = "";
  getReqHeader (Just hdr) hdrs
    = concat
        (case map toLower hdr of
             { "accept" -> [s | Accept s <- hdrs];
               "accept-charset" -> [s | AcceptCharset s <- hdrs];
               "accept-encoding" -> [s | AcceptEncoding s <- hdrs];
               "accept-language" -> [s | AcceptLanguage s <- hdrs];
               "authorization" -> [s | Authorization s <- hdrs];
               "cachecontrol" -> [s | CacheControl s <- hdrs];
               "from" -> [s | From s <- hdrs];
               "if-match" -> [s | IfMatch s <- hdrs];
               "if-modified-since" -> [s | IfModifiedSince s <- hdrs];
               "if-none-match" -> [s | IfNoneMatch s <- hdrs];
               "if-range" -> [s | IfRange s <- hdrs];
               "if-unmodified-since" -> [s | IfUnmodifiedSince s <- hdrs];
               "max-forwards" -> [s | MaxForwards s <- hdrs];
               "proxy-authorization" -> [s | ProxyAuthorization s <- hdrs];
               "range" -> [s | Range s <- hdrs];
               "referer" -> [s | Referer s <- hdrs];
               "te" -> [s | TE s <- hdrs];
               "user-agent" -> [s | UserAgent s <- hdrs];
               _ -> []})}
