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

package de.ovgu.cide;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;

import org.eclipse.core.resources.IContainer;

public class FileColorChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<IContainer> resources;

	public FileColorChangedEvent(Object source, IContainer folder) {
		super(source);
		this.resources = Collections.singleton(folder);
	}

	public FileColorChangedEvent(Object source, Collection<IContainer> folders) {
		super(source);
		assert folders != null && !folders.isEmpty();
		this.resources = folders;
	}

	public Collection<IContainer> getAffectedFolders() {
		return resources;
	}

}
