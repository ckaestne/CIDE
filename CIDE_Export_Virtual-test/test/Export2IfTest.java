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
