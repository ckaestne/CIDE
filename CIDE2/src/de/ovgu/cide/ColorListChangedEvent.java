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

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.tools.featureview.ProjectionKindManager.ProjectionKind;

public class ColorListChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<Change> changes;

	private final IProject project;

	private ProjectionKind projectionKind = null;

	public ColorListChangedEvent(Object source, IProject project,
			Collection<Change> changes) {
		super(source);
		this.changes = changes;
		this.project = project;
	}

	public ColorListChangedEvent(Object source, IProject project, Change change) {
		super(source);
		this.changes = Collections.singleton(change);
		this.project = project;
	}

	public ColorListChangedEvent(Object source, IProject project,
			IFeature feature, ChangeType type) {
		super(source);
		this.changes = Collections.singleton(new Change(feature, type));
		this.project = project;
	}

	/**
	 * projectionkind changes
	 * 
	 * @param source
	 * @param projectionKind
	 */
	public ColorListChangedEvent(Object source, IProject project,
			ProjectionKind projectionKind) {
		super(source);
		this.changes = Collections.emptySet();
		this.project = project;
		this.projectionKind = projectionKind;
	}

	public Collection<Change> getChanges() {
		return changes;
	}

	public IProject getProject() {
		return project;
	}

	public boolean anyChangeOf(ChangeType type) {
		for (Change change : changes)
			if (change.type == type)
				return true;
		return false;
	}

	public ProjectionKind getNewProjectionKind() {
		return projectionKind;
	}
}
