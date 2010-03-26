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

package de.ovgu.cide.language.jdt.editor.inlineprojection;

import java.util.Set;

import org.eclipse.jface.text.Position;

import de.ovgu.cide.features.IFeature;



public class ColoredInlineProjectionAnnotation extends
		InlineProjectionAnnotation {

	private Set<IFeature> colors;

	private Position position;

	public void setColors(Set<IFeature> colors) {
		this.colors = colors;
	}

	public boolean adjustCollapsing(Set<IFeature> selectedColors) {
		boolean expanded = selectedColors.containsAll(colors);
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

	public void setPosition(Position pos) {
		this.position = pos;
	}

	public Position getPosition() {
		return position;
	}

	public Set<IFeature> getColors() {
		return colors;
	}

}
