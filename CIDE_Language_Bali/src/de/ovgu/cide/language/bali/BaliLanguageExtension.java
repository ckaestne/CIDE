package de.ovgu.cide.language.bali;

import java.io.InputStream;

import tmp.generated_bali.BaliParser;
import tmp.generated_bali.SimplePrintVisitor;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;

public class BaliLanguageExtension implements ILanguageExtension {

	public BaliLanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(final InputStream inputStream) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new BaliParser(new OffsetCharStream(inputStream))
						.getRoot();
			}
		};

	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		return new SimplePrintVisitor();
	}

	public ILanguageValidator getValidator() {
//		return new BaliValidator();
		return null;
	}

}
