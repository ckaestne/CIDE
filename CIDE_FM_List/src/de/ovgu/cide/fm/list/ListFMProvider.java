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

package de.ovgu.cide.fm.list;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelProvider;

public class ListFMProvider implements IFeatureModelProvider {

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		if (project == null || !project.exists())
			throw new FeatureModelNotFoundException();
		return ListFeatureModel.getInstance(project);
	}

	public boolean isFeatureModelFile(IFile file) {
		return ".colors".equals(file.getName())
				&& file.getParent() == file.getProject();
	}

	// public Iproje

}
