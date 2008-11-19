package de.ovgu.cide.language.gcide;

import java.io.InputStream;

import tmp.generated_gcide.SlimJJParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class GCIDELanguageExtension implements ILanguageExtension {

	public GCIDELanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new SlimJJParser(new OffsetCharStream(inputStream))
						.Grammar();
			}
		};
	}

	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.gcide";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

}
