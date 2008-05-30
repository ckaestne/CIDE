/**
 * 
 */
package coloredide.export2jak.validator;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.export.LocalVariableHelper;
import coloredide.export2jak.JakColorChecker;
import coloredide.export2jak.UnsupportedColoring;
import coloredide.export2jak.JakColorChecker.UnsupportedColoringMultipleWriteAccess;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ColoredSourceFileIteratorJob;

class JakColorCheckerJob extends ColoredSourceFileIteratorJob {

	public JakColorCheckerJob(IProject[] projects) {
		super(projects, "Checking Colors for Jak Export", "Checking colors");
	}

	public JakColorCheckerJob(IProject project) {
		this(new IProject[] { project });
	}

	protected void processSource(IColoredJavaSourceFile source)
			throws JavaModelException, CoreException {
		try {
			CompilationUnit ast = source.getAST();
			JakColorChecker colorChecker = new JakColorChecker(source
					.getColorManager());
			LocalVariableHelper.cacheCompilationUnit(ast);
			ast.accept(colorChecker);

			ast.accept(new LocalVariableTmp());

			markColorWarnings(source, colorChecker.getUnsupportedColorings());
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;

		} catch (JavaModelException e) {
			e.printStackTrace();
			throw e;

		} catch (CoreException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void markColorWarnings(IColoredJavaSourceFile source,
			List<UnsupportedColoring> unsupportedColorings)
			throws CoreException {

		IResource resource = source.getCompilationUnit().getResource();
		for (UnsupportedColoring u : unsupportedColorings) {
			// if (!(u instanceof UnsupportedColoringMultipleWriteAccess))
			JakColorCheckMarker.createMarker(resource, u);
			count(u);
		}
	}

	private HashMap<String, Integer> stats = new HashMap<String, Integer>();

	private void count(UnsupportedColoring u) {
		String r = u.getReason();
		Integer i = stats.get(r);
		if (i == null)
			i = new Integer(0);
		i = new Integer(i.intValue() + 1);
		stats.put(r, i);
	}

	@Override
	protected void cleanProject(IJavaProject project, IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return;

		project.getResource().deleteMarkers(JakColorCheckMarker.TYPEID, true,
				IResource.DEPTH_INFINITE);
		monitor.worked(5);
	}

	@Override
	protected void finish() {
		for (String r : stats.keySet()) {
			System.out.println(r + " " + stats.get(r));
		}
		super.finish();
	}
}