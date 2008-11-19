package de.ovgu.cide.typing.model;

import java.util.Collection;

public class TypeCheckChangeEvent {
	private final ITypingProvider provider;
	private final Collection<ITypingCheck> added;
	private final Collection<ITypingCheck> removed;

	public TypeCheckChangeEvent(ITypingProvider provider,
			Collection<ITypingCheck> added, Collection<ITypingCheck> removed) {
		this.provider = provider;
		this.added = added;
		this.removed = removed;
	}

	/**
	 * provider that has changed
	 */
	public ITypingProvider getProvider() {
		return provider;
	}

	/**
	 * new typing checks or null if there are no new checks
	 */
	public Collection<ITypingCheck> getAddedChecks() {
		return added;
	}

	/**
	 * obsolete typing checks or null if none have been removed
	 */
	public Collection<ITypingCheck> getObsoleteChecks() {
		return removed;
	}
}
