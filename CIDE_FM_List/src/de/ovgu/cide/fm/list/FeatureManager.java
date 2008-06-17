package de.ovgu.cide.fm.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import coloredide.utils.ColorHelper;

/**
 * provides a list of all features, defines features by ids and colors.
 * 
 * @author cKaestner
 */
public class FeatureManager {

	private static List<FixedFeature> featureList;

	public static List<FixedFeature> getFeatures() {
		if (featureList != null)
			return featureList;
		featureList = new ArrayList<FixedFeature>();
		for (int fid = 1; fid <= 50; fid++) {
			featureList.add(new FixedFeature(fid,
					ColorHelper.DEFAULT_COLORS[fid
							% ColorHelper.DEFAULT_COLORS.length]));
		}
		featureList = Collections.unmodifiableList(featureList);
		return featureList;
	}

	public static final FixedFeature BASEFEATURE = new FixedFeature(-1,
			new RGB(0, 0, 0)) {
		private static final long serialVersionUID = 1L;

		public String getShortName(IProject project) {
			return "Base";
		}

		public boolean isVisible(IProject project) {
			return true;
		}

		// public IFeature getParentFeature(IProject project) {
		// return null;
		// }
	};

}
