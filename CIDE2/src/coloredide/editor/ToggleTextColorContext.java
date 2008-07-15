package coloredide.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import coloredide.ASTColorChangedEvent;
import coloredide.CIDECorePlugin;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.SourceFileColorManager;

public class ToggleTextColorContext {

	private boolean enabled = false;

	private final List<IASTNode> selectedNodes = new ArrayList<IASTNode>();

	private SourceFileColorManager colorManager;

	private ColoredSourceFile sourceFile;

	ToggleTextColorContext(ColoredSourceFile sourceFile, ISelection selection) {
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

	boolean isChecked(IFeature feature) {
		if (!enabled)
			return false;

		boolean allSelected = true;
		for (IASTNode node : selectedNodes) {
			allSelected &= colorManager.hasColor(node, feature);
		}

		return allSelected;
	}

	void run(IFeature feature, boolean addColor) {
		colorManager.beginBatch();
		for (IASTNode node : selectedNodes) {
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
			ast.accept(new SelectionFinder(selectedNodes, txtSelection, true));

		} catch (Exception e) {
		}
	}

	public List<IASTNode> getSelectedNodes() {
		return selectedNodes;
	}

	//	
	// IProject getProject(){
	// return sourceFile.getProject();
	// }

	public IFeatureModel getFeatureModel() {
		return sourceFile.getFeatureModel();
	}
}
