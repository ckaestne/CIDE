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

package de.ovgu.cide.language.jdt;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import cide.gast.ISourceFile;
import cide.gast.SourceFileAdapter;
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
		try {
			return getICompilationUnit(file) != null;
		} catch (java.lang.IllegalArgumentException e) {
			return false;
		}
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

		return parseCompilationUnit(compUnit);
	}

	public static CompilationUnit parseCompilationUnit(ICompilationUnit compUnit)
			throws ParseException {

		ASTParser parser = ASTParser.newParser(AST.JLS3);// TODO: find
		parser.setResolveBindings(true);
		parser.setSource(compUnit);
		parser.setStatementsRecovery(false);
		CompilationUnit root = (CompilationUnit) parser.createAST(null);
		return root;
	}

}
