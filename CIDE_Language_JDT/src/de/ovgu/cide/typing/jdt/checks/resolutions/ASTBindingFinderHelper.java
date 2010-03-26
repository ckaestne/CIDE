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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

import cide.gast.IASTNode;
import cide.gparser.ParseException;
import de.ovgu.cide.language.jdt.ASTBridge;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

/**
 * to resolve a binding, we need to parse (JDT) the target file and find the
 * according node, then convert it back to an IASTNode
 */
public class ASTBindingFinderHelper {

	public static IASTNode getFieldDecl(IBinding binding) {

		CompilationUnit ast = getAST(binding);
		if (ast == null)
			return null;

		ASTBindingFinder bindingFinder = new ASTBindingFinder(binding.getKey());
		ast.accept(bindingFinder);
		ASTNode result = bindingFinder.getResult();
		if (result == null)
			return null;

		return ASTBridge.bridge(result);
	}

	public static IASTNode getMethodDecl(IMethodBinding binding) {
		CompilationUnit ast = getAST(binding);
		if (ast == null)
			return null;

		ASTBindingFinder bindingFinder = new ASTBindingFinder(binding.getKey());
		ast.accept(bindingFinder);
		ASTNode result = bindingFinder.getResult();
		if (result == null)
			return null;

		return ASTBridge.bridge(result);
	}
	public static IASTNode getTypeDecl(ITypeBinding binding) {
		CompilationUnit ast = getAST(binding);
		if (ast == null)
			return null;

		ASTBindingFinder bindingFinder = new ASTBindingFinder(binding.getKey());
		ast.accept(bindingFinder);
		ASTNode result = bindingFinder.getResult();
		if (result == null)
			return null;

		return ASTBridge.bridge(result);
	}

	private static CompilationUnit getAST(IBinding binding) {
		IJavaElement element = binding.getJavaElement();
		if (element == null)
			return null;
		IResource res = element.getResource();
		if (!(res instanceof IFile))
			return null;

		try {
			return JDTParserWrapper.parseJavaFile((IFile) res);
		} catch (ParseException e) {
			e.printStackTrace();
			// if in doubt no error
			return null;
		}

	}

}
