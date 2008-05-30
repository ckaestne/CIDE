package coloredide.tools.updatecache;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;

import coloredide.ColoredIDEPlugin;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ColoredSourceFileIteratorJob;

public class UpdateColorCacheJob extends ColoredSourceFileIteratorJob {

	public UpdateColorCacheJob(IProject[] projects) {
		super(projects, "Updating Colors", "Updating Colors: ");
	}

	protected void processSource(IColoredJavaSourceFile source)
			throws JavaModelException, CoreException {
		ColoredIDEPlugin.getDefault().colorCache.updateASTColors(source
				.getProject(), source.getAST(), source.getColorManager());
	}

	protected void cleanProject(IJavaProject project, IProgressMonitor monitor)
			throws CoreException {
		ColoredIDEPlugin.getDefault().colorCache.cleanCache(project
				.getProject());
	}

}
