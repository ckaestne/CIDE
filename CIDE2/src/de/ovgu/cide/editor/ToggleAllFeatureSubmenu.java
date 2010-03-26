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

package de.ovgu.cide.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.ovgu.cide.features.IFeature;

public class ToggleAllFeatureSubmenu extends MenuManager implements
		IContributionItem {

	public ToggleAllFeatureSubmenu(SelectionActionsContext context,
			Collection<IFeature> features) {

		super("All features");
		ArrayList<IFeature> list = new ArrayList<IFeature>(features);
		Collections.sort(list);
		for (IFeature feature : list) {
			this.add(new ToggleTextColorAction(context, feature));
		}

	}

}
