package de.ovgu.cide.language.haskell;

import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import tmp.generated_haskell.HaskellParser;
import tmp.generated_haskell.module;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.gparser.TokenMgrError;

public class TestFiles {

	@Test
	public void testArithOrig() throws Exception {
		parseFilesInFolder(new File("test/arith/orig_armin"), true, ".block");
	}

	@Test
	public void testFGL() throws Exception {
		parseFilesInFolder(new File("test/fgl-5.4.2"), true, ".block");
	}

	@Test
	public void testWSP() throws Exception {
		parseFilesInFolder(new File("test/WSP"), true, ".hs");
	}

	private void parseFilesInFolder(File folder, boolean recursive,
			String fileextension) throws Exception {
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				if (file.isHidden()) {
					System.out.println("skipping " + file);
				} else {
					String name = file.getName();
					int idx = name.lastIndexOf('.');
					if (idx > 0
							&& (name.substring(idx).equals(fileextension)/*
																		 * ||
																		 * name.
																		 * substring
																		 * (
																		 * idx)
																		 * .equals
																		 * (
																		 * ".h")
																		 */)) {
						try {
							// System.out.println("parsing " + file);
							HaskellLexer lexer = new HaskellLexer(
									new OffsetCharStream(new FileReader(file)));
							// System.out.println(lexer.debugSerialize(false));
							HaskellParser p = new HaskellParser(lexer);
							module m = p.module();
							m.render();
							// System.out.println(pp.getResult());
						} catch (ParseException e) {
							System.out.println("Attempted to parse " + file);
							System.out.flush();
							e.printStackTrace();
							throw e;
						} catch (TokenMgrError e) {
							System.out.println("Attempted to parse " + file);
							System.out.flush();
							e.printStackTrace();
							throw e;
						}
					}
				}
			}
			if (recursive && file.isDirectory())
				parseFilesInFolder(file, recursive, fileextension);
		}
	}

}
