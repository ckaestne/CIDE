package de.ovgu.cide.language.haskell;

import java.io.InputStream;

import tmp.generated_haskell.HaskellParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguageValidator;

public class HaskellLanguageExtension implements ILanguageExtension {

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		return new ILanguageParser(){

			public ISourceFile getRoot() throws ParseException {
				return new HaskellParser(new HaskellLexer(new OffsetCharStream(inputStream))).module();
			}};
	}

	public ILanguageValidator getValidator() {
		return null;
	}

}
