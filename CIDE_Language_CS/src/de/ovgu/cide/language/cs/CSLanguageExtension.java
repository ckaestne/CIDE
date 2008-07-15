package de.ovgu.cide.language.cs;

import java.io.InputStream;

import tmp.generated_cs.CSParser;
import tmp.generated_cs.SimplePrintVisitor;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;

public class CSLanguageExtension implements ILanguageExtension {

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		
		return new ILanguageParser(){

			public ISourceFile getRoot() throws ParseException {
				return new CSParser(new OffsetCharStream(inputStream)).compilation_unit();
			}};
	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		return new SimplePrintVisitor();
	}

	public ILanguageValidator getValidator() {
		return null;
	}

}
