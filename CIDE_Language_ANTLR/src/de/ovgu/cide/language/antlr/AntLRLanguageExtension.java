package de.ovgu.cide.language.antlr;

import java.io.InputStream;

import tmp.generated_antlr.AntLRParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class AntLRLanguageExtension implements ILanguageExtension {

	public ILanguageParser getParser(final InputStream inputStream, String filePath) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new AntLRParser(new OffsetCharStream(inputStream))
						.Grammar();
			}
		};
		// return null;
	}
	public static final String LANGUAGE_EXTENSION_ID="de.ovgu.cide.language.antlr";
	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}


}
