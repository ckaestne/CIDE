package de.ovgu.cide.typing.bali;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.typing.model.ITypingExtension;
import de.ovgu.cide.typing.model.ITypingProvider;

public class BaliTypingExtension implements ITypingExtension {

	public ITypingProvider createTypingProvider(IProject project) {
		return new BaliTypingProvider(project);
	}

}
