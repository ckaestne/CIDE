import java.io.File;

import org.junit.Before;
import org.junit.Test;

import cide.gast.ISourceFile;

public class PrinterTest extends CApproxParserTester {

	private boolean foundDefine;
	private ISourceFile r;

	@Before
	public void setUp() throws Exception {
		tester = new CApproxParserTester();
	}
	@Test
	public void testFullBerkeleyDB4420() throws Exception {
		File file = new File("test/berkeleydb4420/btree/bt_compact.c");
		ISourceFile result = tester.runParser(file);
		System.out.println(result.render());
	}

	
}
