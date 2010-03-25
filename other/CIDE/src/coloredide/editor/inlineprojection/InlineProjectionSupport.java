package coloredide.editor.inlineprojection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationPainter;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.projection.IProjectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;

public class InlineProjectionSupport {

	/**
	 * Key of the projection annotation model inside the visual annotation
	 * model. Also internally used as key for the projection drawing strategy.
	 */
	public final static Object INLINEPROJECTION = new Object();

	private static class InlineProjectionAnnotationsPainter extends
			AnnotationPainter {

		/**
		 * Creates a new painter indicating the location of collapsed regions.
		 * 
		 * @param sourceViewer
		 *            the source viewer for the painter
		 * @param access
		 *            the annotation access
		 */
		public InlineProjectionAnnotationsPainter(ISourceViewer sourceViewer,
				IAnnotationAccess access) {
			super(sourceViewer, access);
		}

		/*
		 * @see org.eclipse.jface.text.source.AnnotationPainter#findAnnotationModel(org.eclipse.jface.text.source.ISourceViewer)
		 */
		protected IAnnotationModel findAnnotationModel(
				ISourceViewer sourceViewer) {
			if (sourceViewer instanceof InlineProjectionJavaViewer) {
				InlineProjectionJavaViewer projectionViewer = (InlineProjectionJavaViewer) sourceViewer;
				return projectionViewer.getInlineProjectionAnnotationModel();
			}
			return null;
		}

		/*
		 * @see org.eclipse.jface.text.source.AnnotationPainter#skip(org.eclipse.jface.text.source.Annotation)
		 */
		protected boolean skip(Annotation annotation) {
			if (annotation instanceof InlineProjectionAnnotation)
				return !((InlineProjectionAnnotation) annotation).isCollapsed();

			return super.skip(annotation);
		}
	}

	private static class InlineProjectionDrawingStrategy implements
			AnnotationPainter.IDrawingStrategy {
		/*
		 * @see org.eclipse.jface.text.source.AnnotationPainter.IDrawingStrategy#draw(org.eclipse.swt.graphics.GC,
		 *      org.eclipse.swt.custom.StyledText, int, int,
		 *      org.eclipse.swt.graphics.Color)
		 */
		public void draw(Annotation annotation, GC gc, StyledText textWidget,
				int offset, int length, Color color) {
			if (annotation instanceof InlineProjectionAnnotation) {
				InlineProjectionAnnotation projectionAnnotation = (InlineProjectionAnnotation) annotation;
				if (projectionAnnotation.isCollapsed()) {

					if (gc != null) {

						// StyledTextContent content= textWidget.getContent();
						// int line= content.getLineAtOffset(offset);
						// int lineStart= content.getOffsetAtLine(line);
						// String text= content.getLine(line);
						// int lineLength= text == null ? 0 : text.length();
						// int lineEnd= lineStart + lineLength;
						Point p = textWidget.getLocationAtOffset(offset + 1);

						Color c = gc.getForeground();
						if (annotation instanceof ColoredInlineProjectionAnnotation) {
							Set<Feature> colors = ((ColoredInlineProjectionAnnotation) annotation)
									.getColors();
							Color combinedColor = FeatureManager
									.getCombinedColor(colors);
							gc.setForeground(combinedColor);
						} else
							gc.setForeground(color);

						FontMetrics metrics = gc.getFontMetrics();
						int top = p.y;
						int bottom = p.y + metrics.getHeight();
						int x = p.x;

						gc.drawLine(x - 2, top, x - 2, bottom);
						gc.drawLine(x - 1, top, x - 1, bottom);
						gc.drawLine(x, top, x, bottom);
						gc.drawLine(x - 4, top, x + 2, top);
						gc.drawLine(x - 4, bottom, x + 2, bottom);
						// gc.drawLine(p.x+1, p.y + leading, p.x+1, p.y +
						// leading+ascent);
						// int third= width/3;
						// int dotsVertical= p.y + baseline - 1;
						// gc.drawPoint(p.x + third, dotsVertical);
						// gc.drawPoint(p.x + width - third, dotsVertical);

						// Color bc= gc.getBackground();
						// gc.setBackground(color);
						//
						// gc.fillRectangle(textWidget.getClientArea());

						gc.setForeground(c);
						// gc.setBackground(bc);

					} else {
						textWidget.redrawRange(offset, length, true);
					}
				}
			}
		}
	}

