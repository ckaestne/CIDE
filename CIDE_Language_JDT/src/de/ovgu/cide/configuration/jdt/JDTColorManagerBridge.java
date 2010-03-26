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

package de.ovgu.cide.configuration.jdt;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.dom.ASTNode;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.language.jdt.ASTID;

/**
 * bride that provides access to the color manager also for JDT-AST nodes
 * 
 * has to reimplement the inheritance functionality
 * 
 **/
public class JDTColorManagerBridge {

	private final IFile file;

	public JDTColorManagerBridge(SourceFileColorManager colorManager, IFile file) {
		this.originalColorManager = colorManager;
		this.file = file;
	}

	public JDTColorManagerBridge(ColoredSourceFile source) {
		this(source.getColorManager(), source.getResource());
	}

	private SourceFileColorManager originalColorManager;

	public Set<IFeature> getColors(ASTNode node) {
		Set<IFeature> result = new HashSet<IFeature>();
		result.addAll(getOwnColors(node));
		result.addAll(getInheritedColors(node));
		return Collections.unmodifiableSet(result);
	}

	public Set<IFeature> getInheritedColors(ASTNode node) {
		return getInheritedColorsI(node, 1);
	}

	private Set<IFeature> getInheritedColorsI(ASTNode node, int i) {
		Set<IFeature> result = new HashSet<IFeature>();

		ASTNode parent = node.getParent();
		if (parent != null) {
			if (ASTColorInheritance.inheritsColors(parent, node))
				result.addAll(getOwnColors(parent));
			result.addAll(getInheritedColorsI(parent, i + 1));
		}
		result.addAll(originalColorManager.getDirectoryColorManager()
				.getColors(file));

		return Collections.unmodifiableSet(result);
	}

	public Set<IFeature> getOwnColors(ASTNode node) {
		return originalColorManager.getOwnColorsS(ASTID.id(node));
	}

}
