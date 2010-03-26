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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.swt.graphics.RGB;

import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.CompilationUnitColorManager;

public class ColoredCodePrinter extends CodePrinter {

	protected CompilationUnitColorManager nodeColors;

	public String printCode(ColoredJavaSourceFile file)
			throws JavaModelException, CoreException {
		ICompilationUnit compUnit = file.getCompilationUnit();
		CompilationUnit ast = file.getAST();
		this.nodeColors = file.getColorManager();
		return printCode(compUnit.getBuffer().getContents(), ast);
	}

	protected String printCode(String buffer, CompilationUnit ast) {

		final List<CodeSegment> list = CodeSegmentCalculator.getCodeSegments(
				ast, nodeColors);
		return printSegments(buffer, list);
	}

	protected String printSegments(String buffer, final List<CodeSegment> list) {
		StringBuffer result = new StringBuffer();
		for (CodeSegment seg : list) {
			result.append(printSegment(buffer, seg));
		}

		return result.toString();
	}

	protected String printSegment(String buffer, CodeSegment seg) {
		return getOpenSpan(FeatureManager.getCombinedRGB(seg.colors))
				+ buffer.substring(seg.getOffset(), seg.endPosition())
				+ getCloseSpan();
	}

	private String getOpenSpan(RGB rgb) {
		return "<span style=\"background-color:RGB(" + rgb.red + ","
				+ rgb.green + "," + rgb.blue + ")\">";
	}

	private String getCloseSpan() {
		return "</span>";
	}
}
