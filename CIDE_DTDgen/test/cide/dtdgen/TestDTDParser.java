package cide.dtdgen;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import tmp.generated_dtd.DTDParser;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class TestDTDParser {

	@Test
	public void dtdTestFile() throws FileNotFoundException, ParseException {
		DTDParser p = new DTDParser(new OffsetCharStream(new FileInputStream(
				"test/people.dtd")));
		p.DTD();
	}

}
