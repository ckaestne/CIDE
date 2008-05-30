package coloredide.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import cide.features.IFeature;

/**
 * provides a list of all features, defines features by ids and colors.
 * 
 * @author cKaestner
 */
public class FeatureManager {

	protected final static RGB[] colors = { new RGB(0, 255, 0),
			new RGB(255, 0, 0), new RGB(0, 0, 255), new RGB(255, 255, 0),
			new RGB(255, 128, 0), new RGB(0, 128, 0), new RGB(0, 0, 128),
			new RGB(0, 128, 192), new RGB(128, 128, 128), new RGB(128, 128, 0),
			new RGB(128, 64, 0), new RGB(64, 0, 64), new RGB(128, 255, 0),
			new RGB(255, 0, 128), new RGB(255, 128, 192), new RGB(0, 128, 255),
			new RGB(128, 0, 0) };

	private static List<Feature> featureList;

	public static List<Feature> getFeatures() {
		if (featureList != null)
			return featureList;
		featureList = new ArrayList<Feature>();
		for (int fid = 1; fid <= 50; fid++) {
			featureList.add(new Feature(fid, colors[fid % colors.length]));
		}
		featureList = Collections.unmodifiableList(featureList);
		return featureList;
	}

	public static final Feature BASEFEATURE = new Feature(-1, new RGB(0, 0, 0)) {
		private static final long serialVersionUID = 1L;

		public String getShortName(IProject project) {
			return "Base";
		}

		public boolean isVisible(IProject project) {
			return true;
		}

//		public IFeature getParentFeature(IProject project) {
//			return null;
//		}
	};

	public static List<Feature> getVisibleFeatures(IProject project) {
		List<Feature> featureList = new ArrayList<Feature>();
		for (Feature f : getFeatures()) {
			if (f.isVisible(project))
				featureList.add(f);
		}
		return Collections.unmodifiableList(featureList);
	}

	public static RGB getCombinedRGB(Collection<Feature> featureList,
			IProject project) {
		RGB rgb = new RGB(255, 255, 255);

		if (featureList.size() > 0) {
			FeatureNameManager mgr = FeatureNameManager
					.getFeatureNameManager(project);
			for (Feature feature : featureList) {
				RGB color = mgr.getFeatureColor(feature);
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

	private static WeakHashMap<IProject, WeakHashMap<RGB, Color>> projectColorCache = new WeakHashMap<IProject, WeakHashMap<RGB, Color>>();

	public static Color getCombinedColor(Collection<Feature> featureList,
			IProject project) {
		RGB rgb = getCombinedRGB(featureList, project);
		WeakHashMap<RGB, Color> colorCache = projectColorCache.get(project);
		if (colorCache == null) {
			colorCache = new WeakHashMap<RGB, Color>();
			projectColorCache.put(project, colorCache);
		}
		Color color = colorCache.get(rgb);
		if (color == null) {
			color = new Color(Display.getDefault(), rgb);
			colorCache.put(rgb, color);
		}
		return color;
	}
}
