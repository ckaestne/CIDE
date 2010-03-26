SPL : MIDP [DITUNES] [DLOGGING] [DTEST] [DSMALLMEM] [DJSR75] DTESTUI derivatives [DCLDCV11] [DJSR238] [Compatibility] :: _SPL ;

MIDP : DMIDP20
	| DMIDP10 ;

derivatives : [notDITUNES] [notDTEST] [notDTESTUI] [notDSMALLMEM] [notDJSR75] [notDMIDP20] [notDCLDCV11] [notDLOGGING] [someCompatibility] [noCompatibility] [notDMIDP10] [compatNot23] [notDCOMPATIBILITY2] [notDCOMPATIBILITY1] :: _derivatives ;

Compatibility : [DCOMPATIBILITY1] [DCOMPATIBILITY3] [DCOMPATIBILITY2] :: _Compatibility ;

%%

notDITUNES iff not DITUNES ;
notDTEST iff not DTEST ;
notDTESTUI iff not DTESTUI ;
notDSMALLMEM iff not DSMALLMEM ;
notDJSR75 iff not DJSR75 ;
notDMIDP20 iff not DMIDP20 ;
notDCLDCV11 iff not DCLDCV11 ;
notDLOGGING iff not DLOGGING ;
someCompatibility iff DCOMPATIBILITY1 or DCOMPATIBILITY2 or DCOMPATIBILITY3 ;
noCompatibility iff not someCompatibility ;
notDMIDP10 iff not DMIDP10 ;
compatNot23 iff not (DCOMPATIBILITY2 or DCOMPATIBILITY3) ;
notDCOMPATIBILITY2 iff not DCOMPATIBILITY2 ;
notDCOMPATIBILITY1 iff not DCOMPATIBILITY1 ;
DTESTUI implies DTEST ;
DLOGGING implies DTEST ;

