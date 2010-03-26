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

package cide.gast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbstractPrintVisitor extends ASTVisitor {

	private final StringBuffer buffer = new StringBuffer();
	private final PrintStream outputStream;

	private List<String> tokensInCurrentLine = new ArrayList<String>();
	private int indentInCurrentLine = 0;

	public AbstractPrintVisitor(PrintStream out) {
		this.outputStream = out;
	}

	public AbstractPrintVisitor() {
		this.outputStream = null;
	}

	protected void printToken(String token) {
		if (tokensInCurrentLine.size() == 0)
			indentInCurrentLine = indent;
		tokensInCurrentLine.add(token);
	}

	protected void hintIncIndent() {
		indent++;
	}

	protected void hintDecIndent() {
		indent--;
	}

	protected void hintNewLine() {
		closeLine();
	}
	protected void hintSingleSpace() {
		printToken(" ");
	}

	public boolean generateSpaces = true;

	protected void closeLine() {
		for (int i = 0; i < indentInCurrentLine; i++)
			print('\t');

		String lastToken = null;
		Iterator<String> i = tokensInCurrentLine.iterator();
		while (i.hasNext()) {
			String token = i.next();
			if (generateSpaces)
				if (lastToken != null
						&& !getNoSpaceAfterToken().contains(lastToken)
						&& !getNoSpaceBeforeToken().contains(token)
						&& !(lastToken.trim().length()==0))
					print(' ');

			print(token);
			lastToken = token;
		}

		print('\n');

		tokensInCurrentLine.clear();
		indentInCurrentLine = 0;
	}

	private void print(String s) {
		buffer.append(s);
		if (outputStream != null)
			outputStream.print(s);
	}

	private void print(char s) {
		buffer.append(s);
		if (outputStream != null)
			outputStream.print(s);
	}

	private int indent = 0;

	public String getResult() {
		if (tokensInCurrentLine.size() > 0)
			closeLine();
		return buffer.toString();
	}

	private List<String> noSpaceAfterToken;

	protected List<String> getNoSpaceAfterToken() {
		if (noSpaceAfterToken == null) {
			noSpaceAfterToken = new ArrayList<String>();
			noSpaceAfterToken.add("{");
			noSpaceAfterToken.add("(");
			noSpaceAfterToken.add("[");
			noSpaceAfterToken.add("@");
			noSpaceAfterToken.add(".");
		}
		return noSpaceAfterToken;
	}

	private List<String> noSpaceBeforeToken;

	protected List<String> getNoSpaceBeforeToken() {
		if (noSpaceBeforeToken == null) {
			noSpaceBeforeToken = new ArrayList<String>();
			noSpaceBeforeToken.add("}");
			noSpaceBeforeToken.add(")");
			noSpaceBeforeToken.add("]");
			noSpaceBeforeToken.add(";");
			noSpaceBeforeToken.add(".");
		}
		return noSpaceBeforeToken;
	}

}
