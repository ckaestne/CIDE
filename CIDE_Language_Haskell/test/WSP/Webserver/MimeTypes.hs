module MimeTypes (MimeType(..), initMimeTypes, mimeTypeOf) where
{ import qualified Data.Map as Map;
  import IO;
  import System.IO.Unsafe;
  import Data.IORef;
  import Text.Regex;
  import Monad;
   
  data MimeType = MimeType String String;
   
  instance Show MimeType where
          { showsPrec _ (MimeType part1 part2)
              = showString (part1 ++ '/' : part2)};
   
  mime_types_ref :: IORef (Map.Map String MimeType);
  mime_types_ref = unsafePerformIO (newIORef Map.empty);
   
  mimeTypeOf :: String -> Maybe MimeType;
  mimeTypeOf filename
    = unsafePerformIO
        (do { mime_types <- readIORef mime_types_ref;
              let { g (base, "") = Nothing;
                    g (base, ext)
                      = Map.lookup ext mime_types `mplus` g (extension base)};
              return $ g (extension filename)});
   
  extension :: String -> (String, String);
  extension fn = go (reverse fn) ""
    where { go [] ext = (ext, "");
            go ('.' : r) ext = (reverse r, ext);
            go (x : s) ext = go s (x : ext)};
   
  initMimeTypes :: String -> IO ();
  initMimeTypes mime_types_file
    = do { h <- openFile mime_types_file ReadMode;
           stuff <- hGetContents h;
           let { mime_types = Map.fromList (parseMimeTypes stuff)};
           writeIORef mime_types_ref mime_types};
  parseMimeTypes file
    = [(ext, val) |
       Just (val, exts) <- map (parseMimeLine . takeWhile (/= '#'))
                             (lines file),
       ext <- exts];
  mimeRegex = mkRegex "^([^/]+)/([^ \t]+)[ \t]+(.*)$";
  parseMimeLine l
    = case matchRegex mimeRegex l of
          { Just (part1 : part2 : extns : _)
              -> Just (MimeType part1 part2, words extns);
            _ -> Nothing}}
