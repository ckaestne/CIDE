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
//import org.eclipse.jface.text.BadLocationException;
//import org.eclipse.jface.text.ITextSelection;
//
//import cide.gast.IASTNode;
//import cide.gparser.ParseException;
//import de.ovgu.cide.af.Alternative;
//import de.ovgu.cide.editor.keepcolors.ColorCacheManager;
//
///**
// * Action that switches to another alternative of the selected code-fragment.
// * 
// * @author Malte Rosenthal
// */
//public class SwitchAlternativeAction extends Action {
//
//	private Alternative alternative;
//	private SelectionActionsContext context;
//	private IASTNode selectedNode;
//	
//	public SwitchAlternativeAction(SelectionActionsContext context, Alternative alternative, IASTNode selectedNode) {
//		this.alternative = alternative;
//		this.context = context;
//		this.selectedNode = selectedNode;
//		
//		this.setChecked(alternative.isActive);
//		this.setDescription("Switch to alternative >" + alternative.altID + "<");
//		this.setEnabled(!alternative.isActive);
//		this.setText(alternative.altID);
//		this.setToolTipText("Switch to alternative >" + alternative.altID + "<");
//	}
//	
//	@Override
//	public void run() {
//		ITextSelection selection = context.getTextSelection();		
//		ColoredEditorExtensions editorExtensions = context.getEditorExtensions();
//		ColorCacheManager colorCacheManager = editorExtensions.getColorCacheManager();
//		
//		context.getEditorExtensions().getAltAnnotationManager().removeAnnotations();
//		
//		try {
//			if (colorCacheManager != null) colorCacheManager.deactivate();
//			editorExtensions.getDocument().replace(selection.getOffset(), selection.getLength(), alternative.text);
//			if (colorCacheManager != null) colorCacheManager.restoreActivation();
//		} catch (BadLocationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		editorExtensions.save();
//		try {
//			context.getSourceFile().getAltFeatureManager().activateAlternative(alternative, selectedNode);
//			context.getEditorExtensions().getAltAnnotationManager().setAnnotations(context.getSourceFile().getAltFeatureManager().getAlternativeNodesWithActiveParent());
//		} catch (CoreException e) {
//			context.getEditorExtensions().markCoreException(e);
//			return;
//		} catch (ParseException e) {
//			context.getEditorExtensions().markParseException(e);
//			return;
//		}
//		
//		// CIDECorePlugin.notifyListeners() funktioniert nicht richtig, wenn man am Ende des Dokuments eine Alternative
//		// einsetzt, die kuerzer ist als der urspruengliche Text. Grund dafuer scheint zu sein, dass man nur die AST-Knoten
//		// der alten Alternative zur Verfuegung hat, die man dem ASTColorChangedEvent gibt.
//		// Bevor wir hier gross aufwendige Workarounds finden, invalidieren wir einfach alles - ist wahrscheinlich sogar
//		// effizienter als z.B. das Ermitteln der neuen AST-Knoten.
//		editorExtensions.invalidateTextPresentation();
//	}
//}
