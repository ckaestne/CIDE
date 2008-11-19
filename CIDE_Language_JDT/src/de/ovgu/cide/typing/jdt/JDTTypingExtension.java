package de.ovgu.cide.typing.jdt;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.typing.model.ITypingExtension;
import de.ovgu.cide.typing.model.ITypingProvider;

public class JDTTypingExtension implements ITypingExtension {

	
	public ITypingProvider createTypingProvider(IProject project) {
		return new JDTTypingProvider(project);
	}

}
