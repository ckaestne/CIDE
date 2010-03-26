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

package cide.gparser;

import cide.gast.IToken;

public class WToken implements IToken {

	private int offset, length;

	public WToken(Token token) {
		offset = token.offset;
		length = token.length;
//		if (token.image==null || token.image.length()==0) length=0;
		image = token.image;
	}

	private String image;

	@Override
	public String toString() {
		return "<" + offset + " - " + (offset + length) + " -> " + image + ">";
	}

	public int getLength() {
		return length;
	}

	public int getOffset() {
		return offset;
	}

}
