package coloredide.berkeley;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

import coloredide.export2jak.Export2JakJob;
import coloredide.export2jak.JakExportOptions;
import coloredide.export2jak.JakHookMethodHelper;
import coloredide.export2jak.MethodObjectHelper;

public class ExportBerkeleyDBAHEAD {

	@Test
	public void exportBerkeleyDB2Jak() throws CoreException {
		JakExportOptions.resetDefaultAHEAD();
		JakExportOptions.DERIVATIVES_IN_SUBDIRECTORIES = false;
		JakExportOptions.FLATTEN_PACKAGES=true;
		JakExportOptions.REMOVE_PACKAGES_PREFIX="com.sleepycat";

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject sourceProject = root.getProject("Berkeley DB 4 Jak");
		if (!sourceProject.isOpen())
			sourceProject.open(null);
		IProject targetProject = root.getProject("Jak_Berkeley_AHEAD");

		Export2JakJob job = new Export2JakJob("Exporting Berkeley DB",
				sourceProject, targetProject);
//		 job.DEBUG_SKIPTO="FileManager.java";
		IProgressMonitor monitor = new NullProgressMonitor();
		job.runInWorkspace(monitor);
		
		System.out.println("------");
		System.out.println("Number of method objects: "+MethodObjectHelper.debug_numberOfMessageObjects);
		System.out.println("Number of hooks: "+JakHookMethodHelper.debug_getHookIdx());
	}

}
