package coloredide.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.DirectoryColorManager;

public class SetCompUnitColorJob extends WorkspaceJob {

	private Collection<IResource> resources;

	private Set<Feature> features;

	private Set<Feature> removedfeatures;

	private List<DirectoryColorManager> batched = new ArrayList<DirectoryColorManager>();

	public SetCompUnitColorJob(Collection<IResource> resources,
			Set<Feature> features, Set<Feature> removedfeatures) {
		super("Set Compilation Unit Colors");
		this.resources = resources;
		this.features = features;
		this.removedfeatures = removedfeatures;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {

		monitor.beginTask("coloring", resources.size());

		for (IResource resource : resources) {
			if (resource instanceof IFile) {
				IFile file = (IFile) resource;
				DirectoryColorManager colorManager = DirectoryColorManager
						.getColoredDirectoryManagerS(resource.getParent());
				colorManager.beginBatch();
				batched.add(colorManager);

				setFileColors(file, colorManager);
			}

			if (resource instanceof IFolder || resource instanceof IProject) {
				IContainer container = (IContainer) resource;
				DirectoryColorManager colorManager = DirectoryColorManager
						.getColoredDirectoryManagerS(container);
				colorManager.beginBatch();
				batched.add(colorManager);

				setDirColors(colorManager);
			}

			monitor.worked(1);
		}

		for (DirectoryColorManager colorManager : batched) {
			colorManager.endBatch();
		}

		monitor.done();

		return Status.OK_STATUS;
	}

	private void setDirColors(DirectoryColorManager colorManager) {
		for (Feature color : features)
			colorManager.addFolderColor(color);
		for (Feature color : removedfeatures)
			colorManager.removeFolderColor(color);
	}

	private void setFileColors(IFile file, DirectoryColorManager colorManager) {
		for (Feature color : features)
			colorManager.addColor(file, color);
		for (Feature color : removedfeatures)
			colorManager.removeColor(file, color);
	}

}
