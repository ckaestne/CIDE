package de.ovgu.cide.typing.jdt;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.model.AbstractTypingProvider;
import de.ovgu.cide.typing.model.ITypingCheck;
import de.ovgu.cide.typing.model.ITypingProvider;

public class JDTTypingProvider extends AbstractTypingProvider implements
		ITypingProvider {

	protected JDTTypingProvider(IProject project) {
		super(project);
	}

	public Set<ITypingCheck> getChecks() {
		return Collections.EMPTY_SET;
	}

	public void updateAll() {

	}

	public void updateFile(Collection<ColoredSourceFile> files) {

	}

}
