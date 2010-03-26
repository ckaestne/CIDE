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

package de.ovgu.cide.export.virtual.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;

public class Export2PPJob extends BaseExportJob {

	private IPPExportOptions options;

	public Export2PPJob(String title, IProject sourceProject,
			IProject targetProject, IPPExportOptions options)
			throws FeatureModelNotFoundException {
		super(title, sourceProject, targetProject);
		this.options = options;
	}

	@Override
	protected void createProject(IProgressMonitor monitor) throws CoreException {

		if (targetProject.exists())
			targetProject.delete(true, monitor);

		targetProject.create(/* projDesc, */monitor);

		targetProject.open(monitor);
	}

	@Override
	protected ExportPPJavaFileJob createExportJavaFileJob(IContainer folder,
			IFile file, ICompilationUnit compUnit, IProgressMonitor monitor) {
		return new ExportPPJavaFileJob(folder, file, compUnit, monitor, this,
				options);
	}

	protected void createFeatureDirectories(IProgressMonitor monitor)
			throws CoreException {
		baseFolder = targetProject.getFolder("java");
		createFolder(baseFolder, monitor);
	}

}
