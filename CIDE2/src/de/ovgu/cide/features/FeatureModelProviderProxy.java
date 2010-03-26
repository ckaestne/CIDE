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

package de.ovgu.cide.features;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class FeatureModelProviderProxy implements IFeatureModelProvider {

	private final IConfigurationElement configElement;

	public FeatureModelProviderProxy(IConfigurationElement configurationElement) {
		this.configElement = configurationElement;
		name = configElement.getAttribute("name");
		id = configElement.getAttribute("id");
	}

	private final String name;
	private final String id;
	private IFeatureModelProvider target = null;

	private void loadTarget() {
		try {
			target = (IFeatureModelProvider) configElement
					.createExecutableExtension("provider");
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Feature Model Provider: " + name + " (" + id + ")";
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public IFeatureModel getFeatureModel(IProject project)
			throws FeatureModelNotFoundException {
		if (target == null)
			loadTarget();
		return target.getFeatureModel(project);
	}

	public boolean isFeatureModelFile(IFile file) {
		if (target == null)
			loadTarget();
		return target.isFeatureModelFile(file);
	}

}