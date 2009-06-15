package de.ovgu.cide.editor.inlineprojection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.jface.text.source.Annotation;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.editor.CodeSegment;
import de.ovgu.cide.editor.CodeSegmentCalculator;
import de.ovgu.cide.editor.ColoredTextEditor;

public class ProjectionReconcilingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension {

	private IDocument fDocument;
	private ColoredTextEditor editor;
	private List<CodeSegment> fAddedPositions = new ArrayList<CodeSegment>();
	private List<CodeSegment> fRemovedPositions = new ArrayList<CodeSegment>();
	private List<ColoredInlineProjectionAnnotation> oldAnnotations = new ArrayList<ColoredInlineProjectionAnnotation>();

	@Override
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		initialReconcile();
	}

	@Override
	public void reconcile(IRegion partition) {
		initialReconcile();
	}

	@Override
	public void setDocument(IDocument document) {
		this.fDocument = document;

	}

	@Override
	public void initialReconcile() {
		try {
			editor.getSourceFile().refreshAST();
			ISourceFile ast = editor.getSourceFile().getAST();
			reconcileAST(ast);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void reconcileAST(ISourceFile ast) {
		reconcilePositions(new IASTNode[] { ast });
		updateInlineProjectionAnnotations(fAddedPositions, editor);
	}

	/**
	 * Reconcile positions based on the AST subtrees
	 * 
	 * @param subtrees
	 *            the AST subtrees
	 */
	private void reconcilePositions(IASTNode[] subtrees) {
		List<CodeSegment> list = new ArrayList<CodeSegment>();
		for (int i = 0, n = subtrees.length; i < n; i++)
			list.addAll(CodeSegmentCalculator.getCodeSegments(subtrees[i],
					editor.getSourceFile().getColorManager()));
		// subtrees[i].accept(fCollector);
		deleteEmptySegments(list);
		fAddedPositions.addAll(list);
		List<CodeSegment> oldPositions = fRemovedPositions;
		List<CodeSegment> newPositions = list;
		for (int i = 0, n = oldPositions.size(); i < n; i++) {
			CodeSegment current = oldPositions.get(i);
			if (current != null)
				newPositions.add(current);
		}
		fRemovedPositions = newPositions;
	}

	private void deleteEmptySegments(List<CodeSegment> list) {
		for (Iterator<CodeSegment> i = list.iterator(); i.hasNext();) {
			CodeSegment segment = i.next();
			if (segment.getColors().isEmpty())
				i.remove();
		}
	}

	@Override
	public void setProgressMonitor(IProgressMonitor monitor) {
	}

	public void setEditor(ColoredTextEditor editor) {
		this.editor = editor;
	}

	protected void updateInlineProjectionAnnotations(
			List<CodeSegment> addedPositions, ColoredTextEditor editor) {
		InlineProjectionAnnotationModel annotationModel = editor.getViewer()
				.getInlineProjectionAnnotationModel();

		ColoredInlineProjectionAnnotation[] annotations = new ColoredInlineProjectionAnnotation[addedPositions
				.size()];

		// this will hold the new annotations along
		// with their corresponding positions
		HashMap<ColoredInlineProjectionAnnotation, Position> newAnnotations = new HashMap<ColoredInlineProjectionAnnotation, Position>();
		addedPositions = new ArrayList<CodeSegment>(addedPositions);
		ArrayList<ColoredInlineProjectionAnnotation> knownPositions = new ArrayList<ColoredInlineProjectionAnnotation>(
				oldAnnotations);
		ArrayList<ColoredInlineProjectionAnnotation> savedPositions = new ArrayList<ColoredInlineProjectionAnnotation>();

		// move unchanged segments from known to saved (those are not deleted)
		for (Iterator<CodeSegment> i = addedPositions.iterator(); i.hasNext();) {
			CodeSegment seg = i.next();
			for (Iterator<ColoredInlineProjectionAnnotation> a = knownPositions
					.iterator(); a.hasNext();) {
				ColoredInlineProjectionAnnotation known = a.next();
				if (seg.offset == known.getPosition().getOffset()
						&& seg.length == known.getPosition().getLength()
						&& seg.getColors().equals(known.getColors())) {
					i.remove();
					a.remove();
					savedPositions.add(known);
				}
			}

		}

		for (int i = 0; i < addedPositions.size(); i++) {
			Position pos = new Position(addedPositions.get(i).offset,
					addedPositions.get(i).length);
			ColoredInlineProjectionAnnotation annotation = new ColoredInlineProjectionAnnotation(
					addedPositions.get(i).getColors(), editor.getSourceFile()
							.getProject(), pos);
			annotation.adjustCollapsing(editor.getProjectionColorManager()
					.getExpandedColors());
			newAnnotations.put(annotation, pos);

			annotations[i] = annotation;
		}

		if (annotationModel != null) {
			Annotation[] deletedAnnotations = new Annotation[knownPositions
					.size()];
			deletedAnnotations = knownPositions.toArray(deletedAnnotations);
			annotationModel.modifyAnnotations(deletedAnnotations,
					newAnnotations, null);
		}
		oldAnnotations = savedPositions;
		oldAnnotations.addAll(newAnnotations.keySet());
	}

}
