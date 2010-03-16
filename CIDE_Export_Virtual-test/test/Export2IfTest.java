import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

import de.ovgu.cide.export.virtual.ExportJavaIfWizard;

public class Export2IfTest {

	@Test
	public void testRefactorLocalVariables() throws CoreException {

		System.out.println(ResourcesPlugin.getWorkspace().getRoot().getProjects());
		
		IProject sourceProject=ResourcesPlugin.getWorkspace().getRoot().getProject("test");
		Assert.assertTrue(sourceProject.exists());
		IProject targetProject=ResourcesPlugin.getWorkspace().getRoot().getProject("testtarget");
		new ExportJavaIfWizard().createExportJob(sourceProject, targetProject).runInWorkspace(new NullProgressMonitor());//.schedule();
		
	}

}
