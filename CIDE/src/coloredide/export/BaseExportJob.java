package coloredide.export;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;

import coloredide.export2jak.JakExportOptions;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;

public abstract class BaseExportJob extends WorkspaceJob {

	protected final IProject sourceProject;

	protected abstract void createProject(IProgressMonitor monitor)
			throws CoreException;

	protected final IProject targetProject;

	protected List<Feature> features;

	protected final Set<Set<Feature>> seenDerivatives = new HashSet<Set<Feature>>();

	protected String title;

	private static int countFiles(IContainer container) throws CoreException {
		int result = 0;
		for (IResource member : container.members()) {
			if (member instanceof IFile && member.exists())
				result++;
			if (member instanceof IFolder)
				result += countFiles((IContainer) member);
		}
		return result;
	}

	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		int work = countFiles(sourceProject) + 12;
		// System.out.println(title + ": " + work);
		monitor.beginTask(title, work);
		try {

			createProject(new SubProgressMonitor(monitor, 10));

			createFeatureDirectories(new SubProgressMonitor(monitor, 2));

			exportProject(sourceProject, monitor);

			finishExport(monitor);

		} catch (CoreException e) {
			e.printStackTrace();
			throw e;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}

	protected void finishExport(IProgressMonitor monitor) throws CoreException {

	}

	private final Map<Feature, IFolder> featureDirectories = new HashMap<Feature, IFolder>();

	private final Map<Set<Feature>, IFolder> derivativeDirectories = new HashMap<Set<Feature>, IFolder>();

	protected IFolder baseFolder;

	private IFolder derivativeBaseFolder;

	public static void createFile(IFile file, InputStream source,
			IProgressMonitor monitor) throws CoreException {
		assert file != null;

		IContainer container = file.getParent();
		if (!container.exists() && container instanceof IFolder)
			createFolder((IFolder) container, monitor);
		if (file.exists())
			file.setContents(source, true, true, monitor);
		file.create(source, true, monitor);
	}

	protected void createFeatureDirectories(IProgressMonitor monitor)
			throws CoreException {
		baseFolder = targetProject.getFolder("base");
		createFolder(baseFolder, monitor);
		for (Feature f : features) {
			IFolder folder = targetProject.getFolder(featureName2LayerName(f
					.getShortName(sourceProject)));
			createFolder(folder, monitor);
			featureDirectories.put(f, folder);
		}
	}

	public IFolder getDerivativeFolder(IProgressMonitor monitor,
			Set<Feature> derivative) throws CoreException {
		IFolder result = derivativeDirectories.get(derivative);
		if (result != null)
			return result;
		if (JakExportOptions.DERIVATIVES_IN_SUBDIRECTORIES) {
			if (derivativeBaseFolder == null) {
				derivativeBaseFolder = targetProject.getFolder("derivatives");
				createFolder(derivativeBaseFolder, monitor);
			}
			result = derivativeBaseFolder
					.getFolder(getDerivativeName(derivative));
		} else {
			result = targetProject.getFolder(getDerivativeName(derivative));
		}

		createFolder(result, monitor);
		derivativeDirectories.put(derivative, result);
		return result;
	}

	/**
	 * sort features to always get the same derivativename
	 * 
	 * @param derivative
	 * @return
	 */
	public String getDerivativeName(Set<Feature> derivative) {
		if (derivative.size() == 0)
			return "";
		if (derivative.size() == 1)
			return featureName2LayerName(derivative.iterator().next()
					.getShortName(sourceProject));

		ArrayList<Feature> featureList = new ArrayList<Feature>(derivative);
		Collections.sort(featureList);

		boolean first = true;
		String result = "";
		for (Feature f : featureList) {
			if (first)
				first = false;
			else
				result += "_";
			result += featureName2LayerName(f.getShortName(sourceProject));
		}
		if (!JakExportOptions.DERIVATIVES_IN_SUBDIRECTORIES)
			result = "Derivative_" + result;
		return result;
	}

	/**
	 * removes whitespaces and potentially other illegal characters from layer
	 * and directory names
	 * 
	 * @param shortName
	 * @return
	 */
	protected String featureName2LayerName(String name) {
		return name.replaceAll(" ", "");
	}

	private void exportProject(IProject sourceProject, IProgressMonitor monitor)
			throws CoreException {
		exportContainer(sourceProject, monitor);
	}

	private void exportContainer(IContainer folder, IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return;

		for (IResource resource : folder.members()) {
			if (resource instanceof IFolder) {
				IFolder subfolder = (IFolder) resource;
				exportContainer(subfolder, monitor);
			}
			if (resource instanceof IFile) {
				exportFile(folder, (IFile) resource, new SubProgressMonitor(
						monitor, 1));
			}
		}

	}

	private void exportFile(IContainer folder, IFile file,
			IProgressMonitor monitor) throws CoreException {
		if (file == null || !file.exists())
			return;
		if (monitor.isCanceled())
			return;

		monitor.subTask(file.getName());
		monitor.worked(1);

		// dont copy color files
		String fileExt = file.getFileExtension();
		if (fileExt != null && fileExt.equals("color"))
			return;

		IContentDescription cd = file.getContentDescription();
		IContentType ct = null;
		if (cd != null)
			ct = cd.getContentType();
		if (ct != null && ct.getId().equals(JavaCore.JAVA_SOURCE_CONTENT_TYPE)) {
			IJavaElement javaElement = JavaCore.create(file);
			if (javaElement instanceof ICompilationUnit) {
				BaseJavaFileExporter exportJob = createExportJavaFileJob(
						folder, file, (ICompilationUnit) javaElement, monitor);
				exportJob.execute();
				seenDerivatives.addAll(exportJob.seenDerivatives);
			} else
				copyFileToBaseDir(file, monitor);
		}
		// TODO deactivated coping of other files than java files (to speed up
		// export during testing). to reactive move the copyFileToBaseDir line
		// here.
	}

	protected abstract BaseJavaFileExporter createExportJavaFileJob(
			IContainer folder, IFile file, ICompilationUnit compUnit,
			IProgressMonitor monitor);

	protected void copyFileToBaseDir(IFile file, IProgressMonitor monitor)
			throws CoreException {
		assert file.exists();
		IPath relativePath = file.getProjectRelativePath();
		IFolder folder = baseFolder.getFolder(relativePath
				.removeLastSegments(1));
		createFolder(folder, monitor);
		IPath path = baseFolder.getFullPath().append(relativePath);
		file.copy(path, true, monitor);
	}

	public IFolder getFeatureDirectory(Feature feature) {
		return featureDirectories.get(feature);
	}

	public IContainer getBaseFolder() {
		return baseFolder;
	}

	public static void createFolder(IFolder folder, IProgressMonitor monitor)
			throws CoreException {
		assert folder != null;

		if (!folder.exists()) {
			if (folder.getParent() instanceof IFolder)
				createFolder((IFolder) folder.getParent(), monitor);
			folder.create(true, true, monitor);
		}
	}

	public BaseExportJob(String title, IProject sourceProject,
			IProject targetProject) {
		super(title);
		this.title = title;
		this.sourceProject = sourceProject;
		this.targetProject = targetProject;
		this.features = FeatureManager.getVisibleFeatures(sourceProject);
	}

}