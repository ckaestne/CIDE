package de.ovgu.cide.language.python;

import java.io.InputStream;

import tmp.generated_python.PythonParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class PythonLanguageExtension implements ILanguageExtension {

	public PythonLanguageExtension() {
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new PythonParser(new OffsetCharStream(inputStream)).file_input();
			}
		};
	}

	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.python";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

}
