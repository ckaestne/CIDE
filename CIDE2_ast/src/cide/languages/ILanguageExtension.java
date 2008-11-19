package cide.languages;

import java.io.InputStream;

public interface ILanguageExtension {
	/**
	 * returns a parser for the specific language for the given input stream
	 * 
	 * @param inputStream
	 *            input for the parser
	 * @param filePath
	 *            full path to the file, in the OS format, to be used in some
	 *            extensions when more context information is required (e.g. the
	 *            current eclipse project)
	 * @return the parser itself for the input stream
	 */
	ILanguageParser getParser(InputStream inputStream, String filePath);

	/**
	 * returns the ID of the language extension
	 * 
	 * @return
	 */
	String getId();

	// /**
	// * returns the language validator. the validator is an interface that
	// * provides all required information about validating the specific
	// language,
	// * including a resolver for references.
	// *
	// * @deprecated currently deactivated, waiting for major refactoring
	// */
	// ILanguageValidator getValidator();
}
