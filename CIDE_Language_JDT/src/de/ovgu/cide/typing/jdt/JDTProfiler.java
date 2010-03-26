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

package de.ovgu.cide.typing.jdt;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.BuildContext;
import org.eclipse.jdt.core.compiler.CompilationParticipant;

public class JDTProfiler extends CompilationParticipant {

	private long start;

	public JDTProfiler() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isActive(IJavaProject project) {
		return true;
	}

	@Override
	public void buildFinished(IJavaProject project) {
		super.buildFinished(project);
		System.out.println("Compiled " + project.getProject().getName() + " in "
				+ (System.currentTimeMillis() - start) + " ms");
	}

	@Override
	public void buildStarting(BuildContext[] files, boolean isBatch) {
		start = System.currentTimeMillis();
		super.buildStarting(files, isBatch);
	}
}
