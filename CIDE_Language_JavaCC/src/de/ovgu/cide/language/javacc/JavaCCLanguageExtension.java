package de.ovgu.cide.language.javacc;

import java.io.InputStream;

import tmp.generated_javacc.JavaCCParser;
import tmp.generated_javacc.SimplePrintVisitor;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;

public class JavaCCLanguageExtension implements ILanguageExtension {

	public JavaCCLanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new JavaCCParser(new OffsetCharStream(inputStream))
						.javacc_input();
			}
		};
	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		return new SimplePrintVisitor();
	}

	public ILanguageValidator getValidator() {
		return null;
	}

}
