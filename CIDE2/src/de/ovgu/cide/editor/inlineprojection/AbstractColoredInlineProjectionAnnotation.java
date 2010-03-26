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

package de.ovgu.cide.editor.inlineprojection;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.Position;

import de.ovgu.cide.features.IFeature;

public abstract class AbstractColoredInlineProjectionAnnotation extends
		InlineProjectionAnnotation {

	private final IProject project;
	private final ProjectionColorManager projectionColorManager;
	protected final Set<IFeature> colors;
	private final Position position;

	public AbstractColoredInlineProjectionAnnotation(Set<IFeature> features,
			IProject project, Position pos,
			ProjectionColorManager projectionColorManager) {
		this.colors = features;
		this.project = project;
		this.position = pos;
		this.projectionColorManager = projectionColorManager;
	}

	// public void setColors(Set<IFeature> colors) {
	// this.colors = colors;
	// }

	/**
	 * automatically fold or unfold based on the global feature selection for
	 * this view
	 * 
	 * @param visibleFeatures
	 */
	public abstract boolean adjustCollapsing(Set<IFeature> selectedColors);

	protected boolean setExpanded(boolean expanded) {
		if (!projectionColorManager.isProjectionActive())
			expanded = true;
		if (isCollapsed() && expanded) {
			this.markExpanded();
			return true;
		}
		if (!isCollapsed() && !expanded) {
			this.markCollapsed();
			return true;
		}
		return false;
	}

	public Position getPosition() {
		return position;
	}

	public Set<IFeature> getColors() {
		return colors;
	}

	public IProject getProject() {
		return project;
	}

}
