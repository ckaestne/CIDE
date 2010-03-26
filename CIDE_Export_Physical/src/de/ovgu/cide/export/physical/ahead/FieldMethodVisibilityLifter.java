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

package de.ovgu.cide.export.physical.ahead;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FieldMethodVisibilityLifter extends ASTVisitor {

	private CompilationUnit compUnit;

	FieldMethodVisibilityLifter(CompilationUnit compUnit) {
		this.compUnit = compUnit;
	}

	private boolean visitName(Name node) {
		IBinding binding = node.resolveBinding();
		if (binding == null)
			return true;

		ASTNode declaration = compUnit.findDeclaringNode(binding);
		if (declaration instanceof MethodDeclaration) {
			makeProtected((MethodDeclaration) declaration);
		} else if (declaration instanceof VariableDeclarationFragment) {
			VariableDeclarationFragment variableFragment = (VariableDeclarationFragment) declaration;

			if (variableFragment.getParent() instanceof FieldDeclaration) {
				FieldDeclaration fieldDecl = (FieldDeclaration) variableFragment
						.getParent();
				makeProtected(fieldDecl);
			}
		}

		return false;
	}

	private void makeProtected(BodyDeclaration bodyDecl) {
		List<Modifier> modifierList = bodyDecl.modifiers();
		for (Iterator<Modifier> iter = modifierList.iterator(); iter.hasNext();) {
			Modifier modifier = iter.next();
			if (modifier.isPrivate()) {
				iter.remove();
				Modifier protectedModifier = bodyDecl.getAST().newModifier(
						Modifier.ModifierKeyword.PROTECTED_KEYWORD);
				modifierList.add(protectedModifier);
			}
		}

	}

	@Override
	public boolean visit(QualifiedName node) {

		return visitName(node);
	}

	public boolean visit(SimpleName node) {
		return visitName(node);
	}

}
