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

package de.ovgu.cide.tools.featureview;

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.ColoredIDEImages;
import de.ovgu.cide.FileColorChangedEvent;
import de.ovgu.cide.IColorChangeListener;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.utils.AbstractToggleProjectionAction;
import de.ovgu.cide.features.utils.ToggleAllFeatureVisibility;
import de.ovgu.cide.utils.ColorHelper;
import de.ovgu.cide.utils.EditorUtility;

/**
 * list of all features. can be used to manipulate features and to control views
 * (projection)
 * 
 * @author ckaestne
 * 
 */
public class FeatureView extends ViewPart {

	private class ActiveProjectListner implements IPartListener {

		public void partActivated(IWorkbenchPart part) {
			update();
		}

		private void update() {
			IProject ap = getActiveProject();
			setProject(ap);
		}

		public void partBroughtToTop(IWorkbenchPart part) {
			update();
		}

		public void partClosed(IWorkbenchPart part) {
			update();
		}

		public void partDeactivated(IWorkbenchPart part) {
			update();
		}

		public void partOpened(IWorkbenchPart part) {
			update();
		}

	}

	private class ColorChangeListner implements IColorChangeListener {

		public void astColorChanged(ASTColorChangedEvent event) {
			// ignore
		}

		public void colorListChanged(ColorListChangedEvent event) {
			if (event.getProject() == project)
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						redraw();
						updateValidPanel();
					}
				});
		}

		public void fileColorChanged(FileColorChangedEvent event) {
			// ignore
		}

	}

	private ActiveProjectListner activeProjectListner;

	private ColorChangeListner colorChangeListner;

	public FeatureView() {
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		activeProjectListner = new ActiveProjectListner();
		site.getPage().addPartListener(activeProjectListner);
		colorChangeListner = new ColorChangeListner();
		CIDECorePlugin.getDefault().addColorChangeListener(colorChangeListner);
	}

	public static final String VIEW_ID = "de.ovgu.cide.core.view.features";

	private Action filterAction;
	private Action selectAllAction;
	private Action selectNoneAction;
	private Action renameAction;
	private Action selectColorAction;
	private Action findFeatureCodeAction;

	// private FeatureTableContentAndLabelProvider provider;

	private Table table;

	private Label validPanel;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		createTable(parent);
		createValidPanel(parent);
		createLayout(parent);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void createLayout(Composite parent) {
		FormData formData = new FormData(-1, 16);
		formData.height = 16;
		formData.right = new FormAttachment(100, 0);
		formData.left = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);
		FormData tableLayoutData = new FormData(-1, -1);
		tableLayoutData.top = new FormAttachment(0, 0);
		tableLayoutData.right = new FormAttachment(100, 0);
		tableLayoutData.left = new FormAttachment(0, 0);
		tableLayoutData.bottom = new FormAttachment(validPanel);
		table.setLayoutData(tableLayoutData);
		validPanel.setLayoutData(formData);
		parent.setLayout(new FormLayout());
		parent.pack();
	}

	private void createValidPanel(Composite parent) {
		validPanel = new Label(parent, SWT.NONE);
		validPanel.setText("");
	}

	private void createTable(Composite parent) {
		table = new Table(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.CHECK);
		table.setHeaderVisible(false);

		TableColumn column;
		column = new TableColumn(table, SWT.LEFT);
		column.setText("Name");
		column.setWidth(120);
		column = new TableColumn(table, SWT.LEFT);
		column.setText("Color");
		column.setWidth(60);

		table.addControlListener(new ControlListener() {
			public void controlMoved(ControlEvent e) {
			}

			public void controlResized(ControlEvent e) {
				int width = table.getClientArea().width;
				if (width > 120) {
					table.getColumn(0).setWidth(width - 60);
					table.getColumn(1).setWidth(60);
				} else {
					table.getColumn(0).setWidth(width / 2);
					table.getColumn(1).setWidth(width / 2);
				}
			}
		});

		setProject(getActiveProject());

		table.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				if (project != null && e.detail == SWT.CHECK) {
					TableItem item = (TableItem) e.item;

					IFeature feature = (IFeature) item.getData();
					if (item.getGrayed())
						item.setChecked(feature.isVisible());
					else
						try {
							feature.setVisible(item.getChecked());
							updateValidPanel();
						} catch (UnsupportedOperationException ex) {
							item.setChecked(feature.isVisible());
						}
				}
			}
		});

	}

	protected void updateValidPanel() {
		if (featureModel.isValidSelection(featureModel.getVisibleFeatures())) {
			validPanel.setText("Valid configuration");
		} else
			validPanel.setText("");
	}

	private IProject project;
	private IFeatureModel featureModel;

	private MenuManager featureContextMenuMgr;

	private void setProject(IProject newProject) {
		if (project == newProject)
			return;
		project = newProject;
		if (project != null)
			try {
				featureModel = FeatureModelManager.getInstance()
						.getFeatureModel(project);
			} catch (FeatureModelNotFoundException e) {
				project = null;
				featureModel = null;
			}
		else
			featureModel = null;

		redraw();
	}

	private void redraw() {
		if (table.isDisposed()) return;
		try {
			table.setRedraw(false);
			IFeature oldSelection = getSelectedFeature();

			for (TableItem item : table.getItems()) {
				if (item.getBackground(1) != null)
					item.getBackground(1).dispose();
			}
			table.removeAll();

			if (project != null && featureModel != null) {

				boolean isFiltered = (filterAction != null)
						&& filterAction.isChecked();
				for (IFeature feature : ColorHelper.sortFeatures(featureModel
						.getFeatures()))
					if (!isFiltered || feature.isVisible()) {
						TableItem item = new TableItem(table, SWT.DEFAULT);
						item.setText(0, feature.getName());
						item.setText(1, ColorHelper.rgb2str(feature.getRGB()));
						item.setBackground(1, new Color(Display.getCurrent(),
								ColorHelper.getCombinedRGB(Collections
										.singleton(feature))));
						item.setChecked(feature.isVisible());
						item.setGrayed(!feature.canSetVisible());
						item.setData(feature);
					}

				setSelectedFeature(oldSelection);
			}
		} finally {
			table.setRedraw(true);
		}
	}

	private void setSelectedFeature(IFeature feature) {
		if (feature != null)
			for (TableItem item : table.getItems()) {
				if (item.getData() == feature) {
					table.setSelection(item);
					break;
				}
			}
		table.setSelection(-1);
	}

	private void hookContextMenu() {
		featureContextMenuMgr = new MenuManager("#PopupMenu");
		featureContextMenuMgr.setRemoveAllWhenShown(true);
		featureContextMenuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				FeatureView.this.fillContextMenu(manager);
			}
		});

		Menu menu = featureContextMenuMgr.createContextMenu(table);
		table.setMenu(menu);
		// getSite().registerContextMenu(menuMgr, table.get);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
		fillMenuBar(bars.getMenuManager());
	}

	private void fillContextMenu(IMenuManager manager) {
		boolean enabled = getSelectedFeature() != null;
		renameAction.setEnabled(enabled && getSelectedFeature().canSetName());
		selectColorAction.setEnabled(enabled
				&& getSelectedFeature().canSetRGB());
		findFeatureCodeAction.setEnabled(enabled);

		manager.add(renameAction);
		manager.add(selectColorAction);
		manager.add(findFeatureCodeAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(filterAction);
		manager.add(selectAllAction);
		manager.add(selectNoneAction);
	}

	private void fillMenuBar(IMenuManager manager) {
		for (Action a : ProjectionKindManager.getInstance().getActions())
			manager.add(a);
	}

	private void makeActions() {
		filterAction = new AbstractToggleProjectionAction() {
			public void run() {
				redraw();
			}
		};

		selectAllAction = new ToggleAllFeatureVisibility(featureModel, true) {
			@Override
			public void run() {
				for (IFeature feature : featureModel.getFeatures()) {
					feature.setVisible(visible);
				}
				updateValidPanel();
			}
		};
		ColoredIDEImages.setImageDescriptors(selectAllAction,
				ColoredIDEImages.CHECKED_IMAGE);

		selectNoneAction = new ToggleAllFeatureVisibility(featureModel, false) {
			@Override
			public void run() {
				for (IFeature feature : featureModel.getFeatures()) {
					feature.setVisible(visible);
				}
				updateValidPanel();
			}
		};
		ColoredIDEImages.setImageDescriptors(selectNoneAction,
				ColoredIDEImages.UNCHECKED_IMAGE);

		renameAction = new Action("Rename...") {
			public void run() {
				IFeature feature = getSelectedFeature();
				if (feature == null)
					return;

				String oldName = feature.getName();

				InputDialog input = new InputDialog(getSite().getShell(),
						"Rename IFeature", "New name:", oldName,
						new IInputValidator() {
							public String isValid(String newText) {
								if (newText.length() == 0)
									return "Name must not be empty";
								return null;
							}
						});
				if (input.open() == InputDialog.OK)
					try {
						feature.setName(input.getValue());
					} catch (UnsupportedOperationException e) {
						// nothing yet. TODO prevent renaming features that
						// cannot
						// be renamed
					}
			}
		};
		renameAction.setToolTipText("Rename IFeature");
		renameAction.setAccelerator(SWT.F2);

		selectColorAction = new Action("Select color...") {
			@Override
			public void run() {
				IFeature feature = getSelectedFeature();
				if (feature == null)
					return;

				RGB oldColor = feature.getRGB();
				ColorDialog colorDialog = new ColorDialog(getSite().getShell());
				colorDialog.setRGB(oldColor);
				colorDialog.setText("Color for IFeature \"" + feature.getName()
						+ "\"");
				RGB newColor = colorDialog.open();
				if (newColor != null && newColor != oldColor)
					try {
						feature.setRGB(newColor);
					} catch (UnsupportedOperationException e) {
						// nothing yet. TODO prevent renaming features that
						// cannot be renamed
					}

			}
		};
		findFeatureCodeAction = new Action("Find feature's code") {
			@Override
			public void run() {
				MessageDialog.openWarning(getSite().getShell(), "Error",
						"not implemented yet");
				super.run();
			}
		};
	}

	protected void selectAll(boolean visible) {
		for (IFeature feature : featureModel.getFeatures()) {
			feature.setVisible(visible);
		}
		updateValidPanel();
	}

	protected IFeature getSelectedFeature() {
		int idx = table.getSelectionIndex();
		if (idx == -1)
			return null;
		return (IFeature) table.getItem(idx).getData();
	}

	private void hookDoubleClickAction() {
		// tree.addSelectionListener(new SelectionListener() {
		// public void widgetDefaultSelected(SelectionEvent e) {
		// }
		//
		// public void widgetSelected(SelectionEvent e) {
		// jumpToAction.run();
		// }
		// });
	}

	// private void showMessage(String message) {
	// MessageDialog.openInformation(tree.getShell(), "Feature Interactions",
	// message);
	// }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		table.setFocus();
	}

	private IProject getActiveProject() {
		IEditorPart part = EditorUtility.getActiveEditor();
		if (part != null) {
			IEditorInput input = part.getEditorInput();
			if (input instanceof IFileEditorInput)
				return ((IFileEditorInput) input).getFile().getProject();
		}
		return null;
	}

	@Override
	public void dispose() {
		setProject(null);
		if (activeProjectListner != null)
			getSite().getPage().removePartListener(activeProjectListner);
		if (colorChangeListner != null)
			CIDECorePlugin.getDefault().removeColorChangeListener(
					colorChangeListner);

	}

}
