package de.ovgu.cide.language.jdt;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import cide.languages.ILanguageExtension;
import cide.languages.ILanguageParser;

public class JDTLanguageExtension implements ILanguageExtension {

	public JDTLanguageExtension() {
		// TODO Auto-generated constructor stub
	}

	public ILanguageParser getParser(InputStream inputStream,String filePath) {
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
		assert file.exists();
		
		return new JDTParserWrapper(file);
	}

	
	

}
