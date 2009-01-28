package de.ovgu.cide.editor;

import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import cide.gast.IASTNode;
import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.af.AlternativeFeatureManager;

public class SwitchAlternativeSubmenu extends MenuManager implements IContributionItem {

	public SwitchAlternativeSubmenu(SelectionActionsContext context) {
		super("Switch to alternative");
		
		List<IASTNode> selectedNodes = context.getSelectedNodes();
		if ((selectedNodes == null) || (selectedNodes.size() != 1))
			return;
		IASTNode selectedNode = selectedNodes.get(0);
		
		AlternativeFeatureManager altFeatureManager = context.getSourceFile().getAltFeatureManager();
		List<Alternative> alternatives = altFeatureManager.getAlternatives(selectedNode.getId());
		
		//                              Wenn es nur eine Alternative gibt, muss sie inaktiv sein
		if ((alternatives != null) && ((alternatives.size() != 1) || !alternatives.get(0).isActive)) {
			for (Alternative alternative : alternatives) {
				this.add(new SwitchAlternativeAction(context, alternative, selectedNode));
			}
		}
	}
}
