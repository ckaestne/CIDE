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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class NCreateFileVisitor extends NVisitor {

	protected String targetPackage;
	protected PrintStream out;

	public NCreateFileVisitor(File targetDir, String fileName,
			String targetPackage) throws FileNotFoundException {
		this(new PrintStream(new File(targetDir, fileName)), targetPackage);
	}

	public NCreateFileVisitor(PrintStream out, String targetPackage) {
		this.targetPackage = targetPackage;
		this.out = out;
	}

	private void print(String p) {
		out.print(p);
	}

	protected void println(String p, int indent) {
		for (int i = 0; i < indent; i++)
			print("\t");
		out.println(p);
	}

}