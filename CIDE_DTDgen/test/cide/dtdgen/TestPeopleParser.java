package cide.dtdgen;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import tmp.generated_people.Document;
import tmp.generated_people.PeopleParser;
import tmp.generated_people.SimplePrintVisitor;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class TestPeopleParser {

	@Test
	public void dtdTestFile() throws FileNotFoundException, ParseException,
			ParseException {
		PeopleParser p = new PeopleParser(
				new OffsetCharStream(new FileInputStream("test/people.xml")));
		Document d = p.Document();
		
		d.accept(new SimplePrintVisitor(System.out));
	}

}
