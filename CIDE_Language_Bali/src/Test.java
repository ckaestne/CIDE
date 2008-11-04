import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tmp.generated_bali.BaliParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class Test {
	public static void main(String[] args) throws FileNotFoundException,
			ParseException {
		ISourceFile r = new BaliParser(new OffsetCharStream(
				new FileInputStream("test.b"))).getRoot();
		
		System.out.println(r.render());
	}
}
