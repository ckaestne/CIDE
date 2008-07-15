package coloredide.languages;

import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;
import cide.languages.ILanguagePrintVisitor;
import cide.languages.ILanguageValidator;

public class LanguageExtensionProxy implements ILanguageExtension {

	private final IConfigurationElement configElement;

	public LanguageExtensionProxy(IConfigurationElement configurationElement) {
		this.configElement = configurationElement;
		String attribute = configElement.getAttribute("fileExtensions");
		if (attribute == null)
			fileExtensions = new String[0];
		else
			fileExtensions = attribute.split(",");
		name = configElement.getAttribute("name");
		id = configElement.getAttribute("id");
	}

	private final String[] fileExtensions;
	private final String name;
	private final String id;
	private ILanguageExtension target = null;

	public String[] getFileExtensions() {
		return fileExtensions;
	}

	public ILanguageParser getParser(InputStream inputStream, String filePath) {
		if (target == null)
			loadTarget();
		return target.getParser(inputStream,filePath);
	}

	public ILanguagePrintVisitor getPrettyPrinter() {
		if (target == null)
			loadTarget();
		return target.getPrettyPrinter();
	}

	private void loadTarget() {
		try {
			target = (ILanguageExtension) configElement
					.createExecutableExtension("class");
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Language Extension: " + name + " (" + id + ") : "
				+ printFileExtensions(", ");
	}

	public String printFileExtensions(String separator) {
		if (fileExtensions.length == 0)
			return "-";
		String result = fileExtensions[0];
		for (int i = 1; i < fileExtensions.length; i++) {
			result += separator + fileExtensions[i];
		}
		return result;
	}

	public ILanguageValidator getValidator() {
		if (target == null)
			loadTarget();
		return target.getValidator();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}
