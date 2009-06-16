Chat : Output [Logging] [Authorization] [Color] Enrcyption* [Encryption_OR] :: _Chat ;

Output : GUI
	| CMD ;

Enrcyption : Caesar
	| Reverse ;

%%
Encryption_OR iff Caesar or Reverse;