import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import tmp.generated_capprox.Function;
import tmp.generated_capprox.PPDefineStatement1;
import tmp.generated_capprox.PPIfDef_TopLevel;
import tmp.generated_capprox.PPIncludeStatement;
import tmp.generated_capprox.Statement;
import cide.gast.ASTNode;
import cide.gast.ASTVisitor;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import cide.languages.ILanguagePrintVisitor;
import de.ovgu.cide.language.capprox.CApproxLanguageExtension;

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
		ILanguagePrintVisitor pp = new CApproxLanguageExtension().getPrettyPrinter();
		result.accept(pp);
		System.out.println(pp.getResult());
	}

	
}
