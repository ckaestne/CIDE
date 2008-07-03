package de.ovgu.cide.language.haskell;

import java.io.InputStream;

import tmp.generated_haskell.HaskellParser;
import tmp.generated_haskell.SimplePrintVisitor;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;

public class HaskellLanguageExtension implements ILanguageExtension {

	// TODO Christian
	//public ILanguageParser getParser(final InputStream inputStream, String filePath) {
	public ILanguageParser getParser(final InputStream inputStream) {
		return new ILanguageParser(){

			public ISourceFile getRoot() throws ParseException {
				return new HaskellParser(new OffsetCharStream(inputStream)).module();
			}};
	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		return new SimplePrintVisitor();
	}

	public ILanguageValidator getValidator() {
		return null;
	}

}
