module Request where
{ import Text.ParserCombinators.Parsec;
  import ParseToken hiding (commaSep);
  import Config;
  import Response;
  import Util;
  import Network.URI (URI, parseabsoluteURI, unEscapeString);
  import Char;
   
  data RequestCmd = OptionsReq
                  | GetReq
                  | HeadReq
                  | PostReq
                  | PutReq
                  | DeleteReq
                  | TraceReq
                  | ConnectReq
                  | ExtensionReq String
                  deriving Eq;
   
  type CResponse = Config -> Response;
  requestCmdString cmd
    = case cmd of
          { OptionsReq -> "OPTIONS";
            GetReq -> "GET";
            HeadReq -> "HEAD";
            PostReq -> "POST";
            PutReq -> "PUT";
            DeleteReq -> "DELETE";
            TraceReq -> "TRACE";
            ConnectReq -> "CONNECT"};
   
  data Request = Request{reqCmd :: RequestCmd, reqURI :: ReqURI,
                         reqHTTPVer :: HTTPVersion, reqHeaders :: [RequestHeader]};
   
  instance Show Request where
          { showsPrec _
              Request{reqCmd = cmd, reqURI = uri, reqHTTPVer = (maj, min)}
              = showString (requestCmdString cmd) . (' ' :) . shows uri . (' ' :)
                  . showString "HTTP/"
                  . shows maj
                  . showString "."
                  . shows min};
   
  type HTTPVersion = (Int, Int);
   
  http1_1, http1_0 :: HTTPVersion;
  http1_1 = (1, 1);
  http1_0 = (1, 0);
   
  data ReqURI = NoURI
              | AbsURI URI
              | AbsPath String
              | AuthorityURI String;
   
  instance Show ReqURI where
          { showsPrec _ (NoURI) = showString "<no URI>";
            showsPrec _ (AbsURI uri) = shows uri;
            showsPrec _ (AbsPath path) = showString path;
            showsPrec _ (AuthorityURI s) = showString s};
   
  data Connection = ConnectionClose
                  | ConnectionKeepAlive
                  | ConnectionOther String
                  deriving (Eq, Show);
   
  data Expect = ExpectContinue
              deriving Show;
   
  data RequestHeader = CacheControl String
                     | Connection [Connection]
                     | Date String
                     | Pragma String
                     | Trailer String
                     | TransferEncoding String
                     | Upgrade String
                     | Via String
                     | Warning String
                     | Accept String
                     | AcceptCharset String
                     | AcceptEncoding String
                     | AcceptLanguage String
                     | Authorization String
                     | Expect Expect
                     | From String
                     | Host String (Maybe Int)
                     | IfMatch String
                     | IfModifiedSince String
                     | IfNoneMatch String
                     | IfRange String
                     | IfUnmodifiedSince String
                     | MaxForwards String
                     | ProxyAuthorization String
                     | Range String
                     | Referer String
                     | TE String
                     | UserAgent String
                     | ExtensionHeader String String
                     deriving Show;
   
  parseRequest :: [String] -> E CResponse Request;
  parseRequest [] = failE badRequestResponse;
  parseRequest (request : headers)
    = case words request of
          { [cmd, uri, http_ver]
              -> do { req_cmd <- maybeE badRequestResponse (parseCmd cmd);
                      req_uri <- maybeE badRequestResponse (parseReqURI uri);
                      req_http_ver <- maybeE badRequestResponse
                                        (parseHTTPVersion http_ver);
                      req_headers <- parseHeaders headers;
                      trace (show req_headers) $
                        return (Request req_cmd req_uri req_http_ver req_headers)};
            _other -> failE badRequestResponse};
   
  parseCmd :: String -> Maybe RequestCmd;
  parseCmd "OPTIONS" = Just OptionsReq;
  parseCmd "GET" = Just GetReq;
  parseCmd "HEAD" = Just HeadReq;
  parseCmd "POST" = Just PostReq;
  parseCmd "PUT" = Just PutReq;
  parseCmd "DELETE" = Just DeleteReq;
  parseCmd "TRACE" = Just TraceReq;
  parseCmd "CONNECT" = Just ConnectReq;
  parseCmd other = Just (ExtensionReq other);
   
