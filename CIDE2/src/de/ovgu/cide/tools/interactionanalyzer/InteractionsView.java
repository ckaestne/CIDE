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

package de.ovgu.cide.tools.interactionanalyzer;

import org.eclipse.core.resources.IProject;
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

import de.ovgu.cide.ColoredIDEImages;
import de.ovgu.cide.editor.ColoredTextEditor;
import de.ovgu.cide.tools.interactionanalyzer.CollectStatisticsAndInteractionsJob.InteractionPosition;
import de.ovgu.cide.utils.EditorUtility;


public class InteractionsView extends ViewPart {

	public static final String VIEW_ID = "de.ovgu.cide.core.view.interactions";

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
				CollectStatisticsAndInteractionsJob job = new CollectStatisticsAndInteractionsJob(
						getActiveProject(), tree);
//				CollectPageStatistics job = new CollectPageStatistics(
//						getActiveProject(), tree);
				job.setUser(true);
				job.schedule();
			}
		};
		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refresh Statistics");
		ColoredIDEImages.setImageDescriptors(refreshAction,
				ColoredIDEImages.REFRESH);

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

				InteractionPosition node = (InteractionPosition) selection[0]
						.getData();
				IEditorPart editor = null;
				try {
					IWorkbenchPage page = InteractionsView.this.getSite()
							.getPage();
					editor = IDE.openEditor(page, node.file,
							ColoredTextEditor.EDITOR_ID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (editor instanceof ITextEditor)
					EditorUtility.selectInEditor((ITextEditor) editor,
							node.start, node.length);
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

	// private void showMessage(String message) {
	// MessageDialog.openInformation(tree.getShell(), "Feature Interactions",
	// message);
	// }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tree.setFocus();
	}

	private IProject getActiveProject() {
		IEditorPart part = EditorUtility.getActiveEditor();
		IEditorInput input = part.getEditorInput();
		if (input instanceof IFileEditorInput)
			return ((IFileEditorInput) input).getFile().getProject();

		return null;
	}
}