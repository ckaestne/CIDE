import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tmp.generated_xml.XMLParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class XMLTest {
	public static void main(String[] args) throws FileNotFoundException,
			ParseException{
//		XMLParserTokenManager tm = new XMLParserTokenManager(new SimpleCharStream(new FileInputStream("src/tmp/test.xml")));
//		Token t;int max=100;
//		while ((t=tm.getNextToken())!=null && --max>0){
//			System.out.println(t.image+" - "+tm.tokenImage[t.kind]);
//		}
//		new XMLParser(
//				new FileInputStream("src/tmp/test.xml")).Document();
		
		ISourceFile r = new XMLParser(new OffsetCharStream(
				new FileInputStream("test/test.xml"))).Document();
		System.out.println(r.render());

	}
}
