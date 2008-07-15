package de.ovgu.cide.language.xml;

import java.io.InputStream;

import tmp.generated_people.SimplePrintVisitor;
import tmp.generated_xhtml.XhtmlParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;

public class XHTMLLanguageExtension implements ILanguageExtension {

	public XHTMLLanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new XhtmlParser(new OffsetCharStream(inputStream))
						.Document();
			}
		};
	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		SimplePrintVisitor r = new SimplePrintVisitor();
		r.generateSpaces = false;
		return r;
	}

	public ILanguageValidator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

}
