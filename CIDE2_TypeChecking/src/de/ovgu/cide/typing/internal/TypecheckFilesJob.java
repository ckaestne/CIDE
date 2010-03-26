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

package de.ovgu.cide.typing.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.typing.internal.manager.TypingExtensionManager;
import de.ovgu.cide.typing.model.ITypingProvider;

/**
 * type checks one or several files in the same project
 * 
 * @author ckaestne
 * 
 */
public class TypecheckFilesJob extends WorkspaceJob {

	private TypingManager typingManager;
	private final Collection<ColoredSourceFile> files;
	private final IProject project;

	public TypecheckFilesJob(IProject project, List<ColoredSourceFile> files,
			TypingManager manager) {
		super("Typechecking Project");
		this.project = project;
		this.files = files;
		this.typingManager = manager;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		List<ITypingProvider> typingProviders = TypingExtensionManager
				.getInstance().getTypingProviders(project);
		TypingExtensionManager.registerListener(typingProviders,
				typingManager.listener);
		for (ITypingProvider typingProvider : typingProviders) {
			typingProvider.updateFile(files, monitor);
		}
		// monitor.worked(5);
		return Status.OK_STATUS;
	}

}
