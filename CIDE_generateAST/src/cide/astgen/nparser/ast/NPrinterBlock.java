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

import cide.astgen.nparser.parser.Token;

public class NPrinterBlock {
	public NPrinterBlock() {
		printerClass = "SimplePrintVisitor";
		printerPackage = null;
	}

	public NPrinterBlock(Token node, Token pkg) {
		printerClass = node.image.substring(1, node.image.length() - 1);
		if (pkg != null)
			printerPackage = pkg.image.substring(1, pkg.image.length() - 1);
		else
			printerPackage = null;
	}

	public final String printerClass;
	public final String printerPackage;
}
