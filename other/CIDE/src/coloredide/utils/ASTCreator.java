package coloredide.utils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.ColoredIDEPlugin;

public class ASTCreator {

	public boolean useStatementsRecovery = true;

	public CompilationUnit createAST(IOpenable input)
			throws JavaModelException, CoreException {
		CompilationUnit root;

		ASTParser parser = ASTParser.newParser(ColoredIDEPlugin.AST_VERSION);
		parser.setResolveBindings(true);
		if (input instanceof ICompilationUnit) {
			parser.setSource((ICompilationUnit) input);
		} else {
			parser.setSource((IClassFile) input);
		}
		parser.setStatementsRecovery(useStatementsRecovery);
		root = (CompilationUnit) parser.createAST(null);

		return root;
	}
}
