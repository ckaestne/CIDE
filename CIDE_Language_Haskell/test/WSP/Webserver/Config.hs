module Config where
{  
  data Config = Config{user :: String, group :: String, port :: Int,
                       listen :: [Int], requestTimeout :: Int, keepAliveTimeout :: Int,
                       maxClients :: Int, serverAdmin :: String, serverName :: String,
                       serverAlias :: [String], useCanonicalName :: Bool,
                       hostnameLookups :: Bool, documentRoot :: String, userDir :: String,
                       directoryIndex :: String, accessFileName :: String,
                       indexes :: Bool, followSymLinks :: Bool,
                       scriptAlias :: Maybe (String, String), typesConfig :: String,
                       defaultType :: String, addLanguage :: [(String, String)],
                       languagePriority :: [String], accessLogFile :: String,
                       accessLogFormat :: String, errorLogFile :: String, logLevel :: Int}
              deriving Show;
   
  defaultConfig :: Config;
  defaultConfig
    = Config{user = "nobody", group = "nobody", port = 80, listen = [],
             requestTimeout = 300, keepAliveTimeout = 15, maxClients = 150,
             serverAdmin = "", serverName = "", serverAlias = [],
             useCanonicalName = False, hostnameLookups = False,
             documentRoot = "/usr/local/www/data", userDir = "",
             directoryIndex = "index.html", accessFileName = ".htaccess",
             indexes = False, followSymLinks = False, scriptAlias = Nothing,
             typesConfig = "/etc/mime.types", defaultType = "text/plain",
             addLanguage = [], languagePriority = ["en"],
             accessLogFile = "http-access.log",
             accessLogFormat =
               "%h %l %u %t \"%r\" %s %b \"%{Referer}i\" \"%{User-Agent}i\"",
             errorLogFile = "httpd-error.log", logLevel = 1};
  serverSoftware = "HWS+WASH";
  serverVersion = "0.2"}
