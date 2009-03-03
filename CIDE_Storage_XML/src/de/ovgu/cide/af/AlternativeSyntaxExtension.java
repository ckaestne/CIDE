package de.ovgu.cide.af;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.typing.model.ITypingExtension;
import de.ovgu.cide.typing.model.ITypingProvider;

/**
 * @author Malte Rosenthal
 */
public class AlternativeSyntaxExtension implements ITypingExtension {

	@Override
	public ITypingProvider createTypingProvider(IProject project) {
		return new AlternativeSyntaxProvider(project);
	}
}
