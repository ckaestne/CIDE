package coloredide.features.source;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.utils.ASTCreator;

public class ColoredJavaSourceFile implements IColoredJavaSourceFile {

	protected final IFile colorFile;

	protected final ICompilationUnit compilationUnit;

	protected WeakReference<CompilationUnit> astRef = null;

	protected IColorManager colorManager = null;

	protected ColoredJavaSourceFile(IFile colorFile) {
		this.colorFile = colorFile;
		this.compilationUnit = getCompilationUnit(getJavaFile(colorFile));
	}

	protected ColoredJavaSourceFile(ICompilationUnit compUnit) {
		this.compilationUnit = compUnit;
		this.colorFile = getColorFile(getResource(compUnit));
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColoredJavaSourceFile#getAST()
	 */
	public CompilationUnit getAST() throws JavaModelException, CoreException {
		return getAST(new ASTCreator());
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColoredJavaSourceFile#getAST(coloredide.utils.ASTCreator)
	 */
	public CompilationUnit getAST(ASTCreator creator)
			throws JavaModelException, CoreException {
		if (astRef != null) {
			CompilationUnit r = astRef.get();
			if (r != null)
				return r;
		}

		CompilationUnit r = creator.createAST(compilationUnit);
		astRef = new WeakReference<CompilationUnit>(r);
		return r;
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColoredJavaSourceFile#refreshAST()
	 */
	public void refreshAST() {
		astRef = null;
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColoredJavaSourceFile#getCompilationUnit()
	 */
	public ICompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public static ICompilationUnit getCompilationUnit(IFile javaFile) {
		if (!javaFile.exists())
			return null;
		return JavaCore.createCompilationUnitFrom(javaFile);
	}

	public static CompilationUnit getASTRoot(ASTNode node) {
		return (CompilationUnit) node.getRoot();
	}

	public static ICompilationUnit getCompilationUnit(CompilationUnit astRoot) {
		return (ICompilationUnit) astRoot.getJavaElement();
	}

	public static IFile getResource(ICompilationUnit cu) {
		IPath path = cu.getPath();
		return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
	}

	public static IFile getResource(ASTNode node) {
		return getResource(getCompilationUnit(getASTRoot(node)));
	}

	public int hashCode() {
		return colorFile.hashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ColoredJavaSourceFile))
			return false;
		return colorFile.equals(((ColoredJavaSourceFile) obj).colorFile);
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColoredJavaSourceFile#getColorManager()
	 */
	public IColorManager getColorManager() {
		if (colorManager == null) {
			colorManager = new CompilationUnitColorManager(colorFile, this);
		}
		return colorManager;
	}

	private static final WeakHashMap<IFile, WeakReference<ColoredJavaSourceFile>> fileCache = new WeakHashMap<IFile, WeakReference<ColoredJavaSourceFile>>();

	private static final WeakHashMap<ICompilationUnit, WeakReference<ColoredJavaSourceFile>> compUnitCache = new WeakHashMap<ICompilationUnit, WeakReference<ColoredJavaSourceFile>>();

	private static void cache(ColoredJavaSourceFile sourceFile) {
		WeakReference<ColoredJavaSourceFile> r = new WeakReference<ColoredJavaSourceFile>(
				sourceFile);
		fileCache.put(sourceFile.colorFile, r);
		compUnitCache.put(sourceFile.compilationUnit, r);
	}

	public static IColoredJavaSourceFile getColoredJavaSourceFile(IFile colorFile) {
		ColoredJavaSourceFile cachedCJSF = null;
		WeakReference<ColoredJavaSourceFile> r = fileCache.get(colorFile);
		if (r != null)
			cachedCJSF = r.get();
		if (cachedCJSF == null) {
			cachedCJSF = new ColoredJavaSourceFile(colorFile);
			cache(cachedCJSF);
		}
		return cachedCJSF;
	}

	/**
	 * returns whether a source object is available for the unit. for processes
	 * that must not create the source object if not created yet.
	 * 
	 * @param compUnit
	 * @return
	 */
	public static boolean existsColoredJavaSourceFile(ICompilationUnit compUnit) {
		return compUnitCache.get(compUnit) != null;
	}

	public static IColoredJavaSourceFile getColoredJavaSourceFile(
			ICompilationUnit compUnit) {
		ColoredJavaSourceFile cachedCJSF = null;
		WeakReference<ColoredJavaSourceFile> r = compUnitCache.get(compUnit);
		if (r != null)
			cachedCJSF = r.get();
		if (cachedCJSF == null) {
			cachedCJSF = new ColoredJavaSourceFile(compUnit);
			cache(cachedCJSF);
		}
		return cachedCJSF;
	}

	protected static IFile getJavaFile(IFile colorFile) {
		IPath javaFilePath = colorFile.getFullPath().removeFileExtension()
				.addFileExtension("java");
		return ResourcesPlugin.getWorkspace().getRoot().getFile(javaFilePath);
	}

	protected static IFile getColorFile(IFile javaFile) {
		IPath colorFilePath = javaFile.getFullPath().removeFileExtension()
				.addFileExtension("color");
		return ResourcesPlugin.getWorkspace().getRoot().getFile(colorFilePath);
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColoredJavaSourceFile#getProject()
	 */
	public IProject getProject() {
		return colorFile.getProject();
	}

	/* (non-Javadoc)
	 * @see coloredide.features.source.IColoredJavaSourceFile#hasColors()
	 */
	public boolean hasColors() {
		return getColorManager().hasColors();
	}
}
