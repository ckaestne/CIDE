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
