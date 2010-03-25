package coloredide.editor.inlineprojection;

import java.util.Set;

import org.eclipse.jface.text.Position;

import coloredide.features.Feature;

public class ColoredInlineProjectionAnnotation extends
		InlineProjectionAnnotation {

	private Set<Feature> colors;

	private Position position;

	public void setColors(Set<Feature> colors) {
		this.colors = colors;
	}

	public boolean adjustCollapsing(Set<Feature> selectedColors) {
		boolean expanded = selectedColors.containsAll(colors);
		if (isCollapsed() && expanded) {
			this.markExpanded();
			return true;
		}
		if (!isCollapsed() && !expanded) {
			this.markCollapsed();
			return true;
		}
		return false;
	}

	public void setPosition(Position pos) {
		this.position = pos;
	}

	public Position getPosition() {
		return position;
	}

	public Set<Feature> getColors() {
		return colors;
	}

}
