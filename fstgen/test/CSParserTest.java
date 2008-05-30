import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

import tmp.generated_csharp.CSharpParser;
import tmp.generated_java15.Java15Parser;

import de.ovgu.cide.fstgen.ast.FSTNonTerminal;

public class CSParserTest {
	@Test
	public void runParser() throws FileNotFoundException, ParseException {
		CSharpParser p= new CSharpParser(new OffsetCharStream( new FileInputStream("test/testfiles/Test.cs")));
		p.using_directive(false);
		System.out.println(p.getRoot().printFST(0));
	}
}
