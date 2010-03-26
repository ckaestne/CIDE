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

import java.util.List;

import cide.astgen.nparser.visitor.NVisitor;

public class NValue extends NAbstractValue {

	public NValue(NChoice parent, Type type, String name) {
		super(parent, name, type);
	}

	public void accept(NVisitor visitor) {
		visitor.visit(this);
		visitor.postVisit(this);
	}

	@Override
	public String getName() {

		return "<" + super.getName() + ">";
	}

	@Override
	public String genVariablePlainName() {
		return super.genVariablePlainName().toLowerCase();
	}

	public String genVariablePlainType() {
		return "ASTStringNode";
	}

	@Override
	public NAbstractValue cloneValue() {
		return new NValue(parent, type, name);
	}


}