package de.ovgu.cide.editor.inlineprojection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.source.Annotation;

import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.utils.AbstractToggleProjectionAction;

/**
 * class that initiates the update (what to show or hide) after projections have
 * been changed in some form
 * 
 * @author ckaestne
 * 
 */
public class ProjectionColorManager implements IProjectionColorManager {

	private final IProjectionColoredEditor editor;
	private AbstractToggleProjectionAction toogleProjectionAction = new AbstractToggleProjectionAction() {
		public void run() {
			updateProjectionAnnotationVisibility();
		};
	};

	public ProjectionColorManager(IProjectionColoredEditor editor) {
		this.editor = editor;
	}
	
	public boolean isProjectionActive(){
		return toogleProjectionAction.isChecked();
	}
	public AbstractToggleProjectionAction getAction(){
		return toogleProjectionAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.ovgu.cide.editor.inlineprojection.IProjectionColorManager#
	 * updateProjectionAnnotations()
	 */
	@SuppressWarnings("unchecked")
	public void updateProjectionAnnotationVisibility() {
		InlineProjectionSourceViewer viewer = (InlineProjectionSourceViewer) editor
				.getSourceViewerR();
		InlineProjectionAnnotationModel annotationModel = viewer
				.getInlineProjectionAnnotationModel();

		List<Annotation> changedAnnotations = new ArrayList<Annotation>();
		if (annotationsEnabled()) {
			// show visible
			Set<IFeature> visibleColors = editor.getFeatureModel()
					.getVisibleFeatures();
			for (Iterator iter = annotationModel.getAnnotationIterator(); iter
					.hasNext();) {
				AbstractColoredInlineProjectionAnnotation annotation = (AbstractColoredInlineProjectionAnnotation) iter
						.next();
				boolean changed = annotation.adjustCollapsing(visibleColors);
				if (changed)
					changedAnnotations.add(annotation);
			}
		} else {
			// show all
			for (Iterator iter = annotationModel.getAnnotationIterator(); iter
					.hasNext();) {
				AbstractColoredInlineProjectionAnnotation annotation = (AbstractColoredInlineProjectionAnnotation) iter
						.next();
				if (annotation.isCollapsed()) {
					annotation.markExpanded();
					changedAnnotations.add(annotation);
				}
			}
		}

		annotationModel.modifyAnnotations(null, null, changedAnnotations
				.toArray(new Annotation[changedAnnotations.size()]));
	}

	private boolean annotationsEnabled() {
		return toogleProjectionAction.isChecked();
	}

}
