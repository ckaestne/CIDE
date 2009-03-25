package de.ovgu.cide.typing.model;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * informs about new or obsolete typechecks (deltas)
 * 
 * @author ckaestne
 * 
 */
public interface ITypingCheckListener {

	/**
	 * invoked after checks have been added/removed from the typing provider
	 * 
	 * monitor for progress report if called in a job (internally creates a submonitor with work = 1)
	 */
	void changedTypingChecks(TypeCheckChangeEvent event,
			IProgressMonitor monitor);

}
