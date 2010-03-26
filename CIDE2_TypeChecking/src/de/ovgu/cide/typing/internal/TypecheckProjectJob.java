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

/**
 * 
 */
package de.ovgu.cide.typing.internal;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

import de.ovgu.cide.typing.internal.manager.TypingExtensionManager;
import de.ovgu.cide.typing.model.DebugTyping;
import de.ovgu.cide.typing.model.ITypingProvider;

/**
 * full typecheck of an entire project
 * 
 * @author ckaestne
 * 
 */
class TypecheckProjectJob extends WorkspaceJob {

	private final IProject[] projects;
	private TypingManager typingManager;

	public TypecheckProjectJob(IProject[] projects, TypingManager manager) {
		super("Typechecking Project");
		this.projects = projects;
		this.typingManager = manager;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		for (IProject project : projects)
			if (project.isOpen()) {
				monitor.subTask("Checking " + project.getName());

				// delete old markers
				project.deleteMarkers(TypingMarkerFactory.MARKER_TYPE_ID, true,
						IResource.DEPTH_INFINITE);

				DebugTyping.reset();
				long s = System.currentTimeMillis();
				List<ITypingProvider> typingProviders = TypingExtensionManager
						.getInstance().getTypingProviders(project);
				TypingExtensionManager.registerListener(typingProviders,
						typingManager.listener);
				for (ITypingProvider typingProvider : typingProviders) {
					typingProvider.updateAll(monitor);
				}
				System.out.println("Typing SPL " + project + " in "
						+ (System.currentTimeMillis() - s) + " ms");
				DebugTyping.print();// debug only
			}
		// monitor.worked(5);
		return Status.OK_STATUS;
	}

}