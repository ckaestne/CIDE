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

package de.ovgu.cide.export.physical.internal;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.AstidWrapper;
import de.ovgu.cide.language.jdt.AstidWrapperWithParents;

public class FeatureFinderVisitor extends ASTVisitor {
	private ColoredSourceFile sourceFile;

	public final Set<Set<IFeature>> seenColors = new HashSet<Set<IFeature>>();

	public FeatureFinderVisitor(ColoredSourceFile sourceFile) {
		this.sourceFile = sourceFile;
	}

	@Override
	public void preVisit(ASTNode node) {
		Set<IFeature> colors = sourceFile.getColorManager().getColors(
				new AstidWrapperWithParents(node));
		this.seenColors.add(colors);
		super.preVisit(node);
	}

}
