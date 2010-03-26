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

package de.ovgu.cide.export.physical.ahead.validator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.cide.export.physical.ahead.UnsupportedColoring;

public class JakColorCheckMarker {

	public static final String TYPEID = "de.ovgu.cide.export.jak.jakColorWarning";

	public static void createMarker(IResource resource, UnsupportedColoring u)
			throws CoreException {
		IMarker marker = resource.createMarker(JakColorCheckMarker.TYPEID);
		marker.setAttribute(IMarker.MESSAGE,
				"Invalid Color for Jak Refactoring: " + u.toString());
		marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
		marker.setAttribute(IMarker.CHAR_START, u.getStartPosition());
		marker.setAttribute(IMarker.CHAR_END, u.getStartPosition()
				+ u.getLength());
	}
}
