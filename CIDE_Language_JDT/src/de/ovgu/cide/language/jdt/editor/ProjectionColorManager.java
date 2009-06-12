package de.ovgu.cide.language.jdt.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.source.Annotation;

import de.ovgu.cide.editor.IProjectionColorManager;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.editor.inlineprojection.ColoredInlineProjectionAnnotation;
import de.ovgu.cide.language.jdt.editor.inlineprojection.InlineProjectionAnnotationModel;
import de.ovgu.cide.language.jdt.editor.inlineprojection.InlineProjectionJavaViewer;

@SuppressWarnings("restriction")
public class ProjectionColorManager implements IProjectionColorManager {

	private final ColoredCompilationUnitEditor editor;

	ProjectionColorManager(ColoredCompilationUnitEditor editor) {
		this.editor = editor;
	}

	private final Set<IFeature> collapsedColors = new HashSet<IFeature>();

	public Set<IFeature> getExpandedColors() {
		ColoredSourceFile source = editor.getSourceFile();
		Set<IFeature> visibleFeatures = new HashSet<IFeature>(source.getFeatureModel()
				.getVisibleFeatures());
		visibleFeatures.removeAll(collapsedColors);

		return visibleFeatures;
	}

	public void expandColor(IFeature color) {
		collapsedColors.remove(color);
		updateProjectionAnnotations();
	}

	public void collapseColor(IFeature color) {
		collapsedColors.add(color);
		updateProjectionAnnotations();
	}

	public void expandAllColors() {
		collapsedColors.clear();
		updateProjectionAnnotations();
	}
	public void collapseAllColors() {
		ColoredSourceFile source = editor.getSourceFile();
		
		collapsedColors.addAll(source.getFeatureModel()
				.getVisibleFeatures());
		updateProjectionAnnotations();
	}

	protected void updateProjectionAnnotations() {
		InlineProjectionJavaViewer viewer = (InlineProjectionJavaViewer) editor
				.getViewer();
		InlineProjectionAnnotationModel annotationModel = viewer
				.getInlineProjectionAnnotationModel();
		Set<IFeature> visibleColors = getExpandedColors();

		List<Annotation> changedAnnotations = new ArrayList<Annotation>();
		for (Iterator<Annotation> iter = annotationModel.getAnnotationIterator(); iter
				.hasNext();) {
			ColoredInlineProjectionAnnotation annotation = (ColoredInlineProjectionAnnotation) iter
					.next();
			boolean changed = annotation.adjustCollapsing(visibleColors);
			if (changed)
				changedAnnotations.add(annotation);
		}

		annotationModel.modifyAnnotations(null, null, changedAnnotations
				.toArray(new Annotation[changedAnnotations.size()]));
	}
}
