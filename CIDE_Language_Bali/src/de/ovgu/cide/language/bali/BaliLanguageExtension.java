package de.ovgu.cide.language.bali;

import java.io.InputStream;

import tmp.generated_bali.BaliParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class BaliLanguageExtension implements ILanguageExtension {

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new BaliParser(new OffsetCharStream(inputStream))
						.getRoot();
			}
		};

	}

	public static final String LANGUAGE_EXTENSION_ID="de.ovgu.cide.language.bali";
	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}


}
