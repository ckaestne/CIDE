package de.ovgu.cide.language.cppapprox;

import java.io.InputStream;

import tmp.generated_cppapprox.CPPApproxParser;
import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class CPPApproxLanguageExtension implements ILanguageExtension {

	private final static String[] EXTENSIONS = new String[] { ".c", ".h" };

	public String[] getFileExtensions() {
		return EXTENSIONS;
	}

	public ILanguageParser getParser(final InputStream inputStream, String filename) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new CPPApproxParser(new OffsetCharStream(inputStream))
						.TranslationUnit();
			}
		};
	}

//	public ILanguagePrintVisitor getPrettyPrinter() {
//		return new SimplePrintVisitor() {
//			protected List<String> getNoSpaceAfterToken() {
//				List<String> l = super.getNoSpaceAfterToken();
//				if (!l.contains("#"))
//					l.add("#");
//				return l;
//			}
//		};
//	}

//	public ILanguageValidator getValidator() {
//		// new AstgenTask
//		return null;
//	}
}
