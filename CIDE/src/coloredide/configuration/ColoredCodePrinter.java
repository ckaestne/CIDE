package coloredide.configuration;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.swt.graphics.RGB;

import coloredide.features.FeatureManager;
import coloredide.features.source.IColorManager;
import coloredide.features.source.IColoredJavaSourceFile;

public class ColoredCodePrinter extends CodePrinter {

	protected IColorManager nodeColors;

	public String printCode(IColoredJavaSourceFile file)
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
