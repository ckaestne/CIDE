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

package coloredide.configuration;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.features.Feature;
import coloredide.features.source.ColoredJavaSourceFile;

/**
 * hides colored code when the colors are selected as hidden.
 * 
 * when one of two sibblings is hidden the code in between is removed as well.
 * 
 */
public class HideColoredCodePrinter extends ColoredCodePrinter {

	protected Set<Feature> hiddenColors;

	public String printCode(ColoredJavaSourceFile file,
			Set<Feature> hiddenColors) throws JavaModelException, CoreException {
		this.hiddenColors = hiddenColors;
		return super.printCode(file);
	}

	protected String printCode(String buffer, CompilationUnit root) {

		try {
			String filteredCode = DeleteHiddenNodesVisitor.hideCode(buffer,
					root, nodeColors, hiddenColors);

			ASTParser parser = ASTParser.newParser(root.getAST().apiLevel());
			parser.setResolveBindings(false);
			parser.setSource(filteredCode.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			CompilationUnit newRoot = (CompilationUnit) parser.createAST(null);

			return super.printCode(filteredCode, newRoot);
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

}
