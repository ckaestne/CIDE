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

package de.ovgu.cide.typing.internal.resolution;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

import de.ovgu.cide.typing.CIDETypingPlugin;
import de.ovgu.cide.typing.internal.TypingMarkerFactory;
import de.ovgu.cide.typing.model.ITypingCheck;
import de.ovgu.cide.typing.model.ITypingCheckWithResolution;

public class ResolutionGenerator implements IMarkerResolutionGenerator {

	public IMarkerResolution[] getResolutions(IMarker marker) {
		ITypingCheck check = findResonsibleCheck(marker);

		if (check instanceof ITypingCheckWithResolution)
			return ((ITypingCheckWithResolution) check).getResolutions(marker);

		return ITypingCheckWithResolution.NO_RESOLUTIONS;
	}

	/**
	 * searches for a check that corresponds to the marker. may return null if
	 * check is not found or marker has nothing to do with typechecking SPLs
	 * 
	 * @param marker
	 * @return check or null
	 */
	private ITypingCheck findResonsibleCheck(IMarker marker) {
		try {
			if (marker == null)
				return null;
			// is type-checking marker?
			if (!marker.getType().equals(TypingMarkerFactory.MARKER_TYPE_ID))
				return null;

			Set<ITypingCheck> knownChecks = CIDETypingPlugin.getDefault()
					.getTypingManager().getKnownChecks();
			for (ITypingCheck check : knownChecks) {
				boolean match = marker.getResource().equals(
						check.getFile().getResource());
				if (match)
					match = check.getProblemType().equals(
							marker.getAttribute(
									TypingMarkerFactory.PARAM_PROBLEMTYPE,
									(String) null));
				if (match)
					match = check.getSource().getId().equals(
							marker.getAttribute(
									TypingMarkerFactory.PARAM_PROBLEMDATA,
									(String) null));

				if (match)
					return check;
			}

			return null;
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
	}

}
