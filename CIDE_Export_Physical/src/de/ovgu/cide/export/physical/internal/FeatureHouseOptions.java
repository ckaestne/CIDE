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

package de.ovgu.cide.export.physical.internal;

import java.util.ArrayList;
import java.util.List;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.physical.ahead.FSTComposerJavaPrettyPrinter;
import de.ovgu.cide.export.useroptions.BooleanUserOption;
import de.ovgu.cide.export.useroptions.IUserOption;

public class FeatureHouseOptions implements IPhysicalOptions {

	BooleanUserOption derivativesInSubdirectories = new BooleanUserOption(
			"Derivatives in subdirectories", true);

	public boolean getFlattenPackages() {
		return false;
	}

	public boolean getMethodObjectsInStaticToplevelClass() {
		return false;
	}

	public boolean getDerivativesInSubdirectories() {
		return derivativesInSubdirectories.getValue();
	}

	public boolean isDebugPrintInnerClassRefinements() {
		return false;
	}

	public CopiedNaiveASTFlattener getPrettyPrinter() {
		return new FSTComposerJavaPrettyPrinter(this);
	}

	public String getFileHeader(String layerName) {
		return "";
	}

	public String getRemovePackagePrefix() {
		return null;
	}

	public String getFilename(String className) {
		return className + ".java";
	}

	public List<IUserOption> getUserOptions() {
		List<IUserOption> result = new ArrayList<IUserOption>(2);
		result.add(derivativesInSubdirectories);
		return result;
	}
}
