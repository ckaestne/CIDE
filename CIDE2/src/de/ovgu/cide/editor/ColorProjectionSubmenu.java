package de.ovgu.cide.editor;

import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.utils.ColorHelper;

public class ColorProjectionSubmenu extends MenuManager implements
		IContributionItem {

	public class ExpandAllAction extends Action {
		private IProjectionColoredEditor editor;

		public ExpandAllAction(IProjectionColoredEditor editor) {

			super("Show all");
			this.editor = editor;
		}

		@Override
		public void run() {
			editor.getProjectionColorManager().expandAllColors();
		}
	}

	public class CollapseAllAction extends Action {
		private IProjectionColoredEditor editor;

		public CollapseAllAction(IProjectionColoredEditor editor) {

			super("Hide all");
			this.editor = editor;
		}

		@Override
		public void run() {
			editor.getProjectionColorManager().collapseAllColors();
		}
	}

	public class ToggleColorProjectionAction extends Action {

		private IProjectionColoredEditor editor;

		private IFeature feature;

		private boolean wasExpanded;

		public ToggleColorProjectionAction(IProjectionColoredEditor editor,
				IFeature feature, boolean isExpanded) {

			super(feature.getName());
			this.setChecked(isExpanded);
			this.wasExpanded = isExpanded;
			this.editor = editor;
			this.feature = feature;
		}

		@Override
		public void run() {
			if (wasExpanded)// isExpanded
				editor.getProjectionColorManager().collapseColor(feature);
			else
				editor.getProjectionColorManager().expandColor(feature);
		}

	}

	public ColorProjectionSubmenu(IProjectionColoredEditor editor,
			SelectionActionsContext context) {

		super("Projection in current file");
		Set<IFeature> expandedColors = editor.getProjectionColorManager()
				.getExpandedColors();
		List<IFeature> allFeatures = ColorHelper.sortFeatures(context
				.getFeatureModel().getVisibleFeatures());
		for (IFeature feature : allFeatures) {
			this.add(new ToggleColorProjectionAction(editor, feature,
					expandedColors.contains(feature)));
		}
		this.add(new ExpandAllAction(editor));
		this.add(new CollapseAllAction(editor));

	}
}
