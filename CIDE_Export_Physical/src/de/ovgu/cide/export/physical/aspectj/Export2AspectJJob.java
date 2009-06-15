package de.ovgu.cide.export.physical.aspectj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import de.ovgu.cide.export.BaseJavaFileExporter;
import de.ovgu.cide.export.physical.internal.BasePhysicalExportJob;
import de.ovgu.cide.export.physical.internal.IPhysicalOptions;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;

public class Export2AspectJJob extends BasePhysicalExportJob<IPhysicalOptions> {

	public Export2AspectJJob(String title, IProject sourceProject,
			IProject targetProject, IPhysicalOptions options)
			throws FeatureModelNotFoundException {
		super(title, sourceProject, targetProject, options);
		// This hashmap will be used to get the aspects implementing features.
		allAspects = new HashMap<Set<IFeature>, AspectJCompilationUnit>();
	}

	final HashMap<Set<IFeature>, AspectJCompilationUnit> allAspects;

	protected void createProject(IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("Creating AspectJ Project", 2);

		if (targetProject.exists())
			targetProject.delete(true, monitor);
		monitor.worked(1);
		targetProject.create(sourceProject.getDescription(), monitor);
		targetProject.open(monitor);
		monitor.worked(1);

		monitor.done();
	}

	protected void finishExport(IProgressMonitor monitor) throws CoreException {
		writeAspects(monitor);

		writeBuildPathes(monitor);
		super.finishExport(monitor);
	}

	private void writeBuildPathes(IProgressMonitor monitor) {
		IClasspathEntry[] newClasspath = new IClasspathEntry[seenDerivatives
				.size()];
		IPath sourceFolder = new Path("/" + targetProject.getName() + "/base");
		IPath outputLocation = new Path("/" + targetProject.getName() + "/bin");
		newClasspath[0] = JavaCore.newSourceEntry(sourceFolder, // source folder
				// location
				new IPath[] { new Path("/" + targetProject.getName()) }, // excluded
				// nested
				// folder
				outputLocation); // output location

		int i = 1;
		for (Set<IFeature> derivative : seenDerivatives) {
			if (getDerivativeName(derivative).length() == 0)
				continue;

			// IClasspathEntry srcEntry = JavaCore.newSourceEntry(new
			// Path("/MyProject/src"));
			// newClasspath[i++] = JavaCore.newSourceEntry(new
			// Path("/"+targetProject.getName()+"/"+getDerivativeName(derivative)));
			sourceFolder = new Path("/" + targetProject.getName()
					+ (derivative.size() > 1 ? "/derivatives" : "") + "/"
					+ getDerivativeName(derivative));
			outputLocation = new Path("/" + targetProject.getName() + "/bin");
			newClasspath[i++] = JavaCore.newSourceEntry(sourceFolder, // source
					// folder
					// location
					new IPath[] { new Path("/" + targetProject.getName()
							+ (derivative.size() > 1 ? "/derivatives" : "")) }, // excluded
					// nested
					// folder
					outputLocation); // output location
		}

		try {
			JavaCore.create(targetProject).setRawClasspath(newClasspath,
					monitor);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeAspects(IProgressMonitor monitor) throws CoreException {
		for (Set<IFeature> derivative : allAspects.keySet()) {
			IFolder featureDir = null;
			if (derivative.size() == 1)
				featureDir = getFeatureDirectory(derivative.iterator().next());
			else if (derivative.size() > 1)
				featureDir = getDerivativeFolder(monitor, derivative);

			if (featureDir == null)
				continue;

			AspectJCompilationUnit aspect = allAspects.get(derivative);
			String aspectSrc = aspect.getSource();
			aspectSrc = aspectSrc.replaceAll("_dot_", ".");
			IFile targetFile = featureDir.getFile(sourceProject
					.getProjectRelativePath().append(
							getDerivativeName(derivative) + ".aj"));
			// AspectJPrettyPrinter p = new AspectJPrettyPrinter();
			// allAspects.get(derivative).accept(p);
			// p.getResult()
			InputStream source = new ByteArrayInputStream(aspectSrc.getBytes());
			targetFile.create(source, true, monitor);
		}

	}

	protected BaseJavaFileExporter createExportJavaFileJob(IContainer folder,
			IFile file, ICompilationUnit compUnit, IProgressMonitor monitor) {
		return new ExportJavaFileJobAJ(folder, file, compUnit, monitor, this);
	}

}