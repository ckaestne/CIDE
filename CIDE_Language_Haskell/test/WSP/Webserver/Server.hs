module Server (main) where
{ import MimeTypes;
  import Request;
  import Response;
  import ErrorLogger;
  import AccessLogger;
  import ConfigParser;
  import Config hiding (listen);
  import Global;
  import Util;
  import System;
  import Network.URI;
  import System.Posix;
  import Network.BSD;
  import IO hiding (bracket);
  import Control.Exception as Exception;
  import List;
  import Maybe;
  import Monad;
  import System.IO.Unsafe (unsafePerformIO);
  import Data.IORef (IORef, newIORef, readIORef, writeIORef);
  import Control.Concurrent;
  import Network.Socket hiding (accept);
  import System.Console.GetOpt;
  import Dispatch;
  import RunCGI;
  import Deploy;
  import InitialDeployments;
   
  data CmdLineOpt = O_ConfigFile String
                  | O_ServerRoot String;
  options
    = [Option ['f'] ["config"] (ReqArg O_ConfigFile "filename")
         ("default: " ++ show defaultConfigFile),
       Option ['d'] ["server-root"] (ReqArg O_ServerRoot "directory")
         ("default: " ++ show defaultServerRoot)];
  usage = "usage: hws [option...]";
   
  cmdline :: IORef [CmdLineOpt];
  cmdline = unsafePerformIO (newIORef (error "no flags"));
  defaultConfigFile = "conf/httpd.conf";
  defaultServerRoot = "/etc/httpd";
  serverRoot
    = unsafePerformIO $
        do { args <- readIORef cmdline;
             case [s | O_ServerRoot s <- args] of
                 { [] -> return defaultServerRoot;
                   (s : _) -> return s}};
  configFile
    = unsafePerformIO $
        do { args <- readIORef cmdline;
             case [s | O_ConfigFile s <- args] of
                 { [] -> return defaultConfigFile;
                   (s : _) -> return s}};
  configPath
    = case configFile of
          { '.' : '/' : _ -> configFile;
            '/' : _ -> configFile;
            _ -> serverRoot ++ '/' : configFile};
  main
    = do { args <- getArgs;
           case getOpt Permute options args of
               { (flags, [], [])
                   -> do { writeIORef cmdline flags;
                           main2};
                 (_, _, errs)
                   -> do { hPutStr stderr (concat errs);
                           hPutStr stderr (usageInfo usage options)}}};
  main2
    = do { main_thread <- myThreadId;
           installHandler sigPIPE Ignore Nothing;
           installHandler sigHUP (Catch (hupHandler main_thread)) Nothing;
           block $ readConfig};
   
  hupHandler :: ThreadId -> IO ();
  hupHandler main_thread
    = throwTo main_thread (ErrorCall "**restart**");
  sigsToBlock = addSignal sigHUP emptySignalSet;
  readConfig
    = do { blockSignals sigsToBlock;
           r <- parseConfig configPath;
           case r of
               { Left err
                   -> do { hPutStrLn stderr ("failed to parse " ++ configPath);
                           hPutStr stderr (show err)};
                 Right b
                   -> do { let { conf = b defaultConfig};
                           initMimeTypes (typesConfig conf);
                           startErrorLogger conf;
                           startAccessLogger conf;
                           InitialDeployments.init;
                           my_hostent <- do { ent <- getHostEntry;
                                              case serverName conf of
                                                  { "" -> return ent;
                                                    n -> return ent{hostName = n}}};
                           putMVar local_hostent my_hostent;
                           topServer conf}}};
  topServer conf
    = Exception.catch
        (do { unblockSignals sigsToBlock;
              unblock $ do { server conf}})
        (\ e ->
           case e of
               { ErrorCall "**restart**"
                   -> do { takeMVar local_hostent;
                           stopAccessLogger;
                           stopErrorLogger;
                           readConfig};
                 IOException io
                   -> do { logError ("server: " ++ showIOError io);
                           topServer conf};
                 _other
                   -> do { logError ("server: " ++ show e);
                           topServer conf}});
  server conf
    = do { proto <- getProtocolNumber "tcp";
           Exception.bracket (socket AF_INET Stream proto)
             (\ sock -> sClose sock)
             (\ sock ->
                do { setSocketOption sock ReuseAddr 1;
                     bindSocket sock
                       (SockAddrInet (fromIntegral (port conf)) iNADDR_ANY);
                     listen sock maxListenQueue;
                     acceptConnections conf sock})};
  acceptConnections conf sock
    = do { (h, SockAddrInet port haddr) <- accept sock;
           forkIO
             ((talk conf h haddr `finally` (hClose h)) `Exception.catch`
                (\ e -> trace ("servlet died: " ++ show e) (return ())));
           acceptConnections conf sock};
  talk conf h haddr
    = do { hSetBuffering h LineBuffering;
           run conf True h haddr};
  run conf first h haddr
    = do { let { time_allowed
                   | first = requestTimeout conf
                   | otherwise = keepAliveTimeout conf};
           req <- catchJust ioErrors
                    (timeout time_allowed
                       (do { r <- getRequest h;
                             return (Just r)})
                       (do { (if first then response conf h (requestTimeOutResponse conf)
                                else return ());
                             return Nothing}))
                    (\ e@(IOException io) ->
                       if isEOFError e then trace "EOF from client" $ return Nothing else
                         do { logError ("request: " ++ showIOError io);
                              return Nothing});
           case req of
               { Nothing -> return ();
                 Just r
                   -> trace "Got request" $
                        do { mapM_ (hPutStrLn stderr) r;
                             case parseRequest r of
                                 { Bad resp
                                     -> do { trace ("Bad : " ++ show (resp conf)) $
                                               do { response conf h (resp conf)};
                                             return ()};
                                   Ok req
                                     -> do { resp <- request haddr conf req;
                                             trace ("Ok : " ++ show resp) $
                                               do { logAccess req resp haddr (error "noTimeDiff")};
                                             response conf h resp;
                                             let { connection_headers
                                                     = getConnection (reqHeaders req)};
                                             if
                                               ConnectionClose `elem` connection_headers ||
                                                 (reqHTTPVer req < http1_1 && ConnectionKeepAlive
                                                    `notElem` connection_headers)
                                               then return () else run conf False h haddr}}}}};
   
  getRequest :: Handle -> IO [String];
  getRequest h
    = do { l <- hGetLine h;
           if (emptyLine l) then getRequest h else getRequest' l h};
  getRequest' l h
    = do { if (emptyLine l) then return [] else
             do { l' <- hGetLine h;
                  ls <- getRequest' l' h;
                  return (l : ls)}};
   
  request :: HostAddress -> Config -> Request -> IO Response;
  request haddr conf req@Request{reqCmd = cmd}
    = (trace "request" $
         case checkHostHeader conf req of
             { Just response
                 -> trace "checkHostHeader failed\n" $ return (response conf);
               Nothing
                 -> case cmd of
                        { GetReq -> doGetPost haddr conf req False;
                          HeadReq -> doGetPost haddr conf req True;
                          PostReq -> doGetPost haddr conf req False;
                          _ -> return (notImplementedResponse conf)}})
        `Exception.catch`
        (\ exception ->
           do { logError ("request: " ++ show exception);
                return (internalServerErrorResponse conf)});
  checkHostHeader conf
    Request{reqHTTPVer = ver, reqHeaders = headers}
    = case getHost headers of
          { [] | ver < http1_1 -> Nothing;
            [host]
              | host == serverName conf || host `elem` serverAlias conf ->
                Nothing
              | otherwise -> Just notFoundResponse;
            _ -> Just badRequestResponse};
  doGetPost haddr conf
    Request{reqURI = uri, reqHeaders = headers, reqCmd = cmd} is_head
    = trace ("doGetPost: " ++ show uri) $
        case uri of
            { NoURI -> return (badRequestResponse conf);
              AuthorityURI _ -> return (badRequestResponse conf);
              AbsPath path -> getPath haddr conf cmd path is_head headers;
              AbsURI uri -> getURI haddr conf cmd uri is_head headers};
  getURI haddr conf cmd uri is_head headers
    = let { scheme = uriScheme uri;
            mauth = uriAuthority uri;
            path = unEscapeString $ uriPath uri;
            query = unEscapeString $ uriQuery uri;
            frag = unEscapeString $ uriFragment uri}
        in
        case scriptAlias conf of
            { Just (scriptPrefix, mappedPath)
                | scheme == "http:" && scriptPrefix `isPrefixOf` path ->
                  getScript scriptPrefix haddr conf path [query]
                    (requestCmdString cmd)
                    is_head
                    headers;
              _ -> if
                     (scheme /= "http:") ||
                       (case mauth of
                            { Nothing -> True;
                              Just auth -> uriRegName auth /= host})
                       || (query /= "")
                       || (frag /= "")
                       || cmd
                       == PostReq
                     then return (notFoundResponse conf) else
                     getFile conf path is_head headers};
  getPath haddr conf cmd path is_head headers
    = let { reqString = requestCmdString cmd;
            args = []}
        in
        do { dispatched <- runDispatch conf path;
             case dispatched of
                 { Nothing | cmd == PostReq -> return (notFoundResponse conf);
                   Nothing -> getFile conf path is_head headers;
                   Just (pathinfo, options, pgm)
                     -> do { host <- inet_ntoa haddr;
                             return
                               (okResponse conf
                                  (RunMe
                                     (\ h ->
                                        runCGI h host conf path pathinfo args reqString is_head
                                          headers
                                          options
                                          pgm))
                                  []
                                  (not is_head))}}};
  getFile conf path is_head headers
    = trace ("getFile " ++ path) $
        do { m_path <- prependDocRoot conf path;
             case m_path of
                 { Left r -> return r;
                   Right path
                     -> do { check <- findRealFilename conf path headers;
                             trace
                               ("reallyGetFile " ++ show (either (Left . id) (Right . fst) check))
                               $
                               case check of
                                   { Left r -> return r;
                                     Right (filename, stat)
                                       -> do { access <- fileAccess filename True False False;
                                               case access of
                                                   { False -> return (notFoundResponse conf);
                                                     True
                                                       -> do { let { content_type
                                                                       = case mimeTypeOf filename of
                                                                             { Nothing
                                                                                 -> contentTypeHeader
                                                                                      (show
                                                                                         (defaultType
                                                                                            conf));
                                                                               Just t
                                                                                 -> contentTypeHeader
                                                                                      (show t)}};
                                                               let { last_modified
                                                                       = lastModifiedHeader
                                                                           (epochTimeToClockTime
                                                                              (modificationTime
                                                                                 stat))};
                                                               let { size
                                                                       = toInteger (fileSize stat)};
                                                               trace
                                                                 ("getFile " ++
                                                                    show (mimeTypeOf filename))
                                                                 $
                                                                 return
                                                                   (okResponse conf
                                                                      (FileBody size filename)
                                                                      [content_type, last_modified]
                                                                      (not is_head))}}}}}}};
   
  prependDocRoot :: Config -> String -> IO (Either Response String);
  prependDocRoot conf ('/' : '~' : userpath)
    | not (null (userDir conf)) =
      do { let { (user, path) = break (== '/') userpath};
           u_ent <- tryJust ioErrors (getUserEntryForName user);
           case u_ent of
               { Left _ -> return (Left (notFoundResponse conf));
                 Right ent
                   -> return
                        (Right (homeDirectory ent ++ '/' : userDir conf ++ path))}};
  prependDocRoot conf path@('/' : _)
    = do { return (Right (documentRoot conf ++ path))};
  prependDocRoot conf _path = return (Left (notFoundResponse conf));
   
  findRealFilename ::
                     Config ->
                       String ->
                         [RequestHeader] -> IO (Either Response (String, FileStatus));
  findRealFilename conf filename headers
    = do { stat <- statFile filename;
           case stat of
               { Nothing -> tryMultiViews conf filename headers;
                 Just stat
                   | isDirectory stat ->
                     do { let { index_filename = filename ++ '/' : directoryIndex conf};
                          stat <- statFile index_filename;
                          case stat of
                              { Nothing -> return (Left (notFoundResponse conf));
                                Just stat -> return (Right (index_filename, stat))}}
                   | isRegularFile stat -> return (Right (filename, stat))
                   | otherwise -> return (Left (notFoundResponse conf))}};
  tryMultiViews conf filename hdrs
    = let { acceptableLanguages
              = (unTangle $ getAcceptLanguage hdrs) ++ languagePriority conf}
        in g acceptableLanguages
    where { g [] = return (Left (notFoundResponse conf));
            g (code : codes)
              = case lookup code (addLanguage conf) of
                    { Nothing -> g codes;
                      Just ext
                        -> let { newFilename = filename ++ ext} in
                             do { mstat <- statFile newFilename;
                                  case mstat of
                                      { Nothing
                                          -> case break (== '-') code of
                                                 { (_, "") -> g codes;
                                                   (codeprefix, _) -> g (codeprefix : codes)};
                                        Just stat -> return (Right (newFilename, stat))}}};
            unTangle [] = [];
            unTangle ("" : langs) = unTangle langs;
            unTangle (lang : langs)
              = case break (== ',') lang of
                    { (code, "") -> code : unTangle langs;
                      (code, _ : rest) -> code : unTangle (rest : langs)}};
  getHost hdrs = [h | Host h p <- hdrs];
  getConnection hdrs = [c | Connection cs <- hdrs, c <- cs];
  getAcceptLanguage hdrs = [al | AcceptLanguage al <- hdrs]}
