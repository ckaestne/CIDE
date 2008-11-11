package de.ovgu.cide.editor;

import org.eclipse.jface.action.Action;

import de.ovgu.cide.features.IFeature;

public class ToggleTextColorAction extends Action {

	protected IFeature feature;
	private ToggleTextColorContext context;

	ToggleTextColorAction(ToggleTextColorContext context, IFeature feature) {
		if (feature!=null)
		this.setText(feature.getName());
		this.setEnabled(context.isEnabled());
		this.setChecked(context.isChecked(feature));
		this.feature=feature;
		this.context=context;
	}
	
	@Override
	public void run() {
		context.run(feature,this.isChecked());
	}
}
