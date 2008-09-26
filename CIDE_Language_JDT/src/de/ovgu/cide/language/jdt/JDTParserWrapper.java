package de.ovgu.cide.language.jdt;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import cide.languages.ILanguageParser;

public class JDTParserWrapper implements ILanguageParser {

	private final IFile javaFile;

	public JDTParserWrapper(IFile javaFile) {
		this.javaFile = javaFile;
	}

	public ISourceFile getRoot() throws ParseException {
		CompilationUnit root = parseJavaFile(javaFile);

		return new SourceFileAdapter(new ASTBridge().getAST(root));
	}

	public static boolean isJavaFile(IFile file) {
		return getICompilationUnit(file) != null;
	}

	public static ICompilationUnit getICompilationUnit(IFile file) {
		IJavaProject javaProject = JavaCore.create(file.getProject());
		if (javaProject == null)
			return null;
		return JavaCore.createCompilationUnitFrom(file);
	}

	public static CompilationUnit parseJavaFile(IFile file)
			throws ParseException {
		ICompilationUnit compUnit = getICompilationUnit(file);
		if (compUnit == null)
			throw new ParseException("Not a java file");

		CompilationUnit root;

		ASTParser parser = ASTParser.newParser(AST.JLS3);// TODO: find
		// user-configured
		// version
		parser.setResolveBindings(false);
		parser.setSource(compUnit);
		parser.setStatementsRecovery(true);
		root = (CompilationUnit) parser.createAST(null);
		return root;
	}

}
