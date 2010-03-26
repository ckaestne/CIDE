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

import java.util.Collections;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.ovgu.cide.configuration.AbstractConfigurationPage;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public class NoConfigurationPage extends AbstractConfigurationPage {

	public NoConfigurationPage(String pageName, IFeatureModel featureModel) {
		super(pageName, featureModel);
		this
				.setErrorMessage("Not available. Use pure::variant transformation mechanism instead.");
	}

	@Override
	protected Control createMainControl(Composite composite) {
		return composite;
	}

	@Override
	public Set<IFeature> getNotSelectedFeatures() {
		return Collections.emptySet();
	}

	@Override
	public Set<IFeature> getSelectedFeatures() {
		return Collections.emptySet();
	}

}
