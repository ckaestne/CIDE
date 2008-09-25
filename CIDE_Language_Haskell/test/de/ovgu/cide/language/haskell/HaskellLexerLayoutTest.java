package de.ovgu.cide.language.haskell;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import cide.gparser.OffsetCharStream;
import cide.gparser.Token;

public class HaskellLexerLayoutTest {

	@Test
	public void testLayoutTokens() {

		HaskellLexer lexer;

		lexer = createLexerLvl2("where abc");
		Assert.assertEquals("{1}where{7} abc", lexer.debugSerialize());

		lexer = createLexerLvl2("\twhere abc");
		Assert.assertEquals("{9}\twhere{15} abc", lexer.debugSerialize());

		lexer = createLexerLvl2("do abc");
		Assert.assertEquals("{1}do{4} abc", lexer.debugSerialize());
		lexer = createLexerLvl2("of abc");
		Assert.assertEquals("{1}of{4} abc", lexer.debugSerialize());
		lexer = createLexerLvl2("let abc");
		Assert.assertEquals("{1}let{5} abc", lexer.debugSerialize());
	}

	@Test
	public void testLayoutTokensMultiline() {

		HaskellLexer lexer;

		lexer = createLexerLvl2(" a\nb\nc");
		Assert.assertEquals("{2} a<1>\\nb<1>\\nc", lexer.debugSerialize());

		lexer = createLexerLvl2(" a\n b\nc");
		Assert.assertEquals("{2} a<2>\\n b<1>\\nc", lexer.debugSerialize());

		lexer = createLexerLvl2("  f = (\"Hello \\\n		          \\Bill\", \"Jake\")");
		Assert.assertEquals(
				"{3}  f = (\"Hello \\\\n		          \\Bill\", \"Jake\")", lexer
						.debugSerialize());

		String t = " f x = let\n          h y = let\n   p z = z\n                in p\n       in h";
		lexer = createLexerLvl2(t);
		Assert
				.assertEquals(
						"{2} f x = let{11}\\n          h y = let{4}\\n   p z = z<17>\\n                in p<8>\\n       in h",
						lexer.debugSerialize());

	}

	@Test
	public void testTokenPosition() {
		HaskellLexer lexer = createLexerLvl3("module where \n  a \n");
		// result: module where{ \\n a} \\n

		lexer.getNextToken();
		Token whereToken = lexer.getNextToken();
		Token leftCurlyToken = lexer.getNextToken();
		Token aToken = lexer.getNextToken();
		Token rightCurlyToken = lexer.getNextToken();

		Assert.assertTrue(whereToken.offset < leftCurlyToken.offset);
		Assert.assertTrue(leftCurlyToken.offset < aToken.offset);
		Assert.assertTrue(aToken.offset < rightCurlyToken.offset);
		Assert.assertEquals(2, rightCurlyToken.beginLine);

	}
	
	@Test
	public void testSemicolonAndEOF(){
		HaskellLexer lexer = createLexerLvl3("module where \na\nb");
		//expected result: module where{ \\n a} \\n
		System.out.println(lexer.debugPrintTokens());
		Assert.assertEquals("module where{ \\na;\\nb}", lexer.debugSerialize());
		
		Token moduleToken = lexer.getNextToken();
		Token whereToken = lexer.getNextToken();
		Token leftCurlyToken = lexer.getNextToken();
		Token aToken = lexer.getNextToken();
		Token semiToken = lexer.getNextToken();
		Token bToken = lexer.getNextToken();
		Token rightCurlyToken = lexer.getNextToken();
		Token eof=lexer.getNextToken();
		Assert.assertEquals(HaskellLexer.SEMICOLON,semiToken.kind);
		Assert.assertEquals(HaskellLexer.RIGHT_CURLY,rightCurlyToken.kind);
		Assert.assertEquals(HaskellLexer.EOF,eof.kind);
	}
	
	/**
	 * no semicolon before a closing curly bracket
	 */
	@Test
	public void testSemicolonAndEOF2(){
		HaskellLexer lexer = createLexerLvl3("module where \na\nb\n");
		//expected result: module where{ \\n a} \\n
		System.out.println(lexer.debugPrintTokens());
		Assert.assertEquals("module where{ \\na;\\nb}\\n", lexer.debugSerialize());
		
		Token moduleToken = lexer.getNextToken();
		Token whereToken = lexer.getNextToken();
		Token leftCurlyToken = lexer.getNextToken();
		Token aToken = lexer.getNextToken();
		Token semiToken = lexer.getNextToken();
		Token bToken = lexer.getNextToken();
		Token rightCurlyToken = lexer.getNextToken();
		Token eof=lexer.getNextToken();
		Assert.assertEquals(HaskellLexer.SEMICOLON,semiToken.kind);
		Assert.assertEquals(HaskellLexer.RIGHT_CURLY,rightCurlyToken.kind);
		Assert.assertEquals(HaskellLexer.EOF,eof.kind);
	}
	
