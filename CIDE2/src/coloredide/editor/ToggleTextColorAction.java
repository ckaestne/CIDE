package coloredide.editor;

import org.eclipse.jface.action.Action;

import coloredide.features.Feature;

public class ToggleTextColorAction extends Action {

	Feature feature;
	private ToggleTextColorContext context;

	ToggleTextColorAction(ToggleTextColorContext context, Feature feature) {
		this.setText(feature.getName(context.getProject()));
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
