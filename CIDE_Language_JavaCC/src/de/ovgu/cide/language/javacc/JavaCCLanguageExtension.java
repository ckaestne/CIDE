package de.ovgu.cide.language.javacc;

import java.io.InputStream;

import tmp.generated_javacc.JavaCCParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class JavaCCLanguageExtension implements ILanguageExtension {

	public JavaCCLanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new JavaCCParser(new OffsetCharStream(inputStream))
						.javacc_input();
			}
		};
	}

	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.javacc";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

}
