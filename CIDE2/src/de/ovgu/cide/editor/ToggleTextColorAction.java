package de.ovgu.cide.editor;

import org.eclipse.jface.action.Action;

import de.ovgu.cide.features.IFeature;

public class ToggleTextColorAction extends Action {

	static IFeature lastFeature = null;

	private SelectionActionsContext context;
	protected IFeature feature;

	public ToggleTextColorAction(SelectionActionsContext context,
			IFeature feature) {
		this.context = context;
		this.feature = feature;

		if (feature != null)
			this.setText(feature.getName());

		this.setEnabled(context.anyNodesSelected());
		this.setChecked(context.nodesHaveColor(feature));
	}

	@Override
	public void run() {
		context.getSourceFile().getColorManager().toggleColor(
				context.getSelectedNodes(), feature,
				!context.nodesHaveColor(feature));
		lastFeature = feature;
	}
}
