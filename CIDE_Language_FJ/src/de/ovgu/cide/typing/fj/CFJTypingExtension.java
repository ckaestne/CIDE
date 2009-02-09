package de.ovgu.cide.typing.fj;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.typing.model.ITypingExtension;
import de.ovgu.cide.typing.model.ITypingProvider;

/**
 * @author Malte Rosenthal
 */
public class CFJTypingExtension implements ITypingExtension {

	public ITypingProvider createTypingProvider(IProject project) {
		return new CFJTypingProvider(project);
	}
}
