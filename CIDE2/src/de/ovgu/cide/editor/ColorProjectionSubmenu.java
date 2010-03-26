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

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;

import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.utils.ToggleAllFeatureVisibility;
import de.ovgu.cide.features.utils.ToggleFeatureVisibility;
import de.ovgu.cide.tools.featureview.ProjectionKindManager;
import de.ovgu.cide.utils.ColorHelper;

public class ColorProjectionSubmenu extends MenuManager implements
		IContributionItem {

	public ColorProjectionSubmenu(IProjectionColoredEditor editor,
			SelectionActionsContext context) {

		super("Views");

		MenuManager featureSubMenu = new MenuManager("Selected Features");
		this.add(featureSubMenu);

		featureSubMenu.add(new ToggleAllFeatureVisibility(context
				.getFeatureModel(), true));
		featureSubMenu.add(new ToggleAllFeatureVisibility(context
				.getFeatureModel(), false));
		featureSubMenu.add(new Separator());
		List<IFeature> allFeatures = ColorHelper.sortFeatures(context
				.getFeatureModel().getFeatures());
		for (IFeature feature : allFeatures) {
			featureSubMenu.add(new ToggleFeatureVisibility(feature));
		}

		MenuManager settingsSubMenu = new MenuManager("Settings");
		this.add(settingsSubMenu);
		for (IAction viewKind : ProjectionKindManager.getInstance()
				.getActions())
			settingsSubMenu.add(viewKind);

	}
}
