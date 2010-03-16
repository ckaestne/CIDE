package de.ovgu.cide.export.virtual.internal;

import java.util.Collections;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.language.jdt.AstidWrapper;

/**
 * prints the AST with preprocessor instructions for features
 * 
 * @author cKaestner
 * 
 */
public class PP2JavaPrettyPrinter extends CopiedNaiveASTFlattener {

	private SourceFileColorManager colorManager;

	private final IPPExportOptions options;

	public PP2JavaPrettyPrinter(SourceFileColorManager colorManager,
			IProject project, IPPExportOptions options) {
		this.colorManager = colorManager;
		// this.project = project;
		this.options = options;
	}

	final Stack<Set<IFeature>> outerAnnotations = new Stack<Set<IFeature>>();
	ASTNode prevSibling = null;
	int siblingDepth = 0;
	int revertSize = 0;
	int revertPos = 0;

	@Override
	public void preVisit(ASTNode node) {

		Set<IFeature> ownColors = colorManager.getOwnColors(new AstidWrapper(
				node));

		if (outerAnnotations.contains(ownColors))
			ownColors = Collections.emptySet();

		if (ownColors != null && ownColors.size() > 0) {
			boolean revert = (prevSibling != null
					&& siblingDepth == outerAnnotations.size() && revertSize > 0);
			if (revert)
				revert = ownColors.equals(colorManager
						.getOwnColors(new AstidWrapper(prevSibling)));

			if (revert)
				buffer.delete(revertPos, revertPos + revertSize);

			if (!revert)
				if (isStatement(node))
					printPPOpen(ownColors, options);
				else
					printPPOpen(ownColors, new AntennaExportOptions());
		}
		outerAnnotations.push(ownColors);

		prevSibling = null;

		super.preVisit(node);
	}

	@Override
	public void postVisit(ASTNode node) {
		Set<IFeature> ownColors = outerAnnotations.pop();

		revertSize = 0;
		revertPos = buffer.length();
		if (ownColors != null && ownColors.size() > 0) {
			if (isStatement(node))
				revertSize = printPPClose(ownColors, options);
			else
				revertSize = printPPClose(ownColors, new AntennaExportOptions());
		}

		prevSibling = node;
		siblingDepth = outerAnnotations.size();

		super.postVisit(node);
	}

	private boolean isStatement(ASTNode node) {
		if (node instanceof Block)
			return node.getParent() instanceof Statement;
		return node instanceof Statement;
	}

	private void printPPOpen(Set<IFeature> ownColors, IPPExportOptions options) {
		String ifdefStr = options.getStartInstruction(ownColors);
		if (options.inNewLine())
			if (buffer.length() > 0) {
				char lastChar = buffer.charAt(buffer.length() - 1);
				if (lastChar != '\n')
					buffer.append('\n');
			}
		buffer.append(ifdefStr);
	}

	private int printPPClose(Set<IFeature> ownColors, IPPExportOptions options) {
		int size = 0;
		String endifdefStr = options.getEndInstruction(ownColors);
		if (options.inNewLine())
			if (buffer.length() > 0) {
				char lastChar = buffer.charAt(buffer.length() - 1);
				if (lastChar != '\n') {
					buffer.append('\n');
					size++;
				}
			}
		buffer.append(endifdefStr);
		return size + endifdefStr.length();
	}

}
