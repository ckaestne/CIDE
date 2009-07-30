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

package de.ovgu.cide.astview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.filebuffers.IFileBufferListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.IShowInSource;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gast.Property;
import cide.gast.SourceFileAdapter;
import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.ColoredIDEImages;
import de.ovgu.cide.FileColorChangedEvent;
import de.ovgu.cide.IColorChangeListener;
import de.ovgu.cide.astview.internal.TreeCopyAction;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.IASTRefreshListener;
import de.ovgu.cide.utils.EditorUtility;
import de.ovgu.cide.utils.NodeFinder;

@SuppressWarnings("unchecked")
public class ASTView extends ViewPart implements IShowInSource {

	private class ASTViewSelectionProvider implements ISelectionProvider {
		ListenerList fListeners = new ListenerList(ListenerList.IDENTITY);

		public void addSelectionChangedListener(
				ISelectionChangedListener listener) {
			fListeners.add(listener);
		}

		public ISelection getSelection() {
			@SuppressWarnings("unused")
			IStructuredSelection selection = (IStructuredSelection) fViewer
					.getSelection();
			ArrayList externalSelection = new ArrayList();
			// for (Iterator iter = selection.iterator(); iter.hasNext();) {
			// Object element = iter.next();
			// if (element instanceof JavaElement)
			// externalSelection.add(((JavaElement) element)
			// .getJavaElement());
			// }
			return new StructuredSelection(externalSelection);
		}

		public void removeSelectionChangedListener(
				ISelectionChangedListener listener) {
			fListeners.remove(listener);
		}

		public void setSelection(ISelection selection) {
			// not supported
		}
	}

	private static class ListenerMix implements ISelectionListener,
			IFileBufferListener, IDocumentListener, ISelectionChangedListener,
			IDoubleClickListener, IPartListener2, IColorChangeListener,
			IASTRefreshListener {

		private boolean fASTViewVisible = true;

		private ASTView fView;

		public ListenerMix(ASTView view) {
			fView = view;
		}

		public void dispose() {
			fView = null;
		}

		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (fASTViewVisible) {
				fView.handleEditorPostSelectionChanged(part, selection);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#bufferCreated(org
		 * .eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferCreated(IFileBuffer buffer) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#bufferDisposed(org
		 * .eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferDisposed(IFileBuffer buffer) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.core.filebuffers.IFileBufferListener#
		 * bufferContentAboutToBeReplaced
		 * (org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferContentAboutToBeReplaced(IFileBuffer buffer) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#bufferContentReplaced
		 * (org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferContentReplaced(IFileBuffer buffer) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#stateChanging(org
		 * .eclipse.core.filebuffers.IFileBuffer)
		 */
		public void stateChanging(IFileBuffer buffer) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#dirtyStateChanged
		 * (org.eclipse.core.filebuffers.IFileBuffer, boolean)
		 */
		public void dirtyStateChanged(IFileBuffer buffer, boolean isDirty) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#stateValidationChanged
		 * (org.eclipse.core.filebuffers.IFileBuffer, boolean)
		 */
		public void stateValidationChanged(IFileBuffer buffer,
				boolean isStateValidated) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#underlyingFileMoved
		 * (org.eclipse.core.filebuffers.IFileBuffer,
		 * org.eclipse.core.runtime.IPath)
		 */
		public void underlyingFileMoved(IFileBuffer buffer, IPath path) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#underlyingFileDeleted
		 * (org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void underlyingFileDeleted(IFileBuffer buffer) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.filebuffers.IFileBufferListener#stateChangeFailed
		 * (org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void stateChangeFailed(IFileBuffer buffer) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.text.IDocumentListener#documentAboutToBeChanged
		 * (org.eclipse.jface.text.DocumentEvent)
		 */
		public void documentAboutToBeChanged(DocumentEvent event) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.text.IDocumentListener#documentChanged(org.eclipse
		 * .jface.text.DocumentEvent)
		 */
		public void documentChanged(DocumentEvent event) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged
		 * (org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			fView.handleSelectionChanged(event.getSelection());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse
		 * .jface.viewers.DoubleClickEvent)
		 */
		public void doubleClick(DoubleClickEvent event) {
			fView.handleDoubleClick(event);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partHidden(IWorkbenchPartReference partRef) {
			IWorkbenchPart part = partRef.getPart(false);
			if (part == fView) {
				fASTViewVisible = false;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partVisible(IWorkbenchPartReference partRef) {
			IWorkbenchPart part = partRef.getPart(false);
			if (part == fView) {
				fASTViewVisible = true;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partActivated(IWorkbenchPartReference partRef) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partClosed(IWorkbenchPartReference partRef) {
			fView.notifyWorkbenchPartClosed(partRef);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partDeactivated(IWorkbenchPartReference partRef) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partOpened(IWorkbenchPartReference partRef) {
			// not interesting
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seeorg.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.
		 * IWorkbenchPartReference)
		 */
		public void partInputChanged(IWorkbenchPartReference partRef) {
			// not interesting
		}

		public void astColorChanged(final ASTColorChangedEvent event) {
			if (event.getColoredSourceFile() == fView.fColoredJavaSourceFile)
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						fView.colorUpdated(event.getAffectedNodes());
					}
				});
		}

		public void astRefreshed(ColoredSourceFile sourceFile) {
			if (sourceFile == fView.fColoredJavaSourceFile)
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						fView.refreshAST();
					}
				});
		}

		public void fileColorChanged(FileColorChangedEvent event) {
			// currently not needed
		}

		public void colorListChanged(ColorListChangedEvent event) {
			if (fView.fColoredJavaSourceFile == null)
				return;
			if (event.getProject() == fView.fColoredJavaSourceFile
					.getResource().getProject()
					&& event.anyChangeOf(ChangeType.COLOR))
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						fView.fViewer.refresh();
					}
				});
		}
	}

