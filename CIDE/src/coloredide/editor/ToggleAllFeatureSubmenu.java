package coloredide.editor;

import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import coloredide.features.Feature;

public class ToggleAllFeatureSubmenu extends MenuManager implements
		IContributionItem {

	public ToggleAllFeatureSubmenu(ToggleTextColorContext context,
			List<Feature> features) {
		
		super("All features");
		for (Feature feature : features) {
			this.add(new ToggleTextColorAction(context, feature));
		}

	}

}
