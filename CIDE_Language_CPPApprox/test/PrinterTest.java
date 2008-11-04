import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cide.gast.ISourceFile;

@Ignore
public class PrinterTest extends CPPApproxParserTester {

	private boolean foundDefine;
	private ISourceFile r;

	@Before
	public void setUp() throws Exception {
		tester = new CPPApproxParserTester();
	}
	@Test
	public void testFullBerkeleyDB4420() throws Exception {
		File file = new File("test/berkeleydb4420/btree/bt_compact.c");
		ISourceFile result = tester.runParser(file);
		System.out.println(result.render());
	}

	
}
