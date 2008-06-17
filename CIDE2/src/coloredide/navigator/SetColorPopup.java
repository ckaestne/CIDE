package coloredide.navigator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import coloredide.features.FeatureModelManager;
import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.features.source.DirectoryColorManager;
import coloredide.utils.ColorHelper;
import coloredide.utils.SelectFeatureSetWizard;

public class SetColorPopup implements IWorkbenchWindowActionDelegate,
		IObjectActionDelegate {

	private final List<IResource> resources = new ArrayList<IResource>();

	protected IWorkbenchPart part;

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		IResource[] p;

		assert !resources.isEmpty();

		IProject project = resources.get(0).getProject();
		for (IResource r : resources) {
			if (r.getProject() != project) {
				MessageBox messageBox = new MessageBox(Display.getCurrent()
						.getActiveShell(), SWT.OK);
				messageBox
						.setText("Unsupported selection. Select resources from a single project only.");
				messageBox.open();
				return;
			}
		}

		if (!resources.isEmpty()) {
			try {
				IFeatureModel fm = FeatureModelManager.getInstance()
						.getFeatureModel(project);
				SelectFeatureSetWizard wizard = new SelectFeatureSetWizard(
						ColorHelper.sortFeatures(fm.getVisibleFeatures()), null);
				calcInitialSelection(resources, wizard, fm);
				WizardDialog dialog = new WizardDialog(new Shell(), wizard);
				dialog.create();
				dialog.open();
				Set<IFeature> features = wizard.getSelectedFeatures();
				Set<IFeature> removedfeatures = wizard.getNotSelectedFeatures();
				if (features != null && removedfeatures != null
						&& (features.size() + removedfeatures.size() > 0)) {
					WorkspaceJob op = new SetCompUnitColorJob(resources,
							features, removedfeatures);
					op.setUser(true);
					op.schedule();
				}
			} catch (FeatureModelNotFoundException e) {
				MessageBox messageBox = new MessageBox(Display.getCurrent()
						.getActiveShell(), SWT.OK);
				messageBox.setText("Cannot instanciate feature model.");
				messageBox.open();
			}
		}

	}

	private void calcInitialSelection(List<IResource> resources2,
			SelectFeatureSetWizard wizard, IFeatureModel fm) {
		Set<IFeature> selected = null;
		Set<IFeature> grayed = new HashSet<IFeature>();
		for (IResource r : resources2) {
			Set<IFeature> colors = null;
			if (r instanceof IFile) {
				DirectoryColorManager c = DirectoryColorManager
						.getColoredDirectoryManagerS(r.getParent(), fm);
				colors = c.getOwnColors((IFile) r);
			}
			if (r instanceof IFolder || r instanceof IProject) {
				DirectoryColorManager c = DirectoryColorManager
						.getColoredDirectoryManagerS((IContainer) r, fm);
				colors = c.getOwnFolderColors();
			}
			if (colors != null) {
				if (selected == null)
					selected = colors;
				else {
					detectedGrayed(selected, grayed, colors);
				}
			}
		}

		wizard.p.setInitialSelection(selected, grayed);
	}

	private void detectedGrayed(Set<IFeature> selected, Set<IFeature> grayed,
			Set<IFeature> colors) {
		if (selected.equals(colors))
			return;
		for (IFeature f : selected) {
			if (!colors.contains(f))
				grayed.add(f);
		}
		for (IFeature f : colors) {
			if (!selected.contains(f))
				grayed.add(f);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		resources.clear();
		if (selection instanceof IStructuredSelection) {
			for (Object selected : ((IStructuredSelection) selection).toArray()) {
				if (selected instanceof IResource) {
					resources.add((IResource) selected);
				}

			}
		}
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}
}
