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

import java.util.HashMap;

/**
 * annotations are used for choices. they are the first term in a choice.
 * 
 * alternatively an annotation can be specified for a production and is thus
 * valid for all choices therein.
 * 
 * @author ckaestne
 * 
 */
public class NAnnotation {

	private String name;
	public final HashMap<String, String> values = new HashMap<String, String>();

	public NAnnotation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addValue(String name, String value) {
		values.put(name, value);
	}

	@Override
	public String toString() {
		String r = "@" + name + "(";
		for (String k : values.keySet())
			r += k + "=" + values.get(k);
		r += ")";
		return r;
	}
}
