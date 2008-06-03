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

	private static List<FixedFeature> featureList;

	public static List<FixedFeature> getFeatures() {
		if (featureList != null)
			return featureList;
		featureList = new ArrayList<FixedFeature>();
		for (int fid = 1; fid <= 50; fid++) {
			featureList.add(new FixedFeature(fid, colors[fid % colors.length]));
		}
		featureList = Collections.unmodifiableList(featureList);
		return featureList;
	}

	public static final FixedFeature BASEFEATURE = new FixedFeature(-1, new RGB(0, 0, 0)) {
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




	
}
