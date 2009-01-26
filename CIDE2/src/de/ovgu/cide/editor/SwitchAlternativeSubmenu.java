package de.ovgu.cide.editor;

import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.ovgu.cide.af.Alternative;
import de.ovgu.cide.af.AlternativeFeatureManager;

public class SwitchAlternativeSubmenu extends MenuManager implements IContributionItem {

	public SwitchAlternativeSubmenu(SelectionActionsContext context) {
		super("Switch to alternative");
		
		AlternativeFeatureManager altFeatureManager = context.getSourceFile().getAltFeatureManager();
		// TODO MRO: get(0)?
		List<Alternative> alternatives = altFeatureManager.getAlternatives(context.getSelectedNodes().get(0).getId());
		
		//                              Wenn es nur eine Alternative gibt, muss sie inaktiv sein
		if ((alternatives != null) && ((alternatives.size() != 1) || !alternatives.get(0).isActive)) {
			for (Alternative alternative : alternatives) {
				this.add(new SwitchAlternativeAction(context, alternative));
			}
		}
	}
}
