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
