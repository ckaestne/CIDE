package coloredide.tools.interactionanalyzer;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

import coloredide.ColoredIDEImages;
import coloredide.tools.interactionanalyzer.CollectInteractionsJob.InteractionPosition;
import coloredide.utils.EditorUtility;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class InteractionsView extends ViewPart {
	Tree tree;

	private Action refreshAction;

	private Action jumpToAction;

	/**
	 * The constructor.
	 */
	public InteractionsView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		tree = new Tree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				InteractionsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(tree);
		tree.setMenu(menu);
		// getSite().registerContextMenu(menuMgr, tree);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refreshAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refreshAction);
	}

	private void makeActions() {
		refreshAction = new Action() {
			public void run() {
				CollectInteractionsJob job = new CollectInteractionsJob(
						getActiveProject(), tree);
				job.setUser(true);
				job.schedule();
			}
		};
		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refresh Statistics");
		ColoredIDEImages.setImageDescriptors(refreshAction, ColoredIDEImages.REFRESH);

		jumpToAction = new Action() {
			public void run() {
				TreeItem[] selection = tree.getSelection();
				if (selection.length != 1)
					return;
				if (!(selection[0].getData() instanceof InteractionPosition))
					return;
				// IEditorPart part = EditorUtility.getActiveEditor();
				// if (part == null) {
				// return;
				// }

				InteractionPosition node = (InteractionPosition) selection[0].getData();
				IEditorPart editor=null;
				try {
					editor = JavaUI.openInEditor(node.compUnit);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (editor instanceof ITextEditor)
					EditorUtility.selectInEditor((ITextEditor) editor, node.start,
							node.length);
			}
		};
	}

	private void hookDoubleClickAction() {
		tree.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				jumpToAction.run();
			}
		});
	}

//	private void showMessage(String message) {
//		MessageDialog.openInformation(tree.getShell(), "Feature Interactions",
//				message);
//	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tree.setFocus();
	}

	private IProject getActiveProject() {
		IEditorPart part = EditorUtility.getActiveEditor();
		IOpenable openable = EditorUtility.getJavaInput(part);
		if (openable instanceof ICompilationUnit) {
			return ((ICompilationUnit) openable).getResource().getProject();
		}
		return null;
	}
}