/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package de.ovgu.cide.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import de.ovgu.cide.CIDECorePlugin;

/**
 * 
 */
public class EditorUtility {
	private EditorUtility() {
		super();
	}

	public static IEditorPart getActiveEditor() {
		IWorkbenchWindow window = CIDECorePlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			IWorkbenchPage page = window.getActivePage();
			if (page != null) {
				return page.getActiveEditor();
			}
		}
		return null;
	}

	public static IFile getFileInput(IEditorPart part) {
		IEditorInput editorInput = part.getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			return ((IFileEditorInput) editorInput).getFile();
		}
		return null;
	}

	// public static IOpenable getJavaInput(IEditorPart part) {
	// IEditorInput editorInput = part.getEditorInput();
	// if (editorInput != null) {
	// IJavaElement input = javaUIgetEditorInputJavaElement(editorInput);
	// if (input instanceof IOpenable) {
	// return (IOpenable) input;
	// }
	// }
	// return null;
	// }
	//
	// public static ICompilationUnit getCompilationUnitFromInput(IEditorPart
	// part) {
	// IOpenable openable = EditorUtility.getJavaInput(part);
	// if (openable instanceof ICompilationUnit) {
	// return (ICompilationUnit) openable;
	// }
	// return null;
	// }
	//
	// /**
	// * Note: This is an inlined version of
	// * {@link JavaUI#getEditorInputJavaElement(IEditorInput)}, which is not
	// * available in 3.1.
	// */
	// public static IJavaElement javaUIgetEditorInputJavaElement(
	// IEditorInput editorInput) {
	// Assert.isNotNull(editorInput);
	// IJavaElement je = JavaUI.getWorkingCopyManager().getWorkingCopy(
	// editorInput);
	// if (je != null)
	// return je;
	//
	// /*
	// * This needs works, see
	// * https://bugs.eclipse.org/bugs/show_bug.cgi?id=120340
	// */
	// return (IJavaElement) editorInput.getAdapter(IJavaElement.class);
	// }

	public static void selectInEditor(ITextEditor editor, int offset, int length) {
		IEditorPart active = getActiveEditor();
		if (active != editor) {
			editor.getSite().getPage().activate(editor);
		}
		editor.selectAndReveal(offset, length);
	}

	public static IEditorInput getEditorInput(Object input) {
		// if (input instanceof IJavaElement)
		// return getEditorInput((IJavaElement) input);

		if (input instanceof IFile)
			return new FileEditorInput((IFile) input);

		return null;
	}

	// private static IEditorInput getEditorInput(IJavaElement element)
	// throws JavaModelException {
	// while (element != null) {
	// if (element instanceof ICompilationUnit) {
	// ICompilationUnit unit = ((ICompilationUnit) element)
	// .getPrimary();
	// IResource resource = unit.getResource();
	// if (resource instanceof IFile)
	// return new FileEditorInput((IFile) resource);
	// }
	//
	// element = element.getParent();
	// }
	//
	// return null;
	// }
}