	private final static String SETTINGS_LINK_WITH_EDITOR = "link_with_editor"; //$NON-NLS-1$

	private SashForm fSash;

	private TreeViewer fViewer;

	private ASTViewLabelProvider fASTLabelProvider;

	private DrillDownAdapter fDrillDownAdapter;

	private Action fFocusAction;
	private Action fFilterNonOptionalAction;

	private TestPersistenceAction fTestPersistenceAction;

	private Action fCollapseAction;

	private Action fExpandAction;

	private TreeCopyAction fCopyAction;

	private Action fDoubleClickAction;

	private Action fLinkWithEditor;

	private int fCurrentInputKind;

	private ITextEditor fEditor;

	private ColoredSourceFile fColoredJavaSourceFile;

	private ISourceFile fRoot;

	private IDocument fCurrentDocument;

	private boolean fDoLinkWithEditor;

	private Object fPreviousDouble;

	private ListenerMix fSuperListener;

	private IDialogSettings fDialogSettings;

	private IFeatureModel fFeatureModel;

	private boolean isFilterNonOptional = false;

	private ASTViewContentProvider fContentProvider;

	public ASTView() {
		fSuperListener = null;
		fDialogSettings = CIDECorePlugin.getDefault().getDialogSettings();
		fDoLinkWithEditor = fDialogSettings
				.getBoolean(SETTINGS_LINK_WITH_EDITOR);
	}

