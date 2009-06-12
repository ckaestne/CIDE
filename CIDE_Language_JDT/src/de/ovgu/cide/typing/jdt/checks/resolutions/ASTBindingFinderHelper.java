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
