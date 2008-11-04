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

public class TestLayoutFiles {

	private HaskellParser parse(String string) {
		ByteArrayInputStream a = new ByteArrayInputStream(string.getBytes());
		InputStreamReader b = new InputStreamReader(a);
		HaskellLexer lexer = new HaskellLexer(new OffsetCharStream(b));
		System.out.println(lexer.debugSerialize());
		HaskellParser p = new HaskellParser(lexer);
		return p;
	}

	@Test
	public void testFGLLayout() throws Exception {
		parseFilesInFolder(new File("test/FGL-layout"), true, ".hs");
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
							System.out.println(lexer.debugSerialize(false));
							HaskellParser p = new HaskellParser(lexer);
							module m = p.module();
							System.out.println(m.render());
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
				parseFilesInFolder(file, recursive, fileextension);
		}
	}

}
