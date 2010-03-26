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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gparser.ParseException;
import cide.gparser.TokenMgrError;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.tools.featureview.ProjectionKindManager;
import de.ovgu.cide.utils.ColorHelper;

/**
 * class responsible to paint the background color in the correct locations
 * 
 * @author ckaestne
 * 
 */
public class ColorRepairer implements IPresentationRepairer {

	private final ColoredSourceFile sourceFile;
	private ColoredTextEditor editor;

	// currently just deactivated

	public ColorRepairer(ColoredSourceFile sourceFile, ColoredTextEditor editor) {
		this.sourceFile = sourceFile;
		this.editor = editor;
	}

	public void createPresentation(TextPresentation presentation,
			ITypedRegion damage) {
		if (sourceFile == null || !sourceFile.isColored()) {
			editor.markNotColored();
			return;
		}
		if (editor.isDirty()) {
			editor.markFileEdited();
			return;
		}

		try {
			long start = System.currentTimeMillis();
			IASTNode root = sourceFile.getAST();

			if (root != null) {
				List<CodeSegment> segments = CodeSegmentCalculator
						.getCodeSegments(root, sourceFile.getColorManager());

				if (!ProjectionKindManager.isVariantView()
						&& editor.getProjectionColorManager()
								.isProjectionActive())
					segments = grayInvisibleCode(segments, root, sourceFile
							.getColorManager(), sourceFile.getFeatureModel()
							.getVisibleFeatures());

				for (CodeSegment segment : segments)
					processSegment(presentation, damage, segment);
			}
			editor.markASTOk(System.currentTimeMillis() - start);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			editor.markCoreException(e);
		} catch (TokenMgrError e) {
			editor.markParseException(e);
		} catch (ParseException e) {
			editor.markParseException(e);
		}
	}

	/**
	 * experimental implementation to change the foreground color of features
	 * marked invisible to gray
	 * 
	 * 
	 * @param segments
	 *            previous segmentation for background colors
	 * @param ast
	 * @param colorManager
	 * @param visibleFeatures
	 *            visible features, all others are grayed out
	 * @return updated segments
	 */
	private List<CodeSegment> grayInvisibleCode(List<CodeSegment> segments,
			IASTNode ast, SourceFileColorManager colorManager,
			Collection<IFeature> visibleFeatures) {
		ArrayList<CodeSegment> result = new ArrayList<CodeSegment>();

		List<Position> hiddenSegments = getHiddenSegments(ast, colorManager,
				visibleFeatures);

		List<Integer> splitPositions = getSplitPositions(hiddenSegments);

		// split code segments according to hidden parts
		for (CodeSegment seg : segments) {
			for (int s : splitPositions) {
				if (s > seg.offset && s < seg.endPosition()) {
					CodeSegment firstPart = seg.copy();
					firstPart.setEndPosition(s);
					seg.moveStartPosition(s);
					result.add(firstPart);
				}
			}
			result.add(seg);
		}
		// set (previously split) code segments as hidden where appropiate
		for (CodeSegment seg : result) {
			for (Position hiddenSeg : hiddenSegments) {
				if (covers(hiddenSeg, seg))
					seg.isHidden = true;
			}
		}

		return result;
	}

	private List<Integer> getSplitPositions(List<Position> segs) {
		List<Integer> result = new ArrayList<Integer>();
		for (Position seg : segs) {
			if (!result.contains(seg.offset))
				result.add(seg.offset);
			if (!(result.contains(seg.offset + seg.length)))
				result.add(seg.offset + seg.length);
		}
		return result;
	}

	private List<Position> getHiddenSegments(IASTNode ast,
			final SourceFileColorManager colorManager,
			final Collection<IFeature> visibleFeatures) {

		final LinkedList<Position> invisibleSegments = new LinkedList<Position>();
		ast.accept(new ASTVisitor() {

			private Stack<List<IASTNode>> visibleNodes = new Stack<List<IASTNode>>();

			@Override
			public boolean visit(IASTNode node) {
				visibleNodes.push(new ArrayList<IASTNode>());
				return super.visit(node);
			}

			@Override
			public void postVisit(IASTNode node) {
				List<IASTNode> visibleChildren = visibleNodes.pop();

				boolean isVisible = !visibleChildren.isEmpty();
				if (!isVisible
						&& overlap(colorManager.getColors(node),
								visibleFeatures))
					isVisible = true;

				if (isVisible && !visibleNodes.isEmpty())
					visibleNodes.peek().add(node);

				if (node.isOptional() && !isVisible) {
					Position newPos = new Position(node.getStartPosition(),
							node.getLength());
					// avoid overlapping segments
					while (!invisibleSegments.isEmpty()
							&& covers(newPos, invisibleSegments.getLast()))
						invisibleSegments.removeLast();
					invisibleSegments.add(newPos);
				}

				super.postVisit(node);
			}

			private boolean overlap(Collection<IFeature> s1,
					Collection<IFeature> s2) {
				for (IFeature f : s1)
					if (s2.contains(f))
						return true;
				return false;
			}
		});

		return invisibleSegments;
	}

