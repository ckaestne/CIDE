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

package de.ovgu.cide.features;

/**
 * extension of the feature model required for the default storage mechanism
 * which addresses features by an id (type long)
 * 
 * @author ckaestne
 * 
 */
public interface IFeatureModelWithID extends IFeatureModel {
	/**
	 * returns a specific feature by a given ID which identifies this feature,
	 * or null if no such feature exists
	 * 
	 * @see IFeature.getId
	 * @param id
	 *            unique featureid in this project
	 * @return the feature or null if no such feature exists
	 */
	IFeature getFeature(long id);
}
