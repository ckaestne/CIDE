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

package de.ovgu.cide.features.utils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public class ToggleAllFeatureVisibility extends Action {

	private final IFeatureModel featureModel;
	protected final boolean visible;

	public ToggleAllFeatureVisibility(IFeatureModel featureModel, boolean select) {

		super(select ? "Select all" : "Deselect all",IAction.AS_PUSH_BUTTON);
		this.featureModel = featureModel;
		this.visible = select;
	}

	@Override
	public void run() {
		for (IFeature feature : featureModel.getFeatures()) {
			feature.setVisible(visible);
		}

	}
}
