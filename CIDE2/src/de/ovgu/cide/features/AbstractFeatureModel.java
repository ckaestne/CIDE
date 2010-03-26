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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ColorListChangedEvent;

public abstract class AbstractFeatureModel implements IFeatureModelWithID {

	protected IProject project;
//	private final List<IFeatureModelChangeListener> listeners = new LinkedList<IFeatureModelChangeListener>();

	protected AbstractFeatureModel(IProject project) {
		assert project != null && project.exists();
		this.project = project;
	}

//	public void addListener(IFeatureModelChangeListener listener) {
//		listeners.add(listener);
//	}

	public IProject getProject() {
		return project;
	}

//	public void removeListener(IFeatureModelChangeListener listener) {
//		listeners.remove(listener);
//	}

	protected void fireFeatureModelChanged(ColorListChangedEvent event) {
		CIDECorePlugin.getDefault().notifyListeners(event);
//		for (IFeatureModelChangeListener listener : listeners)
//			listener.colorListChanged(event);
	}

	public Set<IFeature> getVisibleFeatures() {
		Set<IFeature> result = new HashSet<IFeature>();
		for (IFeature feature : getFeatures()) {
			if (feature.isVisible())
				result.add(feature);
		}
		return Collections.unmodifiableSet(result);
	}

}
