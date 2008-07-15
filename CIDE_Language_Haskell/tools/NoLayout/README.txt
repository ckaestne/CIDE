Zwei Möglichkeiten der Anwendung von NoLayout:

1. Ausführung im GHCI
NoLayout.hs in den GHCI laden (z.B. Shell-Kommando "ghci NoLayout.hs" oder im GHCI-Prompt ":l NoLayout.hs") und das Programm mit ":main EingabeDatei.hs AusgabeDatei.hs" ausführen

2. Kompilieren in eine ausführbare Datei und dann Ausführen durch Shell-Kommando "nolayout EingabeDatei.hs AusgabeDatei.hs"
2.1 Kompilieren mit make
2.2 Manuelles Kompilieren mit dem Befehl "ghc -o nolayout NoLayout.hs -package haskell-src -main-is NoLayout.main"
