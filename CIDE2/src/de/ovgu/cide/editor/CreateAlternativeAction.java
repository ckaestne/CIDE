package de.ovgu.cide.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

public class CreateAlternativeAction extends Action {
	
	private SelectionActionsContext context;
	
	public CreateAlternativeAction(SelectionActionsContext context) {
		this.context = context;
		this.setText("Create alternative");
	}

	@Override
	public void run() {
		InputDialog dialog = new InputDialog(null, "Alternative name", "Name of new alternative", null, 
			new IInputValidator() {
				@Override
				public String isValid(String newText) {
					if ((newText == null) || (newText.length() < 1))
						return "";
					if (newText.length() > 50)
						return "Too long";
					return null;
				}
		});
		
		if (dialog.open() == InputDialog.OK) {
			context.getSourceFile().getAltFeatureManager().createAlternative(context.getSelectedNodes(), dialog.getValue());
		}
	}
}
