package coloredide.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.source.Annotation;

import coloredide.editor.inlineprojection.ColoredInlineProjectionAnnotation;
import coloredide.editor.inlineprojection.InlineProjectionAnnotationModel;
import coloredide.editor.inlineprojection.InlineProjectionSourceViewer;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.features.source.ColoredSourceFile;

@SuppressWarnings("restriction")
public class ProjectionColorManager {

	private final ColoredTextEditor editor;

	ProjectionColorManager(ColoredTextEditor editor) {
		this.editor = editor;
	}

	private final Set<IFeature> collapsedColors = new HashSet<IFeature>();

	public Set<IFeature> getExpandedColors() {
		ColoredSourceFile sourceFile = editor.getSourceFile();
		IFeatureModel fm = sourceFile.getFeatureModel();
		Set<IFeature> visibleFeatures = new HashSet<IFeature>(fm
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
		collapsedColors.addAll(getExpandedColors());
		updateProjectionAnnotations();
	}

	protected void updateProjectionAnnotations() {
		InlineProjectionSourceViewer viewer = (InlineProjectionSourceViewer) editor
				.getSourceViewerI();
		InlineProjectionAnnotationModel annotationModel = viewer
				.getInlineProjectionAnnotationModel();
		Set<IFeature> visibleColors = getExpandedColors();

		List<Annotation> changedAnnotations = new ArrayList<Annotation>();
		for (Iterator iter = annotationModel.getAnnotationIterator(); iter
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
