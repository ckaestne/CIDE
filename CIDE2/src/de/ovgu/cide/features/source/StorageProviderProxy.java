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

package de.ovgu.cide.features.source;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;

import de.ovgu.cide.alternativefeatures.Alternative;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.IFeatureModelWithID;

public class StorageProviderProxy implements IStorageProvider {

	public boolean isCompatible(IFeatureModel featureModel) {
		if (target == null)
			loadTarget();
		return target.isCompatible(featureModel);
	}

	public Map<String, Set<IFeature>> readAnnotations(IProject project,
			Object annotatedResource, IFeatureModel featureModel) {
		if (target == null)
			loadTarget();
		return target.readAnnotations(project, annotatedResource, featureModel);
	}

	public boolean storeAnnotations(IProject project, Object annotatedResource,
			Map<String, Set<IFeature>> annotations,
			Map<String, Boolean> isOptional,
			Map<String, List<String>> parentIDs, IProgressMonitor monitor)
			throws CoreException {
		if (target == null)
			loadTarget();
		return target.storeAnnotations(project, annotatedResource, annotations,
				isOptional, parentIDs, monitor);
	}

	public boolean isColorStorageFile(IFile file) {
		if (target == null)
			loadTarget();
		return target.isColorStorageFile(file);
	}

	public boolean activateAlternative(IProject project,
			Object annotatedResource, Alternative alternative,
			Map<String, String> id2oldText) {
		if (target == null)
			loadTarget();
		return target.activateAlternative(project, annotatedResource,
				alternative, id2oldText);
	}

	public boolean storeNewAlternative(IProject project,
			Object annotatedResource, Alternative alternative,
			Map<String, String> id2oldText) {
		if (target == null)
			loadTarget();
		return target.storeNewAlternative(project, annotatedResource,
				alternative, id2oldText);
	}

	public Map<String, List<Alternative>> getAllAlternatives(IProject project,
			Object annotatedResource, IFeatureModelWithID featureModel) {
		if (target == null)
			loadTarget();
		return target.getAllAlternatives(project, annotatedResource,
				featureModel);
	}

	public boolean canHandleAlternatives() {
		if (target == null)
			loadTarget();
		return target.canHandleAlternatives();
	}

	private IConfigurationElement configElement;

	protected StorageProviderProxy() {
	}

	public StorageProviderProxy(IConfigurationElement configurationElement) {
		this.configElement = configurationElement;
		name = configElement.getAttribute("name");
		id = configElement.getAttribute("id");
	}

	protected String name;
	protected String id;
	protected IStorageProvider target = null;

	protected void loadTarget() {
		try {
			target = (IStorageProvider) configElement
					.createExecutableExtension("provider");
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Storage Provider: " + name + " (" + id + ")";
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

}
