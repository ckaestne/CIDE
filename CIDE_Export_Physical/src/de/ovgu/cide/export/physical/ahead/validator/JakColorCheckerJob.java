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

/**
 * 
 */
package de.ovgu.cide.export.physical.ahead.validator;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import cide.gparser.ParseException;
import de.ovgu.cide.export.physical.ahead.JakColorChecker;
import de.ovgu.cide.export.physical.ahead.UnsupportedColoring;
import de.ovgu.cide.export.physical.internal.LocalVariableHelper;
import de.ovgu.cide.export.physical.internal.RefactoringColorManager;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.ColoredSourceFileIteratorJob;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

class JakColorCheckerJob extends ColoredSourceFileIteratorJob {

	public JakColorCheckerJob(IProject[] projects) {
		super(projects, "Checking Colors for Jak Export", "Checking colors");
	}

	public JakColorCheckerJob(IProject project) {
		this(new IProject[] { project });
	}

	protected void processSource(ColoredSourceFile source)
			throws JavaModelException, CoreException {
		if (!JavaCore.isJavaLikeFileName(source.getResource().getName()))
			return;

		try {
			CompilationUnit ast = JDTParserWrapper.parseJavaFile(source
					.getResource());

			JakColorChecker colorChecker = new JakColorChecker(
					new RefactoringColorManager(source.getColorManager(), ast,
							source.getResource()));
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void markColorWarnings(ColoredSourceFile source,
			List<UnsupportedColoring> unsupportedColorings)
			throws CoreException {

		IFile resource = source.getResource();
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
	protected void cleanProject(IProject project, IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return;

		project.deleteMarkers(JakColorCheckMarker.TYPEID, true,
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