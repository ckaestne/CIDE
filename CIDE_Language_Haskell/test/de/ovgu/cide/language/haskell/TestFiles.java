package de.ovgu.cide.language.haskell;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.junit.Test;

import tmp.generated_haskell.HaskellParser;
import tmp.generated_haskell.module;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguagePrintVisitor;
import de.ovgu.cide.language.haskell.HaskellLanguageExtension;

public class TestFiles {


	private HaskellParser parse(String string) {
		ByteArrayInputStream a = new ByteArrayInputStream(string.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		HaskellParser p = new HaskellParser(new OffsetCharStream(b));
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
	public void testArminsExamples() throws Exception {
		parseFilesInFolder(new File("test/armin"), true);
	}

	private void parseFilesInFolder(File folder, boolean recursive)
			throws Exception {
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				if (file.isHidden()) {
					System.out.println("skipping " + file);
				} else {
					String name = file.getName();
					int idx = name.lastIndexOf('.');
					if (idx > 0 && (name.substring(idx).equals(".hs")/*
																		 * ||
																		 * name.substring(
																		 * idx).equals(".h")
																		 */)) {
						try {
//							System.out.println("parsing " + file);
							HaskellParser p = new HaskellParser(
									new OffsetCharStream(new FileReader(file)));
							module m = p.module();
							ILanguagePrintVisitor pp = new HaskellLanguageExtension().getPrettyPrinter();
							m.accept(pp);
							System.out.println(pp.getResult());
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
				parseFilesInFolder(file, recursive);
		}
	}

}
