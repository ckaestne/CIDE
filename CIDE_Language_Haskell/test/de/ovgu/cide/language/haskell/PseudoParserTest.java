package de.ovgu.cide.language.haskell;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.InputStreamReader;
//
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import tmp.generated_haskell.HaskellParser;
//import tmp.generated_haskell.HaskellParserConstants;
//import tmp.generated_haskell.SimplePrintVisitor;
//import tmp.generated_haskell.block;
//import tmp.generated_haskell.module;
//import cide.gast.ASTStringNode;
//import cide.gparser.OffsetCharStream;
//import cide.gparser.ParseException;
//import cide.languages.ILanguagePrintVisitor;
//import de.ovgu.cide.language.haskell.HaskellLanguageExtension;
//
//public class PseudoParserTest {
//
//	// @Before
//	// public void setUp() throws Exception {
//	// }
//
//	@Test
//	public void testBlock() throws ParseException {
//		HaskellParser p = parse("{a b; { a;}}");
//		p.block();
//		Assert.assertEquals("did not reach end of file: " + p.getToken(1),
//				HaskellParserConstants.EOF, p.getToken(1).kind);
//
//		p = parse("{}");
//		p.block();
//		Assert.assertEquals("did not reach end of file: " + p.getToken(1),
//				HaskellParserConstants.EOF, p.getToken(1).kind);
//		p = parse("{a}");
//		p.block();
//		Assert.assertEquals("did not reach end of file: " + p.getToken(1),
//				HaskellParserConstants.EOF, p.getToken(1).kind);
//		p = parse("{a b}");
//		p.block();
//		Assert.assertEquals("did not reach end of file: " + p.getToken(1),
//				HaskellParserConstants.EOF, p.getToken(1).kind);
//
//	}
//
//	@Test
//	public void testLength() throws ParseException {
//		HaskellParser p;
//		block b;
//		ASTStringNode c;
//
//		p = parse("{}");
//		b = p.block();
//		Assert.assertEquals(2, b.getLength());
//		Assert.assertEquals(null, b.getFindblockcontent());
//
//		p = parse("{a}");
//		b = p.block();
//		Assert.assertEquals(3, b.getLength());
//		Assert.assertTrue(null != b.getFindblockcontent());
//		Assert.assertEquals(1,b.getFindblockcontent().getLength());
//
//		p = parse("{a b}");
//		b = p.block();
//		Assert.assertEquals(5, b.getLength());
//		c=b.getFindblockcontent();
//		Assert.assertTrue(null != c);
//		Assert.assertEquals(3,c.getLength());
//		
//	}
//
//	@Test
//	public void testBlockEnd() throws ParseException {
//		HaskellParser p = parse("{a b; { a;}}a;");
//		p.block();
//		Assert.assertTrue("did reach end of file: " + p.getToken(1),
//				HaskellParserConstants.EOF != p.getToken(1).kind);
//	}
//
//	@Test
//	public void testSigndecl() throws ParseException {
//		HaskellParser p = parse("abc::a}");
//		p.signdecl();
//		Assert.assertEquals("unexpected next token: " + p.getToken(1),
//				HaskellParserConstants.RIGHT_CURLY, p.getToken(1).kind);
//		p = parse("abc::a;");
//		p.signdecl();
//		Assert.assertEquals("unexpected next token: " + p.getToken(1),
//				HaskellParserConstants.SEMICOLON, p.getToken(1).kind);
//		p = parse("abc::;");
//		p.signdecl();
//		Assert.assertEquals("unexpected next token: " + p.getToken(1),
//				HaskellParserConstants.SEMICOLON, p.getToken(1).kind);
//	}
//
//	@Test
//	public void testDeriving() throws ParseException {
//		HaskellParser p = parse("deriving a;");
//		p.deriving();
//		Assert.assertEquals("unexpected next token: " + p.getToken(1),
//				HaskellParserConstants.SEMICOLON, p.getToken(1).kind);
//	}
//
//	@Test
//	public void testDerivingNested() throws ParseException {
//		HaskellParser p = parse("deriving a{b}a;");
//		p.deriving();
//		Assert.assertEquals("unexpected next token: " + p.getToken(1),
//				HaskellParserConstants.SEMICOLON, p.getToken(1).kind);
//	}
//
//	@Test
//	public void testDerivingFail() {
//		HaskellParser p = parse("deriving;");
//		try {
//			p.deriving();
//			Assert.fail();
//		} catch (ParseException e) {
//		}
//	}
//
//	// deriving
//
//	private HaskellParser parse(String string) {
//		ByteArrayInputStream a = new ByteArrayInputStream(string.getBytes());
//		InputStreamReader b = new InputStreamReader(a);
//		HaskellParser p = new HaskellParser(new OffsetCharStream(b));
//		return p;
//	}
//
//	// @Test
//	// public void testTest() throws FileNotFoundException, ParseException {
//	// HaskellParser p = new HaskellParser(new OffsetCharStream(
//	// new FileReader("test/fromviral/Pic.hs")));
//	// module u = p.module();
//	// SimplePrintVisitor v;
//	// u.accept(v = new SimplePrintVisitor());
//	// System.out.println(v.getResult());
//	// }
//
//	@Test
//	public void testArminsExamples() throws Exception {
//		parseFilesInFolder(new File("test/armin"), true);
//	}
//
//	private void parseFilesInFolder(File folder, boolean recursive)
//			throws Exception {
//		for (File file : folder.listFiles()) {
//			if (file.isFile()) {
//				if (file.isHidden()) {
//					System.out.println("skipping " + file);
//				} else {
//					String name = file.getName();
//					int idx = name.lastIndexOf('.');
//					if (idx > 0 && (name.substring(idx).equals(".hs")/*
//																		 * ||
//																		 * name.substring(
//																		 * idx).equals(".h")
//																		 */)) {
//						try {
////							System.out.println("parsing " + file);
//							HaskellParser p = new HaskellParser(
//									new OffsetCharStream(new FileReader(file)));
//							module m = p.module();
//							ILanguagePrintVisitor pp = new HaskellLanguageExtension().getPrettyPrinter();
//							m.accept(pp);
//							System.out.println(pp.getResult());
//						} catch (ParseException e) {
//							System.out.println("Attempted to parse " + file);
//							System.out.flush();
//							e.printStackTrace();
//							throw e;
//						}
//					}
//				}
//			}
//			if (recursive && file.isDirectory())
//				parseFilesInFolder(file, recursive);
//		}
//	}
//
//}
