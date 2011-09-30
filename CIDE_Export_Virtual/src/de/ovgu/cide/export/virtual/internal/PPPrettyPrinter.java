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

package de.ovgu.cide.export.virtual.internal;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTNode;

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
		Set<IFeature> ownColors = new HashSet<IFeature>(
				colorManager.getOwnColors(new AstidWrapper(node)));
		if (node.getParent() == null)
			ownColors.addAll(colorManager.getColors(new AstidWrapper(node)));
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
