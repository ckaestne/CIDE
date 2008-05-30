package coloredide.tools.quickfix;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;

import coloredide.ColorChangedEvent;
import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

@SuppressWarnings("restriction")
public class ChangeNodeColorResolution implements IMarkerResolution,
		IMarkerResolution2 {

	private Set<Feature> colorDiff;

	private boolean add;

	private ASTNode targetNode;

	private String title = null;

	private String description = null;

	protected IColoredJavaSourceFile source;

	private String nodeType;

	private int rel=10;

	public ChangeNodeColorResolution(IColoredJavaSourceFile source,
			ASTNode targetNode, Set<Feature> colorDiff, boolean add) {
		this.targetNode = targetNode;
		this.colorDiff = colorDiff;
		this.add = add;
		this.source = source;
	}

	public ChangeNodeColorResolution(IColoredJavaSourceFile source,
			ASTNode targetNode, Set<Feature> colorDiff, boolean add,
			String title, String desc) {
		this(source, targetNode, colorDiff, add);
		this.title = title;
		this.description = desc;
	}

	public ChangeNodeColorResolution(IColoredJavaSourceFile source,
			ASTNode targetNode, Set<Feature> colorDiff, boolean add,
			String nodeType, int relevance) {
		this(source, targetNode, colorDiff, add);
		this.nodeType = nodeType;
		this.rel=relevance;
	}

	public void run(IMarker marker) {
		nodeColors().beginBatch();
		for (Feature color : colorDiff) {
			if (add)
				nodeColors().addColor(targetNode, color);
			else
				nodeColors().removeColor(targetNode, color);
		}
		nodeColors().endBatch();
		ColoredIDEPlugin.getDefault().notifyListeners(
				new ColorChangedEvent(this, targetNode, source));
	}

	protected IColorManager nodeColors() {
		return source.getColorManager();
	}

	public String getLabel() {
		if (title != null)
			return title;
		return generateTitle();
	}

	private String generateTitle() {
		String t = add ? "Add" : "Remove";
		t += " color";
		t += colorDiff.size() > 1 ? "(s) " : " ";
		t += add ? "to " : "from ";
		t += nodeType == null ? "node" : nodeType;
		t += ": ";
		t += colorDiff.toString();
		t += ".";
		return t;
	}

	public String getDescription() {
		return description;
	}

	public Image getImage() {
		return null;
	}

	public void setRelevance(int rel){this.rel=rel;}
	public int getRelevance() {
		return rel;
	}
}
