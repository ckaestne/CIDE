import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tmp.generated_gcide.SimplePrintVisitor;
import tmp.generated_gcide.SlimJJParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import de.ovgu.cide.language.gcide.GCIDEPrinter;

public class Test {
	public static void main(String[] args) throws FileNotFoundException,
			ParseException {
		ISourceFile r = new SlimJJParser(new OffsetCharStream(
				new FileInputStream("src/tmp/gcide.sjj"))).Grammar();
		SimplePrintVisitor v = new GCIDEPrinter();
		r.accept(v);
		System.out.println(v.getResult());
	}
}