	/**
	 * semicolon even after comment
	 */
	@Test
	public void testSemicolonAndEOF3(){
		HaskellLexer lexer = createLexerLvl3("module where \na-- xy\nb\n");
		//expected result: module where{ \\n a} \\n
		System.out.println(lexer.debugPrintTokens());
		Assert.assertEquals("module where{ \\na;-- xy\\nb}\\n", lexer.debugSerialize());
		
		Token moduleToken = lexer.getNextToken();
		Token whereToken = lexer.getNextToken();
		Token leftCurlyToken = lexer.getNextToken();
		Token aToken = lexer.getNextToken();
		Token semiToken = lexer.getNextToken();
		Token bToken = lexer.getNextToken();
		Token rightCurlyToken = lexer.getNextToken();
		Token eof=lexer.getNextToken();
		Assert.assertEquals(HaskellLexer.SEMICOLON,semiToken.kind);
		Assert.assertEquals(HaskellLexer.RIGHT_CURLY,rightCurlyToken.kind);
		Assert.assertEquals(HaskellLexer.EOF,eof.kind);
	}

	@Test
	public void testTabIndent() {
		Assert.assertEquals(3, HaskellLexer.calcTabIndent("abc"));
		Assert.assertEquals(8 + 3, HaskellLexer.calcTabIndent("\tabc"));
		Assert.assertEquals(8 + 3, HaskellLexer.calcTabIndent("aa\tabc"));
		Assert.assertEquals(8 + 3, HaskellLexer.calcTabIndent("1234567\tabc"));
		Assert.assertEquals(8 + 8 + 3, HaskellLexer
				.calcTabIndent("12345678\tabc"));
		Assert.assertEquals(8 + 8 + 2, HaskellLexer.calcTabIndent("aa\ta\tbc"));
	}

	private HaskellLexer createLexerLvl2(String code) {
		return createLexer(code, 2);
	}

	private HaskellLexer createLexerLvl3(String code) {
		return createLexer(code, 3);
	}

	private HaskellLexer createLexer(String code, int lvl) {
		ByteArrayInputStream a = new ByteArrayInputStream(code.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		HaskellLexer lexer = null;
		lexer = new HaskellLexer(new OffsetCharStream(b), lvl);

		return lexer;
	}

	@Test
	public void testRewriter() {
		HaskellLexer lexer;

		lexer = createLexerLvl3("module {} ");
		Assert.assertEquals("module {}", lexer.debugSerialize().trim());

		lexer = createLexerLvl3("module where \n  a \n");
		Assert.assertEquals("module where{ \\n  a} \\n", lexer.debugSerialize()
				.trim());
	}

	@Test
	@Ignore
	public void testRewriterAdvanced() {
		HaskellLexer lexer;

		lexer = createLexerLvl3("module let x = e; y = x in e'");
		Assert.assertEquals("module let{ x = e; y = x} in e'", lexer
				.debugSerialize().trim());

		lexer = createLexerLvl3("module (x, case s of r -> i r where i x = x)");
		//Assert.assertEquals("module (x, case s of{ r -> i r where{ i x = x}})"
		// , lexer.debugSerialize().trim());
		System.out.println(lexer.debugSerialize());

		String t = " f x = let\n          h y = let\n   p z = z\n                in p\n       in h";
		lexer = createLexerLvl3(t);
		System.out.println(lexer.debugSerialize());

		t = "		module AStack( Stack, push, pop, top, size ) where\n"
				+ "		data Stack a = Empty \n"
				+ "		             | MkStack a (Stack a)\n"
				+ "\n"
				+ "		push :: a -> Stack a -> Stack a\n"
				+ "		push x s = MkStack x s\n"
				+ "\n"
				+ "		size :: Stack a -> Int\n"
				+ "		size s = length (stkToLst s)  where\n"
				+ "		           stkToLst  Empty         = []\n"
				+ "		           stkToLst (MkStack x s)  = x:xs where xs = stkToLst s\n"
				+ "\n"
				+ "		pop :: Stack a -> (a, Stack a)\n"
				+ "		pop (MkStack x s)\n"
				+ "		  = (x, case s of r -> i r where i x = x) -- (pop Empty) is an error\n"
				+ "\n"
				+ "		top :: Stack a -> a\n"
				+ "		top (MkStack x s) = x                     -- (top Empty) is an error\n";
		lexer = createLexerLvl3(t);
		System.out.println(lexer.debugSerialize());

	}

}
