package coloredide.editor.inlineprojection;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationModel;

public class InlineProjectionAnnotationModel extends AnnotationModel {

	/**
	 * Creates a new, empty projection annotation model.
	 */
	public InlineProjectionAnnotationModel() {
	}

	/**
	 * Changes the state of the given annotation to collapsed. An appropriate
	 * annotation model change event is sent out.
	 * 
	 * @param annotation
	 *            the annotation
	 */
	public void collapse(Annotation annotation) {
		if (annotation instanceof InlineProjectionAnnotation) {
			InlineProjectionAnnotation projection = (InlineProjectionAnnotation) annotation;
			if (!projection.isCollapsed()) {
				projection.markCollapsed();
				modifyAnnotation(projection, true);
			}
		}
	}

	/**
	 * Changes the state of the given annotation to expanded. An appropriate
	 * annotation model change event is sent out.
	 * 
	 * @param annotation
	 *            the annotation
	 */
	public void expand(Annotation annotation) {
		if (annotation instanceof InlineProjectionAnnotation) {
			InlineProjectionAnnotation projection = (InlineProjectionAnnotation) annotation;
			if (projection.isCollapsed()) {
				projection.markExpanded();
				modifyAnnotation(projection, true);
			}
		}
	}

	/**
	 * Toggles the expansion state of the given annotation. An appropriate
	 * annotation model change event is sent out.
	 * 
	 * @param annotation
	 *            the annotation
	 */
	public void toggleExpansionState(Annotation annotation) {
		if (annotation instanceof InlineProjectionAnnotation) {
			InlineProjectionAnnotation projection = (InlineProjectionAnnotation) annotation;

			if (projection.isCollapsed())
				projection.markExpanded();
			else
				projection.markCollapsed();

			modifyAnnotation(projection, true);
		}
	}

	/**
	 * Expands all annotations that overlap with the given range and are
	 * collapsed.
	 * 
	 * @param offset
	 *            the range offset
	 * @param length
	 *            the range length
	 * @return <code>true</code> if any annotation has been expanded,
	 *         <code>false</code> otherwise
	 */
	public boolean expandAll(int offset, int length) {
		return expandAll(offset, length, true);
	}

	/**
	 * Collapses all annotations that overlap with the given range and are
	 * collapsed.
	 * 
	 * @param offset
	 *            the range offset
	 * @param length
	 *            the range length
	 * @return <code>true</code> if any annotation has been collapse,
	 *         <code>false</code> otherwise
	 * @since 3.2
	 */
	public boolean collapseAll(int offset, int length) {

		boolean collapsing = false;

		Iterator iterator = getAnnotationIterator();
		while (iterator.hasNext()) {
			InlineProjectionAnnotation annotation = (InlineProjectionAnnotation) iterator
					.next();
			if (!annotation.isCollapsed()) {
				Position position = getPosition(annotation);
				if (position != null && position.overlapsWith(offset, length) /*
																				 * ||
																				 * is a
																				 * delete
																				 * at
																				 * the
																				 * boundary
																				 */) {
					annotation.markCollapsed();
					modifyAnnotation(annotation, false);
					collapsing = true;
				}
			}
		}

		if (collapsing)
			fireModelChanged();

		return collapsing;
	}

	/**
	 * Expands all annotations that overlap with the given range and are
	 * collapsed. Fires a model change event if requested.
	 * 
	 * @param offset
	 *            the offset of the range
	 * @param length
	 *            the length of the range
	 * @param fireModelChanged
	 *            <code>true</code> if a model change event should be fired,
	 *            <code>false</code> otherwise
	 * @return <code>true</code> if any annotation has been expanded,
	 *         <code>false</code> otherwise
	 */
	protected boolean expandAll(int offset, int length, boolean fireModelChanged) {

		boolean expanding = false;

		Iterator iterator = getAnnotationIterator();
		while (iterator.hasNext()) {
			InlineProjectionAnnotation annotation = (InlineProjectionAnnotation) iterator
					.next();
			if (annotation.isCollapsed()) {
				Position position = getPosition(annotation);
				if (position != null && position.overlapsWith(offset, length) /*
																				 * ||
																				 * is a
																				 * delete
																				 * at
																				 * the
																				 * boundary
																				 */) {
					annotation.markExpanded();
					modifyAnnotation(annotation, false);
					expanding = true;
				}
			}
		}

		if (expanding && fireModelChanged)
			fireModelChanged();

		return expanding;
	}

	/**
	 * Modifies the annotation model.
	 * 
	 * @param deletions
	 *            the list of deleted annotations
	 * @param additions
	 *            the set of annotations to add together with their associated
	 *            position
	 * @param modifications
	 *            the list of modified annotations
	 */
	public void modifyAnnotations(Annotation[] deletions, Map additions,
			Annotation[] modifications) {
		if (additions != null)
			for (Object o : additions.values()) {
				Position p = (Position) o;
				if (p.getOffset() > 0) {
					p.setLength(p.getLength() + 1);
					p.setOffset(p.getOffset() - 1);
				}
			}

		try {
			replaceAnnotations(deletions, additions, false);
			if (modifications != null) {
				for (int i = 0; i < modifications.length; i++)
					modifyAnnotation(modifications[i], false);
			}
		} catch (BadLocationException x) {
		}
		fireModelChanged();
	}
}
