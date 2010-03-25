package coloredide.editor;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.views.navigator.CollapseAllAction;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;

public class ColorProjectionSubmenu extends MenuManager implements
		IContributionItem {

	public class ExpandAllAction extends Action {
		private ColoredCompilationUnitEditor editor;

		public ExpandAllAction(ColoredCompilationUnitEditor editor) {

			super("Show all");
			this.editor = editor;
		}

		@Override
		public void run() {
			editor.getProjectionColorManager().expandAllColors();
		}
	}
	public class CollapsAllAction extends Action {
		private ColoredCompilationUnitEditor editor;

		public CollapsAllAction(ColoredCompilationUnitEditor editor) {

			super("Hide all");
			this.editor = editor;
		}

		@Override
		public void run() {
			editor.getProjectionColorManager().collapseAllColors();
		}
	}

	public class ToggleColorProjectionAction extends Action {

		private ColoredCompilationUnitEditor editor;

		private Feature feature;

		private boolean wasExpanded;

		public ToggleColorProjectionAction(ColoredCompilationUnitEditor editor,
				Feature feature, boolean isExpanded, IProject project) {

			super(feature.getName(project));
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

	public ColorProjectionSubmenu(ColoredCompilationUnitEditor editor,
			ToggleTextColorContext context) {

		super("Projection");
		Set<Feature> expandedColors = editor.getProjectionColorManager()
				.getExpandedColors();
		List<Feature> allFeatures = FeatureManager.getVisibleFeatures(context
				.getProject());
		for (Feature feature : allFeatures) {
			this.add(new ToggleColorProjectionAction(editor, feature,
					expandedColors.contains(feature), context.getProject()));
		}
		this.add(new ExpandAllAction(editor));
		this.add(new CollapsAllAction(editor));

	}
}
