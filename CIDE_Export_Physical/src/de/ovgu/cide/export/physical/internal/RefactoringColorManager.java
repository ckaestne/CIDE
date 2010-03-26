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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import de.ovgu.cide.configuration.jdt.ASTColorInheritance;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.language.jdt.AstidWrapper;

/**
 * this colormanager replaces the traditional colormanager during refactoring.
 * 
 * this colormanager works on the identity of AST objects instead on IDs, to the
 * colors remain when moving elements!
 * 
 * all changes to this colormanager are not permanent.
 * 
 * the colormanager is initialized with another colormanager and the root of the
 * AST at hand to copy all values
 * 
 * @author cKaestner
 * 
 */
public class RefactoringColorManager {

	private final HashMap<ASTNode, Set<IFeature>> storage = new HashMap<ASTNode, Set<IFeature>>();
	private Set<IFeature> fileColors;

	public RefactoringColorManager(SourceFileColorManager colorManager,
			ASTNode root, IFile file) {
		initialize(colorManager, root, file);
	}

	private void initialize(final SourceFileColorManager oldColorManager,
			ASTNode root, IFile file) {
		root.accept(new ASTVisitor() {
			@Override
			public void preVisit(ASTNode node) {
				Set<IFeature> colors = oldColorManager
						.getOwnColors(new AstidWrapper(node));
				if (!colors.isEmpty())
					storage.put(node, colors);
				super.preVisit(node);
			}
		});
		fileColors = oldColorManager.getDirectoryColorManager().getColors(file);
	}

	public Set<IFeature> getOwnColors(ASTNode node) {
		Set<IFeature> c = storage.get(node);
		if (c != null)
			return c;
		return new HashSet<IFeature>();
	}

	public boolean hasColors() {
		return storage.size() > 0;
	}

	public void setColors(ASTNode node, Set<IFeature> newColors) {
		if (newColors != null && !newColors.isEmpty())
			storage.put(node, newColors);
		else
			storage.remove(node);

	}

	public Set<IFeature> getColors(ASTNode node) {
		Set<IFeature> result = new HashSet<IFeature>();
		result.addAll(getOwnColors(node));
		result.addAll(getInheritedColors(node));
		return Collections.unmodifiableSet(result);
	}

	public Set<IFeature> getInheritedColors(ASTNode node) {
		Set<IFeature> result = new HashSet<IFeature>();

		ASTNode parent = node.getParent();
		if (parent != null) {
			if (ASTColorInheritance.inheritsColors(parent, node))
				result.addAll(getOwnColors(parent));
			result.addAll(getInheritedColors(parent));
		}
		result.addAll(fileColors);

		return Collections.unmodifiableSet(result);
	}

	public void addColors(ASTNode node, Set<IFeature> colors) {
		Set<IFeature> c = new HashSet<IFeature>();
		c.addAll(getColors(node));
		c.addAll(colors);
		setColors(node, c);
	}
}
