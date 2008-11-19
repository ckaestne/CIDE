package de.ovgu.cide.language.haskell;

import java.io.InputStream;

import tmp.generated_haskell.HaskellParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class HaskellLanguageExtension implements ILanguageExtension {

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new HaskellParser(new HaskellLexer(new OffsetCharStream(
						inputStream))).module();
			}
		};
	}

	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.haskell";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

}
