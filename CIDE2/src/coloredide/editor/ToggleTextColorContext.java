package coloredide.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;

import cide.gast.ASTNode;
import cide.gast.ISourceFile;
import coloredide.ASTColorChangedEvent;
import coloredide.CIDECorePlugin;
import coloredide.features.Feature;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.SourceFileColorManager;

public class ToggleTextColorContext {

	private boolean enabled = false;

	private final List<ASTNode> selectedNodes = new ArrayList<ASTNode>();

	private SourceFileColorManager colorManager;

	private ColoredSourceFile sourceFile;

	ToggleTextColorContext(ColoredSourceFile sourceFile,
			ISelection selection) {
		this.colorManager = sourceFile.getColorManager();
		this.sourceFile = sourceFile;

		if (!(selection instanceof ITextSelection))
			return;
		if (sourceFile == null)
			return;
		ITextSelection tsel = (ITextSelection) selection;
		if (tsel.getLength() == 0)
			return;

		updateSelectedASTs(tsel, sourceFile);

		enabled = !selectedNodes.isEmpty();
	}

	boolean isEnabled() {
		return enabled;
	}

	boolean isChecked(Feature feature) {
		if (!enabled)
			return false;

		boolean allSelected = true;
		for (ASTNode node : selectedNodes) {
			allSelected &= colorManager.hasColor(node, feature);
		}

		return allSelected;
	}

	void run(Feature feature, boolean addColor) {
		colorManager.beginBatch();
		for (ASTNode node : selectedNodes) {
			if (addColor)
				colorManager.addColor(node, feature);
			else
				colorManager.removeColor(node, feature);
		}
		colorManager.endBatch();
		CIDECorePlugin.getDefault().notifyListeners(
				new ASTColorChangedEvent(this, selectedNodes, sourceFile));
	}

	private void updateSelectedASTs(ITextSelection txtSelection,
			ColoredSourceFile sourceFile) {
		try {
			selectedNodes.clear();
			ISourceFile ast = sourceFile.getAST();
			ast.accept(new SelectionFinder(selectedNodes, txtSelection,true));

		} catch (Exception e) {
		}
	}

	public List<ASTNode> getSelectedNodes() {
		return selectedNodes;
	}
	
	IProject getProject(){
		return sourceFile.getProject();
	}
}
