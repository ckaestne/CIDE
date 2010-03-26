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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
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
	// private List<CodeSegment> fAddedPositions = new ArrayList<CodeSegment>();
	// private List<CodeSegment> fRemovedPositions = new
	// ArrayList<CodeSegment>();
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
		List<CodeSegment> segments = calculatePositions(new IASTNode[] { ast });
		updateInlineProjectionAnnotations(segments, editor,
				new AnnotationFactory_VariantView());
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
	 * @return
	 */
	private List<CodeSegment> calculatePositions(IASTNode[] subtrees) {
		List<CodeSegment> list = new ArrayList<CodeSegment>();
		for (int i = 0, n = subtrees.length; i < n; i++)
			list.addAll(CodeSegmentCalculator.getCodeSegments(subtrees[i],
					editor.getSourceFile().getColorManager()));
		// subtrees[i].accept(fCollector);
		deleteEmptySegments(list);
		return list;
		// fAddedPositions.addAll(list);
		// List<CodeSegment> oldPositions = fRemovedPositions;
		// List<CodeSegment> newPositions = list;
		// for (int i = 0, n = oldPositions.size(); i < n; i++) {
		// CodeSegment current = oldPositions.get(i);
		// if (current != null)
		// newPositions.add(current);
		// }
		// fRemovedPositions = newPositions;
	}

	private void deleteEmptySegments(List<CodeSegment> list) {
		for (Iterator<CodeSegment> i = list.iterator(); i.hasNext();) {
			CodeSegment segment = i.next();
			if (segment.getColors().isEmpty())
				i.remove();
		}
	}

	private static abstract class AnnotationFactory {
		abstract AbstractColoredInlineProjectionAnnotation newAnnotation(
				Set<IFeature> features, IProject project, Position pos,
				ProjectionColorManager projectionColorManager);
	}

	private static class AnnotationFactory_VariantView extends
			AnnotationFactory {
		AbstractColoredInlineProjectionAnnotation newAnnotation(
				Set<IFeature> features, IProject project, Position pos,
				ProjectionColorManager projectionColorManager) {
			return new ColoredInlineVariantProjectionAnnotation(features,
					project, pos, projectionColorManager);
		}
	}

	private static class AnnotationFactory_FeatureView extends
			AnnotationFactory {
		AbstractColoredInlineProjectionAnnotation newAnnotation(
				Set<IFeature> features, IProject project, Position pos,
				ProjectionColorManager projectionColorManager) {
			return new ColoredInlineFeatureProjectionAnnotation(features,
					project, pos, projectionColorManager);
		}
	}

	/**
	 * after calculating the segments to hide (updatedPositions), this creates
	 * the according annotations and sets visibility
	 * 
	 * @param updatedPositions
	 * @param editor
	 */
	protected void updateInlineProjectionAnnotations(
			List<CodeSegment> updatedPositions, ColoredTextEditor editor,
			AnnotationFactory factory) {

		InlineProjectionAnnotationModel annotationModel = editor.getViewer()
				.getInlineProjectionAnnotationModel();

		AbstractColoredInlineProjectionAnnotation[] annotations = new AbstractColoredInlineProjectionAnnotation[updatedPositions
				.size()];

		// this will hold the new annotations along
		// with their corresponding positions
		HashMap<AbstractColoredInlineProjectionAnnotation, Position> newAnnotations = new HashMap<AbstractColoredInlineProjectionAnnotation, Position>();
		updatedPositions = new ArrayList<CodeSegment>(updatedPositions);
		ArrayList<AbstractColoredInlineProjectionAnnotation> knownPositions = new ArrayList<AbstractColoredInlineProjectionAnnotation>(
				oldAnnotations);
		ArrayList<AbstractColoredInlineProjectionAnnotation> savedPositions = new ArrayList<AbstractColoredInlineProjectionAnnotation>();

		// move unchanged segments from known to saved (those are not deleted)
		for (Iterator<CodeSegment> i = updatedPositions.iterator(); i.hasNext();) {
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

		for (int i = 0; i < updatedPositions.size(); i++) {
			Position pos = new Position(updatedPositions.get(i).offset,
					updatedPositions.get(i).length);
			AbstractColoredInlineProjectionAnnotation annotation = factory
					.newAnnotation(updatedPositions.get(i).getColors(), editor
							.getSourceFile().getProject(), pos, editor
							.getProjectionColorManager());
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
	private void calculateProjectionAnnotationsOnAST_Feature(ISourceFile ast,
			final SourceFileColorManager colorManager, String fileContent) {

		List<CodeSegment> plainSegments = calcSegments_Feature(ast,
				colorManager);
		cleanSegments(plainSegments, fileContent);

		List<CodeSegment> lineSegments = new ArrayList<CodeSegment>();
		// map segments to lines
		int posStart = 0;
		int length = nextLineBreak(fileContent);
		while (length > 0) {
			String line = fileContent.substring(0, length);

			Set<IFeature> color = determineLineColors(posStart, posStart
					+ length, line.toCharArray(), plainSegments);

//			System.out.println("segment " + posStart + " - "
//					+ (posStart + length) + ": " + line + " -- " + color);

			// if (!color.isEmpty())
			lineSegments.add(new CodeSegment(posStart, posStart + length,
					color, false));

			fileContent = fileContent.substring(length);
			posStart += length;

			length = nextLineBreak(fileContent);
		}

//		System.out.println(plainSegments);

		// TODO die segmentierung funktioniert nicht gut, da sie zuviel
		// whitespace mit einschliesst. kann man das eleganter loesen?

		updateInlineProjectionAnnotations(lineSegments, editor,
				new AnnotationFactory_FeatureView());
	}

	/**
	 * removes all segments that correspond to empty text
	 * 
	 * @param segments
	 * @param fileContent
	 */
	private void cleanSegments(List<CodeSegment> segments, String fileContent) {
		for (Iterator<CodeSegment> iterator = segments.iterator(); iterator
				.hasNext();) try{
			CodeSegment codeSegment = iterator.next();
			String code = fileContent.substring(codeSegment.offset, codeSegment
					.endPosition());
//			System.out.println("SEG: " + codeSegment.offset + " - "
//					+ codeSegment.endPosition() + " \"" + code + "\" "
//					+ codeSegment.getColors());
			if (code.trim().length() == 0)
				iterator.remove();
		}catch (StringIndexOutOfBoundsException e){}

	}

	/**
	 * checks all non-white characters in a line for colors
	 * 
	 * @param offset
	 * @param end
	 * @param line
	 * @param segments
	 * @return
	 */
	private Set<IFeature> determineLineColors(int offset, int end, char[] line,
			List<CodeSegment> segments) {

		// determine relevant segments first, for performance
		List<CodeSegment> relevantSegments = new ArrayList<CodeSegment>();
		for (CodeSegment seg : segments)
			if (seg.offset < end && seg.endPosition() >= offset)
				relevantSegments.add(seg);

		// check every character in the line
		Set<IFeature> result = new HashSet<IFeature>();
		for (int i = 0; i < line.length; i++) {
			char c = line[i];
			if (!Character.isWhitespace(c)) {
				int charPos = offset + i;
				for (CodeSegment seg : relevantSegments)
					if (charPos >= seg.offset && charPos < seg.endPosition())
						result.addAll(seg.getColors());
			}
		}

		return result;
	}

	private static int nextLineBreak(String fileContent) {
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

		// step2a: propagate to all children (because we no longer have
		// inheritance)
		// TODO wrappers not yet considered!
		ast.accept(new ASTVisitor() {
			@Override
			public boolean visit(IASTNode node) {
				if (node.getParent() != null)
					nodeColors.get(node).addAll(
							nodeColors.get(node.getParent()));
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
