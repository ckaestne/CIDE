package de.ovgu.cide.language.haskell;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import cide.gparser.OffsetCharStream;

public class HaskellLexerLayout {

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
				"{3}  f = (\"Hello \\\\n		          \\Bill\", \"Jake\")",
				lexer.debugSerialize());

		String t=" f x = let\n          h y = let\n   p z = z\n                in p\n       in h";		
		lexer = createLexerLvl2(t);
		Assert.assertEquals(
				"{2} f x = let{11}\\n          h y = let{4}\\n   p z = z<17>\\n                in p<8>\\n       in h",
				lexer.debugSerialize());
		
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
		Assert.assertEquals("module where{ \\n  a} \\n", lexer.debugSerialize().trim());
		
		lexer = createLexerLvl3("module let x = e; y = x in e'");
		Assert.assertEquals("module let{ x = e; y = x} in e'", lexer.debugSerialize().trim());
		

		lexer = createLexerLvl3("module (x, case s of r -> i r where i x = x)");
//		Assert.assertEquals("module (x, case s of{ r -> i r where{ i x = x}})", lexer.debugSerialize().trim());
		System.out.println(lexer.debugSerialize());
		
		String t=" f x = let\n          h y = let\n   p z = z\n                in p\n       in h";		
		lexer = createLexerLvl3(t);
		System.out.println(lexer.debugSerialize());

	t=	
"		module AStack( Stack, push, pop, top, size ) where\n"+
"		data Stack a = Empty \n"+
"		             | MkStack a (Stack a)\n"+
"\n"+
"		push :: a -> Stack a -> Stack a\n"+
"		push x s = MkStack x s\n"+
"\n"+
"		size :: Stack a -> Int\n"+
"		size s = length (stkToLst s)  where\n"+
"		           stkToLst  Empty         = []\n"+
"		           stkToLst (MkStack x s)  = x:xs where xs = stkToLst s\n"+
"\n"+
"		pop :: Stack a -> (a, Stack a)\n"+
"		pop (MkStack x s)\n"+
"		  = (x, case s of r -> i r where i x = x) -- (pop Empty) is an error\n"+
"\n"+
"		top :: Stack a -> a\n"+
"		top (MkStack x s) = x                     -- (top Empty) is an error\n";		
	lexer = createLexerLvl3(t);
	System.out.println(lexer.debugSerialize());
		
		
	}
	

}
