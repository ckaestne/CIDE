package de.ovgu.cide.language.java;

import java.io.InputStream;

import tmp.generated_java15.Java15Parser;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class JavaLanguageExtension implements ILanguageExtension {

	private final static String[] EXTENSIONS = new String[] { ".java" };

	public String[] getFileExtensions() {
		return EXTENSIONS;
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new Java15Parser(inputStream).CompilationUnit();
			}
		};
	}

	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.java";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

}
