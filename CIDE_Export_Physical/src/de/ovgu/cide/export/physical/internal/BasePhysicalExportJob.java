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

package de.ovgu.cide.export.physical.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.export.BaseExportJob;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;

/**
 * adds functionality for export to physical separation of concerns, based on
 * the concept of derivatives
 * 
 * @author ckaestne
 * 
 */
public abstract class BasePhysicalExportJob<T extends IPhysicalOptions> extends
		BaseExportJob {

	protected T exportOptions;

	public BasePhysicalExportJob(String title, IProject sourceProject,
			IProject targetProject, T exportOptions)
			throws FeatureModelNotFoundException {
		super(title, sourceProject, targetProject);
		this.exportOptions = exportOptions;
	}

	public T getOptions() {
		return exportOptions;
	}

	private final Map<Set<IFeature>, IFolder> derivativeDirectories = new HashMap<Set<IFeature>, IFolder>();
	private IFolder derivativeBaseFolder;

	public IFolder getDerivativeFolder(IProgressMonitor monitor,
			Set<IFeature> derivative) throws CoreException {
		IFolder result = derivativeDirectories.get(derivative);
		if (result != null)
			return result;
		if (exportOptions.getDerivativesInSubdirectories()) {
			if (derivativeBaseFolder == null) {
				derivativeBaseFolder = targetProject.getFolder("derivatives");
				createFolder(derivativeBaseFolder, monitor);
			}
			result = derivativeBaseFolder
					.getFolder(getDerivativeName(derivative));
		} else {
			result = targetProject.getFolder(getDerivativeName(derivative));
		}

		createFolder(result, monitor);
		derivativeDirectories.put(derivative, result);
		return result;
	}

	/**
	 * sort features to always get the same derivativename
	 * 
	 * @param derivative
	 * @return
	 */
	public String getDerivativeName(Set<IFeature> derivative) {
		if (derivative.size() == 0)
			return "";
		if (derivative.size() == 1)
			return featureName2LayerName(derivative.iterator().next().getName());

		ArrayList<IFeature> featureList = new ArrayList<IFeature>(derivative);
		Collections.sort(featureList);

		boolean first = true;
		String result = "";
		for (IFeature f : featureList) {
			if (first)
				first = false;
			else
				result += "_";
			result += featureName2LayerName(f.getName());
		}
		if (!exportOptions.getDerivativesInSubdirectories())
			result = "Derivative_" + result;
		return result;
	}

}
