package coloredide.features.source;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;

public class ASTRefreshListener implements IResourceChangeListener,
		IResourceDeltaVisitor {

	public void resourceChanged(IResourceChangeEvent event) {
		try {
			if (event.getDelta() != null)
				event.getDelta().accept(this);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public boolean visit(IResourceDelta delta) throws CoreException {
		if (delta.getKind() != IResourceDelta.CHANGED)
			return true;

		if (delta.getResource() instanceof IFile) {
			IFile file = (IFile) delta.getResource();
			if (JavaCore.isJavaLikeFileName(file.getName())) {
				ICompilationUnit compUnit = ColoredJavaSourceFile
						.getCompilationUnit(file);
				if (compUnit != null
						&& ColoredJavaSourceFile
								.existsColoredJavaSourceFile(compUnit)) {
					ColoredJavaSourceFile.getColoredJavaSourceFile(compUnit)
							.refreshAST();
				}
			}
		}

		return true;
	}

}
