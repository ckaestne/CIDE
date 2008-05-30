package coloredide.editor.inlineprojection;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.Position;

import coloredide.features.Feature;

public class ColoredInlineProjectionAnnotation extends
		InlineProjectionAnnotation {

	
	private IProject project;

	public ColoredInlineProjectionAnnotation(Set<Feature> features, IProject project, Position pos){
		this.colors=features;
		this.project=project;
		this.position=pos;
	}
	
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
	
	public IProject getProject(){
		return project;
	}

}
