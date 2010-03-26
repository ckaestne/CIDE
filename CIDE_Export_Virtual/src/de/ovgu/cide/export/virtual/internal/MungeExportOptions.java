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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.useroptions.IUserOption;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;

public class MungeExportOptions implements IPPExportOptions {

	public String getStartInstruction(Set<IFeature> features) {
		String result = "";
		ArrayList<IFeature> sortedFeatures = new ArrayList<IFeature>(features);
		Collections.sort(sortedFeatures);
		for (IFeature f : sortedFeatures)
			result += "/*if[" + getFeatureToken(f) + "]*/";
		return result;
	}

	public boolean inNewLine() {
		return false;
	}

	private String getFeatureToken(IFeature f) {
		String featureName = f.getName().toUpperCase();
		StringBuffer result = new StringBuffer();
		for (int idx = 0; idx < featureName.length(); idx++)
			if (Character.isLetter(featureName.charAt(idx)))
				result.append(featureName.charAt(idx));
		return result.toString();
	}

	public String getEndInstruction(Set<IFeature> features) {
		String result = "";
		ArrayList<IFeature> sortedFeatures = new ArrayList<IFeature>(features);
		Collections.sort(sortedFeatures);
		Collections.reverse(sortedFeatures);
		for (IFeature f : sortedFeatures)
			result += "/*end[" + getFeatureToken(f) + "]*/";
		return result;
	}

	public List<IUserOption> getUserOptions() {
		return null;
	}

	public CopiedNaiveASTFlattener getPrettyPrinter(ColoredSourceFile sourceFile) {
		return new PPPrettyPrinter(sourceFile.getColorManager(), sourceFile
				.getProject(), this);
	}

	public CompilationUnit refactorAST(CompilationUnit root,
			ColoredSourceFile sourceFile) {
		return root;
	}

}
