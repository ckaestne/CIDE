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

package de.ovgu.cide.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;

/**
 * 
 */
public class EditorUtilityJava {
	private EditorUtilityJava() {
		super();
	}

	public static IOpenable getJavaInput(IEditorPart part) {
		IEditorInput editorInput = part.getEditorInput();
		if (editorInput != null) {
			IJavaElement input = javaUIgetEditorInputJavaElement(editorInput);
			if (input instanceof IOpenable) {
				return (IOpenable) input;
			}
		}
		return null;
	}

	public static ICompilationUnit getCompilationUnitFromInput(IEditorPart part) {
		IOpenable openable = EditorUtilityJava.getJavaInput(part);
		if (openable instanceof ICompilationUnit) {
			return (ICompilationUnit) openable;
		}
		return null;
	}

	/**
	 * Note: This is an inlined version of
	 * {@link JavaUI#getEditorInputJavaElement(IEditorInput)}, which is not
	 * available in 3.1.
	 */
	public static IJavaElement javaUIgetEditorInputJavaElement(
			IEditorInput editorInput) {
		assert (null != editorInput);
		IJavaElement je = JavaUI.getWorkingCopyManager().getWorkingCopy(
				editorInput);
		if (je != null)
			return je;

		/*
		 * This needs works, see
		 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=120340
		 */
		return (IJavaElement) editorInput.getAdapter(IJavaElement.class);
	}

	public static IEditorInput getEditorInput(Object input)
			throws JavaModelException {
		if (input instanceof IJavaElement)
			return getEditorInput((IJavaElement) input);

		if (input instanceof IFile)
			return new FileEditorInput((IFile) input);

		return null;
	}

	private static IEditorInput getEditorInput(IJavaElement element)
			throws JavaModelException {
		while (element != null) {
			if (element instanceof ICompilationUnit) {
				ICompilationUnit unit = ((ICompilationUnit) element)
						.getPrimary();
				IResource resource = unit.getResource();
				if (resource instanceof IFile)
					return new FileEditorInput((IFile) resource);
			}

			element = element.getParent();
		}

		return null;
	}
}
