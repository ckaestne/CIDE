package de.ovgu.cide.language.manifest;

import java.io.InputStream;

import tmp.generated_manifest.ManifestParser;

import cide.gast.ISourceFile;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class ManifestExtension implements ILanguageExtension {

	
	public static final String LANGUAGE_EXTENSION_ID = "de.ovgu.cide.language.manifest";

	public String getId() {
		return LANGUAGE_EXTENSION_ID;
	}

	public ILanguageParser getParser(final InputStream inputStream,
			String filePath) {
		return new ILanguageParser() {
			public ISourceFile getRoot() throws ParseException {
				return new ManifestParser(new OffsetCharStream(inputStream))
						.File();
			}
		};
	}

}