	private boolean covers(Position outer, Position inner) {
		if (outer.offset <= inner.offset)
			if (outer.offset + outer.length >= inner.offset + inner.length)
				return true;
		return false;
	}

	private void processSegment(TextPresentation presentation,
			ITypedRegion damage, CodeSegment segment) {
		// colors
		if (inRange(segment, damage))
			presentation.addStyleRange(createStyleRange(segment));

		// // projection
		// InlineProjectionAnnotationModel annotationModel =
		// editor.getSourceViewerI()
		// .getInlineProjectionAnnotationModel();
		// if (annotationModel != null) {
		// ColoredInlineProjectionAnnotation annotation = new
		// ColoredInlineProjectionAnnotation();
		// annotation.setColors(segment.getColors());
		// Position pos = new Position(segment.offset, segment.length);
		// annotation.setPosition(pos);
		// annotation.adjustCollapsing(editor.getProjectionColorManager()
		// .getExpandedColors());
		// HashMap newAnnotations = new HashMap();
		// newAnnotations.put(annotation, pos);
		//
		// annotationModel.modifyAnnotations(null, newAnnotations, null);
		// }
	}

	public void setDocument(IDocument document) {
		// this.document = document;
	}

	private Color gray = new Color(Display.getDefault(), 128, 128, 128);

	/**
	 * @return Returns a corresponding style range.
	 */
	public StyleRange createStyleRange(CodeSegment segment) {

		Color background = null;
		if (!segment.getColors().isEmpty())
			background = ColorHelper.getCombinedColor(segment.getColors());
		Color foreground = null;
		if (segment.isHidden)
			foreground = gray;
		StyleRange styleRange = new StyleRange(segment.getOffset(), segment
				.getLength(), foreground, background/* , fontStyle */);

		return styleRange;
	}

	private boolean inRange(CodeSegment node, ITypedRegion damage) {
		if (node.getOffset() + node.getLength() < damage.getOffset())
			return false;
		if (node.getOffset() > damage.getOffset() + damage.getLength())
			return false;
		return true;
	}

	// protected void updateInlineProjectionAnnotations(
	// List<CodeSegment> addedPositions,
	// ColoredCompilationUnitEditor editor) {
	//
	// ColoredInlineProjectionAnnotation[] annotations = new
	// ColoredInlineProjectionAnnotation[addedPositions
	// .size()];
	//
	// // this will hold the new annotations along
	// // with their corresponding positions
	// HashMap newAnnotations = new HashMap();
	// addedPositions = new ArrayList<CodeSegment>(addedPositions);
	// ArrayList<ColoredInlineProjectionAnnotation> knownPositions = new
	// ArrayList<ColoredInlineProjectionAnnotation>(
	// oldAnnotations);
	// ArrayList<ColoredInlineProjectionAnnotation> savedPositions = new
	// ArrayList<ColoredInlineProjectionAnnotation>();
	//
	// // move unchanged segments from known to saved (those are not deleted)
	// for (Iterator<CodeSegment> i = addedPositions.iterator(); i.hasNext();) {
	// CodeSegment seg = i.next();
	// for (Iterator<ColoredInlineProjectionAnnotation> a = knownPositions
	// .iterator(); a.hasNext();) {
	// ColoredInlineProjectionAnnotation known = a.next();
	// if (seg.offset == known.getPosition().getOffset()
	// && seg.length == known.getPosition().getLength()
	// && seg.getColors().equals(known.getColors())) {
	// i.remove();
	// a.remove();
	// savedPositions.add(known);
	// }
	// }
	//
	// }
	//
	// for (int i = 0; i < addedPositions.size(); i++) {
	// ColoredInlineProjectionAnnotation annotation = new
	// ColoredInlineProjectionAnnotation();
	// annotation.setColors(addedPositions.get(i).getColors());
	// Position pos = new Position(addedPositions.get(i).offset,
	// addedPositions.get(i).length);
	// annotation.setPosition(pos);
	// annotation.adjustCollapsing(editor.getProjectionColorManager()
	// .getExpandedColors());
	// newAnnotations.put(annotation, pos);
	//
	// annotations[i] = annotation;
	// }
	//
	// if (annotationModel != null) {
	// Annotation[] deletedAnnotations = new Annotation[knownPositions
	// .size()];
	// deletedAnnotations = knownPositions.toArray(deletedAnnotations);
	// annotationModel.modifyAnnotations(deletedAnnotations,
	// newAnnotations, null);
	// }
	// oldAnnotations = savedPositions;
	// oldAnnotations.addAll(newAnnotations.keySet());
	// }

}
