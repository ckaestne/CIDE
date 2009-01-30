import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cide.gast.ASTNode;
import cide.gast.IASTNode;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.gparser.Token;

import tmp.generated_python.PythonParser;
import tmp.generated_python.PythonParserConstants;
import tmp.generated_python.file_input;
import tmp.generated_python.if_stmt;

public class PythonParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNesting() throws ParseException {
		String code = "if (value is not None) and (value == 1):        # is tests for same object\n"
				+ "  print \"value equals 1\",\n  print \"x\"\n" + "\n" + "list1 = (1,2,3,9)";
		PythonParser parser = getParser(code);

		Token token;
		while ((token = parser.getNextToken()) != null
				&& token.kind != PythonParserConstants.EOF)
			System.out.println(token + " ["
					+ PythonParserConstants.tokenImage[token.kind] + ":"
					+ token.offset + ":" + token.length + "]");

		parser = getParser(code);
		if_stmt ast = parser.if_stmt();

		System.out.println(ast.render());
		Assert.assertTrue("AST node longer than text " + ast.getLength()
				+ " - " + code.length(), ast.getLength() <= code.length());

		// test rendering
	}

	@Test
	public void testSyntaxFile() throws Exception{
		PythonParser parser = new PythonParser(new OffsetCharStream(
				new FileReader(new File("testfiles/py_syntax.py"))));
		file_input ast = parser.file_input();
		
		String content = ast.render();
		
		FileWriter writer = new FileWriter(new File("testfiles/py_syntax.rendered"));
		writer.write(content);
		writer.close();
		
		
		PythonParser newParser = new PythonParser(new OffsetCharStream(
				new FileReader(new File("testfiles/py_syntax.rendered"))));
		newParser.file_input();
	}
	
	private PythonParser getParser(String code) {
		return new PythonParser(new OffsetCharStream(new ByteArrayInputStream(
				code.getBytes())));
	}

	@Test@Ignore
	public void testParserOnJythonFiles() throws FileNotFoundException,
			ParseException {
		File dir = new File("testfiles");
		if (!dir.isDirectory())
			fail("test directory not found");

		assertTrue("no files found", dir.listFiles().length > 0);
		for (File file : dir.listFiles())
			if (file.getName().endsWith(".py")) {
				System.out.println("parsing " + file);
				PythonParser parser = new PythonParser(new OffsetCharStream(
						new FileReader(file)));
				try {
					ensureNoOverlapping(parser.file_input());
				} catch (ParseException p) {
					fail("parsing " + file + " failed " + p);
					p.printStackTrace();
				}

			}
	}

	/**
	 * ensures that there is no overlapping between siblings. otherwise this is
	 * probably a parsing error
	 * 
	 * @param node
	 */
	private void ensureNoOverlapping(IASTNode node) {
		IASTNode lastSibling = null;
		for (IASTNode child : node.getChildren()) {
			if (lastSibling != null) {
				Assert.assertTrue("node starts before previous sibling ("
						+ child.getStartPosition()
						+ "<"
						+ (lastSibling.getStartPosition() + lastSibling
								.getLength()) + ") "
						+ lastSibling.getDisplayName() + " vs "
						+ node.getDisplayName() + "  --  " + node.render(),
						(lastSibling.getStartPosition() + lastSibling
								.getLength()) <= child.getStartPosition());
			}
			lastSibling = child;
			ensureNoOverlapping(child);
		}
	}

	@Test
	public void testRendering() {

	}

}
