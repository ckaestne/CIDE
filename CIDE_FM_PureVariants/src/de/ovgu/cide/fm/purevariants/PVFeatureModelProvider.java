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

package de.ovgu.cide.fm.purevariants;

import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import com.ps.consul.eclipse.ui.mapping.Mapping;
import com.ps.consul.eclipse.ui.mapping.Util;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelProvider;

public class PVFeatureModelProvider implements IFeatureModelProvider {

	public PVFeatureModelProvider() {
		// TODO Auto-generated constructor stub
	}

	private final WeakHashMap<IProject, PVFeatureModel> cache = new WeakHashMap<IProject, PVFeatureModel>();

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		PVFeatureModel fm = cache.get(project);
		if (fm != null)
			return fm;

		String targetName = project.getName();
		if (targetName.indexOf("_Variability") >= 0)
			throw new FeatureModelNotFoundException(
					"No feature model for a pure::variants model project.");

		String pvProjectName = project.getName() + "_Variability";
		IProject pvProject = project.getWorkspace().getRoot().getProject(
				pvProjectName);
		if (!pvProject.exists())
			try {
				Util.makeStandardMappingProject(pvProjectName);
			} catch (Exception e) {
				e.printStackTrace();
				throw new FeatureModelNotFoundException(
						"Pure::variants model not present and cannot be created.",
						e);
			}

		assert pvProject.exists();

		IFile ccfm = pvProject.getFile("Mapping.ccfm");
		if (!ccfm.exists())
			throw new FeatureModelNotFoundException(
					"Mapping.ccfm file not found in " + pvProjectName);
		IFile vdm = pvProject.getFile("Config/Config.vdm");
		if (!vdm.exists())
			throw new FeatureModelNotFoundException(
					"Config.vdm file not found in " + pvProjectName);

		Mapping mapping = null;
		try {
			mapping = new Mapping(ccfm, vdm);
		} catch (CoreException e) {
			throw new FeatureModelNotFoundException("Cannot create PV Mapping");
		}

		fm = new PVFeatureModel(mapping, project);
		cache.put(project, fm);
		return fm;
	}

	/**
	 * no additional files in project
	 */
	public boolean isFeatureModelFile(IFile file) {
		return false;
	}

}
