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

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.useroptions.IUserOptionProvider;

public interface IPhysicalOptions extends IUserOptionProvider {

	boolean getDerivativesInSubdirectories();

	/**
	 * debug only
	 * 
	 * @return
	 */
	boolean isDebugPrintInnerClassRefinements();

	/**
	 * if true, than method objects are refactored into static top-level
	 * classes, i.e., a this pointer is passed and own files are created.
	 * 
	 * otherwise method objects are just created and refined as non-static inner
	 * classes
	 */
	boolean getMethodObjectsInStaticToplevelClass();

	/**
	 * move all classes to top level classes (necessary for AHEAD)
	 */
	boolean getFlattenPackages();

	/**
	 * if all classes are moved to top level classes, remove all import
	 * declarations that start with this prefix (null if none), e.g.
	 * "com.sleepycat"
	 */
	String getRemovePackagePrefix();

	/**
	 * allows a prefix for each file, even depending on the current layer. used
	 * by ahead to add a "layer X;" specification
	 * 
	 * null if no header is specified
	 * 
	 * @param layerName
	 * @return
	 */
	String getFileHeader(String layerName);

	/**
	 * returns the pretty printer that handles the correct output of refinements
	 * and Super calls etc.
	 * 
	 * @return
	 */
	CopiedNaiveASTFlattener getPrettyPrinter();

	/**
	 * file name for a given class name
	 * 
	 * @param identifier
	 * @return
	 */
	String getFilename(String className);

	

}
