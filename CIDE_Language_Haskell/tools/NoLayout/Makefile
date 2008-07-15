GHC=ghc

.PHONY: all
all: nolayout

nolayout: NoLayout.hs
	$(GHC) -o $@ $< -package haskell-src -main-is NoLayout.main
