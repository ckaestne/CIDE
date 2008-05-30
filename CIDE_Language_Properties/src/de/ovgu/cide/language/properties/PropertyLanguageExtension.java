package de.ovgu.cide.language.properties;

import java.io.InputStream;

import tmp.generated_property.PropertyParser;
import tmp.generated_property.SimplePrintVisitor;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;


public class PropertyLanguageExtension implements ILanguageExtension {

	public PropertyLanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(final InputStream inputStream) {
		return new ILanguageParser() {

			public ISourceFile getRoot() throws ParseException {
				return new PropertyParser(new OffsetCharStream(inputStream))
						.Document();
			}
		};
		// return null;
	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		SimplePrintVisitor v = new SimplePrintVisitor();
		v.generateSpaces=false;
		return v; 

	}

	public ILanguageValidator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

}
