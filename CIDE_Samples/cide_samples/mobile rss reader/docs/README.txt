Program files for Mobile RSS Reader with source code located at 
http://code.google.com/p/mobile-rss-reader/.

Choosing a program file
-----------------------

It is important to choose the right program file.  This is because unlike
on the desktop, choosing a program file which uses features not available
on the phone may cause the program to not run WITHOUT an error message.
Phone makers are not required to give such a message, so many do not let
the user know that why the program is not allowed to run.  So, to avoid
frustration, if you try to run one version of the program and you do not
see anything to show that it was run, try a version with less features.
There is a web site http://www.mobref.com/device/ which allows you to look
up which features are supported on most phones.  You can also install
the MIDP 1.0
version and go to settings and look at phone MIDP version and JSR 75 available
fields at the bottom.  If MIDP version is 2.0 or higher, you can use
versions beginning with midp20_.  If JSR75 available is true, you can use
versions with jsr75_ in the name.  The Itunes version are for phones with
a large amount of J2ME memory and JSR75.  You can find memory available
using settings the program and going to the bottom and looking for application
memory available and if it is above 2Mb (2024 Kb), you can use it.  If
there are phones witout JSR-75 that have this memory, submit enhancement
to
http://code.google.com/p/mobile-rss-reader/ and I will create a version
for MIDP 2.0 with Itunes..

Directories ending in _prc contain PRC for IBM WME compatable versions.  These
are not for wide use as the memory given to J2ME for PRC is small.

For older phones, programs and directories beginning with midp10_ are for
MIDP 1.0 phone versions.  These program versions will also run on other
phones, but these program
versions have less features as the language has improved since then.

midp10_@STAGE@prc/RSSReader.prc - MIDP 1.0 IBM WME PRC version
midp10_@STAGE@RSSReader.cod - MIDP 1.0 Blackberry COD
midp10_@STAGE@RSSReader.cso - MIDP 1.0 Blackberry CSO
midp10_@STAGE@RSSReader.debug - MIDP 1.0 debug
midp10_@STAGE@RSSReader.jad - MIDP 1.0 jad
midp10_@STAGE@RSSReader.jar - MIDP 1.0 jar


midp20_ programs/directories (without jsr75_) are for phones with MIDP 2.0.
This should work for all recent phones.  

midp20_@STAGE@prc/RSSReader.prc - MIDP 2.0 IBM WME PRC version
midp20_RSSReader.cod - MIDP 2.0 IBM WME PRC versiond
midp20_RSSReader.cso - MIDP 2.0 Blackberry COD
midp20_RSSReader.debug - MIDP 2.0 debug
midp20_RSSReader.jad - MIDP 2.0 jad
midp20_RSSReader.jar - MIDP 2.0 jar


midp20_jsr72_ programs .  For newer phones and usually with media (e.g. MP3)
	    capabilities, use files prefixed with
		midp20_jsr72_ if the phones have MIDP-2.0 and JSR-75 use midp20_jsr75_
		version.  To
		tell if you have a phone which supports JSR-75, you can use either
		of the other versions and go to Settings and look at phone jsr75
		if it says true, it supports jsr75 (this is different than progarm
		jsr75 which shows the version of the progam not the phone).

midp20_jsr75_@STAGE@prc/RSSReader.prc - MIDP 2.0 and JSR-75 IBM WME PRC version
midp20_jsr75_RSSReader.cod - MIDP 2.0 and JSR-75 IBM WME PRC version
midp20_jsr75_RSSReader.cso - MIDP 2.0 and JSR-75 Blackberry COD
midp20_jsr75_RSSReader.debug - MIDP 2.0 and JSR-75 debug
midp20_jsr75_RSSReader.jad - MIDP 2.0 and JSR-75 jad
midp20_jsr75_RSSReader.jar - MIDP 2.0 and JSR-75 jar

midp20_itunes_jsr72_ programs .  For usually smartphones and usually with
        media (e.g. MP3)
	    capabilities, use files prefixed with
		midp20_itunes_jsr72_ if the phones have MIDP-2.0 and JSR-75 use
		midp20_jsr75_
		version.  To
		tell if you have a phone which supports JSR-75, see above for
		phones midp20_jsr72_ programs.  Also see above for using Itunes
		versions.

midp20_itunes_jsr75_@STAGE@prc/RSSReader.prc - MIDP 2.0, JSR-75, and Itunes IBM WME PRC version
midp20_itunes_jsr75_@STAGE@RSSReader.cod - MIDP 2.0, itunes and JSR-75 IBM WME PRC version
midp20_itunes_jsr75_@STAGE@RSSReader.cso - MIDP 2.0, itunes and JSR-75 Blackberry COD
midp20_itunes_jsr75_@STAGE@RSSReader.debug - MIDP 2.0, itunes and JSR-75 debug
midp20_itunes_jsr75_@STAGE@RSSReader.jad - MIDP 2.0, itunes and JSR-75 jad
midp20_itunes_jsr75_@STAGE@RSSReader.jar - MIDP 2.0, itunes and JSR-75 jar
