package de.ovgu.cide.language.fj;

import java.io.InputStream;

import tmp.generated_fj.*;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;

public class FJLanguageExtension implements ILanguageExtension {

	private final static String[] EXTENSIONS = new String[] { ".c" ,".h"};

	public String[] getFileExtensions() {
		return EXTENSIONS;
	}

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new FJParser(new OffsetCharStream(inputStream)).Goal();
			}
		};
	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		return new SimplePrintVisitor();
	}

	public ILanguageValidator getValidator() {
//		new AstgenTask
		return null;
	}
}
