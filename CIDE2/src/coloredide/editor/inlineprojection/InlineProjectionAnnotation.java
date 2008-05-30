/**
 * 
 */
package coloredide.editor.inlineprojection;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationPresentation;
import org.eclipse.jface.text.source.ImageUtilities;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;


public class InlineProjectionAnnotation extends Annotation implements
		IAnnotationPresentation {

	private static class DisplayDisposeRunnable implements Runnable {

		public void run() {
			if (fgCollapsedImage != null) {
				fgCollapsedImage.dispose();
				fgCollapsedImage = null;
			}
			if (fgExpandedImage != null) {
				fgExpandedImage.dispose();
				fgExpandedImage = null;
			}
		}
	}

	/**
	 * The type of projection annotations.
	 */
	public static final String TYPE = "org.eclipse.inlineprojection"; //$NON-NLS-1$

	private static Image fgCollapsedImage;

	private static Image fgExpandedImage;

	/** The state of this annotation */
	private boolean fIsCollapsed = false;


	/**
	 * Creates a new expanded projection annotation.
	 */
	public InlineProjectionAnnotation() {
		this(false);
	}

	/**
	 * Creates a new projection annotation. When <code>isCollapsed</code>
	 * is <code>true</code> the annotation is initially collapsed.
	 * 
	 * @param isCollapsed
	 *            <code>true</code> if the annotation should initially be
	 *            collapsed, <code>false</code> otherwise
	 */
	public InlineProjectionAnnotation(boolean isCollapsed) {
		super(TYPE, false, null);
		fIsCollapsed = isCollapsed;
	}




	/*
	 * @see org.eclipse.jface.text.source.IAnnotationPresentation#paint(org.eclipse.swt.graphics.GC,
	 *      org.eclipse.swt.widgets.Canvas,
	 *      org.eclipse.swt.graphics.Rectangle)
	 */
	public void paint(GC gc, Canvas canvas, Rectangle rectangle) {
		Image image = getImage(canvas.getDisplay());
		if (image != null) {
			ImageUtilities.drawImage(image, gc, canvas, rectangle,
					SWT.CENTER, SWT.TOP);
			
		}
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationPresentation#getLayer()
	 */
	public int getLayer() {
		return IAnnotationPresentation.DEFAULT_LAYER;
	}

	private Image getImage(Display display) {
		initializeImages(display);
		return isCollapsed() ? fgCollapsedImage : fgExpandedImage;
	}

	private void initializeImages(Display display) {
		if (fgCollapsedImage == null) {

			ImageDescriptor descriptor = ImageDescriptor.createFromFile(
					ProjectionAnnotation.class, "images/collapsed.gif"); //$NON-NLS-1$
			fgCollapsedImage = descriptor.createImage(display);
			descriptor = ImageDescriptor.createFromFile(
					ProjectionAnnotation.class, "images/expanded.gif"); //$NON-NLS-1$
			fgExpandedImage = descriptor.createImage(display);

			display.disposeExec(new DisplayDisposeRunnable());
		}
	}

	/**
	 * Returns the state of this annotation.
	 * 
	 * @return <code>true</code> if collapsed
	 */
	public boolean isCollapsed() {
		return fIsCollapsed;
	}

	/**
	 * Marks this annotation as being collapsed.
	 */
	public void markCollapsed() {
		fIsCollapsed = true;
	}

	/**
	 * Marks this annotation as being unfolded.
	 */
	public void markExpanded() {
		fIsCollapsed = false;
	}
}