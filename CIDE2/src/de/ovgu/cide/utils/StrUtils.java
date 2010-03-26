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

package de.ovgu.cide.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class StrUtils {

	public static String implode(String[] strs, String glue) {
		boolean first = true;
		String result = "";
		for (String s : strs) {
			if (first)
				first = false;
			else
				result += glue;
			result += s;
		}
		return result;
	}

	public static String implode(Collection<Object> elements, String glue) {
		boolean first = true;
		String result = "";
		for (Object element : elements) {
			if (first)
				first = false;
			else
				result += glue;
			result += element.toString();
		}
		return result;
	}

	public static InputStream strToInputStream(String txt) {
		return new ByteArrayInputStream(txt.getBytes());
	}

	public static String strFromInputStream(InputStream stream)
			throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = stream.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	public static String firstUp(String input) {
		if (input == null || input.equals(""))
			return input;
		return Character.toUpperCase(input.charAt(0)) + input.substring(1);
	}

}
