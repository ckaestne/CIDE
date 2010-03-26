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

package cide.astgen.nparser.visitor;

import cide.astgen.nparser.ast.NChoice;
import cide.astgen.nparser.ast.NGrammar;
import cide.astgen.nparser.ast.NNonTerminal;
import cide.astgen.nparser.ast.NPrinterBlock;
import cide.astgen.nparser.ast.NProduction;
import cide.astgen.nparser.ast.NTextOnly;
import cide.astgen.nparser.ast.NValue;

public class NVisitor {
	public boolean visit(NGrammar g) {
		return true;
	}

	public boolean visit(NProduction p) {
		return true;
	}

	public boolean visit(NChoice c) {
		return true;
	}

	public boolean visit(NNonTerminal t) {
		return true;
	}

	public boolean visit(NValue t) {
		return true;
	}

	public boolean visit(NTextOnly t) {
		return true;
	}

	public boolean visit(NPrinterBlock t) {
		return true;
	}

	public void postVisit(NGrammar g) {
	}

	public void postVisit(NProduction g) {
	}

	public void postVisit(NChoice g) {
	}

	public void postVisit(NNonTerminal g) {
	}

	public void postVisit(NValue g) {
	}

	public void postVisit(NTextOnly g) {
	}

	public void postVisit(NPrinterBlock g) {
	}

}
