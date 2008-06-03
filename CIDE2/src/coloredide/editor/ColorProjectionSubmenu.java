package coloredide.editor;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import coloredide.features.IFeature;
import coloredide.utils.ColorHelper;

public class ColorProjectionSubmenu extends MenuManager implements
		IContributionItem {

	public class ExpandAllAction extends Action {
		private ColoredTextEditor editor;

		public ExpandAllAction(ColoredTextEditor editor) {

			super("Show all");
			this.editor = editor;
		}

		@Override
		public void run() {
			editor.getProjectionColorManager().expandAllColors();
		}
	}

	public class CollapseAllAction extends Action {
		private ColoredTextEditor editor;

		public CollapseAllAction(ColoredTextEditor editor) {

			super("Hide all");
			this.editor = editor;
		}

		@Override
		public void run() {
			editor.getProjectionColorManager().collapseAllColors();
		}
	}

	public class ToggleColorProjectionAction extends Action {

		private ColoredTextEditor editor;

		private IFeature feature;

		private boolean wasExpanded;

		public ToggleColorProjectionAction(ColoredTextEditor editor,
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

	public ColorProjectionSubmenu(ColoredTextEditor editor,
			ToggleTextColorContext context) {

		super("Projection");
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
