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

package de.ovgu.cide.export;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;

/**
 * very simple export wizard with source project
 * 
 * @author ckaestne
 * 
 * @param <T>
 */
public abstract class CIDEExport2Wizard extends
		Wizard {

	protected IProject[] sourceProject;

	public CIDEExport2Wizard(IProject[] sourceProject2) {
		super();
		this.sourceProject = sourceProject2;
	}

	public boolean performFinish() {
		Job job = createExportJob(sourceProject[0],
				getTargetProject());
		if (job == null)
			return false;

		job.setUser(true);
		job.setPriority(Job.LONG);
		job.schedule();

		return true;
	}

	protected abstract IProject getTargetProject();

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		List<?> resources = IDE.computeSelectedResources(selection);
		List<IProject> projects = new ArrayList<IProject>();
		for (Object r : resources)
			if (r instanceof IProject)
				projects.add((IProject) r);
		sourceProject = projects.toArray(new IProject[0]);
	}

	protected abstract Job createExportJob(IProject sourceProject,
			IProject targetProject);

}