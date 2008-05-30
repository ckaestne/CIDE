import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import tmp.generated_xhtml.XhtmlParser;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;


public class XhtmlTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void parseTestFile() throws FileNotFoundException, ParseException{
		
		XhtmlParser p = new XhtmlParser(new OffsetCharStream(new FileInputStream("test/about.de.xhtml")));
		p.Document();
	}

}
