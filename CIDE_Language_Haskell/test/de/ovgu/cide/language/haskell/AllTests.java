package de.ovgu.cide.language.haskell;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { ParserPatternTest.class, TestFiles.class,
		HaskellLexerTest.class, ParserPatternTestWithLexer.class,
		HaskellLexerLayoutTest.class, TestLayoutFiles.class })
public class AllTests {

}
