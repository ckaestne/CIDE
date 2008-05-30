package coloredide.editor;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;

import coloredide.ColorChangedEvent;
import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

public class ToggleTextColorContext {

	private boolean enabled = false;

	private final Set<ASTNode> selectedNodes = new HashSet<ASTNode>();

	private IColorManager colorManager;

	private IColoredJavaSourceFile sourceFile;

	ToggleTextColorContext(IColoredJavaSourceFile sourceFile,
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
		ColoredIDEPlugin.getDefault().notifyListeners(
				new ColorChangedEvent(this, selectedNodes, sourceFile));
	}

	private void updateSelectedASTs(ITextSelection txtSelection,
			IColoredJavaSourceFile sourceFile) {
		try {
			selectedNodes.clear();
			CompilationUnit ast = sourceFile.getAST();
			ast.accept(new SelectionFinder(selectedNodes, txtSelection));

		} catch (Exception e) {
		}
	}

	public Set<ASTNode> getSelectedNodes() {
		return selectedNodes;
	}
	
	IProject getProject(){
		return sourceFile.getProject();
	}
}
