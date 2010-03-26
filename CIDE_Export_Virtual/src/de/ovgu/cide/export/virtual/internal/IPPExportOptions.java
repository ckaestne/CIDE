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

import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.useroptions.IUserOptionProvider;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;

/**
 * how to print annotations? note: we assume ifdef semantics, i.e. annotations
 * may be nested, but always close in the reverse order
 * 
 * 
 * @author ckaestne
 * 
 */
public interface IPPExportOptions extends IUserOptionProvider {
	/**
	 * should the start and end instructions be printed in a new line? (i.e.
	 * should a line break be enforced before?)
	 * 
	 * the instruction is responsible for the linebreak at the end itself
	 * 
	 * @return
	 */
	boolean inNewLine();

	/**
	 * get the code statement(s) to begin an annotation block
	 * 
	 * @param f
	 *            set of features annotated for the current element
	 * @return
	 */
	String getStartInstruction(Set<IFeature> f);

	/**
	 * get the code statement(s) to end an annotation block
	 * 
	 * @param f
	 *            set of features annotated for the current element
	 * @return
	 */
	String getEndInstruction(Set<IFeature> f);

	CopiedNaiveASTFlattener getPrettyPrinter(ColoredSourceFile sourceFile);

	/**
	 * allows the developer to change the AST before printing it. can be used
	 * for some refactorings. returns the modified AST
	 * 
	 * @param root
	 * @param sourceFile 
	 * @return
	 */
	CompilationUnit refactorAST(CompilationUnit root, ColoredSourceFile sourceFile);
}