	private class ProjectionListener implements IProjectionListener, IInlineProjectionListener {

		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionListener#projectionEnabled()
		 */
		public void projectionEnabled() {
		}

		/*
		 * @see org.eclipse.jface.text.source.projection.IProjectionListener#projectionDisabled()
		 */
		public void projectionDisabled() {
		}

		public void inlineProjectionDisabled() {
			doInlineDisableProjection();
		}

		public void inlineProjectionEnabled() {
			doInlineEnableProjection();
		}
	}

	private InlineProjectionJavaViewer fViewer;

	private IAnnotationAccess fAnnotationAccess;

	private ISharedTextColors fSharedTextColors;

	private List fSummarizableTypes;

	private IInformationControlCreator fInformationControlCreator;

	private ProjectionListener fProjectionListener;

	private InlineProjectionAnnotationsPainter fPainter;

	private InlineProjectionRulerColumn fColumn;

	/**
	 * @since 3.1
	 */
	private AnnotationPainter.IDrawingStrategy fDrawingStrategy;

	/**
	 * Creates new projection support for the given projection viewer.
	 * Initially, no annotation types are summarized. A default hover control
	 * creator and a default drawing strategy are used.
	 * 
	 * @param viewer
	 *            the projection viewer
	 * @param annotationAccess
	 *            the annotation access
	 * @param sharedTextColors
	 *            the shared text colors to use
	 */
	public InlineProjectionSupport(InlineProjectionJavaViewer viewer,
			IAnnotationAccess annotationAccess,
			ISharedTextColors sharedTextColors) {
		fViewer = viewer;
		fAnnotationAccess = annotationAccess;
		fSharedTextColors = sharedTextColors;
	}

	/**
	 * Marks the given annotation type to be considered when creating summaries
	 * for collapsed regions of the projection viewer.
	 * <p>
	 * A summary is an annotation that gets created out of all annotations with
	 * a type that has been registered through this method and that are inside
	 * the folded region.
	 * </p>
	 * 
	 * @param annotationType
	 *            the annotation type to consider
	 */
	public void addSummarizableAnnotationType(String annotationType) {
		if (fSummarizableTypes == null) {
			fSummarizableTypes = new ArrayList();
			fSummarizableTypes.add(annotationType);
		} else if (!fSummarizableTypes.contains(annotationType))
			fSummarizableTypes.add(annotationType);
	}

	/**
	 * Marks the given annotation type to be ignored when creating summaries for
	 * collapsed regions of the projection viewer. This method has only an
	 * effect when <code>addSummarizableAnnotationType</code> has been called
	 * before for the give annotation type.
	 * <p>
	 * A summary is an annotation that gets created out of all annotations with
	 * a type that has been registered through this method and that are inside
	 * the folded region.
	 * </p>
	 * 
	 * @param annotationType
	 *            the annotation type to remove
	 */
	public void removeSummarizableAnnotationType(String annotationType) {
		if (fSummarizableTypes != null)
			fSummarizableTypes.remove(annotationType);
		if (fSummarizableTypes.size() == 0)
			fSummarizableTypes = null;
	}

	/**
	 * Sets the hover control creator that is used for the annotation hovers
	 * that are shown in the projection viewer's projection ruler column.
	 * 
	 * @param creator
	 *            the hover control creator
	 */
	public void setHoverControlCreator(IInformationControlCreator creator) {
		fInformationControlCreator = creator;
	}

	/**
	 * Sets the drawing strategy that the projection support's annotation
	 * painter uses to draw the indication of collapsed regions onto the
	 * projection viewer's text widget. When <code>null</code> is passed in,
	 * the drawing strategy is reset to the default. In order to avoid any
	 * representation use
	 * {@link org.eclipse.jface.text.source.AnnotationPainter.NullStrategy}.
	 * 
	 * @param strategy
	 *            the drawing strategy or <code>null</code> to reset the
	 *            strategy to the default
	 * @since 3.1
	 */
	public void setAnnotationPainterDrawingStrategy(
			AnnotationPainter.IDrawingStrategy strategy) {
		fDrawingStrategy = strategy;
	}

