import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import tmp.generated_uml.UMLParser;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class UMLParserTest {
	@Test
	public void runParser() throws FileNotFoundException, ParseException {
		UMLParser p = new UMLParser(new OffsetCharStream(new FileInputStream("test/testfiles/Dialing.uml")));
		p.Document(false);
		System.out.println(p.getRoot().printFST(0));
	}
}
