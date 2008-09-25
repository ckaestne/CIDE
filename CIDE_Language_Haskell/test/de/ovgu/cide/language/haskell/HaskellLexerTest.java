package de.ovgu.cide.language.haskell;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.Test;

import tmp.generated_haskell.HaskellParser;
import cide.gparser.OffsetCharStream;
import cide.gparser.Token;
import cide.gparser.TokenMgrError;

public class HaskellLexerTest {

	@Test
	public void lexerSetup() throws IOException {
		String string = "abc";
		ByteArrayInputStream a = new ByteArrayInputStream(string.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		new HaskellParser(new HaskellLexer(new OffsetCharStream(b),1));
	}

	@Test
	public void testSomeToken() {
		HaskellLexer lexer = createLexer("a b module c d e f x");
		System.out.println(lexer.debugPrintTokens());
		Assert.assertEquals("a", lexer.getNextToken().image);
		Assert.assertEquals("b", lexer.getNextToken().image);
		Assert.assertEquals("module", lexer.getNextToken().image);
		Assert.assertEquals("c", lexer.getNextToken().image);
		Assert.assertEquals("d", lexer.getNextToken().image);
		Assert.assertEquals("e", lexer.getNextToken().image);
		Assert.assertEquals("f", lexer.getNextToken().image);
		Assert.assertEquals("x", lexer.getNextToken().image);
	}

	@Test
	public void testFirstToken() {
		// testFirstToken("abc x", "abc", HaskellLexer.VARIABLE_ID);
		testFirstToken("module x", "module", HaskellLexer.MODULE);
		testFirstToken("where x", "where", HaskellLexer.WHERE);
		testFirstToken("import x", "import", HaskellLexer.IMPORT);
		testFirstToken("qualified x", "qualified", HaskellLexer.QUALIFIED);
		testFirstToken("deriving x", "deriving", HaskellLexer.DERIVING);
		testFirstToken("as x", "as", HaskellLexer.AS);
		testFirstToken("hiding x", "hiding", HaskellLexer.HIDING);
		testFirstToken("type x", "type", HaskellLexer.TYPE);
		testFirstToken("newtype x", "newtype", HaskellLexer.NEWTYPE);
		testFirstToken("class x", "class", HaskellLexer.CLASS);
		testFirstToken("instance x", "instance", HaskellLexer.INSTANCE);
		testFirstToken("default x", "default", HaskellLexer.DEFAULTTOKEN);
		testFirstToken("let x", "let", HaskellLexer.LET);
		testFirstToken("in x", "in", HaskellLexer.IN);
		testFirstToken("do x", "do", HaskellLexer.DO);
		testFirstToken("case x", "case", HaskellLexer.CASE);
		testFirstToken("of x", "of", HaskellLexer.OF);
		testFirstToken("if x", "if", HaskellLexer.IF);
		testFirstToken("then x", "then", HaskellLexer.THEN);
		testFirstToken("else x", "else", HaskellLexer.ELSE);
		testFirstToken("infixl x", "infixl", HaskellLexer.INFIXL);
		testFirstToken("infixr x", "infixr", HaskellLexer.INFIXR);
		testFirstToken("infix x", "infix", HaskellLexer.INFIX);
		testFirstToken("newtype x", "newtype", HaskellLexer.NEWTYPE);

		testFirstToken("=>", HaskellLexer.CONTEXT_ARROW);
		testFirstToken("=", HaskellLexer.EQUALS);
		testFirstToken("|", HaskellLexer.ALT);
		testFirstToken("::", HaskellLexer.OFTYPE);
		testFirstToken(".", HaskellLexer.OTHER1);
		testFirstToken("-", HaskellLexer.OTHER2);
		testFirstToken("@", HaskellLexer.OTHER3);
		testFirstToken("_", HaskellLexer.OTHER4);
		testFirstToken("~", HaskellLexer.OTHER5);
		testFirstToken(":", HaskellLexer.OTHER6);
		testFirstToken("->", HaskellLexer.OTHER7);
		testFirstToken("+", HaskellLexer.OTHER8);
		testFirstToken("\\", HaskellLexer.OTHER9);
		testFirstToken("<-", HaskellLexer.OTHER10);
		// testFirstToken("--", HaskellLexer.OTHER11);
		testFirstToken("..", HaskellLexer.OTHER12);

		testFirstToken("{", HaskellLexer.LEFT_CURLY);
		testFirstToken("}", HaskellLexer.RIGHT_CURLY);
		testFirstToken(";", HaskellLexer.SEMICOLON);
		testFirstToken("(#", HaskellLexer.LEFT_HPAREN);
		testFirstToken("#)", HaskellLexer.RIGHT_HPAREN);
		testFirstToken("(", HaskellLexer.LEFT_PAREN);
		testFirstToken(")", HaskellLexer.RIGHT_PAREN);
		testFirstToken("[", HaskellLexer.LEFT_BRACKET);
		testFirstToken("]", HaskellLexer.RIGHT_BRACKET);
		testFirstToken(",", HaskellLexer.COMMA);
		testFirstToken("`", HaskellLexer.INFIX_QUOTE);

	}

	@Test
	public void testSpecialTokens() {

		HaskellLexer l = createLexer("module x");
		System.out.println(l.debugPrintTokens());
		Token firstToken = l.getNextToken();
		Token secondToken = l.getNextToken();
		Assert.assertEquals("module", firstToken.image);
		Assert.assertNull(firstToken.specialToken);
		Assert.assertEquals("x", secondToken.image);
		Assert.assertNotNull(secondToken.specialToken);

		testFirstToken("--test\nmodule x", "module", HaskellLexer.MODULE);
		testSecondToken("module --test\nmodule x", "module",
				HaskellLexer.MODULE);
		testFirstToken("{-test\nx-}module x", "module", HaskellLexer.MODULE);
		testSecondToken("module {-test\nx-}module x", "module",
				HaskellLexer.MODULE);
		try {
			testFirstToken("module{-test\nx x", "module", HaskellLexer.MODULE);
			Assert.fail("lexer should fail");
		} catch (TokenMgrError e) {
		}
	}

	@Test
	public void testConstrEtc() {
		testFirstToken("A", HaskellLexer.CONSTRUCTOR_ID);
		testFirstToken("Aaaa", HaskellLexer.CONSTRUCTOR_ID);
		testFirstToken("A_", HaskellLexer.CONSTRUCTOR_ID);
		testFirstToken("A1", HaskellLexer.CONSTRUCTOR_ID);
		testFirstToken("A#a", HaskellLexer.CONSTRUCTOR_ID);
		testFirstToken("Aa'aa1", HaskellLexer.CONSTRUCTOR_ID);

		testFirstToken("a", HaskellLexer.VARIABLE_ID);
		testFirstToken("aaaa", HaskellLexer.VARIABLE_ID);
		testFirstToken("a1", HaskellLexer.VARIABLE_ID);
		testFirstToken("a#a", HaskellLexer.VARIABLE_ID);
		testFirstToken("aa'aa1", HaskellLexer.VARIABLE_ID);
		testFirstToken("a'", HaskellLexer.VARIABLE_ID);
		testFirstToken("a_", HaskellLexer.VARIABLE_ID);
		testFirstToken("_a", HaskellLexer.VARIABLE_ID);

		testFirstToken("1", HaskellLexer.INTEGER);
		testFirstToken("11111111111", HaskellLexer.INTEGER);
		testFirstToken("0o1234567", HaskellLexer.INTEGER);
		testFirstToken("0O1234567", HaskellLexer.INTEGER);
		testFirstToken("0x1ABCDEF1", HaskellLexer.INTEGER);
		testFirstToken("0X1ABCDEF1", HaskellLexer.INTEGER);

		testFirstToken("1.1", HaskellLexer.FLOAT);
		testFirstToken("1.111e3", HaskellLexer.FLOAT);
		testFirstToken("1.111e+3", HaskellLexer.FLOAT);
		testFirstToken("1.111e-3", HaskellLexer.FLOAT);

		testFirstToken("\"abc\"", HaskellLexer.STRING_LITERAL);
		testFirstToken("\"ab'c\"", HaskellLexer.STRING_LITERAL);
		testFirstToken("\"ab\\\"c\"", HaskellLexer.STRING_LITERAL);
		testFirstToken("\"ab\\nc\"", HaskellLexer.STRING_LITERAL);
		testFirstToken("\"ab\\\\\"", HaskellLexer.STRING_LITERAL);
		try {
			testFirstToken("\"abc", HaskellLexer.STRING_LITERAL);
			Assert.fail();
		} catch (TokenMgrError e) {
		}

		testFirstToken("'a'", HaskellLexer.CHARACTER_LITERAL);
		testFirstToken("'1'", HaskellLexer.CHARACTER_LITERAL);
		testFirstToken("'\\''", HaskellLexer.CHARACTER_LITERAL);
		testFirstToken("'\\n'", HaskellLexer.CHARACTER_LITERAL);
		testFirstToken("'\\0232'", HaskellLexer.CHARACTER_LITERAL);
		// try {
		// testFirstToken("'aa'", HaskellLexer.CHARACTER_LITERAL);
		// Assert.fail();
		// } catch (TokenMgrError e) {
		// }
		try {
			testFirstToken("'a", HaskellLexer.CHARACTER_LITERAL);
			Assert.fail();
		} catch (TokenMgrError e) {
		}

	}

	@Test
	public void testVarsym() {
		testFirstToken("!", HaskellLexer.VARSYM);
		testFirstToken("!:", HaskellLexer.VARSYM);
		testFirstToken("#@@@$%^*+./<=>?@\\^-~|", HaskellLexer.VARSYM);
		testFirstToken(":!", HaskellLexer.CONSYM);
		testFirstToken(":::-", HaskellLexer.CONSYM);
	}
	
	@Test
	public void testMiscRealCases() {
		HaskellLexer lexer = createLexer("putStrLn \"Simple command line interpreter. Exit with empty input.\";\n           loop}}");
		System.out.println(lexer.debugPrintTokens());
		lexer.getNextToken();
		Assert.assertEquals(HaskellLexer.STRING_LITERAL, lexer.getNextToken().kind);
	}

	protected void testFirstToken(String firstTokenImage, int tokenKind) {
		testFirstToken(firstTokenImage + " x", firstTokenImage, tokenKind);
	}

	protected void testFirstToken(String code, String firstTokenImage,
			int tokenKind) {
		HaskellLexer lexer = createLexer(code);

		Token token = lexer.getNextToken();
		System.out.println(noNL(code) + " -> " + lexer.debugPrintTokens());
		testToken(token, firstTokenImage, tokenKind);
	}

	private String noNL(String code) {
		return code.replace("\n", "\\n").replace("\r", "\\r");
	}

	protected void testSecondToken(String code, String firstTokenImage,
			int tokenKind) {
		HaskellLexer lexer = createLexer(code);

		lexer.getNextToken();
		Token token = lexer.getNextToken();
		System.out.println(noNL(code) + " -> " + lexer.debugPrintTokens());
		testToken(token, firstTokenImage, tokenKind);
	}

	protected void testToken(Token token, String tokenImage, int tokenKind) {
		Assert.assertNotNull(token);
		Assert.assertEquals(tokenImage, token.image);
		Assert.assertEquals("Kind-mismatch: expected "
				+ HaskellLexer.tokenImage[tokenKind] + " but was "
				+ HaskellLexer.tokenImage[token.kind], tokenKind, token.kind);
	}

	private HaskellLexer createLexer(String code) {
		ByteArrayInputStream a = new ByteArrayInputStream(code.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		HaskellLexer lexer = null;
		lexer = new HaskellLexer(new OffsetCharStream(b),1);

		return lexer;
	}

}
