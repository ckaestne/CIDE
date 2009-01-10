package de.ovgu.cide.language.fj;

import java.io.InputStream;

import tmp.generated_fj.FJParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class FJLanguageExtension implements ILanguageExtension {

	private final static String[] EXTENSIONS = new String[] { ".fj" };

	public String[] getFileExtensions() {
		return EXTENSIONS;
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new FJParser(new OffsetCharStream(inputStream)).Goal();
			}
		};
	}

	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.fj";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

}
