package coloredide.tools.featureview;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
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

import coloredide.ASTColorChangedEvent;
import coloredide.CIDECorePlugin;
import coloredide.ColorListChangedEvent;
import coloredide.ColoredIDEImages;
import coloredide.FileColorChangedEvent;
import coloredide.IColorChangeListener;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.FeatureNameManager;
import coloredide.utils.ColorHelper;
import coloredide.utils.EditorUtility;

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
	private Action renameAction;
	private Action selectColorAction;
	private Action findFeatureCodeAction;

	// private FeatureTableContentAndLabelProvider provider;

	private Table table;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		createTable(parent);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
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

					FeatureNameManager.getFeatureNameManager(project)
							.setFeatureVisible((Feature) item.getData(),
									item.getChecked());
				}
			}
		});

	}

	private IProject project;

	private Menu featureContextMenu;

	private MenuManager featureContextMenuMgr;

	private void setProject(IProject newProject) {
		if (project == newProject)
			return;
		project = newProject;

		redraw();
	}

	private void redraw() {
		try {
			table.setRedraw(false);
			Feature oldSelection = getSelectedFeature();

			for (TableItem item : table.getItems()) {
				if (item.getBackground(1) != null)
					item.getBackground(1).dispose();
			}
			table.removeAll();

			if (project != null) {

				boolean isFiltered = (filterAction != null)
						&& filterAction.isChecked();
				FeatureNameManager featureNameManager = FeatureNameManager
						.getFeatureNameManager(project);
				for (Feature feature : FeatureManager.getFeatures())
					if (!isFiltered
							|| featureNameManager.isFeatureVisible(feature)) {
						TableItem item = new TableItem(table, SWT.DEFAULT);
						item.setText(0, featureNameManager
								.getFeatureName(feature));
						item.setText(1, ColorHelper.rgb2str(feature
								.getRGB(project)));
						item.setBackground(1, new Color(Display.getCurrent(),
								FeatureManager.getCombinedRGB(Collections
										.singleton(feature), project)));
						item.setChecked(featureNameManager
								.isFeatureVisible(feature));
						item.setData(feature);
					}

				setSelectedFeature(oldSelection);
			}
		} finally {
			table.setRedraw(true);
		}
	}

	private void setSelectedFeature(Feature feature) {
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
	}

	private void fillContextMenu(IMenuManager manager) {
		boolean enabled = getSelectedFeature() != null;
		renameAction.setEnabled(enabled);
		selectColorAction.setEnabled(enabled);
		findFeatureCodeAction.setEnabled(enabled);

		manager.add(renameAction);
		manager.add(selectColorAction);
		manager.add(findFeatureCodeAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(filterAction);
	}

	private void makeActions() {
		filterAction = new Action("Filter", IAction.AS_CHECK_BOX) {
			public void run() {
				redraw();
			}
		};
		filterAction.setText("Filter");
		filterAction.setToolTipText("Hide invisible features");
		ColoredIDEImages.setImageDescriptors(filterAction,
				ColoredIDEImages.INTERACTION);

		renameAction = new Action("Rename...") {
			public void run() {
				Feature feature = getSelectedFeature();
				if (feature == null)
					return;
				FeatureNameManager fnm = FeatureNameManager
						.getFeatureNameManager(project);
				String oldName = fnm.getFeatureName(feature);

				InputDialog input = new InputDialog(getSite().getShell(),
						"Rename Feature", "New name:", oldName,
						new IInputValidator() {
							public String isValid(String newText) {
								if (newText.length() == 0)
									return "Name must not be empty";
								return null;
							}
						});
				if (input.open() == InputDialog.OK) {
					fnm.setFeatureName(feature, input.getValue());
				}
			}
		};
		renameAction.setToolTipText("Rename Feature");
		renameAction.setAccelerator(SWT.F2);

		selectColorAction = new Action("Select color...") {
			@Override
			public void run() {
				Feature feature = getSelectedFeature();
				if (feature == null)
					return;

				RGB oldColor = feature.getRGB(project);
				ColorDialog colorDialog = new ColorDialog(getSite().getShell());
				colorDialog.setRGB(oldColor);
				colorDialog.setText("Color for Feature \""
						+ feature.getShortName(project) + "\"");
				RGB newColor = colorDialog.open();
				if (newColor != null && newColor != oldColor) {
					FeatureNameManager.getFeatureNameManager(project)
							.setFeatureColor(feature, newColor);
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

	protected Feature getSelectedFeature() {
		int idx = table.getSelectionIndex();
		if (idx == -1)
			return null;
		return (Feature) table.getItem(idx).getData();
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
