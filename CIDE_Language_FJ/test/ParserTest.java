import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import cide.gparser.ParseException;

public class ParserTest extends FJParserTester {
	@Before
	public void setUp() throws Exception {
		tester = new FJParserTester();
	}

	@Test
	public void testComplex() throws Exception {
		parseAndCheckFile("Complex.fj");
	}

	@Test
	public void testTestCase() throws Exception {
		parseAndCheckFile("TestCase.fj");
	}

}
