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

import java.util.ArrayList;
import java.util.List;

import cide.astgen.nparser.visitor.NVisitor;

import com.sun.corba.se.pept.transport.Acceptor;

public class NGrammar {
	final public List<NProduction> productions;
	private String introduction;
	private NPrinterBlock printerBlock = new NPrinterBlock();

	public NGrammar(List<NProduction> productions) {
		this.productions = productions;
	}

	public NGrammar(String introduction) {
		this(new ArrayList<NProduction>());
		this.introduction = introduction;
	}

	public void accept(NVisitor visitor) {
		visitor.visit(printerBlock);
		visitor.postVisit(printerBlock);
		if (visitor.visit(this))
			for (NProduction p : productions)
				p.accept(visitor);
		visitor.postVisit(this);
	}

	public NProduction findProduction(String name) {
		for (NProduction production : productions)
			if (production.getName().equals(name))
				return production;
		return null;
	}

	public String getIntroduction() {
		return introduction;
	}

	@Override
	public String toString() {
		String r = "";
		for (NProduction k : productions)
			r += k.toString() + "\n\n";
		return r;
	}

	public void setPrinterBlock(NPrinterBlock pb) {
		this.printerBlock = pb;
	}

}
