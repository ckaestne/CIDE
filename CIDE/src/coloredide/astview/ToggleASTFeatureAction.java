package coloredide.astview;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.ColorChangedEvent;
import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.utils.FeatureAction;

public class ToggleASTFeatureAction extends FeatureAction {

	private List<ASTNode> nodes;

	private IColoredJavaSourceFile file;

	public ToggleASTFeatureAction(Feature feature, List<ASTNode> nodes,
			IColoredJavaSourceFile file) {
		super(feature);
		assert nodes != null && !nodes.isEmpty();
		this.nodes = nodes;
		this.file = file;
		this.setText(feature.getName(file.getProject()));
		this.setChecked(haveColor(nodes, feature));
	}

	boolean haveColor(List<ASTNode> nodes, Feature feature) {
		return file.getColorManager().hasColor(nodes.get(0), feature);
	}

	public void run() {
		super.run();
		this.setChecked(!this.isChecked());
		for (ASTNode node : nodes)
			if (this.isChecked()) {
				file.getColorManager().removeColor(node, feature);
			} else {
				file.getColorManager().addColor(node, feature);
			}
		ColoredIDEPlugin.getDefault().notifyListeners(
				new ColorChangedEvent(this, nodes, file));
	}

}
