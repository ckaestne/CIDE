package coloredide.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import coloredide.features.IFeature;

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

	public static Color getCombinedColor(Collection<IFeature> featureList) {
		RGB rgb = getCombinedRGB(featureList);
		Color color = colorCache.get(rgb);
		if (color == null) {
			color = new Color(Display.getDefault(), rgb);
			colorCache.put(rgb, color);
		}
		return color;
	}
}
