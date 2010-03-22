package de.ovgu.cide.editor.inlineprojection;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.Position;

import de.ovgu.cide.features.IFeature;

public abstract class AbstractColoredInlineProjectionAnnotation extends
		InlineProjectionAnnotation {

	private final IProject project;
	private final ProjectionColorManager projectionColorManager;
	protected final Set<IFeature> colors;
	private final Position position;

	public AbstractColoredInlineProjectionAnnotation(Set<IFeature> features,
			IProject project, Position pos,
			ProjectionColorManager projectionColorManager) {
		this.colors = features;
		this.project = project;
		this.position = pos;
		this.projectionColorManager = projectionColorManager;
	}

	// public void setColors(Set<IFeature> colors) {
	// this.colors = colors;
	// }

	/**
	 * automatically fold or unfold based on the global feature selection for
	 * this view
	 * 
	 * @param visibleFeatures
	 */
	public abstract boolean adjustCollapsing(Set<IFeature> selectedColors);

	protected boolean setExpanded(boolean expanded) {
		if (!projectionColorManager.isProjectionActive())
			expanded = true;
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

	public Position getPosition() {
		return position;
	}

	public Set<IFeature> getColors() {
		return colors;
	}

	public IProject getProject() {
		return project;
	}

}
