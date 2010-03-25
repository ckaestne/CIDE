import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

import tmp.generated_java15.Java15Parser;

import de.ovgu.cide.fstgen.ast.FSTNonTerminal;

public class JavaParserTest {
	@Test
	public void runParser() throws FileNotFoundException, ParseException {
		Java15Parser p = new Java15Parser(new OffsetCharStream( new FileInputStream("test/testfiles/Test.java")));
		p.CompilationUnit(false);
		System.out.println(p.getRoot().printFST(0));
	}
	
	@Test
	public void runParserSven1() throws FileNotFoundException, ParseException {
		Java15Parser p = new Java15Parser(new OffsetCharStream( new FileInputStream("test/testfiles/UseCaseDiagramGraph.java")));
		p.CompilationUnit(false);
	}
	@Test
	public void runParserSven2() throws FileNotFoundException, ParseException {
		Java15Parser p = new Java15Parser(new OffsetCharStream( new FileInputStream("test/testfiles/BaliParser.java")));
		p.CompilationUnit(false);
	}
	@Test
	public void runParserSven3() throws FileNotFoundException, ParseException {
		Java15Parser p = new Java15Parser(new OffsetCharStream( new FileInputStream("test/testfiles/BaliParserTokenManager.java")));
		p.CompilationUnit(false);
	}
	@Test
	public void runParserSven4() throws FileNotFoundException, ParseException {
		Java15Parser p = new Java15Parser(new OffsetCharStream( new FileInputStream("test/testfiles/or.java")));
		p.CompilationUnit(false);
		System.out.println(p.getRoot().printFST(0));
	}
}
