package de.ovgu.cide.language.haskell;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import tmp.generated_haskell.HaskellParser;
import cide.gparser.OffsetCharStream;

public class ParserPatternTestWithLexer extends ParserPatternTest {
	protected HaskellParser newParser(String string) {
		ByteArrayInputStream a = new ByteArrayInputStream(string.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		HaskellParser p = new HaskellParser(new HaskellLexer(new OffsetCharStream(b),1));
		return p;
	}

}
