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

import java.util.ArrayList;

public class ASTTextNode extends ASTNode {
	private String[] value;

	public ASTTextNode(String value, IToken token) {
		super(new ArrayList<Property>(), token, token);
		this.value = new String[] { value };
	}

	public ASTTextNode(String[] value, IToken token) {
		super(new ArrayList<Property>(), token, token);
		this.value = value;
	}

	public String getValue() {
		String result = "";
		for (int idx = 0; idx < value.length; idx++) {
			if (idx != 0)
				result += ",";
			result += value[idx];
		}
		return result;
	}

	public String toString() {
		return getValue();
	}

	@Override
	public IASTNode deepCopy() {
		return new ASTTextNode(value, firstToken);
	}
	
	@Override
	public String render() {
		return getValue();
	}
}
