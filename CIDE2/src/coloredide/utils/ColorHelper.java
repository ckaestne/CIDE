package coloredide.utils;

import org.eclipse.swt.graphics.RGB;

public class ColorHelper {

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

}
