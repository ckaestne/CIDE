/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

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
