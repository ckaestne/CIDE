package coloredide.astview;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jface.action.Action;

import coloredide.ColorChangedEvent;
import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

/**
 * cleans all colors from the selected (sub-)tree(s)
 * 
 * @author cKaestner
 * 
 */
public class CleanASTColorsAction extends Action {

	private List<ASTNode> nodes;

	private IColoredJavaSourceFile file;

	public CleanASTColorsAction(List<ASTNode> nodes, IColoredJavaSourceFile file) {
		super();
		assert nodes != null && !nodes.isEmpty();
		this.nodes = nodes;
		this.file = file;
		this.setText("Clean colors");
	}

	boolean haveColor(List<ASTNode> nodes, Feature feature) {
		return file.getColorManager().hasColor(nodes.get(0), feature);
	}

	public void run() {
		super.run();
		this.setChecked(!this.isChecked());
		IColorManager colorManager = file.getColorManager();
		colorManager.beginBatch();
		ASTVisitor colorRemover = new ColorRemover(colorManager);
		for (ASTNode node : nodes)
			node.accept(colorRemover);
		colorManager.endBatch();
		ColoredIDEPlugin.getDefault().notifyListeners(
				new ColorChangedEvent(this, nodes, file));
	}

	public static final class ColorRemover extends ASTVisitor {
		private IColorManager colorManager;

		public ColorRemover(IColorManager colorManager) {
			this.colorManager = colorManager;
		}

		public void preVisit(ASTNode node) {
			colorManager.clearColor(node);
			super.preVisit(node);
		}

	}
}
