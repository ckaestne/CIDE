package de.ovgu.cide.typing.model;

import org.eclipse.core.resources.IProject;

/**
 * a typing extension adds one typing provider to one specific language. used
 * only as factory to create instances of typing providers.
 * 
 * @author ckaestne
 * 
 */
public interface ITypingExtension {
	ITypingProvider createTypingProvider(IProject project);
}
