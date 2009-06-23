//package de.ovgu.cide.editor;
//
//import java.util.List;
//
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.jface.action.IContributionItem;
//import org.eclipse.jface.action.MenuManager;
//
//import cide.gast.IASTNode;
//import cide.gparser.ParseException;
//import de.ovgu.cide.af.Alternative;
//import de.ovgu.cide.af.AlternativeFeatureManager;
//
///**
// * @author Malte Rosenthal
// */
//public class SwitchAlternativeSubmenu extends MenuManager implements IContributionItem {
//
//	public SwitchAlternativeSubmenu(SelectionActionsContext context) {
//		super("Switch to alternative");
//		
//		List<IASTNode> selectedNodes = context.getSelectedNodes();
//		if ((selectedNodes == null) || (selectedNodes.size() != 1))
//			return;
//		IASTNode selectedNode = selectedNodes.get(0);
//		
//		AlternativeFeatureManager altFeatureManager;
//		List<Alternative> alternatives;
//		try {
//			altFeatureManager = context.getSourceFile().getAltFeatureManager();
//			alternatives = altFeatureManager.getAlternativesWithActiveParent(selectedNode.getId());
//		} catch (CoreException e) {
//			context.getEditorExtensions().markCoreException(e);
//			return;
//		} catch (ParseException e) {
//			context.getEditorExtensions().markParseException(e);
//			return;
//		}
//		
//		//                              Wenn es nur eine Alternative gibt, muss sie inaktiv sein
//		if ((alternatives != null) && ((alternatives.size() != 1) || !alternatives.get(0).isActive)) {
//			for (Alternative alternative : alternatives) {
//				this.add(new SwitchAlternativeAction(context, alternative, selectedNode));
//			}
//		}
//	}
//}
