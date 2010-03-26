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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;

public abstract class BaseJavaFileExporter {

	public Set<Set<IFeature>> seenDerivatives = new HashSet<Set<IFeature>>();

	protected ICompilationUnit compUnit;

	protected IProgressMonitor monitor;

	protected IFile file;

	protected IContainer folder;

	public BaseJavaFileExporter(IContainer folder, IFile file,
			ICompilationUnit compUnit, IProgressMonitor monitor) {
		this.compUnit = compUnit;
		this.monitor = monitor;
		this.file = file;
		this.folder = folder;
	}

	public abstract void execute() throws CoreException, FeatureModelNotFoundException;

}