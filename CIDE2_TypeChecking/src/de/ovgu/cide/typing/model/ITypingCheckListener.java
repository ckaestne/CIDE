package de.ovgu.cide.typing.model;

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
	
	 */
	void changedTypingChecks(TypeCheckChangeEvent event);

}
