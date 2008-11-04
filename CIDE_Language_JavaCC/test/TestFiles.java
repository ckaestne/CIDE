import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import tmp.generated_javacc.JavaCCParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.languages.ILanguageParser;
import de.ovgu.cide.language.javacc.JavaCCLanguageExtension;

public class TestFiles {

	private JavaCCParser parse(String string) {
		ByteArrayInputStream a = new ByteArrayInputStream(string.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		JavaCCParser p = new JavaCCParser(new OffsetCharStream(b));
		return p;
	}

	// @Test
	// public void testTest() throws FileNotFoundException, ParseException {
	// HaskellParser p = new HaskellParser(new OffsetCharStream(
	// new FileReader("test/fromviral/Pic.hs")));
	// module u = p.module();
	// SimplePrintVisitor v;
	// u.accept(v = new SimplePrintVisitor());
	// System.out.println(v.getResult());
	// }

	@Test
	public void testArminsExamples() throws Throwable {
		parseFilesInFolder(new File("test"), true);
	}

	private void parseFilesInFolder(File folder, boolean recursive)
			throws Throwable {
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				if (file.isHidden()) {
					System.out.println("skipping " + file);
				} else {
					String name = file.getName();
					int idx = name.lastIndexOf('.');
					if (idx > 0 && (name.substring(idx).equals(".jj")/*
																		 * ||
																		 * name.substring(
																		 * idx).equals(".h")
																		 */)) {
						try {
							// System.out.println("parsing " + file);
							ILanguageParser p = new JavaCCLanguageExtension()
									.getParser(new FileInputStream(file),file.getAbsolutePath());
							ISourceFile m = p.getRoot();
							
							System.out.println(m.render());
						} catch (Throwable e) {
							System.out.println("Attempted to parse " + file);
							System.out.flush();
							e.printStackTrace();
							throw e;
						}
					}
				}
			}
			if (recursive && file.isDirectory())
				parseFilesInFolder(file, recursive);
		}
	}

}
