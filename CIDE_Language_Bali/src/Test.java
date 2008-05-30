import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import tmp.generated_bali.BaliParse;
import tmp.generated_bali.BaliParser;
import tmp.generated_bali.SimplePrintVisitor;

public class Test {
	public static void main(String[] args) throws FileNotFoundException,
			ParseException {
		ISourceFile r = new BaliParser(new OffsetCharStream(
				new FileInputStream("test.b"))).getRoot();
		SimplePrintVisitor v = new SimplePrintVisitor();
		r.accept(v);
		System.out.println(v.getResult());
	}
}
