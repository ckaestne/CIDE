package de.ovgu.cide.language.simple;

import java.io.InputStream;

import tmp.generated_simple.JavaParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class SimpleLanguageExtension implements ILanguageExtension {

	private final static String[] EXTENSIONS = new String[] { ".lang" };

	public String[] getFileExtensions() {
		return EXTENSIONS;
	}

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new JavaParser(new OffsetCharStream(inputStream))
						.CompilationUnit();
			}
		};
	}

//	public ILanguagePrintVisitor getPrettyPrinter() {
//		return new SimplePrintVisitor();
//	}
//
//	public ILanguageValidator getValidator() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
