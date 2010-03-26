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

package de.ovgu.cide.typing.jdt.checks.resolutions;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclaration;


public class ASTBindingFinder extends ASTVisitor {

	ASTBindingFinder(String bindingKey) {
		this.target = bindingKey;
	}

	private ASTNode result = null;

	private final String target;

	public boolean visit(TypeDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(MethodDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(ImportDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(PackageDeclaration node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}

	public boolean visit(TypeParameter node) {
		if (result != null)
			return false;
		IBinding binding = node.resolveBinding();
		if (binding != null && binding.getKey().equals(target))
			result = node;
		return super.visit(node);
	}
	public void preVisit(ASTNode node) {
		if (node instanceof Type) {
			if (result != null)
				return;
			IBinding binding = ((Type) node).resolveBinding();
			if (binding != null && binding.getKey().equals(target))
				result = node;
		}
		if (node instanceof VariableDeclaration) {
			if (result != null)
				return;
			IBinding binding = ((VariableDeclaration) node).resolveBinding();
			if (binding != null && binding.getKey().equals(target))
				result = node;
		}
		if (node instanceof AbstractTypeDeclaration) {
			if (result != null)
				return;
			IBinding binding = ((AbstractTypeDeclaration) node).resolveBinding();
			if (binding != null && binding.getKey().equals(target))
				result = node;
		}
		super.preVisit(node);
	}

	public ASTNode getResult() {
		return result;
	}

}