	/**
	 * Returns the drawing strategy to be used by the support's annotation
	 * painter.
	 * 
	 * @return the drawing strategy to be used by the support's annotation
	 *         painter
	 * @since 3.1
	 */
	private AnnotationPainter.IDrawingStrategy getDrawingStrategy() {
		if (fDrawingStrategy == null)
			fDrawingStrategy = new InlineProjectionDrawingStrategy();
		return fDrawingStrategy;
	}

	/**
	 * Installs this projection support on its viewer.
	 */
	public void install() {
		// TODO fViewer.setProjectionSummary(createProjectionSummary());

		fProjectionListener = new ProjectionListener();
		fViewer.addProjectionListener(fProjectionListener);
	}

	/**
	 * Disposes this projection support.
	 */
	public void dispose() {
		if (fProjectionListener != null) {
			fViewer.removeProjectionListener(fProjectionListener);
			fProjectionListener = null;
		}
	}

	/**
	 * Enables projection mode. If not yet done, installs the projection ruler
	 * column in the viewer's vertical ruler and installs a painter that
	 * indicate the locations of collapsed regions.
	 * 
	 */
	protected void doInlineEnableProjection() {

		if (fPainter == null) {
			fPainter = new InlineProjectionAnnotationsPainter(fViewer,
					fAnnotationAccess);
			fPainter.addDrawingStrategy(INLINEPROJECTION, getDrawingStrategy());
			fPainter.addAnnotationType(InlineProjectionAnnotation.TYPE,
					INLINEPROJECTION);
			fPainter.setAnnotationTypeColor(InlineProjectionAnnotation.TYPE,
					fSharedTextColors.getColor(getColor()));
			fViewer.addPainter(fPainter);
		}

		if (fColumn == null) {
			fColumn = new InlineProjectionRulerColumn(9, fAnnotationAccess);
			fColumn.addAnnotationType(InlineProjectionAnnotation.TYPE);
			fColumn.setHover(createProjectionAnnotationHover());
			fViewer.addVerticalRulerColumn(fColumn);
		}

		fColumn.setModel(fViewer.getVisualAnnotationModel());
	}

	/**
	 * Removes the projection ruler column and the painter from the projection
	 * viewer.
	 */
	protected void doInlineDisableProjection() {
		if (fPainter != null) {
			fViewer.removePainter(fPainter);
			fPainter.dispose();
			fPainter = null;
		}

		if (fColumn != null) {
			fViewer.removeVerticalRulerColumn(fColumn);
			fColumn = null;
		}
	}
//
//	private InlineProjectionSummary createProjectionSummary() {
//		InlineProjectionSummary summary = new InlineProjectionSummary(fViewer,
//				fAnnotationAccess);
//		if (fSummarizableTypes != null) {
//			int size = fSummarizableTypes.size();
//			for (int i = 0; i < size; i++)
//				summary.addAnnotationType((String) fSummarizableTypes.get(i));
//		}
//		return summary;
//	}

	private IAnnotationHover createProjectionAnnotationHover() {
		InlineProjectionAnnotationHover hover = new InlineProjectionAnnotationHover();
		hover.setHoverControlCreator(fInformationControlCreator);
		return hover;
	}

	/**
	 * Implements the contract of
	 * {@link org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)}
	 * by forwarding the adapter requests to the given viewer.
	 * 
	 * @param viewer
	 *            the viewer
	 * @param required
	 *            the required class of the adapter
	 * @return the adapter or <code>null</code>
	 * 
	 */
	public Object getAdapter(ISourceViewer viewer, Class required) {
		if (InlineProjectionAnnotationModel.class.equals(required)) {
			if (viewer instanceof InlineProjectionJavaViewer) {
				InlineProjectionJavaViewer projectionViewer = (InlineProjectionJavaViewer) viewer;
				return projectionViewer.getInlineProjectionAnnotationModel();
			}
		}
		return null;
	}

	private RGB getColor() {
		// TODO read out preference settings
		Color c = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
		return c.getRGB();
	}
}
