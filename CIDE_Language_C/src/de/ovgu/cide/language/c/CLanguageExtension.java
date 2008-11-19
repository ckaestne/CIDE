package de.ovgu.cide.language.c;

import java.io.InputStream;

import tmp.generated_c.CParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class CLanguageExtension implements ILanguageExtension {

	private final static String[] EXTENSIONS = new String[] { ".c", ".h" };

	public String[] getFileExtensions() {
		return EXTENSIONS;
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new CParser(new OffsetCharStream(inputStream))
						.TranslationUnit();
			}
		};
	}

	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.c";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

}
