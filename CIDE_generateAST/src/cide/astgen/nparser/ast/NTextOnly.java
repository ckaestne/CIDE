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

package cide.astgen.nparser.ast;

import cide.astgen.nparser.ast.NAbstractValue.Type;
import cide.astgen.nparser.visitor.NVisitor;

/**
 * element of a choice, that only consists of one or more text elements but no
 * value or non-terminal
 * 
 * @author cKaestner
 * 
 */
public class NTextOnly extends NAbstractValue {

	private static int generateIdx = 0;

	public NTextOnly(NChoice parent, Type type) {
		super(parent, generateName(), type);
	}

	private static String generateName() {
		return "text" + (++generateIdx);
	}

	@Override
	public void accept(NVisitor visitor) {
		visitor.visit(this);
		visitor.postVisit(this);
	}

	@Override
	public String genVariablePlainType() {
		return "ASTTextNode";
	}

	@Override
	public NAbstractValue cloneValue() {
		return new NTextOnly(parent, type);
	}
}
