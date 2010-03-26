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
