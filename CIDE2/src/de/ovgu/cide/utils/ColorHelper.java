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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import de.ovgu.cide.features.IFeature;

/**
 * helper class with several static methods related to colors and features
 * 
 * @author ckaestne
 * 
 */
public class ColorHelper {

	/**
	 * 17 default colors that can be used by the feature model (expect the
	 * length of this array to change, always check upper bounds)
	 */
	public final static RGB[] DEFAULT_COLORS = { new RGB(0, 255, 0),
			new RGB(255, 0, 0), new RGB(0, 0, 255), new RGB(255, 255, 0),
			new RGB(255, 128, 0), new RGB(0, 128, 0), new RGB(0, 0, 128),
			new RGB(0, 128, 192), new RGB(128, 128, 128), new RGB(128, 128, 0),
			new RGB(128, 64, 0), new RGB(64, 0, 64), new RGB(128, 255, 0),
			new RGB(255, 0, 128), new RGB(255, 128, 192), new RGB(0, 128, 255),
			new RGB(128, 0, 0) };

	/**
	 * calculates the string representation as known from HTML
	 * 
	 * @param rgb
	 *            color
	 * @return string representation (7 characters)
	 */
	public static String rgb2str(RGB rgb) {
		if (rgb == null)
			return "#000000";

		String r = Integer.toHexString(rgb.red).toUpperCase();
		if (r.length() == 1)
			r = "0" + r;
		String g = Integer.toHexString(rgb.green).toUpperCase();
		if (g.length() == 1)
			g = "0" + g;
		String b = Integer.toHexString(rgb.blue).toUpperCase();
		if (b.length() == 1)
			b = "0" + b;
		return "#" + r + g + b;
	}

	/**
	 * creates a new list with all entries and sorts them (in the natural order
	 * of the feature model)
	 * 
	 * @param features
	 * @return
	 */
	public static List<IFeature> sortFeatures(Collection<IFeature> features) {
		List<IFeature> result = new ArrayList<IFeature>(features);
		Collections.sort(result);
		return result;
	}

	/**
	 * algorithm to blend colors. takes several colors as input and determines a
	 * mixed (blended) color
	 * 
	 * @param featureList
	 * @return blended RGB value
	 */
	public static RGB getCombinedRGB(Collection<IFeature> featureList) {
		RGB rgb = new RGB(255, 255, 255);

		if (featureList.size() > 0) {
			for (IFeature feature : featureList) {
				RGB color = feature.getRGB();
				rgb.red += color.red;
				rgb.green += color.green;
				rgb.blue += color.blue;
			}
			rgb.red /= featureList.size() + 1;
			rgb.green /= featureList.size() + 1;
			rgb.blue /= featureList.size() + 1;
		}
		return rgb;
	}

	private static WeakHashMap<RGB, Color> colorCache = new WeakHashMap<RGB, Color>();

	/**
	 * gets a color object for the given RGB value. colors are cached to deal
	 * with scarce resources of the OS.
	 * 
	 * @param rgb
	 *            color in RGB format
	 * @return color for operating system
	 */
	public static Color getColor(RGB rgb) {
		Color color = colorCache.get(rgb);
		if (color == null) {
			color = new Color(Display.getDefault(), rgb);
			colorCache.put(rgb, color);
		}
		return color;
	}

	public static Color getCombinedColor(Collection<IFeature> featureList) {
		return getColor(getCombinedRGB(featureList));
	}

	public static RGB str2rgb(String color) {
		if (color.length() != 7 || color.charAt(0) != '#')
			return new RGB(0, 0, 0);

		int r = Integer.parseInt(color.substring(1, 3), 16);
		int g = Integer.parseInt(color.substring(3, 5), 16);
		int b = Integer.parseInt(color.substring(5, 7), 16);
		return new RGB(r, g, b);
	}
}
