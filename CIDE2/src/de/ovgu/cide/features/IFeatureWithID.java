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

public interface IFeatureWithID extends IFeature {
	/**
	 * returns a fixed ID that is used for making features persistent. only this
	 * feature-id is stored. the id must be unique for a feature inside a
	 * project and must not change when renaming the feature
	 * 
	 * most feature model plugins and the default storage mechanism use Long as
	 * ID type
	 * 
	 * @return
	 */
	public long getId();
}
