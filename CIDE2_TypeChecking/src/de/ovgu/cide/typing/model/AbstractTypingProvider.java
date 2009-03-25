package de.ovgu.cide.typing.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

public abstract class AbstractTypingProvider implements ITypingProvider {

	private final List<ITypingCheckListener> listeners = new ArrayList<ITypingCheckListener>();
	private final IProject project;

	protected AbstractTypingProvider(IProject project) {
		this.project = project;
	}

	public void addTypingCheckListener(ITypingCheckListener listener) {
		listeners.add(listener);
	}

	public IProject getProject() {
		return project;
	}

	public void removeTypingCheckListener(ITypingCheckListener listener) {
		listeners.remove(listener);
	}

	protected void fireTypingCheckChanged(Collection<ITypingCheck> added,
			Collection<ITypingCheck> obsolete, IProgressMonitor monitor) {
		if (added.size() > 0 || obsolete.size() > 0)
			for (ITypingCheckListener listener : listeners)
				listener.changedTypingChecks(new TypeCheckChangeEvent(this,
						added, obsolete), monitor);
	}

}
