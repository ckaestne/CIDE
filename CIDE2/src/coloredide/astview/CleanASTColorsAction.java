package coloredide.astview;

import java.util.List;

import org.eclipse.jface.action.Action;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import coloredide.ASTColorChangedEvent;
import coloredide.CIDECorePlugin;
import coloredide.features.IFeature;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.SourceFileColorManager;

/**
 * cleans all colors from the selected (sub-)tree(s)
 * 
 * @author cKaestner
 * 
 */
public class CleanASTColorsAction extends Action {

	private List<IASTNode> nodes;

	private ColoredSourceFile file;

	public CleanASTColorsAction(List<IASTNode> nodes, ColoredSourceFile file) {
		super();
		assert nodes != null && !nodes.isEmpty();
		this.nodes = nodes;
		this.file = file;
		this.setText("Clean colors");
	}

	boolean haveColor(List<IASTNode> nodes, IFeature feature) {
		return file.getColorManager().hasColor(nodes.get(0), feature);
	}

	public void run() {
		super.run();
		this.setChecked(!this.isChecked());
		SourceFileColorManager colorManager = file.getColorManager();
		colorManager.beginBatch();
		IASTVisitor colorRemover = new ColorRemover(colorManager);
		for (IASTNode node : nodes)
			node.accept(colorRemover);
		colorManager.endBatch();
		CIDECorePlugin.getDefault().notifyListeners(
				new ASTColorChangedEvent(this, nodes, file));
	}

	public static final class ColorRemover extends ASTVisitor {
		private SourceFileColorManager colorManager;

		public ColorRemover(SourceFileColorManager colorManager) {
			this.colorManager = colorManager;
		}

		public boolean visit(IASTNode node) {
			colorManager.clearColor(node);
			return super.visit(node);
		}

	}
}