	private void colorUpdated(Collection<IASTNode> affectedNodes) {
		if (affectedNodes.size() > 3)
			fViewer.refresh();
		else
			for (IASTNode affectedNode : affectedNodes)
				fViewer.refresh(affectedNode);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewPart#init(org.eclipse.ui.IViewSite)
	 */
	public void init(IViewSite site) throws PartInitException {
		super.setSite(site);
		if (fSuperListener == null) {
			fSuperListener = new ListenerMix(this);

			ISelectionService service = site.getWorkbenchWindow()
					.getSelectionService();
			service.addPostSelectionListener(fSuperListener);
			site.getPage().addPartListener(fSuperListener);
			FileBuffers.getTextFileBufferManager().addFileBufferListener(
					fSuperListener);
			CIDECorePlugin.getDefault().addColorChangeListener(fSuperListener);
		}
	}

	public int getCurrentInputKind() {
		return fCurrentInputKind;
	}

	public void setInput(ITextEditor editor) throws CoreException {

		fEditor = null;
		fRoot = null;

		if (editor != null) {
			IFile currentFile = EditorUtility.getFileInput(editor);
			try {
				fFeatureModel = FeatureModelManager.getInstance()
						.getFeatureModel(currentFile.getProject());
			} catch (FeatureModelNotFoundException e) {
				fFeatureModel = null;
			}
			if (currentFile != null && fFeatureModel != null) {
				fColoredJavaSourceFile = ColoredSourceFile
						.getColoredSourceFile(currentFile, fFeatureModel);
				fColoredJavaSourceFile.addASTRefreshListener(fSuperListener);
				ISelection selection = editor.getSelectionProvider()
						.getSelection();
				if (selection instanceof ITextSelection) {
					ITextSelection textSelection = (ITextSelection) selection;
					fRoot = internalSetInput(fColoredJavaSourceFile,
							textSelection.getOffset(), textSelection
									.getLength());
					fEditor = editor;
				}
			} else
				performClear();
		}
	}

	private ISourceFile internalSetInput(ColoredSourceFile input, int offset,
			int length) {
		ISourceFile root = null;
		if (input.isColored())
			try {
				root = input.getAST();
			} catch (Exception e) {
				root = null;
			}
		resetView(root);
		if (root == null) {
			setContentDescription("AST could not be created."); //$NON-NLS-1$
			return null;
		}
		IASTNode node = NodeFinder.perform(root, offset, length);
		if (node != null) {
			fViewer.getTree().setRedraw(false);
			fASTLabelProvider.setSelectedRange(node.getStartPosition(), node
					.getLength());
			fViewer.setSelection(new StructuredSelection(node), true);
			fViewer.getTree().setRedraw(true);
		}
		return root;
	}

	private void clearView() {
		resetView(null);
		setContentDescription("Open a Java editor and press the 'Show AST of active editor' toolbar button"); //$NON-NLS-1$
	}

	private void resetView(IASTNode root) {
		if (root instanceof SourceFileAdapter)
			root = ((SourceFileAdapter) root).ast;
		if (root != null)
			fViewer.setInput(new ASTViewContentProvider.RootCapsle(root));
		else
			fViewer.setInput(null);
		fViewer.getTree().setEnabled(root != null);
		fViewer.expandToLevel(1);
		fSash.setMaximizedControl(fViewer.getTree());
		fPreviousDouble = null; // avoid leaking AST
	}

	//
	// private void updateContentDescription(IJavaElement element,
	// CompilationUnit root, long time) {
	// String version = "AST Level 3"; //$NON-NLS-1$//$NON-NLS-2$
	// if (getCurrentInputKind() == ASTInputKindAction.USE_RECONCILE) {
	// version += ", from reconciler"; //$NON-NLS-1$
	// } else if (getCurrentInputKind() == ASTInputKindAction.USE_CACHE) {
	// version += ", from ASTProvider"; //$NON-NLS-1$
	// } else if (getCurrentInputKind() == ASTInputKindAction.USE_FOCAL) {
	// version += ", using focal position"; //$NON-NLS-1$
	// }
	// TreeInfoCollector collector = new TreeInfoCollector(root);
	//
	// String msg = "{0} ({1}). Creation time: {2,number} ms. Size: {3,number}
	// nodes, {4,number} bytes (AST nodes only)."; //$NON-NLS-1$
	// Object[] args = { element.getElementName(), version, new Long(time),
	// new Integer(collector.getNumberOfNodes()),
	// new Integer(collector.getSize()) };
	// setContentDescription(MessageFormat.format(msg, args));
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPart#dispose()
	 */
	public void dispose() {
		if (fSuperListener != null) {
			ISelectionService service = getSite().getWorkbenchWindow()
					.getSelectionService();
			service.removePostSelectionListener(fSuperListener);
			getSite().getPage().removePartListener(fSuperListener);
			FileBuffers.getTextFileBufferManager().removeFileBufferListener(
					fSuperListener);
			fSuperListener.dispose(); // removes reference to view
			fSuperListener = null;
		}
		super.dispose();
	}

	private IStatus getErrorStatus(String message, Throwable th) {
		return new Status(IStatus.ERROR, CIDECorePlugin.getPluginId(),
				IStatus.ERROR, message, th);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		fSash = new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);
		fViewer = new TreeViewer(fSash, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		fDrillDownAdapter = new DrillDownAdapter(fViewer);
		fViewer
				.setContentProvider(fContentProvider = new ASTViewContentProvider());
		fASTLabelProvider = new ASTViewLabelProvider(this);
		fViewer.setLabelProvider(fASTLabelProvider);
		fViewer.addSelectionChangedListener(fSuperListener);
		fViewer.addDoubleClickListener(fSuperListener);
		// fViewer.addFilter(fNonRelevantFilter);

		makeActions();
		hookContextMenu();
		contributeToActionBars();
		getSite().setSelectionProvider(new ASTViewSelectionProvider());

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
			refreshAST();
		}
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ASTView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(fViewer.getControl());
		fViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, fViewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
		bars.setGlobalActionHandler(ActionFactory.COPY.getId(), fCopyAction);
		bars
				.setGlobalActionHandler(ActionFactory.REFRESH.getId(),
						fFocusAction);
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(new Separator());
		// manager.add(fStatementsRecoveryAction);
		manager.add(new Separator());
		manager.add(new Separator());
		manager.add(fLinkWithEditor);

		manager.add(new TestAction());

		// MenuManager menu = new MenuManager("&Advanced Options");
		// manager.add(menu);
		// for (int i = 0; i < fASTInputKindActions.length; i++) {
		// menu.add(fASTInputKindActions[i]);
		// }
	}

