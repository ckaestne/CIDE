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

package de.ovgu.cide.fm.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.RGB;

import de.ovgu.cide.utils.ColorHelper;

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

		// public IFeature getParentFeature(IProject project) {
		// return null;
		// }
	};

}
