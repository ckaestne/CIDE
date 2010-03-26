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

package de.ovgu.cide.navigator;

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

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.DirectoryColorManager;
import de.ovgu.cide.utils.ColorHelper;
import de.ovgu.cide.utils.SelectFeatureSetWizard;

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
//		IResource[] p;

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
