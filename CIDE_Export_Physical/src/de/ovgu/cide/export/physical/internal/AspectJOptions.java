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

import java.util.Collections;
import java.util.List;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.useroptions.IUserOption;

public class AspectJOptions implements IPhysicalOptions {

	public boolean getDerivativesInSubdirectories() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFileHeader(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFilename(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getFlattenPackages() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getMethodObjectsInStaticToplevelClass() {
		// TODO Auto-generated method stub
		return false;
	}

	public CopiedNaiveASTFlattener getPrettyPrinter() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemovePackagePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isDebugPrintInnerClassRefinements() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<IUserOption> getUserOptions() {
		return Collections.EMPTY_LIST;
	}

}
