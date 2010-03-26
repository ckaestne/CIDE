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

package de.ovgu.cide.editor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.languages.ExtendedLanguageExtension;
import cide.languages.ILanguageExtension;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.languages.LanguageExtensionProxy;

public class SelectionActionsContext {
	
	private ColoredSourceFile sourceFile;
	private ITextSelection textSelection;
	private final List<IASTNode> selectedNodes = new ArrayList<IASTNode>();
	private List<IASTNode> allSelectedNodes;
	private ColoredEditorExtensions editorExtensions;
	private boolean optionalNodesOnly;	// Auch Indikator dafür, dass alternative Features unterstützt werden
	
	public SelectionActionsContext(ColoredSourceFile sourceFile, ISelection selection, ColoredEditorExtensions editorExtensions) {
		this(sourceFile, selection, editorExtensions, true);
	}
	
	public SelectionActionsContext(ColoredSourceFile sourceFile, ISelection selection, ColoredEditorExtensions editorExtensions,
								   boolean optionalNodesOnly) {
		if ((sourceFile == null) || !(selection instanceof ITextSelection))
			return;
		
		this.sourceFile = sourceFile;
		textSelection = (ITextSelection) selection;
		if (textSelection.getLength() == 0)
			return;
		this.editorExtensions = editorExtensions;

		updateSelectedASTs(optionalNodesOnly);
		this.optionalNodesOnly = optionalNodesOnly;
	}

	boolean anyNodesSelected() {
		return ((selectedNodes != null) && !selectedNodes.isEmpty());
	}

	public boolean nodesHaveColor(IFeature feature) {
		if ((feature == null) || !anyNodesSelected())
			return false;

		SourceFileColorManager colorManager = sourceFile.getColorManager();
		for (IASTNode node : selectedNodes) {
			if (!colorManager.hasColor(node, feature))
				return false;
		}

		return true;
	}
	
	public boolean nodesAreColored() {
		if (!anyNodesSelected())
			return false;
		
		SourceFileColorManager colorManager = sourceFile.getColorManager();
		for (IASTNode node : selectedNodes) {
			if (colorManager.getColors(node).isEmpty())
				return false;
		}

		return true;
	}
	
	public boolean nodesHaveNonInheritedColors() {
		if (!anyNodesSelected())
			return false;
		
		SourceFileColorManager colorManager = sourceFile.getColorManager();
		for (IASTNode node : selectedNodes) {
			if (colorManager.getNotInheritedColors(node).isEmpty())
				return false;
		}
		
		return true;
	}
	
	public boolean canColorNodes() {
		if (!anyNodesSelected())
			return false;
		
		if (!optionalNodesOnly) {
			List<IASTNode> nodesToCheck = new LinkedList<IASTNode>();
			for (IASTNode node : selectedNodes) {
				if (!node.isOptional()) {
					nodesToCheck.add(node);
				}
			}

			if (!nodesToCheck.isEmpty())
				return canCreateAlternatives(nodesToCheck);
		}
		
		return true;
	}
	
	public boolean canCreateAlternatives() {
		return canCreateAlternatives(selectedNodes);
	}
	
	@SuppressWarnings("unchecked")
	private boolean canCreateAlternatives(List<IASTNode> nodes) {
		if ((nodes == null) || nodes.isEmpty() || optionalNodesOnly)
			return false;
		
		ILanguageExtension le = sourceFile.getLanguageExtension();
		if (le != null) {
			if (le instanceof LanguageExtensionProxy) {
				le = ((LanguageExtensionProxy) le).getTarget();
			}
			
			if (!(le instanceof ExtendedLanguageExtension))
				return false;
			
			return ((ExtendedLanguageExtension) le).canCreateAlternatives(nodes);
		}
		
		return false;
	}

	private void updateSelectedASTs(boolean optionalNodesOnly) {
		try {
			selectedNodes.clear();
			ISourceFile ast = sourceFile.getAST();
			
			SelectionFinder selectionFinder = new SelectionFinder(selectedNodes, textSelection, optionalNodesOnly);
			ast.accept(selectionFinder);
			allSelectedNodes = selectionFinder.getAllSelectedNodes();
		} catch (Exception e) { }
	}

	public ColoredSourceFile getSourceFile() {
		return sourceFile;
	}
	
	public ITextSelection getTextSelection() {
		return textSelection;
	}
	
	public List<IASTNode> getSelectedNodes() {
		return selectedNodes;
	}
	
	public List<IASTNode> getAllSelectedNodes() {
		return allSelectedNodes;
	}
	
	public ColoredEditorExtensions getEditorExtensions() {
		return editorExtensions;
	}
	
	public IFeatureModel getFeatureModel() {
		return sourceFile.getFeatureModel();
	}
}
