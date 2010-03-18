package de.ovgu.cide.editor.inlineprojection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.editor.CodeSegment;
import de.ovgu.cide.editor.CodeSegmentCalculator;
import de.ovgu.cide.editor.ColoredTextEditor;
import de.ovgu.cide.editor.IColorProvider;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.tools.featureview.ProjectionKindManager;
import de.ovgu.cide.utils.StrUtils;

/**
 * this class actually calculates the fragments which can later be hidden
 * 
 * actually, there are two different strategies for views on features and views
 * on variants
 * 
 * run manually after editor is openend and after every safe or every a
 * fundamental change to the projection settings
 * 
 * this class dispatches to the appropriet one
 * 
 * 
 * @author ckaestne
 * 
 */
public class ProjectionAnnotationsCalculator {

	public ProjectionAnnotationsCalculator(ColoredTextEditor editor) {
		this.editor = editor;
	}

	private final ColoredTextEditor editor;
	private List<CodeSegment> fAddedPositions = new ArrayList<CodeSegment>();
	private List<CodeSegment> fRemovedPositions = new ArrayList<CodeSegment>();
	private List<AbstractColoredInlineProjectionAnnotation> oldAnnotations = new ArrayList<AbstractColoredInlineProjectionAnnotation>();

	public void calculateProjectionAnnotations() {
		try {
			editor.getSourceFile().refreshAST();
			ISourceFile ast = editor.getSourceFile().getAST();
			if (ProjectionKindManager.isVariantView())
				calculateProjectionAnnotationsOnAST_Variant(ast);
			else
				calculateProjectionAnnotationsOnAST_Feature(ast, editor
						.getSourceFile().getColorManager(),
						getFileContent(editor.getSourceFile().getResource()));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getFileContent(IFile resource) {
		try {
			return StrUtils.strFromInputStream(resource.getContents());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private void calculateProjectionAnnotationsOnAST_Variant(ISourceFile ast) {
		calculatePositions(new IASTNode[] { ast });
		updateInlineProjectionAnnotations(fAddedPositions, editor);
	}

	/**
	 * Reconcile positions based on the AST subtrees
	 * 
	 * checks whether there have been some changes to the AST. changes are
	 * recognized as added positions and removed positions for further
	 * evaluation
	 * 
	 * @param subtrees
	 *            the AST subtrees
	 */
	private void calculatePositions(IASTNode[] subtrees) {
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

	protected void updateInlineProjectionAnnotations(
			List<CodeSegment> addedPositions, ColoredTextEditor editor) {
		if (ProjectionKindManager.isVariantView())
			updateInlineProjectionAnnotations_VariantView(addedPositions,
					editor);
		else
			updateInlineProjectionAnnotations_FeatureView(addedPositions,
					editor);
	}

	/**
	 * calculates which fragments can be projected. depends on the kind of view
	 * 
	 * @param addedPositions
	 * @param editor
	 */
	protected void updateInlineProjectionAnnotations_VariantView(
			List<CodeSegment> addedPositions, ColoredTextEditor editor) {

		InlineProjectionAnnotationModel annotationModel = editor.getViewer()
				.getInlineProjectionAnnotationModel();

		AbstractColoredInlineProjectionAnnotation[] annotations = new AbstractColoredInlineProjectionAnnotation[addedPositions
				.size()];

		// this will hold the new annotations along
		// with their corresponding positions
		HashMap<AbstractColoredInlineProjectionAnnotation, Position> newAnnotations = new HashMap<AbstractColoredInlineProjectionAnnotation, Position>();
		addedPositions = new ArrayList<CodeSegment>(addedPositions);
		ArrayList<AbstractColoredInlineProjectionAnnotation> knownPositions = new ArrayList<AbstractColoredInlineProjectionAnnotation>(
				oldAnnotations);
		ArrayList<AbstractColoredInlineProjectionAnnotation> savedPositions = new ArrayList<AbstractColoredInlineProjectionAnnotation>();

		// move unchanged segments from known to saved (those are not deleted)
		for (Iterator<CodeSegment> i = addedPositions.iterator(); i.hasNext();) {
			CodeSegment seg = i.next();
			for (Iterator<AbstractColoredInlineProjectionAnnotation> a = knownPositions
					.iterator(); a.hasNext();) {
				AbstractColoredInlineProjectionAnnotation known = a.next();
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
			AbstractColoredInlineProjectionAnnotation annotation = new ColoredInlineVariantProjectionAnnotation(
					addedPositions.get(i).getColors(), editor.getSourceFile()
							.getProject(), pos);
			annotation.adjustCollapsing(editor.getFeatureModel()
					.getVisibleFeatures());
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

	/**
	 * this is a line-based implementation only. we hide only entire lines!
	 * 
	 * we show all lines that contain feature code and all lines that contain
	 * mandatory parents (see ViSPLE'08 paper for a detailed description and
	 * examples)
	 * 
	 * basic idea: split the entire document into lines. for each line calculate
	 * all features which lead to the inclusion of this line (any of those
	 * features is sufficient). afterward consolidate duplicate lines to larger
	 * annotations.
	 * 
	 */
	protected void updateInlineProjectionAnnotations_FeatureView(
			List<CodeSegment> addedPositions, ColoredTextEditor editor2) {

		List<Set<IFeature>> featuresPerLine;

	}

	private void calculateProjectionAnnotationsOnAST_Feature(ISourceFile ast,
			final SourceFileColorManager colorManager, String fileContent) {

		List<CodeSegment> segments = calcSegments_Feature(ast, colorManager);
		cleanSegments(segments, fileContent);

		// map segments to lines
		int posStart = 0;
		int length = nextLineBreak(fileContent);
		while (length > 0) {
			String line = fileContent.substring(0, length);

			System.out.println("segment " + posStart + " - "
					+ (posStart + length) + ": " + line + " -- "
					+ checkSegment(posStart, posStart + length, segments));

			fileContent = fileContent.substring(length);
			posStart += length;

			length = nextLineBreak(fileContent);
		}

		System.out.println(segments);
	}

	/**
	 * removes all segments that correspond to empty text
	 * 
	 * @param segments
	 * @param fileContent
	 */
	private void cleanSegments(List<CodeSegment> segments, String fileContent) {
		for (Iterator<CodeSegment> iterator = segments.iterator(); iterator
				.hasNext();) {
			CodeSegment codeSegment = iterator.next();
			String code = fileContent.substring(codeSegment.offset, codeSegment
					.endPosition());
			System.out.println("SEG: " + codeSegment.offset + " - "
					+ codeSegment.endPosition() + " \"" + code + "\" "
					+ codeSegment.getColors());
			if (code.trim().length() == 0)
				iterator.remove();
		}

	}

	private Set<IFeature> checkSegment(int start, int end,
			List<CodeSegment> segments) {

		Set<IFeature> result = new HashSet<IFeature>();
		for (CodeSegment seg : segments)
			if (seg.offset < end && seg.endPosition() >= start)
				result.addAll(seg.getColors());

		return result;
	}

	private int nextLineBreak(String fileContent) {
		int n = fileContent.indexOf('\n');
		int nr = fileContent.indexOf("\r\n");
		int r = fileContent.indexOf('\r');
		if (r == nr)
			r++;

		return Math.min(n, r) + 1;
	}

	/**
	 * segments are inverted to normal segments. set {F1, F2} means that segment
	 * is included if F1 OR F2 is selected.
	 * 
	 */
	private List<CodeSegment> calcSegments_Feature(ISourceFile ast,
			final SourceFileColorManager colorManager) {
		final HashMap<IASTNode, Set<IFeature>> nodeColors = new HashMap<IASTNode, Set<IFeature>>();

		// step1: copy all colors
		ast.accept(new ASTVisitor() {
			@Override
			public void postVisit(IASTNode node) {
				nodeColors.put(node, new HashSet<IFeature>(colorManager
						.getOwnColors(node)));
				// add file colors for root
				if (node.getParent() == null)
					nodeColors.get(node).addAll(colorManager.getColors(node));
			}
		});
		
		// step2a: propagate to all children (because we no longer have inheritance) 
		//TODO wrappers not yet considered!
		ast.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node.getParent() != null)
					nodeColors.get(node).addAll(nodeColors.get(node.getParent()));
				return true;
			}
		});

		// step 2b: propagate all colors to parents
		ast.accept(new ASTVisitor() {
			@Override
			public void postVisit(IASTNode node) {
				if (node.getParent() != null)
					nodeColors.get(node.getParent()).addAll(
							nodeColors.get(node));
			}
		});

		// step 3: propagate all colors to all mandatory children
		ast.accept(new ASTVisitor() {
			@Override
			public void postVisit(IASTNode node) {
				if (node.getParent() != null && !node.isOptional())
					nodeColors.get(node).addAll(
							nodeColors.get(node.getParent()));
			}
		});

		// now we should know for every node the features for which this
		// node is shown (any of those!)

		// now we can create segments
		List<CodeSegment> segments = CodeSegmentCalculator.getCodeSegments(ast,
				new IColorProvider() {
					public Set<IFeature> getColors(IASTNode node) {
						return nodeColors.get(node);
					}
				});
		return segments;
	}
}