  parseReqURI :: String -> Maybe ReqURI;
  parseReqURI "*" = Just NoURI;
  parseReqURI (uri@('/' : _)) = Just (AbsPath (unEscapeString uri));
  parseReqURI uri
    = case parseabsoluteURI uri of
          { Nothing -> Nothing;
            Just uri -> Just (AbsURI uri)};
   
  parseHTTPVersion :: String -> Maybe (Int, Int);
  parseHTTPVersion s
    = case parse httpVersionParser "HTTP version" s of
          { Right result -> Just result;
            Left error -> Nothing};
  httpVersionParser
    = do { string "HTTP/";
           major <- int;
           char '.';
           minor <- int;
           return (major, minor)};
   
  int :: Parser Int;
  int
    = do { digits <- many1 digit;
           let { n = foldl (\ x d -> 10 * x + digitToInt d) 0 digits};
           seq n (return n)};
   
  parseHeaders :: [String] -> E CResponse [RequestHeader];
  parseHeaders hs = sequence (map parseHeader hs);
   
  parseHeader :: String -> E CResponse RequestHeader;
  parseHeader header
    = let { (header_type, val) = break (== ':') header} in
        case val of
            { ':' : val -> parseHeaderAs header_type (stripWS val);
              _ -> failE badRequestResponse};
   
  parseHeaderAs :: String -> String -> E CResponse RequestHeader;
  parseHeaderAs header_type value
    = let { normalized_header = (map toLower header_type)} in
        case normalized_header of
            { "connection" -> parseConnection value;
              "date" -> valString Date;
              "pragma" -> valString Pragma;
              "trailer" -> valString Trailer;
              "transfer-encoding" -> valString TransferEncoding;
              "upgrade" -> valString Upgrade;
              "via" -> valString Via;
              "warning" -> valString Warning;
              "accept" -> valString Accept;
              "accept-charset" -> valString AcceptCharset;
              "accept-encoding" -> valString AcceptEncoding;
              "accept-language" -> valString AcceptLanguage;
              "authorization" -> valString Authorization;
              "cache-control" -> valString CacheControl;
              "expect" -> parseExpect value;
              "from" -> valString From;
              "host" -> parseHost value;
              "if-match" -> valString IfMatch;
              "if-modified-since" -> valString IfModifiedSince;
              "if-none-match" -> valString IfNoneMatch;
              "if-range" -> valString IfRange;
              "if-unmodified-since" -> valString IfUnmodifiedSince;
              "max-forwards" -> valString MaxForwards;
              "proxy-authorization" -> valString ProxyAuthorization;
              "range" -> valString Range;
              "referer" -> valString Referer;
              "te" -> valString TE;
              "user-agent" -> valString UserAgent;
              _ -> valString (ExtensionHeader normalized_header)}
    where {  
            valString ::
                        (String -> RequestHeader) -> E CResponse RequestHeader;
            valString header_con = return (header_con value)};
   
  parseConnection :: String -> E CResponse RequestHeader;
  parseConnection s
    = return (Connection (map fn (commaSep (map toLower s))))
    where { fn "close" = ConnectionClose;
            fn "keep-alive" = ConnectionKeepAlive;
            fn other = ConnectionOther other};
   
  parseExpect :: String -> E CResponse RequestHeader;
  parseExpect s
    = case commaSep s of
          { ["100-continue"] -> return (Expect ExpectContinue);
            _ -> failE expectationFailedResponse};
   
  parseHost :: String -> E CResponse RequestHeader;
  parseHost s
    = case port of
          { "" -> return (Host host Nothing);
            ':' : port
              | all isDigit port -> return (Host host (Just (read port)));
            _ -> failE badRequestResponse}
    where { (host, port) = break (== ':') s}}
