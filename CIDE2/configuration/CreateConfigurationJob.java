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

package coloredide.configuration;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredJavaSourceFile;

public class CreateConfigurationJob extends WorkspaceJob {

	private final IWorkspaceRoot root;

	private final IProject sourceProject;

	private final Set<Feature> selectedFeatures;

	private final IProject targetProject;

	public CreateConfigurationJob(IProject sourceProject,
			Set<Feature> selectedFeatures, String projectName) {
		super("Creating Configuration: " + sourceProject.getName() + " -> "
				+ projectName);
		this.sourceProject = sourceProject;
		root = ResourcesPlugin.getWorkspace().getRoot();
		this.targetProject = root.getProject(projectName);
		this.selectedFeatures = selectedFeatures;
	}

	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		IJavaProject sourceJavaProject = JavaCore.create(sourceProject);
		int compUnitCount = countJavaProject(sourceJavaProject);

		monitor.beginTask("Creating Configuration", compUnitCount+3);

		if (targetProject.exists()) {
			monitor.subTask("Removing existing project.");
			targetProject.delete(true,  new SubProgressMonitor(monitor, 0));
		}
		monitor.worked(1);

		monitor.subTask("Creating target project.");
		targetProject.create(sourceProject.getDescription(),  new SubProgressMonitor(monitor, 0));
		targetProject.open( new SubProgressMonitor(monitor, 0));
		monitor.worked(1);
		monitor.subTask("Configuring new project.");
		IFile cpFile = sourceProject.getFile(".classpath");
		if (cpFile.exists())
			cpFile.copy(targetProject.getFile(".classpath").getFullPath(),
					true, new SubProgressMonitor(monitor, 0));
		monitor.worked(1);


		IJavaProject targetJavaProject = JavaCore.create(targetProject);
		configureJavaProject(sourceJavaProject, targetJavaProject, monitor);

		monitor.done();
		return Status.OK_STATUS;
	}

	private void configureJavaProject(IJavaProject sourceJavaProject,
			IJavaProject targetJavaProject, IProgressMonitor monitor)
			throws CoreException {
		monitor.subTask("Configuring Project "
				+ targetJavaProject.getProject().getName());

		for (IPackageFragmentRoot root : sourceJavaProject
				.getPackageFragmentRoots()) {
			if (monitor.isCanceled())
				return;
			if (!root.exists())
				continue;
			if (root.getKind() == IPackageFragmentRoot.K_BINARY)
				continue;

			IPackageFragmentRoot targetRoot = copySourceFolder(root,
					targetJavaProject);
			configurePackageFragementRoot(sourceJavaProject, root, targetRoot,
					monitor);
		}
	}

	private void configurePackageFragementRoot(IJavaProject sourceJavaProject,
			IPackageFragmentRoot sourceRoot, IPackageFragmentRoot targetRoot,
			IProgressMonitor monitor) throws CoreException {
		for (IPackageFragment pkg : sourceJavaProject.getPackageFragments()) {
			if (monitor.isCanceled())
				return;
			if (pkg.getKind() == IPackageFragmentRoot.K_BINARY)
				continue;
			if (!sourceRoot.getPackageFragment(pkg.getElementName()).exists())
				continue;

			IPackageFragment targetPackage = targetRoot.createPackageFragment(
					pkg.getElementName(), true,  new SubProgressMonitor(monitor, 0));
			configurePackage(pkg, targetPackage, monitor);
			if (pkg.getCompilationUnits().length == 0)
				pkg.delete(false,  new SubProgressMonitor(monitor, 0));
		}

	}

	private void configurePackage(IPackageFragment sourcePackage,
			IPackageFragment targetPackage, IProgressMonitor monitor)
			throws CoreException {
		for (ICompilationUnit compUnit : sourcePackage.getCompilationUnits()) {
			if (monitor.isCanceled())
				return;

			ColoredJavaSourceFile sourceFile = ColoredJavaSourceFile
					.getColoredJavaSourceFile(compUnit);
			String configuredSource;
			try {
				configuredSource = configureSource(sourceFile, monitor);
			} catch (Exception e) {
				System.out.println(compUnit);
				e.printStackTrace();
				configuredSource = "";
			}
			if (!configuredSource.trim().equals(""))
				targetPackage.createCompilationUnit(compUnit.getElementName(),
						configuredSource, true,  new SubProgressMonitor(monitor, 0));
		}
	}

	private String configureSource(ColoredJavaSourceFile sourceFile,
			IProgressMonitor monitor) throws CoreException {
		if (monitor.isCanceled())
			return "";
		String n = "...";
		if (sourceFile.getCompilationUnit().getTypes().length > 0)
			n = sourceFile.getCompilationUnit().getTypes()[0].getFullyQualifiedName();
		monitor.subTask("Configuring "+ n);

		try {
			Set<Feature> hiddenColors = new HashSet<Feature>();
			hiddenColors.addAll(FeatureManager
					.getVisibleFeatures(sourceProject));
			hiddenColors.removeAll(selectedFeatures);
			return DeleteHiddenNodesVisitor.hideCode(sourceFile, hiddenColors);
		} finally {
			monitor.worked(1);
		}
	}

	private IPackageFragmentRoot copySourceFolder(IPackageFragmentRoot source,
			IJavaProject targetJavaProject) throws CoreException {

		IPackageFragmentRoot result = null;
		if (source.getResource() instanceof IFolder) {
			IPath path = source.getPath().makeAbsolute();
			path = path.removeFirstSegments(1);// remove project
			IFolder folder = targetJavaProject.getProject().getFolder(path);
			folder.create(false, true, null);
			result = targetJavaProject.getPackageFragmentRoot(folder);
		}
		if (source.getResource() instanceof IProject) {
			result = targetJavaProject.getPackageFragmentRoot(targetJavaProject
					.getProject());
		}
		if (result != null) {
			IClasspathEntry[] oldEntries = targetJavaProject.getRawClasspath();
			boolean containsPath = false;
			for (IClasspathEntry entry : oldEntries) {
				if (entry.getPath().equals(result.getPath()))
					containsPath = true;
			}
			if (!containsPath) {
				IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
				System.arraycopy(oldEntries, 0, newEntries, 0,
						oldEntries.length);
				newEntries[oldEntries.length] = JavaCore.newSourceEntry(result
						.getPath());
				targetJavaProject.setRawClasspath(newEntries, null);
			}
		}
		return result;
	}

	private int countJavaProject(IJavaProject sourceJavaProject)
			throws CoreException {
		int sum = 0;
		for (IPackageFragmentRoot root : sourceJavaProject
				.getPackageFragmentRoots()) {
			if (!root.exists())
				continue;
			if (root.getKind() == IPackageFragmentRoot.K_BINARY)
				continue;

			sum += countPackageFragementRoot(sourceJavaProject, root);
		}
		return sum;
	}

	private int countPackageFragementRoot(IJavaProject sourceJavaProject,
			IPackageFragmentRoot sourceRoot) throws CoreException {
		int sum = 0;
		for (IPackageFragment pkg : sourceJavaProject.getPackageFragments()) {
			if (pkg.getKind() == IPackageFragmentRoot.K_BINARY)
				continue;
			if (!sourceRoot.getPackageFragment(pkg.getElementName()).exists())
				continue;

			sum += countPackage(pkg);
		}
		return sum;
	}

	private int countPackage(IPackageFragment sourcePackage)
			throws CoreException {
		return sourcePackage.getCompilationUnits().length;
	}

}
