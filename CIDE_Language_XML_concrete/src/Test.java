import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Test {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory f=SAXParserFactory.newInstance();
		SAXParser p = f.newSAXParser();
		p.parse(new File("src/xhtml1-strict.dtd"), new DefaultHandler());
	}
}
