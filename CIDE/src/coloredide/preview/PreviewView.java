package coloredide.preview;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

import coloredide.ColorChangedEvent;
import coloredide.ColoredIDEPlugin;
import coloredide.IColorChangeListener;
import coloredide.configuration.ErrorCodePrinter;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.utils.EditorUtility;

public class PreviewView extends ViewPart {

	private Browser browser;

	private coloredide.preview.PreviewView.ListenerMix fSuperListener;

	private IColoredJavaSourceFile fColoredJavaSourceFile = null;

	private Object fEditor;

	public void createPartControl(Composite parent) {
		browser = new Browser(parent, SWT.NONE);
		contributeToActionBars();

		updateBrowser();
		PreviewManager.addEditor(this);

		try {
			IEditorPart part = EditorUtility.getActiveEditor();
			if (part instanceof ITextEditor) {
				setInput((ITextEditor) part);
			}
		} catch (CoreException e) {
			// ignore
		}
		if (fColoredJavaSourceFile == null) {
			clearView();
		} else {
		}
	}

	@Override
	public void dispose() {
		PreviewManager.removeEditor(this);
		ColoredIDEPlugin.getDefault().removeColorChangeListener(fSuperListener);
		super.dispose();
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.setSite(site);
		if (fSuperListener == null) {
			fSuperListener = new ListenerMix(this);

			ISelectionService service = site.getWorkbenchWindow()
					.getSelectionService();
			service.addPostSelectionListener(fSuperListener);
			site.getPage().addPartListener(fSuperListener);
			ColoredIDEPlugin.getDefault()
					.addColorChangeListener(fSuperListener);
			// FileBuffers.getTextFileBufferManager().addFileBufferListener(
			// fSuperListener);
		}
	}

	private static class ListenerMix implements IPartListener2,
			IColorChangeListener, ISelectionListener {

		private boolean fPreviewVisible = true;

		private PreviewView fView;

		public ListenerMix(PreviewView view) {
			fView = view;
		}

		public void dispose() {
			fView = null;
		}

		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (fPreviewVisible) {
				fView.handleEditorPostSelectionChanged(part, selection);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partHidden(IWorkbenchPartReference partRef) {
			IWorkbenchPart part = partRef.getPart(false);
			if (part == fView) {
				fPreviewVisible = false;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partVisible(IWorkbenchPartReference partRef) {
			IWorkbenchPart part = partRef.getPart(false);
			if (part == fView) {
				fPreviewVisible = true;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partActivated(IWorkbenchPartReference partRef) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partClosed(IWorkbenchPartReference partRef) {
			fView.notifyWorkbenchPartClosed(partRef);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partDeactivated(IWorkbenchPartReference partRef) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partOpened(IWorkbenchPartReference partRef) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
		 */
		public void partInputChanged(IWorkbenchPartReference partRef) {
		}

		public void colorChanged(ColorChangedEvent event) {
			if (event.getColoredJavaSourceFile() == fView.fColoredJavaSourceFile)
				fView.colorUpdated(event.getAffectedNodes());
		}

	}

	final void notifyWorkbenchPartClosed(IWorkbenchPartReference partRef) {
		if (fEditor != null && fEditor.equals(partRef.getPart(false))) {
			try {
				setInput(null);
			} catch (CoreException e) {
				// ignore
			}
		}
	}

	protected void handleEditorPostSelectionChanged(IWorkbenchPart part,
			ISelection selection) {
		if (!(selection instanceof ITextSelection)) {
			return;
		}
		// if (!fDoLinkWithEditor) {
		// return;
		// }
		if (fColoredJavaSourceFile == null || part != fEditor) {
			if (part instanceof ITextEditor
					&& (EditorUtility.getJavaInput((ITextEditor) part) != null)) {
				try {
					setInput((ITextEditor) part);
				} catch (CoreException e) {
					setContentDescription(e.getStatus().getMessage());
				}
			}

		}
	}

	private void colorUpdated(Collection<ASTNode> name) {
		updateBrowser();
	}

	private IStatus getErrorStatus(String message, Throwable th) {
		return new Status(IStatus.ERROR, ColoredIDEPlugin.getPluginId(),
				IStatus.ERROR, message, th);
	}

	public void setInput(ITextEditor editor) throws CoreException {
		// if (fEditor != null) {
		// uninstallModificationListener();
		// }

		fEditor = null;

		if (editor != null) {
			IOpenable openable = EditorUtility.getJavaInput(editor);
			if (openable instanceof ICompilationUnit) {
				// TODO support only compilation units, not already compiled
				// files!
				if (openable == null) {
					throw new CoreException(getErrorStatus(
							"Editor not showing a CU or class file", null)); //$NON-NLS-1$
				}
				fColoredJavaSourceFile = ColoredJavaSourceFile
						.getColoredJavaSourceFile((ICompilationUnit) openable);
				fEditor = editor;
				updateBrowser();

				// installModificationListener();
			} else
				performClear();
		}
	}

	protected void performClear() {
		fColoredJavaSourceFile = null;
		try {
			setInput(null);
		} catch (CoreException e) {
			// showAndLogError("Could not reset AST view ", e); //$NON-NLS-1$
		}
		clearView();
	}

	private void clearView() {
		try {
			setInput(null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		setContentDescription("Open a Java editor and press the 'Show AST of active editor' toolbar button"); //$NON-NLS-1$
	}

	public void updateBrowser() {
		// ASTViewPlugin.getDefault().getValidator().runValidation(fRoot);

		if (fColoredJavaSourceFile != null) {
			renderHtml();
		}
	}

	private void renderHtml() {
		try {
			browser.setText("<pre>"
					+ new ErrorCodePrinter().printCode(fColoredJavaSourceFile,
							PreviewManager.getFeatureSelection(
									fColoredJavaSourceFile.getProject())
									.getHiddenFeatures()));
		} catch (JavaModelException e) {
			browser.setText(e.getMessage());
		} catch (CoreException e) {
			browser.setText(e.getMessage());
		}
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		// fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalToolBar(IToolBarManager toolBarManager) {
		for (Action a : PreviewManager.getFeatureSelection(
				fColoredJavaSourceFile.getProject()).getShowColorActions()) {
			toolBarManager.add(a);
		}
	}

}
