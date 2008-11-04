import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Assert;
import tmp.generated_cppapprox.CPPApproxParser;
import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;

public class CPPApproxParserTester {
	protected CPPApproxParserTester tester;

	public ISourceFile runParser(File f) throws FileNotFoundException,
			ParseException {
		CPPApproxParser parser = new CPPApproxParser(new OffsetCharStream(
				new FileReader(f)));
		ISourceFile root = parser.getRoot();
		checkASTLength(root);
		return root;
	}

	public ISourceFile runParser(String fileContent) throws ParseException {
		CPPApproxParser parser = getParser(fileContent);
		ISourceFile root = parser.getRoot();
		checkASTLength(root);
		return root;
	}

	private void checkASTLength(IASTNode root) {
		root.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				Assert.assertTrue("Node " + node + " has a length < 0", node
						.getLength() >= 0);
				return super.visit(node);
			}
		});
	}

	protected String printAST(ISourceFile ast) {
		return ast.render();
	}

	protected ISourceFile parseAndCheck(String content) throws ParseException {
		ISourceFile parseResult = tester.runParser(content);

		String pp = printAST(parseResult);

		assertWhiteSpaceEqual(content, pp);

		return parseResult;
	}

	protected void assertWhiteSpaceEqual(String a, String b) {
		String ashort = removeWhiteSpace(a);
		String bshort = removeWhiteSpace(b);
		Assert.assertEquals(b + "\n\nexpected:\n" + a, ashort, bshort);
	}

	private String removeWhiteSpace(String a) {
		return a.replaceAll("[\\ ,\\t,\\n,\\r]", "");
	}

	/**
	 * Fetch the entire contents of a text file, and return it in a String. This
	 * style of implementation does not throw Exceptions to the caller.
	 * 
	 * @param aFile
	 *            is a file which already exists and can be read.
	 */
	static public String getContents(File aFile) {
		// ...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		// declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			input = new BufferedReader(new FileReader(aFile));
			String line = null; // not declared within while loop
			/*
			 * readLine is a bit quirky : it returns the content of a line MINUS
			 * the newline. it returns null only for the END of the stream. it
			 * returns an empty String if two newlines appear in a row.
			 */
			while ((line = input.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

	protected ISourceFile parseAndCheckFile(String filename)
			throws FileNotFoundException, ParseException {
		File cfile = new File("test/" + filename);
		ISourceFile parseResult = tester.runParser(cfile);

		String filecontent = getContents(cfile);
		String pp = printAST(parseResult);

		assertWhiteSpaceEqual(filecontent, pp);

		System.out.println();
		System.out.println("----------");
		System.out.println(filename);
		System.out.println(pp);

		return parseResult;
	}

	protected ISourceFile parseAndCheckInFunction(String string)
			throws ParseException {
		return parseAndCheck("int main(){" + string + "}");
	}

	private boolean foundInner, foundOuter;

	protected void assertASTNesting(ISourceFile ast, final Class outer,
			final Class inner) {
		foundInner = foundOuter = false;
		ast.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (foundOuter && node.getClass().isAssignableFrom(inner))
					foundInner = true;
				if (node.getClass().isAssignableFrom(outer))
					foundOuter = true;
				return super.visit(node);
			}
		});
		Assert.assertTrue("outer token not found", foundOuter);
		Assert.assertTrue("inner token not found", foundInner);
	}

	protected void assertASTContains(ISourceFile ast, final Class target) {
		foundOuter = false;
		ast.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node.getClass().isAssignableFrom(target))
					foundOuter = true;
				return super.visit(node);
			}
		});
		Assert.assertTrue("token type not found", foundOuter);
	}

	protected void assertASTNotContains(ISourceFile ast, final Class target) {
		foundOuter = false;
		ast.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node.getClass().isAssignableFrom(target))
					foundOuter = true;
				return super.visit(node);
			}
		});
		Assert.assertFalse("token type should not be found", foundOuter);
	}

	protected CPPApproxParser getParser(String content) {
		return new CPPApproxParser(new OffsetCharStream(
				new ByteArrayInputStream(content.getBytes())));
	}

	protected void parseFilesInFolder(File folder, boolean recursive,
			boolean includeHeader) throws Exception {
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				if (file.isHidden()) {
					System.out.println("skipping " + file);
				} else {
					String name = file.getName();
					int idx = name.lastIndexOf('.');
					if (idx > 0
							&& (name.substring(idx).equals(".c")
									|| (includeHeader && name.substring(idx)
											.equals(".cpp")) || (includeHeader && name
									.substring(idx).equals(".h")))) {
						try {
							System.out.println(file);
							tester.runParser(file);
						} catch (ParseException e) {
							System.out.println("Attempted to parse " + file);
							System.out.flush();
							e.printStackTrace();
							throw e;
						}
					}
				}
			}
			if (recursive && file.isDirectory())
				parseFilesInFolder(file, recursive, includeHeader);
		}
	}
}
