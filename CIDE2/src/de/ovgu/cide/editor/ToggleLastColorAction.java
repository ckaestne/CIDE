package de.ovgu.cide.editor;

import org.eclipse.jface.action.IAction;

import de.ovgu.cide.features.IFeature;

public class ToggleLastColorAction extends ToggleTextColorAction implements
		IAction {

	public ToggleLastColorAction(SelectionActionsContext context,
			IFeature feature) {
		super(context, feature);
		this.setText("Last feature: "+feature.getName()+" (&x)");
	}

}
