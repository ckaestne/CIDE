package de.ovgu.cide.language.fj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import tmp.generated_fj.FJParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ExtendedLanguageExtension;
import cide.languages.ILanguageParser;

public class FJLanguageExtension extends ExtendedLanguageExtension<FJParser> {

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

	@Override
	protected FJParser getInternalParser(String code) {
		if (code == null)
			return null;
		return new FJParser(new ByteArrayInputStream(code.getBytes()));
	}
}
