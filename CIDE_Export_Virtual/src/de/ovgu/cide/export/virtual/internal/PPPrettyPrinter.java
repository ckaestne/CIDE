package de.ovgu.cide.export.virtual.internal;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTNode;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.language.jdt.AstidWrapper;

/**
 * prints the AST with preprocessor instructions for features
 * 
 * @author cKaestner
 * 
 */
public class PPPrettyPrinter extends CopiedNaiveASTFlattener {

	private SourceFileColorManager colorManager;

	// private final IProject project;

	private final IPPExportOptions options;

	public PPPrettyPrinter(SourceFileColorManager colorManager,
			IProject project, IPPExportOptions options) {
		this.colorManager = colorManager;
		// this.project = project;
		this.options = options;
	}

	@Override
	public void preVisit(ASTNode node) {
		Set<IFeature> ownColors = colorManager.getOwnColors(new AstidWrapper(
				node));
		if (ownColors != null && ownColors.size() > 0) {
				printPPOpen(ownColors);
		}

		super.preVisit(node);
	}

	@Override
	public void postVisit(ASTNode node) {
		Set<IFeature> ownColors = colorManager.getOwnColors(new AstidWrapper(
				node));

		if (ownColors != null && ownColors.size() > 0) {
				printPPClose(ownColors);

		}

		super.postVisit(node);
	}

	private void printPPOpen(Set<IFeature> ownColors) {
		String ifdefStr = options.getStartInstruction(ownColors);
		if (options.inNewLine())
			if (buffer.length() > 0) {
				char lastChar = buffer.charAt(buffer.length() - 1);
				if (lastChar != '\n')
					buffer.append('\n');
			}
		buffer.append(ifdefStr);
	}

	private void printPPClose(Set<IFeature> ownColors) {
		String endifdefStr = options.getEndInstruction(ownColors);
		if (options.inNewLine())
		if (buffer.length() > 0) {
			char lastChar = buffer.charAt(buffer.length() - 1);
			if (lastChar != '\n')
				buffer.append('\n');
		}
		buffer.append(endifdefStr);
	}



}
