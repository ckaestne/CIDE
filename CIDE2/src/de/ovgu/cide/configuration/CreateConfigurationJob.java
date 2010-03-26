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

package de.ovgu.cide.configuration;

import java.io.ByteArrayInputStream;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.DirectoryColorManager;
import de.ovgu.cide.features.source.IStorageProvider;
import de.ovgu.cide.features.source.StorageProviderManager;

public class CreateConfigurationJob extends WorkspaceJob {

	private final IWorkspaceRoot root;

	private final IProject sourceProject;

	private final Set<IFeature> selectedFeatures;

	private final IProject targetProject;

	private IFeatureModel featureModel;

	private IStorageProvider storageProvider;

	public CreateConfigurationJob(IProject sourceProject,
			Set<IFeature> selectedFeatures, String projectName) {
		super("Generating Variant: " + sourceProject.getName() + " -> "
				+ projectName);
		this.sourceProject = sourceProject;
		root = ResourcesPlugin.getWorkspace().getRoot();
		this.targetProject = root.getProject(projectName);
		this.selectedFeatures = selectedFeatures;

	}

	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		int coloredFileCount = countColoredFiles(sourceProject);

		monitor.beginTask("Generating Variant", coloredFileCount + 3);

		if (targetProject.exists()) {
			monitor.subTask("Removing existing project.");
			targetProject.delete(true, new SubProgressMonitor(monitor, 0));
		}
		monitor.worked(1);

		monitor.subTask("Creating target project.");
		targetProject.create(sourceProject.getDescription(),
				new SubProgressMonitor(monitor, 0));
		targetProject.open(new SubProgressMonitor(monitor, 0));
		monitor.worked(1);
		monitor.subTask("Generating new project variant.");
		IFile cpFile = sourceProject.getFile(".classpath");
		if (cpFile.exists())
			cpFile.copy(targetProject.getFile(".classpath").getFullPath(),
					true, new SubProgressMonitor(monitor, 0));
		monitor.worked(1);

		featureModel = FeatureModelManager.getInstance().getFeatureModelCore(
				sourceProject);
		storageProvider = StorageProviderManager.getInstance()
				.getStorageProvider(sourceProject, featureModel);

		configureProject(sourceProject, targetProject, monitor);

		monitor.done();
		return Status.OK_STATUS;
	}

	private int countColoredFiles(IContainer directory) throws CoreException {
		int count = 0;
		for (IResource resource : directory.members()) {
			if (resource.getType() == IResource.FOLDER)
				count += countColoredFiles((IContainer) resource);
			if (resource.getType() == IResource.FILE) {
				IFile file = (IFile) resource;
				if ("color".equals(file.getFileExtension()))
					count++;
			}
		}
		return count;
	}

	private void configureProject(IProject sourceProject,
			IProject targetProject, IProgressMonitor monitor)
			throws CoreException {
		monitor.subTask("Generating Project Variant "
				+ targetProject.getProject().getName());

		configureContainer(sourceProject, monitor);
	}

	private void configureContainer(IContainer container,
			IProgressMonitor monitor) throws CoreException {
		if (skipColoredContainer(container))
			return;

		for (IResource resource : container.members()) {
			if (monitor.isCanceled())
				return;
			if (!resource.exists())
				continue;
			if (resource.getType() == IResource.FOLDER)
				configureContainer((IContainer) resource, monitor);
			if (resource.getType() == IResource.FILE)
				configureFile((IFile) resource, monitor);
		}

	}

	private void configureFile(IFile file, IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return;

		if (FeatureModelManager.getInstance().isFeatureModelFile(file))
			return;
		if (storageProvider.isColorStorageFile(file))
			return;

		// check whether the whole file is colored and should be removed
		if (skipColoredFile(file))
			return;

		ColoredSourceFile sourceFile = ColoredSourceFile.getColoredSourceFile(
				file, featureModel);

		if (!sourceFile.isColored()) {
			IFile targetFile = targetProject.getFile(file.getFullPath()
					.removeFirstSegments(1));
			ensureDirectoryExists(targetFile, monitor);
			if (!targetFile.exists())
				file.copy(targetFile.getFullPath(), true, monitor);
			return;
		}

		String configuredSource;
		try {
			configuredSource = configureSource(sourceFile, monitor);
		} catch (Exception e) {
			System.out.println(file);
			e.printStackTrace();
			configuredSource = "";
		}
		if (!configuredSource.trim().equals("")) {
			IFile targetFile = targetProject.getFile(file.getFullPath()
					.removeFirstSegments(1));
			ensureDirectoryExists(targetFile, monitor);
			targetFile.create(new ByteArrayInputStream(configuredSource
					.getBytes()), true, monitor);
		}
	}

	private boolean skipColoredFile(IFile file) {
		Set<IFeature> fileColors = getFileColors(file);
		for (IFeature fileColor : fileColors)
			if (!selectedFeatures.contains(fileColor))
				return true;
		return false;
	}

	private boolean skipColoredContainer(IContainer dir) {
		DirectoryColorManager colorManager = DirectoryColorManager
				.getColoredDirectoryManagerS(dir, featureModel);
		if (colorManager == null)
			return false;
		Set<IFeature> folderColors = colorManager.getFolderColors();
		for (IFeature folderColor : folderColors)
			if (!selectedFeatures.contains(folderColor))
				return true;
		return false;
	}

	private Set<IFeature> getFileColors(IFile file) {
		DirectoryColorManager colorManager = DirectoryColorManager
				.getColoredDirectoryManagerS(file.getParent(), featureModel);
		return colorManager.getColors(file);
	}

	private void ensureDirectoryExists(IResource resource,
			IProgressMonitor monitor) throws CoreException {
		if (resource.getParent() instanceof IFolder) {
			ensureDirectoryExists(resource.getParent(), monitor);
		}

		if (resource instanceof IFolder) {
			IFolder folder = (IFolder) resource;
			if (!folder.exists()) {
				folder.create(true, true, monitor);
			}
		}
	}

	private String configureSource(ColoredSourceFile sourceFile,
			IProgressMonitor monitor) throws ConfigurationException {
		if (monitor.isCanceled())
			return "";
		monitor.subTask("Generating " + sourceFile.getName());

		try {
			IConfigurationMechanism mechanism = ConfigurationMechanismManager
					.getInstance().getConfigurationMechanism(sourceFile);
			return mechanism.configureFile(sourceFile, selectedFeatures);
		} finally {
			monitor.worked(1);
		}
	}

}
