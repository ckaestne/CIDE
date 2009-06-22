package de.ovgu.cide.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import cide.gparser.ParseException;

/**
 * Action that creates an alternative to a selected code-fragment.
 * 
 * @author Malte Rosenthal
 */
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
				public String isValid(String newText) {
					if ((newText == null) || (newText.length() < 1))
						return "";
					if (newText.length() > 50)
						return "Too long";
					return null;
				}
		});
		
		if (dialog.open() == InputDialog.OK) {
			context.getEditorExtensions().getAltAnnotationManager().removeAnnotations();
			try {
				context.getSourceFile().getAltFeatureManager().createAlternative(context.getSelectedNodes(), dialog.getValue());
				context.getEditorExtensions().getAltAnnotationManager().setAnnotations(context.getSourceFile().getAltFeatureManager().getAlternativeNodesWithActiveParent());
			} catch (CoreException e) {
				context.getEditorExtensions().markCoreException(e);
			} catch (ParseException e) {
				context.getEditorExtensions().markParseException(e);
			}
		}
	}
}
