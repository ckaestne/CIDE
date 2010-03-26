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

import cide.gast.IASTNode;
import de.ovgu.cide.features.source.ColoredSourceFile;

public class ASTColorChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<IASTNode> nodes;

	private final ColoredSourceFile sourceFile;

	public ASTColorChangedEvent(Object source, IASTNode node, ColoredSourceFile sourceFile) {
		super(source);
		this.nodes = Collections.singleton(node);
		this.sourceFile=sourceFile;
	}

	public ASTColorChangedEvent(Object source, Collection<IASTNode> nodes, ColoredSourceFile sourceFile) {
		super(source);
		assert nodes!=null && !nodes.isEmpty();
		this.nodes = nodes;
		this.sourceFile=sourceFile;
	}

	public Collection<IASTNode> getAffectedNodes() {
		return nodes;
	}
	
	public ColoredSourceFile getColoredSourceFile(){
		return sourceFile;
	}

}
