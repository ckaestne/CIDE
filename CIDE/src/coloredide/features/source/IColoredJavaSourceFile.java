package coloredide.features.source;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.utils.ASTCreator;

public interface IColoredJavaSourceFile {

	public abstract CompilationUnit getAST() throws JavaModelException,
			CoreException;

	public abstract CompilationUnit getAST(ASTCreator creator)
			throws JavaModelException, CoreException;

	public abstract void refreshAST();

	public abstract ICompilationUnit getCompilationUnit();

	public abstract IColorManager getColorManager();

	public abstract IProject getProject();

	public abstract boolean hasColors();

}