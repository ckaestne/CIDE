package coloredide.configuration;

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

import coloredide.features.FeatureModelManager;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.DirectoryColorManager;

public class CreateConfigurationJob extends WorkspaceJob {

	private final IWorkspaceRoot root;

	private final IProject sourceProject;

	private final Set<IFeature> selectedFeatures;

	private final IProject targetProject;

	private IFeatureModel featureModel;

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

		if ("color".equals(file.getFileExtension()))
			return;
		if ("colors".equals(file.getFileExtension()))
			return;
		if (DirectoryColorManager.DIRCOLOR_FILENAME.equals(file.getName()))
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