	private List<IASTNode> getSelection() {
		List<IASTNode> result = new ArrayList<IASTNode>();
		IStructuredSelection selection = (IStructuredSelection) fViewer
				.getSelection();
		for (Iterator<Object> iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof IASTNode) {
				result.add((IASTNode) element);
			}
		}
		return result;
	}

	protected void fillContextMenu(IMenuManager manager) {
		manager.add(fFocusAction);
		manager.add(fFilterNonOptionalAction);
		manager.add(fCollapseAction);
		manager.add(fExpandAction);
		manager.add(new Separator());
		manager.add(fCopyAction);
		manager.add(new Separator());

		fDrillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		MenuManager idmenu = new MenuManager("ASTID");
		idmenu.add(fTestPersistenceAction);
		manager.add(idmenu);
		fTestPersistenceAction.update();

		List<IASTNode> nodes = new ArrayList<IASTNode>(getSelection());
		// only colors for optional nodes!
		for (Iterator<IASTNode> i = nodes.iterator(); i.hasNext();)
			if (!i.next().isOptional())
				i.remove();

		if (nodes != null && !nodes.isEmpty() && fFeatureModel != null) {
			for (IFeature feature : fFeatureModel.getVisibleFeatures()) {
				ToggleASTFeatureAction action = new ToggleASTFeatureAction(
						feature, nodes, fColoredJavaSourceFile);
				manager.add(action);
			}
			MenuManager allf = new MenuManager("All features");
			for (IFeature feature : fFeatureModel.getFeatures()) {
				ToggleASTFeatureAction action = new ToggleASTFeatureAction(
						feature, nodes, fColoredJavaSourceFile);
				allf.add(action);
			}
			manager.add(allf);

		}
		manager.add(new CleanASTColorsAction(nodes, fColoredJavaSourceFile));
		// if (nodes != null && nodes.size() == 1) {
		// MenuManager derivativesSubmenu = new MenuManager("Derivative");
		// derivativesSubmenu.add(new Action(new TensorDerivative(
		// nodes.get(0), fColoredJavaSourceFile).getDerivativeStr()) {
		// @Override
		// public void run() {
		// Clipboard cb = new Clipboard(Display.getDefault());
		// TextTransfer textTransfer = TextTransfer.getInstance();
		// cb.setContents(new Object[] { getText() },
		// new Transfer[] { textTransfer });
		// super.run();
		// cb.dispose();
		// }
		// });
		// manager.add(derivativesSubmenu);
		// }
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(fFocusAction);
		manager.add(fFilterNonOptionalAction);
		manager.add(new Separator());
		fDrillDownAdapter.addNavigationActions(manager);
		manager.add(new Separator());
		manager.add(fExpandAction);
		manager.add(fCollapseAction);
		manager.add(fLinkWithEditor);
	}

	private void makeActions() {
		fTestPersistenceAction = new TestPersistenceAction(fViewer);

		fFocusAction = new Action() {
			public void run() {
				performSetFocus();
			}
		};
		fFocusAction.setText("&Show AST of active editor"); //$NON-NLS-1$
		fFocusAction.setToolTipText("Show AST of active editor"); //$NON-NLS-1$
		fFocusAction.setActionDefinitionId("org.eclipse.ui.file.refresh"); //$NON-NLS-1$
		ColoredIDEImages.setImageDescriptors(fFocusAction,
				ColoredIDEImages.SETFOCUS);

		fFilterNonOptionalAction = new Action() {
			public void run() {
				performFilterNonOptional();
			}
		};
		fFilterNonOptionalAction.setText("&Show only optional nodes"); //$NON-NLS-1$
		fFilterNonOptionalAction.setToolTipText("Show only optional nodes"); //$NON-NLS-1$
		fFilterNonOptionalAction
				.setChecked(fContentProvider.isFilterNonOptional);
		ColoredIDEImages.setImageDescriptors(fFilterNonOptionalAction,
				ColoredIDEImages.INTERACTION);
		// fFilterNonOptionalAction
		//				.setActionDefinitionId("de.ovgu.cide.astview.filternonoptional"); //$NON-NLS-1$
		// ColoredIDEImages.setImageDescriptors(fFocusAction,
		// ColoredIDEImages.SETFOCUS);

		fCollapseAction = new Action() {
			public void run() {
				performCollapse();
			}
		};
		fCollapseAction.setText("C&ollapse"); //$NON-NLS-1$
		fCollapseAction.setToolTipText("Collapse Selected Node"); //$NON-NLS-1$
		fCollapseAction.setEnabled(false);
		ColoredIDEImages.setImageDescriptors(fCollapseAction,
				ColoredIDEImages.COLLAPSE);

		fExpandAction = new Action() {
			public void run() {
				performExpand();
			}
		};
		fExpandAction.setText("E&xpand"); //$NON-NLS-1$
		fExpandAction.setToolTipText("Expand Selected Node"); //$NON-NLS-1$
		fExpandAction.setEnabled(false);
		ColoredIDEImages.setImageDescriptors(fExpandAction,
				ColoredIDEImages.EXPAND);

		fCopyAction = new TreeCopyAction(new Tree[] { fViewer.getTree() });

		fDoubleClickAction = new Action() {
			public void run() {
				performDoubleClick();
			}
		};

		fLinkWithEditor = new Action() {
			public void run() {
				performLinkWithEditor();
			}
		};
		fLinkWithEditor.setChecked(fDoLinkWithEditor);
		fLinkWithEditor.setText("&Link with Editor"); //$NON-NLS-1$
		fLinkWithEditor.setToolTipText("Link With Editor"); //$NON-NLS-1$
		ColoredIDEImages.setImageDescriptors(fLinkWithEditor,
				ColoredIDEImages.LINK_WITH_EDITOR);

	}

	protected void performFilterNonOptional() {
		fContentProvider.isFilterNonOptional = fFilterNonOptionalAction
				.isChecked();
		// fFilterNonOptionalAction
		// .setChecked(fContentProvider.isFilterNonOptional);
		fViewer.refresh();
	}

	private void refreshAST() {
		int offset = 0;
		int length = 0;

		fRoot = internalSetInput(fColoredJavaSourceFile, offset, length);
	}

	protected void handleSelectionChanged(ISelection selection) {
		fExpandAction.setEnabled(!selection.isEmpty());
		fCollapseAction.setEnabled(!selection.isEmpty());
		fCopyAction.setEnabled(!selection.isEmpty());
	}

	protected void handleEditorPostSelectionChanged(IWorkbenchPart part,
			ISelection selection) {
		if (!(selection instanceof ITextSelection)) {
			return;
		}
		ITextSelection textSelection = (ITextSelection) selection;
		fASTLabelProvider.setSelectedRange(textSelection.getOffset(),
				textSelection.getLength());
		if (!fDoLinkWithEditor) {
			return;
		}
		if (fRoot == null || part != fEditor) {
			if (part instanceof ITextEditor
					&& (EditorUtility.getFileInput((ITextEditor) part) != null)) {
				try {
					setInput((ITextEditor) part);
				} catch (CoreException e) {
					setContentDescription(e.getStatus().getMessage());
				}
			}

		} else { // fRoot != null && part == fEditor
			doLinkWithEditor(selection);
		}
	}

	private void doLinkWithEditor(ISelection selection) {
		ITextSelection textSelection = (ITextSelection) selection;
		int offset = textSelection.getOffset();
		int length = textSelection.getLength();

		IASTNode covering = NodeFinder.perform(fRoot, offset, length);
		if (covering != null) {
			fViewer.reveal(covering);
			fViewer.setSelection(new StructuredSelection(covering));
		}
	}

	protected void handleDoubleClick(DoubleClickEvent event) {
		fDoubleClickAction.run();
	}

	protected void performLinkWithEditor() {
		fDoLinkWithEditor = fLinkWithEditor.isChecked();
		fDialogSettings.put(SETTINGS_LINK_WITH_EDITOR, fDoLinkWithEditor);

		if (fDoLinkWithEditor && fEditor != null) {
			ISelectionProvider selectionProvider = fEditor
					.getSelectionProvider();
			if (selectionProvider != null) { // can be null when editor is
				// closed
				doLinkWithEditor(selectionProvider.getSelection());
			}
		}
	}

	protected void performCollapse() {
		IStructuredSelection selection = (IStructuredSelection) fViewer
				.getSelection();
		if (selection.isEmpty()) {
			fViewer.collapseAll();
		} else {
			Object[] selected = selection.toArray();
			fViewer.getTree().setRedraw(false);
			for (int i = 0; i < selected.length; i++) {
				fViewer.collapseToLevel(selected[i],
						AbstractTreeViewer.ALL_LEVELS);
			}
			fViewer.getTree().setRedraw(true);
		}
	}

	protected void performExpand() {
		IStructuredSelection selection = (IStructuredSelection) fViewer
				.getSelection();
		if (selection.isEmpty()) {
			fViewer.expandToLevel(3);
		} else {
			Object[] selected = selection.toArray();
			fViewer.getTree().setRedraw(false);
			for (int i = 0; i < selected.length; i++) {
				fViewer.expandToLevel(selected[i], 3);
			}
			fViewer.getTree().setRedraw(true);
		}
	}

	protected void performSetFocus() {
		IEditorPart part = EditorUtility.getActiveEditor();
		if (part instanceof ITextEditor) {
			try {
				setInput((ITextEditor) part);
			} catch (CoreException e) {
				showAndLogError("Could not set AST view input ", e); //$NON-NLS-1$
			}
		}
	}

	protected void performClear() {
		fColoredJavaSourceFile = null;
		fFeatureModel = null;
		try {
			setInput(null);
		} catch (CoreException e) {
			showAndLogError("Could not reset AST view ", e); //$NON-NLS-1$
		}
		clearView();
	}

	private void showAndLogError(String message, CoreException e) {
		e.printStackTrace();
		CIDECorePlugin.log(message, e);
		ErrorDialog.openError(getSite().getShell(),
				"AST View", message, e.getStatus()); //$NON-NLS-1$
	}

	protected void performDoubleClick() {
		if (fEditor == null) {
			return;
		}

		ISelection selection = fViewer.getSelection();
		Object obj = ((IStructuredSelection) selection).getFirstElement();

		boolean isTripleClick = (obj == fPreviousDouble);
		fPreviousDouble = isTripleClick ? null : obj;

		IASTNode node = null;
		if (obj instanceof IASTNode) {
			node = (IASTNode) obj;

		} else if (obj instanceof Property) {
			Object val = ((Property) obj).getNode();
			if (val instanceof IASTNode) {
				node = (IASTNode) val;
			}

		}

		if (node != null) {
			int offset = node.getStartPosition();
			int length = node.getLength();

			EditorUtility.selectInEditor(fEditor, offset, length);
		}
	}

	public void setFocus() {
		fViewer.getControl().setFocus();
	}

	public ShowInContext getShowInContext() {
		return new ShowInContext(null, getSite().getSelectionProvider()
				.getSelection());
	}

	ColoredSourceFile getColoredJavaSourceFile() {
		return fColoredJavaSourceFile;
	}

}