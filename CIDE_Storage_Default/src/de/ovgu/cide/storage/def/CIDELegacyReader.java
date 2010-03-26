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

package de.ovgu.cide.storage.def;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import coloredide.features.Feature;

import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModelWithID;

/**
 * can read the very old serialized format from CIDE's first version
 * 
 * @author ckaestne
 * 
 */
public class CIDELegacyReader {

	static final long LEGACY_SERIALIZED_VERSION = 1l;

	/**
	 * old serialization, where a feature class was directly serialized. needed
	 * to be able to load old files
	 * 
	 * @param out
	 * @param project
	 * @param featureModel
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FeatureModelNotFoundException
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	static HashMap<String, Set<IFeature>> loadLegacySerialization(
			ObjectInputStream out, IFeatureModelWithID featureModel)
			throws IOException, ClassNotFoundException {

		HashMap<String, Set<Feature>> storedMap = (HashMap<String, Set<Feature>>) out
				.readObject();

		HashMap<String, Set<IFeature>> result = emptyMap();
		for (Map.Entry<String, Set<Feature>> entry : storedMap.entrySet()) {
			Set<Feature> colorIds = entry.getValue();
			if (!colorIds.isEmpty()) {
				Set<IFeature> features = new HashSet<IFeature>();
				for (Feature id : colorIds) {
					IFeature feature = featureModel.getFeature(id.getId());
					if (feature != null)
						features.add(feature);
					else
						System.out.println("Unknown feature (legacy): "+id.getId());
				}
				if (!features.isEmpty())
					result.put(entry.getKey(), features);
			}
		}
		return result;
	}

	private static HashMap<String, Set<IFeature>> emptyMap() {
		return new HashMap<String, Set<IFeature>>();
	}
}
