/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

//package de.ovgu.cide.editor;
//
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.jface.action.Action;
//import org.eclipse.jface.dialogs.IInputValidator;
//import org.eclipse.jface.dialogs.InputDialog;
//
//import cide.gparser.ParseException;
//
///**
// * Action that creates an alternative to a selected code-fragment.
// * 
// * @author Malte Rosenthal
// */
//public class CreateAlternativeAction extends Action {
//	
//	private SelectionActionsContext context;
//	
//	public CreateAlternativeAction(SelectionActionsContext context) {
//		this.context = context;
//		this.setText("Create alternative");
//	}
//
//	@Override
//	public void run() {
//		InputDialog dialog = new InputDialog(null, "Alternative name", "Name of new alternative", null, 
//			new IInputValidator() {
//				public String isValid(String newText) {
//					if ((newText == null) || (newText.length() < 1))
//						return "";
//					if (newText.length() > 50)
//						return "Too long";
//					return null;
//				}
//		});
//		
//		if (dialog.open() == InputDialog.OK) {
//			context.getEditorExtensions().getAltAnnotationManager().removeAnnotations();
//			try {
//				context.getSourceFile().getAltFeatureManager().createAlternative(context.getSelectedNodes(), dialog.getValue());
//				context.getEditorExtensions().getAltAnnotationManager().setAnnotations(context.getSourceFile().getAltFeatureManager().getAlternativeNodesWithActiveParent());
//			} catch (CoreException e) {
//				context.getEditorExtensions().markCoreException(e);
//			} catch (ParseException e) {
//				context.getEditorExtensions().markParseException(e);
//			}
//		}
//	}
//}
