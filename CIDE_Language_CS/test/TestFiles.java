import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import tmp.generated_cs.CSParser;
import tmp.generated_cs.compilation_unit;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;


public class TestFiles {

//	@Before
//	public void setUp() throws Exception {
//	}

	@Test
	public void testTest() throws FileNotFoundException, ParseException{
		CSParser p = new CSParser(new OffsetCharStream(new FileReader("test/test.cs")));
		compilation_unit u = p.compilation_unit();
		System.out.println(u.render());
}
	@Test
	public void testHello() throws FileNotFoundException, ParseException{
		CSParser p = new CSParser(new OffsetCharStream(new FileReader("test/hello.cs")));
		compilation_unit u = p.compilation_unit();
		System.out.println(u.render());
	}
	@Test
	public void testHelloEq() throws FileNotFoundException, ParseException{
		CSParser p = new CSParser(new OffsetCharStream(new FileReader("test/helloEq.cs")));
		compilation_unit u = p.compilation_unit();
		System.out.println(u.render());
	}
}
