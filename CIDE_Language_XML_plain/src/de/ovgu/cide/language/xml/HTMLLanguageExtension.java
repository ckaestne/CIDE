package de.ovgu.cide.language.xml;

import java.io.InputStream;

import tmp.generated_html.HtmlParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class HTMLLanguageExtension implements ILanguageExtension {

	public HTMLLanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(final InputStream inputStream, String filePath) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new HtmlParser(new OffsetCharStream(inputStream))
						.HtmlDocument();
			}
		};
		// return null;
	}
	
	public static final String LANGUAGE_EXTENSION_ID="de.ovgu.cide.language.html";
	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

//	public ILanguagePrintVisitor getPrettyPrinter() {
//		SimplePrintVisitor v = new SimplePrintVisitor();
//		v.generateSpaces=false;
//		return v; 
//
//	}
//
//	public ILanguageValidator getValidator() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
