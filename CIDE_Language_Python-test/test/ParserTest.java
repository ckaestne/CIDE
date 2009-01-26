import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

import tmp.generated_python.PythonParser;

public class ParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParserOnJythonFiles() throws FileNotFoundException,
			ParseException {
		File dir = new File("testfiles");
		if (!dir.isDirectory())
			fail("test directory not found");

		assertTrue("no files found",dir.listFiles().length>0);
		for (File file : dir.listFiles())
			if (file.getName().endsWith(".py")) {
				PythonParser parser = new PythonParser(new OffsetCharStream(
						new FileReader(file)));
				try {
					parser.file_input();
					System.out.println(file+" passed");
				} catch (ParseException p) {
					fail("parsing " + file + " failed " + p);
					p.printStackTrace();
				}

			}
	}

}
