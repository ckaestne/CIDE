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

package de.ovgu.cide.editor;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import de.ovgu.cide.alternativefeatures.AlternativeAnnotation;
import de.ovgu.cide.features.source.ColoredSourceFile;

public class ColoredSourceViewerConfiguration extends SourceViewerConfiguration {
	private IPresentationRepairer repairer;
	private ColorDamager damager;
	private ColoredTextHover textHover = null;
	private ColoredTextEditor editor;

	public ColoredSourceViewerConfiguration(ColoredSourceFile sourceFile,
			ColoredTextEditor editor) {
		this.editor = editor;

		if (sourceFile != null) {
			repairer = new ColorRepairer(sourceFile, editor);
			damager = new ColorDamager(sourceFile);
			if (sourceFile.isColored())
				textHover = new ColoredTextHover(sourceFile);
		}
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		reconciler
				.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		if (damager != null)
			reconciler.setDamager(damager, IDocument.DEFAULT_CONTENT_TYPE);
		if (repairer != null)
			reconciler.setRepairer(repairer, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}

	@Override
	public ITextHover getTextHover(ISourceViewer sourceViewer,
			String contentType) {
		return textHover;
	}

	@Override
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return new IAnnotationHover() {

			@SuppressWarnings("unchecked")
			public String getHoverInfo(ISourceViewer sourceViewer,
					int lineNumber) {

				/*
				 * Recht aufwendige Arbeit, da jede Annotation auf Relevanz
				 * überprüft wird. Performanter wäre eine Map, die Zeilen mit
				 * den entsprechenden Annotationen identifiziert. Diese Map
				 * müsste aber bei Änderungen im Sourcecode aktualisiert werden.
				 * Mit dieser Lösung können wir vom bereits vorhandenen
				 * Mechanismus profitieren, der Annotationen bei Änderungen
				 * stabil hält.
				 */

				IAnnotationModel annotationModel = sourceViewer
						.getAnnotationModel();
				LinkedList<String> list = new LinkedList<String>();

				Iterator iterator = annotationModel.getAnnotationIterator();
				while (iterator.hasNext()) {
					Annotation annotation = (Annotation) iterator.next();
					try {
						if (!annotation.isMarkedDeleted()
								&& annotation.getType().equals(
										AlternativeAnnotation.ALTERNATIVE_TYPE)
								&& editor.getDocument().getLineOfOffset(
										annotationModel.getPosition(annotation)
												.getOffset()) == lineNumber) {

							list.add(annotation.getText());
						}
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (list.size() > 0) {
					StringBuilder stringBuilder = new StringBuilder(
							100 + 50 * list.size());
					stringBuilder
							.append("Alternatives available for following codefragments:");

					Collections.sort(list);
					for (String s : list) {
						stringBuilder.append("\n   - ").append(s);
					}

					return stringBuilder.toString();
				}

				return null;
			}

			// private String truncate(String text, int maxLen) {
			// if ((text == null) || (text.length() <= maxLen))
			// return text;
			// return text.substring(0, maxLen - 5) + " ...";
			// }
		};
	}

//	IReconciler reconciler = null;
//
//	@Override
//	public IReconciler getReconciler(ISourceViewer sourceViewer) {
//		if (reconciler == null) {
//			ProjectionReconcilingStrategy strategy = new ProjectionReconcilingStrategy();
//			strategy.setEditor(editor);
//
//			reconciler = new MonoReconciler(strategy, false);
//		}
//		return reconciler;
//	}
}