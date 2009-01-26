package de.ovgu.cide.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextSelection;

import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.editor.keepcolors.ColorCacheManager;

public class SwitchAlternativeAction extends Action {

	private Alternative alternative;
	private SelectionActionsContext context;
	
	public SwitchAlternativeAction(SelectionActionsContext context, Alternative alternative) {
		this.alternative = alternative;
		this.context = context;
		
		this.setChecked(alternative.isActive);
		this.setDescription("Switch to alternative >" + alternative.altID + "<");
		this.setEnabled(!alternative.isActive);
		this.setText(alternative.altID);
		this.setToolTipText("Switch to alternative >" + alternative.altID + "<");
	}
	
	@Override
	public void run() {
		ITextSelection selection = context.getTextSelection();
		String oldText = selection.getText();
		
		ColoredEditorExtensions editorExtensions = context.getEditorExtensions();
		ColorCacheManager colorCacheManager = editorExtensions.getColorCacheManager();
		
		try {
			if (colorCacheManager != null) colorCacheManager.deactivate();
			editorExtensions.getDocument().replace(selection.getOffset(), selection.getLength(), alternative.text);
			if (colorCacheManager != null) colorCacheManager.restoreActivation();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		editorExtensions.save();
		context.getSourceFile().getAltFeatureManager().activateAlternative(alternative, oldText, context.getSelectedNodes());
		
		// CIDECorePlugin.notifyListeners() funktioniert nicht richtig, wenn man am Ende des Dokuments eine Alternative
		// einsetzt, die kuerzer ist als der urspruengliche Text. Grund dafuer scheint zu sein, dass man nur die AST-Knoten
		// der alten Alternative zur Verfuegung hat, die man dem ASTColorChangedEvent gibt.
		// Bevor wir hier gross aufwendige Workarounds finden, invalidieren wir einfach alles - ist wahrscheinlich sogar
		// effizienter als z.B. das Ermitteln der neuen AST-Knoten.
		editorExtensions.invalidateTextPresentation();
	}
}
