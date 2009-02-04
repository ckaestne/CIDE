package de.ovgu.cide.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;

public class SelectionActionsContext {
	
	private ColoredSourceFile sourceFile;
	private ITextSelection textSelection;
	private final List<IASTNode> selectedNodes = new ArrayList<IASTNode>();
	private List<IASTNode> allSelectedNodes;
	private ColoredEditorExtensions editorExtensions;
	
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
	
	public boolean nodesHaveNotInheritedColors() {
		if (!anyNodesSelected())
			return false;
		
		SourceFileColorManager colorManager = sourceFile.getColorManager();
		for (IASTNode node : selectedNodes) {
			if (colorManager.getNotInheritedColors(node).isEmpty())
				return false;
		}
		
		return true;
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
