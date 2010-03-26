/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.editor.inlineprojection;

import java.util.Iterator;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHoverExtension;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelExtension;
import org.eclipse.jface.text.source.ILineRange;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension2;
import org.eclipse.jface.text.source.LineRange;
import org.eclipse.jface.text.source.projection.IProjectionPosition;
import org.eclipse.swt.widgets.Shell;

/**
 * Annotation hover for projection annotations.
 *
 * @since 3.0
 */
@SuppressWarnings("restriction")
class InlineProjectionAnnotationHover implements IAnnotationHover, IAnnotationHoverExtension {

	private IInformationControlCreator fInformationControlCreator;

	/**
	 * Sets the hover control creator for this projection annotation hover.
	 *
	 * @param creator the creator
	 */
	public void setHoverControlCreator(IInformationControlCreator creator) {
		fInformationControlCreator= creator;
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationHover#getHoverInfo(org.eclipse.jface.text.source.ISourceViewer, int)
	 */
	public String getHoverInfo(ISourceViewer sourceViewer, int lineNumber) {
		// this is a no-op as semantics is defined by the implementation of the annotation hover extension
		return null;
	}

	/*
	 * @since 3.1
	 */
	private boolean isCaptionLine(InlineProjectionAnnotation annotation, Position position, IDocument document, int line) {
		if (position.getOffset() > -1 && position.getLength() > -1) {
			try {
				int captionOffset;
				if (position instanceof IProjectionPosition)
					captionOffset= ((IProjectionPosition) position).computeCaptionOffset(document);
				else
					captionOffset= 0;
				int startLine= document.getLineOfOffset(position.getOffset() + captionOffset);
				return line == startLine;
			} catch (BadLocationException x) {
			}
		}
		return false;
	}

	private String getProjectionTextAtLine(ISourceViewer viewer, int line, int numberOfLines) {

		IAnnotationModel model= null;
		if (viewer instanceof ISourceViewerExtension2) {
			ISourceViewerExtension2 viewerExtension= (ISourceViewerExtension2) viewer;
			IAnnotationModel visual= viewerExtension.getVisualAnnotationModel();
			if (visual instanceof IAnnotationModelExtension) {
				IAnnotationModelExtension modelExtension= (IAnnotationModelExtension) visual;
				model= modelExtension.getAnnotationModel(InlineProjectionSupport.INLINEPROJECTION);
			}
		}

		if (model != null) {
			try {
				IDocument document= viewer.getDocument();
				Iterator e= model.getAnnotationIterator();
				while (e.hasNext()) {
					InlineProjectionAnnotation annotation= (InlineProjectionAnnotation) e.next();
					if (!annotation.isCollapsed())
						continue;

					Position position= model.getPosition(annotation);
					if (position == null)
						continue;

					if (isCaptionLine(annotation, position, document, line))
						return getText(document, position.getOffset(), position.getLength(), numberOfLines);

				}
			} catch (BadLocationException x) {
			}
		}

		return null;
	}

	private String getText(IDocument document, int offset, int length, int numberOfLines) throws BadLocationException {
		int endOffset= offset + length;

		try {
			int endLine= document.getLineOfOffset(offset) + Math.max(0, numberOfLines -1);
			IRegion lineInfo= document.getLineInformation(endLine);
			endOffset= Math.min(endOffset, lineInfo.getOffset() + lineInfo.getLength());
		} catch (BadLocationException x) {
		}

		return document.get(offset, endOffset - offset);
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationHoverExtension#getHoverInfo(org.eclipse.jface.text.source.ISourceViewer, org.eclipse.jface.text.source.ILineRange, int)
	 */
	public Object getHoverInfo(ISourceViewer sourceViewer, ILineRange lineRange, int visibleLines) {
		return getProjectionTextAtLine(sourceViewer, lineRange.getStartLine(), visibleLines);
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationHoverExtension#getHoverLineRange(org.eclipse.jface.text.source.ISourceViewer, int)
	 */
	public ILineRange getHoverLineRange(ISourceViewer viewer, int lineNumber) {
		return new LineRange(lineNumber, 1);
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationHoverExtension#canHandleMouseCursor()
	 */
	public boolean canHandleMouseCursor() {
		return false;
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationHoverExtension#getHoverControlCreator()
	 */
	public IInformationControlCreator getHoverControlCreator() {

		if (fInformationControlCreator != null)
			return fInformationControlCreator;

		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent);
			}
		};
	}
}
