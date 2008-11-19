package de.ovgu.cide.typing.model;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import de.ovgu.cide.features.source.ColoredSourceFile;

/**
 * typing providers are created on a per project basis. it calculates all the
 * checks that need to be performed by CIDE. it informs CIDE when new checks are
 * necessary and CIDE informs it when files need to be rechecked.
 * 
 * types that cannot be resolved (e.g. a method invocation that has no target in
 * any variant) are not handled here but reported directly by the language.
 * CIDE's typing only considers additional checks on top of existing typing of
 * that language.
 * 
 * @author ckaestne
 * 
 */
public interface ITypingProvider {

	IProject getProject();

	/**
	 * gets all concrete typing checks (every pair of call and references
	 * including evaluation strategy/rules and error message)
	 * 
	 * checks are only available after a first update call
	 * 
	 * @return
	 */
	Set<ITypingCheck> getChecks();

	/**
	 * listner to inform about new and obsolete typing checks (batched). a
	 * change in the current checks should invoke CIDE to evaluate the new
	 * conditions or remove obsolete error messages
	 * 
	 * @param listener
	 */
	void addTypingCheckListener(ITypingCheckListener listener);

	void removeTypingCheckListener(ITypingCheckListener listener);

	/**
	 * informs the typing provider that one or more files have changed. this
	 * operation is blocking and should be run from a thread/job
	 */
	void updateFile(Collection<ColoredSourceFile> files);

	/**
	 * the entire typechecking for the project should be redone. this operation
	 * is blocking and should be run from a thread/job
	 */
	void updateAll();
}
