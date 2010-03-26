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
